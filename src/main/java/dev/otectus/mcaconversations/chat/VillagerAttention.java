package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.AttentionLedger.Hold;
import dev.otectus.mcaconversations.chat.AttentionLedger.Source;
import dev.otectus.mcaconversations.chat.VillagerFinder.VillagerCandidate;
import dev.otectus.mcaconversations.conversation.ConversationDanger;
import dev.otectus.mcaconversations.conversation.ConversationHandle;
import dev.otectus.mcaconversations.conversation.ConversationPresence;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Applies {@link AttentionLedger} holds to live villagers each tick.
 *
 * <p>What a hold does to a body belongs to {@link ConversationMovementController}: this class decides
 * <em>which</em> villagers are held and for whom, and hands each one to the controller to be faced
 * and, when the hold is an exchange rather than a glance, pinned. Works on any vanilla
 * {@code Mob}; MCA villagers extend {@code Villager}, so no MCA API is involved.
 *
 * <p><b>Safety valve:</b> a freshly-hurt or fleeing villager is never pinned — and since 1.7.1 is not
 * merely skipped either. The discussion is revoked through {@link ConversationDanger}, so a hold
 * cannot come back when the hurt animation ends. Sleeping and removed villagers drop their holds.
 * Server thread only.
 */
public final class VillagerAttention {

    /** How long one typing ping holds attention; the client re-pings while the chat screen is open. */
    static final int TYPING_HOLD_TICKS = 60;

    private static final AttentionLedger LEDGER = new AttentionLedger();
    private static final Map<UUID, Entity> VILLAGERS = new ConcurrentHashMap<>();

    private VillagerAttention() {
    }

    /** Requests attention (see {@link AttentionLedger#hold} for the precedence rules). */
    public static void hold(Entity villager, ServerPlayer player, long untilTick, Source source) {
        hold(villager, player, untilTick, source, null);
    }

    /**
     * Requests attention on behalf of an accepted discussion. The hold remembers the handle, so only
     * that discussion's ending can release it (see {@link #releaseIfOwned}).
     */
    public static void hold(Entity villager, ServerPlayer player, long untilTick, Source source,
                            ConversationHandle owner) {
        if (villager == null || player == null) {
            return;
        }
        VILLAGERS.put(villager.getUUID(), villager);
        LEDGER.hold(villager.getUUID(), player.getUUID(), untilTick, source, owner);
    }

    /**
     * Id form of {@link #hold(Entity, ServerPlayer, long, Source)}, for callers that hold ids rather
     * than entities. The entity form registers the live villager first; this one only books the hold,
     * so a villager never seen as an entity is simply never pinned by {@link #tick}.
     */
    public static void hold(UUID villagerId, UUID playerId, long untilTick, Source source) {
        hold(villagerId, playerId, untilTick, source, null);
    }

    /** Id form of the owned hold; see {@link #hold(Entity, ServerPlayer, long, Source, ConversationHandle)}. */
    public static void hold(UUID villagerId, UUID playerId, long untilTick, Source source,
                            ConversationHandle owner) {
        if (villagerId == null || playerId == null) {
            return;
        }
        LEDGER.hold(villagerId, playerId, untilTick, source, owner);
    }

    /** The conversation ended (farewell/mute/shrug): the villager goes back to its day. */
    public static void release(Entity villager) {
        if (villager != null) {
            release(villager.getUUID());
        }
    }

    /** Id form of {@link #release(Entity)} — the villager may already be gone from the world. */
    public static void release(UUID villagerId) {
        if (villagerId != null) {
            LEDGER.release(villagerId);
            VILLAGERS.remove(villagerId);
        }
    }

    /**
     * Releases a villager only when the ending owns its hold: the discussion that booked it, or an
     * unowned hold belonging to the same player. A villager another player has since taken over
     * keeps attending them.
     *
     * @return true when a hold was actually dropped
     */
    public static boolean releaseIfOwned(UUID villagerId, ConversationHandle owner) {
        if (villagerId == null || !LEDGER.releaseIfOwned(villagerId, owner)) {
            return false;
        }
        VILLAGERS.remove(villagerId);
        return true;
    }

    /** Player-token form, for endings that never minted a handle (chat farewells, mutes, logout). */
    public static boolean releaseIfOwned(UUID villagerId, UUID playerId) {
        if (villagerId == null || !LEDGER.releaseIfOwned(villagerId, playerId)) {
            return false;
        }
        VILLAGERS.remove(villagerId);
        return true;
    }

    /** Entity form of {@link #releaseIfOwned(UUID, UUID)} for the chat paths that hold a villager. */
    public static boolean releaseIfOwned(Entity villager, ServerPlayer player) {
        return villager != null && player != null
                && releaseIfOwned(villager.getUUID(), player.getUUID());
    }

    /** Read-only view of the live holds (villager id → hold), for diagnostics and tests. */
    public static Map<UUID, Hold> activeHolds() {
        return java.util.Collections.unmodifiableMap(LEDGER.activeHolds());
    }

    /** A typing ping from {@code player}: nearby villagers glance over until the pings stop. */
    public static void playerTyping(ServerPlayer player, long now) {
        double radius = McaConversationsConfig.chatModeRadius();
        for (VillagerCandidate c : VillagerFinder.candidates(player, radius)) {
            hold(c.entity(), player, now + TYPING_HOLD_TICKS, Source.TYPING);
        }
    }

