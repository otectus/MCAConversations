package dev.otectus.mcaconversations.compat.mca;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.check.CheckContextFactory;
import dev.otectus.mcaconversations.check.CheckDefinition;
import dev.otectus.mcaconversations.check.CheckResolver;
import dev.otectus.mcaconversations.check.CheckTier;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.QuestsBridge;
import dev.otectus.mcaconversations.conversation.ConversationCatalogLoader;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import dev.otectus.mcaconversations.conversation.SessionQuery;
import dev.otectus.mcaconversations.progress.BudgetQuery;
import dev.otectus.mcaconversations.conversation.DepthClass;
import dev.otectus.mcaconversations.conversation.SessionDirective;
import dev.otectus.mcaconversations.conversation.TopicEntry;
import dev.otectus.mcaconversations.progress.Affection;
import dev.otectus.mcaconversations.progress.BudgetQuery;
import dev.otectus.mcaconversations.progress.AffectionApply;
import dev.otectus.mcaconversations.progress.Progress;
import dev.otectus.mcaconversations.progress.ProgressApply;
import dev.otectus.mcaconversations.progress.ProgressQuery;
import dev.otectus.mcaconversations.disposition.DispositionApply;
import dev.otectus.mcaconversations.disposition.DispositionAxis;
import dev.otectus.mcaconversations.disposition.DispositionQuery;
import dev.otectus.mcaconversations.disposition.Dispositions;
import dev.otectus.mcaconversations.compat.quests.QuestConditionQuery;
import dev.otectus.mcaconversations.compat.quests.QuestOpenDirective;
import dev.otectus.mcaconversations.gossip.GossipConditionLogic;
import dev.otectus.mcaconversations.gossip.GossipQuery;
import dev.otectus.mcaconversations.gossip.GossipSayDirective;
import dev.otectus.mcaconversations.personality.PersonalityQuery;
import dev.otectus.mcaconversations.season.SeasonContext;
import dev.otectus.mcaconversations.state.MemoryIds;
import dev.otectus.mcaconversations.template.ConversationsSay;
import dev.otectus.mcaconversations.template.SayDirective;
import dev.otectus.mcaconversations.template.WorldContext;
import dev.otectus.mcaconversations.util.SafeParse;
import dev.otectus.mcaconversations.world.WorldQuery;
import net.conczin.mca.entity.ai.LongTermMemory;
import net.conczin.mca.entity.interaction.gifts.GiftPredicate;
import net.conczin.mca.resources.data.dialogue.Actions;

/**
 * Registers this mod's dialogue conditions and actions into MCA's public static registries.
 * Only ever called through {@link dev.otectus.mcaconversations.compat.McaBridge#tryRegister()} (the
 * classloading gate). Lambdas implementing MCA functional interfaces are confined to this package.
 *
 * <p>Every adapter body is wrapped in {@code try/catch (Throwable)} returning a safe default so a
 * runtime failure can never break MCA's dialogue selection loop. Every <b>parser</b> is wrapped in
 * {@link SafeParse#orNull} because MCA's {@code Dialogues} loader has no error containment — a
 * throwing parser aborts the whole datapack reload and crashes world creation; malformed JSON
 * instead parses to null and the adapter degrades to a no-op action / never-matching condition.
 *
 * <p>Registered vocabulary (see DATAPACK.md):
 * <ul>
 *   <li>condition {@code conversations_enabled: "<feature>"} → 1 when the config feature is on</li>
 *   <li>condition {@code conversations_disabled: "<feature>"} → 1 when off (use with a large negative
 *       {@code chance} to sink a result when a feature is disabled)</li>
 *   <li>condition {@code conversations_gossip: {types?, max_age?}} → 1 when the villager has untold
 *       gossip for this player</li>
 *   <li>action {@code conversations_record: {id, var?, time?}} → writes a LongTermMemory flag (a second
 *       {@code remember} for results that need two)</li>
 *   <li>action {@code conversations_say: {phrase, vars?}} → templated in-dialogue line</li>
 *   <li>action {@code conversations_gossip_say: {types?, max_age?, phrase_prefix?}} → tells the next
 *       untold gossip event and marks it told</li>
 *   <li>condition {@code conversations_disposition: {axis, min?, max?}} → 1 while the decayed
 *       disposition axis lies in range (never for Attraction on romance-ineligible targets)</li>
 *   <li>condition {@code conversations_check: {id, tier, axis, difficulty}} → 1 when the seeded
 *       check resolver lands on this result's declared tier</li>
 *   <li>action {@code conversations_disposition_apply: {topic, deltas}} → moves disposition axes
 *       through the farming guards</li>
 *   <li>condition {@code conversations_progress: {arc|milestone|exclusive, …}} → 1 when the durable
 *       narrative ledger says so (arc stage in range, milestone set, exclusive side taken)</li>
 *   <li>action {@code conversations_session: {op, topic?, budget?, branch?}} → frames the exchange on
 *       the shared conversation session; never rewards anything itself</li>
 *   <li>condition {@code conversations_session: {topic?, branch?}} → 1 while the live session is
 *       inside that topic and/or branch, so sibling branches can share a node</li>
 *   <li>condition {@code conversations_budget: {axis, min?, max?, decision?}} → 1 while today's
 *       affection ledger for this villager and player lies in range, so a villager can voice the cap</li>
 *   <li>action {@code conversations_affection_apply: {decision, delta, budget?, policy?}} → the only
 *       guarded route to a heart change inside branching content</li>
 *   <li>action {@code conversations_progress_apply: {arc|milestone|exclusive, …}} (or an array) →
 *       moves arc stages, fires one-shot milestones, decides exclusive choices</li>
 * </ul>
 */
