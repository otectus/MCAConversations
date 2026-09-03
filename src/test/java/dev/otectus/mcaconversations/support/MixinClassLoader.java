package dev.otectus.mcaconversations.support;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Defines this mod's compiled mixin classes itself, so a test can reflect over them.
 *
 * <p>ModDevGradle's {@code unitTest} boots FML, and FML's transforming class loader has Mixin
 * installed. Any class registered in {@code mcaconversations.mixins.json} is therefore off limits to
 * a plain {@code Class.forName} on the test classloader: Mixin answers with
 * {@code IllegalClassLoadError: ... is defined in mcaconversations.mixins.json and cannot be
 * referenced directly} (or a {@code NoClassDefFoundError: ... is invalid} once the module layer has
 * had its say). On 1.20.1 the same test ran in a plain JVM, where the reflective load simply worked.
 *
 * <p>That guard only protects the <em>transforming</em> loader — it exists to stop a mixin being
 * loaded, and thus frozen, before it can be applied to its target. Reading the bytes off disk and
 * defining them in a child loader of our own sidesteps it entirely, and applies nothing: the class
 * is defined, never applied, so the {@code RUNTIME}-retained {@code @Shadow} annotations the test
 * reads are visible without any target being touched.
 *
 * <p>Child-first for {@code dev.otectus.mcaconversations.mixin.*} only. Everything else — Mixin's
 * own annotation types above all, whose identity has to match the {@code Shadow.class} literal the
 * test compares against — comes from the parent.
 */
public final class MixinClassLoader extends ClassLoader {

    private static final String MIXIN_PACKAGE = "dev.otectus.mcaconversations.mixin.";

    private final Path classesDir;

    /** @param classesDir the compiled main output root, e.g. {@code build/classes/java/main}. */
    public MixinClassLoader(Path classesDir, ClassLoader parent) {
        super(parent);
        this.classesDir = classesDir;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (!name.startsWith(MIXIN_PACKAGE)) {
            return super.loadClass(name, resolve);
        }
        synchronized (getClassLoadingLock(name)) {
            Class<?> loaded = findLoadedClass(name);
            if (loaded == null) {
                loaded = defineFromDisk(name);
            }
            if (resolve) {
                resolveClass(loaded);
            }
            return loaded;
        }
    }

    private Class<?> defineFromDisk(String name) throws ClassNotFoundException {
        Path file = classesDir.resolve(name.replace('.', '/') + ".class");
        if (!Files.isRegularFile(file)) {
            throw new ClassNotFoundException(name + " has no compiled form at " + file);
        }
        try {
            byte[] bytes = Files.readAllBytes(file);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
