package dev.otectus.mcaconversations.client.speech;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.client.dialogue.dev.ClientDiagnostics;
import dev.otectus.mcaconversations.compat.mca.McaHandles;
import dev.otectus.mcaconversations.conversation.Utterance;
import dev.otectus.mcaconversations.conversation.UtteranceTag;
import dev.otectus.mcaconversations.locale.LineVoice;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Makes MCA's speech say the line that was shown (feedback remediation §2.2). Client-only.
 *
 * <h2>The defect</h2>
 *
 * <p>MCA associates every translatable that decomposes with {@code CommonSpeechManager
 * .lastResolvedKey} — the last <em>pooled</em> key any component on this client resolved — and its
 * speech looks the component up in that association. An explicit {@code …/N} key is not a pool base,
 * so resolving it never updates {@code lastResolvedKey}: every line this mod pins, and every single-key
 * prompt, is associated with whatever pool resolved last, usually MCA's {@code dialogue.main}
 * greeting. With a voice pack that greeting's recording plays; with online TTS its text is read. Both
 * sound like "it keeps saying the opener".
 *
 * <h2>The repair</h2>
 *
 * <p>At the message boundary, before MCA reads its map, write the <em>right</em> key: at
 * {@code getMessage()} the line's exact key goes into {@code lastResolvedKey}, so MCA's own association
 * is right when the chat log decomposes it; at {@code getContent()} the pair (contents, exact key) goes
 * into the map itself, so the lookup is right even if something decomposed the line earlier. Nothing
 * is played, queued or invented here; MCA's speech stays the only thing that speaks, and a key no
 * voice pack has a recording for is silent exactly as before.
 *
 * <p>"Exact key" is what {@code DialogueType.applyFallback} resolves the marker-laden key to on this
 * client — the personality, gender and type variant the screen rendered. Only lines this mod owns
 * ({@code dialogue.conversations.*}, {@code dialogue.chatmode.*}) or that carry its utterance tag are
 * touched, and never an unpinned pool, whose own draw already set the association correctly. Any
 * failure leaves MCA's behaviour exactly as it was.
 */
public final class SpeechAssociation {

    /** Key prefixes this mod owns, after MCA's markers are stripped. */
    static final List<String> OWNED_PREFIXES = List.of("dialogue.conversations.", "dialogue.chatmode.");

    private static final Set<String> WARNED_MISSING = ConcurrentHashMap.newKeySet();
    private static final int MAX_WARNED = 256;

    private SpeechAssociation() {
    }

    // --- The decision, pure ----------------------------------------------------------------------------

    /** True for a marker-stripped key this mod owns. */
    static boolean owned(String strippedKey) {
        if (strippedKey == null) {
            return false;
        }
        for (String prefix : OWNED_PREFIXES) {
            if (strippedKey.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The key MCA's speech should be pointed at for a line with {@code rawKey}, or empty when the line
     * is not this repair's business.
     *
     * @param tagged        whether the line carries an utterance tag
     * @param applyFallback MCA's {@code DialogueType.applyFallback}
     * @param has           {@code Language.has} on this client
     * @param missing       told about a resolved variant the language does not have
     */
    static Optional<String> exactKey(String rawKey, boolean tagged, Function<String, String> applyFallback,
                                     Predicate<String> has, java.util.function.Consumer<String> missing) {
        if (rawKey == null || rawKey.isEmpty()) {
            return Optional.empty();
        }
        String stripped = LineVoice.stripMarkers(rawKey);
        if (!tagged && !owned(stripped)) {
            return Optional.empty();
        }
        if (Utterance.variantOf(stripped) == 0 && has.test(stripped + "/1")) {
            // An unpinned pool: MCA draws it on this client and records the draw as the association
            // itself. Pointing speech at the pool base would make it draw a second, different sentence.
            return Optional.empty();
        }
        String resolved = applyFallback.apply(rawKey);
        if (resolved == null || resolved.isEmpty()) {
            resolved = stripped;
        }
        if (!has.test(resolved)) {
            missing.accept(resolved);
            resolved = stripped;
        }
        return Optional.of(resolved);
    }

    // --- The boundary ------------------------------------------------------------------------------------

    /**
     * {@code getMessage()} HEAD: point MCA's {@code lastResolvedKey} at this line, so the association
     * MCA writes when the chat log decomposes it is this line's own.
     */
    public static void beforeChat(Component line) {
        try {
            TranslatableContents contents = translatable(line);
            if (contents == null || UtteranceTag.isWrapper(line)) {
                // A literal (wrapped or not) is spoken as its text; there is no key to point at.
                return;
            }
            exactKey(contents.getKey(), UtteranceTag.read(line).isPresent())
                    .ifPresent(McaHandles.Speech::setLastResolvedKey);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("speech association (chat) failed; MCA's behaviour stands", t);
        }
    }

    /**
     * {@code getContent()} HEAD: write this line's exact key into MCA's association map, and return
     * the component MCA's speech should be handed — the line itself, or, for a tagged literal, the
     * literal inside its wrapper so MCA speaks it as the text it is.
     */
    public static Component beforeSpeech(Component line, UUID speaker) {
        Component spoken = UtteranceTag.speechCarrier(line);
        try {
            UtteranceTag.Id utterance = UtteranceTag.read(line).orElse(null);
            TranslatableContents contents = translatable(spoken);
            if (contents == null) {
                trace(utterance, null, null, null, null);
                return spoken;
            }
            Optional<String> exact = exactKey(contents.getKey(), utterance != null);
            Map<Object, String> translations = McaHandles.Speech.translations();
            String before = translations == null ? null : translations.get(contents);
            String lastResolved = McaHandles.Speech.lastResolvedKey();
            if (exact.isPresent() && translations != null) {
                translations.put(contents, exact.get());
            }
            trace(utterance, exact.orElse(null), lastResolved, before,
                    translations == null ? null : translations.get(contents));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("speech association (speech) failed; MCA's behaviour stands", t);
        }
        return spoken;
    }

    private static Optional<String> exactKey(String rawKey, boolean tagged) {
        Language language = Language.getInstance();
        return exactKey(rawKey, tagged, McaHandles.Speech::applyFallback, language::has, SpeechAssociation::warnMissing);
    }

    private static TranslatableContents translatable(Component line) {
        return line != null && line.getContents() instanceof TranslatableContents contents ? contents : null;
    }

    private static void warnMissing(String key) {
        if (WARNED_MISSING.size() < MAX_WARNED && WARNED_MISSING.add(key)) {
            McaConversations.LOGGER.debug("speech association: {} is not in the current language; using the "
                    + "unprefixed key (a resource pack may have removed the variant)", key);
        }
    }

    private static void trace(UtteranceTag.Id utterance, String exact, String lastResolved, String before,
                              String after) {
        if (!ClientDiagnostics.speechTrace()) {
            return;
        }
        McaConversations.LOGGER.info("[speech-trace] utt={} kind={} exact={} lastResolved={} mapped-before={} "
                        + "mapped-after={} repaired={}",
                utterance == null ? "untagged" : utterance.identity() + "/" + utterance.seq(),
                utterance == null ? "-" : utterance.kind(), exact, lastResolved, before, after,
                exact != null && !exact.equals(before));
    }
}
