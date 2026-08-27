package dev.otectus.mcaconversations.village;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * Datapack loader for {@code data/<namespace>/village_culture/*.json} (spec §22.3).
 *
 * <p>Same shape and the same failure policy as the identity catalog it is modelled on: a malformed
 * token logs and is skipped, and a failure of the listener keeps the previous catalog, because a
 * datapack reload must never be the thing that stops a world loading.
 *
 * <p>Aliases matter more here than almost anywhere else. A village's culture is stored as six token
 * ids, so renaming a token without an alias would leave every village that drew it holding a name
 * nothing answers to. The alias table is how a pack renames one without changing a single village.
 *
 * <pre>{@code
 * {
 *   "tokens": {
 *     "first_frost_supper": {
 *       "family": "festival",
 *       "weight": 12,
 *       "endorsed_by": ["tradition"],
 *       "questioned_by": ["independence"]
 *     }
 *   },
 *   "aliases": {"frost_supper": "first_frost_supper"}
 * }
 * }</pre>
 */
public final class VillageCultureCatalogLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new Gson();
    private static final String DIRECTORY = "village_culture";

    private static volatile VillageCultureCatalog active = VillageCultureCatalog.EMPTY;

    public VillageCultureCatalogLoader() {
        super(GSON, DIRECTORY);
    }

    /** The catalog from the last successful reload; never null, empty before the first load. */
    public static VillageCultureCatalog active() {
        return active;
    }

    /** Test seam: publish a catalog without a resource reload. */
    public static void setActiveForTesting(VillageCultureCatalog catalog) {
        active = catalog == null ? VillageCultureCatalog.EMPTY : catalog;
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager,
                         ProfilerFiller profiler) {
        try {
            Map<String, CultureToken> tokens = new LinkedHashMap<>();
            Map<String, String> aliases = new TreeMap<>();

            // Sorted by file id so "last one wins" across namespaces is a stated rule rather than
            // whatever order the resource manager happened to hand back.
            List<ResourceLocation> ordered = new ArrayList<>(files.keySet());
            ordered.sort(ResourceLocation::compareTo);
            for (ResourceLocation file : ordered) {
                JsonElement element = files.get(file);
                if (element == null || !element.isJsonObject()) {
                    continue;
                }
                JsonObject root = element.getAsJsonObject();
                readTokens(file, root, tokens);
                readAliases(root, aliases);
            }
            active = new VillageCultureCatalog(tokens, aliases);
            McaConversations.LOGGER.debug("village culture catalog: {} tokens", active.size());
        } catch (Throwable t) {
            McaConversations.LOGGER.warn("village culture reload failed; keeping previous catalog", t);
        }
    }

    private static void readTokens(ResourceLocation file, JsonObject root,
                                   Map<String, CultureToken> into) {
        if (!root.has("tokens") || !root.get("tokens").isJsonObject()) {
            return;
        }
        for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject("tokens").entrySet()) {
            if (!entry.getValue().isJsonObject()) {
                continue;
            }
            String id = entry.getKey().trim().toLowerCase(Locale.ROOT);
            try {
                CultureToken token = CultureToken.fromJson(id, entry.getValue().getAsJsonObject());
                if (token == null || !token.isWellFormed()) {
                    McaConversations.LOGGER.warn(
                            "village culture token '{}' in {} is malformed; skipping", id, file);
                    continue;
                }
                into.put(token.id(), token);
            } catch (Throwable t) {
                McaConversations.LOGGER.warn(
                        "village culture token '{}' in {} failed to parse; skipping", id, file, t);
            }
        }
    }

    private static void readAliases(JsonObject root, Map<String, String> into) {
        if (!root.has("aliases") || !root.get("aliases").isJsonObject()) {
            return;
        }
        for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject("aliases").entrySet()) {
            if (!entry.getValue().isJsonPrimitive()) {
                continue;
            }
            into.put(entry.getKey().trim().toLowerCase(Locale.ROOT),
                    entry.getValue().getAsString().trim().toLowerCase(Locale.ROOT));
        }
    }
}
