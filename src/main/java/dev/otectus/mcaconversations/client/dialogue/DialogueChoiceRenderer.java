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
import org.lwjgl.glfw.GLFW;

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
    private final DialogueReadingState reading = new DialogueReadingState();
    private final ConfirmationArming arming = new ConfirmationArming();
    private final DialogueUtilityState utility = new DialogueUtilityState();
    private final PresentationSettings settings =
            new PresentationSettings(ConfigPresentationStore.INSTANCE);

    private ModelKey modelKey;
    private DialoguePresentationBuilder.Model model;
    /** The skin the prepared model was built for. Rebuilt with the model, so the two never disagree. */
    private DialogueSkin skin;
    private int preparedPage = -1;
    private PreparedDialogueCard card;
    private Component footerSelecting;
    private Component footerPage;
    private DialogueControlModel controls = new DialogueControlModel(true, 0, false, false);
    private int preparedScroll;
    private int preparedExpanded = -1;
    private long preparedRevision = -1L;
    private int pointerDwellIndex = -1;
    private float pointerDwellAt;
    private boolean pointerDwellNarrated;
    private boolean revealComplete;
    private boolean exiting;
    private float exitAt;
    private int renderedFocus;
    private int renderedLock = -1;
    private int renderedPage;
    private int renderedPageCount;
    /** The shell action's hitbox while an explanation is on screen; null whenever it is not. */
    private DialogueChoiceLayout.Rect lapseAction;
    private Runnable lapseReturn = () -> {};
    /** Where the open utility put its rows this frame; null whenever none is open. */
    private DialogueUtilityView.Placement utilityPlacement;
    /** The footer's two utility buttons; null while the card has no footer strip. */
    private DialogueChoiceLayout.Rect historyButton;
    private DialogueChoiceLayout.Rect settingsButton;

    public void tick() {
        syncConfirmationArming();
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
        syncConfirmationArming();
        ClientChoiceState state = ClientChoiceMessages.state();
        ClientChoiceState.ClientChoiceOffer offer = state.offer().orElse(null);
        if (offer == null) {
            drawOutgoing(graphics, mouseX, mouseY, partialTick);
            drawLapse(graphics, state, style);
            return;
        }
        lapseAction = null;
        exiting = false;
        if (preparedRevision != offer.revision()) {
            // A new offer is new text: the old scroll offsets and the response being read belong to
            // answers that no longer exist. The player's region survives, and so does the arming of
            // a confirmation key still held from the answer that produced this offer.
            preparedRevision = offer.revision();
            reading.offerChanged();
            arming.offerChanged();
        }

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
            // Paging and utilities need their reserved strip even when the control hint is hidden.
            // Reserving it per offer made the response viewport jump as page counts changed.
            boolean footer = true;
            model = DialoguePresentationBuilder.prepare(font, width, height, exactQuestion,
                    legacyQuestion, speakerName, silent, offer, footer,
                    wantsPortrait(speaker, profile), profile);
            state.updatePages(model.pageMap().pages());
            preparedPage = -1;
            revealComplete = false;
        }
        // Twice at most: the first pass can discover that the document is shorter than the scroll
        // offset it was built with, and the clamped offset then needs one more pass to take effect.
        for (int attempt = 0; attempt < 2; attempt++) {
            if (card == null || preparedPage != state.page()
                    || preparedScroll != reading.responseScroll()
                    || preparedExpanded != reading.expandedIndex()) {
                card = DialoguePresentationBuilder.page(model, state, reading);
                preparedPage = state.page();
                preparedScroll = reading.responseScroll();
                preparedExpanded = reading.expandedIndex();
                if (card != null) {
                    footerSelecting = Component.translatable(
                            "gui.mcaconversations.responses.selecting");
                    footerPage = Component.translatable("gui.mcaconversations.responses.page",
                            state.page() + 1, state.pageCount());
                }
            }
            if (card == null) {
                return;
            }
            reading.questionBounds(card.questionLines().size(), card.layout().questionLines());
            reading.responseBounds(card.layout().documentHeight(),
                    card.layout().responseViewport().height());
            if (preparedScroll == reading.responseScroll()) {
                break;
            }
        }
        controls = new DialogueControlModel(ClientChoiceController.numericShortcutsEnabled(),
                state.visibleCount(), state.pageCount() > 1,
                reading.questionOverflows() || reading.responsesOverflow());

        visual.observe(state, partialTick);
        // A pointer sweep is deliberately silent; only a dwell narrates, and keyboard-driven focus
        // narrates through announceMutation. The returned change flag is intentionally unused.
        if (!utility.isOpen()) {
            presenter.updatePointer(mouseX, mouseY, card, state);
        }
        narrator.offer(offer.revision(), offer.answerIds().size());
        if (!utility.isOpen()) {
            updatePointerDwell(state, offer, partialTick);
        }
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
        if (clickLapseAction(state, mouseX, mouseY)) {
            return new DialogueHitTarget.Responses();
        }
        if (state.lapseFor(dev.otectus.mcaconversations.conversation.ConversationSession.Frontend.GUI)) {
            return new DialogueHitTarget.Responses();
        }
        // Answered before anything that could reach a response. While a utility is open the card's
        // selection is not merely filtered out, it is not consulted at all.
        DialogueHitTarget utilityTarget = clickUtility(mouseX, mouseY);
        if (utilityTarget != null) {
            return utilityTarget;
        }
        DialogueHitTarget target = presenter.click(mouseX, mouseY, card, state);
        if (target instanceof DialogueHitTarget.PreviousPage) {
            changePageFromPointer(-1);
        } else if (target instanceof DialogueHitTarget.NextPage) {
            changePageFromPointer(1);
        } else if (target instanceof DialogueHitTarget.Question) {
            reading.region(DialogueReadingState.Region.QUESTION);
        } else if (target instanceof DialogueHitTarget.Choice
                || target instanceof DialogueHitTarget.Responses) {
            reading.region(DialogueReadingState.Region.RESPONSES);
        }
        return target;
    }

    /**
     * Whether a confirmation control may act on this press.
     *
     * <p>False means the control has been held since before this offer, or since the previous
     * confirmation: the caller must still consume the event, or the repeat falls through to whatever
     * the host screen would have done with it.
     */
    public boolean confirmPress(int control) {
        return arming.press(control);
    }

    public void confirmRelease(int control) {
        arming.release(control);
    }

    /**
     * Reconciles held confirmation controls with the real device, from a caller that runs whether or
     * not an offer is up. Releases must be seen during the gap between two offers, or the first
     * press after a genuine release is mistaken for the repeat of the press that closed the last one.
     */
    public void syncConfirmationInput() {
        syncConfirmationArming();
    }

    /** Reconciles held confirmation controls with the window, including after a focus change. */
    private void syncConfirmationArming() {
        try {
            long window = Minecraft.getInstance().getWindow().getWindow();
            arming.sync(control -> control == ConfirmationArming.POINTER
                    ? GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_LEFT)
                            != GLFW.GLFW_RELEASE
                    : GLFW.glfwGetKey(window, control) != GLFW.GLFW_RELEASE);
        } catch (Throwable ignored) {
            // Without a window there is no device to reconcile with; the press/release pairs the
            // screen delivers are still tracked.
        }
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

    /**
     * Routes the wheel to the region it is over.
     *
     * <p>A wheel event inside a reading region belongs to that region even when the region cannot
     * scroll any further, which is the whole point: reaching the end of the question used to turn
     * the answer page underneath it.
     */
    public boolean scroll(double mouseX, double mouseY, double delta) {
        if (card == null) {
            return false;
        }
        int direction = delta < 0.0D ? 1 : -1;
        if (utility.isOpen()) {
            // The wheel belongs to whatever is drawn in the viewport, and that is the utility.
            utility.scroll(direction * Math.max(1, card.lineStep()) * 3,
                    utilityPlacement == null ? 0 : utilityPlacement.maxScroll());
            return true;
        }
        DialogueHitTarget target = card.hit(mouseX, mouseY);
        if (target instanceof DialogueHitTarget.Question) {
            reading.region(DialogueReadingState.Region.QUESTION);
            reading.scrollQuestion(direction);
            return true;
        }
        if (target instanceof DialogueHitTarget.Choice
                || target instanceof DialogueHitTarget.Responses) {
            reading.region(DialogueReadingState.Region.RESPONSES);
            if (reading.responsesOverflow()) {
                reading.scrollResponses(direction * card.lineStep() * 3);
            } else {
                changePageFromPointer(direction);
            }
            return true;
        }
        if (target instanceof DialogueHitTarget.PreviousPage
                || target instanceof DialogueHitTarget.NextPage) {
            changePageFromPointer(direction);
            return true;
        }
        return false;
    }

    public void keyboardInput() {
        revealComplete = true;
        presenter.keyboard();
    }

    /**
     * Tab, the arrow family and the reading action, routed by the region the keyboard is in.
     *
     * <p>Inside a reading region the arrows read text and are always consumed; in the ordinary list
     * they move the selection and turn pages, exactly as before.
     */
    public boolean navigationKey(int keyCode, boolean shift) {
        if (card == null) {
            return false;
        }
        keyboardInput();
        if (utility.isOpen()) {
            return utilityKey(keyCode, shift);
        }
        switch (keyCode) {
            case GLFW.GLFW_KEY_H -> {
                return openUtility(DialogueUtilityState.Utility.HISTORY);
            }
            case GLFW.GLFW_KEY_P -> {
                return openUtility(DialogueUtilityState.Utility.SETTINGS);
            }
            case GLFW.GLFW_KEY_TAB -> {
                if (reading.cycleRegion(shift, controls.paging())) {
                    reading.collapse();
                    narrateRegion();
                }
                return true;
            }
            case GLFW.GLFW_KEY_R -> {
                return toggleReading();
            }
            case GLFW.GLFW_KEY_ESCAPE -> {
                // Only consumed when it actually leaves reading mode; otherwise Escape still closes.
                if (reading.collapse()) {
                    narrator.region(Component.translatable(
                            "gui.mcaconversations.responses.reading_ended"));
                    return true;
                }
                return false;
            }
            default -> {
            }
        }
        return switch (reading.region()) {
            case QUESTION -> questionKey(keyCode);
            case FOOTER -> footerKey(keyCode);
            case RESPONSES -> reading.expandedIndex() >= 0 ? responseTextKey(keyCode) : listKey(keyCode);
        };
    }

    private boolean questionKey(int keyCode) {
        int page = Math.max(1, card.layout().questionLines() - 1);
        switch (keyCode) {
            case GLFW.GLFW_KEY_UP -> reading.scrollQuestion(-1);
            case GLFW.GLFW_KEY_DOWN -> reading.scrollQuestion(1);
            case GLFW.GLFW_KEY_PAGE_UP -> reading.scrollQuestion(-page);
            case GLFW.GLFW_KEY_PAGE_DOWN -> reading.scrollQuestion(page);
            case GLFW.GLFW_KEY_HOME -> reading.questionBoundary(false);
            case GLFW.GLFW_KEY_END -> reading.questionBoundary(true);
            default -> {
                return false;
            }
        }
        return true;
    }

    private boolean responseTextKey(int keyCode) {
        int viewport = card.layout().responseViewport().height();
        switch (keyCode) {
            case GLFW.GLFW_KEY_UP -> reading.scrollResponses(-card.lineStep());
            case GLFW.GLFW_KEY_DOWN -> reading.scrollResponses(card.lineStep());
            case GLFW.GLFW_KEY_PAGE_UP -> reading.scrollResponses(-viewport);
            case GLFW.GLFW_KEY_PAGE_DOWN -> reading.scrollResponses(viewport);
            case GLFW.GLFW_KEY_HOME -> reading.scrollResponses(-reading.responseMaxScroll());
            case GLFW.GLFW_KEY_END -> reading.scrollResponses(reading.responseMaxScroll());
            default -> {
                return false;
            }
        }
        return true;
    }

    private boolean listKey(int keyCode) {
        switch (keyCode) {
            case GLFW.GLFW_KEY_UP -> moveFocus(-1);
            case GLFW.GLFW_KEY_DOWN -> moveFocus(1);
            case GLFW.GLFW_KEY_HOME -> focusBoundary(false);
            case GLFW.GLFW_KEY_END -> focusBoundary(true);
            case GLFW.GLFW_KEY_PAGE_UP -> changePage(-1);
            case GLFW.GLFW_KEY_PAGE_DOWN -> changePage(1);
            default -> {
                return false;
            }
        }
        return true;
    }

    private boolean footerKey(int keyCode) {
        switch (keyCode) {
            case GLFW.GLFW_KEY_LEFT, GLFW.GLFW_KEY_PAGE_UP, GLFW.GLFW_KEY_UP -> changePage(-1);
            case GLFW.GLFW_KEY_RIGHT, GLFW.GLFW_KEY_PAGE_DOWN, GLFW.GLFW_KEY_DOWN -> changePage(1);
            default -> {
                return false;
            }
        }
        return true;
    }

    /** The reading action: read the focused response in place, or stop reading it. */
    public boolean toggleReading() {
        if (card == null) {
            return false;
        }
        keyboardInput();
        if (reading.region() == DialogueReadingState.Region.QUESTION) {
            reading.region(DialogueReadingState.Region.RESPONSES);
            narrateRegion();
            return true;
        }
        if (reading.collapse()) {
            narrator.region(Component.translatable("gui.mcaconversations.responses.reading_ended"));
            return true;
        }
        int focused = ClientChoiceMessages.state().focusedIndex();
        if (!reading.expand(focused)) {
            return false;
        }
        revealFocusedRow();
        Component answer = answerAt(focused);
        narrator.region(Component.translatable("gui.mcaconversations.responses.reading_started",
                answer == null ? Component.empty() : answer));
        return true;
    }

    private void narrateRegion() {
        narrator.region(Component.translatable(switch (reading.region()) {
            case QUESTION -> "gui.mcaconversations.responses.region_question";
            case RESPONSES -> "gui.mcaconversations.responses.region_responses";
            case FOOTER -> "gui.mcaconversations.responses.region_footer";
        }));
    }

    /** Scrolls the focused row fully into the viewport, so keyboard selection is never off-screen. */
    private void revealFocusedRow() {
        if (card == null) {
            return;
        }
        PreparedChoiceRow row = card.row(ClientChoiceMessages.state().focusedIndex());
        DialogueChoiceLayout.Rect viewport = card.layout().responseViewport();
        if (row != null && viewport != null) {
            reading.revealRow(row.hitRect().y(), row.hitRect().height(),
                    viewport.y(), viewport.height());
        }
    }

    /** Test and preview seam: where the keyboard currently is. */
    public DialogueReadingState readingState() {
        return reading;
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
        reading.reset();
        arming.reset();
        utility.reset();
        utilityPlacement = null;
        historyButton = null;
        settingsButton = null;
        pointerDwellIndex = -1;
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
        revealFocusedRow();
        Component answer = answerAt(state.focusedIndex());
        if (answer != null) {
            narrator.focus(offer.revision(), state.focusedIndex(), controls.narration(
                    state.focusedIndex(), offer.answerIds().size(),
                    state.focusedIndex() - state.firstOnPage() + 1, answer));
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
                narrator.focus(offer.revision(), pointerDwellIndex, controls.narration(
                        pointerDwellIndex, offer.answerIds().size(),
                        pointerDwellIndex - state.firstOnPage() + 1, answer));
            }
            pointerDwellNarrated = true;
        }
    }

    /** The resolved text of one answer, for a caller that has to record what was actually sent. */
    public Component answerText(int absoluteIndex) {
        return answerAt(absoluteIndex);
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

        drawQuestion(graphics, font, motion, partialTick, alpha);

        DialogueChoiceLayout.Rect viewport = card.layout().responseViewport();
        PreparedChoiceRow elevated = null;
        // Exactly one document owns this viewport. Both skins have translucent backing, so
        // painting a utility over already-drawn answers leaves their text and badges visible.
        if (utility.isOpen()) {
            drawUtility(graphics, font, viewport, alpha, mouseX, mouseY);
        } else {
            utilityPlacement = null;
            // Rows keep their natural height; the viewport shows the scrolled portion.
            graphics.enableScissor(viewport.x(), viewport.y(),
                    viewport.x() + viewport.width(), viewport.y() + viewport.height());
            try {
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
            } finally {
                graphics.disableScissor();
            }
            if (card.responsesOverflow()) {
                int step = Math.max(1, card.lineStep());
                skin.scrollbar(graphics, viewport, reading.responseScroll() / step,
                        viewport.height() / step, card.layout().documentHeight() / step, alpha);
            }
        }
        drawFooter(graphics, font, alpha, mouseX, mouseY);

        if (!utility.isOpen() && elevated != null && elevated.textClipped()
                && elevated.hitRect().contains(mouseX, mouseY)) {
            graphics.renderTooltip(font, elevated.answer(), mouseX, mouseY);
        }
    }

    /**
     * The question in its own bounded, scissored reading region, offset by its own scroll.
     *
     * <p>The region is the fixed one the geometry reserved, so a two-line question and an eight-line
     * question put the answers in the same place; the eight-line one scrolls.
     */
    private void drawQuestion(GuiGraphics graphics, Font font, ConversationMotionSpec motion,
                              float partialTick, float alpha) {
        DialogueChoiceLayout.Rect region = card.layout().questionViewport();
        int visibleLines = Math.min(card.questionLines().size(), card.layout().questionLines());
        int first = Math.min(reading.questionScroll(),
                Math.max(0, card.questionLines().size() - visibleLines));
        int questionY = card.layout().questionY();
        int budget = revealBudget(motion, partialTick, visibleLines);
        graphics.enableScissor(region.x(), region.y(),
                region.x() + region.width(), region.y() + region.height());
        try {
            for (int i = first; i < Math.min(card.questionLines().size(), first + visibleLines); i++) {
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
        } finally {
            graphics.disableScissor();
        }
        if (card.questionOverflows()) {
            skin.scrollbar(graphics, region, first, visibleLines, card.questionLines().size(), alpha);
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
        // Every line of every answer is drawn. The row is as tall as its own text, the viewport
        // scissor decides how much of it is on screen, and nothing here shortens authored text.
        for (FormattedCharSequence line : row.lines()) {
            graphics.drawString(font, line, textX, textY,
                    ConversationPalette.withAlpha(ConversationPalette.TEXT, alpha),
                    CARD_TEXT_SHADOW);
            textY += card.lineStep();
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
            InventoryScreen.renderEntityInInventoryFollowsAngle(graphics,
                    frame.x() + frame.width() / 2, frame.y() + frame.height() - inset,
                    scale, 0.0F, 0.0F, speaker);
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
            historyButton = null;
            settingsButton = null;
            return;
        }
        DialogueChoiceLayout.Rect panel = card.layout().panel();
        int muted = ConversationPalette.withAlpha(ConversationPalette.TEXT_MUTED, alpha);
        int hintX = panel.x() + DialogueChoiceLayout.INNER_PADDING;
        hintX = drawUtilityButtons(graphics, font, panel, footerY, alpha, mouseX, mouseY, hintX);
        // The navigation half of the footer is measured and reserved first; the hint is then chosen
        // to fit what is left, so the two halves can never be drawn over each other.
        int hintLimit = panel.x() + panel.width() - DialogueChoiceLayout.INNER_PADDING;
        if (renderedPageCount > 1) {
            Component pageText = footerPage;
            int pageWidth = font.width(pageText);
            DialogueChoiceLayout.Rect firstControl = card.layout().previousPage() != null
                    ? card.layout().previousPage() : card.layout().nextPage();
            int pageX = firstControl == null
                    ? panel.x() + panel.width() - DialogueChoiceLayout.INNER_PADDING - pageWidth
                    : firstControl.x() - 4 - pageWidth;
            hintLimit = pageX - 6;
            graphics.drawString(font, pageText, pageX, footerY, muted, CARD_TEXT_SHADOW);
            drawPageButton(graphics, font, card.layout().previousPage(), "‹",
                    renderedPage > 0, alpha, mouseX, mouseY, skin);
            drawPageButton(graphics, font, card.layout().nextPage(), "›",
                    renderedPage + 1 < renderedPageCount, alpha, mouseX, mouseY, skin);
        }
        if (ClientChoiceController.showHints()) {
            int budget = hintLimit - hintX;
            Component hint = renderedLock >= 0
                    ? (font.width(footerSelecting) <= budget ? footerSelecting : null)
                    : controls.hint(budget, card.compact(), font::width);
            if (hint != null) {
                graphics.drawString(font, hint, hintX, footerY, muted, CARD_TEXT_SHADOW);
            }
        }
    }

    /**
     * The two footer buttons that open the utilities, and the X the hint may start at.
     *
     * <p>Labelled with the keys that do the same thing, because the pane and the drawer are the only
     * parts of the card a pointer and a keyboard reach by different routes, and a button whose label
     * is its shortcut needs no separate hint to explain it.
     */
    private int drawUtilityButtons(GuiGraphics graphics, Font font, DialogueChoiceLayout.Rect panel,
                                   int footerY, float alpha, int mouseX, int mouseY, int hintX) {
        int size = Math.max(10, Math.min(16, font.lineHeight + 3));
        int y = footerY - Math.max(0, (size - font.lineHeight) / 2);
        historyButton = new DialogueChoiceLayout.Rect(hintX, y, size, size);
        settingsButton = new DialogueChoiceLayout.Rect(hintX + size + 2, y, size, size);
        drawUtilityButton(graphics, font, historyButton, "H",
                utility.is(DialogueUtilityState.Utility.HISTORY), alpha, mouseX, mouseY);
        drawUtilityButton(graphics, font, settingsButton, "P",
                utility.is(DialogueUtilityState.Utility.SETTINGS), alpha, mouseX, mouseY);
        return settingsButton.x() + size + 5;
    }

    private void drawUtilityButton(GuiGraphics graphics, Font font, DialogueChoiceLayout.Rect rect,
                                   String glyph, boolean active, float alpha, int mouseX, int mouseY) {
        skin.control(graphics, rect, alpha, true, active || rect.contains(mouseX, mouseY));
        graphics.drawString(font, glyph,
                rect.x() + Math.max(0, (rect.width() - font.width(glyph)) / 2),
                DialogueChoiceLayout.centeredTextY(rect, font.lineHeight),
                ConversationPalette.withAlpha(
                        active ? ConversationPalette.TEXT : ConversationPalette.TEXT_MUTED, alpha),
                CARD_TEXT_SHADOW);
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

    // --- utilities: delivered history and the presentation pane ---------------------------------

    /**
     * Whether one of the two card utilities owns the keyboard and the pointer.
     *
     * <p>Asked by the screen adapter before it interprets anything as an answer, which is how
     * opening a utility is prevented from selecting a response rather than merely discouraged from
     * it: while this is true, no key and no click reaches the selection at all.
     */
    public boolean utilityOpen() {
        return utility.isOpen();
    }

    /** Test and preview seam: which utility is open, and where the keyboard came from. */
    public DialogueUtilityState utilityState() {
        return utility;
    }

    private boolean openUtility(DialogueUtilityState.Utility wanted) {
        if (wanted == DialogueUtilityState.Utility.HISTORY
                && !utility.is(DialogueUtilityState.Utility.HISTORY)
                && !ClientDialogueHistory.get().enabled()) {
            return false; // the drawer is switched off; there is nothing to open, not an empty list.
        }
        if (!utility.toggle(wanted, reading.region())) {
            return false;
        }
        DialogueUiSounds.focus();
        narrateUtility();
        return true;
    }

    /**
     * Every key while a utility is open. Nothing here can submit a response: the only outward action
     * is Back, which hands the keyboard to the region it was taken from.
     */
    public boolean utilityKey(int keyCode, boolean shift) {
        if (!utility.isOpen()) {
            return false;
        }
        keyboardInput();
        int rows = actionableRows();
        int maxScroll = utilityPlacement == null ? 0 : utilityPlacement.maxScroll();
        int step = card == null ? 9 : card.lineStep();
        switch (keyCode) {
            case GLFW.GLFW_KEY_ESCAPE, GLFW.GLFW_KEY_BACKSPACE, GLFW.GLFW_KEY_TAB -> {
                return closeUtility();
            }
            case GLFW.GLFW_KEY_H -> {
                return openUtility(DialogueUtilityState.Utility.HISTORY);
            }
            case GLFW.GLFW_KEY_P -> {
                return openUtility(DialogueUtilityState.Utility.SETTINGS);
            }
            case GLFW.GLFW_KEY_UP -> {
                if (rows > 0) {
                    utility.move(-1, rows);
                } else {
                    utility.scroll(-step, maxScroll);
                }
            }
            case GLFW.GLFW_KEY_DOWN -> {
                if (rows > 0) {
                    utility.move(1, rows);
                } else {
                    utility.scroll(step, maxScroll);
                }
            }
            case GLFW.GLFW_KEY_PAGE_UP -> utility.scroll(-step * 4, maxScroll);
            case GLFW.GLFW_KEY_PAGE_DOWN -> utility.scroll(step * 4, maxScroll);
            case GLFW.GLFW_KEY_HOME -> utility.scroll(-maxScroll, maxScroll);
            case GLFW.GLFW_KEY_END -> utility.scroll(maxScroll, maxScroll);
            case GLFW.GLFW_KEY_LEFT -> changeSetting(-1);
            case GLFW.GLFW_KEY_RIGHT -> changeSetting(1);
            case GLFW.GLFW_KEY_ENTER, GLFW.GLFW_KEY_KP_ENTER, GLFW.GLFW_KEY_SPACE -> {
                if (confirmPress(keyCode)) {
                    activateUtilityRow(utility.selected());
                }
            }
            default -> {
            }
        }
        // Everything else is swallowed rather than passed on: an unhandled key inside a utility must
        // not fall through to the card underneath it.
        return true;
    }

    private boolean closeUtility() {
        DialogueReadingState.Region back = utility.returnRegion();
        if (!utility.back()) {
            return false;
        }
        utilityPlacement = null;
        reading.region(back);
        narrator.region(Component.translatable("gui.mcaconversations.utility.closed"));
        return true;
    }

    /** How many rows of the open utility can be activated; the rest are read-only lines. */
    private int actionableRows() {
        int count = 0;
        for (DialogueUtilityView.Entry entry : utilityEntries()) {
            if (entry.actionable()) {
                count++;
            }
        }
        return count;
    }

    private boolean changeSetting(int direction) {
        if (!utility.is(DialogueUtilityState.Utility.SETTINGS)) {
            return false;
        }
        int selected = utility.selected();
        if (selected >= settings.rows().size()) {
            return false;
        }
        if (!settings.cycle(selected, direction)) {
            return false;
        }
        // A style or motion change alters the card that is drawing this pane, so the prepared model
        // has to go with it; the pane itself stays open and keeps its place.
        modelKey = null;
        utility.resetListingCleared();
        DialogueUiSounds.focus();
        narrator.region(settings.rows().get(selected).value());
        return true;
    }

    /**
     * Activating a row. Only the settings pane has anything to activate, and its one non-cycling row
     * says what it would change before it changes it.
     */
    private boolean activateUtilityRow(int index) {
        if (!utility.is(DialogueUtilityState.Utility.SETTINGS)) {
            return false;
        }
        int settingRows = settings.rows().size();
        if (index < settingRows) {
            return changeSetting(1);
        }
        java.util.List<Component> changes = settings.recommendedChanges();
        if (changes.isEmpty()) {
            narrator.region(Component.translatable("gui.mcaconversations.settings.reset_none"));
            return true;
        }
        if (!utility.resetListed()) {
            utility.listReset();
            narrator.region(Component.translatable("gui.mcaconversations.settings.reset_listed",
                    changes.size()));
            return true;
        }
        settings.applyRecommended();
        utility.resetListingCleared();
        modelKey = null;
        DialogueUiSounds.page();
        narrator.region(Component.translatable("gui.mcaconversations.settings.reset_applied"));
        return true;
    }

    private void narrateUtility() {
        narrator.region(Component.translatable(utility.is(DialogueUtilityState.Utility.HISTORY)
                ? "gui.mcaconversations.history.title"
                : "gui.mcaconversations.settings.title"));
    }

    /** The open utility's lines, actionable ones first so the selection index is the row index. */
    private java.util.List<DialogueUtilityView.Entry> utilityEntries() {
        java.util.List<DialogueUtilityView.Entry> entries = new java.util.ArrayList<>();
        if (utility.is(DialogueUtilityState.Utility.HISTORY)) {
            java.util.List<DeliveredLine> lines = ClientDialogueHistory.get().entries();
            if (lines.isEmpty()) {
                entries.add(DialogueUtilityView.Entry.of(
                        Component.translatable("gui.mcaconversations.history.empty")));
            }
            for (DeliveredLine line : lines) {
                Component status = line.statusLabel();
                Component text = line.kind() == DeliveredLine.Kind.RESPONSE
                        ? Component.translatable("gui.mcaconversations.history.you", line.text())
                        : Component.translatable("gui.mcaconversations.history.said",
                                line.speaker(), line.text());
                entries.add(new DialogueUtilityView.Entry(text, status, false));
            }
            return entries;
        }
        for (PresentationSettings.Row row : settings.rows()) {
            entries.add(DialogueUtilityView.Entry.action(row.label(), row.value()));
        }
        entries.add(DialogueUtilityView.Entry.action(
                Component.translatable("gui.mcaconversations.settings.reset"), null));
        if (utility.resetListed()) {
            for (Component change : settings.recommendedChanges()) {
                entries.add(DialogueUtilityView.Entry.of(change));
            }
        }
        Component townstead = settings.townsteadNote();
        if (townstead != null) {
            entries.add(DialogueUtilityView.Entry.of(townstead));
        }
        return entries;
    }

    private void drawUtility(GuiGraphics graphics, Font font,
                             DialogueChoiceLayout.Rect viewport, float alpha,
                             int mouseX, int mouseY) {
        utilityPlacement = DialogueUtilityView.draw(graphics, font, skin, viewport,
                Component.translatable(utility.is(DialogueUtilityState.Utility.HISTORY)
                        ? "gui.mcaconversations.history.title"
                        : "gui.mcaconversations.settings.title"),
                utilityEntries(), utility.selected(), utility.scroll(), mouseX, mouseY, alpha);
    }

    /**
     * A click while a utility is open, or on one of the two footer buttons that open them.
     *
     * <p>Returns null when the click was not ours, and a consumed target otherwise: a click inside an
     * open utility must never reach the answer list underneath, not even as a miss.
     */
    private DialogueHitTarget clickUtility(double mouseX, double mouseY) {
        if (utility.isOpen()) {
            if (utilityPlacement != null) {
                if (utilityPlacement.backAt(mouseX, mouseY)) {
                    closeUtility();
                    return new DialogueHitTarget.Responses();
                }
                int row = utilityPlacement.rowAt(mouseX, mouseY);
                if (row >= 0) {
                    utility.select(row, Math.max(1, actionableRows()));
                    activateUtilityRow(row);
                }
            }
            return new DialogueHitTarget.Responses();
        }
        if (historyButton != null && historyButton.contains(mouseX, mouseY)) {
            openUtility(DialogueUtilityState.Utility.HISTORY);
            return new DialogueHitTarget.Responses();
        }
        if (settingsButton != null && settingsButton.contains(mouseX, mouseY)) {
            openUtility(DialogueUtilityState.Utility.SETTINGS);
            return new DialogueHitTarget.Responses();
        }
        return null;
    }

    private void clearPrepared() {
        modelKey = null;
        model = null;
        skin = null;
        preparedPage = -1;
        preparedScroll = 0;
        preparedExpanded = -1;
        preparedRevision = -1L;
        card = null;
        footerSelecting = null;
        footerPage = null;
    }

    /**
     * Draws what is left after an offer was refused: the explanation, and the one action that is
     * safe to take from here.
     *
     * <p>The answers are gone — nothing on this shell can be selected or re-sent — but the sentence
     * saying why has to stay readable, because the failure players actually complain about is a card
     * that disappears under the cursor and tells them nothing. A failed action offers navigation
     * only: no retry, no replay, and no claim about what did or did not already happen.
     */
    private void drawLapse(GuiGraphics graphics, ClientChoiceState state, DialogueMenuStyle style) {
        ClientChoiceState.Lapse lapse = state.lapse().orElse(null);
        if (lapse == null) {
            lapseAction = null;
            return;
        }
        Font font = Minecraft.getInstance().font;
        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();
        int panelWidth = DialogueChoiceLayout.panelWidth(screenWidth);
        int textWidth = panelWidth - DialogueChoiceLayout.INNER_PADDING * 2;
        List<FormattedCharSequence> lines = font.split(
                ClientChoiceMessages.explanation(lapse.reason()), textWidth);
        int actionHeight = font.lineHeight + DialogueChoiceLayout.ROW_GAP * 3;
        int panelHeight = DialogueChoiceLayout.INNER_PADDING * 2
                + lines.size() * font.lineHeight + DialogueChoiceLayout.SMALL_MARGIN + actionHeight;
        int x = (screenWidth - panelWidth) / 2;
        int y = Math.max(DialogueChoiceLayout.SAFE_TOP,
                screenHeight - DialogueChoiceLayout.OUTER_MARGIN - panelHeight);
        DialogueChoiceLayout.Rect panel = new DialogueChoiceLayout.Rect(x, y, panelWidth, panelHeight);
        DialogueChoiceLayout.Rect action = new DialogueChoiceLayout.Rect(
                x + DialogueChoiceLayout.INNER_PADDING,
                y + panelHeight - DialogueChoiceLayout.INNER_PADDING - actionHeight,
                textWidth, actionHeight);
        DialogueSkin shellSkin = DialogueSkin.of(style);
        shellSkin.panel(graphics, panel, action, 1.0F);
        int textY = y + DialogueChoiceLayout.INNER_PADDING;
        for (FormattedCharSequence line : lines) {
            graphics.drawString(font, line, x + DialogueChoiceLayout.INNER_PADDING, textY,
                    ConversationPalette.TEXT);
            textY += font.lineHeight;
        }
        shellSkin.row(graphics, action, 1.0F, false, false);
        graphics.drawString(font, ClientChoiceMessages.action(lapse),
                action.x() + DialogueChoiceLayout.INNER_PADDING,
                DialogueChoiceLayout.centeredTextY(action, font.lineHeight),
                ConversationPalette.TEXT);
        lapseAction = action;
    }

    /**
     * The shell's single action requests a fresh server-validated menu or closes the host screen.
     */
    private boolean clickLapseAction(ClientChoiceState state, double mouseX, double mouseY) {
        ClientChoiceState.Lapse lapse = state.lapse().orElse(null);
        if (lapse == null || lapseAction == null || state.offer().isPresent()) {
            return false;
        }
        if (mouseX < lapseAction.x() || mouseX >= lapseAction.x() + lapseAction.width()
                || mouseY < lapseAction.y() || mouseY >= lapseAction.y() + lapseAction.height()) {
            return false;
        }
        activateLapse(state);
        return true;
    }

    public void lapseReturn(Runnable action) {
        lapseReturn = action;
    }

    /** All keys are owned by the shell; confirmation must never select a retired answer. */
    public void lapseKey(int keyCode) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            closeHostScreen();
        } else if ((keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER
                || keyCode == GLFW.GLFW_KEY_SPACE) && confirmPress(keyCode)) {
            activateLapse(ClientChoiceMessages.state());
        }
    }

    private void activateLapse(ClientChoiceState state) {
        ClientChoiceState.Lapse lapse = state.lapse().orElse(null);
        if (lapse == null) {
            return;
        }
        if (lapse.backToTopics()) {
            // Keep the shell visible until a fresh offer (or a new refusal) arrives.
            lapseReturn.run();
        } else {
            closeHostScreen();
        }
    }

    private static void closeHostScreen() {
        var screen = Minecraft.getInstance().screen;
        if (screen != null) {
            // MCA's onClose sends its interaction-close packet, releasing the villager on the server.
            screen.onClose();
        }
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
