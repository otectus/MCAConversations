package dev.otectus.mcaconversations.compat.crime;

import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

/**
 * Which MCA: Crime dialogue events this mod voices, and through which say pool (1.8.0). Kept apart from
 * {@link CrimeVoiceResolver} — which implements a Crime interface and so cannot load without Crime — so
 * the mapping is unit-testable and safe to reference anywhere. No {@code dev.otectus.mcacrime.*} here.
 *
 * <p>Only the lines a guard or clerk says <em>to</em> the player in an ordinary exchange are voiced: a
 * challenge, a stand-down, a filed report, an accepted apology. Mugging, ransom, captivity and fence
 * banter stay Crime's own: they are scene lines, not the villager's conversational voice.
 */
public final class CrimeVoiceKeys {

    private CrimeVoiceKeys() {
    }

    /** Maps a Crime dialogue event to a Conversations say-key, or {@code null} for events left to Crime. */
    @Nullable
    public static String phraseFor(@Nullable ResourceLocation event) {
        if (event == null || !"mcacrime".equals(event.getNamespace())) {
            return null;
        }
        return switch (event.getPath()) {
            case "guard_challenge" -> "conversations.crime.guard_challenge";
            case "guard_stand_down" -> "conversations.crime.guard_stand_down";
            case "report_filed" -> "conversations.crime.report_filed";
            case "apology_accepted" -> "conversations.crime.apology_accepted";
            default -> null;
        };
    }
}
