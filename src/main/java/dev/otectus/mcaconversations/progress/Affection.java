package dev.otectus.mcaconversations.progress;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.ConversationCatalogLoader;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import dev.otectus.mcaconversations.conversation.DepthClass;
import dev.otectus.mcaconversations.conversation.TopicEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

/**
 * The one guarded path from authored content to a visible heart change (plan §5.3). Everything else
 * in this mod's branching content is forbidden from touching hearts, and lint enforces that.
 *
 * <p>What this facade adds on top of {@link ProgressStore#applyAffection}'s arithmetic is the world:
 * it resolves the live session (creating one if a stray action arrives outside a tracked
 * conversation), reads the config, calls MCA's own heart mutation, books the applied amount against
 * the conversation budget, and logs the whole chain when {@code debugBranching} is on.
 *
 * <p>Fails safe at every step: no server, no session, no MCA villager, or any throw at all means no
 * hearts move and nothing is booked.
 */
public final class Affection {

    private Affection() {
    }

    /**
     * Applies one authored heart change.
     *
     * @return what actually happened, for debug output and tests; never null
     */
    public static AffectionOutcome apply(Entity villager, ServerPlayer player, AffectionApply directive) {
        if (villager == null || player == null || directive == null) {
            return AffectionOutcome.none(0, AffectionOutcome.Reason.ZERO);
        }
        try {
            MinecraftServer server = player.getServer();
            if (server == null) {
                return AffectionOutcome.none(directive.delta(), AffectionOutcome.Reason.ZERO);
            }
            long now = villager.level().getGameTime();
            ConversationSession session = ConversationSessions.get(player.getUUID(), now);
            session.setVillagerId(villager.getUUID());

            DepthClass budget = resolveBudget(directive, session);
            McaConversationsConfig.Common config = McaConversationsConfig.COMMON;
            AffectionContext context = new AffectionContext(budget,
                    session.positiveApplied(), session.negativeApplied(),
                    config.conversationDailyPositiveCap.get(),
                    config.conversationDailyNegativeCap.get(),
                    config.strongerNegativeOutcomes.get(),
                    config.conversationHeartMultiplier.get(),
                    directive.decision() + "@" + now,
                    now);

            AffectionOutcome outcome = ProgressSavedData.get(server)
                    .applyAffection(villager.getUUID(), player.getUUID(), directive, context);

            int measured = 0;
            if (outcome.granted() != 0) {
                measured = McaCompat.rewardHearts(villager, player, outcome.granted());
                session.recordApplied(outcome.granted());
            }
            session.touch(now);

            if (config.debugBranching.get()) {
                McaConversations.LOGGER.info(
                        "[branch] affection decision={} authored={} scaled={} granted={} measured={} reason={} "
                                + "budget={} conv(+{}/-{}) topic={} villager={} player={}",
                        directive.decision(), outcome.authored(), outcome.scaled(), outcome.granted(),
                        measured, outcome.reason(), budget.key(),
                        session.positiveApplied(), session.negativeApplied(),
                        session.topicId().orElse("-"), villager.getUUID(), player.getName().getString());
            }
            return outcome;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("conversations_affection_apply failed; no hearts moved", t);
            return AffectionOutcome.none(directive.delta(), AffectionOutcome.Reason.ZERO);
        }
    }

    /**
     * The depth class whose per-conversation caps apply: the one the result declares, else the live
     * session's, else the catalog's for the session topic, else {@link DepthClass#QUICK} — the
     * tightest budget, so an unrecognised context can never pay out more than the smallest topic.
     */
    private static DepthClass resolveBudget(AffectionApply directive, ConversationSession session) {
        if (directive.budget().isPresent()) {
            return directive.budget().get();
        }
        if (session.topicId().isPresent()) {
            return ConversationCatalogLoader.topic(session.topicId().get())
                    .map(TopicEntry::depth)
                    .orElse(session.budget());
        }
        return session.budget();
    }
}
