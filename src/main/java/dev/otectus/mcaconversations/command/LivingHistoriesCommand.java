package dev.otectus.mcaconversations.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.mca.McaBinding;
import dev.otectus.mcaconversations.compat.mca.McaHandles;
import dev.otectus.mcaconversations.context.ContextCapabilities;
import dev.otectus.mcaconversations.context.ContextKey;
import dev.otectus.mcaconversations.context.ContextRequest;
import dev.otectus.mcaconversations.context.ContextSources;
import dev.otectus.mcaconversations.context.ContextValue;
import dev.otectus.mcaconversations.context.ConversationContextSnapshot;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import dev.otectus.mcaconversations.history.CommitmentRecord;
import dev.otectus.mcaconversations.history.ConversationHistorySavedData;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.PairHistory;
import dev.otectus.mcaconversations.history.PlayerClaimRecord;
import dev.otectus.mcaconversations.history.SharedThreadRecord;
import dev.otectus.mcaconversations.history.SocialOpinionRecord;
import dev.otectus.mcaconversations.history.VillagerHistory;
import dev.otectus.mcaconversations.identity.Identity;
import dev.otectus.mcaconversations.identity.IdentityCatalogLoader;
import dev.otectus.mcaconversations.identity.VillagerIdentityRecord;
import dev.otectus.mcaconversations.scene.ConversationDirector;
import dev.otectus.mcaconversations.scene.ConversationPlan;
import dev.otectus.mcaconversations.scene.SceneCatalogLoader;
import dev.otectus.mcaconversations.scene.ScenePurpose;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The operator surface for the living-histories layer (spec §22.6).
 *
 * <p>Everything here is <b>inspection</b>, with one deliberate exception. Generated narrative state is
 * invisible in ordinary play by design — a player should meet a person, not a character sheet — which
 * means an operator debugging "why did she say that?" has no other way to look. So there are commands
 * to print a villager's profile, their history with a player, why the last scene was chosen, and which
 * candidates lost.
 *
 * <p>{@code forget} is the exception, and it is narrow on purpose: it drops one villager's history and
 * profile, and it says exactly what it dropped. There is no wipe-everything command, because the plan
 * is explicit that a broad reset needs explicit confirmation and backup guidance, and a command that
 * quietly deleted every villager's memory would be the single most destructive thing in the mod.
 *
 * <p>Every subcommand is level 2 and every one is read-only unless its name says otherwise.
 */
public final class LivingHistoriesCommand {

    /** How far to look for the villager an operator means when they say "this one". */
    private static final double LOOK_RADIUS = 12.0;

    private LivingHistoriesCommand() {
    }

    /** The {@code profile}, {@code history}, {@code scene} and {@code compat} subtrees. */
    public static LiteralArgumentBuilder<CommandSourceStack>[] subtrees() {
        @SuppressWarnings("unchecked")
        LiteralArgumentBuilder<CommandSourceStack>[] trees = new LiteralArgumentBuilder[] {
                Commands.literal("profile")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("inspect")
                                .executes(ctx -> inspectProfile(ctx.getSource())))
                        .then(Commands.literal("tokens")
                                .executes(ctx -> listTokens(ctx.getSource()))),

                Commands.literal("history")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("inspect")
                                .executes(ctx -> inspectHistory(ctx.getSource())))
                        .then(Commands.literal("forget")
                                .then(Commands.literal("confirm")
                                        .executes(ctx -> forget(ctx.getSource())))),

