package dev.otectus.mcaconversations.chat;

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
 * Datapack loader for {@code data/<namespace>/chat_intents/*.json} (spec §7). A
 * {@link SimpleJsonResourceReloadListener} — the mod's first — registered via {@code
 * AddReloadListenerEvent}, so intents merge across namespaces exactly like MCA's {@code dialogues/}
 * and modpacks can add or override them. Every entry parses through {@link SafeParse}: one malformed
 * intent logs a warning and is skipped, the reload never fails.
 *
 * <p>Builds an immutable {@link IntentIndex} and publishes it to a volatile holder the (server-thread)
 * matcher reads. Keeps MCA out of {@code chat/}: it touches only Minecraft resource types and the pure
 * intent classes; binding validity against real dialogue ids is a build-time concern of
 * {@code ChatIntentLintTest}.
 */
public final class ChatIntentLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new Gson();
    private static final String DIRECTORY = "chat_intents";

    private static volatile IntentIndex active = IntentIndex.build(List.of(), SynonymTable.EMPTY);

    public ChatIntentLoader() {
        super(GSON, DIRECTORY);
    }

    /** The intent index from the last successful reload (never null; empty before the first load). */
    public static IntentIndex active() {
        return active;
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
        try {
            // Pass 1: gather synonym classes across every file (they merge globally, §7.2).
            SynonymTable.Builder synonyms = SynonymTable.builder();
            for (JsonElement el : files.values()) {
                if (!el.isJsonObject()) {
                    continue;
                }
                JsonObject obj = el.getAsJsonObject();
                if (obj.has("synonyms") && obj.get("synonyms").isJsonObject()) {
                    for (Map.Entry<String, JsonElement> e : obj.getAsJsonObject("synonyms").entrySet()) {
                        List<String> aliases = new ArrayList<>();
                        if (e.getValue().isJsonArray()) {
                            e.getValue().getAsJsonArray().forEach(a -> aliases.add(a.getAsString()));
                        }
                        synonyms.addClass(e.getKey(), aliases);
                    }
                }
            }
            SynonymTable syn = synonyms.build();

            // Pass 2: parse intents (last id wins across datapacks, mirroring MCA's dialogue merge).
            Map<String, IntentBinding> byId = new LinkedHashMap<>();
            for (Map.Entry<ResourceLocation, JsonElement> file : files.entrySet()) {
                if (!file.getValue().isJsonObject()) {
                    continue;
                }
                JsonObject obj = file.getValue().getAsJsonObject();
                if (!obj.has("intents") || !obj.get("intents").isJsonObject()) {
                    continue;
                }
                for (Map.Entry<String, JsonElement> e : obj.getAsJsonObject("intents").entrySet()) {
                    String id = e.getKey();
                    if (!e.getValue().isJsonObject()) {
                        McaConversations.LOGGER.warn("chat intent '{}' in {} is not an object — skipped",
                                id, file.getKey());
                        continue;
                    }
                    JsonObject intentJson = e.getValue().getAsJsonObject();
                    IntentBinding binding = SafeParse.orNull("chat_intent", intentJson,
                            () -> IntentBinding.fromJson(id, intentJson));
                    if (binding != null) {
                        byId.put(id, binding);
                    }
                }
            }

            IntentIndex index = IntentIndex.build(new ArrayList<>(byId.values()), syn);
            active = index;
            McaConversations.LOGGER.info("Loaded {} chat intents from {} file(s).", index.size(), files.size());
        } catch (Throwable t) {
            // A whole-listener failure must not abort the datapack reload; keep the previous index.
            McaConversations.LOGGER.error("Chat-intent reload failed; keeping the previous index", t);
        }
    }
}
