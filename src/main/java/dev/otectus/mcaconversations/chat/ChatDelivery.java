package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

/**
 * Renders a villager's dialogue line as a chat message and delivers it — the whisper is the proven
 * chat-safe path already used by {@code McaCompat.sayInDialogue}'s fallback ({@code sendSystemMessage},
 * an unsigned system message, so no 1.19+ chat-signing implications). The MCA {@link Component} is
 * forwarded intact (never flattened to a string) so client-side lang, personality overlay, and template
 * args survive. Delivery is deferred through {@link ChatModeScheduler} for a humanized latency.
 */
public final class ChatDelivery {

    private ChatDelivery() {
    }

    /** Schedules the villager's (already fully-resolved) line for delivery to {@code player}. */
    public static void villagerSays(Entity villager, ServerPlayer player, Component line) {
        McaConversationsConfig.Common cfg = McaConversationsConfig.COMMON;
        String name = McaCompat.getVillagerName(villager).filter(n -> !n.isBlank()).orElse("Villager");
        MutableComponent coloredName = Component.literal(name).withStyle(ChatFormatting.YELLOW);
        MutableComponent rendered = applyFormat(cfg.chatModeMessageFormat.get(), coloredName, line);

        MinecraftServer server = player.getServer();
        long now = server != null ? server.overworld().getGameTime() : 0L;
        int delay = ChatModeScheduler.computeDelayTicks(cfg.chatModeReplyDelayTicks.get(), line.getString().length());
        boolean publicReplies = cfg.chatModePublicReplies.get();
        double radius = cfg.chatModeRadius.get();

        ChatModeScheduler.schedule(now + delay, () -> deliver(villager, player, rendered, publicReplies, radius));
    }

    private static void deliver(Entity villager, ServerPlayer speaker, Component rendered,
                                boolean publicReplies, double radius) {
        if (speaker.hasDisconnected()) {
            return;
        }
        speaker.sendSystemMessage(rendered);
        if (publicReplies && villager.level() instanceof ServerLevel level) {
            double r2 = radius * radius;
            for (ServerPlayer other : level.players()) {
                if (other != speaker && !other.hasDisconnected()
                        && other.distanceToSqr(villager) <= r2) {
                    other.sendSystemMessage(rendered);
                }
            }
        }
    }

    /**
     * Substitutes {@code %1$s} (villager name) and {@code %2$s} (the line Component) into the configured
     * template while keeping both as real Components — {@code String.format} can't be used because the
     * line must not be flattened.
     */
    static MutableComponent applyFormat(String template, Component name, Component line) {
        MutableComponent out = Component.empty();
        int i = 0;
        int len = template.length();
        StringBuilder literal = new StringBuilder();
        while (i < len) {
            if (template.startsWith("%1$s", i)) {
                flush(out, literal);
                out.append(name);
                i += 4;
            } else if (template.startsWith("%2$s", i)) {
                flush(out, literal);
                out.append(line);
                i += 4;
            } else {
                literal.append(template.charAt(i));
                i++;
            }
        }
        flush(out, literal);
        return out;
    }

    private static void flush(MutableComponent out, StringBuilder literal) {
        if (literal.length() > 0) {
            out.append(Component.literal(literal.toString()));
            literal.setLength(0);
        }
    }
}
