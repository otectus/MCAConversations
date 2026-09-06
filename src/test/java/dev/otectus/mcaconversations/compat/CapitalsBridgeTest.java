package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.support.TestPaths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The MCA Capitals seam with Capitals absent, which is the state most installs are in and the state
 * the rest of this suite runs in.
 *
 * <p>{@code CapitalsCompat.init()} itself is deliberately not exercised: it calls
 * {@code ModList.get()}, which needs a running NeoForge. The discipline that actually matters here is
 * checked by source scanning instead, exactly as {@code TownsteadBridgeTest} does for Townstead.
 */
class CapitalsBridgeTest {

    private static final Path SOURCE_ROOT = TestPaths.of("src/main/java/dev/otectus/mcaconversations");

    @AfterEach
    void restoreDefault() {
        CapitalsCompat.resetForTest();
    }

    // --- the absent-mod contract -----------------------------------------------------------------

    @Test
    void theBridgeIsNeverNull() {
        assertNotNull(CapitalsBridge.Holder.get(),
                "Query code is written against a real object rather than a null facade; a null here "
                        + "would mean a branch at every call site, and eventually a missed one.");
    }

    @Test
    void absenceIsQuietAndNeutral() {
        CapitalsBridge bridge = CapitalsBridge.Holder.get();

        assertEquals(CapitalsStatus.ABSENT, bridge.status());
        assertFalse(bridge.isAvailable());
        assertTrue(bridge.capabilities().isEmpty());
        assertTrue(bridge.unresolvedMembers().isEmpty(),
                "An absent mod is not a partial binding; nothing should be reported as a miss.");
        for (CapitalsCapability capability : CapitalsCapability.values()) {
            assertFalse(bridge.has(capability), capability + " must not report as bound when absent");
        }
    }

    @Test
    void everyReadIsEmptyRatherThanNullOrThrowing() {
        CapitalsBridge bridge = CapitalsBridge.Holder.get();
        UUID anyone = UUID.randomUUID();

        assertTrue(bridge.courtOfVillage(null, 7).isEmpty());
        assertTrue(bridge.courtOfResident(null, anyone).isEmpty());
        assertTrue(bridge.allCourts(null).isEmpty());
        assertTrue(bridge.levelOf(null, null).isEmpty());
        assertSame(CapitalStandingView.none(), bridge.standingOf(null, null, anyone, null));
        assertTrue(bridge.relationsOf(null, null).isEmpty());
        assertTrue(bridge.chronicleSince(null, null, 0, 5).isEmpty());
        assertTrue(bridge.declaredCapitalOf(null, anyone).isEmpty());
        assertEquals("", bridge.displayName(null, null, anyone));
    }

    @Test
    void theNeutralStandingReadsAsNoTitleAndNoHouse() {
        CapitalStandingView standing = CapitalStandingView.none();

        assertEquals("none", standing.titleId());
        assertEquals(0, standing.titleRank());
        assertEquals("none", standing.office());
        // "unknown", not "neutral": a villager the Crown has no opinion of is neutral, but with the
        // mod absent we have not asked, and content must be able to tell those apart.
        assertEquals("unknown", standing.crownStanding());
        assertFalse(standing.royalHousehold());
        assertFalse(standing.royalGuard());
        assertFalse(standing.disgraced());
        assertFalse(standing.hasHouse());
    }

    @Test
    void aTestBridgeCanBeInstalledAndCleared() {
        CapitalsCompat.setBridgeForTest(new StubBridge());
        assertEquals(CapitalsStatus.FULL, CapitalsBridge.Holder.get().status());

        CapitalsCompat.resetForTest();
        assertEquals(CapitalsStatus.ABSENT, CapitalsBridge.Holder.get().status());
    }

    // --- source discipline -----------------------------------------------------------------------

