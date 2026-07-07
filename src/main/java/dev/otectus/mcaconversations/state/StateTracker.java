package dev.otectus.mcaconversations.state;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

/**
 * Applies {@link ConversationState}s onto MCA villagers as expiring LongTermMemory entries. The single
 * write path for every conversation state (gratitude, grief, pride, …), guarded by the {@code enableStates}
 * config toggle and MCA's presence (via {@link McaCompat}, which no-ops when the entity isn't an MCA
 * villager). Everything degrades to a silent no-op — a missing state memory simply means the gated
 * dialogue line never fires, and the generic line shows instead.
 */
public final class StateTracker {

    private StateTracker() {
    }

    /** The configured duration (game ticks) a given state lasts. */
    public static long configuredWindow(ConversationState state) {
        return switch (state) {
            case GRATEFUL -> McaConversationsConfig.COMMON.gratitudeWindowTicks.get();
            case GRIEVING -> McaConversationsConfig.COMMON.stateGriefWindowTicks.get();
            case ELATED -> McaConversationsConfig.COMMON.stateElatedWindowTicks.get();
            case ANNOYED -> McaConversationsConfig.COMMON.stateAnnoyedWindowTicks.get();
            case SMITTEN -> McaConversationsConfig.COMMON.stateSmittenWindowTicks.get();
            case PROUD -> McaConversationsConfig.COMMON.stateProudWindowTicks.get();
        };
    }

    /** {@link #apply(Entity, ServerPlayer, ConversationState, long)} using the state's configured window. */
    public static void apply(Entity villager, @Nullable ServerPlayer player, ConversationState state) {
        apply(villager, player, state, state == null ? 0 : configuredWindow(state));
    }

    /** {@link #applyAmbient(ServerLevel, int, ConversationState, long)} using the state's configured window. */
    public static void applyAmbient(ServerLevel level, int villageId, ConversationState state) {
        applyAmbient(level, villageId, state, state == null ? 0 : configuredWindow(state));
    }

    /**
     * Writes {@code state} onto {@code villager} for {@code ticks}. Player-scoped states require a
     * non-null {@code player} (they are keyed to that player); ambient states ignore {@code player}.
     */
    public static void apply(Entity villager, @Nullable ServerPlayer player, ConversationState state, long ticks) {
        if (!McaConversationsConfig.COMMON.enableStates.get() || villager == null || state == null || ticks <= 0) {
            return;
        }
        String id = state.memoryId();
        if (state.playerScoped()) {
            if (player == null) {
                return;
            }
            id = MemoryIds.playerScoped(id, player.getUUID());
        }
        McaCompat.remember(villager, id, ticks);
    }

    /**
     * Applies an ambient (unscoped) {@code state} to every currently-loaded MCA resident of a village —
     * used when a village-wide event (a death, a birth) should colour the whole town's mood.
     */
    public static void applyAmbient(ServerLevel level, int villageId, ConversationState state, long ticks) {
        if (!McaConversationsConfig.COMMON.enableStates.get() || level == null || state == null || ticks <= 0) {
            return;
        }
        for (Entity resident : McaCompat.loadedVillageResidents(level, villageId)) {
            if (McaCompat.isMcaVillager(resident)) {
                McaCompat.remember(resident, state.memoryId(), ticks);
            }
        }
    }
}
