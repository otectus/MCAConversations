package dev.otectus.mcaconversations.chat.group;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.otectus.mcaconversations.history.KnowledgeSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The group pilot's rules (spec §11.6): a closed list of shapes, a hard cap on voices, and no line
 * that is not answering the line before it on a footing its speaker actually has.
 */
class GroupConversationTest {

    private static final UUID LEAD = UUID.fromString("00000000-0000-0000-0000-00000000e001");
    private static final UUID SECOND = UUID.fromString("00000000-0000-0000-0000-00000000e002");
    private static final UUID THIRD = UUID.fromString("00000000-0000-0000-0000-00000000e003");
    private static final UUID FOURTH = UUID.fromString("00000000-0000-0000-0000-00000000e004");
    private static final String BEAT = "work.librarian.damaged_volume.open";

    private static GroupInterjection interjection(GroupShape shape, UUID speaker, String beat,
                                                  KnowledgeSource knowledge) {
        return new GroupInterjection(shape, speaker, beat, knowledge);
    }

    @Test
    @DisplayName("an interjection with no prior line to answer is refused")
    void interjectionsNeedAPriorLine() {
        assertFalse(interjection(GroupShape.CORROBORATE, SECOND, "", KnowledgeSource.WITNESSED)
                .isWellFormed(), "a line that answers nothing is a non-sequitur, not a scene");
    }

    @Test
    @DisplayName("a villager cannot corroborate something they were in no position to know")
    void knowledgeMustFitTheShape() {
        assertFalse(interjection(GroupShape.CORROBORATE, SECOND, BEAT, KnowledgeSource.UNKNOWN_RUMOR)
                .isWellFormed());
        assertTrue(interjection(GroupShape.CORROBORATE, SECOND, BEAT, KnowledgeSource.WITNESSED)
                .isWellFormed());

        assertFalse(interjection(GroupShape.COWORKER_DETAIL, SECOND, BEAT, KnowledgeSource.FAMILY)
                .isWellFormed(), "being someone's cousin is not knowing their trade");
        assertFalse(interjection(GroupShape.FAMILY_REMEMBERS, SECOND, BEAT, KnowledgeSource.COWORKER)
                .isWellFormed(), "and working beside somebody is not being family");
    }

    @Test
    @DisplayName("a preference needs no source, which is why nothing turns on it")
    void disagreementIsOpenToAnybody() {
        for (KnowledgeSource source : KnowledgeSource.values()) {
            assertTrue(GroupShape.FRIENDLY_DISAGREEMENT.acceptsKnowledge(source),
                    source + " should be able to hold an opinion about a preference");
        }
    }

    @Test
    @DisplayName("an interjection answering some other beat is refused")
    void interjectionsAnswerTheLineTheyFollow() {
        GroupConversationSession session = new GroupConversationSession(LEAD, BEAT);

        assertFalse(session.offer(interjection(GroupShape.CORROBORATE, SECOND,
                "village.wall.open", KnowledgeSource.WITNESSED)));
        assertTrue(session.offer(interjection(GroupShape.CORROBORATE, SECOND, BEAT,
                KnowledgeSource.WITNESSED)));
    }

    @Test
    @DisplayName("the lead cannot interject into their own line")
    void theLeadDoesNotAnswerThemselves() {
        GroupConversationSession session = new GroupConversationSession(LEAD, BEAT);

        assertFalse(session.offer(interjection(GroupShape.CORROBORATE, LEAD, BEAT,
                KnowledgeSource.WITNESSED)));
    }

    @Test
    @DisplayName("nobody joins in twice")
    void oneVoiceEach() {
        GroupConversationSession session = new GroupConversationSession(LEAD, BEAT);

        assertTrue(session.offer(interjection(GroupShape.CORROBORATE, SECOND, BEAT,
                KnowledgeSource.WITNESSED)));
        assertFalse(session.offer(interjection(GroupShape.FRIENDLY_DISAGREEMENT, SECOND, BEAT,
                KnowledgeSource.WITNESSED)));
    }

    @Test
    @DisplayName("three speakers including the lead, and no fourth voice")
    void speakerCapHolds() {
        GroupConversationSession session = new GroupConversationSession(LEAD, BEAT);

        assertTrue(session.offer(interjection(GroupShape.CORROBORATE, SECOND, BEAT,
                KnowledgeSource.WITNESSED)));
        assertTrue(session.offer(interjection(GroupShape.FRIENDLY_DISAGREEMENT, THIRD, BEAT,
                KnowledgeSource.WITNESSED)));
        assertTrue(session.isFull());
        assertFalse(session.offer(interjection(GroupShape.CORROBORATE, FOURTH, BEAT,
                KnowledgeSource.WITNESSED)),
                "a fourth line arrives while the player is still reading the second");
        assertEquals(GroupConversationSession.MAX_SPEAKERS, session.speakers());
    }

