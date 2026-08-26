package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.check.CheckInputs;
import dev.otectus.mcaconversations.check.CheckResolver;
import dev.otectus.mcaconversations.check.CheckTier;
import dev.otectus.mcaconversations.template.TemplateVariable;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The MCA: Reputation companion-release regression suite (spec §36.4).
 *
 * <p>The single most important thing asserted here is a <b>negative</b>: with MCA: Reputation absent,
 * nothing about Conversations changes. Every check resolves to the tier it always did, every
 * reputation condition scores neutral, and no code path can reach a class that is not installed.
 */
class ReputationIntegrationTest {

    @AfterEach
    void reset() {
        ReputationBridge.setAvailableForTest(false, null);
    }

    // ------------------------------------------------------------------
    // The check term (§30.3)
    // ------------------------------------------------------------------

    private static CheckInputs inputs(int publicStandingFit, int difficulty) {
        return new CheckInputs(20, 40, 3, publicStandingFit, 2, 5, difficulty, true, true);
    }

    /** With the mod absent the term is exactly 0, so every existing seeded outcome is untouched. */
    @Test
    void withoutReputationTheTermIsExactlyZero() {
        assertEquals(0, ReputationBridge.publicStandingFit(null, null, "trust"));
        assertFalse(ReputationBridge.isAvailable());
    }

    @Test
    void aZeroTermResolvesIdenticallyToTheOldFormula() {
        for (int difficulty = 0; difficulty <= 100; difficulty += 5) {
            CheckInputs withTerm = inputs(0, difficulty);
            assertEquals(CheckResolver.resolve(withTerm.withoutPublicStanding()),
                    CheckResolver.resolve(withTerm),
                    "difficulty " + difficulty + " must resolve identically with a zero standing term");
        }
    }

    @Test
    void theTermIsIncludedExactlyOnce() {
        // axis 20 + hearts(40/…) + fit 3 + roll 2 is fixed; only the standing term varies. A difficulty
        // sitting one point above the zero-term score must flip on a +1 term and no more than that.
        CheckInputs baseline = inputs(0, 0);
        int zeroScore = scoreOf(baseline);
        assertEquals(zeroScore + 1, scoreOf(inputs(1, 0)));
        assertEquals(zeroScore + 8, scoreOf(inputs(8, 0)));
        assertEquals(zeroScore - 8, scoreOf(inputs(-8, 0)));
    }

    /** Recovers the assembled score by finding the difficulty at which the outcome flips. */
    private static int scoreOf(CheckInputs template) {
        for (int difficulty = -200; difficulty <= 400; difficulty++) {
            CheckInputs probe = new CheckInputs(template.axisValue(), template.hearts(),
                    template.personalityFit(), template.publicStandingFit(), template.moodAdjust(),
                    template.roll(), difficulty, false, template.vectorEnabled());
            if (CheckResolver.resolve(probe) == CheckTier.REBUFF) {
                return difficulty - 1;
            }
        }
        throw new AssertionError("score never fell below a probe difficulty");
    }

    /** §30.3: standing must stay smaller than the tier margin, so it colours but cannot decide. */
    @Test
    void theTermIsClampedBelowTheTierMargin() {
        assertEquals(8, ReputationBridge.clampStandingFit(1000, "trust"));
        assertEquals(-8, ReputationBridge.clampStandingFit(-1000, "respect"));
        assertEquals(3, ReputationBridge.clampStandingFit(3, "trust"));
        assertTrue(Math.abs(ReputationBridge.clampStandingFit(Integer.MAX_VALUE, "trust")) < 15,
                "the term must stay under the resolver's tier margin");
    }

    /** §30.3: only trust and respect. Warmth, attraction, tension, familiarity stay private. */
    @Test
    void onlyTrustAndRespectReceiveATerm() {
        assertTrue(ReputationBridge.appliesToAxis("trust"));
        assertTrue(ReputationBridge.appliesToAxis("RESPECT"));
        for (String axis : List.of("warmth", "attraction", "tension", "familiarity", "nonsense")) {
            assertFalse(ReputationBridge.appliesToAxis(axis),
                    axis + " is private interpersonal state and must not see public standing");
            assertEquals(0, ReputationBridge.clampStandingFit(8, axis));
        }
        assertFalse(ReputationBridge.appliesToAxis(null));
        assertEquals(0, ReputationBridge.clampStandingFit(8, null));
    }

    @Test
    void aThrowingBackendDegradesToZeroRatherThanBreakingTheConversation() {
        ReputationBridge.setAvailableForTest(true, new ThrowingQueries());
        assertEquals(0, ReputationBridge.publicStandingFit(null, null, "trust"));
        assertEquals(0, ReputationBridge.score(null, null));
        assertEquals("", ReputationBridge.tierId(null, null));
    }

