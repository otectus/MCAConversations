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
import java.util.Map;
import java.util.Optional;

/**
 * Datapack loader for {@code data/<namespace>/conversation_beats/*.json} (spec §6.8).
 *
 * <p>Same shape as the catalog and intent loaders — {@link SimpleJsonResourceReloadListener}, last id
 * wins across namespaces — so a profession pack ships its beats and replies the way it ships its
 * dialogue, without a Java patch.
 *
 * <p>Each file holds {@code "beats"} and/or {@code "replies"} objects of id → entry. A malformed entry
 * logs and is skipped; a failure of the whole listener keeps the previous catalog, because a datapack
 * reload must never be the thing that stops a world loading (spec §14.3).
 */
public final class BeatContractLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new Gson();
    private static final String DIRECTORY = "conversation_beats";

    private static volatile BeatCatalog active = BeatCatalog.EMPTY;

    public BeatContractLoader() {
        super(GSON, DIRECTORY);
    }

    /** The catalog from the last successful reload (never null; empty before the first load). */
    public static BeatCatalog active() {
        return active;
    }

    /** Test seam: publish a catalog without a resource reload. */
    public static void setActiveForTesting(BeatCatalog catalog) {
        active = catalog == null ? BeatCatalog.EMPTY : catalog;
    }

    /** Runtime lookup that must never throw, whatever a datapack did. */
    public static Optional<BeatContract> beatForRoute(String say, String next) {
        if (say == null || next == null) {
            return Optional.empty();
        }
        try {
            return active.forRoute(say, next);
        } catch (Throwable t) {
            return Optional.empty();
        }
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
        try {
            Map<String, BeatContract> beats = new LinkedHashMap<>();
            Map<String, ReplyContract> replies = new LinkedHashMap<>();

            for (Map.Entry<ResourceLocation, JsonElement> file : files.entrySet()) {
                if (!file.getValue().isJsonObject()) {
                    continue;
                }
                JsonObject root = file.getValue().getAsJsonObject();
                readBeats(root, file.getKey(), beats);
                readReplies(root, file.getKey(), replies);
            }

            BeatCatalog catalog = SafeParse.orNull("conversation_beats", new JsonObject(),
                    () -> BeatCatalog.build(new ArrayList<>(beats.values()), new ArrayList<>(replies.values())));
            if (catalog == null) {
                McaConversations.LOGGER.error(
                        "Beat contracts collided on reload; keeping the previous {} beat(s).", active.size());
                return;
            }
            active = catalog;
            McaConversations.LOGGER.info("Loaded {} conversation beat(s) and {} reply contract(s) from {} file(s).",
                    catalog.size(), catalog.replies().size(), files.size());
        } catch (Throwable t) {
            McaConversations.LOGGER.error("Beat-contract reload failed; keeping the previous catalog", t);
        }
    }

    private static void readBeats(JsonObject root, ResourceLocation source, Map<String, BeatContract> into) {
        if (!root.has("beats") || !root.get("beats").isJsonObject()) {
            return;
        }
        for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject("beats").entrySet()) {
            String id = entry.getKey();
            if (!entry.getValue().isJsonObject()) {
                McaConversations.LOGGER.warn("beat '{}' in {} is not an object — skipped", id, source);
                continue;
            }
            JsonObject json = entry.getValue().getAsJsonObject();
            BeatContract beat = SafeParse.orNull("conversation_beats", json, () -> BeatContract.fromJson(id, json));
            if (beat != null) {
                into.put(id, beat);
            }
        }
    }

    private static void readReplies(JsonObject root, ResourceLocation source, Map<String, ReplyContract> into) {
        if (!root.has("replies") || !root.get("replies").isJsonObject()) {
            return;
        }
        for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject("replies").entrySet()) {
            String key = entry.getKey();
            if (!entry.getValue().isJsonObject()) {
                McaConversations.LOGGER.warn("reply contract '{}' in {} is not an object — skipped", key, source);
                continue;
            }
            JsonObject json = entry.getValue().getAsJsonObject();
            ReplyContract reply = SafeParse.orNull("conversation_beats", json, () -> ReplyContract.fromJson(key, json));
            if (reply != null) {
                into.put(key, reply);
            }
        }
    }
}
