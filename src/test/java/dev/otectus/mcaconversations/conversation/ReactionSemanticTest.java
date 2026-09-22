package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.support.TestPaths;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.check.CheckTier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Conversation reactions (Townstead spec §12): what plays, with which tags, and what ships. */
class ReactionSemanticTest {

    private static final Path REACTIONS = TestPaths.of("src/main/resources/data/mcaconversations/townstead/reactions");
    /** Emotecraft's built-in emotes, the ones Townstead's backend knows how long to lock for. */
    private static final Set<String> BUILT_IN_EMOTES = Set.of("backflip", "clap", "club_penguin_dance", "crying",
            "here", "kazotsky_kick", "palm", "point", "roblox_potion_dance", "twerk", "waving");

    @Test
    @DisplayName("a ruptured, rebuffed or awkward reply says so; a landed one says how; a plain one plays nothing")
    void derivation() {
        assertEquals(Optional.of(ReactionSemantic.HURT),
                ReactionSemantic.derive(null, OutcomeFamily.HURT, null, null, 0));
        assertEquals(Optional.of(ReactionSemantic.BOUNDARY),
                ReactionSemantic.derive(null, OutcomeFamily.BOUNDARY_CLOSED, null, null, 0));
        assertEquals(Optional.of(ReactionSemantic.REBUFF),
                ReactionSemantic.derive(CheckTier.REBUFF, null, StanceFamily.HUMOR, null, 0));
        assertEquals(Optional.of(ReactionSemantic.AWKWARD),
                ReactionSemantic.derive(CheckTier.PARTIAL, null, StanceFamily.HUMOR, null, 0));
        assertEquals(Optional.of(ReactionSemantic.AMUSED),
                ReactionSemantic.derive(CheckTier.SUCCESS, null, StanceFamily.HUMOR, null, 0));
        assertEquals(Optional.of(ReactionSemantic.ACKNOWLEDGE),
                ReactionSemantic.derive(null, OutcomeFamily.APPRECIATED, StanceFamily.EMPATHY, null, 0));
        assertEquals(Optional.of(ReactionSemantic.GRATEFUL),
                ReactionSemantic.derive(CheckTier.CRIT, null, StanceFamily.PRACTICAL_HELP, null, 0));
        assertEquals(Optional.of(ReactionSemantic.DISCLOSURE),
                ReactionSemantic.derive(null, OutcomeFamily.ENGAGED, StanceFamily.CURIOSITY, NpcSpeechAct.DISCLOSE, 0));
        assertEquals(Optional.of(ReactionSemantic.WARM),
                ReactionSemantic.derive(CheckTier.SUCCESS, null, StanceFamily.CANDOR, null, 0));
        assertEquals(Optional.of(ReactionSemantic.HURT), ReactionSemantic.derive(null, null, null, null, -2));
        assertEquals(Optional.empty(), ReactionSemantic.derive(null, OutcomeFamily.ACCEPTED, StanceFamily.CANDOR, null, 0),
                "an ordinary accepted line is not a pantomime");
    }

    @Test
    @DisplayName("reactions carry sanitised topic, stance, outcome, frontend, heart and semantic tags")
    void tags() {
        Set<String> tags = ReactionSemantic.tags("Mine: Pack/Topic!", StanceFamily.HUMOR, CheckTier.CRIT,
                ConversationSession.Frontend.CHAT, 3, "amused");
        assertTrue(tags.contains("mcaconversations:topic/mine__pack/topic_"), tags::toString);
        assertTrue(tags.contains("mcaconversations:stance/humor"));
        assertTrue(tags.contains("mcaconversations:outcome/crit"));
        assertTrue(tags.contains("mcaconversations:frontend/chat"));
        assertTrue(tags.contains("mcaconversations:heart/increased"));
        assertTrue(tags.contains("mcaconversations:semantic/amused"));
        assertTrue(ReactionSemantic.tags(null, null, null, ConversationSession.Frontend.GUI, 0, null)
                .containsAll(Set.of("mcaconversations:frontend/gui", "mcaconversations:heart/unchanged")));
        assertEquals("unknown", ReactionSemantic.sanitize("   "));
        assertEquals(64, ReactionSemantic.sanitize("x".repeat(200)).length());
    }

