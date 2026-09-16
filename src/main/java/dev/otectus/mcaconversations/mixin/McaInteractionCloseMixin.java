package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.conversation.HandleAuthority;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

/**
 * Refuses MCA's tokenless interaction close when it would end somebody else's conversation.
 *
 * <p>MCA's {@code InteractionCloseRequest} carries a villager UUID and nothing else, and its handler
 * clears that villager's interacting player without checking that the sender <em>is</em> that player.
 * On 1.21.1 the request is a record payload implementing {@code HandleablePayload}, whose server
 * entry point is {@code handleServer(ServerPlayer)} — the same behaviour the 1.20.1 class exposed as
 * {@code receive(ServerPlayer)}, which is why this guard cannot be one shared file across the two
 * loaders. Confirmed on 7.7.33+1.21.1 and 7.7.36-beta.3+1.21.1: one {@code villagerUUID} component,
 * one {@code handleServer(ServerPlayer)}.
 *
 * <p>Alone in single player the missing check is harmless. Once a villager can be handed from one
 * player to another it is not: the first player's window closes a moment later, its close request
 * arrives, and the second player — already mid-conversation — has the villager taken out from under
 * them.
 *
 * <h2>What the guard does in each case</h2>
 * <ul>
 *   <li><b>No managed handle owns the villager.</b> MCA proceeds untouched. This mod has no opinion
 *       about interactions it did not accept — a trade, a chore, a vanilla MCA conversation with no
 *       Conversations offer in it — and must not start breaking closes it knows nothing about.</li>
 *   <li><b>The sender is the owner.</b> MCA proceeds. Its close is correct and expected, and the
 *       discussion ends through this mod's own path: the client sends {@code ConversationCloseC2S}
 *       from the same screen teardown, and the lifecycle terminates the handle as
 *       {@link dev.otectus.mcaconversations.conversation.CloseReason#CLIENT_CLOSED}. Cancelling here
 *       would leave MCA still believing the player has a screen open.</li>
 *   <li><b>The sender is <em>not</em> the owner.</b> Cancelled. The request is a straggler from a
 *       conversation that has already been handed over or torn down, and the villager now belongs to
 *       somebody who is still talking to them.</li>
 * </ul>
 *
 * <p>Note what the guard deliberately does <em>not</em> do: it never ends anything of its own. A
 * cancelled close leaves the current owner exactly as they were, which is the whole point — an old
 * participant's payload may be ignored, never obeyed.
 *
 * <p><b>One NeoForge target</b> — see {@link NetworkHandlerMixin} for why the MCA target is named as
 * a string and why {@link Pseudo} is set. {@code remap = false}: MCA's own method. {@code require = 0}:
 * if MCA reshapes the request the injection silently no-ops and closes behave exactly as they do
 * without this mod, which {@code MixinTargetProbeTest} turns into a build failure rather than a
 * silent loss in a player's world.
 */
@Pseudo
@Mixin(targets = "net.conczin.mca.network.c2s.InteractionCloseRequest", remap = false)
public abstract class McaInteractionCloseMixin {

    @Shadow
    @Final
    private UUID villagerUUID;

    @Inject(method = "handleServer", at = @At("HEAD"), cancellable = true, require = 0)
    private void mcaconversations$guardTokenlessClose(ServerPlayer player, CallbackInfo ci) {
        if (player == null || villagerUUID == null) {
            return;
        }
        try {
            if (!HandleAuthority.allowsNativeCloseOf(villagerUUID, player.getUUID())) {
                ci.cancel();
                McaConversations.LOGGER.debug(
                        "ignored a native interaction close for villager {} from {}: another player owns it",
                        villagerUUID, player.getGameProfile().getName());
            }
        } catch (Throwable t) {
            // Fail open. A guard that throws must never be able to wedge a window shut.
            McaConversations.LOGGER.debug("native close guard failed; letting MCA proceed", t);
        }
    }
}
