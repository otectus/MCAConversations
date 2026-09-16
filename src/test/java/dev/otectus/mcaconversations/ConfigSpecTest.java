package dev.otectus.mcaconversations;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The 1.5.0 split of the configuration into three specs, and the safety contract that split needs.
 *
 * <p>Before 1.5.0 every gameplay value lived in {@code mcaconversations-common.toml}. A COMMON spec is
 * loaded on both sides of a connection and synchronised on neither, so a client and a server could
 * disagree about how far a villager hears, how many hearts a day a conversation may pay, or how often
 * a villager may speak first — decisions the server has to make alone. Those values moved to a SERVER
 * spec, which Forge stores per world and synchronises to every client.
 *
 * <p>The move creates a hazard this test is mostly about. A {@link ForgeConfigSpec} value throws from
 * {@code get()} until its file is loaded, and a SERVER spec is not loaded until a world is. Every one
 * of these values is read from a dialogue condition, an entity tick or a chat listener — places where
 * a throw would be taken by MCA's selection loop or by the server tick rather than by us. So each one
 * is read through an accessor on {@link McaConversationsConfig} that answers a documented default in
 * that window, and this test runs with no world at all, which is exactly that window.
 */
class ConfigSpecTest {

    /**
     * Every server accessor, with the default its own javadoc and the TOML comment promise.
     *
     * <p>Kept as data rather than as one assertion per value so that adding a knob without adding it
     * here is the failure — a new accessor that throws on an unloaded spec would otherwise only be
     * discovered by a player creating a world.
     */
    private static final Map<String, Object> EXPECTED_DEFAULTS = Map.ofEntries(
            Map.entry("chat.chatModeRadius", 12.0),
            Map.entry("chat.chatModeAddressedRadius", 24.0),
            Map.entry("chat.chatModeStickinessTicks", 600),
            Map.entry("chat.chatModeGreetChance", 0.35),
            Map.entry("chat.chatModeAttentionTicks", 600),
            Map.entry("conversation.conversationHeartMultiplier", 1.0),
            Map.entry("conversation.conversationDailyPositiveCap", 8),
            Map.entry("conversation.conversationDailyNegativeCap", 10),
            Map.entry("conversation.strongerNegativeOutcomes", false),
            Map.entry("conversation.conversationSessionTimeoutTicks", 1200),
            Map.entry("conversation.continueDistance", 16.0),
            Map.entry("conversation.immediateCloseDistance", 24.0),
            Map.entry("conversation.distanceGraceTicks", 20),
            Map.entry("conversation.guiLeaseTicks", 100),
            Map.entry("conversation.holdVillagerDuringInteraction", true),
            Map.entry("conversation.attackReopenDelayTicks", 100),
            Map.entry("conversation.attackedBehavior",
                    McaConversationsConfig.AttackedBehavior.NATIVE_COMBAT),
            Map.entry("conversation.interruptOnImmediateDanger", true),
            Map.entry("rpg.dispositionGainMultiplier", 1.0),
            Map.entry("rpg.dispositionDecayMultiplier", 1.0),
            Map.entry("rpg.dispositionDailyAxisCap", 8),
            Map.entry("rpg.dispositionStaleDays", 0),
            Map.entry("dynamic.maxInitiativesPerVillagerPlayerDay", 1),
            Map.entry("dynamic.initiativeCooldownTicks", 300),
            Map.entry("dynamic.dynamicTopicSlots", 3),
            Map.entry("history.episodeRetentionDays", 32),
            Map.entry("history.activeEpisodeCap", 6),
            Map.entry("history.resolvedEpisodeCap", 24),
            Map.entry("history.openThreadCapPerPair", 8),
            Map.entry("history.commitmentCapPerPair", 8),
            Map.entry("history.playerClaimCapPerPair", 16),
            Map.entry("history.socialEdgeCapPerVillager", 16),
            Map.entry("history.topicRecencyCapPerPair", 32));

    @Test
    void allThreeSpecsBuild() {
        assertNotNull(McaConversationsConfig.COMMON_SPEC, "common spec");
        assertNotNull(McaConversationsConfig.SERVER_SPEC, "server spec");
        assertNotNull(McaConversationsConfig.CLIENT_SPEC, "client spec");
    }

    @Test
    void everyGameplayValueLivesInTheServerSpec() {
        UnmodifiableConfig server = McaConversationsConfig.SERVER_SPEC.getSpec();
        for (String path : EXPECTED_DEFAULTS.keySet()) {
            assertTrue(server.contains(path), path + " is missing from the server spec");
        }
    }

