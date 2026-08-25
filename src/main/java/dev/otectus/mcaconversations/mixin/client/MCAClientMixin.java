package dev.otectus.mcaconversations.mixin.client;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.locale.OverlayLocales;
import net.conczin.mca.Config;
import net.conczin.mca.MCAClient;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Lets per-personality dialogue resolve in the extra locales this mod ships complete overlays for.
 *
 * <p>MCA gates personality lines behind {@code MCAClient.useExpandedPersonalityTranslations()}:
 * <pre>{@code !isTTSPackActive && language.equals("en_us") && !Config.enableOnlineTTS}</pre>
 * That is a single locale — {@code en_us} — verified against the resolved MCA 7.7.36-beta.3 jar.
 * (The 1.20.1 comment here claimed {@code en_us} or {@code ru_ru}; that is no longer the target's
 * behaviour, if it ever was.) The language check is a content decision — upstream only ships
 * personality packs for English — so on any other locale {@code DialogueType.applyFallback} skips
 * the personality branch entirely and every villager falls through to the generic pool. Shipping a
 * complete {@code pt_br} overlay set is therefore not enough on its own; without this hook it would
 * never be read.
 *
 * <p><b>This only ever widens the language check, never the other two.</b> The TTS-pack and
 * online-TTS guards exist because MCA's voice packs record one audio file per base key and cannot
 * follow a personality-specific key; overriding those would silently break voiced dialogue. So we
 * re-test both conditions ourselves and bail out if either holds — the hook can turn
 * {@code false} into {@code true} only when the sole reason for the {@code false} was the locale.
 *
 * <p>Client-only by construction ({@code Minecraft}, {@code MCAClient}) and declared in the
 * {@code "client"} section of the mixin config, so a dedicated server never loads it.
 * {@code remap = false} (MCA's own method) and {@code require = 0}: if MCA reshapes the method we
 * simply fall back to its own behaviour rather than failing to start.
 */
@Mixin(value = MCAClient.class, remap = false)
public abstract class MCAClientMixin {

    @Inject(method = "useExpandedPersonalityTranslations", at = @At("RETURN"),
            cancellable = true, require = 0)
    private static void mcaconversations$allowOverlayLocales(CallbackInfoReturnable<Boolean> cir) {
        try {
            if (Boolean.TRUE.equals(cir.getReturnValue())) {
                return; // MCA already allows it; nothing to widen.
            }
            Minecraft client = Minecraft.getInstance();
            if (client == null || client.options == null) {
                return;
            }
            if (!OverlayLocales.shipsPersonalityOverlays(client.options.languageCode)) {
                return; // a locale we have no overlays for — MCA's answer stands.
            }
            // Preserve MCA's own two restrictions verbatim.
            if (Config.getInstance().enableOnlineTTS) {
                return;
            }
            boolean voicePackActive = client.getResourceManager().listPacks()
                    .anyMatch(pack -> pack.packId().contains("MCAVoices"));
            if (voicePackActive) {
                return;
            }
            cir.setReturnValue(true);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Overlay-locale hook failed; keeping MCA's answer", t);
        }
    }
}
