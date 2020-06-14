/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.common.tile;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

import com.alcatrazescapee.core.common.tile.DeviceTileEntity;
import com.alcatrazescapee.notreepunching.Config;
import com.alcatrazescapee.notreepunching.common.container.LargeVesselContainer;

import static com.alcatrazescapee.notreepunching.NoTreePunching.MOD_ID;

public class LargeVesselTileEntity extends DeviceTileEntity
{
    private static final ITextComponent NAME = new TranslationTextComponent(MOD_ID + ".tile_entity.large_vessel");

    public LargeVesselTileEntity()
    {
        super(ModTileEntities.LARGE_VESSEL.get(), 9, NAME);
    }

    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player)
    {
        return new LargeVesselContainer(this, playerInventory, windowId);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack)
    {
        return !Config.SERVER.largeCeramicVesselBlacklist.get().test(stack);
    }
}