    @Test
    @DisplayName("every semantic ships a reaction, and every shipped reaction is heart-neutral and bounded")
    void shippedReactionsAreSafe() throws IOException {
        for (ReactionSemantic semantic : ReactionSemantic.values()) {
            assertEquals("mcaconversations:conversation_" + semantic.key(), semantic.reactionId());
            assertTrue(Files.exists(REACTIONS.resolve("conversation_" + semantic.key() + ".json")),
                    semantic + " has no shipped reaction");
        }
        List<String> problems = new ArrayList<>();
        try (Stream<Path> files = Files.list(REACTIONS)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".json")).toList()) {
                JsonObject json = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                String name = file.getFileName().toString();
                if (!"townstead:reaction/v2".equals(json.get("schema").getAsString())) problems.add(name + ": schema");
                if (json.get("hearts").getAsInt() != 0) problems.add(name + ": hearts must be 0");
                if (json.has("do") || json.has("when")) problems.add(name + ": no Pheno action or condition");
                if (seconds(json.get("cooldown").getAsString()) > 60) problems.add(name + ": cooldown over 60s");
                if (seconds(json.get("lock").getAsString()) > 5) problems.add(name + ": lock over 5s");
                if (json.get("mirror_radius").getAsInt() != 0 || json.get("mirror_chance").getAsDouble() != 0) {
                    problems.add(name + ": mirroring");
                }
                if (!json.getAsJsonArray("triggers").isEmpty()) problems.add(name + ": fires only from a conversation");
                JsonArray choices = json.getAsJsonArray("choices");
                if (choices.isEmpty()) problems.add(name + ": no choice");
                for (JsonElement choice : choices) {
                    JsonObject c = choice.getAsJsonObject();
                    if (c.has("do") || c.has("when")) problems.add(name + ": choice has a Pheno action");
                    JsonObject animation = c.getAsJsonObject("animation");
                    if (!"emotecraft".equals(animation.get("type").getAsString())
                            || !BUILT_IN_EMOTES.contains(animation.get("id").getAsString())) {
                        problems.add(name + ": not an Emotecraft built-in emote");
                    }
                    if (!animation.get("allow_movement").getAsBoolean()) problems.add(name + ": stops the villager");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    private static double seconds(String duration) {
        return Double.parseDouble(duration.substring(0, duration.length() - 1));
    }

    @Test
    @DisplayName("a submission settles once: nested scopes join, and a side effect is claimed once per submission")
    void submissionsNestAndClaimOnce() {
        try (ConversationOutcomes.Submission outer = ConversationOutcomes.begin(null, null, "q", "a",
                ConversationSession.Frontend.GUI)) {
            try (ConversationOutcomes.Submission inner = ConversationOutcomes.begin(null, null, "q", "a",
                    ConversationSession.Frontend.CHAT)) {
                assertSame(outer, inner, "the chat path inside the choice service joins, it does not settle");
            }
            assertTrue(ConversationOutcomes.active().isPresent(), "only the outermost close settles");
            assertTrue(ConversationOutcomes.recordHearts(2));
        }
        assertTrue(ConversationOutcomes.active().isEmpty());
        assertFalse(ConversationOutcomes.recordHearts(2), "outside a reply the caller reports it itself");

        ConversationSession session = new ConversationSession(UUID.randomUUID(), 0L);
        long first = session.beginSubmission();
        assertTrue(session.claimSideEffect("outcome", "settle"));
        assertFalse(session.claimSideEffect("outcome", "settle"), "a second settle is a no-op");
        assertEquals(first + 1, session.beginSubmission());
        assertTrue(session.claimSideEffect("outcome", "settle"), "the next reply settles again");
        assertTrue(session.claimTransaction("t1"), "the duplicate-packet guard is untouched");

        try (ConversationOutcomes.Submission again = ConversationOutcomes.begin(null, null, "q", "a",
                ConversationSession.Frontend.GUI)) {
            assertNotSame(null, again);
        }
    }
}
