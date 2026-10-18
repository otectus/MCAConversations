package dev.otectus.mcaconversations.conversation;

import java.util.UUID;

/**
 * What the server remembers about one utterance it delivered: enough to say which line an offer
 * belonged to, and for a diagnostic to name it, without keeping the sentence itself.
 *
 * <p>Transient like the session that holds it — a bounded ring of the last few — and never persisted.
 *
 * @param identity       the conversation the line was said in (see {@link UtteranceTag.Id#identity})
 * @param seq            the line's sequence number within that conversation
 * @param kind           what the line is
 * @param frontend       which frontend delivered it; an offer only ever refers to its own frontend's lines
 * @param key            the lang key with MCA's {@code #G}/{@code #E}/{@code #P}/{@code #T} markers
 *                       stripped, or empty for a line that is not translatable
 * @param variant        the pinned {@code /N} variant, or 0 when the key names no variant
 * @param generation     the content generation the line was said under
 * @param offerRevision  the offer that later named this line, or -1 while none has
 */
public record Utterance(UUID identity, long seq, UtteranceTag.Kind kind, ConversationSession.Frontend frontend,
                        String key, int variant, long generation, long offerRevision) {

    public Utterance {
        key = key == null ? "" : key;
        kind = kind == null ? UtteranceTag.Kind.NPC_LINE : kind;
        frontend = frontend == null ? ConversationSession.Frontend.GUI : frontend;
    }

    /** True for a page prompt MCA marked silent. */
    public boolean silent() {
        return kind == UtteranceTag.Kind.SILENT_PROMPT;
    }

    /** The same record, noting the offer that named it. */
    Utterance referencedBy(long revision) {
        return new Utterance(identity, seq, kind, frontend, key, variant, generation, revision);
    }

    /** The {@code /N} suffix of a marker-stripped key, or 0 when it has none. */
    public static int variantOf(String strippedKey) {
        if (strippedKey == null) {
            return 0;
        }
        int slash = strippedKey.lastIndexOf('/');
        if (slash < 0 || slash == strippedKey.length() - 1) {
            return 0;
        }
        try {
            int parsed = Integer.parseInt(strippedKey.substring(slash + 1));
            return Math.max(0, parsed);
        } catch (NumberFormatException notAVariant) {
            return 0;
        }
    }
}
