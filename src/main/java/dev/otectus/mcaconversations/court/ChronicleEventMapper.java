package dev.otectus.mcaconversations.court;

import dev.otectus.mcaconversations.gossip.GossipEventType;

import java.util.Locale;

/**
 * Which kind of gossip a chronicle entry becomes.
 *
 * <p>Capitals types its chronicle with a coarse event enum — twenty constants covering the whole of a
 * dynasty's public life — and this mod's gossip vocabulary is eleven. The mapping is deliberately
 * many-to-one: a village does not distinguish a throne seized from a throne abdicated to when it
 * gossips about it, it says there is a new king. Every appointment collapses to
 * {@link GossipEventType#APPOINTMENT} for the same reason.
 *
 * <p>Two entries carry no usable type: {@code generic_notable}, which Capitals uses for anything it
 * has not given a constant to, and {@code legacy}, an old plain-text entry from before the semantic
 * encoding existed. The first falls back to substring tests on the entry's translation key, which is
 * a guess but a cheap and reversible one; the second cannot be guessed at all and is
 * {@link GossipEventType#COURT_NEWS}, the type that exists precisely so nothing is ever dropped.
 *
 * <p>Note that {@link GossipEventType#WAR}, {@link GossipEventType#PEACE} and
 * {@link GossipEventType#ALLIANCE} are normally produced by the diplomacy snapshot diff, not here;
 * the substring cases below only catch a chronicle entry that mentions one without Capitals having
 * recorded a relation change.
 *
 * <p>Pure strings in, an enum out — no Capitals type is named, which is what lets the mapping be
 * tested with the mod absent.
 */
public final class ChronicleEventMapper {

    private ChronicleEventMapper() {
    }

    /**
     * @param coarseType     the Capitals event-type enum name lower-cased, or {@code legacy} for an
     *                       undecodable entry; blank is treated as unknown
     * @param translationKey the entry's translation key, used only when the type says nothing
     */
    public static GossipEventType map(String coarseType, String translationKey) {
        String type = coarseType == null ? "" : coarseType.trim().toLowerCase(Locale.ROOT);
        return switch (type) {
            case "capital_founded" -> GossipEventType.CAPITAL_FOUNDED;
            case "crown_child_born", "royal_birth" -> GossipEventType.ROYAL_BIRTH;
            case "heir_apparent_named" -> GossipEventType.COURT_NEWS;
            case "royal_marriage" -> GossipEventType.ROYAL_MARRIAGE;
            case "sovereign_death" -> GossipEventType.ROYAL_DEATH;
            case "throne_seized", "peaceful_transfer" -> GossipEventType.CORONATION;
            case "disinherited" -> GossipEventType.DISGRACE;
            case "legitimized", "new_duke_or_duchess", "lord_commander_appointed", "hand_appointed",
                 "grand_maester_appointed", "royal_guard_appointed", "court_herald_appointed" ->
                    GossipEventType.APPOINTMENT;
            case "mourning_ended", "abdication", "legacy" -> GossipEventType.COURT_NEWS;
            default -> byTranslationKey(translationKey);
        };
    }

    /**
     * The guess of last resort. Ordered so the more specific reading wins: a key mentioning both a
     * pardon and an exile is an appointment only if it mentions neither war nor disgrace first.
     */
    private static GossipEventType byTranslationKey(String translationKey) {
        String key = translationKey == null ? "" : translationKey.toLowerCase(Locale.ROOT);
        if (key.isBlank()) {
            return GossipEventType.COURT_NEWS;
        }
        if (contains(key, "war", "campaign", "siege")) {
            return GossipEventType.WAR;
        }
        if (contains(key, "truce", "peace", "settlement")) {
            return GossipEventType.PEACE;
        }
        if (contains(key, "alliance", "pact", "agreement", "trade")) {
            return GossipEventType.ALLIANCE;
        }
        if (contains(key, "disgrace", "execut", "warrant", "exile", "enemy", "deposed")) {
            return GossipEventType.DISGRACE;
        }
        if (contains(key, "pardon", "appoint", "elevat")) {
            return GossipEventType.APPOINTMENT;
        }
        return GossipEventType.COURT_NEWS;
    }

    private static boolean contains(String key, String... needles) {
        for (String needle : needles) {
            if (java.util.Arrays.stream(key.split("[._:/-]+"))
                    .anyMatch(token -> "war".equals(needle) ? token.equals("war") || token.equals("wars") : token.startsWith(needle))) {
                return true;
            }
        }
        return false;
    }
}
