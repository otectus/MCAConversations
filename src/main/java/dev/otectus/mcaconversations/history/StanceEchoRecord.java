package dev.otectus.mcaconversations.history;

import dev.otectus.mcaconversations.conversation.OutcomeFamily;
import dev.otectus.mcaconversations.conversation.StanceFamily;
import net.minecraft.nbt.CompoundTag;

import java.util.Locale;
import java.util.Optional;

/**
 * What was decided between these two about one subject, and how it landed.
 *
 * <p>The smallest record that lets a villager say <em>"you told me to save the ink"</em> rather than
 * <em>"as I was saying"</em>. Three things have to be remembered together for that sentence to be
 * true: the stance the player took, the outcome it produced, and the subject it was about. Any two of
 * them without the third is a line that can be wrong — a stance with no subject attaches the player's
 * words to the wrong conversation, and a subject with no outcome cannot tell "you talked me into it"
 * from "you tried to".
 *
 * <p>Deliberately not the same thing as {@link TopicRecencyRecord}, which answers "how long since
 * subject X came up". Recency is a clock; this is a decision. A callback needs the decision.
 *
 * <p>Tokens only, never prose, for the same reason as {@link NarrativeValue}: the save file holds
 * what happened and the locale files turn it into a sentence, so a Portuguese player and an English
 * one are recalling the same event rather than two translations of one.
 *
 * @param stance  what the player's chosen button meant
 * @param outcome how the villager took it
 * @param subject the beat's subject, which is what makes this findable later
 * @param day     the in-game day it happened, so a callback can decline to raise something stale
 */
public record StanceEchoRecord(StanceFamily stance, OutcomeFamily outcome, String subject, long day) {

    private static final String KEY_STANCE = "stance";
    private static final String KEY_OUTCOME = "outcome";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_DAY = "day";

    public StanceEchoRecord {
        subject = subject == null ? "" : subject.trim().toLowerCase(Locale.ROOT);
    }

    /**
     * True when this is worth storing at all.
     *
     * <p>A turn with no subject cannot be found again, and a turn with neither a stance nor an
     * outcome records nothing about what happened. Either way it would be a row that no condition
     * could ever match, which is the definition of dead state.
     */
    public boolean isMeaningful() {
        return !subject.isEmpty() && (stance != null || outcome != null);
    }

    /** How many days ago this was, from {@code today}; never negative. */
    public long daysSince(long today) {
        return Math.max(0L, today - day);
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        if (stance != null) {
            tag.putString(KEY_STANCE, stance.key());
        }
        if (outcome != null) {
            tag.putString(KEY_OUTCOME, outcome.key());
        }
        tag.putString(KEY_SUBJECT, subject);
        tag.putLong(KEY_DAY, day);
        return tag;
    }

    /**
     * Reads one row back.
     *
     * <p>Empty rather than partial on anything unreadable: a stance name a future version renamed, or
     * a row written by a build that stored something else here, must not come back as a half-record a
     * callback could speak from.
     */
    public static Optional<StanceEchoRecord> load(CompoundTag tag) {
        if (tag == null || !tag.contains(KEY_SUBJECT)) {
            return Optional.empty();
        }
        StanceFamily stance = tag.contains(KEY_STANCE)
                ? StanceFamily.byKey(tag.getString(KEY_STANCE)).orElse(null) : null;
        OutcomeFamily outcome = tag.contains(KEY_OUTCOME)
                ? OutcomeFamily.byKey(tag.getString(KEY_OUTCOME)).orElse(null) : null;
        StanceEchoRecord record = new StanceEchoRecord(stance, outcome,
                tag.getString(KEY_SUBJECT), tag.getLong(KEY_DAY));
        return record.isMeaningful() ? Optional.of(record) : Optional.empty();
    }
}
