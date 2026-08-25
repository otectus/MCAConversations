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
import dev.otectus.mcaconversations.gift.ConversationsAttachments;
import dev.otectus.mcaconversations.state.ConversationState;
import dev.otectus.mcaconversations.state.StateTracker;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.event.ServerChatEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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

    /** Stems of the two time-of-day greetings that count as a bare greeting on their own. */
    private static final String MORNING_STEM = Normalizer.stemToken("morning");
    private static final String EVENING_STEM = Normalizer.stemToken("evening");

    private ChatModeDispatcher() {
    }

    /**
     * EXPERIMENTAL radius-local chat (Phase 4, {@code chatModeLocalChat}, default off): cancels the
     * sender's global chat message and re-sends it as an <b>unsigned system message</b> to players
     * within {@code chatModeAddressedRadius} only, then runs the normal matching pipeline on the same
     * main-thread hop. Returns true iff the event was consumed (the caller must not also route it
     * through {@link #onChat}).
     *
     * <p><b>Signed-chat trade-off (why this is opt-in):</b> a canceled message never reaches vanilla
     * broadcast, so recipients lose the 1.19+ signed-message chain (chat reporting) for these lines.
     * The event is only canceled after the rebroadcast task is accepted — a failed submission falls
     * back to untouched vanilla chat rather than eating the message.
     */
    public static boolean interceptLocalChat(ServerChatEvent event) {
        if (!McaConversationsConfig.COMMON.chatModeLocalChat.get()) {
            return false;
        }
        ServerPlayer player = event.getPlayer();
        if (player == null || !isOptedIn(player)) {
            return false;
        }
        String raw = event.getRawText();
        MinecraftServer server = player.getServer();
        if (server == null) {
            return false;
        }
        try {
            server.execute(() -> {
                try {
                    rebroadcastLocal(player, raw);
                    handle(player, raw);
                } catch (Throwable t) {
                    McaConversations.LOGGER.warn("chat-mode local-chat delivery failed", t);
                }
            });
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("local-chat thread hop failed; leaving vanilla chat untouched", t);
            return false;
        }
        event.setCanceled(true);
        return true;
    }

    /** Vanilla-style {@code <name> text} line to the sender + players within the addressed radius. */
    private static void rebroadcastLocal(ServerPlayer sender, String raw) {
        if (sender.hasDisconnected()) {
            return;
        }
        // A canceled chat event never reaches vanilla's broadcast OR its log — keep the server's
        // chat record (moderation history) intact.
        McaConversations.LOGGER.info("[local-chat] <{}> {}", sender.getGameProfile().getName(), raw);
        double radius = McaConversationsConfig.COMMON.chatModeAddressedRadius.get();
        double r2 = radius * radius;
        Component line = Component.literal("<").append(sender.getDisplayName())
                .append(Component.literal("> " + raw));
        sender.sendSystemMessage(line);
        for (ServerPlayer other : sender.serverLevel().players()) {
            if (other != sender && !other.hasDisconnected() && other.distanceToSqr(sender) <= r2) {
                other.sendSystemMessage(line);
            }
        }
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

        // A message addressed to another player by name is theirs, not a villager's, no matter which
        // villager happens to be sticky or in the look cone. Checked before targeting so it costs no
        // miss and leaves the session intact.
        if (isPlayerDirected(player, rawMessage)) {
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
            existing.clearQuestion(); // no in-range/in-window sticky partner → drop stale context
        }
        double lookConeCos = lookConeCos(McaConversationsConfig.COMMON.chatModeLookConeDegrees.get());
        Address address = Addressing.resolve(rawMessage, names, lookDots, stickyIndex, lookConeCos,
                reservedNameWords());
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

        // Respect an active "stop talking" mute for this villager↔player pairing (spec §11).
        if (existing != null && directed && existing.isMuted(target.entity().getUUID(), now)) {
            return;
        }

        // Ambient (tier 4): any nearby villager the message applies to may answer (spec §12);
        // muted pairings are filtered per candidate inside.
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

        // A baby cannot hold a conversation; it babbles back and the exchange ends there. Placed
        // after the busy gate so a baby another player is interacting with still reports "busy".
        if (McaCompat.isBaby(target.entity())) {
            deflect(target, player, "babble");
            attend(target, player, now);
            return;
        }

        IntentIndex index = ChatIntentLoader.active();
        String currentQuestion = contextFor(existing, target);
        List<String> offered = currentQuestion == null
                ? List.of() : ChatModeSession.currentAnswers(player.getUUID());

        // A live decision may be answered by its number. The villager listed the choices with its
        // last line, so "2" is as much a way of naming a stance as typing the words would be, and it
        // drives the identical selectAnswer call the GUI button drives.
        if (!offered.isEmpty()) {
            java.util.OptionalInt choice = QuickReplies.parse(address.message(), offered.size());
            if (choice.isPresent()) {
                java.util.Optional<String> picked = QuickReplies.answerFor(offered, choice.getAsInt());
                if (picked.isPresent()
                        && McaCompat.checkConstraints(target.entity(), player, currentQuestion, picked.get())) {
                    drive(target, player, currentQuestion, picked.get(), now);
                    return;
                }
            }
        }

        NormalizedMessage normalized = Normalizer.normalize(address.message(), index.synonyms());
        if (normalized.contentStems.isEmpty() && normalized.tokens.isEmpty()) {
            // "Hey Anna" strips to nothing, but the greeting itself is the message — answer it.
            if (address.named() && address.greeting()) {
                routeSystem(target, player, "greet", now);
                return;
            }
            // A bare name ("Nataliya?") is a call, not a question: acknowledge, turn, and wait.
            if (address.named()) {
                ChatDelivery.villagerSays(target.entity(), player,
                        voiced(target.entity(), player, "chatmode.attentive"));
                ChatModeSession.recordExchange(player.getUUID(), target.entity().getUUID(), now);
                attend(target, player, now);
            }
            return;
        }

        // Small-utterance greeting short-circuit (§6.6): a short "hi"/"hello" is a guaranteed greet,
        // kept away from the topic-threshold math (hello canonicalizes hi/hiya/yo/howdy/greetings).
        if (isSmallGreeting(normalized)) {
            routeSystem(target, player, "greet", now);
            return;
        }

        // While a decision is open, score the choices on the table (plus the ways out) rather than
        // every topic in the pack: a weak global match must never masquerade as an answer (§7.3).
        List<Scored> ranked = IntentMatcher.rank(index, normalized, currentQuestion, offered);
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
            case AMBIGUOUS -> clarify(target, player, decision.chosen(), decision.alternative(), now);
            case NONE -> {
                // A toddler that understood the topic but is gated out of it says so in its own
                // words rather than falling through to the adult confusion ladder.
                if (gatedTopicMiss(ranked, eligible, minScore)
                        && "toddler".equals(McaCompat.getAgeGroup(target.entity()).orElse(""))) {
                    deflect(target, player, "kidtopic");
                    attend(target, player, now);
                    return;
                }
                // Confusion is only in-character when the message was plausibly aimed at the villager:
                // an explicit name, or an engagement cue (question form / second person — spec §5 tier 2).
                // A sticky/look-at capture of ordinary player-to-player chat stays silent, costs no miss,
                // and keeps the session so the player can re-engage.
                if (address.named() || looksEngaged(normalized)) {
                    graduatedConfusion(target, player, now);
                }
            }
        }
    }

    /**
     * Pure engagement cue (spec §5 tier 2): the message reads as talking <em>to</em> someone — it is a
     * question ({@code ?} or a leading question word) or uses second person ({@code you}/{@code your}).
     */
    static boolean looksEngaged(NormalizedMessage msg) {
        return msg.interrogative || msg.contentStems.contains("you") || msg.contentStems.contains("your");
    }

    /**
     * Ambient broadcast (spec §12): each in-range, un-busy, off-cooldown villager scores the message
     * against its <em>own</em> eligible intents; the top {@code chatModeMaxResponders} answer, staggered,
     * and only the first becomes the sticky target. Player-to-player conversation is left alone.
     */
    private static void handleAmbient(ServerPlayer player, String message, List<VillagerCandidate> candidates,
                                      double ambientRadius, long now) {
        IntentIndex index = ChatIntentLoader.active();
        NormalizedMessage normalized = Normalizer.normalize(message, index.synonyms());
        if (normalized.contentStems.isEmpty() && normalized.tokens.isEmpty()) {
            return;
        }
        // "hello" called into a crowd should get ONE villager waving back, not the whole square
        // scoring it as a topic. Ambient greetings route to a single hail instead.
        if (isSmallGreeting(normalized)) {
            ambientHail(player, candidates, ambientRadius, now);
            return;
        }

        double minScore = McaConversationsConfig.COMMON.chatModeMinScore.get();
        double ambientMinScore = McaConversationsConfig.COMMON.chatModeAmbientMinScore.get();
        int cooldown = McaConversationsConfig.COMMON.chatModeCooldownTicks.get();
        double r2 = ambientRadius * ambientRadius;

        // The ranking depends only on (message, index, no-context) — identical for every candidate.
        // Only GatePreview eligibility differs per villager, so rank once and filter per candidate.
        List<Scored> ranked = IntentMatcher.rank(index, normalized, null);

        Session session = ChatModeSession.peek(player.getUUID());
        List<AmbientSelection.Responder> pool = new ArrayList<>();
        Scored[] chosenByCandidate = new Scored[candidates.size()];
        for (int i = 0; i < candidates.size(); i++) {
            VillagerCandidate c = candidates.get(i);
            if (c.distSqr() > r2) {
                continue; // out of ambient hearing range
            }
            if (session != null && session.isMuted(c.entity().getUUID(), now)) {
                continue; // "stop talking" pairing — this villager stays quiet for this player
            }
            Long lastAmbient = ChatModeSession.lastAmbient(c.entity().getUUID());
            if (lastAmbient != null && cooldown > 0 && now - lastAmbient < cooldown) {
                continue; // this villager already answered an ambient message recently
            }
            Optional<UUID> interacting = McaCompat.isInteractingWith(c.entity());
            if (interacting.isPresent() && !interacting.get().equals(player.getUUID())) {
                continue; // busy with another player's GUI — silently skip in ambient
            }
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
                hail(target, player, now, stagger, makeSticky);
            }
            return; // farewell/mute/decline are directed controls — not meaningful shouted to a crowd
        }
        driveStaggered(target, player, chosen.question(), chosen.answer(), now, stagger, makeSticky);
    }

    /** True if the message is aimed at another player (an {@code @}-reply or a player-name vocative). */
    /**
     * True for a message that is nothing but a greeting: "hi"/"hello" and friends in three content
     * tokens or fewer, or a bare "good morning"/"good evening". These bypass topic scoring entirely
     * so a wave never gets mistaken for a question.
     */
    static boolean isSmallGreeting(NormalizedMessage msg) {
        if (msg.contentStems.contains("hello") && msg.contentTokenCount() <= 3) {
            return true;
        }
        return (msg.contentStems.contains(MORNING_STEM) || msg.contentStems.contains(EVENING_STEM))
                && msg.contentTokenCount() <= 2;
    }

    /**
     * Words that must never be read as a villager's name: every keyword the loaded intent packs use,
     * plus the normalizer's stopwords. Without this a content word like "work" could fuzzy-match a
     * villager called "Wark" and silently redirect the message.
     */
    private static Set<String> reservedNameWords() {
        Set<String> out = new HashSet<>(ChatIntentLoader.active().keywordVocabulary());
        out.addAll(Normalizer.stopwords());
        return out;
    }

    /**
     * True when the best-scoring intent was confident enough but was filtered out by
     * {@link GatePreview} — i.e. the villager understood the topic but is not allowed to discuss it.
     * Used to give toddlers a specific "that's grown-up talk" reply instead of generic confusion.
     */
    static boolean gatedTopicMiss(List<Scored> ranked, List<Scored> eligible, double minScore) {
        if (ranked.isEmpty()) {
            return false;
        }
        Scored top = ranked.get(0);
        return !top.isSystem() && top.score() >= minScore && !eligible.contains(top);
    }

    /**
     * Ambient greeting: the nearest eligible villager waves back, and only that one. Skips villagers
     * that are muted, still on their ambient cooldown, or busy in someone else's interaction screen.
     */
    private static void ambientHail(ServerPlayer player, List<VillagerCandidate> candidates,
                                    double ambientRadius, long now) {
        double r2 = ambientRadius * ambientRadius;
        int cooldown = McaConversationsConfig.COMMON.chatModeCooldownTicks.get();
        Session session = ChatModeSession.peek(player.getUUID());
        for (VillagerCandidate c : candidates) {
            if (c.distSqr() > r2) {
                continue;
            }
            if (session != null && session.isMuted(c.entity().getUUID(), now)) {
                continue;
            }
            Long lastAmbient = ChatModeSession.lastAmbient(c.entity().getUUID());
            if (lastAmbient != null && cooldown > 0 && now - lastAmbient < cooldown) {
                continue;
            }
            Optional<UUID> interacting = McaCompat.isInteractingWith(c.entity());
            if (interacting.isPresent() && !interacting.get().equals(player.getUUID())) {
                continue;
            }
            hail(c, player, now, 0, true);
            ChatModeSession.markAmbient(c.entity().getUUID(), now);
            return;
        }
    }

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
            case "greet" -> hail(target, player, now, 0, true);
            case "farewell" -> farewell(target, player);
            case "mute" -> mute(target, player, now);
            case "drop" -> decline(target, player, now);
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
        s.clearQuestion();
        s.consecutiveMisses = 0;
        VillagerAttention.release(target.entity()); // conversation over — back to their day
    }

    /** "Stop talking" (spec §11): mute this villager↔player pairing for {@code chatModeMuteTicks}. */
    private static void mute(VillagerCandidate target, ServerPlayer player, long now) {
        Session s = ChatModeSession.get(player.getUUID());
        int muteTicks = McaConversationsConfig.COMMON.chatModeMuteTicks.get();
        s.mute(target.entity().getUUID(), now + Math.max(0, muteTicks));
        s.clearQuestion();
        deflect(target, player, "muted");
        VillagerAttention.release(target.entity()); // asked to leave the player be — walks off too
    }

    /** "Never mind" (spec §11): drop the open sub-question; never counts as a miss. */
    private static void decline(VillagerCandidate target, ServerPlayer player, long now) {
        ChatModeSession.get(player.getUUID()).clearQuestion();
        deflect(target, player, "dropped");
        attend(target, player, now); // still conversing, just changing the subject
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
        boolean showHearts = McaConversationsConfig.COMMON.chatModeShowHeartChanges.get();
        int heartsBefore = showHearts ? McaCompat.getHearts(player, target.entity()) : 0;
        boolean ok;
        try (ChatModeSession.Scope scope = ChatModeSession.open(player, target.entity(), stagger)) {
            ok = McaCompat.selectAnswer(target.entity(), player, question, answer);
            if (showHearts && ok) {
                // Delivery is deferred through the scheduler, so the delta lands before the line renders.
                scope.heartsDelta = McaCompat.getHearts(player, target.entity()) - heartsBefore;
            }
            if (ok) {
                // selectAnswer may have left a new decision open; the redirect mixin recorded what was
                // offered, so the reply can carry the choices the GUI would have drawn as buttons.
                String nextQuestion = ChatModeSession.currentQuestion(player.getUUID());
                if (nextQuestion != null && !isHubQuestion(nextQuestion)) {
                    QuickReplies.optionsLine(nextQuestion, ChatModeSession.currentAnswers(player.getUUID()))
                            .ifPresent(line -> scope.options = line);
                }
            }
        }
        if (ok) {
            // The redirect mixin may have recorded a follow-up currentQuestion during selectAnswer;
            // recordExchange marks the sticky target + resets the miss ladder without clearing it.
            if (makeSticky) {
                ChatModeSession.recordExchange(player.getUUID(), target.entity().getUUID(), now);
            }
            attend(target, player, now);
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
            attend(target, player, now);
        } else if (s.consecutiveMisses == 2) {
            deflectHint(target, player);
            attend(target, player, now);
        } else {
            deflect(target, player, "shrug");
            // The villager disengages from this player for a while — flailing at Agnes never mutes Ilsa.
            int cooldown = McaConversationsConfig.COMMON.chatModeCooldownTicks.get();
            s.mute(target.entity().getUUID(), now + Math.max(0, cooldown) * 4L);
            VillagerAttention.release(target.entity()); // demonstratively turns back to work
        }
    }

    private static void clarify(VillagerCandidate target, ServerPlayer player, Scored top, Scored alt, long now) {
        // %1$s = player name (auto), %2$s = first topic, %3$s = second topic.
        ChatDelivery.villagerSays(target.entity(), player,
                voiced(target.entity(), player, "chatmode.clarify", topicName(top), topicName(alt)));
        attend(target, player, now); // waiting on the player's answer
    }

    private static void deflect(VillagerCandidate target, ServerPlayer player, String key) {
        ChatDelivery.villagerSays(target.entity(), player, voiced(target.entity(), player, "chatmode." + key));
    }

    private static void deflectHint(VillagerCandidate target, ServerPlayer player) {
        // %1$s = player name (auto), %2$s = the topic list.
        ChatDelivery.villagerSays(target.entity(), player,
                voiced(target.entity(), player, "chatmode.hint", eligibleTopics(target, player)));
    }

    /** Preferred hint order for the shipped topic hubs; datapack-added topics follow alphabetically. */
    private static final List<String> TOPIC_ORDER =
            List.of("chitchat", "greet", "profession", "village", "events", "personal", "us", "family");

    /**
     * The topics this villager can <em>actually</em> discuss with this player (spec §11 step 2): the
     * distinct questions of the global topic intents, kept only when at least one bound answer passes
     * its constraints (a spouse sees "us", a parent sees "the family", everyone else doesn't). Falls
     * back to the static {@code dialogue.chatmode.topics} list if nothing survives.
     */
    private static Component eligibleTopics(VillagerCandidate target, ServerPlayer player) {
        IntentIndex index = ChatIntentLoader.active();
        Set<String> suffixes = new HashSet<>();
        for (IntentIndex.CompiledIntent intent : index.activeIntents(null)) {
            IntentBinding b = intent.source;
            if (b.isSystem() || b.question() == null || suffixes.contains(topicSuffix(b.question()))) {
                continue;
            }
            if (McaCompat.checkConstraints(target.entity(), player, b.question(), b.answer())) {
                suffixes.add(topicSuffix(b.question()));
            }
        }
        if (suffixes.isEmpty()) {
            return Component.translatable("dialogue.chatmode.topics");
        }
        MutableComponent out = Component.empty();
        List<String> ordered = orderedTopics(suffixes);
        for (int i = 0; i < ordered.size(); i++) {
            if (i > 0) {
                out.append(Component.literal(", "));
            }
            out.append(Component.translatableWithFallback(
                    "dialogue.chatmode.topic." + ordered.get(i), ordered.get(i)));
        }
        return out;
    }

    /** Pure: the hub's short name — the last dot segment ({@code conversations.cat.chitchat} → {@code chitchat}). */
    static String topicSuffix(String questionId) {
        int i = questionId.lastIndexOf('.');
        return i < 0 ? questionId : questionId.substring(i + 1);
    }

    /** Pure: dedupes and orders topic suffixes — shipped hubs first in a fixed order, extras alphabetical. */
    static List<String> orderedTopics(Set<String> suffixes) {
        List<String> out = new ArrayList<>(new java.util.TreeSet<>(suffixes));
        out.sort(java.util.Comparator.comparingInt(s -> {
            int i = TOPIC_ORDER.indexOf(s);
            return i < 0 ? TOPIC_ORDER.size() : i;
        }));
        return out;
    }

    /**
     * Renders a chat-mode line in the villager's personality voice via MCA's own {@code getTranslatable}
     * (the same path {@code QuestVoiceResolver} uses): the personality-overlay marker and the random
     * {@code /N} variant are picked by MCA, and the spouse-aware player name is auto-bound to {@code %1$s}
     * (extra args land at {@code %2$s+}). Falls back to a raw translatable — with the player name manually
     * at {@code %1$s} — if MCA has no line (e.g. the entity isn't a loaded villager).
     */
    private static Component voiced(Entity villager, ServerPlayer player, String phrase, Object... extraArgs) {
        // Babies babble and toddlers get their own shorter variants of every chat-mode family.
        String aged = AgeVoice.phrase(phrase, McaCompat.getAgeGroup(villager));
        Optional<MutableComponent> line = McaCompat.getDialogueLine(villager, player, aged, extraArgs);
        if (line.isPresent()) {
            return line.get();
        }
        Object[] fallback = new Object[extraArgs.length + 1];
        fallback[0] = player.getDisplayName();
        System.arraycopy(extraArgs, 0, fallback, 1, extraArgs.length);
        return Component.translatable("dialogue." + aged, fallback);
    }

    private static Component topicName(Scored s) {
        String answer = s.answer() != null ? s.answer() : (s.system() != null ? s.system() : s.id());
        // Topic names are shown to the player (clarify prompts, debug output), so they get a
        // translatable label; the raw answer id remains the fallback for un-localized topics.
        return Component.translatableWithFallback("dialogue.chatmode.topic." + answer, answer);
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
        if (session == null || session.currentQuestion() == null || session.villagerId == null) {
            return null;
        }
        if (!session.villagerId.equals(target.entity().getUUID())) {
            return null;
        }
        // Category hubs (menus in the GUI) are meaningless as chat context — treat them as no context.
        String q = session.currentQuestion();
        return isHubQuestion(q) ? null : q;
    }

    /** True for the navigation pages: menus in the GUI, and nothing to answer in chat. */
    private static boolean isHubQuestion(String question) {
        return question == null || question.equals("conversations")
                || question.startsWith("conversations.cat.");
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

    /** Proactive greet-on-approach entry: an actual hello, sticky so the player can just reply. */
    static void proactiveGreet(VillagerCandidate target, ServerPlayer player, long now) {
        hail(target, player, now, 0, true);
    }

    /**
     * Server entry for the client's typing packet: full re-validation (flags, opt-in, liveness) so a
     * stray or forged packet can at most make villagers glance over, then attention holds/release.
     */
    public static void onTypingStatus(ServerPlayer player, boolean typing) {
        if (!McaBridge.isAvailable() || !McaConversationsConfig.COMMON.enableChatMode.get()
                || !McaConversationsConfig.COMMON.chatModeTypingAttention.get()) {
            return;
        }
        if (player.hasDisconnected() || !player.isAlive() || player.isSpectator() || !isOptedIn(player)) {
            return;
        }
        MinecraftServer server = player.getServer();
        if (server == null) {
            return;
        }
        long now = server.overworld().getGameTime();
        if (typing) {
            VillagerAttention.playerTyping(player, now);
        } else {
            VillagerAttention.playerStoppedTyping(player);
        }
    }

    /** Conversation attention: the villager stays put facing the player until the timer lapses. */
    private static void attend(VillagerCandidate target, ServerPlayer player, long now) {
        int ticks = McaConversationsConfig.COMMON.chatModeAttentionTicks.get();
        if (ticks > 0) {
            VillagerAttention.hold(target.entity(), player, now + ticks, AttentionLedger.Source.CONVERSATION);
        }
    }

    /**
     * A real greeting (not the {@code greet/checkin} "how have you been" <em>answer</em>, which reads
     * as a reply to a question nobody asked): a line from the {@code chatmode.hail} pool — or the
     * {@code hail_cold} brush-off when the villager dislikes the player — rendered in personality
     * voice. Sticky when directed at one player so a plain reply carries the conversation on.
     */
    private static void hail(VillagerCandidate target, ServerPlayer player, long now, int stagger,
                             boolean makeSticky) {
        String pool = McaCompat.getHearts(player, target.entity()) < 0
                ? "chatmode.hail_cold" : "chatmode.hail";
        ChatDelivery.villagerSays(target.entity(), player, voiced(target.entity(), player, pool), stagger);
        if (makeSticky) {
            ChatModeSession.recordExchange(player.getUUID(), target.entity().getUUID(), now);
        }
        attend(target, player, now);
    }

    /**
     * Scoring introspection for {@code /conversations chat debug <msg>} (Phase 4, op-gated): runs the
     * real pipeline — targeting, normalization, ranking, gate preview, both decisions — completely
     * <b>read-only</b>: no session writes, no cooldown, no delivery, no {@code selectAnswer}. Safe to
     * run while the feature is live.
     */
    public static List<String> debugScore(ServerPlayer player, String message) {
        if (!McaBridge.isAvailable()) {
            return List.of("MCA is not available; chat mode is inert.");
        }
        MinecraftServer server = player.getServer();
        if (server == null) {
            return List.of("No server.");
        }
        long now = server.overworld().getGameTime();
        double addressedRadius = McaConversationsConfig.COMMON.chatModeAddressedRadius.get();
        List<VillagerCandidate> candidates = VillagerFinder.candidates(player, addressedRadius);
        if (candidates.isEmpty()) {
            return List.of("No MCA villager within " + (int) addressedRadius + " blocks.");
        }

        List<String> names = new ArrayList<>(candidates.size());
        List<Double> lookDots = new ArrayList<>(candidates.size());
        for (VillagerCandidate c : candidates) {
            names.add(c.name());
            lookDots.add(c.lookDot());
        }
        Session existing = ChatModeSession.peek(player.getUUID());
        int stickyIndex = stickyIndex(existing, candidates, now);
        double lookConeCos = lookConeCos(McaConversationsConfig.COMMON.chatModeLookConeDegrees.get());
        Address address = Addressing.resolve(message.strip(), names, lookDots, stickyIndex, lookConeCos,
                reservedNameWords());
        if (address.targetIndex() < 0) {
            return List.of("No target resolved.");
        }
        VillagerCandidate target = candidates.get(address.targetIndex());
        String tier = address.named() ? "1 (named)"
                : address.targetIndex() == stickyIndex ? "2 (sticky)"
                : address.directed() ? "3 (look-at)" : "4 (ambient/nearest)";

        IntentIndex index = ChatIntentLoader.active();
        String currentQuestion = contextFor(existing, target);
        List<String> offered = currentQuestion == null
                ? List.of() : ChatModeSession.currentAnswers(player.getUUID());

        NormalizedMessage normalized = Normalizer.normalize(address.message(), index.synonyms());
        List<Scored> ranked = IntentMatcher.rank(index, normalized, currentQuestion, offered);
        Set<String> eligibleIds = new HashSet<>();
        List<Scored> eligible = new ArrayList<>();
        for (Scored s : ranked) {
            if (GatePreview.eligible(target.entity(), player, s)) {
                eligibleIds.add(s.id());
                eligible.add(s);
            }
        }
        double minScore = McaConversationsConfig.COMMON.chatModeMinScore.get();
        double ambientMinScore = McaConversationsConfig.COMMON.chatModeAmbientMinScore.get();
        Decision directed = IntentMatcher.decide(eligible, true, minScore, ambientMinScore);
        Decision ambient = IntentMatcher.decide(eligible, false, minScore, ambientMinScore);

        List<String> out = new ArrayList<>();
        out.add("target: " + (target.name().isBlank() ? "villager" : target.name())
                + " — tier " + tier + (currentQuestion != null ? " — context " + currentQuestion : ""));
        out.add("stems: " + String.join(" ", normalized.contentStems)
                + (normalized.negatedStems.isEmpty() ? "" : " | negated: " + String.join(" ", normalized.negatedStems)));
        out.addAll(formatRanked(ranked, eligibleIds, 5));
        out.add(String.format("directed(≥%.2f): %s%s — ambient(≥%.2f): %s", minScore,
                directed.outcome(), directed.chosen() != null ? " " + directed.chosen().id() : "",
                ambientMinScore, ambient.outcome()));
        return out;
    }

    /** Pure formatting: top-{@code limit} ranked intents, gate-ineligible ones marked. */
    static List<String> formatRanked(List<Scored> ranked, Set<String> eligibleIds, int limit) {
        if (ranked.isEmpty()) {
            return List.of("no intent scored above zero");
        }
        List<String> out = new ArrayList<>();
        for (int i = 0; i < Math.min(limit, ranked.size()); i++) {
            Scored s = ranked.get(i);
            String binding = s.isSystem() ? "system:" + s.system() : s.question() + "/" + s.answer();
            out.add(String.format("%d. %s %.3f (%s)%s%s", i + 1, s.id(), s.score(), binding,
                    s.contextScoped() ? " [ctx]" : "",
                    eligibleIds.contains(s.id()) ? "" : " [gated]"));
        }
        return out;
    }

    static boolean isOptedIn(ServerPlayer player) {
        // The attachment always resolves, and ChatModePlayerState.isEnabled() already falls back to
        // chatModeDefaultOn when the player has made no explicit choice — so the capability era's
        // orElse(default) is now built into the value itself.
        return ConversationsAttachments.chatMode(player).isEnabled();
    }
}
