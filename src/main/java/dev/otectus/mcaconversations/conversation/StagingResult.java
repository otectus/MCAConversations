package dev.otectus.mcaconversations.conversation;

import java.util.ArrayList;
import java.util.List;

/**
 * One section's staged outcome: the built value, and every problem found building it.
 *
 * <p>A staged value is never live. Nothing here touches an {@code active} field, so the whole
 * preparation pass can run on the reload's background executor without an observer ever seeing half
 * a bundle. A failed entry is never turned into an accepted partial section: it is recorded, and the
 * section is accepted or refused as one thing.
 */
public record StagingResult<T>(boolean accepted, T value, List<ContentProblem> problems) {

    public StagingResult {
        problems = problems == null ? List.of() : List.copyOf(problems);
    }

    public static <T> StagingResult<T> accepted(T value, List<ContentProblem> problems) {
        return new StagingResult<>(true, value, problems);
    }

    public static <T> StagingResult<T> refused(List<ContentProblem> problems) {
        return new StagingResult<>(false, null, problems);
    }

    /** True when at least one recorded problem forbids publication. */
    public boolean fatal() {
        return !accepted || problems.stream().anyMatch(p -> p.severity().fatal());
    }

    /** The same result with every problem stamped with the owning attempt. */
    public StagingResult<T> stamped(long attempt, long generation) {
        List<ContentProblem> out = new ArrayList<>(problems.size());
        problems.forEach(p -> out.add(p.stamped(attempt, generation)));
        return new StagingResult<>(accepted, value, out);
    }
}
