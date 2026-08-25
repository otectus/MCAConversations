package dev.otectus.mcaconversations.compat.quests;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.otectus.mcaquests.api.ExternalSignalObjective;
import dev.otectus.mcaquests.quest.objective.ObjectiveProgress;
import dev.otectus.mcaquests.quest.objective.QuestObjective;
import dev.otectus.mcaquests.quest.objective.QuestObjectiveType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * MCA: Quests objective {@code mcaconversations:talk_about} — completes when the player has a Conversations
 * conversation about {@code topic} with a villager {@code count} times. Progress is pushed in by Real
 * Talk (not one of Quests' built-in detectors): when a topic's cooldown is recorded the registrar calls
 * {@code QuestManager.notifyExternalObjective(player, "mcaconversations:<topic>", villagerUuid)}, which advances
 * every matching objective — hence {@link ExternalSignalObjective} + {@link #isEventDriven()}.
 *
 * <p>Matches on topic only (talk to <em>any</em> villager about it); the {@code villagerUuid} arg is
 * available for future villager-specific variants.
 */
public record TalkAboutObjective(String topic, int count) implements QuestObjective, ExternalSignalObjective {

    public static final Codec<TalkAboutObjective> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("topic").forGetter(TalkAboutObjective::topic),
            ExtraCodecs.POSITIVE_INT.optionalFieldOf("count", 1).forGetter(TalkAboutObjective::count)
    ).apply(instance, TalkAboutObjective::new));

    /** Set once by {@code ConversationsQuestsCompat.register()} so {@link #type()} returns the registered type. */
    static QuestObjectiveType<TalkAboutObjective> TYPE;

    @Override
    public QuestObjectiveType<?> type() {
        return TYPE;
    }

    @Override
    public Component describe() {
        return Component.translatable("mcaquests.objective.mcaconversations.talk_about", count,
                Component.translatableWithFallback("mcaconversations.topic." + topic, topic));
    }

    @Override
    public int required() {
        return count;
    }

    @Override
    public int current(ServerPlayer player, ObjectiveProgress progress) {
        return Math.min(progress.count(), count);
    }

    @Override
    public boolean isSatisfied(ServerPlayer player, ObjectiveProgress progress) {
        return progress.count() >= count;
    }

    @Override
    public boolean isEventDriven() {
        return true;
    }

    @Override
    public boolean matchesSignal(ResourceLocation signalId, @Nullable UUID villagerUuid) {
        return signalId != null
                && signalId.getNamespace().equals("mcaconversations")
                && signalId.getPath().equals(topic);
    }
}
