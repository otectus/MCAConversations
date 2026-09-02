package dev.otectus.mcaconversations.authoring;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Expands one profession's authoring source into runtime content (spec §12, Packet I).
 *
 * <h2>What the author writes, and what this writes</h2>
 *
 * <p>The author supplies the things only an author can: which situations this trade actually gets
 * into, what the villager says in each state of each one, what the player can say back, and how she
 * takes it. This supplies everything mechanical around that — the scene definitions and their
 * eligibility, the episode/thread/promise templates, the dialogue pages, the v1 and v2 contracts, the
 * entry routes, the chat intents, and the matcher fixtures that prove every button is typable.
 *
 * <p>Nothing here invents prose or shares it between trades. Two professions with identical structure
 * still produce entirely different text, because every string came out of that profession's own file.
 *
 * <h2>The shape it builds, and why</h2>
 *
 * <p>Each episode state becomes a scene gated on that state, so "it is still stuck" can only be said
 * while it is stuck and "it held" only after it did. Each state's replies become one page, each with
 * a reaction beat, and every page ends at a shared follow-up that offers a bridge and a door. Exits
 * reuse the profession's existing 1.4.0 farewell pool rather than minting a new one-line pool per
 * page — which is both less content and better content, because a trade should say goodbye the same
 * way whatever it was just talking about.
 */
final class ProfessionPackCompiler {

    private final ContentCompiler out;
    private final JsonObject source;
    private final String where;

    private final String professionId;
    private final String shortId;
    private final List<String> ages;
    private final List<String> subjectsAny;

    ProfessionPackCompiler(ContentCompiler out, JsonObject source, Path file) {
        this.out = out;
        this.source = source;
        this.where = file.getFileName().toString();
        this.professionId = ContentCompiler.require(source, "profession", where);
        this.shortId = ContentCompiler.require(source, "id", where);
        this.ages = ContentCompiler.strings(source, "ages").isEmpty()
                ? List.of("adult") : ContentCompiler.strings(source, "ages");
        this.subjectsAny = ContentCompiler.strings(source, "subjects_any");
    }

    void compile() {
        // Base trades write to the shared file; an optional mod's write to one named after it.
        String namespace = professionId.substring(0, professionId.indexOf(':'));
        out.beginOwner(namespace.equals("minecraft") || namespace.equals("mca") ? "" : namespace);
        compileSlotLang();
        JsonArray episodes = source.getAsJsonArray("episodes");
        if (episodes == null || episodes.isEmpty()) {
            throw new IllegalStateException(where + " declares no episodes");
        }
        // Three episode families is the floor §12.1 sets: a trade with one situation has a mood, not
        // a working life. Checked here so an under-filled pack fails at compile rather than at review.
        if (episodes.size() < 3) {
            throw new IllegalStateException(where + " declares " + episodes.size()
                    + " episode families; §12.1 asks for at least three");
        }
        for (JsonElement element : episodes) {
            compileEpisode(element.getAsJsonObject());
        }
        compileFollowup();
        compileEvergreenFallback();
    }

    // --- slots ------------------------------------------------------------------------------------

    /**
     * Writes the lang keys for every slot token this pack uses.
     *
     * <p>Each renders as a complete noun phrase carrying its own article, so the sentences around it
     * never have to agree with it. That is the rule that makes one template correct for six different
     * nouns in Portuguese as well as English (spec §18.5).
     */
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

    // --- episodes ---------------------------------------------------------------------------------

    /**
     * Speech acts that designate a pool as a signature beat, and are therefore refused here.
     *
     * <p>{@code SignatureBeat} derives its tiers from what a beat declares, and a disclosure or an
     * invitation obliges authored personality coverage in all twenty-one namespaces. Work talk is
     * reporting, explaining, complaining and conceding; genuine disclosure belongs to the deep
     * personal topics, where that investment buys something. A pack that reaches for one gets told at
     * compile time rather than at the overlay lint.
     */
    private static final Set<String> SIGNATURE_ACTS = Set.of("disclose", "disclose_problem", "invite");

    private void compileEpisode(JsonObject episode) {
        String kind = ContentCompiler.require(episode, "kind", where);
        String subject = ContentCompiler.require(episode, "subject", where);
        String shortKind = kind.startsWith("work.") ? kind.substring("work.".length()) : kind;

        JsonArray states = episode.getAsJsonArray("states");
        Set<String> stateNames = new LinkedHashSet<>();
        for (JsonElement element : states) {
            stateNames.add(ContentCompiler.require(element.getAsJsonObject(), "state", where));
        }
        // "active, changed and resolved/failed forms" (§12.1.3): a family that only ever reports a
        // problem and never resolves it is a treadmill, and the player learns their help means nothing.
        boolean hasTerminal = stateNames.contains("succeeded") || stateNames.contains("failed");
        if (!hasTerminal) {
            throw new IllegalStateException(where + " episode '" + kind
                    + "' has no succeeded or failed state; every situation must be able to end");
        }

        NarrativeTemplates.episode(out, episode, kind, subject, stateNames,
                java.util.List.of(professionId));
        NarrativeTemplates.thread(out, episode, threadId(shortKind), kind, "work", subject,
                resumeScenes(shortKind, stateNames));
        NarrativeTemplates.commitment(out, episode, threadId(shortKind), where);

        for (JsonElement element : states) {
            compileState(episode, element.getAsJsonObject(), kind, subject, shortKind);
        }
    }

