package xyz.luobochuanqi.mindustry.common.Type.DrillBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.Type.Mod2x2Block;

public class DrillBlock extends Mod2x2Block {
    public DrillBlock(AbstractBlock.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.IGNORE;
    }
    public BlockRenderType getRenderShape(BlockState pState) {
        return BlockRenderType.MODEL;
    }

    public boolean canBeInsertedItem() {
        return false;
    }

    @Override
    public void onRemove(BlockState pState, World pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            DrillBlockEntity drillBlockEntity = (DrillBlockEntity) pLevel.getBlockEntity(pPos);
            Utils.LOGGER.info("will drop");
            InventoryHelper.dropContents(pLevel, pPos, drillBlockEntity.getInventory());
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
}
