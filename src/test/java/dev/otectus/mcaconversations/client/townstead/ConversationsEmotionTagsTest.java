package dev.otectus.mcaconversations.client.townstead;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

/** The sidecar index: exact keys, and resolved text only when it is unambiguous. */
class ConversationsEmotionTagsTest {

    @Test
    @DisplayName("keys are exact; plain text answers only when one tagged line strips to it")
    void index() {
        ConversationsEmotionTags.Index index = ConversationsEmotionTags.build(Map.of(
                "a/1", "<sleepy>So tired.</sleepy> Sorry.",
                "b/1", "<sad>Same words.</sad>",
                "c/1", "<happy>Same words.</happy>",
                "d/1", "No tags at all."));
        assertEquals("<sleepy>So tired.</sleepy> Sorry.", index.byKey().get("a/1"));
        assertEquals("<sleepy>So tired.</sleepy> Sorry.", index.byPlain().get("So tired. Sorry."));
        assertNull(index.byPlain().get("Same words."), "a collision answers nothing rather than guessing");
        assertFalse(index.byPlain().containsKey("No tags at all."));
        assertEquals("So tired. Sorry.", ConversationsEmotionTags.strip("<sleepy>So tired.</sleepy> Sorry."));
    }
}
