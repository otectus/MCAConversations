package dev.otectus.mcaconversations.compat;

/**
 * How much of MCA Capitals this build managed to bind.
 *
 * <p>Reported by {@link CapitalsBridge#status()}. Only {@link #FULL} is a supported release
 * configuration; {@link #PARTIAL} exists so a Capitals point release that moved one method disables
 * one capability rather than the whole integration.
 */
public enum CapitalsStatus {

    /** Capitals is not installed, or the integration is switched off. The normal, silent path. */
    ABSENT,

    /** Every capability in {@link CapitalsCapability} bound. */
    FULL,

    /** The core capability bound, but at least one other did not. */
    PARTIAL,

    /** Capitals is installed but even its core record accessors could not be bound. */
    INCOMPATIBLE
}
