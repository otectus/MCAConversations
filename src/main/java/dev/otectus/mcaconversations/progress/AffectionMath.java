package dev.otectus.mcaconversations.progress;

/**
 * The pure arithmetic behind every conversation-sourced heart change (plan §5.2, §5.3). No world, no
 * MCA, no config lookups — the caller passes the numbers in, so the whole guard chain is plain JUnit
 * territory.
 *
 * <p>The chain, in order, is: <b>stronger-negatives</b> → <b>multiplier</b> → <b>repeat
 * diminishing</b> → <b>per-conversation budget</b> → <b>per-day budget</b>. Positive and negative
 * budgets are tracked separately at every stage, so a player cannot spend the negative budget to
 * unlock more positive capacity.
 *
 * <p>All rounding truncates toward zero, which means the guards can only ever reduce a payout in
 * magnitude — never flip its sign, never round a diminished +1 up to +1 forever.
 */
public final class AffectionMath {

    /** The largest delta an author may write on a single result; clamped again at parse time. */
    public static final int MAX_AUTHORED_DELTA = 8;

    private AffectionMath() {
    }

    /** Clamps an authored delta into the safe range. Applied at parse so bad data can never reach play. */
    public static int clampAuthored(int authored) {
        return Math.max(-MAX_AUTHORED_DELTA, Math.min(MAX_AUTHORED_DELTA, authored));
    }

    /**
     * Applies the stronger-negatives toggle and the global multiplier. Negatives are doubled before
     * the multiplier so the two settings compose predictably.
     */
    public static int scaled(int authored, boolean strongerNegatives, double multiplier) {
        int base = strongerNegatives && authored < 0 ? authored * 2 : authored;
        return (int) (base * multiplier);
    }

    /**
     * Applies the replay policy.
     *
     * @param repeatsToday how many times this decision has already paid out today (0 on the first)
     * @param everApplied  whether it has ever paid out, for {@link ReplayPolicy#ONCE}
     */
    public static int diminished(int delta, int repeatsToday, boolean everApplied, ReplayPolicy policy) {
        if (delta == 0) {
            return 0;
        }
        return switch (policy) {
            case ONCE -> everApplied ? 0 : delta;
            case ONCE_PER_DAY -> repeatsToday > 0 ? 0 : delta;
            case DAILY_REPEAT -> switch (Math.min(repeatsToday, 2)) {
                case 0 -> delta;
                case 1 -> delta / 2;
                default -> 0;
            };
        };
    }

    /**
     * Clamps a delta to whatever positive or negative headroom is left.
     *
     * @param usedPositive hearts already gained against this budget (a non-negative number)
     * @param usedNegative hearts already lost against this budget (a non-negative number)
     * @return the delta reduced to fit, possibly 0
     */
    public static int clampToBudget(int delta, int usedPositive, int positiveBudget,
                                    int usedNegative, int negativeBudget) {
        if (delta > 0) {
            int headroom = Math.max(0, positiveBudget - Math.max(0, usedPositive));
            return Math.min(delta, headroom);
        }
        if (delta < 0) {
            int headroom = Math.max(0, negativeBudget - Math.max(0, usedNegative));
            return -Math.min(-delta, headroom);
        }
        return 0;
    }

    /** The MC day a game-time belongs to; the rollover boundary for every daily counter. */
    public static long dayOf(long gameTime) {
        return Math.floorDiv(gameTime, 24_000L);
    }
}
