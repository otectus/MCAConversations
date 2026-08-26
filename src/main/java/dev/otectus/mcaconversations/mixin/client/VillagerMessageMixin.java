package dev.otectus.mcaconversations.mixin.client;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

/**
 * Makes one villager utterance resolve to one sentence on every surface it appears on.
 *
 * <p><b>The bug.</b> MCA picks a pooled {@code /N} dialogue variant <em>on the client, at random, on
 * every {@code Language.getOrDefault} call</em> ({@code MixinTranslationStorage.mca$onGet} →
 * {@code PooledTranslationStorage.get} → {@code PoolUtil.pickOne}, a bare {@code nextInt} with no
 * memoisation). A {@link net.minecraft.network.chat.Component} caches its own decomposition, so one
 * <em>instance</em> is stable — but {@code InteractScreen.setLastPhrase} renders a single line from
 * three different instances:
 *
 * <pre>{@code
 * setLastPhrase(phrase, silent)
 *   Messenger.sendChatMessage(phrase, player)
 *     new VillagerMessage(name, phrase, uuid)   // Component.Serializer.toJson(phrase)
 *     handleVillagerMessage(msg)
 *       msg.getMessage()  -> fromJson(..)       // NEW instance -> chat HUD   [draw 1]
 *       msg.getContent()  -> fromJson(..)       // NEW instance -> TTS        [draw 3]
 *     return name.append(phrase)                // ORIGINAL      -> GUI panel [draw 2]
 * }</pre>
 *
 * Three parses, three independent draws, so the dialogue screen and the chat log routinely show
 * different sentences for the same reply. {@code DialogueType.applyFallback} widens it further — its
 * profession branch flips {@code random.nextBoolean()} per resolution, so the surfaces can land in
 * different <em>pools</em>, not merely different variants.
 *
 * <p><b>The fix.</b> Keep the components the constructor was handed and serve them back from the two
 * getters, so all three surfaces share one instance and therefore one draw. The prefix is
 * {@link MutableComponent#copy() copied} before appending because MCA mutates that same instance
 * afterwards ({@code return name.append(message)}) — appending to it here would double the line.
 *
 * <p><b>Why this is safe.</b> {@code VillagerMessage} is S2C only. On the network path (MCA's own
 * villager chatter) the stash arrives {@code null} — MCA serialises messages with
 * {@code ObjectOutputStream} and {@code transient} fields are excluded from both the stream and the
 * class descriptor — so {@code fromJson} runs exactly as it does today and nothing changes. Only the
 * {@code InteractScreen} path, where the message is built and consumed in the same client JVM with no
 * serialisation in between, sees a live stash. {@code VillagerMessage} declares an explicit
 * {@code serialVersionUID}, so a client carrying this mixin still interoperates with an unmodified MCA
 * server.
 *
 * <p>Client-only by construction (the GUI is the only caller that hits the local path) and declared in
 * the {@code "client"} section of the mixin config. {@code remap = false}: MCA's own class.
 * {@code require = 0} (config default): if MCA ever reshapes this class the injections silently no-op
 * and lines behave exactly as they do without this mod.
 *
 * <p><b>Two targets, one jar</b> — see {@code NetworkHandlerMixin} for why both MCA package roots are
 * listed. No {@code @Coerce} is needed: the constructor takes two {@code MutableComponent}s and a
 * {@code UUID}, and both getters return {@code MutableComponent}, so nothing here names an MCA type.
 */
@Pseudo
@Mixin(targets = {
        "forge.net.mca.network.s2c.VillagerMessage",
        "forge.net.conczin.mca.network.s2c.VillagerMessage",
}, remap = false)
public abstract class VillagerMessageMixin {

    @Unique
    private transient MutableComponent mcaconversations$prefix;

    @Unique
    private transient MutableComponent mcaconversations$message;

    @Inject(method = "<init>", at = @At("RETURN"), require = 0)
    private void mcaconversations$keepOriginals(MutableComponent prefix, MutableComponent message,
                                                UUID uuid, CallbackInfo ci) {
        this.mcaconversations$prefix = prefix;
        this.mcaconversations$message = message;
    }

    @Inject(method = "getMessage", at = @At("HEAD"), cancellable = true, require = 0)
    private void mcaconversations$reuseResolvedMessage(CallbackInfoReturnable<MutableComponent> cir) {
        try {
            if (mcaconversations$prefix == null || mcaconversations$message == null) {
                return; // arrived over the wire: no stash, MCA's own fromJson path stands.
            }
            // copy(): MCA appends the message to this very prefix instance after we return.
            cir.setReturnValue(mcaconversations$prefix.copy().append(mcaconversations$message));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("villager-message reuse failed; falling back to re-parse", t);
        }
    }

    @Inject(method = "getContent", at = @At("HEAD"), cancellable = true, require = 0)
    private void mcaconversations$reuseResolvedContent(CallbackInfoReturnable<MutableComponent> cir) {
        try {
            if (mcaconversations$message != null) {
                cir.setReturnValue(mcaconversations$message);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("villager-content reuse failed; falling back to re-parse", t);
        }
    }
}
