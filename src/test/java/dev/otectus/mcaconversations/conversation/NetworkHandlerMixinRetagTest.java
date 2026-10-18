package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.chat.VillagerAttention;
import net.minecraft.network.chat.Component;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * What the outgoing-packet hook does with MCA's own single-line packets ({@code UtteranceStamp
 * .managedRetag}, the decision {@code NetworkHandlerMixin} acts on). The packet rebuild itself is
 * MCA's constructor and is covered by the binding probe; this pins which lines are re-sent.
 */
class NetworkHandlerMixinRetagTest {

    private static final UUID PLAYER = UUID.nameUUIDFromBytes("retag-player".getBytes());
    private static final UUID VILLAGER = UUID.nameUUIDFromBytes("retag-villager".getBytes());

    @BeforeEach
    @AfterEach
    void reset() {
        ConversationLifecycle.clearTeardownHooks();
        ConversationSessions.clearAllForTesting();
        ConversationPresence.clear();
        OpenRateLimiter.clear();
        VillagerAttention.reset();
        ChatModeScheduler.reset();
    }

    private static ConversationHandle begin() {
        return ConversationLifecycle.begin(PLAYER, VILLAGER, "minecraft:overworld",
                ConversationSession.Frontend.GUI, 100L).orElseThrow();
    }

    @Test
    void anUnmanagedInteractionIsLeftByteIdentical() {
        ConversationSessions.get(PLAYER, 0L);
        assertNull(UtteranceStamp.managedRetag(PLAYER, Component.translatable("dialogue.main"), false));
    }

    @Test
    void aManagedPagePromptIsTaggedWithTheDiscussionAndItsSilence() {
        ConversationHandle handle = begin();
        Component tagged = UtteranceStamp.managedRetag(PLAYER,
                Component.translatable("#Tadult.dialogue.conversations.family"), true);
        assertNotNull(tagged);
        UtteranceTag.Id id = UtteranceTag.read(tagged).orElseThrow();
        assertEquals(handle.sessionId(), id.identity());
        assertEquals(UtteranceTag.Kind.SILENT_PROMPT, id.kind());
        Utterance recorded = ConversationSessions.raw(PLAYER).orElseThrow().recentUtterances().get(0);
        assertEquals("dialogue.conversations.family", recorded.key(), "markers are stripped on record");
        assertEquals(id.seq(), recorded.seq());
    }

    @Test
    void aLineThisModAlreadyTaggedPassesThrough() {
        begin();
        Component ours = UtteranceStamp.stamp(PLAYER, Component.translatable("dialogue.conversations.x/2"),
                UtteranceTag.Kind.NPC_LINE, ConversationSession.Frontend.GUI, null);
        assertTrue(UtteranceTag.read(ours).isPresent());
        assertNull(UtteranceStamp.managedRetag(PLAYER, ours, false), "re-tagging is a no-op");
        assertSame(ours, UtteranceStamp.stamp(PLAYER, ours, UtteranceTag.Kind.NPC_LINE,
                ConversationSession.Frontend.GUI, null));
        assertEquals(1, ConversationSessions.raw(PLAYER).orElseThrow().recentUtterances().size(),
                "a line is recorded once, where it was tagged");
    }

    @Test
    void aGraphicalLineWithNoSessionIsSentUntagged() {
        Component line = Component.translatable("dialogue.conversations.y");
        assertSame(line, UtteranceStamp.stamp(PLAYER, line, UtteranceTag.Kind.NPC_LINE,
                ConversationSession.Frontend.GUI, null));
    }

    @Test
    void aChatGreetingWithNoSessionStillGetsAnIdentityAndAMonotonicSeq() {
        Component first = UtteranceStamp.stamp(PLAYER, Component.translatable("dialogue.chatmode.hail/1"),
                UtteranceTag.Kind.NPC_LINE, ConversationSession.Frontend.CHAT, VILLAGER);
        Component second = UtteranceStamp.stamp(PLAYER, Component.translatable("dialogue.chatmode.hail/1"),
                UtteranceTag.Kind.NPC_LINE, ConversationSession.Frontend.CHAT, VILLAGER);
        UtteranceTag.Id a = UtteranceTag.read(first).orElseThrow();
        UtteranceTag.Id b = UtteranceTag.read(second).orElseThrow();
        assertEquals(a.identity(), b.identity());
        assertTrue(b.seq() > a.seq());
        assertEquals(VILLAGER, a.speaker(), "a chat line names its speaker for the optional chat speech");
        assertTrue(ConversationSessions.raw(PLAYER).isEmpty(), "tagging never creates a session");
    }
}
