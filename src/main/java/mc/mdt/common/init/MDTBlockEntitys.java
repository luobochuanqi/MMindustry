package mc.mdt.common.init;

import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer;
import mc.mdt.common.blockentity.WoodConveyorBeltBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;

/**
 * @author luobochuanqi
 */
public class MDTBlockEntitys implements BlockEntityRegistryContainer {
    public static final BlockEntityType<WoodConveyorBeltBlockEntity> WOOD_CONVEYOR_BELT_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(
                    WoodConveyorBeltBlockEntity::new,
                    MDTBlocks.WOOD_CONVEYOR_BELT_BLOCK)
            .build();
}
