package dev.otectus.mcaconversations.gossip;

import dev.otectus.mcaconversations.compat.ReputationBridge;
import net.minecraft.network.chat.Component;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The §30.4 merge: both gossip sources normalize into one shape, the newest story wins
 * deterministically, and external arguments stay capped at four.
 */
class NormalizedGossipTest {

    private static final UUID SUBJECT = UUID.fromString("00000000-0000-0000-0000-000000000001");

    private static GossipEvent nativeEvent(UUID id, long created) {
        return new GossipEvent(id, GossipEventType.QUEST, 3, created, SUBJECT, "Anna",
                Optional.empty(), "");
    }

    private static ReputationBridge.GossipCandidate external(UUID id, long created) {
        return new ReputationBridge.GossipCandidate(id, "mcareputation:villager_killed", created,
                "condemnation", "mcareputation.gossip.villager_killed",
                List.of(Component.literal("Ada"), Component.literal("Anna")), -40);
    }

    @Test
    void theNewestStoryWinsAcrossSources() {
        NormalizedGossip older = NormalizedGossip.ofNative(nativeEvent(UUID.randomUUID(), 100L));
        NormalizedGossip newer = NormalizedGossip.ofExternal(external(UUID.randomUUID(), 200L));
        assertEquals(Optional.of(newer), NormalizedGossip.newest(List.of(older, newer)));
        assertEquals(Optional.of(newer), NormalizedGossip.newest(List.of(newer, older)),
                "insertion order must not matter");
    }

    @Test
    void tiesBreakDeterministicallyByToldId() {
        UUID a = UUID.fromString("00000000-0000-0000-0000-00000000000a");
        UUID b = UUID.fromString("00000000-0000-0000-0000-00000000000b");
        NormalizedGossip first = NormalizedGossip.ofNative(nativeEvent(a, 100L));
        NormalizedGossip second = NormalizedGossip.ofExternal(external(b, 100L));
        Optional<NormalizedGossip> pick = NormalizedGossip.newest(List.of(first, second));
        assertEquals(pick, NormalizedGossip.newest(List.of(second, first)),
                "the same state must always pick the same story (§30.4)");
    }

    @Test
    void nothingToTellIsEmpty() {
        assertTrue(NormalizedGossip.newest(List.of()).isEmpty());
    }

    @Test
    void nativePhraseKeepsItsAuthoredPrefixAndSubjectArguments() {
        GossipEvent event = nativeEvent(UUID.randomUUID(), 100L);
        NormalizedGossip gossip = NormalizedGossip.ofNative(event);
        assertEquals("conversations.gossip.quest", gossip.phraseKey("conversations.gossip"));
        Object[] args = gossip.arguments();
        assertEquals(2, args.length);
        assertEquals("Anna", ((Component) args[0]).getString());
    }

    @Test
    void externalPhraseCarriesItsOwnKeyAndArguments() {
        NormalizedGossip gossip = NormalizedGossip.ofExternal(external(UUID.randomUUID(), 100L));
        assertTrue(gossip.isExternal());
        assertEquals("mcareputation.gossip.villager_killed",
                gossip.phraseKey("conversations.gossip"),
                "the native prefix must never leak onto an external story");
        assertEquals(2, gossip.arguments().length);
    }

    /** §30.4 caps external arguments at four, however many a malformed candidate carries. */
    @Test
    void externalArgumentsAreCappedAtFour() {
        NormalizedGossip gossip = new NormalizedGossip(UUID.randomUUID(), 100L, null, "k",
                List.of(Component.literal("1"), Component.literal("2"), Component.literal("3"),
                        Component.literal("4"), Component.literal("5")));
        assertEquals(4, gossip.arguments().length);
    }
}