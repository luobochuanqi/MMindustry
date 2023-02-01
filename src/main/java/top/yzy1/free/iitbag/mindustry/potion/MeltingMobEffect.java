
package top.yzy1.free.iitbag.mindustry.potion;

import top.yzy1.free.iitbag.mindustry.procedures.MeltingZaiXiaoGuoChiXuShiMeiKeFaShengProcedure;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class MeltingMobEffect extends MobEffect {
	public MeltingMobEffect() {
		super(MobEffectCategory.HARMFUL, -39424);
	}

	@Override
	public String getDescriptionId() {
		return "effect.mindustry.melting";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		MeltingZaiXiaoGuoChiXuShiMeiKeFaShengProcedure.execute(entity.level, entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
