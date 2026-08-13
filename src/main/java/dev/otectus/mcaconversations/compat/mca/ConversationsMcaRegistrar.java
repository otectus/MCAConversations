package dev.otectus.mcaconversations.compat.mca;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.check.CheckContextFactory;
import dev.otectus.mcaconversations.check.CheckDefinition;
import dev.otectus.mcaconversations.check.CheckResolver;
import dev.otectus.mcaconversations.check.CheckTier;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.compat.QuestsBridge;
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
import forge.net.mca.entity.ai.LongTermMemory;
import forge.net.mca.entity.interaction.gifts.GiftPredicate;
import forge.net.mca.resources.data.dialogue.Actions;

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
 * </ul>
 */
public final class ConversationsMcaRegistrar {

    private ConversationsMcaRegistrar() {
    }

    private static void recordOne(com.google.gson.JsonObject json,
                                  forge.net.mca.entity.VillagerEntityMCA villager,
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
    private static void signalQuestTopic(String id, forge.net.mca.entity.VillagerEntityMCA villager,
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

    public static void register() {
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

        McaConversations.LOGGER.info("Registered dialogue conditions conversations_enabled/conversations_disabled/conversations_gossip"
                + "/conversations_weather/conversations_season/conversations_holiday/conversations_personality/conversations_disposition"
                + "/conversations_check/conversations_quest_* and actions conversations_record/conversations_say"
                + "/conversations_gossip_say/conversations_disposition_apply/conversations_quest_open");
    }

    /** Scores a {@code conversations_quest_*} condition through the {@link QuestsBridge} SPI; 0 when Quests absent. */
    private static float questScore(QuestConditionQuery query,
                                    net.minecraft.server.level.ServerPlayer player,
                                    net.minecraft.world.entity.Entity villager, QuestKind kind) {
        if (query == null) {
            return 0.0f;
        }
        try {
            QuestsBridge.QuestQueries q = QuestsBridge.queries();
            if (q == null) {
                return 0.0f;
            }
            boolean thisOnly = query.thisVillagerOnly();
            boolean match = switch (kind) {
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
}
