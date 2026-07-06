package dev.otectus.mcarealtalk.compat.mca;

import dev.otectus.mcarealtalk.McaRealTalk;
import dev.otectus.mcarealtalk.McaRealTalkConfig;
import dev.otectus.mcarealtalk.gossip.GossipConditionLogic;
import dev.otectus.mcarealtalk.gossip.GossipQuery;
import dev.otectus.mcarealtalk.gossip.GossipSayDirective;
import dev.otectus.mcarealtalk.template.RealTalkSay;
import dev.otectus.mcarealtalk.template.SayDirective;
import dev.otectus.mcarealtalk.util.SafeParse;
import forge.net.mca.entity.ai.LongTermMemory;
import forge.net.mca.entity.interaction.gifts.GiftPredicate;
import forge.net.mca.resources.data.dialogue.Actions;

/**
 * Registers this mod's dialogue conditions and actions into MCA's public static registries.
 * Only ever called through {@link dev.otectus.mcarealtalk.compat.McaBridge#tryRegister()} (the
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
 *   <li>condition {@code realtalk_enabled: "<feature>"} → 1 when the config feature is on</li>
 *   <li>condition {@code realtalk_disabled: "<feature>"} → 1 when off (use with a large negative
 *       {@code chance} to sink a result when a feature is disabled)</li>
 *   <li>condition {@code realtalk_gossip: {types?, max_age?}} → 1 when the villager has untold
 *       gossip for this player</li>
 *   <li>action {@code realtalk_record: {id, var?, time?}} → writes a LongTermMemory flag (a second
 *       {@code remember} for results that need two)</li>
 *   <li>action {@code realtalk_say: {phrase, vars?}} → templated in-dialogue line</li>
 *   <li>action {@code realtalk_gossip_say: {types?, max_age?, phrase_prefix?}} → tells the next
 *       untold gossip event and marks it told</li>
 * </ul>
 */
public final class RealTalkMcaRegistrar {

    private RealTalkMcaRegistrar() {
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
    }

    public static void register() {
        // --- Conditions (dialogue JSON shares the gift-predicate condition registry) ---

        GiftPredicate.register("realtalk_enabled",
                (json, name) -> SafeParse.orNull("realtalk_enabled", json, json::getAsString),
                feature -> (villager, stack, player) -> {
                    try {
                        return feature != null && McaRealTalkConfig.isFeatureEnabled(feature) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaRealTalk.LOGGER.debug("realtalk_enabled({}) failed; defaulting 0", feature, t);
                        return 0.0f;
                    }
                });

        GiftPredicate.register("realtalk_disabled",
                (json, name) -> SafeParse.orNull("realtalk_disabled", json, json::getAsString),
                feature -> (villager, stack, player) -> {
                    try {
                        return feature != null && !McaRealTalkConfig.isFeatureEnabled(feature) ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaRealTalk.LOGGER.debug("realtalk_disabled({}) failed; defaulting 0", feature, t);
                        return 0.0f;
                    }
                });

        GiftPredicate.register("realtalk_gossip",
                (json, name) -> SafeParse.orNull("realtalk_gossip", json,
                        () -> GossipQuery.fromJson(json.getAsJsonObject())),
                query -> (villager, stack, player) -> {
                    try {
                        return query != null && GossipConditionLogic.hasUntoldGossip(query, villager, player)
                                ? 1.0f : 0.0f;
                    } catch (Throwable t) {
                        McaRealTalk.LOGGER.debug("realtalk_gossip condition failed; defaulting 0", t);
                        return 0.0f;
                    }
                });

        // --- Actions ---

        Actions.register("realtalk_record",
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
                        McaRealTalk.LOGGER.debug("realtalk_record failed; ignoring", t);
                    }
                });

        Actions.register("realtalk_say",
                (json, name) -> SafeParse.orNull("realtalk_say", json,
                        () -> SayDirective.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive != null) {
                            RealTalkSay.trigger(directive, villager, player);
                        }
                    } catch (Throwable t) {
                        McaRealTalk.LOGGER.debug("realtalk_say failed; ignoring", t);
                    }
                });

        Actions.register("realtalk_gossip_say",
                (json, name) -> SafeParse.orNull("realtalk_gossip_say", json,
                        () -> GossipSayDirective.fromJson(json.getAsJsonObject())),
                directive -> (villager, player) -> {
                    try {
                        if (directive != null) {
                            GossipConditionLogic.tellNextGossip(directive, villager, player);
                        }
                    } catch (Throwable t) {
                        McaRealTalk.LOGGER.debug("realtalk_gossip_say failed; ignoring", t);
                    }
                });

        McaRealTalk.LOGGER.info("Registered dialogue conditions realtalk_enabled/realtalk_disabled/realtalk_gossip "
                + "and actions realtalk_record/realtalk_say/realtalk_gossip_say");
    }
}
