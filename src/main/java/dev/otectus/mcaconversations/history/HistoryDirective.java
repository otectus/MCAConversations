package dev.otectus.mcaconversations.history;

import com.google.gson.JsonObject;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * The parsed write-side of the living-history dialogue actions (spec §10.6).
 *
 * <p>Every directive names an <b>authored template</b> rather than a shape. A result cannot invent a
 * new episode kind, a new thread or an unregistered promise from JSON: it can only instantiate
 * something the narrative catalog already declares. That is what keeps runtime state and authored
 * content in step — and what makes {@code NoUntrackablePromiseLintTest} possible at all, since there
 * is a finite set of promises to check (spec §8.5).
 *
 * <p>Fail-closed, like the queries: an unparseable directive is a no-op, never a partial write.
 */
public final class HistoryDirective {

    private HistoryDirective() {
    }

    /**
     * {@code conversations_episode: {op, kind, state?, slots?}}.
     *
     * <p>{@code op} is {@code open}, {@code advance} or {@code witness}. Opening an episode that is
     * already live resumes it rather than creating a second, which is what keeps a villager's working
     * life singular (spec §12.2).
     */
    public record Episode(Op op, String kind, Optional<EpisodeState> state,
                          Map<String, NarrativeValue> slots) {

        public enum Op { OPEN, ADVANCE, WITNESS, CORRECT, INVALID }

        public static final Episode INVALID =
                new Episode(Op.INVALID, "", Optional.empty(), Map.of());

        public Episode {
            kind = normalize(kind);
            slots = Map.copyOf(slots);
        }

        public boolean isValid() {
            return op != Op.INVALID && !kind.isEmpty()
                    && (op != Op.ADVANCE || state.isPresent());
        }

        public static Episode fromJson(JsonObject json) {
            if (json == null || !json.has("kind")) {
                return INVALID;
            }
            Op op = switch (json.has("op") ? json.get("op").getAsString().trim().toLowerCase(Locale.ROOT) : "open") {
                case "open" -> Op.OPEN;
                case "advance" -> Op.ADVANCE;
                case "witness" -> Op.WITNESS;
                // A correction addresses the event the villager already holds, by its own id, so a
                // story that reached them second-hand can be set right rather than replaced by a
                // second copy of the same event (spec §16.4 point 8).
                case "correct" -> Op.CORRECT;
                default -> Op.INVALID;
            };
            Optional<EpisodeState> state = json.has("state")
                    ? EpisodeState.byKey(json.get("state").getAsString())
                    : Optional.empty();
            if (op == Op.ADVANCE && state.isEmpty()) {
                return INVALID;
            }
            Map<String, NarrativeValue> slots = new LinkedHashMap<>();
            if (json.has("slots") && json.get("slots").isJsonObject()) {
                json.getAsJsonObject("slots").entrySet().forEach(entry -> {
                    NarrativeValue value = NarrativeValue.parse(entry.getValue().getAsString());
                    if (!value.isEmpty()) {
                        slots.put(entry.getKey().trim().toLowerCase(Locale.ROOT), value);
                    }
                });
            }
            return new Episode(op, json.get("kind").getAsString(), state, slots);
        }
    }

