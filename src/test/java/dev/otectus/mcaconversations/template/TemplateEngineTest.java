package dev.otectus.mcaconversations.template;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import dev.otectus.mcaconversations.support.TestPaths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemplateEngineTest {

    @Test
    void resolvedVarsPassThroughInOrder() {
        SayDirective d = SayDirective.of("conversations.test",
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
        SayDirective d = SayDirective.of("conversations.test", List.of(TemplateVariable.LAST_GIFT_ITEM));
        Object[] args = TemplateEngine.buildArgs(d, new TemplateContext());
        assertEquals(1, args.length);
        TranslatableContents contents = (TranslatableContents) ((Component) args[0]).getContents();
        assertEquals(TemplateVariable.LAST_GIFT_ITEM.fallbackKey(), contents.getKey());
    }

    @Test
    void everyCapitalVariableFallsBackToRealTextInEveryLocale() throws IOException {
        // The capital variables resolve to nothing on the majority of installs — no MCA Capitals, no
        // capital, or a vacant office — so their fallbacks are not an edge case, they are the normal
        // rendering. A missing key would put "mcaconversations.fallback.heir_name" in a villager's
        // mouth on every one of those installs.
        for (String locale : List.of("en_us", "pt_br")) {
            JsonObject lang = JsonParser.parseString(Files.readString(
                    TestPaths.of("src/main/resources/assets/mcaconversations/lang/" + locale + ".json"),
                    StandardCharsets.UTF_8)).getAsJsonObject();
            for (TemplateVariable var : List.of(TemplateVariable.CAPITAL_NAME,
                    TemplateVariable.SOVEREIGN_NAME, TemplateVariable.SOVEREIGN_TITLE,
                    TemplateVariable.HEIR_NAME, TemplateVariable.HOUSE_NAME,
                    TemplateVariable.HOUSE_WORDS, TemplateVariable.VILLAGER_TITLE,
                    TemplateVariable.RIVAL_CAPITAL_NAME, TemplateVariable.ALLY_CAPITAL_NAME)) {
                assertTrue(lang.has(var.fallbackKey()),
                        "missing " + locale + " fallback for " + var.jsonName());
                assertFalse(lang.get(var.fallbackKey()).getAsString().isBlank(),
                        "blank " + locale + " fallback for " + var.jsonName());
            }
        }
    }

    @Test
    void unresolvedCapitalVariablesRenderAsTheirFallbackKey() {
        SayDirective d = SayDirective.of("conversations.court.test",
                List.of(TemplateVariable.SOVEREIGN_TITLE, TemplateVariable.HOUSE_WORDS));
        Object[] args = TemplateEngine.buildArgs(d, new TemplateContext());
        assertEquals(2, args.length);
        assertEquals(TemplateVariable.SOVEREIGN_TITLE.fallbackKey(),
                ((TranslatableContents) ((Component) args[0]).getContents()).getKey());
        assertEquals(TemplateVariable.HOUSE_WORDS.fallbackKey(),
                ((TranslatableContents) ((Component) args[1]).getContents()).getKey());
    }

    @Test
    void everyCourtTitleIdIsLocalized() throws IOException {
        // VILLAGER_TITLE builds its key from the resolved title id, so a title Capitals can report
        // and this mod cannot name renders as the raw key in a dialogue line.
        List<String> titles = List.of("none", "commoner", "ambassador", "knight", "lord",
                "royal_guard", "lord_commander", "maester", "dowager_duke", "duke", "grand_maester",
                "hand", "dowager_prince", "prince_consort", "royal_child", "crown_heir",
                "heir_apparent", "sovereign_dowager", "sovereign_consort", "sovereign",
                "high_sovereign", "court_herald", "king", "queen");
        for (String locale : List.of("en_us", "pt_br")) {
            JsonObject lang = JsonParser.parseString(Files.readString(
                    TestPaths.of("src/main/resources/assets/mcaconversations/lang/" + locale + ".json"),
                    StandardCharsets.UTF_8)).getAsJsonObject();
            for (String title : titles) {
                String key = "mcaconversations.capital.title." + title;
                assertTrue(lang.has(key), "missing " + locale + " name for " + key);
                assertFalse(lang.get(key).getAsString().isBlank(), "blank " + locale + " " + key);
            }
        }
    }

    @Test
    void timeOfDayBucketsCoverTheClock() {
        assertEquals("mcaconversations.time_of_day.morning", TemplateContextFactory.timeOfDayKey(0));
        assertEquals("mcaconversations.time_of_day.day", TemplateContextFactory.timeOfDayKey(6000));
        assertEquals("mcaconversations.time_of_day.evening", TemplateContextFactory.timeOfDayKey(12000));
        assertEquals("mcaconversations.time_of_day.night", TemplateContextFactory.timeOfDayKey(18000));
        assertEquals("mcaconversations.time_of_day.morning", TemplateContextFactory.timeOfDayKey(23500));
    }
}
