package dev.otectus.mcaconversations.conversation;

import java.util.OptionalInt;

/**
 * Everything the social policy reads about one villager and one player, gathered once per decision
 * (Stability spec §8.2).
 *
 * <p>Optional values keep "unavailable" apart from "zero": a disposition subsystem switched off is
 * not a villager who distrusts the player, and a history store that cannot be read is not a pair
 * that has never met.
 *
 * @param hearts           MCA hearts for the pair
 * @param roles            what the player is to the villager in MCA's family tree
 * @param rupture          an unrepaired rupture is recorded between them
 * @param contactDays      distinct days with a meaningful exchange; empty when history is unreadable
 * @param legacyRecognized an upgraded world's one-time import treats this pair as already acquainted
 * @param familiarity      the disposition familiarity axis; empty when dispositions are off
 * @param trustMargin      trust above the villager's personality baseline; empty when unreadable
 */
public record SocialFacts(int hearts, RelationshipRoles roles, boolean rupture, OptionalInt contactDays,
                          boolean legacyRecognized, OptionalInt familiarity, OptionalInt trustMargin) {

    public SocialFacts {
        roles = roles == null ? RelationshipRoles.NONE : roles;
        contactDays = contactDays == null ? OptionalInt.empty() : contactDays;
        familiarity = familiarity == null ? OptionalInt.empty() : familiarity;
        trustMargin = trustMargin == null ? OptionalInt.empty() : trustMargin;
    }

    /** A pair nothing is known about: the band that discloses least. */
    public static SocialFacts unknown() {
        return new SocialFacts(0, RelationshipRoles.NONE, false, OptionalInt.empty(), false,
                OptionalInt.empty(), OptionalInt.empty());
    }
}
