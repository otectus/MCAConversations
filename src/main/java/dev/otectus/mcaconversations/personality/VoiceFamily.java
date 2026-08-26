package dev.otectus.mcaconversations.personality;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Six ways of saying a hard thing, and which personality speaks each (spec §9.1, §9.3).
 *
 * <p><b>Why families rather than sixteen rewrites.</b> Spec §9.1 is explicit that personalization
 * composes in layers and that a separate full tree per combination is the wrong shape. Sixteen
 * personalities times every signature line is that wrong shape: it produces sixteen paraphrases of
 * one sentence, which is precisely what the paraphrase lint exists to reject. What actually differs
 * between a confident villager and a crabby one, when they are telling you the thing they do not
 * tell people, is not the sentiment — it is the <em>approach</em>: whether they state it flat,
 * joke first, turn it outward, understate it, feel its weight, or take their time. There are six
 * such approaches in MCA's roster, not sixteen.
 *
 * <p>So an author writes one line per family and the build expands it into every member's lang
 * namespace. The villager still speaks in their own personality's voice — MCA looks up
 * {@code crabby.dialogue.x} and finds a line written for a villager who says things flat — and two
 * personalities in a family sharing a sentence is a deliberate, documented consequence rather than
 * an accident, in the same way that a mason and a woodworker share a schema and never a sentence.
 *
 * <p>Every id in {@link Personalities#overlayPrefixes()} belongs to exactly one family; the tests
 * enforce that, so a personality added by MCA cannot quietly fall out of the overlay system.
 */
public enum VoiceFamily {

    /**
     * Says the thing without softening it, and dislikes being handled. Mastery, terms, and plain
     * refusal. A rebuff from this family is short and means it.
     */
    PLAINSPOKEN("plainspoken", "confident", "crabby", "greedy"),

    /**
     * Reaches for the lighter reading first — a joke, a reframe, the next thing that could happen.
     * Never used to dismiss grief; the humour is how they get to the point, not a way past it.
     */
    BRIGHT("bright", "peppy", "upbeat", "playful"),

    /**
     * Turns outward. Brings in a neighbour, a family member, a shared afternoon; answers a question
     * with a question. Discloses sooner and expects the exchange to go both ways.
     */
    WARM("warm", "friendly", "extroverted", "flirty"),

    /**
     * Understates, and lands on one small concrete detail instead of the large statement. Sideways
     * disclosure: the fact arrives attached to an object, an hour, a habit.
     */
    QUIET("quiet", "introverted", "odd"),

    /**
     * Feels the weight of it while saying it, and says so. Names the fear rather than the fact.
     * Care lands here; bluntness costs more here than anywhere else.
     */
    TENDER("tender", "sensitive", "anxious", "gloomy"),

    /**
     * Unhurried. Puts the thing in the context of years rather than today, prefers repair to
     * escalation, and is not in a rush to be understood.
     */
    SETTLED("settled", "relaxed", "peaceful");

    private final String key;
    private final Set<String> canonicalMembers;

    VoiceFamily(String key, String... canonicalMembers) {
        this.key = key;
        this.canonicalMembers = Set.of(canonicalMembers);
    }

    public String key() {
        return key;
    }

    /** The MCA 7.7 personalities that speak this way. Legacy spellings resolve through them. */
    public Set<String> canonicalMembers() {
        return canonicalMembers;
    }

    /**
     * Every lang-overlay prefix this family owns: its canonical members plus the MCA 7.6 spellings
     * that resolve to them, so one authored line reaches a 7.6 {@code witty} villager and a 7.7
     * {@code upbeat} one alike.
     */
    public Set<String> overlayPrefixes() {
        Set<String> out = new LinkedHashSet<>(canonicalMembers);
        for (Map.Entry<String, String> alias : Personalities.LEGACY_ALIASES.entrySet()) {
            if (canonicalMembers.contains(alias.getValue())) {
                out.add(alias.getKey());
            }
        }
        for (String legacy : Personalities.LEGACY_ONLY) {
            if (LEGACY_ONLY_HOMES.get(legacy) == this) {
                out.add(legacy);
            }
        }
        return Collections.unmodifiableSet(out);
    }

    /**
     * MCA 7.6 personalities with no 7.7 successor still need a voice. {@code athletic} is unhurried
     * about talking and quick about everything else, which is closest to {@link #SETTLED}.
     */
    private static final Map<String, VoiceFamily> LEGACY_ONLY_HOMES;

    private static final Map<String, VoiceFamily> BY_PERSONALITY;

    static {
        Map<String, VoiceFamily> legacyOnly = new LinkedHashMap<>();
        legacyOnly.put("athletic", SETTLED);
        LEGACY_ONLY_HOMES = Collections.unmodifiableMap(legacyOnly);

        Map<String, VoiceFamily> index = new LinkedHashMap<>();
        for (VoiceFamily family : values()) {
            for (String prefix : family.overlayPrefixes()) {
                index.put(prefix, family);
            }
        }
        BY_PERSONALITY = Collections.unmodifiableMap(index);
    }

    /**
     * The family a villager of this personality speaks in, accepting any MCA spelling. Returns
     * {@link #PLAINSPOKEN} for a personality this mod has never heard of: an unknown personality
     * gets the least-decorated voice rather than a guess at a temperament nobody described.
     */
    public static VoiceFamily of(String rawPersonality) {
        VoiceFamily direct = BY_PERSONALITY.get(Personalities.normalize(rawPersonality));
        if (direct != null) {
            return direct;
        }
        VoiceFamily resolved = BY_PERSONALITY.get(Personalities.canonical(rawPersonality));
        return resolved != null ? resolved : PLAINSPOKEN;
    }

    /** Looks a family up by its author-facing key, or null if no family has that key. */
    public static VoiceFamily byKey(String key) {
        for (VoiceFamily family : values()) {
            if (family.key.equals(key)) {
                return family;
            }
        }
        return null;
    }

    /** Every overlay prefix, mapped to the family that supplies its lines. */
    public static Map<String, VoiceFamily> byPersonality() {
        return BY_PERSONALITY;
    }
}
