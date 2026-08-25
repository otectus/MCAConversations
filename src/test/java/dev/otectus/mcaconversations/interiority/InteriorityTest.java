package dev.otectus.mcaconversations.interiority;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.StanceFamily;
import dev.otectus.mcaconversations.disposition.DispositionAxis;
import dev.otectus.mcaconversations.personality.Personalities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Interiority parsing, bounds, and the coverage the shipped data must have (plan §5.5, §13.5). */
class InteriorityTest {

    private static final Path PROFILES =
            Path.of("src/main/resources/data/mcaconversations/interiority/personalities.json");

    private static JsonObject json(String raw) {
        return JsonParser.parseString(raw).getAsJsonObject();
    }

    @Test
    @DisplayName("baselines and stance bias parse, and unlisted entries are simply neutral")
    void parsesSparsely() {
        InteriorityProfile profile = InteriorityProfile.fromJson("friendly", json("""
                {"baselines": {"warmth": 8}, "stance_bias": {"empathy": 6}}"""));
        assertEquals(8, profile.baseline(DispositionAxis.WARMTH));
        assertEquals(0, profile.baseline(DispositionAxis.TRUST));
        assertEquals(6, profile.stanceBias(StanceFamily.EMPATHY));
        assertEquals(0, profile.stanceBias(StanceFamily.HUMOR));
        assertEquals(0, profile.stanceBias(null));
    }

    @Test
    @DisplayName("values clamp so no personality can dominate a check or bank a head start")
    void clampsBounds() {
        InteriorityProfile profile = InteriorityProfile.fromJson("confident", json("""
                {"baselines": {"warmth": 900, "trust": -900}, "stance_bias": {"candor": 900, "dismissal": -900}}"""));
        assertEquals(InteriorityProfile.MAX_BASELINE, profile.baseline(DispositionAxis.WARMTH));
        assertEquals(-InteriorityProfile.MAX_BASELINE, profile.baseline(DispositionAxis.TRUST));
        assertEquals(InteriorityProfile.MAX_STANCE_BIAS, profile.stanceBias(StanceFamily.CANDOR));
        assertEquals(-InteriorityProfile.MAX_STANCE_BIAS, profile.stanceBias(StanceFamily.DISMISSAL));
    }

    @Test
    @DisplayName("a unipolar axis cannot be given a negative resting value")
    void unipolarAxesStayNonNegative() {
        InteriorityProfile profile = InteriorityProfile.fromJson("relaxed", json("""
                {"baselines": {"tension": -9, "familiarity": -9}}"""));
        assertEquals(0, profile.baseline(DispositionAxis.TENSION));
        assertEquals(0, profile.baseline(DispositionAxis.FAMILIARITY));
    }

    @Test
    @DisplayName("an unknown axis or stance family is a parse error, not a silent no-op")
    void rejectsUnknownVocabulary() {
        assertThrows(IllegalArgumentException.class, () ->
                InteriorityProfile.fromJson("odd", json("{\"baselines\": {\"vibes\": 3}}")));
        assertThrows(IllegalArgumentException.class, () ->
                InteriorityProfile.fromJson("odd", json("{\"stance_bias\": {\"shouting\": 3}}")));
    }

    @Test
    @DisplayName("a personality with no profile reads neutral rather than failing")
    void unknownPersonalityIsNeutral() {
        Interiority.setProfilesForTesting(java.util.Map.of());
        assertEquals(0, Interiority.profile("nonesuch").baseline(DispositionAxis.WARMTH));
        assertEquals(InteriorityProfile.NEUTRAL, Interiority.profile(""));
    }

    @Test
    @DisplayName("legacy MCA 7.6 spellings resolve to the same profile as their 7.7 successor")
    void legacySpellingsShareAVoice() {
        Interiority.setProfilesForTesting(java.util.Map.of("upbeat",
                InteriorityProfile.fromJson("upbeat", json("{\"baselines\": {\"warmth\": 6}}"))));
        assertEquals(6, Interiority.profile("witty").baseline(DispositionAxis.WARMTH));
        assertEquals(6, Interiority.profile("mca:upbeat").baseline(DispositionAxis.WARMTH));
        assertEquals(6, Interiority.profile("UPBEAT").baseline(DispositionAxis.WARMTH));
    }

    @Test
    @DisplayName("the shipped data covers every personality a villager can actually have")
    void shippedProfilesCoverTheRoster() throws IOException {
        JsonObject root = JsonParser.parseString(Files.readString(PROFILES)).getAsJsonObject();
        JsonObject profiles = root.getAsJsonObject("profiles");

        List<String> missing = new ArrayList<>();
        for (String personality : Personalities.CANONICAL) {
            if (!profiles.has(personality)) {
                missing.add(personality);
            }
        }
        // MCA 7.6 villagers can still be athletic, which 7.7 turned into a trait.
        for (String legacy : Personalities.LEGACY_ONLY) {
            if (!profiles.has(legacy)) {
                missing.add(legacy);
            }
        }
        assertTrue(missing.isEmpty(), "interiority profiles missing for: " + missing);
    }

    @Test
    @DisplayName("no shipped personality is punished by every ordinary stance")
    void everyPersonalityHasAWarmRouteAndAnHonestOne() throws IOException {
        JsonObject profiles = JsonParser.parseString(Files.readString(PROFILES))
                .getAsJsonObject().getAsJsonObject("profiles");

        List<String> problems = new ArrayList<>();
        for (String personality : profiles.keySet()) {
            if (personality.startsWith("_")) {
                continue;
            }
            InteriorityProfile profile = InteriorityProfile.fromJson(personality,
                    profiles.getAsJsonObject(personality));
            boolean anyWelcome = false;
            for (StanceFamily family : StanceFamily.values()) {
                if (!family.isAdversarial() && family != StanceFamily.EXIT
                        && profile.stanceBias(family) > 0) {
                    anyWelcome = true;
                    break;
                }
            }
            if (!anyWelcome) {
                problems.add(personality + ": no ordinary stance lands well on them");
            }
            if (profile.stanceBias(StanceFamily.DISMISSAL) > 0) {
                problems.add(personality + ": being dismissed should never be a bonus");
            }
            if (profile.stanceBias(StanceFamily.BOUNDARY_PUSH) > 0) {
                problems.add(personality + ": pushing a boundary should never be a bonus");
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }
}
