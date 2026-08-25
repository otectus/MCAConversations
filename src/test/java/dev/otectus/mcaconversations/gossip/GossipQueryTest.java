package dev.otectus.mcaconversations.gossip;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GossipQueryTest {

    private static JsonObject json(String s) {
        return JsonParser.parseString(s).getAsJsonObject();
    }

    @Test
    void parsesTypesAndMaxAge() {
        GossipQuery q = GossipQuery.fromJson(json("{\"types\": [\"marriage\", \"death\"], \"max_age\": 48000}"));
        assertEquals(EnumSet.of(GossipEventType.MARRIAGE, GossipEventType.DEATH), q.types());
        assertEquals(48000L, q.maxAgeTicks());
    }

    @Test
    void defaultsToAllTypesAndDefaultAge() {
        GossipQuery q = GossipQuery.fromJson(json("{}"));
        assertTrue(q.types().isEmpty()); // empty set = all types in GossipLog.query
        assertEquals(GossipQuery.DEFAULT_MAX_AGE, q.maxAgeTicks());
    }

    @Test
    void rejectsUnknownType() {
        assertThrows(IllegalArgumentException.class,
                () -> GossipQuery.fromJson(json("{\"types\": [\"scandal\"]}")));
    }

    @Test
    void gossipSayDirectiveDefaultsPrefix() {
        GossipSayDirective d = GossipSayDirective.fromJson(json("{\"types\": [\"birth\"]}"));
        assertEquals(GossipSayDirective.DEFAULT_PREFIX, d.phrasePrefix());
        assertEquals(EnumSet.of(GossipEventType.BIRTH), d.query().types());
    }
}
