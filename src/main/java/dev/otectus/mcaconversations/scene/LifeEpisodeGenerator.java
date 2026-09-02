package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.gossip.GossipConditionLogic;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.EpisodeTemplate;
import dev.otectus.mcaconversations.history.History;
import dev.otectus.mcaconversations.history.NarrativeCatalogLoader;
import dev.otectus.mcaconversations.history.NarrativeValue;
import dev.otectus.mcaconversations.history.PairHistory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * Gives a villager something going on that is not their job.
 *
 * <h2>Why a bootstrapper is needed at all</h2>
 *
 * <p>A scene that speaks about a situation requires the situation to already exist, and nothing in
 * the shipped content has ever created one — all 45 uses of the episode action are {@code advance}.
 * Work situations exist only because {@link WorkEpisodeGenerator} opens one the first time a player
 * asks a villager about their trade. Without the same bootstrap for anything else, a non-work family
 * would be authored, compiled, shipped, and never once selected: precisely the failure that left
 * seventeen scenes dark before 1.5.0.
 *
 * <h2>The world decides, not the button</h2>
 *
 * <p>The tempting shortcut is to let a conversation open the episode — the player picks a line and a
 * situation springs into being. That is rejected here. It would mean a villager acquiring a history
 * because somebody clicked, which is the "concrete past tense without provenance" rule the authoring
 * guide forbids, and it is how a mod ends up confidently recalling events that never happened.
 *
 * <p>So each domain has a real precondition, checked against records that exist for their own
 * reasons:
 *
 * <ul>
 *   <li><b>Village change</b> — the villager has a village event they have not told this player. The
 *       gossip log put it there because something actually happened in the village.</li>
 *   <li><b>Family life</b> — MCA says this villager has living close family. A villager with nobody
 *       does not acquire a household.</li>
 *   <li><b>Standing</b> — there is an unhealed rupture between these two. You cannot repair what was
 *       never broken.</li>
 *   <li><b>Shared history</b> — these two have a first-met day and enough of it behind them. Two
 *       strangers have no shared history to have.</li>
 * </ul>
 *
 * <p>Everything else follows {@link WorkEpisodeGenerator}'s rules exactly: resume before create, so a
 * life is continuous rather than re-rolled; the choice among eligible kinds is seeded on villager and
 * day, so asking twice in one day gets the same answer and two neighbours get different ones; and the
 * payload is authored tokens from the template's own pools, never a claim about the world.
 */
public final class LifeEpisodeGenerator {

    /**
     * Days these two must have known each other before there is a shared history to speak of.
     *
     * <p>A week. Short enough that a regular player reaches it quickly, long enough that "what we
     * have been through" is not offered to somebody met this morning.
     */
    static final long SHARED_HISTORY_AFTER_DAYS = 7L;

    private LifeEpisodeGenerator() {
    }

    /** Topics that own a non-work episode family, mapped to the kind prefix their templates use. */
    static String prefixFor(String topic) {
        if (topic == null) {
            return "";
        }
        return switch (topic) {
            case "village" -> "village.";
            case "life" -> "family.";
            case "standing" -> "standing.";
            case "shared_history" -> "shared.";
            default -> "";
        };
    }

    /**
     * Ensures this villager has a live episode for {@code topic}, opening one if the world supports
     * it.
     *
     * @return the episode now in force, or empty when this topic owns none or its precondition fails
     */
    public static Optional<EpisodeRecord> ensure(Entity villager, ServerPlayer player, String topic,
                                                 long today) {
        String prefix = prefixFor(topic);
        if (prefix.isEmpty() || !History.episodesEnabled() || villager == null || player == null) {
            return Optional.empty();
        }
        try {
            List<EpisodeTemplate> candidates = candidatesFor(prefix);
            if (candidates.isEmpty()) {
                return Optional.empty();
            }
            // Resume before create. A situation the player already knows about is the one that is
            // still going on; opening a second would make the villager's life restart every visit.
            for (EpisodeTemplate template : candidates) {
                Optional<EpisodeRecord> live = History.liveEpisode(villager, template.kind(), today);
                if (live.isPresent()) {
                    return live;
                }
            }
            if (!worldSupports(topic, villager, player, today)) {
                return Optional.empty();
            }
            EpisodeTemplate chosen = candidates.get((int) (seed(villager, today) % candidates.size()));
            return History.openEpisode(villager, chosen.kind(), payloadFor(topic, villager, player),
                    today);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("life episode generation failed for '{}'; ignoring", topic, t);
            return Optional.empty();
        }
    }

