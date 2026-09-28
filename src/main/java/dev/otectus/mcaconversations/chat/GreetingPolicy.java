package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.conversation.RelationshipBand;
import dev.otectus.mcaconversations.conversation.RelationshipRoles;
import dev.otectus.mcaconversations.conversation.SocialContact;

/**
 * Which greeting a villager uses, and how often they bother (Stability spec §9.2, §10.1).
 *
 * <p>Pure, so every boundary is a unit test. Every band has a pool of its own, because each one
 * permits different words: "I was hoping I'd run into you" is right for a friend and wrong the first
 * time two people meet, and "there's something I've told nobody else" is right for a confidant and
 * presumptuous from a friend. Nothing falls back to a warmer pool, and a stranger is never greeted by
 * a line that knows their name.
 */
public final class GreetingPolicy {

    /**
     * The one greeting every band shared before 1.8.0. No band chooses it any more; it is kept, and
     * kept stranger-safe (no name, no claim), so that an older resource pack or caller that still asks
     * for it cannot greet somebody the villager has never met as an old friend (spec §9.2).
     */
    public static final String LEGACY = "chatmode.hail";
    /** Never met: neutral, helpful, no name. */
    public static final String STRANGER = "chatmode.hail.stranger";
    /** Met before, nothing more: polite, no assumed warmth, no name. */
    public static final String RECOGNIZED = "chatmode.hail.recognized";
    /**
     * A regular: a face and, now, a name the villager knows. Cordial rather than warm — it can be said
     * at zero hearts — so it never claims to have missed the player or to count them a friend.
     */
    public static final String ACQUAINTANCE = "chatmode.hail.acquaintance";
    /** Warm: glad to see them, and may use their name. Nothing confided, nothing romantic. */
    public static final String FRIEND = "chatmode.hail.friend";
    /**
     * Trusted with what the village is not told: the villager lowers their guard, or offers to. Still
     * not romantic — a confidant may be married to somebody else — and it names no particular shared
     * memory, which a greeting has no evidence for. Said for the player alone (see
     * {@link UtteranceAudience}).
     */
    public static final String CONFIDANT = "chatmode.hail.confidant";
    /**
     * Something is unresolved: reserved, careful, and open to repair rather than shut. Written for any
     * relationship that can go tense — a spouse, a child, a friend or somebody the player only ever
     * hurt — so it assumes none of them, and never uses the player's name.
     */
    public static final String GUARDED = "chatmode.hail.guarded";
    /** Hostile: curt, and that is all. */
    public static final String COLD = "chatmode.hail_cold";
    /** Never met, but well spoken of: courtesy, and no claim of any particular deed (spec §8.7). */
    public static final String RESPECTED_STRANGER = "chatmode.hail.respected_stranger";
    /** Their spouse. */
    public static final String PARTNER = "chatmode.hail.partner";
    /**
     * The player is their parent: a son or daughter of any age, from a child to a grown adult,
     * greeting the person who raised them. Never uses the name — nobody calls a parent by it.
     */
    public static final String FAMILY_PARENT = "chatmode.hail.family.parent";
    /** The player is their child: a parent greeting a grown son or daughter. */
    public static final String FAMILY_CHILD = "chatmode.hail.family.child";
    /** The player is their sibling. */
    public static final String FAMILY_SIBLING = "chatmode.hail.family.sibling";
    /** Goodbye to somebody the villager knows; voiced per personality, and may use their name. */
    public static final String FAREWELL = "chatmode.farewell";
    /** Goodbye to somebody never met: no name, no "come back and tell me everything". */
    public static final String FAREWELL_STRANGER = "chatmode.farewell.stranger";
    /**
     * Goodbye while something is unresolved: careful, never "come back soon", and never the name —
     * the same reasons as {@link #GUARDED}.
     */
    public static final String FAREWELL_GUARDED = "chatmode.farewell.guarded";
    /** Goodbye from somebody who is against the player: curt, and nameless like {@link #COLD}. */
    public static final String FAREWELL_HOSTILE = "chatmode.farewell.hostile";
    /**
     * The player is their parent: a son or daughter of any age seeing them off. Never the name, for the
     * reason {@link #FAMILY_PARENT} gives.
     */
    public static final String FAREWELL_FAMILY_PARENT = "chatmode.farewell.family.parent";

