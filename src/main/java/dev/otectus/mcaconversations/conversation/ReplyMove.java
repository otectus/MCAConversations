package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.history.NarrativeValue;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * The optional v2 half of a reply contract: what this button actually does to the exchange
 * (spec §10.2).
 *
 * <p>v1 already records the reply's stance, the facts it presupposes and the outcomes it may produce.
 * What it cannot record is whether the button <em>answers the question that was asked</em>, which
 * referents its wording takes for granted, what the player is claiming about themselves by choosing
 * it, or which promise it makes. Those four are the difference between a page of permitted stances and
 * a page that actually responds.
 *
 * @param answers      obligations this reply fulfils; empty with a {@link #move} is also legal
 * @param move         the permitted topic move it performs instead of answering
 * @param targetsFrame the predicate it addresses, when the inbound page carries several
 * @param usesReferents aliases its wording presupposes; each must be introduced by every inbound beat
 * @param claimType    the player self-report family this choice records, if any
 * @param claimValue   the claimed token
 * @param commitment   the commitment template this choice creates, if any
 * @param epistemicMove what it does with the villager's certainty
 * @param privacyMove  what it does with the villager's boundary
 * @param temporalMove where it takes the conversation in time
 */
public record ReplyMove(Set<Obligation> answers,
                        Optional<Obligation.Move> move,
                        Optional<DiscourseFrame> targetsFrame,
                        Set<String> usesReferents,
                        String claimType,
                        NarrativeValue claimValue,
                        String commitment,
                        Optional<EpistemicMove> epistemicMove,
                        Optional<PrivacyMove> privacyMove,
                        Optional<TemporalMove> temporalMove) {

    /** What a reply does with a claim's certainty (spec §10.2). */
    public enum EpistemicMove {
        BELIEVE("believe"),
        DOUBT("doubt"),
        ASK_SOURCE("ask_source"),
        SUSPEND_JUDGMENT("suspend_judgment"),
        CORRECT("correct"),
        WITHHOLD("withhold");

        private final String key;

        EpistemicMove(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }

        public static Optional<EpistemicMove> byKey(String key) {
            return lookup(values(), key, EpistemicMove::key);
        }
    }

    /** What a reply does with a boundary (spec §10.2, §4.5). */
    public enum PrivacyMove {
        KEEP_PRIVATE("keep_private"),
        PERMIT_SHARING("permit_sharing"),
        ASK_PERMISSION("ask_permission"),
        /** Deliberately repeating something confidential. Legal only where a breach is authored. */
        PUBLICIZE("publicize");

        private final String key;

        PrivacyMove(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }

        public boolean isBreach() {
            return this == PUBLICIZE;
        }

        public static Optional<PrivacyMove> byKey(String key) {
            return lookup(values(), key, PrivacyMove::key);
        }
    }

    /** Where a reply takes the conversation in time (spec §10.2). */
    public enum TemporalMove {
        ASK_PAST("ask_past"),
        ASK_CURRENT("ask_current"),
        ASK_NEXT("ask_next"),
        DEFER("defer"),
        CLOSE("close");

        private final String key;

        TemporalMove(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }

        public static Optional<TemporalMove> byKey(String key) {
            return lookup(values(), key, TemporalMove::key);
        }
    }

    /** What a v1 reply is treated as: answers nothing in particular, presupposes no referent. */
    public static final ReplyMove V1_DEFAULT = new ReplyMove(Set.of(), Optional.empty(),
            Optional.empty(), Set.of(), "", NarrativeValue.EMPTY, "",
            Optional.empty(), Optional.empty(), Optional.empty());

    public ReplyMove {
        answers = answers == null ? Set.of() : Set.copyOf(answers);
        usesReferents = usesReferents == null ? Set.of() : Set.copyOf(usesReferents);
        claimType = claimType == null ? "" : claimType.trim().toLowerCase(Locale.ROOT);
        claimValue = claimValue == null ? NarrativeValue.EMPTY : claimValue;
        commitment = commitment == null ? "" : commitment.trim().toLowerCase(Locale.ROOT);
        move = move == null ? Optional.empty() : move;
        targetsFrame = targetsFrame == null ? Optional.empty() : targetsFrame;
        epistemicMove = epistemicMove == null ? Optional.empty() : epistemicMove;
        privacyMove = privacyMove == null ? Optional.empty() : privacyMove;
        temporalMove = temporalMove == null ? Optional.empty() : temporalMove;
    }

    /** True when this reply carries declared v2 metadata rather than the v1 default. */
    public boolean isDeclared() {
        return !answers.isEmpty() || move.isPresent() || !claimType.isEmpty() || !commitment.isEmpty()
                || !usesReferents.isEmpty();
    }

    /** True when this reply records something the player said about themselves. */
    public boolean hasClaim() {
        return !claimType.isEmpty() && !claimValue.isEmpty();
    }

    /** True when this reply makes a promise. */
    public boolean hasCommitment() {
        return !commitment.isEmpty();
    }

    /** True when this reply fulfils {@code obligation}. */
    public boolean fulfils(Obligation obligation) {
        return obligation != null && answers.contains(obligation);
    }

    /**
     * True when this reply is a legal thing to put on the page at all.
     *
     * <p>The rule of §10.3: every non-exit reply must fulfil at least one obligation or perform an
     * explicitly permitted topic move. A button that does neither is a comment, and a page of comments
     * after a direct question is the non-sequitur the whole contract system exists to prevent.
     */
    public boolean isResponsive(boolean exit) {
        return exit || !answers.isEmpty() || move.isPresent();
    }

    public static ReplyMove fromJson(JsonObject json, String replyKey) {
        if (json == null) {
            return V1_DEFAULT;
        }
        Set<Obligation> answers = new LinkedHashSet<>();
        for (String key : BeatContract.strings(json, "answers_obligation")) {
            answers.add(Obligation.byKey(key).orElseThrow(() -> new IllegalArgumentException(
                    "reply '" + replyKey + "' answers unknown obligation '" + key + "'")));
        }
        Optional<Obligation.Move> move = Optional.empty();
        if (json.has("move")) {
            String key = json.get("move").getAsString();
            move = Optional.of(Obligation.Move.byKey(key).orElseThrow(() -> new IllegalArgumentException(
                    "reply '" + replyKey + "' move '" + key + "' is unknown")));
        }
        Optional<DiscourseFrame> targets = Optional.empty();
        if (json.has("targets_frame")) {
            String key = json.get("targets_frame").getAsString();
            targets = Optional.of(DiscourseFrame.byKey(key).orElseThrow(() ->
                    new IllegalArgumentException("reply '" + replyKey + "' targets unknown frame '"
                            + key + "'")));
        }

        String claimType = "";
        NarrativeValue claimValue = NarrativeValue.EMPTY;
        if (json.has("claim") && json.get("claim").isJsonObject()) {
            JsonObject claim = json.getAsJsonObject("claim");
            claimType = claim.has("type") ? claim.get("type").getAsString() : "";
            claimValue = claim.has("value")
                    ? NarrativeValue.parse(claim.get("value").getAsString())
                    : NarrativeValue.EMPTY;
            if (claimType.isBlank() || claimValue.isEmpty()) {
                throw new IllegalArgumentException("reply '" + replyKey
                        + "' declares a claim without both a type and a parseable value");
            }
        }

        String commitment = json.has("commitment") ? json.get("commitment").getAsString() : "";

        return new ReplyMove(answers, move, targets,
                BeatContract.strings(json, "uses_referents"),
                claimType, claimValue, commitment,
                optional(json, "epistemic_move", EpistemicMove::byKey, replyKey),
                optional(json, "privacy_move", PrivacyMove::byKey, replyKey),
                optional(json, "temporal_move", TemporalMove::byKey, replyKey));
    }

    private static <T> Optional<T> optional(JsonObject json, String field,
                                            java.util.function.Function<String, Optional<T>> parser,
                                            String replyKey) {
        if (!json.has(field)) {
            return Optional.empty();
        }
        String key = json.get(field).getAsString();
        return Optional.of(parser.apply(key).orElseThrow(() -> new IllegalArgumentException(
                "reply '" + replyKey + "' " + field + " '" + key + "' is unknown")));
    }

    private static <T> Optional<T> lookup(T[] values, String key,
                                          java.util.function.Function<T, String> keyOf) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (T value : values) {
            if (keyOf.apply(value).equals(normalized)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
