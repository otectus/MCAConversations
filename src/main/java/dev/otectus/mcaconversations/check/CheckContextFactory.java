package dev.otectus.mcaconversations.check;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.disposition.DispositionAxis;
import dev.otectus.mcaconversations.disposition.Dispositions;
import dev.otectus.mcaconversations.state.ConversationState;
import dev.otectus.mcaconversations.state.MemoryIds;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Optional;

/**
 * Assembles the pure {@link CheckInputs} for one check resolution — the only impure piece of the
 * check system. Inputs come from the disposition store, MCA hearts/mood, this mod's conversation
 * states, and the seeded roll; the resolver itself never touches the world, so identical clicks
 * inside one time bucket resolve identically no matter how many candidate results re-evaluate the
 * condition.
 *
 * <p>Returns empty — meaning <em>no tier matches and the answer's authored fallback result fires</em> —
 * when checks are disabled or when an Attraction check targets a romance-ineligible villager (the
 * structural age/romance gate, layered with the JSON gates and lint).
 */
public final class CheckContextFactory {

    private CheckContextFactory() {
    }

    public static Optional<CheckInputs> assemble(Entity villager, ServerPlayer player, CheckDefinition check) {
        if (villager == null || player == null || check == null
                || !McaConversationsConfig.COMMON.enableChecks.get()) {
            return Optional.empty();
        }
        if (check.axis() == DispositionAxis.ATTRACTION && !McaCompat.isRomanceEligible(villager, player)) {
            return Optional.empty();
        }

        boolean vectorEnabled = Dispositions.enabled();
        int axisValue = vectorEnabled ? Dispositions.axis(villager, player, check.axis()) : 0;
        int hearts = McaCompat.getHearts(player, villager);
        // Interiority check_bias plugs in here in 0.9.0; neutral until then.
        int personalityFit = 0;
        int moodAdjust = MoodModifiers.moodAdjust(McaCompat.getMoodName(villager).orElse(null))
                + MoodModifiers.stateAdjust(
                        hasState(villager, player, ConversationState.GRIEVING),
                        hasState(villager, player, ConversationState.ANNOYED),
                        hasState(villager, player, ConversationState.GRATEFUL),
                        hasState(villager, player, ConversationState.SMITTEN));
        // Arc stages land in 0.9.0; stage 0 until then.
        int arcStage = 0;
        int roll = CheckSeed.roll(villager.getUUID(), player.getUUID(), check.id(), arcStage,
                villager.level().getDayTime());
        return Optional.of(new CheckInputs(axisValue, hearts, personalityFit, moodAdjust, roll,
                check.difficulty(), McaConversationsConfig.COMMON.enableCheckTiers.get(), vectorEnabled));
    }

    private static boolean hasState(Entity villager, ServerPlayer player, ConversationState state) {
        String id = state.playerScoped()
                ? MemoryIds.playerScoped(state.memoryId(), player.getUUID())
                : state.memoryId();
        return McaCompat.hasMemory(villager, id);
    }
}
