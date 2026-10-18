package dev.otectus.mcaconversations.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.otectus.mcaconversations.chat.ChatModeScheduler;
import dev.otectus.mcaconversations.chat.VillagerAttention;
import dev.otectus.mcaconversations.conversation.ConversationPresence;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import dev.otectus.mcaconversations.debug.PerfCounters;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

/**
 * {@code /conversations perf status|reset} (operator): what this mod's server tick work cost over the
 * last minute, the live counts behind it, and the overworld's chunk tickets — so "Conversations holds
 * no chunk tickets" is something an operator can read rather than take on trust (feedback remediation
 * WP0, WP-D1). Operator diagnostics, literal English like the other admin subtrees.
 */
final class PerfCommand {

    private PerfCommand() {
    }

    static LiteralArgumentBuilder<CommandSourceStack> subtree() {
        return Commands.literal("perf")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("status").executes(ctx -> status(ctx.getSource())))
                .then(Commands.literal("reset").executes(ctx -> {
                    PerfCounters.SERVER.reset();
                    ctx.getSource().sendSuccess(() -> Component.literal("Conversations perf counters reset."), true);
                    return Command.SINGLE_SUCCESS;
                }));
    }

    private static int status(CommandSourceStack source) {
        PerfCounters perf = PerfCounters.SERVER;
        send(source, "Conversations tick sections over the last " + Math.min(perf.ticks(), PerfCounters.WINDOW_TICKS - 1L)
                + " ticks (microseconds: p50 / p95 / max / total, ticks run):");
        for (Map.Entry<PerfCounters.Section, PerfCounters.Stats> entry : perf.all().entrySet()) {
            PerfCounters.Stats stats = entry.getValue();
            send(source, String.format("  %-18s %8.1f %8.1f %8.1f %10.1f  %d", entry.getKey(),
                    micros(stats.p50()), micros(stats.p95()), micros(stats.max()), micros(stats.total()),
                    stats.ticksRun()));
        }
        send(source, "Live: sessions=" + ConversationSessions.size()
                + " discussions=" + ConversationPresence.handles().size()
                + " holds=" + VillagerAttention.activeHolds().size()
                + " queuedReplies=" + ChatModeScheduler.size());
        StringBuilder counts = new StringBuilder("Examined since reset:");
        for (PerfCounters.Count count : PerfCounters.Count.values()) {
            counts.append(' ').append(count.name().toLowerCase(java.util.Locale.ROOT)).append('=')
                    .append(perf.count(count));
        }
        send(source, counts.toString());
        send(source, tickets(source.getServer().overworld()));
        return Command.SINGLE_SUCCESS;
    }

    /**
     * Overworld chunk counts and tickets by type. Read-only. The per-type histogram reads the
     * distance manager's ticket map reflectively, by field type rather than by name (names differ
     * between development and production), and reports itself unavailable when that read fails.
     */
    static String tickets(ServerLevel level) {
        StringBuilder out = new StringBuilder("Overworld chunks: loaded=")
                .append(level.getChunkSource().getLoadedChunksCount())
                .append(" forced=").append(level.getForcedChunks().size());
        Map<String, Integer> byType = ticketTypes(level);
        out.append(" tickets=").append(byType == null ? "unavailable" : byType.toString());
        return out.toString();
    }

    private static Map<String, Integer> ticketTypes(ServerLevel level) {
        try {
            Object manager = level.getChunkSource().chunkMap.getDistanceManager();
            Map<String, Integer> histogram = new TreeMap<>();
            boolean readable = false;
            for (Class<?> type = manager.getClass(); type != null && type != Object.class; type = type.getSuperclass()) {
                for (Field field : type.getDeclaredFields()) {
                    if (!it.unimi.dsi.fastutil.longs.Long2ObjectMap.class.isAssignableFrom(field.getType())) {
                        continue;
                    }
                    field.setAccessible(true);
                    Object map = field.get(manager);
                    if (!(map instanceof it.unimi.dsi.fastutil.longs.Long2ObjectMap<?> tickets)) {
                        continue;
                    }
                    readable = true;
                    for (Object set : tickets.values()) {
                        if (!(set instanceof Iterable<?> iterable)) {
                            continue;
                        }
                        for (Object ticket : iterable) {
                            if (ticket instanceof net.minecraft.server.level.Ticket<?> t) {
                                histogram.merge(String.valueOf(t.getType()), 1, Integer::sum);
                            }
                        }
                    }
                }
            }
            return readable ? histogram : null;
        } catch (Throwable t) {
            return null;
        }
    }

    private static double micros(long nanos) {
        return nanos / 1000.0;
    }

    private static void send(CommandSourceStack source, String line) {
        source.sendSuccess(() -> Component.literal(line), false);
    }
}
