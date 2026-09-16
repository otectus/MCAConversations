package dev.otectus.mcaconversations.client.dialogue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.otectus.mcaconversations.conversation.CloseReason;
import dev.otectus.mcaconversations.support.TestPaths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * What a closed window says, and whether the sentence it names actually ships.
 *
 * <p>The mapping is pure — a close reason in, a translation key out — so it can be checked here, and
 * checking it here is worth doing because the failure mode is invisible in code review: a key that
 * nothing translates renders in game as the raw key itself, in the action bar, at the exact moment a
 * player's conversation is taken away from them.
 */
class ClientChoiceCloseMessageTest {

    // ModDevGradle runs the suite from build/minecraft-junit, so repository paths go through
    // TestPaths rather than being resolved against the working directory.
    private static final Path LANG = TestPaths.of("src/main/resources/assets/mcaconversations/lang");
    private static final String TAKEN_OVER_KEY = "gui.mcaconversations.responses.taken_over";

    private static Map<String, String> locale(String file) throws IOException {
        return new Gson().fromJson(Files.readString(LANG.resolve(file)),
                TypeToken.getParameterized(Map.class, String.class, String.class).getType());
    }

    @Test
    @DisplayName("being taken over has a sentence of its own, not the one for an absent villager")
    void takenOverIsExplainedInItsOwnWords() {
        assertEquals(TAKEN_OVER_KEY, ClientChoiceMessages.explanationOf(CloseReason.TAKEN_OVER));
        assertNotEquals(ClientChoiceMessages.explanationOf(CloseReason.SPEAKER_UNAVAILABLE),
                ClientChoiceMessages.explanationOf(CloseReason.TAKEN_OVER),
                "a villager standing right there talking to somebody else is not an unavailable one");
    }

    @Test
    @DisplayName("every other ending keeps the sentence it already had, and the quiet ones stay quiet")
    void theOtherEndingsAreUnchanged() {
        assertEquals("gui.mcaconversations.responses.out_of_range",
                ClientChoiceMessages.explanationOf(CloseReason.OUT_OF_RANGE));
        assertEquals("gui.mcaconversations.responses.speaker_unavailable",
                ClientChoiceMessages.explanationOf(CloseReason.ATTACKED));
        assertEquals("gui.mcaconversations.responses.content_reloaded",
                ClientChoiceMessages.explanationOf(CloseReason.CONTENT_RELOADED));
        assertNull(ClientChoiceMessages.explanationOf(CloseReason.CLIENT_CLOSED),
                "being told \"you closed this\" for closing it is noise");
        assertNull(ClientChoiceMessages.explanationOf(CloseReason.COMPLETED));
        assertNull(ClientChoiceMessages.explanationOf(null));
    }

    @Test
    @DisplayName("the sentence ships in every locale the mod carries")
    void theKeyIsTranslatedEverywhere() throws IOException {
        for (String file : List.of("en_us.json", "pt_br.json")) {
            Map<String, String> entries = locale(file);
            assertTrue(entries.containsKey(TAKEN_OVER_KEY), file + " is missing " + TAKEN_OVER_KEY);
            assertFalse(entries.get(TAKEN_OVER_KEY).isBlank(), file + " translates it to nothing");
            assertNotEquals(entries.get("gui.mcaconversations.responses.speaker_unavailable"),
                    entries.get(TAKEN_OVER_KEY), file + " says the same thing twice");
        }
    }
}
