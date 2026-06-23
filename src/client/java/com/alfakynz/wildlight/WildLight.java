package com.alfakynz.wildlight;

import com.alfakynz.wildlight.init.WildLightParticles;
import com.alfakynz.wildlight.util.ModUtils;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WildLight implements ModInitializer {

    public static final String MOD_ID = "wildlight";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        if (ModUtils.areModsLoaded("subtle_effects", "deeperdarker")) {
            WildLightParticles.init();
        }

        LOGGER.info("WildLight initialized!");
    }
}