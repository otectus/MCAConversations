package dev.otectus.mcaconversations.progress;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.conversation.ConversationCatalogLoader;
import dev.otectus.mcaconversations.conversation.TopicEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Optional;

/**
 * The facade every dialogue adapter goes through to read or move durable narrative state: arc
 * stages, one-shot milestones and exclusive choices (plan §6).
 *
 * <p>Arc bounds come from the conversation catalog, not from the action, so an arc can never be
 * advanced past the stage its topic declares even by a datapack that asks for it. Everything fails
 * safe: an unreachable server, an unknown arc or any throw at all leaves state untouched and reads
 * as "nothing has happened yet".
 */
public final class Progress {

    private Progress() {
    }

    // --- Reads ------------------------------------------------------------------

    public static int arcStage(Entity villager, ServerPlayer player, String arcId) {
        MinecraftServer server = serverOf(player);
        if (server == null || villager == null) {
            return 0;
        }
        try {
            return ProgressSavedData.get(server).arcStage(villager.getUUID(), player.getUUID(), arcId);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("arc stage read failed; defaulting 0", t);
            return 0;
        }
    }

    public static boolean hasMilestone(Entity villager, ServerPlayer player, String milestoneId) {
        MinecraftServer server = serverOf(player);
        if (server == null || villager == null) {
            return false;
        }
        try {
            return ProgressSavedData.get(server).hasMilestone(villager.getUUID(), player.getUUID(), milestoneId);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("milestone read failed; defaulting false", t);
            return false;
        }
    }

    public static Optional<String> exclusiveChoice(Entity villager, ServerPlayer player, String group) {
        MinecraftServer server = serverOf(player);
        if (server == null || villager == null) {
            return Optional.empty();
        }
        try {
            return ProgressSavedData.get(server).exclusiveChoice(villager.getUUID(), player.getUUID(), group);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("exclusive choice read failed; defaulting none", t);
            return Optional.empty();
        }
    }

    /** Evaluates a {@code conversations_progress} condition. Safe default: false (never matches). */
    public static boolean matches(Entity villager, ServerPlayer player, ProgressQuery query) {
        if (query == null || villager == null || player == null) {
            return false;
        }
        try {
            if (query instanceof ProgressQuery.ArcStage arc) {
                return ProgressQuery.matches(query, arcStage(villager, player, arc.arcId()), false, Optional.empty());
            }
            if (query instanceof ProgressQuery.Milestone milestone) {
                return ProgressQuery.matches(query, 0,
                        hasMilestone(villager, player, milestone.milestoneId()), Optional.empty());
            }
            ProgressQuery.Exclusive exclusive = (ProgressQuery.Exclusive) query;
            return ProgressQuery.matches(query, 0, false, exclusiveChoice(villager, player, exclusive.group()));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("conversations_progress evaluation failed; defaulting false", t);
            return false;
        }
    }

    // --- Writes -----------------------------------------------------------------

    /** Applies one progress directive. Logs the transition when {@code debugBranching} is on. */
    public static void apply(Entity villager, ServerPlayer player, ProgressApply directive) {
        MinecraftServer server = serverOf(player);
        if (server == null || villager == null || directive == null) {
            return;
        }
        try {
            long now = villager.level().getGameTime();
            ProgressSavedData data = ProgressSavedData.get(server);
            boolean debug = McaConversationsConfig.COMMON.debugBranching.get();

            if (directive instanceof ProgressApply.Arc arc) {
                int maxStage = ConversationCatalogLoader.active().arcMaxStage(arc.arcId())
                        .orElse(TopicEntry.MAX_ARC_STAGE);
                int current = data.arcStage(villager.getUUID(), player.getUUID(), arc.arcId());
                int target = ProgressApply.resolveStage(arc, current, maxStage);
                if (target != current) {
                    int applied = data.setArcStage(villager.getUUID(), player.getUUID(),
                            arc.arcId(), target, maxStage, now);
                    if (debug) {
                        McaConversations.LOGGER.info("[branch] arc {} {} {} -> {} (max {}) villager={} player={}",
                                arc.arcId(), arc.op(), current, applied, maxStage,
                                villager.getUUID(), player.getName().getString());
                    }
                } else if (debug) {
                    McaConversations.LOGGER.info("[branch] arc {} {} held at {} villager={} player={}",
                            arc.arcId(), arc.op(), current, villager.getUUID(), player.getName().getString());
                }
                return;
            }

            if (directive instanceof ProgressApply.Milestone milestone) {
                boolean fired = data.setMilestone(villager.getUUID(), player.getUUID(),
                        milestone.milestoneId(), now);
                if (debug) {
                    McaConversations.LOGGER.info("[branch] milestone {} {} villager={} player={}",
                            milestone.milestoneId(), fired ? "SET" : "already set",
                            villager.getUUID(), player.getName().getString());
                }
                return;
            }

            ProgressApply.Exclusive exclusive = (ProgressApply.Exclusive) directive;
            boolean decided = data.setExclusiveChoice(villager.getUUID(), player.getUUID(),
                    exclusive.group(), exclusive.member(), now);
            if (debug) {
                McaConversations.LOGGER.info("[branch] exclusive {} -> {} ({}) villager={} player={}",
                        exclusive.group(), exclusive.member(),
                        decided ? "decided" : "already decided elsewhere",
                        villager.getUUID(), player.getName().getString());
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("conversations_progress_apply failed; ignoring", t);
        }
    }

    private static MinecraftServer serverOf(ServerPlayer player) {
        return player == null ? null : player.getServer();
    }
}
