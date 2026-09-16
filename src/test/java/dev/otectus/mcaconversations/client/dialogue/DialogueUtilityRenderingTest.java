package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Executes the actual card renderer, recording text instead of sending it to OpenGL. */
class DialogueUtilityRenderingTest {
    @ParameterizedTest
    @EnumSource(value = DialogueUtilityState.Utility.class, names = {"HISTORY", "SETTINGS"})
    void utilitiesReplaceAnswersAndBackRestoresThem(DialogueUtilityState.Utility utility) throws Exception {
        for (DialogueStyleProfile profile : List.of(DialogueStyleProfile.MINIMAL, DialogueStyleProfile.RESPONSIVE)) {
            for (int locked : new int[]{-1, 1}) {
                DialogueChoiceRenderer renderer = new DialogueChoiceRenderer();
                var layout = DialogueChoiceLayout.create(640, 360, 1, 9,
                        List.of(24, 24), false, false, false, false);
                List<PreparedChoiceRow> rows = new ArrayList<>();
                for (int index = 0; index < 2; index++) {
                    Component answer = Component.literal("answer-" + index);
                    rows.add(new PreparedChoiceRow(index, index + 1, layout.rows().get(index),
                            layout.rows().get(index), answer, List.of(answer.getVisualOrderText()), false));
                }
                PreparedDialogueCard card = new PreparedDialogueCard(1L, layout,
                        List.of(Component.literal("spoken-question").getVisualOrderText()), rows, 11, 24, false);
                field(renderer, "card", card);
                field(renderer, "skin", new NoOpSkin());
                field(renderer, "renderedLock", locked);
                RecordingGraphics graphics = RecordingGraphics.create();
                Font font = new FixedFont();

                draw(renderer, graphics, font, profile, 1.0F);
                assertTrue(graphics.text.containsAll(List.of("answer-0", "answer-1")));
                renderer.utilityState().open(utility, DialogueReadingState.Region.RESPONSES);
                for (float alpha : new float[]{0.25F, 1.0F}) {
                    graphics.text.clear();
                    draw(renderer, graphics, font, profile, alpha);
                    assertFalse(graphics.text.stream().anyMatch(text -> text.startsWith("answer-")),
                            "hidden answers must not be drawn beneath a translucent utility");
                    assertFalse(graphics.text.contains("1.") || graphics.text.contains("2."));
                    assertTrue(graphics.text.contains("spoken-question"), "the question stays visible");
                    assertTrue(graphics.text.stream().anyMatch(text -> text.contains(
                            utility == DialogueUtilityState.Utility.HISTORY ? "history.title" : "settings.title")));
                    assertEquals(0, graphics.scissors, "every render must restore clipping");
                }
                renderer.utilityState().back();
                graphics.text.clear();
                draw(renderer, graphics, font, profile, 1.0F);
                assertTrue(graphics.text.containsAll(List.of("answer-0", "answer-1")),
                        "Back restores the original response list");
                assertSame(card, field(renderer, "card"), "opening a utility must not replace the prepared offer");
            }
        }
    }

    private static void draw(DialogueChoiceRenderer renderer, GuiGraphics graphics, Font font,
                             DialogueStyleProfile profile, float alpha) throws Exception {
        var method = DialogueChoiceRenderer.class.getDeclaredMethod("drawCard", GuiGraphics.class,
                Font.class, ConversationMotionSpec.class, float.class, float.class,
                int.class, int.class, LivingEntity.class, DialogueStyleProfile.class);
        method.setAccessible(true);
        method.invoke(renderer, graphics, font, ConversationMotionSpec.of(McaConversationsConfig.MotionMode.OFF,
                        McaConversationsConfig.DialogueMenuStyle.MINIMAL),
                0.0F, alpha, -1, -1, null, profile);
    }

    private static void field(Object target, String name, Object value) throws Exception {
        var field = target.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(target, value);
    }

    private static Object field(Object target, String name) throws Exception {
        var field = target.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(target);
    }

    private static String text(FormattedCharSequence sequence) {
        StringBuilder text = new StringBuilder();
        sequence.accept((index, style, codepoint) -> { text.appendCodePoint(codepoint); return true; });
        return text.toString();
    }

    private static final class FixedFont extends Font {
        FixedFont() { super(id -> null, false); }
        @Override public int width(String value) { return value.length() * 6; }
        @Override public int width(FormattedText value) { return width(value.getString()); }
        @Override public int width(FormattedCharSequence value) { return width(text(value)); }
        @Override public List<FormattedCharSequence> split(FormattedText value, int width) {
            return List.of(Component.literal(value.getString()).getVisualOrderText());
        }
    }

    private static final class RecordingGraphics extends GuiGraphics {
        List<String> text;
        int scissors;
        RecordingGraphics() { super(null, null); }
        static RecordingGraphics create() throws Exception {
            // GuiGraphics's normal constructor initializes ItemStack and the game registries.
            // This recorder overrides every operation used here, so it needs none of that state.
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            var singleton = unsafeClass.getDeclaredField("theUnsafe");
            singleton.setAccessible(true);
            RecordingGraphics graphics = (RecordingGraphics) unsafeClass
                    .getMethod("allocateInstance", Class.class)
                    .invoke(singleton.get(null), RecordingGraphics.class);
            graphics.text = new ArrayList<>();
            return graphics;
        }
        @Override public void enableScissor(int left, int top, int right, int bottom) { scissors++; }
        @Override public void disableScissor() { scissors--; }
        @Override public int drawString(Font font, String value, int x, int y, int color, boolean shadow) {
            text.add(value); return font.width(value);
        }
        @Override public int drawString(Font font, Component value, int x, int y, int color, boolean shadow) {
            return drawString(font, value.getString(), x, y, color, shadow);
        }
        @Override public int drawString(Font font, FormattedCharSequence value, int x, int y,
                                        int color, boolean shadow) {
            return drawString(font, text(value), x, y, color, shadow);
        }
    }

    /** Paint is deliberately transparent: visibility cannot depend on a skin covering previous text. */
    private static final class NoOpSkin implements DialogueSkin {
        @Override public void panel(GuiGraphics g, DialogueChoiceLayout.Rect p, DialogueChoiceLayout.Rect b, float a) {}
        @Override public void row(GuiGraphics g, DialogueChoiceLayout.Rect r, float a, boolean f, boolean l) {}
        @Override public void badge(GuiGraphics g, DialogueChoiceLayout.Rect r, float a, boolean h) {}
        @Override public void control(GuiGraphics g, DialogueChoiceLayout.Rect r, float a, boolean e, boolean h) {}
        @Override public void portrait(GuiGraphics g, DialogueChoiceLayout.Rect r, float a) {}
        @Override public void scrollbar(GuiGraphics g, DialogueChoiceLayout.Rect r, int f, int v, int t, float a) {}
        @Override public String badgeLabel(int number) { return number + "."; }
    }
}
