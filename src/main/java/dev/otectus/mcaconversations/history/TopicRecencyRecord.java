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
 * <p>Three of those four levels are stored as last-seen days, because the director only ever asks
 * "how long ago" and days are the whole answer.
 *
 * <h2>Why scenes also keep a ring</h2>
 *
 * <p>The scene level answers a second question the others do not: {@code max_mentions_per_7_days}.
 * Before 1.4.1 that number was derived from the last-seen day alone, so it could only ever be 0 or 1
 * — and a cap is reached when the count is <em>at least</em> the cap. Every scene authored with a cap
 * of 2 or 3 (134 of them in the shipped corpus) therefore had no weekly cap at all; only its separate
 * cooldown could suppress it.
 *
 * <p>So each scene carries a small ring of seven daily bins alongside its last-seen day: two bits per
 * day, saturating at three, anchored on that scene's last-seen day and shifted forward as days pass.
 * Fourteen bits per scene, thirty-two scenes, one integer each — the record stays the handful of small
 * numbers it was. The window is exactly the seven day labels {@code today-6 … today}; "not more than
 * seven days ago" would have spanned eight.
 */
public record TopicRecencyRecord(Map<String, Long> scenes,
                                 Map<String, Long> subjects,
                                 Map<String, Long> shapes,
                                 Map<String, Long> topics,
                                 Map<String, Integer> sceneMentions,
                                 long lastInitiativeDay,
                                 int initiativesToday,
                                 long initiativeCountDay) {

    /** Entries kept per level. Beyond this the oldest is dropped; see the config for the pair cap. */
    public static final int MAX_ENTRIES_PER_LEVEL = 32;

    /** Day labels in the mention window: {@code today-6} through {@code today}. */
    public static final int MENTION_WINDOW_DAYS = 7;

    /** Bits per day in a scene's mention ring, so one day can record more than one mention. */
    private static final int BIN_BITS = 2;

    /** The most mentions one day can record. A cap above this is met by the first three anyway. */
    private static final int BIN_MAX = (1 << BIN_BITS) - 1;

    /** The whole ring: seven bins of two bits. */
    private static final int RING_MASK = (1 << (MENTION_WINDOW_DAYS * BIN_BITS)) - 1;

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

    public static final TopicRecencyRecord EMPTY = new TopicRecencyRecord(
            Map.of(), Map.of(), Map.of(), Map.of(), Map.of(), Long.MIN_VALUE, 0, Long.MIN_VALUE);

    public TopicRecencyRecord {
        scenes = bounded(scenes);
        subjects = bounded(subjects);
        shapes = bounded(shapes);
        topics = bounded(topics);
        sceneMentions = boundedRings(sceneMentions, scenes);
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

    /**
     * How many times this scene came up on the seven day labels {@code today-6 … today}.
     *
     * <p>A scene with a last-seen day but no ring counts as one mention on that day. That is the
     * migration for saves written before the ring existed, and it is also the honest reading: the day
     * is evidence the scene played once, and nothing is evidence it played twice.
     *
     * <p>Bins ahead of {@code today} are ignored rather than counted, so a server clock moved
     * backwards under-counts for a few days instead of locking a scene out of its own cap.
     */
    public int mentionsInWindow(String scene, long today) {
        String key = normalize(scene);
        Long anchor = scenes.get(key);
        if (anchor == null) {
            return 0;
        }
        Integer stored = sceneMentions.get(key);
        int ring = stored == null ? 1 : stored;
        int count = 0;
        for (int bin = 0; bin < MENTION_WINDOW_DAYS; bin++) {
            int mentions = (ring >>> (bin * BIN_BITS)) & BIN_MAX;
            if (mentions == 0) {
                continue;
            }
            long day = anchor - bin;
            if (day <= today && day > today - MENTION_WINDOW_DAYS) {
                count += mentions;
            }
        }
        return count;
    }

    /** Records that a scene was played, stamping all four levels at once. */
    public TopicRecencyRecord played(String scene, String subject, String shape, String topic, long day) {
        Map<String, Long> stampedScenes = stamped(scenes, scene, day);
        return new TopicRecencyRecord(
                stampedScenes, stamped(subjects, subject, day),
                stamped(shapes, shape, day), stamped(topics, topic, day),
                mentioned(sceneMentions, scenes, scene, day),
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
        return new TopicRecencyRecord(scenes, subjects, shapes, topics, sceneMentions, day, count, day);
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

    /**
     * Advances this scene's ring to {@code day} and records one more mention on it.
     *
     * <p>{@code anchors} is the scene map as it stood <em>before</em> the new stamp, because the shift
     * is measured from the day the ring is currently anchored on.
     */
    private static Map<String, Integer> mentioned(Map<String, Integer> rings, Map<String, Long> anchors,
                                                  String id, long day) {
        String key = normalize(id);
        if (key.isEmpty()) {
            return rings;
        }
        Long anchor = anchors.get(key);
        Integer stored = rings.get(key);
        int ring = anchor == null ? 0 : (stored == null ? 1 : stored);
        if (anchor != null) {
            long shift = day - anchor;
            // A gap of a week or a clock moved backwards leaves nothing worth carrying forward.
            ring = shift < 0 || shift >= MENTION_WINDOW_DAYS
                    ? 0
                    : (ring << ((int) shift * BIN_BITS)) & RING_MASK;
        }
        int todayBin = ring & BIN_MAX;
        ring = (ring & ~BIN_MAX) | Math.min(BIN_MAX, todayBin + 1);

        Map<String, Integer> updated = new LinkedHashMap<>(rings);
        updated.put(key, ring);
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

    /**
     * Keeps the rings in lockstep with the scene stamps that anchor them.
     *
     * <p>A ring without its stamp has no anchor and so no meaning; dropping it here is what makes the
     * scene level's own eviction enough for both maps.
     */
    private static Map<String, Integer> boundedRings(Map<String, Integer> rings,
                                                     Map<String, Long> anchors) {
        if (rings == null || rings.isEmpty()) {
            return Map.of();
        }
        Map<String, Integer> out = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : rings.entrySet()) {
            String key = normalize(entry.getKey());
            if (key.isEmpty() || entry.getValue() == null || !anchors.containsKey(key)) {
                continue;
            }
            int ring = entry.getValue() & RING_MASK;
            if (ring != 0) {
                out.put(key, ring);
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
        CompoundTag rings = new CompoundTag();
        new TreeMap<>(sceneMentions).forEach(rings::putInt);
        if (!rings.isEmpty()) {
            tag.put("scene_mentions", rings);
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
        // Absent in saves written before 1.4.1. Left empty rather than invented: mentionsInWindow
        // reads a stamp with no ring as the one mention it is evidence of.
        Map<String, Integer> rings = new LinkedHashMap<>();
        if (tag.contains("scene_mentions", Tag.TAG_COMPOUND)) {
            CompoundTag stored = tag.getCompound("scene_mentions");
            for (String key : new java.util.TreeSet<>(stored.getAllKeys())) {
                rings.put(key, stored.getInt(key));
            }
        }
        return new TopicRecencyRecord(maps.get(0), maps.get(1), maps.get(2), maps.get(3), rings,
                tag.contains("initiative_day") ? tag.getLong("initiative_day") : Long.MIN_VALUE,
                tag.getInt("initiatives_today"),
                tag.contains("initiative_count_day") ? tag.getLong("initiative_count_day") : Long.MIN_VALUE);
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
