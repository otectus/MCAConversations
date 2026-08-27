package dev.otectus.mcaconversations.chat.group;

import dev.otectus.mcaconversations.McaConversationsConfig;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * One lead villager, at most two people joining in, and a fixed turn order (spec §11.6).
 *
 * <p>The hard cap is part of the scene rather than a tuning knob. Three speakers is the point at
 * which a group exchange stops being legible in a chat window: a fourth line arrives while the player
 * is still reading the second, and the conversation reads as noise even when every line is correct.
 * {@code maxSpeakers} may be lowered in the config and is clamped to the cap here regardless.
 *
 * <p>The session refuses more than it accepts, and each refusal is a specific failure it exists to
 * prevent: a villager talking to themselves, one villager interjecting twice, an interjection with no
 * prior line, and a fourth voice.
 */
public final class GroupConversationSession {

    /** The hard ceiling, including the lead. Not configurable upward. */
    public static final int MAX_SPEAKERS = 3;

    private final UUID lead;
    private final String leadBeat;
    private final List<GroupInterjection> interjections = new ArrayList<>();
    private final Set<UUID> spoken = new LinkedHashSet<>();

    public GroupConversationSession(UUID lead, String leadBeat) {
        this.lead = lead;
        this.leadBeat = leadBeat == null ? "" : leadBeat.trim().toLowerCase(java.util.Locale.ROOT);
        if (lead != null) {
            spoken.add(lead);
        }
    }

    public UUID lead() {
        return lead;
    }

    public String leadBeat() {
        return leadBeat;
    }

    /** Speakers allowed in this session, including the lead. */
    public static int speakerCap() {
        int configured = McaConversationsConfig.dynamicInt(
                McaConversationsConfig.COMMON.groupMaxSpeakers, MAX_SPEAKERS);
        return Math.max(1, Math.min(MAX_SPEAKERS, configured));
    }

    public int speakers() {
        return spoken.size();
    }

    public boolean isFull() {
        return spoken.size() >= speakerCap();
    }

    /** The interjections accepted so far, in turn order. */
    public List<GroupInterjection> interjections() {
        List<GroupInterjection> ordered = new ArrayList<>(interjections);
        ordered.sort((a, b) -> Integer.compare(a.stagger(), b.stagger()));
        return List.copyOf(ordered);
    }

    /**
     * Offers an interjection to the session.
     *
     * @return true when it was accepted and will be spoken
     */
    public boolean offer(GroupInterjection interjection) {
        if (interjection == null || !interjection.isWellFormed()) {
            return false;
        }
        if (lead == null || leadBeat.isEmpty()) {
            return false;
        }
        if (!leadBeat.equals(interjection.respondsTo())) {
            // The contract is relative to the *prior line*. An interjection answering some other beat
            // is a line that merely happened to be said next, which is the thing §11.6 forbids.
            return false;
        }
        if (spoken.contains(interjection.speaker())) {
            return false;
        }
        if (isFull()) {
            return false;
        }
        spoken.add(interjection.speaker());
        interjections.add(interjection);
        return true;
    }

    /**
     * The one interjection to keep, when several are on offer and there is room for one.
     *
     * <p>A boundary goes first: somebody saying "that isn't yours to tell" is about whether the
     * conversation should continue at all, and hearing it after two people have already added detail
     * to the thing is worse than not hearing it. Otherwise the earliest turn wins, which keeps the
     * choice stable rather than dependent on collection order.
     */
    public static Optional<GroupInterjection> best(List<GroupInterjection> offers) {
        if (offers == null || offers.isEmpty()) {
            return Optional.empty();
        }
        GroupInterjection best = null;
        for (GroupInterjection offer : offers) {
            if (offer == null || !offer.isWellFormed()) {
                continue;
            }
            if (best == null
                    || (offer.shape().isBoundary() && !best.shape().isBoundary())
                    || (offer.shape().isBoundary() == best.shape().isBoundary()
                            && offer.stagger() < best.stagger())) {
                best = offer;
            }
        }
        return Optional.ofNullable(best);
    }
}
