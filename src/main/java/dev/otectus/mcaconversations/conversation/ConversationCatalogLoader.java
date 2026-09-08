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
            // Last id wins across datapacks, mirroring MCA's dialogue merge and the chat-intent
            // loader — but "last" has to mean something. The map arrives in whatever order the pack
            // stack produced, so sort it, as the scene loader does, and the winner of a collision is
            // the same file on every load and every machine.
            Map<String, TopicEntry> byId = new LinkedHashMap<>();
            Map<String, ResourceLocation> declaredIn = new LinkedHashMap<>();
            Map<String, List<ResourceLocation>> collisions = new LinkedHashMap<>();
            List<ResourceLocation> ordered = new ArrayList<>(files.keySet());
            ordered.sort(ResourceLocation::compareTo);

            for (ResourceLocation location : ordered) {
                JsonElement value = files.get(location);
                if (value == null || !value.isJsonObject()) {
                    continue;
                }
                JsonObject root = value.getAsJsonObject();
                if (!root.has("topics") || !root.get("topics").isJsonObject()) {
                    continue;
                }
                for (Map.Entry<String, JsonElement> e : root.getAsJsonObject("topics").entrySet()) {
                    String id = e.getKey();
                    if (!e.getValue().isJsonObject()) {
                        McaConversations.LOGGER.warn("conversation catalog topic '{}' in {} is not an object — skipped",
                                id, location);
                        continue;
                    }
                    JsonObject topicJson = e.getValue().getAsJsonObject();
                    TopicEntry entry = SafeParse.orNull("conversation_catalog", topicJson,
                            () -> TopicEntry.fromJson(id, topicJson));
                    if (entry == null) {
                        continue;
                    }
                    ResourceLocation previous = declaredIn.put(id, location);
                    if (previous != null) {
                        collisions.computeIfAbsent(id, ignored -> {
                            List<ResourceLocation> all = new ArrayList<>();
                            all.add(previous);
                            return all;
                        }).add(location);
                    }
                    byId.put(id, entry);
                }
            }
            // A topic id is meant to be declared in exactly one place (DATAPACK.md, "the conversation
            // catalog"), so two files claiming one id is an authoring mistake rather than an override
            // idiom — a pack overrides a shipped topic by replacing that topic's file path. One line
            // per colliding id, after the merge, so it can name the file that actually won.
            collisions.forEach((id, sources) -> McaConversations.LOGGER.warn(
                    "conversation catalog topic '{}' is declared in {} — {} wins (last in sorted order)",
                    id, sources, declaredIn.get(id)));
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
