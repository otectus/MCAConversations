package dev.otectus.mcaconversations.village;

import java.util.Locale;
import java.util.Optional;

/**
 * The six things a village has one of (spec §17.3).
 *
 * <p>A village culture is deliberately tiny. Six tokens, one per family, is enough for residents to
 * have common ground — the same festival, the same landmark, the same argument going on — without
 * becoming a second personality system fighting the first. Anything larger would start deciding what
 * individual villagers are like, which is the identity layer's job.
 */
public enum CultureFamily {

    /** Something the village does that the next village along does not. */
    TRADITION("tradition"),

    /** What this village is proud of being. Residents may still fail to live up to it. */
    VALUE("value"),

    /** The work worry everybody here shares, whatever their trade. */
    WORK_CONCERN("work_concern"),

    /** The place people give directions by. */
    LANDMARK("landmark"),

    /** What happens on the day of the year that matters here. */
    FESTIVAL("festival"),

    /** The argument currently going on. The one token a village is expected to be split about. */
    DEBATE("debate");

    private final String key;

    CultureFamily(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /** True when residents are expected to disagree about this, so a scene must not assume assent. */
    public boolean isContested() {
        return this == DEBATE;
    }

    public static Optional<CultureFamily> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (CultureFamily family : values()) {
            if (family.key.equals(normalized)) {
                return Optional.of(family);
            }
        }
        return Optional.empty();
    }
}
