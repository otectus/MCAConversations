package dev.otectus.mcaconversations.command;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.check.CheckContextFactory;
import dev.otectus.mcaconversations.check.CheckDefinition;
import dev.otectus.mcaconversations.check.CheckInputs;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.Townstead;
import dev.otectus.mcaconversations.compat.TownsteadBridge;
import dev.otectus.mcaconversations.compat.TownsteadCalendarView;
import dev.otectus.mcaconversations.compat.TownsteadCapability;
import dev.otectus.mcaconversations.compat.TownsteadConditions;
import dev.otectus.mcaconversations.compat.TownsteadSnapshot;
import dev.otectus.mcaconversations.compat.TownsteadStatus;
import dev.otectus.mcaconversations.compat.TownsteadVillagerView;
import dev.otectus.mcaconversations.compat.mca.McaBinding;
import dev.otectus.mcaconversations.conversation.ContentOperation;
import dev.otectus.mcaconversations.conversation.DialogueResourceIndex;
import dev.otectus.mcaconversations.conversation.ResourceOrigin;
import dev.otectus.mcaconversations.season.SeasonContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fml.ModList;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * {@code /conversations compat ...}: what this build bound, and what Townstead is telling it
 * (Townstead spec §20).
 *
 * <p>{@code status} and {@code namespace} are player-safe summaries anyone may run. {@code probe},
 * {@code snapshot} and {@code explain} are level 2, and the genetic detail under
 * {@code snapshot genes} (heritage fractions, carried variants, expressed alleles, whether fertility
 * is simulated) is level 3: none of it is ever spoken in ordinary play.
 *
 * <p>Everything here is read-only. {@code explain} evaluates conditions but selects nothing, fires no
 * reaction and records nothing.
 */
public final class TownsteadCommand {

    private static final double LOOK_RADIUS = 12.0;
    private static final int TAG_LIST_LIMIT = 24;

    private TownsteadCommand() {
    }

    public static LiteralArgumentBuilder<CommandSourceStack> subtree() {
        return Commands.literal("compat")
                .then(Commands.literal("namespace").executes(ctx -> namespace(ctx.getSource())))
                .then(Commands.literal("townstead")
                        .then(Commands.literal("status").executes(ctx -> status(ctx.getSource())))
                        .then(Commands.literal("probe")
                                .requires(source -> source.hasPermission(2))
                                .executes(ctx -> probe(ctx.getSource())))
                        .then(Commands.literal("snapshot")
                                .requires(source -> source.hasPermission(2))
                                .executes(ctx -> snapshot(ctx.getSource(), false))
                                .then(Commands.literal("genes")
                                        .requires(source -> source.hasPermission(3))
                                        .executes(ctx -> snapshot(ctx.getSource(), true))))
                        .then(Commands.literal("explain")
                                .requires(source -> source.hasPermission(2))
                                .then(Commands.argument("question", StringArgumentType.string())
                                        .then(Commands.argument("answer", StringArgumentType.string())
                                                .executes(ctx -> explain(ctx.getSource(),
                                                        StringArgumentType.getString(ctx, "question"),
                                                        StringArgumentType.getString(ctx, "answer")))))));
    }

    // --- namespace -----------------------------------------------------------------------------

    private static int namespace(CommandSourceStack source) {
        say(source, "MCA binding: " + McaBinding.describe());
        TownsteadBridge bridge = Townstead.bridge();
        say(source, "Townstead: " + (bridge.isAvailable()
                ? bridge.detectedVersion() + ", compiled against MCA root " + bridge.variant().orElse("unknown")
                : "not bound"));
        return Command.SINGLE_SUCCESS;
    }

    // --- status --------------------------------------------------------------------------------

