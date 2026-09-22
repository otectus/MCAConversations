package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.compat.KingdomBridge;
import dev.otectus.mcaconversations.compat.CivicBridge;
import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

/** The shared age, kingdom and civic-contact decision used by every catalog-topic entry path. */
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
                .map(entry -> allows(entry, villager, player))
                .orElse(true);
    }

    /** Runtime decision for a known catalog row, also used by the dynamic hub. */
    public static boolean allows(TopicEntry entry, Entity villager, ServerPlayer player) {
        if (entry == null) return true;
        if (villager == null || !entry.allowsAge(McaCompat.ageGroup(villager))) return false;
        if (entry.kingdomGate().map(gate -> KingdomBridge.allows(gate, player, villager)).orElse(true) == false) {
            return false;
        }
        return !entry.civicContact() || CivicBridge.speakerContext(player, villager).isPresent();
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
        if (catalog == null || question == null || answer == null) return true;
        return catalog.byStarter(question, answer)
                .map(entry -> entry.allowsAge(age)
                        && entry.kingdomGate().map(kingdomEvaluator::test).orElse(true)
                        && (!entry.civicContact() || civicContactEvaluator.getAsBoolean()))
                .orElse(true);
    }
}
