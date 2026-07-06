package dev.otectus.mcarealtalk.gossip;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GossipDiffTest {

    private static final UUID SMALL = UUID.fromString("00000000-0000-0000-0000-000000000001");
    private static final UUID BIG = UUID.fromString("ffffffff-0000-0000-0000-000000000002");

    private static GossipDiff.Observation obs(UUID id, String name, UUID partner, boolean baby) {
        return new GossipDiff.Observation(id, name, Optional.ofNullable(partner), baby);
    }

    private static RelationshipSnapshot snap(UUID partner, String name, boolean wasBaby) {
        return new RelationshipSnapshot(Optional.ofNullable(partner), name, wasBaby, 0);
    }

    @Test
    void marriageEmittedExactlyOnceWhenBothPartnersObserved() {
        // Note: UUID.compareTo is signed, so which partner "wins" is arbitrary but deterministic —
        // the contract is exactly one event per couple, covering both subjects.
        List<GossipDiff.Derived> out = GossipDiff.diff(
                List.of(obs(SMALL, "Ann", BIG, false), obs(BIG, "Bob", SMALL, false)),
                Map.of(SMALL, snap(null, "Ann", false), BIG, snap(null, "Bob", false)),
                Set.of(), true, true, true);
        assertEquals(1, out.size());
        GossipDiff.Derived event = out.get(0);
        assertEquals(GossipEventType.MARRIAGE, event.type());
        assertEquals(Set.of(SMALL, BIG), Set.of(event.aUuid(), event.bUuid().orElseThrow()));
        assertTrue(Set.of("Ann", "Bob").contains(event.bName()));
    }

    @Test
    void marriageEmittedWhenPartnerUnobserved() {
        List<GossipDiff.Derived> out = GossipDiff.diff(
                List.of(obs(BIG, "Bob", SMALL, false)),
                Map.of(BIG, snap(null, "Bob", false), SMALL, snap(null, "Ann", false)),
                Set.of(), true, true, true);
        assertEquals(1, out.size());
        assertEquals("Ann", out.get(0).bName()); // name recovered from the partner's snapshot
    }

    @Test
    void noMarriageWithoutSnapshotChange() {
        List<GossipDiff.Derived> out = GossipDiff.diff(
                List.of(obs(SMALL, "Ann", BIG, false)),
                Map.of(SMALL, snap(BIG, "Ann", false)),
                Set.of(), true, true, true);
        assertTrue(out.isEmpty());
    }

    @Test
    void divorceDetectedButSuppressedForWidows() {
        // Partner vanished, not dead → divorce.
        List<GossipDiff.Derived> divorced = GossipDiff.diff(
                List.of(obs(SMALL, "Ann", null, false)),
                Map.of(SMALL, snap(BIG, "Ann", false), BIG, snap(SMALL, "Bob", false)),
                Set.of(), true, true, true);
        assertEquals(1, divorced.size());
        assertEquals(GossipEventType.DIVORCE, divorced.get(0).type());
        assertEquals("Bob", divorced.get(0).bName());

        // Partner vanished because they died → no divorce event (death gossip covers it).
        List<GossipDiff.Derived> widowed = GossipDiff.diff(
                List.of(obs(SMALL, "Ann", null, false)),
                Map.of(SMALL, snap(BIG, "Ann", false)),
                Set.of(BIG), true, true, true);
        assertTrue(widowed.isEmpty());
    }

    @Test
    void birthOnlyForNeverSeenBabies() {
        // New baby, no snapshot → birth.
        List<GossipDiff.Derived> born = GossipDiff.diff(
                List.of(obs(SMALL, "Junior", null, true)),
                Map.of(), Set.of(), true, true, true);
        assertEquals(1, born.size());
        assertEquals(GossipEventType.BIRTH, born.get(0).type());

        // Known baby (snapshot exists) → no re-emit.
        List<GossipDiff.Derived> known = GossipDiff.diff(
                List.of(obs(SMALL, "Junior", null, true)),
                Map.of(SMALL, snap(null, "Junior", true)), Set.of(), true, true, true);
        assertTrue(known.isEmpty());

        // Never-seen adult (e.g. new resident) → not a birth.
        List<GossipDiff.Derived> adult = GossipDiff.diff(
                List.of(obs(SMALL, "Newcomer", null, false)),
                Map.of(), Set.of(), true, true, true);
        assertTrue(adult.isEmpty());
    }

    @Test
    void detectionTogglesRespected() {
        List<GossipDiff.Derived> out = GossipDiff.diff(
                List.of(obs(SMALL, "Ann", BIG, false), obs(BIG, "Junior", null, true)),
                Map.of(SMALL, snap(null, "Ann", false)),
                Set.of(), false, false, false);
        assertTrue(out.isEmpty());
    }
}
