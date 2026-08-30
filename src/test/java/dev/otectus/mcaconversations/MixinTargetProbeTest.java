package dev.otectus.mcaconversations;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.spongepowered.asm.mixin.Shadow;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * What {@code McaBindingProbeTest} does for the reflective binding, this does for the mixins.
 *
 * <h2>Why it has to exist</h2>
 *
 * <p>Every MCA-targeting mixin here is declared {@code require = 0}, so if MCA renames or reshapes an
 * injection point the injector silently does nothing. That is the correct <em>runtime</em> behaviour —
 * a reshaped API should cost one feature, not a startup crash — but it means the compiler and the game
 * are both silent about the breakage, and the first person to notice is a player whose villagers
 * stopped replying. The binding probe cannot help: it walks {@code McaBinding.MANIFEST}, which by
 * construction contains nothing a mixin targets.
 *
 * <p>So the two invariants below are asserted against every MCA build in the probe fleet:
 *
 * <ol>
 *   <li><b>Every mixin resolves exactly one target per MCA jar.</b> Each mixin lists both known
 *       package roots; a jar must match one of them. Zero matches means a root was forgotten (the
 *       regression that broke this mod on 7.7.1); two would mean a jar somehow shipped both.</li>
 *   <li><b>Every injection point still exists</b> on the target that did resolve — including private
 *       methods ({@code acceptGift}) and constructors. Searched up the superclass chain, because an
 *       injector may legitimately land on an inherited method.</li>
 *   <li><b>Every {@code @Shadow}ed member is declared on the target itself</b> — <em>not</em> merely
 *       inherited. This one is strict, and the strictness is the whole point.</li>
 * </ol>
 *
 * <h2>Why the shadow check is strict</h2>
 *
 * <p>Because a lenient version of it shipped a startup crash. {@code BreedableRelationshipMixin} used
 * to shadow {@code getWorld()} and {@code getUUID()}; both were listed below and both passed, because
 * the check walked the superclass chain and found them on {@code Relationship}. But a {@code @Pseudo}
 * mixin can only shadow members declared <em>directly</em> on its target — Mixin has no guaranteed
 * view of a pseudo target's supertypes — so the real game threw {@code InvalidMixinException} while
 * applying the mixin and never reached the main menu. That is not a silent feature loss that
 * {@code require = 0} absorbs: shadow resolution happens in pre-processing, long before any injector
 * option is consulted, and the config is {@code "required": true}.
 *
 * <p>Shadowed members are therefore discovered by reflection rather than restated below —
 * {@code @Shadow} is the one Mixin annotation with {@code RUNTIME} retention, so the list cannot drift
 * out of step with the code the way a hand-maintained one did.
 *
 * <p>Target strings are read out of the compiled mixin classes rather than restated here, so a mixin
 * that loses a root cannot also quietly lose its test coverage.
 */
class MixinTargetProbeTest {

    private static final String JARS_PROPERTY = "mcaconversations.probe.jars";
    private static final String MIXIN_PACKAGE = "dev.otectus.mcaconversations.mixin";
    private static final Path MIXIN_CLASSES =
            Paths.get("build", "classes", "java", "main", "dev", "otectus", "mcaconversations", "mixin");

    /** Any MCA class name a mixin could name, in the dotted form an annotation value carries. */
    private static final Pattern MCA_TARGET =
            Pattern.compile("forge\\.net\\.(?:conczin\\.)?mca\\.[A-Za-z0-9.$]+");

    /**
     * The members each mixin <em>injects into</em>, keyed by mixin class name. Restated here on
     * purpose: these are the names {@code require = 0} would otherwise let fail silently, and there is
     * no way to recover an {@code @Inject(method = ..)} value from the constant pool unambiguously.
     *
     * <p>Shadowed members are deliberately absent — they are read off the compiled mixin by
     * reflection and checked strictly instead.
     */
    private static final Map<String, List<String>> INJECTION_POINTS = new LinkedHashMap<>();

