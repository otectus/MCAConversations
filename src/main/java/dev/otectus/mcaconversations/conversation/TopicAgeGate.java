package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.world.entity.Entity;

/**
 * The one place that decides whether a villager is old enough for a catalog topic.
 *
 * <p>MCA filters answers through its own closed {@code Constraint} vocabulary, which has no notion of
 * this mod's catalog: an addon condition can only live on a <em>result</em>, and results are scored
 * after the answer is already on the menu and already clickable. So the catalog's {@code ages}
 * allow-list is worth nothing unless every entry path asks for it — the GUI answer list, the direct
 * dialogue packet, the numbered-choice packet, chat mode and the dynamic hub all funnel through here.
 *
 * <p>Only entries the catalog knows are gated. An answer with no catalog row is not a topic entry and
 * is left exactly as MCA offered it.
 */
public final class TopicAgeGate {

    private TopicAgeGate() {
    }

    /** True when this question/answer pair may be entered by this villager. */
    public static boolean allows(ConversationCatalog catalog, String question, String answer, Entity villager) {
        return allows(catalog, question, answer, McaCompat.ageGroup(villager));
    }

    /** The same decision against an already-read age, for callers that have one. */
    public static boolean allows(ConversationCatalog catalog, String question, String answer, AgeGroup age) {
        if (catalog == null || question == null || answer == null) {
            return true;
        }
        return catalog.byStarter(question, answer)
                .map(entry -> entry.allowsAge(age))
                .orElse(true);
    }

    /** The live catalog's decision — the form every runtime caller uses. */
    public static boolean allows(String question, String answer, Entity villager) {
        return allows(ConversationCatalogLoader.active(), question, answer, villager);
    }
}
