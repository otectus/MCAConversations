package dev.otectus.mcaconversations.client.dialogue;

import dev.otectus.mcaconversations.McaConversationsConfig.DialogueMenuStyle;
import dev.otectus.mcaconversations.client.ClientUiResourceGeneration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

/** Cached presentation orchestration plus layered, pixel-aligned response-card drawing. */
public final class DialogueChoiceRenderer {

    /**
     * The card draws every string with a drop shadow, because every string sits on dirt and that is
     * what vanilla does on the same background. Kept as a named constant so the policy is stated once
     * rather than drifting per call site.
     */
    private static final boolean CARD_TEXT_SHADOW = true;

    private final DialogueChoicePresenter presenter = new DialogueChoicePresenter();
    private final DialogueChoiceVisualState visual = new DialogueChoiceVisualState();
    private final DialogueChoiceNarrator narrator = new DialogueChoiceNarrator();

    private ModelKey modelKey;
    private DialoguePresentationBuilder.Model model;
    /** The skin the prepared model was built for. Rebuilt with the model, so the two never disagree. */
    private DialogueSkin skin;
    private int preparedPage = -1;
    private PreparedDialogueCard card;
    private Component footerHint;
    private Component footerSelecting;
    private Component footerPage;
    private int pointerDwellIndex = -1;
    private float pointerDwellAt;
    private boolean pointerDwellNarrated;
    private int scrolledRow = -1;
    private int rowScrollLine;
    private boolean revealComplete;
    private boolean exiting;
    private float exitAt;
    private int renderedFocus;
    private int renderedLock = -1;
    private int renderedPage;
    private int renderedPageCount;

    public void tick() {
        // The style can change between two frames of an open screen. When it changes to the one that
        // hands presentation back to MCA, a card left prepared would keep answering
        // hasOutgoingPresentation and keep MCA's question suppressed under its own native menu.
        if (!ClientChoiceController.conversationsDialogueEnabled() && anythingPrepared()) {
            reset();
        }
        visual.tick();
    }

