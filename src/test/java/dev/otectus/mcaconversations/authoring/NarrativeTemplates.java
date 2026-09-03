package dev.otectus.mcaconversations.authoring;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * The three persistent-state templates an episode family compiles into, shared by both pack
 * compilers.
 *
 * <h2>Why this was extracted</h2>
 *
 * <p>Until 1.5.0 only {@link ProfessionPackCompiler} could emit a narrative template, and it wrote
 * three work-shaped things directly into the JSON: the owning profession, the literal topic
 * {@code "work"}, and a {@code resume_scenes} list built from work scene ids. The consequence was
 * visible in the shipped corpus — 111 of 111 episode templates, 111 of 111 threads and 35 of 35
 * commitments were {@code work.*}, so every villager's persistent life was their trade and nothing
 * else. A player who talked to the same farmer for a month met one recurring situation about crops
 * and no recurring situation about anything they had done together.
 *
 * <p>Those three things are now parameters. Nothing else changed: {@code ContentCompilerTest}
 * byte-compares the committed output against what these methods produce, so the 37 profession packs
 * are proof that the extraction preserved their shape exactly.
 */
final class NarrativeTemplates {

    private NarrativeTemplates() {
    }

    /**
     * The episode: what states this situation can be in, and which moves between them are legal.
     *
     * <p>Transitions are narrowed to what the authored states can actually reach rather than handed
     * the whole state machine, which is what makes an undeclared move a no-op the player never sees
     * instead of a state nothing has written a line for.
     *
     * @param owners professions that own this family; empty for a family that belongs to no trade
     */
    static void episode(ContentCompiler out, JsonObject episode, String kind, String subject,
                        Set<String> stateNames, List<String> owners) {
        JsonObject template = new JsonObject();
        template.addProperty("subject", subject);
        if (!owners.isEmpty()) {
            template.add("professions", ContentCompiler.array(owners));
        }
        template.addProperty("initial_state",
                episode.has("initial_state") ? episode.get("initial_state").getAsString() : "blocked");

        Set<String> declared = new LinkedHashSet<>(stateNames);
        declared.add("abandoned");
        declared.add("remembered");
        template.add("states", ContentCompiler.array(declared));

        List<String> transitions = new ArrayList<>();
        if (stateNames.contains("blocked") && stateNames.contains("active")) {
            transitions.add("blocked->active");
            transitions.add("active->blocked");
        }
        for (String from : List.of("blocked", "active")) {
            if (!stateNames.contains(from)) {
                continue;
            }
            for (String to : List.of("succeeded", "failed")) {
                if (stateNames.contains(to)) {
                    transitions.add(from + "->" + to);
                }
            }
            transitions.add(from + "->abandoned");
        }
        for (String terminal : List.of("succeeded", "failed", "abandoned")) {
            if (declared.contains(terminal)) {
                transitions.add(terminal + "->remembered");
            }
        }
        template.add("transitions", ContentCompiler.array(transitions));

        JsonObject slots = ContentCompiler.object(episode, "slots");
        if (slots != null && slots.size() > 0) {
            template.add("required_slots", ContentCompiler.array(slots.keySet()));
            JsonObject options = new JsonObject();
            slots.entrySet().forEach(entry -> options.add(entry.getKey(), entry.getValue()));
            template.add("slot_options", options);
        }
        template.addProperty("privacy",
                episode.has("privacy") ? episode.get("privacy").getAsString() : "ordinary");
        template.addProperty("salience",
                episode.has("salience") ? episode.get("salience").getAsInt() : 40);
        if (episode.has("due_after_days")) {
            template.addProperty("due_after_days", episode.get("due_after_days").getAsLong());
        }
        template.addProperty("expires_after_days",
                episode.has("expires_after_days") ? episode.get("expires_after_days").getAsLong() : 28L);
        if (episode.has("integrations")) {
            template.add("integrations", episode.get("integrations"));
        }
        out.addEpisode(kind, template);
    }

    /**
     * The thread: the pair's shared handle on the episode, and where to pick it up again.
     *
     * @param topic        the conversation topic the thread resumes under
     * @param resumeScenes the scenes that can carry it — the caller knows how its scene ids are built
     */
    static void thread(ContentCompiler out, JsonObject episode, String threadId, String kind,
                       String topic, String subject, List<String> resumeScenes) {
        JsonObject config = ContentCompiler.object(episode, "thread");
        JsonObject template = new JsonObject();
        template.addProperty("topic", topic);
        template.addProperty("subject", subject);
        template.addProperty("episode_kind", kind);
        template.add("resume_scenes", ContentCompiler.array(resumeScenes));
        template.addProperty("cooldown_days",
                config != null && config.has("cooldown_days")
                        ? config.get("cooldown_days").getAsLong() : 1L);
        template.addProperty("expires_after_days",
                config != null && config.has("expires_after_days")
                        ? config.get("expires_after_days").getAsLong() : 20L);
        template.addProperty("privacy",
                episode.has("privacy") ? episode.get("privacy").getAsString() : "ordinary");
        out.addThread(threadId, template);
    }

    /** The promise, when the family declares one. No-op otherwise. */
    static void commitment(ContentCompiler out, JsonObject episode, String threadId, String where) {
        JsonObject commitment = ContentCompiler.object(episode, "commitment");
        if (commitment == null) {
            return;
        }
        JsonObject template = new JsonObject();
        template.addProperty("resolver", ContentCompiler.require(commitment, "resolver", where));
        if (commitment.has("target")) {
            template.addProperty("target", commitment.get("target").getAsString());
        }
        template.addProperty("due_after_days",
                commitment.has("due_after_days") ? commitment.get("due_after_days").getAsLong() : 3L);
        template.addProperty("made_by", "player");
        template.addProperty("thread", threadId);
        out.addCommitment(ContentCompiler.require(commitment, "id", where), template);
    }
}
