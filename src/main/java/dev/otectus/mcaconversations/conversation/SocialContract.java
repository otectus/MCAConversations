package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * What a scene assumes about the player and the villager, declared rather than hoped for (Stability
 * spec §9.3). A scene's {@code social} block:
 *
 * <pre>{"contact": ["recognized"], "attitudes": ["warm", "affectionate"],
 *  "claims": ["prior_meeting"], "requires_known_player_name": true}</pre>
 *
 * <p>Each part becomes an ordinary context condition, so the scene is simply not a candidate when the
 * pair does not qualify — absent from the set, never merely down-weighted. Every claim maps to a
 * server-side fact; none is judged by reading the line's prose. An unknown key, contact, attitude or
 * claim is refused, because a misspelled requirement that was silently ignored would let the line
 * reach exactly the people it was written to exclude.
 */
public record SocialContract(Set<String> contact, Set<String> attitudes, Set<Claim> claims,
                             boolean requiresKnownPlayerName) {

    /** The claims a line may make, and the fact each one needs. */
    public enum Claim {
        /** "Good to see you again" — they have met. */
        PRIOR_MEETING,
        /** Talks as a friend would — the villager is warm or affectionate towards the player. */
        PERSONAL_FRIENDSHIP,
        /** Talks as a spouse would. */
        ROMANTIC_RELATIONSHIP,
        /** Talks as a parent, child or sibling would. */
        FAMILY_TIE,
        /** Refers to the quarrel still standing between them. */
        UNRESOLVED_RUPTURE,
        /** Refers to something the two of them are in the middle of; the scene must bind an episode. */
        SHARED_EPISODE;

        public String key() {
            return name().toLowerCase(Locale.ROOT);
        }

        static Claim byKey(String raw) {
            for (Claim claim : values()) {
                if (claim.key().equals(raw)) {
                    return claim;
                }
            }
            throw new IllegalArgumentException("unknown social claim '" + raw + "'");
        }
    }

    private static final Set<String> FIELDS = Set.of("contact", "attitudes", "claims", "requires_known_player_name");

    public SocialContract {
        contact = Set.copyOf(contact);
        attitudes = Set.copyOf(attitudes);
        claims = claims.isEmpty() ? Set.of() : Set.copyOf(EnumSet.copyOf(claims));
    }

    public static SocialContract fromJson(JsonElement element) {
        if (element == null || !element.isJsonObject()) {
            throw new IllegalArgumentException("social must be an object");
        }
        JsonObject json = element.getAsJsonObject();
        for (String key : json.keySet()) {
            if (!FIELDS.contains(key)) {
                throw new IllegalArgumentException("unknown social field '" + key + "'");
            }
        }
        Set<String> contact = new LinkedHashSet<>();
        for (String raw : strings(json, "contact")) {
            if (SocialContact.byKey(raw).isEmpty()) {
                throw new IllegalArgumentException("unknown social contact '" + raw + "'");
            }
            contact.add(raw);
        }
        Set<String> attitudes = new LinkedHashSet<>();
        for (String raw : strings(json, "attitudes")) {
            if (SocialAttitude.byKey(raw).isEmpty()) {
                throw new IllegalArgumentException("unknown social attitude '" + raw + "'");
            }
            attitudes.add(raw);
        }
        Set<Claim> claims = EnumSet.noneOf(Claim.class);
        for (String raw : strings(json, "claims")) {
            claims.add(Claim.byKey(raw));
        }
        boolean knownName = false;
        if (json.has("requires_known_player_name")) {
            JsonElement flag = json.get("requires_known_player_name");
            if (!flag.isJsonPrimitive() || !flag.getAsJsonPrimitive().isBoolean()) {
                throw new IllegalArgumentException("requires_known_player_name must be a boolean");
            }
            knownName = flag.getAsBoolean();
        }
        return new SocialContract(contact, attitudes, claims, knownName);
    }

    /** True when the scene must bind an episode for this contract to be honest. */
    public boolean needsEpisode() {
        return claims.contains(Claim.SHARED_EPISODE);
    }

    /** The context conditions this contract amounts to, in the {@code ContextQuery} JSON shape. */
    public List<JsonObject> conditions() {
        List<JsonObject> out = new ArrayList<>();
        if (!contact.isEmpty()) {
            out.add(condition("social.contact", contact));
        }
        if (!attitudes.isEmpty()) {
            out.add(condition("social.attitude", attitudes));
        }
        if (requiresKnownPlayerName || claims.contains(Claim.PRIOR_MEETING)) {
            // A name is known to somebody who has met the player, or is family (whom contact counts
            // as met); MCA passing the name to a formatter does not mean the villager was told it.
            out.add(condition("social.contact", Set.of("recognized")));
        }
        if (claims.contains(Claim.PERSONAL_FRIENDSHIP)) {
            out.add(condition("social.attitude", Set.of("warm", "affectionate")));
        }
        if (claims.contains(Claim.ROMANTIC_RELATIONSHIP)) {
            out.add(condition("player.is_spouse", Set.of("true")));
        }
        if (claims.contains(Claim.FAMILY_TIE)) {
            out.add(condition("player.is_family", Set.of("true")));
        }
        if (claims.contains(Claim.UNRESOLVED_RUPTURE)) {
            out.add(condition("narrative.rupture", Set.of("true")));
        }
        return out;
    }

    private static JsonObject condition(String field, Set<String> anyOf) {
        JsonObject json = new JsonObject();
        json.addProperty("field", field);
        JsonArray values = new JsonArray();
        anyOf.stream().sorted().forEach(values::add);
        json.add("any_of", values);
        json.addProperty("unknown", "fail");
        return json;
    }

    private static List<String> strings(JsonObject json, String key) {
        List<String> out = new ArrayList<>();
        if (!json.has(key)) {
            return out;
        }
        if (!json.get(key).isJsonArray()) {
            throw new IllegalArgumentException("social " + key + " must be an array");
        }
        for (JsonElement item : json.getAsJsonArray(key)) {
            out.add(item.getAsString().trim().toLowerCase(Locale.ROOT));
        }
        return out;
    }
}
