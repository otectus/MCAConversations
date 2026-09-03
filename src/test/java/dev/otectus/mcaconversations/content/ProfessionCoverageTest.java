package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.profession.ProfessionProfile;
import dev.otectus.mcaconversations.profession.ProfessionProfiles;
import dev.otectus.mcaconversations.profession.WorkArchetype;
import dev.otectus.mcaconversations.debug.DialogueGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.otectus.mcaconversations.support.TestPaths;

/**
 * Every trade a villager can have is a trade this mod can hold a conversation about (spec §13.1
 * test 4).
 *
 * <p>The roster is not a list somebody maintains by hand: it is derived from the shipped work router,
 * so adding a profession result without a profile fails here, and adding a profile for a profession
 * nothing routes to fails here too. That symmetry is the point — the previous corpus had thirty-seven
 * profession lines and no machine-readable claim that any of those professions existed.
 */
class ProfessionCoverageTest {

    private static final String SEP = System.lineSeparator();

    private static final Path PROFILES = TestPaths.of("src/main/resources/data/mcaconversations/profession_profiles");
    private static final Path WORK_ROUTER = TestPaths.of(
            "src/main/resources/data/mcaconversations/dialogues/conversations.work.json");

    /** Namespaces whose professions exist with no optional mod installed. */
    private static final Set<String> BASE_NAMESPACES = Set.of("minecraft", "mca");

    private static ProfessionProfiles profiles;
    private static List<String> parseProblems;
    private static Set<String> roster;

