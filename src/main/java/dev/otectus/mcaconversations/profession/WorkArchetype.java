package dev.otectus.mcaconversations.profession;

import java.util.Locale;
import java.util.Optional;

/**
 * The kind of working life a profession is, for the purposes of shared <em>mechanics</em> (spec §7.5).
 *
 * <p>The distinction this enum exists to enforce is easy to state and easy to lose: archetypes may
 * share routing, condition shapes, check difficulties and callback plumbing. They may not share
 * villager lines. A mason and a woodworker both build things that outlive them, and that similarity
 * is worth a shared schema; it is not worth a shared sentence, because one of them reads stone and
 * the other reads grain.
 *
 * <p>Every archetype names the tension its professions actually live with, because that tension is
 * what a conversation about work is usually about.
 */
public enum WorkArchetype {

    /** Farmer, shepherd, florist, hunter. Tension: what you tend can still fail you. */
    CULTIVATION("cultivation"),
    /** Butcher, cook, chef, fisherman. Tension: the village eats whether or not you are ready. */
    FOOD("food"),
    /** Smiths, mason, leatherworker, fletcher, woodworker. Tension: the work is judged by its failures. */
    CRAFT("craft"),
    /** Librarian, cartographer, cleric, scribe, engineer. Tension: knowing a thing is not fixing it. */
    KNOWLEDGE("knowledge"),
    /** Guard, archer, mercenary, monster experts. Tension: a quiet day is both the goal and the boredom. */
    DEFENSE("defense"),
    /** Adventurer, miner, oceanographer, netherian, enderian. Tension: the interesting places are the dangerous ones. */
    EXPLORATION("exploration"),
    /** Cultist, outlaw, shady wizard, vampire expert. Tension: the village tolerates you rather than trusts you. */
    OCCULT("occult"),
    /** Nitwit, unemployed, between trades. Tension: a life measured against a trade you do not have. */
    UNTRADED("untraded");

    private final String key;

    WorkArchetype(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /** True when the profession's work is meaningfully affected by weather and season by default. */
    public boolean isOutdoorByDefault() {
        return this == CULTIVATION || this == EXPLORATION || this == DEFENSE;
    }

    /** The archetype an unknown third-party profession falls back to (spec §16). */
    public static WorkArchetype fallback() {
        return CRAFT;
    }

    public static Optional<WorkArchetype> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (WorkArchetype archetype : values()) {
            if (archetype.key.equals(normalized)) {
                return Optional.of(archetype);
            }
        }
        return Optional.empty();
    }
}
