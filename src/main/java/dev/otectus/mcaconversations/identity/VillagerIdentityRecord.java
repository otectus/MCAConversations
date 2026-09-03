package dev.otectus.mcaconversations.identity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * One villager's stable personal anchors (spec §6.1).
 *
 * <p>Identifiers, never prose. Everything here is a token from {@link IdentityCatalog}; the locale
 * files turn tokens into sentences, so the save file contains no English and a translation change
 * cannot rewrite anybody's personality.
 *
 * <p><b>What makes this stable.</b> The record is generated once from a seed derived from the world
 * seed and the villager's UUID, then persisted. Rebalancing weights later, renaming a token, changing
 * the catalog, restarting the server, moving the villager, renaming them, or a different player
 * talking to them all leave it untouched. Only an explicit life event may change it, and only through
 * the narrow methods below — which is the difference between an identity and a daily roll
 * (spec §6.4).
 *
 * @param schemaVersion    the generation schema this profile was produced by; migrations key off it
 * @param profileSeed      the seed actually used, kept so generation is reproducible and auditable
 * @param interests        up to two; optional topic availability and questions asked back
 * @param values           up to two; scene weighting and how disagreements resolve
 * @param comfort          one; positive small talk and recovery beats
 * @param aversion         one; boundaries and low-stakes conflict
 * @param workStyle        one; which profession subjects recur and what help is welcome
 * @param socialStyle      one; initiative and named-person weighting
 * @param disclosureStyle  one; route shape, not an adjective
 * @param originMotif      one; life and history scenes
 * @param formativeEvent   at most one active motif, written only by an observed life event
 * @param formerProfession the trade held before this one, when a change was actually observed
 * @param lastLifeChangeDay the day the last explicit transition was applied
 */
