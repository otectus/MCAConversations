package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.RelationshipBand;
import dev.otectus.mcaconversations.conversation.Relationships;
import dev.otectus.mcaconversations.personality.Personalities;
import dev.otectus.mcaconversations.personality.VoiceFamily;
import dev.otectus.mcaconversations.profession.ProfessionProfile;
import dev.otectus.mcaconversations.profession.ProfessionProfileLoader;
import dev.otectus.mcaconversations.season.SeasonContext;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;

/**
 * Everything MCA knows about the speaker: who they are, what they do, where they live, who they are
 * related to, and what the player is to them (spec §7.2, §7.3).
 *
 * <p>The reason this source is worth its size is that MCA has always had these answers and this mod
 * has never asked for most of them. Profession was inferred from a <em>translated display string</em>;
 * work activity, assigned chore, workplace, home, building type, family tree and village population
 * were not read at all. A farmer could therefore be told apart from a librarian, but two farmers could
 * not be told apart from each other, and no scene could know it was being spoken inside a library or
 * next to a field.
 *
 * <p>Every read goes through {@link McaCompat}, so no MCA type is named here and a missed compat
 * handle degrades exactly one field. When MCA is absent altogether the source reports
 * {@link ContextCapabilities.Status#ABSENT} and marks all its fields unavailable in one call — which
 * is what makes the base snapshot provably identical with and without the integration (spec §21.1).
 */
public final class McaContextSource implements ConversationContextSource {

    public static final String ID = "mca";

    /** Coarse tags worth noticing in a villager's own inventory. Presence only, never counts. */
    private static final List<String> CARRIED_PROBES = List.of(
            "forge:ingots/iron", "forge:tools", "forge:crops", "forge:seeds",
            "minecraft:fishes", "minecraft:logs", "minecraft:flowers", "forge:leather",
            "forge:string", "forge:feathers");

    /** How many named family members a scene may pick from. Bounded so no slot binder scans a tree. */
    private static final int MAX_FAMILY_NAMES = 8;

    /** Radius, in blocks, within which another villager counts as present for a group scene. */
    private static final double NEARBY_RADIUS = 12.0;

    private static final int MAX_NEARBY = 3;

    /** How close to the assigned workplace still counts as "at work". */
    private static final double WORKSITE_RADIUS_SQ = 12.0 * 12.0;

    private static final List<ContextKey<?>> DECLARES = List.of(
            ContextKeys.SPEAKER_UUID, ContextKeys.SPEAKER_NAME, ContextKeys.SPEAKER_AGE,
            ContextKeys.SPEAKER_PERSONALITY, ContextKeys.SPEAKER_VOICE_FAMILY,
            ContextKeys.SPEAKER_MOOD, ContextKeys.SPEAKER_MARRIED,
            ContextKeys.WORK_PROFESSION_ID, ContextKeys.WORK_PROFESSION_NAME, ContextKeys.WORK_ARCHETYPE,
            ContextKeys.WORK_ACTIVITY, ContextKeys.WORK_CHORE, ContextKeys.WORK_AT_WORKSITE,
            ContextKeys.WORK_MATERIAL_TAGS,
            ContextKeys.PLACE_VILLAGE_ID, ContextKeys.PLACE_VILLAGE_NAME, ContextKeys.PLACE_LOCATION,
            ContextKeys.PLACE_AWAY_FROM_HOME,
            ContextKeys.TIME_SEASON, ContextKeys.TIME_HOLIDAY,
            ContextKeys.WEATHER_RELEVANT,
            ContextKeys.PLAYER_HEARTS, ContextKeys.PLAYER_RELATIONSHIP_BAND,
            ContextKeys.PLAYER_IS_SPOUSE, ContextKeys.PLAYER_IS_FAMILY,
            ContextKeys.SOCIAL_FAMILY_NAMES, ContextKeys.SOCIAL_NEARBY,
            ContextKeys.SOCIAL_VILLAGE_POPULATION);

    @Override
    public String id() {
        return ID;
    }

    @Override
    public List<ContextKey<?>> declares() {
        return DECLARES;
    }

    @Override
    public boolean isAvailable(ContextRequest request) {
        return request.villager() != null && McaCompat.isMcaVillager(request.villager());
    }

    @Override
    public void contribute(ContextSnapshotBuilder builder, ContextRequest request) {
        Entity villager = request.villager();
        if (villager == null || !McaCompat.isMcaVillager(villager)) {
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.ABSENT,
                    villager == null ? "no villager" : "not an MCA villager");
            return;
        }
        ServerPlayer player = request.player();
        boolean degraded = false;

        // --- Volatile ------------------------------------------------------------------------------
        Optional<String> chore = McaCompat.getCurrentChore(villager);
        builder.put(ContextKeys.WORK_CHORE, chore);
        builder.put(ContextKeys.WORK_ACTIVITY, activityOf(villager, chore));
        builder.put(ContextKeys.SPEAKER_MOOD, McaCompat.getMoodName(villager)
                .map(m -> m.toLowerCase(Locale.ROOT)));
        Optional<BlockPos> workplace = McaCompat.getWorkplace(villager);
        builder.put(ContextKeys.WORK_AT_WORKSITE,
                workplace.map(pos -> pos.distSqr(villager.blockPosition()) <= WORKSITE_RADIUS_SQ));

