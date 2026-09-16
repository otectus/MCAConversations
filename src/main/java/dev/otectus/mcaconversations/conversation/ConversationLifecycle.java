package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.chat.ChatModeSession;
import dev.otectus.mcaconversations.chat.VillagerAttention;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The one place a discussion starts and the one place it ends (spec §4.5).
 *
 * <p>Before this coordinator, every path that wanted a conversation gone cleared what it happened to
 * know about — one cleared the session, another released the villager, a third dropped the queued
 * line — and each of them worked by player id alone. That is why a player walking away from Agnes
 * could hand back a villager somebody else was already talking to, and why an ending that failed
 * halfway left the rest of the state standing. Here a full ending is {@link #terminate}: it checks
 * the whole handle first, runs the stages in a fixed order, and releases the ownership indexes in a
 * {@code finally} so a stage that throws cannot strand a villager mid-conversation forever.
 *
 * <p>Two rules are absolute:
 * <ul>
 *   <li><b>An obsolete handle changes nothing.</b> A close for a discussion that has already been
 *       replaced — including a replacement between the same player and the same villager — is a
 *       logged no-op, never a teardown of the successor.</li>
 *   <li><b>Ending a topic is not ending the discussion.</b> {@link ConversationSessions#endTopic}
 *       still returns the player to the topic list with the villager still attending; only the
 *       methods here end an engagement.</li>
 * </ul>
 *
 * <p><b>Server thread only</b>, like {@link ConversationPresence}. Storage removal is kept separate
 * from the public entry points on purpose: {@link ChatModeSession#clear} and
 * {@link ConversationSessions#close} both funnel here, so the stages below call the internal
 * detach helpers rather than those public methods, and nothing recurses.
 */
public final class ConversationLifecycle {

    /**
     * A teardown step that has to reach outside this package — ending MCA's own interaction, telling
     * the client its screen is dead (spec §4.5 steps 6–8). Those layers must not be imported here,
     * and they arrive in later work, so they plug in instead.
     *
     * <p>Runs as an ordinary guarded stage: a hook that throws is logged once and the remaining
     * stages, including the index release, still run.
     */
    @FunctionalInterface
    public interface TeardownHook {
        void onTerminate(ConversationHandle handle, CloseReason reason);
    }

    private static final List<TeardownHook> HOOKS = new CopyOnWriteArrayList<>();

    private ConversationLifecycle() {
    }

    // --- Beginning --------------------------------------------------------------

    /**
     * Accepts a discussion and returns its identity.
     *
     * <p>Reopening the exchange a player is already having returns the same handle rather than
     * minting a second one: a say-only reply, a return from the topic list and a reopened screen are
     * the same discussion, and re-minting would retire the offer the player is looking at. Switching
     * to a different villager ends the previous discussion as {@link CloseReason#TARGET_CHANGED};
     * walking up to a villager somebody else owns goes through {@link #handoff}.
     *
     * @param dimension registry name of the level the pair are in, for example {@code minecraft:overworld}
     * @return the handle now registered for this player, or empty when the arguments name no pair
     */
    public static Optional<ConversationHandle> begin(UUID playerId, UUID villagerId, String dimension,
                                                     ConversationSession.Frontend frontend, long now) {
        if (playerId == null || villagerId == null) {
            return Optional.empty();
        }
        ConversationHandle existing = ConversationPresence.ofPlayer(playerId).orElse(null);
        if (existing != null) {
            if (existing.isFor(playerId, villagerId) && existing.frontend() == frontend) {
                ConversationSessions.attach(existing, now);
                return Optional.of(existing);
            }
            terminate(existing, CloseReason.TARGET_CHANGED);
        }
        ConversationHandle owner = ConversationPresence.ofVillager(villagerId).orElse(null);
        if (owner != null && !owner.playerId().equals(playerId)) {
            return Optional.of(handoff(owner, playerId, dimension, frontend, now));
        }
        if (owner != null) {
            // The same player under a stale handle — a frontend switch, or a discussion whose
            // player index was already retired. Retire it before the replacement claims the pair.
            terminate(owner, CloseReason.TARGET_CHANGED);
        }
        return Optional.of(mintAndClaim(playerId, villagerId, dimension, frontend, now));
    }

    /**
     * Hands a villager from the player who owns it to one whose interaction the server has just
     * accepted (spec §6). The old discussion ends as {@link CloseReason#TAKEN_OVER}.
     *
     * <p><b>Partial:</b> this is the identity half only. Transferring the movement hold without an
     * unheld navigation tick, retiring MCA's own interaction before the new one is assigned, and
     * telling the first player's screen why it closed all belong to the handoff work that follows;
     * they attach through {@link #addTeardownHook} and the movement controller. What already holds
     * is the guarantee that matters most: the transfer is driven by the exact handle that owns the
     * villager, so a stale close or heartbeat from the first player cannot reach the second's hold.
     */
    public static ConversationHandle handoff(ConversationHandle from, UUID toPlayerId, String dimension,
                                             ConversationSession.Frontend frontend, long now) {
        if (from == null) {
            throw new IllegalArgumentException("handoff needs the handle being taken over");
        }
        if (!ConversationPresence.isCurrent(from)) {
            // Already retired: whoever holds the villager now does so legitimately, and the caller's
            // view of the world is one step behind. Never terminate on a stale view.
            McaConversations.LOGGER.debug("handoff from {} ignored: the handle is no longer current", from);
        } else {
            terminate(from, CloseReason.TAKEN_OVER);
        }
        return mintAndClaim(toPlayerId, from.villagerId(), dimension, frontend, now);
    }

    private static ConversationHandle mintAndClaim(UUID playerId, UUID villagerId, String dimension,
                                                   ConversationSession.Frontend frontend, long now) {
        ConversationHandle handle = ConversationHandle.mint(playerId, villagerId, dimension, frontend);
        ConversationPresence.claim(handle);
        ConversationSessions.attach(handle, now);
        McaConversations.LOGGER.debug("conversation accepted: {}", handle);
        return handle;
    }

    // --- Ending -----------------------------------------------------------------

    /**
     * Ends exactly this discussion, once.
     *
     * <p>Idempotent by construction: a handle that is no longer registered under its player or its
     * villager is obsolete, and an obsolete handle is a debug line and nothing else. Every stage is
     * guarded individually, and the ownership indexes are released in a {@code finally}, because the
     * failure that must never happen is a villager left pinned to a discussion nobody can reach.
     *
     * @return the session that was removed, or empty when the handle was already retired
     */
    public static Optional<ConversationSession> terminate(ConversationHandle handle, CloseReason reason) {
        if (handle == null) {
            return Optional.empty();
        }
        CloseReason why = reason == null ? CloseReason.COMPLETED : reason;
        if (!ConversationPresence.isCurrent(handle)) {
            McaConversations.LOGGER.debug("ignored a {} close for {}: a newer discussion owns this pair",
                    why, handle);
            return Optional.empty();
        }
        ConversationSession[] removed = new ConversationSession[1];
        try {
            // 2. The actionable state first: no offer, topic return or transaction may survive the
            //    close long enough for a queued client click to execute against it.
            stage(handle, why, "session", () -> removed[0] = ConversationSessions.detach(handle, why));
            // 3. Only this handle's attention. A villager taken over keeps the new owner's hold.
            stage(handle, why, "attention", () -> VillagerAttention.releaseIfOwned(handle.villagerId(), handle));
            // 4. Nothing THIS discussion scheduled may surface after it ended — and nothing the
            //    successor scheduled may be swept up with it, which a player-wide clear would do.
            stage(handle, why, "delivery", () -> ChatModeScheduler.clearHandle(handle));
            // 5. Chat stickiness and the dynamic hub are this player's view of the discussion.
            stage(handle, why, "chat", () -> ChatModeSession.detach(handle.playerId()));
            stage(handle, why, "hub", () -> dev.otectus.mcaconversations.hub.DynamicHub.clear(handle.playerId()));
            // 6-8. Native close and the terminal packet, when those layers have registered.
            for (TeardownHook hook : HOOKS) {
                stage(handle, why, "hook", () -> hook.onTerminate(handle, why));
            }
        } finally {
            ConversationPresence.release(handle);
        }
        McaConversations.LOGGER.debug("conversation closed as {}: {} (had session: {})",
                why, handle, removed[0] != null);
        return Optional.ofNullable(removed[0]);
    }

    /**
     * Ends the discussion a client named, and only that one (spec §4.4).
     *
     * <p>The entry point for {@code ConversationCloseC2S} and for anything else that knows a
     * discussion by name rather than by object. A close that names a retired handle — the window a
     * player dismissed a moment after reopening the same villager, or the one the server took away
     * from them in a handoff — is a debug line: the successor is untouched.
     *
     * @return the session that was removed, or empty when the named discussion is not the live one
     */
    public static Optional<ConversationSession> terminateIfCurrent(UUID playerId, UUID sessionId,
                                                                   UUID villagerId, CloseReason reason) {
        ConversationHandle current = ConversationPresence.ofPlayer(playerId).orElse(null);
        HandleAuthority.Decision decision = HandleAuthority.judge(current, sessionId, villagerId);
        if (decision != HandleAuthority.Decision.ACCEPT) {
            McaConversations.LOGGER.debug("ignored a {} close naming session {} from {}: {}",
                    reason, sessionId, playerId, decision);
            return Optional.empty();
        }
        return terminate(current, reason);
    }

    /** Ends whatever discussion this player is having, if any. */
    public static Optional<ConversationSession> terminatePlayer(UUID playerId, CloseReason reason) {
        return ConversationPresence.ofPlayer(playerId)
                .flatMap(handle -> terminate(handle, reason));
    }

    /** Ends whatever discussion owns this villager, if any. */
    public static Optional<ConversationSession> terminateVillager(UUID villagerId, CloseReason reason) {
        return ConversationPresence.ofVillager(villagerId)
                .flatMap(handle -> terminate(handle, reason));
    }

    private static void stage(ConversationHandle handle, CloseReason reason, String name, Runnable body) {
        try {
            body.run();
        } catch (Throwable t) {
            // One line per failed stage, then carry on: a broken optional hook must not be able to
            // strand the villager it was supposed to release.
            McaConversations.LOGGER.warn("conversation teardown stage '{}' failed for {} ({})",
                    name, handle, reason, t);
        }
    }

    // --- Registration and lifetime ----------------------------------------------

    /** Adds a step that reaches outside this package; see {@link TeardownHook}. */
    public static void addTeardownHook(TeardownHook hook) {
        if (hook != null) {
            HOOKS.add(hook);
        }
    }

    /** Drops every registered hook (server stop, test reset). */
    public static void clearTeardownHooks() {
        HOOKS.clear();
    }

    /**
     * Starts a fresh server lifetime: every registration is forgotten and the handle epoch advances,
     * so no handle minted by the world that just stopped can ever equal one minted by the next.
     */
    public static void reset() {
        ConversationPresence.clear();
        ConversationHandle.beginServerEpoch();
    }
}
