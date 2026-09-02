package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;

/** Optional restrained UI cues, called only after a real state mutation. */
public final class DialogueUiSounds {

    /** Where a cue goes. Swapped in tests so the documented pitch/volume pairs can be asserted. */
    interface Sink {
        void play(float pitch, float volume);
    }

    private static Sink sink = DialogueUiSounds::playThroughSoundManager;

    private DialogueUiSounds() {
    }

    public static void focus() {
        play(1.35F, 0.18F);
    }

    public static void page() {
        play(1.0F, 0.25F);
    }

    static void withSink(Sink replacement, Runnable body) {
        Sink previous = sink;
        sink = replacement;
        try {
            body.run();
        } finally {
            sink = previous;
        }
    }

    private static void play(float pitch, float baseVolume) {
        float volume = (float) (baseVolume * ClientChoiceController.uiSoundVolume());
        if (volume <= 0.0F) {
            return;
        }
        try {
            sink.play(pitch, volume);
        } catch (Throwable ignored) {
            // UI feedback must never make a compatible native screen fail.
        }
    }

    private static void playThroughSoundManager(float pitch, float volume) {
        Minecraft.getInstance().getSoundManager().play(
                SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK.value(), pitch, volume));
    }
}
