package dev.otectus.mcaconversations.content;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.otectus.mcaconversations.chat.AgeVoice;
import dev.otectus.mcaconversations.chat.GreetingPolicy;
import dev.otectus.mcaconversations.personality.Personalities;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The greetings that mark a relationship — a regular's, a friend's, a confidant's, a spouse's, a
 * relative's, a respected stranger's and a grudge's — and the goodbyes that differ from the ordinary
 * one — a grudge's, an enemy's and a son's or daughter's — are spoken in every personality's own
 * voice, and say only what that relationship allows.
 *
 * <p>These are the lines where a generic sentence costs most: the first thing a friend, a husband, a
 * wife or a child says to the player, day after day. So every personality overlay voices every one of them,
 * with exactly as many sentences as the base pool. Fewer, and {@code LineVoice} would name a variant
 * the overlay does not have, dropping the villager back to the generic line; more, and
 * {@code VariantPools.deliverablePoolSize} would never pick the extras.
 *
 * <p>MCA 7.6's spellings of the personalities 7.7 renamed are one voice with their successors
 * ({@link Personalities#LEGACY_ALIASES}), so they must say the same words. Every other pair of voices
 * must not: two personalities greeting a spouse with the same sentence is a copy, not a voice.
 *
 * <p>Three rules are about what the words may assume. Nobody calls their mother or father by their
 * first name, so the greeting to a parent never uses {@code %1$s}. A line shared by every player
 * cannot know which of them it is talking to, so none of these greetings uses a gendered word for
 * the player — no "son", "wife" or "querida". And romance belongs to the spouse: a friend or a
 * confidant may be married to somebody else, so nobody outside the family calls the player "darling"
 * or asks for a kiss. (A parent may. That is not romance.)
 */
class GreetingVoiceLintTest {

    private static final Path ASSETS = Path.of("src/main/resources/assets");
    private static final List<String> LOCALES = List.of("en_us", "pt_br");

    /** The greetings every personality voices. */
    private static final List<String> VOICED = List.of(
            GreetingPolicy.ACQUAINTANCE, GreetingPolicy.FRIEND, GreetingPolicy.CONFIDANT,
            GreetingPolicy.PARTNER, GreetingPolicy.FAMILY_PARENT, GreetingPolicy.FAMILY_CHILD,
            GreetingPolicy.FAMILY_SIBLING, GreetingPolicy.RESPECTED_STRANGER, GreetingPolicy.GUARDED,
            GreetingPolicy.FAREWELL_GUARDED, GreetingPolicy.FAREWELL_HOSTILE, GreetingPolicy.FAREWELL_FAMILY_PARENT);

    /** Every goodbye {@link GreetingPolicy#farewell} can choose. */
    private static final List<String> ALL_FAREWELLS = List.of(
            GreetingPolicy.FAREWELL, GreetingPolicy.FAREWELL_STRANGER, GreetingPolicy.FAREWELL_GUARDED,
            GreetingPolicy.FAREWELL_HOSTILE, GreetingPolicy.FAREWELL_FAMILY_PARENT);

    /** Every greeting {@link GreetingPolicy#pool} can choose. */
    private static final List<String> ALL_GREETINGS = List.of(
            GreetingPolicy.STRANGER, GreetingPolicy.RESPECTED_STRANGER, GreetingPolicy.RECOGNIZED,
            GreetingPolicy.ACQUAINTANCE, GreetingPolicy.FRIEND, GreetingPolicy.CONFIDANT,
            GreetingPolicy.PARTNER, GreetingPolicy.FAMILY_PARENT, GreetingPolicy.FAMILY_CHILD,
            GreetingPolicy.FAMILY_SIBLING, GreetingPolicy.GUARDED, GreetingPolicy.COLD);

    /** The greetings a toddler speaks in its own words. */
    private static final List<String> TODDLER_VOICED = List.of(
            GreetingPolicy.ACQUAINTANCE + ".toddler", GreetingPolicy.FRIEND + ".toddler",
            GreetingPolicy.CONFIDANT + ".toddler", GreetingPolicy.FAMILY_PARENT + ".toddler",
            GreetingPolicy.GUARDED + ".toddler", GreetingPolicy.FAREWELL_GUARDED + ".toddler",
            GreetingPolicy.FAREWELL_HOSTILE + ".toddler", GreetingPolicy.FAREWELL_FAMILY_PARENT + ".toddler");

    /** MCA's age groups; an empty one is how {@link AgeVoice} reads an adult or a failed read. */
    private static final List<String> AGES = List.of("baby", "toddler", "child", "teen", "adult", "");

    /** Words that would tell the player which they are. A shared line cannot know. */
    private static final Map<String, Pattern> GENDERED = Map.of(
            "en_us", Pattern.compile("\\b(mum|mom|mummy|mommy|mother|dad|daddy|father|son|daughter|brother"
                    + "|sister|husband|wife|boy|girl|lad|lass|sir|madam|ma'am)\\b",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS),
            "pt_br", Pattern.compile("\\b(mãe|mamãe|pai|papai|filho|filha|irmão|irmã|marido|esposa|esposo"
                    + "|querido|querida|menino|menina|moço|moça|senhor|senhora)\\b",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.UNICODE_CHARACTER_CLASS));

    /** Words only a spouse or a relative may greet with. ("Love" and "dear" are how some villagers talk to anyone.) */
    private static final Map<String, Pattern> ROMANTIC = Map.of(
            "en_us", Pattern.compile("\\b(darling|sweetheart|my love|dear heart|kiss|kisses)\\b",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS),
            "pt_br", Pattern.compile("\\b(amor|beijo|beijinho|paixão)\\b",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.UNICODE_CHARACTER_CLASS));

    /** locale -> base lang. */
    private static final Map<String, Map<String, String>> BASE = new HashMap<>();
    /** personality -> locale -> overlay lang. */
    private static final Map<String, Map<String, Map<String, String>>> OVERLAYS = new TreeMap<>();

    @BeforeAll
    static void load() throws IOException {
        for (String locale : LOCALES) {
            BASE.put(locale, read(ASSETS.resolve("mca_dialogue/lang/" + locale + ".json")));
        }
        for (String personality : Personalities.overlayPrefixes()) {
            Map<String, Map<String, String>> byLocale = new HashMap<>();
            for (String locale : LOCALES) {
                Path file = ASSETS.resolve("mca_dialogue_" + personality + "/lang/" + locale + ".json");
                byLocale.put(locale, Files.exists(file) ? read(file) : Map.of());
            }
            OVERLAYS.put(personality, byLocale);
        }
    }

    private static Map<String, String> read(Path file) throws IOException {
        return new Gson().fromJson(Files.readString(file),
                TypeToken.getParameterized(Map.class, String.class, String.class).getType());
    }

    private static List<String> base(String locale, String pool) {
        return LangKeys.linesOf(BASE.get(locale), "dialogue." + pool);
    }

    private static List<String> overlay(String personality, String locale, String pool) {
        return LangKeys.linesOf(OVERLAYS.get(personality).get(locale), personality + ".dialogue." + pool);
    }

    @Test
    @DisplayName("every personality voices every relationship greeting and goodbye, sentence for sentence")
    void everyPersonalityVoicesEveryRelationshipGreeting() {
        List<String> problems = new ArrayList<>();
        OVERLAYS.keySet().forEach(personality -> {
            for (String locale : LOCALES) {
                for (String pool : VOICED) {
                    int want = base(locale, pool).size();
                    int have = overlay(personality, locale, pool).size();
                    if (want == 0) {
                        problems.add(locale + ": the base pool " + pool + " is empty");
                    } else if (have != want) {
                        problems.add(personality + "/" + locale + ": " + pool + " has " + have
                                + " sentence(s), the base pool " + want);
                    }
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    @DisplayName("an MCA 7.6 spelling greets exactly as the personality it was renamed to")
    void aLegacySpellingGreetsLikeItsSuccessor() {
        List<String> problems = new ArrayList<>();
        Personalities.LEGACY_ALIASES.forEach((legacy, canonical) -> {
            for (String locale : LOCALES) {
                for (String pool : VOICED) {
                    if (!overlay(legacy, locale, pool).equals(overlay(canonical, locale, pool))) {
                        problems.add(legacy + "/" + locale + ": " + pool + " differs from " + canonical);
                    }
                }
            }
        });
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    @DisplayName("no two voices, the generic one included, share a relationship greeting")
    void noTwoVoicesShareAGreeting() {
        List<String> problems = new ArrayList<>();
        for (String locale : LOCALES) {
            for (String pool : VOICED) {
                Map<String, String> speaker = new LinkedHashMap<>();
                base(locale, pool).forEach(line -> speaker.put(line, "the base pool"));
                OVERLAYS.keySet().stream()
                        .filter(p -> !Personalities.LEGACY_ALIASES.containsKey(p))
                        .forEach(personality -> overlay(personality, locale, pool).forEach(line -> {
                            String previous = speaker.putIfAbsent(line, personality);
                            if (previous != null) {
                                problems.add(locale + " " + pool + ": " + previous + " and " + personality
                                        + " both say \"" + line + "\"");
                            }
                        }));
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }

    @Test
    @DisplayName("a son or daughter never greets or sees off a parent by their first name")
    void aChildNeverCallsAParentByName() {
        List<String> problems = new ArrayList<>();
        List<String> pools = List.of(GreetingPolicy.FAMILY_PARENT, GreetingPolicy.FAMILY_PARENT + ".toddler",
                GreetingPolicy.FAREWELL_FAMILY_PARENT, GreetingPolicy.FAREWELL_FAMILY_PARENT + ".toddler");
        for (String locale : LOCALES) {
            for (String pool : pools) {
                base(locale, pool).stream().filter(line -> line.contains("%1$s"))
                        .forEach(line -> problems.add("base/" + locale + " " + pool + ": " + line));
                OVERLAYS.keySet().forEach(personality -> overlay(personality, locale, pool).stream()
                        .filter(line -> line.contains("%1$s"))
                        .forEach(line -> problems.add(personality + "/" + locale + " " + pool + ": " + line)));
            }
        }
        assertTrue(problems.isEmpty(), "greetings to a parent using the player's name:\n" + String.join("\n", problems));
    }

    @Test
    @DisplayName("relationship greetings never assume the player's gender")
    void relationshipGreetingsNeverAssumeAGender() {
        List<String> problems = new ArrayList<>();
        List<String> pools = new ArrayList<>(VOICED);
        pools.addAll(TODDLER_VOICED);
        for (String locale : LOCALES) {
            Pattern gendered = GENDERED.get(locale);
            for (String pool : pools) {
                base(locale, pool).stream().filter(line -> gendered.matcher(line).find())
                        .forEach(line -> problems.add("base/" + locale + " " + pool + ": " + line));
                OVERLAYS.keySet().forEach(personality -> overlay(personality, locale, pool).stream()
                        .filter(line -> gendered.matcher(line).find())
                        .forEach(line -> problems.add(personality + "/" + locale + " " + pool + ": " + line)));
            }
        }
        assertTrue(problems.isEmpty(), "gendered words in shared greetings:\n" + String.join("\n", problems));
    }

    @Test
    @DisplayName("nobody outside the family greets the player like a sweetheart")
    void nobodyOutsideTheFamilyGreetsLikeASweetheart() {
        List<String> problems = new ArrayList<>();
        List<String> pools = new ArrayList<>(VOICED);
        pools.addAll(TODDLER_VOICED);
        pools.removeIf(pool -> pool.startsWith(GreetingPolicy.PARTNER) || pool.contains(".family."));
        for (String locale : LOCALES) {
            Pattern romantic = ROMANTIC.get(locale);
            for (String pool : pools) {
                base(locale, pool).stream().filter(line -> romantic.matcher(line).find())
                        .forEach(line -> problems.add("base/" + locale + " " + pool + ": " + line));
                OVERLAYS.keySet().forEach(personality -> overlay(personality, locale, pool).stream()
                        .filter(line -> romantic.matcher(line).find())
                        .forEach(line -> problems.add(personality + "/" + locale + " " + pool + ": " + line)));
            }
        }
        assertTrue(problems.isEmpty(), "endearments outside the family:\n" + String.join("\n", problems));
    }

    @Test
    @DisplayName("every greeting and goodbye the policy can choose has a line for a villager of every age")
    void everyGreetingHasALineForEveryAge() {
        List<String> problems = new ArrayList<>();
        List<String> pools = new ArrayList<>(ALL_GREETINGS);
        pools.addAll(ALL_FAREWELLS);
        for (String pool : pools) {
            for (String age : AGES) {
                String phrase = AgeVoice.phrase(pool, Optional.of(age));
                for (String locale : LOCALES) {
                    if (!LangKeys.hasLine(BASE.get(locale), "dialogue." + phrase)) {
                        problems.add(locale + ": " + pool + " for a " + (age.isEmpty() ? "villager of unknown age" : age)
                                + " resolves to " + phrase + ", which has no line");
                    }
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join("\n", problems));
    }
}
