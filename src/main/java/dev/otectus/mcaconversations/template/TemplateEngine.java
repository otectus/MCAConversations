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
        return buildArgs(directive, context, java.util.List.of());
    }

    /**
     * As above, with the frozen scene's bound slots appended after the vars.
     *
     * <p>The order is the contract every locale file depends on: vars first in declaration order, then
     * slots in declaration order. A slot that did not bind takes the neutral fallback rather than
     * shifting the positions of the ones after it — a missing word is recoverable, a shifted sentence
     * is not.
     */
    public static Object[] buildArgs(SayDirective directive, TemplateContext context,
                                     java.util.List<Component> slotValues) {
        Object[] args = new Object[directive.vars().size() + directive.slots().size()];
        for (int i = 0; i < directive.vars().size(); i++) {
            TemplateVariable var = directive.vars().get(i);
            args[i] = context.get(var).orElseGet(() -> Component.translatable(var.fallbackKey()));
        }
        for (int i = 0; i < directive.slots().size(); i++) {
            Component value = i < slotValues.size() ? slotValues.get(i) : null;
            args[directive.vars().size() + i] = value == null
                    ? Component.translatable(dev.otectus.mcaconversations.template.SlotRenderer.FALLBACK_KEY)
                    : value;
        }
        return args;
    }
}
