package dev.otectus.mcaconversations.scene;

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
 * Datapack loader for {@code data/<namespace>/conversation_scenes/*.json} (spec §10.4, §22.3).
 *
 * <p>Same shape and the same failure policy as every other catalog in the mod: a malformed scene logs
 * and is skipped, and a failure of the whole listener keeps the previous catalog. A dangling fallback
 * is warned rather than rejected — losing every scene because one names a missing fallback would be a
 * far worse outcome than losing the one.
 */
public final class SceneCatalogLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new Gson();
    private static final String DIRECTORY = "conversation_scenes";

    private static volatile SceneCatalog active = SceneCatalog.EMPTY;

    public SceneCatalogLoader() {
        super(GSON, DIRECTORY);
    }

    /** The catalog from the last successful reload; never null, empty before the first load. */
    public static SceneCatalog active() {
        return active;
    }

    /** Test seam: publish a catalog without a resource reload. */
    public static void setActiveForTesting(SceneCatalog catalog) {
        active = catalog == null ? SceneCatalog.EMPTY : catalog;
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager,
                         ProfilerFiller profiler) {
        try {
            Map<String, SceneDefinition> byId = new LinkedHashMap<>();
            List<ResourceLocation> ordered = new ArrayList<>(files.keySet());
            ordered.sort(ResourceLocation::compareTo);

            for (ResourceLocation location : ordered) {
                JsonElement value = files.get(location);
                if (value == null || !value.isJsonObject()) {
                    continue;
                }
                JsonObject root = value.getAsJsonObject();
                if (!root.has("scenes") || !root.get("scenes").isJsonObject()) {
                    continue;
                }
                for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject("scenes").entrySet()) {
                    String id = entry.getKey();
                    if (!entry.getValue().isJsonObject()) {
                        McaConversations.LOGGER.warn("scene '{}' in {} is not an object — skipped",
                                id, location);
                        continue;
                    }
                    JsonObject json = entry.getValue().getAsJsonObject();
                    SceneDefinition scene = SafeParse.orNull("conversation_scenes", json,
                            () -> SceneDefinition.fromJson(id, json));
                    if (scene != null) {
                        byId.put(scene.id(), scene);
                    }
                }
            }

            SceneCatalog built = SafeParse.orNull("conversation_scenes", new JsonObject(),
                    () -> SceneCatalog.build(new ArrayList<>(byId.values())));
            if (built == null) {
                McaConversations.LOGGER.error("Scenes collided; keeping the previous {}.", active.size());
                return;
            }
            active = built;
            built.danglingReferences().forEach(problem ->
                    McaConversations.LOGGER.warn("scene reference unresolved: {}", problem));
            McaConversations.LOGGER.info("Loaded {} conversation scene(s) from {} file(s).",
                    built.size(), files.size());
        } catch (Throwable t) {
            McaConversations.LOGGER.error("Scene reload failed; keeping the previous catalog", t);
        }
    }
}
