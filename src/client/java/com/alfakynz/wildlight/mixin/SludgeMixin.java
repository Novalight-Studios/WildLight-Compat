package com.alfakynz.wildlight.mixin;

import com.alfakynz.wildlight.init.WildLightParticles;
import com.kyanite.deeperdarker.content.entities.Sludge;
import einstein.subtle_effects.init.ModConfigs;
import einstein.subtle_effects.particle.option.FloatParticleOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Slime.class)
public abstract class SludgeMixin extends Mob {

    @Shadow
    public abstract int getSize();

    @Unique
    private boolean subtleEffects$wasInAir;

    protected SludgeMixin(EntityType<? extends Slime> type, Level level) {
        super(type, level);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void subtleEffects$trail(CallbackInfo ci) {

        if (!ModConfigs.ENTITIES.replaceSlimeSquishParticles && !((Object) this instanceof Sludge)) {
            return;
        }

        if (subtleEffects$wasInAir && this.onGround()) {

            int size = this.getSize();

            this.level().addParticle(
                    new FloatParticleOptions(
                            WildLightParticles.SLUDGE_TRAIL,
                            size * 0.5F
                    ),
                    this.getX(),
                    this.getBlockY() + (this.random.nextDouble() / 10),
                    this.getZ(),
                    0,
                    0,
                    0
            );
        }

        subtleEffects$wasInAir = !this.onGround();
    }
}