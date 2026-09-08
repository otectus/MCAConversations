package dev.otectus.mcaconversations.chat;

import dev.otectus.mcaconversations.FeatureId;
import dev.otectus.mcaconversations.McaConversationsConfig;
import dev.otectus.mcaconversations.chat.IntentMatcher.Scored;
import dev.otectus.mcaconversations.compat.McaCompat;
import dev.otectus.mcaconversations.conversation.TopicAgeGate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

/**
 * Read-only eligibility preview (spec §6.6): the only chat matching stage that touches MCA. Because
 * MCA's {@code selectAnswer} trusts the click and does <b>not</b> re-validate an answer's {@code
 * constraints}, chat mode must enforce them itself or a non-spouse could trigger spouse-only content.
 *
 * <p>Two hard gates: the intent's category feature flag, and the answer's constraints via
 * {@link McaCompat#checkConstraints}. Heart/memory/check gates are deliberately <b>not</b> previewed —
 * they live inside the Result conditions and the engine already resolves them to in-character
 * deflections with full parity (§8.1); previewing them would double-implement what §3 chose to reuse.
 */
public final class GatePreview {

    private GatePreview() {
    }

    /** True if this scored intent may be driven for {@code player} against {@code villager}. */
    public static boolean eligible(Entity villager, ServerPlayer player, Scored scored) {
        // Babies never hold a conversation — AgeVoice babbles at them instead. A greeting is the
        // one exception, so waving at a baby still gets an (adorable) reaction rather than silence.
        if (McaCompat.isBaby(villager) && !(scored.isSystem() && "greet".equals(scored.system()))) {
            return false;
        }
        // The category is a runtime string from the intent file; an id that names no feature is an
        // invalid reference, so the intent is not eligible rather than unconditionally eligible.
        String category = scored.category();
        if (category != null && !McaConversationsConfig.isFeatureEnabled(
                FeatureId.parse(category).orElse(null))) {
            return false;
        }
        if (scored.isSystem()) {
            // Insult handling is opt-out via config; when off, the intent must not win at all.
            if ("insult".equals(scored.system()) && !McaConversationsConfig.COMMON.chatModeInsultDetection.get()) {
                return false;
            }
            return true; // system intents route to dispatcher behaviors — no answer constraints to check
        }
        if (!McaCompat.checkConstraints(villager, player, scored.question(), scored.answer())) {
            return false;
        }
        // Chat mode enters topics by the same question/answer pair the GUI does, so it owes the
        // catalog's age allow-list the same answer.
        return TopicAgeGate.allows(scored.question(), scored.answer(), villager);
    }
}
