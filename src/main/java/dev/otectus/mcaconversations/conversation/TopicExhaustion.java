package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.progress.Progress;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

/**
 * When a catalog topic counts as fully discussed between one player and one villager, and what that
 * hides ({@code topics.hideExhaustedTopics}).
 *
 * <p><b>The rule, in one sentence.</b> A topic is fully discussed the first time the villager has
 * answered at least one substantive reply from the player inside the topic and the player then left
 * the topic through the dialogue itself — the leave answer, or an answer that returns to the
 * category — rather than by closing the screen, walking away, disconnecting or leaving on the
 * opening page. An opening the villager deflected or kept guarded never counts: a stranger who was
 * brushed off has not heard the topic.
 *
 * <p>Why this and not "every scene played": a topic is a pool of scenes chosen by the director, and
 * nearly every scene is gated on context that comes and goes (time of day, a live episode, a
 * Townstead age), so no pair can ever exhaust the pool. And why not the {@code end} op alone: every
 * page carries a leave answer that ends the session, the opening page included, so the op fires for a
 * first-page bail-out too. One answered reply followed by an authored exit is exactly one complete
 * pass through the shipped tree shape (opening line, reply, reaction, leave).
 *
 * <p>The rules are pure statics so they can be exercised without a server; the two runtime entry
 * points ({@link #hides} and {@link #onReplySettled}) read the config and the progress ledger and
 * never throw into the mixin or the settle path that calls them. Recording ignores the config on
 * purpose: switching the option on later hides what was finished while it was off.
 */
public final class TopicExhaustion {

    /** MCA's dialogue phrase said once when a category page has no unfinished topic left. */
    public static final String NOTHING_NEW_PHRASE = "conversations.topics.nothing_new";

    private TopicExhaustion() {
    }

    // --- Pure rules -----------------------------------------------------------------------

    /** True when the menu should not offer this topic: the feature is on, it is not repeatable, and it is done. */
    public static boolean hidden(TopicEntry entry, boolean featureOn, boolean discussed) {
        return entry != null && featureOn && !entry.repeatable() && discussed;
    }

    /**
     * True when {@code question} is not one of the topic's own pages: the hub, the main menu, or the
     * page the topic is entered from and returns to. An unknown page is inside — a datapack tree may
     * name its pages anything, and only the catalog's own two questions are known exits.
     */
    public static boolean outsideTopic(TopicEntry entry, String question) {
        if (question == null || question.isBlank()) {
            return true;
        }
        String page = question.trim();
        if ("conversations".equals(page) || "main".equals(page)) {
            return true;
        }
        return entry != null && (page.equals(entry.returnQuestion()) || page.equals(entry.entryQuestion()));
    }

    /**
     * True when the reply said something rather than leaving: its contract is not an exit stance, or,
     * with no contract at all, it is not one of the two conventional exit answers.
     */
    public static boolean substantive(Optional<ReplyContract> contract, String answer) {
        if (contract != null && contract.isPresent()) {
            ReplyContract reply = contract.get();
            return !reply.exit() && reply.stance() != StanceFamily.EXIT;
        }
        String name = answer == null ? "" : answer.trim().toLowerCase(Locale.ROOT);
        return !name.isEmpty() && !"leave".equals(name) && !"back".equals(name);
    }

    /**
     * True when the villager's opening line actually opened the topic. A guarded opening, a deflection
     * or a refusal, or a line filed under a different domain (the "we talked about this already"
     * route opens {@code deep}, not the topic asked for) is not the topic being told. A topic whose
     * route named no beat counts: those are the pre-contract trees, and their replies are real.
     */
    public static boolean openingCounts(BeatContract opening, String topic) {
        if (opening == null) {
            return true;
        }
        if (opening.openness() == Openness.GUARDED || opening.npcAct() == NpcSpeechAct.DEFLECT
                || opening.npcAct().isRupture()) {
            return false;
        }
        String domain = opening.topic() == null ? "" : opening.topic().trim().toLowerCase(Locale.ROOT);
        return domain.isEmpty() || topic == null || domain.equals(topic.trim().toLowerCase(Locale.ROOT));
    }

