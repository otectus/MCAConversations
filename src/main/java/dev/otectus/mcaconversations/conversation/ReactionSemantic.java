package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.check.CheckTier;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * What a conversation outcome looked like from outside, as the heart-neutral reaction a villager may
 * play for it (Townstead spec §12.3). Each semantic names one reaction this mod ships under
 * {@code data/mcaconversations/townstead/reactions/conversation_<key>.json}.
 */
public enum ReactionSemantic {
    GREETING, WARM, AMUSED, ACKNOWLEDGE, GRATEFUL, DISCLOSURE, BOUNDARY, AWKWARD, REBUFF, HURT, REPAIR,
    FAREWELL, NEWS;

    public static final String NAMESPACE = "mcaconversations";
    private static final int MAX_TAG_SEGMENT = 64;

    public String key() {
        return name().toLowerCase(Locale.ROOT);
    }

    /** The shipped reaction for this semantic, as a {@code namespace:path} id. */
    public String reactionId() {
        return NAMESPACE + ":conversation_" + key();
    }

    public static Optional<ReactionSemantic> byKey(String raw) {
        if (raw == null) {
            return Optional.empty();
        }
        for (ReactionSemantic semantic : values()) {
            if (semantic.key().equals(raw.trim().toLowerCase(Locale.ROOT))) {
                return Optional.of(semantic);
            }
        }
        return Optional.empty();
    }

    /**
     * The reaction a reply earned when its content named none. Only a reply with a visible effect gets
     * one — an ordinary accepted line plays nothing — so a long conversation is not a pantomime.
     *
     * <p>A resolved check speaks first, then the beat's outcome, then the heart change.
     */
    public static Optional<ReactionSemantic> derive(CheckTier tier, OutcomeFamily outcome, StanceFamily stance,
                                                    NpcSpeechAct act, int measuredHearts) {
        if (outcome != null && outcome.isRupture()) {
            return Optional.of(switch (outcome) {
                case HURT -> HURT;
                case BOUNDARY_CLOSED -> BOUNDARY;
                default -> REBUFF;
            });
        }
        if (tier == CheckTier.REBUFF) {
            return Optional.of(REBUFF);
        }
        if (tier == CheckTier.PARTIAL || outcome == OutcomeFamily.MISUNDERSTOOD || outcome == OutcomeFamily.QUALIFIED) {
            return Optional.of(AWKWARD);
        }
        boolean landed = tier == CheckTier.CRIT || tier == CheckTier.SUCCESS
                || outcome == OutcomeFamily.APPRECIATED || outcome == OutcomeFamily.ENGAGED;
        if (landed) {
            if (act == NpcSpeechAct.DISCLOSE || act == NpcSpeechAct.DISCLOSE_PROBLEM) {
                return Optional.of(DISCLOSURE);
            }
            if (stance != null) {
                switch (stance) {
                    case HUMOR -> {
                        return Optional.of(AMUSED);
                    }
                    case EMPATHY, ENCOURAGEMENT -> {
                        return Optional.of(ACKNOWLEDGE);
                    }
                    case PRACTICAL_HELP -> {
                        return Optional.of(GRATEFUL);
                    }
                    default -> {
                    }
                }
            }
            return Optional.of(WARM);
        }
        if (measuredHearts < 0) {
            return Optional.of(HURT);
        }
        if (measuredHearts > 0) {
            return Optional.of(WARM);
        }
        return Optional.empty();
    }

    /**
     * The tags a reaction is fired with (spec §12.3). Townstead matches a reaction's
     * {@code required_tags} against exactly this set, so every authored id is sanitised first.
     */
    public static Set<String> tags(String topic, StanceFamily stance, CheckTier tier, ConversationSession.Frontend frontend,
                                   int measuredHearts, String semantic) {
        Set<String> tags = new LinkedHashSet<>();
        if (topic != null && !topic.isBlank()) {
            tags.add(NAMESPACE + ":topic/" + sanitize(topic));
        }
        if (stance != null) {
            tags.add(NAMESPACE + ":stance/" + stance.key());
        }
        if (tier != null) {
            tags.add(NAMESPACE + ":outcome/" + tier.name().toLowerCase(Locale.ROOT));
        }
        tags.add(NAMESPACE + ":frontend/" + (frontend == ConversationSession.Frontend.CHAT ? "chat" : "gui"));
        tags.add(NAMESPACE + ":heart/" + (measuredHearts > 0 ? "increased" : measuredHearts < 0 ? "decreased" : "unchanged"));
        if (semantic != null && !semantic.isBlank()) {
            tags.add(NAMESPACE + ":semantic/" + sanitize(semantic));
        }
        return tags;
    }

    /** Lowercase, {@code [a-z0-9_./-]} only, at most 64 characters; never empty. */
    public static String sanitize(String raw) {
        if (raw == null) {
            return "unknown";
        }
        StringBuilder out = new StringBuilder();
        for (char c : raw.trim().toLowerCase(Locale.ROOT).toCharArray()) {
            if (out.length() >= MAX_TAG_SEGMENT) {
                break;
            }
            out.append((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '_' || c == '.' || c == '/'
                    || c == '-' ? c : '_');
        }
        return out.length() == 0 ? "unknown" : out.toString();
    }
}
