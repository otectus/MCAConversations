package dev.otectus.mcaconversations.interiority;

import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.ContentOperation;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import dev.otectus.mcaconversations.conversation.StanceFamily;
import dev.otectus.mcaconversations.disposition.DispositionAxis;
import dev.otectus.mcaconversations.personality.Personalities;
import net.minecraft.world.entity.Entity;

import java.util.Map;

/**
 * The published view of the {@link InteriorityProfile} registry, loaded from
 * {@code data/<namespace>/interiority/*.json} (plan §5.5) — a data registry rather than a hard-coded
 * switch, so a pack can retune a personality without touching Java.
 *
 * <p><b>Per personality, not per villager.</b> Two Friendly villagers share a profile. Nothing is
 * rolled and nothing is stored per villager, so there is nothing to migrate or to go inconsistent
 * after a reload — the property plan §5.5 asks for, obtained by not having the problem.
 *
 * <p>Lookups resolve MCA 7.6 spellings through {@link Personalities#canonical}, so a {@code witty}
 * villager on 7.6 and an {@code upbeat} villager on 7.7 read the same profile. Two spellings that
 * canonicalise to one id are now a staging refusal rather than a silent last-one-wins.
 */
public final class Interiority {

    private Interiority() {
    }

    private static Map<String, InteriorityProfile> profiles() {
        return ContentOperation.bundle().interiority();
    }

    /** The profile for a personality id in any MCA spelling; neutral when unknown. */
    public static InteriorityProfile profile(String personality) {
        String canonical = Personalities.canonical(personality);
        if (canonical.isEmpty()) {
            return InteriorityProfile.NEUTRAL;
        }
        return profiles().getOrDefault(canonical, InteriorityProfile.NEUTRAL);
    }

    /** The profile for a villager, read through {@link McaCompat}; neutral when MCA is unreadable. */
    public static InteriorityProfile profileOf(Entity villager) {
        try {
            return McaCompat.getPersonality(villager).map(Interiority::profile)
                    .orElse(InteriorityProfile.NEUTRAL);
        } catch (Throwable t) {
            return InteriorityProfile.NEUTRAL;
        }
    }

    /** This villager's resting value for an axis. The single seam behind {@code Dispositions.baseline}. */
    public static int baseline(Entity villager, DispositionAxis axis) {
        return profileOf(villager).baseline(axis);
    }

    /** How well a stance family lands on this villager, bounded by the profile's own clamp. */
    public static int stanceBias(Entity villager, StanceFamily family) {
        return profileOf(villager).stanceBias(family);
    }

    /** How many profiles are loaded, for the debug command and lint. */
    public static int size() {
        return profiles().size();
    }

    /** Test seam: publish profiles without a resource reload. */
    public static void setProfilesForTesting(Map<String, InteriorityProfile> loaded) {
        ContentReloadCoordinator.setCommittedForTesting(ContentReloadCoordinator.committed()
                .withInteriority(loaded == null ? Map.of() : Map.copyOf(loaded)));
    }
}
