package dev.otectus.mcaconversations.template;

import net.minecraft.network.chat.Component;

/**
 * Turns a {@link SayDirective} plus a resolved {@link TemplateContext} into the ordered positional
 * arg array for the translatable line. Pure logic: no MCA types, unit-testable.
 */
public final class TemplateEngine {

    private TemplateEngine() {
    }

    /**
     * One arg per directive var, in order. A var missing from the context resolves to its fallback
     * lang key so the line always renders. (MCA prepends the player name as an additional first
     * arg; these args land at {@code %2$s}+.)
     */
    public static Object[] buildArgs(SayDirective directive, TemplateContext context) {
        Object[] args = new Object[directive.vars().size()];
        for (int i = 0; i < args.length; i++) {
            TemplateVariable var = directive.vars().get(i);
            args[i] = context.get(var).orElseGet(() -> Component.translatable(var.fallbackKey()));
        }
        return args;
    }
}
