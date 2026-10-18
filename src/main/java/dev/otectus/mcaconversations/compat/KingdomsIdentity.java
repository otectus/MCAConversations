package dev.otectus.mcaconversations.compat;

import net.minecraftforge.fml.ModList;

import java.util.Optional;

/**
 * MCA: Kingdoms' identity, current and before its rename. It was Ultima Kingdoms until 0.1.1, when its
 * loader id, its API package and three facade names changed. Both generations are recognised for one
 * release, so a pack still shipping the old jar keeps its kingdom gates and civic contacts; the current
 * one wins if, improbably, both are installed. The bridges resolve every name through here, so they never
 * mix the two.
 */
public final class KingdomsIdentity {

    /** The loader id MCA: Kingdoms registers under. */
    public static final String MOD_ID = "mcakingdoms";
    /** The loader id the same mod used as Ultima Kingdoms, before 0.1.1. */
    public static final String LEGACY_MOD_ID = "ultima_kingdoms";

    /** One generation: its loader id, its API package and the prefix of three facade class names. */
    public enum Generation {
        CURRENT(MOD_ID, "dev.otectus.mcakingdoms.api", "Kingdoms"),
        LEGACY(LEGACY_MOD_ID, "com.ultimakingdoms.api", "Ultima");

        private final String modId;
        private final String apiPackage;
        private final String facadePrefix;

        Generation(String modId, String apiPackage, String facadePrefix) {
            this.modId = modId;
            this.apiPackage = apiPackage;
            this.facadePrefix = facadePrefix;
        }

        public String modId() {
            return modId;
        }

        /** A class name relative to the API package; {@code %s} stands for the facade prefix. */
        public String className(String relative) {
            return apiPackage + '.' + relative.replace("%s", facadePrefix);
        }
    }

    private KingdomsIdentity() {
    }

    /** The installed generation, the current one first; empty when Kingdoms is absent or Forge is not up. */
    public static Optional<Generation> installed() {
        try {
            ModList mods = ModList.get();
            if (mods == null) return Optional.empty();
            if (mods.isLoaded(MOD_ID)) return Optional.of(Generation.CURRENT);
            if (mods.isLoaded(LEGACY_MOD_ID)) return Optional.of(Generation.LEGACY);
        } catch (Throwable unavailableForge) {
            // A unit test or a very early call: Kingdoms counts as absent.
        }
        return Optional.empty();
    }

    /** Whether {@code modId} names MCA: Kingdoms under either of its loader ids. */
    public static boolean isKingdomsModId(String modId) {
        return MOD_ID.equals(modId) || LEGACY_MOD_ID.equals(modId);
    }

    /**
     * Whether a mod is loaded, counting MCA: Kingdoms as present under either loader id, so content that
     * names one id keeps working beside the other jar.
     */
    public static boolean isLoaded(String modId) {
        if (isKingdomsModId(modId)) return installed().isPresent();
        try {
            ModList mods = ModList.get();
            return mods != null && mods.isLoaded(modId);
        } catch (Throwable unavailableForge) {
            return false;
        }
    }
}
