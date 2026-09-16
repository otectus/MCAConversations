package dev.otectus.mcaconversations.conversation;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The identity of one accepted discussion: who is talking to whom, where, through which frontend,
 * and — crucially — <em>which</em> of the discussions that pair has had (spec §3.2.1, §4.5).
 *
 * <p>Everything that can arrive late carries one of these: a close, a heartbeat, an answer, a
 * delayed reply. Comparing the whole handle is what makes an old packet harmless. Two conversations
 * between the same player and the same villager are different handles because {@link #sessionId} is
 * minted fresh each time, so a straggler from the first can never act on the second — that is the
 * entire point of the record's component-wise {@code equals}, and no code may ever compare only the
 * participants when deciding whether a message is still valid.
 *
 * <p>{@link #epoch} closes the same hole across server lifetimes. An integrated server started twice
 * in one JVM would otherwise be free to mint a handle equal to one from the previous world; the
 * epoch advances on every server start, so it cannot.
 *
 * <p>Deliberately free of Minecraft types: the dimension travels as its plain registry-name string
 * (for example {@code minecraft:overworld}), which keeps the identity comparable in a unit test and
 * on either side of the wire.
 *
 * @param epoch      the server lifetime this handle was minted in
 * @param sessionId  unique per accepted discussion — the successor-proofing component
 * @param playerId   the one player who may act on this handle
 * @param villagerId the villager this handle owns
 * @param dimension  registry name of the level both were in when the discussion was accepted
 * @param frontend   which UI this discussion is being held through
 */
public record ConversationHandle(long epoch, UUID sessionId, UUID playerId, UUID villagerId,
                                 String dimension, ConversationSession.Frontend frontend) {

    /**
     * Advanced once per server start. Seeded from wall-clock milliseconds so that even a process
     * that never calls {@link #beginServerEpoch} cannot collide with a saved or logged handle from
     * an earlier run.
     */
    private static final AtomicLong EPOCH = new AtomicLong(System.currentTimeMillis());

    public ConversationHandle {
        Objects.requireNonNull(sessionId, "sessionId");
        Objects.requireNonNull(playerId, "playerId");
        Objects.requireNonNull(villagerId, "villagerId");
        dimension = dimension == null ? "" : dimension;
        frontend = frontend == null ? ConversationSession.Frontend.GUI : frontend;
    }

    /** The epoch handles are currently minted under. */
    public static long currentEpoch() {
        return EPOCH.get();
    }

    /**
     * Starts a new server lifetime. Strictly increasing even when two starts share a millisecond, so
     * handles from the world that just stopped are unequal to every handle of the world starting.
     */
    public static long beginServerEpoch() {
        return EPOCH.updateAndGet(previous -> Math.max(previous + 1L, System.currentTimeMillis()));
    }

    /** Mints the identity of a freshly accepted discussion. */
    public static ConversationHandle mint(UUID playerId, UUID villagerId, String dimension,
                                          ConversationSession.Frontend frontend) {
        return new ConversationHandle(currentEpoch(), UUID.randomUUID(), playerId, villagerId,
                dimension, frontend);
    }

    /** True when this handle is the discussion between exactly this pair. */
    public boolean isFor(UUID player, UUID villager) {
        return playerId.equals(player) && villagerId.equals(villager);
    }

    /**
     * True when {@code other} is a discussion between the same pair — which, note, says nothing
     * about whether it is the <em>same</em> discussion. Only for diagnostics and for choosing
     * between "you switched targets" and "you reopened the same villager"; never for authorizing an
     * action, which always compares the whole handle.
     */
    public boolean samePair(ConversationHandle other) {
        return other != null && isFor(other.playerId, other.villagerId);
    }

    /** Compact log form — full UUIDs turn every teardown line into three lines of hex. */
    @Override
    public String toString() {
        return "handle[" + epoch + '/' + brief(sessionId) + ' ' + brief(playerId) + "->" + brief(villagerId)
                + ' ' + frontend + (dimension.isEmpty() ? "" : "@" + dimension) + ']';
    }

    private static String brief(UUID id) {
        return id.toString().substring(0, 8);
    }
}
