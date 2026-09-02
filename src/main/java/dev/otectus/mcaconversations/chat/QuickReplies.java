package dev.otectus.mcaconversations.chat;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;
import java.util.OptionalInt;

/**
 * Numbered quick-replies for chat mode.
 *
 * <p>Free-text matching is the point of chat mode, and it stays the primary path: every substantive
 * answer still has authored intents with real paraphrases. But a branching tree asks the player to
 * choose between three or four specific things the character could say, and expecting them to guess
 * the wording of a stance they cannot see is a worse experience than the GUI, not a better one.
 *
 * <p>So when a decision is live, the villager's chat line is followed by the choices, numbered, and
 * the player may answer with the number. It is the same {@code selectAnswer} call the button makes —
 * identical hearts, identical state — just a second way to name the same choice.
 *
 * <p>Both halves are pure and unit-tested: {@link #parse} never touches the world, and
 * {@link #optionsBlock} builds translatable components the client resolves in its own locale.
 */
public final class QuickReplies {

    /** Mirrors the bounded synchronized-offer protocol; choices are never silently truncated. */
    public static final int MAX_OPTIONS = 64;

    private QuickReplies() {
    }

    /**
     * Reads a bare choice number out of a player message, 1-based, or empty when the message is
     * anything else.
     *
     * <p>Deliberately strict: only a number, optionally wrapped in the punctuation people naturally
     * type ({@code "2."}, {@code "(2)"}, {@code "#2"}). A message with any other word in it is
     * ordinary speech and belongs to the intent matcher — "give me 2 minutes" must never be read as
     * picking option two.
     */
    public static OptionalInt parse(String message, int optionCount) {
        if (message == null || optionCount <= 0) {
            return OptionalInt.empty();
        }
        String trimmed = message.trim();
        // Strip the punctuation a player might wrap a number in, from both ends.
        int start = 0;
        int end = trimmed.length();
        while (start < end && isWrapper(trimmed.charAt(start))) {
            start++;
        }
        while (end > start && isWrapper(trimmed.charAt(end - 1))) {
            end--;
        }
        String core = trimmed.substring(start, end);
        if (core.isEmpty() || core.length() > Integer.toString(MAX_OPTIONS).length()) {
            return OptionalInt.empty();
        }
        for (int i = 0; i < core.length(); i++) {
            if (!Character.isDigit(core.charAt(i))) {
                return OptionalInt.empty();
            }
        }
        int value = Integer.parseInt(core);
        if (value < 1 || value > Math.min(optionCount, MAX_OPTIONS)) {
            return OptionalInt.empty();
        }
        return OptionalInt.of(value);
    }

    private static boolean isWrapper(char c) {
        return c == '.' || c == ')' || c == '(' || c == '#' || c == '-' || c == ':' || Character.isWhitespace(c);
    }

    /**
     * The offered answer for a 1-based choice number, or empty when it is out of range.
     * The order is MCA's own constraint-filtered answer order, so the numbers on screen and the
     * numbers in chat always agree.
     */
    public static java.util.Optional<String> answerFor(List<String> offered, int choice) {
        if (offered == null || choice < 1 || choice > offered.size() || choice > MAX_OPTIONS) {
            return java.util.Optional.empty();
        }
        return java.util.Optional.of(offered.get(choice - 1));
    }

    /**
     * Renders the live choices as a dim numbered list to append after the villager's line, using the
     * same {@code dialogue.<question>.<answer>} keys the buttons use — so a player reads exactly the
     * words the GUI would have shown them, in their own language.
     *
     * <p>Returns empty when there is nothing worth numbering: no question, no answers, or only a
     * single option (a list of one is noise, and the player can just say it).
     */
    public static java.util.Optional<Component> optionsBlock(String question, List<String> offered) {
        if (question == null || offered == null || offered.size() < 2) {
            return java.util.Optional.empty();
        }
        MutableComponent out = Component.empty();
        int shown = 0;
        for (String answer : offered) {
            if (shown >= MAX_OPTIONS) {
                return java.util.Optional.empty();
            }
            shown++;
            if (shown > 1) {
                out.append(Component.literal("\n"));
            }
            int choice = shown;
            ClickEvent click = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Integer.toString(choice));
            HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    Component.translatable("chat.mcaconversations.responses.hover", choice));
            MutableComponent option = Component.empty().withStyle(style -> style
                    .withClickEvent(click).withHoverEvent(hover));
            option.append(Component.literal(shown + ". ").withStyle(style -> style
                    .withColor(0xFFC34D).withBold(true)));
            option.append(Component.translatable("dialogue." + question + "." + answer)
                    .withStyle(ChatFormatting.WHITE));
            out.append(option);
        }
        out.append(Component.literal("\n"));
        out.append(Component.translatable("chat.mcaconversations.responses.hint")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        return shown < 2 ? java.util.Optional.empty() : java.util.Optional.of(out);
    }

    /** Source-compatible alias for integrations compiled against the pre-1.4.3 helper name. */
    @Deprecated(forRemoval = false)
    public static java.util.Optional<Component> optionsLine(String question, List<String> offered) {
        return optionsBlock(question, offered);
    }
}
