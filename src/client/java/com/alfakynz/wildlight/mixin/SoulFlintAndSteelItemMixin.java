package com.alfakynz.wildlight.mixin;

import einstein.subtle_effects.particle.SparkParticle;
import einstein.subtle_effects.util.SparkType;
import net.agente_511.soulcandles.block.custom.ModSoulCandleBlock;
import net.agente_511.soulcandles.block.custom.ModSoulCandleCakeBlock;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public class SoulFlintAndSteelItemMixin {

    @Unique
    private static boolean wildlight$replacingParticle = false;

    @Inject(
            method = "addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void wildlight$swapFlameForSoulCandle(ParticleOptions options, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, CallbackInfo ci) {
        if (wildlight$replacingParticle) {
            return;
        }
        boolean isFlame = options == ParticleTypes.FLAME;
        boolean isSubtleEffectsSpark = options.getType() == SparkType.SHORT_LIFE.getType().get();

        if (!isFlame && !isSubtleEffectsSpark) {
            return;
        }

        ClientLevel self = (ClientLevel) (Object) this;
        BlockPos pos = BlockPos.containing(x, y, z);

        if (self.getBlockState(pos).getBlock() instanceof ModSoulCandleBlock || self.getBlockState(pos).getBlock() instanceof ModSoulCandleCakeBlock) {
            wildlight$replacingParticle = true;
            try {
                if (isFlame) {
                    self.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, xSpeed, ySpeed, zSpeed);
                }

                if (isSubtleEffectsSpark) {
                    self.addParticle(SparkParticle.createSoul(SparkType.SHORT_LIFE, self.getRandom()), x, y, z, xSpeed, ySpeed, zSpeed);
                }
            } finally {
                wildlight$replacingParticle = false;
            }

            ci.cancel();
        }
    }
}