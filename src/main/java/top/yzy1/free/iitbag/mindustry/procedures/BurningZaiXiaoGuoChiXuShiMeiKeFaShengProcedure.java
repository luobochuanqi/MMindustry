package top.yzy1.free.iitbag.mindustry.procedures;

import top.yzy1.free.iitbag.mindustry.init.MindustryModMobEffects;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.ParticleTypes;

public class BurningZaiXiaoGuoChiXuShiMeiKeFaShengProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (FireOppositesProcedure.execute(entity)) {
			if (Math.random() <= 0.3) {
				world.addParticle(ParticleTypes.FLAME, (x + Mth.nextDouble(RandomSource.create(), -1, 1)),
						(y + Mth.nextDouble(RandomSource.create(), -1, 1)), (z + Mth.nextDouble(RandomSource.create(), -1, 1)), 0, 0, 0);
			}
			entity.hurt(DamageSource.ON_FIRE, 1);
			entity.setSecondsOnFire(1);
			if (entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MindustryModMobEffects.TARRED.get()) : false) {
				entity.hurt(DamageSource.ON_FIRE, (float) 0.5);
			}
		}
	}
}
