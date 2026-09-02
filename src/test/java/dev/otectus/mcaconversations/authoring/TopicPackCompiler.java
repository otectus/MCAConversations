package dev.otectus.mcaconversations.authoring;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Expands one non-work topic's authoring source into runtime content (spec §13, §14).
 *
 * <h2>How a topic pack differs from a profession pack</h2>
 *
 * <p>A profession scene is gated on an <em>episode</em>: a situation with a state that changes, which
 * is what makes "still" and "it held" checkable. Most other topics have no such object. What makes a
 * weather remark or a check-in specific is the <em>context</em> and the <em>identity</em> — the hour,
 * the season, what she is doing, what she values, what the two of you last talked about.
 *
 * <p>So a topic scene is gated on context queries and profile queries instead, and its slots bind from
 * the snapshot rather than from an episode payload. Everything downstream is the same: the same scene
 * schema, the same contracts, the same director, the same lints.
 *
 * <p>A topic pack may still declare an episode when the subject genuinely has one — a worry that is
 * about something, a hope with a first step — and then it behaves exactly like a profession pack's.
 */
final class TopicPackCompiler {

    private final ContentCompiler out;
    private final JsonObject source;
    private final String where;
    private final String topic;
    private final String entryQuestion;
    private final String entryAnswer;
    private final List<String> ages;

    TopicPackCompiler(ContentCompiler out, JsonObject source, Path file) {
        this.out = out;
        this.source = source;
        this.where = file.getFileName().toString();
        this.topic = ContentCompiler.require(source, "topic", where);
        JsonObject entry = ContentCompiler.object(source, "entry");
        this.entryQuestion = ContentCompiler.require(entry, "question", where);
        this.entryAnswer = ContentCompiler.require(entry, "answer", where);
        this.ages = ContentCompiler.strings(source, "ages").isEmpty()
                ? List.of("teen", "adult") : ContentCompiler.strings(source, "ages");
    }

    void compile() {
        // The owner is whoever last wrote content, and a topic pack that inherits the previous
        // profession pack's owner ends up filed under a mod it has nothing to do with.
        out.beginOwner("");
        compileSlotLang();
        JsonArray scenes = source.getAsJsonArray("scenes");
        if (scenes == null || scenes.isEmpty()) {
            throw new IllegalStateException(where + " declares no scenes");
        }
        for (JsonElement element : scenes) {
            compileScene(element.getAsJsonObject());
        }
        compileEpisodes(scenes);
        compileFollowup();
        compileFunnel();
    }

    /**
     * The episode families this topic owns, if it owns any.
     *
     * <p>A topic pack could already point a scene at an episode; what it could not do was create
     * one, so every persistent situation in the game belonged to somebody's trade. A villager's life
     * was their job and nothing else — no running thread about the village, their family, or
     * anything the two of you had actually done together.
     *
     * <p>Compiled after the scenes rather than before, because {@code resume_scenes} is derived from
     * whichever scenes bound this kind rather than being written out by hand. Deriving it is what
     * stops a thread naming a scene that was renamed or removed underneath it.
     */
    private void compileEpisodes(JsonArray scenes) {
        JsonArray episodes = source.getAsJsonArray("episodes");
        if (episodes == null || episodes.isEmpty()) {
            return;
        }
        for (JsonElement element : episodes) {
            JsonObject episode = element.getAsJsonObject();
            String kind = ContentCompiler.require(episode, "kind", where);
            if (kind.startsWith("work.")) {
                // Profession packs compile first and addEpisode throws on a duplicate kind, so this
                // would be a confusing failure a long way from its cause.
                throw new IllegalStateException(where + " episode '" + kind
                        + "' uses the work. prefix, which belongs to profession packs");
            }
            String subject = episode.has("subject")
                    ? episode.get("subject").getAsString() : kind;
            Set<String> stateNames = new LinkedHashSet<>();
            List<String> resumeScenes = new ArrayList<>();
            for (JsonElement sceneElement : scenes) {
                JsonObject scene = sceneElement.getAsJsonObject();
                if (!scene.has("episode_kind")
                        || !kind.equals(scene.get("episode_kind").getAsString())) {
                    continue;
                }
                resumeScenes.add("topic." + topic + "."
                        + ContentCompiler.require(scene, "name", where));
                for (String state : ContentCompiler.strings(scene, "episode_state")) {
                    stateNames.add(state);
                }
            }
            if (stateNames.isEmpty()) {
                throw new IllegalStateException(where + " episode '" + kind
                        + "' has no scene bound to it, so nothing could ever speak from it");
            }
            String threadId = topic + "." + kind.substring(kind.indexOf('.') + 1);
            NarrativeTemplates.episode(out, episode, kind, subject, stateNames, List.of());
            NarrativeTemplates.thread(out, episode, threadId, kind, topic, subject, resumeScenes);
            NarrativeTemplates.commitment(out, episode, threadId, where);
        }
    }

    private void compileSlotLang() {
        JsonObject slotLang = ContentCompiler.object(source, "slot_lang");
        if (slotLang == null) {
            return;
        }
        for (Map.Entry<String, JsonElement> entry : slotLang.entrySet()) {
            JsonObject pair = entry.getValue().getAsJsonObject();
            out.addLang("mcaconversations.slot." + entry.getKey(),
                    ContentCompiler.require(pair, "en", where + " slot " + entry.getKey()),
                    ContentCompiler.require(pair, "pt", where + " slot " + entry.getKey()));
        }
    }

