package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;

import java.util.Locale;
import java.util.Optional;

/**
 * Something the player explicitly said about themselves (spec §8.6).
 *
 * <p>Two rules make this safe to persist, and both are enforced here rather than by convention.
 *
 * <p><b>Only what was chosen.</b> A claim may be created only by an authored reply or a bound chat
 * intent, so {@link #sourceReply} is never empty for a legitimately created record. Free-form typed
 * text may select an authored claim template; it may never become one. Nothing a player types is
 * stored as a personal profile.
 *
 * <p><b>Contradiction is a conversation, not an overwrite.</b> {@link #contradictedBy} keeps the new
 * value beside the old one and marks the record disputed, so the next scene can ask about the change
 * instead of silently rewriting the past or accusing the player of lying (spec §8.6).
 *
 * @param type          the claim family, e.g. {@code food_preference}, {@code weather_preference}
 * @param value         the claimed token, from that family's closed vocabulary
 * @param sourceReply   the {@code question/answer} that introduced it — the provenance
 * @param day           the day it was said
 * @param confidence    always {@link Confidence#SELF_REPORTED} for a claim; never observed
 * @param previousValue the value this replaced, when the player has changed their answer
 * @param disputed      true while a contradiction is open and unclarified
 */
public record PlayerClaimRecord(String type,
                                NarrativeValue value,
                                String sourceReply,
                                long day,
                                Confidence confidence,
                                Optional<NarrativeValue> previousValue,
                                boolean disputed) {

    private static final String KEY_TYPE = "type";
    private static final String KEY_VALUE = "value";
    private static final String KEY_SOURCE = "source";
    private static final String KEY_DAY = "day";
    private static final String KEY_CONFIDENCE = "confidence";
    private static final String KEY_PREVIOUS = "previous";
    private static final String KEY_DISPUTED = "disputed";

    public PlayerClaimRecord {
        type = normalize(type);
        sourceReply = sourceReply == null ? "" : sourceReply.trim();
        value = value == null ? NarrativeValue.EMPTY : value;
        // A claim is by definition the player's own report. Storing it as anything firmer would let a
        // later scene speak about the player's life as though the villager had seen it.
        confidence = Confidence.SELF_REPORTED;
        previousValue = previousValue == null ? Optional.empty() : previousValue;
    }

    /** A claim introduced by an authored reply. */
    public static PlayerClaimRecord stated(String type, NarrativeValue value, String sourceReply, long day) {
        return new PlayerClaimRecord(type, value, sourceReply, day, Confidence.SELF_REPORTED,
                Optional.empty(), false);
    }

    /** True when this record has provenance and may therefore be spoken about at all. */
    public boolean isAttributable() {
        return !sourceReply.isEmpty() && !type.isEmpty() && !value.isEmpty();
    }

    /**
     * Records a new, different answer.
     *
     * <p>Returns {@code this} when the value is unchanged, so re-picking the same button neither
     * disputes anything nor bumps the day. When it differs, the old value is kept and the record is
     * marked disputed — which is what makes a clarification scene eligible.
     */
    public PlayerClaimRecord contradictedBy(NarrativeValue newValue, String newSourceReply, long newDay) {
        if (newValue == null || newValue.isEmpty() || newValue.equals(value)) {
            return this;
        }
        return new PlayerClaimRecord(type, newValue, newSourceReply, newDay, Confidence.SELF_REPORTED,
                Optional.of(value), true);
    }

    /**
     * Settles an open contradiction, keeping the current value.
     *
     * <p>The old value is dropped at this point and not before: the clarification scene needs both to
     * be able to say "you told me one thing and then another", and once that has been said there is
     * nothing further to ask.
     */
    public PlayerClaimRecord clarified(long newDay) {
        return disputed
                ? new PlayerClaimRecord(type, value, sourceReply, newDay, Confidence.SELF_REPORTED,
                        Optional.empty(), false)
                : this;
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putString(KEY_TYPE, type);
        tag.put(KEY_VALUE, value.save());
        tag.putString(KEY_SOURCE, sourceReply);
        tag.putLong(KEY_DAY, day);
        tag.putString(KEY_CONFIDENCE, confidence.key());
        previousValue.ifPresent(previous -> tag.put(KEY_PREVIOUS, previous.save()));
        if (disputed) {
            tag.putBoolean(KEY_DISPUTED, true);
        }
        return tag;
    }

    public static Optional<PlayerClaimRecord> load(CompoundTag tag) {
        if (tag == null) {
            return Optional.empty();
        }
        String type = tag.getString(KEY_TYPE);
        if (type.isBlank()) {
            return Optional.empty();
        }
        return Optional.of(new PlayerClaimRecord(type,
                NarrativeValue.load(tag.getCompound(KEY_VALUE)),
                tag.getString(KEY_SOURCE), tag.getLong(KEY_DAY),
                Confidence.SELF_REPORTED,
                tag.contains(KEY_PREVIOUS) ? Optional.of(NarrativeValue.load(tag.getCompound(KEY_PREVIOUS)))
                        : Optional.empty(),
                tag.getBoolean(KEY_DISPUTED)));
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
