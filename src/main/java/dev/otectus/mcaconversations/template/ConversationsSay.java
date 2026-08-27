package dev.otectus.mcaconversations.template;

import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

/** Executes the {@code conversations_say} action: resolve vars, build args, deliver in-dialogue. */
public final class ConversationsSay {

    private ConversationsSay() {
    }

    public static void trigger(SayDirective directive, Entity villager, ServerPlayer player) {
        TemplateContext context = TemplateContextFactory.build(directive.vars(), villager, player);
        Object[] args = TemplateEngine.buildArgs(directive, context, resolveSlots(directive, villager, player));
        McaCompat.sayInDialogue(villager, player, directive.phrase(), args);
    }

    /**
     * Renders the line's declared slots from the frozen plan.
     *
     * <p>Reads the plan rather than the world: the values were pinned when the scene was chosen, and
     * re-deriving them now is how a sentence ends up naming a different field than the one the player
     * was told about two lines ago (spec §7.4).
     */
    private static java.util.List<net.minecraft.network.chat.Component> resolveSlots(
            SayDirective directive, Entity villager, ServerPlayer player) {
        if (directive.slots().isEmpty()) {
            return java.util.List.of();
        }
        java.util.List<net.minecraft.network.chat.Component> out = new java.util.ArrayList<>();
        var plan = dev.otectus.mcaconversations.conversation.ConversationSessions.raw(player.getUUID())
                .flatMap(dev.otectus.mcaconversations.conversation.ConversationSession::plan);
        net.minecraft.server.level.ServerLevel level =
                villager != null && villager.level() instanceof net.minecraft.server.level.ServerLevel sl
                        ? sl : null;
        for (String slot : directive.slots()) {
            out.add(plan.flatMap(p -> p.slot(slot))
                    .map(value -> SlotRenderer.render(value, level))
                    .orElseGet(() -> net.minecraft.network.chat.Component.translatable(
                            SlotRenderer.FALLBACK_KEY)));
        }
        return out;
    }
}
