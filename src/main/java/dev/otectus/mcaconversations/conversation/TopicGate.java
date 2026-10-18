package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.compat.KingdomBridge;
import dev.otectus.mcaconversations.compat.CivicBridge;
import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

/**
 * The shared age, kingdom, civic-contact, Townstead and family-perspective decision used by every
 * catalog-topic entry path,
 * plus — on the starter overloads only — whether this pair has already discussed the topic to the end
 * ({@link TopicExhaustion}). The {@link TopicEntry} overload leaves that out on purpose: the dynamic
 * hub's slots go through it, and a Continue/Ask entry carries a live thread or episode, which is new
 * content whatever the topic's history.
 */
public final class TopicGate {

    private TopicGate() {
    }

    /** True when this pair is not a catalog starter, or its complete gate currently passes. */
    public static boolean allows(String question, String answer, Entity villager, ServerPlayer player) {
        return allows(ConversationCatalogLoader.active(), question, answer, villager, player);
    }

    public static boolean allows(ConversationCatalog catalog, String question, String answer,
                                 Entity villager, ServerPlayer player) {
        if (catalog == null || question == null || answer == null) return true;
        return catalog.byStarter(question, answer)
                .map(entry -> allows(entry, villager, player) && !TopicExhaustion.hides(entry, villager, player))
                .orElse(true);
    }

    /** Runtime decision for a known catalog row, also used by the dynamic hub. */
    public static boolean allows(TopicEntry entry, Entity villager, ServerPlayer player) {
        if (entry == null) return true;
        if (villager == null || !entry.allowsAge(McaCompat.ageGroup(villager))) return false;
        if (entry.townstead() && !dev.otectus.mcaconversations.compat.Townstead.contentEnabled()) return false;
        if (entry.kingdomGate().map(gate -> KingdomBridge.allows(gate, player, villager)).orElse(true) == false) {
            return false;
        }
        if (entry.civicContact() && CivicBridge.speakerContext(player, villager).isEmpty()) return false;
        // The perspective the topic is written from (1.8.0): a belt beside MCA's kids/parent constraint,
        // read from the same family tree, so no entry path can offer a child's lines to a parent.
        return allowsFamily(entry, () -> Relationships.rolesOf(villager, player),
                requirement -> requirement.test(villager));
    }

    /**
     * Pure: the topic's {@code player_role} and {@code family} requirements against the pair. The
     * roles are read only when the topic states a role, so an ungated topic costs no family-tree read.
     */
    static boolean allowsFamily(TopicEntry entry, java.util.function.Supplier<RelationshipRoles> roles,
                                Predicate<FamilyRequirement> familyEvaluator) {
        if (entry.playerRole().isPresent() && !entry.playerRole().get().holds(roles.get())) {
            return false;
        }
        return entry.family().map(familyEvaluator::test).orElse(true);
    }

    /** Whether a packet/question pair names a catalog starter and therefore needs owned validation. */
    public static boolean isCatalogStarter(String question, String answer) {
        return question != null && answer != null
                && ConversationCatalogLoader.active().byStarter(question, answer).isPresent();
    }

    /** Pure seam for parser and bypass regression tests without a running Minecraft server. */
    static boolean allows(ConversationCatalog catalog, String question, String answer, AgeGroup age,
                          Predicate<KingdomGateSpec> kingdomEvaluator) {
        return allows(catalog, question, answer, age, kingdomEvaluator, () -> false);
    }

    static boolean allows(ConversationCatalog catalog, String question, String answer, AgeGroup age,
                          Predicate<KingdomGateSpec> kingdomEvaluator,
                          java.util.function.BooleanSupplier civicContactEvaluator) {
        return allows(catalog, question, answer, age, kingdomEvaluator, civicContactEvaluator, () -> false);
    }

    static boolean allows(ConversationCatalog catalog, String question, String answer, AgeGroup age,
                          Predicate<KingdomGateSpec> kingdomEvaluator,
                          java.util.function.BooleanSupplier civicContactEvaluator,
                          java.util.function.BooleanSupplier townsteadEvaluator) {
        return allows(catalog, question, answer, age, kingdomEvaluator, civicContactEvaluator, townsteadEvaluator,
                entry -> false);
    }

    /** As above, with the pair's "already discussed to the end" answer supplied by the test. */
    static boolean allows(ConversationCatalog catalog, String question, String answer, AgeGroup age,
                          Predicate<KingdomGateSpec> kingdomEvaluator,
                          java.util.function.BooleanSupplier civicContactEvaluator,
                          java.util.function.BooleanSupplier townsteadEvaluator,
                          Predicate<TopicEntry> exhaustedEvaluator) {
        return allows(catalog, question, answer, age, kingdomEvaluator, civicContactEvaluator, townsteadEvaluator,
                exhaustedEvaluator, () -> RelationshipRoles.NONE, requirement -> false);
    }

    /** As above, with the pair's family roles and the speaker's family supplied by the test. */
    static boolean allows(ConversationCatalog catalog, String question, String answer, AgeGroup age,
                          Predicate<KingdomGateSpec> kingdomEvaluator,
                          java.util.function.BooleanSupplier civicContactEvaluator,
                          java.util.function.BooleanSupplier townsteadEvaluator,
                          Predicate<TopicEntry> exhaustedEvaluator,
                          java.util.function.Supplier<RelationshipRoles> roles,
                          Predicate<FamilyRequirement> familyEvaluator) {
        if (catalog == null || question == null || answer == null) return true;
        return catalog.byStarter(question, answer)
                .map(entry -> entry.allowsAge(age)
                        && (!entry.townstead() || townsteadEvaluator.getAsBoolean())
                        && entry.kingdomGate().map(kingdomEvaluator::test).orElse(true)
                        && (!entry.civicContact() || civicContactEvaluator.getAsBoolean())
                        && allowsFamily(entry, roles, familyEvaluator)
                        && !TopicExhaustion.hidden(entry, true, exhaustedEvaluator.test(entry)))
                .orElse(true);
    }
}
