package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.history.EpisodeState;
import dev.otectus.mcaconversations.history.NarrativeValue;
import dev.otectus.mcaconversations.history.PrivacyLevel;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * The optional v2 half of a beat contract: the discourse frame and its typed slots (spec §10.1).
 *
 * <p>Kept as its own type rather than as ten more components on {@link BeatContract} for two reasons.
 * It is <b>optional</b> — every 1.4.0 beat has none and behaves exactly as before, which is what makes
 * the upgrade non-breaking for datapacks (spec §22.3). And it is <b>cohesive</b> — the frame, its
 * referents and its slots are only ever read together, by the lints and by the slot binder.
 *
 * @param predicate     what is under discussion
 * @param temporal      when it takes place
 * @param epistemic     on what footing it is asserted
 * @param privacy       how freely the information may be repeated
 * @param obligations   what kind of response the line makes relevant
 * @param referents     alias to source, e.g. {@code "problem" -> "slot:problem"}; a reply may only
 *                      presuppose an alias that appears here
 * @param requiredSlots slot names that must bind before this beat may be selected
 * @param episodeStates episode states in which this beat tells the truth; empty means any
 * @param shape         the rhetorical form, for repetition suppression
 * @param consumes      thread obligations or memory mentions this beat discharges
 * @param produces      typed facts this beat establishes, independent of translated wording
 */
