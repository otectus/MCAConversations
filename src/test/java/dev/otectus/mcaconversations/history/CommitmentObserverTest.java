package dev.otectus.mcaconversations.history;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.OptionalLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * When a promise is kept, when it is broken, and — mostly — when neither is true.
 *
 * <p>The rule these cover is the one that decides whether this mod is allowed to say "you said you
 * would". Before 1.5.0 nothing ever settled a promise except the player pressing a button that said
 * they had kept it, so every one of these outcomes was unreachable. Now that they are reachable, the
 * cases that must *not* fire matter more than the ones that must: a villager who wrongly accuses a
 * player of breaking a promise is worse than one who never mentions it again.
 *
 * <p>{@link CommitmentObserver#outcomeOnMeeting} is pure, so the whole rule is testable without a
 * world, a server or a save file.
 */
class CommitmentObserverTest {

    private static CommitmentRecord promise(CommitmentResolver resolver, long created, Long due) {
        return CommitmentRecord.made("test.promise", resolver,
                NarrativeValue.registryId("minecraft:torch"), CommitmentRecord.Party.PLAYER,
                created, due == null ? OptionalLong.empty() : OptionalLong.of(due), Optional.empty());
    }

    @Test
    void comingBackOnTheDayKeepsAVisitPromise() {
        CommitmentRecord visit = promise(CommitmentResolver.VISIT_AFTER_DAY, 1, 4L);
        assertEquals(CommitmentRecord.State.KEPT, CommitmentObserver.outcomeOnMeeting(visit, 4));
        assertEquals(CommitmentRecord.State.KEPT, CommitmentObserver.outcomeOnMeeting(visit, 9));
    }

    @Test
    void comingBackEarlyDoesNotKeepIt() {
        // Nor does it break it. Turning up the day before you said you would is simply not the event.
        CommitmentRecord visit = promise(CommitmentResolver.VISIT_AFTER_DAY, 1, 4L);
        assertNull(CommitmentObserver.outcomeOnMeeting(visit, 3));
    }

    @Test
    void arrivingOnTheLastForgivenDayStillKeepsIt() {
        // The ordering that makes this pass is deliberate: the visit is credited before the deadline
        // is judged. Reversed, a player who turned up late but inside the grace window would be told
        // they had broken the promise they were in the act of keeping.
        CommitmentRecord visit = promise(CommitmentResolver.VISIT_AFTER_DAY, 1, 4L);
        long lastForgiven = 4 + CommitmentObserver.BROKEN_AFTER_GRACE_DAYS;
        assertEquals(CommitmentRecord.State.KEPT,
                CommitmentObserver.outcomeOnMeeting(visit, lastForgiven));
        assertEquals(CommitmentRecord.State.KEPT,
                CommitmentObserver.outcomeOnMeeting(visit, lastForgiven + 1),
                "a visit keeps a visit promise however late it is");
    }

    @Test
    void aDeliveryLeftUndoneLongEnoughIsBroken() {
        CommitmentRecord delivery = promise(CommitmentResolver.GIFT_TAG_RECEIVED, 1, 4L);
        assertNull(CommitmentObserver.outcomeOnMeeting(delivery, 4), "due today is not late");
        assertNull(CommitmentObserver.outcomeOnMeeting(delivery, 4 + CommitmentObserver.BROKEN_AFTER_GRACE_DAYS),
                "the last day of grace is still forgiven");
        assertEquals(CommitmentRecord.State.BROKEN,
                CommitmentObserver.outcomeOnMeeting(delivery, 5 + CommitmentObserver.BROKEN_AFTER_GRACE_DAYS));
    }

    @Test
    void aPromiseWithNoDeadlineIsNeverBroken() {
        // Nothing came due, so nothing was missed. This is the difference between a promise and a wish.
        CommitmentRecord open = promise(CommitmentResolver.GIFT_TAG_RECEIVED, 1, null);
        assertNull(CommitmentObserver.outcomeOnMeeting(open, 500));
    }

    @Test
    void aNeutralPromiseIsNeverJudged() {
        // manual_neutral is the honest escape hatch for prose that cannot claim success or failure.
        // It may be remembered as something that was said, never as something kept or broken.
        CommitmentRecord noted = promise(CommitmentResolver.MANUAL_NEUTRAL, 1, 2L);
        assertNull(CommitmentObserver.outcomeOnMeeting(noted, 900));
    }

    @Test
    void anAlreadySettledPromiseIsLeftAlone() {
        CommitmentRecord kept = promise(CommitmentResolver.GIFT_TAG_RECEIVED, 1, 2L)
                .resolved(CommitmentRecord.State.KEPT, 2);
        assertNull(CommitmentObserver.outcomeOnMeeting(kept, 900));
        assertNull(CommitmentObserver.outcomeOnMeeting(null, 900));
    }

    @Test
    void aPromiseNamesAnItemOnlyWhenItReallyNamesOne() {
        // The registry itself is not available in a unit JVM, so what is checked here is the half that
        // decides whether a gift could ever settle this promise at all: a token, a day, an empty value
        // or a malformed id all mean no, and each of those reaching the registry lookup would be a
        // promise settled by accident.
        assertEquals("minecraft:torch",
                String.valueOf(CommitmentObserver.promisedId(NarrativeValue.registryId("minecraft:torch"))));
        assertEquals("forge:ingots/iron",
                String.valueOf(CommitmentObserver.promisedId(NarrativeValue.registryId("forge:ingots/iron"))),
                "a tag id is a legal target: the resolver is named for tags");

        assertNull(CommitmentObserver.promisedId(NarrativeValue.token("torch")));
        assertNull(CommitmentObserver.promisedId(NarrativeValue.day(4)));
        assertNull(CommitmentObserver.promisedId(NarrativeValue.EMPTY));
        assertNull(CommitmentObserver.promisedId(null));
    }
}
