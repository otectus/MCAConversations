package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.conversation.ConversationHandle;

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

    /**
     * One villager's current attention target.
     *
     * <p>{@code owner} is the discussion that booked the hold, and it is what makes releasing safe:
     * a hold belongs to one accepted discussion, not merely to a player id, so player A's ending
     * cannot hand back a villager that player B has since taken over. Ambient glances — a passing
     * greeting, somebody typing nearby — have no discussion behind them and carry a {@code null}
     * owner; those are released by player instead.
     */
    public record Hold(UUID playerId, long untilTick, Source source, ConversationHandle owner) {

        /** An unowned hold: a glance, or a caller that predates handle identity. */
        public Hold(UUID playerId, long untilTick, Source source) {
            this(playerId, untilTick, source, null);
        }

        /**
         * True when {@code handle} may release this hold: it is the discussion that booked it, or the
         * hold is an unowned one belonging to the same player, which that player's discussion ending
         * is entitled to drop.
         */
        public boolean releasableBy(ConversationHandle handle) {
            if (handle == null) {
                return false;
            }
            return owner == null ? playerId.equals(handle.playerId()) : owner.equals(handle);
        }

        /** True when {@code player} may release this hold — their own glance or their own discussion. */
        public boolean releasableBy(UUID player) {
            return player != null && playerId.equals(player);
        }
    }

    private final Map<UUID, Hold> holds = new LinkedHashMap<>();

    /**
     * Requests attention from {@code villager} toward {@code player}. A {@code CONVERSATION} hold
     * always wins; a {@code TYPING} request never downgrades or shortens an existing conversation
     * hold, and same-source requests only ever extend (never shorten) the deadline.
     */
    public void hold(UUID villagerId, UUID playerId, long untilTick, Source source) {
        hold(villagerId, playerId, untilTick, source, null);
    }

    /**
     * Requests attention on behalf of an accepted discussion, so the hold can later be released by
     * that discussion alone. Precedence is unchanged — ownership decides who may <em>end</em> a
     * hold, not who may take one.
     */
    public void hold(UUID villagerId, UUID playerId, long untilTick, Source source,
                     ConversationHandle owner) {
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
        holds.put(villagerId, new Hold(playerId, untilTick, source, owner));
    }

    /**
     * Drops whatever hold this villager has, whoever booked it.
     *
     * <p>For the endings where the villager itself is the thing that went away — death, removal,
     * server stop — and for nothing else. Every path that ends one <em>discussion</em> uses
     * {@link #releaseIfOwned}, because an unconditional release is exactly how a villager somebody
     * else is talking to gets handed back mid-sentence.
     */
    public void release(UUID villagerId) {
        holds.remove(villagerId);
    }

    /**
     * Drops this villager's hold only if {@code owner} is the discussion that booked it (or an
     * unowned hold by the same player). Returns true when a hold was actually released.
     */
    public boolean releaseIfOwned(UUID villagerId, ConversationHandle owner) {
        Hold hold = villagerId == null ? null : holds.get(villagerId);
        if (hold == null || !hold.releasableBy(owner)) {
            return false;
        }
        holds.remove(villagerId);
        return true;
    }

    /**
     * Player-token form, for the paths that end an exchange without a minted handle — chat mode's
     * farewell, a mute, a session closed for a player who never acquired one. Releases only a hold
     * aimed at that player, so another player's conversation partner keeps attending.
     */
    public boolean releaseIfOwned(UUID villagerId, UUID playerId) {
        Hold hold = villagerId == null ? null : holds.get(villagerId);
        if (hold == null || !hold.releasableBy(playerId)) {
            return false;
        }
        holds.remove(villagerId);
        return true;
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