    private static int status(CommandSourceStack source) {
        TownsteadBridge bridge = Townstead.bridge();
        TownsteadStatus status = displayedStatus(bridge);
        say(source, "Townstead: " + status.name().toLowerCase()
                + (bridge.isAvailable() ? " (" + bridge.detectedVersion() + ", MCA root "
                + bridge.variant().orElse("unknown") + ")" : ""));
        if (!bridge.isAvailable()) {
            say(source, "  Townstead conditions score 0, Townstead template values use their fallbacks, "
                    + "and no Townstead state is read.");
            return Command.SINGLE_SUCCESS;
        }
        Set<TownsteadCapability> missing = EnumSet.copyOf(TownsteadCapability.serverSide());
        missing.removeAll(bridge.capabilities());
        say(source, "  capabilities " + bridge.capabilities().size() + "/" + TownsteadCapability.serverSide().size()
                + (missing.isEmpty() ? "" : "; missing " + keys(missing)));
        int backends = bridge.reactionBackendCount();
        say(source, "  reactions " + (backends > 0 ? backends + " backend(s)"
                : "inert: Townstead has no reaction backend (install Emotecraft)"));
        Entity near = nearestVillager(source);
        SeasonContext.Resolved season = SeasonContext.resolveSeason(near);
        say(source, "  calendar source " + Townstead.calendarSource().key() + ", season from "
                + season.source() + (near == null ? " (no villager nearby; world default)" : ""));
        say(source, "  toggles content " + onOff(Townstead.contentEnabled())
                + ", conditions " + onOff(Townstead.conditionsEnabled())
                + ", check fit " + onOff(Townstead.checkFitEnabled())
                + ", reactions " + onOff(Townstead.reactionsEnabled())
                + ", schedule " + onOff(Townstead.scheduleRespectEnabled())
                + ", typed-chat tracking " + onOff(Townstead.dialogueTrackingEnabled())
                + ", gift needs " + onOff(Townstead.giftNeedObservationEnabled())
                + ", gossip " + onOff(Townstead.gossipEnabled())
                + ", custom personalities " + onOff(Townstead.customPersonalityProfilesEnabled())
                + ", legacy holidays " + onOff(Townstead.legacyHolidayFallback()));
        Townstead.CacheStats cache = Townstead.cacheStats();
        say(source, "  snapshot cache " + cache.hits() + " hit(s), " + cache.misses() + " miss(es), "
                + cache.size() + " held");
        List<String> failures = bridge.failedReads();
        say(source, "  failed reads " + (failures.isEmpty() ? "none"
                : failures.size() + "; first " + failures.get(0)));
        return Command.SINGLE_SUCCESS;
    }

    /** DISABLED is not a bridge state: a switched-off integration never installs a bridge at all. */
    private static TownsteadStatus displayedStatus(TownsteadBridge bridge) {
        if (bridge.status() != TownsteadStatus.ABSENT) {
            return bridge.status();
        }
        boolean installed;
        try {
            installed = ModList.get().isLoaded("townstead");
        } catch (Throwable t) {
            installed = false;
        }
        if (!installed) {
            return TownsteadStatus.ABSENT;
        }
        boolean enabled;
        try {
            enabled = McaConversationsConfig.COMMON.townsteadEnabled.get();
        } catch (Throwable t) {
            enabled = true;
        }
        return enabled ? TownsteadStatus.INCOMPATIBLE : TownsteadStatus.DISABLED;
    }

    // --- probe ---------------------------------------------------------------------------------

    private static int probe(CommandSourceStack source) {
        TownsteadBridge bridge = Townstead.bridge();
        say(source, "Townstead probe: " + displayedStatus(bridge).name().toLowerCase()
                + ", version " + (bridge.detectedVersion().isEmpty() ? "-" : bridge.detectedVersion())
                + ", MCA root " + bridge.variant().orElse("-"));
        for (TownsteadCapability capability : TownsteadCapability.values()) {
            String state = !TownsteadCapability.serverSide().contains(capability) ? "client  "
                    : bridge.has(capability) ? "bound   " : "MISSING ";
            say(source, "  " + state + capability.key());
        }
        for (String member : bridge.unresolvedMembers()) {
            say(source, "  unresolved " + member);
        }
        say(source, "  reaction backends " + bridge.reactionBackendCount());
        for (String failure : bridge.failedReads()) {
            say(source, "  failed read " + failure);
        }
        return Command.SINGLE_SUCCESS;
    }

