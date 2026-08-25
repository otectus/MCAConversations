package dev.otectus.mcaconversations.content;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.otectus.mcaconversations.personality.Personalities;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Lints the personality overlay lang files ({@code assets/mca_dialogue_<personality>/lang/}):
 * every MCA personality has an overlay, every overlay key's base exists in our main lang file,
 * and each overlay covers the standard high-traffic key set.
 */
class OverlayLintTest {

    private static final Path ASSETS = Path.of("src/main/resources/assets");
    private static final Path BASE_LANG = ASSETS.resolve("mca_dialogue/lang/en_us.json");

    /** MCA 7.6.26 personalities (lowercase). */
    /**
     * Every namespace that needs an overlay: the MCA 7.7 roster plus the 7.6 spellings, because one
     * binary serves both MCA versions and a 7.6 world still asks for {@code witty.dialogue.*}.
     */
    private static final Set<String> PERSONALITIES = Personalities.overlayPrefixes();

    /**
     * The core-20 high-traffic topics every personality overlay must cover (v0.5.0 anti-repetition
     * pass). Long-tail topics gain per-personality overlays in later releases.
     */
    /**
     * The reply tier (1.2.0). Overlays used to stop at the topic openers, so the personality spoke
     * one sentence and handed the next six exchanges to a single generic narrator. These six are the
     * first <em>reply</em> in each of six distinct emotional registers — accepting sympathy,
     * accepting help, being seen as a person rather than a pair of hands, being promised support,
     * being given room to hope, and being asked again days later. A personality that sounds the same
     * across those six is not a personality, which is what makes them worth enforcing here.
     */
    private static final Set<String> REPLY_TIER_KEYS = Set.of(
            "dialogue.conversations.day.rough.empathize",
            "dialogue.conversations.checkin.rough.offer_help",
            "dialogue.conversations.work.followup.hear_burnout",
            "dialogue.conversations.fears.open.pledge",
            "dialogue.conversations.hopes.respond.listen",
            "dialogue.conversations.life.revisit");

    private static final Set<String> STANDARD_KEYS = Set.of(
            "dialogue.chat",
            "dialogue.conversations",
            "dialogue.conversations.checkin.good", "dialogue.conversations.checkin.rough",
            "dialogue.conversations.checkin.again",
            "dialogue.conversations.day.good", "dialogue.conversations.day.rough",
            "dialogue.conversations.work.like", "dialogue.conversations.work.hate",
            "dialogue.conversations.village.home", "dialogue.conversations.people.first",
            "dialogue.conversations.food.first",
            "dialogue.conversations.life.first", "dialogue.conversations.dreams.first",
            "dialogue.conversations.fears.first", "dialogue.conversations.feelings.first",
            "dialogue.conversations.deflect.personal", "dialogue.conversations.deflect.intimate",
            "dialogue.conversations.gossip.none", "dialogue.conversations.us.happy.yes",
            // Personality-flavored village gossip (0.6.0): every villager reports news in their own voice.
            "dialogue.conversations.gossip.marriage", "dialogue.conversations.gossip.divorce",
            "dialogue.conversations.gossip.death", "dialogue.conversations.gossip.birth",
            "dialogue.conversations.gossip.arrival", "dialogue.conversations.gossip.departure");

    /**
     * Personalities that carry chat-mode deflection/system voice (spec §11 "at least grumpy/peppy/
     * friendly"). Other personalities intentionally fall through to the base {@code dialogue.chatmode.*}
     * lines, so chatmode keys are deliberately <b>not</b> added to {@link #STANDARD_KEYS}.
     */
    private static final Set<String> CHATMODE_OVERLAYS = Set.of("grumpy", "peppy", "friendly");

    /** Chat-mode line families each {@link #CHATMODE_OVERLAYS} personality must voice (base key form). */
    private static final Set<String> CHATMODE_KEYS = Set.of(
            "dialogue.chatmode.confused", "dialogue.chatmode.hint", "dialogue.chatmode.shrug",
            "dialogue.chatmode.clarify", "dialogue.chatmode.dropped", "dialogue.chatmode.busy",
            "dialogue.chatmode.muted", "dialogue.chatmode.farewell", "dialogue.chatmode.insult",
            "dialogue.chatmode.hail", "dialogue.chatmode.hail_cold");

    private static Map<String, Map<String, String>> overlays;
    private static Map<String, String> baseLang;

    @BeforeAll
    static void load() throws IOException {
        Gson gson = new Gson();
        var type = TypeToken.getParameterized(Map.class, String.class, String.class).getType();
        baseLang = gson.fromJson(Files.readString(BASE_LANG), type);
        overlays = new HashMap<>();
        try (Stream<Path> dirs = Files.list(ASSETS)) {
            for (Path dir : dirs.filter(d -> d.getFileName().toString().startsWith("mca_dialogue_")).toList()) {
                String personality = dir.getFileName().toString().substring("mca_dialogue_".length());
                Map<String, String> lang = gson.fromJson(
                        Files.readString(dir.resolve("lang/en_us.json")), type);
                overlays.put(personality, lang);
            }
        }
    }

