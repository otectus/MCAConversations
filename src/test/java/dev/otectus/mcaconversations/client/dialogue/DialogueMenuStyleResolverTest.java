package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig.DialogueMenuStyle;
import dev.otectus.mcaconversations.support.TestPaths;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The 1.5.2 presentation configuration has two inputs and one answer, and every gate in the client —
 * the renderer, the mixin, the numeric shortcuts — reads that one answer. This pins the table in
 * §5.1 of the specification, including the part that matters most for existing installs: the
 * deprecated {@code numberedResponses=false} override still wins over any style the player never set.
 *
 * <p>{@code resolve} is a pure static so the table can be asserted without a loaded config spec.
 */
class DialogueMenuStyleResolverTest {

    @Test
    void numberedResponsesFalseAlwaysYieldsToMca() {
        for (DialogueMenuStyle configured : DialogueMenuStyle.values()) {
            assertEquals(DialogueMenuStyle.MCA_ORIGINAL,
                    ClientChoiceController.resolve(false, configured),
                    "the legacy override must win over " + configured);
        }
    }

    @Test
    void numberedResponsesTrueUsesTheConfiguredStyle() {
        assertEquals(DialogueMenuStyle.RESPONSIVE,
                ClientChoiceController.resolve(true, DialogueMenuStyle.RESPONSIVE));
        assertEquals(DialogueMenuStyle.MINIMAL,
                ClientChoiceController.resolve(true, DialogueMenuStyle.MINIMAL));
        assertEquals(DialogueMenuStyle.MCA_ORIGINAL,
                ClientChoiceController.resolve(true, DialogueMenuStyle.MCA_ORIGINAL));
    }

    @Test
    void aMissingStyleFallsBackToResponsive() {
        // A null arrives only from a spec that failed to load; the fallback is the shipped default
        // rather than an exception thrown inside a render path.
        assertEquals(DialogueMenuStyle.RESPONSIVE, ClientChoiceController.resolve(true, null));
    }

    @Test
    void unloadedClientConfigResolvesToResponsive() {
        // No config file is loaded in this JVM, so the underlying get() throws.
        assertEquals(DialogueMenuStyle.RESPONSIVE, ClientChoiceController.dialogueMenuStyle());
    }

    @Test
    void chatShortcutsDoNotDependOnDialogueStyle() throws IOException {
        // §27: the chat frontend is not the dialogue card. Choosing MCA's native menu must not
        // silently remove numeric replies from an entirely separate screen. The coupling is only
        // visible in the source, because with no config loaded every accessor answers its default.
        String source = Files.readString(TestPaths.of(
                "src/main/java/dev/otectus/mcaconversations/client/dialogue/ClientChoiceController.java"),
                StandardCharsets.UTF_8).replace("\r\n", "\n");
        int start = source.indexOf("public static boolean chatShortcutsEnabled()");
        assertTrue(start > 0, "chatShortcutsEnabled must still exist");
        String body = source.substring(start, source.indexOf("\n    }", start));
        assertTrue(body.contains("chatNumericShortcuts"));
        assertFalse(body.contains("numberingEnabled"),
                "chatShortcutsEnabled must not be gated on the graphical dialogue style");
    }
}
