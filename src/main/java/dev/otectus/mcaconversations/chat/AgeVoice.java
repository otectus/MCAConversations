package dev.otectus.mcaconversations.chat;

import java.util.Optional;
import java.util.Set;

/**
 * Chooses an age-appropriate variant of a chat-mode line family.
 *
 * <p>Chat mode lets a player address any nearby villager in normal chat, and MCA villagers include
 * babies and toddlers. Answering a question about the harvest in an adult voice because the nearest
 * villager happened to be a two-year-old breaks the illusion badly, so the reply family is remapped
 * by age before it is looked up:
 *
 * <ul>
 *   <li><b>baby</b> — never answers. Every {@code chatmode.*} family collapses to
 *       {@link #BABBLE}, so the baby babbles back whatever was said to it.</li>
 *   <li><b>toddler</b> — answers, but in its own words: the families listed in
 *       {@link #TODDLER_VOICED} gain a {@code .toddler} suffix, which the lang files provide as
 *       shorter, simpler lines.</li>
 *   <li>everyone else — the family is used as authored.</li>
 * </ul>
 *
 * <p>Families with no toddler variant fall through unchanged rather than resolving to a missing
 * key, so adding a new {@code chatmode.*} family never has to be paired with a toddler line.
 */
public final class AgeVoice {

    /** The one thing a baby ever "says" in chat mode. */
    public static final String BABBLE = "chatmode.babble";

    /** Chat-mode families that have an authored {@code .toddler} variant. */
    static final Set<String> TODDLER_VOICED = Set.of(
            "chatmode.confused", "chatmode.hint", "chatmode.shrug", "chatmode.clarify",
            "chatmode.dropped", "chatmode.busy", "chatmode.muted", "chatmode.farewell",
            "chatmode.insult", "chatmode.hail", "chatmode.hail_cold", "chatmode.attentive");

    private AgeVoice() {
    }

    /**
     * Maps a chat-mode line family to the variant this villager's age should speak.
     *
     * @param family    the authored family, e.g. {@code chatmode.hint}
     * @param ageGroup  MCA's lowercase age state (see {@code McaCompat.getAgeGroup}); an empty
     *                  value is treated as an adult, so a failed MCA read degrades to normal speech
     * @return the family to look up
     */
    public static String phrase(String family, Optional<String> ageGroup) {
        String age = ageGroup.orElse("");
        if ("baby".equals(age) && family.startsWith("chatmode.")) {
            return BABBLE;
        }
        if ("toddler".equals(age) && TODDLER_VOICED.contains(family)) {
            return family + ".toddler";
        }
        return family;
    }
}
