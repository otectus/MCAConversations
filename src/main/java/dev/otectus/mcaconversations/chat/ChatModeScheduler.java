package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversations;

import java.util.PriorityQueue;

/**
 * A tiny deadline queue for deferred villager replies. The dialogue engine ({@code selectAnswer}, all
 * state writes) runs immediately at match time; only the visible chat <em>delivery</em> is deferred by
 * a humanized tick count so replies don't feel robotic and multiple responders stagger (spec §8.4).
 *
 * <p>There is no existing scheduler in the mod — {@code onServerTick} is a modulo-cadence dispatcher —
 * so this is drained explicitly every tick from that handler via {@link #drain(long)}. Server thread
 * only; no synchronization needed.
 */
public final class ChatModeScheduler {

    /** {@code player} is the recipient the entry belongs to, or null for a task owned by nobody. */
    private record Scheduled(long tick, long seq, java.util.UUID player, Runnable task) {
    }

    private static final PriorityQueue<Scheduled> QUEUE = new PriorityQueue<>(
            (a, b) -> a.tick != b.tick ? Long.compare(a.tick, b.tick) : Long.compare(a.seq, b.seq));

    private static long sequence = 0L;
    private static final java.util.Map<java.util.UUID, Long> LAST_DELIVERY = new java.util.HashMap<>();

    private ChatModeScheduler() {
    }

    /** Enqueues {@code task} to run when overworld game-time reaches {@code deliverAtTick}. */
    public static void schedule(long deliverAtTick, Runnable task) {
        schedule(null, deliverAtTick, task);
    }

    /**
     * As {@link #schedule(long, Runnable)}, but tagged with the player the task speaks to so
     * {@link #clearPlayer} can drop it when their conversation ends.
     */
    public static void schedule(java.util.UUID player, long deliverAtTick, Runnable task) {
        QUEUE.add(new Scheduled(deliverAtTick, sequence++, player, task));
    }

    /** Keeps a player's spoken turns in order even when a later, shorter line has less typing delay. */
    public static void scheduleOrdered(java.util.UUID playerId, long deliverAtTick, Runnable task) {
        Long previous = LAST_DELIVERY.get(playerId);
        long deadline = previous == null ? deliverAtTick : Math.max(deliverAtTick, previous + 1L);
        LAST_DELIVERY.put(playerId, deadline);
        schedule(playerId, deadline, () -> {
            try {
                task.run();
            } finally {
                LAST_DELIVERY.remove(playerId, deadline);
            }
        });
    }

    /** Runs every task whose deadline is at or before {@code now}, in deadline order. */
    public static void drain(long now) {
        while (!QUEUE.isEmpty() && QUEUE.peek().tick() <= now) {
            Scheduled next = QUEUE.poll();
            try {
                next.task().run();
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("chat-mode scheduled delivery failed; dropping", t);
            }
        }
    }

    /**
     * Humanized delay (ticks) for a line: a base delay plus one tick per four characters, capped at 60
     * (3 s) so long lines don't lag absurdly.
     */
    public static int computeDelayTicks(int baseDelay, int lineLength) {
        long delay = (long) Math.max(0, baseDelay) + Math.max(0, lineLength) / 4L;
        return (int) Math.min(60L, delay);
    }

    /**
     * Drops every queued delivery aimed at {@code player} — their conversation ended, so a line
     * still waiting on the humanized delay must never arrive after the goodbye. Everyone else keeps
     * their deadlines and their order: the queue is rebuilt from the survivors, whose {@code tick}
     * and {@code seq} are untouched.
     */
    public static void clearPlayer(java.util.UUID player) {
        if (player == null) {
            return;
        }
        java.util.List<Scheduled> survivors = new java.util.ArrayList<>(QUEUE.size());
        for (Scheduled entry : QUEUE) {
            if (!player.equals(entry.player())) {
                survivors.add(entry);
            }
        }
        if (survivors.size() != QUEUE.size()) {
            QUEUE.clear();
            QUEUE.addAll(survivors);
        }
        LAST_DELIVERY.remove(player);
    }

    /** How many deliveries are still queued for {@code player} (diagnostics and tests). */
    public static int pendingFor(java.util.UUID player) {
        int count = 0;
        for (Scheduled entry : QUEUE) {
            if (player != null && player.equals(entry.player())) {
                count++;
            }
        }
        return count;
    }

    /** Clears the queue (server stop / test isolation). */
    public static void reset() {
        QUEUE.clear();
        LAST_DELIVERY.clear();
        sequence = 0L;
    }
}
