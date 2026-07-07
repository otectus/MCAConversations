package dev.otectus.mcaconversations.gossip;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.state.ConversationState;
import dev.otectus.mcaconversations.state.StateRules;
import dev.otectus.mcaconversations.state.StateTracker;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;

/**
 * Turns world activity into gossip events.
 *
 * <ul>
 *   <li><b>Deaths</b>: event-driven from {@code LivingDeathEvent} (name cached from the entity or
 *       its snapshot before it is gone).</li>
 *   <li><b>Marriages / divorces / births</b>: derived by the periodic village scan — one nearest
 *       village per online player per sweep (deduped), current partner/age observations diffed
 *       against the persisted snapshots via {@link GossipDiff}.</li>
 * </ul>
 *
 * <p>Divorce-vs-widowhood: the diff suppresses a divorce when the vanished partner has a DEATH
 * event still in the log, so death (persisted) doubles as the suppression record across restarts.
 */
public final class GossipDetectors {

    private static final int VILLAGE_SEARCH_RADIUS = 128;

    private GossipDetectors() {
    }

    /** Called from {@code LivingDeathEvent} for a confirmed MCA villager. */
    public static void onVillagerDeath(Entity villager) {
        if (!McaConversationsConfig.COMMON.enableGossip.get() || !McaConversationsConfig.COMMON.detectDeath.get()) {
            return;
        }
        MinecraftServer server = villager.getServer();
        if (server == null || !(villager.level() instanceof ServerLevel level)) {
            return;
        }
        GossipSavedData data = GossipSavedData.get(server);

        OptionalInt villageId = McaCompat.getHomeVillageId(villager);
        if (villageId.isEmpty()) {
            villageId = McaCompat.findNearestVillageId(level, villager.blockPosition(), VILLAGE_SEARCH_RADIUS);
        }
        if (villageId.isEmpty()) {
            return;
        }
        String name = McaCompat.getVillagerName(villager)
                .or(() -> Optional.ofNullable(data.snapshots().get(villager.getUUID()))
                        .map(RelationshipSnapshot::name))
                .orElse("");
        long now = level.getGameTime();
        GossipEvent event = new GossipEvent(UUID.randomUUID(), GossipEventType.DEATH,
                villageId.getAsInt(), now, villager.getUUID(), name, Optional.empty(), "");
        if (data.addEvent(event, McaConversationsConfig.COMMON.maxEventsPerVillage.get())) {
            StateTracker.applyAmbient(level, villageId.getAsInt(), ConversationState.GRIEVING);
            if (McaConversationsConfig.COMMON.debugLogging.get()) {
                McaConversations.LOGGER.info("Gossip: death of {} ({}) in village {}", name, villager.getUUID(), villageId.getAsInt());
            }
        }
    }

