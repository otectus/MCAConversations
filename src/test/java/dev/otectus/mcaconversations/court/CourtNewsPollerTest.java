package dev.otectus.mcaconversations.court;

import dev.otectus.mcaconversations.compat.CapitalChronicleEventView;
import dev.otectus.mcaconversations.compat.CapitalCourtView;
import dev.otectus.mcaconversations.compat.CapitalRelationView;
import dev.otectus.mcaconversations.gossip.GossipEvent;
import dev.otectus.mcaconversations.gossip.GossipEventType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The poll, against a chronicle that behaves the way Capitals' does: it grows, it occasionally grows
 * a great deal at once, and it can be trimmed under us.
 *
 * <p>Everything here targets the pure {@code poll}, which is why the whole of MCA Capitals is a list
 * and three methods below. The server glue around it does two things — resolve levels and write to
 * world storage — and neither is a rule worth a fake world to check.
 */
class CourtNewsPollerTest {

    private static final UUID CAPITAL = UUID.randomUUID();
    private static final UUID OTHER_CAPITAL = UUID.randomUUID();
    private static final UUID KING = UUID.randomUUID();
    private static final UUID USURPER = UUID.randomUUID();
    private static final long NOW = 240_000L;

    /** A chronicle that can be appended to and trimmed, plus the relations of the moment. */
    private static final class FakeCourt implements CourtNewsPoller.CourtSource {

        private final List<CapitalChronicleEventView> chronicle = new ArrayList<>();
        private final List<CapitalRelationView> relations = new ArrayList<>();

        void write(String type, String text) {
            chronicle.add(new CapitalChronicleEventView(chronicle.size(), 1L, type,
                    "capitals.chronicle." + type, text));
        }

        void trimTo(int size) {
            while (chronicle.size() > size) {
                chronicle.remove(chronicle.size() - 1);
            }
        }

        @Override
        public List<CapitalChronicleEventView> chronicleSince(CapitalCourtView court, int fromIndex,
                                                              int max) {
            if (fromIndex >= chronicle.size()) {
                return List.of();
            }
            return List.copyOf(chronicle.subList(fromIndex,
                    Math.min(chronicle.size(), fromIndex + max)));
        }

        @Override
        public List<CapitalRelationView> relationsOf(CapitalCourtView court) {
            return List.copyOf(relations);
        }

        @Override
        public String displayName(CapitalCourtView court, UUID entity) {
            return KING.equals(entity) ? "Old King" : "Usurper";
        }
    }

