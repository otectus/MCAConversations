package dev.otectus.mcarealtalk.gossip;

import dev.otectus.mcarealtalk.McaRealTalk;
import dev.otectus.mcarealtalk.McaRealTalkConfig;
import dev.otectus.mcarealtalk.compat.McaCompat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
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
        if (!McaRealTalkConfig.COMMON.enableGossip.get() || !McaRealTalkConfig.COMMON.detectDeath.get()) {
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
        if (data.addEvent(event, McaRealTalkConfig.COMMON.maxEventsPerVillage.get())
                && McaRealTalkConfig.COMMON.debugLogging.get()) {
            McaRealTalk.LOGGER.info("Gossip: death of {} ({}) in village {}", name, villager.getUUID(), villageId.getAsInt());
        }
    }

    /** The periodic scan. Called from the server tick handler at the configured interval. */
    public static void scan(MinecraftServer server) {
        if (!McaRealTalkConfig.COMMON.enableGossip.get()) {
            return;
        }
        GossipSavedData data = GossipSavedData.get(server);
        long now = server.overworld().getGameTime();
        data.prune(now, McaRealTalkConfig.COMMON.gossipRetentionDays.get() * 24000L);

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
        List<Entity> residents = McaCompat.loadedVillageResidents(level, villageId);
        if (residents.isEmpty()) {
            return;
        }

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

        Set<UUID> recentlyDead = new HashSet<>();
        for (GossipEvent e : data.log().events()) {
            if (e.type() == GossipEventType.DEATH) {
                recentlyDead.add(e.aUuid());
            }
        }

        List<GossipDiff.Derived> derived = GossipDiff.diff(
                observations, data.snapshots(), recentlyDead,
                McaRealTalkConfig.COMMON.detectMarriage.get(),
                McaRealTalkConfig.COMMON.detectDivorce.get(),
                McaRealTalkConfig.COMMON.detectBirth.get());

        for (GossipDiff.Derived d : derived) {
            GossipEvent event = new GossipEvent(UUID.randomUUID(), d.type(), villageId, now,
                    d.aUuid(), d.aName(), d.bUuid(), d.bName());
            if (data.addEvent(event, McaRealTalkConfig.COMMON.maxEventsPerVillage.get())
                    && McaRealTalkConfig.COMMON.debugLogging.get()) {
                McaRealTalk.LOGGER.info("Gossip: {} in village {}: {} {}", d.type(), villageId, d.aName(), d.bName());
            }
        }

        for (GossipDiff.Observation o : observations) {
            data.putSnapshot(o.uuid(), new RelationshipSnapshot(o.partner(), o.name(), o.isBaby(), now));
        }
    }

    /** Exposed for the admin command. */
    public static Map<UUID, RelationshipSnapshot> snapshots(MinecraftServer server) {
        return GossipSavedData.get(server).snapshots();
    }
}
