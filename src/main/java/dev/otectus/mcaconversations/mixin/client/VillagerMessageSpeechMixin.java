package dev.otectus.mcaconversations.mixin.client;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.client.dialogue.ClientUtteranceLedger;
import dev.otectus.mcaconversations.client.dialogue.dev.ClientDiagnostics;
import dev.otectus.mcaconversations.client.speech.SpeechAssociation;
import dev.otectus.mcaconversations.conversation.UtteranceTag;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.List;

/**
 * Makes MCA's speech say the line that was shown (feedback remediation §2.2, WP-B1), on 1.21.1.
 *
 * <p>1.21.1 MCA's {@code VillagerMessage} is a record of {@link Component}s, so the re-parse that the
 * 1.20.1 {@code VillagerMessageMixin} works around does not exist here (PORT_STATUS.md, Deviation 2):
 * {@code ClientHandlerImpl.handleVillagerMessage} hands the chat log
 * {@code prefix().copy().append(message())} and then gives the same {@code message()} instance to
 * {@code SpeechManager.onChatMessage}. The stale speech association is the same on both lines, though:
 * MCA maps every translatable to the last <em>pooled</em> key it resolved. So this hooks the two calls
 * that method makes, in the order it makes them. Before the chat log decomposes the line, MCA's
 * association key is pointed at the line's own key; before speech reads its map, the pair itself is
 * written. Nothing else changes.
 *
 * <p>Client-only, in the {@code "client"} list. {@code remap = false}: MCA's own class, and the
 * invoke targets are Mojang names, which NeoForge runs. {@code require = 0} (config default): if MCA
 * reshapes the method, both hooks no-op and speech behaves exactly as MCA's does. Nothing here names
 * an MCA type; {@code MixinTargetProbeTest} checks the method and both calls on every 1.21.1 fleet jar.
 */
@Pseudo
@Mixin(targets = "net.conczin.mca.network.ClientHandlerImpl", remap = false)
public abstract class VillagerMessageSpeechMixin {

    @ModifyArg(method = "handleVillagerMessage", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/multiplayer/chat/ChatListener;handleSystemMessage(Lnet/minecraft/network/chat/Component;Z)V"),
            index = 0, require = 0)
    private Component mcaconversations$beforeChat(Component chat) {
        try {
            ClientDiagnostics.fired(ClientDiagnostics.Hook.VILLAGER_MESSAGE);
            SpeechAssociation.beforeChat(mcaconversations$message(chat));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("speech association before chat failed; MCA's behaviour stands", t);
        }
        return chat;
    }

    // Named without its owner on purpose: MCA's SpeechManager is the only onChatMessage(Component, UUID)
    // the method calls (MixinTargetProbeTest checks it), and an owner in slash form would be a static
    // MCA link that NoMcaStaticLinkTest refuses.
    @ModifyArg(method = "handleVillagerMessage", at = @At(value = "INVOKE",
            target = "onChatMessage(Lnet/minecraft/network/chat/Component;Ljava/util/UUID;)V"),
            index = 0, require = 0)
    private Component mcaconversations$beforeSpeech(Component message) {
        try {
            Component spoken = SpeechAssociation.beforeSpeech(message, null);
            mcaconversations$sighted(message);
            return spoken;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("speech association before speech failed; MCA's behaviour stands", t);
            return message;
        }
    }

    /** The chat line is {@code prefix().copy().append(message())}: the message is the last sibling. */
    @Unique
    private static Component mcaconversations$message(Component chat) {
        List<Component> siblings = chat.getSiblings();
        return siblings.isEmpty() ? chat : siblings.get(siblings.size() - 1);
    }

    @Unique
    private static void mcaconversations$sighted(Component message) {
        UtteranceTag.read(message).ifPresent(id -> {
            Minecraft minecraft = Minecraft.getInstance();
            ClientUtteranceLedger.get().record(id, message, ClientUtteranceLedger.Surface.SPEECH,
                    minecraft.player == null ? 0L : minecraft.player.tickCount);
        });
    }
}
