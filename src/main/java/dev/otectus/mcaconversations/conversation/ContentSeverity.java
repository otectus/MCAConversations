package dev.otectus.mcaconversations.conversation;

/**
 * The vocabulary {@code docs/RELOAD-TRANSACTION-BOUNDARY.md} §6 fixes for a reload diagnostic, kept
 * exactly as specified so a log line means one thing.
 *
 * <p>{@link #SKIPPED} describes a staging entry that was left out of the attempt. It never implies
 * that the rest of the section was published: under the coordinator a skipped entry still belongs to
 * an attempt that is accepted or refused as a whole.
 */
public enum ContentSeverity {

    /** A normal pack override, a shadowed resource, or another fact worth attributing. Not a failure. */
    INFO,

    /** One entry was omitted from the staged section. */
    SKIPPED,

    /** The previous content stayed in force for this section or for the whole bundle. */
    RETAINED,

    /** The section — and therefore the attempt — cannot be built at all. */
    REFUSED;

    /** True when a problem of this severity forbids publication. */
    public boolean fatal() {
        return this == REFUSED;
    }
}
