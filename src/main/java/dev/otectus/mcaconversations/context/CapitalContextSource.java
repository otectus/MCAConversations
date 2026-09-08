package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.FeatureId;
import dev.otectus.mcaconversations.compat.CapitalCourtView;
import dev.otectus.mcaconversations.compat.CapitalRelationView;
import dev.otectus.mcaconversations.compat.CapitalStandingView;
import dev.otectus.mcaconversations.compat.CapitalsBridge;
import dev.otectus.mcaconversations.compat.CapitalsCompat;
import dev.otectus.mcaconversations.compat.CapitalsCapability;
import java.util.Set;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.court.CourtMemorySavedData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

/**
 * The court a villager belongs to: their title, their office, their house, and what their capital is
 * to the player standing in front of them.
 *
 * <p>Everything here comes through {@link CapitalsBridge}, so no MCA Capitals type is named and an
 * install without Capitals sees exactly one difference — this source's capability line. Every field
 * it declares reads {@code UNAVAILABLE} then, which is what makes a capital-gated scene unselectable
 * rather than selectable-on-a-false.
 *
 * <p><b>This source is also where title tracking happens.</b> Capitals will say who is Hand today and
 * nothing about who was Hand last week, so every read of a villager's title is recorded in
 * {@link CourtMemorySavedData}. Doing it here rather than on a timer means a villager the player never
 * meets costs nothing, and one the player talks to has their promotion noticed the moment it matters.
 */
public final class CapitalContextSource implements ConversationContextSource {

    public static final String ID = "capital";

    /** The remark window when the config has not loaded; matches {@code capitals.roleRemarkDays}. */
    private static final int DEFAULT_REMARK_DAYS = 7;