    // ------------------------------------------------------------------
    // Condition JSON (§30.2)
    // ------------------------------------------------------------------

    @Test
    void standingQueryParsesEveryField() {
        var query = ReputationQueryJson.standing(JsonParser.parseString("""
                {"min": 75, "max": 299, "min_tier": "friend", "max_tier": "honored",
                 "has_title": "mcareputation:village_guardian"}""").getAsJsonObject());
        assertEquals(75, query.min());
        assertEquals(299, query.max());
        assertEquals("friend", query.minTier());
        assertEquals("honored", query.maxTier());
        assertEquals("mcareputation:village_guardian", query.hasTitle());
        assertFalse(query.isEmpty());
    }

    @Test
    void anEmptyStandingQueryIsRecognisedAsEmpty() {
        assertTrue(ReputationQueryJson.standing(JsonParser.parseString("{}").getAsJsonObject()).isEmpty());
    }

    @Test
    void malformedStandingFieldsAreDroppedNotThrown() {
        var query = ReputationQueryJson.standing(JsonParser.parseString("""
                {"min": "not a number", "min_tier": "", "has_title": null}""").getAsJsonObject());
        assertTrue(query.isEmpty(), "every unusable field is dropped, leaving an empty query");
    }

    @Test
    void incidentQueryParsesListsAndBareStrings() {
        var query = ReputationQueryJson.incident(JsonParser.parseString("""
                {"types": ["mcareputation:villager_assaulted"], "statuses": ["Active", "APOLOGIZED"],
                 "tags": "crime", "known_to_speaker": true, "max_age": 168000}""").getAsJsonObject());
        assertEquals(List.of("mcareputation:villager_assaulted"), query.types());
        assertEquals(List.of("active", "apologized"), query.statuses(), "statuses normalise to lower case");
        assertEquals(List.of("crime"), query.tags(), "a bare string where a list is expected still works");
        assertTrue(query.knownToSpeaker());
        assertEquals(168000L, query.maxAgeTicks());
    }

    @Test
    void anAbsentIncidentQueryIsAllEmptyNotNull() {
        var query = ReputationQueryJson.incident(JsonParser.parseString("{}").getAsJsonObject());
        assertTrue(query.types().isEmpty());
        assertTrue(query.statuses().isEmpty());
        assertTrue(query.tags().isEmpty());
        assertFalse(query.knownToSpeaker());
        assertEquals(0L, query.maxAgeTicks());
    }

    // ------------------------------------------------------------------
    // Gossip candidate normalisation (§30.4)
    // ------------------------------------------------------------------

