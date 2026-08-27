package dev.otectus.mcaconversations;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import dev.otectus.mcaconversations.season.CalendarSource;
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
            // Without these, "seasons" and "holidays" fell through to the default and scored as
            // enabled forever, so a conversations_disabled sink on either could never fire and
            // season- or festival-aware content had no way to degrade when its flag was off.
            case "seasons" -> COMMON.enableSeasonLines.get();
            case "holidays" -> COMMON.enableHolidayLines.get();
            case "dispositions" -> COMMON.enableDispositions.get();
            case "checks" -> COMMON.enableChecks.get();
            case "branching" -> COMMON.enableBranching.get();
            case "chat" -> COMMON.enableChatMode.get();
            case "townstead" -> COMMON.townsteadEnabled.get();
            // Living-histories features. Each is gated by the master switch as well as its own, so
            // dynamic.enabled=false silences the whole layer without touching seven other flags.
            case "dynamic" -> COMMON.dynamicEnabled.get();
            case "identity" -> COMMON.dynamicEnabled.get() && COMMON.identityEnabled.get();
            case "episodes" -> COMMON.dynamicEnabled.get() && COMMON.episodesEnabled.get()
                    && COMMON.historyEnabled.get();
            case "history" -> COMMON.historyEnabled.get();
            case "social_opinions" -> COMMON.dynamicEnabled.get() && COMMON.socialOpinionsEnabled.get();
            case "village_culture" -> COMMON.dynamicEnabled.get() && COMMON.villageCultureEnabled.get();
            case "group" -> COMMON.dynamicEnabled.get() && COMMON.groupEnabled.get();
            default -> true;
        };
    }

    /**
     * Reads a living-histories feature switch without ever throwing.
     *
     * <p>{@link #isFeatureEnabled} is called from dialogue conditions, where a config read happens
     * inside MCA's selection loop and a config that has not loaded yet (a datapack reload during world
     * creation) would otherwise propagate. This wrapper answers {@code fallback} in that window rather
     * than taking the reload with it.
     */
    public static boolean dynamicFeature(String feature, boolean fallback) {
        try {
            return isFeatureEnabled(feature);
        } catch (Throwable t) {
            return fallback;
        }
    }

    /** An int from the living-histories sections, with the same never-throw contract. */
    public static int dynamicInt(ForgeConfigSpec.IntValue value, int fallback) {
        try {
            Integer current = value.get();
            return current == null ? fallback : current;
        } catch (Throwable t) {
            return fallback;
        }
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
        public final ForgeConfigSpec.BooleanValue enableBranching;

        public final ForgeConfigSpec.DoubleValue conversationHeartMultiplier;
        public final ForgeConfigSpec.IntValue conversationDailyPositiveCap;
        public final ForgeConfigSpec.IntValue conversationDailyNegativeCap;
        public final ForgeConfigSpec.BooleanValue strongerNegativeOutcomes;
        public final ForgeConfigSpec.IntValue conversationSessionTimeoutTicks;
        public final ForgeConfigSpec.BooleanValue debugBranching;

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

        public final ForgeConfigSpec.BooleanValue townsteadEnabled;
        public final ForgeConfigSpec.BooleanValue townsteadContentEnabled;
        public final ForgeConfigSpec.BooleanValue townsteadContextConditionsEnabled;
        public final ForgeConfigSpec.BooleanValue townsteadContextCheckFitEnabled;
        public final ForgeConfigSpec.BooleanValue townsteadReactionsEnabled;
        public final ForgeConfigSpec.BooleanValue townsteadEmotionEffectsEnabled;
        public final ForgeConfigSpec.BooleanValue townsteadScheduleRespectEnabled;
        public final ForgeConfigSpec.BooleanValue townsteadTypedChatDialogueTrackingEnabled;
        public final ForgeConfigSpec.BooleanValue townsteadGiftNeedObservationEnabled;
        public final ForgeConfigSpec.BooleanValue townsteadGossipEnabled;
        public final ForgeConfigSpec.BooleanValue townsteadCustomPersonalityProfilesEnabled;
        public final ForgeConfigSpec.EnumValue<CalendarSource> calendarSource;
        public final ForgeConfigSpec.BooleanValue useLegacyHolidayFallbackWithTownstead;
        public final ForgeConfigSpec.IntValue townsteadMaxCheckFit;
        public final ForgeConfigSpec.IntValue townsteadContextCacheTicks;
        public final ForgeConfigSpec.IntValue townsteadNeedCrisisCooldownDays;
        public final ForgeConfigSpec.IntValue townsteadBuildingRemovalConfirmScans;
        public final ForgeConfigSpec.BooleanValue townsteadDebug;

        // --- Living histories (spec §22.5) ---------------------------------------------------------
        public final ForgeConfigSpec.BooleanValue dynamicEnabled;
        public final ForgeConfigSpec.BooleanValue identityEnabled;
        public final ForgeConfigSpec.BooleanValue episodesEnabled;
        public final ForgeConfigSpec.BooleanValue socialOpinionsEnabled;
        public final ForgeConfigSpec.BooleanValue villageCultureEnabled;
        public final ForgeConfigSpec.IntValue maxInitiativesPerVillagerPlayerDay;
        public final ForgeConfigSpec.IntValue dynamicTopicSlots;
        public final ForgeConfigSpec.BooleanValue debugDirector;

        public final ForgeConfigSpec.BooleanValue historyEnabled;
        public final ForgeConfigSpec.IntValue episodeRetentionDays;
        public final ForgeConfigSpec.IntValue resolvedEpisodeCap;
        public final ForgeConfigSpec.IntValue activeEpisodeCap;
        public final ForgeConfigSpec.IntValue openThreadCapPerPair;
        public final ForgeConfigSpec.IntValue commitmentCapPerPair;
        public final ForgeConfigSpec.IntValue playerClaimCapPerPair;
        public final ForgeConfigSpec.IntValue socialEdgeCapPerVillager;
        public final ForgeConfigSpec.IntValue topicRecencyCapPerPair;

        public final ForgeConfigSpec.BooleanValue groupEnabled;
        public final ForgeConfigSpec.IntValue groupMaxSpeakers;

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
            enableBranching = b.comment(
                    "Enable branching conversations (1.1.0): a topic opens a short authored exchange in which",
                    "the villager answers and YOU choose what to say back, and your reply — not the act of",
                    "asking — is what moves hearts. When false, every converted topic falls back to its",
                    "legacy one-line result and returns to its category, exactly as in 1.0.0. Turning this",
                    "off never leaves an empty page: each starter carries an explicit legacy fallback result.")
                    .define("enableBranching", true);
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

            b.push("conversation");
            b.comment("The branching-conversation economy (1.1.0). Hearts move on what you SAY BACK, never on",
                    "asking a question, navigating, or leaving. Every heart change from a conversation passes",
                    "through a guarded ledger: an authored delta is scaled by the multiplier, clamped by the",
                    "depth class's per-conversation budget, clamped again by the per-day budget, diminished on",
                    "repeat (full -> half -> nothing for the same decision on the same day), and applied at most",
                    "once per transaction. Milestone outcomes fire once ever. These caps stay active even when",
                    "the disposition vector is switched off.");
            conversationHeartMultiplier = b.comment(
                    "Scale on every conversation-sourced heart change, positive and negative. 0 makes",
                    "conversation heart-neutral (the trees still play, the vector and arcs still move).")
                    .defineInRange("conversationHeartMultiplier", 1.0, 0.0, 4.0);
            conversationDailyPositiveCap = b.comment(
                    "Per-villager, per-player, per-MC-day ceiling on hearts GAINED from conversation.",
                    "Counted separately from the negative budget, so antagonising a villager can never",
                    "manufacture extra room to earn hearts back.")
                    .defineInRange("conversationDailyPositiveCap", 8, 0, 100);
            conversationDailyNegativeCap = b.comment(
                    "Per-villager, per-player, per-MC-day floor on hearts LOST to conversation (as a positive",
                    "number). Stops rage-baiting a villager to farm reconciliation content.")
                    .defineInRange("conversationDailyNegativeCap", 10, 0, 100);
            strongerNegativeOutcomes = b.comment(
                    "Double the authored negative deltas (before the caps) for players who want dismissiveness",
                    "and boundary-pushing to bite harder. Positive outcomes are unaffected.")
                    .define("strongerNegativeOutcomes", false);
            conversationSessionTimeoutTicks = b.comment(
                    "How long (game ticks) a conversation session survives without activity before it expires",
                    "and its per-conversation budget resets (1200 = 60 s). Sessions are transient and never",
                    "persist across a restart; arcs, milestones and the daily budgets do.")
                    .defineInRange("conversationSessionTimeoutTicks", 1200, 200, 24000);
            debugBranching = b.comment(
                    "Verbose logging for the branching layer: topic and node transitions, decision ids, check",
                    "inputs and tier, requested vs applied hearts, disposition deltas, and arc/milestone moves.")
                    .define("debugBranching", false);
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

            b.push("townstead");
            townsteadEnabled = b.comment(
                    "Master switch for the optional Townstead integration. With Townstead absent this",
                    "changes nothing at all. With Townstead installed and this off, Conversations behaves",
                    "exactly as though it were absent: every Townstead condition scores 0, every Townstead",
                    "template variable falls back, and no Townstead state is read or written.")
                    .define("enabled", true);
            townsteadContentEnabled = b.comment(
                    "Offer the Townstead conversation topics (wellbeing, daily rhythm, work and mastery,",
                    "age and life, roots, home and place, community identity, calendar).")
                    .define("contentEnabled", true);
            townsteadContextConditionsEnabled = b.comment(
                    "Let the conversations_townstead* dialogue conditions read Townstead state. Off, they",
                    "all score 0 and authored fallback branches fire instead.")
                    .define("contextConditionsEnabled", true);
            townsteadContextCheckFitEnabled = b.comment(
                    "Let an authored townstead_fit block colour a dialogue check. Off, the term is exactly",
                    "0 and every check resolves precisely as it does without Townstead.")
                    .define("contextCheckFitEnabled", true);
            townsteadReactionsEnabled = b.comment(
                    "Fire Townstead reactions on conversation outcomes. Every bundled reaction is",
                    "heart-neutral. Townstead can only play a reaction through Emotecraft, so without that",
                    "mod this degrades to no reaction rather than to an error.")
                    .define("reactionsEnabled", true);
            townsteadEmotionEffectsEnabled = b.comment(
                    "Supply Conversations emotion tags inside Townstead's RPG dialogue typewriter. Client",
                    "side only, and never leaks markup into chat mode, system chat, TTS or base MCA UI.")
                    .define("emotionEffectsEnabled", true);
            townsteadScheduleRespectEnabled = b.comment(
                    "Let a villager's Townstead shift affect greetings, ambient replies, deep-topic",
                    "availability and how firmly chat mode holds their attention. Off, the existing rules",
                    "apply unchanged and a working villager is interrupted exactly as before.")
                    .define("scheduleRespectEnabled", true);
            townsteadTypedChatDialogueTrackingEnabled = b.comment(
                    "Tell Townstead when a typed-chat conversation opens and closes, so its",
                    "in_dialogue_with_player and dialogue_just_ended context tags are true for chat mode",
                    "as well as for the RPG screen.")
                    .define("typedChatDialogueTrackingEnabled", true);
            townsteadGiftNeedObservationEnabled = b.comment(
                    "After an accepted gift, re-read the villager's Townstead needs one tick later and only",
                    "then let gratitude lines claim the gift helped. Conversations never fills a need",
                    "itself; this only observes whether Townstead's own value improved.")
                    .define("giftNeedObservationEnabled", true);
            townsteadGossipEnabled = b.comment(
                    "Let the existing village gossip sweep also notice Townstead changes: need crises and",
                    "recoveries, profession progress, newly learned skills, life-stage and birthday",
                    "milestones, buildings appearing and disappearing, and village spirit shifting.")
                    .define("gossipEnabled", true);
            townsteadCustomPersonalityProfilesEnabled = b.comment(
                    "Match a Townstead custom personality to its exact interiority profile before falling",
                    "back to the MCA personality it is based on. Off, custom personalities always use their",
                    "MCA base profile.")
                    .define("customPersonalityProfilesEnabled", true);
            calendarSource = b.comment(
                    "Which mod decides the narrative date and season.",
                    "AUTO           - Townstead when healthy, then Serene Seasons, then the built-in calendar.",
                    "TOWNSTEAD      - Townstead only, falling back to the built-in calendar when absent.",
                    "SERENE_SEASONS - Serene Seasons only, falling back to the built-in calendar when absent.",
                    "BUILTIN        - always the built-in quarter-split of the world day.",
                    "Exactly one source ever answers, so two installed calendars cannot contradict",
                    "each other in the same conversation.")
                    .defineEnum("calendarSource", CalendarSource.AUTO);
            useLegacyHolidayFallbackWithTownstead = b.comment(
                    "When Townstead owns the calendar and no townstead_holidays mapping matches today,",
                    "fall back to the built-in fixed festival cycle. Off by default because that cycle is",
                    "keyed to Conversations' own year length and would land on unrelated dates in a",
                    "Townstead calendar.")
                    .define("useLegacyHolidayFallbackWithTownstead", false);
            townsteadMaxCheckFit = b.comment(
                    "Hard clamp on the townstead_fit dialogue-check term, in points. Kept below the",
                    "15-point tier margin so Townstead state can colour a borderline exchange without",
                    "deciding one on its own.")
                    .defineInRange("maxCheckFit", 8, 0, 14);
            townsteadContextCacheTicks = b.comment(
                    "How long a Townstead context read is reused by the chat scans, in ticks. Dialogue",
                    "evaluation always caches for exactly one tick regardless of this, because MCA scores",
                    "many candidate results for a single click.")
                    .defineInRange("contextCacheTicks", 20, 1, 100);
            townsteadNeedCrisisCooldownDays = b.comment(
                    "Days before the same villager can produce another need-crisis rumour, so a villager",
                    "hovering at the edge of hunger is news once rather than every sweep.")
                    .defineInRange("needCrisisCooldownDays", 2, 0, 60);
            townsteadBuildingRemovalConfirmScans = b.comment(
                    "How many consecutive sweeps must agree a known building is gone before that becomes",
                    "news. Guards against a reload or chunk-loading transient reading as a demolition.")
                    .defineInRange("buildingRemovalConfirmScans", 2, 1, 10);
            townsteadDebug = b.comment("Verbose logging for Townstead binding, context reads and reactions.")
                    .define("debug", false);
            b.pop();

            // --- Living histories -------------------------------------------------------------------
            //
            // Every switch here has an OFF state that reproduces 1.4.0 behaviour exactly, because the
            // whole layer is additive: with dynamic.enabled=false the complete 1.4.0 corpus is selected
            // by the same static routers it always was, and nothing new is read, written or generated
            // (spec §22.5). The caps below may be lowered but never raised past the hard limits the
            // stores enforce for themselves.
            b.push("dynamic");
            dynamicEnabled = b.comment(
                    "Master switch for the living-histories layer: stable villager identity,",
                    "typed episodes and threads, and the conversation director that chooses which authored",
                    "scene fits this villager, on this day, after this history.",
                    "When false, topics are selected exactly as in 1.4.0 and no new state is read or written.")
                    .define("enabled", true);
            identityEnabled = b.comment(
                    "Give each villager a small set of stable anchors - two interests, two values, a comfort,",
                    "an aversion, and a work, social and disclosure style - generated once from the world seed",
                    "and their UUID, then never rerolled. This is what makes two farmers different people.",
                    "When false, no profile is generated or persisted and scene selection is identity-neutral.")
                    .define("identityEnabled", true);
            episodesEnabled = b.comment(
                    "Let villagers carry concrete situations between conversations - a damaged book, a wet",
                    "field, a repair that is still blocked - with real states that change and can be resumed.",
                    "When false, only evergreen scenes are selected and no commitment is ever created.")
                    .define("episodesEnabled", true);
            socialOpinionsEnabled = b.comment(
                    "Allow bounded, caused opinions of named neighbours ('Tomas was late, twice').",
                    "Never a full resident-by-resident graph: an edge needs a family tie, shared work or an",
                    "observed event. When false, only MCA's authoritative family and village relations are used.")
                    .define("socialOpinionsEnabled", true);
            villageCultureEnabled = b.comment(
                    "Give each village a few shared tokens - a tradition, a public value, a current debate -",
                    "that its residents can agree or disagree about. When false, villages have no culture and",
                    "residents speak only for themselves.")
                    .define("villageCultureEnabled", true);
            maxInitiativesPerVillagerPlayerDay = b.comment(
                    "How many times a day one villager may open a conversation with one player unprompted.",
                    "Urgent acute-state lines and genuine episode state changes may still bypass this, but",
                    "never the short real-time cooldown. 0 disables villager initiative entirely.")
                    .defineInRange("maxInitiativesPerVillagerPlayerDay", 1, 0, 8);
            dynamicTopicSlots = b.comment(
                    "How many context-specific entries may appear above the six fixed hub categories",
                    "(Continue..., What's on your mind?, Ask about...). 0 keeps the 1.4.0 hub exactly.")
                    .defineInRange("dynamicTopicSlots", 3, 0, 3);
            debugDirector = b.comment(
                    "Log why each scene was chosen: candidate counts, every non-zero score term, the",
                    "rejected finalists and the decisive reason each was dropped. Verbose; for authoring.")
                    .define("debugDirector", false);
            b.pop();

            b.push("history");
            historyEnabled = b.comment(
                    "Persist typed episodes, shared threads, trackable commitments, player claims and social",
                    "opinions to data/mcaconversations_history.dat. When false, nothing new is written and the",
                    "1.4.0 arcs, milestones, affection budgets and disposition vectors are untouched.")
                    .define("enabled", true);
            episodeRetentionDays = b.comment(
                    "How many in-game days a resolved episode stays available for callbacks before it is",
                    "compressed to a milestone token and pruned.")
                    .defineInRange("episodeRetentionDays", 32, 1, 365);
            activeEpisodeCap = b.comment(
                    "Most simultaneously active or blocked episodes one villager may carry. Beyond this the",
                    "lowest-salience one is abandoned; an episode a live thread references is never pruned.")
                    .defineInRange("activeEpisodeCap", 6, 1, 32);
            resolvedEpisodeCap = b.comment("Most resolved episodes one villager keeps as remembered history.")
                    .defineInRange("resolvedEpisodeCap", 24, 1, 128);
            openThreadCapPerPair = b.comment(
                    "Most open conversation threads between one villager and one player. Only the highest",
                    "priority item in each category is ever offered, so this is a storage bound, not a menu size.")
                    .defineInRange("openThreadCapPerPair", 8, 1, 32);
            commitmentCapPerPair = b.comment("Most tracked promises between one villager and one player.")
                    .defineInRange("commitmentCapPerPair", 8, 1, 32);
            playerClaimCapPerPair = b.comment(
                    "Most things a player has explicitly told one villager about themselves that are remembered.")
                    .defineInRange("playerClaimCapPerPair", 16, 1, 64);
            socialEdgeCapPerVillager = b.comment(
                    "Most explicit opinions one villager may hold about named neighbours.")
                    .defineInRange("socialEdgeCapPerVillager", 16, 1, 64);
            topicRecencyCapPerPair = b.comment(
                    "How many recent scenes, subjects and rhetorical shapes are remembered per pair for",
                    "repetition suppression.")
                    .defineInRange("topicRecencyCapPerPair", 32, 4, 128);
            b.pop();

            b.push("group");
            groupEnabled = b.comment(
                    "Allow a second and third villager to join a conversation with a contracted interjection.",
                    "Off by default: group scenes are chat-mode only for now and every interjection must",
                    "answer the line before it and have a real reason to know what it says.")
                    .define("enabled", false);
            groupMaxSpeakers = b.comment("Hard cap on speakers in one group scene, including the lead villager.")
                    .defineInRange("maxSpeakers", 3, 2, 3);
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
