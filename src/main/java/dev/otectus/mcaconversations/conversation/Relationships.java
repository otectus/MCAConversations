package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.disposition.DispositionAxis;
import dev.otectus.mcaconversations.disposition.Dispositions;
import dev.otectus.mcaconversations.history.History;
import dev.otectus.mcaconversations.history.PairHistory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

/**
 * Gathers everything the game knows about two people into a {@link SocialFacts}, and asks
 * {@link SocialPolicy} what that makes them (Stability spec §8).
 *
 * <p>This is the only place heart totals, family roles, ruptures, contact days and the disposition
 * vector are read together. Dialogue asks for a band, a contact state or an attitude by name; the
 * thresholds live in {@link SocialThresholds} and the precedence in {@link SocialPolicy}.
 *
 * <p><b>Where each fact comes from.</b> Hearts and marriage from MCA. Family roles from MCA's family
 * tree, by UUID: the tree holds players as well as villagers, so "this player is one of the
 * villager's parents" is a membership test, never a guess from a name. Ruptures and contact days from
 * this mod's history store. Familiarity and trust from the disposition vector. Each read fails soft to
 * the answer that discloses least — a compat break must make villagers reticent, never make them
 * confide in somebody they have never met.
 */
public final class Relationships {

    private Relationships() {
    }

    /** The band for this villager and player. Fails soft to {@link RelationshipBand#STRANGER}. */
    public static RelationshipBand bandOf(Entity villager, ServerPlayer player) {
        if (villager == null || player == null) {
            return RelationshipBand.STRANGER;
        }
        try {
            return SocialPolicy.band(facts(villager, player), McaConversationsConfig.socialThresholds(),
                    McaConversationsConfig.relationshipAwareDialogue());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("relationship band read failed; defaulting to stranger", t);
            return RelationshipBand.STRANGER;
        }
    }

    /** What the player is to the villager, independent of how they currently get on. */
    public static RelationshipRoles rolesOf(Entity villager, ServerPlayer player) {
        if (villager == null || player == null) {
            return RelationshipRoles.NONE;
        }
        try {
            UUID playerId = player.getUUID();
            boolean spouse = McaCompat.isMarriedToPlayer(villager, playerId);
            if (!(villager.level() instanceof ServerLevel level)) {
                return new RelationshipRoles(spouse, false, false, false);
            }
            UUID villagerId = villager.getUUID();
            return new RelationshipRoles(spouse,
                    McaCompat.getParents(level, villagerId).contains(playerId),
                    McaCompat.getChildren(level, villagerId).contains(playerId),
                    McaCompat.getSiblings(level, villagerId).contains(playerId));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("relationship roles read failed; treating as none", t);
            return RelationshipRoles.NONE;
        }
    }

    /** Everything the policy reads, gathered once. {@link SocialFacts#unknown()} on any failure. */
    public static SocialFacts facts(Entity villager, ServerPlayer player) {
        if (villager == null || player == null) {
            return SocialFacts.unknown();
        }
        try {
            int hearts = McaCompat.getHearts(player, villager);
            RelationshipRoles roles = rolesOf(villager, player);
            Optional<PairHistory> pair = History.pair(villager, player);
            boolean rupture = pair.flatMap(PairHistory::rupture).isPresent();
            OptionalInt contactDays = History.enabled()
                    ? OptionalInt.of(pair.map(PairHistory::contactDays).orElse(0))
                    : OptionalInt.empty();
            boolean legacy = pair.filter(PairHistory::contactInitialized).isPresent()
                    ? pair.get().legacyContact()
                    : legacyEvidence(villager, hearts, roles);
            OptionalInt familiarity = OptionalInt.empty();
            OptionalInt trustMargin = OptionalInt.empty();
            if (Dispositions.enabled()) {
                familiarity = OptionalInt.of(Dispositions.axis(villager, player, DispositionAxis.FAMILIARITY));
                trustMargin = OptionalInt.of(Dispositions.axis(villager, player, DispositionAxis.TRUST)
                        - Dispositions.baseline(villager, DispositionAxis.TRUST));
            }
            return new SocialFacts(hearts, roles, rupture, contactDays, legacy, familiarity, trustMargin);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("social facts read failed; treating the pair as unknown", t);
            return SocialFacts.unknown();
        }
    }

    /** Whether the villager has met this player at all. */
    public static SocialContact contactOf(Entity villager, ServerPlayer player) {
        return SocialPolicy.contact(facts(villager, player));
    }

    /**
     * Credits a meaningful exchange — an executed reply or an accepted gift — to this pair.
     *
     * <p>The legacy decision is taken here, from the same evidence {@link #facts} reads, and only
     * takes effect on the pair's very first credit, so it is settled before any new contact is
     * counted and can never flip afterwards.
     */
    public static void creditContact(Entity villager, ServerPlayer player) {
        if (villager == null || player == null) {
            return;
        }
        try {
            boolean legacy = legacyEvidence(villager, McaCompat.getHearts(player, villager),
                    rolesOf(villager, player));
            History.recordContact(villager, player, legacy);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("contact credit failed; ignoring", t);
        }
    }

    /**
     * An upgraded world's one-time import (Stability spec §11.2): positive hearts or a real family
     * role count as an existing acquaintance. Zero-heart pairs stay strangers, and a world created
     * under the social model never imports anything.
     */
    private static boolean legacyEvidence(Entity villager, int hearts, RelationshipRoles roles) {
        if (!McaConversationsConfig.legacyRelationshipMigration() || (hearts <= 0 && !roles.any())) {
            return false;
        }
        return History.legacyImportWorld(villager.getServer());
    }
}
