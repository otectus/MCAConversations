package dev.otectus.mcaconversations.chat.group;

import dev.otectus.mcaconversations.history.KnowledgeSource;

import java.util.UUID;

/**
 * One villager's line inside somebody else's conversation (spec §11.6).
 *
 * <p>The plan's requirement is two words long and does all the work: "every interjection needs a
 * contract relative to the prior line and a knowledge source". {@link #respondsTo} is the contract —
 * the id of the beat this answers, so an interjection can never be a line that merely happened to be
 * said next. {@link #knowledge} is the source, checked against the shape, so a villager cannot
 * corroborate something they were not in a position to know.
 *
 * <p>A malformed interjection is dropped rather than spoken. There is no salvageable version of a
 * bystander confirming an event they have never heard of.
 *
 * @param shape      which of the five allowed ways of joining in this is
 * @param speaker    the villager saying it
 * @param respondsTo the beat id of the line it answers
 * @param knowledge  how this speaker knows what they are about to say
 */
public record GroupInterjection(GroupShape shape,
                                UUID speaker,
                                String respondsTo,
                                KnowledgeSource knowledge) {

    public GroupInterjection {
        respondsTo = respondsTo == null ? "" : respondsTo.trim().toLowerCase(java.util.Locale.ROOT);
    }

    /**
     * True when this may actually be spoken.
     *
     * <p>Three conditions, and each of them corresponds to a way group conversation goes wrong: a
     * line with no prior beat is a non-sequitur, a line with no speaker is nobody talking, and a line
     * whose footing the shape does not accept is a villager asserting something they cannot support.
     */
    public boolean isWellFormed() {
        return shape != null && speaker != null && !respondsTo.isEmpty()
                && shape.acceptsKnowledge(knowledge);
    }

    /** The say pool this line is drawn from. */
    public String sayPool() {
        return shape.sayPool();
    }

    /** Ticks after the lead line that this should be delivered. */
    public int stagger() {
        return shape.stagger();
    }
}
