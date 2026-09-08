package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Which villager a full history forgets, and which it refuses to (finding F09).
 *
 * <p>Before this, the world-wide bound evicted whatever came first out of a hash map — which in
 * practice meant the villager the world met earliest, obligations and open conversations included. A
 * player who reached the bound could lose a promise made an hour ago to make room for a villager they
 * walked past once.
 *
 * <p>The order is: never a protected villager, then an empty history, then the least recently active,
 * then the smallest UUID. The last step exists so that two servers handed the same save forget the
 * same villager; the first exists so that no bound is ever paid for with somebody's promise.
 */
class EvictionOrderTest {

    private static final UUID PLAYER = UUID.fromString("00000000-0000-4000-8000-0000000000cc");

    @Test
    void theEvictedVillagerIsTheLeastRecentlyActiveUnprotectedOne() {
        ConversationHistoryStore store = full();
        // The villager who has been quiet longest speaks again, and so should stop being the victim.
        store.peek(villager(0)).orElseThrow().pair(PLAYER).touch(90_000);

        store.getOrCreate(UUID.fromString("00000000-0000-4000-8000-0000000000ff"));

        assertTrue(store.peek(villager(0)).isPresent(),
                "a villager who spoke a moment ago was evicted as though they were the stalest");
        assertTrue(store.peek(villager(1)).isEmpty(),
                "the least recently active villager should have been the one forgotten");
        assertEquals(HistoryCaps.HARD_VILLAGERS, store.villagerCount());
    }

    @Test
    void theSameSaveEvictsTheSameVillagerTwice() {
        CompoundTag saved = full().save(new CompoundTag());

        ConversationHistoryStore first = ConversationHistoryStore.load(saved);
        ConversationHistoryStore second = ConversationHistoryStore.load(saved);
        first.getOrCreate(newcomer());
        second.getOrCreate(newcomer());

        List<UUID> firstSurvivors = first.villagers();
        assertEquals(firstSurvivors, second.villagers(),
                "two loads of one save disagreed about who to forget");
        assertTrue(first.peek(villager(0)).isEmpty(),
                "the stalest villager did not survive the round trip as the stalest");
    }

    @Test
    void anObligationIsNeverEvictedToMakeRoom() {
        ConversationHistoryStore store = new ConversationHistoryStore();
        for (int i = 0; i < HistoryCaps.HARD_VILLAGERS; i++) {
            PairHistory pair = store.getOrCreate(villager(i)).pair(PLAYER);
            pair.touch(i);
            pair.putCommitment(CommitmentRecord.made("promise", CommitmentResolver.VISIT_AFTER_DAY,
                    NarrativeValue.EMPTY, CommitmentRecord.Party.PLAYER, i, OptionalLong.of(i + 5),
                    Optional.empty()));
        }

        VillagerHistory detached = store.getOrCreate(newcomer());

        assertEquals(HistoryCaps.HARD_VILLAGERS, store.villagerCount(),
                "a promise was dropped to make room for a villager");
        assertTrue(store.peek(newcomer()).isEmpty(),
                "the newcomer should not have been remembered at all");
        // A detached record: usable for this conversation, simply never stored.
        assertTrue(detached.isEmpty());
        assertTrue(store.peek(villager(0)).orElseThrow().peekPair(PLAYER).orElseThrow()
                .commitment("promise").orElseThrow().isOutstanding());
    }

    @Test
    void aVillagerInALiveConversationIsNotEvicted() {
        ConversationHistoryStore store = full();
        store.setLiveSessionPredicate(villager(0)::equals);

        store.getOrCreate(newcomer());

        assertTrue(store.peek(villager(0)).isPresent(),
                "the villager the player is talking to was evicted mid-conversation");
        assertTrue(store.peek(villager(1)).isEmpty(),
                "the next stalest villager should have gone instead");
    }

    @Test
    void anEmptyHistoryGoesBeforeAnyRememberedOne() {
        // A villager looked at but never spoken to holds nothing worth keeping, and is cheaper to
        // forget than anyone with something to say, however long ago they said it.
        ConversationHistoryStore store = new ConversationHistoryStore();
        store.getOrCreate(villager(0));
        for (int i = 1; i < HistoryCaps.HARD_VILLAGERS; i++) {
            store.getOrCreate(villager(i)).pair(PLAYER).touch(i);
        }
        assertTrue(store.peek(villager(0)).orElseThrow().isEmpty());

        store.getOrCreate(newcomer());

        assertTrue(store.peek(villager(0)).isEmpty(), "an empty history was not the first choice");
        assertTrue(store.peek(villager(1)).isPresent(),
                "a remembered villager was forgotten while an empty record was still there");
    }

    @Test
    void theDefaultPredicateProtectsNobody() {
        ConversationHistoryStore store = full();
        store.setLiveSessionPredicate(null);
        store.getOrCreate(newcomer());
        assertFalse(store.peek(villager(0)).isPresent(),
                "clearing the predicate should leave eviction to activity alone");
    }

    /** A store at the bound: every villager remembered, each less recently active than the next. */
    private static ConversationHistoryStore full() {
        ConversationHistoryStore store = new ConversationHistoryStore();
        for (int i = 0; i < HistoryCaps.HARD_VILLAGERS; i++) {
            store.getOrCreate(villager(i)).pair(PLAYER).touch(i);
        }
        return store;
    }

    private static UUID villager(int index) {
        return new UUID(21L, index);
    }

    private static UUID newcomer() {
        return new UUID(99L, 99L);
    }
}