    // --- snapshot ------------------------------------------------------------------------------

    private static int snapshot(CommandSourceStack source, boolean genes) {
        Entity villager = nearestVillager(source);
        if (villager == null) {
            return fail(source, "Stand near an MCA villager.");
        }
        if (!Townstead.active()) {
            return fail(source, "Townstead is not active; there is nothing to read.");
        }
        TownsteadSnapshot snapshot = Townstead.snapshot(villager);
        TownsteadVillagerView v = snapshot.villager();
        say(source, "--- " + McaCompat.getVillagerName(villager).orElse("villager") + " (Townstead) ---");
        var needs = v.needs();
        say(source, "  needs    hunger " + needs.hunger() + "/100 " + needs.hungerBucket()
                + ", thirst " + (needs.thirstActive() ? needs.thirst() + "/20 " + needs.thirstBucket() : "not simulated")
                + ", fatigue " + needs.fatigue() + "/20 " + needs.fatigueBucket()
                + ", primary " + needs.primaryNeed() + (needs.inCrisis() ? ", IN CRISIS" : ""));
        var schedule = v.schedule();
        say(source, "  schedule " + schedule.currentActivity() + " (planned " + schedule.plannedActivity()
                + "), template " + orNone(schedule.currentTemplateId()) + ", hour " + schedule.currentDisplayHour());
        var life = v.life();
        say(source, "  life     root " + orNone(life.rootId()) + ", stage " + orNone(life.lifeStage())
                + ", apparent age " + life.apparentAgeYears() + " (" + life.ageDescription() + ")");
        var origin = snapshot.origin();
        say(source, "  origin   species " + orNone(origin.effectiveSpecies()) + ", ancestry "
                + orNone(origin.ancestry()) + ", lineage " + orNone(origin.lineage())
                + ", heritage " + v.dominantHeritage().orElse("mixed or unknown"));
        var profession = v.profession();
        say(source, "  work     " + orNone(profession.professionId()) + " level " + profession.level()
                + " xp " + profession.xp() + ", " + profession.skills().size() + " skill(s)"
                + (profession.skills().isEmpty() ? "" : " " + limit(new TreeSet<>(profession.skills()), 8)));
        var personality = v.personality();
        say(source, "  persona  " + orNone(personality.id()) + (personality.custom()
                ? " (custom, based on " + orNone(personality.baseId()) + ")" : ""));
        var building = snapshot.building();
        say(source, "  building " + (building.present() ? building.type() + " (level " + building.level() + ")" : "none"));
        var spirit = snapshot.spirit();
        say(source, "  spirit   " + (spirit.isEmpty() ? "none" : "tier " + spirit.tier() + " "
                + orNone(spirit.classification()) + ", primary " + orNone(spirit.primaryId())
                + ", secondary " + orNone(spirit.secondaryId()) + ", total " + spirit.total()));
        TownsteadCalendarView calendar = snapshot.calendar();
        say(source, "  calendar " + (calendar.isEmpty() ? "none" : calendar.profileId() + " year " + calendar.year()
                + " month " + calendar.month() + " day " + calendar.day() + ", weekday " + calendar.dayOfWeek()
                + ", season " + orNone(calendar.season()) + ", festival " + SeasonContext.holidayBucket(villager)));
        List<String> tags = new ArrayList<>(new TreeSet<>(snapshot.tags()));
        say(source, "  tags     " + tags.size() + (tags.isEmpty() ? "" : " " + limit(tags, TAG_LIST_LIMIT)));
        if (genes) {
            say(source, "  heritage " + v.heritage());
            say(source, "  carried  " + v.carriedVariants());
            say(source, "  alleles  " + v.expressedAlleles());
            say(source, "  fertility simulated " + life.fertilityPresent());
        }
        return Command.SINGLE_SUCCESS;
    }

    // --- explain -------------------------------------------------------------------------------

    private static int explain(CommandSourceStack source, String question, String answer) {
        try (ContentOperation ignored = ContentOperation.open()) {
            return explainPinned(source, question, answer);
        }
    }

