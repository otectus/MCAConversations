package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * What this villager and this player have talked about lately, at four levels (spec §9.4).
 *
 * <p>Repetition is not a duplicate-key problem. Two scenes can share no ids at all and still feel
 * identical, because they are the same <em>shape</em> — tell me the problem, offer help, thanks — with
 * different nouns. So four levels are tracked separately and penalised independently:
 *
 * <ul>
 *   <li><b>scene</b> — strong suppression until its episode state changes;</li>
 *   <li><b>subject</b> — moderate, across several days;</li>
 *   <li><b>shape</b> — moderate even when the nouns differ, which is the level that stops a corpus of
 *       thousands of lines reading like one conversation;</li>
 *   <li><b>topic</b> — light, because a due work callback must not be hidden merely because work came
 *       up yesterday.</li>
 * </ul>
 *
 * <p>Stored as last-seen days rather than as a list of events: the director only ever asks "how long
 * ago", so days are the whole answer and the record stays a handful of small integers per pair.
 */
public record TopicRecencyRecord(Map<String, Long> scenes,
                                 Map<String, Long> subjects,
                                 Map<String, Long> shapes,
                                 Map<String, Long> topics,
                                 long lastInitiativeDay,
                                 int initiativesToday,
                                 long initiativeCountDay) {

    /** Entries kept per level. Beyond this the oldest is dropped; see the config for the pair cap. */
    public static final int MAX_ENTRIES_PER_LEVEL = 32;

    /** The recency levels, in the order the director applies their penalties. */
    public enum Level {
        SCENE("scene"),
        SUBJECT("subject"),
        SHAPE("shape"),
        TOPIC("topic");

        private final String key;

        Level(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }
    }

    public static final TopicRecencyRecord EMPTY =
            new TopicRecencyRecord(Map.of(), Map.of(), Map.of(), Map.of(), Long.MIN_VALUE, 0, Long.MIN_VALUE);

    public TopicRecencyRecord {
        scenes = bounded(scenes);
        subjects = bounded(subjects);
        shapes = bounded(shapes);
        topics = bounded(topics);
    }

    /** The day something at this level was last seen, or empty when it never was. */
    public java.util.OptionalLong lastSeen(Level level, String id) {
        Long day = mapFor(level).get(normalize(id));
        return day == null ? java.util.OptionalLong.empty() : java.util.OptionalLong.of(day);
    }

    /** Days since something at this level was last seen; {@link Long#MAX_VALUE} when never. */
    public long daysSince(Level level, String id, long today) {
        Long day = mapFor(level).get(normalize(id));
        return day == null ? Long.MAX_VALUE : Math.max(0L, today - day);
    }

    /** Records that a scene was played, stamping all four levels at once. */
    public TopicRecencyRecord played(String scene, String subject, String shape, String topic, long day) {
        return new TopicRecencyRecord(
                stamped(scenes, scene, day), stamped(subjects, subject, day),
                stamped(shapes, shape, day), stamped(topics, topic, day),
                lastInitiativeDay, initiativesToday, initiativeCountDay);
    }

    /**
     * Records that the villager opened a conversation unprompted.
     *
     * <p>The daily counter rolls on the day rather than being reset by a tick handler, so a server
     * that was offline over a day boundary comes back with a correct count instead of a stale one.
     */
    public TopicRecencyRecord initiated(long day) {
        int count = day == initiativeCountDay ? initiativesToday + 1 : 1;
        return new TopicRecencyRecord(scenes, subjects, shapes, topics, day, count, day);
    }

    /** How many unprompted openings have happened today. */
    public int initiativesOn(long day) {
        return day == initiativeCountDay ? initiativesToday : 0;
    }

    public long daysSinceInitiative(long today) {
        return lastInitiativeDay == Long.MIN_VALUE ? Long.MAX_VALUE
                : Math.max(0L, today - lastInitiativeDay);
    }

    private Map<String, Long> mapFor(Level level) {
        return switch (level) {
            case SCENE -> scenes;
            case SUBJECT -> subjects;
            case SHAPE -> shapes;
            case TOPIC -> topics;
        };
    }

    private static Map<String, Long> stamped(Map<String, Long> current, String id, long day) {
        String key = normalize(id);
        if (key.isEmpty()) {
            return current;
        }
        Map<String, Long> updated = new LinkedHashMap<>(current);
        updated.put(key, day);
        if (updated.size() > MAX_ENTRIES_PER_LEVEL) {
            // Drop the oldest stamp: the entry whose last-seen day is furthest back is also the one
            // whose penalty has already decayed to nothing, so forgetting it changes no decision.
            updated.entrySet().stream()
                    .min(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .ifPresent(updated::remove);
        }
        return Map.copyOf(updated);
    }

    private static Map<String, Long> bounded(Map<String, Long> values) {
        if (values == null || values.isEmpty()) {
            return Map.of();
        }
        Map<String, Long> out = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : values.entrySet()) {
            if (out.size() >= MAX_ENTRIES_PER_LEVEL) {
                break;
            }
            String key = normalize(entry.getKey());
            if (!key.isEmpty() && entry.getValue() != null) {
                out.put(key, entry.getValue());
            }
        }
        return Map.copyOf(out);
    }

    // --- Persistence -------------------------------------------------------------------------------

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        for (Level level : Level.values()) {
            CompoundTag entries = new CompoundTag();
            new TreeMap<>(mapFor(level)).forEach(entries::putLong);
            if (!entries.isEmpty()) {
                tag.put(level.key(), entries);
            }
        }
        if (lastInitiativeDay != Long.MIN_VALUE) {
            tag.putLong("initiative_day", lastInitiativeDay);
            tag.putInt("initiatives_today", initiativesToday);
            tag.putLong("initiative_count_day", initiativeCountDay);
        }
        return tag;
    }

    public static TopicRecencyRecord load(CompoundTag tag) {
        if (tag == null) {
            return EMPTY;
        }
        List<Map<String, Long>> maps = new ArrayList<>();
        for (Level level : Level.values()) {
            Map<String, Long> entries = new LinkedHashMap<>();
            if (tag.contains(level.key(), Tag.TAG_COMPOUND)) {
                CompoundTag stored = tag.getCompound(level.key());
                for (String key : new java.util.TreeSet<>(stored.getAllKeys())) {
                    entries.put(key, stored.getLong(key));
                }
            }
            maps.add(entries);
        }
        return new TopicRecencyRecord(maps.get(0), maps.get(1), maps.get(2), maps.get(3),
                tag.contains("initiative_day") ? tag.getLong("initiative_day") : Long.MIN_VALUE,
                tag.getInt("initiatives_today"),
                tag.contains("initiative_count_day") ? tag.getLong("initiative_count_day") : Long.MIN_VALUE);
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
