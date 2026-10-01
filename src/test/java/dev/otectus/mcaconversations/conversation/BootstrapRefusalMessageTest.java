package dev.otectus.mcaconversations.conversation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A refused first load leaves the mod with no dialogue at all, and the operator's two remedies are
 * "fix it" or "remove the pack". The message names every pack that carried a refusal, and only those.
 */
class BootstrapRefusalMessageTest {

    private static ContentProblem problem(String pack, ContentSeverity severity) {
        return ContentProblem.of(ContentSection.CONVERSATION_SCENES, new ResourceOrigin(null, pack, "x.json"),
                "/scenes", "entry", severity, "reason", "message");
    }

    @Test
    @DisplayName("every pack with a refusal is named once, and packs with only notes are not")
    void namesRefusingPacksOnly() {
        assertEquals("pack file/a_pack, file/b_pack", ContentReloadCoordinator.refusedPacks(List.of(
                problem("file/b_pack", ContentSeverity.REFUSED),
                problem("file/a_pack", ContentSeverity.REFUSED),
                problem("file/a_pack", ContentSeverity.REFUSED),
                problem("file/noted", ContentSeverity.INFO))));
    }

    @Test
    @DisplayName("with no attributable refusal the message still reads as a sentence")
    void fallsBackWithoutAPack() {
        assertEquals("the pack at fault", ContentReloadCoordinator.refusedPacks(List.of()));
    }
}