    private void compileScene(JsonObject definition) {
        String name = ContentCompiler.require(definition, "name", where);
        List<String> integrations = ContentCompiler.strings(definition, "integrations");
        out.beginOwner(integrations.isEmpty() ? "" : integrations.get(0));
        String sceneId = "topic." + topic + "." + name;
        String questionId = "conversations.scene." + topic + "." + name + ".respond";
        String beatId = topic + "." + name + ".open";
        String sayKey = "conversations.scene." + topic + "." + name;
        String subject = definition.has("subject")
                ? definition.get("subject").getAsString() : topic + "." + name;
        List<String> slotsUsed = ContentCompiler.strings(definition, "slots_used");
        List<String> obligations = ContentCompiler.strings(definition, "obligations");
        if (obligations.isEmpty()) {
            obligations = List.of("acknowledge");
        }
        String shape = definition.has("shape") ? definition.get("shape").getAsString() : "observe";

        // --- scene ---------------------------------------------------------------------------
        JsonObject scene = new JsonObject();
        scene.addProperty("purpose", "topic:" + topic);
        scene.addProperty("shape", shape);

        JsonObject profile = new JsonObject();
        profile.add("ages", ContentCompiler.array(
                ContentCompiler.strings(definition, "ages").isEmpty()
                        ? ages : ContentCompiler.strings(definition, "ages")));
        if (definition.has("professions")) {
            profile.add("profession", definition.get("professions"));
        }
        if (definition.has("archetypes")) {
            profile.add("archetypes", definition.get("archetypes"));
        }
        if (definition.has("relationships")) {
            profile.add("relationships", definition.get("relationships"));
        }
        scene.add("profile", profile);

        JsonObject context = new JsonObject();
        if (definition.has("episode_kind")) {
            context.addProperty("episode_kind", definition.get("episode_kind").getAsString());
            context.add("episode_state", definition.get("episode_state"));
        }
        JsonObject requiredSlots = new JsonObject();
        JsonObject slotTypes = ContentCompiler.object(definition, "slot_types");
        for (String slot : slotsUsed) {
            requiredSlots.addProperty(slot, slotTypes != null && slotTypes.has(slot)
                    ? slotTypes.get(slot).getAsString() : "localized_token");
        }
        if (requiredSlots.size() > 0) {
            context.add("required_slots", requiredSlots);
        }
        if (definition.has("conditions")) {
            context.add("conditions", definition.get("conditions"));
        }
        if (definition.has("identity")) {
            context.add("identity", definition.get("identity"));
        }
        if (definition.has("integrations")) {
            context.add("integrations", definition.get("integrations"));
        }
        if (context.size() > 0) {
            scene.add("context", context);
        }

        JsonObject selection = new JsonObject();
        selection.addProperty("base_priority",
                definition.has("base_priority") ? definition.get("base_priority").getAsInt() : 18);
        for (String key : List.of("identity_values", "identity_interests", "identity_styles")) {
            if (definition.has(key)) {
                selection.add(key, definition.get(key));
            }
        }
        selection.addProperty("cooldown_days",
                definition.has("cooldown_days") ? definition.get("cooldown_days").getAsInt() : 2);
        selection.addProperty("max_mentions_per_7_days",
                definition.has("max_mentions_per_7_days")
                        ? definition.get("max_mentions_per_7_days").getAsInt() : 2);
        scene.add("selection", selection);

        JsonObject route = new JsonObject();
        route.addProperty("question", questionId);
        route.addProperty("opening_beat", beatId);
        scene.add("route", route);
        if (definition.has("thread")) {
            JsonObject episodeBlock = new JsonObject();
            episodeBlock.addProperty("thread", definition.get("thread").getAsString());
            scene.add("episode", episodeBlock);
        }
        if (definition.has("fallback")) {
            scene.addProperty("fallback", definition.get("fallback").getAsString());
        }
        out.addScene(sceneId, scene);
        sceneIds.add(sceneId);

        // --- opening beat ---------------------------------------------------------------------
        JsonArray replies = definition.getAsJsonArray("replies");
        if (replies == null || replies.isEmpty()) {
            throw new IllegalStateException(where + " scene '" + sceneId + "' has no replies");
        }
        Set<String> allowed = new LinkedHashSet<>();
        for (JsonElement element : replies) {
            allowed.add(ContentCompiler.require(element.getAsJsonObject(), "stance", where));
        }
        allowed.add("exit");

        JsonObject beat = new JsonObject();
        beat.addProperty("topic", topic);
        beat.addProperty("say", sayKey);
        beat.addProperty("response_question", questionId);
        beat.addProperty("npc_act", definition.has("act") ? definition.get("act").getAsString() : "report");
        beat.addProperty("subject", subject);
        beat.addProperty("polarity",
                definition.has("polarity") ? definition.get("polarity").getAsString() : "mixed");
        beat.addProperty("openness", "invites_followup");
        beat.add("facts", ContentCompiler.array(List.of("topic:" + topic)));
        beat.add("allowed_stances", ContentCompiler.array(allowed));
        beat.add("forbidden_stances", ContentCompiler.array(forbidden(allowed)));
        JsonObject beatContext = new JsonObject();
        beatContext.add("ages", ContentCompiler.array(
                ContentCompiler.strings(definition, "ages").isEmpty()
                        ? ages : ContentCompiler.strings(definition, "ages")));
        if (definition.has("relationships")) {
            beatContext.add("relationships", definition.get("relationships"));
        }
        beat.add("context", beatContext);
        beat.add("frame", frame(
                definition.has("predicate") ? definition.get("predicate").getAsString() : "observation",
                definition.has("temporal") ? definition.get("temporal").getAsString() : "current",
                definition.has("epistemic") ? definition.get("epistemic").getAsString() : "observed",
                definition.has("privacy") ? definition.get("privacy").getAsString() : "ordinary",
                obligations, slotsUsed,
                definition.has("episode_state")
                        ? ContentCompiler.strings(definition, "episode_state") : List.of(),
                shape));
        out.addBeat(beatId, beat);
        addPool(sayKey, definition);

        JsonObject prompt = ContentCompiler.object(definition, "prompt");
        out.addLang("dialogue." + questionId,
                ContentCompiler.require(prompt, "en", where + " " + sceneId + " prompt"),
                ContentCompiler.require(prompt, "pt", where + " " + sceneId + " prompt"));

        JsonArray answers = new JsonArray();
        for (JsonElement element : replies) {
            answers.add(compileReply(element.getAsJsonObject(), definition, name, beatId, questionId,
                    sayKey, subject, slotsUsed, obligations));
        }
        answers.add(exitAnswer(questionId));
        JsonObject page = new JsonObject();
        page.add("answers", answers);
        out.addDialogue(questionId, page);

        JsonObject exit = new JsonObject();
        exit.addProperty("stance", "exit");
        exit.add("responds_to", ContentCompiler.array(List.of(beatId)));
        exit.addProperty("tone", "plain");
        exit.addProperty("exit", true);
        out.addReply(questionId + "/leave", exit);
        out.addLang("dialogue." + questionId + ".leave",
                ContentCompiler.require(ContentCompiler.object(source, "leave_label"), "en", where),
                ContentCompiler.require(ContentCompiler.object(source, "leave_label"), "pt", where));

        out.addEntryRoute(entryQuestion + "/" + entryAnswer,
                entryRoute(sceneId, beatId, questionId, sayKey, slotsUsed));
    }

