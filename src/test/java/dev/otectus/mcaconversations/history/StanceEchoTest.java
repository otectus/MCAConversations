package dev.otectus.mcaconversations.history;

import dev.otectus.mcaconversations.conversation.OutcomeFamily;
import dev.otectus.mcaconversations.conversation.StanceFamily;
import net.minecraft.nbt.CompoundTag;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The record that lets a villager name a decision, and the bound that keeps it small.
 *
 * <p>Two things are being protected here. One is that a decision survives a save and a reload
 * unchanged, because a callback that fires on a value corrupted by a round trip is worse than one
 * that never fires. The other is that a save written before 1.5.0 loads as "nothing has been decided
 * yet" rather than as anything at all — this is new state with no earlier form, so it must be absent
 * rather than migrated.
 */
class StanceEchoTest {

    private static StanceEchoRecord echo(String subject, StanceFamily stance,
                                         OutcomeFamily outcome, long day) {
        return new StanceEchoRecord(stance, outcome, subject, day);
    }

    @Test
    void aDecisionSurvivesTheSaveFile() {
        StanceEchoRecord original = echo("work.ink", StanceFamily.PRACTICAL_HELP,
                OutcomeFamily.ACCEPTED, 42);
        Optional<StanceEchoRecord> reloaded = StanceEchoRecord.load(original.save());
        assertTrue(reloaded.isPresent());
        assertEquals(original, reloaded.get());
    }

    @Test
    void halfADecisionStillRoundTrips() {
        // A turn can legitimately have a stance but no outcome — the villager's beat declared none —
        // and that is still worth remembering, because "you pushed back about the ink" is a callback
        // even without knowing how it landed.
        StanceEchoRecord stanceOnly = echo("work.ink", StanceFamily.CHALLENGE, null, 7);
        assertEquals(Optional.of(stanceOnly), StanceEchoRecord.load(stanceOnly.save()));

        StanceEchoRecord outcomeOnly = echo("work.ink", null, OutcomeFamily.HURT, 7);
        assertEquals(Optional.of(outcomeOnly), StanceEchoRecord.load(outcomeOnly.save()));
    }

    @Test
    void aRowThatCouldNeverBeFoundIsNotStored() {
        // No subject means no way to look it up again; no stance and no outcome means it records
        // nothing about what happened. Either way it is a row no condition could ever match.
        assertFalse(echo("", StanceFamily.CANDOR, OutcomeFamily.ACCEPTED, 1).isMeaningful());
        assertFalse(echo("work.ink", null, null, 1).isMeaningful());
        assertTrue(echo("work.ink", StanceFamily.CANDOR, null, 1).isMeaningful());
    }

    @Test
    void anUnreadableRowComesBackEmptyRatherThanHalfBuilt() {
        // A stance name a later version renames must not load as a record with a null stance that a
        // callback would then speak from as though it knew what happened.
        CompoundTag unknownStance = new CompoundTag();
        unknownStance.putString("subject", "work.ink");
        unknownStance.putString("stance", "shouting");
        unknownStance.putLong("day", 3);
        assertEquals(Optional.empty(), StanceEchoRecord.load(unknownStance));

        assertEquals(Optional.empty(), StanceEchoRecord.load(null));
        assertEquals(Optional.empty(), StanceEchoRecord.load(new CompoundTag()));
    }

    @Test
    void subjectsAreNormalisedSoALookupCannotMissOnCase() {
        assertEquals("work.ink", echo("  Work.Ink  ", StanceFamily.CANDOR, null, 1).subject());
    }

    @Test
    void daysSinceNeverGoesNegative() {
        // A server clock moved backwards must read as "today", not as a decision from the future
        // that every window trivially contains.
        assertEquals(0L, echo("s", StanceFamily.CANDOR, null, 50).daysSince(10));
        assertEquals(40L, echo("s", StanceFamily.CANDOR, null, 10).daysSince(50));
    }

    @Test
    void oneDecisionPerSubject_andRedecidingOverwrites() {
        PairHistory pair = new PairHistory();
        assertTrue(pair.recordExchange(echo("work.ink", StanceFamily.PRACTICAL_HELP,
                OutcomeFamily.ACCEPTED, 1)));
        assertTrue(pair.recordExchange(echo("work.ink", StanceFamily.CHALLENGE,
                OutcomeFamily.RESISTED, 5)));
        assertEquals(1, pair.exchanges().size(), "a subject is a state, not a log");
        assertEquals(StanceFamily.CHALLENGE, pair.exchange("work.ink").orElseThrow().stance(),
                "the villager should remember the mind the player ended up with");
    }

    @Test
    void writingTheSameDecisionTwiceIsNotAChange() {
        // The saved-data wrapper marks the world dirty on a real mutation only, so an idempotent
        // write has to report that nothing happened.
        PairHistory pair = new PairHistory();
        StanceEchoRecord decision = echo("work.ink", StanceFamily.EMPATHY, OutcomeFamily.APPRECIATED, 3);
        assertTrue(pair.recordExchange(decision));
        assertFalse(pair.recordExchange(decision));
    }

    @Test
    void theOldestDecisionIsTheOneThatGoesAtTheCap() {
        PairHistory pair = new PairHistory();
        for (int i = 0; i < PairHistory.MAX_EXCHANGES; i++) {
            pair.recordExchange(echo("subject." + i, StanceFamily.CANDOR, OutcomeFamily.ACCEPTED, 100 + i));
        }
        assertEquals(PairHistory.MAX_EXCHANGES, pair.exchanges().size());

        pair.recordExchange(echo("subject.new", StanceFamily.CANDOR, OutcomeFamily.ACCEPTED, 500));
        assertEquals(PairHistory.MAX_EXCHANGES, pair.exchanges().size(), "the cap holds");
        assertTrue(pair.exchange("subject.0").isEmpty(), "the oldest decision is the one dropped");
        assertTrue(pair.exchange("subject.new").isPresent());
        assertTrue(pair.exchange("subject.15").isPresent(), "recent ones are kept");
    }

    @Test
    void aPairWithNoDecisionsSavesNothingExtra() {
        // So a world that never reaches this feature writes the same bytes it did in 1.4.x.
        PairHistory empty = new PairHistory();
        assertFalse(empty.save().contains("exchanges"));
        assertTrue(empty.isEmpty());
    }

    @Test
    void decisionsSurviveAPairRoundTrip() {
        PairHistory pair = new PairHistory();
        pair.recordExchange(echo("work.ink", StanceFamily.PRACTICAL_HELP, OutcomeFamily.ACCEPTED, 9));
        pair.recordExchange(echo("fears.dark", StanceFamily.EMPATHY, OutcomeFamily.ENGAGED, 11));

        PairHistory reloaded = PairHistory.load(pair.save());
        assertEquals(2, reloaded.exchanges().size());
        assertEquals(StanceFamily.PRACTICAL_HELP,
                reloaded.exchange("work.ink").orElseThrow().stance());
        assertEquals(OutcomeFamily.ENGAGED, reloaded.exchange("fears.dark").orElseThrow().outcome());
    }

    @Test
    void aPreviousVersionsSaveLoadsWithNoDecisions() {
        // The whole backward-compatibility story: the key is simply absent, and absent means empty.
        CompoundTag oldSave = new PairHistory().save();
        oldSave.remove("exchanges");
        assertTrue(PairHistory.load(oldSave).exchanges().isEmpty());
    }
}
