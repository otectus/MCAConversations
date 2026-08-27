package dev.otectus.mcaconversations.identity;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.personality.Personalities;
import dev.otectus.mcaconversations.profession.ProfessionProfile;
import dev.otectus.mcaconversations.profession.ProfessionProfileLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.UUID;

/**
 * The facade every caller goes through to read or change a villager's stable identity (spec §6).
 *
 * <p>Two rules live here rather than in the store, because they are policy rather than storage:
 *
 * <ol>
 *   <li><b>Generate lazily, once.</b> A profile is created on the first meaningful interaction and
 *       persisted immediately. Villagers who are never spoken to never cost a byte, and a villager who
 *       has been spoken to keeps the same profile through restarts, renames, relocations, catalog
 *       rebalances and every other player.</li>
 *   <li><b>Off means absent, not neutral-by-default.</b> With {@code dynamic.identityEnabled=false}
 *       every read returns empty and nothing is generated or written, so the feature's off state is
 *       byte-identical to a 1.4.0 world rather than "the same code path with blank values"
 *       (spec §22.5).</li>
 * </ol>
 *
 * <p>Everything fails safe: an unreachable server, an empty catalog or any throw at all reads as "this
 * villager has no profile", which every profile condition already treats as a non-match.
 */
public final class Identity {

    private Identity() {
    }

    /** True when identity is switched on. Callers use it to skip work, never to fabricate a default. */
    public static boolean enabled() {
        return McaConversationsConfig.dynamicFeature("identity", false);
    }

    /**
     * This villager's profile, generating and persisting one on first use.
     *
     * <p>Called on the interaction path, so it is deliberately cheap in the common case: a hit is one
     * map lookup, and generation touches only the token catalog and four already-computed context
     * facts.
     */
    public static Optional<VillagerIdentityRecord> of(Entity villager) {
        if (!enabled() || villager == null) {
            return Optional.empty();
        }
        MinecraftServer server = villager.getServer();
        if (server == null || !McaCompat.isMcaVillager(villager)) {
            return Optional.empty();
        }
        try {
            VillagerIdentitySavedData data = VillagerIdentitySavedData.get(server);
            Optional<VillagerIdentityRecord> existing = data.peek(villager.getUUID());
            if (existing.isPresent()) {
                return existing;
            }
            return generateAndStore(data, server, villager);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("identity read failed for {}; treating as unprofiled",
                    villager.getUUID(), t);
            return Optional.empty();
        }
    }

    /** Read-only lookup that never generates — for reports, commands and the trace. */
    public static Optional<VillagerIdentityRecord> peek(MinecraftServer server, UUID villager) {
        if (server == null || villager == null) {
            return Optional.empty();
        }
        try {
            return VillagerIdentitySavedData.get(server).peek(villager);
        } catch (Throwable t) {
            return Optional.empty();
        }
    }

    /** Scores a {@code conversations_profile} condition. Never throws; unprofiled is a non-match. */
    public static boolean matches(Entity villager, ProfileQuery query) {
        if (query == null || !query.isValid()) {
            return false;
        }
        try {
            return query.matches(of(villager).orElse(null));
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * Records that this villager's trade changed (spec §6.4, §12.5).
     *
     * <p>Idempotent: recording the same former profession twice is a no-op, so a detector that fires
     * on both a chunk load and an interaction cannot turn one career change into two.
     */
    public static void onProfessionChanged(Entity villager, String previousProfessionId, long day) {
        if (!enabled() || villager == null || previousProfessionId == null) {
            return;
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return;
        }
        try {
            VillagerIdentitySavedData data = VillagerIdentitySavedData.get(server);
            data.peek(villager.getUUID()).ifPresent(profile -> {
                VillagerIdentityRecord updated = profile.withProfessionChange(previousProfessionId, day);
                if (!updated.equals(profile)) {
                    data.replace(villager.getUUID(), updated);
                }
            });
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("profession-change identity write failed; ignoring", t);
        }
    }

    /** Records the one active formative-event motif after an observed life event (spec §6.4). */
    public static void onFormativeEvent(Entity villager, String motif, long day) {
        if (!enabled() || villager == null || motif == null || motif.isBlank()) {
            return;
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return;
        }
        try {
            VillagerIdentitySavedData data = VillagerIdentitySavedData.get(server);
            data.peek(villager.getUUID()).ifPresent(profile -> {
                VillagerIdentityRecord updated = profile.withFormativeEvent(motif, day);
                if (!updated.equals(profile)) {
                    data.replace(villager.getUUID(), updated);
                }
            });
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("formative-event identity write failed; ignoring", t);
        }
    }

    /** Drops a dead villager's profile, the same way the progress and disposition stores do. */
    public static void forget(MinecraftServer server, UUID villager) {
        if (server == null || villager == null) {
            return;
        }
        try {
            VillagerIdentitySavedData.get(server).removeVillager(villager);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("identity removal failed for {}; ignoring", villager, t);
        }
    }

    private static Optional<VillagerIdentityRecord> generateAndStore(VillagerIdentitySavedData data,
                                                                     MinecraftServer server,
                                                                     Entity villager) {
        IdentityCatalog catalog = IdentityCatalogLoader.active();
        if (catalog.isEmpty()) {
            return Optional.empty();
        }
        ServerLevel overworld = server.overworld();
        long worldSeed = overworld == null ? 0L : overworld.getSeed();
        long seed = VillagerIdentityGenerator.seedFor(worldSeed, villager.getUUID());

        String age = McaCompat.getAgeGroup(villager).orElse(null);
        String professionId = McaCompat.getProfessionId(villager).orElse(null);
        ProfessionProfile profile = ProfessionProfileLoader.profile(professionId,
                McaCompat.getProfessionText(villager).map(c -> c.getString()).orElse("villager"));
        String personality = Personalities.normalize(McaCompat.getPersonality(villager).orElse(null));

        Optional<VillagerIdentityRecord> generated = VillagerIdentityGenerator.generate(
                catalog, seed, age, professionId, profile.archetype().key(), personality);
        // putIfAbsent rather than put: two interactions in the same tick must agree on one profile,
        // and the one that loses the race must return the profile that was actually persisted.
        return generated.map(record -> data.putIfAbsent(villager.getUUID(), record));
    }
}
