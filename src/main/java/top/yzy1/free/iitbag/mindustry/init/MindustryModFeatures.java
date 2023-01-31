
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.world.features.ores.LeadOreFeature;
import top.yzy1.free.iitbag.mindustry.MindustryMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.feature.Feature;

@Mod.EventBusSubscriber
public class MindustryModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, MindustryMod.MODID);
	public static final RegistryObject<Feature<?>> LEAD_ORE = REGISTRY.register("lead_ore", LeadOreFeature::feature);
}