    /**
     * The decision: an opening that counts, at least one substantive reply, and an authored way out —
     * the topic is already over (the end op ran) or the page now on offer is outside it.
     */
    public static boolean concluded(boolean topicStillOpen, int substantiveReplies, boolean openingCounts,
                                    String offeredQuestion, TopicEntry entry) {
        return openingCounts && substantiveReplies >= 1
                && (!topicStillOpen || outsideTopic(entry, offeredQuestion));
    }

    // --- Runtime --------------------------------------------------------------------------

    /** Whether the menu should hide this topic for this pair right now. Never throws. */
    public static boolean hides(TopicEntry entry, Entity villager, ServerPlayer player) {
        if (entry == null || entry.repeatable() || villager == null || player == null) {
            return false;
        }
        try {
            return hidden(entry, McaConversationsConfig.hideExhaustedTopics(),
                    Progress.topicDiscussed(villager, player, entry.id()));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("exhausted-topic check failed; offering the topic", t);
            return false;
        }
    }

    /**
     * One executed reply, after it ran. Counts it when it was substantive and inside the topic that
     * was open when it was submitted, then records the topic as discussed when the rule is met.
     *
     * @param topic         the topic open when the reply was submitted, or null for a starter or a reply
     *                      outside any topic
     * @param openingBeatId the opening beat of that topic as captured at submission, or null
     * @param repliesBefore the substantive replies counted before this one
     */
    public static void onReplySettled(Entity villager, ServerPlayer player, ConversationSession session,
                                      String topic, String question, String answer, String openingBeatId,
                                      int repliesBefore) {
        if (villager == null || player == null || session == null || topic == null || topic.isBlank()) {
            return;
        }
        try {
            TopicEntry entry = ConversationCatalogLoader.topic(topic).orElse(null);
            if (entry == null) {
                return;
            }
            boolean stillOpen = session.topicId().filter(topic::equals).isPresent();
            int replies = repliesBefore;
            if (!outsideTopic(entry, question)
                    && substantive(BeatContractLoader.active().reply(question, answer), answer)) {
                replies++;
                if (stillOpen) {
                    session.noteSubstantiveReply();
                }
            }
            if (stillOpen && session.topicConcluded()) {
                return;
            }
            BeatContract opening = openingBeatId == null ? null
                    : BeatContractLoader.active().beat(openingBeatId).orElse(null);
            String offered = session.currentOffer().map(ConversationSession.ChoiceOffer::questionId).orElse(null);
            if (concluded(stillOpen, replies, openingCounts(opening, topic), offered, entry)) {
                if (stillOpen) {
                    session.markTopicConcluded();
                }
                Progress.markTopicDiscussed(villager, player, topic);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("topic completion bookkeeping failed; ignoring", t);
        }
    }

    /**
     * A page was just offered. When it is a page topics are entered from, every one of them is
     * missing, and at least one is missing because it was finished, the villager says so once, so a
     * category with nothing left never reads as a broken menu. The page keeps its own back answer.
     */
    public static void onPageOffered(Entity villager, ServerPlayer player, ConversationSession session,
                                     String question, Collection<String> answers) {
        if (villager == null || player == null || session == null || question == null || answers == null) {
            return;
        }
        try {
            if (!McaConversationsConfig.hideExhaustedTopics()) {
                return;
            }
            boolean anyTopic = false;
            boolean anyHiddenHere = false;
            for (TopicEntry entry : ConversationCatalogLoader.active().topics()) {
                if (!question.equals(entry.entryQuestion())) {
                    continue;
                }
                anyTopic = true;
                if (answers.contains(entry.entryAnswer())) {
                    return; // something is still on offer
                }
                if (!anyHiddenHere && hides(entry, villager, player)) {
                    anyHiddenHere = true;
                }
            }
            if (anyTopic && anyHiddenHere && session.claimNothingNewLine(question)) {
                McaCompat.sayInDialogue(villager, player, NOTHING_NEW_PHRASE);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("nothing-new line failed; leaving the page as offered", t);
        }
    }
}
