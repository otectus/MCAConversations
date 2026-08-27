package dev.otectus.mcaconversations.history;

import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.conversation.SceneShape;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * The parsed read-side of the living-history dialogue conditions (spec §10.6).
 *
 * <p>Six small queries in one file because they are one vocabulary: each is a handful of fields, each
 * follows the same never-throw parse rule, and reading them side by side is how an author sees that
 * {@code conversations_episode} and {@code conversations_thread} ask different questions about the
 * same situation.
 *
 * <p>Every query is <b>fail-closed</b>: an unparseable one never matches, so a datapack typo is a dead
 * branch rather than a branch that fires for everybody.
 */
public final class HistoryQuery {

    private HistoryQuery() {
    }

    /**
     * {@code conversations_episode: {kind, state?, overdue?, min_salience?}}.
     *
     * <p>Tests whether the villager currently has a situation of a given kind, and what state it is
     * in. This is the condition that lets one authored page say "still stuck" only while it is true.
     */
    public record Episode(String kind, Set<EpisodeState> states, Optional<Boolean> overdue,
                          int minSalience, boolean negate) {

        public static final Episode INVALID = new Episode("", Set.of(), Optional.empty(), 0, false);

        public Episode {
            kind = normalize(kind);
            states = Set.copyOf(states);
        }

        public boolean isValid() {
            return !kind.isEmpty();
        }

        public static Episode fromJson(JsonObject json) {
            if (json == null || !json.has("kind")) {
                return INVALID;
            }
            Set<EpisodeState> states = new LinkedHashSet<>();
            for (String key : tokens(json, "state")) {
                EpisodeState state = EpisodeState.byKey(key).orElse(null);
                if (state == null) {
                    return INVALID;
                }
                states.add(state);
            }
            return new Episode(json.get("kind").getAsString(), states,
                    json.has("overdue") ? Optional.of(json.get("overdue").getAsBoolean()) : Optional.empty(),
                    json.has("min_salience") ? json.get("min_salience").getAsInt() : 0,
                    json.has("not") && json.get("not").getAsBoolean());
        }

        /** Scores against one villager's history. */
        public boolean matches(Optional<VillagerHistory> history, long today) {
            if (!isValid()) {
                return false;
            }
            boolean matched = history
                    .flatMap(record -> record.liveEpisodeOfKind(kind, today))
                    .filter(episode -> states.isEmpty() || states.contains(episode.state()))
                    .filter(episode -> episode.salience() >= minSalience)
                    .filter(episode -> overdue.isEmpty() || episode.isOverdue(today) == overdue.get())
                    .isPresent();
            return negate != matched;
        }
    }

    /** {@code conversations_thread: {template, status?, ready?}}. */
    public record Thread(String templateId, Set<ThreadStatus> statuses, Optional<Boolean> ready,
                         boolean negate) {

        public static final Thread INVALID = new Thread("", Set.of(), Optional.empty(), false);

        public Thread {
            templateId = normalize(templateId);
            statuses = Set.copyOf(statuses);
        }

        public boolean isValid() {
            return !templateId.isEmpty();
        }

        public static Thread fromJson(JsonObject json) {
            if (json == null || !json.has("template")) {
                return INVALID;
            }
            Set<ThreadStatus> statuses = new LinkedHashSet<>();
            for (String key : tokens(json, "status")) {
                ThreadStatus status = ThreadStatus.byKey(key).orElse(null);
                if (status == null) {
                    return INVALID;
                }
                statuses.add(status);
            }
            return new Thread(json.get("template").getAsString(), statuses,
                    json.has("ready") ? Optional.of(json.get("ready").getAsBoolean()) : Optional.empty(),
                    json.has("not") && json.get("not").getAsBoolean());
        }

        public boolean matches(Optional<PairHistory> pair, long today) {
            if (!isValid()) {
                return false;
            }
            boolean matched = pair
                    .flatMap(history -> history.thread(templateId))
                    .filter(thread -> statuses.isEmpty() || statuses.contains(thread.status()))
                    .filter(thread -> ready.isEmpty() || thread.isReady(today) == ready.get())
                    .isPresent();
            return negate != matched;
        }
    }

