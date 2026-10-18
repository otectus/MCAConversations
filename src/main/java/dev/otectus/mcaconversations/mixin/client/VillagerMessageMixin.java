package dev.otectus.mcaconversations.mixin.client;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.client.dialogue.ClientUtteranceLedger;
import dev.otectus.mcaconversations.client.dialogue.dev.ClientDiagnostics;
import dev.otectus.mcaconversations.client.speech.SpeechAssociation;
import dev.otectus.mcaconversations.conversation.UtteranceTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

/**
 * Makes one villager utterance resolve to one sentence on every surface it appears on, and makes
 * MCA's speech say that sentence.
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
 * <p><b>The network path too</b> (since 1.8.0). MCA's own villager chatter arrives from the server
 * serialised with {@code ObjectOutputStream}, which skips the {@code transient} stash, so it used to
 * re-parse twice: chat and speech could disagree. Now the first getter parses the target's own
 * {@code prefix}/{@code message} JSON once, exactly as MCA's {@code safeLoadFromJson} would, and both
 * getters serve those instances. {@code VillagerMessage} declares an explicit
 * {@code serialVersionUID}, so a client carrying this mixin still interoperates with an unmodified MCA
 * server.
 *
 * <p><b>And speech.</b> Before either instance leaves, {@link SpeechAssociation} points MCA's speech
 * association at the line's own key — MCA would otherwise speak whichever pooled line resolved last.
 * The ledger records which surfaces saw which utterance, for the agreement rule and the diagnostics.
 *
 * <p>Client-only and declared in the {@code "client"} section of the mixin config. {@code remap =
 * false}: MCA's own class. {@code require = 0} (config default): if MCA reshapes the getters the
 * injections silently no-op and lines behave exactly as they do without this mod. The two shadowed
 * fields are declared on {@code VillagerMessage} itself in every fleet build, which
 * {@code MixinTargetProbeTest} checks strictly — a rename fails the build, not the game.
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

    @Shadow(remap = false)
    @Final
    private String prefix;

    @Shadow(remap = false)
    @Final
    private String message;

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
            ClientDiagnostics.fired(ClientDiagnostics.Hook.VILLAGER_MESSAGE);
            if (!mcaconversations$ensureParsed()) {
                return; // MCA's own fromJson path stands.
            }
            SpeechAssociation.beforeChat(mcaconversations$message);
            mcaconversations$sighted(ClientUtteranceLedger.Surface.CHAT);
            // copy(): MCA appends the message to this very prefix instance after we return.
            cir.setReturnValue(mcaconversations$prefix.copy().append(mcaconversations$message));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("villager-message reuse failed; falling back to re-parse", t);
        }
    }

    @Inject(method = "getContent", at = @At("HEAD"), cancellable = true, require = 0)
    private void mcaconversations$reuseResolvedContent(CallbackInfoReturnable<MutableComponent> cir) {
        try {
            if (!mcaconversations$ensureParsed()) {
                return;
            }
            Component spoken = SpeechAssociation.beforeSpeech(mcaconversations$message, null);
            mcaconversations$sighted(ClientUtteranceLedger.Surface.SPEECH);
            cir.setReturnValue(spoken instanceof MutableComponent mutable ? mutable : mcaconversations$message);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("villager-content reuse failed; falling back to re-parse", t);
        }
    }

    /**
     * Fills the stash from the target's own JSON when the message arrived over the wire. Parses each
     * half once, as MCA's {@code safeLoadFromJson} does: {@code fromJson}, an empty literal for null.
     */
    @Unique
    private boolean mcaconversations$ensureParsed() {
        if (mcaconversations$message != null && mcaconversations$prefix != null) {
            return true;
        }
        if (message == null || prefix == null) {
            return false;
        }
        MutableComponent parsedPrefix = mcaconversations$parse(prefix);
        MutableComponent parsedMessage = mcaconversations$parse(message);
        if (parsedPrefix == null || parsedMessage == null) {
            return false;
        }
        mcaconversations$prefix = parsedPrefix;
        mcaconversations$message = parsedMessage;
        return true;
    }

    @Unique
    private static MutableComponent mcaconversations$parse(String json) {
        try {
            MutableComponent parsed = Component.Serializer.fromJson(json);
            return parsed == null ? Component.literal("") : parsed;
        } catch (RuntimeException malformed) {
            // MCA's own getter would throw here; staying out of the way keeps that behaviour.
            return null;
        }
    }

    @Unique
    private void mcaconversations$sighted(ClientUtteranceLedger.Surface surface) {
        UtteranceTag.read(mcaconversations$message).ifPresent(id -> {
            net.minecraft.client.Minecraft minecraft = net.minecraft.client.Minecraft.getInstance();
            ClientUtteranceLedger.get().record(id, mcaconversations$message, surface,
                    minecraft.player == null ? 0L : minecraft.player.tickCount);
        });
    }
}
