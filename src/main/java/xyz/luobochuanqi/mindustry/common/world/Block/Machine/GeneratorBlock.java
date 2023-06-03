package xyz.luobochuanqi.mindustry.common.world.Block.Machine;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import xyz.luobochuanqi.mindustry.common.util.CustomEnergyStorage;
import xyz.luobochuanqi.mindustry.common.world.Block.Mod2x2Block;

public abstract class GeneratorBlock extends Mod2x2Block {
    public int energy;

    protected GeneratorBlock(AbstractBlock.Properties pProperties) {
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

    public int getEnergy(World pLevel, BlockPos pPos) {
        pLevel.getBlockEntity(pPos).getCapability(CapabilityEnergy.ENERGY, null).ifPresent(cap -> {
            energy = ((CustomEnergyStorage) cap).getEnergyStored();
        });
        return energy;
    }

    public boolean canBeInsertedItem() {
        return true;
    }

    public boolean is2x2Block() {
        return false;
    }
}
