package dev.otectus.mcaconversations.disposition;

import com.google.gson.JsonObject;

import java.util.EnumMap;
import java.util.Map;

/**
 * Parsed form of the {@code conversations_disposition_apply} dialogue action:
 * {@code {"topic": "fears.challenge", "deltas": {"respect": 6, "tension": -2}}}. The topic keys the
 * same-day repeat guard; per-delta magnitude is capped at {@link #MAX_DELTA} so no single authored
 * line can swing an axis dramatically. Parse problems throw so
 * {@link dev.otectus.mcaconversations.util.SafeParse} contains them into a no-op action.
 */
public record DispositionApply(String topic, Map<DispositionAxis, Integer> deltas) {

    /** Bound on a single authored delta; the lint pins this too. */
    public static final int MAX_DELTA = 10;

    public static DispositionApply fromJson(JsonObject json) {
        if (!json.has("topic") || json.get("topic").getAsString().isEmpty()) {
            throw new IllegalArgumentException("conversations_disposition_apply requires a \"topic\"");
        }
        if (!json.has("deltas") || !json.get("deltas").isJsonObject()
                || json.getAsJsonObject("deltas").size() == 0) {
            throw new IllegalArgumentException("conversations_disposition_apply requires non-empty \"deltas\"");
        }
        Map<DispositionAxis, Integer> deltas = new EnumMap<>(DispositionAxis.class);
        JsonObject deltasJson = json.getAsJsonObject("deltas");
        for (String key : deltasJson.keySet()) {
            DispositionAxis axis = DispositionAxis.byKey(key)
                    .orElseThrow(() -> new IllegalArgumentException("unknown disposition axis: " + key));
            int delta = deltasJson.get(key).getAsInt();
            if (Math.abs(delta) > MAX_DELTA) {
                throw new IllegalArgumentException("disposition delta " + delta + " exceeds ±" + MAX_DELTA);
            }
            deltas.put(axis, delta);
        }
        return new DispositionApply(json.get("topic").getAsString(), Map.copyOf(deltas));
    }
}
