package dev.otectus.mcaconversations.compat;

/**
 * One stage of a Townstead root's life cycle (Townstead spec 5.2). {@code presentsAs} is the
 * lowercase form Townstead uses to decide how a villager at this stage is rendered and addressed.
 */
public record TownsteadLifeStageView(
        String id,
        String label,
        int days,
        float scale,
        String presentsAs,
        float narrativeStart,
        float narrativeEnd) {
}
