
package top.yzy1.free.iitbag.mindustry.potion;

import top.yzy1.free.iitbag.mindustry.procedures.WetZaiXiaoGuoChiXuShiMeiKeFaShengProcedure;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class WetMobEffect extends MobEffect {
	public WetMobEffect() {
		super(MobEffectCategory.NEUTRAL, -16750900);
	}

	@Override
	public String getDescriptionId() {
		return "effect.mindustry.wet";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		WetZaiXiaoGuoChiXuShiMeiKeFaShengProcedure.execute(entity.level, entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