public record VillagerIdentityRecord(int schemaVersion,
                                     long profileSeed,
                                     Set<String> interests,
                                     Set<String> values,
                                     String comfort,
                                     String aversion,
                                     String workStyle,
                                     String socialStyle,
                                     String disclosureStyle,
                                     String originMotif,
                                     Optional<String> formativeEvent,
                                     Optional<String> formerProfession,
                                     long lastLifeChangeDay) {

    /**
     * Bumped when the <em>meaning</em> of a field changes, never merely because the catalog grew.
     * {@code HistoryMigration} keys its per-field upgrades off this number.
     */
    public static final int SCHEMA_VERSION = 1;

    private static final String KEY_VERSION = "v";
    private static final String KEY_SEED = "seed";
    private static final String KEY_INTERESTS = "interests";
    private static final String KEY_VALUES = "values";
    private static final String KEY_COMFORT = "comfort";
    private static final String KEY_AVERSION = "aversion";
    private static final String KEY_WORK_STYLE = "work";
    private static final String KEY_SOCIAL_STYLE = "social";
    private static final String KEY_DISCLOSURE_STYLE = "disclosure";
    private static final String KEY_ORIGIN = "origin";
    private static final String KEY_FORMATIVE = "formative";
    private static final String KEY_FORMER_PROFESSION = "former_profession";
    private static final String KEY_LIFE_CHANGE_DAY = "life_change_day";

    public VillagerIdentityRecord {
        interests = capped(interests, IdentityFamily.INTEREST.cap());
        values = capped(values, IdentityFamily.VALUE.cap());
        comfort = normalize(comfort);
        aversion = normalize(aversion);
        workStyle = normalize(workStyle);
        socialStyle = normalize(socialStyle);
        disclosureStyle = normalize(disclosureStyle);
        originMotif = normalize(originMotif);
        formativeEvent = formativeEvent == null ? Optional.empty()
                : formativeEvent.map(VillagerIdentityRecord::normalize).filter(s -> !s.isEmpty());
        formerProfession = formerProfession == null ? Optional.empty()
                : formerProfession.map(VillagerIdentityRecord::normalize).filter(s -> !s.isEmpty());
    }

    /**
     * True when every single-token family is filled.
     *
     * <p>A partial profile is legal — a baby has no work style, and a catalog missing a family cannot
     * produce one — and reads as "no preference" everywhere rather than as an empty string that some
     * condition might match by accident.
     */
    public boolean isComplete() {
        return !comfort.isEmpty() && !aversion.isEmpty() && !workStyle.isEmpty()
                && !socialStyle.isEmpty() && !disclosureStyle.isEmpty() && !originMotif.isEmpty()
                && interests.size() == IdentityFamily.INTEREST.cap()
                && values.size() == IdentityFamily.VALUE.cap();
    }

    /** True when this villager holds {@code token} in {@code family}. */
    public boolean has(IdentityFamily family, String token) {
        if (family == null || token == null) {
            return false;
        }
        String needle = normalize(token);
        return switch (family) {
            case INTEREST -> interests.contains(needle);
            case VALUE -> values.contains(needle);
            case COMFORT -> comfort.equals(needle);
            case AVERSION -> aversion.equals(needle);
            case WORK_STYLE -> workStyle.equals(needle);
            case SOCIAL_STYLE -> socialStyle.equals(needle);
            case DISCLOSURE_STYLE -> disclosureStyle.equals(needle);
            case ORIGIN_MOTIF -> originMotif.equals(needle);
        };
    }

    /** Every token this villager holds, as {@code family:id}, in family order. Used by the trace. */
    public Set<String> qualifiedTokens() {
        Set<String> out = new LinkedHashSet<>();
        interests.forEach(id -> out.add(IdentityFamily.INTEREST.key() + ":" + id));
        values.forEach(id -> out.add(IdentityFamily.VALUE.key() + ":" + id));
        addIfPresent(out, IdentityFamily.COMFORT, comfort);
        addIfPresent(out, IdentityFamily.AVERSION, aversion);
        addIfPresent(out, IdentityFamily.WORK_STYLE, workStyle);
        addIfPresent(out, IdentityFamily.SOCIAL_STYLE, socialStyle);
        addIfPresent(out, IdentityFamily.DISCLOSURE_STYLE, disclosureStyle);
        addIfPresent(out, IdentityFamily.ORIGIN_MOTIF, originMotif);
        formativeEvent.ifPresent(id -> out.add("formative:" + id));
        return Set.copyOf(out);
    }

    // --- Explicit life transitions (spec §6.4) --------------------------------------------------

    /**
     * Records a profession change.
     *
     * <p>Writes the former trade and stamps the day; it deliberately does <em>not</em> touch the work
     * style. A methodical mason who becomes a methodical farmer is the same person, and rerolling
     * style on every job change would be identity drift wearing a life-event costume.
     */
    public VillagerIdentityRecord withProfessionChange(String previousProfessionId, long day) {
        String previous = normalize(previousProfessionId);
        if (previous.isEmpty() || formerProfession.map(previous::equals).orElse(false)) {
            return this;
        }
        return new VillagerIdentityRecord(schemaVersion, profileSeed, interests, values, comfort,
                aversion, workStyle, socialStyle, disclosureStyle, originMotif, formativeEvent,
                Optional.of(previous), day);
    }

    /**
     * Sets the one active formative-event motif.
     *
     * <p>One at a time by design. A villager who has been through several keeps the most recent as
     * live material; the earlier ones survive as history milestones rather than accumulating into a
     * list of tragedies (spec §6.1, §20.7).
     */
    public VillagerIdentityRecord withFormativeEvent(String motif, long day) {
        String normalized = normalize(motif);
        if (normalized.isEmpty() || formativeEvent.map(normalized::equals).orElse(false)) {
            return this;
        }
        return new VillagerIdentityRecord(schemaVersion, profileSeed, interests, values, comfort,
                aversion, workStyle, socialStyle, disclosureStyle, originMotif,
                Optional.of(normalized), formerProfession, day);
    }

    /** Rewrites token ids through the catalog's alias table, leaving the profile otherwise identical. */
    public VillagerIdentityRecord withAliasesResolved(IdentityCatalog catalog) {
        if (catalog == null || catalog.aliases().isEmpty()) {
            return this;
        }
        Set<String> newInterests = new LinkedHashSet<>();
        interests.forEach(id -> newInterests.add(catalog.resolveAlias(id)));
        Set<String> newValues = new LinkedHashSet<>();
        values.forEach(id -> newValues.add(catalog.resolveAlias(id)));
        return new VillagerIdentityRecord(schemaVersion, profileSeed, newInterests, newValues,
                catalog.resolveAlias(comfort), catalog.resolveAlias(aversion),
                catalog.resolveAlias(workStyle), catalog.resolveAlias(socialStyle),
                catalog.resolveAlias(disclosureStyle), catalog.resolveAlias(originMotif),
                formativeEvent.map(catalog::resolveAlias), formerProfession, lastLifeChangeDay);
    }

    // --- Persistence -----------------------------------------------------------------------------

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putInt(KEY_VERSION, schemaVersion);
        tag.putLong(KEY_SEED, profileSeed);
        tag.put(KEY_INTERESTS, stringList(interests));
        tag.put(KEY_VALUES, stringList(values));
        putIfPresent(tag, KEY_COMFORT, comfort);
        putIfPresent(tag, KEY_AVERSION, aversion);
        putIfPresent(tag, KEY_WORK_STYLE, workStyle);
        putIfPresent(tag, KEY_SOCIAL_STYLE, socialStyle);
        putIfPresent(tag, KEY_DISCLOSURE_STYLE, disclosureStyle);
        putIfPresent(tag, KEY_ORIGIN, originMotif);
        formativeEvent.ifPresent(value -> tag.putString(KEY_FORMATIVE, value));
        formerProfession.ifPresent(value -> tag.putString(KEY_FORMER_PROFESSION, value));
        if (lastLifeChangeDay != 0L) {
            tag.putLong(KEY_LIFE_CHANGE_DAY, lastLifeChangeDay);
        }
        return tag;
    }

    /**
     * Reads a profile back.
     *
     * <p>Tolerant on purpose: unknown fields are ignored, missing fields read as absent, and a future
     * schema version is read with the current reader rather than discarded. Throwing a player's
     * villagers away because a field moved is never the right failure (spec §22.1).
     */
    public static VillagerIdentityRecord load(CompoundTag tag) {
        if (tag == null) {
            return null;
        }
        return new VillagerIdentityRecord(
                tag.contains(KEY_VERSION) ? tag.getInt(KEY_VERSION) : SCHEMA_VERSION,
                tag.getLong(KEY_SEED),
                readStrings(tag, KEY_INTERESTS),
                readStrings(tag, KEY_VALUES),
                tag.getString(KEY_COMFORT),
                tag.getString(KEY_AVERSION),
                tag.getString(KEY_WORK_STYLE),
                tag.getString(KEY_SOCIAL_STYLE),
                tag.getString(KEY_DISCLOSURE_STYLE),
                tag.getString(KEY_ORIGIN),
                tag.contains(KEY_FORMATIVE) ? Optional.of(tag.getString(KEY_FORMATIVE)) : Optional.empty(),
                tag.contains(KEY_FORMER_PROFESSION)
                        ? Optional.of(tag.getString(KEY_FORMER_PROFESSION)) : Optional.empty(),
                tag.getLong(KEY_LIFE_CHANGE_DAY));
    }

    private static void addIfPresent(Set<String> out, IdentityFamily family, String token) {
        if (!token.isEmpty()) {
            out.add(family.key() + ":" + token);
        }
    }

    private static void putIfPresent(CompoundTag tag, String key, String value) {
        if (value != null && !value.isEmpty()) {
            tag.putString(key, value);
        }
    }

    private static ListTag stringList(Set<String> values) {
        ListTag list = new ListTag();
        // TreeSet order: a set that serialises in a different order every save produces a different
        // NBT blob for identical data, which turns every world save into a spurious region rewrite.
        for (String value : new TreeSet<>(values)) {
            list.add(StringTag.valueOf(value));
        }
        return list;
    }

    private static Set<String> readStrings(CompoundTag tag, String key) {
        Set<String> out = new LinkedHashSet<>();
        if (tag == null || !tag.contains(key, Tag.TAG_LIST)) {
            return out;
        }
        ListTag list = tag.getList(key, Tag.TAG_STRING);
        for (int i = 0; i < list.size(); i++) {
            String value = normalize(list.getString(i));
            if (!value.isEmpty()) {
                out.add(value);
            }
        }
        return out;
    }

    private static Set<String> capped(Set<String> values, int cap) {
        if (values == null) {
            return Set.of();
        }
        Set<String> out = new LinkedHashSet<>();
        for (String value : values) {
            if (out.size() >= cap) {
                break;
            }
            String normalized = normalize(value);
            if (!normalized.isEmpty()) {
                out.add(normalized);
            }
        }
        return Set.copyOf(out);
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
