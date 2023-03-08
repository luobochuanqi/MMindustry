package xyz.luobochuanqi.mindustry.common.world.BlockEntity.Machine.Mechanical_Drill;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.luobochuanqi.mindustry.common.Type.DrillBlock.DrillBlock;

import javax.annotation.Nullable;

public class MechanicalDrillBlock extends DrillBlock {
    public MechanicalDrillBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new MechanicalDrillBlockEntity();
    }

    @Override
    public ActionResultType use(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockRayTraceResult pHit) {
        if (pLevel.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity tileentity = pLevel.getBlockEntity(pPos);
            if (tileentity instanceof INamedContainerProvider) {
                NetworkHooks.openGui((ServerPlayerEntity) pPlayer, (INamedContainerProvider) tileentity, tileentity.getBlockPos());
            }
            return ActionResultType.CONSUME;
        }
    }
}