    /** {@code conversations_commitment: {id, state?, due?}}. */
    public record Commitment(String id, Set<CommitmentRecord.State> states, Optional<Boolean> due,
                             boolean negate) {

        public static final Commitment INVALID = new Commitment("", Set.of(), Optional.empty(), false);

        public Commitment {
            id = normalize(id);
            states = Set.copyOf(states);
        }

        public boolean isValid() {
            return !id.isEmpty();
        }

        public static Commitment fromJson(JsonObject json) {
            if (json == null || !json.has("id")) {
                return INVALID;
            }
            Set<CommitmentRecord.State> states = new LinkedHashSet<>();
            for (String key : tokens(json, "state")) {
                CommitmentRecord.State state = CommitmentRecord.State.byKey(key).orElse(null);
                if (state == null) {
                    return INVALID;
                }
                states.add(state);
            }
            return new Commitment(json.get("id").getAsString(), states,
                    json.has("due") ? Optional.of(json.get("due").getAsBoolean()) : Optional.empty(),
                    json.has("not") && json.get("not").getAsBoolean());
        }

        /**
         * Scores against a pair's promises.
         *
         * <p>Uses {@link CommitmentRecord#stateToday} rather than the stored state, so a promise whose
         * deadline passed while the server was down reads as overdue on the first conversation after
         * — without the condition having written anything.
         */
        public boolean matches(Optional<PairHistory> pair, long today) {
            if (!isValid()) {
                return false;
            }
            boolean matched = pair
                    .flatMap(history -> history.commitment(id))
                    .filter(commitment -> states.isEmpty()
                            || states.contains(commitment.stateToday(today)))
                    .filter(commitment -> due.isEmpty() || commitment.isDue(today) == due.get())
                    .isPresent();
            return negate != matched;
        }
    }

    /** {@code conversations_claim: {type, is?, disputed?}}. */
    public record Claim(String type, Set<String> values, Optional<Boolean> disputed, boolean negate) {

        public static final Claim INVALID = new Claim("", Set.of(), Optional.empty(), false);

        public Claim {
            type = normalize(type);
            values = Set.copyOf(values);
        }

        public boolean isValid() {
            return !type.isEmpty();
        }

        public static Claim fromJson(JsonObject json) {
            if (json == null || !json.has("type")) {
                return INVALID;
            }
            Set<String> values = tokens(json, "is");
            values.addAll(tokens(json, "any_of"));
            return new Claim(json.get("type").getAsString(), values,
                    json.has("disputed") ? Optional.of(json.get("disputed").getAsBoolean())
                            : Optional.empty(),
                    json.has("not") && json.get("not").getAsBoolean());
        }

        public boolean matches(Optional<PairHistory> pair) {
            if (!isValid()) {
                return false;
            }
            boolean matched = pair
                    .flatMap(history -> history.claim(type))
                    .filter(claim -> values.isEmpty() || values.contains(claim.value().raw()))
                    .filter(claim -> disputed.isEmpty() || claim.disputed() == disputed.get())
                    .isPresent();
            return negate != matched;
        }
    }

    /** {@code conversations_opinion: {axis, min?, max?, target_is_family?}}. */
    public record Opinion(String axis, Optional<Integer> min, Optional<Integer> max, boolean negate) {

        public static final Opinion INVALID = new Opinion("", Optional.empty(), Optional.empty(), false);

        public Opinion {
            axis = normalize(axis);
        }

        public boolean isValid() {
            return SocialOpinionRecord.AXES.contains(axis);
        }

        public static Opinion fromJson(JsonObject json) {
            if (json == null || !json.has("axis")) {
                return INVALID;
            }
            return new Opinion(json.get("axis").getAsString(),
                    json.has("min") ? Optional.of(json.get("min").getAsInt()) : Optional.empty(),
                    json.has("max") ? Optional.of(json.get("max").getAsInt()) : Optional.empty(),
                    json.has("not") && json.get("not").getAsBoolean());
        }

