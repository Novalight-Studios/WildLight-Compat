package com.alfakynz.wildlight;

import com.alfakynz.wildlight.init.WildLightParticles;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WildLight implements ModInitializer {

    public static final String MOD_ID = "wildlight";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("subtle_effects") && FabricLoader.getInstance().isModLoaded("deeperdarker")) {
            WildLightParticles.init();
        }

        LOGGER.info("WildLight initialized!");
    }
}