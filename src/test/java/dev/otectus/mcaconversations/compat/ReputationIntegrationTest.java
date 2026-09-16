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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import dev.otectus.mcaconversations.support.TestPaths;

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
        ReputationBridge.clearPendingRemarks();
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
    void malformedStandingFieldsNeverBecomeAnUnrestrictedQuery() {
        for (String json : List.of("{\"min\":\"not a number\"}", "{\"min_tier\":\"\"}",
                "{\"has_title\":null}", "{\"max\":1.5}", "{\"min\":2147483648}",
                "{\"min\":100,\"max\":20}")) {
            assertThrows(RuntimeException.class,
                    () -> ReputationQueryJson.standing(JsonParser.parseString(json).getAsJsonObject()),
                    json + " must fail closed instead of dropping its constraint");
        }
    }

    @Test
    void malformedIncidentFieldsCannotBroadenKnowledgeOrRecencyFilters() {
        for (String json : List.of("{\"types\":[\"mcareputation:theft\",{}]}", "{\"statuses\":[null]}",
                "{\"tags\":true}", "{\"tags\":\" \"}", "{\"known_to_speaker\":\"yes\"}",
                "{\"max_age\":-1}", "{\"max_age\":0.5}", "{\"max_age\":9223372036854775808}")) {
            assertThrows(RuntimeException.class,
                    () -> ReputationQueryJson.incident(JsonParser.parseString(json).getAsJsonObject()),
                    json + " must reject the whole query");
        }
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
    // Profile condition JSON (0.6.0, §16.2)
    // ------------------------------------------------------------------

    @Test
    void theProfileConditionParsesEveryField() {
        var query = ReputationQueryJson.profile(JsonParser.parseString("""
                {"scope": "speaker",
                 "recognition": {"min": 15, "max": 900, "min_tier": "recognized"},
                 "facets": {
                   "mcareputation:bravery": {"min": 10, "min_evidence": 2},
                   "mcareputation:violence": {"max": 0, "allow_unobserved": true}
                 },
                 "allow_partial_history": true}""").getAsJsonObject());
        assertEquals(ReputationBridge.ProfileQuerySpec.Scope.SPEAKER, query.scope());
        assertEquals(15, query.minRecognition());
        assertEquals(900, query.maxRecognition());
        assertEquals("recognized", query.minRecognitionTier());
        assertTrue(query.allowPartialHistory());
        assertEquals(2, query.facets().size());
        var bravery = query.facets().stream()
                .filter(facet -> facet.facet().equals("mcareputation:bravery"))
                .findFirst().orElseThrow();
        assertEquals(10, bravery.min());
        assertEquals(2, bravery.minEvidence());
        assertFalse(bravery.allowUnobserved(), "the absence escape hatch is never a default");
        var violence = query.facets().stream()
                .filter(facet -> facet.facet().equals("mcareputation:violence"))
                .findFirst().orElseThrow();
        assertEquals(0, violence.max());
        assertTrue(violence.allowUnobserved());
        assertFalse(query.isEmpty());
    }

    /**
     * §16.2's question is what <em>this</em> villager knows the player for, so an unscoped condition
     * asks that and not the village's view — the wider answer has to be asked for by name.
     */
    @Test
    void anUnscopedProfileConditionAsksTheSpeakerNotTheVillage() {
        var query = ReputationQueryJson.profile(JsonParser.parseString(
                "{\"recognition\": {\"min\": 1}}").getAsJsonObject());
        assertEquals(ReputationBridge.ProfileQuerySpec.Scope.SPEAKER, query.scope());
        assertEquals(ReputationBridge.ProfileQuerySpec.Scope.COMMUNITY,
                ReputationQueryJson.profile(JsonParser.parseString(
                        "{\"scope\": \"community\"}").getAsJsonObject()).scope());
    }

    @Test
    void aProfileConditionWithNoClauseAsksNothingRatherThanEverything() {
        var query = ReputationQueryJson.profile(JsonParser.parseString("{}").getAsJsonObject());
        assertTrue(query.isEmpty());
        assertTrue(query.facets().isEmpty());
        assertFalse(query.allowPartialHistory(), "a gate that needs whole history must opt in");
    }

    @Test
    void malformedProfileConditionsFailClosedRatherThanBroadening() {
        for (String json : List.of(
                "{\"scope\":\"quest_giver\"}",
                "{\"recognition\":{\"min\":-1}}",
                "{\"recognition\":{\"min\":40,\"max\":10}}",
                "{\"recognition\":{\"min_tier\":\"\"}}",
                "{\"recognition\":[]}",
                "{\"facets\":{\"bravery\":{\"min\":1}}}",
                "{\"facets\":{\"mcareputation:\":{\"min\":1}}}",
                "{\"facets\":{\"mcareputation:bravery\":{}}}",
                "{\"facets\":{\"mcareputation:bravery\":{\"min\":5,\"max\":1}}}",
                "{\"facets\":{\"mcareputation:bravery\":{\"min\":1,\"min_evidence\":-1}}}",
                "{\"facets\":{\"mcareputation:bravery\":{\"min\":1,\"min_evidence\":65}}}",
                "{\"facets\":{\"mcareputation:bravery\":{\"min\":1,\"allow_unobserved\":\"yes\"}}}",
                "{\"facets\":{\"mcareputation:bravery\":true}}",
                "{\"allow_partial_history\":\"sometimes\"}")) {
            assertThrows(RuntimeException.class,
                    () -> ReputationQueryJson.profile(JsonParser.parseString(json).getAsJsonObject()),
                    json + " must reject the whole query rather than drop a clause");
        }
    }

    /** A pack may not ask about more facets than Reputation will evaluate. */
    @Test
    void aProfileConditionCannotNameMoreFacetsThanReputationEvaluates() {
        StringBuilder json = new StringBuilder("{\"facets\":{");
        for (int i = 0; i <= ReputationBridge.ProfileQuerySpec.MAX_FACETS; i++) {
            json.append(i == 0 ? "" : ",")
                    .append("\"mcareputation:facet").append(i).append("\":{\"min\":1}");
        }
        json.append("}}");
        assertThrows(RuntimeException.class, () -> ReputationQueryJson.profile(
                JsonParser.parseString(json.toString()).getAsJsonObject()));
    }

    // ------------------------------------------------------------------
    // Profile answers and capability gating (0.6.0, §14.4)
    // ------------------------------------------------------------------

    private static final ReputationBridge.ProfileQuerySpec SPEAKER_QUERY =
            new ReputationBridge.ProfileQuerySpec(ReputationBridge.ProfileQuerySpec.Scope.SPEAKER,
                    15, null, null, List.of(), false);
    private static final ReputationBridge.ProfileQuerySpec COMMUNITY_QUERY =
            new ReputationBridge.ProfileQuerySpec(ReputationBridge.ProfileQuerySpec.Scope.COMMUNITY,
                    15, null, null, List.of(), false);

    /** With the mod absent there is no answer at all, which is not the same as "no". */
    @Test
    void withoutReputationEveryProfileQuestionIsUnavailable() {
        assertEquals(ReputationBridge.ProfileAnswer.UNAVAILABLE,
                ReputationBridge.matchesProfile(null, null, SPEAKER_QUERY));
        assertTrue(ReputationBridge.features(null).isEmpty());
        assertFalse(ReputationBridge.hasFeature(null, ReputationBridge.FEATURE_SPEAKER_PROFILE));
        assertTrue(ReputationBridge.speakerProfile(null, null).isEmpty());
    }

    /**
     * A build whose profile features are not live answers unavailable <b>before</b> the query runs.
     *
     * <p>Reputation advertises the five profile strings only while profiles are enabled and a pack has
     * published content, precisely so a companion does not read "cannot answer" as "no". Asking anyway
     * and treating the resulting false as an answer is the failure this gate exists to prevent.
     */
    @Test
    void aProfilelessBuildIsUnavailableRatherThanNegative() {
        ReputationBridge.setAvailableForTest(true,
                new CapableQueries(java.util.Set.of(ReputationBridge.FEATURE_DELIVERY),
                        ReputationBridge.ProfileAnswer.MATCH));
        assertFalse(ReputationBridge.supportsProfileScope(null,
                ReputationBridge.ProfileQuerySpec.Scope.SPEAKER));
        assertEquals(ReputationBridge.ProfileAnswer.UNAVAILABLE,
                ReputationBridge.matchesProfile(null, null, SPEAKER_QUERY),
                "an unsupported scope must not reach the query at all");
    }

    /** Each scope negotiates its own capability: a speaker query is not a community query. */
    @Test
    void eachProfileScopeNegotiatesItsOwnCapability() {
        ReputationBridge.setAvailableForTest(true,
                new CapableQueries(java.util.Set.of(ReputationBridge.FEATURE_SPEAKER_PROFILE),
                        ReputationBridge.ProfileAnswer.MATCH));
        var speakerOnly = new CapableQueries(
                java.util.Set.of(ReputationBridge.FEATURE_SPEAKER_PROFILE),
                ReputationBridge.ProfileAnswer.MATCH);
        assertTrue(speakerOnly.features(null).contains(ReputationBridge.FEATURE_SPEAKER_PROFILE));
        assertFalse(speakerOnly.features(null).contains(ReputationBridge.FEATURE_PROFILE_SNAPSHOT),
                "a build with speaker profiles live need not have the community one live");
        assertEquals(ReputationBridge.ProfileAnswer.MATCH,
                speakerOnly.matchesProfile(null, null, SPEAKER_QUERY));
        assertEquals(ReputationBridge.ProfileAnswer.MATCH,
                speakerOnly.matchesProfile(null, null, COMMUNITY_QUERY),
                "the façade answers what it is asked; the scope gate is the bridge's job");
    }

    /** A throwing or drifted build is unavailable, never a silent "no". */
    @Test
    void aThrowingBackendAnswersProfileQuestionsUnavailable() {
        ReputationBridge.setAvailableForTest(true, new ThrowingQueries());
        assertTrue(ReputationBridge.features(null).isEmpty());
        assertEquals(ReputationBridge.ProfileAnswer.UNAVAILABLE,
                ReputationBridge.matchesProfile(null, null, SPEAKER_QUERY));
        assertTrue(ReputationBridge.speakerProfile(null, null).isEmpty());
        assertFalse(ReputationBridge.recordSignal(null, null,
                ReputationBridge.SignalRequest.of("mcareputation:public_apology", null, "d")));
    }

    /** Only a match is a match: a condition scores on MATCH and on nothing else. */
    @Test
    void onlyAMatchCountsAsOne() {
        assertTrue(ReputationBridge.ProfileAnswer.MATCH.matched());
        assertFalse(ReputationBridge.ProfileAnswer.NO_MATCH.matched());
        assertFalse(ReputationBridge.ProfileAnswer.UNAVAILABLE.matched(),
                "an unanswerable question must score zero so the authored fallback fires");
    }

    /** §16.2: at most three descriptors reach a line, however many the profile carries. */
    @Test
    void aSpeakerProfileCarriesAtMostThreeDescriptors() {
        var view = new ReputationBridge.SpeakerProfileView(true, 40, "well_known",
                List.of("a:one", "a:two", "a:three", "a:four", "a:five"), 9, true);
        assertEquals(ReputationBridge.SpeakerProfileView.MAX_DOMINANT_FACETS,
                view.dominantFacets().size());
        assertEquals(List.of("a:one", "a:two", "a:three"), view.dominantFacets());
        var nothing = new ReputationBridge.SpeakerProfileView(false, 0, null, null, 0, true);
        assertEquals("", nothing.recognitionTierId(), "an unknown tier is blank, never null");
        assertTrue(nothing.dominantFacets().isEmpty());
        assertFalse(nothing.knowsPlayer(), "a valid zero stays zero");
    }

    // ------------------------------------------------------------------
    // The signal's identity: binding, supersession, replay (§16.2)
    // ------------------------------------------------------------------

    @Test
    void theSignalActionParsesEveryField() {
        var signal = ReputationQueryJson.signal(JsonParser.parseString("""
                {"incident": "mcareputation:public_apology", "visibility": "witnessed",
                 "decision": "standing.amends.public_apology", "binds": "known_incident",
                 "bind_types": ["mcareputation:villager_assaulted"], "bind_max_age": 168000,
                 "supersedes": "standing.amends.grudging", "supersede_window": 72000}""")
                .getAsJsonObject());
        assertEquals("mcareputation:public_apology", signal.incidentId());
        assertEquals("witnessed", signal.visibility());
        assertEquals("standing.amends.public_apology", signal.decisionId());
        assertTrue(signal.bindKnownIncident());
        assertEquals(List.of("mcareputation:villager_assaulted"), signal.bindTypes());
        assertEquals(168000L, signal.bindMaxAgeTicks());
        assertTrue(signal.supersedes());
        assertEquals("standing.amends.grudging", signal.supersedesDecisionId());
        assertEquals(72000L, signal.supersedeWindowTicks());
    }

    @Test
    void aSignalDefaultsToItsIncidentAndBindsNothing() {
        var signal = ReputationQueryJson.signal(JsonParser.parseString(
                "{\"incident\": \"mcareputation:public_apology\"}").getAsJsonObject());
        assertEquals("mcareputation:public_apology", signal.decisionId(),
                "an unnamed decision is the incident itself, as it always was");
        assertFalse(signal.bindKnownIncident());
        assertFalse(signal.supersedes());
        assertEquals(ReputationBridge.SignalRequest.DEFAULT_SUPERSEDE_WINDOW_TICKS,
                signal.supersedeWindowTicks(), "an unstated window is a documented default, not zero");
    }

    @Test
    void malformedSignalsRecordNothing() {
        for (String json : List.of(
                "{}",
                "{\"incident\": \"\"}",
                "{\"incident\": \"mcareputation:public_apology\", \"decision\": \" \"}",
                "{\"incident\": \"mcareputation:public_apology\", \"binds\": \"whatever\"}",
                "{\"incident\": \"mcareputation:public_apology\", \"bind_types\": [\"a:b\"]}",
                "{\"incident\": \"mcareputation:public_apology\", \"bind_max_age\": -1}",
                "{\"incident\": \"mcareputation:public_apology\", \"supersede_window\": -5}")) {
            assertThrows(RuntimeException.class,
                    () -> ReputationQueryJson.signal(JsonParser.parseString(json).getAsJsonObject()),
                    json + " must fail closed rather than record a deed nobody authored");
        }
    }

    /**
     * The identity fix itself: the villager is gone from the key, and the incident is in it.
     *
     * <p>1.7.1 keyed by {@code conversation:<villager>:<player>:<decision>}, so the same apology paid
     * again as soon as a different resident was standing there, while a second unrelated grievance
     * could not be apologised for at all. The player and community are already part of Reputation's
     * receipt identity, so neither belongs in the key either.
     */
    @Test
    void theOperationKeyNamesTheDecisionAndTheIncidentAndNotTheVillager() {
        String first = ReputationSignalIdentity.operationKey("standing.amends.public_apology", null);
        assertEquals("conversation:standing.amends.public_apology", first);

        java.util.UUID villagerA = java.util.UUID.randomUUID();
        java.util.UUID villagerB = java.util.UUID.randomUUID();
        assertEquals(ReputationSignalIdentity.operationKey("standing.amends", null),
                ReputationSignalIdentity.operationKey("standing.amends", null),
                "the same apology stage is the same operation however often it is clicked");
        assertFalse(first.contains(villagerA.toString()) || first.contains(villagerB.toString()));

        java.util.UUID incidentOne = java.util.UUID.randomUUID();
        java.util.UUID incidentTwo = java.util.UUID.randomUUID();
        String boundOne = ReputationSignalIdentity.operationKey("standing.amends",
                incidentOne.toString());
        String boundTwo = ReputationSignalIdentity.operationKey("standing.amends",
                incidentTwo.toString());
        assertNotEquals(boundOne, boundTwo,
                "two unrelated grievances must stay independently addressable");
        assertEquals(boundOne, ReputationSignalIdentity.operationKey("STANDING.AMENDS",
                incidentOne.toString().toUpperCase(java.util.Locale.ROOT)),
                "case cannot make one apology into two");
        assertNotEquals(boundOne, first,
                "a bound apology and an unbound one are different operations");
    }

    @Test
    void anOverLongOperationKeyIsDigestedRatherThanTruncated() {
        String longDecision = "standing.amends." + "x".repeat(400);
        String key = ReputationSignalIdentity.operationKey(longDecision, null);
        assertTrue(key.length() <= ReputationSignalIdentity.MAX_OPERATION_KEY_LENGTH);
        assertTrue(ReputationSignalIdentity.isCompacted(key));
        assertEquals(key, ReputationSignalIdentity.operationKey(longDecision, null),
                "a receipt written yesterday has to be findable today");
        assertNotEquals(key, ReputationSignalIdentity.operationKey(longDecision + "y", null),
                "truncation would collapse two identities into one paid operation");
    }

    @Test
    void aSignalWithoutADecisionIsAPackErrorRatherThanAnEmptyKey() {
        assertThrows(IllegalArgumentException.class,
                () -> ReputationSignalIdentity.operationKey("   ", null));
        assertThrows(IllegalArgumentException.class,
                () -> ReputationSignalIdentity.operationKey(null, "abc"));
    }

    /**
     * The adapter's delivery path is source-scanned, like the other assertions about the guarded
     * package: {@code compat.reputation} names MCA: Reputation types and cannot load on this suite's
     * classpath, where the API jar is deliberately compile-only.
     */
    @Test
    void theAdapterDeliversInsteadOfRecordingAndSupersedesInsteadOfStacking() throws IOException {
        String compat = Files.readString(
                SOURCE_ROOT.resolve("compat/reputation/ConversationsReputationCompat.java"),
                StandardCharsets.UTF_8);
        assertTrue(compat.contains("McaReputationApi.deliver(delivery)"),
                "a keyed delivery is what makes the apology exactly-once");
        assertTrue(compat.contains("ProfiledDelivery.superseding(delivery, supersede.get())"),
                "an amending decision folds its precursor in one canonical commit");
        assertTrue(compat.contains("McaReputationApi.recordSuperseding("),
                "a build without profiled delivery still supersedes rather than stacking");
        assertTrue(compat.replaceAll("\\s+", " ").contains("McaReputationApi .findReceipt("),
                "the precursor is found by its own operation key, not by guessing the newest deed");
        assertTrue(compat.contains("ReceiptOutcome.DUPLICATE"),
                "a duplicate is a terminal answer, not a failure to retry");
        assertTrue(compat.contains("SpeakerContext"),
                "the villager is supplied as a real speaker context for the bound selection");
        assertTrue(compat.contains(".witness(villager.getUUID())"),
                "a deed the speaker was present for is witnessed, honestly");
        assertFalse(compat.contains("\"conversation:\" + villager.getUUID()"),
                "the 1.7.1 villager-keyed dedupe key must be gone");
        assertFalse(compat.contains("getMethod(\"getOpinionBias\""),
                "capability negotiation replaced the reflective probe");
        assertTrue(compat.contains("McaReputationApi.capabilities(server)"),
                "gating is negotiated through capabilities, not reflected over methods");
    }

    /** §13.2/§16.2: the resolved opinion term replaces the village term, and is never added to it. */
    @Test
    void theOpinionTermReplacesTheVillageTermAndIsNeverAddedToIt() throws IOException {
        String bridge = Files.readString(SOURCE_ROOT.resolve("compat/ReputationBridge.java"),
                StandardCharsets.UTF_8);
        String collapsed = bridge.replaceAll("\\s+", " ");
        assertTrue(collapsed.contains("int raw = queries.supportsOpinionBias(player) "
                        + "? queries.opinionBias(player, villager, normalized) "
                        + ": queries.checkBias(player, villager, normalized);"),
                "exactly one of the two terms is read, chosen rather than summed");
        String compat = Files.readString(
                SOURCE_ROOT.resolve("compat/reputation/ConversationsReputationCompat.java"),
                StandardCharsets.UTF_8);
        assertFalse(compat.contains("getCheckBias") && compat.contains("+ McaReputationApi.getOpinionBias"),
                "a facet bias beside the village bias would count the same standing twice");
    }

    /** The profile-changed listener exists, is manually registered, and only invalidates. */
    @Test
    void theProfileChangeListenerInvalidatesRatherThanSpeaks() throws IOException {
        String events = Files.readString(
                SOURCE_ROOT.resolve("compat/reputation/ConversationsReputationEvents.java"),
                StandardCharsets.UTF_8);
        assertTrue(events.contains("onProfileChanged(ReputationProfileChangedEvent event)"),
                "the profile-only change has a consumer, so it is listened for");
        assertTrue(events.contains("ReputationBridge.invalidateStandingCache("),
                "what it does is drop a memo; nothing is said out loud");
        String handler = events.substring(events.indexOf("public void onProfileChanged"));
        assertFalse(handler.contains("noteStandingChange("),
                "a background profile change must not become a thing a villager remarks on");
        assertFalse(events.contains("\n@EventBusSubscriber"),
                "the annotation would put a Reputation event type on a standalone install's classpath");
    }

    /** The memo the profile event invalidates: per player, and never longer than its window. */
    @Test
    void theStandingMemoIsDroppedPerPlayer() {
        assertTrue(ReputationBridge.BIAS_CACHE_TICKS > 0);
        assertTrue(ReputationBridge.MAX_CACHED_BIASES > 0);
        // Pure invalidation: no server, no cached entries, and no exception either way.
        ReputationBridge.invalidateStandingCache(PLAYER);
        ReputationBridge.invalidateStandingCache(null);
        assertEquals(0, ReputationBridge.publicStandingFit(null, null, "trust"));
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
                    TestPaths.of("src/main/resources/assets/mcaconversations/lang/" + locale + ".json"),
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

    private static final Path SOURCE_ROOT = TestPaths.of("src/main/java/dev/otectus/mcaconversations");

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
    void modMetadataNeverMakesReputationRequired() throws IOException {
        String toml = Files.readString(TestPaths.of("src/main/resources/META-INF/neoforge.mods.toml"),
                StandardCharsets.UTF_8);

        int index = toml.indexOf("modId=\"mcareputation\"");
        assertTrue(index > 0, "the optional MCA: Reputation entry is missing; the 1.21.1 NeoForge "
                + "port of that mod now exists, so the integration is live again");

        // It may come back, but only optional and ordered after — never as a hard dependency.
        String block = toml.substring(index, Math.min(toml.length(), index + 200));
        assertTrue(block.contains("type=\"optional\""),
                "Reputation must never become a required dependency");
        assertTrue(block.contains("ordering=\"AFTER\""));

        // The declared range is deliberately NOT policed here any more. This test used to reject
        // [0.2,) on the grounds that it admits the Forge-only 1.20.1 jars, but that is not what a
        // version range does: a Forge jar ships META-INF/mods.toml and targets Minecraft 1.20.1, so
        // FML on 1.21.1 never loads it as a mod at all. The loader excludes it, not the range —
        // so policing the range here never bought anything. MCA: Reputation's own mod_version moves
        // with its feature set (it is on 0.3.0 now), which is one more reason not to pin it here.
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
    // The per-villager opinion bias (0.4.0, additive to API v1)
    // ------------------------------------------------------------------

    /**
     * A Reputation without the opinion method is a supported install, not a fault: the interface
     * default says so without any of it being reachable, so the check keeps the village-level bias.
     */
    @Test
    void anOlderReputationKeepsTheVillageLevelBias() {
        ReputationBridge.ReputationQueries older = new StubQueries(5);
        assertFalse(older.supportsOpinionBias(null),
                "a build that cannot report the opinion capability must not be asked for one");
        assertEquals(5, older.opinionBias(null, null, "trust"),
                "without the method the village-level bias is the answer");
        assertEquals("", older.communityId(null));
    }

    /** The opinion bias is the same term, so it is held to the same two axes and the same ceiling. */
    @Test
    void theOpinionBiasObeysTheSameAxisGateAndClamp() {
        assertEquals(8, ReputationBridge.clampStandingFit(40, "trust"));
        assertEquals(-8, ReputationBridge.clampStandingFit(-40, "respect"));
        for (String axis : List.of("warmth", "attraction", "tension", "familiarity")) {
            assertEquals(0, ReputationBridge.clampStandingFit(40, axis),
                    axis + " stays private whichever standing the term was read from");
        }
    }

    // ------------------------------------------------------------------
    // Standing remarks (0.4.0)
    // ------------------------------------------------------------------

    private static final java.util.UUID PLAYER = java.util.UUID.randomUUID();

    @Test
    void aCrossingIsHeldUntilSomebodyTakesIt() {
        ReputationBridge.noteStandingChange(PLAYER, "minecraft:overworld/7", "honored", true, 1000L);
        assertTrue(ReputationBridge.pendingRemark(PLAYER, 1000L).isPresent());

        var taken = ReputationBridge.consumeStandingRemark(PLAYER, 1000L);
        assertTrue(taken.isPresent());
        assertEquals("honored", taken.get().tierId());
        assertTrue(taken.get().upward());
        assertTrue(ReputationBridge.consumeStandingRemark(PLAYER, 1000L).isEmpty(),
                "one crossing is remarked on once, not by every villager the player walks past");
    }

    @Test
    void aCrossingNobodyMentionedTimesOutOnItsOwn() {
        ReputationBridge.noteStandingChange(PLAYER, "minecraft:overworld/7", "friend", true, 1000L);
        long expiry = 1000L + ReputationBridge.REMARK_TIMEOUT_TICKS;
        assertTrue(ReputationBridge.pendingRemark(PLAYER, expiry - 1).isPresent());
        assertTrue(ReputationBridge.pendingRemark(PLAYER, expiry).isEmpty(),
                "news older than the window is not news");
        assertTrue(ReputationBridge.pendingRemark(PLAYER, expiry - 1).isEmpty(),
                "and the timed-out entry is dropped rather than left to come back");
    }

    /** Pure, so the whole of the timeout rule can be pinned without a map or a clock. */
    @Test
    void freshnessRejectsAClockThatHasRunBackwards() {
        var remark = new ReputationBridge.PendingRemark("minecraft:overworld/7", "friend", true, 1000L);
        assertTrue(ReputationBridge.isFresh(remark, 1000L));
        assertFalse(ReputationBridge.isFresh(remark, 999L),
                "a crossing nobody can date must not be raised");
        assertFalse(ReputationBridge.isFresh(null, 1000L));
    }

    @Test
    void onlyTheNewestCrossingIsWaiting() {
        ReputationBridge.noteStandingChange(PLAYER, "minecraft:overworld/7", "friend", true, 1000L);
        ReputationBridge.noteStandingChange(PLAYER, "minecraft:overworld/7", "acquaintance", false, 1200L);
        var pending = ReputationBridge.pendingRemark(PLAYER, 1200L).orElseThrow();
        assertEquals("acquaintance", pending.tierId());
        assertFalse(pending.upward(), "a fall is raised as readily as a rise, and replaces it");
    }

    @Test
    void anUnusableCrossingIsNotRecordedAtAll() {
        ReputationBridge.noteStandingChange(null, "minecraft:overworld/7", "friend", true, 1000L);
        ReputationBridge.noteStandingChange(PLAYER, "", "friend", true, 1000L);
        ReputationBridge.noteStandingChange(PLAYER, "minecraft:overworld/7", "", true, 1000L);
        assertTrue(ReputationBridge.pendingRemark(PLAYER, 1000L).isEmpty());
    }

    /** With the mod absent nothing is ever raised, whatever is sitting in the map. */
    @Test
    void withoutReputationNoVillagerRaisesStanding() {
        ReputationBridge.noteStandingChange(PLAYER, "minecraft:overworld/7", "friend", true, 1000L);
        assertFalse(ReputationBridge.hasStandingRemark(null, null, 1000L));
    }

    /** The purpose exists, is an initiative, and names a bark pool the lang file actually ships. */
    @Test
    void theStandingRemarkPurposeHasALineToSay() throws IOException {
        assertTrue(dev.otectus.mcaconversations.scene.ScenePurpose.STANDING_REMARK.isInitiative());
        assertEquals(dev.otectus.mcaconversations.scene.ScenePurpose.REPAIR.interruptionCost(),
                dev.otectus.mcaconversations.scene.ScenePurpose.STANDING_REMARK.interruptionCost());
        for (String locale : List.of("en_us", "pt_br")) {
            var lang = JsonParser.parseString(Files.readString(
                    TestPaths.of("src/main/resources/assets/mca_dialogue/lang/" + locale + ".json"),
                    StandardCharsets.UTF_8)).getAsJsonObject();
            assertTrue(lang.has("dialogue."
                            + dev.otectus.mcaconversations.scene.InitiativePlanner.PHRASE_PREFIX
                            + dev.otectus.mcaconversations.scene.ScenePurpose.STANDING_REMARK.key() + "/1"),
                    "missing " + locale + " line for the standing remark");
        }
    }

    /** The event subscriber lives in the guarded package and is registered by hand, never annotated. */
    @Test
    void theTierChangeSubscriberIsGuardedAndManuallyRegistered() throws IOException {
        String compat = Files.readString(
                SOURCE_ROOT.resolve("compat/reputation/ConversationsReputationCompat.java"),
                StandardCharsets.UTF_8);
        assertTrue(compat.contains("NeoForge.EVENT_BUS.register(new ConversationsReputationEvents())"),
                "the subscriber is registered only after the mod-present check");
        String events = Files.readString(
                SOURCE_ROOT.resolve("compat/reputation/ConversationsReputationEvents.java"),
                StandardCharsets.UTF_8);
        assertFalse(events.contains("\n@EventBusSubscriber"),
                "the annotation would put a Reputation event type on a standalone install's classpath");
        assertTrue(events.contains("ReputationBridge.noteStandingChange("),
                "a crossing is left as a note for the planner, not spoken from the event");
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
                                    net.minecraft.world.entity.Entity villager,
                                    ReputationBridge.SignalRequest request) {
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

    /**
     * A façade that advertises a chosen capability set and answers every profile question the same
     * way, for exercising the gating without a running game.
     */
    private record CapableQueries(java.util.Set<String> features,
                                  ReputationBridge.ProfileAnswer answer)
            implements ReputationBridge.ReputationQueries {

        @Override
        public java.util.Set<String> features(net.minecraft.server.level.ServerPlayer player) {
            return features;
        }

        @Override
        public ReputationBridge.ProfileAnswer matchesProfile(
                net.minecraft.server.level.ServerPlayer player,
                net.minecraft.world.entity.Entity villager,
                ReputationBridge.ProfileQuerySpec query) {
            return answer;
        }

        @Override
        public java.util.Optional<ReputationBridge.SpeakerProfileView> speakerProfile(
                net.minecraft.server.level.ServerPlayer player,
                net.minecraft.world.entity.Entity villager) {
            return java.util.Optional.of(new ReputationBridge.SpeakerProfileView(true, 40,
                    "well_known", List.of("mcareputation:bravery"), 3, true));
        }

        @Override
        public int score(net.minecraft.server.level.ServerPlayer player,
                         net.minecraft.world.entity.Entity villager) {
            return 0;
        }

        @Override
        public String tierId(net.minecraft.server.level.ServerPlayer player,
                             net.minecraft.world.entity.Entity villager) {
            return "";
        }

        @Override
        public int checkBias(net.minecraft.server.level.ServerPlayer player,
                             net.minecraft.world.entity.Entity villager, String axis) {
            return 0;
        }

        @Override
        public boolean matchesStanding(net.minecraft.server.level.ServerPlayer player,
                                       net.minecraft.world.entity.Entity villager,
                                       ReputationBridge.StandingQuery query) {
            return false;
        }

        @Override
        public boolean matchesIncident(net.minecraft.server.level.ServerPlayer player,
                                       net.minecraft.world.entity.Entity villager,
                                       ReputationBridge.IncidentQuery query) {
            return false;
        }

        @Override
        public java.util.Optional<ReputationBridge.GossipCandidate> nextGossip(
                net.minecraft.server.level.ServerPlayer player,
                net.minecraft.world.entity.Entity teller, java.util.Set<String> types,
                long maxAgeTicks) {
            return java.util.Optional.empty();
        }

        @Override
        public boolean recordSignal(net.minecraft.server.level.ServerPlayer player,
                                    net.minecraft.world.entity.Entity villager,
                                    ReputationBridge.SignalRequest request) {
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
                                    net.minecraft.world.entity.Entity villager,
                                    ReputationBridge.SignalRequest request) {
            throw new IllegalStateException("boom");
        }

        @Override
        public java.util.Set<String> features(net.minecraft.server.level.ServerPlayer player) {
            throw new IllegalStateException("boom");
        }

        @Override
        public ReputationBridge.ProfileAnswer matchesProfile(
                net.minecraft.server.level.ServerPlayer player,
                net.minecraft.world.entity.Entity villager,
                ReputationBridge.ProfileQuerySpec query) {
            throw new IllegalStateException("boom");
        }

        @Override
        public java.util.Optional<ReputationBridge.SpeakerProfileView> speakerProfile(
                net.minecraft.server.level.ServerPlayer player,
                net.minecraft.world.entity.Entity villager) {
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
