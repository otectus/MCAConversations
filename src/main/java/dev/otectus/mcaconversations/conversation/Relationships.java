package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

/**
 * Turns everything the game knows about two people into one {@link RelationshipBand} (spec §9.4).
 *
 * <p>This is the only place heart totals are compared to anything. Dialogue asks for a band by name
 * and the thresholds live in {@link RelationshipBand}, so "what counts as a confidant" is one edit
 * rather than a search through forty results carrying the number 80.
 *
 * <p><b>What this build can see.</b> Hearts and marriage come from MCA and are reliable.
 * {@link RelationshipBand#FAMILY} is deliberately never returned: MCA's parent/child relations are
 * not among the members this mod binds, and guessing at an unbound MCA method is how a compat layer
 * breaks on the next release. The band stays in the vocabulary because it is a real relationship a
 * pack may already model, and the lint keeps shipped content from gating on it until the binding
 * exists.
 */
public final class Relationships {

    private Relationships() {
    }

    /**
     * The band for this villager and player. Fails soft: any read failure returns
     * {@link RelationshipBand#STRANGER}, which is the band that discloses least — a compat break must
     * make villagers reticent, never make them confide in somebody they have never met.
     */
    public static RelationshipBand bandOf(Entity villager, ServerPlayer player) {
        if (villager == null || player == null) {
            return RelationshipBand.STRANGER;
        }
        try {
            int hearts = McaCompat.getHearts(player, villager);
            boolean married = McaCompat.isMarriedToPlayer(villager, player.getUUID());
            return RelationshipBand.of(hearts, married, false, false);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("relationship band read failed; defaulting to stranger", t);
            return RelationshipBand.STRANGER;
        }
    }
}