                Commands.literal("scene")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("plan")
                                .executes(ctx -> showPlan(ctx.getSource())))
                        .then(Commands.literal("candidates")
                                .then(Commands.argument("topic", StringArgumentType.word())
                                        .executes(ctx -> showCandidates(ctx.getSource(),
                                                StringArgumentType.getString(ctx, "topic"))))),

                Commands.literal("context")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("snapshot")
                                .executes(ctx -> showSnapshot(ctx.getSource())))
                        .then(Commands.literal("capabilities")
                                .executes(ctx -> showCapabilities(ctx.getSource()))),
        };
        return trees;
    }

    // --- profile -------------------------------------------------------------------------------

    private static int inspectProfile(CommandSourceStack source) {
        ServerPlayer player = player(source);
        Entity villager = nearestVillager(source);
        if (player == null || villager == null) {
            return fail(source, "Stand near an MCA villager and look at them.");
        }
        Optional<VillagerIdentityRecord> profile = Identity.of(villager);
        if (profile.isEmpty()) {
            return fail(source, Identity.enabled()
                    ? "No profile: the identity catalog is empty or this villager has no eligible tokens."
                    : "Identity is switched off (dynamic.identityEnabled).");
        }
        VillagerIdentityRecord record = profile.get();
        say(source, "--- " + McaCompat.getVillagerName(villager).orElse("villager") + " ---");
        say(source, "  seed " + Long.toHexString(record.profileSeed())
                + "  schema " + record.schemaVersion()
                + (record.isComplete() ? "" : "  (reduced profile)"));
        say(source, "  interests    " + record.interests());
        say(source, "  values       " + record.values());
        say(source, "  comfort      " + orNone(record.comfort())
                + "   aversion " + orNone(record.aversion()));
        say(source, "  work style   " + orNone(record.workStyle())
                + "   social " + orNone(record.socialStyle())
                + "   disclosure " + orNone(record.disclosureStyle()));
        say(source, "  origin       " + orNone(record.originMotif()));
        record.formerProfession().ifPresent(previous ->
                say(source, "  was a        " + previous + " (changed day " + record.lastLifeChangeDay() + ")"));
        record.formativeEvent().ifPresent(motif -> say(source, "  formative    " + motif));
        return Command.SINGLE_SUCCESS;
    }

    private static int listTokens(CommandSourceStack source) {
        var catalog = IdentityCatalogLoader.active();
        say(source, "Identity catalog: " + catalog.size() + " token(s), "
                + (catalog.isComplete() ? "every family populated" : "INCOMPLETE — some family is empty"));
        for (var family : dev.otectus.mcaconversations.identity.IdentityFamily.values()) {
            say(source, "  " + family.key() + " (cap " + family.cap() + "): "
                    + catalog.family(family).stream()
                            .map(dev.otectus.mcaconversations.identity.IdentityToken::id).toList());
        }
        return Command.SINGLE_SUCCESS;
    }

    // --- history -------------------------------------------------------------------------------

    private static int inspectHistory(CommandSourceStack source) {
        ServerPlayer player = player(source);
        Entity villager = nearestVillager(source);
        if (player == null || villager == null) {
            return fail(source, "Stand near an MCA villager and look at them.");
        }
        Optional<VillagerHistory> history = ConversationHistorySavedData.get(source.getServer())
                .peek(villager.getUUID());
        if (history.isEmpty()) {
            return fail(source, "No history stored for this villager.");
        }
        long today = source.getLevel().getDayTime() / 24000L;
        VillagerHistory record = history.get();

        say(source, "--- " + McaCompat.getVillagerName(villager).orElse("villager")
                + "  (day " + today + ") ---");
        say(source, "episodes:");
        for (EpisodeRecord episode : record.episodes()) {
            say(source, "  " + episode.kind() + "  " + episode.state().key()
                    + "  salience " + episode.salience()
                    + "  since day " + episode.createdDay()
                    + (episode.isOverdue(today) ? "  OVERDUE" : "")
                    + "  " + episode.payload().keySet());
        }
        say(source, "opinions:");
        for (SocialOpinionRecord opinion : record.opinions()) {
            say(source, "  " + nameOf(source.getLevel(), opinion.target()) + " "
                    + opinion.axis() + " " + (opinion.value() > 0 ? "+" : "") + opinion.value()
                    + "  because " + opinion.cause()
                    + "  (" + opinion.confidence().key() + ", " + opinion.privacy().key() + ")");
        }
        say(source, "roles:");
        for (dev.otectus.mcaconversations.history.SocialRoleRecord role : record.roles()) {
            say(source, "  " + nameOf(source.getLevel(), role.target()) + " is their "
                    + role.role().key()
                    + "  because " + role.cause()
                    + "  for " + role.daysHeld(today) + "d"
                    + role.expiresDay().stream().mapToObj(day -> ", lapses day " + day)
                            .findFirst().orElse(", until withdrawn"));
        }

        dev.otectus.mcaconversations.village.VillageCulture.of(villager).ifPresent(culture -> {
            say(source, "village " + culture.villageId() + " keeps:");
            for (dev.otectus.mcaconversations.village.CultureFamily family
                    : dev.otectus.mcaconversations.village.CultureFamily.values()) {
                culture.token(family).ifPresent(token -> say(source, "  " + family.key() + ": " + token
                        + "  (they " + dev.otectus.mcaconversations.village.VillageCulture
                                .stanceOf(villager, family).key() + " it)"));
            }
        });

        dev.otectus.mcaconversations.hub.HubPlan hub =
                dev.otectus.mcaconversations.hub.DynamicHub.open(villager, player, today);
        if (!hub.isEmpty()) {
            say(source, "hub is offering:");
            for (dev.otectus.mcaconversations.hub.HubSlot slot : hub.slots()) {
                say(source, "  " + slot.answerName() + "  -> topic " + slot.topic());
            }
        }

        PairHistory pair = record.peekPair(player.getUUID()).orElse(null);
        if (pair == null) {
            say(source, "no shared history with " + player.getGameProfile().getName());
            return Command.SINGLE_SUCCESS;
        }
        say(source, "with " + player.getGameProfile().getName() + ":");
        for (SharedThreadRecord thread : pair.threads()) {
            say(source, "  thread " + thread.templateId() + "  " + thread.status().key()
                    + (thread.hasObligation() ? "  owes " + thread.obligation() : "")
                    + "  resumes " + thread.resumeCount() + "x"
                    + "  mentioned day " + thread.lastMentionedDay());
        }
        for (CommitmentRecord commitment : pair.commitments()) {
            say(source, "  promise " + commitment.id() + "  " + commitment.stateToday(today).key()
                    + "  via " + commitment.resolver().key()
                    + (commitment.resolver().isAvailable() ? "" : " (UNOBSERVABLE HERE)")
                    + commitment.dueDay().stream().mapToObj(day -> "  due day " + day)
                            .findFirst().orElse(""));
        }
        for (PlayerClaimRecord claim : pair.claims()) {
            say(source, "  claim " + claim.type() + " = " + claim.value().raw()
                    + (claim.disputed() ? "  DISPUTED (was "
                            + claim.previousValue().map(v -> v.raw()).orElse("?") + ")" : "")
                    + "  from " + claim.sourceReply());
        }
        say(source, "  last spoke day " + pair.lastTalkedDay().orElse(-1)
                + ", first day " + pair.firstMetDay().orElse(-1)
                + ", initiatives today " + pair.recency().initiativesOn(today));
        return Command.SINGLE_SUCCESS;
    }

    private static int forget(CommandSourceStack source) {
        Entity villager = nearestVillager(source);
        if (villager == null) {
            return fail(source, "Stand near an MCA villager and look at them.");
        }
        String name = McaCompat.getVillagerName(villager).orElse("villager");
        Identity.forget(source.getServer(), villager.getUUID());
        dev.otectus.mcaconversations.history.History.forget(source.getServer(), villager.getUUID());
        source.sendSuccess(() -> Component.literal(
                "Dropped " + name + "'s profile and history. Their MCA hearts, memories and this "
                        + "mod's arcs and disposition are untouched; the next conversation will "
                        + "generate a fresh profile from the same seed."), true);
        return Command.SINGLE_SUCCESS;
    }

    // --- scene ---------------------------------------------------------------------------------

    private static int showPlan(CommandSourceStack source) {
        ServerPlayer player = player(source);
        if (player == null) {
            return fail(source, "Run this as a player.");
        }
        Optional<ConversationPlan> plan = ConversationSessions.raw(player.getUUID())
                .flatMap(ConversationSession::plan);
        if (plan.isEmpty()) {
            return fail(source, "No scene is planned. Open a topic with a villager first — with the "
                    + "dynamic layer off, or with nothing eligible, there is deliberately no plan and "
                    + "the static route runs instead.");
        }
        ConversationPlan frozen = plan.get();
        say(source, "--- " + frozen.sceneId() + " ---");
        say(source, "  route  " + frozen.questionId() + " / " + frozen.openingBeatId());
        frozen.slots().forEach((slot, value) -> say(source, "  slot " + slot + " = " + value.qualified()));
        frozen.episodeId().ifPresent(id -> say(source, "  episode " + id));
        frozen.threadId().ifPresent(id -> say(source, "  thread  " + id));
        say(source, "  context " + frozen.context().hex() + "  nonce " + frozen.nonce());
        frozen.explanation().lines().forEach(line -> say(source, line));
        return Command.SINGLE_SUCCESS;
    }

    private static int showCandidates(CommandSourceStack source, String topic) {
        ServerPlayer player = player(source);
        Entity villager = nearestVillager(source);
        if (player == null || villager == null) {
            return fail(source, "Stand near an MCA villager and look at them.");
        }
        say(source, "Scene catalog: " + SceneCatalogLoader.active().size() + " scene(s)");
        // A dry run: this deliberately does NOT store the plan, so inspecting candidates cannot
        // change what the next real conversation says.
        ConversationContextSnapshot snapshot = ContextSources.capture(
                ContextRequest.of(villager, player, ContextRequest.PURPOSE_TOPIC));
        Optional<ConversationPlan> plan = ConversationDirector.select(
                villager, player, ScenePurpose.TOPIC, topic, snapshot);
        if (plan.isEmpty()) {
            return fail(source, "Nothing eligible for topic '" + topic + "'.");
        }
        plan.get().explanation().lines().forEach(line -> say(source, line));
        return Command.SINGLE_SUCCESS;
    }

    // --- context -------------------------------------------------------------------------------

    private static int showSnapshot(CommandSourceStack source) {
        ServerPlayer player = player(source);
        Entity villager = nearestVillager(source);
        if (player == null || villager == null) {
            return fail(source, "Stand near an MCA villager and look at them.");
        }
        ConversationContextSnapshot snapshot = ContextSources.capture(
                ContextRequest.of(villager, player, ContextRequest.PURPOSE_TOPIC));
        say(source, "--- context " + snapshot.fingerprint().hex()
                + "  day " + snapshot.capturedDay() + " ---");
        for (ContextKey<?> key : ContextKey.all()) {
            ContextValue<?> value = snapshot.peek(key);
            say(source, "  " + (key.isVolatile() ? "~" : " ") + key.id() + " = " + value.token());
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int showCapabilities(CommandSourceStack source) {
        ServerPlayer player = player(source);
        Entity villager = nearestVillager(source);
        McaBinding.Resolution resolution = McaHandles.resolution();
        say(source, "MCA binding: " + resolution.status()
                + (resolution.root() == null ? "" : " at " + resolution.root()));
        if (!resolution.unresolvedRequired().isEmpty()) {
            say(source, "  MISSING (required): " + resolution.unresolvedRequired());
        }
        if (!resolution.unresolvedOptional().isEmpty()) {
            say(source, "  absent (fallbacks apply): " + resolution.unresolvedOptional());
        }
        say(source, "context providers:");
        ContextSources.fieldOwners().forEach((field, owner) -> { /* enumerated below by provider */ });
        if (villager == null || player == null) {
            ContextSources.registered().forEach(provider ->
                    say(source, "  " + provider.id() + " declares " + provider.declares().size()
                            + " field(s) — stand near a villager to see live status"));
            return Command.SINGLE_SUCCESS;
        }
        ContextCapabilities capabilities = ContextSources.capture(
                ContextRequest.of(villager, player, ContextRequest.PURPOSE_TOPIC)).capabilities();
        capabilities.asMap().forEach((id, status) -> {
            String note = capabilities.noteOf(id);
            say(source, "  " + id + ": " + status + (note.isEmpty() ? "" : " (" + note + ")"));
        });
        return Command.SINGLE_SUCCESS;
    }

    // --- shared --------------------------------------------------------------------------------

    /**
     * The villager the operator means: the nearest MCA villager within {@link #LOOK_RADIUS}.
     *
     * <p>Nearest rather than looked-at, because a raycast against a moving crowd is fiddly to aim and
     * every one of these commands is a debugging tool where "the one in front of me" is precise enough.
     */
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

    private static String nameOf(ServerLevel level, UUID uuid) {
        return McaCompat.familyTreeName(level, uuid).orElse(uuid.toString().substring(0, 8));
    }

    private static ServerPlayer player(CommandSourceStack source) {
        try {
            return source.getPlayerOrException();
        } catch (Exception e) {
            return null;
        }
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

    /** Unused today; kept so a future subcommand can list scenes without re-deriving the catalog. */
    static List<String> sceneIds() {
        return SceneCatalogLoader.active().all().stream()
                .map(dev.otectus.mcaconversations.scene.SceneDefinition::id)
                .toList();
    }
}
