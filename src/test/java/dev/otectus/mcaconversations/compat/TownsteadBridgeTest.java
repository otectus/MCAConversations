package dev.otectus.mcaconversations.compat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The Townstead seam with Townstead absent, which is the state most installs are in and the state the
 * rest of this suite runs in.
 *
 * <p>{@code TownsteadCompat.init()} itself is deliberately not exercised: it calls
 * {@code ModList.get()}, which needs a running Forge. The same is true of the sibling bridges, and
 * the discipline that actually matters here is checked by source scanning instead, exactly as
 * {@code ReputationIntegrationTest} does for MCA: Reputation.
 */
class TownsteadBridgeTest {

    private static final Path SOURCE_ROOT = Paths.get("src", "main", "java", "dev", "otectus",
            "mcaconversations");

    @AfterEach
    void restoreDefault() {
        TownsteadCompat.resetForTest();
    }

    // --- the absent-mod contract -----------------------------------------------------------------

    @Test
    void theBridgeIsNeverNull() {
        assertNotNull(TownsteadBridge.Holder.get(),
                "Query code is written against a real object rather than a null facade (spec 5.2); a "
                        + "null here would mean a branch at every call site, and eventually a missed one.");
    }

    @Test
    void absenceIsQuietAndNeutral() {
        TownsteadBridge bridge = TownsteadBridge.Holder.get();

        assertEquals(TownsteadStatus.ABSENT, bridge.status());
        assertFalse(bridge.isAvailable());
        assertTrue(bridge.capabilities().isEmpty());
        assertEquals("", bridge.detectedVersion());
        assertTrue(bridge.variant().isEmpty());
        assertTrue(bridge.unresolvedMembers().isEmpty(),
                "An absent mod is not a partial binding; nothing should be reported as a miss.");
        for (TownsteadCapability capability : TownsteadCapability.values()) {
            assertFalse(bridge.has(capability), capability + " must not report as bound when absent");
        }
    }

    @Test
    void everyReadReturnsTheEmptyViewRatherThanNull() {
        TownsteadBridge bridge = TownsteadBridge.Holder.get();

        assertSame(TownsteadVillagerView.EMPTY, bridge.villager(null));
        assertSame(TownsteadCalendarView.EMPTY, bridge.calendar(null));
        assertSame(TownsteadBuildingView.EMPTY, bridge.buildingAt(null, null));
        assertSame(TownsteadRootView.EMPTY, bridge.root(null));
        assertSame(TownsteadPersonalityView.EMPTY, bridge.personality("anything"));
        assertSame(TownsteadSpiritView.EMPTY, bridge.spiritForVillage(null, 7));
        assertTrue(bridge.contextTags(null).isEmpty());
        assertTrue(bridge.learnedSkills(null).isEmpty());
        assertFalse(bridge.hasSkill(null, "townstead:artisan_baking"));
        assertFalse(bridge.isKnownSpirit("nautical"));
        assertFalse(bridge.isReactionLocked(null, 0L));
    }

    @Test
    void everyWriteIsANoOpAndNeverThrows() {
        TownsteadBridge bridge = TownsteadBridge.Holder.get();

        assertFalse(bridge.fireReaction(null, null, null, null, java.util.Set.of()));
        bridge.markHeartChange(null, 3, 0L);
        bridge.dialogueOpen(null, null, 0L);
        bridge.dialogueClose(null, null, 0L);
    }

    @Test
    void theEmptyVillagerViewReadsAsNeutralAllTheWayDown() {
        TownsteadVillagerView villager = TownsteadVillagerView.EMPTY;

        assertTrue(villager.isEmpty());
        assertTrue(villager.dominantHeritage().isEmpty());
        assertEquals(0f, villager.heritageOf("townstead:human"));
        assertFalse(villager.profession().employed());
        assertFalse(villager.personality().custom());
        // Idle, not working: an absent Townstead must never defer a topic or soften a greeting.
        assertFalse(villager.schedule().working());
        assertEquals("none", villager.needs().primaryNeed());
        assertFalse(villager.needs().inCrisis());
        assertEquals("adult", villager.life().ageDescription());
    }

