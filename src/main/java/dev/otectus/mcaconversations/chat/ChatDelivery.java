package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.locale.VariantPools;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
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
        MutableComponent coloredName = Component.literal(name).withStyle(ChatFormatting.YELLOW);
        // Choose the pooled variant here, once, rather than letting every recipient's client roll
        // its own — see VariantPools. Also yields the sentence length the delay is scaled from.
        Voiced voiced = voice(line, villager);
        MutableComponent rendered = applyFormat(cfg.chatModeMessageFormat.get(), coloredName, voiced.line());

        MinecraftServer server = player.getServer();
        long now = server != null ? server.overworld().getGameTime() : 0L;
        int delay = ChatModeScheduler.computeDelayTicks(cfg.chatModeReplyDelayTicks.get(), voiced.length());
        delay += Math.max(0, extraDelayTicks);
        boolean publicReplies = cfg.chatModePublicReplies.get();
        // Public replies travel as far as an overheard player message (the addressed radius), so a
        // bystander hears whole conversations — not the question without the answer.
        double radius = cfg.chatModeAddressedRadius.get();

        ChatModeScheduler.schedule(now + delay,
                () -> deliver(villager, player, rendered, publicReplies, radius, feedback));
    }

    private static void deliver(Entity villager, ServerPlayer speaker, Component rendered,
                                boolean publicReplies, double radius, ChatModeSession.Scope feedback) {
        if (speaker.hasDisconnected()) {
            return;
        }
        String suffix = feedback == null ? "" : heartsSuffix(feedback.heartsDelta);
        boolean hasOptions = feedback != null && feedback.options != null;
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
                if (other != speaker && !other.hasDisconnected()
                        && other.distanceToSqr(villager) <= r2) {
                    other.sendSystemMessage(rendered); // relationship feedback is personal — speaker only
                }
            }
        }
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
     * Names one concrete {@code …/N} variant of a pooled line, so the speaker and every bystander
     * inside the addressed radius read the same sentence. (The interaction screen needs its own fix,
     * {@code VillagerMessageMixin}: there MCA renders one line from three separate components.)
     *
     * <p>MCA resolves {@code /N} pools on the client with a fresh random draw per component instance,
     * so an unpinned line arriving at three clients is three different sentences. Appending the index
     * to the marker-laden key is safe: MCA's {@code applyFallback} strips its {@code #G}/{@code #E}/
     * {@code #P}/{@code #T} tokens by dot-separated prefix and the pool regex is anchored at
     * {@code /[0-9]+$}, so the client sees a concrete key, finds no pool for it, and renders that one
     * line — through the personality overlay when it has one, which is what
     * {@link VariantPools#deliverablePoolSize} keeps the index inside.
     *
     * <p>Fails open: anything that is not a pooled translatable we own is passed through untouched and
     * keeps MCA's own behaviour.
     */
    private static Voiced voice(Component line, Entity villager) {
        try {
            if (line.getContents() instanceof TranslatableContents contents) {
                String key = contents.getKey();
                String base = stripMarkers(key);
                int pool = VariantPools.deliverablePoolSize(base,
                        McaCompat.getPersonality(villager).orElse(""));
                if (pool >= 2) {
                    int n = 1 + villager.level().getRandom().nextInt(pool);
                    MutableComponent pinned = Component.translatable(key + "/" + n, contents.getArgs())
                            .setStyle(line.getStyle());
                    line.getSiblings().forEach(pinned::append);
                    int length = VariantPools.variantLength(base, n);
                    return new Voiced(pinned, length > 0 ? length : NOMINAL_LINE_LENGTH);
                }
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("variant pinning failed; leaving MCA's per-client pick", t);
        }
        return new Voiced(line, typedLength(line.getString()));
    }

    /**
     * Pure: drops MCA's leading {@code #G…}/{@code #E…}/{@code #P…}/{@code #T…} marker tokens from a
     * {@code getTranslatable} key, leaving the plain lang key. Mirrors what
     * {@code DialogueType.applyFallback} does client-side. A malformed key is returned unchanged.
     */
    static String stripMarkers(String key) {
        int i = 0;
        while (i < key.length() && key.charAt(i) == '#') {
            int dot = key.indexOf('.', i);
            if (dot < 0) {
                return key;
            }
            i = dot + 1;
        }
        return key.substring(i);
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
