package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.network.ChoiceClearS2C;
import net.minecraft.network.chat.Component;

/**
 * One thing this client actually received, or actually sent, in a conversation.
 *
 * <p>The entry is written from the resolved component the client was handed, not from a catalog, an
 * episode record or a second lookup in a translation pool: a pooled line resolves to a different
 * sentence every time it is asked for, so a history rebuilt from ids would be a plausible
 * conversation rather than the one that happened.
 *
 * @param kind     whether the villager said it or the player sent it
 * @param identity the delivery this entry is; two records with the same identity are one event, which
 *                 is what keeps a line that appears on both the card and the chat log to one entry
 * @param speaker  who said it, as it was displayed, or empty for the player's own reply
 * @param text     exactly what was displayed
 * @param status   for a reply, how far it got; a received line is always {@link Status#DELIVERED}
 * @param refusal  the reason a refused reply was refused, or null
 */
public record DeliveredLine(Kind kind, String identity, Component speaker, Component text,
                            Status status, ChoiceClearS2C.Reason refusal) {

    public enum Kind {
        /** A villager line this client received and drew. */
        SPOKEN,
        /** A response this client sent. */
        RESPONSE
    }

    public enum Status {
        /** Received and shown. Only ever a {@link Kind#SPOKEN} entry. */
        DELIVERED,
        /** Sent, and not yet answered for. */
        SUBMITTED,
        /** The server ran it. */
        ACCEPTED,
        /** The server refused it, for {@link DeliveredLine#refusal()}. */
        REFUSED
    }

    public DeliveredLine {
        identity = identity == null ? "" : identity;
        speaker = speaker == null ? Component.empty() : speaker;
        text = text == null ? Component.empty() : text;
        status = status == null ? Status.DELIVERED : status;
        if (kind == Kind.SPOKEN) {
            status = Status.DELIVERED;
            refusal = null;
        }
        if (status != Status.REFUSED) {
            refusal = null;
        }
    }

    public static DeliveredLine spoken(String identity, Component speaker, Component text) {
        return new DeliveredLine(Kind.SPOKEN, identity, speaker, text, Status.DELIVERED, null);
    }

    public static DeliveredLine submitted(String identity, Component text) {
        return new DeliveredLine(Kind.RESPONSE, identity, Component.empty(), text,
                Status.SUBMITTED, null);
    }

    /** The same entry with the outcome the server reported for it. */
    public DeliveredLine resolved(Status outcome, ChoiceClearS2C.Reason reason) {
        if (kind != Kind.RESPONSE || status != Status.SUBMITTED) {
            return this;
        }
        return new DeliveredLine(kind, identity, speaker, text, outcome, reason);
    }

    /** The short status word shown beside a reply, or null when there is nothing to qualify. */
    public Component statusLabel() {
        return switch (status) {
            case SUBMITTED -> Component.translatable("gui.mcaconversations.history.status_submitted");
            case ACCEPTED -> Component.translatable("gui.mcaconversations.history.status_accepted");
            case REFUSED -> Component.translatable("gui.mcaconversations.history.status_refused",
                    ClientChoiceMessages.explanation(
                            refusal == null ? ChoiceClearS2C.Reason.EXPIRED : refusal));
            case DELIVERED -> null;
        };
    }
}
