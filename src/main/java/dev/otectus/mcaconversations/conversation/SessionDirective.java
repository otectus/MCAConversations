package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonObject;

import java.util.Locale;
import java.util.Optional;

/**
 * Parsed form of the {@code conversations_session} dialogue action, which is how authored content
 * tells the runtime that a topic has started, branched, or finished:
 *
 * <pre>{@code
 * "conversations_session": {"op": "begin",  "topic": "day", "budget": "quick"}
 * "conversations_session": {"op": "branch", "branch": "rough"}
 * "conversations_session": {"op": "end"}
 * }</pre>
 *
 * <p>{@code begin} resets the per-conversation heart budget, so re-entering a topic never carries
 * leftover headroom. {@code branch} records which way the opener went, so a later node can tell a
 * rough day from a good one without duplicating the whole subtree. {@code end} closes the topic when
 * the player returns to a category — the session itself survives, because the player is still stood
 * in front of the villager.
 *
 * <p>{@code budget} is optional: the catalog's depth class for {@code topic} is used when it is
 * absent, which is the normal case and keeps the depth declared in exactly one place.
 */
public record SessionDirective(Op op, Optional<String> topic, Optional<DepthClass> budget, Optional<String> branch) {

    public enum Op { BEGIN, BRANCH, END }

    public static SessionDirective fromJson(JsonObject json) {
        if (!json.has("op")) {
            throw new IllegalArgumentException("conversations_session requires an \"op\"");
        }
        Op op = switch (json.get("op").getAsString().trim().toLowerCase(Locale.ROOT)) {
            case "begin" -> Op.BEGIN;
            case "branch" -> Op.BRANCH;
            case "end" -> Op.END;
            default -> throw new IllegalArgumentException(
                    "conversations_session op must be begin, branch or end");
        };

        Optional<String> topic = Optional.empty();
        if (json.has("topic")) {
            String value = json.get("topic").getAsString();
            if (!TopicEntry.ID.matcher(value).matches()) {
                throw new IllegalArgumentException("topic id '" + value + "' must match " + TopicEntry.ID.pattern());
            }
            topic = Optional.of(value);
        }
        if (op == Op.BEGIN && topic.isEmpty()) {
            throw new IllegalArgumentException("conversations_session begin requires a \"topic\"");
        }

        Optional<DepthClass> budget = Optional.empty();
        if (json.has("budget")) {
            String key = json.get("budget").getAsString();
            budget = Optional.of(DepthClass.byKey(key).orElseThrow(() ->
                    new IllegalArgumentException("unknown budget class '" + key + "'")));
        }

        Optional<String> branch = Optional.empty();
        if (json.has("branch")) {
            String value = json.get("branch").getAsString();
            if (!TopicEntry.ID.matcher(value).matches()) {
                throw new IllegalArgumentException("branch id '" + value + "' must match " + TopicEntry.ID.pattern());
            }
            branch = Optional.of(value);
        }
        if (op == Op.BRANCH && branch.isEmpty()) {
            throw new IllegalArgumentException("conversations_session branch requires a \"branch\"");
        }
        return new SessionDirective(op, topic, budget, branch);
    }
}
