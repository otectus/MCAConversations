package dev.otectus.mcaconversations.compat.townstead;

import dev.otectus.mcaconversations.compat.TownsteadBridge;
import dev.otectus.mcaconversations.compat.TownsteadBuildingView;
import dev.otectus.mcaconversations.compat.TownsteadCalendarView;
import dev.otectus.mcaconversations.compat.TownsteadCapability;
import dev.otectus.mcaconversations.compat.TownsteadPersonalityView;
import dev.otectus.mcaconversations.compat.TownsteadRootView;
import dev.otectus.mcaconversations.compat.TownsteadSpiritView;
import dev.otectus.mcaconversations.compat.TownsteadStatus;
import dev.otectus.mcaconversations.compat.TownsteadVillagerView;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.fml.ModList;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The real {@link TownsteadBridge}, backed by {@link TownsteadHandles}.
 *
 * <p>Instantiated by name from {@code TownsteadCompat} only after {@code ModList} has confirmed
 * Townstead is present, which is why this class may reference {@link TownsteadHandles} and why
 * nothing outside this package may reference <em>it</em>.
 *
 * <p>Constructing it forces the binding, so {@link #status()} is meaningful the moment the object
 * exists and the caller can log one accurate line.
 */
public final class ReflectiveTownsteadBridge implements TownsteadBridge {

    private final TownsteadStatus status;
    private final Set<TownsteadCapability> capabilities;
    private final String version;
    private final Optional<String> variant;

    public ReflectiveTownsteadBridge() {
        TownsteadBinding.Resolution resolution = TownsteadHandles.resolution();
        this.status = resolution.status();
        this.capabilities = resolution.capabilities();
        this.variant = Optional.ofNullable(resolution.variant());
        this.version = ModList.get()
                .getModContainerById("townstead")
                .map(container -> container.getModInfo().getVersion().toString())
                .orElse("");
    }

    @Override
    public TownsteadStatus status() {
        return status;
    }

    @Override
    public Set<TownsteadCapability> capabilities() {
        return capabilities;
    }

    @Override
    public String detectedVersion() {
        return version;
    }

    @Override
    public Optional<String> variant() {
        return variant;
    }

    @Override
    public List<String> unresolvedMembers() {
        return TownsteadHandles.resolution().unresolved();
    }

    /**
     * How many reaction backends Townstead has registered. Zero means every reaction is inert,
     * whatever else bound, because Townstead can only play one through a backend and ships exactly
     * one, for Emotecraft. Surfaced for the status command; not part of the bridge contract, because
     * no other caller should have to know why a reaction declined.
     */
    public int reactionBackendCount() {
        return TownsteadHandles.reactionBackendCount();
    }

    // --- reads -----------------------------------------------------------------------------------

    @Override
    public TownsteadVillagerView villager(Entity entity) {
        return TownsteadHandles.villager(entity);
    }

    @Override
    public TownsteadCalendarView calendar(MinecraftServer server) {
        return TownsteadHandles.calendar(server);
    }

    @Override
    public TownsteadBuildingView buildingAt(ServerLevel level, BlockPos pos) {
        return TownsteadHandles.buildingAt(level, pos);
    }

    @Override
    public TownsteadRootView root(ResourceLocation id) {
        return TownsteadHandles.root(id);
    }

    @Override
    public TownsteadPersonalityView personality(String personalityId) {
        return TownsteadHandles.personalityOf(personalityId);
    }

    @Override
    public TownsteadSpiritView spiritForVillage(ServerLevel level, int villageId) {
        return TownsteadHandles.spirit(level, villageId);
    }

    @Override
    public Set<String> contextTags(Entity villager) {
        return TownsteadHandles.contextTags(villager);
    }

    @Override
    public Set<String> learnedSkills(Entity villager) {
        return TownsteadHandles.learnedSkills(villager);
    }

    @Override
    public boolean hasSkill(Entity villager, String skillId) {
        return TownsteadHandles.hasSkill(villager, skillId);
    }

    @Override
    public boolean isKnownSpirit(String spiritId) {
        return TownsteadHandles.isKnownSpirit(spiritId);
    }

    @Override
    public boolean isReactionLocked(Entity villager, long gameTime) {
        return TownsteadHandles.isReactionLocked(villager, gameTime);
    }

    // --- writes ----------------------------------------------------------------------------------

    @Override
    public boolean fireReaction(ServerLevel level, Entity villager, ServerPlayer player,
                                ResourceLocation reaction, Set<String> tags) {
        return TownsteadHandles.fireReaction(level, villager, player, reaction, tags);
    }

    @Override
    public void markHeartChange(Entity villager, int measuredDelta, long gameTime) {
        TownsteadHandles.markHeartChange(villager, measuredDelta, gameTime);
    }

    @Override
    public void dialogueOpen(Entity villager, ServerPlayer player, long gameTime) {
        TownsteadHandles.dialogueOpen(villager, player, gameTime);
    }

    @Override
    public void dialogueClose(Entity villager, ServerPlayer player, long gameTime) {
        TownsteadHandles.dialogueClose(villager, player, gameTime);
    }
}
