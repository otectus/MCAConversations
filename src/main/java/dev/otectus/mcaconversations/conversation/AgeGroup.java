package dev.otectus.mcaconversations.conversation;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * MCA's age vocabulary as a closed type, plus {@link #UNKNOWN} for a villager whose age state could
 * not be read (MCA absent, an {@code UNASSIGNED} state, a reshaped jar).
 *
 * <p>Content may only author the four groups a topic can be offered to; {@code baby} exists here
 * because the runtime has to be able to name what it read, and {@code UNKNOWN} exists because an
 * unreadable age must never satisfy a positive allow-list. Both are rejected by {@link #parse}.
 */
public enum AgeGroup {

    BABY("baby", false),
    TODDLER("toddler", true),
    CHILD("child", true),
    TEEN("teen", true),
    ADULT("adult", true),
    /** The age could not be read. Never authorable and never allowed by an allow-list. */
    UNKNOWN("unknown", false);

    private static final Map<String, AgeGroup> BY_NAME = index();

    private final String id;
    private final boolean authorable;

    AgeGroup(String id, boolean authorable) {
        this.id = id;
        this.authorable = authorable;
    }

    /** The lowercase name MCA and the catalog both use. */
    public String id() {
        return id;
    }

    /** True for the four groups a catalog {@code ages} list may name. */
    public boolean authorable() {
        return authorable;
    }

    /** Resolves an authored age value. Empty for {@code baby}, {@code unknown} and anything else. */
    public static Optional<AgeGroup> parse(String raw) {
        if (raw == null) {
            return Optional.empty();
        }
        AgeGroup group = BY_NAME.get(raw.trim().toLowerCase(Locale.ROOT));
        return group != null && group.authorable ? Optional.of(group) : Optional.empty();
    }

    /**
     * Maps what {@code McaCompat.getAgeGroup} read — MCA's {@code AgeState} name, lowercased — onto
     * this enum. Null, {@code unassigned} and any name a future MCA adds become {@link #UNKNOWN}.
     */
    public static AgeGroup fromMca(String ageState) {
        if (ageState == null) {
            return UNKNOWN;
        }
        AgeGroup group = BY_NAME.get(ageState.trim().toLowerCase(Locale.ROOT));
        return group == null || group == UNKNOWN ? UNKNOWN : group;
    }

    private static Map<String, AgeGroup> index() {
        Map<String, AgeGroup> byName = new HashMap<>();
        for (AgeGroup group : values()) {
            byName.put(group.id, group);
        }
        return Map.copyOf(byName);
    }
}
