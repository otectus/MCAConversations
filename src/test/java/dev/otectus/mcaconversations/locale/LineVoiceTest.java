package dev.otectus.mcaconversations.locale;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The rule that makes a pool of three read like three sentences instead of one.
 *
 * <p>These exercise {@link LineVoice}'s pure half — the pass algebra — because that is where the
 * behaviour lives. The impure half reads a world seed and two UUIDs and looks a pool size up in
 * {@link VariantPools}; none of that changes what is asserted here.
 *
 * <p>The property under test is deliberately stronger than "not the same twice in a row". Immediate
 * repeats are the ones a player consciously notices, but a uniform draw also clusters: from a pool of
 * three, ten draws typically show one sentence four or five times and another once. Exhausting the
 * pool before reusing it removes both, and it is the difference between a villager who has three
 * things to say and one who seems to have one.
 */
class LineVoiceTest {

    /** Walks a pool the way the runtime does, returning the sentence indices in order. */
    private static List<Integer> walk(int pool, int picks, long seed) {
        List<Integer> said = new ArrayList<>();
        int state = 0;
        for (int i = 0; i < picks; i++) {
            int chosen = LineVoice.chooseVariant(pool, state, seed);
            said.add(chosen);
            state = LineVoice.advance(pool, state, chosen);
        }
        return said;
    }

    @Test
    void aPoolIsExhaustedBeforeAnySentenceComesBack() {
        for (int pool = 2; pool <= LineVoice.MAX_MASKED_POOL; pool++) {
            List<Integer> said = walk(pool, pool, 987654321L + pool);
            assertEquals(pool, new HashSet<>(said).size(),
                    "pool of " + pool + " repeated a sentence before using them all: " + said);
        }
    }

    @Test
    void theSecondPassUsesThemAllAgain() {
        // The mask has to reset, or a villager falls silent on its own content after one pass.
        for (int pool = 2; pool <= LineVoice.MAX_MASKED_POOL; pool++) {
            List<Integer> said = walk(pool, pool * 2, 424242L + pool);
            Set<Integer> first = new HashSet<>(said.subList(0, pool));
            Set<Integer> second = new HashSet<>(said.subList(pool, pool * 2));
            assertEquals(pool, first.size(), "first pass, pool " + pool);
            assertEquals(pool, second.size(), "second pass, pool " + pool);
        }
    }

    @Test
    void aSentenceIsNeverSaidTwiceRunning() {
        // Including across the pass boundary, which is the only place the exhaustion rule alone would
        // have let one through: the last sentence of a full mask could otherwise open the next pass.
        for (int pool = 2; pool <= 12; pool++) {
            List<Integer> said = walk(pool, 200, 13579L * pool);
            for (int i = 1; i < said.size(); i++) {
                assertNotEquals(said.get(i - 1), said.get(i),
                        "pool " + pool + " repeated at once at index " + i + ": " + said);
            }
        }
    }

    @Test
    void everyChoiceIsInsideThePool() {
        for (int pool = 1; pool <= 12; pool++) {
            for (int chosen : walk(pool, 60, 24680L + pool)) {
                assertTrue(chosen >= 1 && chosen <= pool, "pool " + pool + " chose " + chosen);
            }
        }
    }

    @Test
    void aPoolWiderThanTheMaskStillNeverRepeatsAtOnce() {
        // Nothing we ship is this wide, but a datapack can be. The mask cannot describe it; the
        // no-immediate-repeat half of the rule still holds, which is the half that shows.
        List<Integer> said = walk(16, 300, 55555L);
        for (int i = 1; i < said.size(); i++) {
            assertNotEquals(said.get(i - 1), said.get(i), "wide pool repeated at once at index " + i);
        }
        assertTrue(new HashSet<>(said).size() >= 8, "a wide pool should still spread out: " + said);
    }

    @Test
    void aSinglelineIsAlwaysItself() {
        assertEquals(List.of(1, 1, 1, 1), walk(1, 4, 11L));
        assertEquals(List.of(1, 1, 1, 1), walk(0, 4, 11L));
    }

    @Test
    void twoPairsWalkThePoolDifferently() {
        // The pair is in the seed, so two players talking to the same villager, or one player talking
        // to two villagers of the same trade, do not hear the same sentences in the same order. If
        // they did, a second villager would sound like an echo of the first.
        List<Integer> a = walk(4, 8, 1L);
        List<Integer> b = walk(4, 8, 2L);
        assertNotEquals(a, b, "different seeds produced identical orders");
    }

    @Test
    void thePickIsDeterministicForTheSameSeedAndPass() {
        // The reason there is no RNG anywhere in this path: reopening a screen must not be a reroll,
        // and one utterance rendered to a speaker and to a bystander must be one sentence.
        assertEquals(walk(5, 20, 777L), walk(5, 20, 777L));
    }

    @Test
    void packingRoundTrips() {
        for (int used = 0; used < 256; used += 17) {
            for (int last = 0; last <= 8; last++) {
                int state = LineVoice.pack(used, last);
                assertEquals(used, LineVoice.usedMask(state), "mask");
                assertEquals(last, LineVoice.lastIndex(state), "last");
            }
        }
    }

    @Test
    void advanceStartsAFreshPassWhenTheMaskWasFull() {
        int pool = 3;
        int full = LineVoice.pack(0b111, 2);
        int next = LineVoice.advance(pool, full, 1);
        assertEquals(0b001, LineVoice.usedMask(next), "a pick after exhaustion opens the new pass");
        assertEquals(1, LineVoice.lastIndex(next));
    }

    @Test
    void markersAreStrippedBeforeThePoolIsLookedUp() {
        // The pool index is keyed on the plain lang key; MCA's gender/age/personality markers are a
        // prefix on the same key and would otherwise make every marked line look unpooled.
        assertEquals("dialogue.conversations.day.rough.empathize",
                LineVoice.stripMarkers("#Gmale.#Tadult.dialogue.conversations.day.rough.empathize"));
        assertEquals("dialogue.chatmode.hint", LineVoice.stripMarkers("dialogue.chatmode.hint"));
        assertEquals("#Tadult", LineVoice.stripMarkers("#Tadult"), "no dot: not a marker, left alone");
        assertEquals("", LineVoice.stripMarkers(null));
    }

    @Test
    void everyShippedPoolSizeBehaves() {
        // Guards the assumption the mask width rests on: our own corpus stays inside MAX_MASKED_POOL,
        // so every shipped line gets the full exhaustion rule and not just the fallback.
        int widest = 0;
        for (int pool = 2; pool <= 6; pool++) {
            List<Integer> said = walk(pool, pool, 31L * pool);
            assertEquals(pool, new HashSet<>(said).size(), "pool " + pool);
            widest = pool;
        }
        assertFalse(widest > LineVoice.MAX_MASKED_POOL, "shipped pools must fit the mask");
    }
}
