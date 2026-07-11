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
        return adjust;
    }
}
