package dev.otectus.mcaconversations.conversation;

import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The utterance carrier: it must survive the wire, never render, and tell two identical lines apart. */
class UtteranceTagTest {

    private static final UUID CONVERSATION = UUID.nameUUIDFromBytes("utterance-conversation".getBytes());
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("utterance-villager".getBytes());

    private static UtteranceTag.Id id(long seq) {
        return new UtteranceTag.Id(CONVERSATION, seq, UtteranceTag.Kind.NPC_LINE, null);
    }

    private static Component line(String key) {
        return Component.translatable(key, "Ada");
    }

    @Test
    void anInsertionRoundTrips() {
        UtteranceTag.Id original = new UtteranceTag.Id(CONVERSATION, 42L, UtteranceTag.Kind.SILENT_PROMPT, VILLAGER);
        assertEquals(Optional.of(original), UtteranceTag.decode(UtteranceTag.encode(original)));
        UtteranceTag.Id noSpeaker = id(7L);
        assertEquals(Optional.of(noSpeaker), UtteranceTag.decode(UtteranceTag.encode(noSpeaker)));
    }

    @Test
    void foreignOrMalformedInsertionsAreUnknownNotErrors() {
        assertTrue(UtteranceTag.decode(null).isEmpty());
        assertTrue(UtteranceTag.decode("minecraft:something").isEmpty());
        assertTrue(UtteranceTag.decode(UtteranceTag.PREFIX + "not-a-uuid/1/N").isEmpty());
        assertTrue(UtteranceTag.decode(UtteranceTag.PREFIX + CONVERSATION + "/0/N").isEmpty(), "seq starts at 1");
        assertTrue(UtteranceTag.decode(UtteranceTag.PREFIX + CONVERSATION + "/3/X").isEmpty(), "unknown kind");
        assertTrue(UtteranceTag.decode(UtteranceTag.PREFIX + CONVERSATION + "/3").isEmpty());
    }

    @Test
    void aTaggedTranslatableKeepsKeyArgumentsAndRendersTheSame() {
        Component plain = line("dialogue.conversations.test/2");
        Component tagged = UtteranceTag.apply(plain, id(3L));
        TranslatableContents contents = (TranslatableContents) tagged.getContents();
        assertEquals("dialogue.conversations.test/2", contents.getKey());
        assertEquals(2, contents.getArgs().length, "the original argument, then the tag");
        assertEquals("Ada", contents.getArgs()[0]);
        assertEquals(plain.getString(), tagged.getString(), "an unused argument is never rendered");
        assertEquals(Optional.of(id(3L)), UtteranceTag.read(tagged));
    }

    @Test
    void theTagSurvivesTheJsonRoundTripMcaPacketsUse() {
        Component tagged = UtteranceTag.apply(line("dialogue.conversations.test/1"), id(9L));
        // 1.21.1 MCA packets carry components through ComponentSerialization's codec, which is what
        // this JSON form serialises with; no registry-backed content is involved, so EMPTY suffices.
        MutableComponent back = Component.Serializer.fromJson(
                Component.Serializer.toJson(tagged, RegistryAccess.EMPTY), RegistryAccess.EMPTY);
        assertEquals(Optional.of(id(9L)), UtteranceTag.read(back));
        assertEquals(tagged.getString(), back.getString());
    }

    @Test
    void twoIdenticalSentencesAreTwoKeysInMcasSpeechMap() {
        // MCA's association map is keyed by TranslatableContents.equals, which compares arguments.
        Component first = UtteranceTag.apply(line("dialogue.conversations.same"), id(1L));
        Component second = UtteranceTag.apply(line("dialogue.conversations.same"), id(2L));
        assertNotEquals(first.getContents(), second.getContents());
        assertEquals(first.getString(), second.getString());
    }

    @Test
    void aTagIsAppliedAtMostOnce() {
        Component once = UtteranceTag.apply(line("dialogue.conversations.once"), id(4L));
        assertSame(once, UtteranceTag.apply(once, id(5L)));
        assertEquals(Optional.of(id(4L)), UtteranceTag.read(once));
    }

    @Test
    void aLiteralIsWrappedRendersIdenticallyAndUnwrapsForSpeech() {
        Component literal = Component.literal("The king is dead.").withStyle(style -> style.withItalic(true));
        Component wrapped = UtteranceTag.apply(literal, id(6L));
        assertEquals(literal.getString(), wrapped.getString());
        assertTrue(UtteranceTag.isWrapper(wrapped));
        assertSame(literal, UtteranceTag.speechCarrier(wrapped),
                "MCA speaks a literal as its text; it must be handed the literal, not the wrapper");
        assertFalse(UtteranceTag.isWrapper(literal));
    }

    @Test
    void aLineTagWinsOverAFeedbackTagInTheSameMessage() {
        Component spoken = UtteranceTag.apply(line("dialogue.chatmode.reply"), id(10L));
        Component feedback = UtteranceTag.apply(Component.literal(" (+2 ♥)"),
                new UtteranceTag.Id(CONVERSATION, 11L, UtteranceTag.Kind.FEEDBACK, VILLAGER));
        MutableComponent message = Component.empty().append(feedback).append(Component.literal("Mira: ")).append(spoken);
        UtteranceTag.Located located = UtteranceTag.locate(message).orElseThrow();
        assertEquals(10L, located.id().seq());
        assertSame(spoken, located.carrier());
        // Only feedback in it: found, and its kind says not to speak it.
        UtteranceTag.Located onlyFeedback = UtteranceTag.locate(Component.empty().append(feedback)).orElseThrow();
        assertEquals(UtteranceTag.Kind.FEEDBACK, onlyFeedback.id().kind());
        assertFalse(onlyFeedback.id().kind().speakable());
    }

    @Test
    void aGuiCardLineIsFoundUnderTheSpeakerName() {
        // What InteractScreen hands Font.split for a non-silent line: name.append(message).
        Component tagged = UtteranceTag.apply(line("dialogue.conversations.card"), id(12L));
        MutableComponent card = Component.literal("Mira: ").append(tagged);
        assertEquals(Optional.of(id(12L)), UtteranceTag.read(card.copy()));
    }

    @Test
    void untaggedComponentsReadAsUnknown() {
        assertTrue(UtteranceTag.read(Component.literal("plain")).isEmpty());
        assertTrue(UtteranceTag.read(line("dialogue.main")).isEmpty());
        assertTrue(UtteranceTag.read(null).isEmpty());
    }
}
