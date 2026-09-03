package dev.otectus.mcaconversations.context;

/**
 * Why a context field has the value it has (spec §7.1, §10.7).
 *
 * <p>The distinction this enum exists to preserve is the difference between "she is not pregnant" and
 * "nothing here can tell me whether she is pregnant". Collapsing the second into the first is how a
 * conversation system starts asserting world facts it never observed, which is failure mode 6 in the
 * plan. So an absent answer is never {@code false} — it is {@link #UNKNOWN} when the provider ran and
 * could not tell, and {@link #UNAVAILABLE} when no provider was able to run at all.
 */
public enum ContextStatus {

    /** A provider answered and the answer is trustworthy. */
    KNOWN,

    /** A provider ran but genuinely does not know — an unloaded chunk, an entity with no home. */
    UNKNOWN,

    /** No provider could supply this field: the integration is absent, or a compat handle missed. */
    UNAVAILABLE;

    public boolean isKnown() {
        return this == KNOWN;
    }
}
