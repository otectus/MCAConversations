package dev.otectus.mcaconversations.history;

import java.util.EnumSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * Where a concrete situation stands (spec §8.3).
 *
 * <p>The state machine below is the single most load-bearing thing in the living-history layer,
 * because it is what makes a callback honest. "Is it still blocked?" is answerable only if there is a
 * state, and "the west frame finally held" may only be said from {@link #SUCCEEDED}. Failure mode 3 —
 * a project described as still blocked after a success callback resolved it — is exactly a missing or
 * unenforced transition.
 *
 * <pre>
 *   PLANNED ──→ ACTIVE ⇄ BLOCKED
 *      │           │        │
 *      │           ├────────┴──→ SUCCEEDED ─┐
 *      │           └────────────→ FAILED ───┤
 *      └────────────────────────→ ABANDONED ┴─→ REMEMBERED
 * </pre>
 *
 * <p>{@link #allows} is checked at runtime and an undeclared transition leaves the old state intact,
 * so a datapack that asks for a regression gets a no-op rather than a rewritten history.
 */
public enum EpisodeState {

    /** Intended but not begun. The villager can talk about meaning to. */
    PLANNED("planned"),

    /** Under way. The default state for a live piece of working or personal life. */
    ACTIVE("active"),

    /** Under way and stuck on something. The state most worth a player's help. */
    BLOCKED("blocked"),

    /** Finished well. Terminal for the world; still callable back as a shared memory. */
    SUCCEEDED("succeeded"),

    /** Finished badly. Equally terminal, and equally worth remembering (spec §20.9). */
    FAILED("failed"),

    /** Given up on. Not a failure — a choice, or a thing that stopped mattering. */
    ABANDONED("abandoned"),

    /** Compressed to a memory. No longer live; may still be recalled with the right frame. */
    REMEMBERED("remembered");

    private final String key;

    EpisodeState(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /** True while the situation is live and may still change. */
    public boolean isLive() {
        return this == PLANNED || this == ACTIVE || this == BLOCKED;
    }

    /** True when the situation has come to an end, however it ended. */
    public boolean isTerminal() {
        return this == SUCCEEDED || this == FAILED || this == ABANDONED;
    }

    /** True when only past-tense frames may speak about it. */
    public boolean isPast() {
        return isTerminal() || this == REMEMBERED;
    }

    /** The transitions this state may legally make. */
    public Set<EpisodeState> successors() {
        return switch (this) {
            case PLANNED -> EnumSet.of(ACTIVE, ABANDONED);
            case ACTIVE -> EnumSet.of(BLOCKED, SUCCEEDED, FAILED, ABANDONED);
            case BLOCKED -> EnumSet.of(ACTIVE, SUCCEEDED, FAILED, ABANDONED);
            case SUCCEEDED, FAILED, ABANDONED -> EnumSet.of(REMEMBERED);
            case REMEMBERED -> EnumSet.noneOf(EpisodeState.class);
        };
    }

    /**
     * True when {@code next} may follow this state.
     *
     * <p>A transition to the same state is allowed and is a no-op, so a replayed packet or a second
     * click on the same button cannot duplicate an effect (spec §21.1).
     */
    public boolean allows(EpisodeState next) {
        return next == this || successors().contains(next);
    }

    public static Optional<EpisodeState> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (EpisodeState state : values()) {
            if (state.key.equals(normalized)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }
}
