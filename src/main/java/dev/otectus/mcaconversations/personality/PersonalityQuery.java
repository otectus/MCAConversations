package dev.otectus.mcaconversations.personality;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The parsed form of a {@code conversations_personality} dialogue condition: the set of
 * personalities a result is written for.
 *
 * <p><b>Why we do not use MCA's native {@code personality} condition.</b> MCA parses that value
 * with {@code Personality.get(name).orElseThrow(JsonSyntaxException::new)} and
 * {@code Dialogues.apply} has no error containment, so a single id that the running MCA does not
 * know aborts the whole datapack reload and the world fails to load. MCA 7.7 removed
 * {@code witty}, {@code shy}, {@code lazy}, {@code grumpy} and {@code athletic}, so content
 * naming them crashes 7.7 — and content naming their 7.7 successors would crash 7.6. This query
 * never throws: an unknown id simply never matches, and
 * {@link Personalities#matches(String, String)} makes one authored id match both spellings.
 *
 * <p>Accepts a bare string ({@code "odd"}) or an array ({@code ["odd", "playful"]}).
 */
public record PersonalityQuery(Set<String> personalities) {

    public PersonalityQuery {
        personalities = Set.copyOf(personalities);
    }

    /** Parses the condition value. Returns {@code null} for a shape we cannot read. */
    public static PersonalityQuery fromJson(JsonElement json) {
        if (json == null) {
            return null;
        }
        Set<String> wanted = new LinkedHashSet<>();
        if (json.isJsonPrimitive()) {
            wanted.add(json.getAsString());
        } else if (json.isJsonArray()) {
            JsonArray arr = json.getAsJsonArray();
            for (JsonElement e : arr) {
                if (e.isJsonPrimitive()) {
                    wanted.add(e.getAsString());
                }
            }
        } else {
            return null;
        }
        wanted.removeIf(s -> s == null || s.isBlank());
        return wanted.isEmpty() ? null : new PersonalityQuery(wanted);
    }

    /**
     * True when the villager's personality is one this result was written for, comparing through
     * the canonical roster so a 7.6 {@code witty} villager matches an authored {@code upbeat}
     * (and vice versa).
     */
    public boolean matches(String villagerPersonality) {
        if (villagerPersonality == null || villagerPersonality.isBlank()) {
            return false;
        }
        for (String wanted : personalities) {
            if (Personalities.matches(wanted, villagerPersonality)) {
                return true;
            }
        }
        return false;
    }
}
