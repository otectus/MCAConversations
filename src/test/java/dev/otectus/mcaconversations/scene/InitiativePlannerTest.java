package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.history.CommitmentRecord;
import dev.otectus.mcaconversations.history.CommitmentResolver;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.EpisodeState;
import dev.otectus.mcaconversations.history.NarrativeValue;
import dev.otectus.mcaconversations.history.PairHistory;
import dev.otectus.mcaconversations.history.PrivacyLevel;
import dev.otectus.mcaconversations.history.SharedThreadRecord;
import dev.otectus.mcaconversations.history.ThreadStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * What a villager decides to bring up, and — far more often — that it decides not to.
 *
 * <p>Detection is the half of villager initiative that can be wrong in a way a player notices. A
 * villager who stays quiet when they are owed something is a missed moment; a villager who raises
 * something that is not true is a bug the player will read as the mod inventing a memory. So every
 * trigger here is tied to a record that already exists, and the default — the case for almost every
 * villager a player walks past — is silence.
 *
 * <p>These exercise the pure detection and ranking. Speaking, gating and budget-spending need a world
 * and are covered by the in-world checklist.
 */
class InitiativePlannerTest {

    private static final long TODAY = 100L;

    private static PairHistory pair(long lastTalked) {
        PairHistory pair = new PairHistory();
        if (lastTalked >= 0) {
            pair.touch(lastTalked);
        }
        return pair;
    }

    private static CommitmentRecord duePromise(long dueDay) {
        return CommitmentRecord.made("work.bring_torches", CommitmentResolver.GIFT_TAG_RECEIVED,
                NarrativeValue.registryId("minecraft:torch"), CommitmentRecord.Party.PLAYER,
                dueDay - 3, OptionalLong.of(dueDay), Optional.empty());
    }

    private static SharedThreadRecord thread(ThreadStatus status, long day) {
        return SharedThreadRecord.opened("work.unfinished_delve", "work", "work.delve",
                        Optional.empty(), PrivacyLevel.ORDINARY, day)
                .withStatus(status, day);
    }

    private static EpisodeRecord episode(long updatedDay) {
        return EpisodeRecord.opened(UUID.randomUUID(), "work.bad_route", "work.route",
                EpisodeState.ACTIVE, UUID.randomUUID(), Map.of(), PrivacyLevel.ORDINARY, 60,
                updatedDay);
    }

    @Test
    void aVillagerWithNothingOutstandingSaysNothing() {
        // The overwhelmingly common case, and the one that decides whether this feature is pleasant
        // or exhausting: walking through a village must not be a wall of villagers with opinions.
        assertTrue(InitiativePlanner.candidates(pair(90), List.of(), TODAY).isEmpty());
        assertTrue(InitiativePlanner.candidates(null, List.of(), TODAY).isEmpty());
        assertTrue(InitiativePlanner.candidates(new PairHistory(), List.of(), TODAY).isEmpty(),
                "a pair that has never spoken is owed nothing");
    }

    @Test
    void aPromiseThatHasComeDueIsRaised() {
        PairHistory pair = pair(90);
        pair.putCommitment(duePromise(TODAY));
        assertEquals(Set.of(ScenePurpose.DUE_COMMITMENT),
                InitiativePlanner.candidates(pair, List.of(), TODAY));
    }

    @Test
    void aPromiseNotYetDueIsNotRaised() {
        PairHistory pair = pair(90);
        pair.putCommitment(duePromise(TODAY + 5));
        assertTrue(InitiativePlanner.candidates(pair, List.of(), TODAY).isEmpty());
    }

    @Test
    void aSettledPromiseIsNotRaised() {
        // Keeping a promise must not leave the villager still asking about it.
        PairHistory pair = pair(90);
        pair.putCommitment(duePromise(TODAY).resolved(CommitmentRecord.State.KEPT, TODAY));
        assertTrue(InitiativePlanner.candidates(pair, List.of(), TODAY).isEmpty());
    }

    @Test
    void anUnacknowledgedRuptureIsRaised() {
        PairHistory pair = pair(90);
        pair.putThread(thread(ThreadStatus.RUPTURED, 95));
        assertTrue(InitiativePlanner.candidates(pair, List.of(), TODAY)
                .contains(ScenePurpose.REPAIR));
    }

    @Test
    void aResolvedThreadIsNotRaised() {
        PairHistory pair = pair(90);
        pair.putThread(thread(ThreadStatus.RESOLVED, 95));
        assertTrue(InitiativePlanner.candidates(pair, List.of(), TODAY).isEmpty());
    }

