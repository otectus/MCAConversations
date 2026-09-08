package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.EngagementPolicy;
import dev.otectus.mcaconversations.locale.LineVoice;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

/**
 * Renders a villager's dialogue line as a chat message and delivers it — the whisper is the proven
 * chat-safe path already used by {@code McaCompat.sayInDialogue}'s fallback ({@code sendSystemMessage},
 * an unsigned system message, so no 1.19+ chat-signing implications). The MCA {@link Component} is
 * forwarded intact (never flattened to a string) so client-side lang, personality overlay, and template
 * args survive. One thing is decided here rather than left to the client: which {@code /N} variant of a
 * pooled line is spoken, because a reply that reaches several players must not become several different
 * sentences (see {@link dev.otectus.mcaconversations.locale.VariantPools}). Delivery is deferred through
 * {@link ChatModeScheduler} for a humanized latency.
 */
public final class ChatDelivery {

    private ChatDelivery() {
    }

    /** Schedules the villager's (already fully-resolved) line for delivery to {@code player}. */
    public static void villagerSays(Entity villager, ServerPlayer player, Component line) {
        villagerSays(villager, player, line, 0);
    }

    /**
     * As {@link #villagerSays(Entity, ServerPlayer, Component)} but adds {@code extraDelayTicks} on top
     * of the humanized reply delay — the stagger offset for ambient multi-responder replies (spec §12.3).
     */
    public static void villagerSays(Entity villager, ServerPlayer player, Component line, int extraDelayTicks) {
        villagerSays(villager, player, line, extraDelayTicks, null);
    }

    /**
     * Full form: {@code feedback} (nullable) is the redirect scope whose {@link
     * ChatModeSession.Scope#heartsDelta} — written after {@code selectAnswer} returns — is appended as
     * a subtle {@code (+2 ♥)} suffix at <em>delivery</em> time, speaker-visible only.
     */
    static void villagerSays(Entity villager, ServerPlayer player, Component line, int extraDelayTicks,
                             ChatModeSession.Scope feedback) {
        McaConversationsConfig.Common cfg = McaConversationsConfig.COMMON;
        String name = McaCompat.getVillagerName(villager).filter(n -> !n.isBlank()).orElse("Villager");
        MutableComponent coloredName = Component.literal(name).withStyle(style -> style
                .withColor(0xFFC34D)
                .withBold(true));
        // Choose the pooled variant here, once, rather than letting every recipient's client roll
        // its own — see VariantPools. Also yields the sentence length the delay is scaled from.
        Voiced voiced = voice(line, villager, player);
        MutableComponent rendered = applyFormat(cfg.chatModeMessageFormat.get(), coloredName, voiced.line());

        MinecraftServer server = player.getServer();
        long now = server != null ? server.overworld().getGameTime() : 0L;
        int delay = ChatModeScheduler.computeDelayTicks(cfg.chatModeReplyDelayTicks.get(), voiced.length());
        delay += Math.max(0, extraDelayTicks);
        dev.otectus.mcaconversations.conversation.ConversationSession conversation =
                dev.otectus.mcaconversations.conversation.ConversationSessions.raw(player.getUUID()).orElse(null);
        dev.otectus.mcaconversations.history.PrivacyLevel privacy = conversation == null ? null
                : conversation.plan().flatMap(dev.otectus.mcaconversations.scene.ConversationPlan::episodeId)
                        .flatMap(id -> dev.otectus.mcaconversations.history.History.of(villager)
                                .flatMap(history -> history.episode(id)))
                        .map(dev.otectus.mcaconversations.history.EpisodeRecord::privacy).orElse(null);
        boolean publicReplies = cfg.chatModePublicReplies.get() && mayBroadcast(privacy,
                conversation == null ? null : conversation.lastNpcAct().orElse(null));
        // Public replies travel as far as an overheard player message (the addressed radius), so a
        // bystander hears whole conversations — not the question without the answer.
        double radius = McaConversationsConfig.chatModeAddressedRadius();

        int lineIndex = feedback == null ? 0 : ++feedback.linesScheduled;
        ChatModeScheduler.scheduleOrdered(player.getUUID(), now + delay,
                () -> deliver(villager, player, rendered, publicReplies, radius, feedback, lineIndex));
    }

