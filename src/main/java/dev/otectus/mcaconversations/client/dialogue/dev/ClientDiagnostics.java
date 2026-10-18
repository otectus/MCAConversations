package dev.otectus.mcaconversations.client.dialogue.dev;

import java.util.EnumSet;
import java.util.Set;

/**
 * What a player's client can say about its own dialogue and speech path, for
 * {@code /conversations client diagnose} and {@code speech-trace} (feedback remediation WP0).
 * Client-only by placement and by use.
 *
 * <p>The latches are set by the injectors themselves the first time each one runs, so "applied"
 * means "has actually fired in this client", which is the question a bug report needs answered —
 * a mixin that was configured but never reached is indistinguishable from one that was skipped.
 */
public final class ClientDiagnostics {

    /** The client hooks whose presence decides how a line reaches the screen, the chat log and speech. */
    public enum Hook {
        VILLAGER_MESSAGE("VillagerMessageMixin"),
        INTERACT_SCREEN("InteractScreenChoiceMixin"),
        MCA_CLIENT("MCAClientMixin"),
        CHAT_LISTENER("ChatListenerSpeechMixin");

        private final String mixin;

        Hook(String mixin) {
            this.mixin = mixin;
        }

        public String mixin() {
            return mixin;
        }
    }

    private static final Set<Hook> FIRED = EnumSet.noneOf(Hook.class);
    private static volatile boolean speechTrace;

    private ClientDiagnostics() {
    }

    /** Called by an injector each time it runs; cheap after the first. */
    public static void fired(Hook hook) {
        if (!FIRED.contains(hook)) {
            synchronized (FIRED) {
                FIRED.add(hook);
            }
        }
    }

    public static boolean hasFired(Hook hook) {
        synchronized (FIRED) {
            return FIRED.contains(hook);
        }
    }

    /** Whether one INFO line is logged per speech hand-off (keys and identities, never text). */
    public static boolean speechTrace() {
        return speechTrace;
    }

    public static void speechTrace(boolean on) {
        speechTrace = on;
    }

    /** Whether diagnostic output may include dialogue text: only with an explicit system property. */
    public static boolean textAllowed() {
        return Boolean.getBoolean("mcaconversations.diag.text");
    }
}
