package dev.otectus.mcaconversations.scene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Every authored scene, indexed so the director never scans it (spec §9.1, §21.6).
 *
 * <p>The index is the whole performance story. Stage one of the candidate pipeline is a map lookup,
 * which turns a catalog of any size into a bucket of a few dozen before a single eligibility check
 * runs. Nothing here walks the full collection at selection time; the only full traversals are
 * building the index and writing the report.
 *
 * <h2>Why the leaf is purpose/topic#profession</h2>
 *
 * <p>Before 1.4.1 the leaf was {@code purpose/topic} alone, and the bound below was applied to it.
 * That was correct for every topic but the one that matters most: the shipped corpus files 256 scenes
 * under {@code topic:work}, so exactly half of them were dropped before eligibility ran. Eighteen
 * professions lost every dynamic work scene they had, and the loss was silent — the truncated bucket
 * was the only thing any report or lint could see.
 *
 * <p>Profession is the right discriminator because it is already a <em>hard</em> gate: a scene naming
 * a profession is rejected outright for any other one, and rejected when the profession is unknown
 * ({@link SceneEligibility#check}). Filing by profession therefore returns exactly the set the old
 * lookup returned <em>after</em> filtering — never fewer, and without the truncation. Scenes naming no
 * profession live in the {@link #ANY_PROFESSION} leaf, which every lookup merges.
 *
 * <p>{@link #MAX_INDEXED} and {@link #MAX_SCORED} remain the plan's hard bounds, enforced here rather
 * than trusted. What changed is that overflow is no longer silent: {@link #truncations()} names every
 * leaf that lost scenes and the first id it dropped, the loader logs it, and the bundled corpus fails
 * its lint if it ever overflows again.
 */
public final class SceneCatalog {

    public static final SceneCatalog EMPTY = new SceneCatalog(List.of());

    /** Candidates an index lookup may return before hard filtering (spec §21.6). */
    public static final int MAX_INDEXED = 128;

    /** Candidates that may reach scoring after hard filtering. */
    public static final int MAX_SCORED = 32;

    /** The leaf holding scenes that name no profession, merged into every lookup. */
    public static final String ANY_PROFESSION = "*";

    private final Map<String, SceneDefinition> byId;
    private final Map<String, List<SceneDefinition>> byLeaf;
    private final SortedMap<String, Integer> rawLeafSizes;
    private final SortedMap<String, Integer> topicSizes;
    private final List<String> truncations;

    private SceneCatalog(Collection<SceneDefinition> scenes) {
        Map<String, SceneDefinition> ids = new TreeMap<>();
        for (SceneDefinition scene : scenes) {
            ids.put(scene.id(), scene);
        }

        // Sorted by id, so the bucket order a truncation keeps is identical on every server.
        Map<String, List<SceneDefinition>> leaves = new TreeMap<>();
        SortedMap<String, Integer> topics = new TreeMap<>();
        for (SceneDefinition scene : ids.values()) {
            topics.merge(scene.indexKey(), 1, Integer::sum);
            for (String leaf : scene.indexKeys()) {
                leaves.computeIfAbsent(leaf, key -> new ArrayList<>()).add(scene);
            }
        }

        SortedMap<String, Integer> raw = new TreeMap<>();
        List<String> overflow = new ArrayList<>();
        Map<String, List<SceneDefinition>> frozen = new LinkedHashMap<>();
        for (Map.Entry<String, List<SceneDefinition>> entry : leaves.entrySet()) {
            List<SceneDefinition> list = entry.getValue();
            raw.put(entry.getKey(), list.size());
            if (list.size() > MAX_INDEXED) {
                overflow.add(entry.getKey() + " holds " + list.size() + " scenes; the "
                        + (list.size() - MAX_INDEXED) + " past the " + MAX_INDEXED
                        + " indexed bound can never be selected, from '"
                        + list.get(MAX_INDEXED).id() + "' onward");
                list = list.subList(0, MAX_INDEXED);
            }
            frozen.put(entry.getKey(), List.copyOf(list));
        }

        this.byId = Map.copyOf(ids);
        this.byLeaf = Map.copyOf(frozen);
        this.rawLeafSizes = java.util.Collections.unmodifiableSortedMap(raw);
        this.topicSizes = java.util.Collections.unmodifiableSortedMap(topics);
        this.truncations = List.copyOf(overflow);
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
     * Stage one of the candidate pipeline: every scene this villager's profession could be shown
     * under this purpose and topic.
     *
     * <p>Four leaves are merged, in decreasing specificity: this profession under this topic, any
     * profession under this topic, this profession under any topic, and any profession under any
     * topic. The topic-agnostic pair is what lets a scene that applies to every topic of its purpose
     * be filed once rather than once per topic.
     *
     * @param profession the villager's profession id, or empty/null when it is unknown — in which
     *                   case only the profession-agnostic leaves are returned, because a scene naming
     *                   a profession is rejected outright by the hard gate when the profession cannot
     *                   be read
     */
    public List<SceneDefinition> candidates(ScenePurpose purpose, String topic, String profession) {
        if (purpose == null) {
            return List.of();
        }
        String normalizedTopic = normalize(topic);
        String normalizedProfession = normalize(profession);
        String base = purpose.key() + "/" + (normalizedTopic.isEmpty() ? "*" : normalizedTopic);
        String agnostic = purpose.key() + "/*";

        List<SceneDefinition> merged = new ArrayList<>();
        Set<String> seen = new LinkedHashSet<>();
        append(base, normalizedProfession, merged, seen);
        append(base, ANY_PROFESSION, merged, seen);
        if (!normalizedTopic.isEmpty()) {
            append(agnostic, normalizedProfession, merged, seen);
            append(agnostic, ANY_PROFESSION, merged, seen);
        }
        return List.copyOf(merged);
    }

    private void append(String base, String profession, List<SceneDefinition> into, Set<String> seen) {
        if (profession.isEmpty()) {
            return;
        }
        for (SceneDefinition scene : byLeaf.getOrDefault(base + "#" + profession, List.of())) {
            if (into.size() >= MAX_INDEXED) {
                return;
            }
            if (seen.add(scene.id())) {
                into.add(scene);
            }
        }
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

    /** Live index leaves and their sizes, after any truncation — what a lookup can actually return. */
    public SortedMap<String, Integer> bucketSizes() {
        SortedMap<String, Integer> out = new TreeMap<>();
        byLeaf.forEach((key, list) -> out.put(key, list.size()));
        return java.util.Collections.unmodifiableSortedMap(out);
    }

    /**
     * Leaf sizes <em>before</em> truncation.
     *
     * <p>Reported alongside {@link #bucketSizes()} for one reason: a truncation that only ever
     * appears in the live index cannot be seen, because the live index is exactly the evidence it
     * destroyed. That is how 128 shipped work scenes stayed unreachable through a passing lint.
     */
    public SortedMap<String, Integer> rawBucketSizes() {
        return rawLeafSizes;
    }

    /** Scenes per {@code purpose/topic}, ignoring the profession leaves — the editorial view. */
    public SortedMap<String, Integer> topicSizes() {
        return topicSizes;
    }

    /** One line per index leaf that lost scenes to {@link #MAX_INDEXED}; empty when none did. */
    public List<String> truncations() {
        return truncations;
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
            if (!scene.hasFallback()) {
                continue;
            }
            if (scene.fallbackScene().equals(scene.id())) {
                problems.add("scene '" + scene.id() + "' falls back to itself");
                continue;
            }
            Optional<SceneDefinition> target = scene(scene.fallbackScene());
            if (target.isEmpty()) {
                problems.add("scene '" + scene.id() + "' falls back to unknown scene '"
                        + scene.fallbackScene() + "'");
                continue;
            }
            // A fallback is a route the director will actually take when the preferred scene cannot
            // bind, so it has to be a route this conversation could be having: same purpose, same
            // topic. A truthful degrade to a more general work scene is the point; a silent hop to
            // another subject is the failure this check exists to stop.
            if (target.get().purpose() != scene.purpose()
                    || !target.get().topic().equals(scene.topic())) {
                problems.add("scene '" + scene.id() + "' falls back to '" + scene.fallbackScene()
                        + "', which is " + target.get().indexKey() + " rather than " + scene.indexKey());
            }
        }
        problems.addAll(FallbackChain.cycles(this));
        return List.copyOf(problems);
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