    private JsonObject compileReply(JsonObject reply, JsonObject scene, String sceneName,
                                    String inboundBeat, String questionId, String sayKey,
                                    String subject, List<String> sceneSlots,
                                    List<String> sceneObligations) {
        String name = ContentCompiler.require(reply, "name", where);
        String stance = ContentCompiler.require(reply, "stance", where);
        List<String> answers = ContentCompiler.strings(reply, "answers");
        String replyKey = questionId + "/" + name;
        String move = reply.has("move") ? reply.get("move").getAsString() : "";
        if (answers.isEmpty() && move.isEmpty()) {
            throw new IllegalStateException(replyKey + " neither answers an obligation nor moves topic");
        }
        for (String answer : answers) {
            if (!sceneObligations.contains(answer)) {
                throw new IllegalStateException(replyKey + " answers '" + answer
                        + "', which its inbound beat does not make relevant " + sceneObligations);
            }
        }

        JsonObject contract = new JsonObject();
        contract.addProperty("stance", stance);
        contract.add("responds_to", ContentCompiler.array(List.of(inboundBeat)));
        contract.addProperty("tone", reply.has("tone") ? reply.get("tone").getAsString() : "plain");
        contract.add("outcomes", ContentCompiler.array(List.of(
                reply.has("outcome") ? reply.get("outcome").getAsString() : "engaged")));
        if (!answers.isEmpty()) {
            contract.add("answers_obligation", ContentCompiler.array(answers));
        }
        if (!move.isEmpty()) {
            contract.addProperty("move", move);
        }
        if (!sceneSlots.isEmpty()) {
            contract.add("uses_referents", ContentCompiler.array(sceneSlots));
        }
        if (reply.has("claim")) {
            contract.add("claim", reply.get("claim"));
        }
        for (String key : List.of("epistemic_move", "privacy_move", "temporal_move")) {
            if (reply.has(key)) {
                contract.addProperty(key, reply.get(key).getAsString());
            }
        }
        out.addReply(replyKey, contract);

        JsonObject label = ContentCompiler.object(reply, "label");
        out.addLang("dialogue." + questionId + "." + name,
                ContentCompiler.require(label, "en", where + " " + replyKey + " label"),
                ContentCompiler.require(label, "pt", where + " " + replyKey + " label"));

        JsonObject reaction = ContentCompiler.object(reply, "reaction");
        if (reaction == null) {
            throw new IllegalStateException(replyKey + " has no reaction; every reply is answered");
        }
        String reactionId = ContentCompiler.require(reaction, "id", where);
        String reactionBeat = inboundBeat + "." + reactionId;
        String reactionSay = sayKey + "." + reactionId;
        List<String> reactionSlots = ContentCompiler.strings(reaction, "slots_used");

        // A reaction that carries its own replies earns a page of its own, and the beat has to point
        // at it: the stance rules are contracted against whichever beat opened the page, so a third
        // turn hung off the shared followup would be offering buttons that answer a different line.
        JsonArray followOnReplies = reaction.getAsJsonArray("replies");
        boolean hasFollowOn = followOnReplies != null && !followOnReplies.isEmpty();
        String followOnQuestion = "conversations.scene." + topic + "." + sceneName + "."
                + reactionId + ".respond";
        List<String> reactionObligations = ContentCompiler.strings(reaction, "obligations");
        if (reactionObligations.isEmpty()) {
            reactionObligations = List.of("acknowledge");
        }
        Set<String> reactionStances = new LinkedHashSet<>();
        if (hasFollowOn) {
            for (JsonElement element : followOnReplies) {
                reactionStances.add(ContentCompiler.require(
                        element.getAsJsonObject(), "stance", where));
            }
        } else {
            reactionStances.addAll(List.of("curiosity", "candor"));
        }
        reactionStances.add("exit");

        JsonObject beat = new JsonObject();
        beat.addProperty("topic", topic);
        beat.addProperty("say", reactionSay);
        beat.addProperty("response_question",
                hasFollowOn ? followOnQuestion : followupQuestionId());
        beat.addProperty("npc_act", ContentCompiler.require(reaction, "act", where));
        beat.addProperty("subject", subject);
        beat.addProperty("polarity",
                reaction.has("polarity") ? reaction.get("polarity").getAsString() : "mixed");
        beat.addProperty("openness", "permits_followup");
        beat.add("facts", ContentCompiler.array(List.of("topic:" + topic)));
        beat.add("allowed_stances", ContentCompiler.array(reactionStances));
        beat.add("forbidden_stances", ContentCompiler.array(forbidden(reactionStances)));
        beat.addProperty("outcome",
                reply.has("outcome") ? reply.get("outcome").getAsString() : "engaged");
        JsonObject beatContext = new JsonObject();
        beatContext.add("ages", ContentCompiler.array(
                ContentCompiler.strings(scene, "ages").isEmpty()
                        ? ages : ContentCompiler.strings(scene, "ages")));
        beat.add("context", beatContext);
        beat.add("frame", frame(
                reaction.has("predicate") ? reaction.get("predicate").getAsString() : "observation",
                reaction.has("temporal") ? reaction.get("temporal").getAsString() : "current",
                reaction.has("epistemic") ? reaction.get("epistemic").getAsString() : "observed",
                reaction.has("privacy") ? reaction.get("privacy").getAsString() : "ordinary",
                reactionObligations, reactionSlots,
                scene.has("episode_state") ? ContentCompiler.strings(scene, "episode_state") : List.of(),
                reaction.has("shape") ? reaction.get("shape").getAsString() : "observe"));
        out.addBeat(reactionBeat, beat);
        addPool(reactionSay, reaction);
        if (hasFollowOn) {
            compileFollowOnPage(scene, sceneName, reaction, followOnReplies, followOnQuestion,
                    reactionBeat, reactionSay, subject, reactionSlots, reactionObligations);
        }

        JsonObject actions = new JsonObject();
        JsonObject session = new JsonObject();
        session.addProperty("op", "turn");
        session.addProperty("beat", reactionBeat);
        actions.add("conversations_session", session);
        if (reply.has("affection")) {
            actions.add("conversations_affection_apply", reply.get("affection"));
        }
        if (reply.has("disposition")) {
            JsonObject disposition = new JsonObject();
            disposition.addProperty("topic", subject);
            disposition.add("deltas", reply.get("disposition"));
            actions.add("conversations_disposition_apply", disposition);
        }
        if (reply.has("claim")) {
            JsonObject claim = new JsonObject();
            claim.addProperty("op", "record");
            claim.addProperty("type", reply.getAsJsonObject("claim").get("type").getAsString());
            claim.addProperty("value", reply.getAsJsonObject("claim").get("value").getAsString());
            claim.addProperty("source", replyKey);
            actions.add("conversations_claim", claim);
        }
        String commitmentId = reply.has("commitment") ? reply.get("commitment").getAsString() : "";
        if (scene.has("thread")) {
            JsonObject thread = new JsonObject();
            thread.addProperty("op", reply.has("resolves_thread")
                    && reply.get("resolves_thread").getAsBoolean() ? "resolve" : "open");
            thread.addProperty("template", scene.get("thread").getAsString());
            if (!commitmentId.isEmpty()) {
                // The obligation is what makes the thread refuse to be evicted while the promise is
                // outstanding, so a pair at the thread cap cannot lose a debt to make room.
                thread.addProperty("obligation", "commitment:" + commitmentId);
            }
            actions.add("conversations_thread", thread);
        }
        if (!commitmentId.isEmpty()) {
            JsonObject promise = new JsonObject();
            promise.addProperty("op", "make");
            promise.addProperty("id", commitmentId);
            actions.add("conversations_commitment", promise);
        }
        if (reply.has("advance") && scene.has("episode_kind")) {
            JsonObject advance = new JsonObject();
            advance.addProperty("op", "advance");
            advance.addProperty("kind", scene.get("episode_kind").getAsString());
            advance.addProperty("state", reply.get("advance").getAsString());
            actions.add("conversations_episode", advance);
        }
        // The page the beat says it opens and the page the result actually opens have to be the same
        // one, or the contract describes a route nothing plays.
        actions.addProperty("next", hasFollowOn ? followOnQuestion : followupQuestionId());
        actions.add("conversations_say", sayAction(reactionSay, reactionSlots));

        JsonObject result = new JsonObject();
        result.addProperty("baseChance", 1);
        result.add("actions", actions);
        JsonArray results = new JsonArray();
        results.add(result);
        JsonObject answer = new JsonObject();
        answer.addProperty("name", name);
        answer.add("results", results);

        compileIntent(reply, name, questionId, sceneName);
        return answer;
    }

