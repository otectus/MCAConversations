package dev.otectus.mcaconversations.compat.quests;

import dev.otectus.mcaquests.api.QuestDialogueResolver;
import dev.otectus.mcaquests.quest.QuestDefinition;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

/**
 * Renders a quest's lifecycle line in the villager's personality voice, using Conversations's Conversations lang
 * pools (base {@code dialogue.conversations.quest.<state>} + the 13 personality overlays) via MCA's own
 * {@code getTranslatable}. Registered with MCA: Quests through {@code QuestDialogueHooks.setResolver}.
 *
 * <p>Best-effort: returns {@code null} (→ Quests' own static text) when the integration is disabled, the
 * giver isn't a loaded MCA villager, the state isn't one we voice, or anything fails. The quest's display
 * title is passed as {@code %2$s} for optional interpolation.
 */
public final class QuestVoiceResolver implements QuestDialogueResolver {

    @Override
    @Nullable
    public Component resolve(ServerPlayer player, @Nullable Entity villager, QuestDefinition def,
                             String lifecycleState, Component fallback) {
        if (!McaConversationsConfig.COMMON.enableQuests.get() || villager == null
                || !McaCompat.isMcaVillager(villager)) {
            return null;
        }
        String phrase = phraseFor(lifecycleState);
        if (phrase == null) {
            return null;
        }
        return McaCompat.getDialogueLine(villager, player, phrase, def.title()).orElse(null);
    }

    /** Maps a Quests lifecycle state to a Conversations say-key, or {@code null} for states we leave untouched. */
    @Nullable
    private static String phraseFor(String state) {
        return switch (state) {
            case QuestDefinition.OFFER -> "conversations.quest.offer";
            case QuestDefinition.ACCEPT -> "conversations.quest.accepted";
            case QuestDefinition.IN_PROGRESS -> "conversations.quest.in_progress";
            case QuestDefinition.READY -> "conversations.quest.ready";
            case QuestDefinition.COMPLETE -> "conversations.quest.completed";
            case QuestDefinition.FAILED -> "conversations.quest.failed";
            default -> null;
        };
    }
}
