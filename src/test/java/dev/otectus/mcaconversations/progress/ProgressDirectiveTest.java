package dev.otectus.mcaconversations.progress;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.DepthClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Parsing and semantics of the progress and affection dialogue vocabulary (plan §6.2, §13.5). */
class ProgressDirectiveTest {

    private static JsonObject json(String raw) {
        return JsonParser.parseString(raw).getAsJsonObject();
    }

    // --- conversations_affection_apply ----------------------------------------

    @Test
    @DisplayName("an affection directive parses and clamps its delta at parse time")
    void affectionParses() {
        AffectionApply directive = AffectionApply.fromJson(json("""
                {"decision": "day.rough.empathize", "delta": 99, "budget": "quick", "policy": "once"}"""));
        assertEquals("day.rough.empathize", directive.decision());
        assertEquals(AffectionMath.MAX_AUTHORED_DELTA, directive.delta());
        assertEquals(Optional.of(DepthClass.QUICK), directive.budget());
        assertEquals(ReplayPolicy.ONCE, directive.policy());
    }

    @Test
    @DisplayName("policy defaults to daily_repeat so a pack that omits it still behaves safely")
    void affectionDefaults() {
        AffectionApply directive = AffectionApply.fromJson(json("""
                {"decision": "day.rough.empathize", "delta": 1}"""));
        assertEquals(ReplayPolicy.DAILY_REPEAT, directive.policy());
        assertTrue(directive.budget().isEmpty(), "an absent budget defers to the live session");
    }

    @Test
    @DisplayName("an affection directive without a decision id or delta is refused")
    void affectionRequiresIdentity() {
        assertThrows(IllegalArgumentException.class, () ->
                AffectionApply.fromJson(json("{\"delta\": 2}")));
        assertThrows(IllegalArgumentException.class, () ->
                AffectionApply.fromJson(json("{\"decision\": \"day.x\"}")));
        assertThrows(IllegalArgumentException.class, () ->
                AffectionApply.fromJson(json("{\"decision\": \"Day Rough\", \"delta\": 1}")));
        assertThrows(IllegalArgumentException.class, () ->
                AffectionApply.fromJson(json("{\"decision\": \"day.x\", \"delta\": 1, \"policy\": \"never\"}")));
    }

    // --- conversations_progress_apply -----------------------------------------

    @Test
    @DisplayName("an advance moves exactly one stage, however many it asks for")
    void advanceIsOneStageAtATime() {
        ProgressApply.Arc arc = (ProgressApply.Arc) ProgressApply.fromJson(json("""
                {"arc": "fears", "op": "advance", "to": 3}"""));
        assertEquals(1, ProgressApply.resolveStage(arc, 0, 3));
        assertEquals(2, ProgressApply.resolveStage(arc, 1, 3));
        assertEquals(3, ProgressApply.resolveStage(arc, 2, 3));
        assertEquals(3, ProgressApply.resolveStage(arc, 3, 3), "already at the top: stays");
    }

    @Test
    @DisplayName("an advance never exceeds the catalog's declared bound")
    void advanceRespectsTheCatalogBound() {
        ProgressApply.Arc arc = (ProgressApply.Arc) ProgressApply.fromJson(json("""
                {"arc": "fears", "op": "advance", "to": 5}"""));
        assertEquals(1, ProgressApply.resolveStage(arc, 0, 1));
        assertEquals(1, ProgressApply.resolveStage(arc, 1, 1));
    }

    @Test
    @DisplayName("regress loses ground but never goes below zero or moves forward")
    void regressLosesGround() {
        ProgressApply.Arc arc = (ProgressApply.Arc) ProgressApply.fromJson(json("""
                {"arc": "fears", "op": "regress", "to": 1}"""));
        assertEquals(1, ProgressApply.resolveStage(arc, 3, 3));
        assertEquals(0, ProgressApply.resolveStage(arc, 0, 3));

        ProgressApply.Arc toZero = (ProgressApply.Arc) ProgressApply.fromJson(json("""
                {"arc": "fears", "op": "regress", "to": 0}"""));
        assertEquals(0, ProgressApply.resolveStage(toZero, 2, 3));
    }

