package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.mca.McaBinding;
import dev.otectus.mcaconversations.compat.mca.McaHandles;
import net.neoforged.fml.ModList;

/**
 * The gate in front of everything MCA-shaped.
 *
 * <p>Two things have to be true before Conversations does any work: MCA must be installed, and
 * {@link McaBinding} must have found a package root it recognises. The second is not implied by the
 * first — MCA renamed its base package from {@code net.mca} to {@code net.conczin.mca} in
 * 7.7.1-alpha.1, and the version number alone cannot tell you which layout is installed, so the
 * binding probes for it. When that probe fails, every feature here is inert but nothing throws.
 *
 * <p>Historically this class caught a {@code NoClassDefFoundError} raised by the first MCA type
 * reference and logged one opaque line: <em>"This usually means an incompatible MCA Reborn
 * version."</em> That was true but useless — it named neither the root that was tried nor the one
 * that was found. {@link McaBinding#init()} now logs exactly that, which is what makes the failure
 * diagnosable from a user's log instead of from a debugger.
 *
 * <p>All Forge-bus event handlers must consult {@link #isAvailable()} before calling into
 * {@link McaCompat}.
 */
public final class McaBridge {

    private static volatile boolean available = false;

    private McaBridge() {
    }

    /** True once MCA is confirmed present, bound, and our dialogue extensions are registered. */
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

        // One line, once, naming the root that matched (or every root that did not).
        McaBinding.init();

        McaBinding.Status status = McaHandles.resolution().status();
        if (status == McaBinding.Status.ABSENT || status == McaBinding.Status.UNBINDABLE) {
            // init() has already said precisely why, including the candidate roots.
            available = false;
            return;
        }

        // PARTIAL is deliberately allowed through: an unresolved member is a constant stub, so the
        // features that needed it read as "off" while everything else keeps working. Disabling the
        // whole mod over one renamed getter is the failure mode this layer exists to avoid.
        try {
            dev.otectus.mcaconversations.compat.mca.ConversationsMcaRegistrar.register();
            available = true;
            McaConversations.LOGGER.info("MCA: Conversations dialogue conditions/actions registered with MCA.");
        } catch (Throwable t) {
            available = false;
            McaConversations.LOGGER.error(
                    "Failed to register MCA dialogue extensions; Conversations features disabled. "
                            + "The MCA binding reported {}, so this is a reshaped registry rather than a "
                            + "moved package.", status, t);
        }
    }
}
