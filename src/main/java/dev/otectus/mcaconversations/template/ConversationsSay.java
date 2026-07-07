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
        Object[] args = TemplateEngine.buildArgs(directive, context);
        McaCompat.sayInDialogue(villager, player, directive.phrase(), args);
    }
}
