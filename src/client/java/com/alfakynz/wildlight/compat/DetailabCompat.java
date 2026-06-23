package com.alfakynz.wildlight.compat;

import com.alfakynz.wildlight.util.ModUtils;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.rhm.detailab_compat.DetailArmorBarCompatCommon;
import org.rhm.detailab_compat.DetailArmorBarCompatCommon.CompatInfo;

import java.util.Map;

public class DetailabCompat implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if (ModUtils.areModsLoaded("detailab_compat")) {
            Map<ResourceLocation, CompatInfo> map = DetailArmorBarCompatCommon.MOD_COMPATS;

            if (ModUtils.areModsLoaded("immersive_armors")) {
                String mod = "immersive_armors";
                addSet(map, "bone", mod);
                addSet(map, "divine", mod);
                addSet(map, "heavy", mod);
                addSet(map, "prismarine", mod);
                addSet(map, "robe", mod);
                addSet(map, "slime", mod);
                addSet(map, "steampunk", mod);
                addSet(map, "warrior", mod);
                addSet(map, "wither", mod);
                addSet(map, "wooden", mod);
            }

            if (ModUtils.areModsLoaded("copperagebackport")) {
                String mod = "minecraft"; // Registered at Minecraft in the mod to allow migrating to recent version
                addSet(map, "copper", mod);
            }
        }
    }

    private void addSet(Map<ResourceLocation, CompatInfo> map, String set, String mod) {
        for (String part : DetailArmorBarCompatCommon.CompatBuilder.EQUIPMENT_TYPES) {
            ResourceLocation tex = ResourceLocation.fromNamespaceAndPath(
                    "detailab_compat",
                    "textures/gui/sprites/hud/" + mod + "/" + set + ".png"
            );
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(
                    mod,
                    set + "_" + part
            );

            map.put(id, new CompatInfo.Builder(tex).build());
        }
    }
}