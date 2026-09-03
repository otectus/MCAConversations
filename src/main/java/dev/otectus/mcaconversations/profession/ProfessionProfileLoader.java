package dev.otectus.mcaconversations.profession;

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

/**
 * Datapack loader for {@code data/<namespace>/profession_profiles/*.json} (spec §6.8).
 *
 * <p>This is the extension point a profession pack needs: a mod that adds a trade drops in a profile,
 * its dialogue, its beats and its lang, and the conversation system picks the trade up with no Java
 * change here. Last id wins across namespaces, so a pack may also refine one of this mod's own
 * profiles.
 *
 * <p>A malformed profile logs and is skipped; a failed reload keeps the previous index. A profession
 * whose owning mod is absent is harmless — its registry id simply never matches a villager.
 */
public final class ProfessionProfileLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new Gson();
    private static final String DIRECTORY = "profession_profiles";

    private static volatile ProfessionProfiles active = ProfessionProfiles.EMPTY;

    public ProfessionProfileLoader() {
        super(GSON, DIRECTORY);
    }

    /** The index from the last successful reload (never null; empty before the first load). */
    public static ProfessionProfiles active() {
        return active;
    }

    /** Test seam: publish an index without a resource reload. */
    public static void setActiveForTesting(ProfessionProfiles profiles) {
        active = profiles == null ? ProfessionProfiles.EMPTY : profiles;
    }

    /** Runtime lookup that must never throw, whatever a datapack did. */
    public static ProfessionProfile profile(String id, String displayFallback) {
        try {
            return active.forId(id, displayFallback);
        } catch (Throwable t) {
            return ProfessionProfile.generic(id == null ? "unknown:unknown" : id,
                    displayFallback == null ? "villager" : displayFallback);
        }
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
        try {
            Map<String, ProfessionProfile> byId = new LinkedHashMap<>();
            for (Map.Entry<ResourceLocation, JsonElement> file : files.entrySet()) {
                if (!file.getValue().isJsonObject()) {
                    continue;
                }
                JsonObject root = file.getValue().getAsJsonObject();
                if (!root.has("profiles") || !root.get("profiles").isJsonObject()) {
                    continue;
                }
                for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject("profiles").entrySet()) {
                    String id = entry.getKey();
                    if (!entry.getValue().isJsonObject()) {
                        McaConversations.LOGGER.warn("profession profile '{}' in {} is not an object — skipped",
                                id, file.getKey());
                        continue;
                    }
                    JsonObject json = entry.getValue().getAsJsonObject();
                    ProfessionProfile profile = SafeParse.orNull("profession_profiles", json,
                            () -> ProfessionProfile.fromJson(id, json));
                    if (profile != null) {
                        byId.put(id, profile);
                    }
                }
            }
            ProfessionProfiles built = SafeParse.orNull("profession_profiles", new JsonObject(),
                    () -> ProfessionProfiles.build(new ArrayList<>(byId.values())));
            if (built == null) {
                McaConversations.LOGGER.error("Profession profiles collided; keeping the previous {}.",
                        active.size());
                return;
            }
            active = built;
            McaConversations.LOGGER.info("Loaded {} profession profile(s) from {} file(s).",
                    built.size(), files.size());
        } catch (Throwable t) {
            McaConversations.LOGGER.error("Profession-profile reload failed; keeping the previous index", t);
        }
    }
}
