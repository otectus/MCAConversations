package dev.otectus.mcaconversations.util;

import dev.otectus.mcaconversations.McaConversations;

import java.util.function.Supplier;

/**
 * Parse-time containment for our registered dialogue condition/action parsers.
 *
 * <p>MCA's dialogue loader ({@code Dialogues.apply}) has <b>no</b> per-file error handling — an
 * exception thrown while parsing any condition/action aborts the entire datapack reload, which
 * crashes the game during world creation ("Saving World"). MCA's own strict parsers already have
 * this failure mode (e.g. an invalid {@code current_chore} value); ours must not add to it. A
 * malformed {@code conversations_*} entry in any datapack therefore parses to {@code null} (the adapter
 * then acts as a no-op action / never-matching condition) and logs an ERROR naming the bad JSON.
 */
public final class SafeParse {

    private SafeParse() {
    }

    /** Runs {@code parser}; on any failure logs an ERROR with {@code key} + {@code rawJson} and returns null. */
    public static <T> T orNull(String key, Object rawJson, Supplier<T> parser) {
        try {
            return parser.get();
        } catch (Throwable t) {
            McaConversations.LOGGER.error(
                    "Invalid {} JSON in a dialogue/gift datapack entry — this entry will be ignored: {} ({})",
                    key, rawJson, t.toString());
            return null;
        }
    }
}
