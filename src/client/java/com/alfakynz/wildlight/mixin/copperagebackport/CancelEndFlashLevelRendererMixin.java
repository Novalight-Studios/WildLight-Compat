package com.alfakynz.wildlight.mixin.copperagebackport;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LevelRenderer.class, priority = 2000)
public abstract class CancelEndFlashLevelRendererMixin {

    @Inject(method = "renderEndSky", at = @At("RETURN"))
    private void wildlight$suppressEndFlashRender(PoseStack poseStack, CallbackInfo ci) {}
}