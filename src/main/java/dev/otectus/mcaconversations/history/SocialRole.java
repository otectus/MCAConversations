package dev.otectus.mcaconversations.history;

import java.util.Locale;
import java.util.Optional;

/**
 * What one villager is to another, beyond kinship (spec §16.2).
 *
 * <p>Kinship is not in here on purpose. MCA's family tree is authoritative for parents, children,
 * siblings, grandparents and partners, and §16.1 forbids keeping a second copy of it — a duplicate
 * graph is a graph that goes wrong. These are the <b>observed</b> roles the mod has to derive itself
 * because nothing else records them: who works beside whom, who taught whom, who is owed something,
 * who is being avoided and why.
 *
 * <p>Every role carries a default lifetime, because §16.2 requires a persistence policy rather than
 * a role that simply accumulates. A structural role lasts as long as the arrangement that produced
 * it and is withdrawn when the arrangement ends; an episodic one fades on its own, so a single
 * argument does not turn into a permanent feud. Both are represented here as a number of days, with
 * {@link #PERSISTS} meaning "until the cause is withdrawn".
 */
public enum SocialRole {

    /** They work the same ground, the same shift, or the same trade in the same village. */
    COWORKER("coworker", 0),

    /** One's work needs the other's output. A stoppage at one end is a problem at the other. */
    SUPPLY_DEPENDENCY("supply_dependency", 0),

    /** They buy from them, regularly enough that it is a relationship rather than a transaction. */
    CUSTOMER("customer", 60),

    /** Something the villager did was for this person's benefit. */
    BENEFICIARY("beneficiary", 45),

    /** This person taught them their trade, or enough of it to be owed for it. */
    MENTOR("mentor", 0),

    /** They taught this person. The other half of a mentorship, never auto-created. */
    APPRENTICE("apprentice", 0),

    /** A neighbour they would leave a key with. Earned by repeated observed conduct. */
    TRUSTED_NEIGHBOUR("trusted_neighbour", 90),

    /** The same argument, more than once. Not a feud, and not permanent. */
    RECURRING_DISAGREEMENT("recurring_disagreement", 30),

    /** Someone they look after: a child, an elder, someone unwell. */
    CARED_FOR("cared_for", 0),

    /** Someone they take the long way round to miss. Always caused; never a mood. */
    AVOIDED("avoided", 60),

    /** They were both there when something happened, and it is still worth mentioning. */
    SHARED_EVENT("shared_event", 21);

    /** A role that lasts until the arrangement behind it is withdrawn. */
    public static final int PERSISTS = 0;

    private final String key;
    private final int defaultLifetimeDays;

    SocialRole(String key, int defaultLifetimeDays) {
        this.key = key;
        this.defaultLifetimeDays = defaultLifetimeDays;
    }

    public String key() {
        return key;
    }

    /** Days this role lasts by default, or {@link #PERSISTS} when it has no natural expiry. */
    public int defaultLifetimeDays() {
        return defaultLifetimeDays;
    }

    public boolean persistsUntilWithdrawn() {
        return defaultLifetimeDays == PERSISTS;
    }

    /** True when this role is a reason to speak warmly about the other person. */
    public boolean isPositive() {
        return this == TRUSTED_NEIGHBOUR || this == MENTOR || this == APPRENTICE
                || this == CARED_FOR || this == BENEFICIARY;
    }

    /** True when the role itself is friction, so a scene must not read it as fondness. */
    public boolean isFriction() {
        return this == RECURRING_DISAGREEMENT || this == AVOIDED;
    }

    /**
     * The role the other person holds, where the pair is asymmetric.
     *
     * <p>Offered as a function and never applied automatically: creating the mirror edge on every
     * write would double the store and assert a relationship from one side's account of it. A caller
     * that has actually observed both sides may use this; nothing else does.
     */
    public Optional<SocialRole> mirror() {
        return switch (this) {
            case MENTOR -> Optional.of(APPRENTICE);
            case APPRENTICE -> Optional.of(MENTOR);
            case COWORKER -> Optional.of(COWORKER);
            case SHARED_EVENT -> Optional.of(SHARED_EVENT);
            case RECURRING_DISAGREEMENT -> Optional.of(RECURRING_DISAGREEMENT);
            default -> Optional.empty();
        };
    }

    public static Optional<SocialRole> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (SocialRole role : values()) {
            if (role.key.equals(normalized)) {
                return Optional.of(role);
            }
        }
        return Optional.empty();
    }
}
