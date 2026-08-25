package dev.otectus.mcaconversations.progress;

import com.google.gson.JsonObject;

import java.util.Locale;
import java.util.Optional;

/**
 * Parsed form of the {@code conversations_budget} dialogue condition:
 * {@code {"axis": "positive"|"negative"|"repeats", "min"?: 0, "max"?: 99, "decision"?: "day.rough.empathize"}}
 * — matches while today's counter for that axis lies in the inclusive range.
 *
 * <p>These counters have always been tracked per villager and player and exposed to nothing, so the
 * daily budget could silently clamp a heart gain to zero and the villager had no way to say
 * so. Reading them lets content wind a conversation down warmly at the cap instead of leaving the
 * player wondering why kindness stopped registering.
 *
 * <p>{@code repeats} counts how many times one decision has already fired today and therefore
 * requires a {@code decision}; the other two axes are per-conversation-partner totals and must not
 * name one.
 */
public record BudgetQuery(Axis axis, int min, int max, Optional<String> decision) {

    public enum Axis {
        POSITIVE, NEGATIVE, REPEATS;

        static Axis byKey(String key) {
            return switch (key.trim().toLowerCase(Locale.ROOT)) {
                case "positive" -> POSITIVE;
                case "negative" -> NEGATIVE;
                case "repeats" -> REPEATS;
                default -> throw new IllegalArgumentException(
                        "conversations_budget axis must be positive, negative or repeats: " + key);
            };
        }
    }

    public static BudgetQuery fromJson(JsonObject json) {
        if (!json.has("axis")) {
            throw new IllegalArgumentException("conversations_budget requires an \"axis\"");
        }
        Axis axis = Axis.byKey(json.get("axis").getAsString());
        int min = json.has("min") ? json.get("min").getAsInt() : 0;
        int max = json.has("max") ? json.get("max").getAsInt() : Integer.MAX_VALUE;
        if (min > max) {
            throw new IllegalArgumentException("conversations_budget min " + min + " > max " + max);
        }
        Optional<String> decision = json.has("decision")
                ? Optional.of(json.get("decision").getAsString().trim())
                : Optional.empty();
        if (axis == Axis.REPEATS && decision.isEmpty()) {
            throw new IllegalArgumentException(
                    "conversations_budget axis \"repeats\" counts one decision and requires \"decision\"");
        }
        if (axis != Axis.REPEATS && decision.isPresent()) {
            throw new IllegalArgumentException(
                    "conversations_budget axis \"" + axis.name().toLowerCase(Locale.ROOT)
                            + "\" is a daily total and must not name a \"decision\"");
        }
        return new BudgetQuery(axis, min, max, decision);
    }

    /** Today's value of this query's counter, for the given ledger record. */
    public int valueOf(ProgressRecord record, long day) {
        if (record == null) {
            return 0;
        }
        return switch (axis) {
            case POSITIVE -> record.positiveToday(day);
            case NEGATIVE -> record.negativeToday(day);
            case REPEATS -> record.repeatsToday(decision.orElseThrow(), day);
        };
    }

    public boolean matches(int value) {
        return value >= min && value <= max;
    }
}
