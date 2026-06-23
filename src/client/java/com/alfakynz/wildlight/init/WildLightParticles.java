package com.alfakynz.wildlight.init;

import com.mojang.serialization.MapCodec;
import einstein.subtle_effects.particle.option.FloatParticleOptions;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class WildLightParticles {

    public static final ParticleType<FloatParticleOptions> SLUDGE_TRAIL =
            Registry.register(
                    net.minecraft.core.registries.BuiltInRegistries.PARTICLE_TYPE,
                    ResourceLocation.fromNamespaceAndPath("subtle_effects", "sludge_trail"),
                    new ParticleType<FloatParticleOptions>(false) {

                        @Override
                        public @NotNull MapCodec<FloatParticleOptions> codec() {
                            return FloatParticleOptions.codec(this);
                        }

                        @Override
                        public @NotNull StreamCodec<? super RegistryFriendlyByteBuf, FloatParticleOptions> streamCodec() {
                            return FloatParticleOptions.streamCodec(this);
                        }
                    }
            );

    public static void init() {}
}