    /** {@code conversations_thread: {op, template, status?, obligation?, cooldown_days?}}. */
    public record Thread(Op op, String templateId, Optional<ThreadStatus> status,
                         String obligation, long cooldownDays) {

        public enum Op { OPEN, ADVANCE, RESOLVE, LAPSE, RUPTURE, PLAYED, INVALID }

        public static final Thread INVALID = new Thread(Op.INVALID, "", Optional.empty(), "", 0L);

        public Thread {
            templateId = normalize(templateId);
            obligation = obligation == null ? "" : obligation.trim().toLowerCase(Locale.ROOT);
        }

        public boolean isValid() {
            return op != Op.INVALID && !templateId.isEmpty();
        }

        /** The status this directive moves the thread to, derived from its op. */
        public Optional<ThreadStatus> targetStatus() {
            return switch (op) {
                case RESOLVE -> Optional.of(ThreadStatus.RESOLVED);
                case LAPSE -> Optional.of(ThreadStatus.LAPSED);
                case RUPTURE -> Optional.of(ThreadStatus.RUPTURED);
                case ADVANCE -> status;
                default -> Optional.empty();
            };
        }

        public static Thread fromJson(JsonObject json) {
            if (json == null || !json.has("template")) {
                return INVALID;
            }
            Op op = switch (json.has("op") ? json.get("op").getAsString().trim().toLowerCase(Locale.ROOT) : "open") {
                case "open" -> Op.OPEN;
                case "advance" -> Op.ADVANCE;
                case "resolve" -> Op.RESOLVE;
                case "lapse" -> Op.LAPSE;
                case "rupture" -> Op.RUPTURE;
                case "played" -> Op.PLAYED;
                default -> Op.INVALID;
            };
            Optional<ThreadStatus> status = json.has("status")
                    ? ThreadStatus.byKey(json.get("status").getAsString())
                    : Optional.empty();
            if (op == Op.ADVANCE && status.isEmpty()) {
                return INVALID;
            }
            return new Thread(op, json.get("template").getAsString(), status,
                    json.has("obligation") ? json.get("obligation").getAsString() : "",
                    json.has("cooldown_days") ? json.get("cooldown_days").getAsLong() : 1L);
        }
    }

    /** {@code conversations_commitment: {op, id, outcome?}}. */
    public record Commitment(Op op, String id, Optional<CommitmentRecord.State> outcome) {

        public enum Op { MAKE, RESOLVE, INVALID }

        public static final Commitment INVALID = new Commitment(Op.INVALID, "", Optional.empty());

        public Commitment {
            id = normalize(id);
        }

        public boolean isValid() {
            return op != Op.INVALID && !id.isEmpty() && (op != Op.RESOLVE || outcome.isPresent());
        }

        public static Commitment fromJson(JsonObject json) {
            if (json == null || !json.has("id")) {
                return INVALID;
            }
            Op op = switch (json.has("op") ? json.get("op").getAsString().trim().toLowerCase(Locale.ROOT) : "make") {
                case "make" -> Op.MAKE;
                case "resolve" -> Op.RESOLVE;
                default -> Op.INVALID;
            };
            Optional<CommitmentRecord.State> outcome = json.has("outcome")
                    ? CommitmentRecord.State.byKey(json.get("outcome").getAsString())
                    : Optional.empty();
            if (op == Op.RESOLVE && outcome.isEmpty()) {
                return INVALID;
            }
            return new Commitment(op, json.get("id").getAsString(), outcome);
        }
    }

    /**
     * {@code conversations_claim: {op, type, value?, source}}.
     *
     * <p>{@code source} is the {@code question/answer} that introduced the claim, and it is required
     * for a {@code record} op. Without provenance there is no claim: the whole safety of storing
     * anything a player said rests on being able to point at the button they clicked (spec §8.6).
     */
    public record Claim(Op op, String type, NarrativeValue value, String source) {

        public enum Op { RECORD, CLARIFY, INVALID }

        public static final Claim INVALID =
                new Claim(Op.INVALID, "", NarrativeValue.EMPTY, "");

        public Claim {
            type = normalize(type);
            source = source == null ? "" : source.trim();
            value = value == null ? NarrativeValue.EMPTY : value;
        }

        public boolean isValid() {
            if (op == Op.INVALID || type.isEmpty()) {
                return false;
            }
            return op != Op.RECORD || (!value.isEmpty() && !source.isEmpty());
        }

