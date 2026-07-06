package dev.otectus.mcarealtalk.template;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemplateEngineTest {

    @Test
    void resolvedVarsPassThroughInOrder() {
        SayDirective d = new SayDirective("realtalk.test",
                List.of(TemplateVariable.VILLAGER_NAME, TemplateVariable.VILLAGE_NAME));
        TemplateContext ctx = new TemplateContext()
                .with(TemplateVariable.VILLAGER_NAME, Component.literal("Ann"))
                .with(TemplateVariable.VILLAGE_NAME, Component.literal("Riverbend"));
        Object[] args = TemplateEngine.buildArgs(d, ctx);
        assertEquals(2, args.length);
        assertEquals("Ann", ((Component) args[0]).getString());
        assertEquals("Riverbend", ((Component) args[1]).getString());
    }

    @Test
    void missingVarsFallBackToTheirLangKeys() {
        SayDirective d = new SayDirective("realtalk.test", List.of(TemplateVariable.LAST_GIFT_ITEM));
        Object[] args = TemplateEngine.buildArgs(d, new TemplateContext());
        assertEquals(1, args.length);
        TranslatableContents contents = (TranslatableContents) ((Component) args[0]).getContents();
        assertEquals(TemplateVariable.LAST_GIFT_ITEM.fallbackKey(), contents.getKey());
    }

    @Test
    void timeOfDayBucketsCoverTheClock() {
        assertEquals("mcarealtalk.time_of_day.morning", TemplateContextFactory.timeOfDayKey(0));
        assertEquals("mcarealtalk.time_of_day.day", TemplateContextFactory.timeOfDayKey(6000));
        assertEquals("mcarealtalk.time_of_day.evening", TemplateContextFactory.timeOfDayKey(12000));
        assertEquals("mcarealtalk.time_of_day.night", TemplateContextFactory.timeOfDayKey(18000));
        assertEquals("mcarealtalk.time_of_day.morning", TemplateContextFactory.timeOfDayKey(23500));
    }
}
