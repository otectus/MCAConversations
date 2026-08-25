package dev.otectus.mcaconversations.chat;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Pure ✦ bookkeeping for villager attention: which villager is currently attending to which player,
 * until when, and why. Two sources with a strict pecking order — a villager glancing over because a
 * player opened the chat box ({@link Source#TYPING}) must never shorten or steal the attention of an
 * actual conversation partner ({@link Source#CONVERSATION}).
 *
 * <p>No Minecraft imports; the impure {@code VillagerAttention} applies these holds to live entities
 * each tick. Not thread-safe by design — server thread only, like the session map it accompanies.
 */
public final class AttentionLedger {

    public enum Source {
        /** The player opened chat nearby — short, refreshed while the screen stays open. */
        TYPING,
        /** An actual exchange happened — long, refreshed per exchange (the "wander off" timer). */
        CONVERSATION
    }

    /** One villager's current attention target. */
    public record Hold(UUID playerId, long untilTick, Source source) {
    }

    private final Map<UUID, Hold> holds = new LinkedHashMap<>();

    /**
     * Requests attention from {@code villager} toward {@code player}. A {@code CONVERSATION} hold
     * always wins; a {@code TYPING} request never downgrades or shortens an existing conversation
     * hold, and same-source requests only ever extend (never shorten) the deadline.
     */
    public void hold(UUID villagerId, UUID playerId, long untilTick, Source source) {
        Hold existing = holds.get(villagerId);
        if (existing != null) {
            boolean downgrade = existing.source == Source.CONVERSATION && source == Source.TYPING;
            if (downgrade) {
                return;
            }
            boolean sameRank = existing.source == source;
            if (sameRank && existing.playerId.equals(playerId) && existing.untilTick >= untilTick) {
                return; // never shorten
            }
        }
        holds.put(villagerId, new Hold(playerId, untilTick, source));
    }

    /** Drops whatever hold this villager has (conversation ended, villager gone). */
    public void release(UUID villagerId) {
        holds.remove(villagerId);
    }

    /** Drops only the TYPING holds aimed at {@code player} — conversation partners keep attending. */
    public void releaseTyping(UUID playerId) {
        holds.entrySet().removeIf(e ->
                e.getValue().source == Source.TYPING && e.getValue().playerId.equals(playerId));
    }

    /** Drops every hold aimed at {@code player}, any source (logout). */
    public void releasePlayer(UUID playerId) {
        holds.entrySet().removeIf(e -> e.getValue().playerId.equals(playerId));
    }

    /** Removes expired holds; call once per tick before applying. */
    public void sweep(long now) {
        holds.entrySet().removeIf(e -> now >= e.getValue().untilTick);
    }

    /** Live view of current holds (villager id → hold). Callers must not mutate during iteration. */
    public Map<UUID, Hold> activeHolds() {
        return holds;
    }

    public void clear() {
        holds.clear();
    }
}
