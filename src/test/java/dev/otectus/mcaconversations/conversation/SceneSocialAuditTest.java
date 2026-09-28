package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.scene.SceneCatalog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Scenes other packs add without a {@code social} block are reported, by pack (Stability spec §9.3):
 * loaded and eligible as written, and named for {@code /conversations social audit}. This mod's own
 * scenes are held to their contracts when they are compiled, so they are never on the list — under
 * either loader's name for the mod's pack.
 */
class SceneSocialAuditTest {

    private static final String BARE = "{\"purpose\":\"topic:age_and_life\",\"shape\":\"observe\","
            + "\"route\":{\"question\":\"example.scene.respond\",\"opening_beat\":\"example.scene.open\"}}";
    private static final String DECLARED = "{\"purpose\":\"topic:age_and_life\",\"shape\":\"observe\","
            + "\"context\":{\"social\":{\"contact\":[\"recognized\"]}},"
            + "\"route\":{\"question\":\"example.scene.respond\",\"opening_beat\":\"example.scene.open\"}}";

    private static StagedResource document(String pack, String scenes) {
        return new StagedResource(new ResourceOrigin(null, pack, "data/example/conversation_scenes/scenes.json"),
                JsonParser.parseString("{\"scenes\":{" + scenes + "}}"));
    }

    @Test
    @DisplayName("another pack's scenes without a social block are reported by pack; declared and bundled ones are not")
    void undeclaredScenesFromOtherPacksAreReported() {
        StagingResult<SceneCatalog> result = ContentStaging.scenes(List.of(
                document("file/villager_tales", "\"tales.first\":" + BARE + ",\"tales.second\":" + BARE
                        + ",\"tales.declared\":" + DECLARED),
                document("file/one_more", "\"more.declared\":" + DECLARED),
                document("mod:mcaconversations", "\"bundled.forge\":" + BARE),
                document("mod/mcaconversations", "\"bundled.neoforge\":" + BARE)));

        assertTrue(result.accepted(), () -> "staging refused: " + result.problems());
        assertEquals(Map.of("file/villager_tales", List.of("tales.first", "tales.second")),
                result.value().unauditedByPack());
        assertTrue(result.value().scene("tales.first").isPresent(), "a scene without a social block is still loaded");
        List<ContentProblem> notices = result.problems().stream()
                .filter(problem -> "social_contract_absent".equals(problem.reason())).toList();
        assertEquals(1, notices.size(), "one notice per pack");
        assertEquals(ContentSeverity.INFO, notices.get(0).severity(), "a report, never a refusal");
    }

    @Test
    @DisplayName("with nothing loaded there is nothing to report")
    void anEmptyCatalogReportsNothing() {
        assertTrue(SceneCatalog.EMPTY.unauditedByPack().isEmpty());
        assertTrue(ContentStaging.scenes(List.of()).value().unauditedByPack().isEmpty());
    }
}
