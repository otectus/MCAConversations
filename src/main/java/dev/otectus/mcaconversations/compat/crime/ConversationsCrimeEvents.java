package dev.otectus.mcaconversations.compat.crime;

import dev.otectus.mcacrime.api.event.CrimeWitnessedEvent;
import dev.otectus.mcacrime.api.event.PlayerJailedEvent;
import dev.otectus.mcacrime.api.event.PlayerReleasedFromJailEvent;
import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaBridge;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.state.MemoryIds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bridges MCA: Crime's events into Conversations' memory so a villager who saw a crime can say so
 * (1.8.0). Registered on {@code MinecraftForge.EVENT_BUS} <b>only</b> from
 * {@link ConversationsCrimeCompat#register()} — deliberately NOT an {@code @Mod.EventBusSubscriber},
 * which would force {@code dev.otectus.mcacrime.*} onto the classpath on an install without Crime.
 *
 * <p>A witnessed crime writes a permanent, player-scoped {@code mcaconversations.crime.saw.<type>} flag
 * on every witness villager Crime names (bounded), so dialogue can gate on "I saw what you did". Gossip
 * is deliberately <b>not</b> seeded here: with MCA: Reputation installed Crime's incidents already reach
 * villagers as Reputation gossip candidates, and a second telling in a second voice is the duplication
 * §30.4 exists to prevent; without Reputation, the witness memory is the honest extent of what a
 * village knows.
 */
public final class ConversationsCrimeEvents {

    /** How many witnesses one crime may write a memory on: the nearest few, never a whole riot. */
    static final int MAX_WITNESS_MEMORIES = 8;

    private static final Set<UUID> JAILED = ConcurrentHashMap.newKeySet();

    static boolean isJailed(UUID player) {
        return JAILED.contains(player);
    }

    @SubscribeEvent
    public void onWitnessed(CrimeWitnessedEvent event) {
        ServerPlayer offender = event.getPlayer();
        if (!McaConversationsConfig.COMMON.enableCrime.get() || offender == null || offender.level().isClientSide()
                || !McaBridge.isAvailable() || !(offender.level() instanceof ServerLevel level)) {
            return;
        }
        try {
            String memory = MemoryIds.playerScoped(MemoryIds.crimeWitnessed(event.getCrimeType().toString()),
                    offender.getUUID());
            int written = 0;
            for (UUID witnessId : event.getWitnessIds()) {
                if (written >= MAX_WITNESS_MEMORIES) {
                    break;
                }
                Entity witness = level.getEntity(witnessId);
                if (witness != null && McaCompat.isMcaVillager(witness)) {
                    McaCompat.rememberForever(witness, memory);
                    written++;
                }
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Crime witness -> memory failed for {}; ignoring", event.getCrimeType(), t);
        }
    }

    @SubscribeEvent
    public void onJailed(PlayerJailedEvent event) {
        if (event.getPlayer() != null) {
            JAILED.add(event.getPlayer().getUUID());
        }
    }

    @SubscribeEvent
    public void onReleased(PlayerReleasedFromJailEvent event) {
        if (event.getPlayer() != null) {
            JAILED.remove(event.getPlayer().getUUID());
        }
    }

    @SubscribeEvent
    public void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        JAILED.remove(event.getEntity().getUUID()); // the sentence read is the durable answer on relog
    }

    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        JAILED.clear();
    }
}