        public static Claim fromJson(JsonObject json) {
            if (json == null || !json.has("type")) {
                return INVALID;
            }
            Op op = switch (json.has("op") ? json.get("op").getAsString().trim().toLowerCase(Locale.ROOT) : "record") {
                case "record" -> Op.RECORD;
                case "clarify" -> Op.CLARIFY;
                default -> Op.INVALID;
            };
            return new Claim(op, json.get("type").getAsString(),
                    json.has("value") ? NarrativeValue.parse(json.get("value").getAsString())
                            : NarrativeValue.EMPTY,
                    json.has("source") ? json.get("source").getAsString() : "");
        }
    }

    /**
     * {@code conversations_opinion: {axis, target, delta, cause, privacy?, expires_days?}}.
     *
     * <p>{@code cause} is required. An opinion with no cause could only ever produce "I don't like
     * them", which is the generic drama §16.2 rules out, so the parser refuses one.
     */
    public record Opinion(String axis, String targetSlot, int delta, String cause,
                          PrivacyLevel privacy, java.util.OptionalLong expiresDays) {

        public static final Opinion INVALID = new Opinion("", "", 0, "",
                PrivacyLevel.DISCREET, java.util.OptionalLong.empty());

        public Opinion {
            axis = normalize(axis);
            targetSlot = normalize(targetSlot);
            cause = normalize(cause);
            privacy = privacy == null ? PrivacyLevel.DISCREET : privacy;
            expiresDays = expiresDays == null ? java.util.OptionalLong.empty() : expiresDays;
        }

        public boolean isValid() {
            return SocialOpinionRecord.AXES.contains(axis) && !cause.isEmpty()
                    && !targetSlot.isEmpty() && delta != 0;
        }

        public static Opinion fromJson(JsonObject json) {
            if (json == null || !json.has("axis") || !json.has("cause") || !json.has("target")) {
                return INVALID;
            }
            return new Opinion(json.get("axis").getAsString(), json.get("target").getAsString(),
                    json.has("delta") ? json.get("delta").getAsInt() : 0,
                    json.get("cause").getAsString(),
                    json.has("privacy")
                            ? PrivacyLevel.byKey(json.get("privacy").getAsString())
                                    .orElse(PrivacyLevel.DISCREET)
                            : PrivacyLevel.DISCREET,
                    json.has("expires_days")
                            ? java.util.OptionalLong.of(json.get("expires_days").getAsLong())
                            : java.util.OptionalLong.empty());
        }
    }

    /**
     * {@code conversations_role: {role, target, cause, expires_days?, withdraw?}}.
     *
     * <p>{@code cause} is required for the same reason it is on an opinion: a role nobody can
     * account for is the "random rival" §16.2 rules out. {@code withdraw} is the other half of the
     * persistence policy — a role that lasts until it is ended has to be endable, or a villager
     * introduces somebody as their apprentice ten years after they left.
     */
    public record Role(SocialRole role, String targetSlot, String cause,
                       java.util.OptionalLong expiresDays, boolean withdraw) {

        public static final Role INVALID = new Role(null, "", "", java.util.OptionalLong.empty(), false);

        public Role {
            targetSlot = normalize(targetSlot);
            cause = normalize(cause);
            expiresDays = expiresDays == null ? java.util.OptionalLong.empty() : expiresDays;
        }

        public boolean isValid() {
            return role != null && !targetSlot.isEmpty() && (withdraw || !cause.isEmpty());
        }

        public static Role fromJson(JsonObject json) {
            if (json == null || !json.has("role") || !json.has("target")) {
                return INVALID;
            }
            return new Role(
                    SocialRole.byKey(json.get("role").getAsString()).orElse(null),
                    json.get("target").getAsString(),
                    json.has("cause") ? json.get("cause").getAsString() : "",
                    json.has("expires_days")
                            ? java.util.OptionalLong.of(json.get("expires_days").getAsLong())
                            : java.util.OptionalLong.empty(),
                    json.has("withdraw") && json.get("withdraw").getAsBoolean());
        }
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
