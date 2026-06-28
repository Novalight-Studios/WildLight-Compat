package com.alfakynz.wildlight.mixin.fbp;

import hantonik.fbp.particle.FBPTerrainParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FBPTerrainParticle.class)
public abstract class FBPTerrainParticleMixin extends Particle {

    @Unique
    protected TextureAtlasSprite sprite;

    protected FBPTerrainParticleMixin(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
    }

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void fixFastBetterGrassParticleColor(
            ClientLevel level, double x, double y, double z,
            double xd, double yd, double zd,
            float scale, float rCol, float gCol, float bCol,
            BlockPos pos, BlockState state,
            @Nullable Direction side, @Nullable TextureAtlasSprite sprite,
            CallbackInfo ci
    ) {
        if (!state.is(Blocks.GRASS_BLOCK) || side == null
                || side == Direction.UP || side == Direction.DOWN)
            return;

        var quads = Minecraft.getInstance()
                .getBlockRenderer()
                .getBlockModelShaper()
                .getBlockModel(state)
                .getQuads(state, side, RandomSource.create());

        if (quads.isEmpty()) return;

        var resolvedSprite = quads.getFirst().getSprite();

        if (resolvedSprite == null || !resolvedSprite.contents().name().getPath().contains("grass_block_top"))
            return;

        this.sprite = resolvedSprite;

        int color = Minecraft.getInstance()
                .getBlockColors().getColor(state, level, pos, 0);
        this.rCol = (color >> 16 & 255) / 255.0F;
        this.gCol = (color >> 8 & 255) / 255.0F;
        this.bCol = (color & 255) / 255.0F;
    }
}