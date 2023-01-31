
package top.yzy1.free.iitbag.mindustry.potion;

import top.yzy1.free.iitbag.mindustry.procedures.BurningZaiXiaoGuoChiXuShiMeiKeFaShengProcedure;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class BurningMobEffect extends MobEffect {
	public BurningMobEffect() {
		super(MobEffectCategory.HARMFUL, -13261);
	}

	@Override
	public String getDescriptionId() {
		return "effect.mindustry.burning";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		BurningZaiXiaoGuoChiXuShiMeiKeFaShengProcedure.execute(entity.level, entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
