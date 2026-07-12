package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.Addressing.Address;
import dev.otectus.mcaconversations.chat.ChatModeSession.Session;
import dev.otectus.mcaconversations.chat.IntentMatcher.Decision;
import dev.otectus.mcaconversations.chat.IntentMatcher.Scored;
import dev.otectus.mcaconversations.chat.Normalizer.NormalizedMessage;
import dev.otectus.mcaconversations.chat.VillagerFinder.VillagerCandidate;
import dev.otectus.mcaconversations.compat.McaBridge;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.disposition.DispositionApply;
import dev.otectus.mcaconversations.disposition.DispositionAxis;
import dev.otectus.mcaconversations.disposition.Dispositions;
import dev.otectus.mcaconversations.gift.ConversationsCapabilities;
import dev.otectus.mcaconversations.state.ConversationState;
import dev.otectus.mcaconversations.state.StateTracker;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.ServerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Orchestrates the chat-mode pipeline (spec §3). The only class that sees {@link ServerChatEvent} data.
 *
 * <p><b>Threading:</b> {@code ServerChatEvent} fires on a background thread (Forge 1.20.1 wraps chat
 * decoration in {@code CompletableFuture.supplyAsync}). The subscriber therefore captures only the
 * plain {@code (player, raw text)} and hops to the main thread via {@code server.execute} before any
 * entity access. All matching, session mutation, {@code selectAnswer}, and delivery run on the server
 * thread from {@link #handle} onward.
 *
 * <p>Phase 1 wires the plumbing and the {@code /conversations chat debug-ask} milestone driver; the NLU
 * matching that turns free text into a {@code (question, answer)} binding arrives in Phase 2, in
 * {@link #handle}.
 */
public final class ChatModeDispatcher {

    /** Tension bump applied when a villager is insulted in chat (within {@link DispositionApply#MAX_DELTA}). */
    private static final int INSULT_TENSION = 6;

    private ChatModeDispatcher() {
    }

    /** Background-thread entry point: capture plain data and hop to the server thread. */
    public static void onChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        if (player == null) {
            return;
        }
        String raw = event.getRawText();
        MinecraftServer server = player.getServer();
        if (server == null) {
            return;
        }
        try {
            server.execute(() -> {
                try {
                    handle(player, raw);
                } catch (Throwable t) {
                    McaConversations.LOGGER.warn("chat-mode handler failed; ignoring message", t);
                }
            });
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("chat-mode thread hop failed; ignoring message", t);
        }
    }

    /**
     * Server-thread pipeline (§3): target → address → normalize → match → gate-preview → drive. Every
     * step fails safe; a villager either answers in chat via the redirect scope, deflects in character,
     * or stays silent. Never throws to the caller.
     */
    static void handle(ServerPlayer player, String raw) {
        if (player.hasDisconnected() || !player.isAlive() || player.isSpectator()) {
            return;
        }
        if (!McaBridge.isAvailable() || !McaConversationsConfig.COMMON.enableChatMode.get()) {
            return;
        }
        if (raw == null) {
            return;
        }
        String rawMessage = raw.strip();
        if (rawMessage.isEmpty() || rawMessage.startsWith("/")) {
            return;
        }
        if (!isOptedIn(player)) {
            return;
        }

        MinecraftServer server = player.getServer();
        if (server == null) {
            return;
        }
        long now = server.overworld().getGameTime();

        // Anti-spam floor: at most one processed message per cooldown window (drop extras silently).
        Session existing = ChatModeSession.peek(player.getUUID());
        int cooldown = McaConversationsConfig.COMMON.chatModeCooldownTicks.get();
        if (existing != null && cooldown > 0 && now - existing.lastProcessedGameTime < cooldown
                && existing.lastProcessedGameTime != 0) {
            return;
        }

        // Targeting (tiers 1–4): gather within the larger addressed radius, let Addressing resolve.
        double addressedRadius = McaConversationsConfig.COMMON.chatModeAddressedRadius.get();
        List<VillagerCandidate> candidates = VillagerFinder.candidates(player, addressedRadius);
        if (candidates.isEmpty()) {
            return;
        }
        List<String> names = new ArrayList<>(candidates.size());
        List<Double> lookDots = new ArrayList<>(candidates.size());
        for (VillagerCandidate c : candidates) {
            names.add(c.name());
            lookDots.add(c.lookDot());
        }
        int stickyIndex = stickyIndex(existing, candidates, now);
        if (existing != null && stickyIndex < 0) {
            existing.currentQuestion = null; // no in-range/in-window sticky partner → drop stale context
        }
        double lookConeCos = lookConeCos(McaConversationsConfig.COMMON.chatModeLookConeDegrees.get());
        Address address = Addressing.resolve(rawMessage, names, lookDots, stickyIndex, lookConeCos);
        if (address.targetIndex() < 0) {
            return;
        }
        VillagerCandidate target = candidates.get(address.targetIndex());
        boolean directed = address.directed();

        // A tier-3/4 (look-at / ambient) target must be within the tighter ambient radius to overhear;
        // tier-1 (named) and tier-2 (sticky) reach across the full addressed radius.
        double ambientRadius = McaConversationsConfig.COMMON.chatModeRadius.get();
        boolean reachesAcross = address.named() || address.targetIndex() == stickyIndex;
        if (!reachesAcross && target.distSqr() > ambientRadius * ambientRadius) {
            return;
        }

        // Respect an active mute for this player↔session.
        if (existing != null && now < existing.mutedUntilGameTime) {
            return;
        }

        // Ambient (tier 4): any nearby villager the message applies to may answer (spec §12).
        if (!directed) {
            markProcessed(player, now);
            handleAmbient(player, address.message(), candidates, ambientRadius, now);
            return;
        }

        // --- Directed (tiers 1–3): exactly one villager answers ---

        // Busy guard: don't drive the engine while the villager is in another player's GUI interaction.
        Optional<UUID> interacting = McaCompat.isInteractingWith(target.entity());
        if (interacting.isPresent() && !interacting.get().equals(player.getUUID())) {
            markProcessed(player, now);
            deflect(target, player, "busy");
            return;
        }

        markProcessed(player, now);

        IntentIndex index = ChatIntentLoader.active();
        String currentQuestion = contextFor(existing, target);
        NormalizedMessage normalized = Normalizer.normalize(address.message(), index.synonyms());
        if (normalized.contentStems.isEmpty() && normalized.tokens.isEmpty()) {
            return; // e.g. a bare name with no message — nothing to match
        }

        // Small-utterance greeting short-circuit (§6.6): a short "hi"/"hello" is a guaranteed greet,
        // kept away from the topic-threshold math (hello canonicalizes hi/hiya/yo/howdy/greetings).
        if (normalized.contentStems.contains("hello") && normalized.contentTokenCount() <= 3) {
            routeSystem(target, player, "greet", now);
            return;
        }

        List<Scored> ranked = IntentMatcher.rank(index, normalized, currentQuestion);
        List<Scored> eligible = new ArrayList<>();
        for (Scored s : ranked) {
            if (GatePreview.eligible(target.entity(), player, s)) {
                eligible.add(s);
            }
        }

        double minScore = McaConversationsConfig.COMMON.chatModeMinScore.get();
        double ambientMinScore = McaConversationsConfig.COMMON.chatModeAmbientMinScore.get();
        Decision decision = IntentMatcher.decide(eligible, true, minScore, ambientMinScore);

        switch (decision.outcome()) {
            case MATCH -> fulfil(target, player, decision.chosen(), now);
            case AMBIGUOUS -> clarify(target, player, decision.chosen(), decision.alternative());
            case NONE -> graduatedConfusion(target, player, now);
        }
    }

    /**
     * Ambient broadcast (spec §12): each in-range, un-busy, off-cooldown villager scores the message
     * against its <em>own</em> eligible intents; the top {@code chatModeMaxResponders} answer, staggered,
     * and only the first becomes the sticky target. Player-to-player conversation is left alone.
     */
    private static void handleAmbient(ServerPlayer player, String message, List<VillagerCandidate> candidates,
                                      double ambientRadius, long now) {
        if (isPlayerDirected(player, message)) {
            return; // don't hijack a message aimed at another player
        }
        IntentIndex index = ChatIntentLoader.active();
        NormalizedMessage normalized = Normalizer.normalize(message, index.synonyms());
        if (normalized.contentStems.isEmpty() && normalized.tokens.isEmpty()) {
            return;
        }

        double minScore = McaConversationsConfig.COMMON.chatModeMinScore.get();
        double ambientMinScore = McaConversationsConfig.COMMON.chatModeAmbientMinScore.get();
        int cooldown = McaConversationsConfig.COMMON.chatModeCooldownTicks.get();
        double r2 = ambientRadius * ambientRadius;

        List<AmbientSelection.Responder> pool = new ArrayList<>();
        Scored[] chosenByCandidate = new Scored[candidates.size()];
        for (int i = 0; i < candidates.size(); i++) {
            VillagerCandidate c = candidates.get(i);
            if (c.distSqr() > r2) {
                continue; // out of ambient hearing range
            }
            Long lastAmbient = ChatModeSession.lastAmbient(c.entity().getUUID());
            if (lastAmbient != null && cooldown > 0 && now - lastAmbient < cooldown) {
                continue; // this villager already answered an ambient message recently
            }
            Optional<UUID> interacting = McaCompat.isInteractingWith(c.entity());
            if (interacting.isPresent() && !interacting.get().equals(player.getUUID())) {
                continue; // busy with another player's GUI — silently skip in ambient
            }
            List<Scored> ranked = IntentMatcher.rank(index, normalized, null);
            List<Scored> eligible = new ArrayList<>();
            for (Scored s : ranked) {
                if (GatePreview.eligible(c.entity(), player, s)) {
                    eligible.add(s);
                }
            }
            Decision d = IntentMatcher.decide(eligible, false, minScore, ambientMinScore);
            if (d.outcome() == IntentMatcher.Outcome.MATCH && ambientAnswerable(d.chosen())) {
                chosenByCandidate[i] = d.chosen();
                pool.add(new AmbientSelection.Responder(i, d.chosen().score(), c.distSqr()));
            }
        }
        if (pool.isEmpty()) {
            return;
        }

        int maxResponders = McaConversationsConfig.COMMON.chatModeMaxResponders.get();
        List<AmbientSelection.Responder> responders = AmbientSelection.select(pool, maxResponders);
        for (int rank = 0; rank < responders.size(); rank++) {
            AmbientSelection.Responder r = responders.get(rank);
            VillagerCandidate c = candidates.get(r.candidateIndex());
            Scored chosen = chosenByCandidate[r.candidateIndex()];
            int stagger = AmbientSelection.staggerOffsetTicks(c.entity().getUUID(), rank);
            respondAmbient(c, player, chosen, now, stagger, rank == 0);
            ChatModeSession.markAmbient(c.entity().getUUID(), now);
        }
    }

    /** Topic and greeting intents are shoutable to a crowd; directed controls (farewell/mute/decline/insult) are not. */
    private static boolean ambientAnswerable(Scored chosen) {
        return !chosen.isSystem() || "greet".equals(chosen.system());
    }

    /** Drives one ambient responder; only the first (rank 0) becomes the sticky target. */
    private static void respondAmbient(VillagerCandidate target, ServerPlayer player, Scored chosen,
                                       long now, int stagger, boolean makeSticky) {
        if (chosen.isSystem()) {
            if ("greet".equals(chosen.system())) {
                driveStaggered(target, player, "greet", "checkin", now, stagger, makeSticky);
            }
            return; // farewell/mute/decline are directed controls — not meaningful shouted to a crowd
        }
        driveStaggered(target, player, chosen.question(), chosen.answer(), now, stagger, makeSticky);
    }

    /** True if the message is aimed at another player (an {@code @}-reply or a player-name vocative). */
    private static boolean isPlayerDirected(ServerPlayer player, String message) {
        if (message.startsWith("@")) {
            return true;
        }
        MinecraftServer server = player.getServer();
        if (server == null) {
            return false;
        }
        List<String> names = new ArrayList<>();
        for (ServerPlayer p : server.getPlayerList().getPlayers()) {
            if (p != player) {
                names.add(p.getGameProfile().getName());
            }
        }
        return !names.isEmpty() && Addressing.resolve(message, names).named();
    }

    /** Drives the matched intent through MCA's engine (redirect scope open) or routes a system intent. */
    private static void fulfil(VillagerCandidate target, ServerPlayer player, Scored chosen, long now) {
        if (chosen.isSystem()) {
            routeSystem(target, player, chosen.system(), now);
            return;
        }
        drive(target, player, chosen.question(), chosen.answer(), now);
    }

    private static void routeSystem(VillagerCandidate target, ServerPlayer player, String system, long now) {
        switch (system) {
            case "greet" -> drive(target, player, "greet", "checkin", now);
            case "farewell" -> farewell(target, player);
            case "mute" -> mute(target, player, now);
            case "drop" -> decline(target, player);
            case "insult" -> insult(target, player);
            default -> McaConversations.LOGGER.debug("chat-mode system intent '{}' not handled", system);
        }
    }

    /**
     * Obvious in-game insult (spec §11): in-character rebuke + the villager turns ANNOYED with a small
     * tension bump — the same social response as striking one. Never censors: the player's message still
     * posts to chat untouched. Only reached when {@code chatModeInsultDetection} is on (gated in
     * {@link GatePreview}); {@link StateTracker}/{@link Dispositions} additionally no-op if their own
     * subsystems are disabled.
     */
    private static void insult(VillagerCandidate target, ServerPlayer player) {
        deflect(target, player, "insult");
        StateTracker.apply(target.entity(), player, ConversationState.ANNOYED);
        Dispositions.apply(target.entity(), player,
                new DispositionApply("chatmode.insult", Map.of(DispositionAxis.TENSION, INSULT_TENSION)));
    }

    /** Goodbye line + end stickiness (spec §9): the villager stops being the default target. */
    private static void farewell(VillagerCandidate target, ServerPlayer player) {
        deflect(target, player, "farewell");
        Session s = ChatModeSession.get(player.getUUID());
        s.villagerId = null;
        s.currentQuestion = null;
        s.consecutiveMisses = 0;
    }

    /** "Stop talking" (spec §11): mute this pairing for {@code chatModeMuteTicks}; one acknowledgment. */
    private static void mute(VillagerCandidate target, ServerPlayer player, long now) {
        Session s = ChatModeSession.get(player.getUUID());
        int muteTicks = McaConversationsConfig.COMMON.chatModeMuteTicks.get();
        s.mutedUntilGameTime = now + Math.max(0, muteTicks);
        s.currentQuestion = null;
        deflect(target, player, "muted");
    }

    /** "Never mind" (spec §11): drop the open sub-question; never counts as a miss. */
    private static void decline(VillagerCandidate target, ServerPlayer player) {
        ChatModeSession.get(player.getUUID()).currentQuestion = null;
        deflect(target, player, "dropped");
    }

    private static void drive(VillagerCandidate target, ServerPlayer player, String question, String answer, long now) {
        driveStaggered(target, player, question, answer, now, 0, true);
    }

    /**
     * Drives one exchange with an optional delivery {@code stagger} and, when {@code makeSticky}, marks
     * the villager as the sticky target. Ambient non-first responders drive normally but do not steal
     * stickiness (spec §12.4).
     */
    private static void driveStaggered(VillagerCandidate target, ServerPlayer player, String question,
                                       String answer, long now, int stagger, boolean makeSticky) {
        boolean ok;
        try (ChatModeSession.Scope scope = ChatModeSession.open(player, target.entity(), stagger)) {
            ok = McaCompat.selectAnswer(target.entity(), player, question, answer);
        }
        if (ok) {
            // The redirect mixin may have recorded a follow-up currentQuestion during selectAnswer;
            // recordExchange marks the sticky target + resets the miss ladder without clearing it.
            if (makeSticky) {
                ChatModeSession.recordExchange(player.getUUID(), target.entity().getUUID(), now);
            }
        } else {
            McaConversations.LOGGER.debug("chat-mode selectAnswer({}, {}) returned false", question, answer);
        }
    }

    /** Graduated in-character confusion (§11), tracked per session: confused → hint → shrug+mute. */
    private static void graduatedConfusion(VillagerCandidate target, ServerPlayer player, long now) {
        Session s = ChatModeSession.get(player.getUUID());
        s.consecutiveMisses++;
        if (s.consecutiveMisses == 1) {
            deflect(target, player, "confused");
        } else if (s.consecutiveMisses == 2) {
            deflectHint(target, player);
        } else {
            deflect(target, player, "shrug");
            int cooldown = McaConversationsConfig.COMMON.chatModeCooldownTicks.get();
            s.mutedUntilGameTime = now + Math.max(0, cooldown) * 4L;
        }
    }

    private static void clarify(VillagerCandidate target, ServerPlayer player, Scored top, Scored alt) {
        // %1$s = player name (auto), %2$s = first topic, %3$s = second topic.
        ChatDelivery.villagerSays(target.entity(), player,
                voiced(target.entity(), player, "chatmode.clarify", topicName(top), topicName(alt)));
    }

    private static void deflect(VillagerCandidate target, ServerPlayer player, String key) {
        ChatDelivery.villagerSays(target.entity(), player, voiced(target.entity(), player, "chatmode." + key));
    }

    private static void deflectHint(VillagerCandidate target, ServerPlayer player) {
        // %1$s = player name (auto), %2$s = the topic list.
        Component topics = Component.translatable("dialogue.chatmode.topics");
        ChatDelivery.villagerSays(target.entity(), player,
                voiced(target.entity(), player, "chatmode.hint", topics));
    }

    /**
     * Renders a chat-mode line in the villager's personality voice via MCA's own {@code getTranslatable}
     * (the same path {@code QuestVoiceResolver} uses): the personality-overlay marker and the random
     * {@code /N} variant are picked by MCA, and the spouse-aware player name is auto-bound to {@code %1$s}
     * (extra args land at {@code %2$s+}). Falls back to a raw translatable — with the player name manually
     * at {@code %1$s} — if MCA has no line (e.g. the entity isn't a loaded villager).
     */
    private static Component voiced(Entity villager, ServerPlayer player, String phrase, Object... extraArgs) {
        Optional<MutableComponent> line = McaCompat.getDialogueLine(villager, player, phrase, extraArgs);
        if (line.isPresent()) {
            return line.get();
        }
        Object[] fallback = new Object[extraArgs.length + 1];
        fallback[0] = player.getDisplayName();
        System.arraycopy(extraArgs, 0, fallback, 1, extraArgs.length);
        return Component.translatable("dialogue." + phrase, fallback);
    }

    private static Component topicName(Scored s) {
        String answer = s.answer() != null ? s.answer() : (s.system() != null ? s.system() : s.id());
        return Component.literal(answer);
    }

    /**
     * The candidate index of the still-in-range sticky partner (spec §5 tier 2), or -1. The sticky
     * pointer only survives while the last exchange is within {@code chatModeStickinessTicks}; past
     * that the window has lapsed and no villager is the default target.
     */
    private static int stickyIndex(Session session, List<VillagerCandidate> candidates, long now) {
        if (session == null || session.villagerId == null) {
            return -1;
        }
        int stickinessTicks = McaConversationsConfig.COMMON.chatModeStickinessTicks.get();
        if (stickinessTicks > 0 && now - session.lastExchangeGameTime >= stickinessTicks) {
            return -1; // stickiness window lapsed
        }
        for (int i = 0; i < candidates.size(); i++) {
            if (session.villagerId.equals(candidates.get(i).entity().getUUID())) {
                return i;
            }
        }
        return -1;
    }

    /** {@code cos(coneHalfAngle)} for look-at gating, or {@code NaN} when the cone is disabled (0°). */
    private static double lookConeCos(double coneDegrees) {
        return coneDegrees <= 0 ? Double.NaN : Math.cos(Math.toRadians(coneDegrees));
    }

    private static String contextFor(Session session, VillagerCandidate target) {
        if (session == null || session.currentQuestion == null || session.villagerId == null) {
            return null;
        }
        if (!session.villagerId.equals(target.entity().getUUID())) {
            return null;
        }
        // Category hubs (menus in the GUI) are meaningless as chat context — treat them as no context.
        String q = session.currentQuestion;
        if (q.equals("conversations") || q.startsWith("conversations.cat.")) {
            return null;
        }
        return q;
    }

    private static void markProcessed(ServerPlayer player, long now) {
        ChatModeSession.get(player.getUUID()).lastProcessedGameTime = now;
    }

    /**
     * The Phase-1 de-risk milestone: drive the nearest villager's dialogue engine at an explicit
     * {@code (question, answer)} and let the redirect mixin surface the reply in chat. Returns a short
     * status the command reports. Op-gated by the command.
     */
    public static String debugAsk(ServerPlayer player, String questionId, String answerName) {
        if (!McaBridge.isAvailable()) {
            return "MCA is not available; chat mode is inert.";
        }
        double radius = McaConversationsConfig.COMMON.chatModeAddressedRadius.get();
        List<VillagerFinder.VillagerCandidate> candidates = VillagerFinder.candidates(player, radius);
        if (candidates.isEmpty()) {
            return "No MCA villager within " + (int) radius + " blocks.";
        }
        VillagerFinder.VillagerCandidate target = candidates.get(0);
        boolean ok;
        try (ChatModeSession.Scope scope = ChatModeSession.open(player, target.entity())) {
            ok = McaCompat.selectAnswer(target.entity(), player, questionId, answerName);
        }
        if (!ok) {
            return "selectAnswer failed for (" + questionId + ", " + answerName + ") — see debug log.";
        }
        MinecraftServer server = player.getServer();
        long now = server != null ? server.overworld().getGameTime() : 0L;
        ChatModeSession.recordExchange(player.getUUID(), target.entity().getUUID(), now);
        String name = target.name().isBlank() ? "villager" : target.name();
        String redirect = ChatModeSession.redirectionAvailable() ? "" : " (warning: delivery redirect not active)";
        return "Asked " + name + " (" + questionId + " / " + answerName + ")." + redirect;
    }

    private static boolean isOptedIn(ServerPlayer player) {
        return ConversationsCapabilities.getChatMode(player)
                .map(ChatModePlayerState::isEnabled)
                .orElse(McaConversationsConfig.COMMON.chatModeDefaultOn.get());
    }
}
