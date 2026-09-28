package dev.otectus.mcaconversations.compat.crime;

import dev.otectus.mcacrime.api.CrimeDialogueResolver;
import dev.otectus.mcacrime.dialogue.DialogueContext;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

/**
 * Speaks MCA: Crime's guard lines in the guard's own personality (1.8.0), through this mod's say
 * pools ({@code dialogue.conversations.crime.<event>} plus the personality overlays) via MCA's own
 * {@code getTranslatable}. Registered with Crime through {@code CrimeDialogueHooks.addResolver}; the
 * exact sibling of {@code compat.quests.QuestVoiceResolver}.
 *
 * <p>Best-effort: returns {@code null} (→ Crime's own datapack line) when the integration is disabled,
 * the speaker is not a loaded MCA villager, the event has no pool here, or anything fails.
 */
public final class CrimeVoiceResolver implements CrimeDialogueResolver {

    @Override
    @Nullable
    public Component resolve(@Nullable LivingEntity speaker, ServerPlayer listener, ResourceLocation event,
                             DialogueContext context, Component fallback) {
        if (!McaConversationsConfig.COMMON.enableCrime.get() || speaker == null || listener == null
                || !McaCompat.isMcaVillager(speaker)) {
            return null;
        }
        String phrase = CrimeVoiceKeys.phraseFor(event);
        if (phrase == null) {
            return null;
        }
        return McaCompat.getDialogueLine(speaker, listener, phrase).orElse(null);
    }
}
