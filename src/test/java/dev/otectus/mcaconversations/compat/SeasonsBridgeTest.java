package dev.otectus.mcaconversations.compat;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * The Serene Seasons gate must be closed by default: before {@code tryRegister()} runs (and forever when
 * Serene Seasons is absent), {@link SeasonsBridge#isAvailable()} is false and {@link SeasonsBridge#queries()}
 * is null — so {@code SeasonContext} falls back to the calendar season without touching a
 * {@code sereneseasons.*} class. {@code tryRegister()} itself isn't exercised here: it calls
 * {@code ModList.get()}, which needs a Forge runtime absent from unit tests. The SPI seam (install/read
 * a pure-Minecraft-typed query façade) is verified directly.
 */
class SeasonsBridgeTest {

    @Test
    void defaultsToUnavailableWithNoQueries() {
        assertFalse(SeasonsBridge.isAvailable());
        assertNull(SeasonsBridge.queries());
    }

    @Test
    void installedQueryFacadeIsReadableThroughTheSeam() {
        SeasonsBridge.SeasonQueries fake = level -> Optional.of("autumn");
        try {
            SeasonsBridge.setQueries(fake);
            assertSame(fake, SeasonsBridge.queries());
            assertEquals(Optional.of("autumn"), SeasonsBridge.queries().seasonBucket(null));
        } finally {
            SeasonsBridge.setQueries(null); // don't leak into other tests
        }
    }
}
