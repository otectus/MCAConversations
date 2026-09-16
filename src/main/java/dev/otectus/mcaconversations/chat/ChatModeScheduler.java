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

    /**
     * {@code player} is the recipient the entry belongs to, or null for a task owned by nobody;
     * {@code handle} is the discussion it was queued in, or null when the recipient was in none;
     * {@code bundle} is the content the task was created under.
     *
     * <p>Stamping the bundle is what keeps a deferred line honest. The reply was already chosen and
     * rendered when the task was queued; running it later against whatever content has been published
     * since would let a humanized delivery delay smuggle a new catalog into a finished exchange.
     *
     * <p>Stamping the handle is what keeps it addressed to the right conversation. A queued line
     * belongs to the exchange that produced it; when that exchange ends the line must go with it, and
     * when the <em>next</em> one ends the line it queued must go with that instead. Clearing by
     * player alone could not tell those apart, so an ending erased whatever the successor had already
     * scheduled (spec §4.5 step 4).
     */
    private record Scheduled(long tick, long seq, java.util.UUID player,
                             dev.otectus.mcaconversations.conversation.ConversationHandle handle,
                             dev.otectus.mcaconversations.conversation.ConversationContentBundle bundle,
                             Runnable task) {
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
     *
     * <p>The discussion is stamped here rather than passed in, so every existing caller becomes
     * handle-aware without changing: the conversation a line belongs to is, by definition, the one
     * its recipient is in at the moment it is queued.
     */
    public static void schedule(java.util.UUID player, long deliverAtTick, Runnable task) {
        QUEUE.add(new Scheduled(deliverAtTick, sequence++, player, currentHandle(player),
                dev.otectus.mcaconversations.conversation.ContentOperation.bundle(), task));
    }

    private static dev.otectus.mcaconversations.conversation.ConversationHandle currentHandle(
            java.util.UUID player) {
        try {
            return dev.otectus.mcaconversations.conversation.ConversationPresence.ofPlayer(player)
                    .orElse(null);
        } catch (Throwable ignored) {
            // Presence is server-thread state; a queue used outside one is untagged, never broken.
            return null;
        }
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
                // Pinned to the bundle the task was queued under, not to whatever is committed now.
                dev.otectus.mcaconversations.conversation.ContentOperation.run(next.bundle(), next.task());
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

    /**
     * Drops what exactly one discussion queued, and nothing a successor queued.
     *
     * <p>Untagged work for the same player goes too: a line queued before handles existed, or
     * outside any discussion, still belongs to the exchange that is ending, and leaving it behind
     * would reintroduce the goodbye-then-one-more-line ending this method exists to prevent. What it
     * refuses to touch is work stamped with a <em>different</em> handle — the successor's.
     */
    public static void clearHandle(dev.otectus.mcaconversations.conversation.ConversationHandle handle) {
        if (handle == null) {
            return;
        }
        java.util.UUID player = handle.playerId();
        java.util.List<Scheduled> survivors = new java.util.ArrayList<>(QUEUE.size());
        for (Scheduled entry : QUEUE) {
            boolean mine = player.equals(entry.player())
                    && (entry.handle() == null || handle.equals(entry.handle()));
            if (!mine) {
                survivors.add(entry);
            }
        }
        if (survivors.size() != QUEUE.size()) {
            QUEUE.clear();
            QUEUE.addAll(survivors);
        }
        // Only when nothing of this player's is left: the ordering watermark is per player, and a
        // successor that already queued a line still needs it.
        if (pendingFor(player) == 0) {
            LAST_DELIVERY.remove(player);
        }
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
