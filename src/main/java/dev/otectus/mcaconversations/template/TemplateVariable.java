package dev.otectus.mcaconversations.template;

import java.util.Locale;
import java.util.Optional;

/**
 * The template variables {@code conversations_say} supports in its {@code "vars"} list. Values become
 * positional args of the translatable line. <b>Convention:</b> MCA's {@code getTranslatable}
 * auto-prepends the player's (spouse-aware) name as {@code %1$s}, so vars listed in JSON fill
 * {@code %2$s}, {@code %3$s}, … in order.
 *
 * <p>Each variable carries the lang key of its fallback text (namespace {@code mcaconversations}),
 * substituted when the value cannot be resolved — a line must never abort or show a blank.
 */
public enum TemplateVariable {
    VILLAGER_NAME("mcaconversations.fallback.someone"),
    SPOUSE_NAME("mcaconversations.fallback.spouse"),
    VILLAGE_NAME("mcaconversations.fallback.village"),
    LAST_GIFT_ITEM("mcaconversations.fallback.something"),
    TIME_OF_DAY("mcaconversations.fallback.time"),
    PROFESSION_NAME("mcaconversations.fallback.profession"),
    WEATHER("mcaconversations.fallback.weather"),
    SEASON("mcaconversations.fallback.season"),
    HOLIDAY("mcaconversations.fallback.holiday"),

    // --- MCA: Reputation (spec 30.7) ---
    // Every one of these resolves to a neutral localized fallback when that mod is absent or the
    // village has no opinion yet, so a line using them never breaks and never reads as an error.
    /** The player's current standing tier with this villager's village, e.g. "Friend". */
    REPUTATION_TIER("mcaconversations.fallback.reputation_tier"),
    /** The numeric standing. Only for lines that genuinely intend to show a number. */
    REPUTATION_SCORE("mcaconversations.fallback.reputation_score"),
    /** The village the standing is with — distinct from VILLAGE_NAME, which is the villager's home. */
    REPUTATION_VILLAGE("mcaconversations.fallback.reputation_village"),
    /** A recent deed this villager actually knows about. */
    REPUTATION_RECENT_DEED("mcaconversations.fallback.reputation_recent_deed"),
    /** A title the player holds with this village. */
    REPUTATION_TITLE("mcaconversations.fallback.reputation_title"),

    // --- MCA: Capitals ---
    // A villager speaking about their court must never say a blank or a raw uuid, so every one of
    // these falls back to something a person would actually say — "the capital", "our words" — when
    // Capitals is absent, the villager is in no capital, or the office is simply vacant.
    /** The capital's name, which is the MCA village name of the village it is seated at. */
    CAPITAL_NAME("mcaconversations.fallback.capital_name"),
    /** The reigning sovereign's name. */
    SOVEREIGN_NAME("mcaconversations.fallback.sovereign_name"),
    /** {@code King} or {@code Queen}, by the sovereign's gender rather than by the speaker's. */
    SOVEREIGN_TITLE("mcaconversations.fallback.sovereign_title"),
    /** The named heir. Falls back rather than inventing one when the succession is unsettled. */
    HEIR_NAME("mcaconversations.fallback.heir_name"),
    /** The speaker's house. */
    HOUSE_NAME("mcaconversations.fallback.house_name"),
    /** The speaker's house words. */
    HOUSE_WORDS("mcaconversations.fallback.house_words"),
    /** The speaker's own court title, localized. */
    VILLAGER_TITLE("mcaconversations.fallback.villager_title"),
    /** A capital this one is at war with. */
    RIVAL_CAPITAL_NAME("mcaconversations.fallback.rival_capital_name"),
    /** A capital this one is allied with. */
    ALLY_CAPITAL_NAME("mcaconversations.fallback.ally_capital_name");

    private final String fallbackKey;

    TemplateVariable(String fallbackKey) {
        this.fallbackKey = fallbackKey;
    }

    public String fallbackKey() {
        return fallbackKey;
    }

    /** JSON name, e.g. {@code villager_name}. */
    public String jsonName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static Optional<TemplateVariable> byJsonName(String name) {
        for (TemplateVariable v : values()) {
            if (v.jsonName().equals(name)) {
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }
}