    private GreetingPolicy() {
    }

    /** As below, for a pair with no good name to go on and no family role. */
    public static String pool(RelationshipBand band, SocialContact contact) {
        return pool(band, contact, false, RelationshipRoles.NONE);
    }

    /**
     * The greeting for this band. {@code respected} is the villager's community thinking well of the
     * player, which earns a never-met stranger courtesy and nothing more: a respected stranger is still
     * a stranger, greeted without a name and without any claim that this villager knows what they did.
     * {@code roles} decides which family greeting a relative uses (see {@link #family}).
     */
    public static String pool(RelationshipBand band, SocialContact contact, boolean respected,
                              RelationshipRoles roles) {
        if (band == null) {
            return STRANGER;
        }
        return switch (band) {
            case HOSTILE -> COLD;
            case TENSE -> GUARDED;
            case PARTNER -> PARTNER;
            case FAMILY -> family(roles);
            case CONFIDANT -> CONFIDANT;
            case FRIEND -> FRIEND;
            case ACQUAINTANCE -> ACQUAINTANCE;
            case STRANGER -> contact == SocialContact.RECOGNIZED ? RECOGNIZED
                    : respected ? RESPECTED_STRANGER : STRANGER;
        };
    }

    /**
     * Which family greeting. A child running up to a parent, a parent looking over a grown child and
     * two siblings are three different hellos, so each role has its own pool rather than one pool
     * written to fit all three and sounding right for none.
     *
     * <p>The player being the villager's parent comes first and is also the answer when no role is
     * known: it is by far the common case, since a player's family in MCA is almost always their own
     * children, and those lines claim the least.
     */
    public static String family(RelationshipRoles roles) {
        RelationshipRoles r = roles == null ? RelationshipRoles.NONE : roles;
        if (r.parent()) {
            return FAMILY_PARENT;
        }
        if (r.child()) {
            return FAMILY_CHILD;
        }
        return r.sibling() ? FAMILY_SIBLING : FAMILY_PARENT;
    }

    /**
     * Whether the hello or goodbye comes with a wave. A brush-off is not a wave, and neither is the
     * careful nod of somebody who has not yet made up with the player — coming or going.
     */
    public static boolean waves(String pool) {
        return !COLD.equals(pool) && !GUARDED.equals(pool)
                && !FAREWELL_HOSTILE.equals(pool) && !FAREWELL_GUARDED.equals(pool);
    }

    /** As below, for a pair with no family role. */
    public static String farewell(RelationshipBand band, SocialContact contact) {
        return farewell(band, contact, RelationshipRoles.NONE);
    }

    /**
     * Which goodbye. The same permission rules as the hello: a stranger is never seen off by name, nor
     * is anybody the villager is at odds with, and a son or daughter does not use a parent's first name.
     * Everybody else the villager knows gets the ordinary goodbye, which fits a regular, a friend, a
     * confidant, a spouse, a parent seeing off a grown child and a sibling alike.
     */
    public static String farewell(RelationshipBand band, SocialContact contact, RelationshipRoles roles) {
        if (band == RelationshipBand.HOSTILE) {
            return FAREWELL_HOSTILE;
        }
        if (band == RelationshipBand.TENSE) {
            return FAREWELL_GUARDED;
        }
        if (band == RelationshipBand.FAMILY && FAMILY_PARENT.equals(family(roles))) {
            return FAREWELL_FAMILY_PARENT;
        }
        boolean unmet = contact != SocialContact.RECOGNIZED;
        return (band == null || band == RelationshipBand.STRANGER) && unmet ? FAREWELL_STRANGER : FAREWELL;
    }

    /**
     * Multiplier on the day's greeting chance. A stranger may still greet now and then; friends
     * acknowledge you far more often; somebody you have hurt mostly lets you walk past.
     */
    public static double frequency(RelationshipBand band, SocialContact contact) {
        if (band == null) {
            return 0.35;
        }
        return switch (band) {
            case HOSTILE -> 0.10;
            case TENSE -> 0.25;
            case PARTNER, FAMILY, CONFIDANT, FRIEND -> 1.0;
            case ACQUAINTANCE -> 0.70;
            case STRANGER -> contact == SocialContact.RECOGNIZED ? 0.70 : 0.35;
        };
    }
}
