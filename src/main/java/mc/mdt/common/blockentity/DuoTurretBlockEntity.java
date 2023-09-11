package mc.mdt.common.blockentity;

import mc.mdt.MMindustry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class DuoTurretBlockEntity extends BlockEntity {
    public DuoTurretBlockEntity(BlockPos pos, BlockState state) {
        super(MMindustry.DUO_TURRET_BLOCK_ENTITY, pos, state);
    }
}
