package notreepunching.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import notreepunching.NoTreePunching;

public class ModBlocks {

    public static BlockRock looseRock;

    public static BlockFirepit firepit;

    public static void init(){
        looseRock = new BlockRock("loose_rock");

        firepit = new BlockFirepit(Material.WOOD,"firepit");
    }

    public static void registerBlocks(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(
                looseRock,
                firepit
        );

        GameRegistry.registerTileEntity(firepit.getTileEntityClass(), "tile_entity_firepit");
    }

    public static void registerItemBlocks(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(
                new ItemBlock(looseRock).setRegistryName(looseRock.name),
                new ItemBlock(firepit).setRegistryName(firepit.name)
        );
    }

    public static void registerItemBlockModels(){
        NoTreePunching.proxy.registerItemModel(Item.getItemFromBlock(looseRock),0,looseRock.name);
        NoTreePunching.proxy.registerItemModel(Item.getItemFromBlock(firepit),0,firepit.name);
    }
}
