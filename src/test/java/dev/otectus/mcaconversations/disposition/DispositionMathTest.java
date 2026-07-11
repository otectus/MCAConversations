package dev.otectus.mcaconversations.disposition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DispositionMathTest {

    @Test
    void clampsToAxisBounds() {
        assertEquals(100, DispositionMath.clamp(DispositionAxis.TRUST, 250));
        assertEquals(-100, DispositionMath.clamp(DispositionAxis.TRUST, -250));
        assertEquals(42, DispositionMath.clamp(DispositionAxis.TRUST, 42));
        // Tension and Familiarity are unipolar: they floor at 0, not -100.
        assertEquals(0, DispositionMath.clamp(DispositionAxis.TENSION, -5));
        assertEquals(0, DispositionMath.clamp(DispositionAxis.FAMILIARITY, -1));
        assertEquals(100, DispositionMath.clamp(DispositionAxis.FAMILIARITY, 900));
    }

    @Test
    void decayHalvesTheDistanceToBaselineAtOneHalfLife() {
        long halfLife = DispositionAxis.WARMTH.defaultHalfLifeTicks();
        assertEquals(20, DispositionMath.decayed(DispositionAxis.WARMTH, 40, 0, halfLife, 1.0));
        // Works below baseline too: -40 toward baseline 0 becomes -20.
        assertEquals(-20, DispositionMath.decayed(DispositionAxis.WARMTH, -40, 0, halfLife, 1.0));
        // And toward a non-zero personality baseline: 40 -> 25 when baseline is 10.
        assertEquals(25, DispositionMath.decayed(DispositionAxis.WARMTH, 40, 10, halfLife, 1.0));
    }

    @Test
    void familiarityNeverDecays() {
        assertEquals(70, DispositionMath.decayed(DispositionAxis.FAMILIARITY, 70, 0,
                DispositionAxis.FAMILIARITY.defaultHalfLifeTicks() * 100, 1.0));
    }

    @Test
    void zeroElapsedIsIdentity() {
        assertEquals(40, DispositionMath.decayed(DispositionAxis.TRUST, 40, 0, 0, 1.0));
        assertEquals(40, DispositionMath.decayed(DispositionAxis.TRUST, 40, 0, -50, 1.0));
    }

    @Test
    void decayMultiplierZeroFreezesValues() {
        assertEquals(40, DispositionMath.decayed(DispositionAxis.TRUST, 40, 0,
                DispositionAxis.TRUST.defaultHalfLifeTicks() * 10, 0.0));
    }

    @Test
    void decayConvergesToBaselineNotPastIt() {
        // Truncation toward baseline means long elapsed times land exactly on baseline.
        assertEquals(10, DispositionMath.decayed(DispositionAxis.WARMTH, 40, 10,
                DispositionAxis.WARMTH.defaultHalfLifeTicks() * 40, 1.0));
        assertEquals(10, DispositionMath.decayed(DispositionAxis.WARMTH, -40, 10,
                DispositionAxis.WARMTH.defaultHalfLifeTicks() * 40, 1.0));
    }

    @Test
    void tensionDecaysFasterThanTrust() {
        assertTrue(DispositionAxis.TENSION.defaultHalfLifeTicks() < DispositionAxis.TRUST.defaultHalfLifeTicks(),
                "a single bad conversation must fade faster than earned trust");
    }

    @Test
    void axisKeysAreStableLowercaseNames() {
        assertEquals("trust", DispositionAxis.TRUST.key());
        assertEquals(DispositionAxis.TRUST, DispositionAxis.byKey("trust").orElseThrow());
        assertTrue(DispositionAxis.byKey("charisma").isEmpty());
        assertEquals(6, DispositionAxis.values().length);
    }
}
