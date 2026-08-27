package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;

import java.util.Locale;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;
import java.util.UUID;

/**
 * What one villager thinks of one named neighbour, and why (spec §8.7).
 *
 * <p>Directional and <b>caused</b>. An opinion with no cause is a random rival, which the plan
 * explicitly rules out: an edge may exist only because of a family tie, shared work, a directly
 * observed event, or an authored conversation consequence. The {@link #cause} field is not
 * decoration — it is what a scene binds so the villager can say "Tomas missed the watch" rather than
 * "I don't like Tomas".
 *
 * <p>Edges are never generated as a Cartesian product of residents. There is a hard per-villager cap
 * in the store, and creation is always a consequence of something specific.
 *
 * @param target     the villager this opinion is about
 * @param axis       what dimension it is on: {@code reliability}, {@code warmth}, {@code respect}…
 * @param value      how strongly, from {@link #MIN_VALUE} to {@link #MAX_VALUE}; sign is direction
 * @param cause      the episode, event or authored consequence that produced it
 * @param confidence how firmly the speaker holds it
 * @param privacy    how freely they would repeat it
 * @param createdDay the day it formed
 * @param expiresDay when it fades, if it fades
 */
public record SocialOpinionRecord(UUID target,
                                  String axis,
                                  int value,
                                  String cause,
                                  Confidence confidence,
                                  PrivacyLevel privacy,
                                  long createdDay,
                                  OptionalLong expiresDay) {

    public static final int MIN_VALUE = -3;
    public static final int MAX_VALUE = 3;

    /** The closed axis vocabulary. A datapack extends it through content, not through new axes. */
    public static final Set<String> AXES = Set.of(
            "reliability", "warmth", "respect", "trust", "fairness", "skill");

    private static final String KEY_TARGET = "target";
    private static final String KEY_AXIS = "axis";
    private static final String KEY_VALUE = "value";
    private static final String KEY_CAUSE = "cause";
    private static final String KEY_CONFIDENCE = "confidence";
    private static final String KEY_PRIVACY = "privacy";
    private static final String KEY_CREATED = "created";
    private static final String KEY_EXPIRES = "expires";

    public SocialOpinionRecord {
        axis = normalize(axis);
        cause = normalize(cause);
        value = Math.max(MIN_VALUE, Math.min(MAX_VALUE, value));
        confidence = confidence == null ? Confidence.WITNESSED : confidence;
        privacy = privacy == null ? PrivacyLevel.DISCREET : privacy;
        expiresDay = expiresDay == null ? OptionalLong.empty() : expiresDay;
    }

    /** The key this opinion is filed under for one speaker: one value per target per axis. */
    public String key() {
        return target + "/" + axis;
    }

    /**
     * True when this edge is well-formed enough to speak from.
     *
     * <p>A cause-less or axis-less edge is not merely useless, it is dangerous: it would let a scene
     * assert a dislike it cannot explain. Such a record is dropped on load rather than kept.
     */
    public boolean isWellFormed() {
        return target != null && AXES.contains(axis) && !cause.isEmpty();
    }

    public boolean hasExpired(long today) {
        return expiresDay.isPresent() && today > expiresDay.getAsLong();
    }

    public boolean isPositive() {
        return value > 0;
    }

    /** True when the speaker would repeat this to the player at all. */
    public boolean isShareable(PrivacyLevel ceiling) {
        return privacy.permits(ceiling);
    }

    /** Strengthens or softens an existing opinion, keeping the newer cause. */
    public SocialOpinionRecord adjusted(int delta, String newCause, long day, OptionalLong newExpiry) {
        String effectiveCause = newCause == null || newCause.isBlank() ? cause : newCause;
        return new SocialOpinionRecord(target, axis, value + delta, effectiveCause, confidence,
                privacy, day, newExpiry == null ? expiresDay : newExpiry);
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putUUID(KEY_TARGET, target);
        tag.putString(KEY_AXIS, axis);
        tag.putInt(KEY_VALUE, value);
        tag.putString(KEY_CAUSE, cause);
        tag.putString(KEY_CONFIDENCE, confidence.key());
        tag.putString(KEY_PRIVACY, privacy.key());
        tag.putLong(KEY_CREATED, createdDay);
        expiresDay.ifPresent(day -> tag.putLong(KEY_EXPIRES, day));
        return tag;
    }

    public static Optional<SocialOpinionRecord> load(CompoundTag tag) {
        if (tag == null || !tag.hasUUID(KEY_TARGET)) {
            return Optional.empty();
        }
        SocialOpinionRecord record = new SocialOpinionRecord(tag.getUUID(KEY_TARGET),
                tag.getString(KEY_AXIS), tag.getInt(KEY_VALUE), tag.getString(KEY_CAUSE),
                Confidence.byKey(tag.getString(KEY_CONFIDENCE)).orElse(Confidence.WITNESSED),
                PrivacyLevel.byKey(tag.getString(KEY_PRIVACY)).orElse(PrivacyLevel.DISCREET),
                tag.getLong(KEY_CREATED),
                tag.contains(KEY_EXPIRES) ? OptionalLong.of(tag.getLong(KEY_EXPIRES)) : OptionalLong.empty());
        return record.isWellFormed() ? Optional.of(record) : Optional.empty();
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
