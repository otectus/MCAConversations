package dev.otectus.mcaconversations.scene;

import com.google.gson.JsonObject;

import java.util.Locale;

/**
 * The parsed form of the {@code conversations_scene} dialogue condition (spec §10.6).
 *
 * <pre>{@code
 * {"chance": 800,  "conversations_scene": {"is": "work.librarian.damaged_volume.blocked"}}
 * {"chance": -5000,"conversations_scene": {"is": "work.librarian.damaged_volume.blocked", "not": true}}
 * }</pre>
 *
 * <p>The pair above is the whole integration pattern, and the negated half is the load-bearing one.
 * MCA scores every candidate result and picks a winner, so a dynamic route needs both a large bonus
 * when its scene was chosen <em>and</em> a large sink when it was not — otherwise a scene the director
 * rejected could still win on base chance and speak a line whose facts were never bound.
 *
 * <p>This condition only ever <b>reads</b> the frozen plan. It never selects, never re-selects, and
 * never falls back to selecting when there is no plan: with no plan every scene condition scores zero
 * and the exchange takes the static 1.4.0 route (spec §10.6).
 */
public record SceneQuery(String sceneId, boolean negate) {

    /** A query that can never match: no scene named. */
    public static final SceneQuery INVALID = new SceneQuery("", false);

    public SceneQuery {
        sceneId = sceneId == null ? "" : sceneId.trim().toLowerCase(Locale.ROOT);
    }

    public boolean isValid() {
        return !sceneId.isEmpty();
    }

    public static SceneQuery fromJson(JsonObject json) {
        if (json == null || !json.has("is")) {
            return INVALID;
        }
        return new SceneQuery(json.get("is").getAsString(),
                json.has("not") && json.get("not").getAsBoolean());
    }

    /** Scores against the frozen plan. An absent plan is a non-match before negation is applied. */
    public boolean matches(ConversationPlan plan) {
        if (!isValid()) {
            return false;
        }
        boolean matched = plan != null && plan.sceneId().equals(sceneId);
        return negate != matched;
    }
}