    @Test
    void everyPersonalityHasAnOverlay() {
        List<String> problems = new ArrayList<>();
        for (String p : PERSONALITIES) {
            if (!overlays.containsKey(p)) {
                problems.add("missing overlay file for personality '" + p + "'");
            }
        }
        for (String p : overlays.keySet()) {
            if (!PERSONALITIES.contains(p)) {
                problems.add("overlay for unknown personality '" + p + "' (namespace would never be read)");
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    // Note: dialogue.chat has a base entry in our main lang file (added for the Chat->Conversations
    // entry header), so overlayKeysOverrideRealBaseKeys covers it like any other key.
    /** Strips the {@code <personality>.} prefix and any {@code /N} variant suffix. */
    private static String bareTopic(String personality, String key) {
        String s = key.startsWith(personality + ".") ? key.substring(personality.length() + 1) : key;
        int slash = s.indexOf('/');
        return slash < 0 ? s : s.substring(0, slash);
    }

    /**
     * Every key must start with its own namespace's personality prefix.
     *
     * <p>MCA resolves a personality line as {@code <personality>.[<dialogueType>.]<key>}
     * ({@code DialogueType.applyFallback} → {@code getPrefixedPhrase}), so an unprefixed
     * {@code dialogue.*} key here is never treated as a personality line at all. Worse, Minecraft
     * translation keys are <b>global across asset namespaces</b>: unprefixed keys in several
     * overlays collide, and whichever pack loads last silently becomes every villager's voice.
     */
    @Test
    void everyKeyCarriesItsPersonalityPrefix() {
        List<String> problems = new ArrayList<>();
        overlays.forEach((personality, lang) -> {
            for (String key : lang.keySet()) {
                if (!key.startsWith(personality + ".dialogue.")) {
                    problems.add(personality + ": key '" + key + "' is not prefixed '" + personality
                            + ".dialogue.' (MCA would never resolve it as a personality line)");
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /** Translation keys are global, so two overlays sharing a key would silently overwrite. */
    @Test
    void noKeyCollidesAcrossOverlays() {
        Map<String, String> owner = new HashMap<>();
        List<String> problems = new ArrayList<>();
        overlays.forEach((personality, lang) -> lang.keySet().forEach(key -> {
            String previous = owner.putIfAbsent(key, personality);
            if (previous != null) {
                problems.add("key '" + key + "' defined by both " + previous + " and " + personality);
            }
        }));
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void overlayKeysOverrideRealBaseKeys() {
        List<String> problems = new ArrayList<>();
        overlays.forEach((personality, lang) -> {
            for (String key : lang.keySet()) {
                String base = bareTopic(personality, key);
                if (!LangKeys.hasLine(baseLang, base)) {
                    problems.add(personality + ": overlay key '" + key + "' has no base line to override");
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void overlaysCoverTheStandardKeySet() {
        List<String> problems = new ArrayList<>();
        Set<String> required = new java.util.LinkedHashSet<>(STANDARD_KEYS);
        required.addAll(REPLY_TIER_KEYS);
        overlays.forEach((personality, lang) -> {
            for (String key : required) {
                if (!LangKeys.hasLine(lang, personality + "." + key)) {
                    problems.add(personality + ": missing standard overlay key '" + key + "'");
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    /**
     * The whole point of an overlay is that it does not sound like the others. Two personalities
     * shipping the same sentence for the same key is a copy-paste, not a voice.
     */
    @Test
    void noTwoPersonalitiesGiveTheSameReply() {
        List<String> problems = new ArrayList<>();
        for (String key : REPLY_TIER_KEYS) {
            Map<String, List<String>> byLine = new java.util.TreeMap<>();
            overlays.forEach((personality, lang) -> {
                String line = lang.get(personality + "." + key);
                if (line != null) {
                    byLine.computeIfAbsent(line, l -> new ArrayList<>()).add(personality);
                }
            });
            byLine.forEach((line, personalities) -> {
                if (personalities.size() > 1) {
                    problems.add(key + ": " + personalities + " all say \"" + line + "\"");
                }
            });
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    void chatmodeOverlaysCoverTheChatmodeKeySet() {
        List<String> problems = new ArrayList<>();
        for (String personality : CHATMODE_OVERLAYS) {
            Map<String, String> lang = overlays.get(personality);
            if (lang == null) {
                problems.add("missing overlay for chatmode-voiced personality '" + personality + "'");
                continue;
            }
            for (String key : CHATMODE_KEYS) {
                if (!LangKeys.hasLine(lang, personality + "." + key)) {
                    problems.add(personality + ": missing chat-mode overlay key '" + key + "'");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }
}
