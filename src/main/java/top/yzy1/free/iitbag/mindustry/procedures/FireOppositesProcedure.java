package top.yzy1.free.iitbag.mindustry.procedures;

import top.yzy1.free.iitbag.mindustry.init.MindustryModMobEffects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class FireOppositesProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MindustryModMobEffects.FREEZING.get()) : false)
				|| (entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MindustryModMobEffects.WET.get()) : false)) {
			return false;
		}
		return true;
	}
}
