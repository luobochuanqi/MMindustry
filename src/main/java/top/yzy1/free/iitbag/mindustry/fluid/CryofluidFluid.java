
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

public abstract class CryofluidFluid extends ForgeFlowingFluid {
	public static final ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(() -> MindustryModFluidTypes.CRYOFLUID_TYPE.get(),
			() -> MindustryModFluids.CRYOFLUID.get(), () -> MindustryModFluids.FLOWING_CRYOFLUID.get()).explosionResistance(100f).tickRate(10)
			.bucket(() -> MindustryModItems.CRYOFLUID_BUCKET.get()).block(() -> (LiquidBlock) MindustryModBlocks.CRYOFLUID.get());

	private CryofluidFluid() {
		super(PROPERTIES);
	}

	public static class Source extends CryofluidFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends CryofluidFluid {
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