    /**
     * Delivers a scheduled line, or drops it. Returns {@code true} iff the line was actually spoken
     * to the speaker: a drop writes no state at all — no consumed hearts suffix, no consumed options
     * block — so the exchange still owes the player its feedback if the pair comes back together.
     * Bystanders are gated by the same policy one at a time, and a bystander who has walked off only
     * misses the overheard copy; the speaker's own delivery is what the result reports.
     */
    private static boolean deliver(Entity villager, ServerPlayer speaker, Component rendered,
                                   boolean publicReplies, double radius, ChatModeSession.Scope feedback,
                                   int lineIndex) {
        EngagementPolicy.Verdict verdict = EngagementPolicy.evaluate(speaker, villager, radius * radius);
        if (!verdict.ok()) {
            // Routine: players walk away mid-sentence. Never a warning.
            McaConversations.LOGGER.debug("chat-mode reply dropped before delivery: {}", verdict);
            return false;
        }
        boolean lastLine = feedback != null && lineIndex == feedback.linesScheduled;
        String suffix = lastLine ? heartsSuffix(feedback.heartsDelta) : "";
        boolean hasOptions = lastLine && feedback.options != null
                && dev.otectus.mcaconversations.conversation.ConversationSessions.raw(speaker.getUUID())
                        .flatMap(dev.otectus.mcaconversations.conversation.ConversationSession::currentOffer)
                        .filter(offer -> !offer.consumed() && offer.revision() == feedback.optionsRevision).isPresent();
        Component forSpeaker = rendered;
        if (!suffix.isEmpty() || hasOptions) {
            MutableComponent personal = rendered.copy();
            if (!suffix.isEmpty()) {
                feedback.heartsDelta = 0; // consume: an exchange shows its feedback exactly once
                personal.append(Component.literal(suffix)
                        .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
            }
            if (hasOptions) {
                // The choices belong to the player being asked, never to bystanders overhearing.
                personal.append(Component.literal("\n")).append(feedback.options);
                feedback.options = null; // consume: shown once, under the line that prompted them
            }
            forSpeaker = personal;
        }
        speaker.sendSystemMessage(forSpeaker);
        if (publicReplies && villager.level() instanceof ServerLevel level) {
            double r2 = radius * radius;
            for (ServerPlayer other : level.players()) {
                // Same policy the speaker was gated by, so an overhearing player is refused for the
                // same stated reason rather than by a second, differently-worded distance test.
                if (other != speaker && EngagementPolicy.evaluate(other, villager, r2).ok()) {
                    other.sendSystemMessage(rendered); // relationship feedback is personal — speaker only
                }
            }
        }
        return true;
    }

    /** Public-chat configuration never promotes a recorded confidence or personal disclosure. */
    static boolean mayBroadcast(dev.otectus.mcaconversations.history.PrivacyLevel privacy,
                                dev.otectus.mcaconversations.conversation.NpcSpeechAct act) {
        return (privacy == null || privacy == dev.otectus.mcaconversations.history.PrivacyLevel.PUBLIC)
                && act != dev.otectus.mcaconversations.conversation.NpcSpeechAct.DISCLOSE
                && act != dev.otectus.mcaconversations.conversation.NpcSpeechAct.DISCLOSE_PROBLEM;
    }

    /**
     * Median length of a pooled dialogue line in our own {@code en_us} (58 characters). Stands in for
     * the real length whenever the component cannot be resolved server-side, which on a dedicated
     * server is every translatable line — {@code assets/} is never mounted there, so
     * {@link Component#getString()} hands back the raw lang key instead of a sentence.
     */
    static final int NOMINAL_LINE_LENGTH = 58;

    /** A line with its pooled variant already chosen, and the sentence length the delay scales from. */
    record Voiced(Component line, int length) {
    }

    /**
     * Picks the pooled variant for this line, and measures the sentence that was picked.
     *
     * <p>The choice itself belongs to {@link LineVoice}: it is the same decision the interaction
     * screen needs, it has to remember what this villager last said to this player, and it has to
     * come out the same for the speaker and for every bystander inside the addressed radius. All
     * that is left here is the one thing that is chat's own business — how long to spend pretending
     * to type it.
     */
    private static Voiced voice(Component line, Entity villager, ServerPlayer player) {
        LineVoice.Voiced voiced = LineVoice.pin(line, villager, player);
        int length = voiced.length() > 0 ? voiced.length() : typedLength(voiced.line().getString());
        return new Voiced(voiced.line(), length);
    }

    /**
     * Pure: the length to scale the reply delay by, given whatever {@code getString()} produced. A
     * still-unresolved lang key is not a sentence — measuring it would make the delay track key length
     * instead of typing time — so those fall back to {@link #NOMINAL_LINE_LENGTH}.
     */
    static int typedLength(String resolved) {
        if (resolved == null || resolved.isEmpty()
                || resolved.charAt(0) == '#' || resolved.startsWith("dialogue.")) {
            return NOMINAL_LINE_LENGTH;
        }
        return resolved.length();
    }

    /** Subtle relationship feedback: {@code " (+2 ♥)"} / {@code " (−2 ♥)"}; empty for no change. */
    static String heartsSuffix(int delta) {
        if (delta == 0) {
            return "";
        }
        return delta > 0 ? " (+" + delta + " ♥)" : " (−" + (-delta) + " ♥)";
    }

    /**
     * Substitutes {@code %1$s} (villager name) and {@code %2$s} (the line Component) into the configured
     * template while keeping both as real Components — {@code String.format} can't be used because the
     * line must not be flattened.
     */
    static MutableComponent applyFormat(String template, Component name, Component line) {
        MutableComponent out = Component.empty();
        int i = 0;
        int len = template.length();
        StringBuilder literal = new StringBuilder();
        while (i < len) {
            if (template.startsWith("%1$s", i)) {
                flush(out, literal);
                out.append(name);
                i += 4;
            } else if (template.startsWith("%2$s", i)) {
                flush(out, literal);
                out.append(line);
                i += 4;
            } else {
                literal.append(template.charAt(i));
                i++;
            }
        }
        flush(out, literal);
        return out;
    }

    private static void flush(MutableComponent out, StringBuilder literal) {
        if (literal.length() > 0) {
            out.append(Component.literal(literal.toString()));
            literal.setLength(0);
        }
    }
}