    @Test
    void noGameplayValueIsStillDeclaredInTheCommonSpec() {
        // The common file is not rewritten for existing installs, so a leftover declaration would not
        // merely be duplicated documentation: two specs would answer the same question and whichever
        // the caller happened to read would win.
        UnmodifiableConfig common = McaConversationsConfig.COMMON_SPEC.getSpec();
        for (String path : EXPECTED_DEFAULTS.keySet()) {
            assertFalse(common.contains(path),
                    path + " was moved to the server spec but is still declared in the common spec");
        }
    }

    @Test
    void featureSwitchesAndDebugFlagsStayCommon() {
        // The counterpart to the rule above: what is genuinely per-installation must NOT move. A
        // server owner turning the whole living-histories layer off, or turning debug logging on, is
        // not making a decision the client has to agree with.
        UnmodifiableConfig common = McaConversationsConfig.COMMON_SPEC.getSpec();
        for (String path : List.of("features.enableTopics", "features.hubEntryMode",
                "rpg.enableDispositions", "rpg.enableChecks", "rpg.debugRpg",
                "chat.enableChatMode", "chat.chatModeGreetOnApproach",
                "dynamic.enabled", "dynamic.debugDirector", "history.enabled",
                "group.enabled", "debug.debugLogging")) {
            assertTrue(common.contains(path), path + " must stay in the common spec");
        }
    }

    @Test
    void everyAccessorAnswersItsDocumentedDefaultWithNoWorldLoaded() {
        // No config file has been loaded in this JVM, so every underlying get() throws. Reaching the
        // documented default here is the whole contract: a dialogue condition evaluated during world
        // creation must not take the reload down with it.
        assertEquals(12.0, McaConversationsConfig.chatModeRadius());
        assertEquals(24.0, McaConversationsConfig.chatModeAddressedRadius());
        assertEquals(0.35, McaConversationsConfig.chatModeGreetChance());
        assertEquals(600, McaConversationsConfig.chatModeAttentionTicks());
        assertEquals(600, McaConversationsConfig.chatModeStickinessTicks());

        assertEquals(1.0, McaConversationsConfig.conversationHeartMultiplier());
        assertEquals(8, McaConversationsConfig.conversationDailyPositiveCap());
        assertEquals(10, McaConversationsConfig.conversationDailyNegativeCap());
        assertFalse(McaConversationsConfig.strongerNegativeOutcomes());
        assertEquals(1200, McaConversationsConfig.conversationSessionTimeoutTicks());

        // 1.7.1: the continued-distance policy and the presence lease. These are read from the
        // server tick, which runs long before a server config file is guaranteed to be loaded.
        assertEquals(16.0, McaConversationsConfig.continueDistance());
        assertEquals(24.0, McaConversationsConfig.immediateCloseDistance());
        assertEquals(20, McaConversationsConfig.distanceGraceTicks());
        assertEquals(100, McaConversationsConfig.guiLeaseTicks());
        assertTrue(McaConversationsConfig.holdVillagerDuringInteraction());
        assertEquals(100, McaConversationsConfig.attackReopenDelayTicks());
        assertEquals(McaConversationsConfig.AttackedBehavior.NATIVE_COMBAT,
                McaConversationsConfig.attackedBehavior());
        assertTrue(McaConversationsConfig.interruptOnImmediateDanger());

        assertEquals(1.0, McaConversationsConfig.dispositionGainMultiplier());
        assertEquals(1.0, McaConversationsConfig.dispositionDecayMultiplier());
        assertEquals(8, McaConversationsConfig.dispositionDailyAxisCap());
        assertEquals(0, McaConversationsConfig.dispositionStaleDays());

        assertEquals(1, McaConversationsConfig.maxInitiativesPerVillagerPlayerDay());
        assertEquals(300, McaConversationsConfig.initiativeCooldownTicks());
        assertEquals(3, McaConversationsConfig.dynamicTopicSlots());

        assertEquals(32, McaConversationsConfig.episodeRetentionDays());
        assertEquals(6, McaConversationsConfig.activeEpisodeCap());
        assertEquals(24, McaConversationsConfig.resolvedEpisodeCap());
        assertEquals(8, McaConversationsConfig.openThreadCapPerPair());
        assertEquals(8, McaConversationsConfig.commitmentCapPerPair());
        assertEquals(16, McaConversationsConfig.playerClaimCapPerPair());
        assertEquals(16, McaConversationsConfig.socialEdgeCapPerVillager());
        assertEquals(32, McaConversationsConfig.topicRecencyCapPerPair());
    }