    /**
     * Where a work thread is picked up again: the terminal states first, because coming back to a
     * finished thing is a different line from coming back to a live one.
     */
    private List<String> resumeScenes(String shortKind, Set<String> stateNames) {
        List<String> resume = new ArrayList<>();
        for (String state : List.of("succeeded", "failed", "active", "blocked")) {
            if (stateNames.contains(state)) {
                resume.add(sceneId(shortKind, state));
            }
        }
        return resume;
    }

    // --- one state: a scene, an opener, a page, and a reaction per reply --------------------------

    private void compileState(JsonObject episode, JsonObject state, String kind, String subject,
                              String shortKind) {
        String stateName = ContentCompiler.require(state, "state", where);
        String sceneId = sceneId(shortKind, stateName);
        String questionId = questionId(shortKind, stateName);
        String beatId = beatId(shortKind, stateName);
        String sayKey = sayKey(shortKind, stateName);
        List<String> slotsUsed = ContentCompiler.strings(state, "slots_used");
        List<String> obligations = ContentCompiler.strings(state, "obligations");
        if (obligations.isEmpty()) {
            obligations = List.of("acknowledge");
        }
        String shape = state.has("shape") ? state.get("shape").getAsString() : "observe";
        // Every state the episode cannot leave is spoken about in the past: succeeded and failed, and
        // also remembered and abandoned, which are equally over. Listing only the first two left the
        // long tail of an episode narrated in the present, which the temporal lint refuses.
        String temporal = state.has("temporal") ? state.get("temporal").getAsString()
                : tenseFor(List.of(stateName));

        // --- the scene ------------------------------------------------------------------------
        JsonObject scene = new JsonObject();
        scene.addProperty("purpose", "topic:work");
        scene.addProperty("shape", shape);
        JsonObject profile = new JsonObject();
        profile.add("profession", ContentCompiler.array(List.of(professionId)));
        profile.add("ages", ContentCompiler.array(ages));
        if (!subjectsAny.isEmpty()) {
            profile.add("subjects_any", ContentCompiler.array(subjectsAny));
        }
        scene.add("profile", profile);

        JsonObject context = new JsonObject();
        context.addProperty("episode_kind", kind);
        context.add("episode_state", ContentCompiler.array(List.of(stateName)));
        JsonObject requiredSlots = new JsonObject();
        slotsUsed.forEach(slot -> requiredSlots.addProperty(slot, "localized_token"));
        if (requiredSlots.size() > 0) {
            context.add("required_slots", requiredSlots);
        }
        if (episode.has("integrations")) {
            context.add("integrations", episode.get("integrations"));
        }
        scene.add("context", context);

        JsonObject selection = new JsonObject();
        selection.addProperty("base_priority",
                state.has("base_priority") ? state.get("base_priority").getAsInt() : basePriority(stateName));
        if (state.has("identity_values")) {
            out.checkIdentityTokens(ContentCompiler.strings(state, "identity_values"),
                    where + " " + sceneId);
            selection.add("identity_values", state.get("identity_values"));
        }
        if (state.has("identity_styles")) {
            out.checkIdentityTokens(ContentCompiler.strings(state, "identity_styles"),
                    where + " " + sceneId);
            selection.add("identity_styles", state.get("identity_styles"));
        }
        selection.addProperty("cooldown_days", 1);
        selection.addProperty("max_mentions_per_7_days", terminal(stateName) ? 1 : 2);
        scene.add("selection", selection);

        JsonObject route = new JsonObject();
        route.addProperty("question", questionId);
        route.addProperty("opening_beat", beatId);
        scene.add("route", route);

        JsonObject episodeBlock = new JsonObject();
        episodeBlock.addProperty("thread", threadId(shortKind));
        scene.add("episode", episodeBlock);
        scene.addProperty("fallback", evergreenSceneId());
        out.addScene(sceneId, scene);

        // --- the opening beat -----------------------------------------------------------------
        JsonArray replies = state.getAsJsonArray("replies");
        if (replies == null || replies.isEmpty()) {
            throw new IllegalStateException(where + " state '" + sceneId + "' has no replies");
        }
        Set<String> allowedStances = new LinkedHashSet<>();
        for (JsonElement element : replies) {
            allowedStances.add(ContentCompiler.require(element.getAsJsonObject(), "stance", where));
        }
        allowedStances.add("exit");

        JsonObject beat = new JsonObject();
        beat.addProperty("topic", "work");
        beat.addProperty("say", sayKey);
        beat.addProperty("response_question", questionId);
        beat.addProperty("npc_act", checkedAct(
                state.has("act") ? state.get("act").getAsString() : "report", sceneId));
        beat.addProperty("subject", subject);
        beat.addProperty("polarity", state.has("polarity") ? state.get("polarity").getAsString()
                : polarityFor(stateName));
        beat.addProperty("openness", "invites_followup");
        beat.add("facts", ContentCompiler.array(List.of("work:" + shortId)));
        beat.add("allowed_stances", ContentCompiler.array(allowedStances));
        beat.add("forbidden_stances", ContentCompiler.array(forbidden(allowedStances)));
        JsonObject beatContext = new JsonObject();
        beatContext.addProperty("profession", professionId);
        beatContext.add("ages", ContentCompiler.array(ages));
        beat.add("context", beatContext);
        beat.add("frame", frame(state.has("predicate") ? state.get("predicate").getAsString()
                        : predicateFor(stateName),
                temporal, obligations, slotsUsed, List.of(stateName), shape));
        out.addBeat(beatId, beat);
        addPool(sayKey, state, "lines");

        // --- the page -----------------------------------------------------------------------
        JsonObject prompt = ContentCompiler.object(state, "prompt");
        out.addLang("dialogue." + questionId,
                ContentCompiler.require(prompt, "en", where + " " + sceneId + " prompt"),
                ContentCompiler.require(prompt, "pt", where + " " + sceneId + " prompt"));

        JsonArray answers = new JsonArray();
        for (JsonElement element : replies) {
            answers.add(compileReply(element.getAsJsonObject(), state, stateName, shortKind, kind,
                    beatId, questionId, subject, slotsUsed, obligations));
        }
        answers.add(exitAnswer(questionId));
        JsonObject page = new JsonObject();
        page.add("answers", answers);
        out.addDialogue(questionId, page);

        addExitReply(questionId, beatId);

        // --- the entry route ------------------------------------------------------------------
        out.addEntryRoute("conversations.cat.profession/work",
                entryRoute(sceneId, beatId, questionId, sayKey, slotsUsed));
    }

