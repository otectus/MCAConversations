package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;

import java.util.Locale;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

/**
 * One observed role one villager holds towards another, and what put it there (spec §16.2).
 *
 * <p>The rule this record exists to enforce is the plan's flat refusal of the "random rival": a role
 * with no cause could only ever produce a villager who dislikes somebody for no reason they can give,
 * which is drama rather than a life. {@link #cause} names the episode, shared event or authored
 * consequence behind the role, and a record without one is dropped rather than kept.
 *
 * <p>Roles are directional. That a smith thinks of the farmer as a supplier does not mean the farmer
 * thinks of the smith as a customer, and the mod does not assume it: the mirror edge exists only when
 * something actually observed it from that side too.
 *
 * @param target     the villager this role is about
 * @param role       what they are to the holder
 * @param cause      the episode, event or authored consequence that produced it; never empty
 * @param confidence how firmly the holder believes the role applies
 * @param createdDay the day it was first observed
 * @param expiresDay when it lapses, absent for a role that lasts until it is withdrawn
 */
public record SocialRoleRecord(UUID target,
                               SocialRole role,
                               String cause,
                               Confidence confidence,
                               long createdDay,
                               OptionalLong expiresDay) {

    private static final String KEY_TARGET = "target";
    private static final String KEY_ROLE = "role";
    private static final String KEY_CAUSE = "cause";
    private static final String KEY_CONFIDENCE = "conf";
    private static final String KEY_CREATED = "created";
    private static final String KEY_EXPIRES = "expires";

    public SocialRoleRecord {
        cause = cause == null ? "" : cause.trim().toLowerCase(Locale.ROOT);
        confidence = confidence == null ? Confidence.WITNESSED : confidence;
        expiresDay = expiresDay == null ? OptionalLong.empty() : expiresDay;
    }

    /**
     * A role observed today, expiring after its own default lifetime.
     *
     * <p>Using the role's policy rather than a caller-chosen number is what keeps §16.2's
     * "expiry/persistence policy" a property of the kind of relationship rather than of whichever
     * code path happened to notice it.
     */
    public static SocialRoleRecord observed(UUID target, SocialRole role, String cause, long today) {
        if (role == null) {
            return new SocialRoleRecord(target, null, cause, Confidence.WITNESSED, today,
                    OptionalLong.empty());
        }
        OptionalLong expiry = role.persistsUntilWithdrawn()
                ? OptionalLong.empty()
                : OptionalLong.of(today + role.defaultLifetimeDays());
        return new SocialRoleRecord(target, role, cause, Confidence.WITNESSED, today, expiry);
    }

    /** One role per target, so a fresh observation replaces the old one rather than stacking. */
    public String key() {
        return target + "/" + (role == null ? "" : role.key());
    }

    /**
     * True when this edge is well-formed enough to speak from.
     *
     * <p>A role with no target, no kind or no cause is not merely useless — it would let a scene
     * assert a relationship it cannot explain, so it is refused at the door.
     */
    public boolean isWellFormed() {
        return target != null && role != null && !cause.isEmpty();
    }

    public boolean hasExpired(long today) {
        return expiresDay.isPresent() && today > expiresDay.getAsLong();
    }

    /** How long this has been true, which is what "for years now" is allowed to rest on. */
    public long daysHeld(long today) {
        return Math.max(0L, today - createdDay);
    }

    /** The same role, seen again today: the clock restarts and the cause is refreshed. */
    public SocialRoleRecord renewed(String freshCause, long today) {
        String updated = freshCause == null || freshCause.isBlank() ? cause : freshCause;
        OptionalLong expiry = role != null && !role.persistsUntilWithdrawn()
                ? OptionalLong.of(today + role.defaultLifetimeDays())
                : expiresDay;
        return new SocialRoleRecord(target, role, updated, confidence, createdDay, expiry);
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putUUID(KEY_TARGET, target);
        tag.putString(KEY_ROLE, role.key());
        tag.putString(KEY_CAUSE, cause);
        tag.putString(KEY_CONFIDENCE, confidence.key());
        tag.putLong(KEY_CREATED, createdDay);
        expiresDay.ifPresent(day -> tag.putLong(KEY_EXPIRES, day));
        return tag;
    }

    /** Reads a role back; a row the runtime could not speak from reads as empty. */
    public static Optional<SocialRoleRecord> load(CompoundTag tag) {
        if (tag == null || !tag.hasUUID(KEY_TARGET)) {
            return Optional.empty();
        }
        Optional<SocialRole> role = SocialRole.byKey(tag.getString(KEY_ROLE));
        if (role.isEmpty()) {
            return Optional.empty();
        }
        SocialRoleRecord record = new SocialRoleRecord(
                tag.getUUID(KEY_TARGET), role.get(), tag.getString(KEY_CAUSE),
                Confidence.byKey(tag.getString(KEY_CONFIDENCE)).orElse(Confidence.WITNESSED),
                tag.getLong(KEY_CREATED),
                tag.contains(KEY_EXPIRES) ? OptionalLong.of(tag.getLong(KEY_EXPIRES))
                        : OptionalLong.empty());
        return record.isWellFormed() ? Optional.of(record) : Optional.empty();
    }
}
