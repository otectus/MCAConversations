package dev.otectus.mcarealtalk;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Locale;

/** Forge common + client configuration. See CONFIG.md for the user-facing documentation. */
public final class McaRealTalkConfig {

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

    private McaRealTalkConfig() {
    }

    /**
     * Resolves a feature id used by the {@code realtalk_enabled}/{@code realtalk_disabled} dialogue
     * conditions. Unknown ids count as enabled so a content typo degrades to "always on" rather
     * than silently killing a dialogue branch.
     */
    public static boolean isFeatureEnabled(String feature) {
        return switch (feature.toLowerCase(Locale.ROOT)) {
            case "topics" -> COMMON.enableTopics.get();
            case "states" -> COMMON.enableStates.get();
            case "templates" -> COMMON.enableTemplates.get();
            case "gossip" -> COMMON.enableGossip.get();
            default -> true;
        };
    }

    public static final class Common {
        public final ForgeConfigSpec.BooleanValue enableTopics;
        public final ForgeConfigSpec.BooleanValue enableStates;
        public final ForgeConfigSpec.BooleanValue enableTemplates;
        public final ForgeConfigSpec.BooleanValue enableGossip;

        public final ForgeConfigSpec.IntValue giftMemoryPerPlayerCap;
        public final ForgeConfigSpec.IntValue gratitudeWindowTicks;

        public final ForgeConfigSpec.IntValue gossipScanIntervalTicks;
        public final ForgeConfigSpec.IntValue gossipRetentionDays;
        public final ForgeConfigSpec.IntValue maxEventsPerVillage;
        public final ForgeConfigSpec.BooleanValue detectMarriage;
        public final ForgeConfigSpec.BooleanValue detectDivorce;
        public final ForgeConfigSpec.BooleanValue detectDeath;
        public final ForgeConfigSpec.BooleanValue detectBirth;

        public final ForgeConfigSpec.BooleanValue debugLogging;

        Common(ForgeConfigSpec.Builder b) {
            b.push("features");
            enableTopics = b.comment("Enable the Real Talk conversation topics (heart-gated personal questions).")
                    .define("enableTopics", true);
            enableStates = b.comment("Enable conversation states (e.g. gratitude after a gift influences dialogue).")
                    .define("enableStates", true);
            enableTemplates = b.comment("Enable personalized (templated) lines, e.g. referencing the last gift by name.")
                    .define("enableTemplates", true);
            enableGossip = b.comment("Enable village gossip (villagers mention marriages, births, deaths of others).")
                    .define("enableGossip", true);
            b.pop();

            b.push("gift");
            giftMemoryPerPlayerCap = b.comment("Most recent gifts remembered per player (oldest dropped first).")
                    .defineInRange("giftMemoryPerPlayerCap", 16, 1, 256);
            gratitudeWindowTicks = b.comment("How long (game ticks) a villager stays 'grateful' after an accepted gift (24000 = 1 MC day).")
                    .defineInRange("gratitudeWindowTicks", 24000, 1200, 168000);
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
