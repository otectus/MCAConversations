package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Transient, server-side chat-mode state: a per-player conversation {@link Session} (stickiness,
 * open sub-question, mute/cooldown bookkeeping) plus the <b>delivery-redirect scope</b> that
 * {@code NetworkHandlerMixin} consults while MCA's {@code selectAnswer} runs.
 *
 * <p>This class is deliberately free of any {@code net.conczin.mca.*} import — the mixin extracts the
 * public packet fields and hands us plain vanilla types, so MCA stays isolated behind {@code compat/}
 * and {@code mixin/}. Sessions are not persisted (a relog loses only the sticky pointer, matching how
 * closing a GUI screen behaves); long-term consequences live in MCA memories and this mod's SavedData.
 */
public final class ChatModeSession {

    /**
     * Per-player <b>chat-specific</b> state. All time fields are overworld game-time ticks.
     *
     * <p>The open question and its offered answers deliberately do <em>not</em> live here: since
     * 1.1.0 they belong to the shared {@link dev.otectus.mcaconversations.conversation.ConversationSession},
     * so the GUI and chat mode cannot disagree about what the player was just asked. What remains here
     * is what only chat has: the sticky target, the anti-spam anchors, the miss ladder and the mutes.
     */
    public static final class Session {
        public final UUID playerId;
        public UUID villagerId;              // sticky target
        public long lastExchangeGameTime;    // stickiness window anchor
        public long lastProcessedGameTime;   // anti-spam floor anchor (any processed message, incl. misses)
        public int consecutiveMisses;        // graduated confused/hint/shrug ladder (§11)

        Session(UUID playerId) {
            this.playerId = playerId;
        }

        /** The question this player was last offered, or null. Shared with the GUI frontend. */
        public String currentQuestion() {
            return ChatModeSession.currentQuestion(playerId);
        }

        /** The constraint-filtered answers that came with it; empty when there is no open question. */
        public List<String> currentAnswers() {
            return ChatModeSession.currentAnswers(playerId);
        }

        /** Drops the open question without ending the conversation. */
        public void clearQuestion() {
            ChatModeSession.clearQuestion(playerId);
        }

        /** "Stop talking" mutes, per villager→player pairing (spec §11) — never the whole session. */
        private final Map<UUID, Long> mutedUntil = new ConcurrentHashMap<>();

        /** True while this villager is muted for the player. Expired entries are dropped lazily. */
        public boolean isMuted(UUID villagerId, long now) {
            Long until = mutedUntil.get(villagerId);
            if (until == null) {
                return false;
            }
            if (now >= until) {
                mutedUntil.remove(villagerId);
                return false;
            }
            return true;
        }

        public void mute(UUID villagerId, long untilGameTime) {
            mutedUntil.put(villagerId, untilGameTime);
        }
    }

    private static final Map<UUID, Session> SESSIONS = new ConcurrentHashMap<>();

    /** True once the redirect mixin has been observed running (proves it applied against this MCA). */
    private static volatile boolean redirectInstalled = false;

    /** The open redirect scope, or null. Read/written on the server thread only (see {@link #open}). */
    private static Scope activeScope;

    private ChatModeSession() {
    }

    // --- Sessions -------------------------------------------------------------

    public static Session get(UUID playerId) {
        return SESSIONS.computeIfAbsent(playerId, Session::new);
    }

    /** The question this player was last offered by either frontend, or null. */
    public static String currentQuestion(UUID playerId) {
        return ConversationSessions.raw(playerId).map(ConversationSession::currentQuestion).orElse(null);
    }

    /** The answers offered with it; empty when there is no open question. */
    public static List<String> currentAnswers(UUID playerId) {
        return ConversationSessions.raw(playerId).map(ConversationSession::currentAnswers).orElse(List.of());
    }

    /** Drops the open question — a subject change, "never mind", a farewell, or a stale sticky target. */
    public static void clearQuestion(UUID playerId) {
        ConversationSessions.raw(playerId).ifPresent(ConversationSession::clearOffer);
    }

    /** Peek without creating — for read-only checks. */
    public static Session peek(UUID playerId) {
        return SESSIONS.get(playerId);
    }

    /** Records a successful exchange so the villager stays the sticky default target. */
    public static void recordExchange(UUID playerId, UUID villagerId, long gameTime) {
        Session s = get(playerId);
        s.villagerId = villagerId;
        s.lastExchangeGameTime = gameTime;
        s.consecutiveMisses = 0;
    }

    /** Drops a player's session (logout/death), including the shared conversation session. */
    public static void clear(UUID playerId) {
        SESSIONS.remove(playerId);
        ConversationSessions.clear(playerId);
    }

    // --- Ambient per-villager rate limit (spec §12) ---------------------------

    private static final Map<UUID, Long> LAST_AMBIENT = new ConcurrentHashMap<>();
    private static final long AMBIENT_PRUNE_AGE = 12000L; // ~10 min: drop stale villager entries

    /** Game-time of a villager's last ambient reply, or null. */
    public static Long lastAmbient(UUID villagerId) {
        return LAST_AMBIENT.get(villagerId);
    }

