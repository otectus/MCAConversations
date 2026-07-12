package dev.otectus.mcaconversations.chat;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The chat line template substitution must keep the villager-line {@link Component} intact (never
 * flatten it to a string) so client-side lang/personality styling survives. These tests assert the
 * rendered plain text; the point is that {@code %2$s} is spliced as a live component, not formatted.
 */
class ChatDeliveryTest {

    @Test
    void defaultTemplate_wrapsNameInAngleBrackets() {
        MutableComponent out = ChatDelivery.applyFormat("<%1$s> %2$s",
                Component.literal("Agnes"), Component.literal("Good morning."));
        assertEquals("<Agnes> Good morning.", out.getString());
    }

    @Test
    void colonTemplate_isSupported() {
        MutableComponent out = ChatDelivery.applyFormat("%1$s: %2$s",
                Component.literal("Ilsa"), Component.literal("Hm."));
        assertEquals("Ilsa: Hm.", out.getString());
    }

    @Test
    void reversedPlaceholders_areHonored() {
        MutableComponent out = ChatDelivery.applyFormat("%2$s — %1$s",
                Component.literal("Agnes"), Component.literal("Leave me be"));
        assertEquals("Leave me be — Agnes", out.getString());
    }

    @Test
    void templateWithoutPlaceholders_isLiteral() {
        MutableComponent out = ChatDelivery.applyFormat("static text",
                Component.literal("Agnes"), Component.literal("hi"));
        assertEquals("static text", out.getString());
    }

    @Test
    void lineComponentIsPreserved_notFlattened() {
        Component line = Component.literal("styled");
        MutableComponent out = ChatDelivery.applyFormat("<%1$s> %2$s", Component.literal("Agnes"), line);
        // The exact line component instance is appended as a sibling, not stringified.
        assertEquals(line, out.getSiblings().get(out.getSiblings().size() - 1));
    }
}