    private static CapitalCourtView court(FakeCourt fake, Optional<UUID> sovereign,
                                          Optional<UUID> heir, boolean mourning) {
        return new CapitalCourtView(CAPITAL, 7, "minecraft:overworld", "Highhold", "active",
                sovereign, false, Optional.empty(), heir, Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), mourning,
                false, Optional.empty(), "", fake.chronicle.size());
    }

    private static List<GossipEvent> poll(FakeCourt fake, CapitalCourtView court,
                                          CourtRoleMemory memory, int maxPerPoll) {
        List<GossipEvent> out = new ArrayList<>();
        CourtNewsPoller.poll(List.of(court), fake, memory, NOW, maxPerPoll, out::add);
        return out;
    }

    @Test
    void theFirstPollSeedsAndSaysNothing() {
        // A capital that already has a history when the integration is installed is not news, and a
        // village that announced all of it at once would be unusable.
        FakeCourt fake = new FakeCourt();
        fake.write("royal_marriage", "The king wed.");
        fake.write("hand_appointed", "A new Hand was named.");
        CourtRoleMemory memory = new CourtRoleMemory();

        assertTrue(poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 3)
                .isEmpty());
        assertEquals(2, memory.chronicleSeen(CAPITAL));
    }

    @Test
    void theSecondPollTellsWhatIsNew() {
        FakeCourt fake = new FakeCourt();
        fake.write("royal_marriage", "The king wed.");
        CourtRoleMemory memory = new CourtRoleMemory();
        CapitalCourtView first = court(fake, Optional.of(KING), Optional.empty(), false);
        poll(fake, first, memory, 3);

        fake.write("hand_appointed", "A new Hand was named.");
        List<GossipEvent> told =
                poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 3);

        assertEquals(1, told.size());
        GossipEvent event = told.get(0);
        assertEquals(GossipEventType.APPOINTMENT, event.type());
        assertEquals(CAPITAL, event.aUuid(), "the capital is the subject; gossip is village-scoped");
        assertEquals("Highhold", event.aName());
        assertEquals(7, event.villageId());
        assertEquals("A new Hand was named.", event.bName(), "the herald's own wording is kept");
        assertEquals(Optional.of(KING), event.bUuid());
        assertEquals(2, memory.chronicleSeen(CAPITAL));
    }

    @Test
    void aBurstIsCappedAndTheOlderEntriesAreDroppedForGood() {
        FakeCourt fake = new FakeCourt();
        CourtRoleMemory memory = new CourtRoleMemory();
        poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 2);

        for (int i = 0; i < 5; i++) {
            fake.write("hand_appointed", "Appointment " + i);
        }
        List<GossipEvent> told =
                poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 2);

        assertEquals(2, told.size(), "one poll tells at most maxPerPoll entries");
        assertEquals("Appointment 3", told.get(0).bName(), "and they are the newest ones");
        assertEquals("Appointment 4", told.get(1).bName());
        assertEquals(5, memory.chronicleSeen(CAPITAL));

        assertTrue(poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 2)
                .isEmpty(), "the skipped entries are gone, not queued");
    }

    @Test
    void aTrimmedChronicleReseedsRatherThanRetellingWhatSurvived() {
        FakeCourt fake = new FakeCourt();
        for (int i = 0; i < 6; i++) {
            fake.write("hand_appointed", "Appointment " + i);
        }
        CourtRoleMemory memory = new CourtRoleMemory();
        poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 3);
        assertEquals(6, memory.chronicleSeen(CAPITAL));

        fake.trimTo(2);
        assertTrue(poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 3)
                .isEmpty());
        assertEquals(2, memory.chronicleSeen(CAPITAL));
    }

    @Test
    void aChangedSovereignIsACoronationEvenWithNothingInTheChronicle() {
        FakeCourt fake = new FakeCourt();
        CourtRoleMemory memory = new CourtRoleMemory();
        poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 3);

        List<GossipEvent> told =
                poll(fake, court(fake, Optional.of(USURPER), Optional.empty(), false), memory, 3);

        assertEquals(1, told.size());
        assertEquals(GossipEventType.CORONATION, told.get(0).type());
        assertEquals("Usurper", told.get(0).bName(), "the diff names the new sovereign");
    }

    @Test
    void theChronicleWinsWhenBothPassesSeeTheSameCoronation() {
        // The usual case: Capitals chronicles the seizure and the record changes in the same poll.
        // Two events would have the village tell the same story twice in different words.
        FakeCourt fake = new FakeCourt();
        CourtRoleMemory memory = new CourtRoleMemory();
        poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 3);

        fake.write("throne_seized", "The throne was taken by force.");
        List<GossipEvent> told =
                poll(fake, court(fake, Optional.of(USURPER), Optional.empty(), false), memory, 3);

        assertEquals(1, told.size(), "told: " + told);
        assertEquals(GossipEventType.CORONATION, told.get(0).type());
        assertEquals("The throne was taken by force.", told.get(0).bName());
    }

    @Test
    void aNewlyNamedHeirMourningAndAWarAreEachToldOnce() {
        FakeCourt fake = new FakeCourt();
        CourtRoleMemory memory = new CourtRoleMemory();
        poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 3);

        fake.relations.add(new CapitalRelationView(OTHER_CAPITAL, "Fenmarch", "war", "hostile", -40));
        List<GossipEvent> told =
                poll(fake, court(fake, Optional.of(KING), Optional.of(USURPER), true), memory, 3);

        assertEquals(List.of(GossipEventType.ROYAL_BIRTH, GossipEventType.ROYAL_DEATH,
                        GossipEventType.WAR),
                told.stream().map(GossipEvent::type).toList());
        assertEquals("Fenmarch", told.get(2).bName());

        // Nothing has changed since, so a third poll is silent.
        assertTrue(poll(fake, court(fake, Optional.of(KING), Optional.of(USURPER), true), memory, 3)
                .isEmpty());
    }

    @Test
    void leavingAWarIsPeaceAndEnteringAnAllianceIsAnAlliance() {
        FakeCourt fake = new FakeCourt();
        CourtRoleMemory memory = new CourtRoleMemory();
        fake.relations.add(new CapitalRelationView(OTHER_CAPITAL, "Fenmarch", "war", "hostile", -40));
        poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 3);

        fake.relations.set(0,
                new CapitalRelationView(OTHER_CAPITAL, "Fenmarch", "alliance", "excellent", 60));
        List<GossipEvent> told =
                poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 3);

        assertEquals(List.of(GossipEventType.PEACE, GossipEventType.ALLIANCE),
                told.stream().map(GossipEvent::type).toList());
    }

    @Test
    void officeHoldersAreRememberedAndTheFirstSightingIsNotAPromotion() {
        FakeCourt fake = new FakeCourt();
        CourtRoleMemory memory = new CourtRoleMemory();
        long today = NOW / 24_000L;

        poll(fake, court(fake, Optional.of(KING), Optional.empty(), false), memory, 3);
        assertEquals(Optional.of("sovereign"), memory.title(KING));
        assertTrue(memory.freshChange(KING, today, 7).isEmpty(),
                "somebody already on the throne has not just been crowned");

        // The same villager, now the heir: that is a change, and it is theirs to mention.
        poll(fake, court(fake, Optional.of(USURPER), Optional.of(KING), false), memory, 3);
        assertEquals(Optional.of("sovereign"), memory.freshChange(KING, today, 7));
    }

    @Test
    void nothingToReadIsNotAnError() {
        CourtRoleMemory memory = new CourtRoleMemory();
        assertEquals(0, CourtNewsPoller.poll(List.of(), new FakeCourt(), memory, NOW, 3,
                event -> {
                    throw new AssertionError("nothing to tell");
                }).emitted());
        assertEquals(0, CourtNewsPoller.poll(null, new FakeCourt(), memory, NOW, 3,
                event -> {
                    throw new AssertionError("nothing to tell");
                }).emitted());
    }
}
