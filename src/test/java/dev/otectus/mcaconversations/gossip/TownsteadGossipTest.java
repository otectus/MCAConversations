package dev.otectus.mcaconversations.gossip;

import dev.otectus.mcaconversations.gossip.TownsteadGossipDiff.Derived;
import dev.otectus.mcaconversations.gossip.TownsteadGossipDiff.ResidentNow;
import dev.otectus.mcaconversations.gossip.TownsteadGossipDiff.VillageNow;
import dev.otectus.mcaconversations.gossip.TownsteadObservations.Resident;
import dev.otectus.mcaconversations.gossip.TownsteadObservations.Village;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Townstead gossip (Townstead spec §15): what becomes news, what never does, and what is saved. */
class TownsteadGossipTest {

    private static final UUID ADA = UUID.fromString("0f1e2d3c-4b5a-6978-8796-a5b4c3d2e1f0");

    private static ResidentNow ada(boolean crisis, boolean collapsed, boolean stable, int tier, Set<String> skills,
                                   String stage, int age, int year) {
        return new ResidentNow(ADA, "Ada", crisis, collapsed, stable, "minecraft:farmer", tier, skills, stage, age, year);
    }

    private static ResidentNow well() {
        return ada(false, false, true, 2, Set.of("townstead:a"), "adult", 30, 3);
    }

    private static List<GossipEventType> types(List<Derived> events) {
        return events.stream().map(Derived::type).toList();
    }

    private static Resident step(ResidentNow now, Resident prior, long day, List<GossipEventType> expected) {
        TownsteadGossipDiff.ResidentStep step = TownsteadGossipDiff.resident(now, prior, day, day * 24000L, 2);
        assertEquals(expected, types(step.events()), () -> "day " + day);
        return step.next();
    }

    @Test
    @DisplayName("a first observation seeds and says nothing, even about somebody already in a crisis")
    void seedingIsSilent() {
        Resident seeded = step(ada(true, true, false, 2, Set.of("townstead:a"), "adult", 30, 3), null, 1, List.of());
        assertTrue(seeded.crisis());
        assertFalse(seeded.crisisTold());
        step(well(), seeded, 2, List.of());
    }

    @Test
    @DisplayName("a crisis is news once, holds until every need is comfortable, and recovers only if it was told")
    void crisisHysteresisAndRecovery() {
        Resident r = step(well(), null, 1, List.of());
        r = step(ada(true, false, false, 2, Set.of("townstead:a"), "adult", 30, 3), r, 2,
                List.of(GossipEventType.NEED_CRISIS));
        r = step(ada(false, false, false, 2, Set.of("townstead:a"), "adult", 30, 3), r, 2, List.of());
        assertTrue(r.crisis(), "out of emergency but not yet comfortable: still in the crisis");
        r = step(well(), r, 3, List.of(GossipEventType.RECOVERY));
        r = step(ada(true, false, false, 2, Set.of("townstead:a"), "adult", 30, 3), r, 3, List.of());
        assertTrue(r.crisis(), "a second crisis inside the cooldown is state, not news");
        r = step(well(), r, 4, List.of(), "an untold crisis has no recovery to announce");
    }

    private static Resident step(ResidentNow now, Resident prior, long day, List<GossipEventType> expected, String why) {
        TownsteadGossipDiff.ResidentStep step = TownsteadGossipDiff.resident(now, prior, day, day * 24000L, 2);
        assertEquals(expected, types(step.events()), why);
        return step.next();
    }

    @Test
    @DisplayName("a collapse is news, and so is getting back up")
    void collapse() {
        Resident r = step(well(), null, 1, List.of());
        r = step(ada(true, true, false, 2, Set.of("townstead:a"), "adult", 30, 3), r, 2,
                List.of(GossipEventType.COLLAPSE));
        step(well(), r, 3, List.of(GossipEventType.RECOVERY));
    }

    @Test
    @DisplayName("a trade level, a few new skills, a new stage and a birthday once a year")
    void progress() {
        Resident r = step(well(), null, 1, List.of());
        r = step(ada(false, false, true, 3, Set.of("townstead:a", "townstead:b", "townstead:c", "townstead:d"),
                "adult", 30, 3), r, 2, List.of(GossipEventType.PROFESSION_TIER_UP,
                GossipEventType.SKILL_LEARNED, GossipEventType.SKILL_LEARNED));
        r = step(ada(false, false, true, 3, r.skills(), "adult", 31, 3), r, 3, List.of(GossipEventType.BIRTHDAY));
        r = step(ada(false, false, true, 3, r.skills(), "adult", 32, 3), r, 4, List.of(),
                "one birthday per Townstead year");
        r = step(ada(false, false, true, 3, r.skills(), "senior", 33, 4), r, 5,
                List.of(GossipEventType.LIFE_STAGE_CHANGED));
        ResidentNow newTrade = new ResidentNow(ADA, "Ada", false, false, true, "minecraft:librarian", 4,
                Set.of("townstead:z"), "senior", 33, 4);
        step(newTrade, r, 6, List.of(), "a new trade is not a promotion and brings no skills to announce");
    }

