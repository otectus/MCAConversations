package dev.otectus.mcaconversations.court;

/**
 * How much of a capital's chronicle one poll may turn into news.
 *
 * <p>A chronicle is an append-only list and the poller keeps an index into it, which makes the whole
 * of "what is new" an arithmetic question — and therefore one that can be got wrong in three ways
 * that all end with villagers announcing a dynasty's entire history at once. Those three cases are
 * the reason this is its own class rather than two lines inside the poller:
 *
 * <ol>
 *   <li><b>First sight.</b> A capital this mod has never read is not news; it is a capital that
 *       existed before the integration was installed. Seed the cursor, say nothing.</li>
 *   <li><b>A shrunken chronicle.</b> Capitals is free to trim old entries. An index past the end is
 *       meaningless, so the cursor is re-seeded rather than clamped: re-reading from zero would
 *       re-tell every entry that survived the trim.</li>
 *   <li><b>A burst.</b> A war and a coronation in the same afternoon must not become fifteen gossip
 *       events; the newest {@code maxPerPoll} are taken and the rest are skipped for good, because
 *       stale court news is worth less than a village that will not stop talking about it.</li>
 * </ol>
 *
 * <p>Pure integers, no server and no Capitals type, so all three cases are ordinary JUnit territory.
 */
public final class ChronicleCursor {

    private ChronicleCursor() {
    }

    /**
     * The half-open entry range {@code [from, to)} this poll should tell, and where the cursor lands.
     *
     * <p>The cursor always advances to the current size, even when nothing is told: skipping is a
     * decision, not a backlog.
     */
    public record Range(int from, int to, int newCursor) {

        public boolean isEmpty() {
            return to <= from;
        }

        public int count() {
            return Math.max(0, to - from);
        }
    }

    /**
     * @param seenBefore the stored cursor, or {@link CourtRoleMemory#CHRONICLE_UNSEEN} for a capital
     *                   never read
     * @param sizeNow    the chronicle's current length
     * @param maxPerPoll how many entries one poll may tell; anything below one tells none
     */
    public static Range advance(int seenBefore, int sizeNow, int maxPerPoll) {
        int size = Math.max(0, sizeNow);
        if (seenBefore < 0 || size < seenBefore) {
            // First sight, or a chronicle that has been trimmed under us. Seed, tell nothing.
            return new Range(size, size, size);
        }
        if (maxPerPoll < 1 || size == seenBefore) {
            return new Range(size, size, size);
        }
        int from = Math.max(seenBefore, size - maxPerPoll);
        return new Range(from, size, size);
    }
}
