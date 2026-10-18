package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.Townstead;
import dev.otectus.mcaconversations.compat.TownsteadCapability;
import dev.otectus.mcaconversations.compat.TownsteadNeedsView;
import dev.otectus.mcaconversations.compat.TownsteadSnapshot;
import dev.otectus.mcaconversations.conversation.DepthClass;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.schedule.Activity;

import java.util.Optional;
import java.util.UUID;

/**
 * What a villager's Townstead day lets chat mode do (Townstead spec §13): whether a passing greeting
 * plays, whether an addressed topic is deferred, whether an ambient reply may come from them, and how
 * firmly their attention is held.
 *
 * <p>The decisions are pure functions of {@link Facts}. With Townstead absent, or
 * {@code scheduleRespectEnabled} off, the Townstead half of every fact is false and each answer is
 * exactly the pre-1.8.0 one.
 */
public final class TownsteadChatPolicy {

    /** A passing greeting (spec §13.2). */
    public enum Greeting {
        /** Asleep, collapsed, in an emergency, in danger, or in another player's conversation. */
        SUPPRESS,
        /** On shift: the greeting plays, but nothing about it may interrupt the work. */
        BRIEF,
        NORMAL
    }

    /** Most responders an ambient message may draw from villagers who are at work. */
    public static final int MAX_WORKING_RESPONDERS = 1;
    /** How much an at-work villager's ambient reply score is discounted. */
    public static final double WORKING_AMBIENT_WEIGHT = 0.5;

    /**
     * Everything the policy reads, as booleans.
     *
     * @param sleeping              the villager is asleep
     * @param panicking             the villager's brain is in PANIC
     * @param otherPlayersDialogue  another player has the villager in a dialogue screen
     * @param collapsed             Townstead: collapsed from exhaustion
     * @param crisis                Townstead: a need at emergency level
     * @param working               Townstead: on shift right now
     * @param tired                 Townstead: drowsy or exhausted
     * @param reactionLocked        Townstead: playing a reaction
     */
    public record Facts(boolean sleeping, boolean panicking, boolean otherPlayersDialogue, boolean collapsed,
                        boolean crisis, boolean working, boolean tired, boolean reactionLocked) {

        public static final Facts NONE = new Facts(false, false, false, false, false, false, false, false);

        /** Held villagers whose hold facts are cached at once; cleared, not evicted, past this. */
        private static final int MAX_HELD = 256;
        private static final java.util.Map<UUID, HeldFacts> HELD = new java.util.concurrent.ConcurrentHashMap<>();

        /**
         * Only the Townstead half, for callers that already know the generic state — the attention
         * tick, which must not add a dialogue-screen read per held villager per tick.
         */
        public static Facts townstead(Entity villager) {
            return villager == null ? NONE : readTownstead(villager, false, false, false);
        }

        /**
         * The Townstead half for judging a <em>held</em> villager, read at most once per
         * {@code townstead.contextCacheTicks} instead of once per tick (feedback remediation WP-D3).
         *
         * <p>The hold is renewed every tick for every live discussion, and each renewal used to make a
         * reflective reaction-lock read. Reused for the cache window, the facts are refreshed early
         * when this mod plays a reaction on the villager ({@link #invalidateHold}) or when any other
         * caller's fresh snapshot shows the villager collapsing or recovering. Danger — hurt, PANIC —
         * is vanilla state and is still read live by the caller every tick.
         */
        public static Facts townsteadForHold(Entity villager) {
            if (villager == null || villager.level() == null) {
                return NONE;
            }
            long now = villager.level().getGameTime();
            HeldFacts held = HELD.get(villager.getUUID());
            if (held != null && holdFactsFresh(now, held.at(), Townstead.contextCacheTicks(),
                    collapseChanged(villager, held))) {
                dev.otectus.mcaconversations.debug.PerfCounters.SERVER.count(
                        dev.otectus.mcaconversations.debug.PerfCounters.Count.TOWNSTEAD_HOLD_CACHED, 1);
                return held.facts();
            }
            Facts fresh = townstead(villager);
            dev.otectus.mcaconversations.debug.PerfCounters.SERVER.count(
                    dev.otectus.mcaconversations.debug.PerfCounters.Count.TOWNSTEAD_HOLD_READS, 1);
            if (HELD.size() >= MAX_HELD) {
                HELD.clear();
            }
            HELD.put(villager.getUUID(), new HeldFacts(now, fresh));
            return fresh;
        }

        /**
         * Pure: whether hold facts read at {@code at} may still be used at {@code now}. Within the
         * window, unless somebody else's fresher read shows the villager collapsing or recovering.
         */
        static boolean holdFactsFresh(long now, long at, int ttlTicks, boolean collapseChanged) {
            return !collapseChanged && now >= at && now - at < Math.max(1, ttlTicks);
        }

        /** Drops a villager's cached hold facts: a reaction was just played on it. */
        public static void invalidateHold(Entity villager) {
            if (villager != null) {
                HELD.remove(villager.getUUID());
            }
        }

        /** Drops every cached hold fact (server stop, tests). */
        public static void resetHolds() {
            HELD.clear();
        }

        private static boolean collapseChanged(Entity villager, HeldFacts held) {
            TownsteadSnapshot newer = Townstead.cachedNewerThan(villager, held.at());
            return newer != null && newer.live() && !newer.villager().isEmpty()
                    && newer.villager().needs().collapsed() != held.facts().collapsed();
        }

        /** Reads the live villager. Townstead facts are false unless schedule respect is on. */
        public static Facts of(Entity villager, ServerPlayer player) {
            if (villager == null) {
                return NONE;
            }
            boolean sleeping = villager instanceof Mob mob && mob.isSleeping();
            boolean panicking = false;
            try {
                panicking = villager instanceof Mob mob && mob.getBrain().isActive(Activity.PANIC);
            } catch (Throwable ignored) {
                // A brain that cannot be read is not a panicking one.
            }
            Optional<UUID> interacting = McaCompat.isInteractingWith(villager);
            boolean other = player != null && interacting.isPresent() && !interacting.get().equals(player.getUUID());
            return readTownstead(villager, sleeping, panicking, other);
        }

        private static Facts readTownstead(Entity villager, boolean sleeping, boolean panicking, boolean other) {
            if (!Townstead.scheduleRespectEnabled()) {
                return new Facts(sleeping, panicking, other, false, false, false, false, false);
            }
            TownsteadSnapshot snapshot = Townstead.snapshot(villager, Townstead.contextCacheTicks());
            if (!snapshot.live() || snapshot.villager().isEmpty()) {
                return new Facts(sleeping, panicking, other, false, false, false, false, false);
            }
            TownsteadNeedsView needs = snapshot.villager().needs();
            boolean locked = false;
            try {
                locked = Townstead.has(TownsteadCapability.REACTION_LOCK)
                        && Townstead.bridge().isReactionLocked(villager, villager.level().getGameTime());
            } catch (Throwable ignored) {
                // Unknown is unlocked.
            }
            String fatigue = needs.fatigueBucket();
            return new Facts(sleeping, panicking, other, needs.collapsed(), needs.inCrisis(),
                    snapshot.villager().schedule().working(),
                    "drowsy".equals(fatigue) || "exhausted".equals(fatigue), locked);
        }
    }

