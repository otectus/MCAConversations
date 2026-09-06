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
 * The bridge in use when MCA Capitals is not installed, when the integration is switched off, or
 * when Capitals is present but could not be bound at all.
 *
 * <p>This is the <b>normal</b> state for most installs, so it is silent: no warning is ever emitted
 * merely because Capitals is absent. Every read answers "no capital", which leaves every
 * capital context field unavailable and every capital-gated scene ineligible, by construction
 * rather than by discipline at each call site.
 */
final class NoopCapitalsBridge implements CapitalsBridge {

    static final NoopCapitalsBridge INSTANCE = new NoopCapitalsBridge();

    private NoopCapitalsBridge() {
    }

    @Override
    public CapitalsStatus status() {
        return CapitalsStatus.ABSENT;
    }

    @Override
    public Set<CapitalsCapability> capabilities() {
        return Set.of();
    }

    @Override
    public Optional<CapitalCourtView> courtOfVillage(ServerLevel level, int villageId) {
        return Optional.empty();
    }

    @Override
    public Optional<CapitalCourtView> courtOfResident(ServerLevel level, UUID villager) {
        return Optional.empty();
    }

    @Override
    public List<CapitalCourtView> allCourts(MinecraftServer server) {
        return List.of();
    }

    @Override
    public Optional<ServerLevel> levelOf(MinecraftServer server, CapitalCourtView court) {
        return Optional.empty();
    }

    @Override
    public CapitalStandingView standingOf(ServerLevel level, CapitalCourtView court, UUID villager,
                                          @Nullable Entity loadedEntity) {
        return CapitalStandingView.none();
    }

    @Override
    public List<CapitalRelationView> relationsOf(ServerLevel level, CapitalCourtView court) {
        return List.of();
    }

    @Override
    public List<CapitalChronicleEventView> chronicleSince(ServerLevel level, CapitalCourtView court,
                                                          int fromIndex, int max) {
        return List.of();
    }

    @Override
    public Optional<UUID> declaredCapitalOf(ServerLevel level, UUID player) {
        return Optional.empty();
    }

    @Override
    public String displayName(ServerLevel level, CapitalCourtView court, UUID entity) {
        return "";
    }
}
