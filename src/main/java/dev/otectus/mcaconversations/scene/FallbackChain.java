package dev.otectus.mcaconversations.scene;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The route a scene degrades to when the one the author preferred cannot be told (spec §10.4).
 *
 * <p>A {@code fallback} says: if this exact scene cannot be selected — its episode moved on, its slot
 * has nothing to bind, its cap is spent — here is a less specific scene that is still <em>true</em>
 * for this villager. The shipped corpus makes 219 of these declarations, chaining a profession's
 * specific work moment back to that profession's evergreen one.
 *
 * <p>Before 1.4.1 the declaration was parsed and validated and then never followed: a scene that
 * failed late binding simply dropped out, and the conversation fell all the way back to the static
 * route. The field was schema a pack author could rely on and runtime could not honour. This class is
 * the other half of that contract.
 *
 * <h2>The guards, and why each one is here</h2>
 *
 * <ul>
 *   <li><b>Depth</b> — {@link #MAX_DEPTH} hops. A chain is a degrade, not a search: by the fourth
 *       hop the scene has little to do with what the player asked, and the honest answer is the
 *       static route rather than a distant relative.</li>
 *   <li><b>Cycles</b> — a visited set, so {@code a → b → a} terminates. Validated at load time by
 *       {@link #cycles(SceneCatalog)} as well, so a pack author is told rather than silently capped.</li>
 *   <li><b>Purpose and topic</b> — enforced by {@link SceneCatalog#danglingReferences()} at load, and
 *       re-checked here at selection time, because a datapack reload can replace the target with one
 *       that no longer matches while the referring scene stays as it was.</li>
 * </ul>
 *
 * <p>Every hop is re-checked against the full gate stack by the caller. A fallback is a different
 * candidate, not a licence to skip eligibility: if the general work scene is inside its own cooldown,
 * it is no more selectable than the specific one that led here.
 */
public final class FallbackChain {

    /** Hops a chain may take before the director stops and takes the static route instead. */
    public static final int MAX_DEPTH = 4;

    private FallbackChain() {
    }

    /**
     * The scenes to try after {@code scene}, nearest first.
     *
     * <p>Stops at the first hop that is missing, self-referential, already visited, or files under a
     * different purpose/topic than the scene that named it.
     */
    public static List<SceneDefinition> from(SceneCatalog catalog, SceneDefinition scene) {
        if (catalog == null || scene == null || !scene.hasFallback()) {
            return List.of();
        }
        List<SceneDefinition> chain = new ArrayList<>();
        Set<String> visited = new LinkedHashSet<>();
        visited.add(scene.id());
        SceneDefinition current = scene;
        while (current.hasFallback() && chain.size() < MAX_DEPTH) {
            Optional<SceneDefinition> next = catalog.scene(current.fallbackScene());
            if (next.isEmpty() || !visited.add(next.get().id())) {
                break;
            }
            SceneDefinition target = next.get();
            if (target.purpose() != scene.purpose() || !target.topic().equals(scene.topic())) {
                break;
            }
            chain.add(target);
            current = target;
        }
        return List.copyOf(chain);
    }

    /**
     * Every fallback cycle in the catalog, one line each, for the load-time diagnostic.
     *
     * <p>Reported rather than thrown: a cycle costs the scenes on it, and losing the whole catalog
     * over one pack's mistake would be the worse failure.
     */
    public static List<String> cycles(SceneCatalog catalog) {
        List<String> problems = new ArrayList<>();
        if (catalog == null) {
            return List.of();
        }
        for (SceneDefinition scene : catalog.all()) {
            // A scene naming itself is a cycle of one, and the catalog already reports it in the
            // words that tell the author what to do about it. Reporting it twice helps nobody.
            if (!scene.hasFallback() || scene.fallbackScene().equals(scene.id())) {
                continue;
            }
            Set<String> visited = new LinkedHashSet<>();
            visited.add(scene.id());
            SceneDefinition current = scene;
            while (current.hasFallback()) {
                Optional<SceneDefinition> next = catalog.scene(current.fallbackScene());
                if (next.isEmpty()) {
                    break;
                }
                if (!visited.add(next.get().id())) {
                    // Reported once, from the lowest id on the cycle, so one loop is one line.
                    if (scene.id().equals(java.util.Collections.min(visited))) {
                        problems.add("fallback cycle: " + String.join(" -> ", visited)
                                + " -> " + next.get().id());
                    }
                    break;
                }
                current = next.get();
            }
        }
        return List.copyOf(problems);
    }
}
