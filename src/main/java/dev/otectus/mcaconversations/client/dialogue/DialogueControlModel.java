package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.ToIntFunction;

/**
 * The one description of which controls are actually available, used by both the footer hint and the
 * narrator.
 *
 * <p>Built once per page from the configuration and the page map, so a hint can never advertise a
 * digit the key handler will ignore, and narration can never name a shortcut the card does not draw.
 */
public record DialogueControlModel(boolean numericShortcuts, int shortcutCount, boolean paging,
                                   boolean reading) {

    public DialogueControlModel {
        shortcutCount = Math.max(0, Math.min(DialogueChoiceLayout.MAX_VISIBLE_SHORTCUTS,
                shortcutCount));
    }

    /** The digit that selects a row, or empty when digits are off or the row has no digit. */
    public OptionalInt shortcutFor(int visibleNumber) {
        if (!numericShortcuts || visibleNumber < 1 || visibleNumber > shortcutCount) {
            return OptionalInt.empty();
        }
        return OptionalInt.of(visibleNumber);
    }

    /** "1-5", or empty when there is nothing to press. */
    public String shortcutRange() {
        if (!numericShortcuts || shortcutCount <= 0) {
            return "";
        }
        return shortcutCount == 1 ? "1" : "1-" + shortcutCount;
    }

    /**
     * "Response 10 of 12. Shortcut 1. &lt;answer&gt;", with the shortcut clause omitted whenever the
     * player cannot use one.
     */
    public Component narration(int absoluteIndex, int total, int visibleNumber, Component answer) {
        OptionalInt shortcut = shortcutFor(visibleNumber);
        if (shortcut.isEmpty()) {
            return Component.translatable("gui.mcaconversations.responses.narration",
                    absoluteIndex + 1, total, answer);
        }
        return Component.translatable("gui.mcaconversations.responses.narration_shortcut",
                absoluteIndex + 1, total, shortcut.getAsInt(), answer);
    }

    /** Longest hint first; the footer takes the first one that fits the width it was given. */
    public List<Component> hintVariants(boolean compact) {
        List<Component> variants = new ArrayList<>(3);
        if (numericShortcuts && shortcutCount > 0) {
            String range = shortcutRange();
            if (reading) {
                variants.add(Component.translatable("gui.mcaconversations.responses.hint_reading",
                        range));
            }
            variants.add(Component.translatable("gui.mcaconversations.responses.hint", range));
            variants.add(Component.translatable("gui.mcaconversations.responses.hint_compact", range));
        } else {
            if (reading) {
                variants.add(Component.translatable(
                        "gui.mcaconversations.responses.hint_keys_reading"));
            }
            variants.add(Component.translatable("gui.mcaconversations.responses.hint_keys"));
            variants.add(Component.translatable("gui.mcaconversations.responses.hint_keys_compact"));
        }
        if (compact && variants.size() > 1) {
            variants.remove(0);
        }
        return List.copyOf(variants);
    }

    /**
     * The hint to draw in {@code budget} pixels, or null when even the shortest does not fit. The
     * budget is what is left after the page text and the page controls have taken theirs, so the two
     * halves of the footer cannot overlap however long the translation is.
     */
    public Component hint(int budget, boolean compact, ToIntFunction<Component> width) {
        for (Component variant : hintVariants(compact)) {
            if (width.applyAsInt(variant) <= budget) {
                return variant;
            }
        }
        return null;
    }
}
