package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.McaConversationsConfig.DialogueMenuStyle;
import dev.otectus.mcaconversations.McaConversationsConfig.MotionMode;
import dev.otectus.mcaconversations.McaConversationsConfig.QuestionReveal;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The small pane of presentation choices, as a model rather than a screen.
 *
 * <p>Everything that decides what the pane says lives here, behind {@link Store}, so the interesting
 * parts — what a row currently reads, what "reset to recommended" would actually change, and what the
 * legacy override does to the style — can be exercised without a running client.
 *
 * <p>Two things this pane deliberately does not claim. It never presents the configured style as the
 * effective one while {@code numberedResponses=false} forces MCA_ORIGINAL, and it never offers to
 * change Townstead's presentation: Townstead draws its own dialogue screen with its own camera and
 * typewriter, and a setting here that appeared to govern them would be a lie.
 */
public final class PresentationSettings {

    /** Where the pane reads and writes. The client binding writes the Forge client spec. */
    public interface Store {

        DialogueMenuStyle style();

        void style(DialogueMenuStyle value);

        MotionMode motion();

        void motion(MotionMode value);

        QuestionReveal reveal();

        void reveal(QuestionReveal value);

        double volume();

        void volume(double value);

        boolean hints();

        void hints(boolean value);

        /** True while the deprecated {@code numberedResponses=false} override is in force. */
        boolean legacyOverride();

        /** True when Townstead is present and owns a dialogue presentation of its own. */
        boolean townsteadPresent();

        /** Persists whatever was changed. */
        void save();
    }

    /** Volume steps, so one key press is a usable change rather than a hundredth. */
    static final double VOLUME_STEP = 0.05D;

    public record Row(String id, Component label, Component value) {
    }

    private final Store store;

    public PresentationSettings(Store store) {
        this.store = store;
    }

    public List<Row> rows() {
        List<Row> rows = new ArrayList<>(5);
        rows.add(new Row("style", Component.translatable("gui.mcaconversations.settings.style"),
                styleValue()));
        rows.add(new Row("motion", Component.translatable("gui.mcaconversations.settings.motion"),
                motionName(store.motion())));
        rows.add(new Row("reveal", Component.translatable("gui.mcaconversations.settings.reveal"),
                revealName(store.reveal())));
        rows.add(new Row("volume", Component.translatable("gui.mcaconversations.settings.volume"),
                Component.literal(Math.round(store.volume() * 100.0D) + "%")));
        rows.add(new Row("hints", Component.translatable("gui.mcaconversations.settings.hints"),
                onOff(store.hints())));
        return List.copyOf(rows);
    }

    /**
     * The style row's value. When the legacy switch is forcing MCA's own menu the row states both
     * numbers: what is configured, and what is actually being drawn.
     */
    public Component styleValue() {
        DialogueMenuStyle configured = store.style();
        DialogueMenuStyle effective = ClientChoiceController.resolve(!store.legacyOverride(), configured);
        if (configured == effective) {
            return styleName(configured);
        }
        return Component.translatable("gui.mcaconversations.settings.style_overridden",
                styleName(configured), styleName(effective));
    }

    /** The sentence about Townstead's own presentation, or null when Townstead is not installed. */
    public Component townsteadNote() {
        return store.townsteadPresent()
                ? Component.translatable("gui.mcaconversations.settings.townstead") : null;
    }

    /** Cycles one row by {@code direction}; true when the stored value actually changed. */
    public boolean cycle(int rowIndex, int direction) {
        if (direction == 0 || rowIndex < 0 || rowIndex >= rows().size()) {
            return false;
        }
        switch (rows().get(rowIndex).id()) {
            case "style" -> store.style(next(DialogueMenuStyle.values(), store.style(), direction));
            case "motion" -> store.motion(next(MotionMode.values(), store.motion(), direction));
            case "reveal" -> store.reveal(next(QuestionReveal.values(), store.reveal(), direction));
            case "volume" -> {
                double wanted = Math.max(0.0D, Math.min(1.0D,
                        Math.round((store.volume() + direction * VOLUME_STEP) * 100.0D) / 100.0D));
                if (wanted == store.volume()) {
                    return false;
                }
                store.volume(wanted);
            }
            case "hints" -> store.hints(!store.hints());
            default -> {
                return false;
            }
        }
        store.save();
        return true;
    }

