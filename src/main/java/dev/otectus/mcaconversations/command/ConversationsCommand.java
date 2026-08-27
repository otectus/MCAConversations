package dev.otectus.mcaconversations.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.ChatModeDispatcher;
import dev.otectus.mcaconversations.chat.ChatModePlayerState;
import dev.otectus.mcaconversations.gift.ConversationsCapabilities;
import dev.otectus.mcaconversations.gossip.GossipEvent;
import dev.otectus.mcaconversations.gossip.GossipSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

/**
 * {@code /conversations} command tree.
 *
 * <p>Permissioning is per-subcommand rather than on the root: {@code gossip} (admin inspection) and
 * {@code chat debug-ask} (op test driver) require level 2, while {@code chat on|off|status} is a
 * per-player opt-in usable by everyone. (The root previously gated the whole tree at level 2, which
 * would have blocked non-ops from their own opt-in.)
 */
public final class ConversationsCommand {

    private ConversationsCommand() {
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("conversations")
                .then(Commands.literal("gossip")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("list").executes(ctx -> listGossip(ctx.getSource())))
                        .then(Commands.literal("clear").executes(ctx -> clearGossip(ctx.getSource()))))
                .then(Commands.literal("chat")
                        .then(Commands.literal("on").executes(ctx -> setChat(ctx.getSource(), true)))
                        .then(Commands.literal("off").executes(ctx -> setChat(ctx.getSource(), false)))
                        .then(Commands.literal("status").executes(ctx -> chatStatus(ctx.getSource())))
                        .then(Commands.literal("debug-ask")
                                .requires(source -> source.hasPermission(2))
                                .then(Commands.argument("question", StringArgumentType.string())
                                        .then(Commands.argument("answer", StringArgumentType.string())
                                                .executes(ctx -> debugAsk(ctx.getSource(),
                                                        StringArgumentType.getString(ctx, "question"),
                                                        StringArgumentType.getString(ctx, "answer"))))))
                        .then(Commands.literal("debug")
                                .requires(source -> source.hasPermission(2))
                                .then(Commands.argument("message", StringArgumentType.greedyString())
                                        .executes(ctx -> debugScore(ctx.getSource(),
                                                StringArgumentType.getString(ctx, "message"))))))
                );

        // The living-histories operator surface is a separate tree because it is a different kind of
        // command: everything above is a feature switch or a chat test driver, and everything below
        // inspects generated narrative state that ordinary play deliberately never shows.
        for (var subtree : LivingHistoriesCommand.subtrees()) {
            dispatcher.register(Commands.literal("conversations").then(subtree));
        }
    }

    // --- gossip (op) ----------------------------------------------------------

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

    // --- chat mode ------------------------------------------------------------

    private static int setChat(CommandSourceStack source, boolean enabled) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        ConversationsCapabilities.getChatMode(player).ifPresent(state -> state.setEnabled(enabled));
        String note = McaConversationsConfig.COMMON.enableChatMode.get()
                ? ""
                : " (note: chat mode is disabled server-side, so this has no effect yet)";
        source.sendSuccess(() -> Component.literal("Chat mode " + (enabled ? "enabled" : "disabled")
                + " for you." + note), false);
        return Command.SINGLE_SUCCESS;
    }

    private static int chatStatus(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        boolean serverOn = McaConversationsConfig.COMMON.enableChatMode.get();
        boolean playerOn = ConversationsCapabilities.getChatMode(player)
                .map(ChatModePlayerState::isEnabled)
                .orElse(McaConversationsConfig.COMMON.chatModeDefaultOn.get());
        source.sendSuccess(() -> Component.literal("Chat mode: server " + (serverOn ? "on" : "off")
                + ", you " + (playerOn ? "on" : "off")
                + (serverOn && playerOn ? " — talk to villagers by typing." : ".")), false);
        return Command.SINGLE_SUCCESS;
    }

    private static int debugAsk(CommandSourceStack source, String question, String answer)
            throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        String result = ChatModeDispatcher.debugAsk(player, question, answer);
        source.sendSuccess(() -> Component.literal(result), false);
        return Command.SINGLE_SUCCESS;
    }

    private static int debugScore(CommandSourceStack source, String message) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        for (String line : ChatModeDispatcher.debugScore(player, message)) {
            source.sendSuccess(() -> Component.literal(line), false);
        }
        return Command.SINGLE_SUCCESS;
    }
}
