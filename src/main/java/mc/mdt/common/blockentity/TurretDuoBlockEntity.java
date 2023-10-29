package mc.mdt.common.blockentity;

import mc.mdt.common.init.MDTBlockEntitys;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

// Made with real block
public class TurretDuoBlockEntity extends BlockEntity {
    public TurretDuoBlockEntity(BlockPos pos, BlockState state) {
        super(MDTBlockEntitys.TURRET_DUO_BLOCK_ENTITY, pos, state);
    }


}
