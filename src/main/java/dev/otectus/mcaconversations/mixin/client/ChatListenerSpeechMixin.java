package dev.otectus.mcaconversations.mixin.client;

import dev.otectus.mcaconversations.client.speech.ChatModeSpeech;
import net.minecraft.client.multiplayer.chat.ChatListener;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Sees every system message before vanilla files it, so a chat-mode villager line can be handed to
 * MCA's speech once when {@code [speech] chatModeSpeech} is on (feedback remediation WP-B3).
 *
 * <p>Non-cancelling and read-only: the message reaches the chat log exactly as it would without this
 * mod. With the toggle off (the default) the only work done is one tag lookup, which also lets the
 * diagnostics record that the line reached the chat log. Vanilla target: refmap on Forge, mojmap on NeoForge.
 */
@Mixin(ChatListener.class)
public abstract class ChatListenerSpeechMixin {

    @Inject(method = "handleSystemMessage", at = @At("HEAD"), require = 0)
    private void mcaconversations$speakChatModeLine(Component message, boolean overlay, CallbackInfo ci) {
        ChatModeSpeech.onSystemMessage(message, overlay);
    }
}
