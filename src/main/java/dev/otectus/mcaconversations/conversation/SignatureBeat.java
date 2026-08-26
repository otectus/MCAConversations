package dev.otectus.mcaconversations.conversation;

import java.util.Locale;

/**
 * The lines where a villager's personality has to be audible (spec §9.3).
 *
 * <p>Spec §9.3 refuses a sixteen-personality rewrite of every utility line and instead designates a
 * subset — <em>signature beats</em> — that must carry authored personality coverage in full. This
 * enum is that designation, and it is <b>derived from what a beat already declares</b> rather than
 * hand-marked in the data: a beat is a rebuff because its speech act is a rupture, not because
 * somebody remembered to tick a box. Deriving it means the set cannot drift out of step with the
 * corpus, and a beat authored next month arrives already designated.
 *
 * <p>The five tiers are the five in §9.3, in the order their absence is most noticeable:
 *
 * <ol>
 *   <li>{@link #PROFESSION} — the line that opens a trade, and the line about how it was learned.
 *       The most-read villager sentences in the mod.</li>
 *   <li>{@link #RUPTURE} — refusals, hurt, boundaries and dismissals. The place where a wrong voice
 *       does actual damage: a gloomy villager and a crabby one do not close a subject the same way,
 *       and a player who cannot tell them apart learns nothing from being rebuffed.</li>
 *   <li>{@link #DISCLOSURE} — deep-topic disclosures: what they do not tell the village.</li>
 *   <li>{@link #COMMITMENT} — invitations and promises about the relationship itself.</li>
 *   <li>{@link #CALLBACK} — a villager raising something they were told or promised before.</li>
 * </ol>
 */
public enum SignatureBeat {

    /** The trade's own opening line and the beat about how it was learned. */
    PROFESSION("profession"),
    /** A refusal, a hurt, a boundary or a dismissal — spec §9.3's "boundary/rebuff lines". */
    RUPTURE("rupture"),
    /** Something disclosed that the village does not get told. */
    DISCLOSURE("disclosure"),
    /** A promise, an invitation, or a commitment about what these two are to each other. */
    COMMITMENT("commitment"),
    /** The villager raising a thing the player said or promised on an earlier day. */
    CALLBACK("callback");

    private final String key;

    SignatureBeat(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /**
     * Which signature tier {@code beat} belongs to, or null when it is an ordinary beat.
     *
     * <p>Order matters: a rupture that also discloses something is designated a rupture, because
     * the closing is the part the player has to be able to read.
     */
    public static SignatureBeat of(BeatContract beat) {
        if (beat == null) {
            return null;
        }
        if (beat.callback().isPresent()) {
            return CALLBACK;
        }
        if (beat.npcAct().isRupture() || isRupturedOutcome(beat)) {
            return RUPTURE;
        }
        if (isProfessionSignature(beat)) {
            return PROFESSION;
        }
        if (beat.npcAct() == NpcSpeechAct.DISCLOSE
                || beat.npcAct() == NpcSpeechAct.DISCLOSE_PROBLEM) {
            return DISCLOSURE;
        }
        if (beat.npcAct() == NpcSpeechAct.INVITE) {
            return COMMITMENT;
        }
        return null;
    }

    /** True when {@code beat} is one of the lines §9.3 designates. */
    public static boolean isSignature(BeatContract beat) {
        return of(beat) != null;
    }

    /**
     * A ruptured outcome, except the one that is not a rupture in the sense §9.3 means.
     * {@link OutcomeFamily#CONVERSATION_ENDED} is a rupture to the stance rules — nothing may follow
     * it — but a villager saying goodbye is not a boundary being set against the player, and there
     * are ninety-odd polite farewells in the corpus that would swamp the tier that matters.
     */
    private static boolean isRupturedOutcome(BeatContract beat) {
        return beat.outcome()
                .filter(outcome -> outcome != OutcomeFamily.CONVERSATION_ENDED)
                .map(OutcomeFamily::isRupture)
                .orElse(false);
    }

    /**
     * A trade's identity line and its craft line. Both are keyed off the beat id rather than the
     * subject, because the subject of every work beat is the trade — what distinguishes these two
     * is that they are the answer to "what do you do" and "how did you learn it", the pair a player
     * meets before they have any reason to care about the villager.
     */
    private static boolean isProfessionSignature(BeatContract beat) {
        if (!"work".equals(beat.subjectDomain())) {
            return false;
        }
        String id = beat.id().toLowerCase(Locale.ROOT);
        return id.endsWith(".identity") || id.endsWith(".craft");
    }
}
