package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.gift.ForgeCapsMigration;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Imports this mod's 1.20.1 Forge capability data on the first load of an upgraded player file.
 *
 * <p>NeoForge stores attachments under {@code neoforge:attachments}; Forge stored capabilities under
 * a root-level {@code ForgeCaps} compound, and nothing bridges the two. Without this hook a player
 * upgrading a 1.20.1 world silently loses their remembered gifts and their explicit chat-mode
 * choice.
 *
 * <p><b>Why TAIL of {@code readAdditionalSaveData}.</b> NeoForge's {@code Entity.load} patch
 * deserializes attachments earlier in the same method that later calls
 * {@code readAdditionalSaveData}, so injecting at the tail of that call is the first moment where
 * the raw player NBT and the finished attachment state are both available and truthful. Injecting
 * any earlier would see an attachment map that had not been populated yet, and the migration would
 * wrongly conclude there was no new data to protect.
 *
 * <p>Server side only: gift memory and the chat-mode opt-in are read and mutated purely on the
 * logical server, and a client-side {@code Player} never has a Forge-era file to import.
 *
 * <p>All parsing and precedence lives in {@link ForgeCapsMigration} so it can be unit-tested
 * without invoking Mixin.
 */
@Mixin(Player.class)
public abstract class PlayerLegacyDataMixin {

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"), require = 1)
    private void mcaconversations$migrateForgeCaps(CompoundTag tag, CallbackInfo ci) {
        if ((Object) this instanceof ServerPlayer player) {
            ForgeCapsMigration.apply(player, tag);
        }
    }
}