    /** The periodic scan. Called from the server tick handler at the configured interval. */
    public static void scan(MinecraftServer server) {
        if (!McaConversationsConfig.COMMON.enableGossip.get()) {
            return;
        }
        GossipSavedData data = GossipSavedData.get(server);
        long now = server.overworld().getGameTime();
        data.prune(now, McaConversationsConfig.COMMON.gossipRetentionDays.get() * 24000L);

        Set<Long> scanned = new HashSet<>();
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            ServerLevel level = player.serverLevel();
            OptionalInt villageId = McaCompat.findNearestVillageId(level, player.blockPosition(), VILLAGE_SEARCH_RADIUS);
            if (villageId.isEmpty()) {
                continue;
            }
            // Village ids are per-level ints; key the dedup on (dimension, id).
            long key = ((long) level.dimension().location().hashCode() << 32) | (villageId.getAsInt() & 0xFFFFFFFFL);
            if (!scanned.add(key)) {
                continue;
            }
            scanVillage(data, level, villageId.getAsInt(), now);
        }
    }

    private static void scanVillage(GossipSavedData data, ServerLevel level, int villageId, long now) {
        Set<UUID> recentlyDead = new HashSet<>();
        for (GossipEvent e : data.log().events()) {
            if (e.type() == GossipEventType.DEATH) {
                recentlyDead.add(e.aUuid());
            }
        }

        // --- Relationship diff (marriage/divorce/birth) over the LOADED residents ---
        List<Entity> residents = McaCompat.loadedVillageResidents(level, villageId);
        List<GossipDiff.Observation> observations = new ArrayList<>(residents.size());
        for (Entity resident : residents) {
            if (!McaCompat.isMcaVillager(resident)) {
                continue;
            }
            observations.add(new GossipDiff.Observation(
                    resident.getUUID(),
                    McaCompat.getVillagerName(resident).orElse(""),
                    McaCompat.getPartnerUuid(resident),
                    McaCompat.isBaby(resident)));
        }

        Set<UUID> births = new HashSet<>();
        if (!observations.isEmpty()) {
            List<GossipDiff.Derived> derived = GossipDiff.diff(
                    observations, data.snapshots(), recentlyDead,
                    McaConversationsConfig.COMMON.detectMarriage.get(),
                    McaConversationsConfig.COMMON.detectDivorce.get(),
                    McaConversationsConfig.COMMON.detectBirth.get());

            for (GossipDiff.Derived d : derived) {
                if (d.type() == GossipEventType.BIRTH) {
                    births.add(d.aUuid());
                }
                emit(data, level, villageId, now, d, true);
            }

            for (GossipDiff.Observation o : observations) {
                data.putSnapshot(o.uuid(), new RelationshipSnapshot(o.partner(), o.name(), o.isBaby(), now));
            }
        }

        // --- Residency diff (arrival/departure) over the FULL, load-independent residency set ---
        scanResidency(data, level, villageId, now, recentlyDead, births);
    }

    /**
     * Diffs the village's full residency set (loaded or not) into arrival/departure gossip. The first
     * sighting of a village only seeds the stored set — it emits nothing, so discovery never floods
     * arrivals. Arrival/departure events carry no ambient mood.
     */
    private static void scanResidency(GossipSavedData data, ServerLevel level, int villageId, long now,
                                      Set<UUID> recentlyDead, Set<UUID> births) {
        boolean detectArrival = McaConversationsConfig.COMMON.detectArrival.get();
        boolean detectDeparture = McaConversationsConfig.COMMON.detectDeparture.get();
        if (!detectArrival && !detectDeparture) {
            return;
        }
        Set<UUID> current = McaCompat.villageResidentUuids(level, villageId);
        Set<UUID> prior = data.getResidency(villageId);
        if (prior == null) {
            data.putResidency(villageId, current); // seed; emit nothing on first sighting
            return;
        }
        if (current.equals(prior)) {
            return;
        }

        Map<UUID, String> names = new HashMap<>(McaCompat.villageResidentNames(level, villageId));
        for (UUID u : prior) { // departed residents are no longer in the residency name map — resolve them
            if (!current.contains(u) && !names.containsKey(u)) {
                String n = Optional.ofNullable(data.snapshots().get(u)).map(RelationshipSnapshot::name)
                        .filter(s -> !s.isEmpty())
                        .or(() -> McaCompat.familyTreeName(level, u))
                        .orElse("");
                names.put(u, n);
            }
        }

        for (GossipDiff.Derived d : GossipDiff.diffResidency(
                current, prior, names, recentlyDead, births, detectArrival, detectDeparture)) {
            emit(data, level, villageId, now, d, false);
        }
        data.putResidency(villageId, current);
    }

    /** Records one derived event; when {@code applyState} is set, applies its ambient village mood (if any). */
    private static void emit(GossipSavedData data, ServerLevel level, int villageId, long now,
                             GossipDiff.Derived d, boolean applyState) {
        GossipEvent event = new GossipEvent(UUID.randomUUID(), d.type(), villageId, now,
                d.aUuid(), d.aName(), d.bUuid(), d.bName());
        if (data.addEvent(event, McaConversationsConfig.COMMON.maxEventsPerVillage.get())) {
            if (applyState) {
                StateRules.forGossip(d.type()).ifPresent(st -> StateTracker.applyAmbient(level, villageId, st));
            }
            if (McaConversationsConfig.COMMON.debugLogging.get()) {
                McaConversations.LOGGER.info("Gossip: {} in village {}: {} {}", d.type(), villageId, d.aName(), d.bName());
            }
        }
    }

    /** Exposed for the admin command. */
    public static Map<UUID, RelationshipSnapshot> snapshots(MinecraftServer server) {
        return GossipSavedData.get(server).snapshots();
    }
}
