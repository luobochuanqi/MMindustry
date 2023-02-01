package top.yzy1.free.iitbag.mindustry.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.ParticleTypes;

public class WetZaiXiaoGuoChiXuShiMeiKeFaShengProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (Math.random() <= 0.3) {
			world.addParticle(ParticleTypes.FALLING_WATER, (x + Mth.nextDouble(RandomSource.create(), -1, 1)),
					(y + Mth.nextDouble(RandomSource.create(), -1, 1)), (z + Mth.nextDouble(RandomSource.create(), -1, 1)), 0, 0, 0);
		}
		entity.clearFire();
	}
}