    private void compileIntent(JsonObject reply, String name, String questionId, String sceneName) {
        JsonObject label = ContentCompiler.object(reply, "label");
        List<String> phrases = new ArrayList<>();
        phrases.add(ContentCompiler.normalizePhrase(
                ContentCompiler.require(label, "en", where + " label")));
        ContentCompiler.strings(reply, "phrases")
                .forEach(phrase -> phrases.add(ContentCompiler.normalizePhrase(phrase)));

        List<String> gate = ContentCompiler.strings(reply, "requires_any");
        if (gate.isEmpty()) {
            throw new IllegalStateException(questionId + "/" + name + " declares no requires_any");
        }
        ContentCompiler.checkAnchorsAreNotReserved(gate, questionId + "/" + name);
        ContentCompiler.checkAnchorsAreNotNegated(gate, phrases,
                questionId + "/" + name);
        Map<String, Double> keywords = new LinkedHashMap<>();
        gate.forEach(word -> keywords.put(word, 1.8));
        for (String phrase : phrases) {
            for (String word : ContentCompiler.contentWords(phrase)) {
                keywords.putIfAbsent(word, 0.8);
            }
        }
        JsonObject intent = new JsonObject();
        intent.addProperty("question", questionId);
        intent.addProperty("answer", name);
        intent.addProperty("context", questionId);
        JsonObject keywordObject = new JsonObject();
        keywords.forEach(keywordObject::addProperty);
        intent.add("keywords", keywordObject);
        intent.add("requiresAny", ContentCompiler.array(gate));
        intent.add("phrases", ContentCompiler.array(phrases));
        intent.addProperty("category", "topics");
        String intentId = "scene." + topic + "." + sceneName + "." + name;
        out.addIntent(intentId, intent);
        out.addMatcherFixture(phrases.get(0), questionId, intentId);
        if (phrases.size() > 1) {
            out.addMatcherFixture(phrases.get(1), questionId, intentId);
        }
    }

    // --- the funnel ---------------------------------------------------------------------------

