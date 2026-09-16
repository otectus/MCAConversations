package dev.otectus.mcaconversations.conversation;

/**
 * The eleven datapack sections this mod owns, with the directory each is read from and the class the
 * boundary note inventories it under. One enum so the coordinator, the diagnostics and the inventory
 * test all name the same eleven things in the same order.
 *
 * <p>The order is the registration order the eleven separate listeners used to have, preserved so a
 * log read against the old boundary note still lines up.
 */
public enum ContentSection {

    CHAT_INTENTS("chat_intents", "ChatIntentLoader"),
    CONVERSATION_CATALOG("conversation_catalog", "ConversationCatalogLoader"),
    CONVERSATION_BEATS("conversation_beats", "BeatContractLoader"),
    PROFESSION_PROFILES("profession_profiles", "ProfessionProfileLoader"),
    INTERIORITY("interiority", "Interiority"),
    IDENTITY_TOKENS("identity_tokens", "IdentityCatalogLoader"),
    CONVERSATION_SCENES("conversation_scenes", "SceneCatalogLoader"),
    VILLAGE_CULTURE("village_culture", "VillageCultureCatalogLoader"),
    EPISODE_TEMPLATES("episode_templates", "NarrativeCatalogLoader$Episodes"),
    THREAD_TEMPLATES("thread_templates", "NarrativeCatalogLoader$Threads"),
    COMMITMENT_TEMPLATES("commitment_templates", "NarrativeCatalogLoader$Commitments");

    private final String directory;
    private final String listener;

    ContentSection(String directory, String listener) {
        this.directory = directory;
        this.listener = listener;
    }

    /** {@code data/<namespace>/<directory>/*.json}. */
    public String directory() {
        return directory;
    }

    /** The class the boundary note inventories this section under, for the {@code listener} field. */
    public String listener() {
        return listener;
    }
}
