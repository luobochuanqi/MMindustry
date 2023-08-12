package mc.mdt.common.blockentity;

import mc.mdt.MMindustry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

/**
 * @author BillBodkin
 */
public class WoodConveyorBeltBlockEntity extends ConveyorBeltBlockEntity {
    public WoodConveyorBeltBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, MMindustry.WOOD_CONVEYOR_BELT_BLOCK_ENTITY);
        this.transferCooldown = MMindustry.WOOD_TRANSFER_COOLDOWN;
        this.moveToCenterSpeed = MMindustry.WOOD_MOVE_TO_CENTER_SPEED;
    }

//    @Override
//    public boolean shouldCloseCurrentScreen() {
//        return super.shouldCloseCurrentScreen();
//    }
}
