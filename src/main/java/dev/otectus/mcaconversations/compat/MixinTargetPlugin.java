package dev.otectus.mcaconversations.compat;

import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.fml.loading.moddiscovery.ModFileInfo;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Skips the mixin targets that do not exist in the installed build, before Mixin goes looking for them.
 *
 * <p>Some declared targets may not exist. The Townstead client mixins name a mod that may not be
 * installed at all, and every MCA target is checked against the MCA jar on the same terms.
 * {@code @Pseudo} stops a missing target from being an error, but not from being looked up: Mixin
 * resolves every declared target through {@code ClassInfo.forName}, which logs
 * {@code WARN Error loading class} for each miss, each indistinguishable in a bug report from a
 * genuinely broken hook.
 *
 * <p>Mixin asks {@link #shouldApplyMixin} per declared target <em>before</em> that lookup, so answering
 * "no" for a class the owning mod's jar does not contain removes the noise and nothing else. The check
 * reads the jar's file listing through {@link LoadingModList} (the ordinary {@code ModList} does not
 * exist yet when mixins are prepared): no class is loaded or transformed. Anything this plugin cannot
 * decide — an unknown owner, a lookup that fails — answers "apply", which is exactly the behaviour the
 * configuration had without a plugin; the {@code require = 0} injectors and
 * {@code MixinTargetProbeTest} keep governing what happens next.
 *
 * <p>Deliberately outside the {@code mixin} package: a config plugin is constructed before its own
 * configuration's package is processed. Names only dotted strings, never an MCA or Townstead type.
 */
public final class MixinTargetPlugin implements IMixinConfigPlugin {

    /** Target package prefix → the mod whose jar must contain the class (the one {@code McaBinding} root). */
    private static final Map<String, String> OWNERS = Map.of(
            "net.conczin.mca.", "mca",
            "com.aetherianartificer.townstead.", "townstead");

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String owner = ownerOf(targetClassName);
        return owner == null || applies(present(owner, targetClassName));
    }

    /** The mod that ships this target, or null when it is not one this plugin knows how to check. */
    static String ownerOf(String targetClassName) {
        if (targetClassName == null) {
            return null;
        }
        for (Map.Entry<String, String> entry : OWNERS.entrySet()) {
            if (targetClassName.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    /** Only a definite "not here" skips a target; "cannot tell" applies it, as before this plugin. */
    static boolean applies(Boolean present) {
        return present == null || present;
    }

    /**
     * Whether the owning mod's jar contains the class: false when the mod or the class is absent, null
     * when that cannot be determined (no loading mod list, as in unit tests, or a lookup that fails).
     */
    static Boolean present(String ownerModId, String targetClassName) {
        try {
            LoadingModList mods = LoadingModList.get();
            if (mods == null) {
                return null;
            }
            ModFileInfo file = mods.getModFileById(ownerModId);
            if (file == null) {
                return Boolean.FALSE;
            }
            return Files.exists(file.getFile().findResource(targetClassName.replace('.', '/') + ".class"));
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
