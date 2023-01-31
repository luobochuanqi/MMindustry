
package top.yzy1.free.iitbag.mindustry.block;

import top.yzy1.free.iitbag.mindustry.procedures.SlagDangShengWuWanJiaPengZhuangFangKuaiShiProcedure;
import top.yzy1.free.iitbag.mindustry.init.MindustryModFluids;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class SlagBlock extends LiquidBlock {
	public SlagBlock() {
		super(() -> MindustryModFluids.SLAG.get(), BlockBehaviour.Properties.of(Material.LAVA).strength(100f).hasPostProcess((bs, br, bp) -> true)
				.emissiveRendering((bs, br, bp) -> true).lightLevel(s -> 15).noCollission().noLootTable());
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		SlagDangShengWuWanJiaPengZhuangFangKuaiShiProcedure.execute(entity);
	}
}
