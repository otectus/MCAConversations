package dev.otectus.mcaconversations.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Clears one consumed, expired, or otherwise superseded client offer revision, and says why.
 *
 * <p>The reason is what the player is told, so each constant is an explanation somebody can act on
 * rather than a catch-all lapse. Reasons travel as explicit stable identifiers, never as ordinals:
 * inserting a constant must not silently re-label an older packet, and an identifier this build does
 * not know decodes as {@link Reason#NONE} — a silent clear — instead of throwing on the client.
 *
 * <p>Since protocol 4 a clear also names the discussion it retires. Retiring one offer is not ending
 * a conversation, and a clear that arrives after the player has moved on must not be allowed to wipe
 * the card in front of them just because its revision happens to be higher.
 */
public record ChoiceClearS2C(ConversationRef handle, long revision, Reason reason) {

    public enum Reason {
        /** No explanation: the offer simply no longer applies (an empty answer list, an oversized offer). */
        NONE(0, false),
        /** The answer was accepted and run. */
        CONSUMED(1, false),
        /** The offer was not the live one any more. */
        EXPIRED(2, true),
        /** A newer decision replaced this one before it was answered. */
        SUPERSEDED(3, false),
        /** A reload replaced the content this card was written from. */
        CONTENT_RELOADED(4, true),
        /** The villager can no longer hold the conversation. */
        SPEAKER_UNAVAILABLE(5, true),
        /** The pair drifted apart. */
        OUT_OF_RANGE(6, true),
        /** The answer's requirements no longer accept this pair. */
        REQUIREMENTS_CHANGED(7, true),
        /** The action failed part way. Navigation only — nothing is retried or rolled back. */
        EXECUTION_FAILED(8, true);

        private final int id;
        private final boolean explained;

        Reason(int id, boolean explained) {
            this.id = id;
            this.explained = explained;
        }

        public int id() {
            return id;
        }

        /** True when this reason owes the player a sentence and a way out, not a silent dismissal. */
        public boolean explained() {
            return explained;
        }

        public static Reason byId(int id) {
            for (Reason reason : values()) {
                if (reason.id == id) {
                    return reason;
                }
            }
            return NONE;
        }
    }

    public ChoiceClearS2C {
        handle = handle == null ? ConversationRef.NONE : handle;
        reason = reason == null ? Reason.NONE : reason;
    }

    static void encode(ChoiceClearS2C message, FriendlyByteBuf buffer) {
        ConversationRef.write(buffer, message.handle());
        buffer.writeVarLong(message.revision());
        buffer.writeVarInt(message.reason().id());
    }

    static ChoiceClearS2C decode(FriendlyByteBuf buffer) {
        ConversationRef handle = ConversationRef.read(buffer);
        long revision = buffer.readVarLong();
        return new ChoiceClearS2C(handle, revision, Reason.byId(buffer.readVarInt()));
    }

    static void handle(ChoiceClearS2C message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> ConversationsNetwork.sink().clear(message)));
        context.setPacketHandled(true);
    }
}
