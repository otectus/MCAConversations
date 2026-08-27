package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The registry that runs every {@link ConversationContextSource} once and hands back one snapshot
 * (spec §7.2, §7.4).
 *
 * <p>Order is declaration order and it is deliberate: vanilla facts first, then MCA, then this mod's
 * own stores, then optional integrations. Since a field has exactly one owner, order cannot change
 * any value — it only makes reports and traces diffable.
 *
 * <p><b>Failure containment.</b> A source that throws is caught here, reported
 * {@link ContextCapabilities.Status#FAILED}, and has all of its declared fields marked unavailable, so
 * one broken integration costs exactly its own fields and never the conversation. The failure is
 * logged at debug, once per capture, because a source that fails will fail on every interaction and
 * an ERROR line per click is its own outage.
 */
public final class ContextSources {

    private static final List<ConversationContextSource> SOURCES = new ArrayList<>();

    static {
        register(new VanillaContextSource());
        register(new McaContextSource());
        register(new VillageContextSource());
    }

    private ContextSources() {
    }

    /**
     * Adds a source. Idempotent by id, so a reload or a second mod-init pass cannot double-register
     * one and trip the builder's one-field-one-owner check.
     */
    public static synchronized void register(ConversationContextSource source) {
        if (source == null || source.id() == null || source.id().isBlank()) {
            return;
        }
        String id = source.id().trim().toLowerCase(Locale.ROOT);
        SOURCES.removeIf(existing -> existing.id().equalsIgnoreCase(id));
        SOURCES.add(source);
    }

    /** Registered sources in run order. */
    public static synchronized List<ConversationContextSource> registered() {
        return List.copyOf(SOURCES);
    }

    /** Captures the world once for this villager, player and purpose. Never throws. */
    public static ConversationContextSnapshot capture(ContextRequest request) {
        if (request == null) {
            return ConversationContextSnapshot.EMPTY;
        }
        ContextSnapshotBuilder builder = new ContextSnapshotBuilder();
        builder.clock(gameTime(request.villager()), gameDay(request.villager()));

        for (ConversationContextSource source : registered()) {
            if (request.volatileOnly() && !source.hasVolatileFields()) {
                continue;
            }
            builder.beginSource(source.id());
            try {
                if (!source.isAvailable(request)) {
                    builder.allUnavailable(source.declares());
                    builder.reportCapability(ContextCapabilities.Status.ABSENT, "");
                    continue;
                }
                source.contribute(builder, request);
            } catch (Throwable t) {
                // Never let a provider take the conversation with it. The fields it owns go
                // unavailable, which every consumer already has a declared policy for.
                try {
                    builder.allUnavailable(source.declares());
                } catch (Throwable ignored) {
                    // A source whose declares() also throws gets nothing; the capability line says so.
                }
                builder.reportCapability(ContextCapabilities.Status.FAILED, t.getClass().getSimpleName());
                McaConversations.LOGGER.debug("context source '{}' failed; its fields are unavailable",
                        source.id(), t);
            }
        }
        return builder.build();
    }

    /**
     * A turn-boundary refresh: the volatile fields of a fresh capture merged onto {@code pinned}.
     *
     * <p>Returns {@code pinned} unchanged when it is empty, so a caller that never captured cannot
     * accidentally acquire a half-populated snapshot at turn two.
     */
    public static ConversationContextSnapshot refresh(ConversationContextSnapshot pinned,
                                                      ContextRequest request) {
        if (pinned == null || pinned == ConversationContextSnapshot.EMPTY || request == null) {
            return pinned == null ? ConversationContextSnapshot.EMPTY : pinned;
        }
        return pinned.refreshed(capture(request.asRefresh()));
    }

    /**
     * Which source declares each field, for {@code compat-capabilities.md} and the trace.
     *
     * <p>Computed from the declarations rather than from a capture, so the report can state what an
     * absent integration would have supplied without needing a live world.
     */
    public static Map<String, String> fieldOwners() {
        Map<String, String> owners = new LinkedHashMap<>();
        for (ConversationContextSource source : registered()) {
            for (ContextKey<?> key : source.declares()) {
                owners.putIfAbsent(key.id(), source.id());
            }
        }
        return Map.copyOf(owners);
    }

    private static long gameTime(Entity villager) {
        return villager == null || villager.level() == null ? 0L : villager.level().getGameTime();
    }

    private static long gameDay(Entity villager) {
        return villager == null || villager.level() == null ? 0L : villager.level().getDayTime() / 24000L;
    }
}
