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
 *
 * <p>{@code slots} names bound scene slots from the frozen {@code ConversationPlan}, and they fill the
 * positional args <em>after</em> the vars. That ordering is a contract with the locale files: a line
 * declaring {@code "vars": ["villager_name"], "slots": ["volume"]} renders the villager at
 * {@code %2$s} and the volume at {@code %3$s} in every language.
 *
 * <p>Unlike {@code vars}, a slot name is not validated at parse time. Slot names are authored beside
 * a scene rather than in a closed enum, and a scene that is not the selected one has no slots at all —
 * so an unbound slot renders as a neutral fallback rather than failing the datapack (spec §18.5).
 */
public record SayDirective(String phrase, List<TemplateVariable> vars, List<String> slots) {

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
        List<String> slots = new ArrayList<>();
        if (json.has("slots")) {
            for (JsonElement e : json.getAsJsonArray("slots")) {
                String name = e.getAsString().trim().toLowerCase(java.util.Locale.ROOT);
                if (!name.isEmpty()) {
                    slots.add(name);
                }
            }
        }
        return new SayDirective(phrase, List.copyOf(vars), List.copyOf(slots));
    }

    /** A directive with no slots, for the many lines that need none. */
    public static SayDirective of(String phrase, List<TemplateVariable> vars) {
        return new SayDirective(phrase, List.copyOf(vars), List.of());
    }
}
