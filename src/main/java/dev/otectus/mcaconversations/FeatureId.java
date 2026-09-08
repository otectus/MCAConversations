package dev.otectus.mcaconversations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BooleanSupplier;

/**
 * The closed set of feature ids the {@code conversations_enabled}/{@code conversations_disabled}
 * dialogue conditions and the runtime gates may name.
 *
 * <p>Each constant owns its canonical id, any historical aliases, and the exact config expression
 * that decides it — including the master-switch conjunctions, so {@code capitals.enabled=false}
 * silences every {@code capital_*} sub-feature and {@code dynamic.enabled=false} silences the whole
 * living-histories layer without touching the individual flags.
 *
 * <p><b>An unknown id is an invalid reference, never "enabled".</b> A content typo used to fall
 * through to a default of {@code true}, which made a {@code conversations_disabled} sink on it
 * impossible to fire and hid the typo forever. Now it resolves to nothing at all: both conditions
 * score 0 and the mistake is reported once at WARN.
 */
public enum FeatureId {

    TOPICS("topics", () -> McaConversationsConfig.COMMON.enableTopics.get()),
    STATES("states", () -> McaConversationsConfig.COMMON.enableStates.get()),
    TEMPLATES("templates", () -> McaConversationsConfig.COMMON.enableTemplates.get()),
    GOSSIP("gossip", () -> McaConversationsConfig.COMMON.enableGossip.get()),
    QUESTS("quests", () -> McaConversationsConfig.COMMON.enableQuests.get()),
    WORLD("world", () -> McaConversationsConfig.COMMON.enableWeatherLines.get()),
    // "seasons" and "holidays" have their own flags; before they were listed they fell through to
    // the default and scored as enabled forever, so a sink on either could never fire.
    SEASONS("seasons", () -> McaConversationsConfig.COMMON.enableSeasonLines.get()),
    HOLIDAYS("holidays", () -> McaConversationsConfig.COMMON.enableHolidayLines.get()),
    DISPOSITIONS("dispositions", () -> McaConversationsConfig.COMMON.enableDispositions.get()),
    CHECKS("checks", () -> McaConversationsConfig.COMMON.enableChecks.get()),
    BRANCHING("branching", () -> McaConversationsConfig.COMMON.enableBranching.get()),
    CHAT("chat", () -> McaConversationsConfig.COMMON.enableChatMode.get()),
    TOWNSTEAD("townstead", () -> McaConversationsConfig.COMMON.townsteadEnabled.get()),

    // MCA Capitals. Each sub-feature is gated by the master switch as well as its own.
    CAPITALS("capitals", () -> McaConversationsConfig.COMMON.capitalsEnabled.get()),
    CAPITAL_TOPICS("capital_topics", () -> McaConversationsConfig.COMMON.capitalsEnabled.get()
            && McaConversationsConfig.COMMON.capitalTopicsEnabled.get()),
    CAPITAL_NEWS("capital_news", () -> McaConversationsConfig.COMMON.capitalsEnabled.get()
            && McaConversationsConfig.COMMON.capitalNewsEnabled.get()),
    CAPITAL_DIPLOMACY("capital_diplomacy", () -> McaConversationsConfig.COMMON.capitalsEnabled.get()
            && McaConversationsConfig.COMMON.capitalDiplomacyTalkEnabled.get()),

    // Living-histories features. Each is gated by the master switch as well as its own.
    DYNAMIC("dynamic", () -> McaConversationsConfig.COMMON.dynamicEnabled.get()),
    IDENTITY("identity", () -> McaConversationsConfig.COMMON.dynamicEnabled.get()
            && McaConversationsConfig.COMMON.identityEnabled.get()),
    EPISODES("episodes", () -> McaConversationsConfig.COMMON.dynamicEnabled.get()
            && McaConversationsConfig.COMMON.episodesEnabled.get()
            && McaConversationsConfig.COMMON.historyEnabled.get()),
    HISTORY("history", () -> McaConversationsConfig.COMMON.historyEnabled.get()),
    SOCIAL_OPINIONS("social_opinions", () -> McaConversationsConfig.COMMON.dynamicEnabled.get()
            && McaConversationsConfig.COMMON.socialOpinionsEnabled.get()),
    VILLAGE_CULTURE("village_culture", () -> McaConversationsConfig.COMMON.dynamicEnabled.get()
            && McaConversationsConfig.COMMON.villageCultureEnabled.get()),
    GROUP("group", () -> McaConversationsConfig.COMMON.dynamicEnabled.get()
            && McaConversationsConfig.COMMON.groupEnabled.get());

    private static final Map<String, FeatureId> BY_NAME = index();

    private final String id;
    private final Set<String> aliases;
    private final BooleanSupplier enabled;

    FeatureId(String id, BooleanSupplier enabled) {
        this(id, Set.of(), enabled);
    }

    FeatureId(String id, Set<String> aliases, BooleanSupplier enabled) {
        this.id = id;
        this.aliases = aliases;
        this.enabled = enabled;
    }

    /** The canonical id as content writes it. */
    public String id() {
        return id;
    }

    /** Historical spellings that still resolve to this feature. Empty for every current id. */
    public Set<String> aliases() {
        return aliases;
    }

    /**
     * Reads the config expression for this feature. Throws only what a {@code ForgeConfigSpec} read
     * throws before its file is loaded; callers that cannot take that use
     * {@link McaConversationsConfig#dynamicFeature(FeatureId, boolean)}.
     */
    public boolean read() {
        return enabled.getAsBoolean();
    }

    /** Resolves a raw content id, canonical spelling first, then aliases. Empty when unknown. */
    public static Optional<FeatureId> parse(String raw) {
        if (raw == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(BY_NAME.get(raw.trim().toLowerCase(Locale.ROOT)));
    }

    private static Map<String, FeatureId> index() {
        Map<String, FeatureId> byName = new HashMap<>();
        for (FeatureId feature : values()) {
            byName.put(feature.id, feature);
            for (String alias : feature.aliases) {
                byName.put(alias, feature);
            }
        }
        return Map.copyOf(byName);
    }

    /** Every canonical id, for lint and the debug command. */
    public static Set<String> ids() {
        return Arrays.stream(values()).map(FeatureId::id).collect(java.util.stream.Collectors.toUnmodifiableSet());
    }
}
