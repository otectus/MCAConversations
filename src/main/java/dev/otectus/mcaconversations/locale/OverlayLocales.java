package dev.otectus.mcaconversations.locale;

import java.util.Locale;
import java.util.Set;

/**
 * The locales this mod ships a <em>complete</em> set of personality overlays for.
 *
 * <p>Kept as plain data with no Minecraft or MCA types so both the client locale hook and the
 * resource lint tests can agree on one list. Adding a locale here without also shipping every
 * {@code <personality>.dialogue.*} key for it would make villagers of that locale fall back to
 * raw English mid-conversation, so {@code LocaleParityTest} fails the build if the two disagree.
 *
 * <p>{@code en_us} and {@code ru_ru} are MCA's own two; they are listed for completeness but the
 * client hook never needs to widen for them because MCA already allows both.
 */
public final class OverlayLocales {

    /** Locales with full personality-overlay coverage. Lowercase MC language codes. */
    public static final Set<String> WITH_PERSONALITY_OVERLAYS = Set.of("en_us", "pt_br");

    /** Locales this mod itself authors overlays for (i.e. excluding MCA's own). */
    public static final Set<String> AUTHORED_BY_THIS_MOD = Set.of("en_us", "pt_br");

    private OverlayLocales() {
    }

    /** True when {@code languageCode} has complete personality overlays shipped with this mod. */
    public static boolean shipsPersonalityOverlays(String languageCode) {
        return languageCode != null
                && WITH_PERSONALITY_OVERLAYS.contains(languageCode.toLowerCase(Locale.ROOT));
    }
}
