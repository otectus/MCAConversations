package dev.otectus.mcaconversations.chat.group;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.ChatDelivery;
import dev.otectus.mcaconversations.chat.VillagerFinder;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.ConversationSession;
import dev.otectus.mcaconversations.conversation.ConversationSessions;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.History;
import dev.otectus.mcaconversations.history.KnowledgeSource;
import dev.otectus.mcaconversations.history.SocialRole;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Decides whether anybody nearby has standing to join in, and what they may say (spec §11.6).
 *
 * <p>Two halves, kept apart on purpose. {@link #shapesFor} is a pure function from an observed
 * relation to the shapes that relation permits — no world, no entities, no configuration — because
 * that is the part where a mistake produces a villager confirming something they could not know.
 * Everything else here is the plumbing that observes the relation and delivers the line.
 *
 * <p>The whole feature is off by default and chat-only, which is what the plan asks for a first
 * vertical slice. When it is on, at most one bystander joins any single exchange: the speaker cap is
 * three including the lead, and a group scene that used its whole budget on one line of the player's
 * conversation would leave nothing for the reply.
 */
public final class GroupDirector {

    /** How close a bystander has to be to have plausibly heard the line they are answering. */
    private static final double EARSHOT = 10.0;

    private GroupDirector() {
    }

    public static boolean enabled() {
        return McaConversationsConfig.dynamicFeature("group", false);
    }

    /**
     * The shapes this relation permits, most restrictive first.
     *
     * <p>Every entry is a licence rather than an intention: it says this bystander <em>could</em>
     * honestly say this, not that they will. Which one is spoken is settled by
     * {@link GroupConversationSession#best}, and whether anything is spoken at all is settled by the
     * caps above that.
     *
     * @param relation             what this bystander is to the speaker
     * @param aboutPublicEvent     true when the subject is something the village knows
     * @param speakerIsOversharing true when the line about to be answered spends a confidence that is
     *                             not the speaker's to spend
     */
    public static List<GroupShape> shapesFor(GroupRelation relation, boolean aboutPublicEvent,
                                             boolean speakerIsOversharing) {
        List<GroupShape> shapes = new ArrayList<>(3);
        if (relation == null) {
            return shapes;
        }
        KnowledgeSource knowledge = relation.knowledge().orElse(null);

        // A boundary comes first because it is about whether the conversation should go on at all.
        // It needs standing — somebody with no connection to the subject objecting on its behalf is
        // an interruption, not a boundary — which is what the knowledge check is doing here.
        if (speakerIsOversharing && knowledge != null
                && GroupShape.BYSTANDER_PRIVACY.acceptsKnowledge(knowledge)) {
            shapes.add(GroupShape.BYSTANDER_PRIVACY);
        }
        if (aboutPublicEvent && GroupShape.CORROBORATE.acceptsKnowledge(knowledge)) {
            shapes.add(GroupShape.CORROBORATE);
        }
        if (relation.sharesTrade() && GroupShape.COWORKER_DETAIL.acceptsKnowledge(knowledge)) {
            shapes.add(GroupShape.COWORKER_DETAIL);
        }
        if (relation.family() && GroupShape.FAMILY_REMEMBERS.acceptsKnowledge(knowledge)) {
            shapes.add(GroupShape.FAMILY_REMEMBERS);
        }
        // A preference needs no source, which is exactly why it is the only shape open to anybody and
        // exactly why it is confined to things nothing turns on.
        if (!aboutPublicEvent && !speakerIsOversharing) {
            shapes.add(GroupShape.FRIENDLY_DISAGREEMENT);
        }
        return List.copyOf(shapes);
    }

    /**
     * Lets at most one nearby villager answer the line {@code lead} has just spoken.
     *
     * @return the interjection that was spoken, when one was
     */
    public static Optional<GroupInterjection> maybeInterject(Entity lead, ServerPlayer player,
                                                             long now) {
        if (!enabled() || lead == null || player == null) {
            return Optional.empty();
        }
        try {
            Optional<ConversationSession> session = ConversationSessions.peek(player.getUUID(), now);
            Optional<String> leadBeat = session.flatMap(ConversationSession::currentBeatId);
            if (leadBeat.isEmpty()) {
                // No contracted beat means no prior line to answer, and §11.6 has no shape for that.
                return Optional.empty();
            }
            GroupConversationSession group = new GroupConversationSession(lead.getUUID(), leadBeat.get());
            if (group.isFull()) {
                return Optional.empty();
            }
            List<GroupInterjection> offers = offersFor(lead, player, leadBeat.get(), now);
            Optional<GroupInterjection> chosen = GroupConversationSession.best(offers);
            if (chosen.isEmpty() || !group.offer(chosen.get())) {
                return Optional.empty();
            }
            speak(chosen.get(), player, now);
            return chosen;
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("group interjection failed; ignoring", t);
            return Optional.empty();
        }
    }

    /** Every interjection anybody nearby could honestly offer against this line. */
    static List<GroupInterjection> offersFor(Entity lead, ServerPlayer player, String leadBeat,
                                             long now) {
        List<GroupInterjection> offers = new ArrayList<>();
        if (!(lead.level() instanceof ServerLevel level)) {
            return offers;
        }
        long today = level.getDayTime() / 24000L;
        Optional<EpisodeRecord> subject = History.liveEpisodes(lead, today).stream().findFirst();
        boolean aboutPublicEvent = subject
                .map(episode -> episode.privacy() == dev.otectus.mcaconversations.history.PrivacyLevel.PUBLIC)
                .orElse(false);
        boolean oversharing = subject.map(episode -> !episode.provenance().maySpeak()).orElse(false);

        for (VillagerFinder.VillagerCandidate candidate : VillagerFinder.candidates(player, EARSHOT)) {
            Entity bystander = candidate.entity();
            if (bystander == lead || !McaCompat.isMcaVillager(bystander)) {
                continue;
            }
            GroupRelation relation = observe(level, lead, bystander, subject.orElse(null));
            for (GroupShape shape : shapesFor(relation, aboutPublicEvent, oversharing)) {
                offers.add(new GroupInterjection(shape, bystander.getUUID(), leadBeat,
                        relation.knowledge().orElse(KnowledgeSource.UNKNOWN_RUMOR)));
            }
        }
        return offers;
    }

    /**
     * What {@code bystander} is to {@code lead}, from MCA's family tree and this mod's own records.
     *
     * <p>Kinship is read from MCA rather than kept here, per §16.1. The trade comparison is exact
     * rather than by archetype: a fisherman and a farmer are both outdoors and that is not the same
     * as knowing which batch of anything to use.
     */
    static GroupRelation observe(ServerLevel level, Entity lead, Entity bystander,
                                 EpisodeRecord subject) {
        UUID leadId = lead.getUUID();
        UUID bystanderId = bystander.getUUID();

        Set<UUID> siblings = McaCompat.getSiblings(level, leadId);
        boolean family = siblings.contains(bystanderId)
                || McaCompat.getChildren(level, leadId).contains(bystanderId)
                || McaCompat.getParents(level, leadId).contains(bystanderId)
                || McaCompat.getPartnerFromTree(level, leadId)
                        .map(bystanderId::equals).orElse(false);

        String leadTrade = McaCompat.getProfessionId(lead).orElse("");
        String bystanderTrade = McaCompat.getProfessionId(bystander).orElse("");
        boolean sharesTrade = !leadTrade.isBlank() && leadTrade.equals(bystanderTrade);
        if (!sharesTrade) {
            sharesTrade = History.of(bystander)
                    .map(history -> history.role(leadId, SocialRole.COWORKER).isPresent())
                    .orElse(false);
        }

        KnowledgeSource knowledge = null;
        if (subject != null) {
            if (subject.isKnownTo(bystanderId)) {
                knowledge = KnowledgeSource.WITNESSED;
            } else if (family) {
                knowledge = KnowledgeSource.FAMILY;
            } else if (sharesTrade) {
                knowledge = KnowledgeSource.COWORKER;
            } else if (subject.privacy() == dev.otectus.mcaconversations.history.PrivacyLevel.PUBLIC) {
                knowledge = KnowledgeSource.PUBLIC_NOTICE;
            }
        } else if (family) {
            knowledge = KnowledgeSource.FAMILY;
        } else if (sharesTrade) {
            knowledge = KnowledgeSource.COWORKER;
        }
        return GroupRelation.of(family, sharesTrade, knowledge);
    }

    private static void speak(GroupInterjection interjection, ServerPlayer player, long now) {
        if (!(player.level() instanceof ServerLevel level)) {
            return;
        }
        Entity speaker = level.getEntity(interjection.speaker());
        if (speaker == null) {
            // They walked off between being chosen and being asked to speak. Ordinary; say nothing.
            return;
        }
        Component line = McaCompat.getDialogueLine(speaker, player, interjection.sayPool())
                .map(component -> (Component) component)
                .orElseGet(() -> Component.translatable("dialogue." + interjection.sayPool(),
                        player.getDisplayName()));
        ChatDelivery.villagerSays(speaker, player, line, interjection.stagger());
    }
}
