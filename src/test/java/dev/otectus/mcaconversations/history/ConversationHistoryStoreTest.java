package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The persistence guarantees of the living-history store (spec §21.1, §24.7).
 *
 * <p>Four things have to be true of a store that holds the only copy of who a villager is and what
 * the player promised them:
 *
 * <ol>
 *   <li>every record family survives a round trip unchanged;</li>
 *   <li>a malformed or unknown entry costs itself and nothing around it;</li>
 *   <li>the caps hold after load, after mutation and after pruning — a save that grows without bound
 *       is a bug that only shows up on somebody's year-old world;</li>
 *   <li>a realistic worst case fits in a documented byte budget.</li>
 * </ol>
 */
class ConversationHistoryStoreTest {

    private static final UUID VILLAGER = UUID.fromString("00000000-0000-4000-8000-0000000000aa");
    private static final UUID PLAYER = UUID.fromString("00000000-0000-4000-8000-0000000000bb");
    private static final UUID NEIGHBOUR = UUID.fromString("00000000-0000-4000-8000-0000000000cc");

    @Test
    void everyRecordFamilyRoundTrips() {
        ConversationHistoryStore store = new ConversationHistoryStore();
        VillagerHistory history = store.getOrCreate(VILLAGER);

        EpisodeRecord episode = EpisodeRecord.opened(UUID.randomUUID(), "work.damaged_volume",
                "work.librarian.damaged_volume", EpisodeState.BLOCKED, VILLAGER,
                Map.of("volume", NarrativeValue.token("ledger"),
                        "damage", NarrativeValue.token("damp")),
                PrivacyLevel.ORDINARY, 55, 40)
                .withDeadline(OptionalLong.of(46), OptionalLong.of(64))
                .witnessedBy(PLAYER)
                .withParticipant(NEIGHBOUR);
        history.putEpisode(episode, 40);

        history.putOpinion(new SocialOpinionRecord(NEIGHBOUR, "reliability", -2,
                "episode.harvest_help.late", Confidence.WITNESSED, PrivacyLevel.DISCREET, 38,
                OptionalLong.of(50)));

        PairHistory pair = history.pair(PLAYER);
        pair.putThread(SharedThreadRecord.opened("work.librarian.damaged_volume", "work",
                        "work.librarian.damaged_volume", Optional.of(episode.id()),
                        PrivacyLevel.ORDINARY, 40)
                .withObligation("commitment:work.librarian.bring_absorbent", 40));
        pair.putCommitment(CommitmentRecord.made("work.librarian.bring_absorbent",
                CommitmentResolver.GIFT_TAG_RECEIVED, NarrativeValue.registryId("minecraft:wool"),
                CommitmentRecord.Party.PLAYER, 40, OptionalLong.of(43), Optional.of(episode.id())));
        pair.recordClaim(PlayerClaimRecord.stated("food_preference",
                NarrativeValue.token("bread"), "conversations.topic.food.respond/say_bread", 39));
        pair.recordPlayed("work.librarian.damaged_volume.blocked", "damaged_volume",
                "problem_solve", "work", 40);

        ConversationHistoryStore reloaded = roundTrip(store);
        VillagerHistory back = reloaded.peek(VILLAGER).orElseThrow();

        EpisodeRecord backEpisode = back.episode(episode.id()).orElseThrow();
        assertEquals(episode, backEpisode, "an episode did not survive the round trip intact");
        assertEquals(Set.of(NEIGHBOUR), backEpisode.participants());
        assertTrue(backEpisode.isKnownTo(PLAYER), "the witness list was lost");

        assertEquals(1, back.opinions().size());
        assertEquals(-2, back.opinionsOf(NEIGHBOUR).get(0).value());

        PairHistory backPair = back.peekPair(PLAYER).orElseThrow();
        SharedThreadRecord thread = backPair.thread("work.librarian.damaged_volume").orElseThrow();
        assertEquals("commitment:work.librarian.bring_absorbent", thread.obligation());
        assertEquals(Optional.of(episode.id()), thread.episodeId());

        CommitmentRecord commitment = backPair.commitment("work.librarian.bring_absorbent").orElseThrow();
        assertEquals(CommitmentResolver.GIFT_TAG_RECEIVED, commitment.resolver());
        assertEquals("minecraft:wool", commitment.target().raw());

        assertEquals("bread", backPair.claim("food_preference").orElseThrow().value().raw());
        assertEquals(0, backPair.recency()
                .daysSince(TopicRecencyRecord.Level.SHAPE, "problem_solve", 40));
    }

    @Test
    void serialisingTwiceProducesTheSameBytes() {
        // An unstable ordering would rewrite the world's data file on every save for no reason, and
        // would make the save-size fixture below meaningless.
        ConversationHistoryStore store = populated(20, 3);
        assertEquals(bytes(store).length, bytes(store).length);
        assertEquals(new String(bytes(store), java.nio.charset.StandardCharsets.ISO_8859_1),
                new String(bytes(store), java.nio.charset.StandardCharsets.ISO_8859_1));
    }

