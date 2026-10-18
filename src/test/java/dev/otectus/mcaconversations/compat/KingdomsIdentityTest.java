package dev.otectus.mcaconversations.compat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The two name tables the Kingdoms bridges resolve, pinned class by class. */
class KingdomsIdentityTest {

    @Test
    void currentGenerationNamesTheRenamedPackageAndFacades() {
        KingdomsIdentity.Generation current = KingdomsIdentity.Generation.CURRENT;
        assertEquals("mcakingdoms", current.modId());
        assertEquals("dev.otectus.mcakingdoms.api.gating.KingdomGateApi", current.className("gating.KingdomGateApi"));
        assertEquals("dev.otectus.mcakingdoms.api.factions.KingdomsFactionsApi",
                current.className("factions.%sFactionsApi"));
        assertEquals("dev.otectus.mcakingdoms.api.McaCommunityRef", current.className("McaCommunityRef"));
        assertEquals("dev.otectus.mcakingdoms.api.civic.CivicNetworkApi", current.className("civic.CivicNetworkApi"));
    }

    @Test
    void legacyGenerationNamesUltimaKingdomsAsItShipped() {
        KingdomsIdentity.Generation legacy = KingdomsIdentity.Generation.LEGACY;
        assertEquals("ultima_kingdoms", legacy.modId());
        assertEquals("com.ultimakingdoms.api.gating.KingdomGateApi", legacy.className("gating.KingdomGateApi"));
        assertEquals("com.ultimakingdoms.api.factions.UltimaFactionsApi", legacy.className("factions.%sFactionsApi"));
        assertEquals("com.ultimakingdoms.api.factions.StandingScope", legacy.className("factions.StandingScope"));
        assertEquals("com.ultimakingdoms.api.civic.CivicNetworkApi", legacy.className("civic.CivicNetworkApi"));
    }

    @Test
    void withoutForgeKingdomsIsAbsentUnderEitherId() {
        assertTrue(KingdomsIdentity.installed().isEmpty());
        assertFalse(KingdomsIdentity.isLoaded("mcakingdoms"));
        assertFalse(KingdomsIdentity.isLoaded("ultima_kingdoms"));
        assertTrue(KingdomsIdentity.isKingdomsModId("ultima_kingdoms"));
        assertFalse(KingdomsIdentity.isKingdomsModId("mcaquests"));
    }
}
