package com.alfakynz.wildlight.util;

import net.fabricmc.loader.api.FabricLoader;

public final class ModUtils {

    private ModUtils() {}

    public static boolean areModsLoaded(String... modIds) {
        FabricLoader loader = FabricLoader.getInstance();

        for (String modId : modIds) {
            if (!loader.isModLoaded(modId)) {
                return false;
            }
        }

        return true;
    }
}