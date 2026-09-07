package dev.otectus.mcaconversations.history;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.VillagerFinder;
import dev.otectus.mcaconversations.compat.McaCompat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * How a thing one villager knows becomes a thing another villager has heard (spec §16.4).
 *
 * <p>The plan sets eight conditions on propagation and every one of them is a refusal rather than a
 * feature. Taken together they say: a rumour may travel, it may not improve in the telling, it may
 * not escape the privacy it was told under, and it may not become a different event on the way.
 *
 * <h2>Where it runs</h2>
 *
 * <p>On the existing low-frequency village sweep — never a tick job of its own — and only among
 * villagers near an online player. That bound is deliberate and is not merely a performance
 * concession: a rumour that spread through empty chunks would be a simulation nobody could ever have
 * witnessed, and the first a player heard of it would be a villager referring to something that had
 * never happened in front of anyone. Rumours travel where the village is being watched.
 *
 * <h2>What travels and what does not</h2>
 *
 * <ul>
 *   <li>The <b>event id survives</b> every hop, so a later correction addresses the same event rather
 *       than a copy of it.</li>
 *   <li><b>Confidence falls</b> one step per hop and <b>salience</b> by a fixed amount, so a story
 *       that has been round the village three times is both less certain and less worth telling.</li>
 *   <li><b>Privacy travels with the fact.</b> A confidence does not become ordinary by being
 *       repeated, and anything the holder may not share does not move at all.</li>
 *   <li>Where the holder may describe but not name, <b>the participants are dropped</b> from the copy
 *       rather than carried and hidden — a name that is not in the record cannot leak from it.</li>
 *   <li><b>What the player said about themselves never travels</b> unless the player made it public.
 *       Permission has exactly one representation here, and it is the privacy level itself.</li>
 *   <li><b>Nothing is invented.</b> The copy is faithful; a distortion is authored or it does not
 *       exist.</li>
 * </ul>
 */
public final class RumourPropagation {

    /** Hops a story may travel from the person it happened to. Four tellings and it stops. */
    public static final int MAX_HOPS = 3;

    /** Salience lost per hop, so a thing that has been round the village stops being news. */
    public static final int SALIENCE_PER_HOP = 15;

    /** Salience below which a story is no longer worth passing on at all. */
    public static final int MINIMUM_INTERESTING_SALIENCE = 10;

    /** Villagers considered per player on one sweep. */
    private static final int MAX_LISTENERS_PER_PLAYER = 3;

    /** Stories moved on one sweep, across the whole server. */
    private static final int MAX_EDGES_PER_SWEEP = 4;

    /** How far a villager has to be from another to be within earshot of them. */
    private static final double EARSHOT = 8.0;

    private RumourPropagation() {
    }

    /**
     * Passes {@code episode} from {@code speaker} to {@code listener}, when every rule allows it.
     *
     * @return the copy the listener now holds, or empty when nothing was told and why is not the
     *         caller's business — every refusal here is ordinary
     */
    public static Optional<EpisodeRecord> tell(Entity speaker, Entity listener,
                                               EpisodeRecord episode, long today) {
        if (speaker == null || listener == null || episode == null) {
            return Optional.empty();
        }
        if (!mayTravel(episode, today)) {
            return Optional.empty();
        }
        EpisodeRecord known = History.of(listener).flatMap(history -> history.episode(episode.id())).orElse(null);
        if (!shouldTell(episode, known, listener.getUUID(), today)) {
            return Optional.empty();
        }
        EpisodeRecord copy = asHeardBy(episode, speaker.getUUID(), listener.getUUID(), today);
        return History.putEpisode(listener, copy) ? Optional.of(copy) : Optional.empty();
    }

    /** Receiver history is authoritative; the sender's witness list does not include earlier hops. */
    static boolean shouldTell(EpisodeRecord story, EpisodeRecord known, UUID listener, long today) {
        if (story == null || listener == null || !mayTravel(story, today)
                || story.updatedDay() > today) {
            return false;
        }
        if (known == null) {
            return !story.isKnownTo(listener);
        }
        // A copied witness list only says the listener heard an earlier version. It must not
        // suppress a resolution. The listener's own record, however, must never lose first-hand
        // evidence to a retelling, nor may somebody overwrite the event owner's authoritative copy.
        if (listener.equals(story.ownerVillager()) || known.source().isFirstHand()
                || !known.id().equals(story.id()) || !known.ownerVillager().equals(story.ownerVillager())
                || !known.kind().equals(story.kind()) || !known.subject().equals(story.subject())
                || story.updatedDay() < known.updatedDay()) {
            return false;
        }
        EpisodeRecord received = asHeardBy(story, story.ownerVillager(), listener, today);
        boolean sameState = known.state() == story.state();
        // A terminal outcome is monotonic. Hearsay must not reopen it or replace success with
        // failure, even when an old copy happens to carry a later day.
        if (known.state().isPast() && !sameState) {
            return false;
        }
        boolean sameClaim = sameState && known.payload().equals(received.payload())
                && known.participants().equals(received.participants());
        boolean correction = known.provenance().isCorrectable()
                && !story.provenance().isCorrectable()
                && !received.provenance().isCorrectable()
                && received.confidence().rank() <= known.confidence().rank();
        if (sameClaim) {
            // Witness lists, salience loss, a new teller, or another hop are not new information.
            return correction || received.confidence().rank() < known.confidence().rank();
        }
        if (known.state().isPast()) return sameState && correction;
        if (story.updatedDay() > known.updatedDay()) {
            return canFollow(known.state(), story.state());
        }
        // Day-only records cannot order ACTIVE <-> BLOCKED or two payload edits on the same day.
        // Accept only irreversible progress, or an explicit correction of an uncertain account.
        boolean progress = known.state() == EpisodeState.PLANNED && story.state() != EpisodeState.PLANNED
                || known.state().isLive() && story.state().isTerminal();
        return progress || sameState && correction;
    }

