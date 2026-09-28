package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.check.CheckTier;
import dev.otectus.mcaconversations.compat.Townstead;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.Set;

/**
 * One validated reply, from the moment it is accepted to the moment its effects are settled
 * (Townstead spec §12.1).
 *
 * <p>MCA runs a result's actions in content order, and a reply's heart change, check tier, beat and
 * reaction request arrive from different actions in whatever order the JSON lists them. Nothing
 * outside this mod may depend on that order, so the actions only <em>record</em> here, and the
 * submission settles once, when the outermost scope closes: Townstead is told the measured heart
 * change, then at most one reaction plays.
 *
 * <p>Server thread only. Scopes nest — the numbered chat reply runs the chat path inside the choice
 * service — and only the outermost settles. {@link ConversationSession#claimSideEffect} makes a second
 * settle of the same submission a no-op.
 */
public final class ConversationOutcomes {

    private static final String SIDE_EFFECTS = "outcome";
    private static final ThreadLocal<Submission> ACTIVE = new ThreadLocal<>();

    private ConversationOutcomes() {
    }

    /** A reaction content asked for: a shipped semantic, or any reaction id a pack supplies. */
    public record ReactionRequest(ResourceLocation reaction, String semantic) {
    }

    /** The open submission. {@link #close()} settles it when it is the outermost scope. */
    public static final class Submission implements AutoCloseable {
        private final Entity villager;
        private final ServerPlayer player;
        private final ConversationSession.Frontend frontend;
        private final ConversationSession session;
        private final StanceFamily stance;
        private final String topic;
        private final String question;
        private final String answer;
        /**
         * Topic-completion inputs, captured now because the {@code end} op runs inside the answer and
         * resets the session's topic before this submission settles (see {@link TopicExhaustion}).
         */
        private final String openingBeatId;
        private final int repliesBefore;
        private int depth = 1;
        private boolean succeeded;
        private int measuredHearts;
        private CheckTier tier;
        private OutcomeFamily outcome;
        private NpcSpeechAct act;
        private ReactionRequest requested;

        private Submission(Entity villager, ServerPlayer player, ConversationSession.Frontend frontend,
                           ConversationSession session, StanceFamily stance, String question, String answer) {
            this.villager = villager;
            this.player = player;
            this.frontend = frontend;
            this.session = session;
            this.stance = stance;
            this.question = question;
            this.answer = answer;
            this.topic = session == null ? null : session.topicId().orElse(null);
            this.openingBeatId = session == null ? null : session.openingBeatId().orElse(null);
            this.repliesBefore = session == null ? 0 : session.substantiveReplies();
        }

        /** Whether the answer ran. A reply that failed still reports its real heart change, but plays nothing. */
        public void succeeded(boolean ok) {
            this.succeeded = ok;
        }

        @Override
        public void close() {
            if (--depth > 0) {
                return;
            }
            ACTIVE.remove();
            settle(this);
        }
    }

    /**
     * Opens a submission for a reply about to run, or joins the one already open on this thread.
     * {@code session} may be null; the reply then has no once-per-submission guard beyond this scope.
     */
    public static Submission begin(Entity villager, ServerPlayer player, String question, String answer,
                                   ConversationSession.Frontend frontend) {
        Submission current = ACTIVE.get();
        if (current != null) {
            current.depth++;
            return current;
        }
        ConversationSession session = player == null ? null
                : ConversationSessions.raw(player.getUUID()).orElse(null);
        if (session != null) {
            session.beginSubmission();
        }
        StanceFamily stance = null;
        try {
            stance = BeatContractLoader.active().reply(question, answer).map(ReplyContract::stance).orElse(null);
        } catch (Throwable ignored) {
            // A reply with no declared stance is ordinary; it simply contributes no stance tag.
        }
        Submission submission = new Submission(villager, player, frontend, session, stance, question, answer);
        ACTIVE.set(submission);
        return submission;
    }

    // --- recording, from actions ---------------------------------------------------------------

    /** Adds a measured heart change. False when no submission is open, so the caller reports it now. */
    public static boolean recordHearts(int measured) {
        Submission s = ACTIVE.get();
        if (s == null) {
            return false;
        }
        s.measuredHearts += measured;
        return true;
    }

