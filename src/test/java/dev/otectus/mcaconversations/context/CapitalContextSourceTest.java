package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.compat.CapitalChronicleEventView;
import dev.otectus.mcaconversations.compat.CapitalCourtView;
import dev.otectus.mcaconversations.compat.CapitalRelationView;
import dev.otectus.mcaconversations.compat.CapitalStandingView;
import dev.otectus.mcaconversations.compat.CapitalsBridge;
import dev.otectus.mcaconversations.compat.CapitalsCapability;
import dev.otectus.mcaconversations.compat.CapitalsCompat;
import dev.otectus.mcaconversations.compat.CapitalsStatus;
import dev.otectus.mcaconversations.court.CourtRoleMemory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * What a villager's court looks like once it has been flattened into context fields.
 *
 * <p>The field-by-field decisions are tested through {@code contributeCourt}, which takes only view
 * records: a full {@code contribute} needs a level, a server and an MCA villager, none of which exist
 * in a unit test, and none of which are where the interesting judgements are. The judgements are
 * which absence is UNKNOWN and which is UNAVAILABLE — "this villager has no house" and "nobody was in
 * a position to say" are different claims, and a scene gated on either takes a different path.
 */
class CapitalContextSourceTest {

    private static final UUID CAPITAL = UUID.randomUUID();
    private static final UUID OTHER_CAPITAL = UUID.randomUUID();
    private static final UUID PLAYER = UUID.randomUUID();
    private static final UUID SOVEREIGN = UUID.randomUUID();
    private static final UUID VILLAGER = UUID.randomUUID();

    @AfterEach
    void restoreTheAbsentDefault() {
        CapitalsCompat.resetForTest();
    }

    // --- The fake bridge ------------------------------------------------------------------------

    /** A Capitals that is present and answers, without any of Capitals being on the classpath. */
    private static final class FakeCapitals implements CapitalsBridge {

        private final CapitalsStatus status;

        private FakeCapitals(CapitalsStatus status) {
            this.status = status;
        }

        @Override
        public CapitalsStatus status() {
            return status;
        }

        @Override
        public Set<CapitalsCapability> capabilities() {
            return EnumSet.allOf(CapitalsCapability.class);
        }

        @Override
        public Optional<CapitalCourtView> courtOfVillage(ServerLevel level, int villageId) {
            return Optional.of(court(true, true));
        }

        @Override
        public Optional<CapitalCourtView> courtOfResident(ServerLevel level, UUID villager) {
            return Optional.of(court(true, true));
        }

        @Override
        public List<CapitalCourtView> allCourts(MinecraftServer server) {
            return List.of(court(true, true));
        }

        @Override
        public Optional<ServerLevel> levelOf(MinecraftServer server, CapitalCourtView court) {
            return Optional.empty();
        }

        @Override
        public CapitalStandingView standingOf(ServerLevel level, CapitalCourtView court, UUID villager,
                                              @Nullable Entity loadedEntity) {
            return standing("hand", 40);
        }

        @Override
        public List<CapitalRelationView> relationsOf(ServerLevel level, CapitalCourtView court) {
            return List.of(new CapitalRelationView(OTHER_CAPITAL, "Ostmark", "war", "hostile", -80));
        }

        @Override
        public List<CapitalChronicleEventView> chronicleSince(ServerLevel level, CapitalCourtView court,
                                                              int fromIndex, int max) {
            return List.of();
        }

        @Override
        public Optional<UUID> declaredCapitalOf(ServerLevel level, UUID player) {
            return Optional.of(CAPITAL);
        }

        @Override
        public String displayName(ServerLevel level, CapitalCourtView court, UUID entity) {
            return "Alwin";
        }
    }

