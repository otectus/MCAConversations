package dev.otectus.mcaconversations.compat.capitals;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.CapitalChronicleEventView;
import dev.otectus.mcaconversations.compat.CapitalCourtView;
import dev.otectus.mcaconversations.compat.CapitalRelationView;
import dev.otectus.mcaconversations.compat.CapitalStandingView;
import dev.otectus.mcaconversations.compat.CapitalsBridge;
import dev.otectus.mcaconversations.compat.CapitalsCapability;
import dev.otectus.mcaconversations.compat.CapitalsStatus;
import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The real {@link CapitalsBridge}, backed by {@link CapitalsHandles}.
 *
 * <p>Instantiated by name from {@code CapitalsCompat} only after {@code ModList} has confirmed
 * Capitals is present, which is why this class may reference {@link CapitalsHandles} and why nothing
 * outside this package may reference <em>it</em>. Constructing it forces the binding, so
 * {@link #status()} is meaningful the moment the object exists and the caller can log one accurate
 * line.
 *
 * <p><b>No Capitals object is ever stored in a view.</b> The two caches here hold only uuids and the
 * opaque record objects Capitals itself owns, and a view is rebuilt from the live record every time
 * it is asked for, so a court that changed between two reads never answers from a stale snapshot.
 */
public final class ReflectiveCapitalsBridge implements CapitalsBridge {

    /** Above this many cached residents the map is dropped whole rather than swept. */
    private static final int RESIDENT_CACHE_LIMIT = 4096;

    private static final int DEFAULT_CACHE_TICKS = 200;

    /** One resident's resolved capital, and the tick after which it must be resolved again. */
    private record CachedResidency(long expiryTick, Optional<UUID> capitalId) {
    }

    private final CapitalsStatus status;
    private final Set<CapitalsCapability> capabilities;

    /**
     * Capital records by id, so a chronicle read or a standing read does not rescan the registry.
     * Holds Capitals' own live objects, which is safe here and only here: this class is never loaded
     * without Capitals present.
     */
    private final Map<UUID, Object> records = new ConcurrentHashMap<>();

    private final Map<UUID, CachedResidency> residents = new ConcurrentHashMap<>();

    public ReflectiveCapitalsBridge() {
        CapitalsBinding.Resolution resolution = CapitalsHandles.resolution();
        this.status = resolution.status();
        this.capabilities = resolution.capabilities();
    }

    @Override
    public CapitalsStatus status() {
        return status;
    }

    @Override
    public Set<CapitalsCapability> capabilities() {
        return capabilities;
    }

    @Override
    public List<String> unresolvedMembers() {
        return CapitalsHandles.resolution().unresolved();
    }

    // --- courts ----------------------------------------------------------------------------------

    @Override
    public Optional<CapitalCourtView> courtOfVillage(ServerLevel level, int villageId) {
        if (level == null) {
            return Optional.empty();
        }
        return view(level, CapitalsHandles.capitalForVillage(level, villageId));
    }

    @Override
    public Optional<CapitalCourtView> courtOfResident(ServerLevel level, UUID villager) {
        if (level == null || villager == null) {
            return Optional.empty();
        }
        long now = level.getGameTime();
        CachedResidency cached = residents.get(villager);
        if (cached != null && cached.expiryTick() > now) {
            return cached.capitalId().map(records::get).flatMap(record -> view(level, record));
        }

        Object record = residentRecord(level, villager);
        Optional<UUID> id = record == null ? Optional.empty() : CapitalsHandles.capitalId(record);
        if (residents.size() > RESIDENT_CACHE_LIMIT) {
            residents.clear();
        }
        residents.put(villager, new CachedResidency(now + cacheTicks(), id));
        return view(level, record);
    }

    /**
     * The villager's home village first, because that is a map lookup Capitals already indexes, and
     * only then the resolver, which scans. Both are needed: a villager living outside the capital's
     * own village can still hold a title in it.
     */
    @Nullable
    private Object residentRecord(ServerLevel level, UUID villager) {
        Entity entity = level.getEntity(villager);
        if (entity != null) {
            OptionalInt villageId = McaCompat.getHomeVillageId(entity);
            if (villageId.isPresent()) {
                Object record = CapitalsHandles.capitalForVillage(level, villageId.getAsInt());
                if (record != null) {
                    return record;
                }
            }
        }
        return CapitalsHandles.findCapitalForEntity(level, villager);
    }

    @Override
    public List<CapitalCourtView> allCourts(MinecraftServer server) {
        if (server == null) {
            return List.of();
        }
        List<CapitalCourtView> out = new ArrayList<>();
        for (Object record : CapitalsHandles.allCapitalRecords()) {
            ServerLevel level = levelOfRecord(server, record);
            if (level != null) {
                view(level, record).ifPresent(out::add);
            }
        }
        return List.copyOf(out);
    }

    @Override
    public Optional<ServerLevel> levelOf(MinecraftServer server, CapitalCourtView court) {
        if (server == null || court == null) {
            return Optional.empty();
        }
        Object record = recordOf(court.capitalId());
        return record == null ? Optional.empty() : Optional.ofNullable(levelOfRecord(server, record));
    }

    @Nullable
    private ServerLevel levelOfRecord(MinecraftServer server, Object record) {
        return CapitalsHandles.capitalLevel(server, record) instanceof ServerLevel level ? level : null;
    }

    /** Builds a view from a live record, and remembers the record so later reads can find it. */
    private Optional<CapitalCourtView> view(ServerLevel level, @Nullable Object record) {
        if (record == null) {
            return Optional.empty();
        }
        Optional<UUID> capitalId = CapitalsHandles.capitalId(record);
        if (capitalId.isEmpty()) {
            return Optional.empty();
        }
        records.put(capitalId.get(), record);

        int villageId = CapitalsHandles.villageId(record);
        return Optional.of(new CapitalCourtView(
                capitalId.get(),
                villageId,
                CapitalsHandles.dimensionId(record),
                capitalName(level, villageId),
                CapitalsHandles.state(record),
                CapitalsHandles.sovereign(record),
                CapitalsHandles.sovereignFemale(record),
                CapitalsHandles.consort(record),
                CapitalsHandles.heir(record),
                CapitalsHandles.dowager(record),
                CapitalsHandles.hand(record),
                CapitalsHandles.commander(record),
                CapitalsHandles.herald(record),
                CapitalsHandles.grandMaester(record),
                CapitalsHandles.masterOfLaws(record),
                CapitalsHandles.mourning(record),
                CapitalsHandles.playerSovereign(record),
                CapitalsHandles.playerSovereignId(record),
                CapitalsHandles.playerSovereignName(record),
                CapitalsHandles.chronicleEntries(record).size()));
    }

    /**
     * A capital has no name of its own: it is named for its MCA village. Read through this mod's own
     * MCA binding first, so one mod owns that answer, and only fall back to Capitals' MCA bridge when
     * ours comes back blank.
     */
    private String capitalName(ServerLevel level, int villageId) {
        String name = McaCompat.getVillageName(level, villageId).orElse("");
        if (!name.isBlank()) {
            return name;
        }
        return CapitalsHandles.villageName(level, villageId);
    }

    @Nullable
    private Object recordOf(UUID capitalId) {
        Object cached = records.get(capitalId);
        if (cached != null && CapitalsHandles.capitalId(cached).filter(capitalId::equals).isPresent()) {
            return cached;
        }
        for (Object record : CapitalsHandles.allCapitalRecords()) {
            Optional<UUID> id = CapitalsHandles.capitalId(record);
            if (id.isPresent()) {
                records.put(id.get(), record);
                if (capitalId.equals(id.get())) {
                    cached = record;
                }
            }
        }
        return cached;
    }

    // --- villagers -------------------------------------------------------------------------------

    @Override
    public CapitalStandingView standingOf(ServerLevel level, CapitalCourtView court, UUID villager,
                                          @Nullable Entity loadedEntity) {
        if (level == null || court == null || villager == null) {
            return CapitalStandingView.none();
        }
        Object record = recordOf(court.capitalId());
        if (record == null) {
            return CapitalStandingView.none();
        }

        Object title = CapitalsHandles.resolvedTitle(level, villager);
        String titleId = CapitalsHandles.titleId(title);
        if ("none".equals(titleId)) {
            // A noble outside the resolver's reach still holds a title, and it is the only one they
            // will ever be addressed by.
            String noble = CapitalsHandles.nobleTitle(villager);
            if (!noble.isEmpty()) {
                titleId = noble;
            }
        }

        Object house = CapitalsHandles.houseForMember(level, court.capitalId(), villager);
        Object identity = CapitalsHandles.identityOf(loadedEntity);
        String houseName = CapitalsHandles.houseName(house);
        if (houseName.isEmpty()) {
            houseName = CapitalsHandles.identityHouseName(identity);
        }

        return new CapitalStandingView(
                titleId,
                CapitalsHandles.titleRank(title),
                CapitalsHandles.displayTitle(level, villager),
                CapitalsHandles.courtOffice(level, villager),
                CapitalsHandles.crownStanding(level, record, villager),
                CapitalsHandles.royalHousehold(record, villager),
                CapitalsHandles.royalGuard(record, villager),
                CapitalsHandles.disgraced(record, villager),
                houseName,
                CapitalsHandles.houseTier(house),
                CapitalsHandles.identityHouseWords(identity),
                CapitalsHandles.identitySurname(identity));
    }

    @Override
    public List<CapitalRelationView> relationsOf(ServerLevel level, CapitalCourtView court) {
        if (level == null || court == null) {
            return List.of();
        }
        UUID self = court.capitalId();
        List<CapitalRelationView> out = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : CapitalsHandles.relationsSnapshot(level).entrySet()) {
            Optional<UUID> first = CapitalsHandles.relationFirst(entry.getKey());
            Optional<UUID> second = CapitalsHandles.relationSecond(entry.getKey());
            if (first.isEmpty() || second.isEmpty()) {
                continue;
            }
            UUID other;
            if (self.equals(first.get())) {
                other = second.get();
            } else if (self.equals(second.get())) {
                other = first.get();
            } else {
                continue;
            }
            out.add(new CapitalRelationView(
                    other,
                    nameOfCapital(level, other),
                    CapitalsHandles.relationState(entry.getValue()),
                    CapitalsHandles.relationBand(entry.getValue()),
                    CapitalsHandles.relationScore(entry.getValue())));
        }
        return List.copyOf(out);
    }

    /** Another capital's name, resolved in <em>its</em> level rather than in the caller's. */
    private String nameOfCapital(ServerLevel level, UUID capitalId) {
        Object record = recordOf(capitalId);
        if (record == null) {
            return "";
        }
        MinecraftServer server = level.getServer();
        ServerLevel home = server == null ? level : levelOfRecord(server, record);
        return capitalName(home == null ? level : home, CapitalsHandles.villageId(record));
    }

    @Override
    public List<CapitalChronicleEventView> chronicleSince(ServerLevel level, CapitalCourtView court,
                                                          int fromIndex, int max) {
        if (level == null || court == null || max <= 0) {
            return List.of();
        }
        Object record = recordOf(court.capitalId());
        if (record == null) {
            return List.of();
        }
        // Read from the live record, never from the view: the poller's whole job is to notice what
        // the chronicle gained since the snapshot it is holding.
        List<String> entries = CapitalsHandles.chronicleEntries(record);
        int from = Math.max(0, fromIndex);
        if (from >= entries.size()) {
            return List.of();
        }
        int start = Math.max(from, entries.size() - max);

        List<CapitalChronicleEventView> out = new ArrayList<>();
        for (int index = start; index < entries.size(); index++) {
            String stored = entries.get(index);
            Object decoded = CapitalsHandles.decodeEntry(stored);
            String text = decoded == null
                    ? CapitalsHandles.renderStoredEntry(stored) : CapitalsHandles.entryText(decoded);
            if (text.isEmpty()) {
                text = CapitalsHandles.renderStoredEntry(stored);
            }
            out.add(new CapitalChronicleEventView(
                    index,
                    decoded == null ? 0L : CapitalsHandles.entryDay(decoded),
                    CapitalsHandles.entryType(decoded),
                    decoded == null ? "" : CapitalsHandles.entryTranslationKey(decoded),
                    text));
        }
        return List.copyOf(out);
    }

    @Override
    public Optional<UUID> declaredCapitalOf(ServerLevel level, UUID player) {
        return level == null || player == null
                ? Optional.empty() : CapitalsHandles.declaredCapitalId(level, player);
    }

    @Override
    public String displayName(ServerLevel level, CapitalCourtView court, UUID entity) {
        if (level == null || court == null || entity == null) {
            return "";
        }
        Object record = recordOf(court.capitalId());
        String name = record == null ? "" : CapitalsHandles.resolveDisplayName(level, record, entity);
        return name.isBlank() ? CapitalsHandles.familyNodeName(level, entity) : name;
    }

    /** Config is not loaded during early startup; the documented default stands in for that window. */
    private static int cacheTicks() {
        try {
            return McaConversationsConfig.COMMON.capitalsContextCacheTicks.get();
        } catch (Throwable t) {
            return DEFAULT_CACHE_TICKS;
        }
    }
}