    private static int explainPinned(CommandSourceStack source, String question, String answer) {
        ServerPlayer player = player(source);
        Entity villager = nearestVillager(source);
        if (player == null || villager == null) {
            return fail(source, "Stand near an MCA villager.");
        }
        DialogueResourceIndex.IndexedQuestion indexed = ContentOperation.bundle().dialogues().questions().get(question);
        if (indexed == null) {
            return fail(source, "No dialogue question '" + question + "' is loaded.");
        }
        TownsteadSnapshot snapshot = Townstead.snapshot(villager);
        List<String> tags = new ArrayList<>(new TreeSet<>(snapshot.tags()));
        say(source, "--- explain " + question + " / " + answer + " for "
                + McaCompat.getVillagerName(villager).orElse("villager") + " ---");
        say(source, "  Townstead " + (Townstead.active() ? "active" : "inactive")
                + ", conditions " + onOff(Townstead.conditionsEnabled())
                + ", " + tags.size() + " context tag(s)" + (tags.isEmpty() ? "" : " " + limit(tags, TAG_LIST_LIMIT)));
        boolean found = false;
        for (ResourceOrigin origin : indexed.sources()) {
            Optional<JsonObject> answerJson = readAnswer(source, origin, answer);
            if (answerJson.isEmpty()) {
                continue;
            }
            found = true;
            say(source, "  from " + origin.resource() + " (" + origin.pack() + ")");
            explainAnswer(source, answerJson.get(), villager, player);
        }
        if (!found) {
            return fail(source, "Question '" + question + "' has no answer '" + answer + "'.");
        }
        return Command.SINGLE_SUCCESS;
    }

    private static Optional<JsonObject> readAnswer(CommandSourceStack source, ResourceOrigin origin, String answer) {
        try {
            Optional<Resource> resource = source.getServer().getResourceManager().getResource(origin.resource());
            if (resource.isEmpty()) {
                return Optional.empty();
            }
            JsonElement root;
            try (BufferedReader reader = resource.get().openAsReader()) {
                root = JsonParser.parseReader(reader);
            }
            if (!root.isJsonObject() || !root.getAsJsonObject().has("answers")) {
                return Optional.empty();
            }
            for (JsonElement element : root.getAsJsonObject().getAsJsonArray("answers")) {
                if (element.isJsonObject() && element.getAsJsonObject().has("name")
                        && answer.equals(element.getAsJsonObject().get("name").getAsString())) {
                    return Optional.of(element.getAsJsonObject());
                }
            }
        } catch (Throwable t) {
            say(source, "  could not read " + origin.resource() + ": " + t.getClass().getSimpleName());
        }
        return Optional.empty();
    }

    private static void explainAnswer(CommandSourceStack source, JsonObject answer, Entity villager, ServerPlayer player) {
        if (!answer.has("results") || !answer.get("results").isJsonArray()) {
            say(source, "    no results");
            return;
        }
        int index = 0;
        for (JsonElement element : answer.getAsJsonArray("results")) {
            index++;
            if (!element.isJsonObject()) {
                continue;
            }
            JsonObject result = element.getAsJsonObject();
            List<String> lines = new ArrayList<>();
            int townsteadChance = 0;
            if (result.has("conditions") && result.get("conditions").isJsonArray()) {
                for (JsonElement condition : result.getAsJsonArray("conditions")) {
                    if (!condition.isJsonObject()) {
                        continue;
                    }
                    JsonObject object = condition.getAsJsonObject();
                    int chance = object.has("chance") ? object.get("chance").getAsInt() : 0;
                    for (String type : TownsteadConditions.TYPES) {
                        if (!object.has(type)) {
                            continue;
                        }
                        String verdict;
                        try {
                            boolean holds = TownsteadConditions.test(TownsteadConditions.parse(type, object.get(type)), villager);
                            verdict = holds ? "holds" : "does not hold";
                            if (holds) {
                                townsteadChance += chance;
                            }
                        } catch (IllegalArgumentException malformed) {
                            verdict = "REFUSED: " + malformed.getMessage();
                        }
                        lines.add("      " + type + " " + object.get(type) + " (" + signed(chance) + ") " + verdict);
                    }
                    if (object.has("conversations_check") && object.get("conversations_check").isJsonObject()) {
                        lines.add("      " + describeCheck(object.getAsJsonObject("conversations_check"), villager, player));
                    }
                }
            }
            JsonObject actions = result.has("actions") && result.get("actions").isJsonObject()
                    ? result.getAsJsonObject("actions") : new JsonObject();
            if (actions.has("conversations_townstead_react")) {
                lines.add("      reaction " + actions.get("conversations_townstead_react") + ": " + reactionGate());
            }
            if (lines.isEmpty()) {
                say(source, "    result " + index + ": no Townstead terms");
                continue;
            }
            say(source, "    result " + index + ": Townstead terms add " + signed(townsteadChance));
            for (String line : lines) {
                say(source, line);
            }
        }
    }