    static {
        INJECTION_POINTS.put("NetworkHandlerMixin", List.of("sendToPlayer"));
        INJECTION_POINTS.put("DialoguesMixin", List.of("getQuestion"));
        INJECTION_POINTS.put("QuestionMixin", List.of("getValidAnswers"));
        INJECTION_POINTS.put("InteractionDialogueMessageMixin", List.of("receive"));
        INJECTION_POINTS.put("BreedableRelationshipMixin", List.of("acceptGift"));
        INJECTION_POINTS.put("MCAClientMixin", List.of("useExpandedPersonalityTranslations"));
        INJECTION_POINTS.put("VillagerMessageMixin", List.of("<init>", "getMessage", "getContent"));
        INJECTION_POINTS.put("InteractScreenChoiceMixin", List.of("<init>", "m_88315_", "m_7933_",
                "m_6375_", "m_6050_", "m_7379_"));
    }

    @Test
    void everyMixinResolvesExactlyOneTargetInEveryProbedMcaJar() throws Exception {
        List<Path> jars = probeJars();
        Assumptions.assumeFalse(jars.isEmpty(),
                "No MCA jar to probe (" + JARS_PROPERTY + "); run via Gradle to exercise this.");
        assertTrue(Files.isDirectory(MIXIN_CLASSES),
                MIXIN_CLASSES + " does not exist; run `./gradlew compileJava` first.");

        Map<String, CompiledMixin> declared = declaredTargets();
        assertTrue(declared.size() >= INJECTION_POINTS.size(),
                "found " + declared.size() + " compiled mixins but " + INJECTION_POINTS.size()
                        + " are described here: " + declared.keySet());

        List<String> problems = new ArrayList<>();
        for (Path jar : jars) {
            try (URLClassLoader loader = new URLClassLoader(new URL[] {jar.toUri().toURL()},
                    MixinTargetProbeTest.class.getClassLoader())) {
                for (Map.Entry<String, CompiledMixin> mixin : declared.entrySet()) {
                    checkMixin(jar, loader, mixin.getKey(), mixin.getValue(), problems);
                }
            }
        }
        assertTrue(problems.isEmpty(), "Mixin targets do not line up with MCA:\n  "
                + String.join("\n  ", problems));
    }

    private static void checkMixin(Path jar, ClassLoader loader, String mixin, CompiledMixin compiled,
                                   List<String> problems) {
        Set<String> targets = compiled.targets();
        List<Class<?>> resolved = new ArrayList<>();
        for (String target : targets) {
            try {
                // initialize = false: a probe must not run MCA's static initialisers.
                resolved.add(Class.forName(target, false, loader));
            } catch (Throwable ignored) {
                // The other root, as expected.
            }
        }
        if (resolved.size() != 1) {
            problems.add(jar.getFileName() + ": " + mixin + " resolved " + resolved.size()
                    + " of its " + targets.size() + " declared targets " + targets
                    + " (expected exactly 1 — a 0 means a package root is missing from the @Mixin)");
            return;
        }
        Class<?> target = resolved.get(0);
        for (String member : INJECTION_POINTS.getOrDefault(mixin, List.of())) {
            if (!hasMember(target, member)) {
                problems.add(jar.getFileName() + ": " + mixin + " injects into '" + member
                        + "', which no longer exists on " + target.getName()
                        + " — require = 0 means this would fail silently in game");
            }
        }
        checkShadows(jar, mixin, compiled.binaryName(), target, problems);
    }

