package xyz.luobochuanqi.mindustry.common.world.Block.Machine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.state.StateContainer;
import xyz.luobochuanqi.mindustry.Utils;

public class TurretBlock extends HorizontalBlock {
	public TurretBlock(Properties pProperties)
	{
		super(pProperties);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(FACING, Utils.MULTIBLOCKSLAVE);
	}
}
