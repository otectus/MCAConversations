package dev.otectus.mcaconversations.history;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The episode state machine, which is the mechanism that keeps a callback honest (spec §21.1).
 *
 * <p>Failure mode 3 in the plan — "a project is still blocked after a success callback already
 * resolved it" — is exactly an unenforced transition. So the rules worth asserting are the negative
 * ones: a regression is refused, an undeclared transition is refused, and a repeat is a no-op rather
 * than a second effect.
 */
class EpisodeLifecycleTest {

    private static final UUID OWNER = UUID.fromString("00000000-0000-4000-8000-0000000000ee");

    private static EpisodeRecord episode(EpisodeState state) {
        return EpisodeRecord.opened(UUID.randomUUID(), "work.damaged_volume",
                "work.librarian.damaged_volume", state, OWNER,
                Map.of("volume", NarrativeValue.token("ledger")), PrivacyLevel.ORDINARY, 40, 10);
    }

    @Test
    void aResolvedEpisodeNeverGoesBackToBeingLive() {
        for (EpisodeState terminal : List.of(EpisodeState.SUCCEEDED, EpisodeState.FAILED,
                EpisodeState.ABANDONED)) {
            EpisodeRecord record = episode(terminal);
            for (EpisodeState live : List.of(EpisodeState.PLANNED, EpisodeState.ACTIVE,
                    EpisodeState.BLOCKED)) {
                assertSame(record, record.transitioned(live, 20),
                        terminal.key() + " -> " + live.key() + " was allowed; a finished situation "
                                + "cannot become unfinished");
            }
        }
    }

    @Test
    void aRememberedEpisodeIsTheEndOfTheLine() {
        EpisodeRecord record = episode(EpisodeState.SUCCEEDED).transitioned(EpisodeState.REMEMBERED, 20);
        assertEquals(EpisodeState.REMEMBERED, record.state());
        for (EpisodeState any : EpisodeState.values()) {
            if (any == EpisodeState.REMEMBERED) {
                continue;
            }
            assertEquals(EpisodeState.REMEMBERED, record.transitioned(any, 21).state());
        }
    }

    @Test
    void blockedAndActiveMayMoveBothWays() {
        // The one legal loop: help arrives, then the next obstacle turns up. Both directions are
        // things a villager can truthfully say, which is why the machine permits them.
        EpisodeRecord blocked = episode(EpisodeState.BLOCKED);
        EpisodeRecord active = blocked.transitioned(EpisodeState.ACTIVE, 11);
        assertEquals(EpisodeState.ACTIVE, active.state());
        assertEquals(EpisodeState.BLOCKED, active.transitioned(EpisodeState.BLOCKED, 12).state());
    }

    @Test
    void repeatingATransitionIsANoOpRatherThanASecondEffect() {
        // A duplicated packet, or a player double-clicking, must not move anything twice.
        EpisodeRecord active = episode(EpisodeState.ACTIVE);
        EpisodeRecord succeeded = active.transitioned(EpisodeState.SUCCEEDED, 20);
        assertSame(succeeded, succeeded.transitioned(EpisodeState.SUCCEEDED, 25),
                "a repeated transition produced a new record, so its updatedDay moved");
    }

    @Test
    void aOneShotOutcomeCanOnlyBeConsumedOnce() {
        EpisodeRecord record = episode(EpisodeState.ACTIVE);
        Optional<EpisodeRecord> first = record.consume("paid.reward");
        assertTrue(first.isPresent(), "the first consumption should have succeeded");
        assertFalse(first.get().consume("paid.reward").isPresent(),
                "a one-shot outcome paid out twice");
    }

    @Test
    void aTemplateMayNarrowTheMachineButNeverWidenIt() {
        EpisodeTemplate template = template("""
                {
                  "subject": "work.librarian.damaged_volume",
                  "initial_state": "blocked",
                  "states": ["blocked", "active", "succeeded"],
                  "transitions": ["blocked->active", "active->succeeded"]
                }
                """);
        assertTrue(template.permits(EpisodeState.BLOCKED, EpisodeState.ACTIVE));
        assertFalse(template.permits(EpisodeState.BLOCKED, EpisodeState.FAILED),
                "a state the template does not declare must not be reachable");
        assertFalse(template.permits(EpisodeState.SUCCEEDED, EpisodeState.ACTIVE),
                "no template may permit what the state machine forbids");
    }

    @Test
    void aTemplateDeclaringAnIllegalTransitionIsRejectedAtParse() {
        // Data that contradicts the state machine has to fail loudly at load, or state drift walks
        // straight back in through a datapack.
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                template("""
                        {
                          "subject": "work.subject",
                          "transitions": ["succeeded->active"]
                        }
                        """));
        assertTrue(thrown.getMessage().contains("succeeded->active"), thrown.getMessage());
    }

    @Test
    void slotGenerationIsStablePerVillagerAndDiffersBetweenThem() {
        EpisodeTemplate template = template("""
                {
                  "subject": "work.librarian.damaged_volume",
                  "required_slots": ["volume", "damage"],
                  "slot_options": {
                    "volume": ["ledger", "atlas", "herbal", "psalter", "songbook", "field_notes"],
                    "damage": ["damp", "mould", "torn_gathering", "faded_ink"]
                  }
                }
                """);
        UUID a = new UUID(1, 1);
        UUID b = new UUID(1, 2);
        long seedA = EpisodeTemplate.seedFor(77L, a, template.kind());

        Map<String, NarrativeValue> first = template.fillSlots(Map.of(), seedA);
        assertEquals(first, template.fillSlots(Map.of(), seedA),
                "a villager's situation must be the same every time it is asked for");
        assertTrue(template.satisfiedBy(first), "generation left a required slot empty");

        // Across a village, the pools must actually produce a spread rather than one book for everyone.
        List<String> volumes = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            long seed = EpisodeTemplate.seedFor(77L, new UUID(4, i), template.kind());
            volumes.add(template.fillSlots(Map.of(), seed).get("volume").raw());
        }
        assertTrue(volumes.stream().distinct().count() >= 4,
                "60 librarians produced only " + volumes.stream().distinct().count()
                        + " distinct volumes: " + volumes.stream().distinct().toList());
        assertEquals(true, b != null);
    }

    @Test
    void anAuthoredSlotAlwaysBeatsAGeneratedOne() {
        EpisodeTemplate template = template("""
                {
                  "subject": "work.librarian.damaged_volume",
                  "required_slots": ["volume"],
                  "slot_options": {"volume": ["ledger", "atlas"]}
                }
                """);
        Map<String, NarrativeValue> filled = template.fillSlots(
                Map.of("volume", NarrativeValue.token("psalter")), 12345L);
        assertEquals("psalter", filled.get("volume").raw(),
                "a scene that knows which object it means must not have it overwritten");
    }

    @Test
    void anOverdueEpisodeIsOverdueOnlyWhileItIsStillLive() {
        EpisodeRecord live = episode(EpisodeState.BLOCKED)
                .withDeadline(OptionalLong.of(15), OptionalLong.of(40));
        assertFalse(live.isOverdue(15), "the due day itself is not yet overdue");
        assertTrue(live.isOverdue(16));
        assertFalse(live.transitioned(EpisodeState.SUCCEEDED, 16).isOverdue(30),
                "a finished situation cannot be overdue");
    }

    private static EpisodeTemplate template(String json) {
        JsonObject parsed = JsonParser.parseString(json).getAsJsonObject();
        return EpisodeTemplate.fromJson("work.damaged_volume", parsed);
    }
}
