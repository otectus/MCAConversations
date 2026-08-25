package dev.otectus.mcaconversations.check;

import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.conversation.StanceFamily;
import dev.otectus.mcaconversations.disposition.DispositionAxis;
import dev.otectus.mcaconversations.conversation.TopicEntry;

import java.util.Optional;

/**
 * Parsed form of the {@code conversations_check} dialogue condition:
 * {@code {"id": "fears.challenge", "tier": "crit", "axis": "trust", "difficulty": 45,
 * "stance": "challenge", "arc": "fears"}}. All tier results of a checked stance share the same
 * id/axis/difficulty/stance/arc (lint-enforced) and differ only in {@code tier}; the condition
 * matches when the resolver's tier equals the declared one.
 *
 * <p>{@code stance} and {@code arc} are optional and are what make a check personal rather than
 * arithmetic: {@code stance} names the kind of thing the player is saying, so the villager's
 * interiority profile can make candour land better on one personality than another, and {@code arc}
 * names the ordered progression this check belongs to, so the seeded roll changes when the
 * relationship genuinely moves on rather than staying frozen for the life of the save.
 *
 * <p>Parse problems throw so {@link dev.otectus.mcaconversations.util.SafeParse} contains them into
 * a never-matching condition.
 */
public record CheckDefinition(String id, CheckTier tier, DispositionAxis axis, int difficulty,
                              Optional<StanceFamily> stance, Optional<String> arc) {

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
        Optional<StanceFamily> stance = Optional.empty();
        if (json.has("stance")) {
            String stanceKey = json.get("stance").getAsString();
            stance = Optional.of(StanceFamily.byKey(stanceKey).orElseThrow(() ->
                    new IllegalArgumentException("unknown stance family: " + stanceKey)));
        }

        Optional<String> arc = Optional.empty();
        if (json.has("arc")) {
            String arcId = json.get("arc").getAsString();
            if (!TopicEntry.ID.matcher(arcId).matches()) {
                throw new IllegalArgumentException("arc id '" + arcId + "' must match " + TopicEntry.ID.pattern());
            }
            arc = Optional.of(arcId);
        }

        return new CheckDefinition(json.get("id").getAsString(), tier, axis, difficulty, stance, arc);
    }
}
