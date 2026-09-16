package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.NarrativeCatalog;
import dev.otectus.mcaconversations.history.PairHistory;
import dev.otectus.mcaconversations.history.SharedThreadRecord;
import dev.otectus.mcaconversations.history.ThreadTemplate;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

/**
 * Which subject this pair may safely pick up again, and where picking it up is allowed to land
 * (spec §8.4, §11.5).
 *
 * <p>Coming back to a conversation is an <em>authored</em> moment. A thread stores the frame — the
 * subject, the stance, what is outstanding — and never a screen, so there is nothing here that could
 * replay the starter the player already answered or restore a response index they had highlighted.
 * What this resolves is a thread whose {@link ThreadTemplate#resumeScenes() resume scenes} still
 * exist in the content this operation is pinned to; the scene itself is the decision boundary the
 * author wrote for returning, and it is entered like any other scene, through the director's full
 * gate stack.
 *
 * <h2>What is revalidated here</h2>
 *
 * <p>Everything that can be answered from the pair's own records and the pinned bundle: readiness and
 * cooldown, the resume budget, lapsing, the template still existing, the template still describing
 * the same subject under the same topic, at least one authored resume scene still being in the scene
 * catalog, and the bound episode still being retained, unexpired and in a state the resume scene accepts.
 *
 * <p>Everything else is revalidated where it already lives, and is deliberately not duplicated:
 * speaker availability and relationship, age and topic visibility by the hub's age filter and the
 * catalog's own gates, privacy by the hub's offerability rule, generation by the
 * {@link dev.otectus.mcaconversations.conversation.ContentOperation} pin every entry point opens, and
 * slot binding, recency and context conditions by {@link ConversationDirector}. A continuation that
 * cannot establish its state is declined rather than guessed: nothing here infers that a check
 * succeeded, that a consequence landed, or that an observation was made.
 */
public final class ContinuationResolver {

    private ContinuationResolver() {
    }

    /** How a villager and a player may pick a subject back up, resolved against current content. */
    public record Continuation(SharedThreadRecord thread, ThreadTemplate template,
                               List<String> resumeScenes) {

        public Continuation {
            resumeScenes = List.copyOf(resumeScenes);
        }

        /** The hub topic this continuation belongs under. */
        public String topic() {
            return template.topic();
        }

        /** True when something is outstanding that the villager may legitimately raise. */
        public boolean hasObligation() {
            return thread.hasObligation();
        }
    }

    /** Looks an episode up by id; the resolver never reaches into the world itself. */
    public interface Episodes extends Function<UUID, Optional<EpisodeRecord>> {
    }

    /** An episode lookup for a pair whose threads bind to nothing. */
    public static final Episodes NO_EPISODES = id -> Optional.empty();

    /**
     * Every subject this pair may continue today, most claim first.
     *
     * <p>Order is a claim order, not a preference: a thread waiting on something outstanding comes
     * before one that is merely unfinished, and within a class the pair's own readiness order (most
     * recently active first) is kept.
     */
    public static List<Continuation> resolve(PairHistory pair, NarrativeCatalog narrative,
                                             SceneCatalog scenes, Episodes episodes, long today) {
        if (pair == null || narrative == null || scenes == null) {
            return List.of();
        }
        Episodes lookup = episodes == null ? NO_EPISODES : episodes;
        List<Continuation> outstanding = new ArrayList<>();
        List<Continuation> unfinished = new ArrayList<>();
        for (SharedThreadRecord thread : pair.resumable(today)) {
            Continuation continuation = continuationOf(thread, narrative, scenes, lookup, today);
            if (continuation == null) {
                continue;
            }
            (continuation.hasObligation() ? outstanding : unfinished).add(continuation);
        }
        outstanding.addAll(unfinished);
        return List.copyOf(outstanding);
    }

    /** The one subject the hub would offer to continue, if any. */
    public static Optional<Continuation> best(PairHistory pair, NarrativeCatalog narrative,
                                              SceneCatalog scenes, Episodes episodes, long today) {
        List<Continuation> all = resolve(pair, narrative, scenes, episodes, today);
        return all.isEmpty() ? Optional.empty() : Optional.of(all.get(0));
    }

    /**
     * The threads behind {@link #resolve}, for a caller that speaks in records.
     *
     * <p>This is what the hub offers: a ready thread that has been dropped here is one the player
     * would have been invited to continue into content that no longer exists.
     */
    public static List<SharedThreadRecord> continuable(PairHistory pair, NarrativeCatalog narrative,
                                                       SceneCatalog scenes, Episodes episodes,
                                                       long today) {
        List<SharedThreadRecord> out = new ArrayList<>();
        for (Continuation continuation : resolve(pair, narrative, scenes, episodes, today)) {
            out.add(continuation.thread());
        }
        return List.copyOf(out);
    }

    /** The authored scene ids every live continuation could land on, in claim order. */
    public static Set<String> resumeSceneIds(List<Continuation> continuations) {
        Set<String> ids = new LinkedHashSet<>();
        if (continuations != null) {
            for (Continuation continuation : continuations) {
                ids.addAll(continuation.resumeScenes());
            }
        }
        return Set.copyOf(ids);
    }

    /**
     * One thread, revalidated against the pinned content.
     *
     * @return the continuation, or null when this thread has no honest way back today
     */
    private static Continuation continuationOf(SharedThreadRecord thread, NarrativeCatalog narrative,
                                               SceneCatalog scenes, Episodes episodes, long today) {
        if (thread == null || !thread.isReady(today)) {
            return null;
        }
        ThreadTemplate template = narrative.thread(thread.templateId()).orElse(null);
        if (template == null || !thread.agreesWith(template)) {
            // The template was removed or now describes a different subject. Continuing would raise
            // the wrong thing under the wrong heading, so the pair simply does not continue this one.
            return null;
        }
        Optional<EpisodeRecord> episode = thread.episodeId().flatMap(episodes);
        if (template.needsEpisode()) {
            if (episode.isEmpty() || episode.get().hasExpired(today)
                    || !episode.get().kind().equals(template.episodeKind())) {
                return null;
            }
        }
        // An authored boundary that is still in the catalog. The scene last played on this thread is
        // kept as a last resort rather than dropped: it may be the only way back, but a resume that
        // has another option must not open on the page the player has already seen.
        List<String> fresh = new ArrayList<>();
        List<String> replayed = new ArrayList<>();
        for (String sceneId : template.resumeScenes()) {
            SceneDefinition scene = scenes.scene(sceneId).orElse(null);
            if (scene == null || !scene.topic().equals(template.topic())
                    || scene.needsEpisode() && (episode.isEmpty()
                        || !scene.episodeKind().equals(episode.get().kind())
                        || !scene.episodeStates().contains(episode.get().state()))) {
                continue;
            }
            (sceneId.equals(thread.lastScene()) ? replayed : fresh).add(sceneId);
        }
        fresh.addAll(replayed);
        return fresh.isEmpty() ? null : new Continuation(thread, template, fresh);
    }
}
