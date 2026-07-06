package dev.otectus.mcarealtalk.mixin;

import dev.otectus.mcarealtalk.McaRealTalk;
import dev.otectus.mcarealtalk.gift.GiftTracker;
import forge.net.mca.entity.ai.BreedableRelationship;
import forge.net.mca.entity.ai.Memories;
import forge.net.mca.entity.ai.Relationship;
import forge.net.mca.entity.interaction.gifts.GiftType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Records accepted gifts for the gratitude state and the {@code last_gift_item} template variable.
 * {@code acceptGift} is called only when MCA has decided the gift is taken (rejects go through
 * {@code rejectGift}), with the gifted stack still intact as a parameter — the exact hook the
 * gift tracker needs.
 *
 * <p>{@code remap = false}: {@code acceptGift} is MCA's own method (no vanilla obfuscation
 * mapping), so Mixin must match it by literal name. The villager entity is reached through the
 * public {@code Relationship#getUUID()/getWorld()} accessors rather than the protected
 * {@code entity} field.
 */
@Mixin(value = BreedableRelationship.class, remap = false)
public abstract class BreedableRelationshipMixin {

    @Inject(method = "acceptGift", at = @At("HEAD"), require = 0)
    private void mcarealtalk$onAcceptGift(ItemStack stack, GiftType gift, ServerPlayer player,
                                          Memories memories, CallbackInfo ci) {
        try {
            Relationship<?> self = (Relationship<?>) (Object) this;
            ServerLevel level = self.getWorld();
            Entity villager = level == null ? null : level.getEntity(self.getUUID());
            if (villager != null) {
                GiftTracker.recordAcceptedGift(villager, player, stack);
            }
        } catch (Throwable t) {
            McaRealTalk.LOGGER.debug("Gift-detection hook failed; ignoring", t);
        }
    }
}