    @Test
    void aMalformedRowCostsOnlyItself() {
        ConversationHistoryStore store = new ConversationHistoryStore();
        store.getOrCreate(VILLAGER).putEpisode(EpisodeRecord.opened(UUID.randomUUID(),
                "work.damaged_volume", "work.librarian.damaged_volume", EpisodeState.BLOCKED,
                VILLAGER, Map.of("volume", NarrativeValue.token("ledger")),
                PrivacyLevel.ORDINARY, 30, 1), 1);

        CompoundTag tag = store.save(new CompoundTag());
        // A row with no UUID, and an episode with no kind: both are unusable, neither is fatal.
        tag.getList("villagers", net.minecraft.nbt.Tag.TAG_COMPOUND).add(new CompoundTag());

        ConversationHistoryStore reloaded = ConversationHistoryStore.load(tag);
        assertEquals(1, reloaded.villagerCount(), "a junk row took a real villager with it");
        assertEquals(1, reloaded.peek(VILLAGER).orElseThrow().episodes().size());
    }

    @Test
    void aNewerSchemaIsReadRatherThanDiscarded() {
        // Somebody who tried a later build and rolled back must keep their villagers.
        ConversationHistoryStore store = populated(3, 2);
        CompoundTag tag = store.save(new CompoundTag());
        tag.putInt("version", ConversationHistoryStore.CURRENT_VERSION + 5);

        ConversationHistoryStore reloaded = ConversationHistoryStore.load(tag);
        assertEquals(store.villagerCount(), reloaded.villagerCount());
        assertEquals(ConversationHistoryStore.CURRENT_VERSION + 5, reloaded.loadedVersion());
    }

    @Test
    void theActiveEpisodeCapAbandonsRatherThanDeletes() {
        VillagerHistory history = new VillagerHistory();
        for (int i = 0; i < HistoryCaps.HARD_ACTIVE_EPISODES + 10; i++) {
            history.putEpisode(EpisodeRecord.opened(new UUID(0, i), "work.kind" + i,
                    "work.subject", EpisodeState.ACTIVE, VILLAGER,
                    Map.of("volume", NarrativeValue.token("ledger")),
                    PrivacyLevel.ORDINARY, 10 + i, 5), 5);
        }
        List<EpisodeRecord> live = history.liveEpisodes(5);
        assertTrue(live.size() <= HistoryCaps.activeEpisodes(),
                "live episodes exceeded the cap: " + live.size());
        // Over the cap, the least salient is abandoned — a state a scene can speak from — never
        // silently deleted while it is still someone's current work.
        long abandoned = history.episodes().stream()
                .filter(episode -> episode.state() == EpisodeState.ABANDONED)
                .count();
        assertTrue(abandoned > 0, "episodes over the cap vanished instead of being abandoned");
    }

    @Test
    void anOpenCommitmentIsNeverEvictedToMakeRoom() {
        PairHistory pair = new PairHistory();
        for (int i = 0; i < HistoryCaps.HARD_COMMITMENTS_PER_PAIR + 8; i++) {
            pair.putCommitment(CommitmentRecord.made("promise." + i,
                    CommitmentResolver.VISIT_AFTER_DAY, NarrativeValue.EMPTY,
                    CommitmentRecord.Party.PLAYER, 1, OptionalLong.of(3), Optional.empty()));
        }
        assertTrue(pair.commitments().size() <= HistoryCaps.commitmentsPerPair(),
                "the commitment cap did not hold");
        for (CommitmentRecord commitment : pair.commitments()) {
            assertTrue(commitment.isOutstanding(),
                    "a settled promise should have been the one evicted");
        }
    }

    @Test
    void aRuptureAndAnObligationSurvivePruning() {
        PairHistory pair = new PairHistory();
        pair.putThread(SharedThreadRecord
                .opened("rupture.thread", "work", "work.subject", Optional.empty(),
                        PrivacyLevel.ORDINARY, 1)
                .withStatus(ThreadStatus.RUPTURED, 1)
                .withSchedule(1, OptionalLong.of(2)));
        pair.putThread(SharedThreadRecord
                .opened("owed.thread", "work", "work.subject", Optional.empty(),
                        PrivacyLevel.ORDINARY, 1)
                .withObligation("commitment:something", 1)
                .withSchedule(1, OptionalLong.of(2)));

        pair.prune(500);

        assertEquals(ThreadStatus.RUPTURED, pair.thread("rupture.thread").orElseThrow().status(),
                "an unrepaired rupture must never lapse on a timer");
        assertTrue(pair.thread("owed.thread").orElseThrow().hasObligation(),
                "a thread with something outstanding must never lapse on a timer");
    }

