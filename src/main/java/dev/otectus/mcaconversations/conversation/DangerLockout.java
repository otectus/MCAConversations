package dev.otectus.mcaconversations.conversation;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * How long a villager who has just been attacked is left out of conversations, and what counts as
 * one attack (spec §5.3).
 *
 * <p>Two small jobs, kept together because they are two halves of the same sentence — "one incident,
 * one interruption, and then a few seconds of peace":
 *
 * <ul>
 *   <li><b>Incident deduplication.</b> One blow raises two events on both loaders: the attack (which
 *       also fires for a hit an armour or shield absorbs entirely) and the damage that follows it.
 *       Both must be observed, because a blocked hit is still an attack and a conversation must not
 *       survive it — but between them they are one incident, and the discussion must end once.
 *       Same villager, same tick, one incident.</li>
 *   <li><b>The re-open delay.</b> For {@code attackReopenDelayTicks} after that incident the villager
 *       accepts no new discussion. Without it the player who just swung is one right-click away from
 *       pinning the villager they attacked, which is the exact thing the interruption exists to
 *       prevent.</li>
 * </ul>
 *
 * <p>The lockout is <b>per villager, not per attacker</b>. A villager fleeing a zombie is no more
 * available to a bystander than to the zombie, and keying it by pair would let a second player pin a
 * panicking villager the moment the first was refused.
 *
 * <p>Pure bookkeeping: UUIDs and tick numbers, no Minecraft types, no entity lookups. Times are
 * server game ticks, so a paused single-player world holds the lockout rather than expiring it.
 */
public final class DangerLockout {

    /**
     * How many entries either map may hold before a write sweeps the expired ones out.
     *
     * <p>Both maps gain an entry per villager anybody hits, and a villager hit once in a fight nobody
     * ever talks to would otherwise keep that entry for the life of the world. Sweeping on a write
     * rather than on a tick keeps the cost proportional to combat rather than to time, and the
     * threshold is high enough that ordinary play never pays it.
     */
    private static final int SWEEP_THRESHOLD = 256;

    private static final Map<UUID, Long> LOCKED_UNTIL = new ConcurrentHashMap<>();
    private static final Map<UUID, Long> LAST_INCIDENT = new ConcurrentHashMap<>();

    private DangerLockout() {
    }

    /**
     * Claims this tick's incident for {@code villagerId}.
     *
     * @return true the first time an incident is reported for this villager on this tick, and false
     *         for every further report of the same one — the attack event and the damage event that
     *         follows it are one blow
     */
    public static boolean noteIncident(UUID villagerId, long gameTime) {
        if (villagerId == null) {
            return false;
        }
        Long previous = LAST_INCIDENT.put(villagerId, gameTime);
        return previous == null || previous != gameTime;
    }

    /** Refuses this villager any new discussion for {@code ticks} from now. Zero disables it. */
    public static void lock(UUID villagerId, long gameTime, int ticks) {
        if (villagerId == null || ticks <= 0) {
            return;
        }
        long until = gameTime + ticks;
        LOCKED_UNTIL.merge(villagerId, until, Math::max);
        sweep(gameTime, ticks);
    }

    /** Drops entries nothing can read again, but only once either map has grown worth sweeping. */
    private static void sweep(long gameTime, int ticks) {
        if (LOCKED_UNTIL.size() > SWEEP_THRESHOLD) {
            LOCKED_UNTIL.values().removeIf(until -> gameTime >= until);
        }
        if (LAST_INCIDENT.size() > SWEEP_THRESHOLD) {
            // An incident older than a lockout can no longer deduplicate anything: its tick is past.
            LAST_INCIDENT.values().removeIf(at -> gameTime - at > ticks);
        }
    }

    /**
     * Whether this villager is still inside a danger lockout.
     *
     * <p>Expired entries are dropped as they are read, so the map holds only villagers who were
     * recently attacked and never grows with the age of the world.
     */
    public static boolean locked(UUID villagerId, long gameTime) {
        Long until = villagerId == null ? null : LOCKED_UNTIL.get(villagerId);
        if (until == null) {
            return false;
        }
        if (gameTime >= until) {
            LOCKED_UNTIL.remove(villagerId, until);
            return false;
        }
        return true;
    }

    /** How many ticks of the lockout are left, or 0 when the villager is free to talk. */
    public static long remaining(UUID villagerId, long gameTime) {
        Long until = villagerId == null ? null : LOCKED_UNTIL.get(villagerId);
        return until == null || gameTime >= until ? 0L : until - gameTime;
    }

    /** Lets this villager talk again immediately (an explicit reset, never an ordinary ending). */
    public static void clear(UUID villagerId) {
        if (villagerId != null) {
            LOCKED_UNTIL.remove(villagerId);
            LAST_INCIDENT.remove(villagerId);
        }
    }

    /** Server stop and test reset. */
    public static void clear() {
        LOCKED_UNTIL.clear();
        LAST_INCIDENT.clear();
    }
}
