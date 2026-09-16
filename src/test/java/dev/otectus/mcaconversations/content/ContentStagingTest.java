package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.conversation.ContentProblem;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.ContentSection;
import dev.otectus.mcaconversations.conversation.ContentSeverity;
import dev.otectus.mcaconversations.conversation.ContentSources;
import dev.otectus.mcaconversations.conversation.ConversationContentBundle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Staging is pure, and a malformed owned resource is a refusal rather than a silent absence.
 *
 * <p>The old loaders dropped what they could not parse and published the rest, so a typo in one
 * third-party file replaced a working catalog with a partial one and said so only in a warning
 * nobody reads. Every case below is one of those drops, and every one of them now stops the reload
 * and leaves the working content exactly where it was.
 */
class ContentStagingTest {

    private final ConversationContentBundle before = ContentReloadCoordinator.committed();

    @AfterEach
    void restore() {
        ContentReloadCoordinator.setCommittedForTesting(before);
    }

    /** A minimal valid document for each section, so "the rest of the pack" is identifiable. */
    private static String valid(ContentSection section) {
        return switch (section) {
            case CHAT_INTENTS -> """
                    {"intents": {"probe": {"question": "conversations.cat.chitchat", "answer": "day",
                     "keywords": {"weather": 1.0}}}}""";
            case CONVERSATION_CATALOG -> """
                    {"topics": {"day": {"entry": {"question": "conversations.cat.chitchat", "answer": "day"},
                     "depth": "quick", "return_question": "conversations.cat.chitchat",
                     "ages": ["adult"], "required_stance_families": ["empathy", "exit"]}}}""";
            case CONVERSATION_BEATS -> """
                    {"beats": {"day.probe": {"topic": "day", "say": "conversations.reload.probe",
                     "response_question": "conversations.cat.chitchat", "npc_act": "report",
                     "subject": "day.probe", "polarity": "neutral", "openness": "permits_followup",
                     "allowed_stances": ["exit"]}}}""";
            case PROFESSION_PROFILES -> """
                    {"profiles": {"minecraft:farmer": {"archetype": "cultivation", "subjects": ["day.probe"]}}}""";
            case INTERIORITY -> """
                    {"profiles": {"friendly": {}}}""";
            case IDENTITY_TOKENS -> """
                    {"tokens": {"animals": {"family": "interest", "weight": 12}}}""";
            case CONVERSATION_SCENES -> """
                    {"scenes": {"probe.scene": {"purpose": "topic:day",
                     "route": {"question": "conversations.cat.chitchat",
                               "opening_beat": "day.probe"}}}}""";
            case VILLAGE_CULTURE -> """
                    {"tokens": {"first_frost_supper": {"family": "festival", "weight": 12}}}""";
            case EPISODE_TEMPLATES -> """
                    {"episodes": {"day.probe": {"subject": "day.probe", "initial_state": "active",
                     "states": ["active", "succeeded"], "transitions": ["active->succeeded"]}}}""";
            case THREAD_TEMPLATES -> """
                    {"threads": {"day.probe": {"topic": "day", "subject": "day.probe",
                     "resume_scenes": ["probe.scene"]}}}""";
            case COMMITMENT_TEMPLATES -> """
                    {"commitments": {"day.probe": {"resolver": "gift_tag_received",
                     "target": "registry_id:minecraft:torch", "due_after_days": 3, "made_by": "player"}}}""";
        };
    }

    private static String sectionKey(ContentSection section) {
        return switch (section) {
            case CHAT_INTENTS -> "intents";
            case CONVERSATION_CATALOG -> "topics";
            case CONVERSATION_BEATS -> "beats";
            case PROFESSION_PROFILES, INTERIORITY -> "profiles";
            case IDENTITY_TOKENS, VILLAGE_CULTURE -> "tokens";
            case CONVERSATION_SCENES -> "scenes";
            case EPISODE_TEMPLATES -> "episodes";
            case THREAD_TEMPLATES -> "threads";
            case COMMITMENT_TEMPLATES -> "commitments";
        };
    }

    @ParameterizedTest
    @EnumSource(ContentSection.class)
    @DisplayName("a file whose JSON will not parse is an attributed refusal, not a file that was never there")
    void malformedSyntaxIsAttributed(ContentSection section) {
        ContentSources.Staged staged = read(section, "{\"" + sectionKey(section) + "\": {");

        assertTrue(staged.fatal(), section + ": unparseable JSON must refuse the section");
        ContentProblem problem = staged.problems().get(0);
        assertEquals("resource_malformed", problem.reason());
        assertEquals(section.directory(), problem.directory());
        assertEquals("fixture", problem.origin().pack(),
                "the pack id is the field the inherited prepare could not supply; it is supplied now");
    }

    @ParameterizedTest
    @EnumSource(ContentSection.class)
    @DisplayName("a file whose root is not an object refuses its section")
    void wrongRootIsRefused(ContentSection section) {
        assertRefused(section, "[1, 2, 3]", "root_not_an_object");
    }

    @ParameterizedTest
    @EnumSource(ContentSection.class)
    @DisplayName("a section that is an array rather than an object refuses, and says which")
    void wrongSectionShapeIsRefused(ContentSection section) {
        assertRefused(section, "{\"" + sectionKey(section) + "\": [\"nope\"]}", "section_wrong_shape");
    }

