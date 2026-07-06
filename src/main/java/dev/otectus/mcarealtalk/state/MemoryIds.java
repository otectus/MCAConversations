package dev.otectus.mcarealtalk.state;

import java.util.UUID;

/**
 * Canonical builder for the LongTermMemory ids this mod writes into MCA villagers. MCA's own ids
 * are bare strings ({@code seen}, {@code asked_to_stay}) in the same per-villager map, so
 * everything here is namespaced with {@code mcarealtalk.}.
 *
 * <p><b>Memory semantics (verified against MCA 7.6.23 bytecode):</b> MCA's LongTermMemory stores an
 * <em>expiry game-time</em> per id — {@code remember(id, t)} stores {@code max(now, current) + t}
 * (default {@code t} = {@code Integer.MAX_VALUE} ≈ forever), and {@code getMemory(id)} returns the
 * ticks <em>remaining</em> until expiry (0 when missing or expired; expired entries are removed on
 * read). The dialogue-JSON {@code memory} condition computes
 * {@code clamp(remaining / dividend + add, 0, max)} (defaults 1/0/1), which gives two clean gates:
 * <ul>
 *   <li>{@code {"memory": {"id": X}}} → 1 while an unexpired memory exists ("has")</li>
 *   <li>{@code {"memory": {"id": X, "dividend": -1.0, "add": 1.0}}} → 1 when missing/expired ("lacks")</li>
 * </ul>
 *
 * <p><b>Player scoping (pinned against {@code LongTermMemory.parseId}):</b> {@code "var": "player"}
 * suffixes the id with {@code "." + playerUUID}.
 */
public final class MemoryIds {

    public static final String PREFIX = "mcarealtalk.";

    private MemoryIds() {
    }

    /** Permanent "player has asked this topic at least once" flag. */
    public static String topicEver(String topic) {
        return PREFIX + "topic." + topic;
    }

    /** Expiring "player asked this topic recently" cooldown flag. */
    public static String topicCooldown(String topic) {
        return PREFIX + "cooldown." + topic;
    }

    /** Expiring conversation-state flag (e.g. {@code grateful}). */
    public static String state(String name) {
        return PREFIX + "state." + name;
    }

    /** Permanent unlock flag (e.g. {@code opened_up}, {@code confided}). */
    public static String unlock(String name) {
        return PREFIX + "unlock." + name;
    }

    /** Permanent per-(villager,player) "this gossip event was already told" flag. */
    public static String gossipTold(UUID eventId) {
        return PREFIX + "gossip." + eventId;
    }

    /**
     * Applies MCA's {@code "var": "player"} scoping. Must match
     * {@code LongTermMemory.parseId} byte-for-byte: {@code id + "." + playerUuid}.
     */
    public static String playerScoped(String id, UUID playerUuid) {
        return id + "." + playerUuid;
    }

    /** True when the id belongs to this mod (used by lint tooling and tests). */
    public static boolean isOurs(String id) {
        return id.startsWith(PREFIX);
    }
}
