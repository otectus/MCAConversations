package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversations;
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
 * <p>This class is deliberately free of any {@code forge.net.mca.*} import — the mixin extracts the
 * public packet fields and hands us plain vanilla types, so MCA stays isolated behind {@code compat/}
 * and {@code mixin/}. Sessions are not persisted (a relog loses only the sticky pointer, matching how
 * closing a GUI screen behaves); long-term consequences live in MCA memories and this mod's SavedData.
 */
public final class ChatModeSession {

    /** Per-player conversation state. All time fields are overworld game-time ticks. */
    public static final class Session {
        public UUID villagerId;              // sticky target
        public long lastExchangeGameTime;    // stickiness window anchor
        public long lastProcessedGameTime;   // anti-spam floor anchor (any processed message, incl. misses)
        public String currentQuestion;       // open sub-question (set by the redirected dialogue packet)
        public List<String> currentAnswers;  // constraint-filtered answers captured from the same packet
        public int consecutiveMisses;        // graduated confused/hint/shrug ladder (§11)
        public long mutedUntilGameTime;      // "stop talking" / villager unavailable
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
        return SESSIONS.computeIfAbsent(playerId, id -> new Session());
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

    /** Drops a player's session (logout/death). */
    public static void clear(UUID playerId) {
        SESSIONS.remove(playerId);
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
     * Mixin hook for MCA's question-prompt packet ({@code InteractionDialogueResponse}): records the
     * open question id and its constraint-filtered answers on the session for follow-up matching, then
     * the caller cancels (chat mode never renders menus). Returns true iff consumed.
     */
    public static boolean recordDialogue(ServerPlayer player, String question, List<String> answers) {
        Scope s = activeScope;
        if (s == null || s.player != player) {
            return false;
        }
        Session sess = get(player.getUUID());
        sess.currentQuestion = question;
        sess.currentAnswers = answers;
        return true;
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
