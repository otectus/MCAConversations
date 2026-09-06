package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import net.neoforged.fml.ModList;

/**
 * The optional-classloading seam for MCA Capitals, built to the discipline
 * {@link TownsteadCompat} already uses.
 *
 * <h2>The rule this class exists to enforce</h2>
 *
 * <p><b>Nothing in this file, or in anything it can reach without the mod-present check, may name a
 * Capitals type.</b> Java resolves references lazily, but lazily is not never: a field type, a
 * method signature or a static initialiser mentioning a missing class throws
 * {@code NoClassDefFoundError} the moment something touches it, and on a dedicated server that
 * moment is usually inside an event handler, which takes the server with it.
 *
 * <p>So the real implementation lives entirely under {@code compat.capitals} and is reached through
 * the dotted string below only after {@link ModList} confirms Capitals is present. The class name is
 * stored <em>dotted</em> on purpose: the JVM writes real class references in internal (slash) form,
 * so a dotted literal can never be mistaken for linkage, which is why
 * {@code NoCapitalsStaticLinkTest} needs no exemption for this file.
 *
 * <h2>Logging</h2>
 *
 * <p>Bind once, one INFO on success, one WARN when degraded, and <b>nothing at all when Capitals is
 * simply absent</b>. That is the normal case for most installs and it is not news.
 */
public final class CapitalsCompat {

    private static final String MOD_ID = "mcacapitals";

    /** Dotted on purpose. See the class javadoc. */
    private static final String IMPLEMENTATION =
            "dev.otectus.mcaconversations.compat.capitals.ReflectiveCapitalsBridge";

    private static boolean initialised;

    private CapitalsCompat() {
    }

    /**
     * Binds Capitals if it is present and enabled. Called once from common setup, after Forge has
     * loaded every mod so {@link ModList} is authoritative, and after {@code McaBridge} because a
     * capital's name is the MCA village name that only {@code McaCompat} can produce.
     */
    public static synchronized void init() {
        if (initialised) {
            return;
        }
        initialised = true;

        if (!ModList.get().isLoaded(MOD_ID)) {
            McaConversations.LOGGER.debug("MCA Capitals is not installed; every capital context field "
                    + "stays unavailable and no capital topic, remark or court news is offered.");
            return;
        }
        if (!capitalsEnabled()) {
            McaConversations.LOGGER.info("MCA Capitals is installed but the integration is switched "
                    + "off (capitals.enabled=false); Conversations behaves as though it were absent.");
            return;
        }

        try {
            Class<?> implementation = Class.forName(IMPLEMENTATION);
            CapitalsBridge candidate =
                    (CapitalsBridge) implementation.getDeclaredConstructor().newInstance();
            CapitalsBridge.Holder.set(candidate);
            report(candidate);
        } catch (Throwable t) {
            McaConversations.LOGGER.error("MCA Capitals is installed but the integration could not "
                    + "start; capital content stays unavailable and the rest of Conversations is "
                    + "unaffected. Please report this with your MCA Capitals version.", t);
        }
    }

    /** One line, chosen by outcome. Never more: this runs once, and nobody wants a wall of it. */
    private static void report(CapitalsBridge bridge) {
        switch (bridge.status()) {
            case FULL -> McaConversations.LOGGER.info(
                    "MCA Capitals detected; {} capabilities bound. Courts, titles, houses, "
                            + "diplomacy and the chronicle are now conversation state.",
                    bridge.capabilities().size());
            case PARTIAL -> McaConversations.LOGGER.warn(
                    "MCA Capitals detected but only {} of {} capabilities bound. Content needing the "
                            + "rest stays unavailable. Please report this with your MCA Capitals "
                            + "version. Unresolved: {}",
                    bridge.capabilities().size(), CapitalsCapability.values().length,
                    bridge.unresolvedMembers());
            case INCOMPATIBLE -> McaConversations.LOGGER.warn(
                    "MCA Capitals is installed but none of the members this mod reads could be bound, "
                            + "so the integration is off. This usually means an unsupported MCA "
                            + "Capitals version. Unresolved: {}", bridge.unresolvedMembers());
            default -> McaConversations.LOGGER.debug(
                    "Capitals bridge reported {} after binding.", bridge.status());
        }
    }

    /** True when the bridge bound and the master switch is on. */
    public static boolean isActive() {
        return CapitalsBridge.Holder.get().isAvailable() && capitalsEnabled();
    }

    /** Config is not loaded during unit tests or very early startup; treat that as enabled. */
    private static boolean capitalsEnabled() {
        try {
            return McaConversationsConfig.COMMON.capitalsEnabled.get();
        } catch (Throwable t) {
            return true;
        }
    }

    /** Test seam: force a bridge. Production calls {@link #init()} exactly once from mod setup. */
    public static synchronized void setBridgeForTest(CapitalsBridge replacement) {
        CapitalsBridge.Holder.set(replacement);
        initialised = true;
    }

    /** Test seam: restore the absent-mod default. */
    public static synchronized void resetForTest() {
        CapitalsBridge.Holder.set(null);
        initialised = false;
    }
}
