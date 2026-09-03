package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversationsConfig;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The transient, server-side registry of live {@link ConversationSession}s, keyed by player UUID
 * (plan §7.1). One session per player, shared by the interaction GUI and chat mode, so a branching
 * tree behaves identically whichever way the player is talking.
 *
 * <p>Never persisted. Cleared on logout, on the target villager's death, when a session times out,
 * and whenever the player switches to a different villager.
 *
 * <p>Deliberately free of any {@code net.conczin.mca.*} import: mixins and dialogue adapters hand it
 * plain UUIDs and strings, keeping MCA behind {@code compat/} and {@code mixin/}.
 */
public final class ConversationSessions {

    private static final Map<UUID, ConversationSession> SESSIONS = new ConcurrentHashMap<>();

    private ConversationSessions() {
    }

    /** The player's session, creating one if needed. */
    public static ConversationSession get(UUID playerId, long now) {
        ConversationSession session = SESSIONS.computeIfAbsent(playerId, id -> new ConversationSession(id, now));
        if (session.isExpired(now, timeoutTicks())) {
            session.endTopic();
        }
        session.touch(now);
        return session;
    }

    /** The player's session if one exists and has not timed out; never creates. */
    public static Optional<ConversationSession> peek(UUID playerId, long now) {
        ConversationSession session = SESSIONS.get(playerId);
        if (session == null) {
            return Optional.empty();
        }
        if (session.isExpired(now, timeoutTicks())) {
            session.endTopic();
        }
        return Optional.of(session);
    }

    /** The player's session without any expiry handling — for read-only diagnostics. */
    public static Optional<ConversationSession> raw(UUID playerId) {
        return Optional.ofNullable(SESSIONS.get(playerId));
    }

    /**
     * Records the question and constraint-filtered answers MCA just offered this player. Called for
     * both frontends from the outgoing-packet mixin, which is the only place that sees the real
     * offered set.
     */
    public static ConversationSession.ChoiceOffer recordOffer(UUID playerId, String question,
                                                               List<String> answers, long now) {
        return recordOffer(playerId, null, question, answers, ConversationSession.Frontend.GUI, now);
    }

    /** Records a frontend-aware offer and returns the revision the client must echo when selecting. */
    public static ConversationSession.ChoiceOffer recordOffer(UUID playerId, UUID villagerId,
                                                               String question, List<String> answers,
                                                               ConversationSession.Frontend frontend,
                                                               long now) {
        ConversationSession session = get(playerId, now);
        session.setFrontend(frontend);
        return session.setOffer(question, answers, frontend, villagerId, now);
    }

    /**
     * Re-arms the offer a terminal villager reply just consumed, under a fresh revision.
     *
     * <p>MCA only sends a new response packet when the chosen answer leads to another question. A
     * say-only answer leaves the screen's buttons in place in vanilla MCA, so mirroring that here is
     * what stops a consume-once offer from dead-ending the conversation. Returns empty whenever the
     * world already moved on — nothing offered, not consumed, a newer revision, a different question,
     * or a topic that ended — so a real follow-up is never overwritten.
     *
     * <p>Deliberately unfiltered: every submission re-checks its answer's constraints, and dropping
     * entries here would desync the numbering chat mode has already printed.
     */
    public static Optional<ConversationSession.ChoiceOffer> reofferConsumed(UUID playerId, String question,
                                                                           long revisionBefore, long now) {
        ConversationSession session = SESSIONS.get(playerId);
        if (session == null) {
            return Optional.empty();
        }
        ConversationSession.ChoiceOffer offer = session.currentOffer().orElse(null);
        if (offer == null || !offer.consumed() || offer.revision() != revisionBefore
                || !offer.questionId().equals(question)) {
            return Optional.empty();
        }
        return Optional.of(session.setOffer(offer.questionId(), offer.answerIds(), offer.frontend(),
                offer.villagerId(), now));
    }

    public static Optional<String> consumeOffer(UUID playerId, long revision, int index, long now) {
        return peek(playerId, now).flatMap(session -> session.consumeOffer(revision, index));
    }

    /** Starts a topic for this player and villager. */
    public static ConversationSession beginTopic(UUID playerId, UUID villagerId, String topicId,
                                                 DepthClass budget, long now) {
        ConversationSession session = get(playerId, now);
        session.setVillagerId(villagerId);
        session.beginTopic(topicId, budget, now);
        return session;
    }

    public static void endTopic(UUID playerId, long now) {
        peek(playerId, now).ifPresent(ConversationSession::endTopic);
    }

    /** Drops a player's session entirely (logout, death). */
    public static void clear(UUID playerId) {
        SESSIONS.remove(playerId);
    }

    /** Ends any session pointed at a villager that just died or was removed. */
    public static void clearVillager(UUID villagerId) {
        for (ConversationSession session : SESSIONS.values()) {
            if (villagerId.equals(session.villagerId())) {
                session.endTopic();
                session.setVillagerId(null);
            }
        }
    }

    /**
     * Drops sessions idle for well past the timeout. Called on the low-frequency maintenance cadence,
     * not per tick — an expired-but-not-yet-swept session is already inert because
     * {@link #get} and {@link #peek} end its topic on contact.
     */
    public static int sweep(long now) {
        long hardTimeout = (long) timeoutTicks() * 4L;
        int removed = 0;
        for (Map.Entry<UUID, ConversationSession> entry : SESSIONS.entrySet()) {
            if (now - entry.getValue().lastActivityGameTime() > hardTimeout) {
                SESSIONS.remove(entry.getKey());
                removed++;
            }
        }
        return removed;
    }

    public static int size() {
        return SESSIONS.size();
    }

    /** Test seam: forget every session. */
    public static void clearAllForTesting() {
        SESSIONS.clear();
    }

    private static int timeoutTicks() {
        try {
            return McaConversationsConfig.conversationSessionTimeoutTicks();
        } catch (Throwable t) {
            // Config not loaded (unit tests, early startup): fall back to the documented default.
            return 1200;
        }
    }
}
