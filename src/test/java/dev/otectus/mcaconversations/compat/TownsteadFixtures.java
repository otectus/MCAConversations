package dev.otectus.mcaconversations.compat;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/** Hand-built Townstead views, so the query, context and template layers test without the mod. */
public final class TownsteadFixtures {

    private TownsteadFixtures() {
    }

    public static TownsteadNeedsView needs(int hunger, int thirst, int fatigue, boolean collapsed) {
        return new TownsteadNeedsView(hunger, 0f, 0f, thirst, thirst, 0f, fatigue, collapsed, false);
    }

    public static TownsteadScheduleView schedule(String activity) {
        return new TownsteadScheduleView("custom", "townstead:farmer", false, false, 9000, 9, 1,
                activity, activity, "townstead:farmer", List.of(), List.of());
    }

    public static TownsteadVillagerView villager(TownsteadNeedsView needs, String activity, int level,
                                                 Set<String> skills, TownsteadPersonalityView personality) {
        return new TownsteadVillagerView(UUID.fromString("0f1e2d3c-4b5a-6978-8796-a5b4c3d2e1f0"), "Ada",
                "mca:female_villager",
                new TownsteadLifeView("townstead:human", "adult", 9000L, 34, false, false, false, true),
                new TownsteadProfessionView("minecraft:farmer", level, 120, skills),
                personality, schedule(activity), needs,
                Map.of("coat", "dun"), List.of("coat:dun"), Map.of("townstead:human", 0.75f, "townstead:elf", 0.25f));
    }

    public static TownsteadVillagerView villager() {
        return villager(needs(80, 18, 2, false), "work", 3, Set.of("townstead:artisan_baking"),
                TownsteadPersonalityView.EMPTY);
    }

    public static TownsteadCalendarView calendar(String profile, int month, int day, int dayOfYear, String season) {
        return new TownsteadCalendarView(profile, 400L, 0, "real", 3, month, day, dayOfYear, 2, season);
    }

    public static TownsteadSpiritView spirit(int tier, String classification, String primary, int primaryPoints,
                                             int total) {
        return new TownsteadSpiritView(7, Map.of(primary, primaryPoints), total, 4, tier, classification,
                primary, "", null);
    }

    public static TownsteadRootView root() {
        return new TownsteadRootView("townstead:human", "Human", "human", "townstead:plains", "",
                "human", List.of(), List.of());
    }

    public static TownsteadBuildingView building(String type) {
        return new TownsteadBuildingView(true, 3, 7, type, 40, 0, 64, 0, -4, 60, -4, 4, 70, 4);
    }

    public static TownsteadSnapshot snapshot(TownsteadVillagerView villager, Set<String> tags) {
        return TownsteadSnapshot.of(villager,
                calendar("townstead_calendar:default", 6, 21, 172, "summer"),
                building("bakery_l2"), root(), spirit(2, "single", "townstead:hearth", 30, 40), tags);
    }
}
