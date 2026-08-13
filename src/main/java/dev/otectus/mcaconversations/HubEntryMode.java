package dev.otectus.mcaconversations;

/**
 * How the Conversations hub is reached from MCA's villager <em>interaction screen</em>.
 *
 * <p>This replaces the 0.7.x boolean {@code replaceChatWithConversations}, which only had two
 * settings and made the third useful state unreachable: with it off, MCA's Chat behaved normally
 * but the Conversations hub had no entry point at all.
 *
 * <p><b>Not to be confused with chat mode</b> ({@code enableChatMode} and the {@code chat} package).
 * That feature is about talking to villagers in normal chat with natural language. This enum is only
 * about which button opens the hub inside MCA's own interaction GUI. They are independent: chat mode
 * works in every hub-entry mode, and vice versa.
 *
 * <p><b>None of these modes touch MCA's AI chat</b> either. That is driven entirely by
 * {@code MixinServerPlayNetworkHandler.handleChat} — a player names a nearby villager in normal
 * chat, or continues an open conversation — and never routes through the dialogue system, so MCA's
 * AI listener, endpoint, token and conversation state are identical in every mode.
 */
public enum HubEntryMode {

    /**
     * Default. MCA's Chat answer keeps its own behaviour and Conversations appears as its own
     * button, injected into MCA's {@code main} question through the datapack merge that
     * {@code Dialogues.loadDialogue} already performs for same-named questions.
     */
    ADDITIVE,

    /**
     * The 0.2.0–0.9.x behaviour: MCA's Chat answer is rerouted to the Conversations hub and the
     * separate button is hidden, so the screen does not show two ways into the same place.
     */
    REPLACE,

    /**
     * No Conversations button, and MCA's Chat is left alone. Dialogue conditions, gossip, memory,
     * chat mode and the rest still run — this only removes the interaction-screen entry.
     */
    HIDDEN;

    /** True when MCA's {@code chat} question should be rerouted to the hub. */
    public boolean replacesMcaChat() {
        return this == REPLACE;
    }

    /** True when our injected {@code main} menu answer should be offered to the player. */
    public boolean showsOwnButton() {
        return this == ADDITIVE;
    }
}