    /**
     * Whether anything has actually happened that this domain could be about.
     *
     * <p>Fails closed: an unreadable record is "no", so the worst case is a villager with nothing
     * going on rather than a villager with something invented.
     */
    static boolean worldSupports(String topic, Entity villager, ServerPlayer player, long today) {
        return switch (topic) {
            case "village" -> GossipConditionLogic.nextUntoldEventType(villager, player).isPresent();
            case "life" -> hasLivingFamily(villager);
            case "standing" -> History.pair(villager, player)
                    .flatMap(PairHistory::rupture)
                    .isPresent();
            case "shared_history" -> History.pair(villager, player)
                    .map(PairHistory::firstMetDay)
                    .filter(java.util.OptionalLong::isPresent)
                    .map(first -> today - first.getAsLong() >= SHARED_HISTORY_AFTER_DAYS)
                    .orElse(false);
            default -> false;
        };
    }

    /**
     * Whether MCA says this villager has anybody: a partner, or children.
     *
     * <p>Deliberately the two relations MCA can answer without a family-tree walk. A villager with
     * neither does not acquire a household, and the domain simply never opens for them.
     */
    private static boolean hasLivingFamily(Entity villager) {
        if (McaCompat.getPartnerUuid(villager).isPresent()) {
            return true;
        }
        return villager.level() instanceof net.minecraft.server.level.ServerLevel level
                && !McaCompat.getChildren(level, villager.getUUID()).isEmpty();
    }

    /**
     * Slots the world can fill, rather than the template's own pools.
     *
     * <p>Only village change has one: which event this is about is a fact the gossip log already
     * holds, and letting the template pick from a pool instead would mean a villager talking about a
     * wedding that had not happened. Every other domain's slots come from the authored pools, where
     * the only claim being made is a turn of phrase.
     */
    static Map<String, NarrativeValue> payloadFor(String topic, Entity villager, ServerPlayer player) {
        if (!"village".equals(topic)) {
            return Map.of();
        }
        return GossipConditionLogic.nextUntoldEventType(villager, player)
                .map(type -> Map.of("change", NarrativeValue.token("village_change_" + type)))
                .orElse(Map.of());
    }

    /**
     * Non-work episode kinds under this prefix, in a stable order.
     *
     * <p>A template that names professions belongs to a trade and is {@link WorkEpisodeGenerator}'s
     * business; excluding them here is what keeps the two generators from ever competing for the same
     * villager.
     */
    static List<EpisodeTemplate> candidatesFor(String prefix) {
        List<EpisodeTemplate> out = new ArrayList<>();
        for (EpisodeTemplate template : NarrativeCatalogLoader.active().episodes()) {
            if (template.professions().isEmpty()
                    && template.kind().toLowerCase(Locale.ROOT).startsWith(prefix)) {
                out.add(template);
            }
        }
        out.sort(Comparator.comparing(EpisodeTemplate::kind));
        return List.copyOf(out);
    }

    /** Villager and day, exactly as work does: asked twice today, the same thing is going on. */
    private static long seed(Entity villager, long today) {
        long hash = 0xcbf29ce484222325L;
        for (byte b : (villager.getUUID() + "/" + today).getBytes(StandardCharsets.UTF_8)) {
            hash ^= (b & 0xffL);
            hash *= 0x100000001b3L;
        }
        return hash & Long.MAX_VALUE;
    }
}
