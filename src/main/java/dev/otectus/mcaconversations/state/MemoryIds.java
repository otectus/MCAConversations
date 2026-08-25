package dev.otectus.mcaconversations.state;

import java.util.UUID;

/**
 * Canonical builder for the LongTermMemory ids this mod writes into MCA villagers. MCA's own ids
 * are bare strings ({@code seen}, {@code asked_to_stay}) in the same per-villager map, so
 * everything here is namespaced with {@code mcaconversations.}.
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

    public static final String PREFIX = "mcaconversations.";

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
     * Permanent "this player completed this MCA: Quests quest for me" flag (per-villager, scope by player).
     * {@code questId} is the quest's {@code namespace:path}; {@code :} and {@code /} are flattened to
     * {@code .} so the result stays a bare MCA memory-map key.
     */
    public static String questCompleted(String questId) {
        return PREFIX + "quest.done." + sanitize(questId);
    }

    /** Permanent "this player failed this MCA: Quests quest for me" flag (per-villager, scope by player). */
    public static String questFailed(String questId) {
        return PREFIX + "quest.failed." + sanitize(questId);
    }

    /** Flattens a {@code namespace:path} (or path with {@code /}) into a bare LongTermMemory key segment. */
    private static String sanitize(String questId) {
        return questId.replace(':', '.').replace('/', '.');
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
