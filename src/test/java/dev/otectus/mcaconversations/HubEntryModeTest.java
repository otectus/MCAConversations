package dev.otectus.mcaconversations;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The hub-entry behaviour matrix, and the datapack file that implements the additive button.
 *
 * <pre>
 * mode      | MCA's Chat answer      | Conversations button
 * ----------+------------------------+---------------------
 * ADDITIVE  | unchanged              | visible   (default)
 * REPLACE   | opens Conversations    | hidden    (no duplicate entry)
 * HIDDEN    | unchanged              | hidden
 * </pre>
 *
 * MCA's AI chat is unaffected in every row: it is driven by
 * {@code MixinServerPlayNetworkHandler.handleChat} (naming a villager in normal chat) and never
 * routes through the dialogue system this mod touches.
 */
class HubEntryModeTest {

    private static final Path MAIN_JSON =
            Path.of("src/main/resources/data/mcaconversations/dialogues/main.json");

    @Test
    void defaultModeIsAdditiveSoBothEntriesWork() {
        // 0.7.x-0.9.x defaulted to replacing MCA's Chat, which left no way to reach MCA's own chat
        // answer. Additive is the documented default from 0.8.0 on.
        assertTrue(HubEntryMode.ADDITIVE.showsOwnButton());
        assertFalse(HubEntryMode.ADDITIVE.replacesMcaChat());
    }

    @Test
    void replaceModeHidesOurButtonToAvoidTwoEntriesToTheSamePlace() {
        assertTrue(HubEntryMode.REPLACE.replacesMcaChat());
        assertFalse(HubEntryMode.REPLACE.showsOwnButton());
    }

    @Test
    void hiddenModeLeavesMcaChatCompletelyAlone() {
        assertFalse(HubEntryMode.HIDDEN.replacesMcaChat());
        assertFalse(HubEntryMode.HIDDEN.showsOwnButton());
    }

    @Test
    void exactlyOneModeReplacesChatAndExactlyOneShowsAButton() {
        long replacing = 0;
        long showing = 0;
        for (HubEntryMode m : HubEntryMode.values()) {
            if (m.replacesMcaChat()) {
                replacing++;
            }
            if (m.showsOwnButton()) {
                showing++;
            }
        }
        assertEquals(1, replacing, "more than one mode reroutes MCA's Chat");
        assertEquals(1, showing, "more than one mode shows the Conversations button");
        // No mode may do both — that is the duplicate-button state the matrix rules out.
        for (HubEntryMode m : HubEntryMode.values()) {
            assertFalse(m.replacesMcaChat() && m.showsOwnButton(),
                    m + " would show two entries into the Conversations hub");
        }
    }

    /**
     * MCA merges same-named questions ({@code Dialogues.loadDialogue} → {@code q.merge(...)}) but
     * keeps the top-level flags of whichever file loaded last, and {@code data.forEach} over a
     * HashMap has no defined order. Our injected file must therefore mirror MCA's
     * {@code "silent": true} or the main menu's behaviour would flip at random.
     */
    @Test
    void injectedMainMenuMirrorsMcasFlagsAndAddsExactlyOneAnswer() throws IOException {
        JsonObject main = JsonParser.parseString(Files.readString(MAIN_JSON)).getAsJsonObject();

        assertTrue(main.has("silent") && main.get("silent").getAsBoolean(),
                "main.json must carry \"silent\": true to match MCA's own main question");

        JsonArray answers = main.getAsJsonArray("answers");
        assertEquals(1, answers.size(), "we inject exactly one answer into MCA's main menu");

        JsonObject answer = answers.get(0).getAsJsonObject();
        assertEquals("conversations", answer.get("name").getAsString(),
                "answer name must match QuestionMixin's CONVERSATIONS_ANSWER constant");

        JsonArray results = answer.getAsJsonArray("results");
        assertEquals(1, results.size());
        assertEquals("conversations",
                results.get(0).getAsJsonObject().getAsJsonObject("actions").get("next").getAsString(),
                "the button must open the Conversations hub question");
    }

    /** The label the button renders with; MCA builds it as dialogue.<question>.<answer>. */
    @Test
    void injectedAnswerHasALabelInEveryAuthoredLocale() throws IOException {
        for (String locale : new String[]{"en_us", "pt_br"}) {
            String lang = Files.readString(
                    Path.of("src/main/resources/assets/mca_dialogue/lang/" + locale + ".json"));
            assertTrue(JsonParser.parseString(lang).getAsJsonObject().has("dialogue.main.conversations"),
                    locale + " is missing the dialogue.main.conversations button label");
        }
    }
}
