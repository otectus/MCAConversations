package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.chat.ChatModeSession;
import dev.otectus.mcaconversations.chat.ConversationMovementController;
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
        if (DangerLockout.locked(villagerId, now)) {
            // Recently attacked: for attackReopenDelayTicks this villager talks to nobody. Without
            // it the player who just swung is one right-click away from pinning the villager who is
            // trying to get away from them (spec §5.3).
            McaConversations.LOGGER.debug("refused a discussion with villager {}: {} ticks of danger "
                    + "lockout left", villagerId, DangerLockout.remaining(villagerId, now));
            return Optional.empty();
        }
        ConversationHandle existing = ConversationPresence.ofPlayer(playerId).orElse(null);
        if (existing != null && existing.isFor(playerId, villagerId) && existing.frontend() == frontend) {
            // Not an open: the exchange is already this player's, so nothing is minted, nothing is
            // taken over, and nothing is charged against the pair's allowance.
            ConversationSessions.attach(existing, now);
            return Optional.of(existing);
        }
        if (!OpenRateLimiter.accept(playerId, villagerId, now)) {
            // Faster than anybody means it. Refuse before anything is torn down: a rate-limited open
            // must leave the discussion the player is already in exactly as it was (spec §6).
            McaConversations.LOGGER.debug("refused an open of villager {} for {}: {} opens already "
                    + "accepted for this pair inside the window", villagerId, playerId, OpenRateLimiter.BURST);
            return Optional.empty();
        }
        if (existing != null) {
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
     * <p>One serialized operation on the server thread, in the order the spec fixes, because every
     * step of it is a place where the villager could be left belonging to nobody:
     *
     * <ol>
     *   <li><b>The hold moves first.</b> The attention hold is re-booked under the successor's handle
     *       before anything releases the predecessor's, so there is no tick between the two owners in
     *       which the villager is unheld and ordinary navigation resumes. The predecessor's teardown
     *       then finds a hold it does not own and leaves it alone.</li>
     *   <li><b>The successor claims the villager before the predecessor is retired</b>, for the same
     *       reason: the villager index names an owner throughout, and the conditional removal in
     *       {@link ConversationPresence#release} means the retirement cannot unseat the claim.</li>
     *   <li><b>Then the predecessor ends</b>, as {@link CloseReason#TAKEN_OVER}: their offers and
     *       queued lines go, their screen is closed by the terminal packet with a sentence saying
     *       why, and MCA's own interaction is closed <em>only if MCA still says it is theirs</em>.
     *       By the time this runs MCA has normally already made the new player the interacting one —
     *       {@code EntityCommandHandler.interactAt} assigns it before the dialogue packet this path
     *       is driven from — and {@code stopInteracting()} closes whoever is interacting <em>now</em>,
     *       so an unconditional native close here would shut the successor's window instead.</li>
     * </ol>
     *
     * <p>The successor's own interaction is MCA's to have assigned; this method only makes it this
     * mod's discussion. Facing the new player, and the fresh offer they get, follow from the handle
     * this returns.
     */
    public static ConversationHandle handoff(ConversationHandle from, UUID toPlayerId, String dimension,
                                             ConversationSession.Frontend frontend, long now) {
        if (from == null) {
            throw new IllegalArgumentException("handoff needs the handle being taken over");
        }
        if (toPlayerId == null) {
            throw new IllegalArgumentException("handoff needs the player taking over");
        }
        if (!ConversationPresence.isCurrent(from)) {
            // Already retired: whoever holds the villager now does so legitimately, and the caller's
            // view of the world is one step behind. Never terminate on a stale view.
            McaConversations.LOGGER.debug("handoff from {} ignored: the handle is no longer current", from);
            return mintAndClaim(toPlayerId, from.villagerId(), dimension, frontend, now);
        }
        ConversationHandle to = ConversationHandle.mint(toPlayerId, from.villagerId(), dimension, frontend);
        boolean held = ConversationMovementController.transfer(from, to, now);
        ConversationPresence.claim(to, now);
        terminate(from, CloseReason.TAKEN_OVER);
        ConversationSessions.attach(to, now);
        McaConversations.LOGGER.info("villager {} taken over by {}: {} ends, {} begins (hold carried: {})",
                from.villagerId(), toPlayerId, from.sessionId(), to.sessionId(), held);
        return to;
    }

    private static ConversationHandle mintAndClaim(UUID playerId, UUID villagerId, String dimension,
                                                   ConversationSession.Frontend frontend, long now) {
        ConversationHandle handle = ConversationHandle.mint(playerId, villagerId, dimension, frontend);
        ConversationPresence.claim(handle, now);
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
        DangerLockout.clear();
        OpenRateLimiter.clear();
        ConversationHandle.beginServerEpoch();
    }
}
