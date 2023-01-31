package top.yzy1.free.iitbag.mindustry.procedures;

import top.yzy1.free.iitbag.mindustry.init.MindustryModMobEffects;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.particles.ParticleTypes;

public class MeltingZaiXiaoGuoChiXuShiMeiKeFaShengProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (FireOppositesProcedure.execute(entity)) {
			if (Math.random() <= 0.3) {
				world.addParticle(ParticleTypes.LAVA, x, y, z, 0, 0, 0);
			}
			entity.hurt(DamageSource.LAVA, 2);
			entity.setSecondsOnFire((int) 0.05);
			if (entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MindustryModMobEffects.TARRED.get()) : false) {
				entity.hurt(DamageSource.LAVA, 1);
			}
		}
	}
}