    @Test
    @DisplayName("a building is new once seen, gone only after two sweeps agree, and an unreadable village changes nothing")
    void buildings() {
        Village v = TownsteadGossipDiff.village(7, new VillageNow(Map.of(1, "pen"), false, 0, "", "", ""), null, 2, 0).next();
        TownsteadGossipDiff.VillageStep step = TownsteadGossipDiff.village(7,
                new VillageNow(Map.of(1, "pen", 2, "dock_l2"), false, 0, "", "", ""), v, 2, 1);
        assertEquals(List.of(GossipEventType.BUILDING_REGISTERED), types(step.events()));
        Derived dock = step.events().get(0);
        assertEquals("dock", dock.aName());
        assertEquals("buildingType.dock_l2", dock.attributes().get("a_key"));
        v = step.next();

        assertEquals(List.of(), types(TownsteadGossipDiff.village(7, new VillageNow(null, false, 0, "", "", ""),
                v, 2, 2).events()), "unknown is not demolished");
        step = TownsteadGossipDiff.village(7, new VillageNow(Map.of(2, "dock_l2"), false, 0, "", "", ""), v, 2, 3);
        assertEquals(List.of(), types(step.events()), "one sweep without the pen is a transient");
        step = TownsteadGossipDiff.village(7, new VillageNow(Map.of(2, "dock_l2"), false, 0, "", "", ""), step.next(), 2, 4);
        assertEquals(List.of(GossipEventType.BUILDING_REMOVED), types(step.events()));
    }

    @Test
    @DisplayName("the village's spirit is news when its character changes, not when it is first read")
    void spirit() {
        Village v = TownsteadGossipDiff.village(7, new VillageNow(Map.of(), true, 1, "single", "industrious", ""),
                null, 2, 0).next();
        assertEquals(List.of(), types(TownsteadGossipDiff.village(7,
                new VillageNow(Map.of(), true, 1, "single", "industrious", ""), v, 2, 1).events()));
        TownsteadGossipDiff.VillageStep step = TownsteadGossipDiff.village(7,
                new VillageNow(Map.of(), true, 2, "single", "industrious", ""), v, 2, 2);
        assertEquals(List.of(GossipEventType.SPIRIT_IDENTITY_CHANGED), types(step.events()));
        assertEquals("townstead.spirit.industrious", step.events().get(0).attributes().get("a_key"));
    }

    @Test
    @DisplayName("the saved section round-trips, tolerates a missing or future section, and holds primitives only")
    void saveSection() {
        TownsteadObservations obs = new TownsteadObservations();
        obs.putResident(ADA, new Resident(true, false, true, 4, "minecraft:farmer", 2, Set.of("townstead:a"),
                "adult", 30, 3, 96000L));
        obs.putVillage(7, new Village(Map.of(1, "pen"), Map.of(1, 1), 2, "single", "industrious", "", 96000L));
        CompoundTag tag = obs.toNbt();
        TownsteadObservations back = TownsteadObservations.fromNbt(tag);
        assertEquals(obs.resident(ADA), back.resident(ADA));
        assertEquals(obs.village(7), back.village(7));

        assertEquals(0, TownsteadObservations.fromNbt(new CompoundTag()).residentCount(), "a pre-1.8.0 save");
        CompoundTag future = tag.copy();
        future.putInt("format", TownsteadObservations.FORMAT + 1);
        assertEquals(0, TownsteadObservations.fromNbt(future).residentCount(), "a newer format re-seeds");
        assertTrue(back.prune(100000L));
        assertEquals(0, back.residentCount());
    }

    @Test
    @DisplayName("event attributes are bounded, survive a save, and an older event loads with none")
    void attributes() {
        GossipEvent event = new GossipEvent(UUID.randomUUID(), GossipEventType.BUILDING_REGISTERED, 7, 5L,
                UUID.randomUUID(), "dock", Optional.empty(), "",
                Map.of("a_key", "buildingType.dock_l2", "Bad Key", "x", "long_value", "y".repeat(500)));
        assertFalse(event.attributes().containsKey("Bad Key"));
        assertEquals(GossipEvent.MAX_ATTRIBUTE_LENGTH, event.attributes().get("long_value").length());
        assertEquals(event, GossipEvent.fromNbt(event.toNbt()).orElseThrow());

        CompoundTag old = new GossipEvent(UUID.randomUUID(), GossipEventType.DEATH, 7, 5L, UUID.randomUUID(),
                "Ada", Optional.empty(), "").toNbt();
        assertFalse(old.contains("attrs"));
        assertEquals(Map.of(), GossipEvent.fromNbt(old).orElseThrow().attributes());

        Object[] args = NormalizedGossip.ofNative(event).arguments();
        assertTrue(((Component) args[0]).getContents() instanceof TranslatableContents translatable
                && translatable.getKey().equals("buildingType.dock_l2")
                && "dock".equals(translatable.getFallback()), "a building is told in the listener's language");
    }
}
