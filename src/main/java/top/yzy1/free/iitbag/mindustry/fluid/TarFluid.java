
package top.yzy1.free.iitbag.mindustry.fluid;

import top.yzy1.free.iitbag.mindustry.init.MindustryModItems;
import top.yzy1.free.iitbag.mindustry.init.MindustryModFluids;
import top.yzy1.free.iitbag.mindustry.init.MindustryModFluidTypes;
import top.yzy1.free.iitbag.mindustry.init.MindustryModBlocks;

import net.minecraftforge.fluids.ForgeFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

public abstract class TarFluid extends ForgeFlowingFluid {
	public static final ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(() -> MindustryModFluidTypes.TAR_TYPE.get(),
			() -> MindustryModFluids.TAR.get(), () -> MindustryModFluids.FLOWING_TAR.get()).explosionResistance(100f).tickRate(30)
			.bucket(() -> MindustryModItems.TAR_BUCKET.get()).block(() -> (LiquidBlock) MindustryModBlocks.TAR.get());

	private TarFluid() {
		super(PROPERTIES);
	}

	public static class Source extends TarFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends TarFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}
}
