package dev.otectus.mcaconversations.client.speech;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The speech association repair, against a fake language and a fake MCA association map
 * (feedback remediation WP-B1). The runtime half is a map write and a field write through the binding;
 * what can be wrong is the key, so the key is what is tested.
 */
class SpeechAssociationTest {

    /** A language with these keys; {@code applyFallback} as MCA's, reduced to the personality prefix. */
    private static final Set<String> LANGUAGE = Set.of(
            "dialogue.conversations.family.child.doing/1", "dialogue.conversations.family.child.doing/2",
            "friendly.dialogue.conversations.family.child.doing/2",
            "dialogue.conversations.family", "dialogue.chatmode.hail/1",
            "dialogue.main/1", "dialogue.main/2", "dialogue.main.single");

    private static final Function<String, String> APPLY_FALLBACK = raw -> {
        String stripped = dev.otectus.mcaconversations.locale.LineVoice.stripMarkers(raw);
        if (raw.contains("#Efriendly") && LANGUAGE.contains("friendly." + stripped)) {
            return "friendly." + stripped;
        }
        return stripped;
    };

    private static Optional<String> exact(String raw, boolean tagged, List<String> missing) {
        return SpeechAssociation.exactKey(raw, tagged, APPLY_FALLBACK, LANGUAGE::contains, missing::add);
    }

    @Test
    void aPinnedLineIsPointedAtTheVariantTheScreenRendered() {
        List<String> missing = new ArrayList<>();
        assertEquals(Optional.of("friendly.dialogue.conversations.family.child.doing/2"),
                exact("#Gfemale.#Efriendly.#Tchild.dialogue.conversations.family.child.doing/2", true, missing));
        assertTrue(missing.isEmpty());
    }

    @Test
    void theStaleOpenerIsReplacedInMcasMap() {
        // What MCA's TAIL association leaves behind for a pinned line: the last pooled key resolved.
        Map<Object, String> translations = new HashMap<>();
        Object contents = new Object();
        translations.put(contents, "dialogue.main/2");
        String exactKey = exact("#Tadult.dialogue.conversations.family", true, new ArrayList<>()).orElseThrow();
        String before = translations.put(contents, exactKey);
        assertEquals("dialogue.main/2", before);
        assertNotEquals(before, translations.get(contents));
        assertEquals("dialogue.conversations.family", translations.get(contents));
    }

    @Test
    void anUntaggedLineMcaOwnsIsLeftAlone() {
        assertTrue(exact("#Tadult.dialogue.main.single", false, new ArrayList<>()).isEmpty());
    }

    @Test
    void anUnpinnedPoolKeepsMcasOwnDrawAssociation() {
        // Pointing speech at a pool base would make it draw a second, different sentence.
        assertTrue(exact("dialogue.main", true, new ArrayList<>()).isEmpty());
        assertTrue(exact("dialogue.chatmode.hail", false, new ArrayList<>()).isEmpty());
    }

    @Test
    void anOwnedSingleKeyIsRepairedEvenUntagged() {
        assertEquals(Optional.of("dialogue.conversations.family"),
                exact("#Tadult.dialogue.conversations.family", false, new ArrayList<>()));
    }

    @Test
    void aMissingVariantFallsBackToTheStrippedKeyAndSaysSo() {
        Function<String, String> stale = raw -> "grumpy." + dev.otectus.mcaconversations.locale.LineVoice.stripMarkers(raw);
        List<String> missing = new ArrayList<>();
        Optional<String> key = SpeechAssociation.exactKey("#Egrumpy.dialogue.conversations.family", true,
                stale, LANGUAGE::contains, missing::add);
        assertEquals(Optional.of("dialogue.conversations.family"), key);
        assertEquals(List.of("grumpy.dialogue.conversations.family"), missing);
    }

    @Test
    void ownedPrefixesAreExactlyConversationsAndChatmode() {
        assertTrue(SpeechAssociation.owned("dialogue.conversations.x"));
        assertTrue(SpeechAssociation.owned("dialogue.chatmode.y"));
        assertTrue(!SpeechAssociation.owned("dialogue.main") && !SpeechAssociation.owned(null));
    }
}
