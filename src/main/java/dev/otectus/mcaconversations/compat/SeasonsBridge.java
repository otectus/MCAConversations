package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;

import java.util.Optional;

/**
 * The classloading gate in front of the optional <b>Serene Seasons</b> integration — the sibling of
 * {@link QuestsBridge}. This class has <b>no</b> {@code sereneseasons.*} imports;
 * {@code compat.seasons.ConversationsSeasonsCompat} (which reaches Serene Seasons purely by reflection)
 * is only <em>named</em> here, so nothing Serene-Seasons-shaped is touched until after the
 * {@link ModList#isLoaded} check, and {@code catch (Throwable)} additionally absorbs any failure.
 *
 * <p>Serene Seasons is a <b>soft, reflection-only</b> dependency: it is not on our compile classpath, so
 * {@link SeasonQueries} is expressed in pure Minecraft types and the implementing class resolves the
 * Serene Seasons API by name at runtime. When Serene Seasons is absent, {@link #isAvailable()} stays
 * {@code false} and season lines fall back to the calendar season derived from the world day
 * ({@code template.WorldContext#seasonFromDay}); holidays are always calendar-based regardless.
 *
 * <p>Everything that reaches into Serene Seasons must consult {@link #isAvailable()} first.
 */
public final class SeasonsBridge {

    private static volatile boolean available = false;

    /**
     * The season query façade, or {@code null} until (and unless) the Serene Seasons integration installs
     * it. Pure-Minecraft-typed SPI so the MCA-importing resolution code can call through without ever
     * forcing a {@code sereneseasons.*} class to load on an install that lacks the mod.
     */
    private static volatile SeasonQueries queries;

    private SeasonsBridge() {
    }

    /** Season lookup expressed in pure Minecraft types; implemented over the Serene Seasons API by reflection. */
    public interface SeasonQueries {
        /**
         * The current season in {@code level} as one of {@code spring|summer|autumn|winter}, or empty when
         * Serene Seasons isn't tracking that dimension (caller then uses the calendar season). Must fail
         * safe — it runs during MCA dialogue evaluation.
         */
        Optional<String> seasonBucket(Level level);
    }

    /** Installs the query façade (called by {@code ConversationsSeasonsCompat.register()} when Serene Seasons is present). */
    public static void setQueries(SeasonQueries impl) {
        queries = impl;
    }

    /** The query façade, or {@code null} when Serene Seasons is absent — callers must null-check. */
    public static SeasonQueries queries() {
        return queries;
    }

    /** True once Serene Seasons is confirmed present and our integration installed successfully. */
    public static boolean isAvailable() {
        return available;
    }

    /**
     * Called from {@code FMLCommonSetupEvent.enqueueWork}. Never fails the load: an absent mod or any
     * reflection/API-drift failure just leaves the calendar fallback in charge.
     */
    public static void tryRegister() {
        if (!ModList.get().isLoaded("sereneseasons")) {
            McaConversations.LOGGER.info("Serene Seasons not present; Conversations uses calendar seasons.");
            available = false;
            return;
        }
        try {
            dev.otectus.mcaconversations.compat.seasons.ConversationsSeasonsCompat.register();
            available = true;
            McaConversations.LOGGER.info("Serene Seasons detected; Conversations reads seasons from it.");
        } catch (Throwable t) {
            available = false;
            McaConversations.LOGGER.error(
                    "Failed to wire Serene Seasons integration; falling back to calendar seasons.", t);
        }
    }
}
