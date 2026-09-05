package dev.otectus.mcaconversations.mixin.client;

import dev.otectus.mcaconversations.client.dialogue.ClientChoiceController;
import dev.otectus.mcaconversations.client.dialogue.ConversationPalette;
import dev.otectus.mcaconversations.client.dialogue.DialogueCardSkin;
import dev.otectus.mcaconversations.client.dialogue.DialogueChoiceLayout;
import dev.otectus.mcaconversations.client.townstead.NumberedChoicePanelBridge;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/** Number badges for Townstead's already-wrapped, scrollable visible entry model. */
@Pseudo
@Mixin(targets = "com.aetherianartificer.townstead.client.gui.dialogue.ChoicePanel", remap = false)
public abstract class TownsteadChoicePanelMixin implements NumberedChoicePanelBridge {

    @Shadow private List<?> displayEntries;
    @Shadow private boolean visible;
    @Shadow private int hoveredIndex;
    @Shadow private int selectedIndex;
    @Shadow private int scrollOffset;
    @Shadow @Final private List<Integer> entryHeights;
    @Shadow private int x;
    @Shadow private int y;
    @Shadow private int width;
    @Shadow private int height;

    @Unique private int mcaconversations$badgeFadeTicks;
    @Unique private int mcaconversations$entryCount = -1;

    @Inject(method = "setVisible", at = @At("TAIL"), require = 0, remap = false)
    private void mcaconversations$badgeVisibility(boolean show, CallbackInfo ci) {
        mcaconversations$badgeFadeTicks = show ? 0 : 5;
        mcaconversations$entryCount = displayEntries.size();
    }

    @Inject(method = "tick", at = @At("TAIL"), require = 0, remap = false)
    private void mcaconversations$tickBadgeFade(CallbackInfo ci) {
        if (visible && mcaconversations$badgeFadeTicks < 5) {
            mcaconversations$badgeFadeTicks++;
        }
    }

    @Inject(method = "render", at = @At("TAIL"), require = 0, remap = false)
    private void mcaconversations$renderNumbers(GuiGraphics graphics, Font font, int mouseX, int mouseY,
                                                CallbackInfo ci) {
        if (!ClientChoiceController.numberingEnabled() || !visible || displayEntries.isEmpty()) {
            return;
        }
        if (displayEntries.size() != mcaconversations$entryCount) {
            mcaconversations$entryCount = displayEntries.size();
            mcaconversations$badgeFadeTicks = 0;
        }
        float alpha = Math.max(0.0F, Math.min(1.0F, mcaconversations$badgeFadeTicks / 5.0F));
        graphics.enableScissor(x, y, x + width, y + height);
        try {
            int rowY = y + 8 - scrollOffset;
            int visibleNumber = 1;
            for (int i = 0; i < entryHeights.size() && visibleNumber <= 9; i++) {
                int rowHeight = entryHeights.get(i);
                if (rowY + rowHeight > y && rowY < y + height) {
                    int badgeY = Math.max(y + 1, rowY);
                    boolean selected = i == selectedIndex;
                    DialogueCardSkin.INSTANCE.badge(graphics, new DialogueChoiceLayout.Rect(x + 3, badgeY, 15,
                            Math.min(y + height, badgeY + 11) - badgeY), alpha, selected);
                    graphics.drawString(font, visibleNumber + ".", x + 5, badgeY + 1,
                            ConversationPalette.withAlpha(ConversationPalette.TEXT, alpha), false);
                    visibleNumber++;
                }
                rowY += rowHeight + 6;
            }
        } finally {
            graphics.disableScissor();
        }
    }

    @Override
    public boolean mcaconversations$selectVisibleDigit(int digit) {
        if (!visible || digit < 1 || digit > 9 || displayEntries.isEmpty()) {
            return false;
        }
        int rowY = y + 8 - scrollOffset;
        int visibleNumber = 1;
        for (int i = 0; i < entryHeights.size(); i++) {
            int rowHeight = entryHeights.get(i);
            if (rowY + rowHeight > y && rowY < y + height) {
                if (visibleNumber == digit) {
                    selectedIndex = i;
                    hoveredIndex = i;
                    return true;
                }
                visibleNumber++;
                if (visibleNumber > 9) {
                    return false;
                }
            }
            rowY += rowHeight + 6;
        }
        return false;
    }
}
