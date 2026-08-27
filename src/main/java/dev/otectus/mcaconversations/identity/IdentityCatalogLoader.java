package dev.otectus.mcaconversations.identity;

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
import java.util.TreeMap;

/**
 * Datapack loader for {@code data/<namespace>/identity_tokens/*.json} (spec §22.3).
 *
 * <p>Same shape and the same failure policy as every other catalog in the mod: a malformed token logs
 * and is skipped, and a failure of the whole listener keeps the previous catalog, because a datapack
 * reload must never be the thing that stops a world loading.
 *
 * <p>Files hold a {@code "tokens"} object of id → definition and an optional {@code "aliases"} object
 * of old id → current id. Aliases are how a token is renamed without rerolling anybody: an existing
 * profile keeps the string it was generated with, and the catalog resolves it forward (spec §6.4).
 *
 * <pre>{@code
 * {
 *   "tokens": {
 *     "animals": {"family": "interest", "weight": 12, "favour_archetypes": ["tender"]}
 *   },
 *   "aliases": {"beasts": "animals"}
 * }
 * }</pre>
 */
public final class IdentityCatalogLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new Gson();
    private static final String DIRECTORY = "identity_tokens";

    private static volatile IdentityCatalog active = IdentityCatalog.EMPTY;

    public IdentityCatalogLoader() {
        super(GSON, DIRECTORY);
    }

    /** The catalog from the last successful reload; never null, empty before the first load. */
    public static IdentityCatalog active() {
        return active;
    }

    /** Test seam: publish a catalog without a resource reload. */
    public static void setActiveForTesting(IdentityCatalog catalog) {
        active = catalog == null ? IdentityCatalog.EMPTY : catalog;
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager,
                         ProfilerFiller profiler) {
        try {
            Map<String, IdentityToken> byQualifiedId = new LinkedHashMap<>();
            Map<String, String> aliases = new TreeMap<>();

            // Sorted by file id so "last one wins" across namespaces is a stated rule rather than
            // whatever order the resource manager happened to hand back.
            List<ResourceLocation> ordered = new ArrayList<>(files.keySet());
            ordered.sort(ResourceLocation::compareTo);

            for (ResourceLocation location : ordered) {
                JsonElement value = files.get(location);
                if (value == null || !value.isJsonObject()) {
                    continue;
                }
                JsonObject root = value.getAsJsonObject();
                readTokens(root, location, byQualifiedId);
                readAliases(root, location, aliases);
            }

            IdentityCatalog built = SafeParse.orNull("identity_tokens", new JsonObject(),
                    () -> IdentityCatalog.build(new ArrayList<>(byQualifiedId.values()), aliases));
            if (built == null) {
                McaConversations.LOGGER.error("Identity tokens collided; keeping the previous {}.",
                        active.size());
                return;
            }
            active = built;
            McaConversations.LOGGER.info("Loaded {} identity token(s) from {} file(s).",
                    built.size(), files.size());
            if (!built.isEmpty() && !built.isComplete()) {
                // Not fatal: identity simply stays off rather than producing half a profile.
                McaConversations.LOGGER.warn("Identity catalog is missing at least one token family; "
                        + "profiles will not be generated until every family has a token.");
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.error("Identity-token reload failed; keeping the previous catalog", t);
        }
    }

    private static void readTokens(JsonObject root, ResourceLocation location,
                                   Map<String, IdentityToken> out) {
        if (!root.has("tokens") || !root.get("tokens").isJsonObject()) {
            return;
        }
        for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject("tokens").entrySet()) {
            String id = entry.getKey();
            if (!entry.getValue().isJsonObject()) {
                McaConversations.LOGGER.warn("identity token '{}' in {} is not an object — skipped",
                        id, location);
                continue;
            }
            JsonObject json = entry.getValue().getAsJsonObject();
            IdentityToken token = SafeParse.orNull("identity_tokens", json,
                    () -> IdentityToken.fromJson(id, json));
            if (token != null) {
                out.put(token.qualifiedId(), token);
            }
        }
    }

    private static void readAliases(JsonObject root, ResourceLocation location, Map<String, String> out) {
        if (!root.has("aliases") || !root.get("aliases").isJsonObject()) {
            return;
        }
        for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject("aliases").entrySet()) {
            if (!entry.getValue().isJsonPrimitive()) {
                McaConversations.LOGGER.warn("identity alias '{}' in {} is not a string — skipped",
                        entry.getKey(), location);
                continue;
            }
            out.put(entry.getKey(), entry.getValue().getAsString());
        }
    }
}
