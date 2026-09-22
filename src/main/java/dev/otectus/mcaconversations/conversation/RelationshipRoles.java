package dev.otectus.mcaconversations.conversation;

/**
 * Who this player <em>is</em> to a villager, independent of how the two currently get on.
 *
 * <p>A role survives a quarrel: a spouse with an unresolved incident is still a spouse, and a child
 * who is angry with a parent is still their child. That is why roles are kept apart from the
 * {@link RelationshipBand} — the band says how the villager speaks right now, the role says what
 * they are to each other (Stability spec §8.3). Every flag is read from MCA's own family tree by
 * UUID; nothing is inferred from a shared surname, a shared village or proximity.
 *
 * @param spouse  MCA has them married to this player
 * @param parent  this player is one of the villager's parents
 * @param child   this player is one of the villager's children
 * @param sibling this player is one of the villager's siblings
 */
public record RelationshipRoles(boolean spouse, boolean parent, boolean child, boolean sibling) {

    public static final RelationshipRoles NONE = new RelationshipRoles(false, false, false, false);

    /** A blood or adoptive tie in MCA's family tree. Marriage is a role but not "family" here. */
    public boolean family() {
        return parent || child || sibling;
    }

    /** Any role at all. */
    public boolean any() {
        return spouse || family();
    }
}
