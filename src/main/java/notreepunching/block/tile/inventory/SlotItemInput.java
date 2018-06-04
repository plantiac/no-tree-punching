package notreepunching.block.tile.inventory;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotItemInput extends SlotItemHandler {

    public SlotItemInput(@Nonnull IItemHandler inventory, int idx, int x, int y){
        super(inventory, idx,x,y);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return true;
    }

    @Override
    public void onSlotChanged() {

    }
}
