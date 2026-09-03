package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import net.neoforged.fml.ModList;

/**
 * The optional-classloading seam for Townstead (Townstead spec 5.1), built to the discipline
 * {@link ReputationBridge} already uses.
 *
 * <h2>The rule this class exists to enforce</h2>
 *
 * <p><b>Nothing in this file, or in anything it can reach without the mod-present check, may name a
 * {@code com.aetherianartificer.townstead} type.</b> Java resolves references lazily, but lazily is
 * not never: a field type, a method signature or a static initialiser mentioning a missing class
 * throws {@code NoClassDefFoundError} the moment something touches it, and on a dedicated server
 * that moment is usually inside an event handler, which takes the server with it.
 *
 * <p>So the real implementation lives entirely under {@code compat.townstead} and is reached through
 * the dotted string below only after {@link ModList} confirms Townstead is present. The class name
 * is stored <em>dotted</em> on purpose: the JVM writes real class references in internal (slash)
 * form, so a dotted literal can never be mistaken for linkage, which is why
 * {@code NoTownsteadStaticLinkTest} needs no exemption for this file.
 *
 * <h2>Logging</h2>
 *
 * <p>Bind once, one INFO on success, one WARN when degraded, and <b>nothing at all when Townstead is
 * simply absent</b>. That is the normal case for most installs and it is not news. Absence is
 * recorded at DEBUG so {@code /conversations compat townstead status} still has something to say.
 */
public final class TownsteadCompat {

    private static final String MOD_ID = "townstead";

    /** Dotted on purpose. See the class javadoc. */
    private static final String IMPLEMENTATION =
            "dev.otectus.mcaconversations.compat.townstead.ReflectiveTownsteadBridge";

    private static boolean initialised;

    private TownsteadCompat() {
    }

    /**
     * Binds Townstead if it is present and enabled. Called once from common setup, after Forge has
     * loaded every mod so {@link ModList} is authoritative, and after {@code McaBridge} because the
     * spirit capability needs an MCA village object that only {@code McaCompat} can produce.
     */
    public static synchronized void init() {
        if (initialised) {
            return;
        }
        initialised = true;

        if (!ModList.get().isLoaded(MOD_ID)) {
            McaConversations.LOGGER.debug("Townstead is not installed; its conditions score 0, its "
                    + "template variables use their neutral fallbacks, and no Townstead state is read.");
            return;
        }
        if (!townsteadEnabled()) {
            McaConversations.LOGGER.info("Townstead is installed but the integration is switched off "
                    + "(townstead.enabled=false); Conversations behaves as though it were absent.");
            return;
        }

        try {
            Class<?> implementation = Class.forName(IMPLEMENTATION);
            TownsteadBridge candidate =
                    (TownsteadBridge) implementation.getDeclaredConstructor().newInstance();
            TownsteadBridge.Holder.set(candidate);
            report(candidate);
        } catch (Throwable t) {
            McaConversations.LOGGER.error("Townstead is installed but the integration could not start; "
                    + "Townstead content stays unavailable and the rest of Conversations is "
                    + "unaffected. Please report this with your Townstead version.", t);
        }
    }

    /** One line, chosen by outcome. Never more: this runs once, and nobody wants a wall of it. */
    private static void report(TownsteadBridge bridge) {
        String version = bridge.detectedVersion();
        String variant = bridge.variant().orElse("unknown");
        switch (bridge.status()) {
            case FULL -> McaConversations.LOGGER.info(
                    "Townstead {} detected (MCA root: {}); {} capabilities bound. Needs, schedules, "
                            + "calendar, roots, professions, skills, buildings, village spirit and "
                            + "context tags are now conversation state.",
                    version, variant, bridge.capabilities().size());
            case PARTIAL -> McaConversations.LOGGER.warn(
                    "Townstead {} detected (MCA root: {}) but only {} of {} capabilities bound. Content "
                            + "needing the rest stays unavailable. Run '/conversations compat townstead "
                            + "status' to see which, and report it with your Townstead version. "
                            + "Unresolved: {}",
                    version, variant, bridge.capabilities().size(),
                    TownsteadCapability.values().length, bridge.unresolvedMembers());
            case INCOMPATIBLE -> McaConversations.LOGGER.warn(
                    "Townstead {} is installed but none of its API could be bound, so the integration "
                            + "is off. This usually means an unsupported Townstead version. Run "
                            + "'/conversations compat townstead status' for details.",
                    version);
            default -> McaConversations.LOGGER.debug(
                    "Townstead bridge reported {} after binding.", bridge.status());
        }
    }

    /** Config is not loaded during unit tests or very early startup; treat that as enabled. */
    private static boolean townsteadEnabled() {
        try {
            return McaConversationsConfig.COMMON.townsteadEnabled.get();
        } catch (Throwable t) {
            return true;
        }
    }

    /** Test seam: force a bridge. Production calls {@link #init()} exactly once from mod setup. */
    public static synchronized void setBridgeForTest(TownsteadBridge replacement) {
        TownsteadBridge.Holder.set(replacement);
        initialised = true;
    }

    /** Test seam: restore the absent-mod default. */
    public static synchronized void resetForTest() {
        TownsteadBridge.Holder.set(null);
        initialised = false;
    }
}
