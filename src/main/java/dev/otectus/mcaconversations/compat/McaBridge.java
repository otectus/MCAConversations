package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraftforge.fml.ModList;

/**
 * The classloading gate in front of everything MCA-shaped. This class has <b>no</b>
 * {@code forge.net.mca.*} imports; {@code compat.mca.ConversationsMcaRegistrar} (which does) is only
 * <em>named</em> here, so the JVM does not load any MCA class until after the
 * {@link ModList#isLoaded} check, and {@code catch (Throwable)} additionally absorbs
 * {@code NoClassDefFoundError}/{@code NoSuchMethodError} from MCA API drift.
 *
 * <p>All Forge-bus event handlers must consult {@link #isAvailable()} before calling into
 * {@link McaCompat}.
 */
public final class McaBridge {

    private static volatile boolean available = false;

    private McaBridge() {
    }

    /** True once MCA is confirmed present and our dialogue extensions registered successfully. */
    public static boolean isAvailable() {
        return available;
    }

    /** Called from {@code FMLCommonSetupEvent.enqueueWork} — main thread, before datapack parse. */
    public static void tryRegister() {
        if (!ModList.get().isLoaded("mca")) {
            McaConversations.LOGGER.warn("MCA Reborn not present; MCA: Conversations features disabled.");
            available = false;
            return;
        }
        try {
            dev.otectus.mcaconversations.compat.mca.ConversationsMcaRegistrar.register();
            available = true;
            McaConversations.LOGGER.info("MCA: Conversations dialogue conditions/actions registered with MCA.");
        } catch (Throwable t) {
            available = false;
            McaConversations.LOGGER.error(
                    "Failed to register MCA dialogue extensions; Conversations features disabled. "
                            + "This usually means an incompatible MCA Reborn version.", t);
        }
    }
}
