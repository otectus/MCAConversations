package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import io.netty.handler.codec.DecoderException;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/** A bounded client projection of the server-owned choice offer. */
public record ChoiceOfferS2C(long revision, ConversationSession.Frontend frontend,
                             String questionId, List<String> answerIds) implements CustomPacketPayload {

    public static final int MAX_CHOICES = 64;
    public static final int MAX_ID_LENGTH = 256;

    public ChoiceOfferS2C {
        frontend = frontend == null ? ConversationSession.Frontend.GUI : frontend;
        questionId = questionId == null ? "" : questionId;
        answerIds = answerIds == null ? List.of() : List.copyOf(answerIds);
        if (questionId.length() > MAX_ID_LENGTH || answerIds.size() > MAX_CHOICES
                || answerIds.stream().anyMatch(id -> id == null || id.length() > MAX_ID_LENGTH)) {
            throw new IllegalArgumentException("choice offer exceeds protocol bounds");
        }
    }

    public static final CustomPacketPayload.Type<ChoiceOfferS2C> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(McaConversations.MOD_ID, "choice_offer"));

    public static final StreamCodec<FriendlyByteBuf, ChoiceOfferS2C> STREAM_CODEC =
            StreamCodec.of(ChoiceOfferS2C::encode, ChoiceOfferS2C::decode);

    public static ChoiceOfferS2C from(ConversationSession.ChoiceOffer offer) {
        return new ChoiceOfferS2C(offer.revision(), offer.frontend(), offer.questionId(), offer.answerIds());
    }

    static void encode(FriendlyByteBuf buffer, ChoiceOfferS2C message) {
        buffer.writeVarLong(message.revision());
        buffer.writeEnum(message.frontend());
        buffer.writeUtf(message.questionId(), MAX_ID_LENGTH);
        buffer.writeVarInt(message.answerIds().size());
        message.answerIds().forEach(id -> buffer.writeUtf(id, MAX_ID_LENGTH));
    }

    static ChoiceOfferS2C decode(FriendlyByteBuf buffer) {
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
        return new ChoiceOfferS2C(revision, frontend, question, answers);
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
