package dev.otectus.mcaconversations.compat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The plugin may only ever remove a target it knows is absent. Everything it cannot decide must be
 * applied exactly as the configuration applied it before the plugin existed, because a skipped
 * target is a hook that silently never runs.
 */
class MixinTargetPluginTest {

    @Test
    @DisplayName("MCA and Townstead targets are checked against their own mod; anything else is not checked")
    void ownersFollowTheTargetPackage() {
        assertEquals("mca", MixinTargetPlugin.ownerOf("net.conczin.mca.network.Network"));
        assertEquals("mca", MixinTargetPlugin.ownerOf("net.conczin.mca.resources.Dialogues"));
        assertEquals("townstead", MixinTargetPlugin.ownerOf(
                "com.aetherianartificer.townstead.client.gui.dialogue.ChoicePanel"));
        assertNull(MixinTargetPlugin.ownerOf("net.minecraft.client.gui.screens.ChatScreen"));
        assertNull(MixinTargetPlugin.ownerOf(null));
    }

    @Test
    @DisplayName("only a definite absence skips a target")
    void onlyDefiniteAbsenceSkips() {
        assertTrue(MixinTargetPlugin.applies(Boolean.TRUE));
        assertTrue(MixinTargetPlugin.applies(null), "cannot tell: apply, as without the plugin");
        assertFalse(MixinTargetPlugin.applies(Boolean.FALSE));
    }

    @Test
    @DisplayName("a class nothing ships is never reported present")
    void anUnshippedClassIsNeverPresent() {
        // Forge's unit JVM has no loading mod list (null: cannot tell); NeoForge's loads MCA (false: a
        // definite no). Either way the plugin must never claim a target that does not exist.
        assertNotEquals(Boolean.TRUE, MixinTargetPlugin.present("mca", "net.conczin.mca.NoSuchClassAnywhere"));
        assertNotEquals(Boolean.TRUE, MixinTargetPlugin.present("no_such_mod", "net.conczin.mca.resources.Dialogues"));
    }

    @Test
    @DisplayName("against the real MCA jar the lookup finds what MCA ships and nothing else")
    void lookupAgainstTheLoadedMcaJar() {
        // NeoForge's unit JVM carries MCA in its loading mod list, so this is the lookup the game makes.
        assertEquals(Boolean.TRUE, MixinTargetPlugin.present("mca", "net.conczin.mca.resources.Dialogues"));
        assertEquals(Boolean.FALSE, MixinTargetPlugin.present("mca", "net.conczin.mca.NoSuchClassAnywhere"));
    }

    @Test
    @DisplayName("a target no owner claims is always applied")
    void unownedTargetsAlwaysApply() {
        assertTrue(new MixinTargetPlugin().shouldApplyMixin("net.minecraft.client.gui.screens.ChatScreen",
                "dev.otectus.mcaconversations.mixin.client.ChatScreenChoiceMixin"));
    }
}
