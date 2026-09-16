package dev.otectus.mcaconversations.conversation;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * How often one player may have a discussion with one villager <em>accepted</em> (spec §6).
 *
 * <p>Since a villager can change hands, the cost of an accepted open is no longer local: it retires
 * whatever discussion held that villager, closes somebody's window and moves the movement hold. A
 * client that re-interacts every tick — a stuck right-click, a macro, two players racing for the
 * same villager — would otherwise turn that into a villager flickering between owners several times
 * a second, with a stream of closing windows behind it.
 *
 * <p>So an open is charged, and the charge is deliberately generous: {@link #BURST} accepted opens
 * for a pair inside {@link #WINDOW_TICKS} ticks, then nothing more until the window rolls over. A
 * deliberate second interaction — closing a window and walking back up to the same villager, another
 * player stepping in — is one open and is never refused; only repetition faster than a person can
 * mean it is. Reopening the exchange already in progress is not an open at all and is never charged,
 * because it mints nothing.
 *
 * <p>Refusal is a refusal and nothing else: the caller simply gets no handle, exactly as it does
 * during a danger lockout, and whatever discussion was already live is left untouched.
 *
 * <p>Pure bookkeeping: UUIDs and tick numbers, no Minecraft types. Times are server game ticks, so a
 * paused world holds a window rather than expiring it. Server thread only.
 */
public final class OpenRateLimiter {

    /** How many opens one pair may have accepted inside one window. */
    public static final int BURST = 3;

    /** How long that window is, in ticks. One second: shorter than deliberation, longer than spam. */
    public static final int WINDOW_TICKS = 20;

    /**
     * How many pairs the map may hold before a write drops the windows that have already rolled over.
     * Sweeping on a write keeps the cost proportional to how much talking is going on rather than to
     * how long the world has been running.
     */
    private static final int SWEEP_THRESHOLD = 256;

    /** One player talking to one villager. Both halves matter: the limit is per pair, not per player. */
    private record Pair(UUID playerId, UUID villagerId) {
    }

    /** When this pair's current window opened, and how many opens it has already accepted. */
    private record Window(long startedAt, int accepted) {
    }

    private static final Map<Pair, Window> WINDOWS = new ConcurrentHashMap<>();

    private OpenRateLimiter() {
    }

    /**
     * Charges one accepted open for this pair.
     *
     * <p>Call this only where a discussion is actually about to be minted or taken over; every call
     * that returns true has spent part of the pair's allowance.
     *
     * @return true when the open may proceed, false when this pair has already had {@link #BURST}
     *         accepted inside the current window
     */
    public static boolean accept(UUID playerId, UUID villagerId, long now) {
        if (playerId == null || villagerId == null) {
            return false;
        }
        Pair pair = new Pair(playerId, villagerId);
        Window window = WINDOWS.get(pair);
        // A window in the future belongs to a world that has since restarted; treat it as expired
        // rather than letting a stale timestamp refuse every open until the tick count catches up.
        if (window == null || now < window.startedAt() || now - window.startedAt() >= WINDOW_TICKS) {
            WINDOWS.put(pair, new Window(now, 1));
            sweep(now);
            return true;
        }
        if (window.accepted() >= BURST) {
            return false;
        }
        WINDOWS.put(pair, new Window(window.startedAt(), window.accepted() + 1));
        return true;
    }

    /** How many more opens this pair may have accepted before the current window has to roll over. */
    public static int remaining(UUID playerId, UUID villagerId, long now) {
        if (playerId == null || villagerId == null) {
            return 0;
        }
        Window window = WINDOWS.get(new Pair(playerId, villagerId));
        if (window == null || now < window.startedAt() || now - window.startedAt() >= WINDOW_TICKS) {
            return BURST;
        }
        return Math.max(0, BURST - window.accepted());
    }

    private static void sweep(long now) {
        if (WINDOWS.size() > SWEEP_THRESHOLD) {
            WINDOWS.values().removeIf(w -> now - w.startedAt() >= WINDOW_TICKS);
        }
    }

    /** Forget every window (server stop, test reset). */
    public static void clear() {
        WINDOWS.clear();
    }
}