    @Test
    void accessorDefaultsMatchWhatTheSpecWouldHaveWritten() {
        // The accessor default and the spec default are two hand-written copies of one number. When
        // they disagree, a server that has never edited its TOML behaves differently before and after
        // its world finishes loading — the hardest kind of bug to see.
        UnmodifiableConfig spec = McaConversationsConfig.SERVER_SPEC.getSpec();
        EXPECTED_DEFAULTS.forEach((path, expected) -> {
            ForgeConfigSpec.ValueSpec value =
                    assertInstanceOf(ForgeConfigSpec.ValueSpec.class, spec.get(path), path);
            assertEquals(expected, value.getDefault(), path + " default");
        });
    }

    @Test
    void gameplayRangesRejectValuesOutsideThem() {
        // defineInRange is the reason a hand-edited TOML cannot hand the director a negative radius or
        // an unbounded heart budget. Forge falls back to the default for a value that fails test().
        UnmodifiableConfig spec = McaConversationsConfig.SERVER_SPEC.getSpec();

        ForgeConfigSpec.ValueSpec radius = (ForgeConfigSpec.ValueSpec) spec.get("chat.chatModeRadius");
        assertTrue(radius.test(12.0));
        assertFalse(radius.test(0.5), "below the documented minimum");
        assertFalse(radius.test(100.0), "above the documented maximum");

        ForgeConfigSpec.ValueSpec initiatives =
                (ForgeConfigSpec.ValueSpec) spec.get("dynamic.maxInitiativesPerVillagerPlayerDay");
        assertTrue(initiatives.test(0), "0 is the documented way to switch initiative off");
        assertTrue(initiatives.test(8));
        assertFalse(initiatives.test(9));
        assertFalse(initiatives.test(-1));

        ForgeConfigSpec.ValueSpec cooldown =
                (ForgeConfigSpec.ValueSpec) spec.get("dynamic.initiativeCooldownTicks");
        assertTrue(cooldown.test(300));
        assertFalse(cooldown.test(0), "there is no such thing as no cooldown at all");
    }

    @Test
    void theDiscussionDistancesAndLeaseAreBoundedAndOrdered() {
        // The distance policy normalises a nonsensical pair for itself, but the spec should not let
        // one be written in the first place: a radius of zero or a lease of a real-time hour is a
        // typo, not a configuration. Zero is kept meaningful where it means "off" and refused where
        // it would mean "no conversation may continue at all".
        UnmodifiableConfig spec = McaConversationsConfig.SERVER_SPEC.getSpec();

        ForgeConfigSpec.ValueSpec continued =
                (ForgeConfigSpec.ValueSpec) spec.get("conversation.continueDistance");
        assertTrue(continued.test(16.0));
        assertFalse(continued.test(0.0), "a conversation has to be holdable at some distance");
        assertFalse(continued.test(128.0));

        ForgeConfigSpec.ValueSpec immediate =
                (ForgeConfigSpec.ValueSpec) spec.get("conversation.immediateCloseDistance");
        assertTrue(immediate.test(24.0));
        assertFalse(immediate.test(0.0));

        ForgeConfigSpec.ValueSpec grace =
                (ForgeConfigSpec.ValueSpec) spec.get("conversation.distanceGraceTicks");
        assertTrue(grace.test(0), "0 is the documented way to end the moment the pair separate");
        assertFalse(grace.test(-1));

        ForgeConfigSpec.ValueSpec lease =
                (ForgeConfigSpec.ValueSpec) spec.get("conversation.guiLeaseTicks");
        assertTrue(lease.test(0), "0 is the documented way to switch the lease off");
        assertTrue(lease.test(100));
        assertFalse(lease.test(-1));

        ForgeConfigSpec.ValueSpec attacked =
                (ForgeConfigSpec.ValueSpec) spec.get("conversation.attackedBehavior");
        assertEquals(McaConversationsConfig.AttackedBehavior.NATIVE_COMBAT, attacked.getDefault());
        for (McaConversationsConfig.AttackedBehavior value
                : McaConversationsConfig.AttackedBehavior.values()) {
            assertTrue(attacked.test(value), value + " must be accepted by the spec");
        }
    }

