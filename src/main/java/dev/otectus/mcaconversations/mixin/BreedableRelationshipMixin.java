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
 * <p><b>One target, one jar</b> — see {@link NetworkHandlerMixin} for why the MCA package root is
 * given as a string and why {@link Pseudo} is set.
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
 * so Mixin matches it by literal name. It is private, which mixins may still target. The
 * {@code ItemStack.split} target inside it is vanilla and is remapped normally.
 * {@code require = 0}: if MCA reshapes it, gift tracking quietly stops rather than the game failing
 * to start.
 */
@Pseudo
@Mixin(targets = "net.conczin.mca.entity.ai.BreedableRelationship", remap = false)
public abstract class BreedableRelationshipMixin {

    @Inject(
            // Plain name, never a descriptor: a descriptor would spell an MCA package in internal
            // (slash) form into this class's constant pool, which is exactly the static linkage
            // NoMcaStaticLinkTest forbids. acceptGift is not overloaded on the target.
            method = "acceptGift",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;split(I)Lnet/minecraft/world/item/ItemStack;",
                    shift = At.Shift.BEFORE,
                    remap = true),
            require = 0,
            remap = false)
    private void mcaconversations$onAcceptGift(ItemStack stack, @Coerce Object gift, ServerPlayer player,
                                               @Coerce Object memories, CallbackInfo ci) {
        try {
            // One item is what MCA is about to split off and accept; the rest stays with the
            // player. Recording stack.getCount() here would remember a whole stack as gifted.
            McaHandles.relationshipVillager(this)
                    .ifPresent(villager -> GiftTracker.recordAcceptedGift(villager, player,
                            stack.copyWithCount(1)));
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Gift-detection hook failed; ignoring", t);
        }
    }
}
