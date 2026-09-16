package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.conversation.ContentReloadCoordinator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import com.google.gson.JsonElement;

import java.util.Map;

/**
 * Routes MCA's "Chat" button into the Conversations conversation hub.
 *
 * <p>{@code Dialogues.getQuestion(String)} is MCA's single dialogue routing point — the interact
 * init fetches {@code "root"}, every {@code next} action and {@code selectAnswer} resolve through
 * it — so redirecting the exact name {@code "chat"} here reroutes only the Chat button's
 * {@code next: "chat"} hop. The returned {@code conversations} question is not {@code auto}, so MCA
 * renders the hub screen, and every subsequent click references {@code "conversations"} directly
 * (never {@code "chat"} again). {@code chat.topic}/{@code chat.fail} are different names and pass
 * through untouched; the hub's {@code back} answer targets {@code "main"}, also untouched.
 *
 * <p><b>One target, one jar</b> — see {@link NetworkHandlerMixin} for why the MCA package root is
 * given as a string and why {@link Pseudo} is set.
 *
 * <p>The shadowed map is declared {@code Map<String, Object>} rather than
 * {@code Map<String, Question>}: Mixin matches a shadowed field on its <em>erased</em> descriptor,
 * which is {@code Ljava/util/Map;} either way, and generics are never compared. That is what lets
 * the field be shadowed without naming MCA's {@code Question} type — the question object only has
 * to be handed straight back to MCA, never inspected.
 *
 * <p>{@code remap = false}: MCA's own method, no vanilla mapping. {@code require = 0} (config
 * default): if MCA ever reshapes this method the injection silently no-ops and Chat behaves
 * vanilla. Any runtime failure likewise falls through to vanilla chat.
 */
@Pseudo
@Mixin(targets = "net.conczin.mca.resources.Dialogues", remap = false)
public abstract class DialoguesMixin {

    /**
     * {@code @Final} because MCA declares this field {@code private final}. Mixin only warns about
     * the mismatch today, but promotes it to an error under {@code -Dmixin.debug.strict=true}.
     */
    @Shadow
    @Final
    private Map<String, Object> questions;

    /**
     * The owned-question lookup boundary.
     *
     * <p>{@code getQuestion} is MCA's single dialogue routing point — the interact init fetches
     * {@code "root"}, every {@code next} action and {@code selectAnswer} resolve through it — which
     * is exactly why it is the right place to answer for names this mod owns. For a
     * {@code conversations}/{@code conversations.*} name, once a bundle has been committed, the
     * committed bundle's executable question is returned and <b>absence is authoritative</b>: a
     * newly parsed owned key the committed bundle never validated is never handed back, because
     * doing so would combine old metadata with new actions, which is the exact mixing this
     * transaction exists to prevent.
     *
     * <p>Before the first commit nothing is pinned, so every lookup falls straight through to MCA and
     * behaviour is unchanged. External names always fall through and stay outside the guarantee.
     *
     * <p>The {@code "chat"} redirect is unchanged in effect, but its hub now comes from the same
     * bundle rather than from whatever is in MCA's live map at that instant.
     */
    @Inject(method = "getQuestion", at = @At("HEAD"), cancellable = true, require = 0)
    private void mcaconversations$resolveOwnedQuestion(String name, CallbackInfoReturnable<Object> cir) {
        try {
            if ("chat".equals(name)) {
                if (!McaConversationsConfig.hubEntryMode().replacesMcaChat()) {
                    return;
                }
                Object hub = ContentReloadCoordinator.governsOwnedLookups()
                        ? ContentReloadCoordinator.ownedQuestion("conversations")
                        : questions.get("conversations");
                if (hub != null) {
                    cir.setReturnValue(hub);
                }
                // hub missing (e.g. a datapack removed it) -> fall through to MCA's vanilla chat.
                return;
            }
            ContentReloadCoordinator.OwnedLookup lookup = ContentReloadCoordinator.lookup(name);
            if (lookup.intercepted()) {
                cir.setReturnValue(lookup.question());
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Owned question lookup failed; deferring to MCA's live map", t);
        }
    }

    /**
     * Binds the running reload attempt to this exact {@code Dialogues} instance before it parses
     * anything, so a callback arriving from a superseded attempt or another server lifecycle is
     * recognisable and cannot commit.
     */
    @Inject(method = "apply", at = @At("HEAD"), require = 0, remap = false)
    private void mcaconversations$applyHead(Map<ResourceLocation, JsonElement> data, ResourceManager manager,
                                            ProfilerFiller profiler, CallbackInfo ci) {
        try {
            ContentReloadCoordinator.onDialoguesApplyHead(this);
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("reload attempt binding failed", t);
        }
    }

    /**
     * The publication point. The shadowed map is read directly here — the lookup boundary above is
     * bypassed on purpose, because what is needed is exactly the newly parsed objects that boundary
     * is hiding.
     *
     * <p>Totally guarded: an exception escaping an injection at this point fails the whole reload
     * future, which is a crash during world load. A failure here means no publication, which means
     * the previous bundle stays in force.
     */
    @Inject(method = "apply", at = @At("TAIL"), require = 0, remap = false)
    private void mcaconversations$applyTail(Map<ResourceLocation, JsonElement> data, ResourceManager manager,
                                            ProfilerFiller profiler, CallbackInfo ci) {
        try {
            ContentReloadCoordinator.onDialoguesApplyTail(this, questions);
        } catch (Throwable t) {
            McaConversations.LOGGER.error("Dialogue retention hook failed; no content was published", t);
        }
    }
}
