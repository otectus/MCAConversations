package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.gift.GiftTracker;
import net.conczin.mca.entity.ai.BreedableRelationship;
import net.conczin.mca.entity.ai.Memories;
import net.conczin.mca.entity.ai.Relationship;
import net.conczin.mca.entity.interaction.gifts.GiftType;
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
 *
 * <p><b>Why the injection point is an INVOKE and not HEAD.</b> {@code acceptGift} is not the
 * "gift accepted" branch — it is the whole decision. Inside it MCA rejects the gift when the
 * villager's inventory is full, when the response is {@code FAIL}, and again when desaturation
 * drags the response down to {@code FAIL}; only the surviving {@code else} branch actually takes
 * the item. The 1.20.1 hook injected at HEAD and therefore recorded every one of those rejections
 * as an accepted gift, handing out gratitude and a {@code last_gift_item} for gifts the villager
 * had just refused.
 *
 * <p>The accepted branch is identified by its single {@code ItemStack.split(1)} call — verified
 * against the resolved MCA jar to be the only {@code ItemStack.split(I)} invocation in the method
 * (see {@code docs/PORT-1.21.1-EVIDENCE.md}). Injecting immediately before it means this fires
 * exactly when, and only when, MCA is about to take the item. {@code stack} still holds the full
 * stack at that moment, so the tracker is given a one-item copy: the villager accepts one item,
 * and that is what should be remembered.
 *
 * <p>{@code require = 1} on purpose. This hook is the whole feature; a silent no-op after an MCA
 * refactor would look like "gifts stopped being remembered" and be diagnosed as a data bug rather
 * than a missing injection.
 *
 * <p>{@code remap = false} on the method: {@code acceptGift} is MCA's own (no vanilla obfuscation
 * mapping), matched by literal name. The {@code ItemStack.split} target inside it is vanilla and is
 * remapped normally. The villager entity is reached through the public
 * {@code Relationship#getUUID()/getWorld()} accessors rather than the protected {@code entity} field.
 */
@Mixin(value = BreedableRelationship.class, remap = false)
public abstract class BreedableRelationshipMixin {

    @Inject(
            method = "acceptGift(Lnet/minecraft/world/item/ItemStack;"
                    + "Lnet/conczin/mca/entity/interaction/gifts/GiftType;"
                    + "Lnet/minecraft/server/level/ServerPlayer;"
                    + "Lnet/conczin/mca/entity/ai/Memories;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;split(I)Lnet/minecraft/world/item/ItemStack;",
                    shift = At.Shift.BEFORE,
                    remap = true),
            require = 1,
            remap = false)
    private void mcaconversations$onAcceptGift(ItemStack stack, GiftType gift, ServerPlayer player,
                                          Memories memories, CallbackInfo ci) {
        try {
            Relationship<?> self = (Relationship<?>) (Object) this;
            ServerLevel level = self.getWorld();
            Entity villager = level == null ? null : level.getEntity(self.getUUID());
            if (villager != null) {
                // One item is what MCA is about to split off and accept; the rest stays with the
                // player. Recording stack.getCount() here would remember a whole stack as gifted.
                GiftTracker.recordAcceptedGift(villager, player, stack.copyWithCount(1));
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Gift-detection hook failed; ignoring", t);
        }
    }
}
