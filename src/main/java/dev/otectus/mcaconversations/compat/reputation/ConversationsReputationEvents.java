package dev.otectus.mcaconversations.compat.reputation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.ReputationBridge;
import dev.otectus.mcareputation.api.event.ReputationTierChangedEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Turns a village changing its mind about a player into something a villager can bring up.
 *
 * <p>Registered on {@code MinecraftForge.EVENT_BUS} <b>only</b> from
 * {@link ConversationsReputationCompat#register()}, i.e. only once MCA: Reputation is known to be
 * present — deliberately not an {@code @Mod.EventBusSubscriber}, which would put
 * {@code dev.otectus.mcareputation.*} on the classpath of an install that has no Reputation and break
 * the standalone case. It lives in this package for the same reason: nothing outside
 * {@code compat.reputation} may name a Reputation type.
 *
 * <p>All it does is leave a note. Who says something about it, whether they are the sort to know, and
 * whether now is a moment to say anything at all are the initiative planner's decisions, not this
 * one's.
 */
public final class ConversationsReputationEvents {

    @SubscribeEvent
    public void onTierChanged(ReputationTierChangedEvent event) {
        try {
            ServerPlayer player = event.player().orElse(null);
            if (player == null) {
                // Standing can move with nobody online. There is no one to remark to, and by the time
                // they log back in the crossing is older than the remark window anyway.
                return;
            }
            ReputationBridge.noteStandingChange(player.getUUID(), event.community().asString(),
                    event.newTierId(), event.upward(), player.level().getGameTime());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("standing tier change note failed; nothing is raised", t);
        }
    }
}
