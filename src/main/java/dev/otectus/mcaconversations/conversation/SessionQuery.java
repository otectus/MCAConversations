package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonObject;

import java.util.Locale;
import java.util.Optional;

/**
 * Parsed form of the {@code conversations_session} dialogue <em>condition</em>:
 * {@code {"topic"?: "day", "branch"?: "rough"}} — matches while the player's live session is inside
 * that topic and/or that branch. Both fields are optional; an empty object matches whenever any
 * topic session is open at all.
 *
 * <p>The matching action has always written a {@code branch} — 114 shipped results set one — and
 * nothing ever read it back, so content compensated by duplicating the branch into the node name
 * ({@code conversations.topic.day.rough.respond}). Being able to ask the session instead is what
 * lets sibling branches share a node without drifting apart.
 *
 * <p>Parse problems throw so {@link dev.otectus.mcaconversations.util.SafeParse} contains them into
 * a never-matching condition rather than a load failure.
 */
public record SessionQuery(Optional<String> topic, Optional<String> branch) {

    public static SessionQuery fromJson(JsonObject json) {
        Optional<String> topic = optionalId(json, "topic");
        Optional<String> branch = optionalId(json, "branch");
        return new SessionQuery(topic, branch);
    }

    private static Optional<String> optionalId(JsonObject json, String field) {
        if (!json.has(field)) {
            return Optional.empty();
        }
        String value = json.get(field).getAsString().trim().toLowerCase(Locale.ROOT);
        if (value.isEmpty()) {
            throw new IllegalArgumentException("conversations_session \"" + field + "\" must not be blank");
        }
        return Optional.of(value);
    }

    /** True when a live session exists and matches every field this query names. */
    public boolean matches(ConversationSession session) {
        if (session == null) {
            return false;
        }
        if (topic.isPresent() && !topic.equals(session.topicId().map(t -> t.toLowerCase(Locale.ROOT)))) {
            return false;
        }
        return branch.isEmpty() || branch.equals(session.branch().map(b -> b.toLowerCase(Locale.ROOT)));
    }
}