    private static final List<ContextKey<?>> DECLARES = List.of(
            ContextKeys.CAPITAL_PRESENT, ContextKeys.CAPITAL_NAME, ContextKeys.CAPITAL_STATE,
            ContextKeys.CAPITAL_TITLE, ContextKeys.CAPITAL_TITLE_RANK, ContextKeys.CAPITAL_OFFICE,
            ContextKeys.CAPITAL_CROWN_STANDING, ContextKeys.CAPITAL_ROYAL_HOUSEHOLD,
            ContextKeys.CAPITAL_ROYAL_GUARD, ContextKeys.CAPITAL_DISGRACED,
            ContextKeys.CAPITAL_HOUSE, ContextKeys.CAPITAL_HOUSE_TIER,
            ContextKeys.CAPITAL_AT_WAR, ContextKeys.CAPITAL_ALLIED, ContextKeys.CAPITAL_MOURNING,
            ContextKeys.CAPITAL_SOVEREIGN_IS_PLAYER, ContextKeys.CAPITAL_PLAYER_IS_SOVEREIGN,
            ContextKeys.CAPITAL_PLAYER_ALLEGIANCE, ContextKeys.CAPITAL_HEIR_NAMED,
            ContextKeys.CAPITAL_TITLE_CHANGED, ContextKeys.CAPITAL_PREVIOUS_TITLE,
            ContextKeys.CAPITAL_SOVEREIGN_NAMED, ContextKeys.CAPITAL_SOVEREIGN_FEMALE,
            ContextKeys.CAPITAL_CONSORT_NAMED, ContextKeys.CAPITAL_HOUSE_PRESENT, ContextKeys.CAPITAL_HOUSE_WORDS_PRESENT);

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
        return request.villager() != null
                && McaCompat.isMcaVillager(request.villager())
                && CapitalsCompat.isActive()
                && topicsEnabled();
    }

    @Override
    public void contribute(ContextSnapshotBuilder builder, ContextRequest request) {
        Entity villager = request.villager();
        if (villager == null || !McaCompat.isMcaVillager(villager)) {
            markUnavailable(builder, villager == null ? "no villager" : "not an MCA villager");
            return;
        }
        if (!CapitalsCompat.isActive()) {
            markUnavailable(builder, "MCA Capitals is not bound");
            return;
        }
        if (!topicsEnabled()) {
            markUnavailable(builder, "capital topics are disabled");
            return;
        }
        if (!(villager.level() instanceof ServerLevel level)) {
            // Client-side or off-thread. Capitals is server state; guessing would be worse than
            // saying nothing.
            markUnavailable(builder, "no server level");
            return;
        }
        try {
            CapitalsBridge bridge = CapitalsBridge.Holder.get();
            Optional<CapitalCourtView> resolved = courtOf(bridge, level, villager);
            if (resolved.isEmpty()) {
                contributeNoCapital(builder);
                builder.reportCapability(ContextCapabilities.Status.READY,
                        "villager belongs to no capital");
                return;
            }

            CapitalCourtView court = resolved.get();
            CapitalStandingView standing =
                    bridge.standingOf(level, court, villager.getUUID(), villager);
            boolean diplomacy = diplomacyEnabled();
            List<CapitalRelationView> relations =
                    diplomacy ? bridge.relationsOf(level, court) : List.of();

            ServerPlayer player = request.player();
            UUID playerId = player == null ? null : player.getUUID();
            Optional<UUID> declared = playerId == null
                    ? Optional.empty()
                    : bridge.declaredCapitalOf(level, playerId);

            contributeCourt(builder, court, standing, relations, declared, playerId, diplomacy,
                    bridge.has(CapitalsCapability.TITLES)
                            ? rememberTitle(request, villager, standing, level) : Optional.empty());
            maskUnavailableCapabilities(builder, bridge.capabilities());
            builder.reportCapability(ContextCapabilities.Status.READY, "");
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("capital context unavailable; those fields go dark", t);
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.FAILED, "capital read failed");
        }
    }

    /**
     * The answer for a villager Capitals knows about but seats in no capital.
     *
     * <p>{@code capital.present} is a confident {@code false} and everything else is UNKNOWN rather
     * than UNAVAILABLE: Capitals ran and answered, so the distinction a scene needs is "there is no
     * court here", not "nobody could say".
     */
    static void contributeNoCapital(ContextSnapshotBuilder builder) {
        builder.put(ContextKeys.CAPITAL_PRESENT, false);
        for (ContextKey<?> key : DECLARES) {
            if (key != ContextKeys.CAPITAL_PRESENT) {
                builder.unknown(key);
            }
        }
    }

    /**
     * Writes one resolved court into the snapshot.
     *
     * <p>Split out from {@link #contribute} and given only view records so the field-by-field
     * decisions — which absence is unknown, which is unavailable, which boolean the player half
     * answers — are testable without a level, a server or a villager.
     */
    static void contributeCourt(ContextSnapshotBuilder builder,
                                CapitalCourtView court,
                                CapitalStandingView standing,
                                List<CapitalRelationView> relations,
                                Optional<UUID> declaredCapital,
                                UUID playerId,
                                boolean diplomacy,
                                Optional<String> previousTitle) {
        builder.put(ContextKeys.CAPITAL_PRESENT, true);
        builder.put(ContextKeys.CAPITAL_NAME, court.name());
        builder.put(ContextKeys.CAPITAL_STATE, court.state());
        builder.put(ContextKeys.CAPITAL_MOURNING, court.mourning());
        builder.put(ContextKeys.CAPITAL_HEIR_NAMED, court.heirNamed());
        builder.put(ContextKeys.CAPITAL_SOVEREIGN_NAMED,
                court.sovereign().isPresent() || court.playerSovereignId().isPresent());
        builder.put(ContextKeys.CAPITAL_SOVEREIGN_FEMALE, court.sovereignFemale());
        builder.put(ContextKeys.CAPITAL_CONSORT_NAMED, court.consort().isPresent());
        builder.put(ContextKeys.CAPITAL_HOUSE_PRESENT, standing.hasHouse());
        builder.put(ContextKeys.CAPITAL_HOUSE_WORDS_PRESENT, !standing.houseWords().isBlank());

        builder.put(ContextKeys.CAPITAL_TITLE, standing.titleId());
        builder.put(ContextKeys.CAPITAL_TITLE_RANK, standing.titleRank());
        builder.put(ContextKeys.CAPITAL_OFFICE, standing.office());
        builder.put(ContextKeys.CAPITAL_CROWN_STANDING, standing.crownStanding());
        builder.put(ContextKeys.CAPITAL_ROYAL_HOUSEHOLD, standing.royalHousehold());
        builder.put(ContextKeys.CAPITAL_ROYAL_GUARD, standing.royalGuard());
        builder.put(ContextKeys.CAPITAL_DISGRACED, standing.disgraced());

        // A villager in no house has no house name, and "" is not a name a line could speak.
        if (standing.hasHouse()) {
            builder.put(ContextKeys.CAPITAL_HOUSE, standing.houseName());
        } else {
            builder.unknown(ContextKeys.CAPITAL_HOUSE);
        }
        if (standing.houseTier().isEmpty()) {
            builder.unknown(ContextKeys.CAPITAL_HOUSE_TIER);
        } else {
            builder.put(ContextKeys.CAPITAL_HOUSE_TIER, standing.houseTier());
        }

        // Wars and alliances are a config-switchable subject, so with the switch off they are
        // unavailable rather than false: "we are at peace" is a claim nobody made.
        if (diplomacy) {
            builder.put(ContextKeys.CAPITAL_AT_WAR,
                    relations.stream().anyMatch(CapitalRelationView::atWar));
            builder.put(ContextKeys.CAPITAL_ALLIED,
                    relations.stream().anyMatch(CapitalRelationView::allied));
        } else {
            builder.unavailable(ContextKeys.CAPITAL_AT_WAR);
            builder.unavailable(ContextKeys.CAPITAL_ALLIED);
        }

        builder.put(ContextKeys.CAPITAL_SOVEREIGN_IS_PLAYER, court.playerSovereign());
        if (playerId == null) {
            builder.unknown(ContextKeys.CAPITAL_PLAYER_IS_SOVEREIGN);
            builder.unknown(ContextKeys.CAPITAL_PLAYER_ALLEGIANCE);
        } else {
            builder.put(ContextKeys.CAPITAL_PLAYER_IS_SOVEREIGN,
                    court.playerSovereignId().map(playerId::equals).orElse(false));
            builder.put(ContextKeys.CAPITAL_PLAYER_ALLEGIANCE,
                    allegiance(declaredCapital, court.capitalId()));
        }

        builder.put(ContextKeys.CAPITAL_TITLE_CHANGED, previousTitle.isPresent());
        if (previousTitle.isPresent()) {
            builder.put(ContextKeys.CAPITAL_PREVIOUS_TITLE, previousTitle.get());
        } else {
            builder.unknown(ContextKeys.CAPITAL_PREVIOUS_TITLE);
        }
    }

    /**
     * Where the player has sworn themselves, seen from this capital: {@code same} for this one,
     * {@code foreign} for another, {@code none} for a player who has sworn to nobody.
     */
    static String allegiance(Optional<UUID> declaredCapital, UUID capitalId) {
        if (declaredCapital == null || declaredCapital.isEmpty()) {
            return "none";
        }
        return declaredCapital.get().equals(capitalId) ? "same" : "foreign";
    }

    /**
     * The villager's home village first, because that is one map lookup, and only then Capitals'
     * own resolver, which scans. A villager away from home still resolves; a wanderer does not.
     *
     * <p>Public because the template factory resolves the same villager for {@code capital_name} and
     * its siblings, and two different resolution orders would let a line name one court while the
     * conditions gated on another.
     */
    public static Optional<CapitalCourtView> courtOf(CapitalsBridge bridge, ServerLevel level,
                                                     Entity villager) {
        OptionalInt villageId = McaCompat.getHomeVillageId(villager);
        if (villageId.isPresent()) {
            Optional<CapitalCourtView> byVillage = bridge.courtOfVillage(level, villageId.getAsInt());
            if (byVillage.isPresent()) {
                return byVillage;
            }
        }
        return bridge.courtOfResident(level, villager.getUUID());
    }

    /**
     * Records the title just read and answers with the title it replaced, when the replacement is
     * recent and unremarked. Silent when there is no server to persist to, which is a test double
     * rather than a world.
     */
    private static Optional<String> rememberTitle(ContextRequest request, Entity villager,
                                                  CapitalStandingView standing, ServerLevel level) {
        MinecraftServer server = request.server();
        if (server == null) {
            return Optional.empty();
        }
        long today = level.getGameTime() / 24000L;
        CourtMemorySavedData memory = CourtMemorySavedData.get(server);
        memory.observe(villager.getUUID(), standing.titleId(), today);
        return memory.freshChange(villager.getUUID(), today, remarkDays());
    }

    /** Partial optional bindings must not turn neutral stubs into facts such as peace or no heir. */
    static void maskUnavailableCapabilities(ContextSnapshotBuilder builder, Set<CapitalsCapability> capabilities) {
        if (!capabilities.contains(CapitalsCapability.COURT)) {
            builder.allUnavailable(List.of(ContextKeys.CAPITAL_MOURNING, ContextKeys.CAPITAL_HEIR_NAMED,
                    ContextKeys.CAPITAL_SOVEREIGN_NAMED, ContextKeys.CAPITAL_SOVEREIGN_FEMALE,
                    ContextKeys.CAPITAL_CONSORT_NAMED, ContextKeys.CAPITAL_SOVEREIGN_IS_PLAYER,
                    ContextKeys.CAPITAL_PLAYER_IS_SOVEREIGN, ContextKeys.CAPITAL_ROYAL_HOUSEHOLD,
                    ContextKeys.CAPITAL_ROYAL_GUARD, ContextKeys.CAPITAL_DISGRACED));
        }
        if (!capabilities.contains(CapitalsCapability.TITLES)) {
            builder.allUnavailable(List.of(ContextKeys.CAPITAL_TITLE, ContextKeys.CAPITAL_TITLE_RANK,
                    ContextKeys.CAPITAL_OFFICE, ContextKeys.CAPITAL_TITLE_CHANGED, ContextKeys.CAPITAL_PREVIOUS_TITLE));
        }
        if (!capabilities.contains(CapitalsCapability.HOUSES)) {
            builder.allUnavailable(List.of(ContextKeys.CAPITAL_HOUSE, ContextKeys.CAPITAL_HOUSE_TIER,
                    ContextKeys.CAPITAL_HOUSE_PRESENT, ContextKeys.CAPITAL_HOUSE_WORDS_PRESENT));
        }
        if (!capabilities.contains(CapitalsCapability.STANDING)) builder.unavailable(ContextKeys.CAPITAL_CROWN_STANDING);
        if (!capabilities.contains(CapitalsCapability.ALLEGIANCE)) builder.unavailable(ContextKeys.CAPITAL_PLAYER_ALLEGIANCE);
        if (!capabilities.contains(CapitalsCapability.DIPLOMACY)) {
            builder.allUnavailable(List.of(ContextKeys.CAPITAL_AT_WAR, ContextKeys.CAPITAL_ALLIED));
        }
    }

    private static void markUnavailable(ContextSnapshotBuilder builder, String reason) {
        builder.allUnavailable(DECLARES);
        builder.reportCapability(ContextCapabilities.Status.ABSENT, reason);
    }

    /**
     * Config reads here are wrapped because this source runs inside dialogue scoring, where a spec
     * that has not loaded yet — a datapack reload during world creation — would otherwise throw into
     * MCA's selection loop. An unloaded config reads as the shipped default.
     */
    private static boolean topicsEnabled() {
        return McaConversationsConfig.dynamicFeature(FeatureId.CAPITAL_TOPICS, true);
    }

    private static boolean diplomacyEnabled() {
        return McaConversationsConfig.dynamicFeature(FeatureId.CAPITAL_DIPLOMACY, true);
    }

    private static int remarkDays() {
        return McaConversationsConfig.dynamicInt(
                McaConversationsConfig.COMMON.capitalRoleRemarkDays, DEFAULT_REMARK_DAYS);
    }
}
