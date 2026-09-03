package dev.otectus.mcaconversations.client.dialogue.dev;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceMessages;
import dev.otectus.mcaconversations.client.dialogue.ClientChoiceState;
import dev.otectus.mcaconversations.client.dialogue.DialogueChoiceInput;
import dev.otectus.mcaconversations.client.dialogue.DialogueChoiceRenderer;
import dev.otectus.mcaconversations.client.dialogue.DialogueHitTarget;
import dev.otectus.mcaconversations.client.dialogue.DialoguePresentationBuilder;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

/**
 * Development-only harness for the response card.
 *
 * <p>It exists because {@code gradlew runClient} cannot show the real thing. MCA's Mixins ship with
 * no refmap and hard-coded SRG names, so MCA does not load in a development runtime at all, and the
 * {@code InteractScreen} this card attaches to never appears. Without this screen the only way to
 * look at a layout change is to build a jar and start a production instance, which is far too slow a
 * loop to iterate a visual design in, and GUI-scale regressions are exactly what gets skipped when
 * checking is expensive.
 *
 * <p>It drives the real {@link DialogueChoiceRenderer} against the real {@link ClientChoiceState},
 * so what appears here is what MCA's screen draws. Only the source of the offer is synthetic.
 */
public final class DialogueCardPreviewScreen extends Screen {

    private final DialogueChoiceRenderer renderer = new DialogueChoiceRenderer();
    private int fixtureIndex;
    private long revision;

    public DialogueCardPreviewScreen() {
        super(Component.literal("MCA: Conversations response-card preview"));
    }

    private DialoguePreviewFixture fixture() {
        return DialoguePreviewFixture.ALL.get(fixtureIndex);
    }

    @Override
    protected void init() {
        loadFixture(0);
    }

    private void loadFixture(int delta) {
        fixtureIndex = Math.floorMod(fixtureIndex + delta, DialoguePreviewFixture.ALL.size());
        ClientChoiceState state = ClientChoiceMessages.state();
        revision = state.highestRevision() + 1L;
        List<String> answerIds = new ArrayList<>();
        for (int i = 0; i < fixture().answers().size(); i++) {
            answerIds.add(Integer.toString(i));
        }
        state.accept(new ClientChoiceState.ClientChoiceOffer(revision, "preview", answerIds,
                ConversationSession.Frontend.GUI, 0L));
        renderer.reset();
    }

    @Override
    public void tick() {
        renderer.tick();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        DialoguePreviewFixture fixture = fixture();
        DialoguePresentationBuilder.withAnswerText(
                id -> fixture.answers().get(Integer.parseInt(id)),
                () -> renderer.render(graphics, mouseX, mouseY, partialTick,
                        fixture.question(), revision, fixture.speaker(), fixture.silent(),
                        List.<FormattedCharSequence>of(),
                        // No villager in a preview, so the local player stands in for one.
                        this.minecraft == null ? null : this.minecraft.player));
        drawLegend(graphics);
    }

    private void drawLegend(GuiGraphics graphics) {
        int y = 4;
        for (String line : legend()) {
            graphics.drawString(this.font, line, 4, y, 0xFFB7B1A6, false);
            y += this.font.lineHeight + 1;
        }
    }

    private List<String> legend() {
        double scale = this.minecraft == null ? 0.0D : this.minecraft.getWindow().getGuiScale();
        return List.of(
                "[ ] fixture " + (fixtureIndex + 1) + "/" + DialoguePreviewFixture.ALL.size()
                        + "  " + fixture().name(),
                "M motion " + read(McaConversationsConfig.CLIENT.motionMode)
                        + "   H hints " + read(McaConversationsConfig.CLIENT.showResponseControlHints)
                        + "   R reload fixture",
                "arrows / Home / End / PgUp / PgDn / digits drive the card",
                "gui scale " + (scale <= 0.0D ? "?" : String.valueOf((int) scale))
                        + "   viewport " + this.width + "x" + this.height);
    }

    private static String read(ModConfigSpec.ConfigValue<?> value) {
        try {
            return String.valueOf(value.get());
        } catch (Throwable ignored) {
            return "unavailable";
        }
    }

    private static <T> void cycle(ModConfigSpec.ConfigValue<T> value, List<T> options) {
        try {
            value.set(options.get(Math.floorMod(options.indexOf(value.get()) + 1, options.size())));
        } catch (Throwable ignored) {
            // A preview control must never take the development client down.
        }
    }

    private static void toggle(ModConfigSpec.ConfigValue<Boolean> value) {
        try {
            value.set(!value.get());
        } catch (Throwable ignored) {
            // A preview control must never take the development client down.
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        ClientChoiceState state = ClientChoiceMessages.state();
        switch (keyCode) {
            case GLFW.GLFW_KEY_LEFT_BRACKET -> {
                loadFixture(-1);
                return true;
            }
            case GLFW.GLFW_KEY_RIGHT_BRACKET -> {
                loadFixture(1);
                return true;
            }
            case GLFW.GLFW_KEY_R -> {
                loadFixture(0);
                return true;
            }
            case GLFW.GLFW_KEY_M -> {
                cycle(McaConversationsConfig.CLIENT.motionMode,
                        List.of(McaConversationsConfig.MotionMode.values()));
                return true;
            }
            case GLFW.GLFW_KEY_H -> {
                toggle(McaConversationsConfig.CLIENT.showResponseControlHints);
                return true;
            }
            case GLFW.GLFW_KEY_UP -> {
                renderer.moveFocus(-1);
                return true;
            }
            case GLFW.GLFW_KEY_DOWN -> {
                renderer.moveFocus(1);
                return true;
            }
            case GLFW.GLFW_KEY_HOME -> {
                renderer.focusBoundary(false);
                return true;
            }
            case GLFW.GLFW_KEY_END -> {
                renderer.focusBoundary(true);
                return true;
            }
            case GLFW.GLFW_KEY_PAGE_UP -> {
                renderer.changePage(-1);
                return true;
            }
            case GLFW.GLFW_KEY_PAGE_DOWN -> {
                renderer.changePage(1);
                return true;
            }
            case GLFW.GLFW_KEY_ENTER, GLFW.GLFW_KEY_KP_ENTER, GLFW.GLFW_KEY_SPACE -> {
                renderer.keyboardInput();
                // Locked directly rather than through the controller: the preview shows the
                // confirmed visual state without putting a selection packet on the wire.
                state.lock(state.focusedIndex());
                return true;
            }
            default -> {
                OptionalInt digit = DialogueChoiceInput.digit(keyCode, modifiers);
                if (digit.isPresent()) {
                    renderer.keyboardInput();
                    state.lock(state.firstOnPage() + digit.getAsInt() - 1);
                    return true;
                }
            }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            DialogueHitTarget target = renderer.click(mouseX, mouseY);
            if (target instanceof DialogueHitTarget.Choice choice) {
                ClientChoiceMessages.state().lock(choice.absoluteIndex());
                return true;
            }
            if (!(target instanceof DialogueHitTarget.None)) {
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        double delta = scrollY;
        if (!renderer.scroll(mouseX, mouseY, delta)) {
            renderer.changePage(delta < 0.0D ? 1 : -1);
        }
        return true;
    }

    @Override
    public void onClose() {
        ClientChoiceMessages.state().clearLocal();
        renderer.reset();
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
