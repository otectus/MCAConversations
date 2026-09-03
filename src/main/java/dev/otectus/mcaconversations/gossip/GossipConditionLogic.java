package dev.otectus.mcaconversations.gossip;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.ReputationBridge;
import dev.otectus.mcaconversations.state.MemoryIds;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.EnumSet;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

/**
 * Server-side glue between the {@code conversations_gossip} condition / {@code conversations_gossip_say}
 * action and the gossip sources. The condition and the action run the same deterministic query
 * ({@link #findNext}) so they always agree on which story is next.
 *
 * <h2>Two sources, one merge (§30.4)</h2>
 *
 * <p>Stories come from Conversations' own gossip log and — when MCA: Reputation is installed — from
 * the incidents this villager knows about the listening player's deeds. Both are normalized into
 * {@link NormalizedGossip} and the newest wins deterministically, so a villager who watched you kill
 * someone yesterday tells <em>that</em>, not last week's wedding.
 *
 * <p>"Already told" is a permanent per-(villager,player) LongTermMemory flag
 * ({@code mcaconversations.gossip.<id>.<playerUuid>}) for both sources alike — Reputation says what
 * the teller knows, Conversations remembers what the teller already said (§19.4).
 */
public final class GossipConditionLogic {

    /** How many known external stories to consider per ask; the told-filter runs on this side. */
    private static final int EXTERNAL_CANDIDATE_CAP = 8;

    private GossipConditionLogic() {
    }

    /** True when this villager has an untold, unexpired story for this player. */
    public static boolean hasUntoldGossip(GossipQuery query, Entity villager, ServerPlayer player) {
        return findNext(query, villager, player).isPresent();
    }

    /**
     * The type of the next untold <em>village</em> story this villager has for this player.
     *
     * <p>Backs the {@code village.recent_event} context field. Read-only and told-aware: it runs the
     * same {@link #findNext} the condition and the action run, so a scene chosen because there is
     * news to tell and the line that tells it cannot disagree about whether there is any.
     *
     * <p>External stories — a deed MCA: Reputation knows about the player — deliberately do not
     * answer here. This field names something that happened in the village, and "you were seen
     * doing something" is not that; content about the player's own deeds has
     * {@code conversations_reputation} to ask instead.
     */
    public static Optional<String> nextUntoldEventType(Entity villager, ServerPlayer player) {
        if (villager == null || player == null) {
            return Optional.empty();
        }
        try {
            return findNext(new GossipQuery(EnumSet.allOf(GossipEventType.class),
                            GossipQuery.DEFAULT_MAX_AGE), villager, player)
                    .filter(gossip -> !gossip.isExternal())
                    .map(gossip -> gossip.nativeEvent().type().jsonName());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("gossip context read failed; no recent event", t);
            return Optional.empty();
        }
    }

    /** Tells the next untold story in the dialogue screen and marks it told. No-op when none. */
    public static void tellNextGossip(GossipSayDirective directive, Entity villager, ServerPlayer player) {
        Optional<NormalizedGossip> next = findNext(directive.query(), villager, player);
        if (next.isEmpty()) {
            return;
        }
        NormalizedGossip gossip = next.get();
        McaCompat.sayInDialogue(villager, player, gossip.phraseKey(directive.phrasePrefix()),
                gossip.arguments());
        // The same told-memory for both sources: this is what makes each story once-per-teller,
        // and what nextGossip's contract says Reputation never tracks itself (§30.4).
        McaCompat.rememberForever(villager,
                MemoryIds.playerScoped(MemoryIds.gossipTold(gossip.toldId()), player.getUUID()));
        if (McaConversationsConfig.COMMON.debugLogging.get()) {
            McaConversations.LOGGER.info("{} told {} about {} ({})", villager.getUUID(),
                    player.getGameProfile().getName(),
                    gossip.isExternal() ? gossip.externalPhraseKey() : gossip.nativeEvent().type(),
                    gossip.toldId());
        }
    }

    private static Optional<NormalizedGossip> findNext(GossipQuery query, Entity villager,
                                                       ServerPlayer player) {
        if (!McaConversationsConfig.COMMON.enableGossip.get()) {
            return Optional.empty();
        }
        MinecraftServer server = player.getServer();
        if (server == null) {
            return Optional.empty();
        }
        OptionalInt villageId = McaCompat.getHomeVillageId(villager);
        if (villageId.isEmpty()) {
            return Optional.empty();
        }
        long now = player.serverLevel().getGameTime();
        long retentionTicks = McaConversationsConfig.COMMON.gossipRetentionDays.get() * 24000L;
        GossipSavedData data = GossipSavedData.get(server);
        data.prune(now, retentionTicks);
        long maxAge = Math.min(query.maxAgeTicks(), retentionTicks);

        List<NormalizedGossip> candidates = new ArrayList<>();
        data.log().query(villageId.getAsInt(), query.types(), now, maxAge, villager.getUUID(),
                        e -> McaCompat.hasMemory(villager,
                                MemoryIds.playerScoped(MemoryIds.gossipTold(e.id()), player.getUUID())))
                .map(NormalizedGossip::ofNative)
                .ifPresent(candidates::add);
        candidates.addAll(untoldExternal(villager, player, maxAge));
        return NormalizedGossip.newest(candidates);
    }

    /**
     * The untold Reputation stories this villager knows (§30.4). The bridge cannot see the
     * told-memory, so it hands back a bounded, newest-first list and the filter runs here — the one
     * place that memory has always lived.
     */
    private static List<NormalizedGossip> untoldExternal(Entity villager, ServerPlayer player,
                                                         long maxAgeTicks) {
        if (!ReputationBridge.isAvailable()) {
            return List.of();
        }
        try {
            List<NormalizedGossip> out = new ArrayList<>();
            for (ReputationBridge.GossipCandidate candidate : ReputationBridge.queries()
                    .gossipCandidates(player, villager, Set.of(), maxAgeTicks, EXTERNAL_CANDIDATE_CAP)) {
                boolean told = McaCompat.hasMemory(villager, MemoryIds.playerScoped(
                        MemoryIds.gossipTold(candidate.alreadyToldId()), player.getUUID()));
                if (!told) {
                    out.add(NormalizedGossip.ofExternal(candidate));
                }
            }
            return out;
        } catch (Throwable t) {
            // A bridge failure costs a story, never the conversation (§35.1).
            McaConversations.LOGGER.debug("[MCA: Conversations] external gossip lookup failed; "
                    + "using the native log only", t);
            return List.of();
        }
    }
}