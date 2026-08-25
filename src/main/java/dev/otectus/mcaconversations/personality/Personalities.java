package dev.otectus.mcaconversations.personality;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * The single source of truth for MCA personality identifiers across the versions we support.
 *
 * <p><b>Why this exists.</b> MCA 7.7 replaced the hard-coded {@code Personality} enum with a
 * registry of namespaced ids and renamed four personalities; {@code athletic} stopped being a
 * personality at all and became the {@code mca:athletic} trait. Resources (one lang overlay per
 * personality), dialogue conditions and lint tests all need the same roster, so it is defined
 * here once rather than duplicated as string literals in each place.
 *
 * <p><b>Normalisation.</b> {@link #normalize(String)} accepts every spelling MCA can hand us —
 * {@code "mca:odd"} (7.7 {@code Personality.toString()}), {@code "ODD"} (7.6 enum
 * {@code toString()}), or a bare {@code "odd"} from dialogue JSON — and reduces all three to the
 * lowercase path. That path is exactly the prefix MCA's client-side resolver builds when it looks
 * up a personality line: {@code Personality.getDialoguePrefix} returns
 * {@code ExtensibleTypeRegistry.translationSuffix(id)}, which drops the {@code mca} namespace.
 * Hence lang keys are {@code odd.dialogue.…}, never {@code mca:odd.dialogue.…}.
 */
public final class Personalities {

    /**
     * The rollable personalities MCA registers on 1.21.1, in MCA's own registration order.
     * {@code unassigned} is deliberately absent: it is a sentinel, never rolled onto a villager
     * (MCA's {@code getRandom} skips it explicitly), and its resolver falls back to it only when an
     * id fails to parse.
     *
     * <p>Read straight off {@code Personality}'s built-in registrations in the resolved MCA jar —
     * see {@code docs/PORT-1.21.1-EVIDENCE.md}. {@code confident} and {@code peppy} were rollable in
     * the 1.20.1-era 7.7 beta but are not registered here, so they moved to {@link #LEGACY_ONLY}.
     */
    public static final Set<String> CANONICAL = Collections.unmodifiableSet(new LinkedHashSet<>(List.of(
            "friendly", "flirty", "playful", "gloomy", "sensitive", "greedy", "odd", "crabby",
            "extroverted", "introverted", "relaxed", "anxious", "peaceful", "upbeat")));

    /**
     * MCA 7.6 personality ids that 7.7 renamed, mapped to their 7.7 successor. MCA migrates saved
     * villagers automatically (see upstream {@code PersonalityAndTraitsFix}), but a world still on
     * MCA 7.6 reports the old id, so both spellings must resolve to one voice.
     */
    public static final Map<String, String> LEGACY_ALIASES;

    /**
     * Personality ids this mod still ships a voice for, but which the target MCA does not register.
     *
     * <p>{@code athletic} became the {@code mca:athletic} trait back in 7.7. {@code confident} and
     * {@code peppy} were rollable in the 1.20.1-era 7.7 beta and are gone from the 1.21.1 registry.
     * None of the three can be rolled onto a villager here, but all three can still arrive from an
     * upgraded save or a third-party pack, so their overlays are retained as compatibility assets
     * rather than deleted — a villager who *is* one keeps their voice instead of falling back to the
     * generic pool.
     */
    public static final Set<String> LEGACY_ONLY =
            Collections.unmodifiableSet(new LinkedHashSet<>(List.of("athletic", "confident", "peppy")));

    static {
        Map<String, String> aliases = new LinkedHashMap<>();
        aliases.put("witty", "upbeat");
        aliases.put("shy", "introverted");
        aliases.put("lazy", "relaxed");
        aliases.put("grumpy", "crabby");
        LEGACY_ALIASES = Collections.unmodifiableMap(aliases);
    }

    private Personalities() {
    }

    /**
     * Reduces any MCA spelling of a personality to its bare lowercase path: strips a namespace
     * ({@code "mca:odd"} → {@code "odd"}) and lowercases the 7.6 enum form ({@code "ODD"} →
     * {@code "odd"}). Returns an empty string for null/blank input.
     */
    public static String normalize(String raw) {
        if (raw == null) {
            return "";
        }
        String s = raw.trim();
        int colon = s.lastIndexOf(':');
        if (colon >= 0) {
            s = s.substring(colon + 1);
        }
        return s.toLowerCase(Locale.ROOT);
    }

    /**
     * Normalises {@code raw} and resolves a renamed MCA 7.6 id to its 7.7 successor, so
     * {@code witty} and {@code upbeat} both answer to {@code upbeat}. Ids with no successor
     * (including {@code athletic} and anything a third-party addon registers) pass through
     * normalised but unchanged — an unknown personality is never an error here, it simply
     * matches only itself.
     */
    public static String canonical(String raw) {
        String n = normalize(raw);
        return LEGACY_ALIASES.getOrDefault(n, n);
    }

    /** True when {@code raw} names a personality MCA 7.7 still rolls onto villagers. */
    public static boolean isCanonical(String raw) {
        return CANONICAL.contains(canonical(raw));
    }

    /**
     * Every prefix that needs a lang overlay: the 7.7 roster plus the legacy spellings, because
     * one binary serves both MCA versions and a 7.6 world still asks for {@code witty.dialogue.…}.
     */
    public static Set<String> overlayPrefixes() {
        Set<String> all = new LinkedHashSet<>(CANONICAL);
        all.addAll(LEGACY_ALIASES.keySet());
        all.addAll(LEGACY_ONLY);
        return Collections.unmodifiableSet(all);
    }

    /**
     * True when two personality ids name the same voice — i.e. after alias resolution they are
     * equal. This is the matching rule behind the {@code conversations_personality} condition, so
     * a datapack asking for {@code upbeat} also matches a 7.6 {@code witty} villager.
     */
    public static boolean matches(String a, String b) {
        String ca = canonical(a);
        return !ca.isEmpty() && ca.equals(canonical(b));
    }
}
