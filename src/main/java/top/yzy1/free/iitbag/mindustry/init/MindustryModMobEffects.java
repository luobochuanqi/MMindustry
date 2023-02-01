
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.potion.WetMobEffect;
import top.yzy1.free.iitbag.mindustry.potion.TarredMobEffect;
import top.yzy1.free.iitbag.mindustry.potion.MeltingMobEffect;
import top.yzy1.free.iitbag.mindustry.potion.FreezingMobEffect;
import top.yzy1.free.iitbag.mindustry.potion.BurningMobEffect;
import top.yzy1.free.iitbag.mindustry.MindustryMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

public class MindustryModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MindustryMod.MODID);
	public static final RegistryObject<MobEffect> WET = REGISTRY.register("wet", () -> new WetMobEffect());
	public static final RegistryObject<MobEffect> BURNING = REGISTRY.register("burning", () -> new BurningMobEffect());
	public static final RegistryObject<MobEffect> MELTING = REGISTRY.register("melting", () -> new MeltingMobEffect());
	public static final RegistryObject<MobEffect> FREEZING = REGISTRY.register("freezing", () -> new FreezingMobEffect());
	public static final RegistryObject<MobEffect> TARRED = REGISTRY.register("tarred", () -> new TarredMobEffect());
}