    @Test
    void clientSpecDefaultsToTheRestrainedPresentation() {
        // 1.6.3: an install that has never stated a preference gets MINIMAL and REDUCED. The
        // constants are asserted alongside the spec because the client answers them directly while
        // no file is loaded, and a disagreement would show as a different card for a few frames.
        UnmodifiableConfig spec = McaConversationsConfig.CLIENT_SPEC.getSpec();
        assertTrue(spec.contains("display.dialogueMenuStyle"), "display.dialogueMenuStyle");

        ForgeConfigSpec.ValueSpec style = assertInstanceOf(
                ForgeConfigSpec.ValueSpec.class, spec.get("display.dialogueMenuStyle"));
        assertEquals(McaConversationsConfig.DialogueMenuStyle.MINIMAL, style.getDefault());
        assertEquals(McaConversationsConfig.DEFAULT_DIALOGUE_MENU_STYLE, style.getDefault());
        for (McaConversationsConfig.DialogueMenuStyle value
                : McaConversationsConfig.DialogueMenuStyle.values()) {
            assertTrue(style.test(value), value + " must be accepted by the spec");
        }

        ForgeConfigSpec.ValueSpec motion = assertInstanceOf(
                ForgeConfigSpec.ValueSpec.class, spec.get("display.motionMode"));
        assertEquals(McaConversationsConfig.MotionMode.REDUCED, motion.getDefault());
        assertEquals(McaConversationsConfig.DEFAULT_MOTION_MODE, motion.getDefault());
        for (McaConversationsConfig.MotionMode value : McaConversationsConfig.MotionMode.values()) {
            assertTrue(motion.test(value), value + " must be accepted by the spec");
        }
    }

    @Test
    void anExistingFileKeepsItsRicherPresentationAcrossTheDefaultChange() {
        // The upgrade hazard in changing a default: Forge writes every declared key when it first
        // saves the file, so a player who has ever run 1.5.x has display.dialogueMenuStyle stated
        // explicitly. Correcting such a file must leave RESPONSIVE, FULL and the legacy override
        // exactly as they are — a stored value is a choice, and nothing here may read it as a
        // leftover default.
        CommentedConfig existing = CommentedConfig.inMemory();
        existing.set("display.numberedResponses", false);
        existing.set("display.dialogueMenuStyle", "RESPONSIVE");
        existing.set("display.motionMode", "FULL");
        existing.set("display.questionRevealMode", "FAST");
        McaConversationsConfig.CLIENT_SPEC.correct(existing);

        assertEquals(Boolean.FALSE, existing.<Boolean>get("display.numberedResponses"),
                "the deprecated MCA_ORIGINAL override must survive a default change");
        assertEquals(McaConversationsConfig.DialogueMenuStyle.RESPONSIVE,
                existing.getEnum("display.dialogueMenuStyle",
                        McaConversationsConfig.DialogueMenuStyle.class));
        assertEquals(McaConversationsConfig.MotionMode.FULL,
                existing.getEnum("display.motionMode", McaConversationsConfig.MotionMode.class));
        assertEquals(McaConversationsConfig.QuestionReveal.FAST,
                existing.getEnum("display.questionRevealMode",
                        McaConversationsConfig.QuestionReveal.class),
                "an explicitly enabled reveal is not switched off by anything in 1.6.3");
    }

    @Test
    void anAbsentKeyIsTheOnlyThingTheNewDefaultsReach() {
        // The other half of the same contract: a file that never mentions these keys — a new
        // install, or one that was hand-trimmed — is corrected to the recommended pair, and the
        // reveal stays off because that default did not change.
        CommentedConfig fresh = CommentedConfig.inMemory();
        McaConversationsConfig.CLIENT_SPEC.correct(fresh);

        assertEquals(McaConversationsConfig.DialogueMenuStyle.MINIMAL,
                fresh.getEnum("display.dialogueMenuStyle",
                        McaConversationsConfig.DialogueMenuStyle.class));
        assertEquals(McaConversationsConfig.MotionMode.REDUCED,
                fresh.getEnum("display.motionMode", McaConversationsConfig.MotionMode.class));
        assertEquals(McaConversationsConfig.QuestionReveal.OFF,
                fresh.getEnum("display.questionRevealMode",
                        McaConversationsConfig.QuestionReveal.class));
        assertEquals(Boolean.TRUE, fresh.<Boolean>get("display.numberedResponses"));
    }

    @Test
    void dialogueMenuStyleIsNotDeclaredInCommonOrServerSpec() {
        // Presentation is a client decision. A server-side copy would be one more thing able to
        // disagree with the client about a question the server never asks.
        assertFalse(McaConversationsConfig.COMMON_SPEC.getSpec().contains("display.dialogueMenuStyle"));
        assertFalse(McaConversationsConfig.SERVER_SPEC.getSpec().contains("display.dialogueMenuStyle"));
    }

    @Test
    void numberedResponsesRemainsDeclaredWithDefaultTrue() {
        // Deprecated, not removed: existing installs that set it false chose MCA's native UI on
        // purpose and must keep it.
        UnmodifiableConfig spec = McaConversationsConfig.CLIENT_SPEC.getSpec();
        assertTrue(spec.contains("display.numberedResponses"));
        ForgeConfigSpec.ValueSpec numbered = assertInstanceOf(
                ForgeConfigSpec.ValueSpec.class, spec.get("display.numberedResponses"));
        assertEquals(Boolean.TRUE, numbered.getDefault());
    }
}
