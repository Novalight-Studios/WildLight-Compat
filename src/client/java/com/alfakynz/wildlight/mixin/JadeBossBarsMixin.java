package com.alfakynz.wildlight.mixin;

import com.alfakynz.wildlight.mixin.accessor.BossBarsOverlayAccessor;
import com.alfakynz.wildlight.util.ModUtils;
import com.alfakynz.wildlight.util.ModConstants;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import snownee.jade.api.ui.TooltipRect;
import snownee.jade.impl.ui.BoxElement;

@Mixin(BoxElement.class)
public class JadeBossBarsMixin {

    @Inject(
            at = @At("TAIL"),
            method = "updateExpectedRect(Lsnownee/jade/api/ui/TooltipRect;)V"
    )
    private void wildlight$shiftWhenBossBar(TooltipRect rect, CallbackInfo ci) {
        BossBarsOverlayAccessor overlay =
                (BossBarsOverlayAccessor) Minecraft.getInstance().gui.getBossOverlay();

        int bossCount = overlay.wildlight$getEvents().size();
        if (bossCount == 0) return;

        boolean isEnhancedBossBars = ModUtils.areResourcePacksLoaded(ModConstants.PACK_ENHANCED_BOSS_BARS);
        int barSpacing = isEnhancedBossBars ? ModConstants.BAR_SPACING_ENHANCED : ModConstants.BAR_SPACING_DEFAULT;

        int totalHeight = ModConstants.BAR_START_Y + bossCount * barSpacing;

        rect.expectedRect.setY(
                Math.max(rect.expectedRect.getY(), totalHeight + ModConstants.EXTRA_PADDING)
        );
    }
}