package dev.otectus.mcaconversations.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.conversation.RelationshipBand;
import dev.otectus.mcaconversations.conversation.RelationshipQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * How close two people are is asked for by name, not by number (spec §9.4).
 *
 * <p>The corpus used to gate forty results on {@code hearts_max: 24} and {@code hearts_max: 59}.
 * Nobody reading those could say what they were for, and nobody changing them could find every copy.
 * {@link RelationshipBand} is the single documented source of truth for the thresholds, and dialogue
 * names a band. This suite keeps it that way: the numbers may not come back, and a band name that
 * does not exist fails the build rather than silently never matching.
 */
class RelationshipBandLintTest {

    private static final String SEP = System.lineSeparator();

    /** MCA's own heart conditions. Correct in a datapack; wrong in ours, now that bands exist. */
    private static final List<String> RAW_HEART_KEYS = List.of("hearts", "hearts_min", "hearts_max");

    private static void walk(JsonElement node, java.util.function.Consumer<JsonObject> visit) {
        if (node == null || node.isJsonNull()) {
            return;
        }
        if (node.isJsonArray()) {
            node.getAsJsonArray().forEach(element -> walk(element, visit));
            return;
        }
        if (!node.isJsonObject()) {
            return;
        }
        JsonObject object = node.getAsJsonObject();
        visit.accept(object);
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            walk(entry.getValue(), visit);
        }
    }

    private static void forEachDialogueObject(java.util.function.BiConsumer<String, JsonObject> visit)
            throws IOException {
        try (var files = Files.list(ContentFixture.DIALOGUES)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".json")).sorted().toList()) {
                String name = file.getFileName().toString().replace(".json", "");
                walk(JsonParser.parseString(Files.readString(file)), object -> visit.accept(name, object));
            }
        }
    }

    @Test
    @DisplayName("no shipped result gates on a raw heart number")
    void heartNumbersHaveGone() throws IOException {
        List<String> problems = new ArrayList<>();
        forEachDialogueObject((file, object) -> {
            for (String key : RAW_HEART_KEYS) {
                if (object.has(key)) {
                    problems.add(file + ": '" + key + "': " + object.get(key)
                            + " — say which band that is instead (conversations_relationship)");
                }
            }
        });
        assertTrue(problems.isEmpty(), problems.size() + " raw heart threshold(s) are back:"
                + SEP + String.join(SEP, problems));
    }

    @Test
    @DisplayName("every relationship condition names bands that exist")
    void everyBandQueryParses() throws IOException {
        List<String> problems = new ArrayList<>();
        int[] seen = {0};
        forEachDialogueObject((file, object) -> {
            if (!object.has("conversations_relationship")) {
                return;
            }
            seen[0]++;
            try {
                RelationshipQuery.fromJson(object.get("conversations_relationship"));
            } catch (RuntimeException e) {
                problems.add(file + ": " + e.getMessage());
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
        assertTrue(seen[0] > 0, "nothing in the corpus asks about the relationship at all —"
                + " the heart gates were migrated to bands, so some result should be using them");
    }

    /**
     * {@link RelationshipBand#FAMILY} is real and a pack may model it, but this build cannot resolve
     * it: MCA's parent/child relations are not among the members the compat layer binds, and
     * {@code Relationships} deliberately never returns it. Shipping content behind it would be
     * content nobody can ever reach.
     */
    @Test
    @DisplayName("shipped content does not gate on a band this build cannot resolve")
    void noContentWaitsOnAnUnresolvableBand() throws IOException {
        List<String> problems = new ArrayList<>();
        forEachDialogueObject((file, object) -> {
            if (!object.has("conversations_relationship")) {
                return;
            }
            RelationshipQuery query;
            try {
                query = RelationshipQuery.fromJson(object.get("conversations_relationship"));
            } catch (RuntimeException e) {
                return;  // the parse test owns this one
            }
            if (query.bands().equals(java.util.Set.of(RelationshipBand.FAMILY))) {
                problems.add(file + ": gated on 'family' alone, which this build never resolves");
            }
        });
        assertTrue(problems.isEmpty(), String.join(SEP, problems));
    }

    @Test
    @DisplayName("the warmth line is an order, and the roles sit above it")
    void bandsAreOrdered() {
        List<RelationshipBand> line = new ArrayList<>();
        for (RelationshipBand band : RelationshipBand.values()) {
            if (band.onWarmthLine()) {
                line.add(band);
            }
        }
        line.sort(java.util.Comparator.comparingInt(RelationshipBand::minHearts));
        for (int i = 1; i < line.size(); i++) {
            assertTrue(line.get(i).minHearts() > line.get(i - 1).minHearts(),
                    line.get(i) + " and " + line.get(i - 1) + " share a threshold, so one of them"
                            + " can never be reached");
            assertTrue(line.get(i).isAtLeast(line.get(i - 1)),
                    line.get(i) + " should be at least " + line.get(i - 1));
            assertFalse(line.get(i - 1).isAtLeast(line.get(i)),
                    line.get(i - 1) + " should not be at least " + line.get(i));
        }

        assertTrue(RelationshipBand.PARTNER.isAtLeast(RelationshipBand.CONFIDANT),
                "a spouse may hear anything a confidant may");
        assertTrue(RelationshipBand.FAMILY.isAtLeast(RelationshipBand.CONFIDANT),
                "a relative may hear anything a confidant may");
        assertFalse(RelationshipBand.TENSE.isAtLeast(RelationshipBand.STRANGER),
                "a ruptured relationship is not 'at least' anything, whatever preceded it");
        assertFalse(RelationshipBand.HOSTILE.isAtLeast(RelationshipBand.STRANGER),
                "a hostile relationship is not 'at least' anything");
    }

    @Test
    @DisplayName("the resolver puts a rupture above warmth, and marriage above a heart total")
    void resolverOrdersItsInputs() {
        assertEquals(RelationshipBand.HOSTILE, RelationshipBand.of(-80, true, true, false),
                "hostile outranks everything, including a marriage");
        assertEquals(RelationshipBand.TENSE, RelationshipBand.of(95, true, false, true),
                "an unresolved matter outranks ninety-five hearts");
        assertEquals(RelationshipBand.PARTNER, RelationshipBand.of(5, true, false, false),
                "a spouse is a spouse at five hearts");
        assertEquals(RelationshipBand.FAMILY, RelationshipBand.of(0, false, true, false),
                "a parent does not become a parent at seventy hearts");
        assertEquals(RelationshipBand.STRANGER, RelationshipBand.of(24, false, false, false));
        assertEquals(RelationshipBand.ACQUAINTANCE, RelationshipBand.of(25, false, false, false));
        assertEquals(RelationshipBand.ACQUAINTANCE, RelationshipBand.of(59, false, false, false));
        assertEquals(RelationshipBand.FRIEND, RelationshipBand.of(60, false, false, false));
        assertEquals(RelationshipBand.CONFIDANT, RelationshipBand.of(80, false, false, false));
    }

    /**
     * The migration was supposed to change nothing about who sees what. The two {@code hearts_max}
     * gates covered negative heart totals too, so the bands that replaced them have to as well.
     */
    @Test
    @DisplayName("the bands that replaced the old gates cover exactly the same heart totals")
    void migrationPreservedBehaviour() {
        RelationshipQuery below25 = RelationshipQuery.fromJson(
                JsonParser.parseString("[\"stranger\",\"tense\",\"hostile\"]"));
        RelationshipQuery below60 = RelationshipQuery.fromJson(
                JsonParser.parseString("[\"stranger\",\"acquaintance\",\"tense\",\"hostile\"]"));
        RelationshipQuery notNegative = RelationshipQuery.fromJson(
                JsonParser.parseString("{\"at_least\":\"stranger\"}"));

        for (int hearts = -100; hearts <= 120; hearts++) {
            RelationshipBand band = RelationshipBand.of(hearts, false, false, false);
            assertEquals(hearts <= 24, below25.matches(band), "hearts_max 24 at " + hearts);
            assertEquals(hearts <= 59, below60.matches(band), "hearts_max 59 at " + hearts);
            assertEquals(hearts >= 0, notNegative.matches(band), "hearts_min 0 at " + hearts);
        }
    }
}
