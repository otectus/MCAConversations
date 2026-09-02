package dev.otectus.mcaconversations.context;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Every declared context field must have somebody who writes it.
 *
 * <h2>The failure this exists to prevent</h2>
 *
 * <p>A {@link ContextKey} nobody writes is not an empty field. It reads {@code UNAVAILABLE}, and the
 * default policy for an unknown field is {@code fail}, so a scene gated on it is not merely vaguer —
 * it is <b>permanently unselectable</b>, on every install, with nothing at runtime to say so.
 *
 * <p>That is not hypothetical. Before 1.5.0, twenty-one of the sixty-one declared fields had no
 * source at all, and seventeen shipped scenes were gated on one of them: fourteen on
 * {@code time.days_since_first_met}, two on {@code time.days_since_last_talk}, one on
 * {@code village.recent_event}. A seventh of the dynamic corpus had been dark since it was written,
 * and every lint in the suite passed the whole time, because each one checked that a condition named
 * a <em>declared</em> field and none checked that the field was <em>answerable</em>.
 *
 * <p>The corpus test is the important half. Declaring a key and forgetting to write it is a mistake
 * anyone can make; shipping content that depends on the mistake is what makes it expensive.
 */
class ContextKeyOwnershipTest {

    private static final Path SCENES =
            Path.of("src/main/resources/data/mcaconversations/conversation_scenes/generated.json");

    private static String read(Path path) {
        try {
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /** Every field some registered source claims to write. */
    private static Set<String> owned() {
        Set<String> owned = new LinkedHashSet<>();
        for (ConversationContextSource source : ContextSources.registered()) {
            for (ContextKey<?> key : source.declares()) {
                owned.add(key.id());
            }
        }
        return owned;
    }

    @Test
    void everyDeclaredFieldIsWrittenBySomeSource() {
        Set<String> owned = owned();
        Set<String> orphans = new TreeSet<>();
        for (ContextKey<?> key : ContextKey.all()) {
            if (!owned.contains(key.id())) {
                orphans.add(key.id());
            }
        }
        assertTrue(orphans.isEmpty(),
                "these context fields are declared but no registered source writes them, so every "
                        + "scene condition on them is permanently unselectable: " + orphans
                        + " — either add a provider or delete the key. A field that can never answer "
                        + "is worse than no field, because content gets written against it.");
    }

    @Test
    void noSourceClaimsAFieldThatIsNotDeclared() {
        // The other direction: a source writing a key ContextKey.byId cannot resolve would be a
        // value no condition could ever name, which is dead weight in the snapshot.
        Set<String> declared = new LinkedHashSet<>();
        for (ContextKey<?> key : ContextKey.all()) {
            declared.add(key.id());
        }
        Set<String> undeclared = new TreeSet<>(owned());
        undeclared.removeAll(declared);
        assertTrue(undeclared.isEmpty(), "sources write undeclared fields: " + undeclared);
    }

    @Test
    void noTwoSourcesClaimTheSameField() {
        // One owner per field is what makes the capability report readable, and what stops two
        // providers racing to answer the same question differently.
        Set<String> seen = new LinkedHashSet<>();
        Set<String> duplicated = new TreeSet<>();
        for (ConversationContextSource source : ContextSources.registered()) {
            for (ContextKey<?> key : source.declares()) {
                if (!seen.add(key.id())) {
                    duplicated.add(key.id());
                }
            }
        }
        assertTrue(duplicated.isEmpty(), "more than one source claims: " + duplicated);
    }

    @Test
    void noShippedSceneIsGatedOnAFieldNobodyWrites() {
        // The corpus half, and the one that would have caught the seventeen dark scenes on the day
        // they were authored.
        Set<String> owned = owned();
        List<String> problems = new ArrayList<>();
        JsonObject root = JsonParser.parseString(read(SCENES)).getAsJsonObject();
        JsonObject scenes = root.has("scenes") ? root.getAsJsonObject("scenes") : root;
        for (String id : scenes.keySet()) {
            JsonElement entry = scenes.get(id);
            if (!entry.isJsonObject() || !entry.getAsJsonObject().has("context")) {
                continue;
            }
            JsonObject context = entry.getAsJsonObject().getAsJsonObject("context");
            if (!context.has("conditions")) {
                continue;
            }
            for (JsonElement condition : context.getAsJsonArray("conditions")) {
                if (!condition.isJsonObject() || !condition.getAsJsonObject().has("field")) {
                    continue;
                }
                String field = condition.getAsJsonObject().get("field").getAsString();
                if (!owned.contains(field)) {
                    problems.add(id + " is gated on '" + field
                            + "', which no source writes, so it can never be selected");
                }
            }
        }
        assertTrue(problems.isEmpty(), String.join(System.lineSeparator(), problems));
    }

    @Test
    void theFieldsThatWereDarkAreAnsweredNow() {
        // A named regression guard for the specific fields the 1.5.0 audit found. If one loses its
        // provider again, this fails with the reason rather than as a mystery.
        Set<String> owned = owned();
        for (String field : List.of("time.days_since_last_talk", "time.days_since_first_met",
                "time.absence_band", "village.recent_event",
                "narrative.active_episodes", "narrative.ready_threads",
                "narrative.due_commitments", "narrative.rupture", "narrative.recent_subjects",
                "identity.interests", "identity.values", "identity.comfort", "identity.aversion",
                "identity.work_style", "identity.social_style", "identity.disclosure_style",
                "identity.origin_motif", "identity.formative_event",
                "work.former_profession", "work.profession_changed_day")) {
            assertTrue(owned.contains(field), field + " has lost its provider again");
        }
    }

    @Test
    void theKeyThatCouldNeverAnswerIsGone() {
        // speaker.affect declared an AffectFrame nothing in the mod ever produced. Rather than ship
        // a field that can only read UNAVAILABLE, both it and the type were removed.
        assertFalse(ContextKey.byId("speaker.affect").isPresent(),
                "speaker.affect is back without a producer");
    }

    @Test
    void theVocabularyIsStillTheSizeWeThinkItIs() {
        // A cheap guard on the tests above: if the key set silently emptied, they would pass by
        // vacuum.
        assertTrue(ContextKey.all().size() >= 55,
                "expected the full context vocabulary, found " + ContextKey.all().size());
        assertEquals(5, ContextSources.registered().size(),
                "vanilla, mca, village, history, identity");
    }
}
