package dev.otectus.mcaconversations.history;

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
 * Datapack loaders for the three narrative template directories (spec Appendix A).
 *
 * <p>Three directories, one catalog. Each concrete subclass below is a
 * {@link SimpleJsonResourceReloadListener} over its own directory; all three write into the same
 * staging maps, and the last one to finish publishes the merged catalog. That is why
 * {@link #publish()} is called from every subclass rather than from one: reload order between
 * listeners is not guaranteed, so "the last one wins" has to be true whichever runs last.
 *
 * <p>Failure policy matches every other catalog in the mod: a malformed template logs and is skipped,
 * and a failure of the whole listener keeps the previous catalog — a datapack reload must never be the
 * thing that stops a world loading.
 */
public abstract class NarrativeCatalogLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new Gson();

    private static volatile NarrativeCatalog active = NarrativeCatalog.EMPTY;

    // Staging, written by all three listeners during one reload and published by each in turn.
    private static final Map<String, EpisodeTemplate> STAGED_EPISODES = new LinkedHashMap<>();
    private static final Map<String, ThreadTemplate> STAGED_THREADS = new LinkedHashMap<>();
    private static final Map<String, CommitmentTemplate> STAGED_COMMITMENTS = new LinkedHashMap<>();

    protected NarrativeCatalogLoader(String directory) {
        super(GSON, directory);
    }

    /** The catalog from the last successful reload; never null, empty before the first load. */
    public static NarrativeCatalog active() {
        return active;
    }

    /** Test seam: publish a catalog without a resource reload. */
    public static void setActiveForTesting(NarrativeCatalog catalog) {
        active = catalog == null ? NarrativeCatalog.EMPTY : catalog;
    }

    /** Every listener needed to load the three directories, in the order they are registered. */
    public static List<NarrativeCatalogLoader> listeners() {
        return List.of(new Episodes(), new Threads(), new Commitments());
    }

    /** Reads one section of a file into its staging map. */
    protected abstract void read(String id, JsonObject json, ResourceLocation file);

    /** Which top-level object of the JSON file this listener reads. */
    protected abstract String sectionName();

    /** Clears this listener's staging map at the start of its own reload pass. */
    protected abstract void clearStaging();

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager,
                         ProfilerFiller profiler) {
        try {
            synchronized (NarrativeCatalogLoader.class) {
                clearStaging();
                List<ResourceLocation> ordered = new ArrayList<>(files.keySet());
                // Sorted so "last one wins" across namespaces is a stated rule rather than whatever
                // order the resource manager happened to hand back.
                ordered.sort(ResourceLocation::compareTo);
                for (ResourceLocation location : ordered) {
                    JsonElement value = files.get(location);
                    if (value == null || !value.isJsonObject()) {
                        continue;
                    }
                    JsonObject root = value.getAsJsonObject();
                    if (!root.has(sectionName()) || !root.get(sectionName()).isJsonObject()) {
                        continue;
                    }
                    for (Map.Entry<String, JsonElement> entry
                            : root.getAsJsonObject(sectionName()).entrySet()) {
                        if (!entry.getValue().isJsonObject()) {
                            McaConversations.LOGGER.warn("{} '{}' in {} is not an object — skipped",
                                    sectionName(), entry.getKey(), location);
                            continue;
                        }
                        read(entry.getKey(), entry.getValue().getAsJsonObject(), location);
                    }
                }
                publish();
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.error("Narrative-template reload failed; keeping the previous catalog", t);
        }
    }

    private static void publish() {
        NarrativeCatalog built = SafeParse.orNull("narrative_templates", new JsonObject(),
                () -> NarrativeCatalog.build(new ArrayList<>(STAGED_EPISODES.values()),
                        new ArrayList<>(STAGED_THREADS.values()),
                        new ArrayList<>(STAGED_COMMITMENTS.values())));
        if (built == null) {
            McaConversations.LOGGER.error("Narrative templates collided; keeping the previous {}.",
                    active.size());
            return;
        }
        active = built;
        List<String> dangling = built.danglingReferences();
        if (!dangling.isEmpty()) {
            // Warned rather than rejected: a dangling reference costs one scene, and refusing the whole
            // catalog over it would cost every other scene as well.
            dangling.forEach(problem ->
                    McaConversations.LOGGER.warn("narrative template reference unresolved: {}", problem));
        }
        McaConversations.LOGGER.info("Loaded {} episode, {} thread and {} commitment template(s).",
                built.episodes().size(), built.threads().size(), built.commitments().size());
    }

    /** {@code data/<namespace>/episode_templates/*.json}, section {@code "episodes"}. */
    public static final class Episodes extends NarrativeCatalogLoader {
        public Episodes() {
            super("episode_templates");
        }

        @Override
        protected String sectionName() {
            return "episodes";
        }

        @Override
        protected void clearStaging() {
            STAGED_EPISODES.clear();
        }

        @Override
        protected void read(String id, JsonObject json, ResourceLocation file) {
            EpisodeTemplate template = SafeParse.orNull("episode_templates", json,
                    () -> EpisodeTemplate.fromJson(id, json));
            if (template != null) {
                STAGED_EPISODES.put(template.kind(), template);
            }
        }
    }

    /** {@code data/<namespace>/thread_templates/*.json}, section {@code "threads"}. */
    public static final class Threads extends NarrativeCatalogLoader {
        public Threads() {
            super("thread_templates");
        }

        @Override
        protected String sectionName() {
            return "threads";
        }

        @Override
        protected void clearStaging() {
            STAGED_THREADS.clear();
        }

        @Override
        protected void read(String id, JsonObject json, ResourceLocation file) {
            ThreadTemplate template = SafeParse.orNull("thread_templates", json,
                    () -> ThreadTemplate.fromJson(id, json));
            if (template != null) {
                STAGED_THREADS.put(template.id(), template);
            }
        }
    }

    /** {@code data/<namespace>/commitment_templates/*.json}, section {@code "commitments"}. */
    public static final class Commitments extends NarrativeCatalogLoader {
        public Commitments() {
            super("commitment_templates");
        }

        @Override
        protected String sectionName() {
            return "commitments";
        }

        @Override
        protected void clearStaging() {
            STAGED_COMMITMENTS.clear();
        }

        @Override
        protected void read(String id, JsonObject json, ResourceLocation file) {
            CommitmentTemplate template = SafeParse.orNull("commitment_templates", json,
                    () -> CommitmentTemplate.fromJson(id, json));
            if (template != null) {
                STAGED_COMMITMENTS.put(template.id(), template);
            }
        }
    }
}
