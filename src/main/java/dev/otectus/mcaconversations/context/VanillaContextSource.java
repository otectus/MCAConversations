package dev.otectus.mcaconversations.context;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * Everything readable from Minecraft itself: time, weather, biome, shelter, health and what the
 * player is holding (spec §7.2).
 *
 * <p>Always available, so it is the one source whose absence is not a case anyone has to handle. It
 * is also the source that most needs to resist over-claiming: every field it writes is a coarse band,
 * because "it is raining" is a fact a villager can observe and "it is raining at 62% intensity over
 * block 412, 71" is not something anyone would ever say.
 */
public final class VanillaContextSource implements ConversationContextSource {

    public static final String ID = "vanilla";

    /** Item tags coarse enough to speak about, checked against what the player is visibly holding. */
    private static final List<String> HELD_TAG_PROBES = List.of(
            "forge:ingots/iron", "forge:ingots/gold", "forge:gems/diamond", "forge:tools",
            "minecraft:swords", "minecraft:pickaxes", "minecraft:hoes", "minecraft:axes",
            "minecraft:flowers", "minecraft:saplings", "minecraft:fishes", "minecraft:logs",
            "minecraft:wool", "minecraft:beds", "forge:crops", "forge:seeds", "forge:string",
            "forge:leather", "forge:feathers", "minecraft:banners");

    private static final List<ContextKey<?>> DECLARES = List.of(
            ContextKeys.PLACE_DIMENSION, ContextKeys.PLACE_BIOME_FAMILY, ContextKeys.PLACE_SHELTERED,
            ContextKeys.TIME_DAY, ContextKeys.TIME_BAND,
            ContextKeys.WEATHER_STATE,
            ContextKeys.SPEAKER_HEALTH_BAND,
            ContextKeys.PLAYER_UUID, ContextKeys.PLAYER_NAME, ContextKeys.PLAYER_HEALTH_BAND,
            ContextKeys.PLAYER_HELD_TAGS);

    @Override
    public String id() {
        return ID;
    }

    @Override
    public List<ContextKey<?>> declares() {
        return DECLARES;
    }

    @Override
    public void contribute(ContextSnapshotBuilder builder, ContextRequest request) {
        Entity villager = request.villager();
        ServerPlayer player = request.player();
        if (villager == null || villager.level() == null) {
            builder.allUnavailable(DECLARES);
            builder.reportCapability(ContextCapabilities.Status.FAILED, "no villager or level");
            return;
        }
        Level level = villager.level();

        // --- Volatile: re-read at every turn boundary --------------------------------------------
        builder.put(ContextKeys.TIME_BAND, timeBand(level.getDayTime()));
        builder.put(ContextKeys.WEATHER_STATE, weatherState(level));
        builder.put(ContextKeys.PLACE_SHELTERED, sheltered(villager));
        builder.put(ContextKeys.SPEAKER_HEALTH_BAND, healthBand(villager));
        if (player == null) {
            builder.unknown(ContextKeys.PLAYER_HEALTH_BAND);
            builder.unknown(ContextKeys.PLAYER_HELD_TAGS);
        } else {
            builder.put(ContextKeys.PLAYER_HEALTH_BAND, healthBand(player));
            builder.put(ContextKeys.PLAYER_HELD_TAGS, heldTags(player));
        }
        if (request.volatileOnly()) {
            builder.reportCapability(ContextCapabilities.Status.READY, "");
            return;
        }

        // --- Pinned -------------------------------------------------------------------------------
        builder.put(ContextKeys.TIME_DAY, level.getDayTime() / 24000L);
        builder.put(ContextKeys.PLACE_DIMENSION, level.dimension().location().toString());
        builder.put(ContextKeys.PLACE_BIOME_FAMILY, biomeFamily(level, villager.blockPosition()));
        if (player == null) {
            builder.unknown(ContextKeys.PLAYER_UUID);
            builder.unknown(ContextKeys.PLAYER_NAME);
        } else {
            builder.put(ContextKeys.PLAYER_UUID, player.getUUID());
            builder.put(ContextKeys.PLAYER_NAME, player.getGameProfile().getName());
        }
        builder.reportCapability(ContextCapabilities.Status.READY, "");
    }