public final class ConversationsMcaRegistrar {

    private ConversationsMcaRegistrar() {
    }

    private static void recordOne(com.google.gson.JsonObject json,
                                  net.conczin.mca.entity.VillagerEntityMCA villager,
                                  net.minecraft.server.level.ServerPlayer player) {
        // Reuse MCA's own id parsing so "var": "player" scoping matches remember/memory.
        String id = LongTermMemory.parseId(json, player);
        if (json.has("time")) {
            villager.getLongTermMemory().remember(id, json.get("time").getAsLong());
        } else {
            villager.getLongTermMemory().remember(id);
        }
        signalQuestTopic(id, villager, player);
    }

    /**
     * When a topic's cooldown flag is (re)written — i.e. the player just had that conversation — signal any
     * MCA: Quests {@code mcaconversations:talk_about} objective for it. Cooldowns are written through
     * {@code conversations_record} (topic-ever flags use MCA's native {@code remember}, which we can't hook), so
     * this fires exactly once per completed topic conversation. No-op when Quests is absent.
     */
    private static void signalQuestTopic(String id, net.conczin.mca.entity.VillagerEntityMCA villager,
                                         net.minecraft.server.level.ServerPlayer player) {
        QuestsBridge.QuestQueries q = QuestsBridge.queries();
        if (q == null) {
            return;
        }
        String cooldownPrefix = MemoryIds.PREFIX + "cooldown.";
        if (!id.startsWith(cooldownPrefix)) {
            return;
        }
        String topic = id.substring(cooldownPrefix.length());
        String scopeSuffix = "." + player.getUUID();
        if (topic.endsWith(scopeSuffix)) {
            topic = topic.substring(0, topic.length() - scopeSuffix.length());
        }
        try {
            q.signalTopicTalked(player, villager, topic);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("quest topic signal failed for {}; ignoring", id, t);
        }
    }

    /**
     * Guards against a second registration pass.
     *
     * <p>MCA's {@code GiftPredicate}/{@code Actions} registries are plain static maps with no
     * duplicate check, so registering the same key twice would quietly replace the first adapter
     * with an identical one -- harmless today, but it means the "registered vocabulary" log line
     * could appear more than once and an unusual lifecycle (a test bootstrap, a future mod-reload
     * feature) would give no signal that it had happened. One call, once, and say so if something
     * asks for a second.
     */
    private static final java.util.concurrent.atomic.AtomicBoolean REGISTERED =
            new java.util.concurrent.atomic.AtomicBoolean();

