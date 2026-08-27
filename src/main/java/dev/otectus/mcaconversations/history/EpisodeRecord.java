package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;

/**
 * One concrete situation a villager is in, with a lifecycle (spec §8.2).
 *
 * <p>This is the record that turns "arc 2, stage 3" into something a villager can actually talk about.
 * The old ledger could say a numbered arc had advanced; it could not say <em>what</em> had advanced,
 * to whom, when, how sure anyone was, or who was allowed to hear about it. An episode carries all of
 * that, in tokens rather than prose.
 *
 * <p><b>Every field earns its place by preventing a specific failure.</b> {@link #state} prevents
 * state drift; {@link #participants} and {@link #witnessedBy} prevent knowledge leakage;
 * {@link #privacy} and {@link #confidence} prevent a rumour being spoken as an observation;
 * {@link #createdDay} and {@link #dueDay} make "still", "again" and "tomorrow" checkable; and
 * {@link #payload} is a closed union so no English ever reaches the save file.
 *
 * @param id             stable identity, so a thread can bind to this exact situation
 * @param kind           the episode template this instantiates, e.g. {@code work.damaged_volume}
 * @param subject        the conversational subject it belongs to, e.g. {@code work.librarian.damaged_volume}
 * @param state          where it stands
 * @param ownerVillager  whose situation this is
 * @param participants   everyone else it concerns; a named person here has been validated as alive
 * @param payload        typed facts the scene binds into slots
 * @param provenance     how the owner knows it, how firmly, how sensitive it is, and what they are
 *                       allowed to do with it in front of somebody else (spec §16.3)
 * @param salience       how much it currently matters; drives selection and pruning order
 * @param createdDay     the game day it began
 * @param updatedDay     the game day its state last changed
 * @param dueDay         when it must be resolved by, if anything depends on a deadline
 * @param expiresDay     when it stops being live regardless of state
 * @param witnessedBy    ids — player UUIDs and villager UUIDs — that have actually heard about it
 * @param consumedMilestones one-shot outcomes already paid out, so a replay cannot pay twice
 */