    @Test
    void externalGossipArgumentsAreCappedAtFour() {
        List<net.minecraft.network.chat.Component> many = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            many.add(net.minecraft.network.chat.Component.literal("arg" + i));
        }
        var candidate = new ReputationBridge.GossipCandidate(java.util.UUID.randomUUID(),
                "mcareputation:villager_assaulted", 100L, "condemnation", "phrase", many, -8);
        assertEquals(ReputationBridge.GossipCandidate.MAX_ARGUMENTS, candidate.arguments().size());
    }

    /** §30.4: the already-told identity stays the incident, which is what Conversations keys memory on. */
    @Test
    void theAlreadyToldIdentityIsTheIncident() {
        java.util.UUID incident = java.util.UUID.randomUUID();
        var candidate = new ReputationBridge.GossipCandidate(incident, "t", 1L, "praise", "p",
                List.of(), 4);
        assertEquals(incident, candidate.alreadyToldId());
    }

    // ------------------------------------------------------------------
    // Template variables (§30.7)
    // ------------------------------------------------------------------

    @Test
    void everyReputationTemplateVariableHasALocalizedFallback() throws IOException {
        for (String locale : List.of("en_us", "pt_br")) {
            String json = Files.readString(
                    Paths.get("src/main/resources/assets/mcaconversations/lang/" + locale + ".json"),
                    StandardCharsets.UTF_8);
            var lang = JsonParser.parseString(json).getAsJsonObject();
            for (TemplateVariable var : TemplateVariable.values()) {
                if (!var.name().startsWith("REPUTATION_")) {
                    continue;
                }
                assertTrue(lang.has(var.fallbackKey()),
                        "missing " + locale + " fallback for " + var.jsonName());
                assertFalse(lang.get(var.fallbackKey()).getAsString().isBlank(),
                        "blank " + locale + " fallback for " + var.jsonName());
            }
        }
    }

    // ------------------------------------------------------------------
    // The classloading seam (§30.1, §36.4)
    // ------------------------------------------------------------------

    private static final Path SOURCE_ROOT = Paths.get("src/main/java/dev/otectus/mcaconversations");

    @Test
    void onlyTheGuardedPackageNamesReputationTypes() throws IOException {
        List<String> offenders = new ArrayList<>();
        try (Stream<Path> files = Files.walk(SOURCE_ROOT)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".java")).toList()) {
                String relative = SOURCE_ROOT.relativize(file).toString().replace('\\', '/');
                if (relative.startsWith("compat/reputation/")) {
                    continue;
                }
                String source = Files.readString(file, StandardCharsets.UTF_8);
                if (source.contains("import dev.otectus.mcareputation.")) {
                    offenders.add(relative);
                }
            }
        }
        assertTrue(offenders.isEmpty(), () -> "MCA: Reputation imports outside compat/reputation:\n  "
                + String.join("\n  ", offenders));
    }

    @Test
    void theBridgeResolvesItsImplementationReflectively() throws IOException {
        String bridge = Files.readString(SOURCE_ROOT.resolve("compat/ReputationBridge.java"),
                StandardCharsets.UTF_8);
        assertTrue(bridge.contains("ModList.get().isLoaded(\"mcareputation\")"));
        assertTrue(bridge.contains("Class.forName("),
                "a direct reference would defeat the classloading gate");
        assertTrue(bridge.contains("catch (Throwable"),
                "API drift must disable the integration, never crash a conversation");
    }

    @Test
    void modsTomlDeclaresReputationAsOptional() throws IOException {
        String toml = Files.readString(Paths.get("src/main/resources/META-INF/mods.toml"),
                StandardCharsets.UTF_8);
        int index = toml.indexOf("modId=\"mcareputation\"");
        assertTrue(index > 0, "the optional dependency entry is missing");
        String block = toml.substring(index, Math.min(toml.length(), index + 200));
        assertTrue(block.contains("mandatory=false"));
        assertTrue(block.contains("ordering=\"AFTER\""));
    }

    @Test
    void theRegistrarRegistersBothConditionsUnconditionally() throws IOException {
        // §30.2: the keys must be known even without the mod, or a suite-authored pack fails to load.
        String registrar = Files.readString(SOURCE_ROOT.resolve("compat/mca/ConversationsMcaRegistrar.java"),
                StandardCharsets.UTF_8);
        // The registration entry points moved to McaHandles when MCA's package root stopped being
        // knowable at compile time; the keys and the unconditional-registration rule are unchanged.
        assertTrue(registrar.contains("McaHandles.registerCondition(\"conversations_reputation\""));
        assertTrue(registrar.contains("McaHandles.registerCondition(\"conversations_reputation_incident\""));
        assertTrue(registrar.contains("McaHandles.registerAction(\"conversations_reputation_signal\""));
        assertFalse(registrar.contains("import dev.otectus.mcareputation."),
                "the registrar is always loaded and must not name a Reputation type");
    }

    // ------------------------------------------------------------------
    // External gossip (§30.4, §36.4)
    // ------------------------------------------------------------------

    /**
     * §36.4: quest completion must not seed the generic {@code QUEST} gossip while Reputation is
     * active — its named quest incident is the canonical story, and seeding both would have villagers
     * telling the same deed twice. Source-scanned like the other quests-compat assertions, because
     * {@code compat.quests} names Quests types and cannot load on this suite's classpath.
     */
    @Test
    void questCompletionSeedsNoGenericGossipWhileReputationIsActive() throws IOException {
        String events = Files.readString(
                SOURCE_ROOT.resolve("compat/quests/ConversationsQuestsEvents.java"),
                StandardCharsets.UTF_8);
        assertTrue(events.contains("!shouldSuppressGenericQuestGossip()"),
                "the seeding branch must consult the suppression rule");
        assertTrue(events.contains("ReputationBridge.isAvailable()"),
                "the rule is Reputation-presence, via the always-loaded bridge");
        int memory = events.indexOf("rememberForever");
        int guard = events.indexOf("shouldSuppressGenericQuestGossip");
        assertTrue(memory >= 0 && memory < guard,
                "memories and state still apply either way; only the gossip seed is suppressed");
    }

    /** The gossip merge consults the same told-memory for both sources and shares one selection. */
    @Test
    void theGossipLogicMergesBothSourcesThroughOneSelection() throws IOException {
        String logic = Files.readString(SOURCE_ROOT.resolve("gossip/GossipConditionLogic.java"),
                StandardCharsets.UTF_8);
        assertTrue(logic.contains("NormalizedGossip.newest"),
                "condition and say action must share the §30.4 selection");
        assertTrue(logic.contains("gossipCandidates"),
                "the bridge supplies a candidate list, filtered against the told-memory here");
        assertTrue(logic.contains("MemoryIds.gossipTold(gossip.toldId())"),
                "external stories use the same once-per-teller memory as native ones");
    }

    // ------------------------------------------------------------------
    // Stubs
    // ------------------------------------------------------------------

    /** A façade that reports a fixed bias, for exercising the clamp without a running game. */
    private record StubQueries(int bias) implements ReputationBridge.ReputationQueries {

        @Override
        public int score(net.minecraft.server.level.ServerPlayer player,
                         net.minecraft.world.entity.Entity villager) {
            return bias;
        }

        @Override
        public String tierId(net.minecraft.server.level.ServerPlayer player,
                             net.minecraft.world.entity.Entity villager) {
            return "friend";
        }

        @Override
        public int checkBias(net.minecraft.server.level.ServerPlayer player,
                             net.minecraft.world.entity.Entity villager, String axis) {
            return bias;
        }

        @Override
        public boolean matchesStanding(net.minecraft.server.level.ServerPlayer player,
                                       net.minecraft.world.entity.Entity villager,
                                       ReputationBridge.StandingQuery query) {
            return true;
        }

        @Override
        public boolean matchesIncident(net.minecraft.server.level.ServerPlayer player,
                                       net.minecraft.world.entity.Entity villager,
                                       ReputationBridge.IncidentQuery query) {
            return true;
        }

        @Override
        public java.util.Optional<ReputationBridge.GossipCandidate> nextGossip(
                net.minecraft.server.level.ServerPlayer player, net.minecraft.world.entity.Entity teller,
                java.util.Set<String> types, long maxAgeTicks) {
            return java.util.Optional.empty();
        }

        @Override
        public boolean recordSignal(net.minecraft.server.level.ServerPlayer player,
                                    net.minecraft.world.entity.Entity villager, String incidentId,
                                    String visibility, String decisionId) {
            return true;
        }

        @Override
        public java.util.Optional<net.minecraft.network.chat.Component> recentKnownDeed(
                net.minecraft.server.level.ServerPlayer player,
                net.minecraft.world.entity.Entity villager) {
            return java.util.Optional.empty();
        }

        @Override
        public boolean hasUnresolvedNegativeIncident(net.minecraft.server.level.ServerPlayer player,
                                                     net.minecraft.world.entity.Entity villager) {
            return false;
        }
    }

    /** A façade that throws from everything, standing in for a broken or drifted Reputation build. */
    private static final class ThrowingQueries extends Object implements ReputationBridge.ReputationQueries {

        @Override
        public int score(net.minecraft.server.level.ServerPlayer player,
                         net.minecraft.world.entity.Entity villager) {
            throw new IllegalStateException("boom");
        }

        @Override
        public String tierId(net.minecraft.server.level.ServerPlayer player,
                             net.minecraft.world.entity.Entity villager) {
            throw new IllegalStateException("boom");
        }

        @Override
        public int checkBias(net.minecraft.server.level.ServerPlayer player,
                             net.minecraft.world.entity.Entity villager, String axis) {
            throw new IllegalStateException("boom");
        }

        @Override
        public boolean matchesStanding(net.minecraft.server.level.ServerPlayer player,
                                       net.minecraft.world.entity.Entity villager,
                                       ReputationBridge.StandingQuery query) {
            throw new IllegalStateException("boom");
        }

        @Override
        public boolean matchesIncident(net.minecraft.server.level.ServerPlayer player,
                                       net.minecraft.world.entity.Entity villager,
                                       ReputationBridge.IncidentQuery query) {
            throw new IllegalStateException("boom");
        }

        @Override
        public java.util.Optional<ReputationBridge.GossipCandidate> nextGossip(
                net.minecraft.server.level.ServerPlayer player, net.minecraft.world.entity.Entity teller,
                java.util.Set<String> types, long maxAgeTicks) {
            throw new IllegalStateException("boom");
        }

        @Override
        public boolean recordSignal(net.minecraft.server.level.ServerPlayer player,
                                    net.minecraft.world.entity.Entity villager, String incidentId,
                                    String visibility, String decisionId) {
            throw new IllegalStateException("boom");
        }

        @Override
        public java.util.Optional<net.minecraft.network.chat.Component> recentKnownDeed(
                net.minecraft.server.level.ServerPlayer player,
                net.minecraft.world.entity.Entity villager) {
            throw new IllegalStateException("boom");
        }

        @Override
        public boolean hasUnresolvedNegativeIncident(net.minecraft.server.level.ServerPlayer player,
                                                     net.minecraft.world.entity.Entity villager) {
            throw new IllegalStateException("boom");
        }
    }
}
