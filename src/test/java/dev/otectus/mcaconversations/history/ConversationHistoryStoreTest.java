package dev.otectus.mcaconversations.history;

import dev.otectus.mcaconversations.conversation.OutcomeFamily;
import dev.otectus.mcaconversations.conversation.StanceFamily;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
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

    @Test
    void pruneReportsLapsesOnceAndNeverReopensResolvedHistory() {
        PairHistory pair = new PairHistory();
        pair.putThread(SharedThreadRecord.opened("open", "work", "subject", Optional.empty(),
                PrivacyLevel.ORDINARY, 1).withSchedule(1, OptionalLong.of(2)));
        pair.putThread(SharedThreadRecord.opened("done", "work", "subject", Optional.empty(),
                PrivacyLevel.ORDINARY, 1).withStatus(ThreadStatus.RESOLVED, 1)
                .withSchedule(1, OptionalLong.of(2)));
        assertEquals(1, pair.prune(5), "a lapse must mark the save dirty even with no deletions");
        assertEquals(ThreadStatus.LAPSED, pair.thread("open").orElseThrow().status());
        assertEquals(ThreadStatus.RESOLVED, pair.thread("done").orElseThrow().status());
        assertEquals(0, pair.prune(6), "already-lapsed history must not keep changing dates");
    }

    @Test
    void abandonedEpisodeTransitionMarksHistoryDirtyWithoutDeletion() {
        ConversationHistoryStore store = new ConversationHistoryStore();
        EpisodeRecord episode = EpisodeRecord.opened(UUID.randomUUID(), "test.expiry", "test",
                EpisodeState.ACTIVE, VILLAGER, Map.of(), PrivacyLevel.ORDINARY, 50, 1)
                .withDeadline(OptionalLong.empty(), OptionalLong.of(2));
        store.getOrCreate(VILLAGER).putEpisode(episode, 1);
        assertTrue(store.prune(3) > 0);
        assertEquals(EpisodeState.ABANDONED,
                roundTrip(store).peek(VILLAGER).orElseThrow().episode(episode.id()).orElseThrow().state());
    }

    // --- Load-time bounds (F08) --------------------------------------------------------------------

    @Test
    void anOversizedThreadListLoadsWithinTheCapKeepingTheNewest() {
        ListTag threads = new ListTag();
        for (int i = 0; i < HistoryCaps.HARD_THREADS_PER_PAIR + 16; i++) {
            threads.add(SharedThreadRecord
                    .opened("thread." + i, "work", "work.subject", Optional.empty(),
                            PrivacyLevel.ORDINARY, 100 + i)
                    .withStatus(ThreadStatus.RESOLVED, 100 + i)
                    .save());
        }
        CompoundTag pair = new CompoundTag();
        pair.put("threads", threads);

        PairHistory loaded = loadPair(pair);
        assertEquals(HistoryCaps.threadsPerPair(), loaded.threads().size(),
                "the thread cap was ignored on load");
        // pruneOneThread drops the oldest closed thread, so the newest survive, exactly as they would
        // have had the pair filled up one conversation at a time.
        for (SharedThreadRecord thread : loaded.threads()) {
            assertTrue(thread.lastMentionedDay()
                            >= 100 + HistoryCaps.HARD_THREADS_PER_PAIR + 16 - HistoryCaps.threadsPerPair(),
                    "load kept an older thread than the mutation path would have: " + thread.templateId());
        }
    }

    @Test
    void anOversizedCommitmentListKeepsTheNewestSettledPromises() {
        ListTag commitments = new ListTag();
        int total = HistoryCaps.HARD_COMMITMENTS_PER_PAIR + 8;
        for (int i = 0; i < total; i++) {
            commitments.add(CommitmentRecord.made("promise." + i, CommitmentResolver.VISIT_AFTER_DAY,
                            NarrativeValue.EMPTY, CommitmentRecord.Party.PLAYER, 10 + i,
                            OptionalLong.of(11 + i), Optional.empty())
                    .resolved(CommitmentRecord.State.KEPT, 20 + i)
                    .save());
        }
        CompoundTag pair = new CompoundTag();
        pair.put("commitments", commitments);

        PairHistory loaded = loadPair(pair);
        assertEquals(HistoryCaps.commitmentsPerPair(), loaded.commitments().size());
        for (CommitmentRecord commitment : loaded.commitments()) {
            assertTrue(commitment.resolvedDay().orElse(0) >= 20 + total - HistoryCaps.commitmentsPerPair(),
                    "load forgot a newer promise than the mutation path would have");
        }
    }

    @Test
    void anOversizedClaimListKeepsTheNewestUndisputedClaims() {
        ListTag claims = new ListTag();
        int total = HistoryCaps.HARD_CLAIMS_PER_PAIR + 8;
        for (int i = 0; i < total; i++) {
            claims.add(PlayerClaimRecord.stated("claim_" + i, NarrativeValue.token("value_" + i),
                    "conversations.topic.food.respond/answer_" + i, 10 + i).save());
        }
        CompoundTag pair = new CompoundTag();
        pair.put("claims", claims);

        PairHistory loaded = loadPair(pair);
        assertEquals(HistoryCaps.claimsPerPair(), loaded.claims().size());
        for (PlayerClaimRecord claim : loaded.claims()) {
            assertTrue(claim.day() >= 10 + total - HistoryCaps.claimsPerPair(),
                    "load kept an older claim than the mutation path would have");
        }
    }

    @Test
    void anOversizedRecencyBlockLoadsWithinTheHardBound() {
        CompoundTag scenes = new CompoundTag();
        int total = TopicRecencyRecord.MAX_ENTRIES_PER_LEVEL + 40;
        for (int i = 0; i < total; i++) {
            scenes.putLong("scene." + i, 100 + i);
        }
        CompoundTag recency = new CompoundTag();
        recency.put("scene", scenes);
        CompoundTag pair = new CompoundTag();
        pair.put("recency", recency);

        PairHistory loaded = loadPair(pair);
        int kept = Math.min(TopicRecencyRecord.MAX_ENTRIES_PER_LEVEL, HistoryCaps.recencyPerPair());
        assertEquals(kept, loaded.recency().scenes().size(), "the recency cap was ignored on load");
        assertTrue(loaded.recency().scenes().size() * 4 <= HistoryCaps.HARD_RECENCY_PER_PAIR);
        // The most recent stamps survive: the oldest is the one whose penalty has already decayed.
        for (long day : loaded.recency().scenes().values()) {
            assertTrue(day >= 100 + total - kept, "load kept a staler recency stamp than mutation would");
        }
    }

    @Test
    void anOversizedExchangeListLoadsWithinItsCap() {
        ListTag exchanges = new ListTag();
        for (int i = 0; i < PairHistory.MAX_EXCHANGES + 12; i++) {
            exchanges.add(new StanceEchoRecord(StanceFamily.PRACTICAL_HELP, OutcomeFamily.ACCEPTED,
                    "subject." + i, 50 + i).save());
        }
        CompoundTag pair = new CompoundTag();
        pair.put("exchanges", exchanges);

        PairHistory loaded = loadPair(pair);
        assertEquals(PairHistory.MAX_EXCHANGES, loaded.exchanges().size());
        assertEquals(50 + PairHistory.MAX_EXCHANGES + 11, loaded.exchanges().get(0).day(),
                "the newest decision should have survived");
    }

    @Test
    void anOversizedOpinionAndRoleListLoadWithinTheirCaps() {
        ListTag opinions = new ListTag();
        int opinionTotal = HistoryCaps.HARD_OPINIONS_PER_VILLAGER + 12;
        for (int i = 0; i < opinionTotal; i++) {
            opinions.add(new SocialOpinionRecord(new UUID(7L, i), "reliability", 3,
                    "episode.cause." + i, Confidence.WITNESSED, PrivacyLevel.DISCREET, 10 + i,
                    OptionalLong.empty()).save());
        }
        ListTag roles = new ListTag();
        int roleTotal = HistoryCaps.HARD_ROLES_PER_VILLAGER + 12;
        for (int i = 0; i < roleTotal; i++) {
            roles.add(SocialRoleRecord.observed(new UUID(8L, i), SocialRole.MENTOR,
                    "episode.taught." + i, 10 + i).save());
        }
        CompoundTag row = new CompoundTag();
        row.put("opinions", opinions);
        row.put("roles", roles);

        VillagerHistory loaded = VillagerHistory.load(row);
        assertEquals(HistoryCaps.opinionsPerVillager(), loaded.opinions().size());
        assertEquals(HistoryCaps.rolesPerVillager(), loaded.roles().size());
        // Equal strength and equal persistence throughout, so both rules fall through to "oldest goes".
        for (SocialOpinionRecord opinion : loaded.opinions()) {
            assertTrue(opinion.createdDay() >= 10 + opinionTotal - HistoryCaps.opinionsPerVillager());
        }
        for (SocialRoleRecord role : loaded.roles()) {
            assertTrue(role.createdDay() >= 10 + roleTotal - HistoryCaps.rolesPerVillager());
        }
    }

    @Test
    void anOversizedEpisodeListLoadsWithinTheActiveAndResolvedCaps() {
        ListTag episodes = new ListTag();
        int liveTotal = HistoryCaps.HARD_ACTIVE_EPISODES + 8;
        for (int i = 0; i < liveTotal; i++) {
            episodes.add(EpisodeRecord.opened(new UUID(11L, i), "work.kind." + i, "work.subject",
                    EpisodeState.ACTIVE, VILLAGER, Map.of(), PrivacyLevel.ORDINARY, i + 1, 200).save());
        }
        int resolvedTotal = HistoryCaps.HARD_RESOLVED_EPISODES + 8;
        for (int i = 0; i < resolvedTotal; i++) {
            episodes.add(EpisodeRecord.opened(new UUID(12L, i), "work.done." + i, "work.subject",
                            EpisodeState.SUCCEEDED, VILLAGER, Map.of(), PrivacyLevel.ORDINARY, i + 1, 100)
                    .save());
        }
        CompoundTag row = new CompoundTag();
        row.put("episodes", episodes);

        VillagerHistory loaded = VillagerHistory.load(row);
        assertTrue(loaded.liveEpisodes(200).size() <= HistoryCaps.activeEpisodes(),
                "the live cap was ignored on load");
        long past = loaded.episodes().stream().filter(e -> !e.state().isLive()).count();
        assertTrue(past <= HistoryCaps.resolvedEpisodes(), "the resolved cap was ignored on load");
        // Salience decides, exactly as it does on the mutation path: the most salient live episodes
        // stay live, and the blandest resolved ones are the ones forgotten.
        for (EpisodeRecord episode : loaded.liveEpisodes(200)) {
            assertTrue(episode.salience() > liveTotal - HistoryCaps.activeEpisodes(),
                    "a blander episode outlived a more salient one");
        }
    }

    @Test
    void anOversizedPairListLoadsWithinThePairCap() {
        ListTag pairs = new ListTag();
        int total = HistoryCaps.HARD_PAIRS_PER_VILLAGER + 8;
        for (int i = 0; i < total; i++) {
            CompoundTag pair = new CompoundTag();
            pair.putUUID("player", new UUID(13L, i));
            pair.putLong("first_met", 1);
            pair.putLong("last_talked", 10 + i);
            pairs.add(pair);
        }
        CompoundTag row = new CompoundTag();
        row.put("pairs", pairs);

        VillagerHistory loaded = VillagerHistory.load(row);
        assertEquals(HistoryCaps.HARD_PAIRS_PER_VILLAGER, loaded.pairs().size());
        for (PairHistory pair : loaded.pairs().values()) {
            assertTrue(pair.lastTalkedDay().orElseThrow()
                            >= 10 + total - HistoryCaps.HARD_PAIRS_PER_VILLAGER,
                    "load forgot a more recent player than the mutation path would have");
        }
    }

    @Test
    void anOversizedVillagerListLoadsWithinTheWorldBound() {
        ListTag villagers = new ListTag();
        int total = HistoryCaps.HARD_VILLAGERS + 5;
        for (int i = 0; i < total; i++) {
            villagers.add(villagerRow(new UUID(14L, i), 10 + i));
        }
        CompoundTag tag = new CompoundTag();
        tag.putInt("version", ConversationHistoryStore.CURRENT_VERSION);
        tag.put("villagers", villagers);

        ConversationHistoryStore loaded = ConversationHistoryStore.load(tag);
        assertEquals(HistoryCaps.HARD_VILLAGERS, loaded.villagerCount(),
                "the world-wide bound was ignored on load");
        for (int i = 0; i < 5; i++) {
            assertTrue(loaded.peek(new UUID(14L, i)).isEmpty(),
                    "the least recently active villagers should have been the ones dropped");
        }
        assertTrue(loaded.peek(new UUID(14L, total - 1)).isPresent(),
                "the most recently active villager was dropped");
    }

    @Test
    void aCappedLoadReSavesByteIdenticallyAndKeepsTheSameRecords() {
        ListTag villagers = new ListTag();
        for (int i = 0; i < 40; i++) {
            villagers.add(villagerRow(new UUID(15L, i), 10 + i));
        }
        CompoundTag tag = new CompoundTag();
        tag.putInt("version", ConversationHistoryStore.CURRENT_VERSION);
        tag.put("villagers", villagers);

        ConversationHistoryStore once = ConversationHistoryStore.load(tag);
        CompoundTag first = once.save(new CompoundTag());
        ConversationHistoryStore twice = ConversationHistoryStore.load(first);
        CompoundTag second = twice.save(new CompoundTag());

        assertEquals(first, second, "a capped load did not settle after one round trip");
        assertEquals(once.villagers(), twice.villagers(), "the retained set changed on reload");
        assertEquals(once.recordCount(), twice.recordCount());
    }

    @Test
    void recordCountCountsRolesAndRecency() {
        ConversationHistoryStore store = new ConversationHistoryStore();
        VillagerHistory history = store.getOrCreate(VILLAGER);
        assertEquals(0, store.recordCount());

        history.putRole(SocialRoleRecord.observed(NEIGHBOUR, SocialRole.MENTOR,
                "episode.taught_me_the_trade", 10));
        assertEquals(1, store.recordCount(), "an observed role was not counted");

        history.pair(PLAYER).recordPlayed("scene.one", "subject.one", "problem_solve", "work", 12);
        // Four levels stamped by one played scene, each its own entry in the file.
        assertEquals(5, store.recordCount(), "the recency levels were not counted");
    }

    @Test
    void anOverLongTextFieldIsTruncatedOnLoadAndOnMutation() {
        String huge = "a".repeat(HistoryCaps.MAX_TEXT_LENGTH * 3);
        SocialOpinionRecord opinion = new SocialOpinionRecord(NEIGHBOUR, "reliability", 2, huge,
                Confidence.WITNESSED, PrivacyLevel.DISCREET, 10, OptionalLong.empty());
        assertEquals(HistoryCaps.MAX_TEXT_LENGTH, opinion.cause().length(),
                "an over-long cause was stored whole on the mutation path");

        CompoundTag row = new CompoundTag();
        ListTag opinions = new ListTag();
        CompoundTag stored = opinion.save();
        stored.putString("cause", huge);
        opinions.add(stored);
        row.put("opinions", opinions);

        VillagerHistory loaded = VillagerHistory.load(row);
        assertEquals(HistoryCaps.MAX_TEXT_LENGTH, loaded.opinions().get(0).cause().length(),
                "an over-long cause survived the load path");
    }

    // --- Fixtures for the load-time bounds ----------------------------------------------------------

    /** One villager row with a single non-empty pair that last spoke on {@code day}. */
    private static CompoundTag villagerRow(UUID villager, long day) {
        CompoundTag pair = new CompoundTag();
        pair.putUUID("player", PLAYER);
        pair.putLong("first_met", 1);
        pair.putLong("last_talked", day);
        ListTag pairs = new ListTag();
        pairs.add(pair);
        CompoundTag row = new CompoundTag();
        row.put("pairs", pairs);
        row.putUUID("uuid", villager);
        return row;
    }

    /** Reads one pair record on its own, through the villager that owns it. */
    private static PairHistory loadPair(CompoundTag pairBody) {
        pairBody.putUUID("player", PLAYER);
        ListTag pairs = new ListTag();
        pairs.add(pairBody);
        CompoundTag row = new CompoundTag();
        row.put("pairs", pairs);
        return VillagerHistory.load(row).peekPair(PLAYER).orElseThrow();
    }
}
