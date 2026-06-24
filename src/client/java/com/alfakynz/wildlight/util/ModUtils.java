package com.alfakynz.wildlight.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;

import java.util.Collection;

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

    public static boolean areResourcePacksLoaded(String... packIds) {
        Minecraft client = Minecraft.getInstance();
        if (client == null) return false;

        PackRepository repo = client.getResourcePackRepository();
        Collection<Pack> selectedPacks = repo.getSelectedPacks();

        for (String packId : packIds) {
            boolean found = selectedPacks.stream()
                    .anyMatch(pack -> pack.getId().equals(packId));
            if (!found) return false;
        }

        return true;
    }
}