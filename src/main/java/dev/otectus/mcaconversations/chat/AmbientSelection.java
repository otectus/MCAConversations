package dev.otectus.mcaconversations.chat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * Pure ✦ responder selection for ambient (tier-4) broadcast (spec §12): given the villagers that
 * matched an un-addressed message above the ambient threshold, choose which few actually answer and
 * how their replies stagger. Deterministic — the stagger jitter is derived from each villager's UUID,
 * never {@code Math.random}, so a replay (and the unit tests) reproduce the exact ordering and delays.
 */
public final class AmbientSelection {

    private static final int STAGGER_BASE = 20;
    private static final int STAGGER_SPREAD = 16; // jitter ∈ [20, 35]

    private AmbientSelection() {
    }

    /**
     * A candidate that matched an ambient message: its index in the candidate list, score, distance²,
     * and whether they are at work (Townstead), which caps how many of them answer.
     */
    public record Responder(int candidateIndex, double score, double distSqr, boolean working) {

        public Responder(int candidateIndex, double score, double distSqr) {
            this(candidateIndex, score, distSqr, false);
        }
    }

    /**
     * Highest score first, nearest breaking ties, capped at {@code maxResponders} (spec §12.2). The
     * input is assumed already threshold-filtered by the caller (via {@code IntentMatcher.decide}).
     */
    public static List<Responder> select(List<Responder> matched, int maxResponders) {
        return select(matched, maxResponders, Integer.MAX_VALUE);
    }

    /**
     * As above, with at most {@code maxWorking} of the answers coming from villagers at work (Townstead
     * spec §13.4). The order among those kept is unchanged.
     */
    public static List<Responder> select(List<Responder> matched, int maxResponders, int maxWorking) {
        List<Responder> sorted = new ArrayList<>(matched);
        sorted.sort(Comparator.comparingDouble(Responder::score).reversed()
                .thenComparingDouble(Responder::distSqr));
        List<Responder> kept = new ArrayList<>();
        int working = 0;
        for (Responder responder : sorted) {
            if (maxResponders > 0 && kept.size() >= maxResponders) {
                break;
            }
            if (responder.working()) {
                if (working >= maxWorking) {
                    continue;
                }
                working++;
            }
            kept.add(responder);
        }
        return kept;
    }

    /**
     * Delivery offset (ticks) for the responder at {@code rank} (0 = first, no offset). Each later
     * responder is pushed back by a per-villager jitter of 20–35 ticks so a crowd doesn't answer in
     * one instant (spec §12.3). Deterministic in the villager UUID.
     */
    public static int staggerOffsetTicks(UUID villagerId, int rank) {
        if (rank <= 0) {
            return 0;
        }
        int jitter = STAGGER_BASE + Math.floorMod(villagerId.hashCode(), STAGGER_SPREAD);
        return rank * jitter;
    }
}
