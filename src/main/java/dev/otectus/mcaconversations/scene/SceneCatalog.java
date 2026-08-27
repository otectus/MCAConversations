package dev.otectus.mcaconversations.scene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * Every authored scene, indexed so the director never scans it (spec §9.1, §21.6).
 *
 * <p>The index is the whole performance story. Stage one of the candidate pipeline is a map lookup on
 * {@code purpose/topic}, which turns a catalog of any size into a bucket of a few dozen before a
 * single eligibility check runs. Nothing here walks the full collection at selection time; the only
 * full traversals are building the index and writing the report.
 *
 * <p>{@link #MAX_INDEXED} and {@link #MAX_SCORED} are the plan's hard bounds, enforced here rather
 * than trusted: a datapack that files two hundred scenes under one topic gets the first hundred and
 * twenty-eight in a stable order, not a frame-rate problem.
 */
public final class SceneCatalog {

    public static final SceneCatalog EMPTY = new SceneCatalog(List.of());

    /** Candidates an index lookup may return before hard filtering (spec §21.6). */
    public static final int MAX_INDEXED = 128;

    /** Candidates that may reach scoring after hard filtering. */
    public static final int MAX_SCORED = 32;

    private final Map<String, SceneDefinition> byId;
    private final Map<String, List<SceneDefinition>> byIndexKey;

    private SceneCatalog(Collection<SceneDefinition> scenes) {
        Map<String, SceneDefinition> ids = new TreeMap<>();
        for (SceneDefinition scene : scenes) {
            ids.put(scene.id(), scene);
        }
        Map<String, List<SceneDefinition>> index = new LinkedHashMap<>();
        // Sorted by id, so the bucket order a truncation keeps is identical on every server.
        for (SceneDefinition scene : ids.values()) {
            index.computeIfAbsent(scene.indexKey(), key -> new ArrayList<>()).add(scene);
        }
        Map<String, List<SceneDefinition>> frozen = new LinkedHashMap<>();
        index.forEach((key, list) -> frozen.put(key,
                List.copyOf(list.size() > MAX_INDEXED ? list.subList(0, MAX_INDEXED) : list)));
        this.byId = Map.copyOf(ids);
        this.byIndexKey = Map.copyOf(frozen);
    }

    public static SceneCatalog build(Collection<SceneDefinition> scenes) {
        Map<String, String> seen = new LinkedHashMap<>();
        for (SceneDefinition scene : scenes) {
            if (seen.put(scene.id(), scene.id()) != null) {
                throw new IllegalArgumentException("duplicate scene '" + scene.id() + "'");
            }
        }
        return new SceneCatalog(scenes);
    }

    public Optional<SceneDefinition> scene(String id) {
        return id == null ? Optional.empty()
                : Optional.ofNullable(byId.get(id.trim().toLowerCase(Locale.ROOT)));
    }

    /**
     * Stage one of the candidate pipeline: every scene filed under this purpose and topic.
     *
     * <p>A topic lookup also returns the topic-agnostic bucket, so a scene that applies to any topic
     * of its purpose does not have to be filed once per topic.
     */
    public List<SceneDefinition> candidates(ScenePurpose purpose, String topic) {
        if (purpose == null) {
            return List.of();
        }
        String normalizedTopic = topic == null ? "" : topic.trim().toLowerCase(Locale.ROOT);
        List<SceneDefinition> exact = byIndexKey.getOrDefault(
                purpose.key() + "/" + (normalizedTopic.isEmpty() ? "*" : normalizedTopic), List.of());
        List<SceneDefinition> agnostic = normalizedTopic.isEmpty()
                ? List.of()
                : byIndexKey.getOrDefault(purpose.key() + "/*", List.of());
        if (agnostic.isEmpty()) {
            return exact;
        }
        List<SceneDefinition> merged = new ArrayList<>(exact);
        for (SceneDefinition scene : agnostic) {
            if (merged.size() >= MAX_INDEXED) {
                break;
            }
            merged.add(scene);
        }
        return List.copyOf(merged);
    }

    /**
     * Every scene, in id order.
     *
     * <p>Sorted explicitly rather than by relying on the backing map: {@code Map.copyOf} makes no
     * ordering promise at all, so a report or a lint that walked it directly would produce a
     * different ordering on a different JVM run and a diff on every build.
     */
    public List<SceneDefinition> all() {
        List<SceneDefinition> out = new ArrayList<>(byId.values());
        out.sort(Comparator.comparing(SceneDefinition::id));
        return List.copyOf(out);
    }

    public boolean isEmpty() {
        return byId.isEmpty();
    }

    public int size() {
        return byId.size();
    }

    /** Index keys and their bucket sizes, for the scenes report and the performance assertion. */
    public java.util.SortedMap<String, Integer> bucketSizes() {
        // A SortedMap rather than Map.copyOf, for the same reason all() sorts: the caller is a report.
        java.util.SortedMap<String, Integer> out = new TreeMap<>();
        byIndexKey.forEach((key, list) -> out.put(key, list.size()));
        return java.util.Collections.unmodifiableSortedMap(out);
    }

    /**
     * References a scene makes that nothing satisfies.
     *
     * <p>Returned rather than thrown for the same reason the narrative catalog does it: a reload
     * should report every broken reference at once, and one bad scene must not cost the rest.
     */
    public List<String> danglingReferences() {
        List<String> problems = new ArrayList<>();
        for (SceneDefinition scene : byId.values()) {
            if (scene.hasFallback() && scene(scene.fallbackScene()).isEmpty()) {
                problems.add("scene '" + scene.id() + "' falls back to unknown scene '"
                        + scene.fallbackScene() + "'");
            }
            if (scene.hasFallback() && scene.fallbackScene().equals(scene.id())) {
                problems.add("scene '" + scene.id() + "' falls back to itself");
            }
        }
        return List.copyOf(problems);
    }
}
