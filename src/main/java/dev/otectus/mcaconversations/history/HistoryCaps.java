package dev.otectus.mcaconversations.history;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;

import java.util.concurrent.atomic.AtomicLong;

/**
 * The bounds on everything the history store keeps (spec §8.8).
 *
 * <p>Two numbers per collection, deliberately. The <b>hard</b> limit is a constant the store enforces
 * whatever the config says, so a mis-set server property can never make a save file grow without
 * bound; the <b>configured</b> limit is read from the config and clamped to the hard one, so an
 * operator may make the mod remember less but never more (spec §22.5).
 *
 * <p>Reading config here rather than at each call site also gives one place that cannot throw: config
 * is read on the dialogue path, and a config that has not finished loading during world creation must
 * degrade to the default rather than propagate out of a condition.
 */
public final class HistoryCaps {

    private HistoryCaps() {
    }

    // Hard ceilings. Chosen from the plan's recommended limits; raising one is a schema decision.
    public static final int HARD_ACTIVE_EPISODES = 32;
    public static final int HARD_RESOLVED_EPISODES = 128;
    public static final int HARD_THREADS_PER_PAIR = 32;
    public static final int HARD_COMMITMENTS_PER_PAIR = 32;
    public static final int HARD_CLAIMS_PER_PAIR = 64;
    public static final int HARD_OPINIONS_PER_VILLAGER = 64;
    public static final int HARD_ROLES_PER_VILLAGER = 64;
    public static final int HARD_RECENCY_PER_PAIR = 128;

    /** World-wide bound on tracked villagers, matching the progress ledger's own pair bound. */
    public static final int HARD_VILLAGERS = 4096;

    /** Bound on villager/player pairs one villager may hold state for. */
    public static final int HARD_PAIRS_PER_VILLAGER = 32;

    /**
     * Code points any single stored string may hold: template ids, subjects, causes, obligations,
     * player-claim source replies and {@link NarrativeValue} payloads.
     *
     * <p>Nothing this mod authors comes close — the longest string in the whole shipped resource set
     * is 183 characters, and that is a translated sentence, not a stored id. The bound exists for the
     * strings the mod does <em>not</em> author: a datapack id, or a hand-edited save. One field of a
     * few megabytes is enough to make a world unloadable, and truncating an id costs a record that was
     * never going to match anything anyway.
     */
    public static final int MAX_TEXT_LENGTH = 512;

    /** DEBUG lines spent on truncation before the counter alone has to speak for the rest. */
    private static final int TRUNCATION_REPORTS = 8;

    private static final AtomicLong TRUNCATIONS = new AtomicLong();

    public static int activeEpisodes() {
        return clamp(McaConversationsConfig.activeEpisodeCap(), 1, HARD_ACTIVE_EPISODES);
    }

    public static int resolvedEpisodes() {
        return clamp(McaConversationsConfig.resolvedEpisodeCap(), 1, HARD_RESOLVED_EPISODES);
    }

    public static int threadsPerPair() {
        return clamp(McaConversationsConfig.openThreadCapPerPair(), 1, HARD_THREADS_PER_PAIR);
    }

    public static int commitmentsPerPair() {
        return clamp(McaConversationsConfig.commitmentCapPerPair(), 1, HARD_COMMITMENTS_PER_PAIR);
    }

    public static int claimsPerPair() {
        return clamp(McaConversationsConfig.playerClaimCapPerPair(), 1, HARD_CLAIMS_PER_PAIR);
    }

    public static int opinionsPerVillager() {
        return clamp(McaConversationsConfig.socialEdgeCapPerVillager(), 1, HARD_OPINIONS_PER_VILLAGER);
    }

    /**
     * Observed social roles one villager may hold.
     *
     * <p>Shares the social-edge config knob with opinions, because both are the same thing to a
     * server owner: how much of a village's inner bookkeeping they are willing to pay for. They are
     * counted separately so a villager with a full opinion list can still learn that somebody is
     * their supplier.
     */
    public static int rolesPerVillager() {
        return clamp(McaConversationsConfig.socialEdgeCapPerVillager(), 1, HARD_ROLES_PER_VILLAGER);
    }

    public static int recencyPerPair() {
        return clamp(McaConversationsConfig.topicRecencyCapPerPair(), 4, HARD_RECENCY_PER_PAIR);
    }

    public static int episodeRetentionDays() {
        return clamp(McaConversationsConfig.episodeRetentionDays(), 1, 365);
    }

    /**
     * Truncates one stored string to {@link #MAX_TEXT_LENGTH} code points.
     *
     * <p>Called from the record constructors, so the bound holds on the mutation path and on the load
     * path with one rule rather than two. Truncation is deliberately silent past the first few lines:
     * a save file with one over-long field usually has thousands, and a log that reports every one of
     * them is a second denial of service on top of the first.
     */
    public static String text(String value) {
        if (value == null) {
            return "";
        }
        if (value.length() <= MAX_TEXT_LENGTH) {
            // Fast path: fewer chars than the cap means fewer code points than the cap.
            return value;
        }
        if (value.codePointCount(0, value.length()) <= MAX_TEXT_LENGTH) {
            return value;
        }
        long seen = TRUNCATIONS.incrementAndGet();
        if (seen <= TRUNCATION_REPORTS) {
            McaConversations.LOGGER.debug(
                    "Truncated a history field of {} code points to the {} allowed{}",
                    value.codePointCount(0, value.length()), MAX_TEXT_LENGTH,
                    seen == TRUNCATION_REPORTS ? " (further truncations are counted, not logged)" : "");
        }
        return value.substring(0, value.offsetByCodePoints(0, MAX_TEXT_LENGTH));
    }

    /**
     * How many strings have been truncated in this JVM.
     *
     * <p>The load path samples this either side of reading a file to learn whether anything was lost,
     * which is what decides whether a backup copy of the original is written.
     */
    public static long truncations() {
        return TRUNCATIONS.get();
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