    /**
     * Deliberately looks for an {@code import}, not for any mention of the package. The seam classes
     * talk about Capitals' package in their javadoc, because the rule they exist to enforce is worth
     * stating where someone editing them will read it, and prose is not linkage. What would be
     * linkage is an import, so that is what this rejects; anything subtler is caught in bytecode by
     * {@code NoCapitalsStaticLinkTest}.
     */
    @Test
    void nothingAnywhereImportsCapitals() throws IOException {
        List<String> offenders = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(SOURCE_ROOT)) {
            paths.filter(p -> p.toString().endsWith(".java")).forEach(p -> {
                try {
                    if (Files.readString(p, StandardCharsets.UTF_8)
                            .contains("import com.majesttyx.mcacapitals")) {
                        offenders.add(SOURCE_ROOT.relativize(p).toString().replace('\\', '/'));
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
        assertEquals(List.of(), offenders,
                "CapitalsBinding resolves every member by name; importing one would hard-link this "
                        + "mod to a single MCA package layout through Capitals descriptors.");
    }

    @Test
    void theGateChecksModListAndSwallowsEverything() throws IOException {
        String source = Files.readString(SOURCE_ROOT.resolve("compat/CapitalsCompat.java"),
                StandardCharsets.UTF_8);

        assertTrue(source.contains("ModList.get().isLoaded(MOD_ID)"),
                "The implementation must only be reached after Forge confirms Capitals is loaded.");
        assertTrue(source.contains("Class.forName("),
                "The implementation class must be named as a string, never referenced.");
        assertTrue(source.contains("catch (Throwable"),
                "A broken integration must disable itself, not take the mod down with it.");
        assertFalse(source.contains("com/majesttyx"),
                "The package root must be stored dotted so the bytecode tripwire needs no exemption.");
    }

    /**
     * Read from source rather than by calling {@code isFeatureEnabled}, because a COMMON spec value
     * throws until its file is loaded and no file is loaded in a unit run. What matters is that each
     * feature id has a case at all: an id with no case falls through to the default and scores as
     * enabled forever, so a {@code conversations_disabled} sink on it could never fire.
     */
    @Test
    void everyCapitalFeatureIdHasItsOwnCase() throws IOException {
        String source = Files.readString(SOURCE_ROOT.resolve("McaConversationsConfig.java"),
                StandardCharsets.UTF_8);

        for (String feature : List.of("capitals", "capital_topics", "capital_news",
                "capital_diplomacy")) {
            assertTrue(source.contains("case \"" + feature + "\""),
                    feature + " has no case in isFeatureEnabled, so it can never be switched off.");
        }
        assertTrue(source.contains("b.push(\"capitals\")"),
                "The [capitals] config section must exist for those switches to read.");
    }

    @Test
    void capitalsIsDeclaredOptionalAndOrderedAfter() throws IOException {
        String toml = Files.readString(TestPaths.of("src/main/resources/META-INF/neoforge.mods.toml"),
                StandardCharsets.UTF_8);
        int block = toml.indexOf("modId=\"mcacapitals\"");

        assertTrue(block > 0, "mods.toml must declare the optional mcacapitals dependency.");
        String declaration = toml.substring(block, Math.min(toml.length(), block + 220));
        assertTrue(declaration.contains("type=\"optional\""),
                "Capitals must never become a hard dependency.");
        assertTrue(declaration.contains("ordering=\"AFTER\""),
                "We read Capitals' registry, so it must load first.");
    }

    /** A bridge that claims everything bound, for the install-and-clear test only. */
    private static final class StubBridge implements CapitalsBridge {

        @Override
        public CapitalsStatus status() {
            return CapitalsStatus.FULL;
        }

        @Override
        public Set<CapitalsCapability> capabilities() {
            return Set.of(CapitalsCapability.values());
        }

        @Override
        public Optional<CapitalCourtView> courtOfVillage(net.minecraft.server.level.ServerLevel level,
                                                         int villageId) {
            return Optional.empty();
        }

        @Override
        public Optional<CapitalCourtView> courtOfResident(net.minecraft.server.level.ServerLevel level,
                                                          UUID villager) {
            return Optional.empty();
        }

        @Override
        public List<CapitalCourtView> allCourts(net.minecraft.server.MinecraftServer server) {
            return List.of();
        }

        @Override
        public Optional<net.minecraft.server.level.ServerLevel> levelOf(
                net.minecraft.server.MinecraftServer server, CapitalCourtView court) {
            return Optional.empty();
        }

        @Override
        public CapitalStandingView standingOf(net.minecraft.server.level.ServerLevel level,
                                              CapitalCourtView court, UUID villager,
                                              net.minecraft.world.entity.Entity loadedEntity) {
            return CapitalStandingView.none();
        }

        @Override
        public List<CapitalRelationView> relationsOf(net.minecraft.server.level.ServerLevel level,
                                                     CapitalCourtView court) {
            return List.of();
        }

        @Override
        public List<CapitalChronicleEventView> chronicleSince(
                net.minecraft.server.level.ServerLevel level, CapitalCourtView court, int fromIndex,
                int max) {
            return List.of();
        }

        @Override
        public Optional<UUID> declaredCapitalOf(net.minecraft.server.level.ServerLevel level,
                                                UUID player) {
            return Optional.empty();
        }

        @Override
        public String displayName(net.minecraft.server.level.ServerLevel level, CapitalCourtView court,
                                  UUID entity) {
            return "";
        }
    }
}
