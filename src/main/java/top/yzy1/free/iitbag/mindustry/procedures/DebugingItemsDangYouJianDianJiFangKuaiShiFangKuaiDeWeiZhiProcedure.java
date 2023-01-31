package top.yzy1.free.iitbag.mindustry.procedures;

import top.yzy1.free.iitbag.mindustry.init.MindustryModParticleTypes;
import top.yzy1.free.iitbag.mindustry.MindustryMod;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.particles.SimpleParticleType;

public class DebugingItemsDangYouJianDianJiFangKuaiShiFangKuaiDeWeiZhiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double i = 0;
		i = 0;
		while (i <= 50) {
			world.addParticle((SimpleParticleType) (MindustryModParticleTypes.ENERGY_LINE_1.get()), x, (y + i), z, 0, 0, 0);
			i = i + 0.01;
			MindustryMod.LOGGER.info(i);
		}
	}
}
