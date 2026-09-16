package dev.otectus.mcaconversations.conversation;

import com.google.gson.JsonSyntaxException;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Objects;

/**
 * One structured reload diagnostic, exactly the record specified in
 * {@code docs/RELOAD-TRANSACTION-BOUNDARY.md} §6.
 *
 * <p>Every field the current architecture cannot always supply — pack id, line, column — is
 * explicitly unknown rather than optional, so a reader can distinguish "not available on this path"
 * from "no problem here". {@link #reason()} is a stable machine token; {@link #message()} is the
 * human half, never the exception's {@code toString()} alone.
 */
public record ContentProblem(long attempt,
                             long generation,
                             String listener,
                             String directory,
                             ResourceOrigin origin,
                             String path,
                             int line,
                             int column,
                             String entry,
                             ContentSeverity severity,
                             String reason,
                             String message,
                             List<ResourceOrigin> contributors) {

    /** Recorded for a line or column that is genuinely not knowable, never fabricated. */
    public static final int UNKNOWN_POSITION = -1;

    public ContentProblem {
        listener = listener == null ? "<unknown>" : listener;
        directory = directory == null ? "<unknown>" : directory;
        path = path == null ? "" : path;
        entry = entry == null ? "" : entry;
        severity = severity == null ? ContentSeverity.SKIPPED : severity;
        reason = reason == null ? "unspecified" : reason;
        message = message == null ? "" : message;
        contributors = contributors == null ? List.of() : List.copyOf(contributors);
    }

    /** A problem attributed to one resource, with no JSON position available. */
    public static ContentProblem of(ContentSection section, ResourceOrigin origin, String pointer,
                                    String entry, ContentSeverity severity, String reason, String message) {
        return new ContentProblem(0L, 0L, section == null ? null : section.listener(),
                section == null ? null : section.directory(), origin, pointer,
                UNKNOWN_POSITION, UNKNOWN_POSITION, entry, severity, reason, message, List.of());
    }

    /**
     * A problem carrying Gson's reported position when the failure was a syntax error. Gson reports
     * {@code at line N column M} inside the message rather than as fields, so it is parsed out of
     * there; anything unparseable stays {@link #UNKNOWN_POSITION}.
     */
    public static ContentProblem ofSyntax(ContentSection section, ResourceOrigin origin, String entry,
                                          ContentSeverity severity, String reason, Throwable cause) {
        int[] position = positionOf(cause);
        return new ContentProblem(0L, 0L, section == null ? null : section.listener(),
                section == null ? null : section.directory(), origin, "", position[0], position[1],
                entry, severity, reason, String.valueOf(cause), List.of());
    }

    /** The same problem, stamped with the attempt and target generation the coordinator owns. */
    public ContentProblem stamped(long attemptId, long targetGeneration) {
        return new ContentProblem(attemptId, targetGeneration, listener, directory, origin, path,
                line, column, entry, severity, reason, message, contributors);
    }

    /** The same problem, naming every resource that contributed to a collision. */
    public ContentProblem withContributors(List<ResourceOrigin> all) {
        return new ContentProblem(attempt, generation, listener, directory, origin, path, line, column,
                entry, severity, reason, message, all);
    }

    /** The single log line for this problem, with unknown fields spelled out as unknown. */
    public String format() {
        StringBuilder out = new StringBuilder();
        out.append('[').append(severity.name().toLowerCase(java.util.Locale.ROOT)).append("] ")
                .append(reason)
                .append(" attempt=").append(attempt)
                .append(" generation=").append(generation)
                .append(" listener=").append(listener)
                .append(" directory=").append(directory)
                .append(" resource=").append(origin == null ? "<unknown>" : origin.resource())
                .append(" pack=").append(origin == null ? ResourceOrigin.UNKNOWN_PACK : origin.pack())
                .append(" path=").append(path.isEmpty() ? "<none>" : path)
                .append(" line=").append(line == UNKNOWN_POSITION ? "<unknown>" : line)
                .append(" column=").append(column == UNKNOWN_POSITION ? "<unknown>" : column)
                .append(" entry=").append(entry.isEmpty() ? "<none>" : entry);
        if (!contributors.isEmpty()) {
            out.append(" contributors=").append(contributors);
        }
        if (!message.isEmpty()) {
            out.append(" :: ").append(message);
        }
        return out.toString();
    }

    /**
     * The identity two diagnostics must share to be coalesced: one bad resource must not surface once
     * per derived dangling reference.
     */
    public String coalescingKey() {
        return reason + '|' + (origin == null ? "" : String.valueOf(origin.resource())) + '|' + entry + '|' + path;
    }

    private static int[] positionOf(Throwable cause) {
        for (Throwable t = cause; t != null; t = t.getCause()) {
            if (!(t instanceof JsonSyntaxException) && !(t instanceof java.io.IOException)
                    && !Objects.requireNonNullElse(t.getClass().getName(), "").contains("MalformedJson")
                    && !(t instanceof IllegalStateException)) {
                continue;
            }
            int[] parsed = parse(t.getMessage());
            if (parsed != null) {
                return parsed;
            }
        }
        for (Throwable t = cause; t != null; t = t.getCause()) {
            int[] parsed = parse(t.getMessage());
            if (parsed != null) {
                return parsed;
            }
        }
        return new int[]{UNKNOWN_POSITION, UNKNOWN_POSITION};
    }

    private static int[] parse(String message) {
        if (message == null) {
            return null;
        }
        int lineAt = message.indexOf("line ");
        int columnAt = message.indexOf("column ");
        if (lineAt < 0 || columnAt < 0) {
            return null;
        }
        Integer line = number(message, lineAt + 5);
        Integer column = number(message, columnAt + 7);
        if (line == null || column == null) {
            return null;
        }
        return new int[]{line, column};
    }

    private static Integer number(String message, int from) {
        int end = from;
        while (end < message.length() && Character.isDigit(message.charAt(end))) {
            end++;
        }
        if (end == from) {
            return null;
        }
        try {
            return Integer.valueOf(message.substring(from, end));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
