package dev.otectus.mcaconversations.compat.quests;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.otectus.mcaquests.quest.reward.QuestReward;
import dev.otectus.mcaquests.quest.reward.QuestRewardType;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.state.MemoryIds;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

/**
 * MCA: Quests reward {@code mcaconversations:unlock_topic} — completing the quest writes a permanent Conversations
 * unlock flag on the giver villager (player-scoped), which a deeper Conversations conversation branch can gate
 * on via the native {@code memory} condition. No-op when the giver is gone (documented safe default).
 */
public record UnlockTopicReward(String topic) implements QuestReward {

    public static final Codec<UnlockTopicReward> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("topic").forGetter(UnlockTopicReward::topic)
    ).apply(instance, UnlockTopicReward::new));

    /** Set once by {@code ConversationsQuestsCompat.register()} so {@link #type()} returns the registered type. */
    static QuestRewardType<UnlockTopicReward> TYPE;

    @Override
    public QuestRewardType<?> type() {
        return TYPE;
    }

    @Override
    public Component describe() {
        return Component.translatable("mcaquests.reward.mcaconversations.unlock_topic",
                Component.translatableWithFallback("mcaconversations.topic." + topic, topic));
    }

    @Override
    public void grant(ServerPlayer player, @Nullable Entity villager) {
        if (villager == null || !McaCompat.isMcaVillager(villager)) {
            return;
        }
        McaCompat.rememberForever(villager, MemoryIds.playerScoped(MemoryIds.unlock(topic), player.getUUID()));
    }
}
