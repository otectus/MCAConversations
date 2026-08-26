package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * What a {@code conversations_relationship} condition asks for (spec §9.4).
 *
 * <p>Dialogue used to have no way to say "only tell this to someone who has earned it" except a
 * heart number written into a result, and the corpus quite reasonably never did it — a number in
 * JSON is a magic constant that nobody can change once forty results carry it. This query is the
 * author-facing form: a list of {@link RelationshipBand} names, or a floor on the warmth line.
 *
 * <pre>
 *   {"conversations_relationship": "confidant"}
 *   {"conversations_relationship": ["partner", "family"]}
 *   {"conversations_relationship": {"at_least": "friend"}}
 *   {"conversations_relationship": {"at_least": "friend", "not": ["partner"]}}
 * </pre>
 *
 * <p>The thresholds live in {@link RelationshipBand} and nowhere else, so raising what "confidant"
 * means is one edit rather than a search across the datapack.
 */
public record RelationshipQuery(Set<RelationshipBand> bands,
                                Optional<RelationshipBand> atLeast,
                                Set<RelationshipBand> excluded) {

    public RelationshipQuery {
        bands = Set.copyOf(bands);
        excluded = Set.copyOf(excluded);
    }

    public static RelationshipQuery fromJson(JsonElement json) {
        if (json == null || json.isJsonNull()) {
            throw new IllegalArgumentException("conversations_relationship: no value");
        }
        if (json.isJsonPrimitive()) {
            return new RelationshipQuery(Set.of(band(json.getAsString())), Optional.empty(), Set.of());
        }
        if (json.isJsonArray()) {
            return new RelationshipQuery(bands(json.getAsJsonArray()), Optional.empty(), Set.of());
        }

        JsonObject object = json.getAsJsonObject();
        Set<RelationshipBand> listed = object.has("bands")
                ? bands(object.getAsJsonArray("bands")) : Set.of();
        Optional<RelationshipBand> floor = object.has("at_least")
                ? Optional.of(band(object.get("at_least").getAsString())) : Optional.empty();
        Set<RelationshipBand> not = object.has("not")
                ? bands(object.getAsJsonArray("not")) : Set.of();
        if (listed.isEmpty() && floor.isEmpty()) {
            throw new IllegalArgumentException(
                    "conversations_relationship: needs \"bands\" or \"at_least\"");
        }
        return new RelationshipQuery(listed, floor, not);
    }

    private static Set<RelationshipBand> bands(JsonArray array) {
        Set<RelationshipBand> out = new LinkedHashSet<>();
        for (JsonElement element : array) {
            out.add(band(element.getAsString()));
        }
        if (out.isEmpty()) {
            throw new IllegalArgumentException("conversations_relationship: empty band list");
        }
        return Collections.unmodifiableSet(out);
    }

    private static RelationshipBand band(String key) {
        return RelationshipBand.byKey(key).orElseThrow(() -> new IllegalArgumentException(
                "conversations_relationship: unknown band '" + key + "'"));
    }

    /** True when {@code actual} satisfies this query. An excluded band always loses. */
    public boolean matches(RelationshipBand actual) {
        if (actual == null || excluded.contains(actual)) {
            return false;
        }
        if (bands.contains(actual)) {
            return true;
        }
        return atLeast.map(actual::isAtLeast).orElse(false);
    }
}
