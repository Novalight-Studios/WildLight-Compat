package com.alfakynz.wildlight.mixin;

import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LightTexture.class, priority = 500)
public abstract class CancelEndFlashLightTextureMixin {

    @Inject(
            method = "updateLightTexture",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/texture/DynamicTexture;upload()V"
            )
    )
    private void wildlight$preventEndFlashTint(float partialTicks, CallbackInfo ci) {}
}