        OptionalInt villageId = McaCompat.getHomeVillageId(villager);
        builder.put(ContextKeys.PLACE_LOCATION, locationToken(villager, workplace, villageId));
        builder.put(ContextKeys.PLACE_AWAY_FROM_HOME, awayFromHome(villager, villageId));
        builder.put(ContextKeys.SOCIAL_NEARBY, nearbyNames(villager));

        if (request.volatileOnly()) {
            builder.reportCapability(ContextCapabilities.Status.READY, "");
            return;
        }

        // --- Speaker -------------------------------------------------------------------------------
        builder.put(ContextKeys.SPEAKER_UUID, villager.getUUID());
        builder.put(ContextKeys.SPEAKER_NAME, McaCompat.getVillagerName(villager));
        builder.put(ContextKeys.SPEAKER_AGE, McaCompat.getAgeGroup(villager));
        String personality = Personalities.normalize(McaCompat.getPersonality(villager).orElse(null));
        if (personality == null || personality.isBlank()) {
            builder.unknown(ContextKeys.SPEAKER_PERSONALITY);
            builder.unknown(ContextKeys.SPEAKER_VOICE_FAMILY);
            degraded = true;
        } else {
            builder.put(ContextKeys.SPEAKER_PERSONALITY, personality);
            builder.put(ContextKeys.SPEAKER_VOICE_FAMILY, VoiceFamily.of(personality).key());
        }
        builder.put(ContextKeys.SPEAKER_MARRIED, McaCompat.isMarried(villager));

        // --- Work ----------------------------------------------------------------------------------
        Optional<String> professionId = McaCompat.getProfessionId(villager);
        if (professionId.isEmpty()) {
            degraded = true;
        }
        builder.put(ContextKeys.WORK_PROFESSION_ID, professionId);
        String displayFallback = McaCompat.getProfessionText(villager)
                .map(component -> component.getString())
                .orElse("villager");
        builder.put(ContextKeys.WORK_PROFESSION_NAME, displayFallback);
        ProfessionProfile profile = ProfessionProfileLoader.profile(professionId.orElse(null), displayFallback);
        builder.put(ContextKeys.WORK_ARCHETYPE, profile.archetype().key());
        builder.put(ContextKeys.WORK_MATERIAL_TAGS, McaCompat.getCarriedTags(villager, CARRIED_PROBES));
        builder.put(ContextKeys.WEATHER_RELEVANT, profile.weatherAffinity());

        // --- Place and calendar --------------------------------------------------------------------
        if (villageId.isPresent()) {
            builder.put(ContextKeys.PLACE_VILLAGE_ID, villageId.getAsInt());
            builder.put(ContextKeys.PLACE_VILLAGE_NAME, McaCompat.getHomeVillageName(villager));
            builder.put(ContextKeys.SOCIAL_VILLAGE_POPULATION,
                    population(villager, villageId.getAsInt()));
        } else {
            // A wanderer genuinely has no village. That is unknown, not unavailable: MCA answered.
            builder.unknown(ContextKeys.PLACE_VILLAGE_ID);
            builder.unknown(ContextKeys.PLACE_VILLAGE_NAME);
            builder.unknown(ContextKeys.SOCIAL_VILLAGE_POPULATION);
        }
        builder.put(ContextKeys.TIME_SEASON, SeasonContext.seasonBucket(villager));
        builder.put(ContextKeys.TIME_HOLIDAY, SeasonContext.holidayBucket(villager));

        // --- Player relationship --------------------------------------------------------------------
        if (player == null) {
            builder.unknown(ContextKeys.PLAYER_HEARTS);
            builder.unknown(ContextKeys.PLAYER_RELATIONSHIP_BAND);
            builder.unknown(ContextKeys.PLAYER_IS_SPOUSE);
            builder.unknown(ContextKeys.PLAYER_IS_FAMILY);
        } else {
            builder.put(ContextKeys.PLAYER_HEARTS, McaCompat.getHearts(player, villager));
            RelationshipBand band = Relationships.bandOf(villager, player);
            builder.put(ContextKeys.PLAYER_RELATIONSHIP_BAND, band.key());
            builder.put(ContextKeys.PLAYER_IS_SPOUSE,
                    McaCompat.isMarriedToPlayer(villager, player.getUUID()));
            builder.put(ContextKeys.PLAYER_IS_FAMILY, band == RelationshipBand.FAMILY);
        }

        // --- Family names ----------------------------------------------------------------------------
        builder.put(ContextKeys.SOCIAL_FAMILY_NAMES, familyNames(villager));

