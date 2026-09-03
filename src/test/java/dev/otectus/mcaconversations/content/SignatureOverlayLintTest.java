package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.BeatContract;
import dev.otectus.mcaconversations.conversation.SignatureBeat;
import dev.otectus.mcaconversations.conversation.VoiceWeight;
import dev.otectus.mcaconversations.personality.Personalities;
import dev.otectus.mcaconversations.personality.VoiceFamily;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.otectus.mcaconversations.support.TestPaths;

/**
 * Personality has to be audible where it matters (spec §9.3).
 *
 * <p>The mod has always had per-personality lang overlays, and they always stopped at the topic
 * openers: a villager said one sentence in their own voice and then handed the next six exchanges to
 * a single narrator. §9.3 names the lines where that is least acceptable — the trade a villager
 * opens with, the way they close a subject against you, what they disclose, what they promise, and
 * what they bring back up days later — and requires authored coverage for all of them.
 *
 * <p>{@link SignatureBeat} derives that set from the beat contracts, so this suite never carries a
 * hand-maintained list of which lines matter. What it does carry is a <b>debt ledger</b>
 * ({@code src/test/resources/signature_overlay_debt.txt}) of the signature pools not yet written, in
 * the same shape the route ledger used during the coherence repair: an uncovered pool that is not in
 * the ledger fails, and a ledger entry that is now covered also fails, so the file can only shrink
 * and cannot quietly grow back.
 */
class SignatureOverlayLintTest {

    private static final String SEP = System.lineSeparator();

    private static final Path ASSETS = TestPaths.of("src/main/resources/assets");
    private static final Path DEBT = TestPaths.of("src/test/resources/signature_overlay_debt.txt");

    /**
     * The share of all referenced say pools that carry authored personality coverage. Spec §9.3 sets
     * 25% as the end-of-expansion target, "prioritizing salience over raw percentage"; this floor is
     * what the release has actually reached, and it may only be raised.
     */
    private static final double COVERAGE_FLOOR = 0.075;
    /**
     * Overlay coverage owed by the generated profession scenes, which is presently none.
     *
     * <p>The living-history scenes ship in 1.4.0 with one say pool each and no personality variants,
     * so measuring them in the same ratio as the hand-authored corpus would report a fall in coverage
     * that nobody caused — the hand-written overlays are exactly where they were. They are counted
     * separately instead, with their own floor, so that the gap is a number on the record rather than
     * a number quietly absorbed into a larger denominator. Raise this as the scenes get their voices.
     */
    private static final double SCENE_COVERAGE_FLOOR = 0.0;

    /**
     * Salience-weighted coverage that must not fall (spec §15.5).
     *
     * <p>The raw percentage is the one the plan says can be gamed: a corpus could reach its number by
     * writing twenty-one voices for "see you later". The weighted measure prices a signature line at
     * eight farewells, so it can only be moved by writing the lines that make a villager sound like
     * somebody. Like the raw floor, this is a ratchet rather than a target — the plan's target is
     * 90%, and where this release stands is on the record in {@code reports/coverage.md}.
     *
     * <p>The number here is a little lower than the report's, and deliberately so: this measures
     * every pool the lang file carries, including the ones no beat references any more, while the
     * report measures the pools the contracts actually speak. A floor should be the harsher of the
     * two.
     */
    private static final double WEIGHTED_COVERAGE_FLOOR = 0.18;
    /** Say pools generated from {@code src/content}, which have no overlays yet. */
    private static final String SCENE_PREFIX = "conversations.scene.";

    /** pool key (without the "dialogue." prefix) -> number of variants in the base corpus. */
    private static Map<String, Integer> basePools;
    /** overlay prefix -> that namespace's pools, with their variant counts. */
    private static Map<String, Map<String, Integer>> overlays;
    /** signature pool -> the tier that designated it. */
    private static Map<String, SignatureBeat> signature;
    private static Set<String> debt;

