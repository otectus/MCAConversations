package dev.otectus.mcaconversations.compat;

/**
 * How much of Townstead this build managed to bind (Townstead spec §5.3).
 *
 * <p>Reported by {@link TownsteadBridge#status()} and by
 * {@code /conversations compat townstead status}. Only {@link #FULL} is a supported release
 * configuration; {@link #PARTIAL} exists so a Townstead point release that moved one internal method
 * disables one feature rather than the integration.
 */
public enum TownsteadStatus {

    /** Townstead is not installed. The normal, silent path — never a warning. */
    ABSENT,

    /** Townstead is installed but the integration is switched off in config. */
    DISABLED,

    /** Every capability in {@link TownsteadCapability} bound. */
    FULL,

    /** The core facade bound, but at least one capability did not. */
    PARTIAL,

    /** Townstead is installed but even its core facade could not be bound. */
    INCOMPATIBLE
}
