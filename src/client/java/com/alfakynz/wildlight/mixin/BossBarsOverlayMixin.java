package com.alfakynz.wildlight.mixin;

import com.alfakynz.wildlight.util.ModConstants;
import com.alfakynz.wildlight.util.ModUtils;
import net.minecraft.client.gui.components.BossHealthOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BossHealthOverlay.class)
public class BossBarsOverlayMixin {

    @ModifyConstant(
            method = "render(Lnet/minecraft/client/gui/GuiGraphics;)V",
            constant = @Constant(intValue = 10)
    )
    private int wildlight$modifySpacing10(int original) {
        return !ModUtils.areResourcePacksLoaded(ModConstants.PACK_ENHANCED_BOSS_BARS) ? original : 28;
    }

    @ModifyConstant(
            method = "render(Lnet/minecraft/client/gui/GuiGraphics;)V",
            constant = @Constant(intValue = 9)
    )
    private int wildlight$modifySpacing9(int original) {
        return !ModUtils.areResourcePacksLoaded(ModConstants.PACK_ENHANCED_BOSS_BARS) ? original : 9;
    }
}