
package top.yzy1.free.iitbag.mindustry.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class TarredMobEffect extends MobEffect {
	public TarredMobEffect() {
		super(MobEffectCategory.NEUTRAL, -13421773);
	}

	@Override
	public String getDescriptionId() {
		return "effect.mindustry.tarred";
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
