package dev.otectus.mcaconversations.template;

import net.minecraft.network.chat.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

/**
 * Resolved variable values for one templated line. Built server-side by
 * {@link TemplateContextFactory}; consumed by {@link TemplateEngine}. Missing values fall back to
 * the variable's fallback lang key.
 */
public final class TemplateContext {

    private final Map<TemplateVariable, Component> values = new EnumMap<>(TemplateVariable.class);

    public TemplateContext with(TemplateVariable var, Component value) {
        if (value != null) {
            values.put(var, value);
        }
        return this;
    }

    public Optional<Component> get(TemplateVariable var) {
        return Optional.ofNullable(values.get(var));
    }
}
