package com.alfakynz.wildlight.mixin;

import com.github.smallinger.copperagebackport.client.endflash.EndFlashAccessor;
import com.github.smallinger.copperagebackport.client.endflash.EndFlashState;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(value = ClientLevel.class, priority = 2000)
public abstract class CancelEndFlashClientLevelMixin implements EndFlashAccessor {

    /**
     * @author Alfakynz
     * @reason Cancel the End Flash backported by Copper Age Backport
     */
    @Overwrite
    public EndFlashState copperagebackport$getEndFlashState() {
        return null;
    }
}