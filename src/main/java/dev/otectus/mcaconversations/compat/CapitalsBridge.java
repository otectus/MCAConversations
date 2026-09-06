package dev.otectus.mcaconversations.compat;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * The seam to the optional MCA Capitals integration, built to the discipline
 * {@link TownsteadBridge} already uses in this mod.
 *
 * <p><b>Only {@code java.*} and {@code net.minecraft.*} types, plus Conversations' own
 * {@code Capital*View} records, may appear in this interface.</b> The real implementation lives
 * under {@code compat.capitals} and is reached by name from {@link CapitalsCompat} only after
 * {@code ModList} confirms Capitals is present. Nothing here, nor anything reachable from here
 * without that check, may name a Capitals type; {@code NoCapitalsStaticLinkTest} enforces it.
 *
 * <p><b>Every method is total.</b> Reads return an empty optional, an empty list or the neutral view
 * rather than throwing, because they are called from dialogue condition scoring and from a server
 * tick, where an exception would take a conversation, or a tick, with it.
 */
public interface CapitalsBridge {

    /** How much of Capitals bound. {@link CapitalsStatus#ABSENT} when the mod is not installed. */
    CapitalsStatus status();

    /** The capabilities that bound. Empty when Capitals is absent. */
    Set<CapitalsCapability> capabilities();

    /**
     * Members that were expected but did not bind, named for a bug report. Empty when everything
     * bound and when Capitals is absent: an absent mod is not a partial binding.
     */
    default List<String> unresolvedMembers() {
        return List.of();
    }

    // ---------------------------------------------------------------------------------- courts

    /** The capital seated at this MCA village, when there is one. */
    Optional<CapitalCourtView> courtOfVillage(ServerLevel level, int villageId);

    /**
     * The capital this villager belongs to. Tries their home village first, because that is the
     * cheap lookup, and only then asks Capitals to resolve them by entity id.
     */
    Optional<CapitalCourtView> courtOfResident(ServerLevel level, UUID villager);

    /** Every capital on the server, in whatever order Capitals holds them. */
    List<CapitalCourtView> allCourts(MinecraftServer server);

    /** The level a capital's village sits in, when it is loaded. */
    Optional<ServerLevel> levelOf(MinecraftServer server, CapitalCourtView court);

    // -------------------------------------------------------------------------------- villagers

    /**
     * Where one villager stands in one capital. {@code loadedEntity} is optional and used only for
     * the reads Capitals can answer solely from a loaded entity (house words, surname); everything
     * else resolves from the uuid, so an unloaded villager still gets their title.
     */
    CapitalStandingView standingOf(ServerLevel level, CapitalCourtView court, UUID villager,
                                   @Nullable Entity loadedEntity);

    /** This capital's relations with every other capital it has one with. */
    List<CapitalRelationView> relationsOf(ServerLevel level, CapitalCourtView court);

    /**
     * At most {@code max} chronicle entries at or after {@code fromIndex}, in chronological order.
     * Read from the live record rather than from {@code court}, so a poller that kept a snapshot
     * still sees what has happened since it took one.
     */
    List<CapitalChronicleEventView> chronicleSince(ServerLevel level, CapitalCourtView court,
                                                   int fromIndex, int max);

    /** The capital this player has declared for, if any. */
    Optional<UUID> declaredCapitalOf(ServerLevel level, UUID player);

    /**
     * A display name for a villager who may not be loaded. Never {@code null}: an unknown entity is
     * the empty string, which every caller already has a fallback for.
     */
    String displayName(ServerLevel level, CapitalCourtView court, UUID entity);

    // --------------------------------------------------------------------------- convenience

    default boolean has(CapitalsCapability capability) {
        return capabilities().contains(capability);
    }

    /** True when Capitals is installed and at least its core capability bound. */
    default boolean isAvailable() {
        return status() == CapitalsStatus.FULL || status() == CapitalsStatus.PARTIAL;
    }

    /**
     * The live bridge. Never {@code null}: an absent Capitals is {@link NoopCapitalsBridge}, whose
     * every answer is the neutral one.
     */
    final class Holder {
        private static volatile CapitalsBridge instance = NoopCapitalsBridge.INSTANCE;

        private Holder() {
        }

        public static CapitalsBridge get() {
            return instance;
        }

        /** Set once from {@link CapitalsCompat#init()}; last writer wins. */
        public static void set(CapitalsBridge bridge) {
            instance = bridge == null ? NoopCapitalsBridge.INSTANCE : bridge;
        }
    }
}
