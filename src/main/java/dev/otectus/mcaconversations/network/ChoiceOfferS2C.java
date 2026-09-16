package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import io.netty.handler.codec.DecoderException;
import net.minecraft.network.FriendlyByteBuf;
import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * A bounded client projection of the server-owned choice offer.
 *
 * <p>Since protocol 4 it also names the discussion it belongs to. The revision alone could only say
 * which offer this was, never which conversation minted it, so a client had no way to tell an offer
 * for the villager it is looking at from one for the villager it just walked away from.
 */
public record ChoiceOfferS2C(ConversationRef handle, long revision, ConversationSession.Frontend frontend,
                             String questionId, List<String> answerIds) implements CustomPacketPayload {

    public static final int MAX_CHOICES = 64;
    public static final int MAX_ID_LENGTH = 256;

    public ChoiceOfferS2C {
        handle = handle == null ? ConversationRef.NONE : handle;
        frontend = frontend == null ? ConversationSession.Frontend.GUI : frontend;
        questionId = questionId == null ? "" : questionId;
        answerIds = answerIds == null ? List.of() : List.copyOf(answerIds);
        if (questionId.length() > MAX_ID_LENGTH || answerIds.size() > MAX_CHOICES
                || answerIds.stream().anyMatch(id -> id == null || id.length() > MAX_ID_LENGTH)) {
            throw new IllegalArgumentException("choice offer exceeds protocol bounds");
        }
    }

    /** The wire form of a server-side offer, stamped with the discussion it was minted in. */
    public static ChoiceOfferS2C from(ConversationRef handle, ConversationSession.ChoiceOffer offer) {
        return new ChoiceOfferS2C(handle == null ? ConversationRef.ofVillager(offer.villagerId()) : handle,
                offer.revision(), offer.frontend(), offer.questionId(), offer.answerIds());
    }

    static void encode(FriendlyByteBuf buffer, ChoiceOfferS2C message) {
        ConversationRef.write(buffer, message.handle());
        buffer.writeVarLong(message.revision());
        buffer.writeEnum(message.frontend());
        buffer.writeUtf(message.questionId(), MAX_ID_LENGTH);
        buffer.writeVarInt(message.answerIds().size());
        message.answerIds().forEach(id -> buffer.writeUtf(id, MAX_ID_LENGTH));
    }

    static ChoiceOfferS2C decode(FriendlyByteBuf buffer) {
        ConversationRef handle = ConversationRef.read(buffer);
        long revision = buffer.readVarLong();
        ConversationSession.Frontend frontend = buffer.readEnum(ConversationSession.Frontend.class);
        String question = buffer.readUtf(MAX_ID_LENGTH);
        int count = buffer.readVarInt();
        if (count < 0 || count > MAX_CHOICES) {
            throw new DecoderException("invalid synchronized choice count: " + count);
        }
        List<String> answers = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            answers.add(buffer.readUtf(MAX_ID_LENGTH));
        }
        return new ChoiceOfferS2C(handle, revision, frontend, question, answers);
    }


    public static final CustomPacketPayload.Type<ChoiceOfferS2C> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "choice_offer"));
    public static final StreamCodec<FriendlyByteBuf, ChoiceOfferS2C> STREAM_CODEC =
            StreamCodec.of(ChoiceOfferS2C::encode, ChoiceOfferS2C::decode);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() { return TYPE; }
}