    @Test
    void aSituationThatMovedSinceTheyLastSpokeIsRaised() {
        assertTrue(InitiativePlanner.candidates(pair(90), List.of(episode(95)), TODAY)
                .contains(ScenePurpose.STATE_CHANGE));
    }

    @Test
    void aSituationTheyAlreadyHeardAboutIsNotRaised() {
        // Anchored on when these two last spoke, not on a fixed number of days: the player who was
        // here yesterday and the one who has been away a season are each told what is news to them.
        assertFalse(InitiativePlanner.candidates(pair(96), List.of(episode(95)), TODAY)
                .contains(ScenePurpose.STATE_CHANGE));
    }

    @Test
    void aStrangerIsNotOwedAnUpdate() {
        // No last-talked day means they have never spoken, so nothing about the villager's situation
        // can be news between them yet.
        assertFalse(InitiativePlanner.candidates(new PairHistory(), List.of(episode(95)), TODAY)
                .contains(ScenePurpose.STATE_CHANGE));
    }

    @Test
    void aDuePromiseOutranksEverythingElseOutstanding() {
        // Ranking is InitiativeGate.mostImportant, by interruption cost: the things worth
        // interrupting somebody for are exactly the things that cost least to raise.
        PairHistory pair = pair(90);
        pair.putCommitment(duePromise(TODAY));
        pair.putThread(thread(ThreadStatus.RUPTURED, 95));

        Set<ScenePurpose> candidates = InitiativePlanner.candidates(pair, List.of(episode(95)), TODAY);
        assertTrue(candidates.size() >= 3, "several things are outstanding at once: " + candidates);
        assertEquals(Optional.of(ScenePurpose.STATE_CHANGE),
                InitiativeGate.mostImportant(candidates),
                "a changed situation is the cheapest interruption, so it goes first");
    }

    @Test
    void onlyOneThingIsEverRaised() {
        // The plan's rule: never more than one. Whatever is outstanding, the villager says one thing.
        PairHistory pair = pair(90);
        pair.putCommitment(duePromise(TODAY));
        pair.putThread(thread(ThreadStatus.RUPTURED, 95));
        assertTrue(InitiativeGate.mostImportant(
                InitiativePlanner.candidates(pair, List.of(episode(95)), TODAY)).isPresent());
    }

    @Test
    void onlyTheDuePromiseNamesAThing() {
        // The other three lines stand on their own. A slot the line does not have an argument for
        // would render as a raw format specifier in front of the player.
        PairHistory pair = pair(90);
        pair.putCommitment(duePromise(TODAY));

        assertTrue(InitiativePlanner.slotFor(ScenePurpose.DUE_COMMITMENT, pair, TODAY).isPresent());
        assertTrue(InitiativePlanner.slotFor(ScenePurpose.REPAIR, pair, TODAY).isEmpty());
        assertTrue(InitiativePlanner.slotFor(ScenePurpose.RESUME, pair, TODAY).isEmpty());
        assertTrue(InitiativePlanner.slotFor(ScenePurpose.STATE_CHANGE, pair, TODAY).isEmpty());
        assertTrue(InitiativePlanner.slotFor(ScenePurpose.DUE_COMMITMENT, null, TODAY).isEmpty());
    }

    @Test
    void theThingNamedIsTheThingPromised() {
        PairHistory pair = pair(90);
        pair.putCommitment(duePromise(TODAY));
        assertEquals("minecraft:torch",
                InitiativePlanner.slotFor(ScenePurpose.DUE_COMMITMENT, pair, TODAY)
                        .orElseThrow().raw());
    }

    @Test
    void everyRaisableThingIsAnInitiativeAndHasALine() {
        // The gate refuses anything that is not an initiative outright, and the pool it names has to
        // exist or the villager opens its mouth and nothing comes out.
        for (ScenePurpose purpose : List.of(ScenePurpose.DUE_COMMITMENT, ScenePurpose.REPAIR,
                ScenePurpose.RESUME, ScenePurpose.STATE_CHANGE)) {
            assertTrue(purpose.isInitiative(), purpose + " must be an initiative");
            assertEquals(InitiativePlanner.PHRASE_PREFIX + purpose.key(),
                    new InitiativePlanner.Opening(purpose, Optional.empty()).phrase());
        }
    }
}
