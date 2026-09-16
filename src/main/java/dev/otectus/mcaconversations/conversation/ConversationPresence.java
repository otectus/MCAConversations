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
    /**
     * Last heartbeat per discussion, keyed by session UUID rather than by player: keying it by
     * player would let a heartbeat for a discussion that has ended renew the one that replaced it,
     * which is the exact confusion the handles exist to prevent. Entries are removed with their
     * handle, so the map cannot outgrow the presence indexes.
     */
    private static final Map<UUID, Long> HEARTBEATS = new ConcurrentHashMap<>();
    /**
     * The tick each discussion first went past the continue distance, keyed the same way. Absent
     * means the pair is together; the lifecycle tick writes what
     * {@link ConversationDistancePolicy#judge} hands back and owns nothing else about the grace band.
     */
    private static final Map<UUID, Long> OUTSIDE_SINCE = new ConcurrentHashMap<>();

    private ConversationPresence() {
    }

    /**
     * Registers {@code handle} as the current owner on both sides, and starts its presence lease.
     *
     * <p>The lease is seeded with the acceptance tick rather than left empty, so a discussion whose
     * client never attaches a window is expired by the same rule as one whose client stopped
     * reporting. Without the seed there would be no timestamp to expire and a screen that never
     * opened would hold its villager until something else noticed.
     */
    public static void claim(ConversationHandle handle, long now) {
        if (handle == null) {
            return;
        }
        BY_PLAYER.put(handle.playerId(), handle);
        BY_VILLAGER.put(handle.villagerId(), handle);
        HEARTBEATS.put(handle.sessionId(), now);
    }

    /** Every discussion currently registered under a player. A snapshot, safe to iterate and close. */
    public static java.util.List<ConversationHandle> handles() {
        return java.util.List.copyOf(BY_PLAYER.values());
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
        HEARTBEATS.remove(handle.sessionId());
        OUTSIDE_SINCE.remove(handle.sessionId());
    }

    /**
     * Records that the client is still present in the discussion it names (spec §4.3, §4.5).
     *
     * <p>Refuses anything that is not the sender's live discussion. A heartbeat is the cheapest
     * packet to forge and the easiest to deliver late, so it is given no power at all beyond
     * refreshing a timestamp: it cannot create a discussion, revive a retired one, or move a
     * villager between owners.
     *
     * @return true when the heartbeat was recorded
     */
    public static boolean heartbeat(UUID senderId, UUID sessionId, UUID villagerId, long gameTime) {
        ConversationHandle current = ofPlayer(senderId).orElse(null);
        if (!HandleAuthority.judge(current, sessionId, villagerId).equals(HandleAuthority.Decision.ACCEPT)) {
            return false;
        }
        HEARTBEATS.put(current.sessionId(), gameTime);
        return true;
    }

    /** When this discussion last said it was still there, or empty when it never has. */
    public static java.util.OptionalLong lastHeartbeat(ConversationHandle handle) {
        Long at = handle == null ? null : HEARTBEATS.get(handle.sessionId());
        return at == null ? java.util.OptionalLong.empty() : java.util.OptionalLong.of(at);
    }

    /**
     * Whether this discussion's client has gone quiet for longer than the lease (spec §4.3).
     *
     * <p>The lease is what a graphical conversation has instead of a reading timeout. Nothing about
     * how long the player has been looking at the card counts against them; what counts is whether
     * their window is still there to say so. A discussion with no timestamp at all has already been
     * retired and is nobody's to expire, and a lease of zero switches the rule off entirely.
     */
    public static boolean leaseExpired(ConversationHandle handle, long now, int leaseTicks) {
        if (handle == null || leaseTicks <= 0) {
            return false;
        }
        Long last = HEARTBEATS.get(handle.sessionId());
        return last != null && now - last > leaseTicks;
    }

    /** The tick this discussion first went out of range, or {@link ConversationDistancePolicy#NOT_OUTSIDE}. */
    public static long outsideSince(ConversationHandle handle) {
        Long since = handle == null ? null : OUTSIDE_SINCE.get(handle.sessionId());
        return since == null ? ConversationDistancePolicy.NOT_OUTSIDE : since;
    }

    /** Remembers what the distance policy decided; {@code NOT_OUTSIDE} forgets an earlier lapse. */
    public static void noteOutside(ConversationHandle handle, long since) {
        if (handle == null) {
            return;
        }
        if (since == ConversationDistancePolicy.NOT_OUTSIDE) {
            OUTSIDE_SINCE.remove(handle.sessionId());
        } else {
            OUTSIDE_SINCE.put(handle.sessionId(), since);
        }
    }

    /** How many players are in a registered discussion. Diagnostics and tests. */
    public static int size() {
        return BY_PLAYER.size();
    }

    /** Forget every registration (server stop, test reset). */
    public static void clear() {
        BY_PLAYER.clear();
        BY_VILLAGER.clear();
        HEARTBEATS.clear();
        OUTSIDE_SINCE.clear();
    }
}