    /**
     * What "reset to recommended" would change, one sentence per row, so the action can be read
     * before it is taken. Empty means the recommended presentation is already in force.
     */
    public List<Component> recommendedChanges() {
        List<Component> changes = new ArrayList<>(5);
        if (store.style() != McaConversationsConfig.DEFAULT_DIALOGUE_MENU_STYLE) {
            changes.add(change("gui.mcaconversations.settings.style", styleName(store.style()),
                    styleName(McaConversationsConfig.DEFAULT_DIALOGUE_MENU_STYLE)));
        }
        if (store.motion() != McaConversationsConfig.DEFAULT_MOTION_MODE) {
            changes.add(change("gui.mcaconversations.settings.motion", motionName(store.motion()),
                    motionName(McaConversationsConfig.DEFAULT_MOTION_MODE)));
        }
        if (store.reveal() != QuestionReveal.OFF) {
            changes.add(change("gui.mcaconversations.settings.reveal", revealName(store.reveal()),
                    revealName(QuestionReveal.OFF)));
        }
        if (!store.hints()) {
            changes.add(change("gui.mcaconversations.settings.hints", onOff(false), onOff(true)));
        }
        return List.copyOf(changes);
    }

    /**
     * Applies the recommended presentation. Deliberately separate from {@link #recommendedChanges()}:
     * the pane lists what will change first and applies it only on a second, explicit action.
     */
    public boolean applyRecommended() {
        if (recommendedChanges().isEmpty()) {
            return false;
        }
        store.style(McaConversationsConfig.DEFAULT_DIALOGUE_MENU_STYLE);
        store.motion(McaConversationsConfig.DEFAULT_MOTION_MODE);
        store.reveal(QuestionReveal.OFF);
        store.hints(true);
        store.save();
        return true;
    }

    private static Component change(String labelKey, Component from, Component to) {
        return Component.translatable("gui.mcaconversations.settings.change",
                Component.translatable(labelKey), from, to);
    }

    public static Component styleName(DialogueMenuStyle style) {
        return Component.translatable(switch (style == null
                ? McaConversationsConfig.DEFAULT_DIALOGUE_MENU_STYLE : style) {
            case RESPONSIVE -> "gui.mcaconversations.responses.style.responsive";
            case MINIMAL -> "gui.mcaconversations.responses.style.minimal";
            case MCA_ORIGINAL -> "gui.mcaconversations.responses.style.mca_original";
        });
    }

    public static Component motionName(MotionMode mode) {
        return Component.translatable(switch (mode == null
                ? McaConversationsConfig.DEFAULT_MOTION_MODE : mode) {
            case FULL -> "gui.mcaconversations.responses.motion.full";
            case REDUCED -> "gui.mcaconversations.responses.motion.reduced";
            case OFF -> "gui.mcaconversations.responses.motion.off";
        });
    }

    public static Component revealName(QuestionReveal reveal) {
        return Component.translatable(reveal == QuestionReveal.FAST
                ? "gui.mcaconversations.settings.reveal_fast"
                : "gui.mcaconversations.settings.reveal_off");
    }

    private static Component onOff(boolean on) {
        return Component.translatable(on
                ? "gui.mcaconversations.settings.on" : "gui.mcaconversations.settings.off");
    }

    private static <T> T next(T[] values, T current, int direction) {
        int at = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == current) {
                at = i;
            }
        }
        return values[Math.floorMod(at + (direction < 0 ? -1 : 1), values.length)];
    }
}
