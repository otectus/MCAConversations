package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Optional;

/**
 * What the player is to the villager a topic is written for (feedback remediation §2.3).
 *
 * <p>The perspective a family topic is written from, stated rather than implied: {@code checkin_child}
 * is a parent talking to their child, so its catalog row says the player is the speaker's
 * {@code parent}; {@code ask_parent} is a child talking to their parent, so it says {@code child}.
 * Until 1.8.0 that perspective lived only in MCA's {@code kids}/{@code parent} constraints and in the
 * authors' heads, which is how half of each topic came to be written from the other side.
 *
 * <p>Read against MCA's family tree through {@link Relationships#rolesOf}, never inferred.
 */
public enum PlayerRole {
    /** The player is one of the speaker's parents: the speaker is their child. */
    PARENT("parent"),
    /** The player is one of the speaker's children: the speaker is their parent. */
    CHILD("child"),
    /** The player is one of the speaker's siblings. */
    SIBLING("sibling"),
    /** MCA has the speaker married to the player. */
    SPOUSE("spouse");

    private final String key;

    PlayerRole(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /** True when MCA's tree says the player holds this role towards the speaker. */
    public boolean holds(RelationshipRoles roles) {
        if (roles == null) {
            return false;
        }
        return switch (this) {
            case PARENT -> roles.parent();
            case CHILD -> roles.child();
            case SIBLING -> roles.sibling();
            case SPOUSE -> roles.spouse();
        };
    }

    public static Optional<PlayerRole> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String wanted = key.trim().toLowerCase(Locale.ROOT);
        for (PlayerRole role : values()) {
            if (role.key.equals(wanted)) {
                return Optional.of(role);
            }
        }
        return Optional.empty();
    }
}
