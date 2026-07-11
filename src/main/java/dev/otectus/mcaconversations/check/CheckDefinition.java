package dev.otectus.mcaconversations.check;

import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.disposition.DispositionAxis;

/**
 * Parsed form of the {@code conversations_check} dialogue condition:
 * {@code {"id": "fears.challenge", "tier": "crit", "axis": "trust", "difficulty": 45}}. All four
 * tier results of a checked stance share the same id/axis/difficulty (lint-enforced) and differ only
 * in {@code tier}; the condition matches when the resolver's tier equals the declared one. Parse
 * problems throw so {@link dev.otectus.mcaconversations.util.SafeParse} contains them into a
 * never-matching condition.
 */
public record CheckDefinition(String id, CheckTier tier, DispositionAxis axis, int difficulty) {

    public static CheckDefinition fromJson(JsonObject json) {
        if (!json.has("id") || json.get("id").getAsString().isEmpty()) {
            throw new IllegalArgumentException("conversations_check requires an \"id\"");
        }
        if (!json.has("tier")) {
            throw new IllegalArgumentException("conversations_check requires a \"tier\"");
        }
        String tierKey = json.get("tier").getAsString();
        CheckTier tier = CheckTier.byKey(tierKey)
                .orElseThrow(() -> new IllegalArgumentException("unknown check tier: " + tierKey));
        if (!json.has("axis")) {
            throw new IllegalArgumentException("conversations_check requires an \"axis\"");
        }
        String axisKey = json.get("axis").getAsString();
        DispositionAxis axis = DispositionAxis.byKey(axisKey)
                .orElseThrow(() -> new IllegalArgumentException("unknown disposition axis: " + axisKey));
        if (!json.has("difficulty")) {
            throw new IllegalArgumentException("conversations_check requires a \"difficulty\"");
        }
        int difficulty = json.get("difficulty").getAsInt();
        if (difficulty < 0 || difficulty > 100) {
            throw new IllegalArgumentException("check difficulty out of range 0..100: " + difficulty);
        }
        return new CheckDefinition(json.get("id").getAsString(), tier, axis, difficulty);
    }
}
