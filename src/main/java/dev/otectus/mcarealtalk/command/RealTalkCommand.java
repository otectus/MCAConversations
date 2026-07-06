package dev.otectus.mcarealtalk.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Command;
import dev.otectus.mcarealtalk.gossip.GossipEvent;
import dev.otectus.mcarealtalk.gossip.GossipSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.util.List;

/** Admin debug command: {@code /realtalk gossip list|clear}. Permission level 2. */
public final class RealTalkCommand {

    private RealTalkCommand() {
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("realtalk")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("gossip")
                        .then(Commands.literal("list").executes(ctx -> listGossip(ctx.getSource())))
                        .then(Commands.literal("clear").executes(ctx -> clearGossip(ctx.getSource())))));
    }

    private static int listGossip(CommandSourceStack source) {
        List<GossipEvent> events = GossipSavedData.get(source.getServer()).log().events();
        if (events.isEmpty()) {
            source.sendSuccess(() -> Component.literal("No gossip events recorded."), false);
            return 0;
        }
        long now = source.getServer().overworld().getGameTime();
        for (GossipEvent e : events) {
            String line = String.format("[village %d] %s: %s%s (%d ticks ago)",
                    e.villageId(), e.type().jsonName(), e.aName(),
                    e.bName().isBlank() ? "" : " & " + e.bName(), now - e.created());
            source.sendSuccess(() -> Component.literal(line), false);
        }
        return events.size();
    }

    private static int clearGossip(CommandSourceStack source) {
        GossipSavedData.get(source.getServer()).clearEvents();
        source.sendSuccess(() -> Component.literal("Gossip log cleared."), true);
        return Command.SINGLE_SUCCESS;
    }
}
