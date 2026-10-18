package dev.otectus.mcaconversations.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * A pass over a keyed store, handed out a bounded batch at a time (feedback remediation WP-D2).
 *
 * <p>The keys are snapshotted when a pass starts, so the store may gain or lose entries between
 * batches: a removed key is simply found absent by the caller and skipped, and a key added mid-pass
 * waits for the next pass. Every key present at the start is visited exactly once per pass, so a pass
 * over {@code n} keys in batches of {@code b} takes {@code ceil(n / b)} calls and examines exactly what
 * a single unbatched call would have.
 *
 * <p>Not thread-safe; the maintenance sweeps that use it run on the server thread.
 */
public final class ResumableSweep<K> {

    private List<K> pass = List.of();
    private int next;
    private boolean inPass;
    private long passesCompleted;

    /** True while a pass has started and not yet handed out its last batch. */
    public boolean inPass() {
        return inPass;
    }

    /** Passes that have handed out their last batch since creation or {@link #reset()}. */
    public long passesCompleted() {
        return passesCompleted;
    }

    /**
     * The next batch of at most {@code batchSize} keys. Starts a new pass from {@code keys} when none
     * is in progress; the batch that empties a pass ends it.
     */
    public List<K> next(Supplier<? extends Collection<K>> keys, int batchSize) {
        if (!inPass) {
            Collection<K> fresh = keys.get();
            pass = fresh == null ? List.of() : List.copyOf(fresh);
            next = 0;
            inPass = true;
        }
        int end = Math.min(pass.size(), next + Math.max(1, batchSize));
        List<K> batch = List.copyOf(pass.subList(next, end));
        next = end;
        if (next >= pass.size()) {
            inPass = false;
            passesCompleted++;
            pass = List.of();
            next = 0;
        }
        return batch;
    }

    /** Abandons any pass in progress. */
    public void reset() {
        pass = List.of();
        next = 0;
        inPass = false;
        passesCompleted = 0;
    }
}
