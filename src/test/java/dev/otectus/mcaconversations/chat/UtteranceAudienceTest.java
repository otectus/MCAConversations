package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.conversation.NpcSpeechAct;
import dev.otectus.mcaconversations.history.PrivacyLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Who may overhear a line is decided before it is scheduled. Silence about a line's privacy is
 * never permission, so every one of these cases starts from "only the person being spoken to".
 */
class UtteranceAudienceTest {

    @Test
    void recordedConfidencesNeverReachBystanders() {
        for (PrivacyLevel privacy : PrivacyLevel.values()) {
            boolean overheard = UtteranceAudience
                    .ofDialogue(privacy, NpcSpeechAct.REPORT, true).bystandersMayHear();
            assertTrue(privacy == PrivacyLevel.PUBLIC == overheard, privacy.name());
        }
    }

    @Test
    void personalDisclosureOverridesAPublicEpisode() {
        assertFalse(UtteranceAudience.ofDialogue(PrivacyLevel.PUBLIC, NpcSpeechAct.DISCLOSE, true)
                .bystandersMayHear());
        assertFalse(UtteranceAudience
                .ofDialogue(PrivacyLevel.PUBLIC, NpcSpeechAct.DISCLOSE_PROBLEM, true)
                .bystandersMayHear());
    }

    @Test
    void unknownMetadataFallsBackToTheParticipant() {
        assertFalse(UtteranceAudience.ofDialogue(null, NpcSpeechAct.REPORT, true).bystandersMayHear());
        assertFalse(UtteranceAudience.ofDialogue(null, null, true).bystandersMayHear());
    }

    @Test
    void metadataFromAnotherTurnIsNotUsedAtAll() {
        // A bystander interjecting is not the villager the session's privacy and speech act describe.
        assertFalse(UtteranceAudience.ofDialogue(PrivacyLevel.PUBLIC, NpcSpeechAct.REPORT, false)
                .bystandersMayHear());
    }

    @Test
    void staticLinesAreOverhearableOnlyWhereTheyAreClassified() {
        assertTrue(UtteranceAudience.ofStaticLine("chatmode.hail").bystandersMayHear());
        assertTrue(UtteranceAudience.ofStaticLine("chatmode.confused").bystandersMayHear());
        assertTrue(UtteranceAudience.ofStaticLine("chatmode.farewell").bystandersMayHear());
        // Sensitive or simply unlisted static content stays with the participant.
        assertFalse(UtteranceAudience.ofStaticLine("chatmode.insult").bystandersMayHear());
        assertFalse(UtteranceAudience.ofStaticLine("conversations.secret.confided").bystandersMayHear());
        assertFalse(UtteranceAudience.ofStaticLine(null).bystandersMayHear());
    }

    @Test
    void anAudienceIsNeverNullAndAlwaysStatesItsBasis() {
        UtteranceAudience audience = new UtteranceAudience(null, null);
        assertFalse(audience.bystandersMayHear());
        assertTrue(audience.basis() != null && !audience.basis().isBlank());
    }
}
