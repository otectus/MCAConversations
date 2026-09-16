package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.McaConversationsConfig.DialogueMenuStyle;
import dev.otectus.mcaconversations.McaConversationsConfig.MotionMode;
import dev.otectus.mcaconversations.McaConversationsConfig.QuestionReveal;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** What the pane says, what the reset would change, and what the legacy override does to the style. */
class PresentationSettingsTest {

    private static final class FakeStore implements PresentationSettings.Store {
        DialogueMenuStyle style = McaConversationsConfig.DEFAULT_DIALOGUE_MENU_STYLE;
        MotionMode motion = McaConversationsConfig.DEFAULT_MOTION_MODE;
        QuestionReveal reveal = QuestionReveal.OFF;
        double volume = 0.65D;
        boolean hints = true;
        boolean legacy;
        boolean townstead;
        int saves;

        @Override public DialogueMenuStyle style() { return style; }
        @Override public void style(DialogueMenuStyle value) { style = value; }
        @Override public MotionMode motion() { return motion; }
        @Override public void motion(MotionMode value) { motion = value; }
        @Override public QuestionReveal reveal() { return reveal; }
        @Override public void reveal(QuestionReveal value) { reveal = value; }
        @Override public double volume() { return volume; }
        @Override public void volume(double value) { volume = value; }
        @Override public boolean hints() { return hints; }
        @Override public void hints(boolean value) { hints = value; }
        @Override public boolean legacyOverride() { return legacy; }
        @Override public boolean townsteadPresent() { return townstead; }
        @Override public void save() { saves++; }
    }

    private static String key(Component component) {
        return component.getContents() instanceof TranslatableContents contents
                ? contents.getKey() : component.getString();
    }

    @Test
    void theStyleRowStatesBothNumbersWhileTheLegacyOverrideIsInForce() {
        FakeStore store = new FakeStore();
        store.style = DialogueMenuStyle.MINIMAL;
        store.legacy = true;
        PresentationSettings settings = new PresentationSettings(store);
        Component value = settings.styleValue();
        assertEquals("gui.mcaconversations.settings.style_overridden", key(value));
        Object[] args = ((TranslatableContents) value.getContents()).getArgs();
        assertEquals("gui.mcaconversations.responses.style.minimal", key((Component) args[0]));
        assertEquals("gui.mcaconversations.responses.style.mca_original", key((Component) args[1]),
                "the effective style has to be the one MCA is actually drawing");
    }

    @Test
    void withoutTheOverrideTheStyleRowIsSimplyTheConfiguredStyle() {
        FakeStore store = new FakeStore();
        store.style = DialogueMenuStyle.RESPONSIVE;
        assertEquals("gui.mcaconversations.responses.style.responsive",
                key(new PresentationSettings(store).styleValue()));
    }

    @Test
    void resetListsOnlyWhatItWouldActuallyChange() {
        FakeStore store = new FakeStore();
        PresentationSettings settings = new PresentationSettings(store);
        assertTrue(settings.recommendedChanges().isEmpty());
        store.style = DialogueMenuStyle.RESPONSIVE;
        store.motion = MotionMode.FULL;
        List<Component> changes = settings.recommendedChanges();
        assertEquals(2, changes.size());
        assertTrue(settings.applyRecommended());
        assertEquals(McaConversationsConfig.DEFAULT_DIALOGUE_MENU_STYLE, store.style);
        assertEquals(McaConversationsConfig.DEFAULT_MOTION_MODE, store.motion);
        assertTrue(settings.recommendedChanges().isEmpty());
        assertFalse(settings.applyRecommended(), "nothing to change is not a change");
    }

    @Test
    void cyclingWritesThroughTheStoreAndSavesOnce() {
        FakeStore store = new FakeStore();
        PresentationSettings settings = new PresentationSettings(store);
        int hintsRow = rowIndex(settings, "hints");
        assertTrue(settings.cycle(hintsRow, 1));
        assertFalse(store.hints);
        assertEquals(1, store.saves);
    }

    @Test
    void volumeStopsAtItsBoundsRatherThanWrapping() {
        FakeStore store = new FakeStore();
        store.volume = 0.0D;
        PresentationSettings settings = new PresentationSettings(store);
        int volumeRow = rowIndex(settings, "volume");
        assertFalse(settings.cycle(volumeRow, -1));
        assertEquals(0.0D, store.volume);
        assertTrue(settings.cycle(volumeRow, 1));
        assertEquals(0.05D, store.volume, 1.0E-9D);
    }

    @Test
    void townsteadOwnershipIsStatedOnlyWhenTownsteadIsPresent() {
        FakeStore store = new FakeStore();
        assertNull(new PresentationSettings(store).townsteadNote());
        store.townstead = true;
        assertEquals("gui.mcaconversations.settings.townstead",
                key(new PresentationSettings(store).townsteadNote()));
    }

    private static int rowIndex(PresentationSettings settings, String id) {
        List<PresentationSettings.Row> rows = settings.rows();
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).id().equals(id)) {
                return i;
            }
        }
        throw new AssertionError("no row " + id);
    }
}
