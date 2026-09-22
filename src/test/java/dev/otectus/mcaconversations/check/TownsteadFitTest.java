package dev.otectus.mcaconversations.check;

import com.google.gson.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** The {@code townstead_fit} check term (Townstead spec §9.1). */
class TownsteadFitTest {

    private static TownsteadFit fit(String raw) {
        return TownsteadFit.fromJson(JsonParser.parseString(raw));
    }

    @Test
    @DisplayName("good and bad tags add their points, clamped to the configured cap")
    void scores() {
        TownsteadFit fit = fit("{\"good_if_any\": [\"townstead:well_fed\"], \"good\": 6, "
                + "\"bad_if_any\": [\"townstead:exhausted\", \"townstead:working\"], \"bad\": -10}");
        assertEquals(6, fit.score(Set.of("townstead:well_fed"), 8));
        assertEquals(-8, fit.score(Set.of("townstead:working"), 8), "clamped to -cap");
        assertEquals(-4, fit.score(Set.of("townstead:well_fed", "townstead:exhausted"), 8));
        assertEquals(0, fit.score(Set.of(), 8), "no Townstead tags, no term");
        assertEquals(0, fit.score(Set.of("townstead:well_fed"), 0), "a zero cap turns the term off");
    }

    @Test
    @DisplayName("the term is exactly zero without Townstead, so no existing seeded outcome moves")
    void withoutTheTermNothingMoves() {
        CheckInputs in = new CheckInputs(10, 10, 0, 0, 0, 5, 20, true, true, 6);
        assertEquals(0, in.withoutTownsteadFit().townsteadFit());
        assertEquals(CheckTier.PARTIAL, CheckResolver.resolve(in.withoutTownsteadFit()));
        assertEquals(CheckTier.SUCCESS, CheckResolver.resolve(in),
                "a borderline check is exactly where the term is allowed to matter");
    }

    @Test
    @DisplayName("malformed fits are refused rather than read as neutral")
    void strictParsing() {
        assertThrows(IllegalArgumentException.class, () -> fit("{}"));
        assertThrows(IllegalArgumentException.class, () -> fit("{\"good_if_any\": [\"a\"], \"good\": -2}"));
        assertThrows(IllegalArgumentException.class, () -> fit("{\"bad_if_any\": [\"a\"], \"bad\": 3}"));
        assertThrows(IllegalArgumentException.class, () -> fit("{\"good_if_any\": [\"a\"]}"));
        assertThrows(IllegalArgumentException.class, () -> fit("{\"good_if_any\": [\"a\"], \"good\": 2, \"x\": 1}"));
    }
}
