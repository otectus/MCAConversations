package dev.otectus.mcaconversations.compat.mca;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
 * Guards the one step in the MCA binding that hands an implementation <em>back</em> to MCA.
 *
 * <p>Everything else the binding does is a call into MCA, which {@code McaBindingProbeTest} covers by
 * resolving the manifest. Registration is the opposite direction: MCA's dialogue conditions and
 * actions are single-abstract-method interfaces whose package is exactly what varies between
 * versions, so our bodies are wrapped with {@link java.lang.invoke.MethodHandleProxies} against a
 * {@link Class} that came from the probed root. When that wrapping is wrong the failure is silent —
 * the registration is swallowed, every {@code conversations_*} key stays unknown, and the only
 * symptom is a datapack log full of "Unknown dialogue action".
 *
 * <h2>What is asserted, and what is deliberately not</h2>
 *
 * <p>{@code asInterfaceInstance} enforces exactly three things, and all three are ours to get wrong:
 * the interface must be public, it must have exactly one abstract method, and the handle's type must
 * equal that method's type. So this resolves MCA's real interfaces out of every jar in the probe
 * fleet and checks all three against the handle {@code McaHandles} actually builds.
 *
 * <p>It stops short of constructing the proxy. {@link java.lang.reflect.Proxy} generates a class
 * whose {@code <clinit>} resolves every parameter type of the methods it implements, so building a
 * {@code Condition} proxy initialises {@code net.minecraft.world.entity.Entity} and cascades into
 * Minecraft's registry bootstrap — which has long since happened in game but cannot happen in a bare
 * unit-test JVM. Attempting it here only tests whether Minecraft can boot, which is neither what
 * varies nor what we control.
 */
class McaProxyBindingTest {

    private static final String JARS_PROPERTY = "mcaconversations.probe.jars";

    @Test
    void ourBodiesMatchMcaConditionAndActionExactly() throws Throwable {
        List<Path> jars = probeJars();
        Assumptions.assumeFalse(jars.isEmpty(),
                "No MCA jar to probe (" + JARS_PROPERTY + "); run via Gradle to exercise this.");

        for (Path jar : jars) {
            try (URLClassLoader loader = new URLClassLoader(new URL[] {jar.toUri().toURL()},
                    McaProxyBindingTest.class.getClassLoader())) {
                String root = rootOf(loader);
                assertNotNull(root, "no known MCA package root in " + jar.getFileName());
                String where = jar.getFileName() + " (" + root + "): ";

                Class<?> villager = load(loader, root + "entity.VillagerEntityMCA");
                Class<?> condition = load(loader, root + "entity.interaction.gifts.GiftPredicate$Condition");
                Class<?> action = load(loader, root + "resources.data.dialogue.Actions$Action");
                Class<?> giftFactory = load(loader, root + "entity.interaction.gifts.GiftPredicate$Factory");
                Class<?> actionsFactory = load(loader, root + "resources.data.dialogue.Actions$Factory");

                // The type our production code builds must BE the interface method's type.
                assertEquals(soleAbstractType(where, condition),
                        McaHandles.conditionHandle(villager, (v, stack, player) -> 0.0f).type(),
                        where + "our condition handle no longer matches MCA's Condition.test");
                assertEquals(soleAbstractType(where, action),
                        McaHandles.actionHandle(villager, (v, player) -> { }).type(),
                        where + "our action handle no longer matches MCA's Action.trigger");

                // Both factories must stay single-abstract too — they are proxied the same way, with
                // an erased (Object)-to-interface handle built in McaHandles.register.
                assertEquals(1, abstractMethods(giftFactory).size(),
                        where + "GiftPredicate.Factory is no longer a single-method interface");
                assertEquals(1, abstractMethods(actionsFactory).size(),
                        where + "Actions.Factory is no longer a single-method interface");
                assertEquals(condition, abstractMethods(giftFactory).get(0).getReturnType(),
                        where + "Factory.parse no longer returns Condition");
                assertEquals(action, abstractMethods(actionsFactory).get(0).getReturnType(),
                        where + "Factory.parse no longer returns Action");

                // and(..) must stay a DEFAULT method. If MCA ever made it abstract, Condition would
                // stop being single-abstract and every condition registration would fail at once.
                Method and = condition.getMethod("and", condition);
                assertTrue(and.isDefault(),
                        where + "Condition.and is no longer a default method, so asInterfaceInstance "
                                + "would reject Condition as a multi-method interface");

                System.out.println("[proxy] " + jar.getFileName() + " -> " + root + " ok");
            }
        }
    }

    /** The {@link MethodType} of the interface's single abstract method, asserted to be single. */
    private static MethodType soleAbstractType(String where, Class<?> intf) {
        assertTrue(intf.isInterface() && Modifier.isPublic(intf.getModifiers()),
                where + intf.getName() + " must be a public interface for asInterfaceInstance");
        List<Method> abstracts = abstractMethods(intf);
        assertEquals(1, abstracts.size(),
                where + intf.getName() + " must have exactly one abstract method, found " + abstracts);
        Method m = abstracts.get(0);
        return MethodType.methodType(m.getReturnType(), m.getParameterTypes());
    }

    /** Mirrors {@code MethodHandleProxies}' own rule: {@link Object} methods and defaults do not count. */
    private static List<Method> abstractMethods(Class<?> intf) {
        List<Method> out = new ArrayList<>();
        for (Method m : intf.getMethods()) {
            if (m.isDefault() || Modifier.isStatic(m.getModifiers()) || m.getDeclaringClass() == Object.class) {
                continue;
            }
            if (Modifier.isAbstract(m.getModifiers())) {
                out.add(m);
            }
        }
        return out;
    }

    private static Class<?> load(ClassLoader loader, String name) throws Exception {
        // initialize = false: a probe must not run MCA's static initialisers.
        return Class.forName(name, false, loader);
    }

    /** The probed root, with a trailing dot, or null. Mirrors {@code McaBinding.CANDIDATE_ROOTS}. */
    private static String rootOf(ClassLoader loader) {
        for (String root : new String[] {
                "forge.net.conczin.mca.", "forge.net.mca.", "net.conczin.mca.", "net.mca."}) {
            try {
                Class.forName(root + "entity.VillagerEntityMCA", false, loader);
                return root;
            } catch (Throwable ignored) {
                // try the next
            }
        }
        return null;
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
