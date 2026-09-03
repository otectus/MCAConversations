package dev.otectus.mcaconversations.identity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.otectus.mcaconversations.support.TestPaths;

/**
 * The determinism, constraint and distribution guarantees of villager identity (spec §21.1, §24.2).
 *
 * <p>These three properties are what separate an identity from a daily roll, and all three are easy
 * to break by accident:
 *
 * <ul>
 *   <li><b>Determinism</b> — a profile must be a pure function of the seed. Introduce a map whose
 *       iteration order varies, or reach for {@code Math.random}, and villagers quietly become
 *       different people between restarts.</li>
 *   <li><b>Constraints</b> — no villager may hold two anchors that contradict each other, none may
 *       hold one their age rules out, and none may be handed a token banned for their profession or
 *       personality. That last one is the anti-stereotype rule, and it is the reason bans exist as
 *       data rather than as an editorial note.</li>
 *   <li><b>Distribution</b> — over ten thousand seeds no ordinary token may dominate. A weight typo
 *       that made one interest four times commoner than the rest would pass every other test in the
 *       suite and make every villager in a world faintly the same.</li>
 * </ul>
 */
class VillagerIdentityGeneratorTest {

    private static final Path CATALOG =
            TestPaths.of("src/main/resources/data/mcaconversations/identity_tokens/base.json");

    /** A representative spread rather than the full matrix: enough to exercise every gate. */
    private static final String[][] SPEAKERS = {
            {"adult", "minecraft:farmer", "cultivation", "friendly"},
            {"adult", "minecraft:librarian", "knowledge", "introverted"},
            {"adult", "mca:guard", "defense", "confident"},
            {"adult", "minecraft:none", "untraded", "gloomy"},
            {"teen", "minecraft:none", "untraded", "peppy"},
            {"child", "minecraft:none", "untraded", "playful"},
    };

    private static IdentityCatalog catalog;

    @BeforeAll
    static void loadShippedCatalog() {
        catalog = load(CATALOG);
        IdentityCatalogLoader.setActiveForTesting(catalog);
    }

    @Test
    void shippedCatalogCanProduceACompleteProfile() {
        assertFalse(catalog.isEmpty(), "the shipped identity catalog parsed to nothing");
        assertTrue(catalog.isComplete(),
                "every token family needs at least one token, or no villager can be given a full profile");
    }

    @Test
    void theSameSeedAlwaysProducesTheSameProfile() {
        UUID villager = UUID.fromString("00000000-0000-4000-8000-000000000001");
        long seed = VillagerIdentityGenerator.seedFor(1234L, villager);

        VillagerIdentityRecord first = generate(seed, "adult", "minecraft:farmer", "cultivation", "friendly");
        for (int run = 0; run < 32; run++) {
            assertEquals(first,
                    generate(seed, "adult", "minecraft:farmer", "cultivation", "friendly"),
                    "generation is not a pure function of its inputs");
        }
    }

    @Test
    void theSeedIgnoresEverythingThatIsNotTheWorldAndTheVillager() {
        UUID villager = UUID.fromString("00000000-0000-4000-8000-000000000002");
        long seed = VillagerIdentityGenerator.seedFor(99L, villager);

        // The seed derivation takes exactly two inputs. Anything else — the day, the villager's
        // position, their name, which player is asking — must be incapable of reaching it.
        assertEquals(seed, VillagerIdentityGenerator.seedFor(99L, villager));
        assertNotEquals(seed, VillagerIdentityGenerator.seedFor(100L, villager),
                "two worlds must not generate the same village of people");
        assertNotEquals(seed,
                VillagerIdentityGenerator.seedFor(99L,
                        UUID.fromString("00000000-0000-4000-8000-000000000003")),
                "two villagers in one world must not be the same person");
    }

