package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.chat.ChatModeDispatcher;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * The mod's (first and only) network registration. One tiny C2S payload,
 * {@link TypingStatusC2S}, sent by the client while the chat screen is open so nearby villagers can
 * turn toward the typing player (chat-mode attention).
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

    private ConversationsNetwork() {
    }

    /** Mod-bus listener; wired up in the {@link McaConversations} constructor. */
    public static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(McaConversations.MOD_ID).versioned(PROTOCOL);
        registrar.playToServer(
                TypingStatusC2S.TYPE,
                TypingStatusC2S.STREAM_CODEC,
                ConversationsNetwork::handleTyping);
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
}