    /** One reply: a button, its contract, its reaction beat and its chat intent. */
    private JsonObject compileReply(JsonObject reply, JsonObject state, String stateName,
                                    String shortKind, String kind, String inboundBeat,
                                    String questionId, String subject, List<String> stateSlots,
                                    List<String> stateObligations) {
        String name = ContentCompiler.require(reply, "name", where);
        String stance = ContentCompiler.require(reply, "stance", where);
        List<String> answers = ContentCompiler.strings(reply, "answers");
        String replyKey = questionId + "/" + name;

        // Every non-exit button must fulfil an obligation the line above it actually made relevant, or
        // perform a declared topic move. This is the §10.3 invariant, checked at compile so a pack can
        // never ship a page of comments after a direct question.
        String move = reply.has("move") ? reply.get("move").getAsString() : "";
        if (answers.isEmpty() && move.isEmpty()) {
            throw new IllegalStateException(replyKey + " neither answers an obligation nor moves topic");
        }
        for (String answer : answers) {
            if (!stateObligations.contains(answer)) {
                throw new IllegalStateException(replyKey + " answers '" + answer
                        + "', which its inbound beat does not make relevant " + stateObligations);
            }
        }

        JsonObject contract = new JsonObject();
        contract.addProperty("stance", stance);
        contract.add("responds_to", ContentCompiler.array(List.of(inboundBeat)));
        contract.add("requires_facts", ContentCompiler.array(List.of("work:" + shortId)));
        contract.addProperty("tone", reply.has("tone") ? reply.get("tone").getAsString() : "plain");
        contract.add("outcomes", ContentCompiler.array(List.of(
                reply.has("outcome") ? reply.get("outcome").getAsString() : "engaged")));
        if (!answers.isEmpty()) {
            contract.add("answers_obligation", ContentCompiler.array(answers));
        }
        if (!move.isEmpty()) {
            contract.addProperty("move", move);
        }
        if (!stateSlots.isEmpty()) {
            contract.add("uses_referents", ContentCompiler.array(stateSlots));
        }
        boolean makesCommitment = reply.has("commitment") && reply.get("commitment").getAsBoolean();
        if (makesCommitment) {
            contract.addProperty("commitment", commitmentIdFor(shortKind));
        }
        out.addReply(replyKey, contract);

        JsonObject label = ContentCompiler.object(reply, "label");
        out.addLang("dialogue." + questionId + "." + name,
                ContentCompiler.require(label, "en", where + " " + replyKey + " label"),
                ContentCompiler.require(label, "pt", where + " " + replyKey + " label"));

        // --- the reaction --------------------------------------------------------------------
        JsonObject reaction = ContentCompiler.object(reply, "reaction");
        if (reaction == null) {
            throw new IllegalStateException(replyKey + " has no reaction; every reply is answered");
        }
        String reactionId = ContentCompiler.require(reaction, "id", where);
        String reactionBeat = beatId(shortKind, stateName) + "." + reactionId;
        String reactionSay = sayKey(shortKind, stateName) + "." + reactionId;
        List<String> reactionSlots = ContentCompiler.strings(reaction, "slots_used");

        // A reaction may open a page of its own rather than handing straight back to the follow-up.
        // That is what lets an exchange go a turn deeper where the subject earns it: she explains what
        // is at stake, and the decision is still in front of you (spec §11).
        JsonArray followOnReplies = reaction.getAsJsonArray("replies");
        boolean opensPage = followOnReplies != null && !followOnReplies.isEmpty();
        String reactionQuestion = opensPage
                ? questionId(shortKind, stateName).replace(".respond", "." + reactionId + ".respond")
                : followupQuestionId();
        List<String> reactionObligations = ContentCompiler.strings(reaction, "obligations");
        if (reactionObligations.isEmpty()) {
            reactionObligations = List.of("acknowledge");
        }
        Set<String> reactionStances = new LinkedHashSet<>();
        if (opensPage) {
            for (JsonElement element : followOnReplies) {
                reactionStances.add(ContentCompiler.require(element.getAsJsonObject(), "stance", where));
            }
        } else {
            reactionStances.addAll(List.of("curiosity", "candor"));
        }
        reactionStances.add("exit");

        JsonObject beat = new JsonObject();
        beat.addProperty("topic", "work");
        beat.addProperty("say", reactionSay);
        beat.addProperty("response_question", reactionQuestion);
        beat.addProperty("npc_act",
                checkedAct(ContentCompiler.require(reaction, "act", where), reactionBeat));
        beat.addProperty("subject", subject);
        beat.addProperty("polarity",
                reaction.has("polarity") ? reaction.get("polarity").getAsString() : "mixed");
        beat.addProperty("openness", opensPage ? "invites_followup" : "permits_followup");
        beat.add("facts", ContentCompiler.array(List.of("work:" + shortId)));
        beat.add("allowed_stances", ContentCompiler.array(reactionStances));
        beat.add("forbidden_stances", ContentCompiler.array(forbidden(reactionStances)));
        beat.addProperty("outcome",
                reply.has("outcome") ? reply.get("outcome").getAsString() : "engaged");
        JsonObject beatContext = new JsonObject();
        beatContext.addProperty("profession", professionId);
        beatContext.add("ages", ContentCompiler.array(ages));
        beat.add("context", beatContext);
        // A reaction to a finished situation is past tense unless the author says otherwise. Left at
        // "current" it would claim the present about something that has already ended, which is the
        // contradiction the temporal lint refuses (spec §10.3.4).
        List<String> reactionEpisodeStates = reactionStates(reaction, stateName);
        String reactionTense = reaction.has("temporal") ? reaction.get("temporal").getAsString()
                : tenseFor(reactionEpisodeStates);
        beat.add("frame", frame(
                predicateFor(reaction, reactionTense), reactionTense,
                reactionObligations, reactionSlots, reactionEpisodeStates,
                reaction.has("shape") ? reaction.get("shape").getAsString() : "observe"));
        out.addBeat(reactionBeat, beat);
        addPool(reactionSay, reaction, "lines");

        if (opensPage) {
            compileFollowOnPage(reaction, followOnReplies, reactionQuestion, reactionBeat, reactionSay,
                    subject, shortKind, stateName, reactionId, kind, reactionSlots, reactionObligations);
        }

        // --- the result that plays it ---------------------------------------------------------
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
        if (reply.has("advance")) {
            JsonObject advance = new JsonObject();
            advance.addProperty("op", "advance");
            advance.addProperty("kind", kind);
            advance.addProperty("state", reply.get("advance").getAsString());
            actions.add("conversations_episode", advance);
        }
        JsonObject thread = new JsonObject();
        thread.addProperty("op", terminal(stateName) ? "resolve" : "open");
        thread.addProperty("template", threadId(shortKind));
        if (makesCommitment) {
            thread.addProperty("obligation", "commitment:" + commitmentIdFor(shortKind));
        }
        actions.add("conversations_thread", thread);
        if (makesCommitment) {
            JsonObject promise = new JsonObject();
            promise.addProperty("op", "make");
            promise.addProperty("id", commitmentIdFor(shortKind));
            actions.add("conversations_commitment", promise);
        }
        actions.addProperty("next", reactionQuestion);
        actions.add("conversations_say", say(reactionSay, reactionSlots));

        JsonObject result = new JsonObject();
        result.addProperty("baseChance", 1);
        result.add("actions", actions);
        JsonArray results = new JsonArray();
        results.add(result);

        JsonObject answer = new JsonObject();
        answer.addProperty("name", name);
        answer.add("results", results);

        compileIntent(reply, name, questionId, shortKind, stateName);
        return answer;
    }

