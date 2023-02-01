
package top.yzy1.free.iitbag.mindustry.block;

import top.yzy1.free.iitbag.mindustry.procedures.TarDangShengWuWanJiaPengZhuangFangKuaiShiProcedure;
import top.yzy1.free.iitbag.mindustry.init.MindustryModFluids;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class TarBlock extends LiquidBlock {
	public TarBlock() {
		super(() -> MindustryModFluids.TAR.get(), BlockBehaviour.Properties.of(Material.WATER).strength(100f).noCollission().noLootTable());
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 1;
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		TarDangShengWuWanJiaPengZhuangFangKuaiShiProcedure.execute(entity);
	}
}
