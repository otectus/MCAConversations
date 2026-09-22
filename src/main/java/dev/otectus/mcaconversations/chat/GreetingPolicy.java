package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.conversation.RelationshipBand;
import dev.otectus.mcaconversations.conversation.SocialContact;

/**
 * Which greeting a villager uses, and how often they bother (Stability spec §9.2, §10.1).
 *
 * <p>Pure, so every boundary is a unit test. The point of it is the fallback order: a stranger never
 * falls back to the familiar pool, because that pool greets people by name and says things like
 * "I was hoping I'd run into you" — lines that are wrong the first time two people meet.
 */
public final class GreetingPolicy {

    /** Warm greetings for people the villager actually knows well; voiced per personality. */
    public static final String FAMILIAR = "chatmode.hail";
    /** Met before, nothing more: polite, no assumed warmth, no name. */
    public static final String RECOGNIZED = "chatmode.hail.recognized";
    /** Never met: neutral, helpful, no name. */
    public static final String STRANGER = "chatmode.hail.stranger";
    /** Something is wrong between them; curt either way. */
    public static final String COLD = "chatmode.hail_cold";
    /** Never met, but well spoken of: courtesy, and no claim of any particular deed (spec §8.7). */
    public static final String RESPECTED_STRANGER = "chatmode.hail.respected_stranger";
    /** Their spouse. */
    public static final String PARTNER = "chatmode.hail.partner";
    /** Their parent, child or sibling. */
    public static final String FAMILY = "chatmode.hail.family";

    private GreetingPolicy() {
    }

    public static String pool(RelationshipBand band, SocialContact contact) {
        return pool(band, contact, false);
    }

    /**
     * As above. {@code respected} is the villager's community thinking well of the player, which
     * earns a never-met stranger courtesy and nothing more: a respected stranger is still a stranger,
     * greeted without a name and without any claim that this villager knows what they did.
     */
    public static String pool(RelationshipBand band, SocialContact contact, boolean respected) {
        if (band == null) {
            return STRANGER;
        }
        return switch (band) {
            case HOSTILE, TENSE -> COLD;
            case PARTNER -> PARTNER;
            case FAMILY -> FAMILY;
            case CONFIDANT, FRIEND -> FAMILIAR;
            case ACQUAINTANCE -> RECOGNIZED;
            case STRANGER -> contact == SocialContact.RECOGNIZED ? RECOGNIZED
                    : respected ? RESPECTED_STRANGER : STRANGER;
        };
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
