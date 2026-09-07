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

    private record Scheduled(long tick, long seq, Runnable task) {
    }

    private static final PriorityQueue<Scheduled> QUEUE = new PriorityQueue<>(
            (a, b) -> a.tick != b.tick ? Long.compare(a.tick, b.tick) : Long.compare(a.seq, b.seq));

    private static long sequence = 0L;
    private static final java.util.Map<java.util.UUID, Long> LAST_DELIVERY = new java.util.HashMap<>();

    private ChatModeScheduler() {
    }

    /** Enqueues {@code task} to run when overworld game-time reaches {@code deliverAtTick}. */
    public static void schedule(long deliverAtTick, Runnable task) {
        QUEUE.add(new Scheduled(deliverAtTick, sequence++, task));
    }

    /** Keeps a player's spoken turns in order even when a later, shorter line has less typing delay. */
    public static void scheduleOrdered(java.util.UUID playerId, long deliverAtTick, Runnable task) {
        Long previous = LAST_DELIVERY.get(playerId);
        long deadline = previous == null ? deliverAtTick : Math.max(deliverAtTick, previous + 1L);
        LAST_DELIVERY.put(playerId, deadline);
        schedule(deadline, () -> {
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

    /** Clears the queue (server stop / test isolation). */
    public static void reset() {
        QUEUE.clear();
        LAST_DELIVERY.clear();
        sequence = 0L;
    }
}