        /** True when the villager holds at least one opinion on this axis inside the range. */
        public boolean matches(Optional<VillagerHistory> history) {
            if (!isValid()) {
                return false;
            }
            boolean matched = false;
            if (history.isPresent()) {
                for (SocialOpinionRecord opinion : history.get().opinions()) {
                    if (!opinion.axis().equals(axis)) {
                        continue;
                    }
                    if (min.isPresent() && opinion.value() < min.get()) {
                        continue;
                    }
                    if (max.isPresent() && opinion.value() > max.get()) {
                        continue;
                    }
                    matched = true;
                    break;
                }
            }
            return negate != matched;
        }
    }

    /**
     * {@code conversations_role: {role, min_days?, not?}}.
     *
     * <p>Asks whether the villager holds an observed role towards anybody at all, which is what a
     * scene needs to know before it offers "the one who taught me" as a subject. Whether that person
     * is still alive and nameable is settled later, when the plan binds the slot — a condition that
     * resolved a villager here would be doing the binder's job at the wrong moment.
     */
    public record Role(SocialRole role, long minDays, boolean negate) {

        public static final Role INVALID = new Role(null, 0L, false);

        public boolean isValid() {
            return role != null;
        }

        public static Role fromJson(JsonObject json) {
            if (json == null || !json.has("role")) {
                return INVALID;
            }
            return new Role(SocialRole.byKey(json.get("role").getAsString()).orElse(null),
                    json.has("min_days") ? json.get("min_days").getAsLong() : 0L,
                    json.has("not") && json.get("not").getAsBoolean());
        }

        /** True when the villager has held this role towards somebody for long enough. */
        public boolean matches(Optional<VillagerHistory> history, long today) {
            if (!isValid()) {
                return false;
            }
            boolean matched = false;
            if (history.isPresent()) {
                for (SocialRoleRecord record : history.get().rolesOfKind(role)) {
                    if (record.hasExpired(today) || record.daysHeld(today) < minDays) {
                        continue;
                    }
                    matched = true;
                    break;
                }
            }
            return negate != matched;
        }
    }

    /** {@code conversations_recent: {level, id, within_days}}. */
    public record Recent(TopicRecencyRecord.Level level, String id, long withinDays, boolean negate) {

        public static final Recent INVALID = new Recent(TopicRecencyRecord.Level.SCENE, "", 0L, false);

        public Recent {
            id = normalize(id);
        }

        public boolean isValid() {
            return !id.isEmpty() && withinDays > 0;
        }

        public static Recent fromJson(JsonObject json) {
            if (json == null || !json.has("id")) {
                return INVALID;
            }
            String levelKey = json.has("level")
                    ? json.get("level").getAsString().trim().toUpperCase(Locale.ROOT) : "SCENE";
            TopicRecencyRecord.Level level;
            try {
                level = TopicRecencyRecord.Level.valueOf(levelKey);
            } catch (IllegalArgumentException e) {
                return INVALID;
            }
            String id = json.get("id").getAsString();
            // A shape query names the shape token, which must be a real one, or the condition would
            // silently never fire and read as "this shape is never recent".
            if (level == TopicRecencyRecord.Level.SHAPE
                    && SceneShape.byKey(id).isEmpty()) {
                return INVALID;
            }
            return new Recent(level, id,
                    json.has("within_days") ? json.get("within_days").getAsLong() : 3L,
                    json.has("not") && json.get("not").getAsBoolean());
        }

        public boolean matches(Optional<PairHistory> pair, long today) {
            if (!isValid()) {
                return false;
            }
            boolean matched = pair
                    .map(history -> history.recency().daysSince(level, id, today) <= withinDays)
                    .orElse(false);
            return negate != matched;
        }
    }

    private static Set<String> tokens(JsonObject json, String member) {
        Set<String> out = new LinkedHashSet<>();
        if (json == null || !json.has(member)) {
            return out;
        }
        if (json.get(member).isJsonPrimitive()) {
            out.add(json.get(member).getAsString().trim().toLowerCase(Locale.ROOT));
            return out;
        }
        if (json.get(member).isJsonArray()) {
            json.getAsJsonArray(member).forEach(item ->
                    out.add(item.getAsString().trim().toLowerCase(Locale.ROOT)));
        }
        return out;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
