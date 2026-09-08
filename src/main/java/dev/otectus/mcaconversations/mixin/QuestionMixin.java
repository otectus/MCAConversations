package dev.otectus.mcaconversations.mixin;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.conversation.ConversationGuard;
import dev.otectus.mcaconversations.conversation.TopicAgeGate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * Filters the answers MCA is about to offer: hides our injected {@code main} menu answer when the
 * configured {@link dev.otectus.mcaconversations.HubEntryMode} says it should not be offered.
 *
 * <p>The answer itself is added by a datapack file, which is the right mechanism for *adding* a
 * button — but MCA decides which answers a player may pick purely from {@code Constraint}s, a
 * fixed vocabulary with no notion of our config. A result whose conditions all score zero is
 * still listed, because {@code getValidAnswers} filters on constraints alone and never consults
 * result scores. So removing the button at runtime genuinely requires an injection; this is the
 * narrowest one available and it touches exactly one answer name in exactly one question.
 *
 * <p>Modes: {@code ADDITIVE} keeps the answer (MCA's Chat is untouched, the hub gets its own
 * button); {@code REPLACE} drops it because MCA's Chat already leads to the hub and two entries
 * would be duplicates; {@code HIDDEN} drops it because the UI entry is switched off.
 *
 * <p><b>Two targets, one jar</b> — see {@link NetworkHandlerMixin} for why both MCA package roots
 * are listed and why {@link Pseudo} is set. The question's own name is read through a
 * {@link Shadow}ed {@code getName()} rather than the old {@code ((Question) (Object) this)} cast,
 * which would have named the MCA type this mixin must stay agnostic about.
 *
 * <p>Server-side: {@code getValidAnswers} takes a {@code ServerPlayer}, so this loads no client
 * class and is registered in the common {@code mixins} list. {@code remap = false} (MCA's own
 * method) and {@code require = 0} — if MCA reshapes the method the button simply stays visible
 * rather than the game failing to start.
 */
@Pseudo
@Mixin(targets = {
        "forge.net.mca.resources.data.dialogue.Question",
        "forge.net.conczin.mca.resources.data.dialogue.Question",
}, remap = false)
public abstract class QuestionMixin {

    /** The question our {@code main.json} merges into, and the answer name it adds. */
    private static final String MAIN_QUESTION = "main";
    private static final String CONVERSATIONS_ANSWER = "conversations";

    @Shadow
    public abstract String getName();

    /** One WARN for the age filter, however many questions are scored afterwards. */
    private static boolean mcaconversations$ageFilterWarned;

    @Inject(method = "getValidAnswers", at = @At("RETURN"), require = 0)
    private void mcaconversations$filterAnswers(ServerPlayer player, @Coerce Object villager,
                                                CallbackInfoReturnable<List<String>> cir) {
        List<String> answers = cir.getReturnValue();
        if (answers == null) {
            return;
        }
        try {
            if (!McaConversationsConfig.hubEntryMode().showsOwnButton()
                    // Scoped to the main menu: only the answer we inject there is ours to remove. A
                    // same-named answer in any other question (ours or a third-party pack's) is left be.
                    && MAIN_QUESTION.equals(getName())) {
                answers.remove(CONVERSATIONS_ANSWER);
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.debug("Hub-button visibility filter failed; leaving answer visible", t);
        }
        mcaconversations$filterAnswersByAge(villager, answers);
    }

    /**
     * Drops answers the catalog says this villager is too young to be asked. MCA's constraints have no
     * {@code child} token, so a topic declared {@code "ages": ["teen", "adult"]} would otherwise still
     * be listed for a child and be clickable; result conditions cannot help, because they are scored
     * only after the answer is on the menu.
     *
     * <p>Soft-fail by design: anything unexpected leaves the list exactly as MCA built it, logged once.
     */
    private void mcaconversations$filterAnswersByAge(Object villager, List<String> answers) {
        try {
            String question = getName();
            if (!ConversationGuard.isOurQuestion(question) || !(villager instanceof Entity entity)) {
                return;
            }
            answers.removeIf(answer -> !TopicAgeGate.allows(question, answer, entity));
        } catch (Throwable t) {
            if (!mcaconversations$ageFilterWarned) {
                mcaconversations$ageFilterWarned = true;
                McaConversations.LOGGER.warn("Topic age filter failed; answers left as MCA offered them", t);
            }
        }
    }
}
