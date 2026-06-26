package com.alfakynz.wildlight.mixin.subtle_effects;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import einstein.subtle_effects.init.ModConfigs;
import einstein.subtle_effects.init.ModParticles;
import net.minecraft.core.particles.SimpleParticleType;
import net.agente_511.soulcandles.block.custom.ModSoulCandleBlock;
import net.agente_511.soulcandles.block.custom.ModSoulCandleCakeBlock;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({ModSoulCandleBlock.class, ModSoulCandleCakeBlock.class})
public abstract class SoulCandlesMixin {

    @WrapOperation(method = "spawnCandleParticles", at = @At(value = "FIELD", target = "Lnet/minecraft/core/particles/ParticleTypes;SMOKE:Lnet/minecraft/core/particles/SimpleParticleType;", opcode = Opcodes.GETSTATIC))
    private static SimpleParticleType replaceSmoke(Operation<SimpleParticleType> original) {
        if (ModConfigs.BLOCKS.updatedSmoke.candleSmoke) {
            return ModParticles.SMOKE.get();
        }
        return original.call();
    }
}