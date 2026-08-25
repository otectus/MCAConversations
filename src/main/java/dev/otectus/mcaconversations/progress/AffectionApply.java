package dev.otectus.mcaconversations.progress;

import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.conversation.DepthClass;
import dev.otectus.mcaconversations.conversation.TopicEntry;

import java.util.Optional;

/**
 * Parsed form of the {@code conversations_affection_apply} dialogue action — the only way authored
 * content is allowed to move hearts once a topic has been converted to a branching tree:
 *
 * <pre>{@code
 * "conversations_affection_apply": {
 *   "decision": "day.rough.empathize",
 *   "delta": 2,
 *   "budget": "quick",
 *   "policy": "daily_repeat"
 * }
 * }</pre>
 *
 * <p>{@code decision} is the stable decision id (plan §4.4) that keys anti-farming, debug output and
 * tests. {@code delta} is clamped to ±{@link AffectionMath#MAX_AUTHORED_DELTA} at parse, so no
 * datapack value can reach the runtime out of range. {@code budget} is optional — the live session's
 * depth class is used when it is absent. {@code policy} defaults to {@code daily_repeat} so a
 * third-party pack that omits it still behaves safely; this mod's own lint requires it explicitly.
 *
 * <p>Parse problems throw, so {@link dev.otectus.mcaconversations.util.SafeParse} contains them into
 * a no-op action: malformed affection data grants nothing rather than breaking the reload.
 */
public record AffectionApply(String decision, int delta, Optional<DepthClass> budget, ReplayPolicy policy) {

    public static AffectionApply fromJson(JsonObject json) {
        if (!json.has("decision") || json.get("decision").getAsString().isBlank()) {
            throw new IllegalArgumentException("conversations_affection_apply requires a \"decision\" id");
        }
        String decision = json.get("decision").getAsString();
        if (!TopicEntry.ID.matcher(decision).matches()) {
            throw new IllegalArgumentException("decision id '" + decision + "' must match "
                    + TopicEntry.ID.pattern());
        }
        if (!json.has("delta")) {
            throw new IllegalArgumentException("conversations_affection_apply requires a \"delta\"");
        }
        int delta = AffectionMath.clampAuthored(json.get("delta").getAsInt());

        Optional<DepthClass> budget = Optional.empty();
        if (json.has("budget")) {
            String key = json.get("budget").getAsString();
            budget = Optional.of(DepthClass.byKey(key).orElseThrow(() ->
                    new IllegalArgumentException("unknown affection budget class '" + key + "'")));
        }

        ReplayPolicy policy = ReplayPolicy.DAILY_REPEAT;
        if (json.has("policy")) {
            String key = json.get("policy").getAsString();
            policy = ReplayPolicy.byKey(key).orElseThrow(() ->
                    new IllegalArgumentException("unknown affection replay policy '" + key + "'"));
        }
        return new AffectionApply(decision, delta, budget, policy);
    }
}
