package dev.otectus.mcaconversations.history;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * The episode, thread and commitment templates the running build knows about (spec §22.3).
 *
 * <p>Held as one catalog even though the three are loaded from three data directories, because they
 * are validated against each other: a thread that binds an episode kind nobody declares, or a
 * commitment that names a thread nobody declares, is a dangling reference the build should be able to
 * see in one place.
 *
 * <p>Immutable once built and swapped atomically on reload, so a conversation in progress keeps the
 * catalog it started with rather than half of two.
 */
public final class NarrativeCatalog {

    public static final NarrativeCatalog EMPTY = new NarrativeCatalog(List.of(), List.of(), List.of());

    private final Map<String, EpisodeTemplate> episodes;
    private final Map<String, ThreadTemplate> threads;
    private final Map<String, CommitmentTemplate> commitments;

    private NarrativeCatalog(Collection<EpisodeTemplate> episodes,
                             Collection<ThreadTemplate> threads,
                             Collection<CommitmentTemplate> commitments) {
        Map<String, EpisodeTemplate> episodeMap = new TreeMap<>();
        episodes.forEach(template -> episodeMap.put(template.kind(), template));
        Map<String, ThreadTemplate> threadMap = new TreeMap<>();
        threads.forEach(template -> threadMap.put(template.id(), template));
        Map<String, CommitmentTemplate> commitmentMap = new TreeMap<>();
        commitments.forEach(template -> commitmentMap.put(template.id(), template));
        this.episodes = Map.copyOf(episodeMap);
        this.threads = Map.copyOf(threadMap);
        this.commitments = Map.copyOf(commitmentMap);
    }

    public static NarrativeCatalog build(Collection<EpisodeTemplate> episodes,
                                         Collection<ThreadTemplate> threads,
                                         Collection<CommitmentTemplate> commitments) {
        Map<String, String> seen = new LinkedHashMap<>();
        for (EpisodeTemplate template : episodes) {
            if (seen.put("episode:" + template.kind(), template.kind()) != null) {
                throw new IllegalArgumentException("duplicate episode template '" + template.kind() + "'");
            }
        }
        for (ThreadTemplate template : threads) {
            if (seen.put("thread:" + template.id(), template.id()) != null) {
                throw new IllegalArgumentException("duplicate thread template '" + template.id() + "'");
            }
        }
        for (CommitmentTemplate template : commitments) {
            if (seen.put("commitment:" + template.id(), template.id()) != null) {
                throw new IllegalArgumentException("duplicate commitment template '" + template.id() + "'");
            }
        }
        return new NarrativeCatalog(episodes, threads, commitments);
    }

    public Optional<EpisodeTemplate> episode(String kind) {
        return kind == null ? Optional.empty()
                : Optional.ofNullable(episodes.get(kind.trim().toLowerCase(Locale.ROOT)));
    }

    public Optional<ThreadTemplate> thread(String id) {
        return id == null ? Optional.empty()
                : Optional.ofNullable(threads.get(id.trim().toLowerCase(Locale.ROOT)));
    }

    public Optional<CommitmentTemplate> commitment(String id) {
        return id == null ? Optional.empty()
                : Optional.ofNullable(commitments.get(id.trim().toLowerCase(Locale.ROOT)));
    }

    /** Every template, in id order; Map.copyOf makes no ordering promise. */
    public List<EpisodeTemplate> episodes() {
        List<EpisodeTemplate> out = new java.util.ArrayList<>(episodes.values());
        out.sort(java.util.Comparator.comparing(EpisodeTemplate::kind));
        return List.copyOf(out);
    }

    /** Every template, in id order; Map.copyOf makes no ordering promise. */
    public List<ThreadTemplate> threads() {
        List<ThreadTemplate> out = new java.util.ArrayList<>(threads.values());
        out.sort(java.util.Comparator.comparing(ThreadTemplate::id));
        return List.copyOf(out);
    }

    /** Every template, in id order; Map.copyOf makes no ordering promise. */
    public List<CommitmentTemplate> commitments() {
        List<CommitmentTemplate> out = new java.util.ArrayList<>(commitments.values());
        out.sort(java.util.Comparator.comparing(CommitmentTemplate::id));
        return List.copyOf(out);
    }

    public boolean isEmpty() {
        return episodes.isEmpty() && threads.isEmpty() && commitments.isEmpty();
    }

    public int size() {
        return episodes.size() + threads.size() + commitments.size();
    }

    /**
     * Cross-references that could not be checked while parsing one file at a time.
     *
     * <p>Returned as a list rather than thrown, so a reload logs every dangling reference at once
     * instead of stopping at the first — and so the content test can assert the list is empty for the
     * shipped corpus while a player's broken datapack merely warns.
     */
    public List<String> danglingReferences() {
        List<String> problems = new java.util.ArrayList<>();
        for (ThreadTemplate thread : threads.values()) {
            if (thread.needsEpisode() && episode(thread.episodeKind()).isEmpty()) {
                problems.add("thread '" + thread.id() + "' binds unknown episode kind '"
                        + thread.episodeKind() + "'");
            }
        }
        for (CommitmentTemplate commitment : commitments.values()) {
            if (!commitment.threadId().isEmpty() && thread(commitment.threadId()).isEmpty()) {
                problems.add("commitment '" + commitment.id() + "' names unknown thread '"
                        + commitment.threadId() + "'");
            }
        }
        return List.copyOf(problems);
    }
}
