package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
 * <p><b>Two targets, one jar</b> — see {@link NetworkHandlerMixin} for why both MCA package roots
 * are listed and why {@link Pseudo} is set.
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
@Mixin(targets = {
        "forge.net.mca.resources.Dialogues",
        "forge.net.conczin.mca.resources.Dialogues",
}, remap = false)
public abstract class DialoguesMixin {

    /**
     * {@code @Final} because MCA declares this field {@code private final}. Mixin only warns about
     * the mismatch today, but promotes it to an error under {@code -Dmixin.debug.strict=true}.
     */
    @Shadow
    @Final
    private Map<String, Object> questions;

    @Inject(method = "getQuestion", at = @At("HEAD"), cancellable = true, require = 0)
    private void mcaconversations$redirectChatToConversations(String name, CallbackInfoReturnable<Object> cir) {
        try {
            if (!"chat".equals(name)) {
                return;
            }
            if (!McaConversationsConfig.hubEntryMode().replacesMcaChat()) {
                return;
            }
            Object hub = questions.get("conversations");
            if (hub != null) {
                cir.setReturnValue(hub);
            }
            // hub missing (e.g. a datapack removed it) -> fall through to MCA's vanilla chat.
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Chat->Conversations redirect failed; falling back to vanilla chat", t);
        }
    }
}
