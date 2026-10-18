package dev.otectus.mcaconversations.conversation;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.UUID;

/**
 * The identity of one villager utterance, carried inside the component that says it.
 *
 * <h2>Why inside the component</h2>
 *
 * <p>A line reaches up to three client surfaces — the dialogue card, the chat log and MCA's speech —
 * and the answer offer for it arrives on a different channel. Correlating them by packet order is
 * what failed before: nothing tied the offer to the line it belonged to. Riding inside the component
 * means every surface that receives the line also receives its identity, and no second packet has to
 * be matched up by arrival order.
 *
 * <p>The carrier is one extra, unused translatable argument: an empty literal whose style insertion is
 * {@code mcaconversations:utt/<identity>/<seq>/<flags>[/<speaker>]}. An argument no placeholder names
 * is never rendered and never reaches chat on shift-click; it survives
 * {@code Component.Serializer} (MCA serialises its dialogue packets as JSON); and because
 * {@link TranslatableContents#equals} compares arguments, two deliberately identical sentences are two
 * different keys in MCA's speech association. A line that is not translatable — a Townstead species
 * line, Capitals chronicle text, the chat fallback — is wrapped as {@code translatable("%s", line, tag)},
 * which renders exactly as the line did.
 *
 * <p>Pure and side-effect free, so the server and the client share it and a unit test can hold it to
 * its round trip. Reading tolerates absence: an untagged component is an unknown utterance, never an
 * error.
 */
public final class UtteranceTag {

    /** Every tag starts with this; nothing else in the mod writes a style insertion with it. */
    public static final String PREFIX = "mcaconversations:utt/";

    /** The template a non-translatable line is wrapped in. */
    static final String WRAPPER_KEY = "%s";

    /** How far a read walks into a component tree: lines are shallow, so this is generous. */
    private static final int MAX_NODES = 64;

    /** What an utterance is, which decides who may show it and who may speak it. */
    public enum Kind {
        /** Something the villager said. Shown, echoed to chat, and spoken. */
        NPC_LINE('N'),
        /** A page prompt MCA marked silent: shown on the card, never echoed or spoken. */
        SILENT_PROMPT('S'),
        /** A chat options block or heart feedback: shown once, never spoken. */
        FEEDBACK('F');

        private final char flag;

        Kind(char flag) {
            this.flag = flag;
        }

        char flag() {
            return flag;
        }

        /** Whether a speech backend may voice this utterance at all. */
        public boolean speakable() {
            return this == NPC_LINE;
        }

        static Kind ofFlag(char flag) {
            for (Kind kind : values()) {
                if (kind.flag == flag) {
                    return kind;
                }
            }
            return null;
        }
    }

    /**
     * One utterance's identity.
     *
     * @param identity the conversation it belongs to: the handle's session id for a managed
     *                 discussion, the session nonce otherwise
     * @param seq      per-conversation monotonic sequence number, starting at 1
     * @param kind     what the utterance is
     * @param speaker  the villager speaking, or null when the carrier does not say (graphical lines,
     *                 whose packet already names the villager)
     */
    public record Id(UUID identity, long seq, Kind kind, UUID speaker) {
        public Id {
            if (identity == null) {
                throw new IllegalArgumentException("an utterance needs a conversation identity");
            }
            if (seq < 1) {
                throw new IllegalArgumentException("utterance sequence numbers start at 1: " + seq);
            }
            kind = kind == null ? Kind.NPC_LINE : kind;
        }

        /** The same utterance seen from anywhere: identity and sequence, nothing else. */
        public boolean sameUtterance(Id other) {
            return other != null && identity.equals(other.identity) && seq == other.seq;
        }
    }

    /** A tag together with the component that carries it, so a caller can act on exactly that line. */
    public record Located(Id id, Component carrier) {
    }

    private UtteranceTag() {
    }

    // --- Encoding -----------------------------------------------------------------------------------

    /** The style insertion for {@code id}. */
    public static String encode(Id id) {
        StringBuilder out = new StringBuilder(PREFIX.length() + 90);
        out.append(PREFIX).append(id.identity()).append('/').append(id.seq()).append('/').append(id.kind().flag());
        if (id.speaker() != null) {
            out.append('/').append(id.speaker());
        }
        return out.toString();
    }

