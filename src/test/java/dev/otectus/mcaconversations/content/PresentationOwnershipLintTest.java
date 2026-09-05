package dev.otectus.mcaconversations.content;

import dev.otectus.mcaconversations.support.TestPaths;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Keeps presentation ownership decided in one place.
 *
 * <p>MCA_ORIGINAL is the style nothing in CI can actually look at: MCA does not load in a
 * development runtime, so nobody sees the failure where the card intercepts a click over MCA's own
 * menu until it ships. What can be checked is the shape of the gate -- that every injector asks the
 * same question, and that the question is asked of the controller rather than of the config -- and
 * that is the part that has actually gone wrong before.
 */
class PresentationOwnershipLintTest {

    private static final Path MIXIN = TestPaths.of("src/main/java/dev/otectus/mcaconversations/"
            + "mixin/client/InteractScreenChoiceMixin.java");
    private static final Path RENDERER = TestPaths.of("src/main/java/dev/otectus/mcaconversations/"
            + "client/dialogue/DialogueChoiceRenderer.java");

    /** The injected methods that must never run while MCA owns its screen. */
    private static final List<String> INPUT_INJECTORS =
            List.of("mcaconversations$keyPressed", "mcaconversations$mouseClicked",
                    "mcaconversations$mouseScrolled");

    @Test
    void theMixinReadsOwnershipThroughTheControllerOnly() throws IOException {
        String source = stripComments(Files.readString(MIXIN, StandardCharsets.UTF_8));
        assertTrue(!source.contains("McaConversationsConfig"),
                "the mixin must not read the config itself; resolving numberedResponses and "
                        + "dialogueMenuStyle together is ClientChoiceController's job, and a second "
                        + "reading is a second answer");
    }

    @Test
    void everyInputInjectorIsGatedOnTheOneOwnershipCheck() throws IOException {
        String source = stripComments(Files.readString(MIXIN, StandardCharsets.UTF_8));
        List<String> offenders = new ArrayList<>();
        for (String injector : INPUT_INJECTORS) {
            String body = methodBody(source, injector);
            if (body == null) {
                offenders.add(injector + ": not found");
            } else if (!body.contains("mcaconversations$active()")) {
                offenders.add(injector + ": does not consult mcaconversations$active()");
            }
        }
        assertTrue(offenders.isEmpty(),
                () -> "input is intercepted without checking ownership:\n  "
                        + String.join("\n  ", offenders));
    }

    @Test
    void theRenderHeadSuppressesMcaOnlyWhileConversationsOwnsTheScreen() throws IOException {
        String source = stripComments(Files.readString(MIXIN, StandardCharsets.UTF_8));
        assertTrue(normalize(source).contains(
                        "if (mcaconversations$active() || mcaconversations$renderer"
                                + ".hasOutgoingPresentation()) {"),
                "the render HEAD condition is the one that hides MCA's question; it must stay "
                        + "exactly the ownership check plus the exit animation");
    }

    @Test
    void theRendererYieldsPresentationWithoutTheMixinsHelp() throws IOException {
        String source = stripComments(Files.readString(RENDERER, StandardCharsets.UTF_8));
        List<String> offenders = new ArrayList<>();
        String outgoing = methodBody(source, "hasOutgoingPresentation");
        if (outgoing == null || !outgoing.contains("conversationsDialogueEnabled()")) {
            offenders.add("hasOutgoingPresentation() must be false when MCA owns the screen");
        }
        String tick = methodBody(source, "public void tick");
        if (tick == null || !tick.contains("conversationsDialogueEnabled()")
                || !tick.contains("reset()")) {
            offenders.add("tick() must drop a prepared card once Conversations no longer owns "
                    + "presentation");
        }
        String render = methodBody(source, "LivingEntity speaker) {");
        if (render == null || !render.contains("customRenderer()") || !render.contains("reset()")) {
            offenders.add("render() must reset and return when the style has no custom renderer");
        }
        assertTrue(offenders.isEmpty(),
                () -> "the renderer can still draw over MCA's native menu:\n  "
                        + String.join("\n  ", offenders));
    }

    /**
     * The text from the first line naming {@code signature} to the matching closing brace. Crude on
     * purpose: it only has to be able to say which gate a body contains, and a real parser here
     * would be a bigger thing to maintain than the rule it enforces.
     */
    private static String methodBody(String source, String signature) {
        int at = source.indexOf(signature);
        if (at < 0) {
            return null;
        }
        int open = source.indexOf('{', at);
        if (open < 0) {
            return null;
        }
        int depth = 0;
        for (int i = open; i < source.length(); i++) {
            char c = source.charAt(i);
            if (c == '{') {
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0) {
                    return source.substring(open, i + 1);
                }
            }
        }
        return null;
    }

    /** Collapses line breaks and runs of spaces so a wrapped condition still matches. */
    private static String normalize(String source) {
        return source.replaceAll("\\s+", " ").replace(" .", ".");
    }

    private static String stripComments(String source) {
        return source
                .replaceAll("(?s)/\\*.*?\\*/", " ")
                .replaceAll("(?m)//.*$", " ");
    }
}
