package dev.otectus.mcaconversations.gossip;

import dev.otectus.mcaconversations.compat.ReputationBridge;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * One tellable story, whichever source it came from (spec §30.4): Conversations' own gossip log, or
 * an incident MCA: Reputation says this villager knows about.
 *
 * <p>This is the normalized shape the §30.4 merge runs on. Both sources reduce to the same four
 * facts — an identity to hang the told-memory off, a creation time to order by, a phrase key, and at
 * most {@link ReputationBridge.GossipCandidate#MAX_ARGUMENTS} arguments — so the condition and the
 * say action can share one deterministic selection and can never disagree about what gets told next.
 *
 * <p>Pure by design: no Minecraft server types, so {@code NormalizedGossipTest} exercises the
 * ordering and tie-breaks with nothing running.
 */
public record NormalizedGossip(UUID toldId, long createdGameTime,
                               @Nullable GossipEvent nativeEvent,
                               @Nullable String externalPhraseKey,
                               List<Component> externalArguments) {

    public NormalizedGossip {
        externalArguments = externalArguments == null ? List.of()
                : List.copyOf(externalArguments.subList(0,
                        Math.min(externalArguments.size(), ReputationBridge.GossipCandidate.MAX_ARGUMENTS)));
    }

    public static NormalizedGossip ofNative(GossipEvent event) {
        return new NormalizedGossip(event.id(), event.created(), event, null, List.of());
    }

    public static NormalizedGossip ofExternal(ReputationBridge.GossipCandidate candidate) {
        return new NormalizedGossip(candidate.alreadyToldId(), candidate.createdGameTime(), null,
                candidate.phraseKey(), candidate.arguments());
    }

    public boolean isExternal() {
        return nativeEvent == null;
    }

    /**
     * The dialogue phrase, minus MCA's {@code dialogue.} prefix. Native events keep their authored
     * prefix + type shape; external candidates carry their own key, which Conversations renders
     * through the same personality-overlay pipeline with its own {@code dialogue.mcareputation.…}
     * lang entries — Reputation supplies the fact, Conversations the voice (§30.4).
     */
    public String phraseKey(String nativePhrasePrefix) {
        return isExternal()
                ? externalPhraseKey
                : nativePhrasePrefix + "." + nativeEvent.type().jsonName();
    }

    /** The phrase arguments, capped at four either way (§30.4). */
    public Object[] arguments() {
        if (isExternal()) {
            return externalArguments.toArray();
        }
        return new Object[] {Component.literal(nativeEvent.aName()), Component.literal(nativeEvent.bName())};
    }

    /**
     * §30.4's selection rule: newest by creation time, told-id string as the stable tiebreak so two
     * evaluations of the same state always pick the same story.
     */
    public static Optional<NormalizedGossip> newest(List<NormalizedGossip> candidates) {
        return candidates.stream().max(
                Comparator.comparingLong(NormalizedGossip::createdGameTime)
                        .thenComparing(gossip -> gossip.toldId().toString(), Comparator.reverseOrder()));
    }
}