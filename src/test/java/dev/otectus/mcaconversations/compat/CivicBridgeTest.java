package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.support.TestPaths;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CivicBridgeTest {
    private static final UUID NPC = UUID.fromString("a0f63bf6-dd4f-4eb3-97ab-1e204fd733b8");

    public record FakeContext(UUID npc, String organization, String nameKey, String role,
                              boolean servicesAvailable, boolean introductionQualified,
                              boolean commissionQualified, List<String> reasons,
                              List<String> commissionReasons, long stateRevision, long policyRevision) {
    }

    public static final class FakeResult {
        public boolean success() { return true; }
        public String reason() { return "civic.introduction_sent"; }
        public Optional<UUID> settlement() {
            throw new AssertionError("the conversations bridge must never read hidden destinations");
        }
    }

    @Test
    void publicContextIsFlattenedAndBounded() throws Throwable {
        CivicBridge.Contact context = CivicBridge.decodeContextForTest(new FakeContext(
                NPC, "ultima_kingdoms:civic/lamplighters", "civic.organization/lamplighters",
                "guild_contact", true, true, false,
                List.of("civic.qualified"), List.of("civic.rank_required"), 14L, 9L));

        assertEquals(NPC, context.npc());
        assertEquals("ultima_kingdoms:civic/lamplighters", context.organization().toString());
        assertEquals(List.of("civic.rank_required"), context.commissionReasons());
        assertThrows(UnsupportedOperationException.class,
                () -> context.reasons().add("civic.not_allowed"));
    }

    @Test
    void malformedOrOversizedProviderContextFailsClosed() {
        assertThrows(IllegalArgumentException.class, () -> CivicBridge.decodeContextForTest(
                new FakeContext(NPC, "NOT A RESOURCE", "civic.name", "contact",
                        true, true, true, List.of(), List.of(), 1L, 1L)));
        assertThrows(IllegalArgumentException.class, () -> CivicBridge.decodeContextForTest(
                new FakeContext(NPC, "ultima_kingdoms:guild", "civic.name", "contact",
                        true, true, true, List.of("x", "x", "x", "x", "x", "x", "x", "x", "x"),
                        List.of(), 1L, 1L)));
    }

    @Test
    void actionReplyNeverTouchesSettlementIdentity() throws Throwable {
        assertEquals(new CivicBridge.Reply(true, "civic.introduction_sent"),
                CivicBridge.decodeReplyForTest(new FakeResult()));
    }

    /** Ultima's reason keys only exist while Ultima is installed; a request that never reached it
     *  must answer with a key this mod ships in every maintained locale, never a raw key. */
    @Test
    void aRequestThatNeverReachesUltimaUsesAKeyThisModTranslates() throws Exception {
        CivicBridge.resetForTest();
        CivicBridge.Reply reply = CivicBridge.request(CivicBridge.Action.INTRODUCTION, null, null);

        assertFalse(reply.success());
        assertEquals(CivicBridge.UNAVAILABLE_KEY, reply.reason());
        for (String locale : List.of("en_us", "pt_br")) {
            JsonObject lang = JsonParser.parseString(Files.readString(
                    TestPaths.of("src/main/resources/assets/mcaconversations/lang/" + locale + ".json")))
                    .getAsJsonObject();
            assertTrue(lang.has(CivicBridge.UNAVAILABLE_KEY), locale + " lacks " + CivicBridge.UNAVAILABLE_KEY);
        }
    }
}