    private static String describeCheck(JsonObject json, Entity villager, ServerPlayer player) {
        try {
            CheckDefinition check = CheckDefinition.fromJson(json);
            Optional<CheckInputs> inputs = CheckContextFactory.assemble(villager, player, check);
            if (inputs.isEmpty()) {
                return "check " + check.axis() + ": not assembled (dialogue checks or the vector are off)";
            }
            CheckInputs in = inputs.get();
            return "check " + check.axis() + " vs " + in.difficulty() + ": axis " + in.axisValue()
                    + ", hearts " + in.hearts() + ", personality " + signed(in.personalityFit())
                    + ", standing " + signed(in.publicStandingFit()) + ", mood " + signed(in.moodAdjust())
                    + ", townsteadFit " + signed(in.townsteadFit())
                    + (check.townsteadFit().isPresent() ? "" : " (no townstead_fit authored)");
        } catch (Throwable t) {
            return "check: REFUSED: " + t.getMessage();
        }
    }

    private static String reactionGate() {
        if (!Townstead.reactionsEnabled()) {
            return "gated: reactions are off or Townstead is inactive";
        }
        if (!Townstead.has(TownsteadCapability.FIRE_REACTION)) {
            return "gated: the reaction dispatcher did not bind";
        }
        if (Townstead.bridge().reactionBackendCount() == 0) {
            return "gated: Townstead has no reaction backend (install Emotecraft)";
        }
        return "queued on selection, fired once after the outcome settles, subject to Townstead's own gates";
    }

    // --- helpers -------------------------------------------------------------------------------

    private static Entity nearestVillager(CommandSourceStack source) {
        ServerPlayer player = player(source);
        if (player == null) {
            return null;
        }
        AABB box = player.getBoundingBox().inflate(LOOK_RADIUS);
        Entity best = null;
        double bestDistance = Double.MAX_VALUE;
        for (Entity entity : player.level().getEntities(player, box, McaCompat::isMcaVillager)) {
            double distance = entity.distanceToSqr(player);
            if (distance < bestDistance) {
                bestDistance = distance;
                best = entity;
            }
        }
        return best;
    }

    private static ServerPlayer player(CommandSourceStack source) {
        try {
            return source.getPlayerOrException();
        } catch (Exception e) {
            return null;
        }
    }

    private static String keys(Set<TownsteadCapability> capabilities) {
        return capabilities.stream().map(TownsteadCapability::key).toList().toString();
    }

    private static String limit(java.util.Collection<String> values, int max) {
        List<String> list = List.copyOf(values);
        return list.size() <= max ? list.toString()
                : list.subList(0, max) + " +" + (list.size() - max) + " more";
    }

    private static String onOff(boolean value) {
        return value ? "on" : "off";
    }

    private static String signed(int value) {
        return value >= 0 ? "+" + value : String.valueOf(value);
    }

    private static String orNone(String value) {
        return value == null || value.isEmpty() ? "-" : value;
    }

    private static void say(CommandSourceStack source, String line) {
        source.sendSuccess(() -> Component.literal(line), false);
    }

    private static int fail(CommandSourceStack source, String message) {
        source.sendFailure(Component.literal(message));
        return 0;
    }
}
