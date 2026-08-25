package dev.otectus.mcaconversations.conversation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.util.SafeParse;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Datapack loader for {@code data/<namespace>/conversation_catalog/*.json} (plan §4.5), built on the
 * same {@link SimpleJsonResourceReloadListener} pattern as {@code ChatIntentLoader} so third-party
 * packs can add topics by dropping in a file.
 *
 * <p>Each file holds a {@code "topics"} object of {@code id -> entry}. A malformed entry logs and is
 * skipped; a malformed file is skipped; a failure of the whole listener keeps the previous catalog.
 * A datapack reload must never be the thing that stops a world from loading.
 */
public final class ConversationCatalogLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new Gson();
    private static final String DIRECTORY = "conversation_catalog";

    private static volatile ConversationCatalog active = ConversationCatalog.EMPTY;

    public ConversationCatalogLoader() {
        super(GSON, DIRECTORY);
    }

    /** The catalog from the last successful reload (never null; empty before the first load). */
    public static ConversationCatalog active() {
        return active;
    }

    /** Test seam: publish a catalog without a resource reload. */
    public static void setActiveForTesting(ConversationCatalog catalog) {
        active = catalog == null ? ConversationCatalog.EMPTY : catalog;
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
        try {
            // Last id wins across datapacks, mirroring MCA's dialogue merge and the chat-intent loader.
            Map<String, TopicEntry> byId = new LinkedHashMap<>();
            for (Map.Entry<ResourceLocation, JsonElement> file : files.entrySet()) {
                if (!file.getValue().isJsonObject()) {
                    continue;
                }
                JsonObject root = file.getValue().getAsJsonObject();
                if (!root.has("topics") || !root.get("topics").isJsonObject()) {
                    continue;
                }
                for (Map.Entry<String, JsonElement> e : root.getAsJsonObject("topics").entrySet()) {
                    String id = e.getKey();
                    if (!e.getValue().isJsonObject()) {
                        McaConversations.LOGGER.warn("conversation catalog topic '{}' in {} is not an object — skipped",
                                id, file.getKey());
                        continue;
                    }
                    JsonObject topicJson = e.getValue().getAsJsonObject();
                    TopicEntry entry = SafeParse.orNull("conversation_catalog", topicJson,
                            () -> TopicEntry.fromJson(id, topicJson));
                    if (entry != null) {
                        byId.put(id, entry);
                    }
                }
            }
            ConversationCatalog catalog = ConversationCatalog.build(new ArrayList<>(byId.values()));
            active = catalog;
            McaConversations.LOGGER.info("Loaded {} conversation topics from {} catalog file(s).",
                    catalog.size(), files.size());
        } catch (Throwable t) {
            McaConversations.LOGGER.error("Conversation-catalog reload failed; keeping the previous catalog", t);
        }
    }

    /** Convenience for runtime lookups that must never throw. */
    public static java.util.Optional<TopicEntry> topic(String id) {
        try {
            return active.topic(id);
        } catch (Throwable t) {
            return java.util.Optional.empty();
        }
    }

    /** All topic ids currently loaded, for the debug command. */
    public static List<String> topicIds() {
        return active.topics().stream().map(TopicEntry::id).sorted().toList();
    }
}
