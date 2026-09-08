package dev.otectus.mcaconversations.conversation;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

/**
 * The single answer to "may this player still engage this villager?".
 *
 * <p>Every frontend asked that question in its own words: chat delivery inlined a five-clause
 * boolean, the numbered-choice service compared one squared distance, and the dispatcher checked
 * nothing at all between accepting a message and executing it. They agreed on the rules but not on
 * the wording, so a refusal could not be logged, tested, or explained the same way twice. The rules
 * live here now; each caller keeps only its own radius.
 *
 * <p>The core is pure — booleans and squared distances, no entity access — so the precedence is
 * testable without a server. {@link #evaluate(ServerPlayer, Entity, double)} is the thin adapter
 * that reads those booleans off live entities.
 *
 * <p>This vocabulary is deliberately shared with the conversation-session close reasons: a session
 * that ends because the player walked away closes for the same stated reason that a line was
 * dropped for.
 */
public final class EngagementPolicy {

    /**
     * Why an engagement may not continue, or {@link #OK} when it may. Ordered by the precedence
     * {@link #evaluate(boolean, boolean, boolean, boolean, double, double)} applies: the most
     * fundamental reason wins, so a disconnected player is never reported as merely out of range.
     */
    public enum Verdict {
        OK,
        SPEAKER_GONE,
        SPEAKER_DEAD,
        VILLAGER_DEAD,
        DIMENSION_CHANGED,
        OUT_OF_RANGE;

        public boolean ok() {
            return this == OK;
        }
    }

    private EngagementPolicy() {
    }

    /**
     * Pure core. Precedence is fixed: speaker gone, then speaker dead, then villager dead, then a
     * dimension change, then range. {@code distSqr == maxDistSqr} is inside — the radius is the
     * boundary of the sphere, not the first point outside it.
     */
    public static Verdict evaluate(boolean speakerConnected, boolean speakerAlive, boolean villagerAlive,
                                   boolean sameLevel, double distSqr, double maxDistSqr) {
        if (!speakerConnected) {
            return Verdict.SPEAKER_GONE;
        }
        if (!speakerAlive) {
            return Verdict.SPEAKER_DEAD;
        }
        if (!villagerAlive) {
            return Verdict.VILLAGER_DEAD;
        }
        if (!sameLevel) {
            return Verdict.DIMENSION_CHANGED;
        }
        if (distSqr > maxDistSqr) {
            return Verdict.OUT_OF_RANGE;
        }
        return Verdict.OK;
    }

    /**
     * Entity adapter: reads the booleans off a live speaker and villager. A null on either side is
     * treated as gone rather than thrown, because every caller is on a deferred path where the pair
     * may have stopped existing since the check was scheduled.
     */
    public static Verdict evaluate(ServerPlayer speaker, Entity villager, double maxDistSqr) {
        if (speaker == null || villager == null) {
            return Verdict.SPEAKER_GONE;
        }
        return evaluate(!speaker.hasDisconnected(), speaker.isAlive(), villager.isAlive(),
                speaker.level() == villager.level(), speaker.distanceToSqr(villager), maxDistSqr);
    }
}