    @Test
    void differentVillagersInOneWorldDifferFromEachOther() {
        List<VillagerIdentityRecord> profiles = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            long seed = VillagerIdentityGenerator.seedFor(7L, new UUID(0L, i));
            profiles.add(generate(seed, "adult", "minecraft:farmer", "cultivation", "friendly"));
        }
        long distinct = profiles.stream().distinct().count();
        // Forty farmers of one personality sharing fewer than half as many profiles would mean the
        // corpus cannot tell them apart, whatever the wording does.
        assertTrue(distinct >= 20,
                "40 same-profession villagers produced only " + distinct + " distinct profiles");
    }

    @Test
    void noProfileEverHoldsTwoConflictingAnchors() {
        List<String> problems = new ArrayList<>();
        for (String[] speaker : SPEAKERS) {
            for (int i = 0; i < 500; i++) {
                VillagerIdentityRecord profile = generate(
                        VillagerIdentityGenerator.seedFor(i, new UUID(i, i)),
                        speaker[0], speaker[1], speaker[2], speaker[3]);
                List<String> held = new ArrayList<>(bareTokens(profile));
                for (int a = 0; a < held.size(); a++) {
                    for (int b = a + 1; b < held.size(); b++) {
                        if (catalog.conflict(held.get(a), held.get(b))) {
                            problems.add(held.get(a) + " + " + held.get(b) + " for " + speaker[1]);
                        }
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), "conflicting anchors generated: " + distinctFirst(problems));
    }

    @Test
    void noProfileHoldsATokenItsAgeOrProfessionForbids() {
        List<String> problems = new ArrayList<>();
        for (String[] speaker : SPEAKERS) {
            for (int i = 0; i < 400; i++) {
                VillagerIdentityRecord profile = generate(
                        VillagerIdentityGenerator.seedFor(i, new UUID(i, ~i)),
                        speaker[0], speaker[1], speaker[2], speaker[3]);
                for (String qualified : profile.qualifiedTokens()) {
                    if (qualified.startsWith("formative:")) {
                        continue;
                    }
                    int colon = qualified.indexOf(':');
                    IdentityFamily family = IdentityFamily.byKey(qualified.substring(0, colon)).orElse(null);
                    if (family == null) {
                        continue;
                    }
                    IdentityToken token = catalog.token(family, qualified.substring(colon + 1))
                            .orElse(null);
                    if (token == null) {
                        problems.add("unknown token " + qualified);
                        continue;
                    }
                    if (!token.isEligible(speaker[0], speaker[1], speaker[2], speaker[3])) {
                        problems.add(qualified + " given to " + speaker[0] + "/" + speaker[1]
                                + "/" + speaker[3] + " which it does not permit");
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), "ineligible anchors generated: " + distinctFirst(problems));
    }

    @Test
    void aChildGetsNoWorkStyle() {
        // Not a technicality: a work style decides which profession subjects recur and what kind of
        // help is welcome, and a child has no trade for either question to be about.
        for (int i = 0; i < 200; i++) {
            VillagerIdentityRecord profile = generate(
                    VillagerIdentityGenerator.seedFor(i, new UUID(i, 5L)),
                    "child", "minecraft:none", "untraded", "playful");
            assertEquals("", profile.workStyle(),
                    "a child was given the work style '" + profile.workStyle() + "'");
        }
    }

    @Test
    void noOrdinaryTokenDominatesAcrossTenThousandSeeds() {
        Map<String, Integer> counts = new TreeMap<>();
        int samples = 10_000;
        for (int i = 0; i < samples; i++) {
            VillagerIdentityRecord profile = generate(
                    VillagerIdentityGenerator.seedFor(4242L, new UUID(i, i * 31L)),
                    "adult", "minecraft:farmer", "cultivation", "friendly");
            for (String token : profile.qualifiedTokens()) {
                counts.merge(token, 1, Integer::sum);
            }
        }
        List<String> problems = new ArrayList<>();
        for (IdentityFamily family : IdentityFamily.values()) {
            Map<String, Integer> inFamily = new TreeMap<>();
            counts.forEach((token, count) -> {
                if (token.startsWith(family.key() + ":")) {
                    inFamily.put(token, count);
                }
            });
            if (inFamily.size() < 2) {
                continue;
            }
            int total = inFamily.values().stream().mapToInt(Integer::intValue).sum();
            int max = inFamily.values().stream().mapToInt(Integer::intValue).max().orElse(0);
            // A token may legitimately be commoner than its siblings — village_born is deliberately
            // weighted high — but no ordinary token should take more than 45% of its family, which is
            // the shape a weight typo produces.
            double share = total == 0 ? 0 : (double) max / total;
            if (share > 0.45) {
                problems.add(family.key() + " is " + Math.round(share * 100) + "% one token: " + inFamily);
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void everyShippedTokenIsReachable() {
        // A token nothing can ever generate is dead content: it costs a lang key, appears in the
        // coverage report, and no villager will ever hold it (spec §24.2).
        Map<String, Integer> counts = new HashMap<>();
        for (String[] speaker : SPEAKERS) {
            for (int i = 0; i < 2000; i++) {
                VillagerIdentityRecord profile = generate(
                        VillagerIdentityGenerator.seedFor(i * 7L, new UUID(i, speaker[1].hashCode())),
                        speaker[0], speaker[1], speaker[2], speaker[3]);
                for (String token : bareTokens(profile)) {
                    counts.merge(token, 1, Integer::sum);
                }
            }
        }
        List<String> unreachable = new ArrayList<>();
        for (IdentityToken token : catalog.all()) {
            if (!counts.containsKey(token.id())) {
                unreachable.add(token.qualifiedId());
            }
        }
        assertTrue(unreachable.isEmpty(),
                "tokens no simulated villager could ever hold: " + unreachable);
    }

    @Test
    void aliasesRewriteAProfileWithoutRerollingIt() {
        UUID villager = UUID.fromString("00000000-0000-4000-8000-00000000000a");
        VillagerIdentityRecord profile = generate(
                VillagerIdentityGenerator.seedFor(11L, villager),
                "adult", "minecraft:librarian", "knowledge", "introverted");

        String held = profile.values().iterator().next();
        IdentityCatalog renamed = IdentityCatalog.build(catalog.all(), Map.of(held, "fairness"));
        VillagerIdentityRecord migrated = profile.withAliasesResolved(renamed);

        assertEquals(profile.profileSeed(), migrated.profileSeed(),
                "an alias migration must never change the seed a profile was generated from");
        assertTrue(migrated.values().contains("fairness") || held.equals("fairness"),
                "the alias table did not rewrite the renamed token");
        assertEquals(profile.interests(), migrated.interests(),
                "an alias for one token must leave every other family alone");
    }

    private static VillagerIdentityRecord generate(long seed, String age, String profession,
                                                   String archetype, String personality) {
        Optional<VillagerIdentityRecord> generated =
                VillagerIdentityGenerator.generate(catalog, seed, age, profession, archetype, personality);
        assertTrue(generated.isPresent(),
                "the shipped catalog produced no profile for " + age + "/" + profession);
        return generated.get();
    }

    /** Bare token ids across every family, which is the form conflicts are declared in. */
    private static List<String> bareTokens(VillagerIdentityRecord profile) {
        List<String> out = new ArrayList<>();
        for (String qualified : profile.qualifiedTokens()) {
            out.add(qualified.substring(qualified.indexOf(':') + 1));
        }
        return out;
    }

    private static String distinctFirst(List<String> problems) {
        return problems.stream().distinct().limit(8).toList().toString()
                + (problems.size() > 8 ? " (+" + (problems.size() - 8) + " more)" : "");
    }

    static IdentityCatalog load(Path path) {
        try {
            JsonObject root = JsonParser.parseString(Files.readString(path)).getAsJsonObject();
            List<IdentityToken> tokens = new ArrayList<>();
            root.getAsJsonObject("tokens").entrySet().forEach(entry ->
                    tokens.add(IdentityToken.fromJson(entry.getKey(), entry.getValue().getAsJsonObject())));
            Map<String, String> aliases = new TreeMap<>();
            if (root.has("aliases")) {
                root.getAsJsonObject("aliases").entrySet().forEach(entry ->
                        aliases.put(entry.getKey(), entry.getValue().getAsString()));
            }
            return IdentityCatalog.build(tokens, aliases);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
