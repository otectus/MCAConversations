package dev.otectus.mcaconversations.progress;

import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.conversation.TopicEntry;

import java.util.Optional;

/**
 * Parsed form of the {@code conversations_progress} dialogue condition — how authored content asks
 * where a relationship stands (plan §6.2):
 *
 * <pre>{@code
 * {"arc": "fears", "min": 1, "max": 2}                  // stage in range
 * {"milestone": "fears.revelation"}                     // milestone is set
 * {"milestone": "fears.revelation", "has": false}       // milestone is NOT set
 * {"exclusive": "fears.promise", "is": "kept"}          // this side was taken
 * {"exclusive": "fears.promise", "is": "none"}          // no side taken yet
 * }</pre>
 *
 * <p>Exactly one of {@code arc}, {@code milestone} or {@code exclusive} must be present, so a
 * condition always means one legible thing. Parse problems throw and
 * {@link dev.otectus.mcaconversations.util.SafeParse} turns the condition into one that never
 * matches — an authored typo silently withholds a branch instead of granting it.
 */
public sealed interface ProgressQuery {

    /** The sentinel {@code "is"} value meaning "no member of this group has been chosen". */
    String NONE = "none";

    record ArcStage(String arcId, int min, int max) implements ProgressQuery {
    }

    record Milestone(String milestoneId, boolean expected) implements ProgressQuery {
    }

    record Exclusive(String group, String member) implements ProgressQuery {
    }

    static ProgressQuery fromJson(JsonObject json) {
        int keys = (json.has("arc") ? 1 : 0) + (json.has("milestone") ? 1 : 0) + (json.has("exclusive") ? 1 : 0);
        if (keys != 1) {
            throw new IllegalArgumentException(
                    "conversations_progress needs exactly one of \"arc\", \"milestone\" or \"exclusive\"");
        }
        if (json.has("arc")) {
            String arcId = requireId(json.get("arc").getAsString(), "arc");
            int min = json.has("min") ? json.get("min").getAsInt() : 0;
            int max = json.has("max") ? json.get("max").getAsInt() : TopicEntry.MAX_ARC_STAGE;
            if (min > max) {
                throw new IllegalArgumentException("conversations_progress arc min " + min + " exceeds max " + max);
            }
            return new ArcStage(arcId, min, max);
        }
        if (json.has("milestone")) {
            String id = requireId(json.get("milestone").getAsString(), "milestone");
            boolean expected = !json.has("has") || json.get("has").getAsBoolean();
            return new Milestone(id, expected);
        }
        String group = requireId(json.get("exclusive").getAsString(), "exclusive group");
        if (!json.has("is")) {
            throw new IllegalArgumentException("conversations_progress exclusive requires an \"is\" member");
        }
        String member = json.get("is").getAsString();
        if (!NONE.equals(member)) {
            requireId(member, "exclusive member");
        }
        return new Exclusive(group, member);
    }

    private static String requireId(String value, String what) {
        if (value == null || !TopicEntry.ID.matcher(value).matches()) {
            throw new IllegalArgumentException(what + " id '" + value + "' must match " + TopicEntry.ID.pattern());
        }
        return value;
    }

    /** Evaluates against the plain state a caller reads out of the ledger. Pure. */
    static boolean matches(ProgressQuery query, int arcStage, boolean hasMilestone, Optional<String> exclusiveChoice) {
        if (query instanceof ArcStage arc) {
            return arcStage >= arc.min() && arcStage <= arc.max();
        }
        if (query instanceof Milestone milestone) {
            return hasMilestone == milestone.expected();
        }
        Exclusive exclusive = (Exclusive) query;
        if (NONE.equals(exclusive.member())) {
            return exclusiveChoice.isEmpty();
        }
        return exclusiveChoice.filter(chosen -> chosen.equals(exclusive.member())).isPresent();
    }
}
