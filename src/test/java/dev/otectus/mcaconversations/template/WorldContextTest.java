package dev.otectus.mcaconversations.template;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorldContextTest {

    @Test
    void clearWhenNoPrecipitation() {
        assertEquals("clear", WorldContext.weatherBucket(false, false));
    }

    @Test
    void rainWhenRainingWithoutThunder() {
        assertEquals("rain", WorldContext.weatherBucket(true, false));
    }

    @Test
    void stormTakesPrecedenceOverRain() {
        // A thunderstorm always reports raining too; storm must win.
        assertEquals("storm", WorldContext.weatherBucket(true, true));
        assertEquals("storm", WorldContext.weatherBucket(false, true));
    }
}
