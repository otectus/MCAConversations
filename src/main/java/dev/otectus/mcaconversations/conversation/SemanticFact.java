package dev.otectus.mcaconversations.conversation;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * One thing a turn has established, as an id rather than as prose (spec §5.2, §9.7).
 *
 * <p>Written {@code type:value} — {@code crop:stressed}, {@code cause:dry_weather},
 * {@code player:offered_water_help}. A beat declares the facts every one of its variants makes true;
 * a reply declares the facts its wording presupposes. Lint then answers the question that used to be
 * unanswerable: is "I'll bring you some" referring to anything?
 *
 * <p>Facts are deliberately not sentences. Nothing here is ever shown to a player and nothing here is
 * ever localized — the durable store keeps ids and the dialogue renders fresh lines from them, so a
 * language change never strands a save (spec §17 rule 11).
 */
public record SemanticFact(String type, String value) implements Comparable<SemanticFact> {

    /** Dotted lowercase segments on each side of the colon. */
    public static final Pattern TYPE = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");
    public static final Pattern VALUE = Pattern.compile("[a-z0-9_]+(\\.[a-z0-9_]+)*");

    public SemanticFact {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(value, "value");
        if (!TYPE.matcher(type).matches()) {
            throw new IllegalArgumentException("fact type '" + type + "' must match " + TYPE.pattern());
        }
        if (!VALUE.matcher(value).matches()) {
            throw new IllegalArgumentException("fact value '" + value + "' must match " + VALUE.pattern());
        }
    }

    /** Parses {@code type:value}; throws on anything else so a typo cannot become a silent no-op. */
    public static SemanticFact parse(String raw) {
        if (raw == null) {
            throw new IllegalArgumentException("fact must not be null");
        }
        String trimmed = raw.trim().toLowerCase(Locale.ROOT);
        int colon = trimmed.indexOf(':');
        if (colon <= 0 || colon == trimmed.length() - 1) {
            throw new IllegalArgumentException("fact '" + raw + "' must be written type:value");
        }
        if (trimmed.indexOf(':', colon + 1) >= 0) {
            throw new IllegalArgumentException("fact '" + raw + "' must contain exactly one colon");
        }
        return new SemanticFact(trimmed.substring(0, colon), trimmed.substring(colon + 1));
    }

    @Override
    public String toString() {
        return type + ":" + value;
    }

    @Override
    public int compareTo(SemanticFact other) {
        int byType = type.compareTo(other.type);
        return byType != 0 ? byType : value.compareTo(other.value);
    }
}
