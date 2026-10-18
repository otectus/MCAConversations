package dev.otectus.mcaconversations.conversation;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.locale.LineVoice;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Where a line leaving the server becomes an identified utterance: recorded on the player's session
 * and tagged with {@link UtteranceTag}, in one step, so the tag a client reads and the record the
 * server keeps can never disagree.
 *
 * <p>Applied at most once per line. A line tagged where it was written ({@code sayInDialogue}, chat
 * delivery) passes the outgoing-packet hook untouched; only a line nobody tagged — one of MCA's own
 * page prompts in a managed discussion — is stamped there.
 *
 * <p>A chat line for a player with no session yet (a greeting to somebody walking past) still needs an
 * identity, or the chat frontend could not deduplicate it. Those take a per-player ambient nonce from
 * a small bounded map instead of creating a session: creating one here would touch the session
 * timeout, the sweep and the close path for every greeting, which is behaviour this class has no
 * business changing.
 */
public final class UtteranceStamp {

    /** Players with ambient (session-less) chat lines remembered at once; least recently used goes. */
    static final int MAX_AMBIENT = 256;

    private static final Map<UUID, long[]> AMBIENT_SEQ = Collections.synchronizedMap(
            new LinkedHashMap<>(32, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<UUID, long[]> eldest) {
                    return size() > MAX_AMBIENT;
                }
            });
    private static final Map<UUID, UUID> AMBIENT_NONCE = Collections.synchronizedMap(
            new LinkedHashMap<>(32, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<UUID, UUID> eldest) {
                    return size() > MAX_AMBIENT;
                }
            });

    private UtteranceStamp() {
    }

    /**
     * {@code line}, tagged as this player's next utterance on {@code frontend}. Never throws: any
     * failure returns the line exactly as it came in, which is how it would have been sent before.
     *
     * @param speaker the villager saying it, carried in the tag for a chat line (whose system message
     *                does not otherwise name a villager); null for a graphical line
     */
    public static Component stamp(ServerPlayer player, Component line, UtteranceTag.Kind kind,
                                  ConversationSession.Frontend frontend, UUID speaker) {
        if (player == null) {
            return line;
        }
        return stamp(player.getUUID(), line, kind, frontend, speaker);
    }

    /** As above, by player id; the seam the tests use. */
    public static Component stamp(UUID playerId, Component line, UtteranceTag.Kind kind,
                                  ConversationSession.Frontend frontend, UUID speaker) {
        if (playerId == null || line == null) {
            return line;
        }
        try {
            if (UtteranceTag.read(line).isPresent()) {
                return line;
            }
            String key = strippedKey(line);
            ConversationSession session = ConversationSessions.raw(playerId).orElse(null);
            UtteranceTag.Id id;
            if (session != null) {
                Utterance said = session.recordUtterance(kind, frontend, key);
                id = new UtteranceTag.Id(said.identity(), said.seq(), kind, speaker);
            } else if (frontend == ConversationSession.Frontend.CHAT) {
                id = new UtteranceTag.Id(ambientNonce(playerId), nextAmbientSeq(playerId), kind, speaker);
            } else {
                // A graphical line with no session belongs to no discussion this mod tracks; the
                // client treats an untagged line as unknown and stays fail-open.
                return line;
            }
            return UtteranceTag.apply(line, id);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("utterance tagging failed; sending the line untagged", t);
            return line;
        }
    }

    /**
     * One of MCA's own single-line dialogue packets in a managed graphical discussion, re-sent with
     * its utterance tag. True when a tagged copy went out and the caller must cancel the original.
     *
     * <p>Only managed discussions: an interaction this mod never accepted keeps MCA's packets
     * byte-identical. A line that already carries a tag — every line this mod says — is left alone,
     * and the re-sent copy passes through here again as exactly such a line.
     */
    public static boolean restampQuestion(ServerPlayer player, Object message) {
        if (player == null || message == null) {
            return false;
        }
        try {
            boolean silent = dev.otectus.mcaconversations.compat.mca.McaHandles.isSilentQuestion(message);
            Component tagged = managedRetag(player.getUUID(),
                    dev.otectus.mcaconversations.compat.mca.McaHandles.questionText(message), silent);
            return tagged != null
                    && dev.otectus.mcaconversations.compat.mca.McaHandles.sendDialogueLine(player, tagged, silent);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("could not re-send MCA's line with its utterance tag", t);
            return false;
        }
    }

    /**
     * The decision behind {@link #restampQuestion}, free of MCA: the tagged copy of {@code text} to
     * send in its place, or null when the line must go out untouched — no managed graphical
     * discussion, no session, nothing to tag, or a tag already present.
     */
    static Component managedRetag(UUID playerId, Component text, boolean silent) {
        if (playerId == null || text == null || UtteranceTag.read(text).isPresent()) {
            return null;
        }
        ConversationHandle handle = ConversationPresence.ofPlayer(playerId).orElse(null);
        if (handle == null || handle.frontend() != ConversationSession.Frontend.GUI
                || ConversationSessions.raw(playerId).isEmpty()) {
            return null;
        }
        Component tagged = stamp(playerId, text, silent ? UtteranceTag.Kind.SILENT_PROMPT
                : UtteranceTag.Kind.NPC_LINE, ConversationSession.Frontend.GUI, null);
        return tagged == text ? null : tagged;
    }

    /** The lang key of a line with MCA's markers stripped, or empty when it is not translatable. */
    static String strippedKey(Component line) {
        if (line != null && line.getContents() instanceof TranslatableContents contents) {
            return LineVoice.stripMarkers(contents.getKey());
        }
        return "";
    }

    private static UUID ambientNonce(UUID playerId) {
        return AMBIENT_NONCE.computeIfAbsent(playerId, ignored -> UUID.randomUUID());
    }

    private static long nextAmbientSeq(UUID playerId) {
        synchronized (AMBIENT_SEQ) {
            long[] counter = AMBIENT_SEQ.computeIfAbsent(playerId, ignored -> new long[1]);
            return ++counter[0];
        }
    }

    /** Forgets a player's ambient identity (logout, death, server stop). */
    public static void forgetPlayer(UUID playerId) {
        if (playerId != null) {
            AMBIENT_SEQ.remove(playerId);
            AMBIENT_NONCE.remove(playerId);
        }
    }

    /** Test seam and server-stop path. */
    public static void resetForTesting() {
        AMBIENT_SEQ.clear();
        AMBIENT_NONCE.clear();
    }
}