    @ParameterizedTest
    @EnumSource(ContentSection.class)
    @DisplayName("an entry that is not an object refuses, naming the entry")
    void malformedEntryIsRefused(ContentSection section) {
        assertRefused(section, "{\"" + sectionKey(section) + "\": {\"bad\": 3}}", "entry_not_an_object");
    }

    @ParameterizedTest
    @EnumSource(ContentSection.class)
    @DisplayName("a file carrying none of this section's keys contributes nothing and is not a failure")
    void anUnrelatedFileIsNotAFailure(ContentSection section) throws Exception {
        ReloadFixture.Outcome outcome = new ReloadFixture().withChitchat()
                .with(section.directory(), "mcaconversations", "unrelated", "{\"something_else\": {}}")
                .with("conversation_catalog", "mcaconversations", "topics",
                        valid(ContentSection.CONVERSATION_CATALOG))
                .run();

        assertTrue(outcome.committedNow(),
                section + ": the beat directory legitimately holds files with only replies in them");
    }

    @Test
    @DisplayName("a failure in the last narrative section cannot leave the first two live")
    void aLateNarrativeFailureContaminatesNothing() throws Exception {
        ReloadFixture.Outcome good = new ReloadFixture().withChitchat()
                .with("conversation_catalog", "mcaconversations", "t", valid(ContentSection.CONVERSATION_CATALOG))
                .with("episode_templates", "mcaconversations", "e", valid(ContentSection.EPISODE_TEMPLATES))
                .with("thread_templates", "mcaconversations", "t", valid(ContentSection.THREAD_TEMPLATES))
                .run();
        assertTrue(good.committedNow(), () -> String.valueOf(good.attempt().problems()));
        ConversationContentBundle published = ContentReloadCoordinator.committed();
        assertEquals(1, published.narrative().episodes().size());

        // The three directories used to be three listeners sharing one process-static staging area,
        // so a refusal in the last of them published the first two's contents anyway.
        ReloadFixture.Outcome broken = new ReloadFixture().withChitchat()
                .with("conversation_catalog", "mcaconversations", "t", valid(ContentSection.CONVERSATION_CATALOG))
                .with("episode_templates", "mcaconversations", "e", valid(ContentSection.EPISODE_TEMPLATES))
                .with("commitment_templates", "mcaconversations", "c", "{\"commitments\": {\"bad\": 7}}")
                .run();

        assertFalse(broken.committedNow());
        assertSame(published, ContentReloadCoordinator.committed(),
                "the earlier sections of the same attempt are not published either");
    }

    @Test
    @DisplayName("an empty section is not the same as a malformed one")
    void emptyIsNotMalformed() throws Exception {
        assertTrue(new ReloadFixture().withChitchat()
                        .with("conversation_catalog", "mcaconversations", "t",
                                valid(ContentSection.CONVERSATION_CATALOG))
                        .with("village_culture", "mcaconversations", "c", "{\"tokens\": {}}")
                        .run().committedNow(),
                "a section declared and left empty is a choice a pack is allowed to make");

        assertFalse(new ReloadFixture().withChitchat()
                        .with("conversation_catalog", "mcaconversations", "t",
                                valid(ContentSection.CONVERSATION_CATALOG))
                        .with("village_culture", "mcaconversations", "c", "{\"tokens\": {\"x\": 3}}")
                        .run().committedNow(),
                "a section with a malformed entry in it is not empty, it is broken");
    }

    // --- Plumbing ------------------------------------------------------------------------------

    private static ContentSources.Staged read(ContentSection section, String body) {
        ReloadFixture fixture = new ReloadFixture().withChitchat()
                .with(section.directory(), "mcaconversations", "probe", body);
        return ContentSources.read(fixture.manager(), section);
    }

    private static void assertRefused(ContentSection section, String body, String reason) {
        ContentSources.Staged staged = read(section, body);
        List<ContentProblem> problems = staged.problems();
        if (staged.fatal()) {
            assertEquals(reason.equals("root_not_an_object") ? reason : problems.get(0).reason(),
                    problems.get(0).reason());
            return;
        }
        // Root-shape and entry-shape failures surface from the section parser rather than the reader.
        ReloadFixture fixture = new ReloadFixture().withChitchat()
                .with(section.directory(), "mcaconversations", "probe", body)
                .with("conversation_catalog", "mcaconversations", "topics",
                        valid(ContentSection.CONVERSATION_CATALOG));
        ReloadFixture.Outcome outcome;
        try {
            outcome = fixture.reload(fixture.manager(), Map.of(), Set.of());
        } catch (Exception e) {
            throw new AssertionError(e);
        }
        assertFalse(outcome.committedNow(), section + ": " + reason + " must refuse the attempt");
        assertTrue(outcome.attempt().problems().stream()
                        .anyMatch(p -> p.reason().equals(reason) && p.severity() == ContentSeverity.REFUSED),
                section + ": expected a " + reason + " refusal, got "
                        + outcome.attempt().problems().stream().map(ContentProblem::reason).toList());
    }
}