    /** The player closed the chat screen: drop their TYPING holds (conversations keep attending). */
    public static void playerStoppedTyping(ServerPlayer player) {
        LEDGER.releaseTyping(player.getUUID());
    }

    /** Logout: drop every hold aimed at the player. */
    public static void clearPlayer(UUID playerId) {
        LEDGER.releasePlayer(playerId);
    }

    /**
     * True when a discussion's hold currently owns this villager's movement.
     *
     * <p>The question {@code InteractTaskMovementMixin} asks before cancelling MCA's follow step, and
     * the only thing that mixin knows about this mod. Both halves of the answer matter: a passing
     * glance never owns movement (spec §5.1), and a server that has turned
     * {@code holdVillagerDuringInteraction} off owns none at all — MCA's villager follows the player
     * exactly as it always did.
     */
    public static boolean holdsMovement(UUID villagerId) {
        if (villagerId == null || !McaConversationsConfig.holdVillagerDuringInteraction()) {
            return false;
        }
        Hold hold = LEDGER.activeHolds().get(villagerId);
        return hold != null && hold.ownsMovement();
    }

    /**
     * Server-tick driver for the holds this class owns: sweep expiries, then face (and, for an
     * exchange rather than a glance, pin) each live one.
     *
     * <p><b>Two things changed in 1.7.1.</b> Chat mode being off no longer clears every hold: it ends
     * chat engagements and typing glances, which are the things it created, and leaves a graphical
     * discussion alone — a GUI conversation has never had anything to do with chat mode and must keep
     * working without it (spec §5.1). And a villager in danger is no longer skipped and quietly
     * resumed once the hurt animation ends: the discussion is revoked, which is what stops an
     * attacked villager being recaptured by the conversation they were escaping.
     *
     * <p>Graphical discussions are applied by the lifecycle tick instead of here, because that is
     * where their distance, lease and identity are judged in the same pass — see
     * {@link ConversationMovementController#tickHold}. This method owns the rest: chat engagements,
     * greetings and typing glances.
     */
    public static void tick(MinecraftServer server, long now) {
        if (!McaConversationsConfig.COMMON.enableChatMode.get()) {
            releaseChatHolds();
        }
        LEDGER.sweep(now);
        if (LEDGER.activeHolds().isEmpty()) {
            if (!VILLAGERS.isEmpty()) {
                VILLAGERS.keySet().retainAll(LEDGER.activeHolds().keySet());
            }
            return;
        }

        List<UUID> drop = new ArrayList<>(0);
        List<UUID> revoke = new ArrayList<>(0);
        for (Map.Entry<UUID, Hold> e : LEDGER.activeHolds().entrySet()) {
            try {
                Hold hold = e.getValue();
                if (managed(hold)) {
                    continue; // the lifecycle tick owns this one, chat mode or not
                }
                if (hold.owner() != null) {
                    // A discussion booked it and that discussion is gone: nothing left to attend to.
                    drop.add(e.getKey());
                    continue;
                }
                Entity villager = VILLAGERS.get(e.getKey());
                ServerPlayer player = server.getPlayerList().getPlayer(hold.playerId());
                ConversationMovementController.Stance stance =
                        ConversationMovementController.judge(villager, player, hold.ownsMovement());
                switch (stance) {
                    case DROP -> drop.add(e.getKey());
                    case REVOKE_ATTACKED, REVOKE_DANGER -> revoke.add(e.getKey());
                    case LEAVE -> { /* fleeing, and this server has chosen not to interrupt */ }
                    case FACE, HOLD -> ConversationMovementController.apply(villager, player, stance);
                }
            } catch (Throwable t) {
                McaConversations.LOGGER.debug("villager attention tick failed; dropping hold", t);
                drop.add(e.getKey());
            }
        }
        for (UUID id : revoke) {
            // Ends the discussion, drops the hold and starts the re-open delay. Never a skipped tick:
            // a hold that could resume after hurtTime expired is the bug this replaced.
            ConversationDanger.onAttacked(VILLAGERS.get(id), id, now);
            VILLAGERS.remove(id);
        }
        for (UUID id : drop) {
            LEDGER.release(id);
            VILLAGERS.remove(id);
        }
        VILLAGERS.keySet().retainAll(LEDGER.activeHolds().keySet());
    }

    /**
     * Chat mode is off: drop the holds chat mode created, and keep the ones it never owned.
     *
     * <p>Before 1.7.1 this cleared the ledger outright, which meant a server running graphical
     * conversations with chat mode disabled — a perfectly ordinary configuration — held no villager
     * still for anybody. A typing glance and a chat engagement are chat mode's; a graphical
     * discussion's hold is the lifecycle's, and survives (spec §5.1).
     *
     * @return how many holds were dropped
     */
    public static int releaseChatHolds() {
        int dropped = LEDGER.releaseIf(hold -> !managed(hold));
        if (dropped > 0) {
            VILLAGERS.keySet().retainAll(LEDGER.activeHolds().keySet());
        }
        return dropped;
    }

    /** True when a still-live graphical discussion owns this hold, and so the lifecycle tick does. */
    public static boolean managed(Hold hold) {
        return hold != null && hold.isGuiDiscussion() && ConversationPresence.isCurrent(hold.owner());
    }

    /** Test/server-stop reset. */
    public static void reset() {
        LEDGER.clear();
        VILLAGERS.clear();
    }
}
