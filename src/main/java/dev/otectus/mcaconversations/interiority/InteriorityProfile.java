package dev.otectus.mcaconversations.interiority;

import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.conversation.StanceFamily;
import dev.otectus.mcaconversations.disposition.DispositionAxis;

import java.util.EnumMap;
import java.util.Map;

/**
 * What one MCA personality is like underneath: where its disposition axes rest when nothing has
 * happened, and which kinds of thing it warms to or bristles at (plan §5.5).
 *
 * <p>Two fields, both consumed by real code — {@code baselines} by
 * {@link dev.otectus.mcaconversations.disposition.Dispositions#baseline}, {@code stance_bias} by
 * {@link dev.otectus.mcaconversations.check.CheckContextFactory}. Wants, boundaries and secret pools
 * are deliberately <em>not</em> here yet: they arrive with the topics that read them, because state
 * nothing reads is dead weight in a save file.
 *
 * <pre>{@code
 * "friendly": {
 *   "baselines":   {"warmth": 8, "trust": 4, "tension": 2},
 *   "stance_bias": {"empathy": 8, "practical_help": 4, "dismissal": -10}
 * }
 * }</pre>
 *
 * <p>Both maps are sparse: an axis or stance that is not listed is simply neutral. Values are
 * clamped at parse, so no datapack can make a personality dominate a check.
 */
public record InteriorityProfile(String personality,
                                 Map<DispositionAxis, Integer> baselines,
                                 Map<StanceFamily, Integer> stanceBias) {

    /** Resting baselines stay near neutral: a personality is a tendency, not a head start. */
    public static final int MAX_BASELINE = 15;
    /**
     * Stance fit is capped below {@link dev.otectus.mcaconversations.check.CheckResolver#CRIT_MARGIN}
     * so personality can move a check by less than a full tier on its own. It colours an outcome; it
     * never decides one.
     */
    public static final int MAX_STANCE_BIAS = 12;

    public static final InteriorityProfile NEUTRAL =
            new InteriorityProfile("", Map.of(), Map.of());

    public static InteriorityProfile fromJson(String personality, JsonObject json) {
        Map<DispositionAxis, Integer> baselines = new EnumMap<>(DispositionAxis.class);
        if (json.has("baselines") && json.get("baselines").isJsonObject()) {
            JsonObject baselineJson = json.getAsJsonObject("baselines");
            for (String key : baselineJson.keySet()) {
                DispositionAxis axis = DispositionAxis.byKey(key).orElseThrow(() ->
                        new IllegalArgumentException("unknown disposition axis '" + key
                                + "' in interiority profile '" + personality + "'"));
                int value = clamp(baselineJson.get(key).getAsInt(), MAX_BASELINE);
                // Unipolar axes cannot rest below zero however hopeful the data is.
                baselines.put(axis, Math.max(axis.min(), Math.min(axis.max(), value)));
            }
        }

        Map<StanceFamily, Integer> bias = new EnumMap<>(StanceFamily.class);
        if (json.has("stance_bias") && json.get("stance_bias").isJsonObject()) {
            JsonObject biasJson = json.getAsJsonObject("stance_bias");
            for (String key : biasJson.keySet()) {
                StanceFamily family = StanceFamily.byKey(key).orElseThrow(() ->
                        new IllegalArgumentException("unknown stance family '" + key
                                + "' in interiority profile '" + personality + "'"));
                bias.put(family, clamp(biasJson.get(key).getAsInt(), MAX_STANCE_BIAS));
            }
        }
        return new InteriorityProfile(personality, Map.copyOf(baselines), Map.copyOf(bias));
    }

    /** This personality's resting value for an axis; 0 when unlisted. */
    public int baseline(DispositionAxis axis) {
        return baselines.getOrDefault(axis, 0);
    }

    /** How well a stance family lands on this personality; 0 when unlisted. */
    public int stanceBias(StanceFamily family) {
        return family == null ? 0 : stanceBias.getOrDefault(family, 0);
    }

    private static int clamp(int value, int bound) {
        return Math.max(-bound, Math.min(bound, value));
    }
}