    /**
     * Compiles the always-available funnel: two chained decision pages under
     * {@code conversations.topic.<id>}, plus the legacy pool the opener falls back to when topics are
     * switched off entirely.
     *
     * <p>Two pages rather than one because a quick topic owes the player two decisions, and a single
     * page of buttons that all end the conversation is one decision wearing a second's coat.
     */
    private void compileFunnel() {
        JsonObject funnel = ContentCompiler.object(source, "funnel");
        if (funnel == null) {
            return;
        }
        JsonObject legacy = ContentCompiler.object(funnel, "legacy_lines");
        if (legacy == null) {
            throw new IllegalStateException(where + " funnel needs legacy_lines: the line the villager"
                    + " speaks when topics are switched off entirely");
        }
        out.ownFunnelTopic(topic);
        JsonObject legacyHolder = new JsonObject();
        legacyHolder.add("lines", legacy);
        addPool(legacySay(), legacyHolder);

        // The legacy line is a speaking route like any other and has to declare what it means, or
        // the corpus has an uncontracted route the moment a player switches branching off. It says
        // its piece and hands the player back to the category page, so nothing may follow it.
        JsonObject legacyBeat = new JsonObject();
        legacyBeat.addProperty("topic", topic);
        legacyBeat.addProperty("say", legacySay());
        legacyBeat.addProperty("response_question",
                ContentCompiler.require(source, "return_question", where));
        legacyBeat.addProperty("npc_act", "report");
        legacyBeat.addProperty("subject", topic + ".talk");
        legacyBeat.addProperty("polarity", "positive");
        legacyBeat.addProperty("openness", "ends_conversation");
        legacyBeat.add("allowed_stances", ContentCompiler.array(List.of("exit")));
        out.addBeat(topic + ".legacy", legacyBeat);

        JsonObject open = ContentCompiler.object(funnel, "open");
        JsonObject more = ContentCompiler.object(funnel, "more");
        if (open == null || more == null) {
            throw new IllegalStateException(where + " funnel needs both an 'open' and a 'more' page");
        }
        List<String> inbound = compileFunnelPage(open, more, true, List.of());
        compileFunnelPage(more, null, false, inbound);
        out.addEntryRoute(entryQuestion + "/" + entryAnswer, funnelRoute());
    }

    private String legacySay() {
        return "conversations." + topic + ".legacy";
    }

    private String funnelQuestionId(boolean first) {
        return "conversations.topic." + topic + "." + (first ? "open" : "more") + ".respond";
    }

    private String funnelBeatId(boolean first) {
        return topic + "." + (first ? "open" : "more");
    }

    /**
     * One page of the funnel. The first page's replies hand on to the second; the second's end the
     * conversation and return to the hub, which is what makes the pair two decisions rather than one.
     */
    private List<String> compileFunnelPage(JsonObject page, JsonObject next, boolean first,
                                           List<String> inboundBeats) {
        String questionId = funnelQuestionId(first);
        String beatId = funnelBeatId(first);
        String sayKey = "conversations." + topic + "." + (first ? "open" : "more");
        String subject = page.has("subject") ? page.get("subject").getAsString() : topic + ".talk";
        List<String> obligations = ContentCompiler.strings(page, "obligations");
        if (obligations.isEmpty()) {
            obligations = List.of("acknowledge");
        }

        JsonArray replies = page.getAsJsonArray("replies");
        if (replies == null || replies.size() < 2) {
            throw new IllegalStateException(questionId + " needs at least two replies to be a decision");
        }
        Set<String> allowed = new LinkedHashSet<>();
        for (JsonElement element : replies) {
            allowed.add(ContentCompiler.require(element.getAsJsonObject(), "stance", where));
        }
        allowed.add("exit");

        List<String> responds = first ? List.of(beatId) : inboundBeats;

        JsonObject beat = new JsonObject();
        beat.addProperty("topic", topic);
        beat.addProperty("say", sayKey);
        beat.addProperty("response_question", questionId);
        beat.addProperty("npc_act", page.has("act") ? page.get("act").getAsString() : "report");
        beat.addProperty("subject", subject);
        beat.addProperty("polarity",
                page.has("polarity") ? page.get("polarity").getAsString() : "mixed");
        beat.addProperty("openness", "invites_followup");
        beat.add("facts", ContentCompiler.array(List.of("topic:" + topic)));
        beat.add("allowed_stances", ContentCompiler.array(allowed));
        beat.add("forbidden_stances", ContentCompiler.array(forbidden(allowed)));
        JsonObject beatContext = new JsonObject();
        beatContext.add("ages", ContentCompiler.array(ages));
        beat.add("context", beatContext);
        beat.add("frame", frame(
                page.has("predicate") ? page.get("predicate").getAsString() : "observation",
                page.has("temporal") ? page.get("temporal").getAsString() : "current",
                page.has("epistemic") ? page.get("epistemic").getAsString() : "observed",
                page.has("privacy") ? page.get("privacy").getAsString() : "ordinary",
                obligations, List.of(), List.of(),
                page.has("shape") ? page.get("shape").getAsString() : "observe"));
        // Only the first page has an opener of its own. Arriving at the second happens
        // through the first page's reaction, and that reaction is the line spoken there.
        if (first) {
            out.addBeat(beatId, beat);
            addPool(sayKey, page);
        }

        JsonObject prompt = ContentCompiler.object(page, "prompt");
        out.addLang("dialogue." + questionId,
                ContentCompiler.require(prompt, "en", where + " " + questionId + " prompt"),
                ContentCompiler.require(prompt, "pt", where + " " + questionId + " prompt"));

        List<String> reactionBeats = new ArrayList<>();
        JsonArray answers = new JsonArray();
        for (JsonElement element : replies) {
            answers.add(compileFunnelReply(element.getAsJsonObject(), responds, questionId,
                    sayKey, subject, obligations, next, first, reactionBeats));
        }
        answers.add(exitAnswer(questionId));
        JsonObject dialogue = new JsonObject();
        dialogue.add("answers", answers);
        out.addDialogue(questionId, dialogue);

        JsonObject exit = new JsonObject();
        exit.addProperty("stance", "exit");
        exit.add("responds_to", ContentCompiler.array(responds));
        exit.addProperty("tone", "plain");
        exit.addProperty("exit", true);
        out.addReply(questionId + "/leave", exit);
        out.addLang("dialogue." + questionId + ".leave",
                ContentCompiler.require(ContentCompiler.object(source, "leave_label"), "en", where),
                ContentCompiler.require(ContentCompiler.object(source, "leave_label"), "pt", where));
        return reactionBeats;
    }

