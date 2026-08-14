package dev.otectus.mcaconversations.progress;

import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.conversation.TopicEntry;

import java.util.Locale;

/**
 * Parsed form of one {@code conversations_progress_apply} entry — how authored content moves durable
 * narrative state (plan §6.2). The action accepts a single object or an array of them, because a
 * result may need to advance an arc <em>and</em> set a milestone and JSON keys cannot repeat.
 *
 * <pre>{@code
 * {"arc": "fears", "op": "advance", "to": 1}   // one stage at a time, clamped to the catalog bound
 * {"arc": "fears", "op": "regress", "to": 1}   // a mishandled stage can lose ground
 * {"arc": "fears", "op": "hold"}               // deliberately does not move; documents the intent
 * {"milestone": "fears.revelation"}            // fires once, ever
 * {"exclusive": "fears.promise", "member": "kept"}  // first choice wins for good
 * }</pre>
 *
 * <p><b>Advance is bounded to a single stage per call.</b> Asking to advance two stages at once
 * lands on one, which enforces plan §6.3 in the runtime rather than only in lint — an arc cannot be
 * skipped even by a datapack that tries.
 */
public sealed interface ProgressApply {

    enum ArcOp { ADVANCE, REGRESS, HOLD }

    record Arc(String arcId, ArcOp op, int to) implements ProgressApply {
    }

    record Milestone(String milestoneId) implements ProgressApply {
    }

    record Exclusive(String group, String member) implements ProgressApply {
    }

    static ProgressApply fromJson(JsonObject json) {
        int keys = (json.has("arc") ? 1 : 0) + (json.has("milestone") ? 1 : 0) + (json.has("exclusive") ? 1 : 0);
        if (keys != 1) {
            throw new IllegalArgumentException(
                    "conversations_progress_apply needs exactly one of \"arc\", \"milestone\" or \"exclusive\"");
        }
        if (json.has("arc")) {
            String arcId = requireId(json.get("arc").getAsString(), "arc");
            String opKey = json.has("op") ? json.get("op").getAsString() : "advance";
            ArcOp op = switch (opKey.trim().toLowerCase(Locale.ROOT)) {
                case "advance" -> ArcOp.ADVANCE;
                case "regress" -> ArcOp.REGRESS;
                case "hold" -> ArcOp.HOLD;
                default -> throw new IllegalArgumentException("arc op must be advance, regress or hold");
            };
            int to = json.has("to") ? json.get("to").getAsInt() : -1;
            if (op != ArcOp.HOLD && (to < 0 || to > TopicEntry.MAX_ARC_STAGE)) {
                throw new IllegalArgumentException("arc " + opKey + " requires a \"to\" stage in 0.."
                        + TopicEntry.MAX_ARC_STAGE);
            }
            return new Arc(arcId, op, to);
        }
        if (json.has("milestone")) {
            return new Milestone(requireId(json.get("milestone").getAsString(), "milestone"));
        }
        String group = requireId(json.get("exclusive").getAsString(), "exclusive group");
        if (!json.has("member")) {
            throw new IllegalArgumentException("conversations_progress_apply exclusive requires a \"member\"");
        }
        return new Exclusive(group, requireId(json.get("member").getAsString(), "exclusive member"));
    }

    /**
     * The stage an advance/regress should land on, given where the arc stands and what the catalog
     * allows. Pure, so the one-stage-at-a-time rule and the clamp are unit-testable.
     */
    static int resolveStage(Arc arc, int current, int maxStage) {
        return switch (arc.op()) {
            case HOLD -> current;
            case ADVANCE -> Math.min(Math.min(arc.to(), current + 1), Math.max(0, maxStage));
            case REGRESS -> Math.max(0, Math.min(arc.to(), current));
        };
    }

    private static String requireId(String value, String what) {
        if (value == null || !TopicEntry.ID.matcher(value).matches()) {
            throw new IllegalArgumentException(what + " id '" + value + "' must match " + TopicEntry.ID.pattern());
        }
        return value;
    }
}