public record DiscourseSpec(Optional<DiscourseFrame> predicate,
                            TemporalFrame temporal,
                            Epistemic epistemic,
                            PrivacyLevel privacy,
                            Set<Obligation> obligations,
                            Map<String, String> referents,
                            Set<String> requiredSlots,
                            Set<EpisodeState> episodeStates,
                            Optional<SceneShape> shape,
                            Set<String> consumes,
                            Map<String, NarrativeValue> produces) {

    /** What a v1 beat is treated as: present-tense, observed, ordinary, obliging nothing. */
    public static final DiscourseSpec V1_DEFAULT = new DiscourseSpec(
            Optional.empty(), TemporalFrame.CURRENT, Epistemic.OBSERVED, PrivacyLevel.ORDINARY,
            Set.of(Obligation.NONE), Map.of(), Set.of(), Set.of(), Optional.empty(),
            Set.of(), Map.of());

    public DiscourseSpec {
        temporal = temporal == null ? TemporalFrame.CURRENT : temporal;
        epistemic = epistemic == null ? Epistemic.OBSERVED : epistemic;
        privacy = privacy == null ? PrivacyLevel.ORDINARY : privacy;
        obligations = obligations == null || obligations.isEmpty()
                ? Set.of(Obligation.NONE) : Set.copyOf(obligations);
        referents = referents == null ? Map.of() : Map.copyOf(referents);
        requiredSlots = requiredSlots == null ? Set.of() : Set.copyOf(requiredSlots);
        episodeStates = episodeStates == null ? Set.of() : Set.copyOf(episodeStates);
        consumes = consumes == null ? Set.of() : Set.copyOf(consumes);
        produces = produces == null ? Map.of() : Map.copyOf(produces);
        predicate = predicate == null ? Optional.empty() : predicate;
        shape = shape == null ? Optional.empty() : shape;
    }

    /** True when this is a real v2 frame rather than the default a v1 beat is read as. */
    public boolean isDeclared() {
        return predicate.isPresent();
    }

    /** True when this beat tells the truth about an episode in {@code state}. */
    public boolean fitsEpisodeState(EpisodeState state) {
        if (state == null) {
            return episodeStates.isEmpty();
        }
        if (!episodeStates.isEmpty() && !episodeStates.contains(state)) {
            return false;
        }
        return temporal.fits(state);
    }

    /** True when {@code alias} is a referent this beat introduces, and a reply may therefore use it. */
    public boolean introduces(String alias) {
        return alias != null && referents.containsKey(alias.trim().toLowerCase(Locale.ROOT));
    }

    /** True when this beat makes {@code obligation} relevant. */
    public boolean obliges(Obligation obligation) {
        return obligation != null && obligations.contains(obligation);
    }

    /** True when at least one declared obligation actually needs a reply to fulfil it. */
    public boolean demandsFulfilment() {
        for (Obligation obligation : obligations) {
            if (obligation.requiresFulfilment()) {
                return true;
            }
        }
        return predicate.map(DiscourseFrame::demandsResponse).orElse(false);
    }

    /**
     * Parses the optional {@code "frame"} object of a beat definition.
     *
     * <p>Returns {@link #V1_DEFAULT} when there is none, so every caller reads a non-null spec and no
     * existing content changes behaviour by acquiring one.
     */
    public static DiscourseSpec fromJson(JsonObject json, String beatId) {
        if (json == null) {
            return V1_DEFAULT;
        }
        Optional<DiscourseFrame> predicate = Optional.empty();
        if (json.has("predicate")) {
            String key = json.get("predicate").getAsString();
            predicate = Optional.of(DiscourseFrame.byKey(key).orElseThrow(() ->
                    new IllegalArgumentException("beat '" + beatId + "' frame predicate '" + key
                            + "' is unknown")));
        }

        TemporalFrame temporal = json.has("temporal")
                ? TemporalFrame.byKey(json.get("temporal").getAsString()).orElseThrow(() ->
                        new IllegalArgumentException("beat '" + beatId + "' temporal frame '"
                                + json.get("temporal").getAsString() + "' is unknown"))
                : TemporalFrame.CURRENT;

        Epistemic epistemic = json.has("epistemic")
                ? Epistemic.byKey(json.get("epistemic").getAsString()).orElseThrow(() ->
                        new IllegalArgumentException("beat '" + beatId + "' epistemic '"
                                + json.get("epistemic").getAsString() + "' is unknown"))
                : Epistemic.OBSERVED;

        PrivacyLevel privacy = json.has("privacy")
                ? PrivacyLevel.byKey(json.get("privacy").getAsString()).orElseThrow(() ->
                        new IllegalArgumentException("beat '" + beatId + "' privacy '"
                                + json.get("privacy").getAsString() + "' is unknown"))
                : PrivacyLevel.ORDINARY;

        Set<Obligation> obligations = new LinkedHashSet<>();
        for (String key : BeatContract.strings(json, "obligations")) {
            obligations.add(Obligation.byKey(key).orElseThrow(() -> new IllegalArgumentException(
                    "beat '" + beatId + "' obligation '" + key + "' is unknown")));
        }

        Map<String, String> referents = new LinkedHashMap<>();
        if (json.has("referents") && json.get("referents").isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject("referents").entrySet()) {
                referents.put(entry.getKey().trim().toLowerCase(Locale.ROOT),
                        entry.getValue().getAsString().trim());
            }
        }

        Set<EpisodeState> states = new LinkedHashSet<>();
        for (String key : BeatContract.strings(json, "episode_states")) {
            states.add(EpisodeState.byKey(key).orElseThrow(() -> new IllegalArgumentException(
                    "beat '" + beatId + "' episode state '" + key + "' is unknown")));
        }

        Optional<SceneShape> shape = Optional.empty();
        if (json.has("shape")) {
            String key = json.get("shape").getAsString();
            shape = Optional.of(SceneShape.byKey(key).orElseThrow(() -> new IllegalArgumentException(
                    "beat '" + beatId + "' shape '" + key + "' is unknown")));
        }

        Map<String, NarrativeValue> produces = new LinkedHashMap<>();
        if (json.has("produces") && json.get("produces").isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject("produces").entrySet()) {
                NarrativeValue value = NarrativeValue.parse(entry.getValue().getAsString());
                if (value.isEmpty()) {
                    throw new IllegalArgumentException("beat '" + beatId + "' produces '"
                            + entry.getKey() + "' with an unparseable value");
                }
                produces.put(entry.getKey().trim().toLowerCase(Locale.ROOT), value);
            }
        }

        DiscourseSpec spec = new DiscourseSpec(predicate, temporal, epistemic, privacy, obligations,
                referents, BeatContract.strings(json, "slots"), states, shape,
                BeatContract.strings(json, "consumes"), produces);

        // A frame that claims observation but names no source is exactly the claim §10.3 forbids;
        // catching it at parse means the content lint has nothing left to find at build time.
        if (spec.epistemic().requiresSource() && !json.has("source")
                && !spec.referents().containsKey("source")) {
            throw new IllegalArgumentException("beat '" + beatId + "' is " + spec.epistemic().key()
                    + " but names no source referent; a reported or rumoured line must be able to say "
                    + "who said it, even if the answer is an explicit anonymous token");
        }
        return spec;
    }
}