    /** The tier a check resolved to. The first one resolved in a submission is the one it keeps. */
    public static void recordCheckTier(CheckTier tier) {
        Submission s = ACTIVE.get();
        if (s != null && s.tier == null) {
            s.tier = tier;
        }
    }

    /** The beat the villager's line moved onto: its outcome family and speech act. */
    public static void recordBeat(BeatContract beat) {
        Submission s = ACTIVE.get();
        if (s != null && beat != null) {
            s.outcome = beat.outcome().orElse(null);
            s.act = beat.npcAct();
        }
    }

    /**
     * Queues a reaction. The first request in a submission is the primary one and later ones are
     * ignored. With no submission open the reaction plays now, once.
     */
    public static void requestReaction(Entity villager, ServerPlayer player, ReactionRequest request) {
        if (request == null) {
            return;
        }
        Submission s = ACTIVE.get();
        if (s != null) {
            if (s.requested == null) {
                s.requested = request;
            }
            return;
        }
        fire(villager, player, request, Set.of(ReactionSemantic.NAMESPACE + ":semantic/"
                + ReactionSemantic.sanitize(request.semantic())));
    }

    /**
     * A reaction for something that is not a reply — a greeting or a farewell in chat. Played at
     * once, subject to the same switches and to Townstead's own gates.
     */
    public static void react(Entity villager, ServerPlayer player, ReactionSemantic semantic,
                             ConversationSession.Frontend frontend) {
        if (semantic == null) {
            return;
        }
        fire(villager, player, new ReactionRequest(ResourceLocation.tryParse(semantic.reactionId()), semantic.key()),
                ReactionSemantic.tags(null, null, null, frontend, 0, semantic.key()));
    }

    /** Tells Townstead about a heart change that happened outside any submission. */
    public static void markHeartChangeNow(Entity villager, int measured) {
        if (villager == null || measured == 0 || !Townstead.active()) {
            return;
        }
        try {
            Townstead.bridge().markHeartChange(villager, measured, villager.level().getGameTime());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Townstead heart notification failed", t);
        }
    }

    // --- settling ------------------------------------------------------------------------------

    private static void settle(Submission s) {
        if (s.villager == null || s.player == null) {
            return;
        }
        if (s.session != null && !s.session.claimSideEffect(SIDE_EFFECTS, "settle")) {
            return;
        }
        // The heart notification first, so a reaction keyed on Townstead's heart tags sees it.
        markHeartChangeNow(s.villager, s.measuredHearts);
        if (!s.succeeded) {
            return;
        }
        // The reply ran: count it against the topic it was submitted in and record the topic as
        // discussed when this was the authored way out. Guarded inside; never delays the reaction.
        TopicExhaustion.onReplySettled(s.villager, s.player, s.session, s.topic, s.question, s.answer,
                s.openingBeatId, s.repliesBefore);
        ReactionRequest request = s.requested;
        if (request == null) {
            Optional<ReactionSemantic> derived = ReactionSemantic.derive(s.tier, s.outcome, s.stance, s.act,
                    s.measuredHearts);
            if (derived.isEmpty()) {
                return;
            }
            request = new ReactionRequest(ResourceLocation.tryParse(derived.get().reactionId()), derived.get().key());
        }
        fire(s.villager, s.player, request, ReactionSemantic.tags(s.topic, s.stance, s.tier, s.frontend,
                s.measuredHearts, request.semantic()));
    }

    private static void fire(Entity villager, ServerPlayer player, ReactionRequest request, Set<String> tags) {
        if (villager == null || player == null || request == null || request.reaction() == null
                || !Townstead.reactionsEnabled() || !(villager.level() instanceof ServerLevel level)) {
            return;
        }
        try {
            boolean played = Townstead.bridge().fireReaction(level, villager, player, request.reaction(), tags);
            if (Townstead.debug()) {
                McaConversations.LOGGER.info("[townstead] reaction {} for {} {} (tags {})", request.reaction(),
                        villager.getUUID(), played ? "played" : "declined", tags);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Townstead reaction failed; the conversation is unaffected", t);
        }
    }

    /** Test seam: the submission open on this thread, if any. */
    static Optional<Submission> active() {
        return Optional.ofNullable(ACTIVE.get());
    }
}
