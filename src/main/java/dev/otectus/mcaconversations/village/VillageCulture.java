package dev.otectus.mcaconversations.village;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.identity.Identity;
import dev.otectus.mcaconversations.identity.VillagerIdentityRecord;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.ModList;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

/**
 * The read surface for village culture: what this village is like, and what this resident makes of it
 * (spec §17.3).
 *
 * <p>Everything here answers empty rather than throwing. It is called from dialogue conditions and
 * from the context snapshot, both of which run inside MCA's selection loop, and a village that cannot
 * be identified is an ordinary situation rather than an error — a wanderer genuinely has no culture,
 * and the plan says to treat that as unknown rather than inventing one.
 *
 * <p>Generation happens on first ask and is then persisted. A village is not rerolled because a
 * datapack grew, and two players arriving from opposite directions find the same place.
 */
public final class VillageCulture {

    private VillageCulture() {
    }

    public static boolean enabled() {
        return McaConversationsConfig.dynamicFeature("village_culture", false);
    }

    /**
     * The culture of this villager's home village, generating it the first time it is asked for.
     *
     * <p>Empty for an unhoused wanderer, for a villager whose server is not available, and on any
     * install whose datapacks cannot fill all six families.
     */
    public static Optional<VillageCultureRecord> of(Entity villager) {
        if (!enabled() || villager == null) {
            return Optional.empty();
        }
        MinecraftServer server = villager.getServer();
        if (server == null) {
            return Optional.empty();
        }
        OptionalInt villageId = McaCompat.getHomeVillageId(villager);
        if (villageId.isEmpty()) {
            return Optional.empty();
        }
        return forVillage(server, villageId.getAsInt(), dayOf(server));
    }

    /** The culture in force for one village id, generating it if this is the first time. */
    public static Optional<VillageCultureRecord> forVillage(MinecraftServer server, int villageId,
                                                            long day) {
        if (!enabled() || server == null) {
            return Optional.empty();
        }
        try {
            VillageCultureSavedData data = VillageCultureSavedData.get(server);
            Optional<VillageCultureRecord> existing = data.peek(villageId);
            if (existing.isPresent()) {
                return existing;
            }
            ServerLevel overworld = server.overworld();
            long worldSeed = overworld == null ? 0L : overworld.getSeed();
            return VillageCultureGenerator.generate(
                            VillageCultureCatalogLoader.active(),
                            VillageCultureGenerator.seedFor(worldSeed, villageId),
                            villageId, day, VillageCulture::modPresent)
                    // putIfAbsent rather than put: two conversations in the same tick must agree on
                    // one culture, and the one that loses the race takes what was persisted.
                    .map(data::putIfAbsent);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("village culture lookup failed; ignoring", t);
            return Optional.empty();
        }
    }

    /** The token ids of this villager's village, for the context snapshot. Empty for a wanderer. */
    public static Set<String> tokensOf(Entity villager) {
        return of(villager).map(VillageCultureRecord::tokenIds).orElse(Set.of());
    }

    /**
     * What this resident makes of their village's token in one family.
     *
     * <p>{@link CultureStance#IGNORE} for a villager with no identity profile, which is the honest
     * answer: without knowing who somebody is there is no basis for saying they hold a view, and
     * guessing one would be exactly the "specificity without a source" the plan forbids.
     */
    public static CultureStance stanceOf(Entity villager, CultureFamily family) {
        Optional<CultureToken> token = of(villager)
                .flatMap(record -> record.token(family))
                .flatMap(id -> VillageCultureCatalogLoader.active().token(id));
        if (token.isEmpty()) {
            return CultureStance.IGNORE;
        }
        return token.get().stanceFor(identityTokens(villager));
    }

    /**
     * Records that {@code absorbedId} has been taken into {@code survivingId} (spec §17.3).
     *
     * <p>Called when a merge is actually observed. The surviving village keeps its own culture and
     * remembers the merge, so the absorbed id keeps resolving for residents who came across.
     */
    public static boolean merge(MinecraftServer server, int absorbedId, int survivingId) {
        if (!enabled() || server == null) {
            return false;
        }
        try {
            return VillageCultureSavedData.get(server).merge(absorbedId, survivingId);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("village culture merge failed; ignoring", t);
            return false;
        }
    }

    /** A villager's identity tokens as bare ids, which is what a culture token's lists name. */
    static Set<String> identityTokens(Entity villager) {
        Optional<VillagerIdentityRecord> identity = Identity.of(villager);
        if (identity.isEmpty()) {
            return Set.of();
        }
        VillagerIdentityRecord record = identity.get();
        Set<String> tokens = new LinkedHashSet<>(record.interests());
        tokens.addAll(record.values());
        addIfPresent(tokens, record.comfort());
        addIfPresent(tokens, record.aversion());
        addIfPresent(tokens, record.workStyle());
        addIfPresent(tokens, record.socialStyle());
        addIfPresent(tokens, record.disclosureStyle());
        addIfPresent(tokens, record.originMotif());
        return Set.copyOf(tokens);
    }

    private static void addIfPresent(Set<String> into, String token) {
        if (token != null && !token.isBlank()) {
            into.add(token);
        }
    }

    private static long dayOf(MinecraftServer server) {
        ServerLevel overworld = server.overworld();
        return overworld == null ? 0L : overworld.getDayTime() / 24000L;
    }

    private static boolean modPresent(String modId) {
        try {
            return modId != null && ModList.get() != null && ModList.get().isLoaded(modId);
        } catch (Throwable t) {
            return false;
        }
    }
}
