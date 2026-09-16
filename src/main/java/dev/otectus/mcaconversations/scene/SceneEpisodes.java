package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.PairHistory;
import dev.otectus.mcaconversations.history.SharedThreadRecord;

import java.util.Optional;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

/** Resolves the exact situation a pair is discussing, including completed situations recalled later. */
public final class SceneEpisodes {
    private SceneEpisodes() {
    }

    public static Optional<EpisodeRecord> resolve(SceneDefinition scene, PairHistory pair,
            Function<UUID, Optional<EpisodeRecord>> episodes, Supplier<Optional<EpisodeRecord>> live,
            long today) {
        return resolve(scene, pair, List.of(), episodes, live, today);
    }

    public static Optional<EpisodeRecord> resolve(SceneDefinition scene, PairHistory pair,
            List<ContinuationResolver.Continuation> continuations,
            Function<UUID, Optional<EpisodeRecord>> episodes, Supplier<Optional<EpisodeRecord>> live,
            long today) {
        // An authored resume scene need not open the thread it is continuing.
        Optional<UUID> resumed = continuations.stream()
                .filter(continuation -> continuation.resumeScenes().contains(scene.id()))
                .flatMap(continuation -> continuation.thread().episodeId().stream()).findFirst();
        Optional<UUID> direct = pair == null ? Optional.empty()
                : pair.thread(scene.threadTemplate()).flatMap(SharedThreadRecord::episodeId);
        Optional<EpisodeRecord> bound = resumed.or(() -> direct)
                        .flatMap(episodes).filter(episode -> !episode.hasExpired(today)
                                && episode.kind().equals(scene.episodeKind()));
        if (bound.isPresent()) {
            // Authoritative even when the state does not fit: another episode of the same kind
            // cannot substitute for the one these two actually discussed.
            return bound.filter(episode -> scene.episodeStates().contains(episode.state()));
        }
        // A past-tense callback needs this pair's record. A new player cannot inherit somebody
        // else's advice merely because the villager has completed an episode of that kind.
        return live.get().filter(episode -> episode.isLive(today)
                && scene.episodeStates().contains(episode.state()));
    }
}