    /** The identity in a style insertion, or empty for anything that is not one of ours. */
    public static Optional<Id> decode(String insertion) {
        if (insertion == null || !insertion.startsWith(PREFIX)) {
            return Optional.empty();
        }
        String[] parts = insertion.substring(PREFIX.length()).split("/", -1);
        if (parts.length < 3 || parts.length > 4 || parts[2].isEmpty()) {
            return Optional.empty();
        }
        try {
            UUID identity = UUID.fromString(parts[0]);
            long seq = Long.parseLong(parts[1]);
            Kind kind = Kind.ofFlag(parts[2].charAt(0));
            UUID speaker = parts.length == 4 && !parts[3].isEmpty() ? UUID.fromString(parts[3]) : null;
            if (kind == null || seq < 1) {
                return Optional.empty();
            }
            return Optional.of(new Id(identity, seq, kind, speaker));
        } catch (RuntimeException malformed) {
            return Optional.empty();
        }
    }

    // --- Applying -------------------------------------------------------------------------------------

    /** The unused argument that carries {@code id}. */
    static MutableComponent carrier(Id id) {
        String insertion = encode(id);
        return Component.literal("").withStyle(style -> style.withInsertion(insertion));
    }

    /**
     * {@code line} carrying {@code id}. Applied at most once: a component that already carries a tag
     * is returned unchanged, so a line tagged where it was written is not re-tagged where it is sent.
     *
     * <p>A translatable keeps its key, fallback, arguments, style and siblings, with the tag appended
     * as the last argument. Anything else is wrapped, which renders identically.
     */
    public static Component apply(Component line, Id id) {
        if (line == null || id == null || read(line).isPresent()) {
            return line;
        }
        if (line.getContents() instanceof TranslatableContents contents) {
            Object[] args = contents.getArgs();
            Object[] tagged = new Object[args.length + 1];
            System.arraycopy(args, 0, tagged, 0, args.length);
            tagged[args.length] = carrier(id);
            MutableComponent copy = contents.getFallback() == null
                    ? Component.translatable(contents.getKey(), tagged)
                    : Component.translatableWithFallback(contents.getKey(), contents.getFallback(), tagged);
            copy.setStyle(line.getStyle());
            line.getSiblings().forEach(copy::append);
            return copy;
        }
        return Component.translatable(WRAPPER_KEY, line, carrier(id));
    }

    // --- Reading ----------------------------------------------------------------------------------------

    /** The first tag anywhere in {@code component}, preferring a spoken line over feedback. */
    public static Optional<Id> read(Component component) {
        return locate(component).map(Located::id);
    }

    /**
     * The tag and the component carrying it. A chat message is a tree — the speaker's name, the
     * line, then perhaps an options block — and only the line itself is a thing to speak, so a line
     * tag wins over a feedback tag found earlier.
     */
    public static Optional<Located> locate(Component component) {
        if (component == null) {
            return Optional.empty();
        }
        Located feedback = null;
        Deque<Component> pending = new ArrayDeque<>();
        pending.add(component);
        int visited = 0;
        while (!pending.isEmpty() && visited++ < MAX_NODES) {
            Component node = pending.removeFirst();
            if (node.getContents() instanceof TranslatableContents contents) {
                Object[] args = contents.getArgs();
                for (int i = args.length - 1; i >= 0; i--) {
                    if (args[i] instanceof Component arg) {
                        Optional<Id> id = decode(arg.getStyle().getInsertion());
                        if (id.isPresent()) {
                            if (id.get().kind() != Kind.FEEDBACK) {
                                return Optional.of(new Located(id.get(), node));
                            }
                            if (feedback == null) {
                                feedback = new Located(id.get(), node);
                            }
                        }
                    }
                }
                for (Object arg : args) {
                    if (arg instanceof Component nested && decode(nested.getStyle().getInsertion()).isEmpty()) {
                        pending.addLast(nested);
                    }
                }
            }
            pending.addAll(node.getSiblings());
        }
        return Optional.ofNullable(feedback);
    }

    /**
     * The component a speech backend should be handed for a tagged line: the line itself for a
     * translatable, and the original literal for a wrapped one. MCA speaks a {@code LiteralContents}
     * as its text and looks a translatable up in its association map, so handing it the wrapper
     * would turn a literal sentence into a lookup.
     */
    public static Component speechCarrier(Component carrier) {
        if (carrier != null && carrier.getContents() instanceof TranslatableContents contents
                && WRAPPER_KEY.equals(contents.getKey()) && contents.getArgs().length == 2
                && contents.getArgs()[0] instanceof Component inner
                && contents.getArgs()[1] instanceof Component tag
                && decode(tag.getStyle().getInsertion()).isPresent()) {
            return inner;
        }
        return carrier;
    }

    /** True for a component that is one of this class's literal wrappers. */
    public static boolean isWrapper(Component component) {
        return component != null && speechCarrier(component) != component;
    }
}
