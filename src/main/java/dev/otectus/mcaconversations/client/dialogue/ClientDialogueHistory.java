package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.network.ChoiceClearS2C;
import net.minecraft.network.chat.Component;

/**
 * The one delivered history this client keeps, and the identities its recording points agree on.
 *
 * <p>Held statically for the same reason {@link ClientChoiceMessages} is: the screen that draws it is
 * created and destroyed several times inside one conversation, and a history that died with the
 * screen would forget the line the player opened it to re-read. It does not outlive the connection —
 * {@link #clear()} runs from the same connection check that resets the offer state.
 */
public final class ClientDialogueHistory {

    private static DeliveredHistory history = new DeliveredHistory(cap());
    private static int builtForCap = cap();

    private ClientDialogueHistory() {
    }

    public static DeliveredHistory get() {
        int wanted = cap();
        if (wanted != builtForCap) {
            // A lowered bound has to take effect on the history that exists, not only on the next
            // one; the simplest honest answer to "keep fewer" is to keep none of the old ones.
            history = new DeliveredHistory(wanted);
            builtForCap = wanted;
        }
        return history;
    }

    public static void clear() {
        get().clear();
    }

    /** Identity of a villager line: one per delivered phrase of one conversation. */
    public static String spokenIdentity(Object conversationId, long phraseRevision) {
        return "spoken:" + conversationId + ":" + phraseRevision;
    }

    /** Identity of a reply: the offer it answered and the answer that was picked. */
    public static String responseIdentity(long revision, int index) {
        return "response:" + revision + ":" + index;
    }

    private static String responsePrefix(long revision) {
        return "response:" + revision + ":";
    }

    public static void spoken(String identity, Component speaker, Component text) {
        get().record(DeliveredLine.spoken(identity, speaker, text));
    }

    public static void submitted(long revision, int index, Component answer) {
        get().record(DeliveredLine.submitted(responseIdentity(revision, index), answer));
    }

    /**
     * Applies a server clear to whatever reply was outstanding for that revision.
     *
     * <p>Only the two things the client is actually told are recorded: the answer ran, or it was
     * refused for a named reason. A silent clear says nothing about the reply, so it leaves the entry
     * as submitted rather than inventing an outcome for it.
     */
    public static void resolved(long revision, ChoiceClearS2C.Reason reason) {
        if (reason == ChoiceClearS2C.Reason.CONSUMED) {
            get().resolveAll(responsePrefix(revision), DeliveredLine.Status.ACCEPTED, null);
        } else if (reason != null && reason.explained()) {
            get().resolveAll(responsePrefix(revision), DeliveredLine.Status.REFUSED, reason);
        }
    }

    private static int cap() {
        try {
            return McaConversationsConfig.CLIENT.deliveredHistoryEntries.get();
        } catch (Throwable ignored) {
            return DeliveredHistory.DEFAULT_CAP;
        }
    }
}
