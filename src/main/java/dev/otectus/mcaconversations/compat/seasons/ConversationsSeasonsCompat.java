package dev.otectus.mcaconversations.compat.seasons;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.SeasonsBridge;
import net.minecraft.world.level.Level;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Optional;

/**
 * The sole class that reaches Serene Seasons, and it does so purely by <b>reflection</b> — Serene Seasons
 * is not on our compile classpath, so there is no {@code sereneseasons.*} import anywhere. Loaded only
 * through {@link SeasonsBridge#tryRegister()}, after the {@code ModList} "sereneseasons" check.
 *
 * <p>Resolves the current season for a level via {@code SeasonHelper.getSeasonState(level).getSeason()}
 * and maps the {@code Season} enum name onto our {@code spring|summer|autumn|winter} buckets. The
 * reflective {@link Method} handles are resolved once in {@link #register()} so any API drift fails there
 * (disabling the bridge → calendar fallback) rather than per dialogue evaluation.
 */
public final class ConversationsSeasonsCompat {

    private ConversationsSeasonsCompat() {
    }

    /** Resolves the Serene Seasons API by name and installs the season query façade. */
    public static void register() throws ReflectiveOperationException {
        Class<?> seasonHelper = Class.forName("sereneseasons.api.season.SeasonHelper");
        Method getSeasonState = seasonHelper.getMethod("getSeasonState", Level.class);
        Class<?> seasonState = Class.forName("sereneseasons.api.season.ISeasonState");
        Method getSeason = seasonState.getMethod("getSeason");

        SeasonsBridge.setQueries(level -> seasonBucket(level, getSeasonState, getSeason));
    }

    private static Optional<String> seasonBucket(Level level, Method getSeasonState, Method getSeason) {
        if (level == null) {
            return Optional.empty();
        }
        try {
            Object state = getSeasonState.invoke(null, level);
            if (state == null) {
                return Optional.empty();
            }
            Object season = getSeason.invoke(state);
            if (!(season instanceof Enum<?> e)) {
                return Optional.empty();
            }
            String name = e.name().toLowerCase(Locale.ROOT); // SPRING/SUMMER/AUTUMN/WINTER
            return switch (name) {
                case "spring", "summer", "autumn", "winter" -> Optional.of(name);
                default -> Optional.empty();
            };
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Serene Seasons season lookup failed; using calendar season", t);
            return Optional.empty();
        }
    }
}
