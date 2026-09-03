package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

/** Transition-only narration with revision/index deduplication. */
public final class DialogueChoiceNarrator {

    private final Consumer<Component> sink;
    private long offerRevision = -1L;
    private int focusedIndex = -1;
    private int page = -1;
    private int lockedIndex = -1;

    public DialogueChoiceNarrator() {
        this(DialogueChoiceNarrator::sayThroughNarrator);
    }

    /**
     * Test seam. The deduplication rules are the part worth asserting -- narrating the same row
     * twice is the failure players actually hear -- and they cannot be exercised through a live
     * {@code Minecraft} instance.
     */
    DialogueChoiceNarrator(Consumer<Component> sink) {
        this.sink = sink;
    }

    public void offer(long revision, int responseCount) {
        if (offerRevision == revision) {
            return;
        }
        offerRevision = revision;
        focusedIndex = -1;
        page = -1;
        lockedIndex = -1;
        say(Component.translatable("gui.mcaconversations.responses.count", responseCount));
    }

    public void focus(long revision, int absoluteIndex, int total, Component answer) {
        if (offerRevision != revision || focusedIndex == absoluteIndex) {
            return;
        }
        focusedIndex = absoluteIndex;
        say(Component.translatable("gui.mcaconversations.responses.narration",
                absoluteIndex + 1, total, answer));
    }

    public void page(long revision, int current, int total) {
        if (offerRevision != revision || page == current) {
            return;
        }
        page = current;
        say(Component.translatable("gui.mcaconversations.responses.page_narration", current + 1, total));
    }

    public void locked(long revision, int absoluteIndex, Component answer) {
        if (offerRevision != revision || lockedIndex == absoluteIndex) {
            return;
        }
        lockedIndex = absoluteIndex;
        say(Component.translatable("gui.mcaconversations.responses.selected", answer));
    }

    public void reset() {
        offerRevision = -1L;
        focusedIndex = -1;
        page = -1;
        lockedIndex = -1;
    }

    /**
     * Announced from the packet sink rather than an instance, because a lapsed offer clears the
     * screen state that would otherwise own the narrator.
     */
    public static void expired() {
        sayThroughNarrator(Component.translatable("gui.mcaconversations.responses.expired"));
    }

    private void say(Component component) {
        try {
            sink.accept(component);
        } catch (Throwable ignored) {
            // Narration is additive accessibility feedback, never a screen-ownership requirement.
        }
    }

    private static void sayThroughNarrator(Component component) {
        try {
            Minecraft.getInstance().getNarrator().sayNow(component);
        } catch (Throwable ignored) {
            // Narration is additive accessibility feedback, never a screen-ownership requirement.
        }
    }
}
