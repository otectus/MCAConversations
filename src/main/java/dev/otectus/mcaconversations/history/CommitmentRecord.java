package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;

import java.util.Locale;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

/**
 * A promise the game can actually check (spec §8.5).
 *
 * <p>Guarded by construction: a commitment cannot exist without naming a {@link CommitmentResolver},
 * and one whose resolver is {@link CommitmentResolver#MANUAL_NEUTRAL} can never reach
 * {@link State#KEPT} or {@link State#BROKEN}. That is the runtime half of "never write a promise the
 * game cannot resolve"; the content half is a build lint over commitment replies.
 *
 * @param id         the authored commitment template id
 * @param resolver   how it will be observed
 * @param target     what satisfies it — an item tag, a quest id, a day, a reply id — as a typed value
 * @param madeBy     who promised: the player, or the villager
 * @param state      where it stands
 * @param createdDay the day it was made
 * @param dueDay     the day it is due, when the resolver needs one
 * @param resolvedDay the day it was settled, when it has been
 * @param episodeId  the episode it serves, when it serves one
 */
public record CommitmentRecord(String id,
                               CommitmentResolver resolver,
                               NarrativeValue target,
                               Party madeBy,
                               State state,
                               long createdDay,
                               OptionalLong dueDay,
                               OptionalLong resolvedDay,
                               Optional<UUID> episodeId) {

    /** Who made the promise. Both directions exist: a villager may owe the player something too. */
    public enum Party {
        PLAYER("player"),
        VILLAGER("villager");

        private final String key;

        Party(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }

        static Party byKey(String key) {
            return VILLAGER.key.equalsIgnoreCase(key == null ? "" : key.trim()) ? VILLAGER : PLAYER;
        }
    }

    /** Where a promise stands. */
    public enum State {
        /** Made, not yet due, not yet observed. */
        PENDING("pending"),
        /** Past its due day and still unobserved. Raisable, once, without nagging. */
        OVERDUE("overdue"),
        /** The resolver observed it. */
        KEPT("kept"),
        /** The resolver observed that it will not happen, or the deadline passed for good. */
        BROKEN("broken"),
        /**
         * The resolver stopped being available — the mod that observed it was removed.
         *
         * <p>Explicitly not {@link #BROKEN}: punishing a player because an integration went away is
         * exactly the unfairness the resolver rule exists to prevent (spec §12.6).
         */
        UNOBSERVABLE("unobservable"),
        /** Acknowledged and deliberately never judged; the terminal state of a neutral promise. */
        NOTED("noted");

        private final String key;

        State(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }

        public boolean isSettled() {
            return this != PENDING && this != OVERDUE;
        }

        static Optional<State> byKey(String key) {
            if (key == null) {
                return Optional.empty();
            }
            String normalized = key.trim().toLowerCase(Locale.ROOT);
            for (State state : values()) {
                if (state.key.equals(normalized)) {
                    return Optional.of(state);
                }
            }
            return Optional.empty();
        }
    }

    private static final String KEY_ID = "id";
    private static final String KEY_RESOLVER = "resolver";
    private static final String KEY_TARGET = "target";
    private static final String KEY_MADE_BY = "made_by";
    private static final String KEY_STATE = "state";
    private static final String KEY_CREATED = "created";
    private static final String KEY_DUE = "due";
    private static final String KEY_RESOLVED = "resolved";
    private static final String KEY_EPISODE = "episode";

    public CommitmentRecord {
        id = value(id);
        resolver = resolver == null ? CommitmentResolver.MANUAL_NEUTRAL : resolver;
        target = target == null ? NarrativeValue.EMPTY : target;
        madeBy = madeBy == null ? Party.PLAYER : madeBy;
        state = state == null ? State.PENDING : state;
        dueDay = dueDay == null ? OptionalLong.empty() : dueDay;
        resolvedDay = resolvedDay == null ? OptionalLong.empty() : resolvedDay;
        episodeId = episodeId == null ? Optional.empty() : episodeId;
        // A neutral promise may never be recorded as kept or broken, whoever asks.
        if (!resolver.isJudgeable() && (state == State.KEPT || state == State.BROKEN)) {
            state = State.NOTED;
        }
    }

    public static CommitmentRecord made(String id, CommitmentResolver resolver, NarrativeValue target,
                                        Party madeBy, long day, OptionalLong dueDay,
                                        Optional<UUID> episodeId) {
        return new CommitmentRecord(id, resolver, target, madeBy, State.PENDING, day, dueDay,
                OptionalLong.empty(), episodeId);
    }

    /** True when the promise is still outstanding. */
    public boolean isOutstanding() {
        return !state.isSettled();
    }

    /** True when the villager may raise it today without nagging. */
    public boolean isDue(long today) {
        return isOutstanding() && dueDay.isPresent() && today >= dueDay.getAsLong();
    }

    /**
     * The state this promise should be in today, given nothing new was observed.
     *
     * <p>Pure and side-effect free, so the director may consult it during selection without writing to
     * the save — history is written on mutation only (spec §21.6).
     */
    public State stateToday(long today) {
        if (state.isSettled()) {
            return state;
        }
        if (!resolver.isAvailable()) {
            return State.UNOBSERVABLE;
        }
        if (dueDay.isPresent() && today > dueDay.getAsLong()) {
            return State.OVERDUE;
        }
        return state;
    }

    /** Settles the promise. A neutral resolver settles as {@link State#NOTED} whatever is asked. */
    public CommitmentRecord resolved(State outcome, long day) {
        if (outcome == null || state.isSettled()) {
            return this;
        }
        State effective = resolver.isJudgeable() ? outcome : State.NOTED;
        return new CommitmentRecord(id, resolver, target, madeBy, effective, createdDay, dueDay,
                OptionalLong.of(day), episodeId);
    }

    /** Marks the promise overdue without settling it. */
    public CommitmentRecord markOverdue() {
        return state == State.PENDING
                ? new CommitmentRecord(id, resolver, target, madeBy, State.OVERDUE, createdDay,
                        dueDay, resolvedDay, episodeId)
                : this;
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putString(KEY_ID, id);
        tag.putString(KEY_RESOLVER, resolver.key());
        tag.put(KEY_TARGET, target.save());
        tag.putString(KEY_MADE_BY, madeBy.key());
        tag.putString(KEY_STATE, state.key());
        tag.putLong(KEY_CREATED, createdDay);
        dueDay.ifPresent(day -> tag.putLong(KEY_DUE, day));
        resolvedDay.ifPresent(day -> tag.putLong(KEY_RESOLVED, day));
        episodeId.ifPresent(episode -> tag.putUUID(KEY_EPISODE, episode));
        return tag;
    }

    public static Optional<CommitmentRecord> load(CompoundTag tag) {
        if (tag == null) {
            return Optional.empty();
        }
        String id = tag.getString(KEY_ID);
        if (id.isBlank()) {
            return Optional.empty();
        }
        return Optional.of(new CommitmentRecord(id,
                CommitmentResolver.byKey(tag.getString(KEY_RESOLVER))
                        .orElse(CommitmentResolver.MANUAL_NEUTRAL),
                NarrativeValue.load(tag.getCompound(KEY_TARGET)),
                Party.byKey(tag.getString(KEY_MADE_BY)),
                State.byKey(tag.getString(KEY_STATE)).orElse(State.PENDING),
                tag.getLong(KEY_CREATED),
                tag.contains(KEY_DUE) ? OptionalLong.of(tag.getLong(KEY_DUE)) : OptionalLong.empty(),
                tag.contains(KEY_RESOLVED) ? OptionalLong.of(tag.getLong(KEY_RESOLVED)) : OptionalLong.empty(),
                tag.hasUUID(KEY_EPISODE) ? Optional.of(tag.getUUID(KEY_EPISODE)) : Optional.empty()));
    }

    private static String value(String raw) {
        return raw == null ? "" : raw.trim().toLowerCase(Locale.ROOT);
    }
}