    public static void register() {
        if (!REGISTERED.compareAndSet(false, true)) {
            McaConversations.LOGGER.warn("Conversations dialogue vocabulary was already registered; "
                    + "ignoring the duplicate request.");
            return;
        }

        // --- Conditions (dialogue JSON shares the gift-predicate condition registry) ---

        GiftPredicate.register("conversations_enabled",
                (json, name) -> SafeParse.orNull("conversations_enabled", json, json::getAsString),
                feature -> (villager, stack, player) -> {
                    try {
                        return feature != null && McaConversationsConfig.isFeatureEnabled(feature) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_enabled({}) failed; defaulting 0", feature, t);
                        return 0.0f;
                    }
                });

        GiftPredicate.register("conversations_disabled",
                (json, name) -> SafeParse.orNull("conversations_disabled", json, json::getAsString),
                feature -> (villager, stack, player) -> {
                    try {
                        return feature != null && !McaConversationsConfig.isFeatureEnabled(feature) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_disabled({}) failed; defaulting 0", feature, t);
                        return 0.0f;
                    }
                });

        GiftPredicate.register("conversations_gossip",
                (json, name) -> SafeParse.orNull("conversations_gossip", json,
                        () -> GossipQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        return query != null && GossipConditionLogic.hasUntoldGossip(query, villager, player)
                                ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_gossip condition failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        GiftPredicate.register("conversations_weather",
                (json, name) -> SafeParse.orNull("conversations_weather", json,
                        () -> WorldQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || !McaConversationsConfig.COMMON.enableWeatherLines.get()) {
                            return 0.0f;
                        }
                        String bucket = WorldContext.weatherBucket(
                                McaCompat.isRaining(villager), McaCompat.isThundering(villager));
                        return query.matches(bucket) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_weather failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        GiftPredicate.register("conversations_season",
                (json, name) -> SafeParse.orNull("conversations_season", json,
                        () -> WorldQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || !McaConversationsConfig.COMMON.enableSeasonLines.get()) {
                            return 0.0f;
                        }
                        return query.matches(SeasonContext.seasonBucket(villager)) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_season failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        GiftPredicate.register("conversations_holiday",
                (json, name) -> SafeParse.orNull("conversations_holiday", json,
                        () -> WorldQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || !McaConversationsConfig.COMMON.enableHolidayLines.get()) {
                            return 0.0f;
                        }
                        return query.matches(SeasonContext.holidayBucket(villager)) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_holiday failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // Personality gate. Replaces MCA's native "personality" condition throughout our content:
        // MCA parses that one with orElseThrow inside an uncontained datapack reload, so an id the
        // running MCA does not know (7.7 dropped witty/shy/lazy/grumpy/athletic) aborts world load.
        // This one is parse-safe and alias-aware, so a single authored id works on 7.6 and 7.7.
        GiftPredicate.register("conversations_personality",
                (json, name) -> SafeParse.orNull("conversations_personality", json,
                        () -> PersonalityQuery.fromJson(json)),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null) {
                            return 0.0f;
                        }
                        return McaCompat.getPersonality(villager)
                                .filter(query::matches)
                                .isPresent() ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_personality failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // --- RPG layer (1.0.0): disposition vector + dialogue checks ---

        // Matches while the decayed axis value lies in [min, max]. Never matches when the vector
        // subsystem is off (content authors an explicit fallback result; lint enforces it) and never
        // matches on Attraction for romance-ineligible targets, whatever range is asked for.
        GiftPredicate.register("conversations_disposition",
                (json, name) -> SafeParse.orNull("conversations_disposition", json,
                        () -> DispositionQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || !McaConversationsConfig.COMMON.enableDispositions.get()) {
                            return 0.0f;
                        }
                        if (query.axis() == DispositionAxis.ATTRACTION
                                && !McaCompat.isRomanceEligible(villager, player)) {
                            return 0.0f;
                        }
                        return query.matches(Dispositions.axis(villager, player, query.axis())) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_disposition failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // Reads the live session the conversations_session ACTION writes. 114 shipped results set a
        // branch and nothing ever read one back, so content duplicated the branch into the node name
        // instead. Registered unconditionally: dialogue naming an unregistered key is a load error,
        // so a pack written for this must still load on a build that has it.
        GiftPredicate.register("conversations_session",
                (json, name) -> SafeParse.orNull("conversations_session", json,
                        () -> SessionQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        if (query == null || player == null) {
                            return 0.0f;
                        }
                        long now = player.level().getGameTime();
                        return ConversationSessions.peek(player.getUUID(), now)
                                .filter(query::matches).isPresent() ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_session condition failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // Reads today's affection ledger so the villager can voice the cap instead of the player
        // quietly receiving nothing once it is reached.
        GiftPredicate.register("conversations_budget",
                (json, name) -> SafeParse.orNull("conversations_budget", json,
                        () -> BudgetQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        return Progress.matchesBudget(villager, player, query) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_budget failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // Matches when the deterministic check resolver's tier equals this result's declared tier.
        // All four tier results of a stance carry the same id/axis/difficulty, so exactly one matches
        // per click; the resolver is pure and seeded, so re-evaluating per candidate result (and
        // re-opening the screen inside a time bucket) cannot change the outcome.
        GiftPredicate.register("conversations_check",
                (json, name) -> SafeParse.orNull("conversations_check", json,
                        () -> CheckDefinition.fromJson(json.getAsJsonObject())),
                check -> (villager, stack, player) -> {
                    try {
                        if (check == null) {
                            return 0.0f;
                        }
                        return CheckContextFactory.assemble(villager, player, check)
                                .map(inputs -> {
                                    CheckTier tier = CheckResolver.resolve(inputs);
                                    if (tier == check.tier()
                                            && McaConversationsConfig.COMMON.debugRpg.get()) {
                                        McaConversations.LOGGER.info("[rpg] check {} -> {} inputs={}",
                                                check.id(), tier.key(), inputs);
                                    }
                                    return tier == check.tier() ? 1.0f : 0.0f;
                                })
                                .orElse(0.0f);
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_check failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // Where a relationship stands in an authored arc, or whether a one-shot milestone or an
        // exclusive promise has been recorded. Reads the progress ledger, which is deliberately
        // independent of the disposition vector so narrative state survives with that feature off.
        GiftPredicate.register("conversations_progress",
                (json, name) -> SafeParse.orNull("conversations_progress", json,
                        () -> ProgressQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        return query != null && Progress.matches(villager, player, query) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_progress failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // --- Quest-aware conditions (MCA: Quests integration; return 0 when that mod is absent) ---
        // These keys are always registered so dialogue JSON referencing them stays a known key and
        // scores 0 (never a crash) on an MCA-only install. The lambdas touch a Quests class only through
        // the pure QuestsBridge SPI, so no mcaquests class loads unless MCA: Quests is present.

        GiftPredicate.register("conversations_quest_available",
                (json, name) -> SafeParse.orNull("conversations_quest_available", json,
                        () -> QuestConditionQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> questScore(query, player, villager, QuestKind.AVAILABLE));

        GiftPredicate.register("conversations_quest_active",
                (json, name) -> SafeParse.orNull("conversations_quest_active", json,
                        () -> QuestConditionQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> questScore(query, player, villager, QuestKind.ACTIVE));

        GiftPredicate.register("conversations_quest_ready",
                (json, name) -> SafeParse.orNull("conversations_quest_ready", json,
                        () -> QuestConditionQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> questScore(query, player, villager, QuestKind.READY));

        GiftPredicate.register("conversations_quest_completed",
                (json, name) -> SafeParse.orNull("conversations_quest_completed", json,
                        () -> QuestConditionQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> questScore(query, player, villager, QuestKind.COMPLETED));

        // --- Reputation-aware conditions (MCA: Reputation integration; 0 when that mod is absent) ---
        // Registered unconditionally for the same reason the quest conditions are: dialogue JSON that
        // names an unregistered key is an error, so a pack written for the full suite must still load
        // on an install without Reputation. Scoring 0 there lets the pack's own fallback branch fire
        // (spec 30.2). Both lambdas reach Reputation only through the pure ReputationBridge SPI.

        GiftPredicate.register("conversations_reputation",
                (json, name) -> SafeParse.orNull("conversations_reputation", json,
                        () -> dev.otectus.mcaconversations.compat.ReputationQueryJson
                                .standing(json.getAsJsonObject())),
                query -> (villager, stack, player) -> standingScore(query, player, villager));

        GiftPredicate.register("conversations_reputation_incident",
                (json, name) -> SafeParse.orNull("conversations_reputation_incident", json,
                        () -> dev.otectus.mcaconversations.compat.ReputationQueryJson
                                .incident(json.getAsJsonObject())),
                query -> (villager, stack, player) -> incidentScore(query, player, villager));

        // --- Actions ---

        Actions.register("conversations_record",
                (json, name) -> json,
                json -> (villager, player) -> {
                    try {
                        // Accepts one {id, var?, time?} object or an array of them (a result may
                        // need several memory writes and JSON keys can't repeat).
                        if (json.isJsonArray()) {
                            for (var element : json.getAsJsonArray()) {
                                recordOne(element.getAsJsonObject(), villager, player);
                            }
                        } else {
                            recordOne(json.getAsJsonObject(), villager, player);
                        }
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_record failed; ignoring", t);
                    }
                });

        Actions.register("conversations_say",
                (json, name) -> SafeParse.orNull("conversations_say", json,
                        () -> SayDirective.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive != null) {
                            ConversationsSay.trigger(directive, villager, player);
                        }
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_say failed; ignoring", t);
                    }
                });

        Actions.register("conversations_gossip_say",
                (json, name) -> SafeParse.orNull("conversations_gossip_say", json,
                        () -> GossipSayDirective.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive != null) {
                            GossipConditionLogic.tellNextGossip(directive, villager, player);
                        }
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_gossip_say failed; ignoring", t);
                    }
                });

        // Moves disposition axes through the farming guards (per-day cap, same-day repeat
        // diminishing). No-op when the vector subsystem is off; Attraction deltas are dropped for
        // romance-ineligible targets inside Dispositions.apply.
        Actions.register("conversations_disposition_apply",
                (json, name) -> SafeParse.orNull("conversations_disposition_apply", json,
                        () -> DispositionApply.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive != null) {
                            Dispositions.apply(villager, player, directive);
                        }
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_disposition_apply failed; ignoring", t);
                    }
                });

        // --- Branching layer (1.1.0): session, guarded affection, narrative progress ---

        // Starts, branches, or ends a topic on the shared conversation session. Carries no reward of
        // its own — an opener is not kindness (plan §3.2) — it only frames what follows.
        Actions.register("conversations_session",
                (json, name) -> SafeParse.orNull("conversations_session", json,
                        () -> SessionDirective.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive != null) {
                            applySession(directive, villager, player);
                        }
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_session failed; ignoring", t);
                    }
                });

        // The one guarded route from authored content to a visible heart change. Runs the full chain:
        // duplicate-transaction refusal, replay policy, per-conversation budget, per-day budget, then
        // MCA's own rewardHearts. Content must never use native positive/negative inside a branch.
        Actions.register("conversations_affection_apply",
                (json, name) -> SafeParse.orNull("conversations_affection_apply", json,
                        () -> AffectionApply.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive != null) {
                            Affection.apply(villager, player, directive);
                        }
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_affection_apply failed; ignoring", t);
                    }
                });

        // Moves durable narrative state: an arc stage (one step at a time, clamped to the catalog
        // bound), a one-shot milestone, or one side of a mutually exclusive choice. Accepts one object
        // or an array of them, because a result may need several and JSON keys cannot repeat.
        Actions.register("conversations_progress_apply",
                (json, name) -> json,
                json -> (villager, player) -> {
                    try {
                        if (json.isJsonArray()) {
                            for (var element : json.getAsJsonArray()) {
                                applyProgress(element.getAsJsonObject(), villager, player);
                            }
                        } else {
                            applyProgress(json.getAsJsonObject(), villager, player);
                        }
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_progress_apply failed; ignoring", t);
                    }
                });

        // Opens (or directly accepts from) the MCA: Quests menu for this villager. No-op when Quests absent.
        Actions.register("conversations_quest_open",
                (json, name) -> SafeParse.orNull("conversations_quest_open", json,
                        () -> QuestOpenDirective.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        QuestsBridge.QuestQueries q = QuestsBridge.queries();
                        if (directive == null || q == null) {
                            return;
                        }
                        if (directive.mode() == QuestOpenDirective.Mode.ACCEPT && directive.quest().isPresent()) {
                            q.accept(player, villager, directive.quest().get());
                        } else {
                            q.openMenu(player, villager);
                        }
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_quest_open failed; ignoring", t);
                    }
                });

        /*
         * conversations_reputation_signal — records an authored conversation outcome as a public deed
         * (spec 30.6).
         *
         * <pre>{@code
         * { "incident": "mcareputation:public_apology", "decision": "standing.apology.public",
         *   "visibility": "witnessed", "policy": "once_per_incident" }
         * }</pre>
         *
         * The action names an INCIDENT DEFINITION; it never accepts a raw score delta. That is what
         * stops a datapack — or a player clicking the same apology repeatedly — from farming standing:
         * how much an apology is worth is decided by the incident's own definition, and Reputation's
         * dedupe key (built from the villager, the player, and the decision id) makes the second click
         * a no-op. Generic small talk, navigation, and asking the opener cannot reach this action at
         * all, because nothing authors it on them.
         */
        Actions.register("conversations_reputation_signal",
                (json, name) -> SafeParse.orNull("conversations_reputation_signal", json,
                        () -> json.getAsJsonObject()),
                obj -> (villager, player) -> {
                    try {
                        var queries = dev.otectus.mcaconversations.compat.ReputationBridge.queries();
                        if (obj == null || queries == null
                                || !dev.otectus.mcaconversations.compat.ReputationBridge.isAvailable()) {
                            return; // no Reputation: an authored signal is simply not recordable
                        }
                        var object = obj.getAsJsonObject();
                        String incident = object.has("incident")
                                ? object.get("incident").getAsString() : null;
                        if (incident == null || incident.isBlank()) {
                            return;
                        }
                        String visibility = object.has("visibility")
                                ? object.get("visibility").getAsString() : null;
                        String decision = object.has("decision")
                                ? object.get("decision").getAsString() : incident;
                        queries.recordSignal(player, villager, incident, visibility, decision);
                    } catch (Throwable t) {
                        McaConversations.LOGGER.debug("conversations_reputation_signal failed; ignoring", t);
                    }
                });

        McaConversations.LOGGER.info("Registered dialogue conditions conversations_enabled/conversations_disabled/conversations_gossip"
                + "/conversations_weather/conversations_season/conversations_holiday/conversations_personality/conversations_disposition"
                + "/conversations_check/conversations_progress/conversations_quest_* and actions conversations_record/conversations_say"
                + "/conversations_gossip_say/conversations_disposition_apply/conversations_session"
                + "/conversations_affection_apply/conversations_progress_apply/conversations_quest_open"
                + "; reputation conditions conversations_reputation/conversations_reputation_incident"
                + " and action conversations_reputation_signal");
    }

    /**
     * Applies one {@code conversations_session} op. The depth class comes from the catalog unless the
     * result overrides it, so a topic's depth is declared in exactly one place.
     */
    private static void applySession(SessionDirective directive,
                                     net.conczin.mca.entity.VillagerEntityMCA villager,
                                     net.minecraft.server.level.ServerPlayer player) {
        long now = villager.level().getGameTime();
        switch (directive.op()) {
            case BEGIN -> {
                String topic = directive.topic().orElseThrow();
                DepthClass budget = directive.budget()
                        .or(() -> ConversationCatalogLoader.topic(topic).map(TopicEntry::depth))
                        .orElse(DepthClass.QUICK);
                ConversationSessions.beginTopic(player.getUUID(), villager.getUUID(), topic, budget, now);
                if (McaConversationsConfig.COMMON.debugBranching.get()) {
                    McaConversations.LOGGER.info("[branch] session begin topic={} budget={} villager={} player={}",
                            topic, budget.key(), villager.getUUID(), player.getName().getString());
                }
            }
            case BRANCH -> ConversationSessions.get(player.getUUID(), now)
                    .setBranch(directive.branch().orElse(null));
            case END -> ConversationSessions.endTopic(player.getUUID(), now);
        }
    }

    /** Parses and applies one {@code conversations_progress_apply} entry, containing parse failures. */
    private static void applyProgress(com.google.gson.JsonObject json,
                                      net.conczin.mca.entity.VillagerEntityMCA villager,
                                      net.minecraft.server.level.ServerPlayer player) {
        ProgressApply directive = SafeParse.orNull("conversations_progress_apply", json,
                () -> ProgressApply.fromJson(json));
        if (directive != null) {
            Progress.apply(villager, player, directive);
        }
    }

    /** Scores a {@code conversations_quest_*} condition through the {@link QuestsBridge} SPI; 0 when Quests absent. */
    private static float questScore(QuestConditionQuery query,
                                    net.minecraft.server.level.ServerPlayer player,
                                    net.minecraft.world.entity.Entity villager, QuestKind kind) {
        if (query == null) {
            return 0.0f;
        }
        // CONFIG.md has always said the quest-aware conditions score 0 with the integration
        // switched off. They did not: the flag gated the voice lines and the gossip seeding but
        // never this, so quest branches kept matching for a player who had turned Quests off.
        if (!McaConversationsConfig.COMMON.enableQuests.get()) {
            return 0.0f;
        }
        try {
            QuestsBridge.QuestQueries q = QuestsBridge.queries();
            if (q == null) {
                return 0.0f;
            }
            boolean thisOnly = query.thisVillagerOnly();
            boolean match = switch (kind) {
                // AVAILABLE is inherently about the villager in front of you — the SPI has no
                // "anywhere" form to call, because MCA: Quests offers are held by a giver. So
                // scope is not silently ignored here, it is not applicable; ContentLintTest
                // rejects scope:"any" on this condition rather than letting a pack believe it works.
                case AVAILABLE -> q.hasEligibleOffer(player, villager);
                case ACTIVE -> q.hasActive(player, villager, thisOnly, query.min());
                case READY -> q.hasReadyTurnIn(player, villager, thisOnly);
                case COMPLETED -> q.completedCount(player, villager, thisOnly) >= query.min();
            };
            return match ? 1.0f : 0.0f;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("conversations quest condition ({}) failed; defaulting 0", kind, t);
            return 0.0f;
        }
    }

    private enum QuestKind { AVAILABLE, ACTIVE, READY, COMPLETED }

    /**
     * Scores {@code conversations_reputation} through the {@link dev.otectus.mcaconversations.compat.ReputationBridge}
     * SPI; {@code 0} when MCA: Reputation is absent, which is what lets an authored disabled-context
     * fallback fire (spec 30.2).
     */
    private static float standingScore(dev.otectus.mcaconversations.compat.ReputationBridge.StandingQuery query,
                                       net.minecraft.server.level.ServerPlayer player,
                                       net.minecraft.world.entity.Entity villager) {
        if (query == null) {
            return 0.0f;
        }
        try {
            var queries = dev.otectus.mcaconversations.compat.ReputationBridge.queries();
            if (queries == null || !dev.otectus.mcaconversations.compat.ReputationBridge.isAvailable()) {
                return 0.0f;
            }
            return queries.matchesStanding(player, villager, query) ? 1.0f : 0.0f;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("conversations_reputation failed; defaulting 0", t);
            return 0.0f;
        }
    }

    /** Scores {@code conversations_reputation_incident}; {@code 0} when MCA: Reputation is absent. */
    private static float incidentScore(dev.otectus.mcaconversations.compat.ReputationBridge.IncidentQuery query,
                                       net.minecraft.server.level.ServerPlayer player,
                                       net.minecraft.world.entity.Entity villager) {
        if (query == null) {
            return 0.0f;
        }
        try {
            var queries = dev.otectus.mcaconversations.compat.ReputationBridge.queries();
            if (queries == null || !dev.otectus.mcaconversations.compat.ReputationBridge.isAvailable()) {
                return 0.0f;
            }
            return queries.matchesIncident(player, villager, query) ? 1.0f : 0.0f;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("conversations_reputation_incident failed; defaulting 0", t);
            return 0.0f;
        }
    }
}
