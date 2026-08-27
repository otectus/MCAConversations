package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.history.NarrativeValue;

import java.util.Locale;
import java.util.Optional;

/**
 * What kind of value a scene slot accepts (spec §10.1, §10.3).
 *
 * <p>Typing slots is what keeps §5.2's rule enforceable: it is safe for one contracted scene to say
 * "the <b>east field</b> is too wet" or "the <b>orchard</b> is too wet", because both establish the
 * same typed condition. It is not safe for a slot to hold "the harvest was saved", because that is a
 * different meaning and needs a different beat. A type is the line between the two.
 *
 * <p>The rule in {@link #accepts} is deliberately strict. A slot that quietly accepted the wrong kind
 * of value would produce a sentence with a registry id or a raw UUID in the middle of it, which is
 * worse than the scene simply not being eligible.
 */
public enum SlotType {

    /** A token the locale files know how to render as a noun phrase: {@code ledger}, {@code damp}. */
    LOCALIZED_TOKEN("localized_token"),

    /** A namespaced id, rendered through the game's own display name for it. */
    REGISTRY_ID("registry_id"),

    /**
     * A living, named person.
     *
     * <p>Bound from a UUID and resolved to a name at render time, never stored as a name: a name that
     * was cached would keep being spoken after the person died or moved away (spec §2.4).
     */
    PERSON("person"),

    /** One of the coarse semantic locations of §17.4 — never coordinates. */
    LOCATION_TOKEN("location_token"),

    /** A coarse integer band: several, many, most. Never an exact economic count. */
    NUMBER_BAND("number_band"),

    /** A game day, so the locale line can say "three days ago" and be right. */
    DATE("date"),

    /** A yes/no fact the sentence turns on. */
    FLAG("flag");

    private final String key;

    SlotType(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    /** True when {@code value} is a legal binding for a slot of this type. */
    public boolean accepts(NarrativeValue value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return switch (this) {
            case LOCALIZED_TOKEN, LOCATION_TOKEN ->
                    value.kind() == NarrativeValue.Kind.TOKEN
                            || value.kind() == NarrativeValue.Kind.ENUM_TOKEN;
            case REGISTRY_ID -> value.kind() == NarrativeValue.Kind.REGISTRY_ID;
            case PERSON -> value.kind() == NarrativeValue.Kind.UUID_REF;
            case NUMBER_BAND -> value.kind() == NarrativeValue.Kind.BAND;
            case DATE -> value.kind() == NarrativeValue.Kind.DAY;
            case FLAG -> value.kind() == NarrativeValue.Kind.FLAG;
        };
    }

    /**
     * True when a slot of this type needs its referent re-validated before every use.
     *
     * <p>Only {@link #PERSON}. Everything else names a thing that cannot die or move away between one
     * conversation and the next.
     */
    public boolean needsLivenessCheck() {
        return this == PERSON;
    }

    public static Optional<SlotType> byKey(String key) {
        if (key == null) {
            return Optional.empty();
        }
        String normalized = key.trim().toLowerCase(Locale.ROOT);
        for (SlotType type : values()) {
            if (type.key.equals(normalized)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
