package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModeDispatcher;
import dev.otectus.mcaconversations.conversation.ChoiceSelectionService;
import dev.otectus.mcaconversations.conversation.CloseReason;
import dev.otectus.mcaconversations.conversation.ConversationLifecycle;
import dev.otectus.mcaconversations.conversation.ConversationPresence;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * MCA: Conversations' strict client/server channel. Choice payloads carry only a revision and an
 * index; question and answer ids remain server-owned.
 *
 * <p>This is chat mode's one deviation from the "no new client code/packets" posture: typing state
 * simply does not exist server-side. The mod is already required on both sides (MCA dependency), so
 * strict protocol compatibility is correct — a 1.20 client cannot reach a 1.21 server anyway, and
 * no legacy decoder is needed.
 *
 * <p>Registration must happen from a {@code RegisterPayloadHandlersEvent} listener on the mod bus.
 * The 1.20.1 build registered its {@code SimpleChannel} straight from the mod constructor; doing the
 * equivalent late on NeoForge throws.
 */
public final class ConversationsNetwork {

    /**
     * Bumped from the Forge channel's {@code "1"}: the payload id, encoding and framing all changed
     * with the loader, so nothing on the old protocol could have talked to this anyway.
     */
    public static final String PROTOCOL = NetworkProtocol.version();

    private static volatile ChoicePacketSink sink = ChoicePacketSink.NONE;

    public static void installSink(ChoicePacketSink incoming) {
        sink = incoming == null ? ChoicePacketSink.NONE : incoming;
    }

    static ChoicePacketSink sink() { return sink; }

    private static final java.util.Set<String> WARNED_OVERSIZED_OFFERS =
            java.util.concurrent.ConcurrentHashMap.newKeySet();

    private ConversationsNetwork() {
    }

