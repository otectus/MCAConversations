package dev.otectus.mcaconversations.compat;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.Set;

/**
 * The bridge in use when Townstead is not installed, when the integration is switched off, or when
 * Townstead is present but could not be bound at all (Townstead spec 5.1).
 *
 * <p>This is the <b>normal</b> state for most installs, so it is silent: no warning is ever emitted
 * merely because Townstead is absent. Every read returns the neutral view rather than null, every
 * condition built on it scores 0, every template variable falls back to its localized neutral, and
 * every write is a no-op. That is what makes "Townstead absent contributes exactly 0 to every
 * existing seeded check" (spec 4.5) true by construction rather than by discipline at each call site.
 */
final class NoopTownsteadBridge implements TownsteadBridge {

    static final NoopTownsteadBridge INSTANCE = new NoopTownsteadBridge();

    private NoopTownsteadBridge() {
    }

    @Override
    public TownsteadStatus status() {
        return TownsteadStatus.ABSENT;
    }

    @Override
    public Set<TownsteadCapability> capabilities() {
        return Set.of();
    }

    @Override
    public String detectedVersion() {
        return "";
    }

    @Override
    public Optional<String> variant() {
        return Optional.empty();
    }

    @Override
    public TownsteadVillagerView villager(Entity entity) {
        return TownsteadVillagerView.EMPTY;
    }

    @Override
    public TownsteadCalendarView calendar(MinecraftServer server) {
        return TownsteadCalendarView.EMPTY;
    }

    @Override
    public TownsteadBuildingView buildingAt(ServerLevel level, BlockPos pos) {
        return TownsteadBuildingView.EMPTY;
    }

    @Override
    public TownsteadRootView root(ResourceLocation id) {
        return TownsteadRootView.EMPTY;
    }

    @Override
    public TownsteadPersonalityView personality(String personalityId) {
        return TownsteadPersonalityView.EMPTY;
    }

    @Override
    public TownsteadSpiritView spiritForVillage(ServerLevel level, int villageId) {
        return TownsteadSpiritView.EMPTY;
    }

    @Override
    public Set<String> contextTags(Entity villager) {
        return Set.of();
    }

    @Override
    public Set<String> learnedSkills(Entity villager) {
        return Set.of();
    }

    @Override
    public boolean hasSkill(Entity villager, String skillId) {
        return false;
    }

    @Override
    public boolean isKnownSpirit(String spiritId) {
        return false;
    }

    @Override
    public boolean isReactionLocked(Entity villager, long gameTime) {
        return false;
    }

    @Override
    public boolean fireReaction(ServerLevel level, Entity villager, ServerPlayer player,
                                ResourceLocation reaction, Set<String> tags) {
        return false;
    }

    @Override
    public void markHeartChange(Entity villager, int measuredDelta, long gameTime) {
    }

    @Override
    public void dialogueOpen(Entity villager, ServerPlayer player, long gameTime) {
    }

    @Override
    public void dialogueClose(Entity villager, ServerPlayer player, long gameTime) {
    }
}
