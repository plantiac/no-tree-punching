/*
 *
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 *
 */

package notreepunching.block.tile.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemStackInventoryWrapper extends ItemStackHandler implements ICapabilityProvider {

    protected ItemStack container;

    public ItemStackInventoryWrapper(ItemStack stack, NBTTagCompound compound, int size){
        super(size);
        this.container = stack;

        if (stack.hasTagCompound()) {
            readNBT(stack.getTagCompound());
        } else {
            readNBT(compound);
        }
    }

    private void readNBT(NBTTagCompound compound){
        if(compound != null) {
            this.deserializeNBT(compound.getCompoundTag("inventory"));
        }
    }

    public void saveNBT(ItemStack stack) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setTag("inventory", this.serializeNBT());
        stack.setTagCompound(compound);
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing enumFacing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this);
        }
        return null;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing enumFacing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }
}
