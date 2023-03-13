package xyz.luobochuanqi.mindustry.common.Type.DrillBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
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
}
