package xyz.luobochuanqi.mindustry.common.world.BlockEntity.Machine.Mechanical_Drill;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import xyz.luobochuanqi.mindustry.common.Type.DrillBlock.DrillBlock;
import xyz.luobochuanqi.mindustry.common.util.Mod2x2Part;

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

    @Override
    public void onRemove(BlockState pState, World pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock()) && pState.getValue(ModPART) == Mod2x2Part.START) {
            pLevel.getBlockEntity(pPos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(cap -> {
                InventoryHelper.dropItemStack(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), cap.getStackInSlot(0));
            });
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
}
