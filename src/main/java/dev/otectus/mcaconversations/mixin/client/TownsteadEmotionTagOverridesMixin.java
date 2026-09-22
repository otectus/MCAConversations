package dev.otectus.mcaconversations.mixin.client;

import dev.otectus.mcaconversations.client.townstead.ConversationsEmotionTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Lets Townstead's RPG typewriter find Conversations' emotion tags (Townstead spec §17.3).
 *
 * <p>Townstead answers first, always: its own non-null result is never touched. Only when it has
 * nothing does Conversations' sidecar get asked. {@link Pseudo} and {@code require = 0} make this a
 * no-op when Townstead is absent or has renamed either method; the typewriter then shows clean text,
 * exactly as it does for any line without tags.
 */
@Pseudo
@Mixin(targets = "com.aetherianartificer.townstead.client.gui.dialogue.EmotionTagOverrides", remap = false)
public abstract class TownsteadEmotionTagOverridesMixin {

    @Inject(method = "getTaggedText", at = @At("RETURN"), cancellable = true, require = 0, remap = false)
    private static void mcaconversations$taggedByKey(String key, CallbackInfoReturnable<String> cir) {
        if (cir.getReturnValue() == null) {
            String ours = ConversationsEmotionTags.byKey(key);
            if (ours != null) {
                cir.setReturnValue(ours);
            }
        }
    }

    @Inject(method = "applyTagsToResolvedText", at = @At("RETURN"), cancellable = true, require = 0, remap = false)
    private static void mcaconversations$taggedByText(String plainText, CallbackInfoReturnable<String> cir) {
        if (cir.getReturnValue() == null) {
            String ours = ConversationsEmotionTags.byResolvedText(plainText);
            if (ours != null) {
                cir.setReturnValue(ours);
            }
        }
    }
}