    private JsonObject compileFunnelReply(JsonObject reply, List<String> respondsTo,
                                          String questionId, String sayKey, String subject,
                                          List<String> pageObligations, JsonObject next,
                                          boolean first, List<String> reactionBeats) {
        String name = ContentCompiler.require(reply, "name", where);
        String stance = ContentCompiler.require(reply, "stance", where);
        List<String> answers = ContentCompiler.strings(reply, "answers");
        String replyKey = questionId + "/" + name;
        for (String answer : answers) {
            if (!pageObligations.contains(answer)) {
                throw new IllegalStateException(replyKey + " answers '" + answer
                        + "', which its inbound beat does not make relevant " + pageObligations);
            }
        }

        JsonObject contract = new JsonObject();
        contract.addProperty("stance", stance);
        contract.add("responds_to", ContentCompiler.array(respondsTo));
        contract.addProperty("tone", reply.has("tone") ? reply.get("tone").getAsString() : "plain");
        // "conversation_ended" rather than "rebuffed": a villager declining to go on is not
        // the boundary-against-the-player that the signature tier is reserved for, and
        // filing it there would swamp that tier with ordinary brush-offs.
        contract.add("outcomes", ContentCompiler.array(List.of(
                "dismissal".equals(stance) ? "conversation_ended"
                        : reply.has("outcome") ? reply.get("outcome").getAsString() : "engaged")));
        if (!answers.isEmpty()) {
            contract.add("answers_obligation", ContentCompiler.array(answers));
        }
        out.addReply(replyKey, contract);

        JsonObject label = ContentCompiler.object(reply, "label");
        out.addLang("dialogue." + questionId + "." + name,
                ContentCompiler.require(label, "en", where + " " + replyKey + " label"),
                ContentCompiler.require(label, "pt", where + " " + replyKey + " label"));

        JsonObject reaction = ContentCompiler.object(reply, "reaction");
        if (reaction == null) {
            throw new IllegalStateException(replyKey + " has no reaction; every reply is answered");
        }
        String reactionId = ContentCompiler.require(reaction, "id", where);
        String reactionBeat = topic + "." + (first ? "open" : "more") + "." + reactionId;
        String reactionSay = sayKey + "." + reactionId;

        // A brush-off ends the conversation wherever it is used. Handing on from one would
        // open a page that keeps probing a subject the player has just closed, and the
        // stance rules refuse that for good reason.
        boolean closes = "dismissal".equals(stance);
        String responseQuestion = first && !closes ? funnelQuestionId(false)
                : ContentCompiler.require(source, "return_question", where);
        Set<String> onward = new LinkedHashSet<>();
        if (first && !closes) {
            for (JsonElement element : next.getAsJsonArray("replies")) {
                onward.add(ContentCompiler.require(element.getAsJsonObject(), "stance", where));
            }
        }
        onward.add("exit");

        JsonObject beat = new JsonObject();
        beat.addProperty("topic", topic);
        beat.addProperty("say", reactionSay);
        beat.addProperty("response_question", responseQuestion);
        beat.addProperty("npc_act", ContentCompiler.require(reaction, "act", where));
        beat.addProperty("subject", subject);
        beat.addProperty("polarity",
                reaction.has("polarity") ? reaction.get("polarity").getAsString() : "mixed");
        beat.addProperty("openness", first && !closes ? "invites_followup" : "ends_conversation");
        beat.add("facts", ContentCompiler.array(List.of("topic:" + topic)));
        beat.add("allowed_stances", ContentCompiler.array(onward));
        beat.add("forbidden_stances", ContentCompiler.array(forbidden(onward)));
        beat.addProperty("outcome",
                "dismissal".equals(stance) ? "conversation_ended"
                        : reply.has("outcome") ? reply.get("outcome").getAsString() : "engaged");
        JsonObject beatContext = new JsonObject();
        beatContext.add("ages", ContentCompiler.array(ages));
        beat.add("context", beatContext);
        beat.add("frame", frame(
                reaction.has("predicate") ? reaction.get("predicate").getAsString() : "observation",
                reaction.has("temporal") ? reaction.get("temporal").getAsString() : "current",
                reaction.has("epistemic") ? reaction.get("epistemic").getAsString() : "observed",
                reaction.has("privacy") ? reaction.get("privacy").getAsString() : "ordinary",
                first && !closes ? ContentCompiler.strings(next, "obligations")
                        : List.of("acknowledge"),
                List.of(), List.of(),
                reaction.has("shape") ? reaction.get("shape").getAsString() : "observe"));
        out.addBeat(reactionBeat, beat);
        addPool(reactionSay, reaction);
        if (first && !closes) {
            reactionBeats.add(reactionBeat);
        }

        JsonObject actions = new JsonObject();
        JsonObject session = new JsonObject();
        session.addProperty("op", first && !closes ? "turn" : "end");
        session.addProperty("beat", reactionBeat);
        actions.add("conversations_session", session);
        // A reply that earns or costs affection has to say so here. Dropped, the topic
        // reads to the graph lints as one where nothing is at stake.
        if (reply.has("affection")) {
            actions.add("conversations_affection_apply", reply.get("affection"));
        }
        // A typed claim is the only thing a player's own words may leave behind, and it is bound to
        // the exact button that was pressed. The source is the reply key rather than anything the
        // pack chose, so a claim can always be traced to a click (spec §11.3, §8.6).
        if (reply.has("claim")) {
            JsonObject source = ContentCompiler.object(reply, "claim");
            JsonObject claim = new JsonObject();
            claim.addProperty("op", "record");
            claim.addProperty("type", ContentCompiler.require(source, "type", where));
            claim.addProperty("value", ContentCompiler.require(source, "value", where));
            claim.addProperty("source", replyKey);
            actions.add("conversations_claim", claim);
        }
        if (reply.has("disposition")) {
            JsonObject disposition = new JsonObject();
            disposition.addProperty("topic", subject);
            disposition.add("deltas", reply.get("disposition"));
            actions.add("conversations_disposition_apply", disposition);
        }
        actions.addProperty("next", responseQuestion);
        actions.add("conversations_say", sayAction(reactionSay, List.of()));

        JsonObject result = new JsonObject();
        result.addProperty("baseChance", 1);
        result.add("actions", actions);
        JsonArray results = new JsonArray();
        results.add(result);
        JsonObject answer = new JsonObject();
        answer.addProperty("name", name);
        answer.add("results", results);

        compileIntent(reply, name, questionId, (first ? "open" : "more"));
        return answer;
    }

