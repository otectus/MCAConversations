package dev.otectus.mcaconversations.compat;

import java.util.Locale;

/**
 * One independently-bindable slice of MCA Capitals.
 *
 * <p>The unit of failure for the integration: a binding miss disables exactly the capability that
 * needed it, leaves the views it feeds at their neutral values, and produces one diagnostic — it
 * never disables the bridge and never throws.
 */
public enum CapitalsCapability {

    /** The capital registry and the record identity fields every other read starts from. */
    CORE,
    /** The court: sovereign, consort, heir, dowager and the five offices, plus mourning. */
    COURT,
    /** Resolved titles, their rank, and the secondary court office line. */
    TITLES,
    /** A villager's standing with the Crown: friend, enemy or neither. */
    STANDING,
    /** Noble houses, their tier, and the identity data carrying house words and surname. */
    HOUSES,
    /** Relations between capitals: diplomatic state, band, score, ambassador. */
    DIPLOMACY,
    /** The capital chronicle: decoding stored entries and rendering them to text. */
    CHRONICLE,
    /** A player's declared allegiance to a capital. */
    ALLEGIANCE,
    /** Display names for villagers who may not be loaded. */
    NAMES;

    /** The lowercase id used in logs and in the status command. */
    public String key() {
        return name().toLowerCase(Locale.ROOT);
    }
}
