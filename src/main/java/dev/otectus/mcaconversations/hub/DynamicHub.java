package dev.otectus.mcaconversations.hub;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.History;
import dev.otectus.mcaconversations.history.PairHistory;
import dev.otectus.mcaconversations.history.PrivacyLevel;
import dev.otectus.mcaconversations.history.SharedThreadRecord;
import dev.otectus.mcaconversations.history.VillagerHistory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Works out which contextual entries the hub should show this time (spec §14.2, §14.3).
 *
 * <p>Three questions, answered from records that already exist rather than from a fresh selection
 * pass, because the hub is drawn on a menu click and the plan's performance rule is explicit that
 * nothing expensive may run there:
 *
 * <ol>
 *   <li><b>Where were we?</b> — the highest-priority resumable thread.</li>
 *   <li><b>What's on your mind?</b> — the villager's most salient live situation that the player has
 *       <em>not</em> been told about. Its label says nothing at all, because by construction the
 *       player has no way of knowing what it is.</li>
 *   <li><b>Ask about…</b> — the most salient situation the player <em>has</em> been told about, so
 *       the label may name its domain without telling them anything new.</li>
 * </ol>
 *
 * <h2>What can never appear</h2>
 *
 * <p>Anything the villager would not repeat. A situation recorded {@code speaker_only} is not offered
 * as a menu entry even to the person it was disclosed to, because a button is a standing offer and a
 * confidence is not. Everything more specific than a domain stays inside the scene, which has its own
 * conditions and its own privacy to answer to.
 */
public final class DynamicHub {

    /** player -> the hub they were last shown, so the mixin and the router agree on one answer. */
    private static final Map<UUID, HubPlan> SHOWING = new ConcurrentHashMap<>();

    /** Bound on remembered hubs, so a long-running server cannot accumulate players forever. */
    private static final int MAX_TRACKED_PLAYERS = 1024;

    private DynamicHub() {
    }

    /** How many dynamic entries this server allows. Zero reproduces the 1.4.0 hub exactly. */
    public static int slotBudget() {
        return Math.max(0, Math.min(HubPlan.MAX_SLOTS, McaConversationsConfig.dynamicInt(
                McaConversationsConfig.COMMON.dynamicTopicSlots, HubPlan.MAX_SLOTS)));
    }

    /**
     * Computes and remembers the hub for this player and villager.
     *
     * <p>Called when the hub is about to be drawn. Never throws: a hub that cannot be planned shows
     * the six fixed categories, which is exactly the 1.4.0 menu and never a broken one.
     */
    public static HubPlan open(Entity villager, ServerPlayer player, long today) {
        if (player == null) {
            return HubPlan.EMPTY;
        }
        HubPlan plan = HubPlan.EMPTY;
        try {
            if (McaConversationsConfig.dynamicFeature("dynamic", false) && slotBudget() > 0
                    && villager != null) {
                plan = planFor(villager, player, today);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("dynamic hub planning failed; showing the fixed hub", t);
            plan = HubPlan.EMPTY;
        }
        remember(player.getUUID(), plan);
        return plan;
    }

    /** The hub this player is currently being shown. */
    public static HubPlan showing(UUID playerId) {
        if (playerId == null) {
            return HubPlan.EMPTY;
        }
        return SHOWING.getOrDefault(playerId, HubPlan.EMPTY);
    }

    /** Forgets a player's hub, on logout or when the conversation ends. */
    public static void clear(UUID playerId) {
        if (playerId != null) {
            SHOWING.remove(playerId);
        }
    }

    /** Test seam: publish a hub without a world. */
    public static void showForTesting(UUID playerId, HubPlan plan) {
        remember(playerId, plan == null ? HubPlan.EMPTY : plan);
    }

    /**
     * The three candidates, from records that already exist.
     *
     * <p>Package-visible and world-free below the two history reads, so the rules can be exercised
     * without a server.
     */
    static HubPlan planFor(Entity villager, ServerPlayer player, long today) {
        Optional<VillagerHistory> history = History.of(villager);
        if (history.isEmpty()) {
            return HubPlan.EMPTY;
        }
        Optional<PairHistory> pair = history.get().peekPair(player.getUUID());
        List<EpisodeRecord> live = history.get().liveEpisodes(today);
        return build(pair.map(record -> record.resumable(today)).orElse(List.of()),
                live, player.getUUID(), slotBudget());
    }

    /**
     * The rules themselves.
     *
     * @param resumable ready threads, highest priority first
     * @param live      the villager's live episodes, most salient first
     * @param player    who is being spoken to, so "have they been told" can be answered
     * @param budget    how many entries this server allows
     */
    static HubPlan build(List<SharedThreadRecord> resumable, List<EpisodeRecord> live,
                         UUID player, int budget) {
        if (budget <= 0) {
            return HubPlan.EMPTY;
        }
        List<HubSlot> slots = new ArrayList<>(HubPlan.MAX_SLOTS);

        for (SharedThreadRecord thread : resumable) {
            if (!offerable(thread.privacy())) {
                continue;
            }
            Optional<HubSlot> slot = slotOf(HubSlot.Kind.CONTINUE, thread.topic());
            if (slot.isPresent()) {
                slots.add(slot.get());
                break;
            }
        }

        for (EpisodeRecord episode : live) {
            if (!offerable(episode.privacy()) || episode.isKnownTo(player)) {
                continue;
            }
            Optional<HubSlot> slot = slotOf(HubSlot.Kind.MIND, topicOf(episode));
            if (slot.isPresent()) {
                slots.add(slot.get());
                break;
            }
        }

        for (EpisodeRecord episode : live) {
            if (!offerable(episode.privacy()) || !episode.isKnownTo(player)) {
                continue;
            }
            Optional<HubSlot> slot = slotOf(HubSlot.Kind.ASK, topicOf(episode));
            if (slot.isPresent()) {
                slots.add(slot.get());
                break;
            }
        }

        return new HubPlan(slots.size() <= budget ? slots : slots.subList(0, budget));
    }

    /**
     * True when a fact at this level may be advertised as a button.
     *
     * <p>A menu entry is a standing offer, which is a different thing from a villager choosing to
     * disclose something in the middle of a conversation. Anything they would not repeat is not
     * offered here at all, even to the person it concerns.
     */
    private static boolean offerable(PrivacyLevel privacy) {
        return privacy != null && privacy != PrivacyLevel.SPEAKER_ONLY;
    }

    /** The topic an episode belongs to: the first segment of its subject. */
    static String topicOf(EpisodeRecord episode) {
        String subject = episode == null ? "" : episode.subject();
        int dot = subject.indexOf('.');
        return dot > 0 ? subject.substring(0, dot) : subject;
    }

    private static Optional<HubSlot> slotOf(HubSlot.Kind kind, String topic) {
        String normalized = topic == null ? "" : topic.trim().toLowerCase(Locale.ROOT);
        return HubDomain.ofTopic(normalized)
                .map(domain -> new HubSlot(kind, domain, normalized))
                .filter(HubSlot::isWellFormed);
    }

    private static void remember(UUID playerId, HubPlan plan) {
        if (playerId == null) {
            return;
        }
        if (SHOWING.size() >= MAX_TRACKED_PLAYERS) {
            SHOWING.clear();
        }
        if (plan.isEmpty()) {
            SHOWING.remove(playerId);
        } else {
            SHOWING.put(playerId, plan);
        }
    }
}