    @Test
    void aTestBridgeCanBeInstalledAndCleared() {
        TownsteadCompat.setBridgeForTest(new StubBridge());
        assertEquals(TownsteadStatus.FULL, TownsteadBridge.Holder.get().status());

        TownsteadCompat.resetForTest();
        assertEquals(TownsteadStatus.ABSENT, TownsteadBridge.Holder.get().status());
    }

    // --- source discipline -----------------------------------------------------------------------
    //
    // The compiler cannot enforce any of this, and NoTownsteadStaticLinkTest only sees bytecode that
    // was compiled at all. These scans catch the mistake before it becomes a NoClassDefFoundError on
    // somebody's dedicated server.

    /**
     * Deliberately looks for an {@code import}, not for any mention of the package.
     *
     * <p>The seam classes talk about Townstead's package in their javadoc, because the rule
     * they exist to enforce is worth stating where someone editing them will read it, and prose
     * is not linkage. What would be linkage is an import, so that is what this rejects.
     * Everything subtler than an import (a fully qualified name in an expression, a field type,
     * a method signature) is caught in bytecode by {@code NoTownsteadStaticLinkTest}, which is
     * the authority here; this test just fails earlier and says why.
     */
    @Test
    void nothingOutsideTheGuardedPackageImportsTownstead() throws IOException {
        List<String> offenders = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(SOURCE_ROOT)) {
            paths.filter(p -> p.toString().endsWith(".java")).forEach(p -> {
                String relative = SOURCE_ROOT.relativize(p).toString().replace('\\', '/');
                if (relative.startsWith("compat/townstead/")) {
                    return;
                }
                try {
                    if (Files.readString(p, StandardCharsets.UTF_8)
                            .contains("import com.aetherianartificer.townstead")) {
                        offenders.add(relative);
                    }
                } catch (IOException e) {
                    throw new java.io.UncheckedIOException(e);
                }
            });
        }
        assertEquals(List.of(), offenders,
                "Only compat/townstead/ may reach Townstead, and even there only by name.");
    }

    /**
     * The guarded package must not import Townstead either. It is reflection-only end to end,
     * which is exactly what lets one jar serve both MCA package layouts: Townstead is compiled
     * against MCA, so importing any of its types would drag a relocated MCA type in behind it.
     */
    @Test
    void notEvenTheGuardedPackageImportsTownstead() throws IOException {
        List<String> offenders = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(SOURCE_ROOT.resolve("compat").resolve("townstead"))) {
            paths.filter(p -> p.toString().endsWith(".java")).forEach(p -> {
                try {
                    if (Files.readString(p, StandardCharsets.UTF_8)
                            .contains("import com.aetherianartificer.townstead")) {
                        offenders.add(p.getFileName().toString());
                    }
                } catch (IOException e) {
                    throw new java.io.UncheckedIOException(e);
                }
            });
        }
        assertEquals(List.of(), offenders,
                "TownsteadBinding resolves every member by name; importing one would hard-link "
                        + "this mod to a single MCA package layout through Townstead descriptors.");
    }

    @Test
    void theGateChecksModListAndSwallowsEverything() throws IOException {
        String source = Files.readString(SOURCE_ROOT.resolve("compat/TownsteadCompat.java"),
                StandardCharsets.UTF_8);

        assertTrue(source.contains("ModList.get().isLoaded(MOD_ID)"),
                "The implementation must only be reached after Forge confirms Townstead is loaded.");
        assertTrue(source.contains("Class.forName("),
                "The implementation class must be named as a string, never referenced.");
        assertTrue(source.contains("catch (Throwable"),
                "A broken integration must disable itself, not take the mod down with it.");
        assertFalse(source.contains("com/aetherianartificer"),
                "The package root must be stored dotted so the bytecode tripwire needs no exemption.");
    }

    @Test
    void townsteadIsDeclaredOptionalAndOrderedAfter() throws IOException {
        String toml = Files.readString(Paths.get("src", "main", "resources", "META-INF", "mods.toml"),
                StandardCharsets.UTF_8);
        int block = toml.indexOf("modId=\"townstead\"");

        assertTrue(block > 0, "mods.toml must declare the optional townstead dependency.");
        String declaration = toml.substring(block, Math.min(toml.length(), block + 220));
        assertTrue(declaration.contains("mandatory=false"),
                "Townstead must never become a hard dependency (spec 4.1).");
        assertTrue(declaration.contains("ordering=\"AFTER\""),
                "We read Townstead registries and data, so it must load first.");
    }

    /** A bridge that claims everything bound, for the install-and-clear test only. */
    private static final class StubBridge implements TownsteadBridge {

        @Override
        public TownsteadStatus status() {
            return TownsteadStatus.FULL;
        }

        @Override
        public java.util.Set<TownsteadCapability> capabilities() {
            return java.util.Set.of(TownsteadCapability.values());
        }

        @Override
        public String detectedVersion() {
            return "0.7.6+1.20.1";
        }

        @Override
        public java.util.Optional<String> variant() {
            return java.util.Optional.of("forge.net.mca");
        }

        @Override
        public TownsteadVillagerView villager(net.minecraft.world.entity.Entity entity) {
            return TownsteadVillagerView.EMPTY;
        }

        @Override
        public TownsteadCalendarView calendar(net.minecraft.server.MinecraftServer server) {
            return TownsteadCalendarView.EMPTY;
        }

        @Override
        public TownsteadBuildingView buildingAt(net.minecraft.server.level.ServerLevel level,
                                                net.minecraft.core.BlockPos pos) {
            return TownsteadBuildingView.EMPTY;
        }

        @Override
        public TownsteadRootView root(net.minecraft.resources.ResourceLocation id) {
            return TownsteadRootView.EMPTY;
        }

        @Override
        public TownsteadPersonalityView personality(String personalityId) {
            return TownsteadPersonalityView.EMPTY;
        }

        @Override
        public TownsteadSpiritView spiritForVillage(net.minecraft.server.level.ServerLevel level,
                                                    int villageId) {
            return TownsteadSpiritView.EMPTY;
        }

        @Override
        public java.util.Set<String> contextTags(net.minecraft.world.entity.Entity villager) {
            return java.util.Set.of();
        }

        @Override
        public java.util.Set<String> learnedSkills(net.minecraft.world.entity.Entity villager) {
            return java.util.Set.of();
        }

        @Override
        public boolean hasSkill(net.minecraft.world.entity.Entity villager, String skillId) {
            return false;
        }

        @Override
        public boolean isKnownSpirit(String spiritId) {
            return false;
        }

        @Override
        public boolean isReactionLocked(net.minecraft.world.entity.Entity villager, long gameTime) {
            return false;
        }

        @Override
        public boolean fireReaction(net.minecraft.server.level.ServerLevel level,
                                    net.minecraft.world.entity.Entity villager,
                                    net.minecraft.server.level.ServerPlayer player,
                                    net.minecraft.resources.ResourceLocation reaction,
                                    java.util.Set<String> tags) {
            return false;
        }

        @Override
        public void markHeartChange(net.minecraft.world.entity.Entity villager, int measuredDelta,
                                    long gameTime) {
        }

        @Override
        public void dialogueOpen(net.minecraft.world.entity.Entity villager,
                                 net.minecraft.server.level.ServerPlayer player, long gameTime) {
        }

        @Override
        public void dialogueClose(net.minecraft.world.entity.Entity villager,
                                  net.minecraft.server.level.ServerPlayer player, long gameTime) {
        }
    }
}
