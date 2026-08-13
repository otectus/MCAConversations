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
            case "dispositions" -> COMMON.enableDispositions.get();
            case "checks" -> COMMON.enableChecks.get();
            case "chat" -> COMMON.enableChatMode.get();
            default -> true;
        };
    }

    /**
     * How the hub is reached from MCA's interaction screen. Read through this accessor so callers
     * agree across a config reload and a value the spec cannot parse degrades to the default
     * instead of throwing inside a mixin.
     */
    public static HubEntryMode hubEntryMode() {
        try {
            HubEntryMode mode = COMMON.hubEntryMode.get();
            return mode == null ? HubEntryMode.ADDITIVE : mode;
        } catch (Throwable t) {
            return HubEntryMode.ADDITIVE;
        }
    }

    public static final class Common {
        public final ForgeConfigSpec.EnumValue<HubEntryMode> hubEntryMode;
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

        public final ForgeConfigSpec.BooleanValue enableDispositions;
        public final ForgeConfigSpec.BooleanValue enableChecks;
        public final ForgeConfigSpec.BooleanValue enableCheckTiers;
        public final ForgeConfigSpec.DoubleValue dispositionGainMultiplier;
        public final ForgeConfigSpec.DoubleValue dispositionDecayMultiplier;
        public final ForgeConfigSpec.IntValue dispositionDailyAxisCap;
        public final ForgeConfigSpec.IntValue dispositionStaleDays;
        public final ForgeConfigSpec.BooleanValue debugRpg;

        public final ForgeConfigSpec.BooleanValue enableChatMode;
        public final ForgeConfigSpec.BooleanValue chatModeDefaultOn;
        public final ForgeConfigSpec.DoubleValue chatModeRadius;
        public final ForgeConfigSpec.DoubleValue chatModeAddressedRadius;
        public final ForgeConfigSpec.IntValue chatModeStickinessTicks;
        public final ForgeConfigSpec.DoubleValue chatModeLookConeDegrees;
        public final ForgeConfigSpec.IntValue chatModeMaxResponders;
        public final ForgeConfigSpec.DoubleValue chatModeMinScore;
        public final ForgeConfigSpec.DoubleValue chatModeAmbientMinScore;
        public final ForgeConfigSpec.IntValue chatModeReplyDelayTicks;
        public final ForgeConfigSpec.IntValue chatModeCooldownTicks;
        public final ForgeConfigSpec.BooleanValue chatModePublicReplies;
        public final ForgeConfigSpec.BooleanValue chatModeShowHeartChanges;
        public final ForgeConfigSpec.ConfigValue<String> chatModeMessageFormat;
        public final ForgeConfigSpec.IntValue chatModeMuteTicks;
        public final ForgeConfigSpec.BooleanValue chatModeInsultDetection;
        public final ForgeConfigSpec.BooleanValue chatModeLocalChat;
        public final ForgeConfigSpec.BooleanValue chatModeGreetOnApproach;
        public final ForgeConfigSpec.DoubleValue chatModeGreetChance;
        public final ForgeConfigSpec.BooleanValue chatModeTypingAttention;
        public final ForgeConfigSpec.IntValue chatModeAttentionTicks;

        public final ForgeConfigSpec.BooleanValue debugLogging;

        Common(ForgeConfigSpec.Builder b) {
            b.push("features");
            hubEntryMode = b.comment(
                    "How the Conversations hub is reached from MCA's villager interaction screen.",
                    "  ADDITIVE (default) - MCA's 'Chat' button keeps its own behaviour and Conversations",
                    "                       appears as a SEPARATE button. Both are available.",
                    "  REPLACE            - MCA's 'Chat' button opens the Conversations hub instead, and the",
                    "                       separate button is hidden (this was the 0.2.0-0.9.x behaviour).",
                    "  HIDDEN             - No Conversations button; MCA's Chat is untouched. Gossip, memory,",
                    "                       chat mode and every other feature still run.",
                    "Replaces the old boolean 'replaceChatWithConversations'. This is about the interaction",
                    "SCREEN only - it is unrelated to enableChatMode (talking to villagers in normal chat),",
                    "and no mode affects MCA's own AI chat, which never routes through the dialogue system.")
                    .defineEnum("hubEntryMode", HubEntryMode.ADDITIVE);
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

            b.push("rpg");
            b.comment("The 1.0.0 RPG layer: an internal per-(villager, player) disposition vector (Trust, Respect,",
                    "Warmth, Attraction, Tension, Familiarity) that gates and voices dialogue, plus dialogue checks",
                    "with success tiers. Hearts remain MCA's sole visible relationship economy — the vector never",
                    "shows as a number and never grants hearts. Each toggle degrades to a documented simpler",
                    "behavior; everything off is exactly the 0.6.0 experience.");
            enableDispositions = b.comment(
                    "Master toggle for the disposition vector. When false, no vector state is read or written:",
                    "disposition-gated results never match (their authored fallbacks fire) and checks run on a",
                    "hearts-only formula.")
                    .define("enableDispositions", true);
            enableChecks = b.comment(
                    "Master toggle for dialogue checks. When false, checked stances resolve through their",
                    "authored plain fallback result (the 0.6.0-style single outcome).")
                    .define("enableChecks", true);
            enableCheckTiers = b.comment(
                    "Four-tier check outcomes (crit/success/partial/rebuff). When false, checks collapse to",
                    "binary success/rebuff at the same difficulty.")
                    .define("enableCheckTiers", true);
            dispositionGainMultiplier = b.comment("Scale on all disposition gains and losses (0 freezes the vector).")
                    .defineInRange("dispositionGainMultiplier", 1.0, 0.0, 4.0);
            dispositionDecayMultiplier = b.comment(
                    "Scale on disposition decay toward the personality baseline (0 = values never drift back).")
                    .defineInRange("dispositionDecayMultiplier", 1.0, 0.0, 4.0);
            dispositionDailyAxisCap = b.comment(
                    "Per-axis, per-MC-day cap on total disposition movement from conversations (anti-farming).")
                    .defineInRange("dispositionDailyAxisCap", 8, 1, 50);
            dispositionStaleDays = b.comment(
                    "Prune disposition records untouched for this many MC days (0 = only prune on villager death).")
                    .defineInRange("dispositionStaleDays", 0, 0, 365);
            debugRpg = b.comment(
                    "Verbose logging for disposition reads/writes, check inputs, tier selection, seed derivation.")
                    .define("debugRpg", false);
            b.pop();

            b.push("chat");
            b.comment("Chat-only mode: a second frontend to the same dialogue engine. Players talk to villagers",
                    "by typing in the vanilla chat box and villagers answer in chat, in their own voice,",
                    "applying the identical heart gates, cooldowns, dispositions, moods, checks, and gossip as",
                    "the interact GUI. No AI/LLM — all matching is deterministic and datapack-driven.",
                    "On by default since 0.8.0; set enableChatMode=false for the pre-chat-mode experience.");
            enableChatMode = b.comment(
                    "Master switch. When false, no chat listener work happens and behavior is unchanged.")
                    .define("enableChatMode", true);
            chatModeDefaultOn = b.comment(
                    "Whether players are opted in to chat mode before running '/conversations chat on'.")
                    .define("chatModeDefaultOn", true);
            chatModeRadius = b.comment(
                    "Ambient hearing radius (blocks) for unaddressed messages — villagers this close may",
                    "answer a message that clearly matches a topic but names no one.")
                    .defineInRange("chatModeRadius", 12.0, 1.0, 64.0);
            chatModeAddressedRadius = b.comment(
                    "Radius (blocks) when the villager is named or the sticky conversation partner",
                    "('calling out' across the square). Larger than the ambient radius.")
                    .defineInRange("chatModeAddressedRadius", 24.0, 1.0, 96.0);
            chatModeStickinessTicks = b.comment(
                    "How long (game ticks) the last conversation partner stays the default target (600 = 30s).")
                    .defineInRange("chatModeStickinessTicks", 600, 0, 72000);
            chatModeLookConeDegrees = b.comment(
                    "Half-angle (degrees) of the look-at targeting cone. 0 disables look-at addressing.")
                    .defineInRange("chatModeLookConeDegrees", 25.0, 0.0, 90.0);
            chatModeMaxResponders = b.comment(
                    "Maximum villagers that may answer one ambient (unaddressed) message.")
                    .defineInRange("chatModeMaxResponders", 2, 1, 5);
            chatModeMinScore = b.comment(
                    "Confidence threshold (0-1) for addressed messages — lower favors recall (answer more often).")
                    .defineInRange("chatModeMinScore", 0.55, 0.0, 1.0);
            chatModeAmbientMinScore = b.comment(
                    "Stricter threshold (0-1) for ambient messages so eavesdropping villagers do not misfire on",
                    "player-to-player chatter. Raise on busy town-square servers.")
                    .defineInRange("chatModeAmbientMinScore", 0.75, 0.0, 1.0);
            chatModeReplyDelayTicks = b.comment(
                    "Base humanized delay (game ticks) before a villager's reply appears (scaled up by line length).")
                    .defineInRange("chatModeReplyDelayTicks", 15, 0, 100);
            chatModeCooldownTicks = b.comment(
                    "Per-player floor (game ticks) between processed chat messages (anti-spam).")
                    .defineInRange("chatModeCooldownTicks", 40, 0, 1200);
            chatModePublicReplies = b.comment(
                    "When true (default), a villager's reply is also shown to other players near the villager",
                    "(roleplay feel). When false, only the speaking player sees it (whisper model, GUI parity).")
                    .define("chatModePublicReplies", true);
            chatModeShowHeartChanges = b.comment(
                    "Append a subtle '(+2 heart)'-style suffix to lines for players who want heart-change feedback.")
                    .define("chatModeShowHeartChanges", true);
            chatModeMessageFormat = b.comment(
                    "Chat line template: %1$s = villager name (colored), %2$s = the line. Roleplay servers may",
                    "prefer e.g. \"%1$s: %2$s\".")
                    .define("chatModeMessageFormat", "<%1$s> %2$s");
            chatModeMuteTicks = b.comment(
                    "Duration (game ticks) of a 'stop talking' mute per villager->player pairing (6000 = 5 min).")
                    .defineInRange("chatModeMuteTicks", 6000, 200, 72000);
            chatModeInsultDetection = b.comment(
                    "Map obvious in-game insults to an in-character rebuke and an ANNOYED state (never censors chat).")
                    .define("chatModeInsultDetection", true);
            chatModeLocalChat = b.comment(
                    "EXPERIMENTAL: cancel and rebroadcast opted-in players' chat only within the addressed",
                    "radius (proximity/RP chat). This downgrades their messages to unsigned system messages",
                    "(disables client-side chat reporting for them; they are still logged to the server",
                    "console) and effectively removes global chat for opted-in players. Off by default since",
                    "0.8.1 — solo players and RP packs can safely enable it; on public servers understand the",
                    "chat-signing and moderation implications first. See the spec's chat-signing notes.")
                    .define("chatModeLocalChat", false);
            chatModeGreetOnApproach = b.comment(
                    "Villagers may proactively greet an opted-in player entering the radius (once per",
                    "villager per player per day; see chatModeGreetChance).")
                    .define("chatModeGreetOnApproach", true);
            chatModeGreetChance = b.comment(
                    "Chance (0-1) that a given villager greets a given player on a given day. Scaled by",
                    "personality (outgoing villagers greet more, reserved ones less); deterministic per day,",
                    "so re-entering the radius never re-rolls. 1.0 = everyone always greets.")
                    .defineInRange("chatModeGreetChance", 0.35, 0.0, 1.0);
            chatModeTypingAttention = b.comment(
                    "Nearby villagers stop and look at a player while their chat screen is open (requires the",
                    "client half of this mod, which MCA already requires anyway).")
                    .define("chatModeTypingAttention", true);
            chatModeAttentionTicks = b.comment(
                    "How long (game ticks) a villager keeps standing with its conversation partner after the",
                    "last exchange before wandering off (600 = 30s; refreshed per exchange; 0 disables).")
                    .defineInRange("chatModeAttentionTicks", 600, 0, 72000);
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
