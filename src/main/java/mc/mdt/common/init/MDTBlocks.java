package mc.mdt.common.init;

import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import mc.mdt.common.blocks.WoodConveyorBeltBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

/**
 * @author luobochuanqi
 */
public class MDTBlocks implements BlockRegistryContainer {
    public static WoodConveyorBeltBlock WOOD_CONVEYOR_BELT_BLOCK =
            new WoodConveyorBeltBlock(FabricBlockSettings.create().strength(0.8f));

    @Override
    public BlockItem createBlockItem(Block block, String identifier) {
        return new BlockItem(block, new Item.Settings());
    }
}
