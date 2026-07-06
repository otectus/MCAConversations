package dev.otectus.mcarealtalk.gossip;

import dev.otectus.mcarealtalk.McaRealTalk;
import dev.otectus.mcarealtalk.McaRealTalkConfig;
import dev.otectus.mcarealtalk.compat.McaCompat;
import dev.otectus.mcarealtalk.state.MemoryIds;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Server-side glue between the {@code realtalk_gossip} condition / {@code realtalk_gossip_say}
 * action and the gossip log. The condition and the action run the same deterministic query
 * ({@link GossipLog#query}) so they always agree on which event is next.
 *
 * <p>"Already told" is a permanent per-(villager,player) LongTermMemory flag
 * ({@code mcarealtalk.gossip.<eventId>.<playerUuid>}), so it persists with the villager and
 * de-duplicates per listener.
 */
public final class GossipConditionLogic {

    private GossipConditionLogic() {
    }

    /** True when this villager has an untold, unexpired event for this player. */
    public static boolean hasUntoldGossip(GossipQuery query, Entity villager, ServerPlayer player) {
        return findNext(query, villager, player).isPresent();
    }

    /** Tells the next untold event in the dialogue screen and marks it told. No-op when none. */
    public static void tellNextGossip(GossipSayDirective directive, Entity villager, ServerPlayer player) {
        Optional<GossipEvent> next = findNext(directive.query(), villager, player);
        if (next.isEmpty()) {
            return;
        }
        GossipEvent event = next.get();
        String phrase = directive.phrasePrefix() + "." + event.type().jsonName();
        McaCompat.sayInDialogue(villager, player, phrase,
                Component.literal(event.aName()), Component.literal(event.bName()));
        McaCompat.rememberForever(villager,
                MemoryIds.playerScoped(MemoryIds.gossipTold(event.id()), player.getUUID()));
        if (McaRealTalkConfig.COMMON.debugLogging.get()) {
            McaRealTalk.LOGGER.info("{} told {} about {} ({})", villager.getUUID(),
                    player.getGameProfile().getName(), event.type(), event.id());
        }
    }

    private static Optional<GossipEvent> findNext(GossipQuery query, Entity villager, ServerPlayer player) {
        if (!McaRealTalkConfig.COMMON.enableGossip.get()) {
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
        long retentionTicks = McaRealTalkConfig.COMMON.gossipRetentionDays.get() * 24000L;
        GossipSavedData data = GossipSavedData.get(server);
        data.prune(now, retentionTicks);
        long maxAge = Math.min(query.maxAgeTicks(), retentionTicks);
        return data.log().query(villageId.getAsInt(), query.types(), now, maxAge, villager.getUUID(),
                e -> McaCompat.hasMemory(villager,
                        MemoryIds.playerScoped(MemoryIds.gossipTold(e.id()), player.getUUID())));
    }
}
