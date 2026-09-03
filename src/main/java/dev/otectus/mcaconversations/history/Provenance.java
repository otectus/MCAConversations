package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Everything §16.3 asks a villager to track about a thing they know.
 *
 * <p>Before this, an episode carried a free-text {@code source} beside a privacy level and a
 * confidence, and nothing tied the three together. That let a record exist saying a villager was
 * <em>certain</em> about a thing they heard as an <em>unknown rumour</em>, which is exactly the
 * "specificity without a source" failure §4.2 rules out. Bundling them makes the relationships
 * checkable in one place, and the canonical constructor is where the checking happens:
 *
 * <ul>
 *   <li>confidence is clamped to the firmest footing the source can bear, so a rumour cannot be
 *       stated as an observation however it was authored;</li>
 *   <li>{@code told_by} with no teller degrades to an unattributed rumour, because a chain the
 *       villager cannot produce is not a chain;</li>
 *   <li>a teller on any other source is dropped rather than kept as decoration;</li>
 *   <li>share permission may be stricter than the privacy level implies, and never freer, so a fact
 *       cannot be laundered by relabelling what may be done with it.</li>
 * </ul>
 *
 * <p>Distortion is the one field the runtime never sets: it stays {@link Distortion#NONE} unless an
 * author wrote otherwise, and {@link #afterHop} does not introduce one.
 *
 * @param source     how the holder came to know it
 * @param toldBy     who told them, when the source names a teller
 * @param confidence how firmly they hold it
 * @param privacy    how sensitive the fact itself is
 * @param share      what this holder may do with it in front of someone else
 * @param distortion whether this particular account is faithful; authored only
 * @param hops       how many times this has been passed on; the chain length §16.4 caps
 */
public record Provenance(KnowledgeSource source,
                         Optional<UUID> toldBy,
                         Confidence confidence,
                         PrivacyLevel privacy,
                         SharePermission share,
                         Distortion distortion,
                         int hops) {

    private static final String KEY_SOURCE = "src";
    private static final String KEY_TELLER = "teller";
    private static final String KEY_CONFIDENCE = "conf";
    private static final String KEY_PRIVACY = "priv";
    private static final String KEY_SHARE = "share";
    private static final String KEY_DISTORTION = "dist";
    private static final String KEY_HOPS = "hops";

    public Provenance {
        source = source == null ? KnowledgeSource.defaultSource() : source;
        toldBy = toldBy == null ? Optional.empty() : toldBy;
        if (source == KnowledgeSource.TOLD_BY && toldBy.isEmpty()) {
            source = KnowledgeSource.UNKNOWN_RUMOR;
        } else if (source != KnowledgeSource.TOLD_BY) {
            toldBy = Optional.empty();
        }
        confidence = confidence == null ? source.defaultConfidence() : confidence;
        if (confidence.rank() < source.ceiling().rank()) {
            confidence = source.ceiling();
        }
        privacy = privacy == null ? PrivacyLevel.defaultLevel() : privacy;
        SharePermission implied = SharePermission.impliedBy(privacy);
        share = share == null ? implied : share.atMost(implied);
        distortion = distortion == null ? Distortion.NONE : distortion;
        hops = Math.max(0, hops);
    }

    /** The villager saw it happen. */
    public static Provenance witnessed(PrivacyLevel privacy) {
        return new Provenance(KnowledgeSource.WITNESSED, Optional.empty(),
                Confidence.WITNESSED, privacy, null, Distortion.NONE, 0);
    }

    /** It happened to them. */
    public static Provenance participant(PrivacyLevel privacy) {
        return new Provenance(KnowledgeSource.PARTICIPANT, Optional.empty(),
                Confidence.WITNESSED, privacy, null, Distortion.NONE, 0);
    }

    /** A named person told them. A null teller lands as an unattributed rumour, not a false chain. */
    public static Provenance told(UUID teller, PrivacyLevel privacy) {
        return new Provenance(KnowledgeSource.TOLD_BY, Optional.ofNullable(teller),
                null, privacy, null, Distortion.NONE, 1);
    }

    /** The player said it about themselves. Believed; never treated as observed (§8.6). */
    public static Provenance selfReported(UUID player, PrivacyLevel privacy) {
        return new Provenance(KnowledgeSource.TOLD_BY, Optional.ofNullable(player),
                Confidence.SELF_REPORTED, privacy, null, Distortion.NONE, 1);
    }

    /** The village was told. Nobody in particular said it, and everyone has it. */
    public static Provenance publicNotice() {
        return new Provenance(KnowledgeSource.PUBLIC_NOTICE, Optional.empty(),
                Confidence.CERTAIN, PrivacyLevel.PUBLIC, null, Distortion.NONE, 0);
    }

    /**
     * True when the holder may pass this on to somebody else.
     *
     * <p>Permission governs <em>onward</em> telling. It says nothing about whether the person a
     * thing happened to may speak of their own life: a villager confiding their own worry is
     * governed by privacy and the relationship, exactly as it was before this field existed. What
     * this refuses is repeating it to a third party.
     */
    public boolean maySpeak() {
        return share.maySpeak();
    }

    /** True when they may name the people in it as well as describe what happened. */
    public boolean mayName() {
        return share.mayName();
    }

    /** True when the wording has to hedge — "I heard", "she says" — rather than assert. */
    public boolean needsHedging() {
        return confidence.needsHedging() || !source.isFirstHand();
    }

    /** True when repeating this without permission is a breach with an authored consequence. */
    public boolean isBreachable() {
        return privacy.isBreachable() || share == SharePermission.MAY_NOT_SHARE;
    }

    /** True when this has travelled far enough that it should stop (spec §16.4 point 6). */
    public boolean hasTravelledFarEnough(int maxHops) {
        return hops >= maxHops;
    }

    /** True when a correction scene has something to correct (§16.4 point 8). */
    public boolean isCorrectable() {
        return distortion.isCorrectable() || confidence == Confidence.DOUBTED;
    }

    /**
     * This same fact as the next villager along would hold it (spec §16.4).
     *
     * <p>One hop costs one step of confidence and re-sources the fact to whoever passed it on.
     * Permission narrows and never widens, and privacy is carried across unchanged: a confidential
     * thing does not become ordinary by being repeated, it becomes a confidential thing that has
     * been repeated. Nothing here invents detail — a distortion may only be authored, so the hop
     * carries {@link Distortion#NONE} regardless of what the source account said.
     *
     * @param teller who is passing it on, or null when the receiver could not say
     */
    public Provenance afterHop(UUID teller) {
        return new Provenance(
                source.afterHop(teller != null),
                Optional.ofNullable(teller),
                confidence.weakened(),
                privacy,
                share.atMost(SharePermission.impliedBy(privacy)),
                Distortion.NONE,
                hops + 1);
    }

    /** The same fact held under an explicit permission, never freer than its privacy allows. */
    public Provenance withPermission(SharePermission permission) {
        return permission == null || permission == share ? this
                : new Provenance(source, toldBy, confidence, privacy, permission, distortion, hops);
    }

    /** The same fact at a different sensitivity; permission is re-derived against the new level. */
    public Provenance withPrivacy(PrivacyLevel level) {
        return level == null || level == privacy ? this
                : new Provenance(source, toldBy, confidence, level, share, distortion, hops);
    }

    /** The same event, now known to be as the record says it is (spec §16.4 point 8). */
    public Provenance corrected(Confidence firmer) {
        Confidence target = firmer == null ? Confidence.CERTAIN : firmer;
        return new Provenance(source, toldBy, target, privacy, share, Distortion.NONE, hops);
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putString(KEY_SOURCE, source.key());
        toldBy.ifPresent(teller -> tag.putUUID(KEY_TELLER, teller));
        tag.putString(KEY_CONFIDENCE, confidence.key());
        tag.putString(KEY_PRIVACY, privacy.key());
        tag.putString(KEY_SHARE, share.key());
        if (distortion != Distortion.NONE) {
            tag.putString(KEY_DISTORTION, distortion.key());
        }
        if (hops > 0) {
            tag.putInt(KEY_HOPS, hops);
        }
        return tag;
    }

    public static Provenance load(CompoundTag tag) {
        if (tag == null) {
            return witnessed(PrivacyLevel.defaultLevel());
        }
        return new Provenance(
                KnowledgeSource.byKey(tag.getString(KEY_SOURCE)).orElse(KnowledgeSource.defaultSource()),
                tag.hasUUID(KEY_TELLER) ? Optional.of(tag.getUUID(KEY_TELLER)) : Optional.empty(),
                Confidence.byKey(tag.getString(KEY_CONFIDENCE)).orElse(null),
                PrivacyLevel.byKey(tag.getString(KEY_PRIVACY)).orElse(PrivacyLevel.defaultLevel()),
                SharePermission.byKey(tag.getString(KEY_SHARE)).orElse(null),
                Distortion.byKey(tag.getString(KEY_DISTORTION)).orElse(Distortion.NONE),
                tag.getInt(KEY_HOPS));
    }

    /**
     * Reads provenance out of a record written before it existed.
     *
     * <p>Saves from 1.4.0 up to this change carry a free-text source beside a privacy level and a
     * confidence. A string that names a source we now understand is kept; anything else — including
     * the empty string a record could be written with — becomes {@code witnessed}, which is what
     * that generation of the code assumed when it wrote the row. Permission is derived, because
     * those saves had nowhere to put one.
     */
    public static Provenance fromLegacy(String source, String privacy, String confidence) {
        PrivacyLevel level = PrivacyLevel.byKey(privacy).orElse(PrivacyLevel.defaultLevel());
        return new Provenance(
                KnowledgeSource.byKey(source).orElse(KnowledgeSource.defaultSource()),
                Optional.empty(),
                Confidence.byKey(confidence).orElse(null),
                level,
                null,
                Distortion.NONE,
                0);
    }

    /** The legacy string this provenance would have been written as, for the report and traces. */
    public String legacyKey() {
        return source.key();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return other instanceof Provenance that
                && source == that.source
                && Objects.equals(toldBy, that.toldBy)
                && confidence == that.confidence
                && privacy == that.privacy
                && share == that.share
                && distortion == that.distortion
                && hops == that.hops;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, toldBy, confidence, privacy, share, distortion, hops);
    }
}
