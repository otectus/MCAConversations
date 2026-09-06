package dev.otectus.mcaconversations.gossip;

import java.util.Locale;
import java.util.Optional;

/** The village events villagers gossip about. */
public enum GossipEventType {
    MARRIAGE,
    DIVORCE,
    DEATH,
    BIRTH,
    /** A villager took up residence in the village (0.6.0 — from residency-set diffing). */
    ARRIVAL,
    /** A villager left the village for good — moved away, not died (0.6.0 — from residency-set diffing). */
    DEPARTURE,
    /** A player completed an MCA: Quests quest for a villager (0.4.0 — only seeded when Quests is present). */
    QUEST,

    /** A new sovereign took the throne — seized, abdicated to or peacefully passed on (1.6.0 — only seeded when MCA Capitals is present). */
    CORONATION,
    /** A marriage within the royal house (1.6.0 — only seeded when MCA Capitals is present). */
    ROYAL_MARRIAGE,
    /** A royal child was born, or an heir was named (1.6.0 — only seeded when MCA Capitals is present). */
    ROYAL_BIRTH,
    /** The sovereign died and the capital went into mourning (1.6.0 — only seeded when MCA Capitals is present). */
    ROYAL_DEATH,
    /** Somebody was given a court office — Hand, commander, herald, maester (1.6.0 — only seeded when MCA Capitals is present). */
    APPOINTMENT,
    /** Somebody was disinherited, exiled or otherwise fell out of the crown's favour (1.6.0 — only seeded when MCA Capitals is present). */
    DISGRACE,
    /** This capital went to war with another (1.6.0 — only seeded when MCA Capitals is present). */
    WAR,
    /** A war ended, in truce or in settlement (1.6.0 — only seeded when MCA Capitals is present). */
    PEACE,
    /** This capital allied with another (1.6.0 — only seeded when MCA Capitals is present). */
    ALLIANCE,
    /** The village became a capital (1.6.0 — only seeded when MCA Capitals is present). */
    CAPITAL_FOUNDED,
    /** Court news with no better type: the chronicle said something and the herald read it out (1.6.0 — only seeded when MCA Capitals is present). */
    COURT_NEWS;

    /** JSON/lang name, e.g. {@code marriage}. */
    public String jsonName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static Optional<GossipEventType> byJsonName(String name) {
        for (GossipEventType t : values()) {
            if (t.jsonName().equalsIgnoreCase(name)) {
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }
}
