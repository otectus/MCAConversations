package dev.otectus.mcaconversations.state;

import java.util.Locale;
import java.util.Optional;

/**
 * A short-lived conversational mood a villager can be in after something happens to it or around it.
 * Each state is written into MCA's LongTermMemory as an expiring {@code mcaconversations.state.<name>}
 * entry (see {@link MemoryIds#state}); dialogue JSON gates on it with a plain MCA-native
 * {@code {"memory": {"id": "mcaconversations.state.<name>"}}} condition — no custom condition needed.
 *
 * <p><b>Scoping.</b> Player-scoped states are caused by a specific player (a gift, a completed quest,
 * a punch) and are only "felt" toward that player, so their memory id is suffixed with the player
 * UUID. Ambient states come from a village-wide event (a death, a birth) and any onlooker sees them,
 * so they are unscoped.
 *
 * <p>{@code defaultDurationTicks} is a sane fallback/documentation value; callers pass the
 * config-driven window at write time (see {@code McaConversationsConfig}'s {@code states} group and
 * the existing {@code gift.gratitudeWindowTicks} for {@link #GRATEFUL}).
 */
public enum ConversationState {

    /** Player gave the villager a gift it accepted. (Window: {@code gift.gratitudeWindowTicks}.) */
    GRATEFUL(24000, true),
    /** Player gifted the villager while very fond of them — a warmer form of gratitude. */
    SMITTEN(24000, true),
    /** Player completed an MCA: Quests quest for this villager. */
    PROUD(24000, true),
    /** Player struck the villager. */
    ANNOYED(12000, true),
    /** A death happened in the villager's village (ambient — any resident grieves). */
    GRIEVING(48000, false),
    /** A birth or marriage happened in the villager's village (ambient — the village is cheered). */
    ELATED(24000, false);

    private final long defaultDurationTicks;
    private final boolean playerScoped;

    ConversationState(long defaultDurationTicks, boolean playerScoped) {
        this.defaultDurationTicks = defaultDurationTicks;
        this.playerScoped = playerScoped;
    }

    public long defaultDurationTicks() {
        return defaultDurationTicks;
    }

    /** True when this state is felt toward one specific player (memory id is player-scoped). */
    public boolean playerScoped() {
        return playerScoped;
    }

    /** JSON/lang name, e.g. {@code grateful}. */
    public String jsonName() {
        return name().toLowerCase(Locale.ROOT);
    }

    /** The (unscoped) LongTermMemory id for this state, e.g. {@code mcaconversations.state.grateful}. */
    public String memoryId() {
        return MemoryIds.state(jsonName());
    }

    public static Optional<ConversationState> byJsonName(String name) {
        if (name == null) {
            return Optional.empty();
        }
        for (ConversationState s : values()) {
            if (s.jsonName().equals(name)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }
}
