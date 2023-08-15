package mc.mdt.common.blocks;

import mc.mdt.MMindustry;
import mc.mdt.common.blockentity.WoodConveyorBeltBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * @author BillBodkin
 */
public class WoodConveyorBeltBlock extends ConveyorBeltBlock {

    public WoodConveyorBeltBlock(Settings settings) {
        super(settings, false);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WoodConveyorBeltBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ?
                checkType(type, MMindustry.WOOD_CONVEYOR_BELT_BLOCK_ENTITY, WoodConveyorBeltBlockEntity::clientTick)
                : checkType(type, MMindustry.WOOD_CONVEYOR_BELT_BLOCK_ENTITY, WoodConveyorBeltBlockEntity::serverTick);
    }

    @Override
    public void moveEntityOn(Vec3d vector, LivingEntity livingEntity){
        vector = vector.multiply(MMindustry.WOOD_ENTITY_MOVE_SPEED);
        moveEntityOn2(vector, livingEntity);
    }

//    @Override
//    public Identifier getNextCycleBeltType(){
//        return MMindustry.WOOD_SLOPED_CONVEYOR_BELT_UP_IDENTIFIER;
//    }
}
