package dev.otectus.mcaconversations.chat.group;

import dev.otectus.mcaconversations.history.KnowledgeSource;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * The five ways a second villager is allowed to join in (spec §11.6).
 *
 * <p>The plan is explicit that this is a closed list and that free-for-all ambient response selection
 * is not allowed. That restriction is the feature: a bystander who may say anything says nothing in
 * particular, and the failure mode of group conversation in every game that has tried it is a chorus
 * of villagers agreeing with each other.
 *
 * <p>Each shape names the knowledge sources an interjection may rest on. That is what stops the
 * second villager from confirming a thing they could not know: corroboration requires having been
 * there or having been told by somebody who was, a coworker's detail requires actually sharing the
 * trade, and a family member's different memory requires being family. Only friendly disagreement is
 * open to anybody, because a preference needs no source — which is exactly why it is restricted to
 * low-stakes preferences.
 *
 * <p>{@link #stagger} is turn order, in ticks. Interjections are not simultaneous; a group scene that
 * arrived as three lines in one tick would read as noise rather than as people speaking.
 */
public enum GroupShape {

    /** "It was like that when I passed, too." Confirming or qualifying something public. */
    CORROBORATE("corroborate",
            Set.of(KnowledgeSource.WITNESSED, KnowledgeSource.PARTICIPANT,
                    KnowledgeSource.PUBLIC_NOTICE, KnowledgeSource.TOLD_BY),
            true, 20),

    /** "I've never seen the appeal." A difference of taste, and nothing turns on it. */
    FRIENDLY_DISAGREEMENT("friendly_disagreement",
            Set.of(KnowledgeSource.WITNESSED, KnowledgeSource.PARTICIPANT, KnowledgeSource.FAMILY,
                    KnowledgeSource.COWORKER, KnowledgeSource.TOLD_BY,
                    KnowledgeSource.PUBLIC_NOTICE, KnowledgeSource.UNKNOWN_RUMOR),
            false, 30),

    /** "You'll want the second batch, not the first." Something only the trade would know. */
    COWORKER_DETAIL("coworker_detail",
            Set.of(KnowledgeSource.COWORKER, KnowledgeSource.PARTICIPANT, KnowledgeSource.WITNESSED),
            false, 25),

    /** "That's not how I remember it." The same event, from inside the same family. */
    FAMILY_REMEMBERS("family_remembers",
            Set.of(KnowledgeSource.FAMILY, KnowledgeSource.PARTICIPANT, KnowledgeSource.WITNESSED),
            false, 35),

    /** "That's not yours to tell." Somebody stopping a confidence being spent. */
    BYSTANDER_PRIVACY("bystander_privacy",
            Set.of(KnowledgeSource.WITNESSED, KnowledgeSource.PARTICIPANT, KnowledgeSource.FAMILY,
                    KnowledgeSource.COWORKER),
            false, 15);

    private final String key;
    private final Set<KnowledgeSource> allowedSources;
    private final boolean requiresPublicEvent;
    private final int stagger;

    GroupShape(String key, Set<KnowledgeSource> allowedSources, boolean requiresPublicEvent,
               int stagger) {
        this.key = key;
        this.allowedSources = allowedSources;
        this.requiresPublicEvent = requiresPublicEvent;
        this.stagger = stagger;
    }

    public String key() {
        return key;
    }

    /** Ticks after the lead line before this shape speaks. */
    public int stagger() {
        return stagger;
    }

    /** True when this shape may only be used about something the whole village knows. */
    public boolean requiresPublicEvent() {
        return requiresPublicEvent;
    }

    /** True when a villager on this footing could honestly say this. */
    public boolean acceptsKnowledge(KnowledgeSource source) {
        return source != null && allowedSources.contains(source);
    }

    /** The say pool an interjection of this shape draws from. */
    public String sayPool() {
        return "chatmode.group." + key;
    }

    /**
     * True when this shape is a refusal rather than a contribution.
     *
     * <p>A bystander enforcing privacy is the one shape that is <em>about</em> the conversation
     * rather than about the subject, so it goes first in turn order and it is the one shape that
     * still fires when the subject is something the lead should not have raised.
     */
    public boolean isBoundary() {
        return this == BYSTANDER_PRIVACY;
    }

    public static Optional<GroupShape> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (GroupShape shape : values()) {
            if (shape.key.equals(normalized)) {
                return Optional.of(shape);
            }
        }
        return Optional.empty();
    }
}
