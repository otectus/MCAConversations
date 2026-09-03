package dev.otectus.mcaconversations.compat;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;

/**
 * A parent loader that hides Minecraft Comes Alive from whatever loads through it.
 *
 * <p>The unit-test runtime carries the pinned MCA jar because ModDevGradle's unitTest boots FML,
 * and FML enforces the mandatory {@code mca} dependency declared in {@code neoforge.mods.toml}.
 * Without this filter every probe jar opened by {@link McaBindingProbeTest} would resolve its MCA
 * classes from the parent rather than from itself, so the multi-version fleet would silently test
 * one version -- the pinned one -- over and over. Everything that is not MCA still delegates, which
 * is what keeps Minecraft and Architectury types identical to the ones the manifest's parameter
 * hints name.
 */
class McaHidingClassLoader extends ClassLoader {

    private static final String HIDDEN_PACKAGE = "net.conczin.mca.";
    private static final String HIDDEN_RESOURCE = "net/conczin/mca/";

    McaHidingClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.startsWith(HIDDEN_PACKAGE)) {
            throw new ClassNotFoundException(name + " is hidden from probe loaders on purpose");
        }
        return super.loadClass(name, resolve);
    }

    @Override
    public URL getResource(String name) {
        return name.startsWith(HIDDEN_RESOURCE) ? null : super.getResource(name);
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        return name.startsWith(HIDDEN_RESOURCE) ? Collections.emptyEnumeration() : super.getResources(name);
    }
}
