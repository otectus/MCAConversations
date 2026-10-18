package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.conversation.ConversationSession;
import io.netty.handler.codec.DecoderException;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * A bounded client projection of the server-owned choice offer.
 *
 * <p>Since protocol 4 it also names the discussion it belongs to. The revision alone could only say
 * which offer this was, never which conversation minted it, so a client had no way to tell an offer
 * for the villager it is looking at from one for the villager it just walked away from.
 *
 * <p>Since protocol 5 it also names the <em>line</em> it belongs to: {@code utteranceSeq} is the
 * sequence number of the last utterance the discussion delivered before this offer was sent (see
 * {@code conversation.UtteranceTag}), or {@code -1} when it makes no claim. The client keeps the card
 * from being answered until that line is the one on screen.
 */
public record ChoiceOfferS2C(ConversationRef handle, long revision, ConversationSession.Frontend frontend,
                             String questionId, List<String> answerIds, long utteranceSeq) {

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
        if (utteranceSeq < -1L) {
            throw new IllegalArgumentException("utterance sequence below -1: " + utteranceSeq);
        }
    }

    /** An offer that names no line. */
    public ChoiceOfferS2C(ConversationRef handle, long revision, ConversationSession.Frontend frontend,
                          String questionId, List<String> answerIds) {
        this(handle, revision, frontend, questionId, answerIds, -1L);
    }

    /** The wire form of a server-side offer, stamped with the discussion it was minted in. */
    public static ChoiceOfferS2C from(ConversationRef handle, ConversationSession.ChoiceOffer offer) {
        return from(handle, offer, -1L);
    }

    /** As above, naming the line the offer belongs to. */
    public static ChoiceOfferS2C from(ConversationRef handle, ConversationSession.ChoiceOffer offer,
                                      long utteranceSeq) {
        return new ChoiceOfferS2C(handle == null ? ConversationRef.ofVillager(offer.villagerId()) : handle,
                offer.revision(), offer.frontend(), offer.questionId(), offer.answerIds(),
                Math.max(-1L, utteranceSeq));
    }

    static void encode(ChoiceOfferS2C message, FriendlyByteBuf buffer) {
        ConversationRef.write(buffer, message.handle());
        buffer.writeVarLong(message.revision());
        buffer.writeEnum(message.frontend());
        buffer.writeUtf(message.questionId(), MAX_ID_LENGTH);
        buffer.writeVarInt(message.answerIds().size());
        message.answerIds().forEach(id -> buffer.writeUtf(id, MAX_ID_LENGTH));
        // Shifted by one so the common "no claim" costs one byte rather than a ten-byte -1.
        buffer.writeVarLong(message.utteranceSeq() + 1L);
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
        long utteranceSeq = buffer.readVarLong() - 1L;
        if (utteranceSeq < -1L) {
            throw new DecoderException("invalid utterance sequence: " + utteranceSeq);
        }
        return new ChoiceOfferS2C(handle, revision, frontend, question, answers, utteranceSeq);
    }

    static void handle(ChoiceOfferS2C message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> ConversationsNetwork.sink().offer(message)));
        context.setPacketHandled(true);
    }
}