    /**
     * The chat intent that makes the button typable, derived from its own authored phrases.
     *
     * <p>Keywords come from the content words of the phrases rather than being written twice, which
     * keeps them in step: an author who rewrites a button cannot leave a keyword set behind pointing
     * at the old wording. {@code requiresAny} takes the rarest words, which is what stops a phrase
     * from matching every page in the corpus.
     */
    private void compileIntent(JsonObject reply, String name, String questionId, String shortKind,
                               String stateName) {
        List<String> phrases = ContentCompiler.strings(reply, "phrases");
        JsonObject label = ContentCompiler.object(reply, "label");
        List<String> allPhrases = new ArrayList<>();
        allPhrases.add(ContentCompiler.normalizePhrase(
                ContentCompiler.require(label, "en", where + " label")));
        phrases.forEach(phrase -> allPhrases.add(ContentCompiler.normalizePhrase(phrase)));

        List<String> gate = ContentCompiler.strings(reply, "requires_any");
        if (gate.isEmpty()) {
            throw new IllegalStateException(questionId + "/" + name
                    + " declares no requires_any; without one its phrases would match half the corpus");
        }
        ContentCompiler.checkAnchorsAreNotReserved(gate, where + " "
                + questionId + "/" + name);
        ContentCompiler.checkAnchorsAreNotNegated(gate, allPhrases, where + " " + questionId + "/" + name);
        Map<String, Double> keywords = new LinkedHashMap<>();
        gate.forEach(word -> keywords.put(word, 1.8));
        for (String phrase : allPhrases) {
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
        intent.add("phrases", ContentCompiler.array(allPhrases));
        intent.addProperty("category", "topics");
        String intentId = "scene.work." + shortId + "." + shortKind + "." + stateName + "." + name;
        out.addIntent(intentId, intent);

        // Two fixtures per reply: the label itself and one paraphrase, so the guarantee that a
        // pressable button is a speakable one is asserted rather than asserted-about.
        out.addMatcherFixture(allPhrases.get(0), questionId, intentId);
        if (allPhrases.size() > 1) {
            out.addMatcherFixture(allPhrases.get(1), questionId, intentId);
        }
    }

    /**
     * A reaction's own page: the second turn of an exchange that earned one.
     *
     * <p>Its replies are contracted against the reaction beat rather than the opener, which is what
     * makes the stance rules bite in the right place. A villager who has just pushed back on your
     * advice does not allow the same moves as one who has just explained what is at stake, and a page
     * shared between the two would offer buttons that answer neither.
     */
    private void compileFollowOnPage(JsonObject reaction, JsonArray replies, String questionId,
                                     String inboundBeat, String inboundSay, String subject,
                                     String shortKind, String stateName, String reactionId,
                                     String kind, List<String> inboundSlots,
                                     List<String> obligations) {
        JsonObject prompt = ContentCompiler.object(reaction, "prompt");
        out.addLang("dialogue." + questionId,
                ContentCompiler.require(prompt, "en", where + " " + questionId + " prompt"),
                ContentCompiler.require(prompt, "pt", where + " " + questionId + " prompt"));

        JsonArray answers = new JsonArray();
        for (JsonElement element : replies) {
            JsonObject reply = element.getAsJsonObject();
            String name = ContentCompiler.require(reply, "name", where);
            String replyKey = questionId + "/" + name;
            List<String> answered = ContentCompiler.strings(reply, "answers");
            String move = reply.has("move") ? reply.get("move").getAsString() : "";
            if (answered.isEmpty() && move.isEmpty()) {
                throw new IllegalStateException(replyKey + " neither answers an obligation nor moves topic");
            }
            for (String answer : answered) {
                if (!obligations.contains(answer)) {
                    throw new IllegalStateException(replyKey + " answers '" + answer
                            + "', which its inbound beat does not make relevant " + obligations);
                }
            }

            JsonObject contract = new JsonObject();
            contract.addProperty("stance", ContentCompiler.require(reply, "stance", where));
            contract.add("responds_to", ContentCompiler.array(List.of(inboundBeat)));
            contract.add("requires_facts", ContentCompiler.array(List.of("work:" + shortId)));
            contract.addProperty("tone", reply.has("tone") ? reply.get("tone").getAsString() : "plain");
            contract.add("outcomes", ContentCompiler.array(List.of(
                    reply.has("outcome") ? reply.get("outcome").getAsString() : "engaged")));
            if (!answered.isEmpty()) {
                contract.add("answers_obligation", ContentCompiler.array(answered));
            }
            if (!move.isEmpty()) {
                contract.addProperty("move", move);
            }
            if (!inboundSlots.isEmpty()) {
                contract.add("uses_referents", ContentCompiler.array(inboundSlots));
            }
            boolean makesCommitment = reply.has("commitment") && reply.get("commitment").getAsBoolean();
            if (makesCommitment) {
                contract.addProperty("commitment", commitmentIdFor(shortKind));
            }
            out.addReply(replyKey, contract);

            JsonObject label = ContentCompiler.object(reply, "label");
            out.addLang("dialogue." + questionId + "." + name,
                    ContentCompiler.require(label, "en", where + " " + replyKey + " label"),
                    ContentCompiler.require(label, "pt", where + " " + replyKey + " label"));

            JsonObject inner = ContentCompiler.object(reply, "reaction");
            if (inner == null) {
                throw new IllegalStateException(replyKey + " has no reaction");
            }
            String innerId = ContentCompiler.require(inner, "id", where);
            String innerBeat = inboundBeat + "." + innerId;
            String innerSay = inboundSay + "." + innerId;
            List<String> innerSlots = ContentCompiler.strings(inner, "slots_used");

            JsonObject beat = new JsonObject();
            beat.addProperty("topic", "work");
            beat.addProperty("say", innerSay);
            beat.addProperty("response_question", followupQuestionId());
            beat.addProperty("npc_act",
                    checkedAct(ContentCompiler.require(inner, "act", where), innerBeat));
            beat.addProperty("subject", subject);
            beat.addProperty("polarity",
                    inner.has("polarity") ? inner.get("polarity").getAsString() : "mixed");
            beat.addProperty("openness", "permits_followup");
            beat.add("facts", ContentCompiler.array(List.of("work:" + shortId)));
            beat.add("allowed_stances", ContentCompiler.array(List.of("curiosity", "candor", "exit")));
            beat.add("forbidden_stances", ContentCompiler.array(
                    forbidden(Set.of("curiosity", "candor", "exit"))));
            beat.addProperty("outcome",
                    reply.has("outcome") ? reply.get("outcome").getAsString() : "engaged");
            JsonObject beatContext = new JsonObject();
            beatContext.addProperty("profession", professionId);
            beatContext.add("ages", ContentCompiler.array(ages));
            beat.add("context", beatContext);
            List<String> innerStates = reactionStates(inner, stateName);
            String innerTense = inner.has("temporal") ? inner.get("temporal").getAsString()
                    : tenseFor(innerStates);
            beat.add("frame", frame(predicateFor(inner, innerTense), innerTense,
                    List.of("acknowledge"), innerSlots, innerStates,
                    inner.has("shape") ? inner.get("shape").getAsString() : "observe"));
            out.addBeat(innerBeat, beat);
            addPool(innerSay, inner, "lines");

            JsonObject actions = new JsonObject();
            JsonObject session = new JsonObject();
            session.addProperty("op", "turn");
            session.addProperty("beat", innerBeat);
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
            if (reply.has("advance")) {
                JsonObject advance = new JsonObject();
                advance.addProperty("op", "advance");
                advance.addProperty("kind", kind);
                advance.addProperty("state", reply.get("advance").getAsString());
                actions.add("conversations_episode", advance);
            }
            JsonObject thread = new JsonObject();
            thread.addProperty("op", terminal(stateName) ? "resolve" : "open");
            thread.addProperty("template", threadId(shortKind));
            if (makesCommitment) {
                thread.addProperty("obligation", "commitment:" + commitmentIdFor(shortKind));
            }
            actions.add("conversations_thread", thread);
            if (makesCommitment) {
                JsonObject promise = new JsonObject();
                promise.addProperty("op", "make");
                promise.addProperty("id", commitmentIdFor(shortKind));
                actions.add("conversations_commitment", promise);
            }
            actions.addProperty("next", followupQuestionId());
            actions.add("conversations_say", say(innerSay, innerSlots));

            JsonObject result = new JsonObject();
            result.addProperty("baseChance", 1);
            result.add("actions", actions);
            JsonArray results = new JsonArray();
            results.add(result);
            JsonObject answer = new JsonObject();
            answer.addProperty("name", name);
            answer.add("results", results);
            answers.add(answer);

            compileIntent(reply, name, questionId, shortKind, stateName + "." + reactionId);
        }
        answers.add(exitAnswer(questionId));
        JsonObject page = new JsonObject();
        page.add("answers", answers);
        out.addDialogue(questionId, page);
        addExitReply(questionId, inboundBeat);
    }

    // --- the shared follow-up and evergreen fallback -----------------------------------------------

    /**
     * One follow-up page per profession, shared by every reaction in the pack.
     *
     * <p>Shared deliberately. It offers a bridge into the trade's existing 1.4.0 work conversation and
     * a door out, and both of those mean the same thing whatever situation was just discussed. A page
     * per state would be twenty near-identical pages saying "anything else?".
     */
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

        JsonObject bridge = ContentCompiler.object(followup, "bridge");
        JsonObject label = ContentCompiler.object(bridge, "label");
        out.addLang("dialogue." + questionId + ".ask_more",
                ContentCompiler.require(label, "en", where + " followup bridge"),
                ContentCompiler.require(label, "pt", where + " followup bridge"));

        JsonObject actions = new JsonObject();
        JsonObject session = new JsonObject();
        session.addProperty("op", "turn");
        session.addProperty("beat", "work." + shortId + ".hard");
        actions.add("conversations_session", session);
        JsonObject disposition = new JsonObject();
        disposition.addProperty("topic", "work." + shortId + ".hard");
        JsonObject deltas = new JsonObject();
        deltas.addProperty("familiarity", 2);
        disposition.add("deltas", deltas);
        actions.add("conversations_disposition_apply", disposition);
        actions.addProperty("next", "conversations.topic.work." + shortId + ".followup");
        actions.add("conversations_say",
                say("conversations.work.prof." + shortId + ".hard", List.of()));

        JsonObject result = new JsonObject();
        result.addProperty("baseChance", 1);
        result.add("actions", actions);
        JsonArray results = new JsonArray();
        results.add(result);
        JsonObject answer = new JsonObject();
        answer.addProperty("name", "ask_more");
        answer.add("results", results);

        JsonArray answers = new JsonArray();
        answers.add(answer);
        answers.add(exitAnswer(questionId));
        JsonObject page = new JsonObject();
        page.add("answers", answers);
        out.addDialogue(questionId, page);

        // The bridge is a button, so it is typable like every other button.
        JsonObject bridgeIntent = new JsonObject();
        bridgeIntent.addProperty("question", questionId);
        bridgeIntent.addProperty("answer", "ask_more");
        bridgeIntent.addProperty("context", questionId);
        JsonObject bridgeKeywords = new JsonObject();
        String bridgeEnglish = ContentCompiler.normalizePhrase(
                ContentCompiler.require(label, "en", where + " followup bridge"));
        List<String> bridgeGate = ContentCompiler.strings(bridge, "requires_any");
        if (bridgeGate.isEmpty()) {
            throw new IllegalStateException(where + " followup bridge declares no requires_any");
        }
        bridgeGate.forEach(word -> bridgeKeywords.addProperty(word, 1.8));
        ContentCompiler.contentWords(bridgeEnglish)
                .forEach(word -> { if (!bridgeKeywords.has(word)) { bridgeKeywords.addProperty(word, 0.8); } });
        bridgeIntent.add("keywords", bridgeKeywords);
        bridgeIntent.add("requiresAny", ContentCompiler.array(bridgeGate));
        List<String> bridgePhrases = new ArrayList<>();
        bridgePhrases.add(bridgeEnglish);
        ContentCompiler.strings(bridge, "phrases")
                .forEach(phrase -> bridgePhrases.add(ContentCompiler.normalizePhrase(phrase)));
        bridgeIntent.add("phrases", ContentCompiler.array(bridgePhrases));
        bridgeIntent.addProperty("category", "topics");
        String bridgeIntentId = "scene.work." + shortId + ".followup.ask_more";
        out.addIntent(bridgeIntentId, bridgeIntent);
        out.addMatcherFixture(bridgeEnglish, questionId, bridgeIntentId);
        if (bridgePhrases.size() > 1) {
            out.addMatcherFixture(bridgePhrases.get(1), questionId, bridgeIntentId);
        }

        JsonObject contract = new JsonObject();
        contract.addProperty("stance", "curiosity");
        contract.add("responds_to", ContentCompiler.array(List.of("subject:work." + shortId + ".*")));
        contract.add("requires_facts", ContentCompiler.array(List.of("work:" + shortId)));
        contract.addProperty("tone", "plain");
        contract.add("outcomes", ContentCompiler.array(List.of("engaged")));
        contract.addProperty("move", "bridge");
        out.addReply(questionId + "/ask_more", contract);

        JsonObject exit = new JsonObject();
        exit.addProperty("stance", "exit");
        exit.add("responds_to", ContentCompiler.array(List.of("subject:work." + shortId + ".*")));
        exit.addProperty("tone", "plain");
        exit.addProperty("exit", true);
        out.addReply(questionId + "/leave", exit);
        out.addLang("dialogue." + questionId + ".leave",
                ContentCompiler.require(ContentCompiler.object(followup, "leave"), "en", where),
                ContentCompiler.require(ContentCompiler.object(followup, "leave"), "pt", where));
    }

