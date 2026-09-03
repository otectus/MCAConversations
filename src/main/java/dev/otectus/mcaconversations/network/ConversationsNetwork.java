package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModeDispatcher;
import dev.otectus.mcaconversations.conversation.ChoiceSelectionService;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.loading.FMLEnvironment;
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
    private static final String PROTOCOL = "2";

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
        try {
            context.enqueueWork(() -> {
                if (FMLEnvironment.dist.isClient()) {
                    dev.otectus.mcaconversations.client.dialogue.ClientChoiceMessages.accept(payload);
                }
            });
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("choice offer handler failed; ignoring", t);
        }
    }

    private static void handleClear(ChoiceClearS2C payload, IPayloadContext context) {
        try {
            context.enqueueWork(() -> {
                if (FMLEnvironment.dist.isClient()) {
                    dev.otectus.mcaconversations.client.dialogue.ClientChoiceMessages.clear(payload);
                }
            });
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("choice clear handler failed; ignoring", t);
        }
    }

    private static void handleSelect(ChoiceSelectC2S payload, IPayloadContext context) {
        try {
            context.enqueueWork(() -> {
                if (context.player() instanceof ServerPlayer sender) {
                    ChoiceSelectionService.select(sender, payload.revision(),
                            payload.absoluteIndex(), payload.villagerId());
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
        PacketDistributor.sendToPlayer(player, new ChoiceClearS2C(revision, reason));
    }

    public static void warnOversizedOffer(String question, int count) {
        if (WARNED_OVERSIZED_OFFERS.size() < 128 && WARNED_OVERSIZED_OFFERS.add(question)) {
            McaConversations.LOGGER.warn(
                    "Question '{}' offered {} answers; numbered synchronization is disabled above {}",
                    question, count, ChoiceOfferS2C.MAX_CHOICES);
        }
    }
}