    /** Mod-bus listener; wired up in the {@link McaConversations} constructor. */
    public static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(McaConversations.MOD_ID).versioned(PROTOCOL);
        registrar.playToServer(
                TypingStatusC2S.TYPE,
                TypingStatusC2S.STREAM_CODEC,
                ConversationsNetwork::handleTyping);
        registrar.playToClient(
                ChoiceOfferS2C.TYPE,
                ChoiceOfferS2C.STREAM_CODEC,
                ConversationsNetwork::handleOffer);
        registrar.playToClient(
                ChoiceClearS2C.TYPE,
                ChoiceClearS2C.STREAM_CODEC,
                ConversationsNetwork::handleClear);
        registrar.playToServer(
                ChoiceSelectC2S.TYPE,
                ChoiceSelectC2S.STREAM_CODEC,
                ConversationsNetwork::handleSelect);
        registrar.playToServer(ChoiceReturnC2S.TYPE, ChoiceReturnC2S.STREAM_CODEC,
                ConversationsNetwork::handleReturn);
        // Protocol 4: the discussion lifecycle itself. The registrar is versioned and the version is
        // an exact match, so an older client is refused at handshake rather than arriving here.
        registrar.playToClient(ConversationOpenedS2C.TYPE, ConversationOpenedS2C.STREAM_CODEC,
                ConversationsNetwork::handleOpened);
        registrar.playToClient(ConversationClosedS2C.TYPE, ConversationClosedS2C.STREAM_CODEC,
                ConversationsNetwork::handleClosed);
        registrar.playToServer(ConversationPresenceC2S.TYPE, ConversationPresenceC2S.STREAM_CODEC,
                ConversationsNetwork::handlePresence);
        registrar.playToServer(ConversationCloseC2S.TYPE, ConversationCloseC2S.STREAM_CODEC,
                ConversationsNetwork::handleClose);
    }

    private static void handleOpened(ConversationOpenedS2C payload, IPayloadContext context) {
        context.enqueueWork(() -> sink.opened(payload));
    }

    private static void handleClosed(ConversationClosedS2C payload, IPayloadContext context) {
        context.enqueueWork(() -> sink.closed(payload));
    }

    private static void handlePresence(ConversationPresenceC2S payload, IPayloadContext context) {
        try {
            context.enqueueWork(() -> {
                if (context.player() instanceof ServerPlayer sender) {
                    ConversationPresence.heartbeat(sender.getUUID(), payload.handle().sessionId(),
                            payload.handle().villagerId(), sender.level().getGameTime());
                }
            });
        } catch (Throwable t) {
            // A lost heartbeat costs one cadence, never a disconnect.
            McaConversations.LOGGER.debug("conversation presence handler failed; ignoring", t);
        }
    }

    private static void handleClose(ConversationCloseC2S payload, IPayloadContext context) {
        try {
            context.enqueueWork(() -> {
                if (context.player() instanceof ServerPlayer sender) {
                    ConversationLifecycle.terminateIfCurrent(sender.getUUID(),
                            payload.handle().sessionId(), payload.handle().villagerId(),
                            CloseReason.CLIENT_CLOSED);
                }
            });
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("conversation close handler failed; ignoring", t);
        }
    }

    /** The wire name of the discussion this player is in right now, or {@link ConversationRef#NONE}. */
    public static ConversationRef refFor(ServerPlayer player) {
        return player == null ? ConversationRef.NONE
                : ConversationRef.of(ConversationPresence.ofPlayer(player.getUUID()).orElse(null));
    }

    /** Announces an accepted discussion to the one player who is in it. */
    public static void sendOpened(ServerPlayer player, ConversationRef handle,
                                  ConversationSession.Frontend frontend) {
        if (player == null || player.hasDisconnected()) {
            return;
        }
        PacketDistributor.sendToPlayer(player, new ConversationOpenedS2C(handle, frontend));
    }

    /**
     * Announces one discussion's ending. Best effort by design: a client that never receives this
     * loses nothing the server is waiting on, because no villager is held pending an acknowledgement.
     */
    public static void sendClosed(ServerPlayer player, ConversationRef handle, CloseReason reason) {
        if (player == null || player.hasDisconnected()) {
            return;
        }
        PacketDistributor.sendToPlayer(player, new ConversationClosedS2C(handle, reason));
    }

    /**
     * NeoForge runs payload handlers on the main thread by default, which is the same guarantee the
     * old {@code ctx.enqueueWork(...)} wrapper provided.
     *
     * <p>Kept defensive on purpose: an exception escaping a NeoForge payload handler disconnects the
     * player, where the Forge {@code SimpleChannel} merely logged. A failed typing ping is never
     * worth kicking someone out of the game over.
     */
    private static void handleTyping(TypingStatusC2S payload, IPayloadContext context) {
        try {
            if (context.player() instanceof ServerPlayer sender) {
                ChatModeDispatcher.onTypingStatus(sender, payload.typing());
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("typing status handler failed; ignoring", t);
        }
    }

    private static void handleOffer(ChoiceOfferS2C payload, IPayloadContext context) {
        context.enqueueWork(() -> sink.offer(payload));
    }

    private static void handleClear(ChoiceClearS2C payload, IPayloadContext context) {
        context.enqueueWork(() -> sink.clear(payload));
    }

    private static void handleReturn(ChoiceReturnC2S payload, IPayloadContext context) {
        if (context.player() instanceof ServerPlayer sender) {
            ChoiceSelectionService.returnToTopics(sender, payload.handle().sessionId(),
                    payload.revision(), payload.villagerId());
        }
    }

    private static void handleSelect(ChoiceSelectC2S payload, IPayloadContext context) {
        try {
            context.enqueueWork(() -> {
                if (context.player() instanceof ServerPlayer sender) {
                    ChoiceSelectionService.select(sender, payload.handle().sessionId(),
                            payload.revision(), payload.absoluteIndex(), payload.villagerId());
                }
            });
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("choice select handler failed; ignoring", t);
        }
    }

    public static void sendOffer(ServerPlayer player, ChoiceOfferS2C offer) {
        PacketDistributor.sendToPlayer(player, offer);
    }

    public static void clearOffer(ServerPlayer player, long revision, ChoiceClearS2C.Reason reason) {
        clearOffer(player, refFor(player), revision, reason);
    }

    /**
     * As above for a clear that belongs to a <em>named</em> discussion rather than the current one.
     *
     * <p>Refusing a straggler has to be addressed to the discussion the straggler came from: sending
     * it under the live handle would tell the client to retire the card the player is looking at,
     * which is exactly the bug the identity work exists to remove.
     */
    public static void clearOffer(ServerPlayer player, ConversationRef handle, long revision,
                                  ChoiceClearS2C.Reason reason) {
        if (player == null || player.hasDisconnected()) {
            return;
        }
        PacketDistributor.sendToPlayer(player, new ChoiceClearS2C(handle, revision, reason));
    }

    public static void warnOversizedOffer(String question, int count) {
        if (WARNED_OVERSIZED_OFFERS.size() < 128 && WARNED_OVERSIZED_OFFERS.add(question)) {
            McaConversations.LOGGER.warn(
                    "Question '{}' offered {} answers; numbered synchronization is disabled above {}",
                    question, count, ChoiceOfferS2C.MAX_CHOICES);
        }
    }
}
