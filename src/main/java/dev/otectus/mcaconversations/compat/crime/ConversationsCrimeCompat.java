package dev.otectus.mcaconversations.compat.crime;

import dev.otectus.mcacrime.api.CrimeDialogueHooks;
import dev.otectus.mcacrime.api.McaCrimeApi;
import dev.otectus.mcacrime.crime.Band;
import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.CrimeBridge;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;

import java.util.Locale;
import java.util.Optional;

/**
 * The single class (with its two neighbours) that imports {@code dev.otectus.mcacrime.*} — the exact
 * sibling of {@code compat.quests.ConversationsQuestsCompat}. Only loaded through
 * {@link CrimeBridge#tryRegister()} after the mod-present check, so an install without Crime never
 * touches a Crime class; compiled against Crime's vendored compile-only API jar, so only its published
 * surface can be named. Every method fails safe: {@code try/catch (Throwable)} + a documented default,
 * because the query methods run during MCA dialogue evaluation.
 */
public final class ConversationsCrimeCompat implements CrimeBridge.CrimeQueries {

    private ConversationsCrimeCompat() {
    }

    /**
     * Sole entry point from {@link CrimeBridge#tryRegister()}: installs the query facade, the witness-memory
     * event subscriber and the guard voice resolver. The calls below are also the version handshake: MCA:
     * Crime publishes no API generation constant, so a {@code NoSuchMethodError} out of here is what the
     * bridge reports as an incompatible build.
     */
    public static void register() {
        CrimeBridge.setQueries(new ConversationsCrimeCompat());
        MinecraftForge.EVENT_BUS.register(new ConversationsCrimeEvents());
        CrimeDialogueHooks.addResolver(McaConversations.MOD_ID, new CrimeVoiceResolver());
        McaConversations.LOGGER.info("MCA: Crime integration: registered the legal-state queries, the witness "
                + "memory subscriber and the guard voice resolver.");
    }

    @Override
    public boolean isWanted(ServerPlayer player) {
        try {
            return McaCrimeApi.isWanted(player);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Crime isWanted failed; defaulting false", t);
            return false;
        }
    }

    @Override
    public Optional<String> band(ServerPlayer player) {
        try {
            Band band = McaCrimeApi.getBand(player);
            if (band == null) {
                return Optional.empty();
            }
            // Crime's internal names are colours (Blue/Grey/Red); content sees the legal words.
            return Optional.of(switch (band.name().toUpperCase(Locale.ROOT)) {
                case "BLUE" -> "lawful";
                case "GREY" -> "neutral";
                case "RED" -> "outlaw";
                default -> band.name().toLowerCase(Locale.ROOT);
            });
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Crime getBand failed; defaulting empty", t);
            return Optional.empty();
        }
    }

    @Override
    public long heat(ServerPlayer player) {
        try {
            return McaCrimeApi.getHeat(player);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Crime getHeat failed; defaulting 0", t);
            return 0L;
        }
    }

    @Override
    public boolean isJailed(ServerPlayer player) {
        try {
            return ConversationsCrimeEvents.isJailed(player.getUUID()) || McaCrimeApi.sentence(player).isPresent();
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Crime sentence read failed; defaulting false", t);
            return false;
        }
    }
}