    @Test
    @DisplayName("hold documents that a branch deliberately does not move the arc")
    void holdDoesNothing() {
        ProgressApply.Arc arc = (ProgressApply.Arc) ProgressApply.fromJson(json("""
                {"arc": "fears", "op": "hold"}"""));
        assertEquals(2, ProgressApply.resolveStage(arc, 2, 3));
    }

    @Test
    @DisplayName("a progress directive must say exactly one thing")
    void progressRequiresExactlyOneTarget() {
        assertThrows(IllegalArgumentException.class, () -> ProgressApply.fromJson(json("{}")));
        assertThrows(IllegalArgumentException.class, () -> ProgressApply.fromJson(json("""
                {"arc": "fears", "op": "advance", "to": 1, "milestone": "fears.revelation"}""")));
        assertThrows(IllegalArgumentException.class, () -> ProgressApply.fromJson(json("""
                {"exclusive": "fears.support"}""")));
    }

    // --- conversations_progress -----------------------------------------------

    @Test
    @DisplayName("an arc-stage condition matches inside its declared range")
    void arcStageCondition() {
        ProgressQuery query = ProgressQuery.fromJson(json("{\"arc\": \"fears\", \"min\": 1, \"max\": 2}"));
        assertFalse(ProgressQuery.matches(query, 0, false, Optional.empty()));
        assertTrue(ProgressQuery.matches(query, 1, false, Optional.empty()));
        assertTrue(ProgressQuery.matches(query, 2, false, Optional.empty()));
        assertFalse(ProgressQuery.matches(query, 3, false, Optional.empty()));
    }

    @Test
    @DisplayName("a milestone condition reads both ways round")
    void milestoneCondition() {
        ProgressQuery has = ProgressQuery.fromJson(json("{\"milestone\": \"fears.scar\"}"));
        assertTrue(ProgressQuery.matches(has, 0, true, Optional.empty()));
        assertFalse(ProgressQuery.matches(has, 0, false, Optional.empty()));

        ProgressQuery lacks = ProgressQuery.fromJson(json("""
                {"milestone": "fears.scar", "has": false}"""));
        assertTrue(ProgressQuery.matches(lacks, 0, false, Optional.empty()));
        assertFalse(ProgressQuery.matches(lacks, 0, true, Optional.empty()));
    }

    @Test
    @DisplayName("an exclusive condition distinguishes each side from 'not decided yet'")
    void exclusiveCondition() {
        ProgressQuery pledged = ProgressQuery.fromJson(json("""
                {"exclusive": "fears.support", "is": "pledged"}"""));
        ProgressQuery undecided = ProgressQuery.fromJson(json("""
                {"exclusive": "fears.support", "is": "none"}"""));

        assertTrue(ProgressQuery.matches(pledged, 0, false, Optional.of("pledged")));
        assertFalse(ProgressQuery.matches(pledged, 0, false, Optional.of("stepped_back")));
        assertFalse(ProgressQuery.matches(pledged, 0, false, Optional.empty()));
        assertTrue(ProgressQuery.matches(undecided, 0, false, Optional.empty()));
        assertFalse(ProgressQuery.matches(undecided, 0, false, Optional.of("pledged")));
    }

    @Test
    @DisplayName("a progress condition must ask exactly one question")
    void progressQueryRequiresExactlyOneTarget() {
        assertThrows(IllegalArgumentException.class, () -> ProgressQuery.fromJson(json("{}")));
        assertThrows(IllegalArgumentException.class, () -> ProgressQuery.fromJson(json("""
                {"arc": "fears", "milestone": "fears.scar"}""")));
        assertThrows(IllegalArgumentException.class, () -> ProgressQuery.fromJson(json("""
                {"arc": "fears", "min": 3, "max": 1}""")));
    }
}