    @BeforeAll
    static void load() throws IOException {
        basePools = poolsOf(ASSETS.resolve("mca_dialogue/lang/en_us.json"), "");

        overlays = new TreeMap<>();
        for (String prefix : Personalities.overlayPrefixes()) {
            Path file = ASSETS.resolve("mca_dialogue_" + prefix + "/lang/en_us.json");
            overlays.put(prefix, Files.exists(file) ? poolsOf(file, prefix + ".") : Map.of());
        }

        signature = new TreeMap<>();
        for (BeatContract beat : ContentFixture.catalog().beats()) {
            SignatureBeat tier = SignatureBeat.of(beat);
            if (tier != null) {
                signature.putIfAbsent(beat.say(), tier);
            }
        }

        debt = new TreeSet<>();
        for (String line : Files.readAllLines(DEBT)) {
            String trimmed = line.trim();
            if (!trimmed.isEmpty() && !trimmed.startsWith("#")) {
                debt.add(trimmed);
            }
        }
    }

    /** Reads a lang file into pool -> variant count, stripping {@code stripPrefix} and "dialogue.". */
    private static Map<String, Integer> poolsOf(Path file, String stripPrefix) throws IOException {
        Map<String, Integer> out = new TreeMap<>();
        JsonObject json = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            String key = entry.getKey();
            if (!stripPrefix.isEmpty()) {
                if (!key.startsWith(stripPrefix)) {
                    continue;
                }
                key = key.substring(stripPrefix.length());
            }
            if (!key.startsWith("dialogue.")) {
                continue;
            }
            key = key.substring("dialogue.".length());
            int slash = key.indexOf('/');
            String pool = slash < 0 ? key : key.substring(0, slash);
            out.merge(pool, 1, Integer::sum);
        }
        return out;
    }

    /** Every overlay prefix a family owns, and the pools it has written. */
    private static boolean covers(String prefix, String pool, int variants) {
        Integer got = overlays.getOrDefault(prefix, Map.of()).get(pool);
        return got != null && got >= variants;
    }

    @Test
    @DisplayName("every MCA personality belongs to exactly one voice family")
    void everyPersonalityHasAFamily() {
        List<String> problems = new ArrayList<>();
        Map<String, VoiceFamily> byPersonality = VoiceFamily.byPersonality();
        for (String prefix : Personalities.overlayPrefixes()) {
            if (!byPersonality.containsKey(prefix)) {
                problems.add(prefix + " has no voice family — add it to VoiceFamily or it gets no"
                        + " authored signature lines at all");
            }
        }
        Map<String, List<String>> homes = new LinkedHashMap<>();
        for (VoiceFamily family : VoiceFamily.values()) {
            for (String prefix : family.overlayPrefixes()) {
                homes.computeIfAbsent(prefix, p -> new ArrayList<>()).add(family.key());
            }
        }
        homes.forEach((prefix, families) -> {
            if (families.size() > 1) {
                problems.add(prefix + " is claimed by " + families + " — a personality speaks one way");
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("the beat catalog designates signature lines in every tier")
    void everyTierIsRepresented() {
        Map<SignatureBeat, Integer> counts = new LinkedHashMap<>();
        signature.values().forEach(tier -> counts.merge(tier, 1, Integer::sum));
        List<String> missing = new ArrayList<>();
        for (SignatureBeat tier : SignatureBeat.values()) {
            if (tier != SignatureBeat.CALLBACK && !counts.containsKey(tier)) {
                missing.add(tier.key());
            }
        }
        assertTrue(missing.isEmpty(), "no beat in the corpus is designated " + missing
                + " — either the corpus lost those lines or SignatureBeat stopped recognising them");
    }

    /**
     * The rule itself. A signature pool must exist, at full variant count, in every personality
     * namespace: an overlay with fewer variants than the base pool leaves MCA free to ask for a
     * variant index the personality never wrote, and the villager falls back mid-pool.
     */
    @Test
    @DisplayName("every signature pool is written for every personality, or is named in the ledger")
    void signaturePoolsAreCovered() {
        List<String> uncovered = new ArrayList<>();
        List<String> retired = new ArrayList<>();

        for (Map.Entry<String, SignatureBeat> entry : signature.entrySet()) {
            String pool = entry.getKey();
            Integer variants = basePools.get(pool);
            if (variants == null) {
                continue;  // the missing-lang lint owns this one
            }
            List<String> gaps = new ArrayList<>();
            for (String prefix : Personalities.overlayPrefixes()) {
                if (!covers(prefix, pool, variants)) {
                    gaps.add(prefix);
                }
            }
            if (gaps.isEmpty()) {
                if (debt.contains(pool)) {
                    retired.add(pool);
                }
            } else if (!debt.contains(pool)) {
                uncovered.add(pool + " (" + entry.getValue().key() + ", " + variants
                        + " variant(s)): missing in " + gaps.size() + " namespace(s), first "
                        + gaps.subList(0, Math.min(4, gaps.size())));
            }
        }

        List<String> problems = new ArrayList<>();
        if (!uncovered.isEmpty()) {
            problems.add(uncovered.size() + " signature pool(s) have no personality coverage and are"
                    + " not in the ledger — write them, or add them to " + DEBT + " with a reason:");
            problems.addAll(uncovered);
        }
        if (!retired.isEmpty()) {
            problems.add(retired.size() + " ledger entr(ies) are now covered and must be deleted from "
                    + DEBT + " so the ledger keeps meaning what it says:");
            problems.addAll(retired);
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("the ledger names only pools that are really signature beats")
    void ledgerIsNotStale() {
        List<String> problems = new ArrayList<>();
        for (String pool : debt) {
            if (!signature.containsKey(pool)) {
                problems.add(pool + " is in the ledger and is not a signature beat any more");
            } else if (!basePools.containsKey(pool)) {
                problems.add(pool + " is in the ledger and no longer exists in the corpus");
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * Spec §9.3's percentage target. It is expressed as a floor that may only be raised, because a
     * percentage that can fall is not a target — it is a description of whatever happened.
     */
    @Test
    @DisplayName("personality coverage across the whole corpus does not go backwards")
    void coverageDoesNotRegress() {
        Set<String> covered = new TreeSet<>();
        for (Map<String, Integer> pools : overlays.values()) {
            covered.addAll(pools.keySet());
        }
        covered.retainAll(basePools.keySet());

        Set<String> authored = new TreeSet<>(basePools.keySet());
        authored.removeIf(pool -> pool.startsWith(SCENE_PREFIX));
        Set<String> authoredCovered = new TreeSet<>(covered);
        authoredCovered.removeIf(pool -> pool.startsWith(SCENE_PREFIX));
        double authoredShare = (double) authoredCovered.size() / authored.size();
        assertTrue(authoredShare >= COVERAGE_FLOOR, String.format(
                "personality overlay coverage is %.1f%% of %d hand-authored say pools and the floor"
                        + " is %.1f%%; spec section 9.3 targets 25%%", authoredShare * 100,
                authored.size(), COVERAGE_FLOOR * 100));

        Set<String> scenes = new TreeSet<>(basePools.keySet());
        scenes.removeIf(pool -> !pool.startsWith(SCENE_PREFIX));
        if (!scenes.isEmpty()) {
            Set<String> sceneCovered = new TreeSet<>(covered);
            sceneCovered.removeIf(pool -> !pool.startsWith(SCENE_PREFIX));
            double sceneShare = (double) sceneCovered.size() / scenes.size();
            assertTrue(sceneShare >= SCENE_COVERAGE_FLOOR, String.format(
                    "generated scene overlay coverage is %.1f%% of %d say pools and the floor is"
                            + " %.1f%%", sceneShare * 100, scenes.size(),
                    SCENE_COVERAGE_FLOOR * 100));
        }
    }

    @Test
    @DisplayName("salience-weighted coverage does not go backwards either")
    void weightedCoverageDoesNotRegress() {
        Set<String> covered = new TreeSet<>();
        for (Map<String, Integer> pools : overlays.values()) {
            covered.addAll(pools.keySet());
        }
        covered.retainAll(basePools.keySet());

        Map<String, VoiceWeight> tiers = new TreeMap<>();
        for (BeatContract beat : ContentFixture.catalog().beats()) {
            tiers.merge(beat.say(), VoiceWeight.of(beat), (a, b) -> a.weight() >= b.weight() ? a : b);
        }

        long total = 0;
        long weighted = 0;
        for (String pool : basePools.keySet()) {
            int weight = tiers.getOrDefault(pool, VoiceWeight.FILLER).weight();
            total += weight;
            if (covered.contains(pool)) {
                weighted += weight;
            }
        }
        double share = total == 0 ? 0.0 : (double) weighted / total;
        assertTrue(share >= WEIGHTED_COVERAGE_FLOOR, String.format(
                "salience-weighted overlay coverage is %.1f%% and the floor is %.1f%%; spec"
                        + " section 15.5 targets 90%%", share * 100, WEIGHTED_COVERAGE_FLOOR * 100));
    }

    @Test
    @DisplayName("an overlay never invents a pool the base corpus does not have")
    void overlaysShadowRealPools() {
        List<String> problems = new ArrayList<>();
        overlays.forEach((prefix, pools) -> pools.forEach((pool, variants) -> {
            Integer base = basePools.get(pool);
            if (base == null) {
                problems.add(prefix + ": " + pool + " has no base line to override");
            } else if (variants > base) {
                problems.add(prefix + ": " + pool + " has " + variants + " variants and the base has "
                        + base + " — the extra ones can never be chosen");
            }
        }));
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("both locales carry the same overlay keys")
    void overlayLocalesAgree() throws IOException {
        List<String> problems = new ArrayList<>();
        for (String prefix : Personalities.overlayPrefixes()) {
            Path en = ASSETS.resolve("mca_dialogue_" + prefix + "/lang/en_us.json");
            Path pt = ASSETS.resolve("mca_dialogue_" + prefix + "/lang/pt_br.json");
            if (!Files.exists(en)) {
                problems.add(prefix + ": no English overlay at all");
                continue;
            }
            if (!Files.exists(pt)) {
                problems.add(prefix + ": no Portuguese overlay at all");
                continue;
            }
            Set<String> english = ContentFixture.readLang(en).keySet();
            Set<String> portuguese = ContentFixture.readLang(pt).keySet();
            Set<String> missing = new TreeSet<>(english);
            missing.removeAll(portuguese);
            Set<String> extra = new TreeSet<>(portuguese);
            extra.removeAll(english);
            if (!missing.isEmpty()) {
                problems.add(prefix + ": " + missing.size() + " key(s) with no Portuguese, first "
                        + new ArrayList<>(missing).subList(0, Math.min(3, missing.size())));
            }
            if (!extra.isEmpty()) {
                problems.add(prefix + ": " + extra.size() + " Portuguese key(s) with no English, first "
                        + new ArrayList<>(extra).subList(0, Math.min(3, extra.size())));
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * A family is the authoring unit, so its members must be covered together: a signature pool
     * written for {@code confident} and not for {@code crabby} means a crabby villager falls back to
     * the narrator on the line where personality matters most.
     *
     * <p>It deliberately does <em>not</em> require the members to say the same words. Some overlays
     * predate the families and are authored per personality, which is strictly better than a shared
     * line; flattening those to enforce tidiness would take content away.
     */
    @Test
    @DisplayName("a voice family's members are covered together")
    void familiesAreCoveredTogether() {
        List<String> problems = new ArrayList<>();
        for (VoiceFamily family : VoiceFamily.values()) {
            for (Map.Entry<String, Integer> pool : basePools.entrySet()) {
                if (!signature.containsKey(pool.getKey())) {
                    continue;
                }
                List<String> have = new ArrayList<>();
                List<String> lack = new ArrayList<>();
                for (String prefix : family.overlayPrefixes()) {
                    (covers(prefix, pool.getKey(), pool.getValue()) ? have : lack).add(prefix);
                }
                if (!have.isEmpty() && !lack.isEmpty()) {
                    problems.add(family.key() + " is split on " + pool.getKey() + ": written for "
                            + have + ", missing for " + lack);
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("the six voice families cover the roster with none left over")
    void familiesPartitionTheRoster() {
        Set<String> claimed = new TreeSet<>();
        for (VoiceFamily family : VoiceFamily.values()) {
            claimed.addAll(family.overlayPrefixes());
        }
        assertEquals(new TreeSet<>(Personalities.overlayPrefixes()), claimed,
                "the voice families and the overlay roster must be the same set");
    }
}