    /**
     * The route from the hub into the funnel. It sinks when branching or topics are switched off, so
     * that the legacy line behind it is what the player gets — the documented off-state.
     */
    private JsonObject funnelRoute() {
        JsonArray conditions = new JsonArray();
        for (String feature : List.of("branching", "topics")) {
            JsonObject disabled = new JsonObject();
            disabled.addProperty("chance", -2000);
            disabled.addProperty("conversations_disabled", feature);
            conditions.add(disabled);
        }
        // A preselected scene is the better answer, so the funnel stands down for it.
        // Two live results on one button is a lottery over the consequence, not a choice.
        for (String sceneId : sceneIds) {
            JsonObject sunk = new JsonObject();
            sunk.addProperty("chance", -5000);
            JsonObject query = new JsonObject();
            query.addProperty("is", sceneId);
            sunk.add("conversations_scene", query);
            conditions.add(sunk);
        }

        JsonObject session = new JsonObject();
        session.addProperty("op", "begin");
        session.addProperty("topic", topic);
        session.addProperty("budget",
                source.has("budget") ? source.get("budget").getAsString() : "quick");
        session.addProperty("branch", "funnel");
        session.addProperty("beat", funnelBeatId(true));

        JsonObject actions = new JsonObject();
        actions.add("conversations_session", session);
        actions.addProperty("next", funnelQuestionId(true));
        actions.add("conversations_say", sayAction("conversations." + topic + ".open", List.of()));

        JsonObject route = new JsonObject();
        route.addProperty("baseChance", 800);
        route.add("conditions", conditions);
        route.add("actions", actions);
        return route;
    }

    private void compileFollowup() {
        JsonObject followup = ContentCompiler.object(source, "followup");
        if (followup == null) {
            throw new IllegalStateException(where + " has no followup block");
        }
        String questionId = followupQuestionId();
        JsonObject prompt = ContentCompiler.object(followup, "prompt");
        out.addLang("dialogue." + questionId,
                ContentCompiler.require(prompt, "en", where + " followup prompt"),
                ContentCompiler.require(prompt, "pt", where + " followup prompt"));
        out.addLang("dialogue." + questionId + ".leave",
                ContentCompiler.require(ContentCompiler.object(followup, "leave"), "en", where),
                ContentCompiler.require(ContentCompiler.object(followup, "leave"), "pt", where));

        JsonArray answers = new JsonArray();
        answers.add(exitAnswer(questionId));
        JsonObject page = new JsonObject();
        page.add("answers", answers);
        out.addDialogue(questionId, page);

        JsonObject exit = new JsonObject();
        exit.addProperty("stance", "exit");
        exit.add("responds_to", ContentCompiler.array(List.of("subject:" + topic + ".*")));
        exit.addProperty("tone", "plain");
        exit.addProperty("exit", true);
        out.addReply(questionId + "/leave", exit);
    }

    // --- builders --------------------------------------------------------------------------------

    /**
     * The pool the villager speaks when the player leaves the topic.
     *
     * <p>A pack may point at an existing pool with {@code leave_say}, or write its own three lines as
     * {@code leave_lines} and get a pool of its own. The second is usually right: a borrowed pool is a
     * borrowed voice, and the line that ends a conversation is the one the player hears last.
     */
    private String leaveSayKey;
    /** Scene ids compiled from this pack, which the funnel route has to stand down for. */
    private final List<String> sceneIds = new ArrayList<>();

    private String leaveSay() {
        if (source.has("leave_say")) {
            return source.get("leave_say").getAsString();
        }
        if (leaveSayKey != null) {
            return leaveSayKey;
        }
        JsonObject lines = ContentCompiler.object(source, "leave_lines");
        if (lines == null) {
            throw new IllegalStateException(where + " needs either a \"leave_say\" pool to borrow"
                    + " or three \"leave_lines\" of its own");
        }
        String key = "conversations.scene." + topic + ".leaving";
        JsonObject holder = new JsonObject();
        holder.add("lines", lines);
        addPool(key, holder);

        JsonObject beat = new JsonObject();
        beat.addProperty("topic", topic);
        beat.addProperty("say", key);
        beat.addProperty("response_question",
                ContentCompiler.require(source, "return_question", where));
        beat.addProperty("npc_act", "accept");
        beat.addProperty("subject", topic + ".talk");
        beat.addProperty("polarity", "neutral");
        beat.addProperty("openness", "ends_conversation");
        beat.add("allowed_stances", ContentCompiler.array(List.of("exit")));
        out.addBeat(topic + ".scene.leaving", beat);
        leaveSayKey = key;
        return key;
    }

    private JsonObject entryRoute(String sceneId, String beatId, String questionId, String sayKey,
                                  List<String> slots) {
        JsonArray conditions = new JsonArray();
        JsonObject positive = new JsonObject();
        positive.addProperty("chance", 900);
        JsonObject query = new JsonObject();
        query.addProperty("is", sceneId);
        positive.add("conversations_scene", query);
        conditions.add(positive);

        JsonObject negative = new JsonObject();
        negative.addProperty("chance", -5000);
        JsonObject negated = new JsonObject();
        negated.addProperty("is", sceneId);
        negated.addProperty("not", true);
        negative.add("conversations_scene", negated);
        conditions.add(negative);

        for (String feature : List.of("dynamic", "branching", "topics")) {
            JsonObject disabled = new JsonObject();
            disabled.addProperty("chance", -2000);
            disabled.addProperty("conversations_disabled", feature);
            conditions.add(disabled);
        }

        JsonObject session = new JsonObject();
        session.addProperty("op", "begin");
        session.addProperty("topic", topic);
        session.addProperty("budget",
                source.has("budget") ? source.get("budget").getAsString() : "standard");
        session.addProperty("branch", "scene");
        session.addProperty("beat", beatId);
        JsonObject record = new JsonObject();
        record.addProperty("id", "mcaconversations.cooldown." + topic);
        record.addProperty("var", "player");
        record.addProperty("time", 36000);

        JsonObject actions = new JsonObject();
        actions.add("conversations_session", session);
        actions.add("conversations_record", record);
        actions.addProperty("next", questionId);
        actions.add("conversations_say", sayAction(sayKey, slots));

        JsonObject route = new JsonObject();
        route.addProperty("baseChance", 0);
        route.add("conditions", conditions);
        route.add("actions", actions);
        return route;
    }

