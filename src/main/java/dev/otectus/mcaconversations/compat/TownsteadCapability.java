package dev.otectus.mcaconversations.compat;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * One independently-bindable Townstead feature (Townstead spec §5.3).
 *
 * <p>The unit of failure for the whole integration: a binding miss disables exactly the capability
 * that needed it, makes the content declaring it score {@code 0}, and produces one actionable
 * diagnostic — it never disables the bridge and never throws.
 *
 * <p>Datapacks gate on these ids through {@code conversations_townstead_available}, so the lowercase
 * names are part of the public pack contract and must not be renamed without a migration note.
 */
public enum TownsteadCapability {

    /** The villager snapshot: identity, life stage, age, personality id, heritage. */
    READ_VILLAGER,
    /** Hunger, thirst, fatigue, collapse and the gated flag. */
    READ_NEEDS,
    /** Shift mode, template, current and planned activity. */
    READ_SCHEDULE,
    /** World day, season, weekday and the active calendar profile. */
    READ_CALENDAR,
    /** The registered building at a position: type, size, bounds, owning village. */
    READ_BUILDING,
    /** A root (species/ancestry/lineage) definition and its life cycle. */
    READ_ROOT,
    /** A custom personality definition and the MCA voice it is based on. */
    READ_PERSONALITY,
    /** The set of profession skills a villager has learned. Read-only, always. */
    READ_SKILLS,
    /** Village spirit totals, tier and classification. */
    READ_SPIRIT,
    /** Townstead's full resolved context-tag vocabulary for a villager. */
    READ_CONTEXT_TAGS,

    /**
     * Playing an authored, heart-neutral reaction on a conversation outcome.
     *
     * <p>Binding this is necessary but not sufficient. Townstead can only play a reaction through a
     * registered backend, and it ships exactly one, for Emotecraft; without that mod the registry is
     * empty and every reaction is inert however well the dispatcher bound. Backends register at
     * runtime, after this probe runs, so the count is checked at the moment of firing instead and
     * reported by the status command. An empty registry means "no reaction", never an error.
     */
    FIRE_REACTION,
    /** Telling Townstead the measured MCA heart delta after Conversations applies affection. */
    MARK_HEART_CHANGE,
    /** Marking a typed-chat conversation open and closed so Townstead emits dialogue context tags. */
    TRACK_DIALOGUE,
    /** Reading Townstead's reaction lock, so chat attention never fights a playing animation. */
    REACTION_LOCK,
    /** Supplying Conversations-owned emotion tags inside Townstead's RPG typewriter. Client only. */
    RPG_EMOTION_TAGS;

    private static final Map<String, TownsteadCapability> BY_NAME = Arrays.stream(values())
            .collect(Collectors.toUnmodifiableMap(TownsteadCapability::key, Function.identity()));

    /** The lowercase id a datapack writes. */
    public String key() {
        return name().toLowerCase(Locale.ROOT);
    }

    /**
     * Accepts the constant name in either case, so a pack may write {@code READ_NEEDS} or
     * {@code read_needs}. An unknown id yields empty, which the parser turns into a definition that
     * never matches — a capability gate that quietly matched everything would be worse than absent.
     */
    public static Optional<TownsteadCapability> byKey(String raw) {
        return raw == null ? Optional.empty()
                : Optional.ofNullable(BY_NAME.get(raw.trim().toLowerCase(Locale.ROOT)));
    }
}
