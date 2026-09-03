package dev.otectus.mcaconversations.compat;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Everything Townstead knows about one villager, normalised for Conversations (Townstead spec 5.2).
 *
 * <p>Three deliberate differences from Townstead's own {@code TownsteadVillagerSnapshot}:
 *
 * <ul>
 *   <li>{@link #uuid()} is a real {@link UUID}. Townstead ships it as a string, and parsing once at
 *       the boundary means no condition ever has to decide what to do with an unparseable one.</li>
 *   <li>The flat snapshot is grouped into {@link TownsteadLifeView}, {@link TownsteadProfessionView}
 *       and {@link TownsteadPersonalityView}, because the datapack query paths in spec 8.2 address
 *       needs and state by group ({@code needs.collapsed}, {@code life.senior}) and the record shape
 *       is what defines those paths.</li>
 *   <li>Raw fertility does not cross this boundary at all. See {@link TownsteadLifeView}.</li>
 * </ul>
 *
 * <p>{@link #carriedVariants()}, {@link #expressedAlleles()} and {@link #heritage()} are carried
 * because spec 8.2 makes them queryable for pack authors, but spec 15.2 forbids them in shipped
 * ordinary gossip and spec 20 keeps them out of the player-facing status command.
 *
 * <p>Every field is a JDK type. Nothing Townstead-owned escapes this record.
 */
public record TownsteadVillagerView(
        UUID uuid,
        String name,
        String entityType,
        TownsteadLifeView life,
        TownsteadProfessionView profession,
        TownsteadPersonalityView personality,
        TownsteadScheduleView schedule,
        TownsteadNeedsView needs,
        Map<String, String> carriedVariants,
        List<String> expressedAlleles,
        Map<String, Float> heritage) {

    /**
     * The villager Townstead does not know about. A real object rather than a null, so a query walks
     * into empty strings and neutral needs instead of branching around a missing root (spec 5.2).
     */
    public static final TownsteadVillagerView EMPTY = new TownsteadVillagerView(
            new UUID(0L, 0L), "", "",
            TownsteadLifeView.EMPTY, TownsteadProfessionView.EMPTY, TownsteadPersonalityView.EMPTY,
            TownsteadScheduleView.EMPTY, TownsteadNeedsView.EMPTY,
            Map.of(), List.of(), Map.of());

    public TownsteadVillagerView {
        carriedVariants = Map.copyOf(carriedVariants);
        expressedAlleles = List.copyOf(expressedAlleles);
        heritage = Map.copyOf(heritage);
    }

    /** True when this is {@link #EMPTY} or an equivalently blank read. */
    public boolean isEmpty() {
        return name.isEmpty() && life.rootId().isEmpty() && !profession.employed();
    }

    /** This villager's share of one ancestry, {@code 0f} when they have none of it. */
    public float heritageOf(String rootId) {
        return heritage.getOrDefault(rootId, 0f);
    }

    /**
     * The ancestry this villager is most of, when one clearly dominates. Deliberately coarse: it is
     * what the privacy-safe {@code townstead_heritage_summary} template variable speaks from, and a
     * villager of genuinely mixed descent should get no summary rather than a list of fractions.
     */
    public Optional<String> dominantHeritage() {
        String best = null;
        float bestShare = 0f;
        for (Map.Entry<String, Float> entry : heritage.entrySet()) {
            if (entry.getValue() != null && entry.getValue() > bestShare) {
                bestShare = entry.getValue();
                best = entry.getKey();
            }
        }
        return bestShare >= 0.6f ? Optional.ofNullable(best) : Optional.empty();
    }
}