    @Test
    @DisplayName("interjections come out in turn order, not in the order they were offered")
    void turnOrderIsFixed() {
        GroupConversationSession session = new GroupConversationSession(LEAD, BEAT);
        session.offer(interjection(GroupShape.FAMILY_REMEMBERS, SECOND, BEAT, KnowledgeSource.FAMILY));
        session.offer(interjection(GroupShape.BYSTANDER_PRIVACY, THIRD, BEAT, KnowledgeSource.FAMILY));

        List<GroupInterjection> ordered = session.interjections();

        assertEquals(GroupShape.BYSTANDER_PRIVACY, ordered.get(0).shape());
        assertEquals(GroupShape.FAMILY_REMEMBERS, ordered.get(1).shape());
    }

    @Test
    @DisplayName("a boundary is heard before anybody adds detail to the thing")
    void boundariesWinTheSlot() {
        List<GroupInterjection> offers = List.of(
                interjection(GroupShape.CORROBORATE, SECOND, BEAT, KnowledgeSource.WITNESSED),
                interjection(GroupShape.BYSTANDER_PRIVACY, THIRD, BEAT, KnowledgeSource.FAMILY));

        assertEquals(Optional.of(GroupShape.BYSTANDER_PRIVACY),
                GroupConversationSession.best(offers).map(GroupInterjection::shape));
    }

    @Test
    @DisplayName("a malformed offer never wins the slot")
    void malformedOffersAreDropped() {
        List<GroupInterjection> offers = new ArrayList<>();
        offers.add(interjection(GroupShape.CORROBORATE, SECOND, "", KnowledgeSource.WITNESSED));
        offers.add(null);

        assertEquals(Optional.empty(), GroupConversationSession.best(offers));
    }

    // --- Which shapes a relation actually licenses -------------------------------------------------

    @Test
    @DisplayName("a stranger may differ about a preference and may not confirm an event")
    void strangersMayOnlyDisagree() {
        List<GroupShape> shapes = GroupDirector.shapesFor(GroupRelation.STRANGER, false, false);

        assertEquals(List.of(GroupShape.FRIENDLY_DISAGREEMENT), shapes);
    }

    @Test
    @DisplayName("only somebody who was there may corroborate a public event")
    void corroborationNeedsStanding() {
        assertTrue(GroupDirector.shapesFor(
                        GroupRelation.of(false, false, KnowledgeSource.WITNESSED), true, false)
                .contains(GroupShape.CORROBORATE));
        assertFalse(GroupDirector.shapesFor(GroupRelation.STRANGER, true, false)
                .contains(GroupShape.CORROBORATE),
                "a bystander with no footing confirming an event is the failure this list exists to stop");
    }

    @Test
    @DisplayName("the trade licenses a trade detail and the family tree licenses a different memory")
    void relationsLicenseTheirOwnShapes() {
        List<GroupShape> coworker = GroupDirector.shapesFor(
                GroupRelation.of(false, true, KnowledgeSource.COWORKER), false, false);
        assertTrue(coworker.contains(GroupShape.COWORKER_DETAIL));
        assertFalse(coworker.contains(GroupShape.FAMILY_REMEMBERS));

        List<GroupShape> family = GroupDirector.shapesFor(
                GroupRelation.of(true, false, KnowledgeSource.FAMILY), false, false);
        assertTrue(family.contains(GroupShape.FAMILY_REMEMBERS));
        assertFalse(family.contains(GroupShape.COWORKER_DETAIL));
    }

    @Test
    @DisplayName("somebody with standing may stop a confidence being spent, and a stranger may not")
    void boundariesNeedStanding() {
        assertTrue(GroupDirector.shapesFor(
                        GroupRelation.of(true, false, KnowledgeSource.FAMILY), false, true)
                .contains(GroupShape.BYSTANDER_PRIVACY));
        assertFalse(GroupDirector.shapesFor(GroupRelation.STRANGER, false, true)
                .contains(GroupShape.BYSTANDER_PRIVACY),
                "objecting on behalf of a thing you know nothing about is an interruption");
    }

    @Test
    @DisplayName("nobody makes small talk over somebody spending a confidence")
    void oversharingSilencesTheChitchat() {
        assertFalse(GroupDirector.shapesFor(
                        GroupRelation.of(true, true, KnowledgeSource.FAMILY), false, true)
                .contains(GroupShape.FRIENDLY_DISAGREEMENT));
    }

    // --- Every shape can actually speak ------------------------------------------------------------

    @Test
    @DisplayName("every shape has a full say pool in both locales")
    void everyShapeIsWritten() throws Exception {
        JsonObject en = readLang("en_us");
        JsonObject pt = readLang("pt_br");

        List<String> missing = new ArrayList<>();
        for (GroupShape shape : GroupShape.values()) {
            for (int variant = 1; variant <= 3; variant++) {
                String key = "dialogue." + shape.sayPool() + "/" + variant;
                if (!en.has(key)) {
                    missing.add(key + " (en_us)");
                }
                if (!pt.has(key)) {
                    missing.add(key + " (pt_br)");
                }
            }
        }
        assertTrue(missing.isEmpty(), String.join(", ", missing));
    }

    private static JsonObject readLang(String locale) throws Exception {
        Path path = Path.of("src/main/resources/assets/mca_dialogue/lang/" + locale + ".json");
        return JsonParser.parseString(Files.readString(path)).getAsJsonObject();
    }
}
