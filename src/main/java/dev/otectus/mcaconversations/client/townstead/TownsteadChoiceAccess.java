package dev.otectus.mcaconversations.client.townstead;

import dev.otectus.mcaconversations.McaConversations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** Cached, input-time-only access to Townstead's panel and native selection routine. */
public final class TownsteadChoiceAccess {

    private static Class<?> screenClass;
    private static Field choicePanelField;
    private static Method handleChoiceSelection;

    private TownsteadChoiceAccess() {
    }

    public static boolean selectVisibleDigit(Object screen, int digit) {
        if (screen == null || digit < 1 || digit > 9 || !resolve(screen.getClass())) {
            return false;
        }
        try {
            Object panel = choicePanelField.get(screen);
            if (!(panel instanceof NumberedChoicePanelBridge bridge)
                    || !bridge.mcaconversations$selectVisibleDigit(digit)) {
                return false;
            }
            handleChoiceSelection.invoke(screen);
            return true;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Townstead numbered choice adapter failed; using native controls", t);
            return false;
        }
    }

    private static synchronized boolean resolve(Class<?> type) {
        if (type == screenClass) {
            return choicePanelField != null && handleChoiceSelection != null;
        }
        screenClass = type;
        choicePanelField = null;
        handleChoiceSelection = null;
        try {
            Field panel = type.getDeclaredField("choicePanel");
            Method selection = type.getDeclaredMethod("handleChoiceSelection");
            panel.setAccessible(true);
            selection.setAccessible(true);
            choicePanelField = panel;
            handleChoiceSelection = selection;
            return true;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Townstead choice members changed; numbered decoration disabled", t);
            return false;
        }
    }
}
