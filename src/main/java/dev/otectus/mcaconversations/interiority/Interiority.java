package dev.otectus.mcaconversations.interiority;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.StanceFamily;
import dev.otectus.mcaconversations.disposition.DispositionAxis;
import dev.otectus.mcaconversations.personality.Personalities;
import dev.otectus.mcaconversations.util.SafeParse;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.Entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The reloadable registry of {@link InteriorityProfile}s, loaded from
 * {@code data/<namespace>/interiority/*.json} (plan §5.5) — a data registry rather than a hard-coded
 * switch, so a pack can retune a personality without touching Java.
 *
 * <p><b>Per personality, not per villager.</b> Two Friendly villagers share a profile in 1.1.0.
 * Nothing is rolled, nothing is stored per villager, and there is therefore nothing to migrate or to
 * go inconsistent after a reload — the property plan §5.5 asks for, obtained by not having the
 * problem. Per-villager selection can be layered on later without invalidating a save.
 *
 * <p>Lookups resolve MCA 7.6 spellings through {@link Personalities#canonical}, so a {@code witty}
 * villager on 7.6 and an {@code upbeat} villager on 7.7 read the same profile.
 */
public final class Interiority extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new Gson();
    private static final String DIRECTORY = "interiority";

    private static volatile Map<String, InteriorityProfile> profiles = Map.of();

    public Interiority() {
        super(GSON, DIRECTORY);
    }

    /** The profile for a personality id in any MCA spelling; neutral when unknown. */
    public static InteriorityProfile profile(String personality) {
        String canonical = Personalities.canonical(personality);
        if (canonical.isEmpty()) {
            return InteriorityProfile.NEUTRAL;
        }
        return profiles.getOrDefault(canonical, InteriorityProfile.NEUTRAL);
    }

    /** The profile for a villager, read through {@link McaCompat}; neutral when MCA is unreadable. */
    public static InteriorityProfile profileOf(Entity villager) {
        try {
            return McaCompat.getPersonality(villager).map(Interiority::profile)
                    .orElse(InteriorityProfile.NEUTRAL);
        } catch (Throwable t) {
            return InteriorityProfile.NEUTRAL;
        }
    }

    /** This villager's resting value for an axis. The single seam behind {@code Dispositions.baseline}. */
    public static int baseline(Entity villager, DispositionAxis axis) {
        return profileOf(villager).baseline(axis);
    }

    /** How well a stance family lands on this villager, bounded by the profile's own clamp. */
    public static int stanceBias(Entity villager, StanceFamily family) {
        return profileOf(villager).stanceBias(family);
    }

    /** How many profiles are loaded, for the debug command and lint. */
    public static int size() {
        return profiles.size();
    }

    /** Test seam: publish profiles without a resource reload. */
    public static void setProfilesForTesting(Map<String, InteriorityProfile> loaded) {
        profiles = loaded == null ? Map.of() : Map.copyOf(loaded);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
        try {
            Map<String, InteriorityProfile> loaded = new LinkedHashMap<>();
            for (Map.Entry<ResourceLocation, JsonElement> file : files.entrySet()) {
                if (!file.getValue().isJsonObject()) {
                    continue;
                }
                JsonObject root = file.getValue().getAsJsonObject();
                if (!root.has("profiles") || !root.get("profiles").isJsonObject()) {
                    continue;
                }
                for (Map.Entry<String, JsonElement> e : root.getAsJsonObject("profiles").entrySet()) {
                    String personality = Personalities.canonical(e.getKey());
                    if (!e.getValue().isJsonObject()) {
                        McaConversations.LOGGER.warn("interiority profile '{}' in {} is not an object — skipped",
                                e.getKey(), file.getKey());
                        continue;
                    }
                    JsonObject profileJson = e.getValue().getAsJsonObject();
                    InteriorityProfile profile = SafeParse.orNull("interiority", profileJson,
                            () -> InteriorityProfile.fromJson(personality, profileJson));
                    if (profile != null) {
                        loaded.put(personality, profile);
                    }
                }
            }
            profiles = Map.copyOf(loaded);
            McaConversations.LOGGER.info("Loaded {} interiority profiles from {} file(s).",
                    profiles.size(), files.size());
        } catch (Throwable t) {
            McaConversations.LOGGER.error("Interiority reload failed; keeping the previous profiles", t);
        }
    }
}
