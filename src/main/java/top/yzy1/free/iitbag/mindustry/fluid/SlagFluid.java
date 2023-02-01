
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
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ParticleOptions;

public abstract class SlagFluid extends ForgeFlowingFluid {
	public static final ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(() -> MindustryModFluidTypes.SLAG_TYPE.get(),
			() -> MindustryModFluids.SLAG.get(), () -> MindustryModFluids.FLOWING_SLAG.get()).explosionResistance(100f).tickRate(30)
			.bucket(() -> MindustryModItems.SLAG_BUCKET.get()).block(() -> (LiquidBlock) MindustryModBlocks.SLAG.get());

	private SlagFluid() {
		super(PROPERTIES);
	}

	@Override
	public ParticleOptions getDripParticle() {
		return ParticleTypes.LAVA;
	}

	public static class Source extends SlagFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends SlagFluid {
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
