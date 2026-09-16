package dev.otectus.mcaconversations.conversation;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Who is talking to whom, right now: player → handle and villager → handle (spec §3.2.1). Two plain
 * UUID maps, so asking "does anybody own this villager?" is a lookup and never a scan of the world's
 * entities or of every open session.
 *
 * <p>These two maps are the authority the teardown coordinator checks a handle against. A handle
 * that appears in neither is obsolete by definition, and the conditional removals below are what
 * make cleanup idempotent: {@link #release} can only ever evict the exact handle it was given, so a
 * late close for a retired discussion cannot unseat the discussion that replaced it.
 *
 * <p><b>Server thread only.</b> The maps are concurrent so a stray read cannot corrupt them, but the
 * read-then-write sequences in {@link ConversationLifecycle} are not atomic, and ownership decisions
 * made off the server thread would be decided against a moving target. Every caller — begin,
 * terminate, handoff, the tick sweep — runs on the server thread.
 */
public final class ConversationPresence {

    private static final Map<UUID, ConversationHandle> BY_PLAYER = new ConcurrentHashMap<>();
    private static final Map<UUID, ConversationHandle> BY_VILLAGER = new ConcurrentHashMap<>();

    private ConversationPresence() {
    }

    /** Registers {@code handle} as the current owner on both sides. */
    public static void claim(ConversationHandle handle) {
        if (handle == null) {
            return;
        }
        BY_PLAYER.put(handle.playerId(), handle);
        BY_VILLAGER.put(handle.villagerId(), handle);
    }

    /** The discussion this player is having, if any. */
    public static Optional<ConversationHandle> ofPlayer(UUID playerId) {
        return playerId == null ? Optional.empty() : Optional.ofNullable(BY_PLAYER.get(playerId));
    }

    /** The discussion that owns this villager, if any. */
    public static Optional<ConversationHandle> ofVillager(UUID villagerId) {
        return villagerId == null ? Optional.empty() : Optional.ofNullable(BY_VILLAGER.get(villagerId));
    }

    /** True when this exact handle is still the player's current discussion. */
    public static boolean ownsPlayer(ConversationHandle handle) {
        return handle != null && handle.equals(BY_PLAYER.get(handle.playerId()));
    }

    /** True when this exact handle is still the villager's current owner. */
    public static boolean ownsVillager(ConversationHandle handle) {
        return handle != null && handle.equals(BY_VILLAGER.get(handle.villagerId()));
    }

    /**
     * True while the handle is still registered on either side.
     *
     * <p>Either, not both, on purpose: a villager taken over by another player leaves the first
     * player's handle registered only under the player, and that handle still has state of its own
     * to tear down. What it must not do is touch the villager's new owner, which the conditional
     * removal in {@link #release} guarantees.
     */
    public static boolean isCurrent(ConversationHandle handle) {
        return ownsPlayer(handle) || ownsVillager(handle);
    }

    /**
     * Retires exactly this handle. Entries belonging to a successor are left untouched, so calling
     * this twice — or calling it with a handle that was replaced minutes ago — is a no-op.
     */
    public static void release(ConversationHandle handle) {
        if (handle == null) {
            return;
        }
        BY_PLAYER.remove(handle.playerId(), handle);
        BY_VILLAGER.remove(handle.villagerId(), handle);
    }

    /** How many players are in a registered discussion. Diagnostics and tests. */
    public static int size() {
        return BY_PLAYER.size();
    }

    /** Forget every registration (server stop, test reset). */
    public static void clear() {
        BY_PLAYER.clear();
        BY_VILLAGER.clear();
    }
}
