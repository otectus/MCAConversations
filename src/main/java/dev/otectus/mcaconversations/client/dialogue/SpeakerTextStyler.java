package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;
import java.util.Optional;

/** Styles only an unambiguous leading speaker label while retaining every source style run. */
public final class SpeakerTextStyler {

    private SpeakerTextStyler() {
    }

    public static FormattedText style(FormattedText source, Component speakerName,
                                      boolean silent) {
        if (source == null || silent || !ClientChoiceController.speakerNameAccent()
                || speakerName == null || speakerName.getString().isBlank()) {
            return source;
        }
        String plain = source.getString();
        String name = speakerName.getString();
        int separator = plain.indexOf(": ");
        if (separator < 0) {
            return source;
        }
        String label = plain.substring(0, separator);
        if (!label.endsWith(name)) {
            return source;
        }
        int nameStart = separator - name.length();
        if (nameStart < 0 || !plain.regionMatches(nameStart, name, 0, name.length())) {
            return source;
        }
        if (nameStart > 0) {
            String prefix = label.substring(0, nameStart);
            boolean boundedPrefix = prefix.endsWith("] ") || prefix.endsWith("> ")
                    || prefix.endsWith("» ");
            if (!boundedPrefix) {
                return source;
            }
        }
        return restyle(source, nameStart, separator, separator, separator + 1);
    }

    static FormattedText restyle(FormattedText source, int nameStart, int nameEnd,
                                 int separatorStart, int separatorEnd) {
        MutableComponent result = Component.empty();
        int[] offset = {0};
        source.visit((style, text) -> {
            appendRange(result, text, style, offset[0], nameStart, nameEnd,
                    separatorStart, separatorEnd);
            offset[0] += text.length();
            return Optional.empty();
        }, Style.EMPTY);
        return result;
    }

    private static void appendRange(MutableComponent result, String text, Style style, int base,
                                    int nameStart, int nameEnd,
                                    int separatorStart, int separatorEnd) {
        int cursor = 0;
        while (cursor < text.length()) {
            int absolute = base + cursor;
            int boundary = text.length();
            Style applied = style;
            if (absolute >= nameStart && absolute < nameEnd) {
                boundary = Math.min(boundary, nameEnd - base);
                applied = style.withColor(ConversationPalette.SPEAKER_NAME & 0x00FFFFFF)
                        .withBold(true);
            } else if (absolute >= separatorStart && absolute < separatorEnd) {
                // The colon is only broken out as its own run so the bold name stops at the name.
                // It keeps whatever style the source gave it, as any other body character does.
                boundary = Math.min(boundary, separatorEnd - base);
            } else {
                for (int candidate : new int[] {nameStart, separatorStart}) {
                    if (candidate > absolute) {
                        boundary = Math.min(boundary, candidate - base);
                    }
                }
            }
            if (boundary <= cursor) {
                boundary = cursor + 1;
            }
            result.append(Component.literal(text.substring(cursor, boundary)).setStyle(applied));
            cursor = boundary;
        }
    }

    /** Conservative fallback when MCA's exact component-capture hook is unavailable. */
    public static FormattedText fromLegacy(List<FormattedCharSequence> legacyLines) {
        if (legacyLines == null || legacyLines.isEmpty()) {
            return Component.empty();
        }
        MutableComponent result = Component.empty();
        for (int i = 0; i < legacyLines.size(); i++) {
            if (i > 0) {
                result.append(" ");
            }
            appendSequence(result, legacyLines.get(i));
        }
        return result;
    }

    private static void appendSequence(MutableComponent result, FormattedCharSequence sequence) {
        StringBuilder run = new StringBuilder();
        Style[] previous = {null};
        sequence.accept((index, style, codePoint) -> {
            if (previous[0] != null && !previous[0].equals(style)) {
                result.append(Component.literal(run.toString()).setStyle(previous[0]));
                run.setLength(0);
            }
            previous[0] = style;
            run.appendCodePoint(codePoint);
            return true;
        });
        if (run.length() > 0) {
            result.append(Component.literal(run.toString())
                    .setStyle(previous[0] == null ? Style.EMPTY : previous[0]));
        }
    }
}