    @BeforeAll
    static void load() throws IOException {
        List<ProfessionProfile> parsed = new ArrayList<>();
        parseProblems = new ArrayList<>();
        Map<String, String> owners = new LinkedHashMap<>();
        try (var files = Files.list(PROFILES)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".json")).sorted().toList()) {
                JsonObject root = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                if (!root.has("profiles")) {
                    parseProblems.add(file.getFileName() + ": no \"profiles\" object");
                    continue;
                }
                for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject("profiles").entrySet()) {
                    try {
                        parsed.add(ProfessionProfile.fromJson(entry.getKey(), entry.getValue().getAsJsonObject()));
                        owners.put(entry.getKey(), file.getFileName().toString());
                    } catch (RuntimeException e) {
                        parseProblems.add(file.getFileName() + ": " + e.getMessage());
                    }
                }
            }
        }
        profiles = ProfessionProfiles.build(parsed);

        // The roster is whatever the work router actually branches on.
        roster = new TreeSet<>();
        JsonObject router = JsonParser.parseString(Files.readString(WORK_ROUTER)).getAsJsonObject();
        DialogueGraph.Question question = DialogueGraph.of(Map.of("conversations.work", router))
                .question("conversations.work").orElseThrow();
        for (DialogueGraph.Answer answer : question.answers()) {
            for (DialogueGraph.Result result : answer.results()) {
                for (JsonObject condition : result.conditions()) {
                    if (condition.has("profession")) {
                        roster.add(condition.get("profession").getAsString());
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("every profession profile parses")
    void profilesParse() {
        assertTrue(parseProblems.isEmpty(),
                "Profession profiles failed to parse:" + SEP + String.join(SEP, parseProblems));
    }

    @Test
    @DisplayName("the roster and the profiles are the same set, in both directions")
    void rosterAndProfilesAgree() {
        Set<String> missing = new TreeSet<>(roster);
        missing.removeAll(profiles.ids());
        Set<String> extra = new TreeSet<>(profiles.ids());
        extra.removeAll(roster);

        List<String> problems = new ArrayList<>();
        if (!missing.isEmpty()) {
            problems.add("professions the work router branches on with no profile: " + missing);
        }
        if (!extra.isEmpty()) {
            problems.add("profiles for professions nothing routes to: " + extra);
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
        assertFalse(roster.isEmpty(), "the work router declares no professions at all");
    }

    @Test
    @DisplayName("every profile has enough to talk about, and enough to remember")
    void profilesMeetTheContentFloor() {
        List<String> problems = new ArrayList<>();
        for (ProfessionProfile profile : profiles.all()) {
            if (profile.subjects().size() < ProfessionProfile.MIN_SUBJECTS) {
                problems.add(profile.id() + ": " + profile.subjects().size() + " subject(s), and "
                        + ProfessionProfile.MIN_SUBJECTS + " is the floor — a trade with fewer than that"
                        + " becomes one line and a shrug");
            }
            if (profile.callbackTypes().size() < ProfessionProfile.MIN_CALLBACK_TYPES) {
                problems.add(profile.id() + ": " + profile.callbackTypes().size() + " callback type(s), and "
                        + ProfessionProfile.MIN_CALLBACK_TYPES + " is the floor — a trade that remembers"
                        + " nothing cannot have a second conversation");
            }
            if (profile.displayFallback().isBlank()) {
                problems.add(profile.id() + ": no display fallback for when the mod that owns it is absent");
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("optional professions declare the mod that owns them, and base ones do not pretend to")
    void ownershipIsHonest() {
        List<String> problems = new ArrayList<>();
        for (ProfessionProfile profile : profiles.all()) {
            boolean baseNamespace = BASE_NAMESPACES.contains(profile.namespace());
            if (baseNamespace && !profile.isBase()) {
                problems.add(profile.id() + ": is a base profession but declares owner '" + profile.owner() + "'");
            }
            if (!baseNamespace && profile.isBase()) {
                problems.add(profile.id() + ": comes from an optional mod but declares itself base — an absent"
                        + " mod must not look like a missing base profession");
            }
            if (!baseNamespace && !profile.namespace().equals(profile.owner())) {
                problems.add(profile.id() + ": owner '" + profile.owner()
                        + "' does not match its registry namespace");
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("every archetype is used, and none is a dumping ground")
    void archetypesAreBalanced() {
        List<String> problems = new ArrayList<>();
        for (WorkArchetype archetype : WorkArchetype.values()) {
            List<ProfessionProfile> members = profiles.ofArchetype(archetype);
            if (members.isEmpty()) {
                problems.add(archetype.key() + ": no profession uses this archetype — delete it or use it");
            }
            if (members.size() > profiles.size() / 2) {
                problems.add(archetype.key() + ": holds " + members.size() + " of " + profiles.size()
                        + " professions, which means it is not saying anything about them");
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("an unknown third-party profession still gets a coherent, claim-free profile")
    void unknownProfessionsFallBack() {
        ProfessionProfile unknown = profiles.forId("somemod:chandler", "chandler");
        assertEquals("chandler", unknown.displayFallback());
        assertEquals(WorkArchetype.fallback(), unknown.archetype());
        assertFalse(unknown.isBase(), "an unknown profession must not claim to be one of ours");
        assertTrue(unknown.materials().isEmpty(),
                "a generic profile must invent no materials — it knows the name and nothing else");
        assertTrue(unknown.risks().isEmpty(), "a generic profile must invent no risks");

        ProfessionProfile nothingAtAll = profiles.forId(null, null);
        assertEquals("villager", nothingAtAll.displayFallback(),
                "a villager with no profession at all must still be answerable");
    }

    /**
     * The rule this whole overhaul exists for (spec §16): a villager with a trade gets a page written
     * for that trade. The old corpus routed all thirty-seven through
     * {@code conversations.topic.work.respond}, whose five buttons could not be right for more than a
     * handful of them.
     */
    @Test
    @DisplayName("no known profession routes through a shared work page")
    void everyProfessionHasItsOwnPage() {
        List<String> problems = new ArrayList<>();
        DialogueGraph graph = ContentFixture.graph();
        DialogueGraph.Question router = graph.question("conversations.work").orElseThrow();

        for (DialogueGraph.Answer answer : router.answers()) {
            for (DialogueGraph.Result result : answer.results()) {
                String profession = null;
                for (JsonObject condition : result.conditions()) {
                    if (condition.has("profession")) {
                        profession = condition.get("profession").getAsString();
                    }
                }
                if (profession == null) {
                    continue;
                }
                String next = result.next().orElse("(none)");
                String own = "conversations.topic.work."
                        + profession.substring(profession.indexOf(':') + 1) + ".";
                if (!next.startsWith(own) || !next.endsWith(".respond")) {
                    problems.add(profession + ": opens '" + next + "', which is not one of its own"
                            + " pages (expected " + own + "<subject>.respond)");
                } else if (graph.question(next).isEmpty()) {
                    problems.add(profession + ": names page '" + next + "', which does not exist");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("every profession's pages carry beat contracts")
    void everyProfessionIsContracted() {
        List<String> problems = new ArrayList<>();
        for (String id : roster) {
            String path = id.substring(id.indexOf(':') + 1);
            String own = "conversations.topic.work." + path + ".";
            List<String> pages = ContentFixture.graph().questionIds().stream()
                    .filter(q -> q.startsWith(own)).sorted().toList();
            if (pages.isEmpty()) {
                problems.add(id + ": no pages of its own at all");
                continue;
            }
            for (String node : pages) {
                if (ContentFixture.catalog().inbound(node).isEmpty()) {
                    problems.add(id + ": nothing declares what opens " + node);
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * Spec §7.3: six distinct opener beat pools per trade — the current job, the craft, the risk,
     * what the village gets, how it was learned, and where it goes. One line and a shrug is what
     * this release exists to stop.
     */
    @Test
    @DisplayName("every profession has its six work subjects")
    void everyProfessionHasSixSubjects() {
        List<String> problems = new ArrayList<>();
        for (String id : roster) {
            String path = id.substring(id.indexOf(':') + 1);
            String own = "conversations.topic.work." + path + ".";
            long openers = ContentFixture.graph().questionIds().stream()
                    .filter(q -> q.startsWith(own) && q.endsWith(".respond"))
                    .count();
            if (openers < ProfessionProfile.MIN_SUBJECTS) {
                problems.add(id + ": " + openers + " work subject(s), and "
                        + ProfessionProfile.MIN_SUBJECTS + " is the floor");
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("every base profession is reachable without any optional mod")
    void baseProfessionsNeedNoMod() {
        List<String> ids = profiles.base().stream().map(ProfessionProfile::id).sorted().toList();
        assertTrue(ids.size() >= 21, "expected the vanilla and MCA rosters, got " + ids);
        for (String id : ids) {
            assertTrue(BASE_NAMESPACES.contains(id.substring(0, id.indexOf(':'))), id + " is not a base namespace");
        }
    }
}