    /**
     * The truthful floor every scene degrades to.
     *
     * <p>It claims nothing about any object, which is exactly why it is right whatever the world turns
     * out to be — a missing slot or a dead referent lands here rather than on a sentence that names
     * something that is not there (spec §10.3.9).
     */
    private void compileEvergreenFallback() {
        JsonObject scene = new JsonObject();
        scene.addProperty("purpose", "topic:work");
        scene.addProperty("shape", "observe");
        JsonObject profile = new JsonObject();
        profile.add("profession", ContentCompiler.array(List.of(professionId)));
        profile.add("ages", ContentCompiler.array(ages));
        scene.add("profile", profile);
        JsonObject selection = new JsonObject();
        selection.addProperty("base_priority", 6);
        selection.addProperty("cooldown_days", 0);
        scene.add("selection", selection);
        JsonObject route = new JsonObject();
        route.addProperty("question", "conversations.topic.work." + shortId + ".task.respond");
        route.addProperty("opening_beat", "work." + shortId + ".task");
        scene.add("route", route);
        out.addScene(evergreenSceneId(), scene);
    }

    // --- small builders -----------------------------------------------------------------------

    private JsonObject entryRoute(String sceneId, String beatId, String questionId, String sayKey,
                                  List<String> slots) {
        JsonArray conditions = new JsonArray();
        conditions.add(sceneCondition(sceneId, 900, false));
        conditions.add(sceneCondition(sceneId, -5000, true));
        for (String feature : List.of("dynamic", "episodes", "branching", "topics")) {
            JsonObject disabled = new JsonObject();
            disabled.addProperty("chance", -2000);
            disabled.addProperty("conversations_disabled", feature);
            conditions.add(disabled);
        }

        JsonObject session = new JsonObject();
        session.addProperty("op", "begin");
        session.addProperty("topic", "work");
        session.addProperty("budget", "standard");
        session.addProperty("branch", "scene");
        session.addProperty("beat", beatId);
        JsonObject record = new JsonObject();
        record.addProperty("id", "mcaconversations.cooldown.work");
        record.addProperty("var", "player");
        record.addProperty("time", 36000);

        JsonObject actions = new JsonObject();
        actions.add("conversations_session", session);
        actions.add("conversations_record", record);
        actions.addProperty("next", questionId);
        actions.add("conversations_say", say(sayKey, slots));

        JsonObject route = new JsonObject();
        route.addProperty("baseChance", 0);
        route.add("conditions", conditions);
        route.add("actions", actions);
        return route;
    }

