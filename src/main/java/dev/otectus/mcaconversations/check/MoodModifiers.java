package dev.otectus.mcaconversations.check;

/**
 * Fixed check adjustments from MCA's mood and this mod's conversation states. Mood names are pinned
 * against MCA's lang ({@code mood.<name>}) — the same seven values {@code ContentLintTest} pins for
 * the native {@code mood} condition. Unknown names are neutral so an MCA mood rework degrades the
 * check instead of breaking it.
 */
public final class MoodModifiers {

    private MoodModifiers() {
    }

    public static int moodAdjust(String moodName) {
        if (moodName == null) {
            return 0;
        }
        return switch (moodName) {
            case "depressed" -> -12;
            case "sad" -> -8;
            case "unhappy" -> -4;
            case "happy" -> 3;
            case "overjoyed" -> 6;
            default -> 0; // passive, fine, unknown
        };
    }

    /** Conversation-state adjustments; states stack (grieving + grateful is still a raw moment). */
    public static int stateAdjust(boolean grieving, boolean annoyedAtPlayer, boolean gratefulToPlayer,
                                  boolean smittenWithPlayer) {
        return stateAdjust(grieving, annoyedAtPlayer, gratefulToPlayer, smittenWithPlayer, false);
    }

    /**
     * Conversation-state adjustments; states stack (grieving + grateful is still a raw moment).
     *
     * <p>{@code proud} is the state a villager is left in by the player finishing a quest for it. It
     * was written, given its own config window, and then read by nothing at all — not even the check
     * resolver, which knew about the other four. It sits below {@code smitten}: being impressed with
     * someone is warmer than gratitude and cooler than infatuation.
     */
    public static int stateAdjust(boolean grieving, boolean annoyedAtPlayer, boolean gratefulToPlayer,
                                  boolean smittenWithPlayer, boolean proudOfPlayer) {
        int adjust = 0;
        if (grieving) {
            adjust -= 12;
        }
        if (annoyedAtPlayer) {
            adjust -= 8;
        }
        if (gratefulToPlayer) {
            adjust += 4;
        }
        if (smittenWithPlayer) {
            adjust += 6;
        }
        if (proudOfPlayer) {
            adjust += 4;
        }
        return adjust;
    }
}
