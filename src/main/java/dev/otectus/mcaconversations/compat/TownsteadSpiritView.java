package dev.otectus.mcaconversations.compat;

import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

/**
 * A village's Townstead "spirit", the character a settlement acquires from what its people build
 * (Townstead spec 5.2).
 *
 * <p>Points accrue per spirit from completed buildings, and Townstead reduces the spread to a
 * {@link #classification()}: a village that has built nothing in particular reads as a plain
 * settlement, one with a clear favourite has a single identity, two close leaders blend, and a wide
 * even spread is mixed.
 *
 * <p>{@link #tier()} is always whatever Townstead's aggregator computed. Its thresholds are not
 * copied here and must never be copied into dialogue content either: a retune upstream would
 * silently change what every authored line means.
 *
 * <p>{@link #classification()} is a lowercase string rather than an enum on purpose, so a future
 * Townstead constant cannot break linkage here.
 */
public record TownsteadSpiritView(
        int villageId,
        Map<String, Integer> perSpirit,
        int total,
        int contributingBuildings,
        int tier,
        String classification,
        String primaryId,
        String secondaryId,
        @Nullable Component readout) {

    public static final TownsteadSpiritView EMPTY =
            new TownsteadSpiritView(-1, Map.of(), 0, 0, 0, "", "", "", null);

    public TownsteadSpiritView {
        perSpirit = Map.copyOf(perSpirit);
    }

    /** Points for one spirit id, or {@code 0} if this village has none. */
    public int pointsFor(String spiritId) {
        return perSpirit.getOrDefault(spiritId, 0);
    }

    /** A spirit's share of the village total, {@code 0.0} when nothing has been built yet. */
    public double shareOf(String spiritId) {
        return total <= 0 ? 0.0D : (double) pointsFor(spiritId) / total;
    }

    /** Townstead's own translated readout, when the aggregator produced one. */
    public Optional<Component> readoutOpt() {
        return Optional.ofNullable(readout);
    }

    /** True when there is no spirit to speak about. */
    public boolean isEmpty() {
        return villageId < 0;
    }
}