    /** Whether a model, a page or an exit animation is still holding presentation state. */
    private boolean anythingPrepared() {
        return modelKey != null || model != null || card != null || exiting;
    }

    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick,
                       FormattedText exactQuestion, long questionRevision, Component speakerName,
                       boolean silent, List<FormattedCharSequence> legacyQuestion) {
        render(graphics, mouseX, mouseY, partialTick, exactQuestion, questionRevision, speakerName,
                silent, legacyQuestion, null);
    }

    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick,
                       FormattedText exactQuestion, long questionRevision, Component speakerName,
                       boolean silent, List<FormattedCharSequence> legacyQuestion,
                       LivingEntity speaker) {
        // One reading of the presentation configuration for the whole frame: a style that changed
        // between the model key and the drawing would leave geometry from one skin under another.
        DialogueMenuStyle style = ClientChoiceController.dialogueMenuStyle();
        DialogueStyleProfile profile = DialogueStyleProfile.of(style);
        if (!profile.customRenderer()) {
            // MCA Reborn owns the screen under this style. Dropping the prepared card here as well
            // as in tick() means the switch can never leave one frame with both menus drawn, and no
            // exit animation plays over the native one.
            if (anythingPrepared()) {
                reset();
            }
            return;
        }
        ClientChoiceState state = ClientChoiceMessages.state();
        ClientChoiceState.ClientChoiceOffer offer = state.offer().orElse(null);
        if (offer == null) {
            drawOutgoing(graphics, mouseX, mouseY, partialTick);
            return;
        }
        exiting = false;

        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;
        int width = graphics.guiWidth();
        int height = graphics.guiHeight();
        int configSignature = configSignature();
        ModelKey wanted = new ModelKey(offer.revision(), questionRevision, width, height,
                System.identityHashCode(font), font.lineHeight, ClientUiResourceGeneration.current(),
                minecraft.getLanguageManager().getSelected(), configSignature, silent,
                wantsPortrait(speaker, profile), style);
        if (!wanted.equals(modelKey)) {
            modelKey = wanted;
            skin = DialogueSkin.of(style);
            boolean footer = ClientChoiceController.showHints();
            model = DialoguePresentationBuilder.prepare(font, width, height, exactQuestion,
                    legacyQuestion, speakerName, silent, offer, footer,
                    wantsPortrait(speaker, profile), profile);
            if (!footer && model.pageMap().pages().size() > 1) {
                // Hints are off, but a multi-page offer still needs the footer strip for its page
                // controls. Only the page map changes, so re-pack rather than re-wrapping the text.
                model = model.withFooter(true);
            }
            state.updatePages(model.pageMap().pages());
            preparedPage = -1;
            revealComplete = false;
        }
        if (card == null || preparedPage != state.page()) {
            card = DialoguePresentationBuilder.page(model, state);
            preparedPage = state.page();
            scrolledRow = -1;
            rowScrollLine = 0;
            if (card != null) {
                footerHint = Component.translatable(card.compact()
                                ? "gui.mcaconversations.responses.hint_compact"
                                : "gui.mcaconversations.responses.hint",
                        "1-" + state.visibleCount());
                footerSelecting = Component.translatable("gui.mcaconversations.responses.selecting");
                footerPage = Component.translatable("gui.mcaconversations.responses.page",
                        state.page() + 1, state.pageCount());
            }
        }
        if (card == null) {
            return;
        }

        visual.observe(state, partialTick);
        // A pointer sweep is deliberately silent; only a dwell narrates, and keyboard-driven focus
        // narrates through announceMutation. The returned change flag is intentionally unused.
        presenter.updatePointer(mouseX, mouseY, card, state);
        narrator.offer(offer.revision(), offer.answerIds().size());
        updatePointerDwell(state, offer, partialTick);
        if (state.locked()) {
            Component answer = answerAt(state.lockedIndex());
            if (answer != null) {
                narrator.locked(offer.revision(), state.lockedIndex(), answer);
            }
        }

        ConversationMotionSpec motion = ConversationMotionSpec.current(style);
        float cardProgress = ConversationMotionSpec.easeOutCubic(visual.cardProgress(partialTick, motion));
        float pageProgress = visual.pageProgress(partialTick, motion);
        float alpha = cardProgress;
        int translateY = Math.round((1.0F - cardProgress) * motion.enterDistance());
        int translateX = Math.round((1.0F - pageProgress) * motion.pageDistance());

        renderedFocus = state.focusedIndex();
        renderedLock = state.lockedIndex();
        renderedPage = state.page();
        renderedPageCount = state.pageCount();

        graphics.pose().pushPose();
        graphics.pose().translate(translateX, translateY, 0.0F);
        drawCard(graphics, font, motion, partialTick, alpha, mouseX, mouseY, speaker, profile);
        graphics.pose().popPose();
    }

    public DialogueHitTarget click(double mouseX, double mouseY) {
        revealComplete = true;
        ClientChoiceState state = ClientChoiceMessages.state();
        DialogueHitTarget target = presenter.click(mouseX, mouseY, card, state);
        if (target instanceof DialogueHitTarget.PreviousPage) {
            changePageFromPointer(-1);
        } else if (target instanceof DialogueHitTarget.NextPage) {
            changePageFromPointer(1);
        }
        return target;
    }

    /**
     * Whether an exit animation is still occupying the screen, which is the one case where the card
     * outlives its offer. Gated on ownership: under MCA_ORIGINAL this must answer false whatever is
     * left prepared, or the mixin goes on suppressing MCA's question over MCA's own menu.
     */
    public boolean hasOutgoingPresentation() {
        return ClientChoiceController.conversationsDialogueEnabled()
                && card != null && ClientChoiceMessages.state().offer().isEmpty();
    }

    public boolean scroll(double mouseX, double mouseY, double delta) {
        if (card == null) {
            return false;
        }
        DialogueHitTarget target = card.hit(mouseX, mouseY);
        if (!(target instanceof DialogueHitTarget.Choice choice)) {
            return false;
        }
        PreparedChoiceRow row = card.row(choice.absoluteIndex());
        if (row == null || !row.textClipped()) {
            return false;
        }
        int padding = card.compact() ? 3 : 5;
        int visibleLines = Math.max(1, (row.baseVisualRect().height() - padding * 2) / card.lineStep());
        int maxScroll = Math.max(0, row.lines().size() - visibleLines);
        if (scrolledRow != row.absoluteIndex()) {
            scrolledRow = row.absoluteIndex();
            rowScrollLine = 0;
        }
        rowScrollLine = Math.max(0, Math.min(maxScroll, rowScrollLine + (delta < 0.0D ? 1 : -1)));
        return true;
    }

    public void keyboardInput() {
        revealComplete = true;
        presenter.keyboard();
    }

    public boolean moveFocus(int delta) {
        revealComplete = true;
        presenter.keyboard();
        ClientChoiceState state = ClientChoiceMessages.state();
        int oldPage = state.page();
        boolean changed = state.moveFocus(delta);
        if (changed) {
            announceMutation(oldPage != state.page());
        }
        return changed;
    }

    public boolean focusBoundary(boolean end) {
        revealComplete = true;
        presenter.keyboard();
        boolean changed = ClientChoiceMessages.state().focusBoundary(end);
        if (changed) {
            announceMutation(false);
        }
        return changed;
    }

    public boolean changePage(int delta) {
        revealComplete = true;
        presenter.keyboard();
        boolean changed = ClientChoiceMessages.state().changePage(delta);
        if (changed) {
            announceMutation(true);
        }
        return changed;
    }

    private boolean changePageFromPointer(int delta) {
        ClientChoiceState state = ClientChoiceMessages.state();
        boolean changed = state.changePage(delta);
        if (changed) {
            announceMutation(true);
        }
        return changed;
    }

    public void reset() {
        clearPrepared();
        presenter.reset();
        visual.reset();
        narrator.reset();
        pointerDwellIndex = -1;
        scrolledRow = -1;
        rowScrollLine = 0;
        revealComplete = false;
        exiting = false;
    }

    private void announceMutation(boolean pageChanged) {
        ClientChoiceState state = ClientChoiceMessages.state();
        ClientChoiceState.ClientChoiceOffer offer = state.offer().orElse(null);
        if (offer == null) {
            return;
        }
        if (pageChanged) {
            DialogueUiSounds.page();
            narrator.page(offer.revision(), state.page(), state.pageCount());
        } else {
            DialogueUiSounds.focus();
        }
        Component answer = answerAt(state.focusedIndex());
        if (answer != null) {
            narrator.focus(offer.revision(), state.focusedIndex(), offer.answerIds().size(), answer);
        }
        // No rebuild here. Rows do not move when focus changes, and render() already re-pages when
        // state.page() actually changes -- invalidating on every arrow key re-wrapped the page and
        // discarded the scroll position of a long answer the player was part-way through reading.
    }

    private void updatePointerDwell(ClientChoiceState state,
                                    ClientChoiceState.ClientChoiceOffer offer,
                                    float partialTick) {
        if (presenter.modality() != DialogueChoicePresenter.InputModality.POINTER || state.locked()) {
            pointerDwellIndex = -1;
            return;
        }
        if (pointerDwellIndex != state.focusedIndex()) {
            pointerDwellIndex = state.focusedIndex();
            pointerDwellAt = visual.time(partialTick);
            pointerDwellNarrated = false;
            return;
        }
        if (!pointerDwellNarrated && visual.time(partialTick) - pointerDwellAt >= 6.0F) {
            Component answer = answerAt(pointerDwellIndex);
            if (answer != null) {
                narrator.focus(offer.revision(), pointerDwellIndex, offer.answerIds().size(), answer);
            }
            pointerDwellNarrated = true;
        }
    }

    private Component answerAt(int absoluteIndex) {
        return model != null && absoluteIndex >= 0 && absoluteIndex < model.answers().size()
                ? model.answers().get(absoluteIndex) : null;
    }

    /** A portrait needs both a villager to draw and the player's consent to draw it. */
    private static boolean wantsPortrait(LivingEntity speaker, DialogueStyleProfile profile) {
        // Asked during preparation as well as during drawing, so a style with no portrait gives the
        // question its width back rather than leaving a reserved column empty.
        return speaker != null && profile.portrait() && ClientChoiceController.showSpeakerPortrait();
    }

    private void drawCard(GuiGraphics graphics, Font font,
                          ConversationMotionSpec motion, float partialTick, float alpha,
                          int mouseX, int mouseY, LivingEntity speaker,
                          DialogueStyleProfile profile) {
        DialogueChoiceLayout.Rect panel = card.layout().panel();
        skin.panel(graphics, panel, listBody(panel), alpha);
        drawPortrait(graphics, card.layout().portrait(), alpha, speaker, skin, profile);

        int questionY = card.layout().questionY();
        int questionLines = Math.min(card.questionLines().size(), card.layout().questionLines());
        int budget = revealBudget(motion, partialTick, questionLines);
        for (int i = 0; i < questionLines; i++) {
            FormattedCharSequence line = card.questionLines().get(i);
            if (budget >= 0) {
                if (budget == 0) {
                    break;
                }
                int length = QuestionReveal.length(line);
                if (budget < length) {
                    line = QuestionReveal.limit(line, budget);
                    budget = 0;
                } else {
                    budget -= length;
                }
            }
            graphics.drawString(font, line, card.layout().questionX(), questionY,
                    ConversationPalette.withAlpha(ConversationPalette.TEXT, alpha),
                    CARD_TEXT_SHADOW);
            questionY += card.lineStep();
        }

        PreparedChoiceRow elevated = null;
        for (int i = 0; i < card.visibleRows().size(); i++) {
            PreparedChoiceRow row = card.visibleRows().get(i);
            if (row.absoluteIndex() == renderedLock
                    || (renderedLock < 0 && row.absoluteIndex() == renderedFocus)) {
                elevated = row;
            } else {
                drawRow(graphics, font, row, motion, partialTick, alpha, i, profile);
            }
        }
        if (elevated != null) {
            drawRow(graphics, font, elevated, motion, partialTick, alpha,
                    elevated.visibleNumber() - 1, profile);
        }
        drawFooter(graphics, font, alpha, mouseX, mouseY);

        if (elevated != null && elevated.textClipped() && elevated.hitRect().contains(mouseX, mouseY)) {
            graphics.renderTooltip(font, elevated.answer(), mouseX, mouseY);
        }
    }

    private void drawRow(GuiGraphics graphics, Font font, PreparedChoiceRow row,
                         ConversationMotionSpec motion, float partialTick,
                         float cardAlpha, int visibleIndex, DialogueStyleProfile profile) {
        float entry = visual.rowEntryProgress(visibleIndex, partialTick, motion);
        float focus = visual.focusProgress(row.absoluteIndex(), partialTick, motion);
        boolean locked = renderedLock == row.absoluteIndex();
        boolean focused = renderedFocus == row.absoluteIndex();
        // A style without pop-out keeps the row exactly where the layout put it, so its hitbox and
        // its painted edge stay the same rect however focus moves.
        int outset = 0;
        int lift = 0;
        if (profile.focusPopout()) {
            outset = locked
                    ? Math.round(visual.lockedOutset(partialTick, motion))
                    : Math.round(focus * motion.focusOutset());
            lift = Math.round(focus * motion.focusLift());
        }
        int entryX = Math.round((1.0F - entry) * -motion.rowEntryDistance());
        float alpha = cardAlpha * entry;
        DialogueChoiceLayout.Rect base = row.baseVisualRect();
        DialogueChoiceLayout.Rect rect = new DialogueChoiceLayout.Rect(
                base.x() + entryX - outset, base.y() - lift,
                base.width() + outset * 2, base.height() + lift * 2);
        skin.row(graphics, rect, alpha, focused, locked);

        String numeral = skin.badgeLabel(row.visibleNumber());
        int numeralWidth = font.width(numeral);
        DialogueChoiceLayout.Rect badge =
                DialogueChoiceLayout.badgeRect(rect, font.lineHeight, numeralWidth, profile);
        skin.badge(graphics, badge, alpha, focused || locked);
        graphics.drawString(font, numeral,
                badge.x() + Math.max(0, (badge.width() - numeralWidth) / 2),
                DialogueChoiceLayout.centeredTextY(badge, font.lineHeight),
                ConversationPalette.withAlpha(ConversationPalette.TEXT, alpha), CARD_TEXT_SHADOW);
        int padding = card.compact() ? 3 : 5;
        int textY = rect.y() + padding;
        int textX = rect.x() + card.numberColumn() + Math.round(focus * 2.0F);
        boolean clipped = row.textClipped();
        int firstLine = clipped && scrolledRow == row.absoluteIndex() ? rowScrollLine : 0;
        int visibleLines = clipped
                ? Math.max(1, (rect.height() - padding * 2) / card.lineStep()) : row.lines().size();
        if (clipped) {
            // The scrollbar owns the right-hand gutter, so a long answer is clipped short of it
            // rather than sliding underneath it.
            graphics.enableScissor(rect.x() + card.numberColumn(), rect.y() + 2,
                    rect.x() + rect.width() - 2 - DialogueCardSkin.SCROLLBAR_WIDTH,
                    rect.y() + rect.height() - 2);
        }
        int lastLine = Math.min(row.lines().size(), firstLine + visibleLines);
        try {
            for (int i = firstLine; i < lastLine; i++) {
                FormattedCharSequence line = row.lines().get(i);
                graphics.drawString(font, line, textX, textY,
                        ConversationPalette.withAlpha(ConversationPalette.TEXT, alpha),
                        CARD_TEXT_SHADOW);
                textY += card.lineStep();
            }
        } finally {
            if (clipped) {
                graphics.disableScissor();
            }
        }
        if (clipped) {
            skin.scrollbar(graphics, rect, firstLine, visibleLines,
                    row.lines().size(), alpha);
        }
    }

    /**
     * The recessed strip the choice rows sit in, from the divider down to where the footer rule used
     * to be. Inset by the panel's one-pixel border so that border stays a clean black edge.
     */
    private DialogueChoiceLayout.Rect listBody(DialogueChoiceLayout.Rect panel) {
        int top = card.layout().dividerY();
        int footerY = card.layout().footerY();
        int bottom = footerY < 0 ? panel.y() + panel.height() - 1 : footerY - 4;
        return new DialogueChoiceLayout.Rect(panel.x() + 1, top,
                panel.width() - 2, Math.max(0, bottom - top));
    }

    /**
     * Draws the speaking villager into the header well.
     *
     * <p>Entity rendering is by far the most expensive thing on this card and the most likely to
     * object to an entity in an unusual state, so it is wrapped: a failure leaves an empty frame and
     * the conversation continues. The frame is drawn either way, so the layout never shifts
     * depending on whether the render succeeded.
     */
    private static void drawPortrait(GuiGraphics graphics, DialogueChoiceLayout.Rect frame,
                                     float alpha, LivingEntity speaker, DialogueSkin skin,
                                     DialogueStyleProfile profile) {
        if (frame == null || !profile.portrait()) {
            return;
        }
        skin.portrait(graphics, frame, alpha);
        if (speaker == null) {
            return;
        }
        int inset = 2;
        graphics.enableScissor(frame.x() + inset, frame.y() + inset,
                frame.x() + frame.width() - inset, frame.y() + frame.height() - inset);
        try {
            // Villagers stand a little under two blocks, so half the well's height in pixels per
            // block keeps a whole one inside the frame at any font size.
            int scale = Math.max(6, (frame.height() - inset * 3) / 2);
            // 1.21.1 takes the well as a rect and centres the entity in it, where the older call
            // took a bottom-centre anchor; the inset well is that rect, so the villager lands in the
            // same place at the same scale.
            InventoryScreen.renderEntityInInventoryFollowsAngle(graphics,
                    frame.x() + inset, frame.y() + inset,
                    frame.x() + frame.width() - inset, frame.y() + frame.height() - inset,
                    scale, 0.0625F, 0.0F, 0.0F, speaker);
        } catch (Throwable ignored) {
            // An empty frame is a far better outcome than a broken conversation screen.
        } finally {
            graphics.disableScissor();
        }
    }

    /**
     * Code points of the question to show this frame, or -1 to show all of it.
     *
     * <p>Off by default, skipped entirely when motion is disabled -- a reveal is motion, and a player
     * who turned motion off did not ask for their dialogue to arrive slowly -- and abandoned the
     * moment the player does anything, because input must never wait on an animation.
     */
    private int revealBudget(ConversationMotionSpec motion, float partialTick, int questionLines) {
        if (revealComplete
                || ClientChoiceController.questionRevealMode()
                        != dev.otectus.mcaconversations.McaConversationsConfig.QuestionReveal.FAST
                || motion.mode() == dev.otectus.mcaconversations.McaConversationsConfig.MotionMode.OFF) {
            return -1;
        }
        int total = 0;
        for (int i = 0; i < questionLines; i++) {
            total += QuestionReveal.length(card.questionLines().get(i));
        }
        float progress = visual.questionRevealProgress(partialTick, QuestionReveal.revealTicks(total));
        if (progress >= 1.0F) {
            revealComplete = true;
            return -1;
        }
        return Math.round(total * progress);
    }

    private void drawFooter(GuiGraphics graphics, Font font, float alpha, int mouseX, int mouseY) {
        int footerY = card.layout().footerY();
        if (footerY < 0) {
            return;
        }
        DialogueChoiceLayout.Rect panel = card.layout().panel();
        int muted = ConversationPalette.withAlpha(ConversationPalette.TEXT_MUTED, alpha);
        if (ClientChoiceController.showHints()) {
            Component hint = renderedLock >= 0 ? footerSelecting : footerHint;
            graphics.drawString(font, hint, panel.x() + DialogueChoiceLayout.INNER_PADDING,
                    footerY, muted, CARD_TEXT_SHADOW);
        }
        if (renderedPageCount > 1) {
            Component pageText = footerPage;
            int pageWidth = font.width(pageText);
            DialogueChoiceLayout.Rect firstControl = card.layout().previousPage() != null
                    ? card.layout().previousPage() : card.layout().nextPage();
            int pageX = firstControl == null
                    ? panel.x() + panel.width() - DialogueChoiceLayout.INNER_PADDING - pageWidth
                    : firstControl.x() - 4 - pageWidth;
            graphics.drawString(font, pageText, pageX, footerY, muted, CARD_TEXT_SHADOW);
            drawPageButton(graphics, font, card.layout().previousPage(), "‹",
                    renderedPage > 0, alpha, mouseX, mouseY, skin);
            drawPageButton(graphics, font, card.layout().nextPage(), "›",
                    renderedPage + 1 < renderedPageCount, alpha, mouseX, mouseY, skin);
        }
    }

    /**
     * Both page controls are reserved whenever the offer pages at all, so the control strip never
     * reflows between pages. The unavailable direction is drawn disabled rather than removed; a
     * click on it is consumed and then rejected by {@code ClientChoiceState.changePage}.
     */
    private static void drawPageButton(GuiGraphics graphics, Font font, DialogueChoiceLayout.Rect rect,
                                       String glyph, boolean enabled, float alpha,
                                       int mouseX, int mouseY, DialogueSkin skin) {
        if (rect == null) {
            return;
        }
        boolean hovered = enabled && rect.contains(mouseX, mouseY);
        skin.control(graphics, rect, alpha, enabled, hovered);
        graphics.drawString(font, glyph,
                rect.x() + Math.max(0, (rect.width() - font.width(glyph)) / 2),
                DialogueChoiceLayout.centeredTextY(rect, font.lineHeight),
                ConversationPalette.withAlpha(
                        enabled ? ConversationPalette.TEXT : ConversationPalette.TEXT_MUTED, alpha),
                CARD_TEXT_SHADOW);
    }

    private void clearPrepared() {
        modelKey = null;
        model = null;
        skin = null;
        preparedPage = -1;
        card = null;
        footerHint = null;
        footerSelecting = null;
        footerPage = null;
    }

    private void drawOutgoing(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (card == null) {
            return;
        }
        if (!exiting) {
            exiting = true;
            exitAt = visual.time(partialTick);
        }
        ConversationMotionSpec motion = ConversationMotionSpec.current(
                ClientChoiceController.dialogueMenuStyle());
        float progress = visual.exitProgress(exitAt, partialTick, motion);
        if (progress >= 1.0F) {
            clearPrepared();
            return;
        }
        drawCard(graphics, Minecraft.getInstance().font,
                motion, partialTick, 1.0F - progress, mouseX, mouseY, null,
                DialogueStyleProfile.of(ClientChoiceController.dialogueMenuStyle()));
    }

    private static int configSignature() {
        int result = ClientChoiceController.motionMode().hashCode();
        result = 31 * result + Boolean.hashCode(ClientChoiceController.showHints());
        result = 31 * result + Boolean.hashCode(ClientChoiceController.speakerNameAccent());
        result = 31 * result + Boolean.hashCode(ClientChoiceController.showSpeakerPortrait());
        return 31 * result + ClientChoiceController.questionRevealMode().hashCode();
    }

    private record ModelKey(long revision, long questionRevision, int width, int height,
                            int fontIdentity, int lineHeight, int resourceGeneration,
                            String locale, int configSignature, boolean silent, boolean portrait,
                            DialogueMenuStyle style) {
    }
}