    /** Six bands over a Minecraft day, chosen so each names something a villager would say. */
    static String timeBand(long dayTime) {
        long t = Math.floorMod(dayTime, 24000L);
        if (t < 1000) {
            return "dawn";
        }
        if (t < 5000) {
            return "morning";
        }
        if (t < 7000) {
            return "midday";
        }
        if (t < 11000) {
            return "afternoon";
        }
        if (t < 13500) {
            return "dusk";
        }
        return "night";
    }

    static String weatherState(Level level) {
        if (level.isThundering()) {
            return "storm";
        }
        return level.isRaining() ? "rain" : "clear";
    }

    /**
     * Whether there is sky above the speaker.
     *
     * <p>Not a roof test — it is the same question {@code canSeeSky} answers, which is exactly what
     * makes "we should get inside" honest or absurd. A cave counts as sheltered; that is correct for
     * the only use the field has.
     */
    private static boolean sheltered(Entity entity) {
        try {
            BlockPos pos = entity.blockPosition();
            return !entity.level().canSeeSky(pos);
        } catch (Throwable t) {
            return false;
        }
    }

    /** Three bands. A hit-point number is not something one villager can see about another. */
    static String healthBand(Entity entity) {
        if (!(entity instanceof LivingEntity living)) {
            return "hale";
        }
        float max = living.getMaxHealth();
        if (max <= 0) {
            return "hale";
        }
        float ratio = living.getHealth() / max;
        if (ratio >= 0.85f) {
            return "hale";
        }
        return ratio >= 0.4f ? "hurt" : "grave";
    }

    /**
     * Coarse biome families.
     *
     * <p>Derived from the registry path rather than from climate numbers, because the families exist
     * to select dialogue ("this cold ground", "the water out there") and a path substring is stable
     * across the biome-tag churn between Minecraft versions in a way climate lookups are not.
     */
    static String biomeFamily(Level level, BlockPos pos) {
        try {
            ResourceKey<Biome> key = level.getBiome(pos).unwrapKey().orElse(null);
            if (key == null) {
                return "unknown";
            }
            String path = key.location().getPath();
            if (level.dimension() == Level.NETHER) {
                return "nether";
            }
            if (level.dimension() == Level.END) {
                return "end";
            }
            if (path.contains("ocean") || path.contains("river") || path.contains("beach")) {
                return "aquatic";
            }
            if (path.contains("snow") || path.contains("frozen") || path.contains("ice")
                    || path.contains("cold") || path.contains("taiga")) {
                return "cold";
            }
            if (path.contains("desert") || path.contains("badlands") || path.contains("savanna")) {
                return "arid";
            }
            if (path.contains("jungle") || path.contains("swamp") || path.contains("mangrove")) {
                return "humid";
            }
            if (path.contains("cave") || path.contains("deep_dark") || path.contains("lush")) {
                return "underground";
            }
            if (path.contains("peaks") || path.contains("hills") || path.contains("mountain")
                    || path.contains("slopes")) {
                return "highland";
            }
            return "temperate";
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("biome family read failed; unknown", t);
            return "unknown";
        }
    }

    /**
     * Which coarse tags the player's held items match.
     *
     * <p>Deliberately a bounded probe list rather than "every tag on the stack": the point is to
     * enable "is that iron?", and an unbounded tag dump would let a scene condition on something no
     * villager could name (spec §7.3).
     */
    private static Set<String> heldTags(ServerPlayer player) {
        Set<String> tags = new LinkedHashSet<>();
        try {
            for (ItemStack stack : List.of(player.getMainHandItem(), player.getOffhandItem())) {
                if (stack == null || stack.isEmpty()) {
                    continue;
                }
                for (String probe : HELD_TAG_PROBES) {
                    net.minecraft.resources.ResourceLocation id =
                            net.minecraft.resources.ResourceLocation.tryParse(probe);
                    if (id == null) {
                        continue;
                    }
                    if (stack.is(net.minecraft.tags.TagKey.create(Registries.ITEM, id))) {
                        tags.add(probe);
                    }
                }
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("held tag probe failed; treating as empty", t);
        }
        return Set.copyOf(tags);
    }

    /** Test seam: the level's day, defaulting to 0 rather than throwing on a null level. */
    static long dayOf(ServerLevel level) {
        return level == null ? 0L : level.getDayTime() / 24000L;
    }

    /** Normalises a token the way every source in this package does. */
    static String token(Optional<String> raw) {
        return raw.map(s -> s.trim().toLowerCase(Locale.ROOT)).orElse("");
    }
}
