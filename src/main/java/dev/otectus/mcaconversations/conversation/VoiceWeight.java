package dev.otectus.mcaconversations.conversation;

/**
 * How much it matters that a given line is written in the villager's own voice (spec §15.5).
 *
 * <p>The plan sets two coverage targets and is explicit that only one of them can be gamed: "a raw
 * percentage may not be met by overlaying terminal small-talk filler". A raw count treats a farewell
 * and a confession as one pool each, so a corpus could hit its number by writing twenty-one voices
 * for "see you later" and none for the line that matters. The weighted measure exists to make that
 * arithmetic impossible.
 *
 * <p>The weights are read off what a beat already declares rather than from a table kept beside the
 * content, so a line cannot be promoted by relabelling it. A signature beat is one §9.3 designates;
 * a substantive beat is one that leaves the subject open, which is the body of a conversation; and
 * everything terminal is filler for this purpose however good it is, because a line the player hears
 * on the way out is not where a personality is established.
 */
public enum VoiceWeight {

    /** Professions, ruptures, disclosures, commitments, callbacks: the lines §9.3 designates. */
    SIGNATURE(8),

    /** The body of a conversation: a line that leaves the subject open. */
    SUBSTANTIVE(4),

    /** Guarded, closing and terminal lines. Worth writing; not where a voice is made. */
    FILLER(1);

    private final int weight;

    VoiceWeight(int weight) {
        this.weight = weight;
    }

    public int weight() {
        return weight;
    }

    /** The tier a beat belongs to. */
    public static VoiceWeight of(BeatContract beat) {
        if (beat == null) {
            return FILLER;
        }
        if (SignatureBeat.isSignature(beat)) {
            return SIGNATURE;
        }
        return switch (beat.openness()) {
            case INVITES_FOLLOWUP, PERMITS_FOLLOWUP -> SUBSTANTIVE;
            case GUARDED, CLOSES_SUBJECT, ENDS_CONVERSATION -> FILLER;
        };
    }
}