    private static JsonObject sceneCondition(String sceneId, int chance, boolean negate) {
        JsonObject condition = new JsonObject();
        condition.addProperty("chance", chance);
        JsonObject query = new JsonObject();
        query.addProperty("is", sceneId);
        if (negate) {
            query.addProperty("not", true);
        }
        condition.add("conversations_scene", query);
        return condition;
    }

    private static JsonObject say(String phrase, List<String> slots) {
        JsonObject say = new JsonObject();
        say.addProperty("phrase", phrase);
        if (!slots.isEmpty()) {
            say.add("slots", ContentCompiler.array(slots));
        }
        return say;
    }

    /** The exit answer, routed to the profession's existing farewell pool. */
    private JsonObject exitAnswer(String questionId) {
        JsonObject session = new JsonObject();
        session.addProperty("op", "end");
        JsonObject actions = new JsonObject();
        actions.add("conversations_session", session);
        actions.addProperty("next", "conversations.cat.profession");
        actions.add("conversations_say",
                say("conversations.work.prof." + shortId + ".leave", List.of()));

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

    private void addExitReply(String questionId, String inboundBeat) {
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

    private JsonObject frame(String predicate, String temporal, List<String> obligations,
                             List<String> slots, List<String> episodeStates, String shape) {
        JsonObject frame = new JsonObject();
        frame.addProperty("predicate", predicate);
        frame.addProperty("temporal", temporal);
        frame.addProperty("epistemic", "observed");
        frame.addProperty("privacy", "ordinary");
        frame.add("obligations", ContentCompiler.array(obligations));
        if (!slots.isEmpty()) {
            JsonObject referents = new JsonObject();
            slots.forEach(slot -> referents.addProperty(slot, "slot:" + slot));
            frame.add("referents", referents);
            frame.add("slots", ContentCompiler.array(slots));
        }
        frame.add("episode_states", ContentCompiler.array(episodeStates));
        frame.addProperty("shape", shape);
        return frame;
    }

    /**
     * Which episode states a reaction may truthfully play in.
     *
     * <p>A reaction that advances the episode is true in both the old state and the new one — it is
     * the sentence in which the change happens — so both are declared. Anything else stays where it
     * was.
     */
    private static List<String> reactionStates(JsonObject reaction, String stateName) {
        List<String> declared = ContentCompiler.strings(reaction, "episode_states");
        return declared.isEmpty() ? List.of(stateName) : declared;
    }

    private void addPool(String sayKey, JsonObject holder, String field) {
        JsonObject lines = ContentCompiler.object(holder, field);
        if (lines == null) {
            throw new IllegalStateException(where + " '" + sayKey + "' has no " + field);
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
     * The tense a beat playing in these episode states may honestly use.
     *
     * <p>Past when every state it plays in has ended; habitual when it spans both a live and a
     * finished state, since only a tenseless sentence is true in both; present otherwise.
     */
    /**
     * The predicate a reaction takes when the author has not named one.
     *
     * <p>Most reactions report where the situation now stands, so "status_change" is the ordinary
     * default. A past-tense reaction is not doing that: it is narrating something that already
     * happened, and calling that a status change would have her announce an ending for a situation
     * that may still be running — the exact contradiction the temporal lint refuses. Past tense is
     * therefore a memory unless the author says otherwise.
     */
    private static String predicateFor(JsonObject node, String tense) {
        if (node.has("predicate")) {
            return node.get("predicate").getAsString();
        }
        return "past".equals(tense) ? "memory" : "status_change";
    }

    private static String tenseFor(List<String> episodeStates) {
        boolean anyLive = false;
        boolean anyPast = false;
        for (String state : episodeStates) {
            if (terminal(state) || state.equals("remembered") || state.equals("abandoned")) {
                anyPast = true;
            } else {
                anyLive = true;
            }
        }
        if (anyPast && anyLive) {
            return "habitual";
        }
        return anyPast ? "past" : "current";
    }

    private String checkedAct(String act, String beatId) {
        if (SIGNATURE_ACTS.contains(act)) {
            throw new IllegalStateException(where + " beat '" + beatId + "' uses speech act '" + act
                    + "', which designates a signature pool and obliges twenty-one personality"
                    + " overlays. Use report, explain, complain, qualify, accept, resist, celebrate"
                    + " or reminisce for work talk.");
        }
        return act;
    }

    private static boolean terminal(String state) {
        return state.equals("succeeded") || state.equals("failed");
    }

    private static int basePriority(String state) {
        return switch (state) {
            case "succeeded" -> 34;
            case "failed" -> 32;
            case "blocked" -> 28;
            case "active" -> 26;
            default -> 20;
        };
    }

    private static String polarityFor(String state) {
        return switch (state) {
            case "succeeded" -> "positive";
            case "failed", "blocked" -> "negative";
            default -> "mixed";
        };
    }

    private static String predicateFor(String state) {
        return switch (state) {
            case "succeeded", "failed" -> "status_change";
            case "remembered", "abandoned" -> "memory";
            case "blocked" -> "work_problem";
            default -> "plan";
        };
    }

    // --- id shapes ------------------------------------------------------------------------------

    private String sceneId(String shortKind, String state) {
        return "work." + shortId + "." + shortKind + "." + state;
    }

    private String questionId(String shortKind, String state) {
        return "conversations.scene.work." + shortId + "." + shortKind + "." + state + ".respond";
    }

    private String beatId(String shortKind, String state) {
        return "work." + shortId + "." + shortKind + "." + state;
    }

    private String sayKey(String shortKind, String state) {
        return "conversations.scene.work." + shortId + "." + shortKind + "." + state;
    }

    private String threadId(String shortKind) {
        return "work." + shortId + "." + shortKind;
    }

    private String commitmentIdFor(String shortKind) {
        for (JsonElement element : source.getAsJsonArray("episodes")) {
            JsonObject episode = element.getAsJsonObject();
            String kind = episode.get("kind").getAsString();
            String candidate = kind.startsWith("work.") ? kind.substring("work.".length()) : kind;
            if (candidate.equals(shortKind)) {
                JsonObject commitment = ContentCompiler.object(episode, "commitment");
                if (commitment == null) {
                    throw new IllegalStateException(where + " episode '" + kind
                            + "' has a reply that promises something, but the episode declares no"
                            + " commitment template — a promise with no resolver cannot ship");
                }
                return commitment.get("id").getAsString();
            }
        }
        throw new IllegalStateException(where + " has no episode '" + shortKind + "'");
    }

    private String followupQuestionId() {
        return "conversations.scene.work." + shortId + ".followup";
    }

    private String evergreenSceneId() {
        return "work." + shortId + ".current_task.evergreen";
    }
}
