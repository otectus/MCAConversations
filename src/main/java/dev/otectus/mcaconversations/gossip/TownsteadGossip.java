package dev.otectus.mcaconversations.gossip;

import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.Townstead;
import dev.otectus.mcaconversations.compat.TownsteadCapability;
import dev.otectus.mcaconversations.compat.TownsteadSnapshot;
import dev.otectus.mcaconversations.compat.TownsteadSpiritView;
import dev.otectus.mcaconversations.compat.TownsteadVillagerView;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.function.Consumer;

/**
 * The Townstead half of the gossip sweep (Townstead spec §15): reads each loaded resident and the
 * village, diffs against {@link TownsteadObservations}, and hands the resulting events back. Runs only
 * while Townstead is live and its gossip is switched on; absent, nothing is read and the saved
 * section is left exactly as it was, so re-adding Townstead picks up where it left off.
 */
public final class TownsteadGossip {

    private TownsteadGossip() {
    }

    public static void scan(GossipSavedData data, ServerLevel level, int villageId, long now,
                            List<Entity> residents, Consumer<TownsteadGossipDiff.Derived> emit) {
        if (!Townstead.gossipEnabled()) {
            return;
        }
        TownsteadObservations observations = data.townstead();
        long today = now / 24000L;
        int cooldown = Townstead.needCrisisCooldownDays();
        for (Entity resident : residents) {
            if (!McaCompat.isMcaVillager(resident)) {
                continue;
            }
            TownsteadSnapshot snapshot = Townstead.snapshot(resident);
            TownsteadVillagerView view = snapshot.villager();
            if (!snapshot.live() || view.isEmpty()) {
                continue; // unknown, not a transition
            }
            TownsteadGossipDiff.ResidentStep step = TownsteadGossipDiff.resident(new TownsteadGossipDiff.ResidentNow(
                    resident.getUUID(), McaCompat.getVillagerName(resident).orElse(view.name()),
                    view.needs().inCrisis(), view.needs().collapsed(), "none".equals(view.needs().primaryNeed()),
                    view.profession().professionId(), view.profession().level(), view.profession().skills(),
                    view.life().lifeStage(), view.life().apparentAgeYears(),
                    snapshot.calendar().isEmpty() ? 0 : snapshot.calendar().year()),
                    observations.resident(resident.getUUID()), today, now, cooldown);
            step.events().forEach(emit);
            observations.putResident(resident.getUUID(), step.next());
        }

        TownsteadSpiritView spirit = Townstead.has(TownsteadCapability.READ_SPIRIT)
                ? Townstead.bridge().spiritForVillage(level, villageId) : TownsteadSpiritView.EMPTY;
        TownsteadGossipDiff.VillageStep step = TownsteadGossipDiff.village(villageId, new TownsteadGossipDiff.VillageNow(
                        McaCompat.villageBuildings(level, villageId).orElse(null), !spirit.isEmpty(), spirit.tier(),
                        spirit.classification(), spirit.primaryId(), spirit.secondaryId()),
                observations.village(villageId), Townstead.buildingRemovalConfirmScans(), now);
        step.events().forEach(emit);
        observations.putVillage(villageId, step.next());
        data.townsteadChanged();
    }
}