    private TownsteadChatPolicy() {
    }

    public static Greeting greeting(Facts f) {
        if (f.sleeping() || f.panicking() || f.otherPlayersDialogue() || f.collapsed() || f.crisis()) {
            return Greeting.SUPPRESS;
        }
        return f.working() ? Greeting.BRIEF : Greeting.NORMAL;
    }

    /**
     * True when an addressed chat topic of this depth should be deferred with a line rather than
     * opened (spec §13.3). A collapsed or desperate villager opens nothing beyond small talk; one at
     * work or worn out puts off the long and personal conversations and still answers quick ones.
     */
    public static boolean defersTopic(Facts f, DepthClass depth) {
        if (depth == null || depth == DepthClass.QUICK || depth == DepthClass.SERVICE) {
            return false;
        }
        if (f.collapsed() || f.crisis()) {
            return true;
        }
        return (f.working() || f.tired()) && (depth == DepthClass.DEEP || depth == DepthClass.RELATIONSHIP);
    }

    /** False for a villager who should not answer a message shouted to the crowd (spec §13.4). */
    public static boolean ambientEligible(Facts f) {
        return !(f.sleeping() || f.panicking() || f.collapsed() || f.reactionLocked());
    }

    /** Multiplier on an ambient reply's score: an at-work villager is a less likely responder. */
    public static double ambientWeight(Facts f) {
        return f.working() ? WORKING_AMBIENT_WEIGHT : 1.0D;
    }

    /** One held villager's Townstead facts and the game tick they were read. */
    private record HeldFacts(long at, Facts facts) {
    }
}
