package dev.otectus.mcaconversations.client.speech;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.client.dialogue.ClientUtteranceLedger;
import dev.otectus.mcaconversations.client.dialogue.dev.ClientDiagnostics;
import dev.otectus.mcaconversations.compat.mca.McaHandles;
import dev.otectus.mcaconversations.conversation.UtteranceTag;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.util.Optional;

/**
 * The optional speech for chat-mode lines (feedback remediation WP-B3; {@code [speech]
 * chatModeSpeech}, off by default). Client-only.
 *
 * <p>Chat-mode lines are plain system messages, and nobody speaks them today. With the toggle on, a
 * tagged chat-mode villager line is handed to MCA's own speech once — the same call MCA makes for the
 * lines in its interaction screen, after the same association repair — so it follows MCA's mode
 * exactly: silent with a voice pack, spoken with online TTS. "Once" is keyed by utterance identity,
 * never by text, so a villager may say the same sentence twice and be heard twice.
 *
 * <p>What is never spoken: feedback and options blocks (their tag says so), graphical lines (MCA's own
 * handler already speaks those; their tag names no speaker), untagged lines (nothing to dedupe by),
 * and anything while Townstead's dialogue screen is open, which speaks its own displayed text.
 */
public final class ChatModeSpeech {

    private ChatModeSpeech() {
    }

    /** Vanilla {@code ChatListener.handleSystemMessage} HEAD. Never cancels, never throws. */
    public static void onSystemMessage(Component message, boolean overlay) {
        ClientDiagnostics.fired(ClientDiagnostics.Hook.CHAT_LISTENER);
        if (overlay || message == null) {
            return;
        }
        try {
            Optional<UtteranceTag.Located> located = UtteranceTag.locate(message);
            if (located.isEmpty()) {
                return;
            }
            UtteranceTag.Id id = located.get().id();
            Component carrier = located.get().carrier();
            long tick = clientTick();
            ClientUtteranceLedger ledger = ClientUtteranceLedger.get();
            ledger.record(id, carrier, ClientUtteranceLedger.Surface.CHAT, tick);
            if (!McaConversationsConfig.chatModeSpeech() || !speakable(id) || townsteadScreenOpen()) {
                return;
            }
            if (!ledger.claimSpeech(id, carrier, tick)) {
                return;
            }
            Component spoken = SpeechAssociation.beforeSpeech(carrier, id.speaker());
            McaHandles.Speech.onChatMessage(spoken, id.speaker());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("chat-mode speech hand-off failed; the line is shown, not spoken", t);
        }
    }

    /** A chat-mode villager line: a spoken kind, and a tag that names the villager speaking it. */
    static boolean speakable(UtteranceTag.Id id) {
        return id != null && id.kind().speakable() && id.speaker() != null;
    }

    private static boolean townsteadScreenOpen() {
        Minecraft minecraft = Minecraft.getInstance();
        return minecraft.screen != null
                && minecraft.screen.getClass().getName().endsWith(".RpgDialogueScreen");
    }

    private static long clientTick() {
        Minecraft minecraft = Minecraft.getInstance();
        return minecraft.player == null ? 0L : minecraft.player.tickCount;
    }
}
