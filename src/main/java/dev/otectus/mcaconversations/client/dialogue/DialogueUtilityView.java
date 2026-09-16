package dev.otectus.mcaconversations.client.dialogue;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Draws one utility inside the response viewport, and says where it put its rows.
 *
 * <p>The viewport is the card's own, so both utilities live entirely inside the panel the player was
 * already looking at: no second window, no resize, and no movement of the outer geometry. The
 * question above stays where it was, which is what makes coming back from the history feel like
 * closing a drawer rather than returning from somewhere else.
 */
public final class DialogueUtilityView {

    /** One drawn row: its text, and the rectangle it can be clicked in. */
    public record Placed(DialogueChoiceLayout.Rect rect, int index) {
    }

    /** Where everything ended up, so the caller can hit-test without re-deriving the layout. */
    public record Placement(List<Placed> rows, DialogueChoiceLayout.Rect back, int maxScroll) {
        public Placement {
            rows = List.copyOf(rows);
        }

        /** The row index at a point, or -1. */
        public int rowAt(double mouseX, double mouseY) {
            for (Placed placed : rows) {
                if (placed.rect().contains(mouseX, mouseY)) {
                    return placed.index();
                }
            }
            return -1;
        }

        public boolean backAt(double mouseX, double mouseY) {
            return back != null && back.contains(mouseX, mouseY);
        }
    }

    /** One line of a utility: a label, an optional trailing value, and whether it can be activated. */
    public record Entry(Component label, Component value, boolean actionable) {
        public static Entry of(Component label) {
            return new Entry(label, null, false);
        }

        public static Entry action(Component label, Component value) {
            return new Entry(label, value, true);
        }
    }

    private static final int GAP = 2;

    private DialogueUtilityView() {
    }

    public static Placement draw(GuiGraphics graphics, Font font, DialogueSkin skin,
                                 DialogueChoiceLayout.Rect viewport, Component title,
                                 List<Entry> entries, int selected, int scroll,
                                 int mouseX, int mouseY, float alpha) {
        int step = DialogueChoiceLayout.lineStep(font.lineHeight);
        int headerHeight = font.lineHeight + 4;
        DialogueChoiceLayout.Rect back = new DialogueChoiceLayout.Rect(
                viewport.x() + viewport.width() - 4 - backWidth(font), viewport.y(),
                backWidth(font), headerHeight);
        // The renderer suspends response drawing while this utility owns the viewport.
        // This translucent surface provides backing, not an occlusion layer for other text.
        skin.panel(graphics, viewport, null, alpha);
        graphics.drawString(font, title, viewport.x() + 4,
                viewport.y() + 2, ConversationPalette.withAlpha(ConversationPalette.TEXT, alpha), true);
        boolean backHovered = back.contains(mouseX, mouseY);
        skin.control(graphics, back, alpha, true, backHovered);
        Component backLabel = Component.translatable("gui.mcaconversations.utility.back");
        graphics.drawString(font, backLabel,
                back.x() + Math.max(0, (back.width() - font.width(backLabel)) / 2),
                DialogueChoiceLayout.centeredTextY(back, font.lineHeight),
                ConversationPalette.withAlpha(ConversationPalette.TEXT, alpha), true);

        DialogueChoiceLayout.Rect body = new DialogueChoiceLayout.Rect(viewport.x() + 2,
                viewport.y() + headerHeight + GAP, Math.max(8, viewport.width() - 4),
                Math.max(step, viewport.height() - headerHeight - GAP));
        int valueColumn = valueColumn(font, entries);
        int textWidth = Math.max(24, body.width() - 8 - valueColumn);

        List<List<FormattedCharSequence>> wrapped = new ArrayList<>(entries.size());
        int documentHeight = 0;
        for (Entry entry : entries) {
            List<FormattedCharSequence> lines = font.split(entry.label(), textWidth);
            wrapped.add(lines);
            documentHeight += Math.max(1, lines.size()) * step + GAP;
        }
        int maxScroll = Math.max(0, documentHeight - body.height());
        int offset = Math.max(0, Math.min(maxScroll, scroll));

        List<Placed> placed = new ArrayList<>(entries.size());
        int y = body.y() - offset;
        graphics.enableScissor(body.x(), body.y(), body.x() + body.width(), body.y() + body.height());
        try {
            for (int i = 0; i < entries.size(); i++) {
                Entry entry = entries.get(i);
                List<FormattedCharSequence> lines = wrapped.get(i);
                int height = Math.max(1, lines.size()) * step;
                DialogueChoiceLayout.Rect rect =
                        new DialogueChoiceLayout.Rect(body.x(), y, body.width(), height);
                if (entry.actionable()) {
                    int visibleTop = Math.max(rect.y(), body.y());
                    int visibleBottom = Math.min(rect.y() + rect.height(), body.y() + body.height());
                    if (visibleBottom > visibleTop) {
                        placed.add(new Placed(new DialogueChoiceLayout.Rect(body.x(), visibleTop,
                                body.width(), visibleBottom - visibleTop), i));
                    }
                    skin.row(graphics, rect, alpha, i == selected, false);
                }
                int lineY = y;
                for (FormattedCharSequence line : lines) {
                    graphics.drawString(font, line, rect.x() + 4, lineY,
                            ConversationPalette.withAlpha(entry.actionable()
                                    ? ConversationPalette.TEXT : ConversationPalette.TEXT_MUTED, alpha),
                            true);
                    lineY += step;
                }
                if (entry.value() != null) {
                    int valueWidth = font.width(entry.value());
                    graphics.drawString(font, entry.value(),
                            rect.x() + rect.width() - 4 - valueWidth, y,
                            ConversationPalette.withAlpha(ConversationPalette.TEXT, alpha), true);
                }
                y += height + GAP;
            }
        } finally {
            graphics.disableScissor();
        }
        if (maxScroll > 0) {
            skin.scrollbar(graphics, body, offset / Math.max(1, step),
                    body.height() / Math.max(1, step), documentHeight / Math.max(1, step), alpha);
        }
        return new Placement(placed, back, maxScroll);
    }

    private static int backWidth(Font font) {
        return font.width(Component.translatable("gui.mcaconversations.utility.back")) + 10;
    }

    private static int valueColumn(Font font, List<Entry> entries) {
        int widest = 0;
        for (Entry entry : entries) {
            if (entry.value() != null) {
                widest = Math.max(widest, font.width(entry.value()));
            }
        }
        return widest == 0 ? 0 : widest + 8;
    }
}
