package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.compat.townstead.TownsteadBinding;
import dev.otectus.mcaconversations.support.ClassFileConstants;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Resolves {@link TownsteadBinding#MANIFEST} against a real Townstead jar.
 *
 * <h2>Why this exists</h2>
 *
 * <p>{@code NoTownsteadStaticLinkTest} guarantees no class names a Townstead type, which means the
 * compiler cannot tell anyone when Townstead renames or removes something the manifest asks for: a
 * stale member name would surface as a silently dead capability rather than as a build error. This is
 * the replacement safety net. It walks the whole manifest against the supplied jar and fails if
 * anything is missing, so a moved method shows up here rather than as a topic that never appears.
 *
 * <h2>Why MCA has to be in the loader too</h2>
 *
 * <p>Townstead is compiled against MCA, so {@code TownsteadAPI} declares
 * {@code villager(VillagerEntityMCA)} beside the vanilla-descriptor {@code entity(Entity)} we
 * actually bind. Enumerating a class's methods resolves every parameter type, so with MCA absent
 * {@code getMethods()} throws and the whole class reads as unbound. That is the correct production
 * behaviour, and exactly how a mismatched Townstead/MCA pair degrades, but it would make this probe
 * vacuously green.
 *
 * <h2>Running it</h2>
 *
 * <pre>./gradlew townsteadProbeTest -PtownsteadLegacyJar=/path/townstead-0.7.6+1.20.1.jar</pre>
 *
 * <p>Both {@code -PtownsteadModernJar} and {@code -PtownsteadLegacyJar} are accepted and either may
 * be given alone. Skipped rather than failed when neither is supplied, so an ordinary checkout still
 * runs the suite.
 */
class TownsteadBindingProbeTest {

    private static final String TOWNSTEAD_JARS_PROPERTY = "mcaconversations.townstead.probe.jars";
    private static final String MCA_JARS_PROPERTY = "mcaconversations.probe.jars";

    private static final String API_CLASS = "com.aetherianartificer.townstead.api.TownsteadAPI";
    private static final String HUNGER_DATA = "com.aetherianartificer.townstead.hunger.HungerData";
    private static final String THIRST_DATA = "com.aetherianartificer.townstead.thirst.ThirstData";
    private static final String FATIGUE_DATA = "com.aetherianartificer.townstead.fatigue.FatigueData";

    @Test
    void manifestResolvesAgainstTheRealTownsteadJar() throws Exception {
        List<Path> townstead = requireJars();
        List<Path> all = withMca(townstead);

        try (URLClassLoader loader = loaderFor(all)) {
            TownsteadBinding.Resolution resolution = TownsteadBinding.resolveAgainst(loader);

            assertEquals(List.of(), resolution.unresolved(),
                    "Townstead is missing member(s) the manifest asks for. Either Townstead renamed "
                            + "them (update TownsteadBinding's manifest) or removed them (drop the "
                            + "capability and give TownsteadHandles a fallback). Jars: " + all);
            assertEquals(TownsteadStatus.FULL, resolution.status(),
                    "Every declared capability must bind against a supported Townstead.");
            assertEquals(TownsteadBinding.DECLARED_CAPABILITIES, resolution.capabilities());
            assertNotNull(resolution.variant(),
                    "The MCA package root Townstead was built against could not be read. Diagnostics "
                            + "only, but if TownsteadAPI#villager has gone the variant probe needs a "
                            + "new method to read.");
            System.out.println("[probe] Townstead bound; MCA root = " + resolution.variant()
                    + ", capabilities = " + resolution.capabilities().size());
        }
    }

    /**
     * {@code TownsteadAPI#entity} is the one entry point whose parameter descriptor is vanilla-only,
     * and the entire read facade rests on that. If a future Townstead changed it to take an MCA type,
     * binding would still "work" and then fail at every call, so assert the shape, not just presence.
     */
    @Test
    void theEntryPointTakesAVanillaEntity() throws Exception {
        List<Path> all = withMca(requireJars());

        try (URLClassLoader loader = loaderFor(all)) {
            Class<?> api = Class.forName(API_CLASS, false, loader);
            Method entry = null;
            for (Method candidate : api.getMethods()) {
                if (candidate.getName().equals("entity") && candidate.getParameterCount() == 1) {
                    entry = candidate;
                    break;
                }
            }
            assertNotNull(entry, "TownsteadAPI#entity(Entity) is gone; the read facade has no safe entry.");
            assertEquals("net.minecraft.world.entity.Entity", entry.getParameterTypes()[0].getName(),
                    "TownsteadAPI#entity no longer takes a vanilla Entity. Binding it would drag a "
                            + "relocated MCA type into every villager read.");
        }
    }

    /**
     * The four overload collisions must resolve to the overload the facade actually calls with. A
     * silent flip here would send a UUID where a player belongs, or hand the dispatcher a resolved
     * reaction where an id belongs, and neither would fail until a player triggered it.
     */
    @Test
    void collidingOverloadsResolveToTheIntendedOne() throws Exception {
        List<Path> all = withMca(requireJars());

        try (URLClassLoader loader = loaderFor(all)) {
            assertParameterTypes(loader, "com.aetherianartificer.townstead.reaction.trigger.event"
                            + ".DialogueStateTracker", "onOpen", 3,
                    "net.minecraft.world.entity.LivingEntity",
                    "net.minecraft.server.level.ServerPlayer", "long");
            assertParameterTypes(loader, "com.aetherianartificer.townstead.profession.skill.LearnedSkills",
                    "learned", 1, "net.minecraft.world.entity.LivingEntity");
            assertParameterTypes(loader, "com.aetherianartificer.townstead.profession.skill.LearnedSkills",
                    "has", 2, "net.minecraft.world.entity.LivingEntity",
                    "net.minecraft.resources.ResourceLocation");

            // fire is picked by its third parameter; assert that overload exists to be picked.
            assertTrue(hasOverloadWithParameterAt(loader,
                            "com.aetherianartificer.townstead.reaction.ReactionDispatcher", "fire", 4, 2,
                            "net.minecraft.resources.ResourceLocation"),
                    "ReactionDispatcher.fire no longer has an overload taking a reaction id; the "
                            + "outcome coordinator has nothing safe to call.");
        }
    }

    /**
     * Townstead owns the real need ranges, and its three scales are not the same scale. Pin every
     * constant the bands are built on rather than trusting a comment: if a range is retuned, this
     * fails instead of every bundled wellbeing line quietly meaning something else.
     */
    @Test
    void theNeedScalesMatchTownstead() throws Exception {
        List<Path> jars = requireJars();

        assertConstant(jars, HUNGER_DATA, "MAX_HUNGER", TownsteadNeedsView.MAX_HUNGER);
        assertConstant(jars, HUNGER_DATA, "EMERGENCY_THRESHOLD", TownsteadNeedsView.HUNGER_EMERGENCY);
        assertConstant(jars, HUNGER_DATA, "ADEQUATE_THRESHOLD", TownsteadNeedsView.HUNGER_ADEQUATE);
        assertConstant(jars, HUNGER_DATA, "DINNER_THRESHOLD", TownsteadNeedsView.HUNGER_DINNER);
        assertConstant(jars, HUNGER_DATA, "BREAKFAST_THRESHOLD", TownsteadNeedsView.HUNGER_BREAKFAST);

        assertConstant(jars, THIRST_DATA, "MAX_THIRST", TownsteadNeedsView.MAX_THIRST);
        assertConstant(jars, THIRST_DATA, "EMERGENCY_THRESHOLD", TownsteadNeedsView.THIRST_EMERGENCY);
        assertConstant(jars, THIRST_DATA, "ADEQUATE_THRESHOLD", TownsteadNeedsView.THIRST_ADEQUATE);
        assertConstant(jars, THIRST_DATA, "BREAKFAST_THRESHOLD", TownsteadNeedsView.THIRST_BREAKFAST);
        assertConstant(jars, THIRST_DATA, "SATIETY_THRESHOLD", TownsteadNeedsView.THIRST_SATIETY);

        assertConstant(jars, FATIGUE_DATA, "MAX_FATIGUE", TownsteadNeedsView.MAX_FATIGUE);
        assertConstant(jars, FATIGUE_DATA, "TIRED_THRESHOLD", TownsteadNeedsView.FATIGUE_TIRED);
        assertConstant(jars, FATIGUE_DATA, "DROWSY_THRESHOLD", TownsteadNeedsView.FATIGUE_DROWSY);
        assertConstant(jars, FATIGUE_DATA, "EXHAUSTED_THRESHOLD", TownsteadNeedsView.FATIGUE_EXHAUSTED);
    }

    /**
     * Sanity check on the probe itself: with no Townstead anywhere, resolution must report a clean
     * absence rather than throwing. That is the state the rest of the unit suite runs in, and the
     * state most servers are in. It has to be boring, not fatal.
     */
    @Test
    void resolutionWithoutTownsteadIsAbsentAndDoesNotThrow() throws Exception {
        try (URLClassLoader empty = new URLClassLoader(new URL[0], null)) {
            TownsteadBinding.Resolution resolution = TownsteadBinding.resolveAgainst(empty);

            assertEquals(TownsteadStatus.ABSENT, resolution.status());
            assertTrue(resolution.capabilities().isEmpty());
            assertTrue(resolution.unresolved().isEmpty(),
                    "An absent Townstead is not a partial binding; nothing should be reported as a miss.");
            // Every handle must still be a usable stub: TownsteadHandles invokes them with no null check.
            assertNotNull(resolution.handle(TownsteadBinding.API_ENTITY));
        }
    }

    // --- helpers ---------------------------------------------------------------------------------

    private static void assertConstant(List<Path> jars, String owner, String field, int expected)
            throws Exception {
        Integer declared = null;
        for (Path jar : jars) {
            declared = ClassFileConstants.staticFinalInt(jar, owner, field);
            if (declared != null) {
                break;
            }
        }
        assertNotNull(declared, "Townstead no longer declares " + owner + "." + field
                + " as a constant, so the band built on it can no longer be verified. Find where the "
                + "range now lives before trusting any threshold derived from it.");
        assertEquals(expected, declared.intValue(),
                owner + "." + field + " has moved. Update TownsteadNeedsView and re-check every "
                        + "bundled line that speaks about this need.");
    }

    private static void assertParameterTypes(ClassLoader loader, String owner, String name, int arity,
                                             String... expected) throws Exception {
        Class<?> type = Class.forName(owner, false, loader);
        List<String> found = new ArrayList<>();
        for (Method candidate : type.getMethods()) {
            if (!candidate.getName().equals(name) || candidate.getParameterCount() != arity) {
                continue;
            }
            List<String> parameters = new ArrayList<>();
            for (Class<?> parameter : candidate.getParameterTypes()) {
                parameters.add(parameter.getName());
            }
            if (parameters.equals(List.of(expected))) {
                return;
            }
            found.add(parameters.toString());
        }
        throw new AssertionError(owner + "." + name + "/" + arity + " has no overload taking "
                + List.of(expected) + "; the binding would pick one of " + found + " instead.");
    }

    private static boolean hasOverloadWithParameterAt(ClassLoader loader, String owner, String name,
                                                      int arity, int index, String parameterType)
            throws Exception {
        Class<?> type = Class.forName(owner, false, loader);
        for (Method candidate : type.getMethods()) {
            if (candidate.getName().equals(name) && candidate.getParameterCount() == arity
                    && candidate.getParameterTypes()[index].getName().equals(parameterType)) {
                return true;
            }
        }
        return false;
    }

    private static List<Path> requireJars() {
        List<Path> townstead = jars(TOWNSTEAD_JARS_PROPERTY);
        Assumptions.assumeFalse(townstead.isEmpty(),
                "No Townstead jar supplied (" + TOWNSTEAD_JARS_PROPERTY + "); run "
                        + "`./gradlew townsteadProbeTest -PtownsteadLegacyJar=<path>` to exercise this.");
        return townstead;
    }

    private static List<Path> withMca(List<Path> townstead) {
        List<Path> all = new ArrayList<>(townstead);
        all.addAll(jars(MCA_JARS_PROPERTY));
        return all;
    }

    private static URLClassLoader loaderFor(List<Path> jars) throws Exception {
        List<URL> urls = new ArrayList<>();
        for (Path jar : jars) {
            urls.add(jar.toUri().toURL());
        }
        return new URLClassLoader(urls.toArray(URL[]::new),
                TownsteadBindingProbeTest.class.getClassLoader());
    }

    private static List<Path> jars(String property) {
        List<Path> jars = new ArrayList<>();
        for (String entry : System.getProperty(property, "").split(File.pathSeparator)) {
            if (!entry.isBlank()) {
                Path path = Paths.get(entry.trim());
                if (Files.isRegularFile(path)) {
                    jars.add(path);
                }
            }
        }
        return jars;
    }
}
