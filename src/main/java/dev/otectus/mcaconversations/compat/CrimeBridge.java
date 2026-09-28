package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.ModList;

import java.util.Optional;

/**
 * The classloading gate in front of everything MCA: Crime-shaped (1.8.0) — the exact sibling of
 * {@link QuestsBridge}. This class has <b>no</b> {@code dev.otectus.mcacrime.*} imports;
 * {@code compat.crime.ConversationsCrimeCompat} (which does) is only <em>named</em> here, so the JVM
 * does not load any Crime class until after the {@link ModList#isLoaded} check, and
 * {@code catch (Throwable)} absorbs {@code NoClassDefFoundError}/{@code NoSuchMethodError} from API drift.
 *
 * <p>MCA: Crime is an <b>optional</b> integration. Without it the {@code conversations_crime_*} dialogue
 * conditions score 0, the {@code crime.*} context fields read UNAVAILABLE, no witness memory is
 * written and no guard line is voiced — Conversations behaves exactly as it does today. With MCA:
 * Reputation also installed, Crime's deeds already reach villagers' gossip through Reputation's
 * candidates; this bridge adds what Reputation cannot know: the live legal state of the player in
 * front of the villager (wanted, band, in custody) and what a witness personally saw.
 */
public final class CrimeBridge {

    /** Everything Conversations asks of MCA: Crime, in Minecraft and Java types. Every method fails safe. */
    public interface CrimeQueries {
        /** Whether MCA: Crime holds a warrant on this player right now. */
        boolean isWanted(ServerPlayer player);

        /** The player's band as a lowercase legal word: {@code lawful}, {@code neutral} or {@code outlaw}. */
        Optional<String> band(ServerPlayer player);

        /** The player's current Heat. */
        long heat(ServerPlayer player);

        /** Whether the player is serving a jail sentence right now. */
        boolean isJailed(ServerPlayer player);
    }

    private static volatile boolean available;
    private static volatile CrimeQueries queries;

    private CrimeBridge() {
    }

    /** Installs the query facade (called by {@code ConversationsCrimeCompat.register()} when Crime is present). */
    public static void setQueries(CrimeQueries impl) {
        queries = impl;
    }

    /** The query facade, or {@code null} when MCA: Crime is absent — callers must null-check. */
    public static CrimeQueries queries() {
        return queries;
    }

    /** True once MCA: Crime is confirmed present and the integration registered successfully. */
    public static boolean isAvailable() {
        return available;
    }

    /**
     * Called from {@code FMLCommonSetupEvent.enqueueWork}, after {@link McaBridge#tryRegister()} so the
     * crime-aware MCA conditions exist first. Crime is loaded before this mod when present (its
     * mods.toml orders nothing about us, ours declares nothing about it; both bind at setup by name).
     */
    public static void tryRegister() {
        if (!ModList.get().isLoaded("mcacrime")) {
            McaConversations.LOGGER.info("MCA: Crime not present; Conversations crime integration disabled.");
            available = false;
            queries = null;
            return;
        }
        try {
            dev.otectus.mcaconversations.compat.crime.ConversationsCrimeCompat.register();
            available = true;
            McaConversations.LOGGER.info("MCA: Crime detected; Conversations crime integration registered.");
        } catch (Throwable t) {
            available = false;
            queries = null;
            McaConversations.LOGGER.error("Failed to register MCA: Crime integration; crime features disabled. "
                    + "This usually means an incompatible MCA: Crime version.", t);
        }
    }

    /** Test seam. */
    public static void setAvailableForTest(boolean value, CrimeQueries impl) {
        available = value;
        queries = impl;
    }

    /** A cheap, exception-free read of a player's band for content that only wants the word. */
    public static Optional<String> band(ServerPlayer player) {
        CrimeQueries q = queries;
        if (q == null || player == null) {
            return Optional.empty();
        }
        try {
            return q.band(player);
        } catch (Throwable t) {
            return Optional.empty();
        }
    }

    /** Whether this villager is MCA's law: a guard or an archer. Crime's own responder notion, by profession. */
    public static boolean isLaw(Entity villager) {
        return McaCompat.getProfessionId(villager)
                .map(id -> id.contains(":") ? id.substring(id.indexOf(':') + 1) : id)
                .map(path -> "guard".equals(path) || "archer".equals(path))
                .orElse(false);
    }
}
