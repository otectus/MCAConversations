package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.chat.Addressing.Address;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tier-1 name vocative + tier-4 nearest resolution and vocative stripping (§5). */
class AddressingTest {

    private static final List<String> TOWN = List.of("Agnes", "Ilsa", "Sam");

    @Test
    void leadingVocativeTargetsAndStrips() {
        Address a = Addressing.resolve("Agnes, how's your day?", TOWN);
        assertTrue(a.named());
        assertEquals(0, a.targetIndex());
        assertEquals("how's your day?", a.message());
    }

    @Test
    void trailingCommaVocativeTargetsAndStrips() {
        Address a = Addressing.resolve("what are you afraid of, Ilsa?", TOWN);
        assertTrue(a.named());
        assertEquals(1, a.targetIndex());
        assertEquals("what are you afraid of", a.message());
    }

    @Test
    void bareNameTargetsWithEmptyRemainder() {
        Address a = Addressing.resolve("Agnes", TOWN);
        assertTrue(a.named());
        assertEquals(0, a.targetIndex());
        assertEquals("", a.message());
    }

    @Test
    void commaDelimitedVocativeToleratesTypo() {
        Address a = Addressing.resolve("Anges, hello", TOWN); // transposed Agnes
        assertTrue(a.named());
        assertEquals(0, a.targetIndex());
    }

    @Test
    void bareWordEqualToNameStemDoesNotFuzzyGrab() {
        // "same" (no comma) must NOT grab villager Sam — exact-only without a comma.
        Address a = Addressing.resolve("same here", TOWN);
        assertFalse(a.named(), "no comma → exact match only, and 'same' != 'sam'");
        assertEquals(0, a.targetIndex(), "falls through to nearest");
    }

    @Test
    void exactLeadingNameWithoutCommaStillAddresses() {
        Address a = Addressing.resolve("Sam come here", TOWN);
        assertTrue(a.named());
        assertEquals(2, a.targetIndex());
        assertEquals("come here", a.message());
    }

    @Test
    void unnamedMessageIsNearestAndNotNamed() {
        Address a = Addressing.resolve("heard any rumors lately?", TOWN);
        assertFalse(a.named());
        assertEquals(0, a.targetIndex());
        assertEquals("heard any rumors lately?", a.message());
    }

    @Test
    void nonMatchingLeadingWordFallsThroughToNearest() {
        Address a = Addressing.resolve("where is the market", TOWN);
        assertFalse(a.named());
        assertEquals(0, a.targetIndex());
    }

    @Test
    void noCandidatesYieldsNoTarget() {
        Address a = Addressing.resolve("hello?", List.of());
        assertEquals(-1, a.targetIndex());
        assertFalse(a.named());
    }

    @Test
    void trailingVocativeWithoutCommaIsNotAnAddress() {
        // Without a comma, a trailing name is treated as content, not a vocative.
        Address a = Addressing.resolve("have you seen Sam", TOWN);
        assertFalse(a.named());
    }

    // --- Phase 3: tier 2 (stickiness) + tier 3 (look-at) ---------------------------

    private static final double CONE_25 = Math.cos(Math.toRadians(25.0));
    private static final List<Double> NO_LOOK = List.of(0.0, 0.0, 0.0);

    @Test
    void twoArgFallsThroughToNearestUnchanged() {
        // The convenience overload has no sticky/look context — tier 1 then tier 4, as before.
        Address a = Addressing.resolve("any news", TOWN);
        assertFalse(a.named());
        assertFalse(a.directed());
        assertEquals(0, a.targetIndex());
    }

    @Test
    void stickyPartnerAnswersFollowUpWithoutBeingNamed() {
        Address a = Addressing.resolve("you could face it", TOWN, NO_LOOK, 1, CONE_25);
        assertFalse(a.named(), "no vocative");
        assertTrue(a.directed(), "sticky is a directed, single-responder target");
        assertEquals(1, a.targetIndex());
        assertEquals("you could face it", a.message(), "no vocative to strip");
    }

    @Test
    void nameAddressBeatsStickyPartner() {
        Address a = Addressing.resolve("Agnes, how are you", TOWN, NO_LOOK, 1, CONE_25);
        assertTrue(a.named());
        assertEquals(0, a.targetIndex(), "tier 1 outranks the sticky Ilsa at index 1");
    }

    @Test
    void lookAtPicksTheMostCenteredCandidateInCone() {
        List<Double> looks = List.of(0.2, 0.99, 0.5); // only Ilsa is inside the 25° cone
        Address a = Addressing.resolve("what is your job", TOWN, looks, -1, CONE_25);
        assertFalse(a.named());
        assertTrue(a.directed(), "look-at is directed");
        assertEquals(1, a.targetIndex());
    }

    @Test
    void stickyBeatsLookAt() {
        List<Double> looks = List.of(0.2, 0.2, 0.99); // Sam is in the cone
        Address a = Addressing.resolve("what is your job", TOWN, looks, 0, CONE_25);
        assertEquals(0, a.targetIndex(), "tier 2 sticky outranks the tier-3 look target");
    }

    @Test
    void noCandidateInConeFallsThroughToNearestAmbient() {
        List<Double> looks = List.of(0.5, 0.6, 0.4); // all below cos(25°)
        Address a = Addressing.resolve("any news", TOWN, looks, -1, CONE_25);
        assertFalse(a.named());
        assertFalse(a.directed(), "tier 4 is ambient");
        assertEquals(0, a.targetIndex());
    }

    @Test
    void nanConeDisablesLookAt() {
        List<Double> looks = List.of(0.99, 0.99, 0.99);
        Address a = Addressing.resolve("any news", TOWN, looks, -1, Double.NaN);
        assertFalse(a.directed(), "look-at disabled → ambient nearest");
        assertEquals(0, a.targetIndex());
    }
}