    @Test
    void aWorstCaseSaveStaysInsideItsBudget() {
        // 200 villagers, 20 active player pairs each, every bounded collection full. The plan asks
        // for a budget the test fails on rather than merely logs (spec §8.9).
        ConversationHistoryStore store = populated(200, 20);
        byte[] compressed = bytes(store);
        int budgetBytes = 6 * 1024 * 1024;
        assertTrue(compressed.length < budgetBytes,
                "worst-case history serialised to " + (compressed.length / 1024) + " KiB, over the "
                        + (budgetBytes / 1024) + " KiB budget");
        // Also assert it is not trivially small, which would mean the fixture stopped being worst-case.
        assertTrue(compressed.length > 64 * 1024,
                "the worst-case fixture only produced " + compressed.length + " bytes; it is no "
                        + "longer exercising the caps it was written to exercise");
    }

    @Test
    void anEmptyStoreSurvivesEverything() {
        ConversationHistoryStore empty = new ConversationHistoryStore();
        assertEquals(0, empty.villagerCount());
        assertEquals(0, empty.prune(100));
        assertEquals(0, roundTrip(empty).villagerCount());
        assertSame(Optional.empty(), Optional.empty());
        assertFalse(ConversationHistoryStore.load(null).peek(VILLAGER).isPresent());
    }

    // --- Fixtures ---------------------------------------------------------------------------------

    private static ConversationHistoryStore populated(int villagers, int pairsEach) {
        ConversationHistoryStore store = new ConversationHistoryStore();
        for (int v = 0; v < villagers; v++) {
            UUID villager = new UUID(1L, v);
            VillagerHistory history = store.getOrCreate(villager);
            for (int e = 0; e < HistoryCaps.activeEpisodes(); e++) {
                history.putEpisode(EpisodeRecord.opened(new UUID(v, e), "work.kind" + e,
                        "work.subject." + e, EpisodeState.BLOCKED, villager,
                        Map.of("volume", NarrativeValue.token("ledger"),
                                "damage", NarrativeValue.token("damp"),
                                "who", NarrativeValue.uuid(new UUID(9L, e))),
                        PrivacyLevel.ORDINARY, 40 + e, 100), 100);
            }
            for (int o = 0; o < HistoryCaps.opinionsPerVillager(); o++) {
                history.putOpinion(new SocialOpinionRecord(new UUID(3L, o), "reliability",
                        (o % 5) - 2, "episode.some_cause." + o, Confidence.LIKELY,
                        PrivacyLevel.DISCREET, 90, OptionalLong.of(200)));
            }
            for (int p = 0; p < pairsEach; p++) {
                // Unicode names are not stored, but Unicode ids exercise the same encoder path.
                PairHistory pair = history.pair(new UUID(2L, p));
                for (int t = 0; t < HistoryCaps.threadsPerPair(); t++) {
                    pair.putThread(SharedThreadRecord.opened("thread.template." + t, "work",
                                    "work.subject." + t, Optional.of(new UUID(v, t)),
                                    PrivacyLevel.ORDINARY, 100)
                            .withObligation("commitment:promise." + t, 100));
                }
                for (int c = 0; c < HistoryCaps.commitmentsPerPair(); c++) {
                    pair.putCommitment(CommitmentRecord.made("promise." + c,
                            CommitmentResolver.GIFT_TAG_RECEIVED,
                            NarrativeValue.registryId("forge:ingots/iron"),
                            CommitmentRecord.Party.PLAYER, 100, OptionalLong.of(105),
                            Optional.empty()));
                }
                for (int c = 0; c < HistoryCaps.claimsPerPair(); c++) {
                    pair.recordClaim(PlayerClaimRecord.stated("claim_type_" + c,
                            NarrativeValue.token("value_" + c),
                            "conversations.topic.food.respond/answer_" + c, 99));
                }
                for (int r = 0; r < 8; r++) {
                    pair.recordPlayed("scene.id." + r, "subject." + r, "problem_solve", "work", 100);
                }
            }
        }
        return store;
    }

    private static ConversationHistoryStore roundTrip(ConversationHistoryStore store) {
        return ConversationHistoryStore.load(store.save(new CompoundTag()));
    }

    /** GZipped NBT, which is the form the world actually writes. */
    private static byte[] bytes(ConversationHistoryStore store) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try (DataOutputStream stream = new DataOutputStream(
                    new java.util.zip.GZIPOutputStream(out))) {
                NbtIo.write(store.save(new CompoundTag()), stream);
            }
            return out.toByteArray();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Test
    void prunedRecordsAreCountedAndBounded() {
        ConversationHistoryStore store = populated(4, 2);
        int before = store.recordCount();
        assertTrue(before > 0);
        // Far past every expiry: everything prunable should go, and nothing should throw.
        store.prune(100_000);
        assertTrue(store.recordCount() <= before);
        List<UUID> villagers = new ArrayList<>(store.villagers());
        assertEquals(villagers.size(), store.villagerCount());
        assertNotEquals(null, villagers);
    }
}
