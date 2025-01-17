package com.alcatrazescapee.notreepunching.mixin;

import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ReloadableServerResources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.alcatrazescapee.notreepunching.util.HarvestBlockHandler;

@Mixin(ReloadableServerResources.class)
public abstract class ReloadableServerResourcesMixin
{
    @Inject(method = "updateRegistryTags(Lnet/minecraft/core/RegistryAccess;)V", at = @At("RETURN"))
    private void afterLoadTagsOnServer(RegistryAccess registryAccess, CallbackInfo ci)
    {
        HarvestBlockHandler.inferUniqueToolTags();
    }
}