    private static CapitalCourtView court(boolean heir, boolean playerSovereign) {
        return new CapitalCourtView(CAPITAL, 7, "minecraft:overworld", "Highmoor", "active",
                Optional.of(SOVEREIGN), true, Optional.empty(),
                heir ? Optional.of(UUID.randomUUID()) : Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), false,
                playerSovereign, playerSovereign ? Optional.of(PLAYER) : Optional.empty(),
                playerSovereign ? "Otec" : "", 3);
    }

    private static CapitalStandingView standing(String titleId, int rank) {
        return new CapitalStandingView(titleId, rank, "Hand of the Crown", "master_of_laws", "friend",
                true, false, false, "House Vance", "great", "We hold the line", "Vance");
    }

    private static ContextSnapshotBuilder builder() {
        ContextSnapshotBuilder builder = new ContextSnapshotBuilder();
        builder.beginSource(CapitalContextSource.ID);
        return builder;
    }

    // --- The bridge seam -------------------------------------------------------------------------

    @Test
    void aFakeBridgeIsWhatTheSourceReads() {
        CapitalsBridge fake = new FakeCapitals(CapitalsStatus.FULL);
        CapitalsCompat.setBridgeForTest(fake);
        assertSame(fake, CapitalsBridge.Holder.get());
        assertTrue(CapitalsCompat.isActive());

        CapitalsCompat.resetForTest();
        assertFalse(CapitalsCompat.isActive(), "an absent Capitals is never active");
    }

    // --- Present ---------------------------------------------------------------------------------

    @Test
    void aVillagerOfACourtGetsEveryField() {
        ContextSnapshotBuilder builder = builder();
        CapitalContextSource.contributeCourt(builder, court(true, true), standing("hand", 40),
                new FakeCapitals(CapitalsStatus.FULL).relationsOf(null, null),
                Optional.of(CAPITAL), PLAYER, true, Optional.empty());
        ConversationContextSnapshot snapshot = builder.build();

        assertEquals(Optional.of(true), snapshot.value(ContextKeys.CAPITAL_PRESENT));
        assertEquals(Optional.of("Highmoor"), snapshot.value(ContextKeys.CAPITAL_NAME));
        assertEquals(Optional.of("active"), snapshot.value(ContextKeys.CAPITAL_STATE));
        assertEquals(Optional.of("hand"), snapshot.value(ContextKeys.CAPITAL_TITLE));
        assertEquals(Optional.of(40), snapshot.value(ContextKeys.CAPITAL_TITLE_RANK));
        assertEquals(Optional.of("master_of_laws"), snapshot.value(ContextKeys.CAPITAL_OFFICE));
        assertEquals(Optional.of("friend"), snapshot.value(ContextKeys.CAPITAL_CROWN_STANDING));
        assertEquals(Optional.of(true), snapshot.value(ContextKeys.CAPITAL_ROYAL_HOUSEHOLD));
        assertEquals(Optional.of(false), snapshot.value(ContextKeys.CAPITAL_ROYAL_GUARD));
        assertEquals(Optional.of(false), snapshot.value(ContextKeys.CAPITAL_DISGRACED));
        assertEquals(Optional.of("House Vance"), snapshot.value(ContextKeys.CAPITAL_HOUSE));
        assertEquals(Optional.of("great"), snapshot.value(ContextKeys.CAPITAL_HOUSE_TIER));
        assertEquals(Optional.of(true), snapshot.value(ContextKeys.CAPITAL_AT_WAR));
        assertEquals(Optional.of(false), snapshot.value(ContextKeys.CAPITAL_ALLIED));
        assertEquals(Optional.of(false), snapshot.value(ContextKeys.CAPITAL_MOURNING));
        assertEquals(Optional.of(true), snapshot.value(ContextKeys.CAPITAL_SOVEREIGN_IS_PLAYER));
        assertEquals(Optional.of(true), snapshot.value(ContextKeys.CAPITAL_PLAYER_IS_SOVEREIGN));
        assertEquals(Optional.of(true), snapshot.value(ContextKeys.CAPITAL_HEIR_NAMED));
        assertEquals(Optional.of(false), snapshot.value(ContextKeys.CAPITAL_TITLE_CHANGED));
        assertEquals(ContextStatus.UNKNOWN,
                snapshot.get(ContextKeys.CAPITAL_PREVIOUS_TITLE).status());
    }

    @Test
    void aHouselessVillagerHasNoHouseRatherThanABlankOne() {
        ContextSnapshotBuilder builder = builder();
        CapitalStandingView houseless = new CapitalStandingView("commoner", 1, "", "none", "neutral",
                false, false, false, "", "", "", "");
        CapitalContextSource.contributeCourt(builder, court(false, false), houseless, List.of(),
                Optional.empty(), PLAYER, true, Optional.empty());
        ConversationContextSnapshot snapshot = builder.build();

        assertEquals(Optional.of(false), snapshot.value(ContextKeys.CAPITAL_HOUSE_PRESENT));
        assertEquals(ContextStatus.UNKNOWN, snapshot.get(ContextKeys.CAPITAL_HOUSE).status());
        assertEquals(ContextStatus.UNKNOWN, snapshot.get(ContextKeys.CAPITAL_HOUSE_TIER).status());
        assertEquals(Optional.of(false), snapshot.value(ContextKeys.CAPITAL_HEIR_NAMED));
        assertEquals(Optional.of(false), snapshot.value(ContextKeys.CAPITAL_PLAYER_IS_SOVEREIGN));
    }

    // --- Absent ----------------------------------------------------------------------------------

    @Test
    void withoutCapitalsEveryFieldIsUnavailable() {
        CapitalsCompat.resetForTest();
        ContextSnapshotBuilder builder = builder();
        CapitalContextSource source = new CapitalContextSource();
        ContextRequest request = new ContextRequest(null, null, "topic", false);

        assertFalse(source.isAvailable(request));
        source.contribute(builder, request);
        ConversationContextSnapshot snapshot = builder.build();
        for (ContextKey<?> key : source.declares()) {
            assertEquals(ContextStatus.UNAVAILABLE, snapshot.get(key).status(),
                    key.id() + " must be unavailable when there is no capital layer at all");
        }
    }

    @Test
    void aVillagerInNoCapitalIsAKnownNoRatherThanAnUnavailable() {
        ContextSnapshotBuilder builder = builder();
        CapitalContextSource.contributeNoCapital(builder);
        ConversationContextSnapshot snapshot = builder.build();

        assertEquals(Optional.of(false), snapshot.value(ContextKeys.CAPITAL_PRESENT));
        assertEquals(ContextStatus.UNKNOWN, snapshot.get(ContextKeys.CAPITAL_TITLE).status());
        assertEquals(ContextStatus.UNKNOWN, snapshot.get(ContextKeys.CAPITAL_NAME).status());
    }

    @Test
    void diplomacyTurnedOffIsUnavailableNotPeace() {
        // "We are at war with nobody" is a claim the config told us not to make; a scene gated on
        // capital.at_war must self-hide instead of reading a false.
        ContextSnapshotBuilder builder = builder();
        CapitalContextSource.contributeCourt(builder, court(true, false), standing("knight", 20),
                List.of(), Optional.empty(), PLAYER, false, Optional.empty());
        ConversationContextSnapshot snapshot = builder.build();

        assertEquals(ContextStatus.UNAVAILABLE, snapshot.get(ContextKeys.CAPITAL_AT_WAR).status());
        assertEquals(ContextStatus.UNAVAILABLE, snapshot.get(ContextKeys.CAPITAL_ALLIED).status());
        assertEquals(Optional.of("knight"), snapshot.value(ContextKeys.CAPITAL_TITLE));
    }

    @Test
    void withNoPlayerThePlayerHalfIsUnknown() {
        ContextSnapshotBuilder builder = builder();
        CapitalContextSource.contributeCourt(builder, court(true, true), standing("lord", 30),
                List.of(), Optional.empty(), null, true, Optional.empty());
        ConversationContextSnapshot snapshot = builder.build();

        assertEquals(ContextStatus.UNKNOWN,
                snapshot.get(ContextKeys.CAPITAL_PLAYER_IS_SOVEREIGN).status());
        assertEquals(ContextStatus.UNKNOWN,
                snapshot.get(ContextKeys.CAPITAL_PLAYER_ALLEGIANCE).status());
        assertEquals(Optional.of(true), snapshot.value(ContextKeys.CAPITAL_SOVEREIGN_IS_PLAYER));
    }

    // --- Allegiance -------------------------------------------------------------------------------

    @Test
    void allegianceIsReadFromWhereThePlayerSwore() {
        assertEquals("same", CapitalContextSource.allegiance(Optional.of(CAPITAL), CAPITAL));
        assertEquals("foreign", CapitalContextSource.allegiance(Optional.of(OTHER_CAPITAL), CAPITAL));
        assertEquals("none", CapitalContextSource.allegiance(Optional.empty(), CAPITAL));
        assertEquals("none", CapitalContextSource.allegiance(null, CAPITAL));
    }

    @Test
    void allegianceReachesTheSnapshot() {
        for (String expected : List.of("same", "foreign", "none")) {
            Optional<UUID> declared = switch (expected) {
                case "same" -> Optional.of(CAPITAL);
                case "foreign" -> Optional.of(OTHER_CAPITAL);
                default -> Optional.empty();
            };
            ContextSnapshotBuilder builder = builder();
            CapitalContextSource.contributeCourt(builder, court(true, false), standing("lord", 30),
                    List.of(), declared, PLAYER, true, Optional.empty());
            assertEquals(Optional.of(expected),
                    builder.build().value(ContextKeys.CAPITAL_PLAYER_ALLEGIANCE));
        }
    }

    // --- Title changes ---------------------------------------------------------------------------

    @Test
    void aSecondObserveWithANewTitleShowsUpAsAChange() {
        CourtRoleMemory memory = new CourtRoleMemory();
        memory.observe(VILLAGER, "knight", 10L);
        assertEquals(Optional.empty(), memory.freshChange(VILLAGER, 10L, 7));

        memory.observe(VILLAGER, "hand", 12L);
        ContextSnapshotBuilder builder = builder();
        CapitalContextSource.contributeCourt(builder, court(true, false), standing("hand", 40),
                List.of(), Optional.empty(), PLAYER, true,
                memory.freshChange(VILLAGER, 12L, 7));
        ConversationContextSnapshot snapshot = builder.build();

        assertEquals(Optional.of(true), snapshot.value(ContextKeys.CAPITAL_TITLE_CHANGED));
        assertEquals(Optional.of("knight"), snapshot.value(ContextKeys.CAPITAL_PREVIOUS_TITLE));
    }

    @Test
    void partialBindingDoesNotInventPeaceAnEmptySuccessionOrAnUnswornPlayer() {
        ContextSnapshotBuilder builder = builder();
        CapitalContextSource.contributeCourt(builder, court(false, false), standing("none", 0),
                List.of(), Optional.empty(), PLAYER, true, Optional.empty());
        CapitalContextSource.maskUnavailableCapabilities(builder, Set.of(CapitalsCapability.CORE));
        ConversationContextSnapshot snapshot = builder.build();
        assertEquals(Optional.of(true), snapshot.value(ContextKeys.CAPITAL_PRESENT));
        for (ContextKey<?> key : List.of(ContextKeys.CAPITAL_AT_WAR, ContextKeys.CAPITAL_ALLIED,
                ContextKeys.CAPITAL_HEIR_NAMED, ContextKeys.CAPITAL_CONSORT_NAMED,
                ContextKeys.CAPITAL_PLAYER_ALLEGIANCE, ContextKeys.CAPITAL_TITLE_CHANGED,
                ContextKeys.CAPITAL_HOUSE_PRESENT, ContextKeys.CAPITAL_HOUSE_WORDS_PRESENT)) {
            assertEquals(ContextStatus.UNAVAILABLE, snapshot.get(key).status(), key.id());
        }
    }

    // --- Ownership --------------------------------------------------------------------------------

    @Test
    void thisSourceOwnsExactlyTheCapitalFields() {
        List<String> declared = new CapitalContextSource().declares().stream()
                .map(ContextKey::id)
                .sorted()
                .toList();
        assertEquals(26, declared.size());
        assertTrue(declared.stream().allMatch(id -> id.startsWith("capital.")),
                "this source may only own capital.* fields: " + declared);
    }
}