    /** Marks a villager as having just answered an ambient message; opportunistically prunes old rows. */
    public static void markAmbient(UUID villagerId, long gameTime) {
        LAST_AMBIENT.put(villagerId, gameTime);
        if (LAST_AMBIENT.size() > 256) {
            LAST_AMBIENT.entrySet().removeIf(e -> gameTime - e.getValue() > AMBIENT_PRUNE_AGE);
        }
    }

    // --- Redirect scope -------------------------------------------------------

    /**
     * Opens a delivery-redirect scope for {@code player}'s exchange with {@code villager}. While open,
     * dialogue-response packets MCA sends to this player are converted to chat instead of the GUI.
     * <b>Must be used with try-with-resources</b> so it always closes, even if a dialogue action throws.
     */
    public static Scope open(ServerPlayer player, Entity villager) {
        return open(player, villager, 0);
    }

    /**
     * As {@link #open(ServerPlayer, Entity)} but the redirected line is delivered with an extra tick
     * offset on top of the humanized reply delay — used to stagger ambient multi-responder replies
     * (spec §12.3) so a crowd doesn't all answer in the same instant.
     */
    public static Scope open(ServerPlayer player, Entity villager, int extraDelayTicks) {
        assertServerThread(player);
        Scope scope = new Scope(player, villager, extraDelayTicks, activeScope);
        activeScope = scope;
        return scope;
    }

    /** True when a redirect scope is currently open for exactly this player (server thread). */
    public static boolean activeFor(ServerPlayer player) {
        Scope s = activeScope;
        return s != null && s.player == player;
    }

    /** Villager owned by the active redirect scope, for frontend-aware offer recording. */
    public static UUID activeVillagerId(ServerPlayer player) {
        Scope s = activeScope;
        return s != null && s.player == player ? s.villager.getUUID() : null;
    }

    /** True once {@code NetworkHandlerMixin} has been observed running at least once. */
    public static boolean redirectionAvailable() {
        return redirectInstalled;
    }

    /** Called from the mixin HEAD on every {@code sendToPlayer}, proving the injection applied. */
    public static void markRedirectInstalled() {
        if (!redirectInstalled) {
            redirectInstalled = true;
        }
    }

    /**
     * Mixin hook for MCA's villager-line packet ({@code InteractionDialogueQuestionResponse}). When a
     * scope is open for this player, the (already fully personality-resolved) line is delivered to chat
     * and the caller cancels the GUI packet. Returns true iff consumed.
     */
    public static boolean deliverQuestion(ServerPlayer player, MutableComponent line, boolean silent) {
        Scope s = activeScope;
        if (s == null || s.player != player) {
            return false;
        }
        if (!silent && line != null) {
            // The scope rides along so heart feedback (set after selectAnswer returns, but before the
            // deferred delivery fires) can be appended at delivery time.
            ChatDelivery.villagerSays(s.villager, player, line, s.extraDelayTicks, s);
        }
        return true;
    }

    /**
     * Mixin hook for MCA's question-prompt packet ({@code InteractionDialogueResponse}): true when a
     * redirect scope is open for this player, so the caller cancels the GUI packet — chat mode never
     * renders menus. The question and its answers are recorded by the caller into
     * {@link dev.otectus.mcaconversations.conversation.ConversationSessions}, which both frontends
     * share, so this only decides delivery.
     */
    public static boolean swallowDialogue(ServerPlayer player) {
        Scope s = activeScope;
        return s != null && s.player == player;
    }

    /** Mixin hook for the heart-impact analysis strip ({@code AnalysisResults}): swallowed silently. */
    public static boolean swallowAnalysis(ServerPlayer player) {
        Scope s = activeScope;
        return s != null && s.player == player;
    }

    private static void assertServerThread(ServerPlayer player) {
        MinecraftServer server = player.getServer();
        if (server != null && !server.isSameThread()) {
            McaConversations.LOGGER.error(
                    "ChatModeSession.open called off the server thread — chat mode pipeline must run on the main thread");
        }
    }

    /** An open redirect scope. Restores the previous scope on close, so nesting is safe. */
    public static final class Scope implements AutoCloseable {
        final ServerPlayer player;
        final Entity villager;
        final int extraDelayTicks;
        /**
         * Hearts gained/lost by the exchange ({@code chatModeShowHeartChanges}); written after
         * {@code selectAnswer} returns, read by the deferred delivery closure. 0 = no feedback.
         */
        public int heartsDelta;
        /**
         * The numbered choices to show under the villager's line, when the exchange left a decision
         * open. Written after {@code selectAnswer} returns (the redirect mixin records the new offer
         * during it) and read by the deferred delivery closure.
         */
        public net.minecraft.network.chat.Component options;
        private final Scope previous;

        private Scope(ServerPlayer player, Entity villager, int extraDelayTicks, Scope previous) {
            this.player = player;
            this.villager = villager;
            this.extraDelayTicks = extraDelayTicks;
            this.previous = previous;
        }

        @Override
        public void close() {
            activeScope = previous;
        }
    }
}