    private JsonObject exitAnswer(String questionId) {
        JsonObject session = new JsonObject();
        session.addProperty("op", "end");
        JsonObject actions = new JsonObject();
        actions.add("conversations_session", session);
        actions.addProperty("next", ContentCompiler.require(source, "return_question", where));
        actions.add("conversations_say", sayAction(leaveSay(), List.of()));

        JsonObject result = new JsonObject();
        result.addProperty("baseChance", 1);
        result.add("actions", actions);
        JsonArray results = new JsonArray();
        results.add(result);
        JsonObject answer = new JsonObject();
        answer.addProperty("name", "leave");
        answer.add("results", results);
        return answer;
    }

    private JsonObject frame(String predicate, String temporal, String epistemic, String privacy,
                             List<String> obligations, List<String> slots, List<String> episodeStates,
                             String shape) {
        JsonObject frame = new JsonObject();
        frame.addProperty("predicate", predicate);
        frame.addProperty("temporal", temporal);
        frame.addProperty("epistemic", epistemic);
        frame.addProperty("privacy", privacy);
        frame.add("obligations", ContentCompiler.array(obligations));
        if (!slots.isEmpty()) {
            JsonObject referents = new JsonObject();
            slots.forEach(slot -> referents.addProperty(slot, "slot:" + slot));
            frame.add("referents", referents);
            frame.add("slots", ContentCompiler.array(slots));
        }
        if (!episodeStates.isEmpty()) {
            frame.add("episode_states", ContentCompiler.array(episodeStates));
        }
        frame.addProperty("shape", shape);
        // A reported or rumoured line has to be able to say who said it, even if the answer is an
        // explicit anonymous token — otherwise a rumour can be spoken as an observation (spec §10.3.5).
        if (epistemic.equals("reported") || epistemic.equals("rumoured")) {
            frame.addProperty("source", "anonymous");
        }
        return frame;
    }

    private void addPool(String sayKey, JsonObject holder) {
        JsonObject lines = ContentCompiler.object(holder, "lines");
        if (lines == null) {
            throw new IllegalStateException(where + " '" + sayKey + "' has no lines");
        }
        List<String> en = ContentCompiler.strings(lines, "en");
        List<String> pt = ContentCompiler.strings(lines, "pt");
        if (en.size() < 3 || pt.size() != en.size()) {
            throw new IllegalStateException(where + " '" + sayKey + "' has " + en.size() + " English and "
                    + pt.size() + " Portuguese variants; the floor is three, matched");
        }
        for (int i = 0; i < en.size(); i++) {
            out.addLang("dialogue." + sayKey + "/" + (i + 1), en.get(i), pt.get(i));
        }
    }

    private static List<String> forbidden(Set<String> allowed) {
        List<String> all = List.of("empathy", "curiosity", "candor", "encouragement", "practical_help",
                "humor", "respectful_disagreement", "self_disclosure", "restraint", "challenge",
                "flirtation", "dismissal", "boundary_push");
        List<String> out = new ArrayList<>();
        for (String stance : all) {
            if (!allowed.contains(stance)) {
                out.add(stance);
            }
        }
        return out;
    }

    /**
     * A reaction's own page: the third turn of an exchange that earned one.
     *
     * <p>Its replies are contracted against the reaction beat rather than the opener, which is what
     * puts the stance rules in the right place. A villager who has just pushed back on your advice
     * does not permit the same moves as one who has just explained what is at stake, and a page
     * shared between the two would offer buttons that answer neither.
     *
     * <p>Recursive by design: a follow-on reply is an ordinary reply with an ordinary reaction, so it
     * goes through the same compiler and can itself carry a further page. Nothing here caps the depth
     * — the authoring does, by choosing where to stop writing.
     */
    private void compileFollowOnPage(JsonObject scene, String sceneName, JsonObject reaction,
                                     JsonArray replies, String questionId, String inboundBeat,
                                     String inboundSay, String subject, List<String> inboundSlots,
                                     List<String> obligations) {
        JsonObject prompt = ContentCompiler.object(reaction, "prompt");
        if (prompt == null) {
            throw new IllegalStateException(where + " " + questionId
                    + " has replies but no prompt to put above them");
        }
        out.addLang("dialogue." + questionId,
                ContentCompiler.require(prompt, "en", where + " " + questionId + " prompt"),
                ContentCompiler.require(prompt, "pt", where + " " + questionId + " prompt"));

        JsonArray answers = new JsonArray();
        for (JsonElement element : replies) {
            answers.add(compileReply(element.getAsJsonObject(), scene, sceneName, inboundBeat,
                    questionId, inboundSay, subject, inboundSlots, obligations));
        }
        answers.add(exitAnswer(questionId));
        JsonObject page = new JsonObject();
        page.add("answers", answers);
        out.addDialogue(questionId, page);

        JsonObject exit = new JsonObject();
        exit.addProperty("stance", "exit");
        exit.add("responds_to", ContentCompiler.array(List.of(inboundBeat)));
        exit.addProperty("tone", "plain");
        exit.addProperty("exit", true);
        out.addReply(questionId + "/leave", exit);
        out.addLang("dialogue." + questionId + ".leave",
                ContentCompiler.require(ContentCompiler.object(source, "leave_label"), "en", where),
                ContentCompiler.require(ContentCompiler.object(source, "leave_label"), "pt", where));
    }

    private String followupQuestionId() {
        return "conversations.scene." + topic + ".followup";
    }

    /**
     * The {@code conversations_say} action for one line.
     *
     * <p>Every villager line goes through this action rather than MCA's native {@code say}, because
     * MCA resolves a {@code /N} pool on the client with a fresh random draw and no memory of the last
     * sentence. Ours names the variant on the server (see {@code LineVoice}), which is what stops a
     * pool of three reading like a pool of one. The slot list is omitted when empty so the emitted
     * JSON stays the smallest thing that says what it means.
     */
    private static JsonObject sayAction(String phrase, List<String> slots) {
        JsonObject say = new JsonObject();
        say.addProperty("phrase", phrase);
        if (!slots.isEmpty()) {
            say.add("slots", ContentCompiler.array(slots));
        }
        return say;
    }

}
