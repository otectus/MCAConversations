package dev.otectus.mcaconversations.history;

import java.util.Locale;
import java.util.Optional;

/**
 * How a villager came to know a thing (spec §16.3).
 *
 * <p>Until now this was a free string on {@link EpisodeRecord}, which meant nothing could check it.
 * A typed source is what lets the rest of the model refuse the two failures §2.4 names: a villager
 * stating as observed something they were only told, and a villager naming a chain of hearsay they
 * could not actually produce.
 *
 * <p>Each source carries two footings: the one it gives by default, and the firmest it could honestly
 * support. {@link Provenance} defaults to the first and clamps to the second, so an authored pack
 * cannot mark a village rumour {@code witnessed} by declaring it so — the declaration is lowered to
 * what the source can bear, and the scene hedges accordingly.
 */
public enum KnowledgeSource {

    /** They were there and saw it. */
    WITNESSED("witnessed", Confidence.WITNESSED, Confidence.WITNESSED),

    /** It happened to them, or they helped do it. */
    PARTICIPANT("participant", Confidence.WITNESSED, Confidence.WITNESSED),

    /** A relative told them, or it is simply family knowledge. */
    FAMILY("family", Confidence.CERTAIN, Confidence.CERTAIN),

    /** Someone they work beside told them, in the ordinary course of work. */
    COWORKER("coworker", Confidence.LIKELY, Confidence.CERTAIN),

    /** A named person told them. The teller is on the record and can be asked. */
    TOLD_BY("told_by", Confidence.LIKELY, Confidence.CERTAIN),

    /** Announced to the village. Everyone has it; nobody in particular said it. */
    PUBLIC_NOTICE("public_notice", Confidence.CERTAIN, Confidence.CERTAIN),

    /** Heard, with no chain worth naming. The weakest footing that is still worth repeating. */
    UNKNOWN_RUMOR("unknown_rumor", Confidence.UNCERTAIN, Confidence.UNCERTAIN);

    private final String key;
    private final Confidence standard;
    private final Confidence ceiling;

    KnowledgeSource(String key, Confidence standard, Confidence ceiling) {
        this.key = key;
        this.standard = standard;
        this.ceiling = ceiling;
    }

    public String key() {
        return key;
    }

    /**
     * The footing this source gives by default, when nothing said otherwise.
     *
     * <p>Deliberately weaker than {@link #ceiling()} for the second-hand sources. Being told a thing
     * by somebody you could ask is ordinarily "likely" — but it <em>can</em> support certainty, when
     * the teller was the person it happened to, or when a correction has since settled it. Collapsing
     * the two into one number would mean either believing every rumour or never being able to be told
     * the truth.
     */
    public Confidence defaultConfidence() {
        return standard;
    }

    /** The firmest footing this source can honestly support, however it was authored. */
    public Confidence ceiling() {
        return ceiling;
    }

    /** True when the villager has it first-hand and may say so without hedging. */
    public boolean isFirstHand() {
        return this == WITNESSED || this == PARTICIPANT;
    }

    /** True when the source names a person who could be asked about it. */
    public boolean namesATeller() {
        return this == TOLD_BY;
    }

    /**
     * Where this source lands after one propagation hop (spec §16.4).
     *
     * <p>A hop always produces hearsay. First-hand knowledge becomes {@code told_by} when the teller
     * is known and an unattributed rumour otherwise; anything already second-hand stays where it is,
     * because there is no rank below "someone said so" to fall to.
     */
    public KnowledgeSource afterHop(boolean tellerKnown) {
        if (this == UNKNOWN_RUMOR) {
            return UNKNOWN_RUMOR;
        }
        return tellerKnown ? TOLD_BY : UNKNOWN_RUMOR;
    }

    public static Optional<KnowledgeSource> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (KnowledgeSource source : values()) {
            if (source.key.equals(normalized)) {
                return Optional.of(source);
            }
        }
        return Optional.empty();
    }

    public static KnowledgeSource defaultSource() {
        return WITNESSED;
    }
}
