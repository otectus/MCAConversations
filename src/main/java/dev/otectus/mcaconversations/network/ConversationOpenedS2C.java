package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Tells the client which discussion the server has just accepted (spec §7, §4.1 step 6).
 *
 * <p>Until this packet existed the client had no name for what it was in. It inferred a conversation
 * from whichever interaction screen happened to be open, which is why state left over from one
 * villager could be shown on the next one's screen: nothing on the client could tell the two apart.
 * Now the server names the discussion when it accepts it, and every later C2S packet quotes that
 * name back.
 *
 * <p>Carries the handle and the frontend only. Lease and distance values are deliberately absent:
 * they are server-authoritative, and the client has no decision to make with them that the server
 * does not re-make itself.
 */
public record ConversationOpenedS2C(ConversationRef handle, ConversationSession.Frontend frontend) {

    public ConversationOpenedS2C {
        handle = handle == null ? ConversationRef.NONE : handle;
        frontend = frontend == null ? ConversationSession.Frontend.GUI : frontend;
    }

    static void encode(ConversationOpenedS2C message, FriendlyByteBuf buffer) {
        ConversationRef.write(buffer, message.handle());
        buffer.writeEnum(message.frontend());
    }

    static ConversationOpenedS2C decode(FriendlyByteBuf buffer) {
        ConversationRef handle = ConversationRef.read(buffer);
        return new ConversationOpenedS2C(handle, buffer.readEnum(ConversationSession.Frontend.class));
    }

    static void handle(ConversationOpenedS2C message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> ConversationsNetwork.sink().opened(message)));
        context.setPacketHandled(true);
    }
}
