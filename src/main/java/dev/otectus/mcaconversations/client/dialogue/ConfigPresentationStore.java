package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.McaConversationsConfig.DialogueMenuStyle;
import dev.otectus.mcaconversations.McaConversationsConfig.MotionMode;
import dev.otectus.mcaconversations.McaConversationsConfig.QuestionReveal;
import net.minecraftforge.fml.ModList;

/**
 * The pane's binding to the Forge client configuration: {@code set} on the value, then one
 * {@code save} on the spec, which is how a {@code ForgeConfigSpec} expects to be written at runtime.
 *
 * <p>Every read is defensive. The pane can be opened before the client config has loaded — a config
 * screen is a screen like any other — and a pane that threw there would take the dialogue down with
 * it, so an unreadable value reads as the recommended one instead.
 */
public final class ConfigPresentationStore implements PresentationSettings.Store {

    public static final ConfigPresentationStore INSTANCE = new ConfigPresentationStore();

    private ConfigPresentationStore() {
    }

    @Override
    public DialogueMenuStyle style() {
        try {
            return McaConversationsConfig.CLIENT.dialogueMenuStyle.get();
        } catch (Throwable ignored) {
            return McaConversationsConfig.DEFAULT_DIALOGUE_MENU_STYLE;
        }
    }

    @Override
    public void style(DialogueMenuStyle value) {
        write(() -> McaConversationsConfig.CLIENT.dialogueMenuStyle.set(value));
    }

    @Override
    public MotionMode motion() {
        return ClientChoiceController.motionMode();
    }

    @Override
    public void motion(MotionMode value) {
        write(() -> McaConversationsConfig.CLIENT.motionMode.set(value));
    }

    @Override
    public QuestionReveal reveal() {
        return ClientChoiceController.questionRevealMode();
    }

    @Override
    public void reveal(QuestionReveal value) {
        write(() -> McaConversationsConfig.CLIENT.questionRevealMode.set(value));
    }

    @Override
    public double volume() {
        return ClientChoiceController.uiSoundVolume();
    }

    @Override
    public void volume(double value) {
        write(() -> McaConversationsConfig.CLIENT.uiSoundVolume.set(value));
    }

    @Override
    public boolean hints() {
        return ClientChoiceController.showHints();
    }

    @Override
    public void hints(boolean value) {
        write(() -> McaConversationsConfig.CLIENT.showResponseControlHints.set(value));
    }

    @Override
    public boolean legacyOverride() {
        try {
            return !McaConversationsConfig.CLIENT.numberedResponses.get();
        } catch (Throwable ignored) {
            return false;
        }
    }

    @Override
    public boolean townsteadPresent() {
        try {
            return ModList.get() != null && ModList.get().isLoaded("townstead");
        } catch (Throwable ignored) {
            return false;
        }
    }

    @Override
    public void save() {
        write(McaConversationsConfig.CLIENT_SPEC::save);
    }

    private static void write(Runnable action) {
        try {
            action.run();
        } catch (Throwable t) {
            // A setting that cannot be stored is a setting that does not change; it is never a
            // reason to break the conversation the pane was opened from.
            McaConversations.LOGGER.debug("presentation setting could not be written", t);
        }
    }
}
