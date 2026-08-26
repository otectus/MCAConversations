package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.otectus.mcaconversations.debug.DialogueGraph;
import dev.otectus.mcaconversations.personality.Personalities;
import dev.otectus.mcaconversations.personality.VoiceFamily;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Which of a trade's six subjects a villager reaches for first depends on who they are (spec §9.3).
 *
 * <p>Six equally weighted results is not personalization, it is a coin toss with six sides. The
 * router therefore carries a second result per trade and subject, conditioned on the voice family
 * that subject belongs to, so a gloomy villager opens on what the work costs and a friendly one on
 * who it is for. The neutral result stays alongside it, which is the part this suite cares about
 * most: a preference must never become a rail. A confident villager who never once mentions the
 * risk is a worse villager than one who mentions it slightly less often.
 */
class PersonalitySelectionTest {

    private static final String SEP = System.lineSeparator();

    /** The six subjects every trade has, in the order they were authored. */
    private static final List<String> SUBJECTS =
            List.of("identity", "task", "craft", "risk", "village", "future");

    /** subject -> the personalities whose preference result names them. */
    private static Map<String, Set<String>> preferredBy;
    /** profession -> subject -> how many results open it. */
    private static Map<String, Map<String, Integer>> neutral;
    private static Map<String, Map<String, Integer>> preferred;
    /** The trade paths the router really branches on — not the generic pride/hate fallback pages. */
    private static Set<String> trades;

    @BeforeAll
    static void load() {
        preferredBy = new TreeMap<>();
        neutral = new TreeMap<>();
        preferred = new TreeMap<>();

        DialogueGraph.Question router =
                ContentFixture.graph().question("conversations.work").orElseThrow();

        trades = new TreeSet<>();
        for (DialogueGraph.Answer answer : router.answers()) {
            for (DialogueGraph.Result result : answer.results()) {
                for (JsonObject condition : result.conditions()) {
                    if (condition.has("profession")) {
                        String id = condition.get("profession").getAsString();
                        trades.add(id.substring(id.indexOf(':') + 1));
                    }
                }
            }
        }

        for (DialogueGraph.Answer answer : router.answers()) {
            for (DialogueGraph.Result result : answer.results()) {
                String beat = beatOf(result);
                if (beat == null || !beat.startsWith("work.")) {
                    continue;
                }
                String[] parts = beat.split("\\.");
                if (parts.length != 3) {
                    continue;
                }
                String subject = parts[2];
                String profession = parts[1];
                if (!trades.contains(profession)) {
                    continue;  // the shared pride/hate pages, which have no six subjects to prefer
                }

                Set<String> personalities = new LinkedHashSet<>();
                for (JsonObject condition : result.conditions()) {
                    if (condition.has("conversations_personality")) {
                        // The condition takes either one personality or a list of them.
                        JsonElement declared = condition.get("conversations_personality");
                        if (declared.isJsonArray()) {
                            for (JsonElement element : declared.getAsJsonArray()) {
                                personalities.add(element.getAsString());
                            }
                        } else {
                            personalities.add(declared.getAsString());
                        }
                    }
                }
                Map<String, Map<String, Integer>> bucket = personalities.isEmpty() ? neutral : preferred;
                bucket.computeIfAbsent(profession, p -> new TreeMap<>()).merge(subject, 1, Integer::sum);
                if (!personalities.isEmpty()) {
                    preferredBy.computeIfAbsent(subject, s -> new TreeSet<>()).addAll(personalities);
                }
            }
        }
    }

    private static String beatOf(DialogueGraph.Result result) {
        JsonObject session = result.actions().getAsJsonObject("conversations_session");
        return session != null && session.has("beat") ? session.get("beat").getAsString() : null;
    }

    @Test
    @DisplayName("every trade's six subjects each carry exactly one personality preference")
    void everySubjectIsPreferredByExactlyOneFamily() {
        List<String> problems = new ArrayList<>();
        for (Map.Entry<String, Map<String, Integer>> trade : neutral.entrySet()) {
            Map<String, Integer> boosts = preferred.getOrDefault(trade.getKey(), Map.of());
            for (String subject : SUBJECTS) {
                int count = boosts.getOrDefault(subject, 0);
                if (count != 1) {
                    problems.add(trade.getKey() + "." + subject + ": " + count
                            + " personality-preference result(s), and exactly one is wanted");
                }
            }
            Set<String> extra = new TreeSet<>(boosts.keySet());
            extra.removeAll(SUBJECTS);
            if (!extra.isEmpty()) {
                problems.add(trade.getKey() + ": preference results for unknown subjects " + extra);
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    /**
     * The rule the whole thing turns on. A preference is a thumb on the scale; the unconditioned
     * result has to stay, or the preferred personalities are the only ones who can ever reach that
     * subject and everybody else silently loses a sixth of the trade.
     */
    @Test
    @DisplayName("no subject is reachable only by the personalities that prefer it")
    void preferenceIsNeverARail() {
        List<String> problems = new ArrayList<>();
        for (String profession : new TreeSet<>(preferred.keySet())) {
            Map<String, Integer> open = neutral.getOrDefault(profession, Map.of());
            for (String subject : preferred.get(profession).keySet()) {
                if (open.getOrDefault(subject, 0) < 1) {
                    problems.add(profession + "." + subject + ": only the preferring personalities can"
                            + " open it — the unconditioned result has gone");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("the preferences use the voice families, and use each of them once")
    void preferencesFollowTheVoiceFamilies() {
        List<String> problems = new ArrayList<>();
        Map<String, String> claimedBy = new LinkedHashMap<>();

        for (String subject : SUBJECTS) {
            Set<String> personalities = preferredBy.get(subject);
            if (personalities == null) {
                problems.add(subject + ": no personality prefers this subject anywhere");
                continue;
            }
            Set<String> families = new TreeSet<>();
            for (String personality : personalities) {
                families.add(VoiceFamily.of(personality).key());
            }
            if (families.size() != 1) {
                problems.add(subject + ": preferred by " + families
                        + " — a subject belongs to one voice family, not several");
                continue;
            }
            String family = families.iterator().next();
            String already = claimedBy.put(family, subject);
            if (already != null) {
                problems.add(family + " prefers both " + already + " and " + subject
                        + " — each family reaches for one subject first");
            }
            Set<String> expected = new TreeSet<>(VoiceFamily.byKey(family).overlayPrefixes());
            if (!new TreeSet<>(personalities).equals(expected)) {
                problems.add(subject + ": named " + new TreeSet<>(personalities) + " but the "
                        + family + " family is " + expected);
            }
        }

        Set<String> unused = new TreeSet<>();
        for (VoiceFamily family : VoiceFamily.values()) {
            if (!claimedBy.containsKey(family.key())) {
                unused.add(family.key());
            }
        }
        if (!unused.isEmpty()) {
            problems.add("voice families that prefer no subject at all: " + unused);
        }
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("every personality MCA can roll has a subject it reaches for first")
    void everyPersonalityIsCovered() {
        Set<String> covered = new TreeSet<>();
        preferredBy.values().forEach(covered::addAll);
        Set<String> missing = new TreeSet<>(Personalities.overlayPrefixes());
        missing.removeAll(covered);
        assertTrue(missing.isEmpty(), "personalities with no preferred work subject: " + missing);
    }
}
