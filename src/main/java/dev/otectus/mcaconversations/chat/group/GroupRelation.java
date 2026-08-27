package dev.otectus.mcaconversations.chat.group;

import dev.otectus.mcaconversations.history.KnowledgeSource;

import java.util.Optional;

/**
 * What one bystander is to the villager currently speaking, as far as it can be observed.
 *
 * <p>Deliberately three facts and no more. A group scene needs to know whether this person is family,
 * whether they share the trade, and on what footing they know the subject — and nothing else about
 * them decides whether they may join in. Keeping it this narrow is what makes
 * {@link GroupDirector#shapesFor} a pure function that can be reasoned about and tested without a
 * world.
 *
 * @param family      MCA's family tree records a relation between them
 * @param sharesTrade they hold the same profession, so "the second batch, not the first" is theirs
 * @param knowledge   how this bystander knows the subject, when they know it at all
 */
public record GroupRelation(boolean family, boolean sharesTrade, Optional<KnowledgeSource> knowledge) {

    public static final GroupRelation STRANGER =
            new GroupRelation(false, false, Optional.empty());

    public GroupRelation {
        knowledge = knowledge == null ? Optional.empty() : knowledge;
    }

    public static GroupRelation of(boolean family, boolean sharesTrade, KnowledgeSource knowledge) {
        return new GroupRelation(family, sharesTrade, Optional.ofNullable(knowledge));
    }

    /** True when this bystander has any standing to speak about the subject at all. */
    public boolean knowsTheSubject() {
        return knowledge.isPresent();
    }
}
