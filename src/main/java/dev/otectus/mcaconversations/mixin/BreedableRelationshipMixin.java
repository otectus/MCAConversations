package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.compat.mca.McaHandles;
import dev.otectus.mcaconversations.gift.GiftTracker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Records accepted gifts for the gratitude state and the {@code last_gift_item} template variable.
 * {@code acceptGift} is called only when MCA has decided the gift is taken (rejects go through
 * {@code rejectGift}), with the gifted stack still intact as a parameter — the exact hook the
 * gift tracker needs.
 *
 * <p><b>Two targets, one jar</b> — see {@link NetworkHandlerMixin} for why both MCA package roots
 * are listed and why {@link Pseudo} is set.
 *
 * <p>Two of the four target parameters are MCA types ({@code GiftType}, {@code Memories}) that this
 * hook never reads, so they are taken as {@link Coerce}d {@link Object}s: {@code @Coerce} lets an
 * injector handler declare a supertype of the real parameter, which is what removes the last MCA
 * name from this file.
 *
 * <p><b>Nothing here may be {@code @Shadow}ed.</b> The villager is reached by handing {@code this} to
 * {@link McaHandles#relationshipVillager}, which resolves the inherited {@code getWorld()} and
 * {@code getUUID()} accessors through the binding. Shadowing them directly is what broke: a
 * {@code @Pseudo} mixin can only shadow members declared <em>on the target class itself</em>, because
 * Mixin has no guaranteed view of a pseudo target's supertypes — and both accessors are declared on
 * {@code EntityRelationship} and implemented on {@code Relationship}, never on
 * {@code BreedableRelationship}. Mixin rejected the shadow with {@code InvalidMixinException} while
 * applying the mixin, which is a startup crash, not a disabled feature: shadow resolution happens
 * during pre-processing, long before {@code require = 0} is consulted. The rule that follows is
 * simple — in a {@code @Pseudo} mixin every MCA member goes through {@code McaBinding}, including
 * ones whose signatures are pure vanilla. {@code McaPseudoShadowProbeTest} enforces it.
 *
 * <p>{@code remap = false}: {@code acceptGift} is MCA's own method (no vanilla obfuscation mapping),
 * so Mixin matches it by literal name. It is private, which mixins may still target.
 * {@code require = 0}: if MCA reshapes it, gift tracking quietly stops rather than the game failing
 * to start.
 */
@Pseudo
@Mixin(targets = {
        "forge.net.mca.entity.ai.BreedableRelationship",
        "forge.net.conczin.mca.entity.ai.BreedableRelationship",
}, remap = false)
public abstract class BreedableRelationshipMixin {

    @Inject(method = "acceptGift", at = @At("HEAD"), require = 0)
    private void mcaconversations$onAcceptGift(ItemStack stack, @Coerce Object gift, ServerPlayer player,
                                               @Coerce Object memories, CallbackInfo ci) {
        try {
            McaHandles.relationshipVillager(this)
                    .ifPresent(villager -> GiftTracker.recordAcceptedGift(villager, player, stack));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Gift-detection hook failed; ignoring", t);
        }
    }
}