public record EpisodeRecord(UUID id,
                            String kind,
                            String subject,
                            EpisodeState state,
                            UUID ownerVillager,
                            Set<UUID> participants,
                            Map<String, NarrativeValue> payload,
                            Provenance provenance,
                            int salience,
                            long createdDay,
                            long updatedDay,
                            OptionalLong dueDay,
                            OptionalLong expiresDay,
                            Set<String> witnessedBy,
                            Set<String> consumedMilestones) {

    public static final int MIN_SALIENCE = 0;
    public static final int MAX_SALIENCE = 100;

    /** Payload entries per episode. Enough for a whole authored scene; far short of a database. */
    public static final int MAX_PAYLOAD_ENTRIES = 12;

    /** Participants per episode. Group scenes cap at three speakers; this bounds the cast, not the scene. */
    public static final int MAX_PARTICIPANTS = 6;

    private static final String KEY_ID = "id";
    private static final String KEY_KIND = "kind";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_STATE = "state";
    private static final String KEY_OWNER = "owner";
    private static final String KEY_PARTICIPANTS = "participants";
    private static final String KEY_PAYLOAD = "payload";
    private static final String KEY_PROVENANCE = "prov";
    /** Pre-provenance keys, still read so old saves keep their footing (spec §22 migration). */
    private static final String KEY_SOURCE = "source";
    private static final String KEY_PRIVACY = "privacy";
    private static final String KEY_CONFIDENCE = "confidence";
    private static final String KEY_SALIENCE = "salience";
    private static final String KEY_CREATED = "created";
    private static final String KEY_UPDATED = "updated";
    private static final String KEY_DUE = "due";
    private static final String KEY_EXPIRES = "expires";
    private static final String KEY_WITNESSED = "witnessed";
    private static final String KEY_CONSUMED = "consumed";

    public EpisodeRecord {
        kind = normalize(kind);
        subject = normalize(subject);
        state = state == null ? EpisodeState.ACTIVE : state;
        provenance = provenance == null ? Provenance.witnessed(PrivacyLevel.defaultLevel()) : provenance;
        salience = Math.max(MIN_SALIENCE, Math.min(MAX_SALIENCE, salience));
        participants = boundedUuids(participants);
        payload = boundedPayload(payload);
        witnessedBy = Set.copyOf(new TreeSet<>(witnessedBy == null ? Set.<String>of() : witnessedBy));
        consumedMilestones = Set.copyOf(new TreeSet<>(
                consumedMilestones == null ? Set.<String>of() : consumedMilestones));
        dueDay = dueDay == null ? OptionalLong.empty() : dueDay;
        expiresDay = expiresDay == null ? OptionalLong.empty() : expiresDay;
    }

    /** A fresh episode in its opening state. */
    public static EpisodeRecord opened(UUID id, String kind, String subject, EpisodeState state,
                                       UUID owner, Map<String, NarrativeValue> payload,
                                       PrivacyLevel privacy, int salience, long day) {
        return new EpisodeRecord(id, kind, subject, state, owner, Set.of(), payload,
                Provenance.witnessed(privacy), salience, day, day,
                OptionalLong.empty(), OptionalLong.empty(), Set.of(), Set.of());
    }

    /**
     * How the owner knows this. Shorthand for {@code provenance().source()}.
     *
     * <p>These three delegates exist because privacy and confidence were components of this record
     * before provenance bundled them, and every caller that asks "how sensitive is this" should keep
     * asking the episode rather than learning the shape of the bundle.
     */
    public KnowledgeSource source() {
        return provenance.source();
    }

    /** How freely this may be repeated. */
    public PrivacyLevel privacy() {
        return provenance.privacy();
    }

    /** How firmly the owner holds it. */
    public Confidence confidence() {
        return provenance.confidence();
    }

    /** The same episode as somebody else would hold it after being told (spec §16.4). */
    public EpisodeRecord asToldBy(UUID teller) {
        return withProvenance(provenance.afterHop(teller));
    }

    public EpisodeRecord withProvenance(Provenance updated) {
        return updated == null || updated.equals(provenance) ? this
                : new EpisodeRecord(id, kind, subject, state, ownerVillager, participants, payload,
                        updated, salience, createdDay, updatedDay, dueDay, expiresDay,
                        witnessedBy, consumedMilestones);
    }

    /** True when this episode is live and has not passed its expiry. */
    public boolean isLive(long today) {
        return state.isLive() && !hasExpired(today);
    }

    public boolean hasExpired(long today) {
        return expiresDay.isPresent() && today > expiresDay.getAsLong();
    }

    /** True when a deadline has passed without the episode reaching a terminal state. */
    public boolean isOverdue(long today) {
        return state.isLive() && dueDay.isPresent() && today > dueDay.getAsLong();
    }

    /** True when {@code id} — a player or a villager — has actually been told about this. */
    public boolean isKnownTo(UUID id) {
        return id != null && (id.equals(ownerVillager) || witnessedBy.contains(id.toString())
                || participants.contains(id));
    }

    public Optional<NarrativeValue> slot(String name) {
        return name == null ? Optional.empty()
                : Optional.ofNullable(payload.get(name.trim().toLowerCase(Locale.ROOT)));
    }

    /** How many days have passed since the state last changed; the input to "still" and "again". */
    public long daysSinceUpdate(long today) {
        return Math.max(0L, today - updatedDay);
    }

    // --- Transitions ------------------------------------------------------------------------------

    /**
     * Moves to {@code next}, or returns {@code this} unchanged when the transition is not legal.
     *
     * <p>Returning the original rather than throwing is deliberate. This runs from a dialogue action
     * inside MCA's selection loop; a datapack asking for {@code succeeded -> blocked} must produce a
     * no-op and a trace line, not an exception that takes the reload with it (spec §8.3).
     */
    public EpisodeRecord transitioned(EpisodeState next, long day) {
        if (next == null || !state.allows(next) || next == state) {
            return this;
        }
        return new EpisodeRecord(id, kind, subject, next, ownerVillager, participants, payload,
                provenance, salience, createdDay, day, dueDay, expiresDay,
                witnessedBy, consumedMilestones);
    }

    /** Records that somebody now knows about this episode. Idempotent. */
    public EpisodeRecord witnessedBy(UUID witness) {
        if (witness == null || witnessedBy.contains(witness.toString())) {
            return this;
        }
        Set<String> updated = new TreeSet<>(witnessedBy);
        updated.add(witness.toString());
        return new EpisodeRecord(id, kind, subject, state, ownerVillager, participants, payload,
                provenance, salience, createdDay, updatedDay, dueDay, expiresDay,
                updated, consumedMilestones);
    }

    /**
     * Marks a one-shot outcome as paid.
     *
     * @return empty when it was already consumed, so the caller can tell "first time" from "again"
     *         and refuse to pay a second time (spec §20.9)
     */
    public Optional<EpisodeRecord> consume(String milestone) {
        String normalized = normalize(milestone);
        if (normalized.isEmpty() || consumedMilestones.contains(normalized)) {
            return Optional.empty();
        }
        Set<String> updated = new TreeSet<>(consumedMilestones);
        updated.add(normalized);
        return Optional.of(new EpisodeRecord(id, kind, subject, state, ownerVillager, participants,
                payload, provenance, salience, createdDay, updatedDay, dueDay,
                expiresDay, witnessedBy, updated));
    }

    public EpisodeRecord withSalience(int newSalience) {
        return newSalience == salience ? this
                : new EpisodeRecord(id, kind, subject, state, ownerVillager, participants, payload,
                        provenance, newSalience, createdDay, updatedDay, dueDay,
                        expiresDay, witnessedBy, consumedMilestones);
    }

    public EpisodeRecord withDeadline(OptionalLong newDue, OptionalLong newExpiry) {
        return new EpisodeRecord(id, kind, subject, state, ownerVillager, participants, payload,
                provenance, salience, createdDay, updatedDay,
                newDue == null ? dueDay : newDue, newExpiry == null ? expiresDay : newExpiry,
                witnessedBy, consumedMilestones);
    }

    /** Adds or replaces one payload slot, respecting the entry cap. */
    public EpisodeRecord withSlot(String name, NarrativeValue value) {
        String key = normalize(name);
        if (key.isEmpty() || value == null || value.isEmpty()) {
            return this;
        }
        if (!payload.containsKey(key) && payload.size() >= MAX_PAYLOAD_ENTRIES) {
            return this;
        }
        Map<String, NarrativeValue> updated = new LinkedHashMap<>(payload);
        updated.put(key, value);
        return new EpisodeRecord(id, kind, subject, state, ownerVillager, participants, updated,
                provenance, salience, createdDay, updatedDay, dueDay, expiresDay,
                witnessedBy, consumedMilestones);
    }

    /**
     * The same episode with nobody named in it.
     *
     * <p>Used when a fact may be described but its people may not be named. Dropping the names is the
     * only honest way to do that: a name kept in the record and merely not spoken is one authoring
     * mistake away from being spoken.
     */
    public EpisodeRecord withoutParticipants() {
        return participants.isEmpty() ? this
                : new EpisodeRecord(id, kind, subject, state, ownerVillager, Set.of(), payload,
                        provenance, salience, createdDay, updatedDay, dueDay, expiresDay,
                        witnessedBy, consumedMilestones);
    }

    public EpisodeRecord withParticipant(UUID participant) {
        if (participant == null || participants.contains(participant)
                || participants.size() >= MAX_PARTICIPANTS) {
            return this;
        }
        Set<UUID> updated = new LinkedHashSet<>(participants);
        updated.add(participant);
        return new EpisodeRecord(id, kind, subject, state, ownerVillager, updated, payload,
                provenance, salience, createdDay, updatedDay, dueDay, expiresDay,
                witnessedBy, consumedMilestones);
    }

    // --- Persistence -------------------------------------------------------------------------------

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putUUID(KEY_ID, id);
        tag.putString(KEY_KIND, kind);
        tag.putString(KEY_SUBJECT, subject);
        tag.putString(KEY_STATE, state.key());
        tag.putUUID(KEY_OWNER, ownerVillager);
        if (!participants.isEmpty()) {
            ListTag list = new ListTag();
            for (UUID participant : new TreeSet<>(participants)) {
                CompoundTag row = new CompoundTag();
                row.putUUID("u", participant);
                list.add(row);
            }
            tag.put(KEY_PARTICIPANTS, list);
        }
        if (!payload.isEmpty()) {
            CompoundTag slots = new CompoundTag();
            new TreeMap<>(payload).forEach((name, value) -> slots.put(name, value.save()));
            tag.put(KEY_PAYLOAD, slots);
        }
        tag.put(KEY_PROVENANCE, provenance.save());
        tag.putInt(KEY_SALIENCE, salience);
        tag.putLong(KEY_CREATED, createdDay);
        tag.putLong(KEY_UPDATED, updatedDay);
        dueDay.ifPresent(day -> tag.putLong(KEY_DUE, day));
        expiresDay.ifPresent(day -> tag.putLong(KEY_EXPIRES, day));
        putStrings(tag, KEY_WITNESSED, witnessedBy);
        putStrings(tag, KEY_CONSUMED, consumedMilestones);
        return tag;
    }

    /** Reads an episode back; a row without an id, kind or owner is unusable and reads as empty. */
    public static Optional<EpisodeRecord> load(CompoundTag tag) {
        if (tag == null || !tag.hasUUID(KEY_ID) || !tag.hasUUID(KEY_OWNER)) {
            return Optional.empty();
        }
        String kind = tag.getString(KEY_KIND);
        if (kind.isBlank()) {
            return Optional.empty();
        }
        Set<UUID> participants = new LinkedHashSet<>();
        if (tag.contains(KEY_PARTICIPANTS, Tag.TAG_LIST)) {
            ListTag list = tag.getList(KEY_PARTICIPANTS, Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                CompoundTag row = list.getCompound(i);
                if (row.hasUUID("u")) {
                    participants.add(row.getUUID("u"));
                }
            }
        }
        Map<String, NarrativeValue> payload = new LinkedHashMap<>();
        if (tag.contains(KEY_PAYLOAD, Tag.TAG_COMPOUND)) {
            CompoundTag slots = tag.getCompound(KEY_PAYLOAD);
            for (String name : new TreeSet<>(slots.getAllKeys())) {
                NarrativeValue value = NarrativeValue.load(slots.getCompound(name));
                if (!value.isEmpty()) {
                    payload.put(name, value);
                }
            }
        }
        return Optional.of(new EpisodeRecord(
                tag.getUUID(KEY_ID), kind, tag.getString(KEY_SUBJECT),
                EpisodeState.byKey(tag.getString(KEY_STATE)).orElse(EpisodeState.ACTIVE),
                tag.getUUID(KEY_OWNER), participants, payload,
                readProvenance(tag),
                tag.getInt(KEY_SALIENCE), tag.getLong(KEY_CREATED), tag.getLong(KEY_UPDATED),
                tag.contains(KEY_DUE) ? OptionalLong.of(tag.getLong(KEY_DUE)) : OptionalLong.empty(),
                tag.contains(KEY_EXPIRES) ? OptionalLong.of(tag.getLong(KEY_EXPIRES)) : OptionalLong.empty(),
                readStrings(tag, KEY_WITNESSED), readStrings(tag, KEY_CONSUMED)));
    }

    /**
     * Provenance out of either shape of save.
     *
     * <p>A row written since provenance existed has it as a compound. A row written before carries
     * the three loose fields it replaced, and {@link Provenance#fromLegacy} reconstructs a
     * well-formed bundle from them rather than dropping the footing the old save recorded.
     */
    private static Provenance readProvenance(CompoundTag tag) {
        if (tag.contains(KEY_PROVENANCE, Tag.TAG_COMPOUND)) {
            return Provenance.load(tag.getCompound(KEY_PROVENANCE));
        }
        return Provenance.fromLegacy(tag.getString(KEY_SOURCE), tag.getString(KEY_PRIVACY),
                tag.getString(KEY_CONFIDENCE));
    }

    private static void putStrings(CompoundTag tag, String key, Set<String> values) {
        if (values.isEmpty()) {
            return;
        }
        ListTag list = new ListTag();
        for (String value : new TreeSet<>(values)) {
            list.add(StringTag.valueOf(value));
        }
        tag.put(key, list);
    }

    private static Set<String> readStrings(CompoundTag tag, String key) {
        Set<String> out = new TreeSet<>();
        if (!tag.contains(key, Tag.TAG_LIST)) {
            return out;
        }
        ListTag list = tag.getList(key, Tag.TAG_STRING);
        for (int i = 0; i < list.size(); i++) {
            String value = list.getString(i);
            if (!value.isBlank()) {
                out.add(value);
            }
        }
        return out;
    }

    private static Set<UUID> boundedUuids(Set<UUID> values) {
        if (values == null || values.isEmpty()) {
            return Set.of();
        }
        Set<UUID> out = new LinkedHashSet<>();
        for (UUID value : values) {
            if (out.size() >= MAX_PARTICIPANTS) {
                break;
            }
            if (value != null) {
                out.add(value);
            }
        }
        return Set.copyOf(out);
    }

    private static Map<String, NarrativeValue> boundedPayload(Map<String, NarrativeValue> values) {
        if (values == null || values.isEmpty()) {
            return Map.of();
        }
        Map<String, NarrativeValue> out = new LinkedHashMap<>();
        for (Map.Entry<String, NarrativeValue> entry : values.entrySet()) {
            if (out.size() >= MAX_PAYLOAD_ENTRIES) {
                break;
            }
            String key = normalize(entry.getKey());
            NarrativeValue value = entry.getValue();
            if (!key.isEmpty() && value != null && !value.isEmpty()) {
                out.put(key, value);
            }
        }
        return Map.copyOf(out);
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
