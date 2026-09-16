package dev.otectus.mcaconversations.compat.reputation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.ReputationBridge;
import dev.otectus.mcareputation.api.event.ReputationProfileChangedEvent;
import dev.otectus.mcareputation.api.event.ReputationTierChangedEvent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;

/**
 * Turns a village changing its mind about a player into something a villager can bring up.
 *
 * <p>Registered on {@code NeoForge.EVENT_BUS} <b>only</b> from
 * {@link ConversationsReputationCompat#register()}, i.e. only once MCA: Reputation is known to be
 * present — deliberately not an {@code @EventBusSubscriber}, which would put
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

    /**
     * Drops this mod's memo of how a village's residents read a player, because their public profile
     * moved.
     *
     * <p>Subscribed because there is a consumer: the check term is memoized for twenty ticks
     * (`ReputationBridge#BIAS_CACHE_TICKS`), and since 0.6.0 the number behind it is the villager's
     * facet-aware opinion — so recognition or a facet moving changes it while the score, the tier and
     * the standing revision all stand still. That is exactly the profile-only change no standing
     * event can describe, and the reason Reputation publishes this one at all (§15).
     *
     * <p>Treated as an invalidation and nothing else: the numbers on the event are not cached as a
     * profile, and nothing is said out loud. Recognition may authorise an acknowledgement, never a
     * greeting — who mentions it, and whether now is the moment, stay the planner's decisions, and a
     * quiet background change is not news.
     */
    @SubscribeEvent
    public void onProfileChanged(ReputationProfileChangedEvent event) {
        try {
            ReputationBridge.invalidateStandingCache(event.playerId());
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("standing profile change note failed; the memo expires on "
                    + "its own", t);
        }
    }
}
