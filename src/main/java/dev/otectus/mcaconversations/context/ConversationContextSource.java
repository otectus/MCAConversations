package dev.otectus.mcaconversations.context;

import java.util.List;

/**
 * One supplier of context fields (spec §7.2).
 *
 * <p>Narrow on purpose. A source declares which fields it owns, reports how far it got, and writes
 * values — it never reads another source's fields, never decides anything, and never mutates
 * narrative state. That keeps every optional integration a self-contained adapter that can be absent
 * without the base snapshot changing in any way except its capability line (spec §21.1).
 *
 * <p><b>A source must not throw.</b> {@link ContextSources} still wraps every call, but the contract
 * is that a source catches its own failures, reports {@link ContextCapabilities.Status#FAILED} and
 * marks its fields unavailable, so one broken provider degrades exactly its own fields.
 */
public interface ConversationContextSource {

    /** Stable lowercase id: {@code vanilla}, {@code mca}, {@code history}, {@code townstead}. */
    String id();

    /**
     * Every field this source may write, whether or not it can write them right now.
     *
     * <p>Declared rather than discovered, so the builder can mark them all unavailable in one call
     * when the source is absent, and so the capability report can list what a missing integration
     * costs without having to run it.
     */
    List<ContextKey<?>> declares();

    /** True when this source can contribute at all right now — mod present, feature enabled. */
    default boolean isAvailable(ContextRequest request) {
        return true;
    }

    /** True when this source owns at least one volatile field and is worth re-running on a refresh. */
    default boolean hasVolatileFields() {
        for (ContextKey<?> key : declares()) {
            if (key.isVolatile()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Writes this source's fields into the snapshot under construction.
     *
     * <p>On a {@link ContextRequest#volatileOnly()} request, write only the volatile fields: the
     * pinned ones are already fixed for the life of the scene and rewriting them is how a bound
     * referent drifts (spec §7.4).
     */
    void contribute(ContextSnapshotBuilder builder, ContextRequest request);
}
