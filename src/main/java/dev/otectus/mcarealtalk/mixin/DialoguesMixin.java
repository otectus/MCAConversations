package dev.otectus.mcarealtalk.mixin;

import dev.otectus.mcarealtalk.McaRealTalk;
import dev.otectus.mcarealtalk.McaRealTalkConfig;
import forge.net.mca.resources.Dialogues;
import forge.net.mca.resources.data.dialogue.Question;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

/**
 * Routes MCA's "Chat" button into the Real Talk conversation hub.
 *
 * <p>{@code Dialogues.getQuestion(String)} is MCA's single dialogue routing point — the interact
 * init fetches {@code "root"}, every {@code next} action and {@code selectAnswer} resolve through
 * it — so redirecting the exact name {@code "chat"} here reroutes only the Chat button's
 * {@code next: "chat"} hop. The returned {@code realtalk} question is not {@code auto}, so MCA
 * renders the hub screen, and every subsequent click references {@code "realtalk"} directly
 * (never {@code "chat"} again). {@code chat.topic}/{@code chat.fail} are different names and pass
 * through untouched; the hub's {@code back} answer targets {@code "main"}, also untouched.
 *
 * <p>{@code remap = false}: MCA's own method, no vanilla mapping. {@code require = 0} (config
 * default): if MCA ever reshapes this method the injection silently no-ops and Chat behaves
 * vanilla. Any runtime failure likewise falls through to vanilla chat.
 */
@Mixin(value = Dialogues.class, remap = false)
public abstract class DialoguesMixin {

    @Shadow
    private Map<String, Question> questions;

    @Inject(method = "getQuestion", at = @At("HEAD"), cancellable = true, require = 0)
    private void mcarealtalk$redirectChatToRealTalk(String name, CallbackInfoReturnable<Question> cir) {
        try {
            if (!"chat".equals(name)) {
                return;
            }
            if (!McaRealTalkConfig.COMMON.replaceChatWithRealTalk.get()) {
                return;
            }
            Question hub = questions.get("realtalk");
            if (hub != null) {
                cir.setReturnValue(hub);
            }
            // hub missing (e.g. a datapack removed it) -> fall through to MCA's vanilla chat.
        } catch (Throwable t) {
            McaRealTalk.LOGGER.debug("Chat->RealTalk redirect failed; falling back to vanilla chat", t);
        }
    }
}
