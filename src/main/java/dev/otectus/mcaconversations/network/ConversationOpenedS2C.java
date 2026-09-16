package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Tells the client which discussion the server has just accepted (spec §7, §4.1 step 6).
 *
 * <p>Until this payload existed the client had no name for what it was in. It inferred a
 * conversation from whichever interaction screen happened to be open, which is why state left over
 * from one villager could be shown on the next one's screen: nothing on the client could tell the
 * two apart. Now the server names the discussion when it accepts it, and every later C2S payload
 * quotes that name back.
 *
 * <p>Carries the handle and the frontend only. Lease and distance values are deliberately absent:
 * they are server-authoritative, and the client has no decision to make with them that the server
 * does not re-make itself.
 */
public record ConversationOpenedS2C(ConversationRef handle, ConversationSession.Frontend frontend)
        implements CustomPacketPayload {

    public ConversationOpenedS2C {
        handle = handle == null ? ConversationRef.NONE : handle;
        frontend = frontend == null ? ConversationSession.Frontend.GUI : frontend;
    }

    public static final CustomPacketPayload.Type<ConversationOpenedS2C> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "conversation_opened"));
    public static final StreamCodec<FriendlyByteBuf, ConversationOpenedS2C> STREAM_CODEC =
            StreamCodec.of(ConversationOpenedS2C::encode, ConversationOpenedS2C::decode);

    static void encode(FriendlyByteBuf buffer, ConversationOpenedS2C message) {
        ConversationRef.write(buffer, message.handle());
        buffer.writeEnum(message.frontend());
    }

    static ConversationOpenedS2C decode(FriendlyByteBuf buffer) {
        ConversationRef handle = ConversationRef.read(buffer);
        return new ConversationOpenedS2C(handle, buffer.readEnum(ConversationSession.Frontend.class));
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() { return TYPE; }
}
