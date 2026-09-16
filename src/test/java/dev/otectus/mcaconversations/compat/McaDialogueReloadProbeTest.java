package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.compat.mca.McaBinding;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pins the exact shape and the exact reload behaviour of MCA's {@code Dialogues} listener across the
 * probe fleet, because the add-on's dialogue routing depends on both and neither is guarded by the
 * compiler or by {@code McaBindingProbeTest}.
 *
 * <h2>Why this is separate from {@link McaBindingProbeTest}</h2>
 *
 * <p>Three of the members this mod's dialogue path stands on cannot be expressed in
 * {@code McaBinding}'s manifest at all: {@code Dialogues.INSTANCE} is a <em>static</em> field and a
 * {@code getter} member erases to an instance getter ({@code Member#erasedType} prepends a receiver),
 * {@code Dialogues.questions} is reached through Mixin's {@code @Shadow} rather than a handle, and
 * {@code Dialogues.apply} is {@code protected}, which {@code McaBinding#bindMethod} cannot see because
 * it scans {@code getMethods()}. They are load-bearing all the same — {@code DialoguesMixin} shadows
 * the field and injects by method name with {@code require = 0}, so a rename there is a silently dead
 * feature rather than a build failure. This test is the missing net: it asserts the descriptors
 * directly, against every jar in {@code mca_probe_versions}, in one throwaway class loader per jar.
 *
 * <p>No MCA type is named anywhere: every class, field and method is reached reflectively through the
 * per-jar loader, exactly as {@link McaBindingProbeTest} does.
 *
 * <h2>The behavioural half</h2>
 *
 * <p>{@code reloadClearsTheSameMapInPlaceAndCarriedQuestionsStayReachable} constructs real
 * {@code Dialogues} listeners from the jar and drives {@code apply} on them. That is what turns part
 * of the transaction record in {@code docs/RELOAD-TRANSACTION-BOUNDARY.md} from a decompilation
 * reading into a demonstrated fact: the map identity survives, its contents do not, the singleton
 * repoints on construction, and an entry carried over from a previous instance is reachable through
 * {@code getQuestion} on the new one. Its own javadoc records the one thing it cannot drive.
 */
class McaDialogueReloadProbeTest {

    private static final String JARS_PROPERTY = "mcaconversations.probe.jars";

    // ---------------------------------------------------------------------------------------------
    // Shape
    // ---------------------------------------------------------------------------------------------

    @Test
    @DisplayName("every probed MCA jar still has the Dialogues members the reload hooks need")
    void dialogueReloadMembersResolveAgainstEveryProbedJar() throws Exception {
        forEachJar((jar, loader, root) -> {
            Class<?> dialogues = Class.forName(root + "resources.Dialogues", false, loader);
            Class<?> question = Class.forName(root + "resources.data.dialogue.Question", false, loader);
            Class<?> answer = Class.forName(root + "resources.data.dialogue.Answer", false, loader);

            // Still a vanilla SimpleJsonResourceReloadListener: the whole preparation/apply barrier
            // described in the transaction record depends on that and on nothing MCA-specific.
            assertEquals("net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener",
                    dialogues.getSuperclass().getName(),
                    jar + ": Dialogues is no longer a SimpleJsonResourceReloadListener");
            assertNotNull(dialogues.getDeclaredConstructor(),
                    jar + ": Dialogues no longer has the no-argument constructor MCA registers");

            // DialoguesMixin @Shadow @Final private Map<String, Object> questions.
            Field questions = dialogues.getDeclaredField("questions");
            assertSame(Map.class, questions.getType(), jar + ": Dialogues.questions changed type");
            assertTrue(Modifier.isPrivate(questions.getModifiers()) && Modifier.isFinal(questions.getModifiers()),
                    jar + ": Dialogues.questions is no longer private final — DialoguesMixin's @Final would warn");
            assertFalse(Modifier.isStatic(questions.getModifiers()),
                    jar + ": Dialogues.questions became static; the shadow is an instance field");

            // The singleton, repointed in the constructor; the only way to reach the outgoing instance.
            Field instance = dialogues.getDeclaredField("INSTANCE");
            assertTrue(Modifier.isStatic(instance.getModifiers()), jar + ": Dialogues.INSTANCE is not static");
            assertSame(dialogues, instance.getType(), jar + ": Dialogues.INSTANCE changed type");
            Method getInstance = dialogues.getMethod("getInstance");
            assertTrue(Modifier.isStatic(getInstance.getModifiers()), jar + ": getInstance is not static");
            assertSame(dialogues, getInstance.getReturnType(), jar + ": getInstance changed return type");

            // The apply the reinsertion hook targets. Protected, hence invisible to the manifest.
            Method apply = dialogues.getDeclaredMethod("apply", Map.class,
                    Class.forName("net.minecraft.server.packs.resources.ResourceManager", false, loader),
                    Class.forName("net.minecraft.util.profiling.ProfilerFiller", false, loader));
            assertTrue(Modifier.isProtected(apply.getModifiers()),
                    jar + ": Dialogues.apply is no longer protected — the manifest note in this test is stale");
            assertSame(void.class, apply.getReturnType(), jar + ": Dialogues.apply changed return type");

            // The two late-bound entry points every retained question has to stay reachable through.
            Method getQuestion = dialogues.getMethod("getQuestion", String.class);
            assertSame(question, getQuestion.getReturnType(), jar + ": getQuestion changed return type");
            Method selectAnswer = dialogues.getMethod("selectAnswer",
                    Class.forName(root + "entity.VillagerEntityMCA", false, loader),
                    Class.forName("net.minecraft.server.level.ServerPlayer", false, loader),
                    String.class, String.class);
            assertSame(void.class, selectAnswer.getReturnType(), jar + ": selectAnswer changed return type");

            // Question/Answer members a retained entry is inspected by (name identity, answer lookup).
            assertSame(String.class, question.getMethod("getName").getReturnType(),
                    jar + ": Question.getName changed return type");
            assertSame(answer, question.getMethod("getAnswer", String.class).getReturnType(),
                    jar + ": Question.getAnswer changed return type");
            assertSame(String.class, answer.getMethod("getName").getReturnType(),
                    jar + ": Answer.getName changed return type");
        });
    }

    // ---------------------------------------------------------------------------------------------
    // Behaviour
    // ---------------------------------------------------------------------------------------------

    /**
     * The reload transaction itself, driven against the real class.
     *
     * <p><b>What this cannot do, and why.</b> MCA's jars are SRG-named, while the dev/test Minecraft is
     * official-mapped, so anything in MCA that calls a Minecraft member fails with
     * {@code NoSuchMethodError} in this JVM — {@code Dialogues.loadDialogue} calls
     * {@code ResourceLocation.m_135815_()} at its first line. The JSON parse path is therefore out of
     * reach here (it is exercised in-game, not in the unit suite), and the fixture drives {@code apply}
     * with an empty map and populates the listener's map with {@code Question} objects built through
     * {@code Question}'s own public constructor, which touches no Minecraft type. That is enough for
     * the two claims the retention strategy rests on and is honest about the third: the
     * namespace-dropping key rule in {@code loadDialogue} remains a decompilation reading, not a
     * demonstrated fact.
     */
    @Test
    @DisplayName("apply clears the same map in place, and a carried-over question stays reachable")
    void reloadClearsTheSameMapInPlaceAndCarriedQuestionsStayReachable() throws Exception {
        forEachJar((jar, loader, root) -> {
            Class<?> dialogues = Class.forName(root + "resources.Dialogues", false, loader);
            Class<?> questionClass = Class.forName(root + "resources.data.dialogue.Question", false, loader);
            Field questionsField = dialogues.getDeclaredField("questions");
            questionsField.setAccessible(true);
            Method apply = dialogues.getDeclaredMethod("apply", Map.class,
                    Class.forName("net.minecraft.server.packs.resources.ResourceManager", false, loader),
                    Class.forName("net.minecraft.util.profiling.ProfilerFiller", false, loader));
            apply.setAccessible(true);
            Method getQuestion = dialogues.getMethod("getQuestion", String.class);
            Method getInstance = dialogues.getMethod("getInstance");
            Method questionName = questionClass.getMethod("getName");

            // --- the outgoing listener -----------------------------------------------------------
            Object first = dialogues.getDeclaredConstructor().newInstance();
            assertSame(first, getInstance.invoke(null),
                    jar + ": the constructor no longer repoints INSTANCE at the new listener");

            @SuppressWarnings("unchecked")
            Map<String, Object> firstMap = (Map<String, Object>) questionsField.get(first);
            assertTrue(firstMap.isEmpty(), jar + ": a freshly constructed Dialogues is expected to start empty");
            Object carried = question(questionClass, "conversations.probe");
            firstMap.put("conversations.probe", carried);
            assertSame(carried, getQuestion.invoke(first, "conversations.probe"),
                    jar + ": getQuestion no longer reads the shadowed map");

            // (a) apply clears the existing contents and keeps the same map object.
            apply.invoke(first, new LinkedHashMap<>(), null, null);
            assertSame(firstMap, questionsField.get(first),
                    jar + ": apply replaced the map reference — it is documented as cleared and refilled in place");
            assertTrue(firstMap.isEmpty(), jar + ": apply no longer clears the previous contents");
            assertNull(getQuestion.invoke(first, "conversations.probe"),
                    jar + ": a question present before apply survived it");

            // --- the incoming listener -----------------------------------------------------------
            Object second = dialogues.getDeclaredConstructor().newInstance();
            assertSame(second, getInstance.invoke(null),
                    jar + ": INSTANCE did not move to the newly constructed listener");
            assertNotSame(questionsField.get(first), questionsField.get(second),
                    jar + ": two listeners are expected to own two maps");
            apply.invoke(second, new LinkedHashMap<>(), null, null);
            assertNull(getQuestion.invoke(second, "conversations.probe"),
                    jar + ": the new instance is expected not to inherit the old instance's entries");

            // (b) the retention step the recommended strategy performs at apply TAIL: the very same
            // object, put into the new instance's map, comes back out of the new instance's lookup.
            @SuppressWarnings("unchecked")
            Map<String, Object> secondMap = (Map<String, Object>) questionsField.get(second);
            secondMap.put("conversations.probe", carried);
            assertSame(carried, getQuestion.invoke(second, "conversations.probe"),
                    jar + ": a question copied from the previous instance is not reachable through getQuestion");
            assertEquals("conversations.probe", questionName.invoke(carried),
                    jar + ": the carried question lost its identity");
        });
    }

    // ---------------------------------------------------------------------------------------------
    // Plumbing
    // ---------------------------------------------------------------------------------------------

    /**
     * A {@code Question} built through MCA's own public constructor {@code (String, List, boolean,
     * boolean)}. No JSON, because {@code Question.fromJson} is only reachable through
     * {@code loadDialogue}, which needs SRG-named Minecraft.
     */
    private static Object question(Class<?> questionClass, String name) throws Exception {
        return questionClass.getConstructor(String.class, List.class, boolean.class, boolean.class)
                .newInstance(name, new ArrayList<>(), false, false);
    }

    private interface JarCheck {
        void run(String jarName, ClassLoader loader, String root) throws Exception;
    }

    private static void forEachJar(JarCheck check) throws Exception {
        List<Path> jars = probeJars();
        Assumptions.assumeFalse(jars.isEmpty(),
                "No MCA jar to probe (" + JARS_PROPERTY + "); run via Gradle to exercise this.");
        for (Path jar : jars) {
            try (URLClassLoader loader = new URLClassLoader(new URL[] {jar.toUri().toURL()},
                    McaDialogueReloadProbeTest.class.getClassLoader())) {
                String root = McaBinding.resolveAgainst(loader).root();
                assertNotNull(root, "No candidate package root matched " + jar.getFileName());
                check.run(jar.getFileName().toString(), loader, root);
            }
        }
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
