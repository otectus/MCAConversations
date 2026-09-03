package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Optional;

/**
 * The emotional sign of a villager line (spec §5.2).
 *
 * <p>Polarity is deliberately coarse. Its job is not to describe a feeling but to stop the two
 * mistakes that ruin an exchange: offering sympathy to someone who is happy, and offering
 * congratulations to someone who is not. {@link #ACUTE} is separated from {@link #NEGATIVE} because
 * grief, fear and fresh injury are not "a bad mood with a larger number" — humour and flirtation are
 * forbidden outright at an acute beat, however playful the villager normally is (spec §9.5).
 */
public enum Polarity {

    POSITIVE("positive"),
    NEUTRAL("neutral"),
    /** Genuinely two-sided: proud but tired, fond but wary. Both warm and sober replies fit. */
    MIXED("mixed"),
    NEGATIVE("negative"),
    /** Grief, fear, fresh harm. Levity is never appropriate here. */
    ACUTE("acute");

    private final String key;

    Polarity(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /** True when celebratory or congratulatory replies make sense. */
    public boolean isGlad() {
        return this == POSITIVE;
    }

    /** True when the line names something wrong, so sympathy and practical help are on the table. */
    public boolean isTroubled() {
        return this == NEGATIVE || this == ACUTE;
    }

    public static Optional<Polarity> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (Polarity polarity : values()) {
            if (polarity.key.equals(normalized)) {
                return Optional.of(polarity);
            }
        }
        return Optional.empty();
    }
}