    private static boolean canFollow(EpisodeState before, EpisodeState after) {
        if (before == after || before.allows(after)) return true;
        // The listener may have missed intermediate states of this same event.
        return before == EpisodeState.PLANNED
                && (after == EpisodeState.BLOCKED || after.isPast());
    }

    /**
     * Whether this episode is one that may be passed on at all.
     *
     * <p>Public because the same question is asked before a group scene lets one villager corroborate
     * another's account: a bystander who may not repeat a thing may not confirm it either.
     */
    public static boolean mayTravel(EpisodeRecord episode, long today) {
        if (episode == null || today < episode.createdDay()) {
            return false;
        }
        Provenance provenance = episode.provenance();
        if (!provenance.maySpeak()) {
            return false;
        }
        if (provenance.hasTravelledFarEnough(MAX_HOPS)) {
            return false;
        }
        if (episode.salience() < MINIMUM_INTERESTING_SALIENCE) {
            return false;
        }
        if (episode.hasExpired(today)) {
            return false;
        }
        // What a player told a villager about themselves is theirs, and stays where they put it.
        // The exception is permission, and permission has exactly one representation: the fact has
        // been made public. That is what a player saying "you can tell people" means, and modelling
        // it as a flag beside the privacy level would allow the contradiction of a confidential fact
        // that may nonetheless be repeated.
        return provenance.confidence() != Confidence.SELF_REPORTED
                || provenance.privacy() == PrivacyLevel.PUBLIC;
    }

    /**
     * The same event as the listener would now hold it.
     *
     * <p>Not a new episode: the id, kind, subject, state and payload are the ones the event actually
     * has. What changes is the footing it is held on and how much it is worth repeating.
     */
    public static EpisodeRecord asHeardBy(EpisodeRecord episode, UUID speaker, UUID listener,
                                          long today) {
        Provenance heard = episode.provenance().afterHop(speaker);
        EpisodeRecord copy = episode
                .withProvenance(heard)
                .withSalience(Math.max(0, episode.salience() - SALIENCE_PER_HOP));
        if (!heard.mayName()) {
            // Described, not named. Dropping the participants is the only honest way to do that: a
            // name kept in the record and merely not spoken is a name one authoring mistake away
            // from being spoken.
            copy = copy.withoutParticipants();
        }
        return listener == null ? copy : copy.witnessedBy(listener);
    }

    /**
     * One pass of village talk, riding the low-frequency sweep.
     *
     * @return how many stories moved, for the debug command
     */
    public static int sweep(MinecraftServer server, long today) {
        if (server == null || !McaConversationsConfig.dynamicFeature("episodes", false)) {
            return 0;
        }
        int moved = 0;
        try {
            double radius = McaConversationsConfig.chatModeRadius();
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (moved >= MAX_EDGES_PER_SWEEP) {
                    break;
                }
                moved += sweepAround(player, radius, today, MAX_EDGES_PER_SWEEP - moved);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("rumour sweep failed; ignoring", t);
        }
        return moved;
    }

    private static int sweepAround(ServerPlayer player, double radius, long today, int budget) {
        List<VillagerFinder.VillagerCandidate> nearby = VillagerFinder.candidates(player, radius);
        if (nearby.size() < 2) {
            return 0;
        }
        int moved = 0;
        int considered = 0;
        for (VillagerFinder.VillagerCandidate speaker : nearby) {
            if (moved >= budget || considered >= MAX_LISTENERS_PER_PLAYER) {
                break;
            }
            considered++;
            List<EpisodeRecord> stories = tellableStories(speaker.entity(), today);
            if (stories.isEmpty()) {
                continue;
            }
            for (VillagerFinder.VillagerCandidate listener : nearby) {
                if (listener.entity() == speaker.entity()
                        || speaker.entity().distanceToSqr(listener.entity()) > EARSHOT * EARSHOT) {
                    continue;
                }
                VillagerHistory listenerHistory = History.of(listener.entity()).orElse(null);
                Optional<EpisodeRecord> story = nextForListener(stories, listenerHistory,
                        listener.entity().getUUID(), today);
                if (story.isPresent() && tell(speaker.entity(), listener.entity(), story.get(), today).isPresent()) {
                    moved++;
                    break;
                }
            }
        }
        return moved;
    }

    /** Retained resolutions are news too; a known high-salience opening must not hide them. */
    private static List<EpisodeRecord> tellableStories(Entity villager, long today) {
        if (!McaCompat.isMcaVillager(villager)) return List.of();
        return History.of(villager).map(history -> tellableStories(history, today)).orElse(List.of());
    }

    static List<EpisodeRecord> tellableStories(VillagerHistory history, long today) {
        if (history == null) return List.of();
        return history.episodes().stream()
                .filter(episode -> mayTravel(episode, today) && episode.updatedDay() <= today)
                .sorted(Comparator.comparingInt(EpisodeRecord::salience).reversed()
                        .thenComparing(Comparator.comparingLong(EpisodeRecord::updatedDay).reversed())
                        .thenComparing(EpisodeRecord::id))
                .limit(HistoryCaps.HARD_ACTIVE_EPISODES + HistoryCaps.HARD_RESOLVED_EPISODES)
                .toList();
    }

    static Optional<EpisodeRecord> nextForListener(List<EpisodeRecord> stories,
                                                   VillagerHistory history, UUID listener, long today) {
        return stories.stream().filter(story -> shouldTell(story,
                history == null ? null : history.episode(story.id()).orElse(null), listener, today))
                .findFirst();
    }
}
