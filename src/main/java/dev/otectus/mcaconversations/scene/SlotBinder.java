package dev.otectus.mcaconversations.scene;

import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.context.ContextKeys;
import dev.otectus.mcaconversations.context.ConversationContextSnapshot;
import dev.otectus.mcaconversations.history.EpisodeRecord;
import dev.otectus.mcaconversations.history.NarrativeValue;
import net.minecraft.server.level.ServerLevel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Binds a scene's declared slots to real, typed, currently-true values (spec §10.4, §20.3).
 *
 * <p>This is where "specificity must have a source" stops being a principle and becomes a function.
 * Every value it produces comes from one of exactly three places — the bound episode's payload, the
 * context snapshot, or the scene's own declared constant — and each binding is recorded with its
 * provenance so the trace can name where a mentioned field, sister or promise came from (spec §4.2).
 *
 * <p><b>Binding is all-or-nothing.</b> A scene whose slots cannot all bind is not eligible, full stop.
 * There is no partial binding and no substituting a vaguer value for a missing one, because a sentence
 * built to name a specific thing reads badly with a generic one dropped in — that is what the scene's
 * declared fallback route is for (spec §10.3).
 */
public final class SlotBinder {

    /** The outcome of an attempted binding. */
    public record Result(boolean bound, Map<String, NarrativeValue> slots,
                         Map<String, String> provenance, String failedSlot) {

        static Result success(Map<String, NarrativeValue> slots, Map<String, String> provenance) {
            return new Result(true, Map.copyOf(slots), Map.copyOf(provenance), "");
        }

        static Result failure(String slot) {
            return new Result(false, Map.of(), Map.of(), slot);
        }
    }

    private SlotBinder() {
    }

    /**
     * Attempts to bind every slot {@code scene} declares.
     *
     * @param scene    the scene whose slots to bind
     * @param episode  the episode the scene is about, when it has one
     * @param snapshot the frozen world state
     * @param level    the level, needed to validate that a named person is still alive
     */
    public static Result bind(SceneDefinition scene, Optional<EpisodeRecord> episode,
                              ConversationContextSnapshot snapshot, ServerLevel level) {
        if (scene == null) {
            return Result.failure("");
        }
        if (scene.requiredSlots().isEmpty()) {
            return Result.success(Map.of(), Map.of());
        }
        Map<String, NarrativeValue> bound = new LinkedHashMap<>();
        Map<String, String> provenance = new LinkedHashMap<>();

        for (Map.Entry<String, SlotType> entry : scene.requiredSlots().entrySet()) {
            String name = entry.getKey();
            SlotType type = entry.getValue();

            NarrativeValue value = fromEpisode(episode, name).orElse(null);
            String source = "episode." + name;
            if (value == null) {
                value = fromContext(name, type, snapshot).orElse(null);
                source = "context." + name;
            }
            if (value == null || !type.accepts(value)) {
                return Result.failure(name);
            }
            if (type.needsLivenessCheck() && !isStillValid(value, level)) {
                // A named person who has died or left is exactly the referent drift the design exists
                // to prevent. The scene is ineligible; its fallback route says something honest.
                return Result.failure(name);
            }
            bound.put(name, value);
            provenance.put(name, source);
        }
        return Result.success(bound, provenance);
    }

    private static Optional<NarrativeValue> fromEpisode(Optional<EpisodeRecord> episode, String name) {
        return episode.flatMap(record -> record.slot(name));
    }

    /**
     * The small set of slot names the context snapshot can answer directly.
     *
     * <p>Deliberately a short, explicit list rather than a generic "any context field": a slot is a
     * noun in a sentence, and most context fields are bands and flags that do not read as nouns. A
     * scene that wants a field the list does not cover puts it in its episode payload, where the
     * author can choose the token.
     */
    private static Optional<NarrativeValue> fromContext(String name, SlotType type,
                                                        ConversationContextSnapshot snapshot) {
        if (snapshot == null) {
            return Optional.empty();
        }
        return switch (name) {
            case "worksite", "location" -> snapshot.value(ContextKeys.PLACE_LOCATION)
                    .map(NarrativeValue::token);
            case "village" -> snapshot.value(ContextKeys.PLACE_VILLAGE_NAME)
                    .map(value -> NarrativeValue.token(value.toLowerCase(java.util.Locale.ROOT)
                            .replaceAll("[^a-z0-9_]", "_")));
            case "season" -> snapshot.value(ContextKeys.TIME_SEASON).map(NarrativeValue::token);
            case "weather" -> snapshot.value(ContextKeys.WEATHER_STATE).map(NarrativeValue::token);
            case "time_band" -> snapshot.value(ContextKeys.TIME_BAND).map(NarrativeValue::token);
            case "profession" -> snapshot.value(ContextKeys.WORK_PROFESSION_ID)
                    .map(NarrativeValue::registryId);
            case "today" -> snapshot.value(ContextKeys.TIME_DAY).map(NarrativeValue::day);
            case "chore" -> snapshot.value(ContextKeys.WORK_CHORE).map(NarrativeValue::token);
            default -> Optional.empty();
        };
    }

    /**
     * True when a {@link SlotType#PERSON} binding still names somebody who is alive.
     *
     * <p>Checked against MCA's family tree, which knows about villagers whether or not they are
     * loaded — an unloaded neighbour is absent, not dead, and the two must not be confused.
     */
    private static boolean isStillValid(NarrativeValue value, ServerLevel level) {
        Optional<UUID> uuid = value.asUuid();
        if (uuid.isEmpty() || level == null) {
            return false;
        }
        if (McaCompat.isDeceased(level, uuid.get())) {
            return false;
        }
        // A person with no name in the tree cannot be spoken about by name, whatever else is true.
        return McaCompat.familyTreeName(level, uuid.get()).filter(name -> !name.isBlank()).isPresent();
    }
}