        builder.reportCapability(
                degraded ? ContextCapabilities.Status.DEGRADED : ContextCapabilities.Status.READY,
                degraded ? "one or more MCA handles did not resolve" : "");
    }

    /**
     * A coarse activity token.
     *
     * <p>Derived from the two states MCA reports directly (panic, grief) plus the assigned chore and
     * the time of day, rather than from Minecraft's brain schedule. The schedule is finer-grained than
     * anything worth authoring against, and its activity ids move between versions; these five tokens
     * are the ones initiative suppression actually needs (spec §11.2).
     */
    private static String activityOf(Entity villager, Optional<String> chore) {
        if (McaCompat.isPanicking(villager)) {
            return "panic";
        }
        if (McaCompat.isGrieving(villager)) {
            return "grieve";
        }
        if (chore.isPresent() && !"none".equals(chore.get())) {
            return "work";
        }
        long time = villager.level() == null ? 0L : Math.floorMod(villager.level().getDayTime(), 24000L);
        if (time >= 12000L) {
            return "rest";
        }
        return time >= 9000L ? "meet" : "work";
    }

    /**
     * The semantic location token of spec §17.4.
     *
     * <p>Asked of MCA's building registry first, because a building type is what a villager would
     * actually name; falls back to the coarse worksite/outdoors distinction so an unregistered
     * building still yields something honest instead of an invented room.
     */
    private static String locationToken(Entity villager, Optional<BlockPos> workplace, OptionalInt villageId) {
        if (!(villager.level() instanceof ServerLevel level) || villageId.isEmpty()) {
            return "unknown";
        }
        Optional<String> building = McaCompat.getBuildingTypeAt(level, villageId.getAsInt(),
                villager.blockPosition());
        if (building.isPresent()) {
            String type = building.get();
            return switch (type) {
                case "house", "home", "big_house" -> "home";
                case "inn", "tavern" -> "inn";
                case "library" -> "library";
                case "smithy", "blacksmith", "armory", "forge" -> "smithy";
                case "meeting_point", "bell", "town_hall" -> "meeting";
                default -> type;
            };
        }
        if (workplace.map(pos -> pos.distSqr(villager.blockPosition()) <= WORKSITE_RADIUS_SQ).orElse(false)) {
            return "workplace";
        }
        return "outdoors";
    }

    private static Boolean awayFromHome(Entity villager, OptionalInt homeVillageId) {
        if (homeVillageId.isEmpty() || !(villager.level() instanceof ServerLevel level)) {
            return null;
        }
        OptionalInt here = McaCompat.findNearestVillageId(level, villager.blockPosition(), 64);
        return here.isEmpty() || here.getAsInt() != homeVillageId.getAsInt();
    }

    private static Integer population(Entity villager, int villageId) {
        if (!(villager.level() instanceof ServerLevel level)) {
            return null;
        }
        OptionalInt population = McaCompat.getVillagePopulation(level, villageId);
        return population.isEmpty() ? null : population.getAsInt();
    }

    /**
     * Names of <b>living</b> close family, bounded and in a deterministic order.
     *
     * <p>The deceased filter is the point. A scene that binds "how is your sister?" to a name MCA has
     * marked dead is failure mode 4 — referent drift — and the cheapest place to prevent it is here,
     * before any slot binder ever sees the name (spec §2.4).
     */
    private static List<String> familyNames(Entity villager) {
        if (!(villager.level() instanceof ServerLevel level)) {
            return List.of();
        }
        UUID self = villager.getUUID();
        List<UUID> candidates = new ArrayList<>();
        McaCompat.getPartnerFromTree(level, self).ifPresent(candidates::add);
        candidates.addAll(McaCompat.getParents(level, self));
        candidates.addAll(sorted(McaCompat.getSiblings(level, self)));
        candidates.addAll(sorted(McaCompat.getChildren(level, self)));

        List<String> names = new ArrayList<>();
        for (UUID uuid : candidates) {
            if (names.size() >= MAX_FAMILY_NAMES) {
                break;
            }
            if (uuid == null || uuid.equals(self) || McaCompat.isDeceased(level, uuid)) {
                continue;
            }
            McaCompat.familyTreeName(level, uuid)
                    .filter(name -> !name.isBlank() && !names.contains(name))
                    .ifPresent(names::add);
        }
        return List.copyOf(names);
    }

    /** UUID order, so two snapshots of an unchanged family produce the same list and fingerprint. */
    private static List<UUID> sorted(Set<UUID> uuids) {
        List<UUID> out = new ArrayList<>(uuids);
        out.sort(UUID::compareTo);
        return out;
    }

    /** Loaded MCA villagers close enough to plausibly overhear, capped for the group-scene budget. */
    private static List<String> nearbyNames(Entity villager) {
        List<String> names = new ArrayList<>();
        try {
            for (Entity other : villager.level().getEntities(villager,
                    villager.getBoundingBox().inflate(NEARBY_RADIUS), McaCompat::isMcaVillager)) {
                if (names.size() >= MAX_NEARBY) {
                    break;
                }
                McaCompat.getVillagerName(other)
                        .filter(name -> !name.isBlank() && !names.contains(name))
                        .ifPresent(names::add);
            }
        } catch (Throwable t) {
            return List.of();
        }
        names.sort(String::compareTo);
        return List.copyOf(names);
    }
}
