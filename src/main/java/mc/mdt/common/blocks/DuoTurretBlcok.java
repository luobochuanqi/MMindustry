package mc.mdt.common.blocks;

import mc.mdt.common.blockentity.DuoTurretBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class DuoTurretBlcok extends BlockWithEntity {
    public DuoTurretBlcok(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DuoTurretBlockEntity(pos, state);
    }
}
