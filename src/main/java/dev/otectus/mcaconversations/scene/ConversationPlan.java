package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.context.ContextFingerprint;
import dev.otectus.mcaconversations.history.NarrativeValue;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * A frozen decision: this scene, this route, these bound facts (spec §10.5).
 *
 * <p>The plan is what makes reroll resistance real. Once it exists, closing and reopening the screen,
 * switching between the GUI and chat, changing language, or reconnecting all reuse it — none of them
 * re-runs selection, so none of them can quietly produce a different conversation (spec §9.3).
 *
 * <p>It is also what pins referents. The slots bound here are the ones the whole exchange uses; a
 * later turn never re-binds them, so a named person cannot become a different named person between
 * one line and the next. If a pinned referent stops being valid, the scene ends through its declared
 * fallback rather than retargeting (spec §7.4).
 *
 * @param sceneId       the scene that was chosen
 * @param questionId    the contracted MCA question it routes to
 * @param openingBeatId the beat that opens it
 * @param slots         the bound typed values, pinned for the life of the scene
 * @param episodeId     the episode it is about, when it is about one
 * @param threadId      the thread it belongs to, when it belongs to one
 * @param context       the fingerprint of the world it was chosen in, for staleness checks
 * @param nonce         a short stable token stored on any thread this plan opens, so a resumed thread
 *                      can be tied back to the decision that started it
 * @param explanation   why this and not the others
 */
public record ConversationPlan(String sceneId,
                               String questionId,
                               String openingBeatId,
                               Map<String, NarrativeValue> slots,
                               Optional<UUID> episodeId,
                               Optional<String> threadId,
                               ContextFingerprint context,
                               String nonce,
                               SelectionExplanation explanation) {

    public ConversationPlan {
        slots = slots == null ? Map.of() : Map.copyOf(slots);
        episodeId = episodeId == null ? Optional.empty() : episodeId;
        threadId = threadId == null ? Optional.empty() : threadId;
        context = context == null ? ContextFingerprint.EMPTY : context;
        nonce = nonce == null ? "" : nonce;
    }

    public Optional<NarrativeValue> slot(String name) {
        return name == null ? Optional.empty() : Optional.ofNullable(slots.get(name));
    }

    /**
     * True when the world has drifted far enough that this plan should be re-made.
     *
     * <p>Compared against the <em>pinned</em> half of the context only, because the fingerprint is
     * computed over pinned fields alone. That is deliberate: the time band ticking over mid-exchange
     * must not invalidate a plan, and a villager changing profession mid-exchange should.
     */
    public boolean isStale(ContextFingerprint current) {
        return current != null && !current.equals(context);
    }

    /** How the plan appears in a trace: enough to identify it, short enough to read. */
    @Override
    public String toString() {
        return "plan[" + sceneId + " -> " + questionId + "/" + openingBeatId
                + " slots=" + slots.size() + " fp=" + context.hex() + "]";
    }
}
