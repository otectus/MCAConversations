package dev.otectus.mcaconversations.history;

import net.minecraft.nbt.CompoundTag;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * One fact a persisted narrative record may carry — and a closed set of the kinds it may be
 * (spec §8.2).
 *
 * <p>The closure is the point. If an episode payload could hold arbitrary strings, the first thing
 * anyone would put in one is an English sentence, and from then on the save file would contain prose
 * that no translation could reach and no lint could check. Here a payload holds a
 * {@link Kind#TOKEN localized token}, a {@link Kind#REGISTRY_ID registry id}, a
 * {@link Kind#UUID_REF UUID}, an {@link Kind#BAND integer band}, a {@link Kind#DAY game day}, a
 * {@link Kind#FLAG boolean} or an {@link Kind#ENUM_TOKEN enum token} — and the locale files turn
 * those into sentences at the moment of speaking (Appendix B).
 *
 * <p>Immutable, comparable by content, and round-trippable through a two-field compound so an unknown
 * future kind reads back as {@link #EMPTY} rather than corrupting the record around it.
 *
 * @param kind what sort of fact this is
 * @param raw  the payload, always rendered as a string; numeric kinds parse it on demand
 */
public record NarrativeValue(Kind kind, String raw) {

    /** The kinds a payload may hold. Adding one is a schema change with a migration. */
    public enum Kind {
        /** A dotted token the locale files know how to say: {@code ledger}, {@code damp}. */
        TOKEN("token"),
        /** A namespaced registry id: {@code minecraft:iron_ingot}, {@code forge:ingots/iron}. */
        REGISTRY_ID("registry_id"),
        /** A reference to an entity — resolved and validated before use, never spoken raw. */
        UUID_REF("uuid"),
        /** A coarse integer band: how many, how bad, how far — never an exact economic count. */
        BAND("band"),
        /** A game day, so tense words can be checked against the calendar. */
        DAY("day"),
        /** A yes/no fact. */
        FLAG("flag"),
        /** A member of a closed vocabulary this mod owns, such as an episode state. */
        ENUM_TOKEN("enum");

        private final String key;

        Kind(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }

        static Kind byKey(String key) {
            if (key != null) {
                String normalized = key.trim().toLowerCase(Locale.ROOT);
                for (Kind kind : values()) {
                    if (kind.key.equals(normalized)) {
                        return kind;
                    }
                }
            }
            return null;
        }
    }

    /** Tokens and enum tokens: dotted lowercase, the shape used everywhere else in the mod. */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");
    public static final Pattern REGISTRY_PATTERN = Pattern.compile("[a-z0-9_.-]+:[a-z0-9_./-]+");

    /** The value an unreadable or unknown-kind payload becomes. Never spoken, never matched. */
    public static final NarrativeValue EMPTY = new NarrativeValue(Kind.TOKEN, "");

    private static final String KEY_KIND = "k";
    private static final String KEY_RAW = "v";

    public NarrativeValue {
        raw = raw == null ? "" : raw.trim();
        if (kind == null) {
            kind = Kind.TOKEN;
        }
    }

    public static NarrativeValue token(String value) {
        String normalized = value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
        return TOKEN_PATTERN.matcher(normalized).matches()
                ? new NarrativeValue(Kind.TOKEN, normalized)
                : EMPTY;
    }

    public static NarrativeValue registryId(String value) {
        String normalized = value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
        return REGISTRY_PATTERN.matcher(normalized).matches()
                ? new NarrativeValue(Kind.REGISTRY_ID, normalized)
                : EMPTY;
    }

    public static NarrativeValue uuid(UUID value) {
        return value == null ? EMPTY : new NarrativeValue(Kind.UUID_REF, value.toString());
    }

    public static NarrativeValue band(int value) {
        return new NarrativeValue(Kind.BAND, Integer.toString(value));
    }

    public static NarrativeValue day(long value) {
        return new NarrativeValue(Kind.DAY, Long.toString(value));
    }

    public static NarrativeValue flag(boolean value) {
        return new NarrativeValue(Kind.FLAG, Boolean.toString(value));
    }

    public static NarrativeValue enumToken(String value) {
        String normalized = value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
        return TOKEN_PATTERN.matcher(normalized).matches()
                ? new NarrativeValue(Kind.ENUM_TOKEN, normalized)
                : EMPTY;
    }

    public boolean isEmpty() {
        return raw.isEmpty();
    }

    /** The UUID this value references, or empty for any other kind or a malformed payload. */
    public Optional<UUID> asUuid() {
        if (kind != Kind.UUID_REF) {
            return Optional.empty();
        }
        try {
            return Optional.of(UUID.fromString(raw));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<Integer> asBand() {
        if (kind != Kind.BAND) {
            return Optional.empty();
        }
        try {
            return Optional.of(Integer.valueOf(raw));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public Optional<Long> asDay() {
        if (kind != Kind.DAY) {
            return Optional.empty();
        }
        try {
            return Optional.of(Long.valueOf(raw));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public boolean asFlag() {
        return kind == Kind.FLAG && Boolean.parseBoolean(raw);
    }

    /**
     * How this value appears in a trace, a report and a slot-binding key: {@code token:ledger}.
     *
     * <p>Never how it appears to a player. A player sees the sentence the locale file built around
     * this token, which is why the token itself may be terse and machine-shaped.
     */
    public String qualified() {
        return kind.key() + ":" + raw;
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putString(KEY_KIND, kind.key());
        tag.putString(KEY_RAW, raw);
        return tag;
    }

    /** Reads a value back; an unknown kind or a missing payload becomes {@link #EMPTY}. */
    public static NarrativeValue load(CompoundTag tag) {
        if (tag == null) {
            return EMPTY;
        }
        Kind kind = Kind.byKey(tag.getString(KEY_KIND));
        if (kind == null) {
            return EMPTY;
        }
        return new NarrativeValue(kind, tag.getString(KEY_RAW));
    }

    /**
     * Parses the compact {@code kind:value} form datapacks and commands use.
     *
     * <p>An unprefixed string is read as a {@link Kind#TOKEN}, because that is the overwhelmingly
     * common case and requiring {@code token:} on every one would make authored payloads unreadable.
     */
    public static NarrativeValue parse(String text) {
        if (text == null || text.isBlank()) {
            return EMPTY;
        }
        String trimmed = text.trim();
        int colon = trimmed.indexOf(':');
        if (colon > 0) {
            Kind kind = Kind.byKey(trimmed.substring(0, colon));
            if (kind != null) {
                String value = trimmed.substring(colon + 1);
                return switch (kind) {
                    case TOKEN -> token(value);
                    case ENUM_TOKEN -> enumToken(value);
                    case REGISTRY_ID -> registryId(value);
                    case UUID_REF -> {
                        try {
                            yield uuid(UUID.fromString(value));
                        } catch (IllegalArgumentException e) {
                            yield EMPTY;
                        }
                    }
                    case BAND -> {
                        try {
                            yield band(Integer.parseInt(value));
                        } catch (NumberFormatException e) {
                            yield EMPTY;
                        }
                    }
                    case DAY -> {
                        try {
                            yield day(Long.parseLong(value));
                        } catch (NumberFormatException e) {
                            yield EMPTY;
                        }
                    }
                    case FLAG -> flag(Boolean.parseBoolean(value));
                };
            }
            // A colon that is not a known kind is very likely a registry id.
            return registryId(trimmed);
        }
        return token(trimmed);
    }

    @Override
    public String toString() {
        return qualified();
    }
}