    /**
     * Strict: a shadowed member must be <em>declared</em> on the target, never merely inherited.
     * Anything else is a crash on startup rather than a degraded feature — see the class javadoc.
     */
    private static void checkShadows(Path jar, String mixin, String binaryName, Class<?> target,
                                     List<String> problems) {
        Class<?> mixinClass;
        try {
            mixinClass = Class.forName(binaryName, false, MixinTargetProbeTest.class.getClassLoader());
        } catch (Throwable t) {
            problems.add("could not load compiled mixin " + binaryName + ": " + t);
            return;
        }
        for (java.lang.reflect.Method m : mixinClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Shadow.class) && !declaresMethod(target, m.getName())) {
                problems.add(jar.getFileName() + ": " + mixin + " @Shadows method '" + m.getName()
                        + "', which " + target.getName() + " inherits rather than declares — a @Pseudo "
                        + "mixin cannot shadow an inherited member, so this crashes on startup. Reach it "
                        + "through McaBinding/McaHandles instead (see McaHandles#relationshipVillager).");
            }
        }
        for (java.lang.reflect.Field f : mixinClass.getDeclaredFields()) {
            if (f.isAnnotationPresent(Shadow.class) && !declaresField(target, f.getName())) {
                problems.add(jar.getFileName() + ": " + mixin + " @Shadows field '" + f.getName()
                        + "', which " + target.getName() + " inherits rather than declares — a @Pseudo "
                        + "mixin cannot shadow an inherited member, so this crashes on startup.");
            }
        }
    }

    private static boolean declaresMethod(Class<?> target, String name) {
        for (java.lang.reflect.Method m : target.getDeclaredMethods()) {
            if (m.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static boolean declaresField(Class<?> target, String name) {
        for (java.lang.reflect.Field f : target.getDeclaredFields()) {
            if (f.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /** {@code #name} is a field; {@code <init>} a constructor; anything else a method, private included. */
    private static boolean hasMember(Class<?> target, String member) {
        if (member.startsWith("#")) {
            String field = member.substring(1);
            for (Class<?> c = target; c != null; c = c.getSuperclass()) {
                for (java.lang.reflect.Field f : c.getDeclaredFields()) {
                    if (f.getName().equals(field)) {
                        return true;
                    }
                }
            }
            return false;
        }
        if ("<init>".equals(member)) {
            return target.getDeclaredConstructors().length > 0;
        }
        for (Class<?> c = target; c != null; c = c.getSuperclass()) {
            for (java.lang.reflect.Method m : c.getDeclaredMethods()) {
                if (m.getName().equals(member)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** A compiled mixin: its binary name, for reflection, and the MCA classes it names as targets. */
    private record CompiledMixin(String binaryName, Set<String> targets) {
    }

    /** Mixin simple name to the MCA class names its compiled form mentions. */
    private static Map<String, CompiledMixin> declaredTargets() throws IOException {
        Map<String, CompiledMixin> out = new LinkedHashMap<>();
        try (Stream<Path> paths = Files.walk(MIXIN_CLASSES)) {
            paths.filter(p -> p.toString().endsWith(".class")).forEach(p -> {
                String name = p.getFileName().toString().replace(".class", "");
                if (name.contains("$")) {
                    return; // inner/lambda classes carry no @Mixin annotation of their own
                }
                try {
                    // ISO-8859-1 keeps every byte a distinct char, so constant-pool UTF8 entries
                    // survive the decode intact and the regex sees them exactly as stored.
                    String body = new String(Files.readAllBytes(p), StandardCharsets.ISO_8859_1);
                    Matcher m = MCA_TARGET.matcher(body);
                    Set<String> targets = new TreeSet<>();
                    while (m.find()) {
                        targets.add(m.group());
                    }
                    if (!targets.isEmpty()) {
                        // Mixins may sit in a client/ subpackage, so rebuild the binary name from the
                        // path rather than assuming everything is directly under the mixin package.
                        String relative = MIXIN_CLASSES.relativize(p).toString().replace('\\', '/');
                        String binaryName = MIXIN_PACKAGE + "."
                                + relative.substring(0, relative.length() - ".class".length())
                                        .replace('/', '.');
                        out.put(name, new CompiledMixin(binaryName, targets));
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
        return out;
    }

    private static List<Path> probeJars() {
        List<Path> jars = new ArrayList<>();
        for (String entry : System.getProperty(JARS_PROPERTY, "").split(File.pathSeparator)) {
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
