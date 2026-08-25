package dev.otectus.mcaconversations.compat.quests;

import com.google.gson.JsonObject;

import java.util.Locale;

/**
 * Parsed args for the {@code conversations_quest_*} dialogue conditions:
 * {@code {"scope": "this"|"any", "min": 1}}. Both fields optional (default scope {@code THIS}, min 1).
 *
 * <p>This record has no {@code dev.otectus.mcaquests.*} imports, so it is safe to reference from the
 * MCA-importing registrar even on an MCA-only install. An unknown {@code scope} (or negative {@code min})
 * throws, so {@code SafeParse.orNull} degrades the condition to never-matching rather than crashing MCA's
 * containment-free {@code Dialogues} loader.
 */
public record QuestConditionQuery(Scope scope, int min) {

    public enum Scope { THIS, ANY }

    /** True when the condition should consider only the villager being talked to (not quests anywhere). */
    public boolean thisVillagerOnly() {
        return scope == Scope.THIS;
    }

    public static QuestConditionQuery fromJson(JsonObject json) {
        Scope scope = Scope.THIS;
        if (json.has("scope")) {
            String raw = json.get("scope").getAsString().toLowerCase(Locale.ROOT);
            scope = switch (raw) {
                case "this" -> Scope.THIS;
                case "any" -> Scope.ANY;
                default -> throw new IllegalArgumentException("conversations quest condition: unknown scope '" + raw + "'");
            };
        }
        int min = json.has("min") ? json.get("min").getAsInt() : 1;
        if (min < 0) {
            throw new IllegalArgumentException("conversations quest condition: min must be >= 0, was " + min);
        }
        return new QuestConditionQuery(scope, min);
    }
}
