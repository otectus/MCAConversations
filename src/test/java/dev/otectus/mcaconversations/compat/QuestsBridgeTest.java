package dev.otectus.mcaconversations.compat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * The Quests gate must be closed by default: before {@code tryRegister()} runs (and forever on an
 * MCA-only install, where it never runs), {@link QuestsBridge#isAvailable()} is false and
 * {@link QuestsBridge#queries()} is null — so every quest-aware condition/action short-circuits without
 * touching a {@code dev.otectus.mcaquests.*} class. {@code tryRegister()} itself isn't exercised here: it
 * calls {@code ModList.get()}, which needs a Forge runtime absent from unit tests.
 */
class QuestsBridgeTest {

    @Test
    void defaultsToUnavailableWithNoQueries() {
        assertFalse(QuestsBridge.isAvailable());
        assertNull(QuestsBridge.queries());
    }
}
