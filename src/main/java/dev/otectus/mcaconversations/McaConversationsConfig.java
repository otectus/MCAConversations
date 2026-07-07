package dev.otectus.mcaconversations;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Locale;

/** Forge common + client configuration. See CONFIG.md for the user-facing documentation. */
public final class McaConversationsConfig {

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Client CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        final Pair<Common, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = common.getLeft();
        COMMON_SPEC = common.getRight();

        final Pair<Client, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT = client.getLeft();
        CLIENT_SPEC = client.getRight();
    }

    private McaConversationsConfig() {
    }

    /**
     * Resolves a feature id used by the {@code conversations_enabled}/{@code conversations_disabled} dialogue
     * conditions. Unknown ids count as enabled so a content typo degrades to "always on" rather
     * than silently killing a dialogue branch.
     */
    public static boolean isFeatureEnabled(String feature) {
        return switch (feature.toLowerCase(Locale.ROOT)) {
            case "topics" -> COMMON.enableTopics.get();
            case "states" -> COMMON.enableStates.get();
            case "templates" -> COMMON.enableTemplates.get();
            case "gossip" -> COMMON.enableGossip.get();
            case "quests" -> COMMON.enableQuests.get();
            case "world" -> COMMON.enableWeatherLines.get();
            default -> true;
        };
    }

    public static final class Common {
        public final ForgeConfigSpec.BooleanValue replaceChatWithConversations;
        public final ForgeConfigSpec.BooleanValue enableTopics;
        public final ForgeConfigSpec.BooleanValue enableStates;
        public final ForgeConfigSpec.BooleanValue enableTemplates;
        public final ForgeConfigSpec.BooleanValue enableGossip;
        public final ForgeConfigSpec.BooleanValue enableQuests;

        public final ForgeConfigSpec.IntValue giftMemoryPerPlayerCap;
        public final ForgeConfigSpec.IntValue gratitudeWindowTicks;

        public final ForgeConfigSpec.IntValue stateGriefWindowTicks;
        public final ForgeConfigSpec.IntValue stateElatedWindowTicks;
        public final ForgeConfigSpec.IntValue stateAnnoyedWindowTicks;
        public final ForgeConfigSpec.IntValue stateSmittenWindowTicks;
        public final ForgeConfigSpec.IntValue stateProudWindowTicks;
        public final ForgeConfigSpec.IntValue stateSmittenMinHearts;

        public final ForgeConfigSpec.BooleanValue enableWeatherLines;
        public final ForgeConfigSpec.BooleanValue enableSeasonLines;
        public final ForgeConfigSpec.BooleanValue enableHolidayLines;
        public final ForgeConfigSpec.IntValue seasonYearLengthDays;

        public final ForgeConfigSpec.IntValue gossipScanIntervalTicks;
        public final ForgeConfigSpec.IntValue gossipRetentionDays;
        public final ForgeConfigSpec.IntValue maxEventsPerVillage;
        public final ForgeConfigSpec.BooleanValue detectMarriage;
        public final ForgeConfigSpec.BooleanValue detectDivorce;
        public final ForgeConfigSpec.BooleanValue detectDeath;
        public final ForgeConfigSpec.BooleanValue detectBirth;
        public final ForgeConfigSpec.BooleanValue detectArrival;
        public final ForgeConfigSpec.BooleanValue detectDeparture;

        public final ForgeConfigSpec.BooleanValue debugLogging;

        Common(ForgeConfigSpec.Builder b) {
            b.push("features");
            replaceChatWithConversations = b.comment(
                    "Route MCA's 'Chat' button to the Conversations conversation hub.",
                    "When false, Chat behaves like vanilla MCA and the Conversations hub is unreachable",
                    "(v0.2.0 removed the separate main-menu button; see DATAPACK.md to restore one).")
                    .define("replaceChatWithConversations", true);
            enableTopics = b.comment("Enable the Conversations conversation topics (heart-gated personal questions).")
                    .define("enableTopics", true);
            enableStates = b.comment("Enable conversation states (e.g. gratitude after a gift influences dialogue).")
                    .define("enableStates", true);
            enableTemplates = b.comment("Enable personalized (templated) lines, e.g. referencing the last gift by name.")
                    .define("enableTemplates", true);
            enableGossip = b.comment("Enable village gossip (villagers mention marriages, births, deaths of others).")
                    .define("enableGossip", true);
            enableQuests = b.comment(
                    "Enable MCA: Quests integration (only active when the 'mcaquests' mod is installed):",
                    "villagers acknowledge available/active/completed quests in conversation, finished quests",
                    "seed gossip + memory, and quest lines can speak in the villager's personality.")
                    .define("enableQuests", true);
            b.pop();

            b.push("gift");
            giftMemoryPerPlayerCap = b.comment("Most recent gifts remembered per player (oldest dropped first).")
                    .defineInRange("giftMemoryPerPlayerCap", 16, 1, 256);
            gratitudeWindowTicks = b.comment("How long (game ticks) a villager stays 'grateful' after an accepted gift (24000 = 1 MC day).")
                    .defineInRange("gratitudeWindowTicks", 24000, 1200, 168000);
            b.pop();

            b.push("states");
            b.comment("Conversation states (moods) are short-lived flags an event leaves on a villager that colour",
                    "its dialogue for a while. All require enableStates; each value is a duration in game ticks",
                    "(24000 = 1 MC day). Set a window to its minimum to make a state effectively momentary.");
            stateGriefWindowTicks = b.comment("How long residents stay 'grieving' after a death in their village.")
                    .defineInRange("stateGriefWindowTicks", 48000, 1200, 168000);
            stateElatedWindowTicks = b.comment("How long residents stay 'elated' after a birth or marriage in their village.")
                    .defineInRange("stateElatedWindowTicks", 24000, 1200, 168000);
            stateAnnoyedWindowTicks = b.comment("How long a villager stays 'annoyed' at a player who struck it.")
                    .defineInRange("stateAnnoyedWindowTicks", 12000, 1200, 168000);
            stateSmittenWindowTicks = b.comment("How long a villager stays 'smitten' with a player after a gift given while very fond.")
                    .defineInRange("stateSmittenWindowTicks", 24000, 1200, 168000);
            stateProudWindowTicks = b.comment("How long a villager stays 'proud' of a player after they complete a quest for it (needs MCA: Quests).")
                    .defineInRange("stateProudWindowTicks", 24000, 1200, 168000);
            stateSmittenMinHearts = b.comment("Minimum hearts at gift time for the gift to also make the villager 'smitten' (as well as grateful).")
                    .defineInRange("stateSmittenMinHearts", 100, 1, 1000);
            b.pop();

            b.push("world");
            enableWeatherLines = b.comment(
                    "Enable weather-aware conversation lines — villagers can remark on rain and storms,",
                    "and the 'weather' template variable resolves to the current sky. Gates the",
                    "conversations_weather dialogue condition and the 'world' feature flag.")
                    .define("enableWeatherLines", true);
            enableSeasonLines = b.comment(
                    "Enable season-aware conversation lines — villagers can remark on the time of year, and",
                    "the 'season' template variable resolves to spring/summer/autumn/winter. When Serene",
                    "Seasons is installed the season is read from it; otherwise it is derived from the world",
                    "day via seasonYearLengthDays. Gates the conversations_season dialogue condition.")
                    .define("enableSeasonLines", true);
            enableHolidayLines = b.comment(
                    "Enable festival-day conversation lines — villagers can remark on calendar holidays",
                    "(spring bloom, midsummer, harvest festival, midwinter), and the 'holiday' template",
                    "variable resolves to the current festival (or 'none'). Holidays are always calendar-based",
                    "(seasonYearLengthDays), independent of Serene Seasons. Gates the conversations_holiday condition.")
                    .define("enableHolidayLines", true);
            seasonYearLengthDays = b.comment(
                    "Length of a full year in MC days, used to derive the calendar season (without Serene",
                    "Seasons) and all holiday dates. Split into four equal quarters starting at spring on day 0.",
                    "The default 96 matches Serene Seasons' default 24-day seasons.")
                    .defineInRange("seasonYearLengthDays", 96, 4, 4096);
            b.pop();

            b.push("gossip");
            gossipScanIntervalTicks = b.comment("Ticks between village relationship scans for gossip events (600 = 30s).")
                    .defineInRange("gossipScanIntervalTicks", 600, 100, 24000);
            gossipRetentionDays = b.comment("How many MC days a gossip event stays tellable before it expires.")
                    .defineInRange("gossipRetentionDays", 7, 1, 64);
            maxEventsPerVillage = b.comment("Maximum retained gossip events per village (oldest dropped first).")
                    .defineInRange("maxEventsPerVillage", 32, 4, 256);
            detectMarriage = b.define("detectMarriage", true);
            detectDivorce = b.define("detectDivorce", true);
            detectDeath = b.define("detectDeath", true);
            detectBirth = b.define("detectBirth", true);
            detectArrival = b.comment("Notice villagers moving INTO a village (residency-set diffing).")
                    .define("detectArrival", true);
            detectDeparture = b.comment("Notice villagers moving AWAY from a village for good (not deaths).")
                    .define("detectDeparture", true);
            b.pop();

            b.push("debug");
            debugLogging = b.comment("Verbose logging for gossip detection and dialogue condition evaluation.")
                    .define("debugLogging", false);
            b.pop();
        }
    }

    public static final class Client {
        Client(ForgeConfigSpec.Builder b) {
            // Reserved for forward-compat (siblings keep an always-registered client spec).
            b.push("display");
            b.pop();
        }
    }
}
