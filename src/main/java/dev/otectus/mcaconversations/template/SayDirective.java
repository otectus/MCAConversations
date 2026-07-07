package dev.otectus.mcaconversations.template;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Parsed form of the {@code conversations_say} action JSON:
 * {@code {"phrase": "conversations.us.firstmet", "vars": ["villager_name"]}}.
 *
 * <p>{@code phrase} is the lang key <em>without</em> the {@code dialogue.} prefix (mirroring MCA's
 * own {@code say} action). {@code vars} is optional.
 */
public record SayDirective(String phrase, List<TemplateVariable> vars) {

    /** @throws IllegalArgumentException on missing phrase or unknown var name (fails at datapack parse). */
    public static SayDirective fromJson(JsonObject json) {
        if (json == null || !json.has("phrase")) {
            throw new IllegalArgumentException("conversations_say requires a \"phrase\"");
        }
        String phrase = json.get("phrase").getAsString();
        if (phrase.isBlank()) {
            throw new IllegalArgumentException("conversations_say \"phrase\" must not be blank");
        }
        List<TemplateVariable> vars = new ArrayList<>();
        if (json.has("vars")) {
            for (JsonElement e : json.getAsJsonArray("vars")) {
                String name = e.getAsString();
                vars.add(TemplateVariable.byJsonName(name).orElseThrow(
                        () -> new IllegalArgumentException("conversations_say unknown var \"" + name + "\"")));
            }
        }
        return new SayDirective(phrase, List.copyOf(vars));
    }
}
