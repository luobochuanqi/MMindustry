
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.MindustryMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

public class MindustryModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MindustryMod.MODID);
	public static final RegistryObject<SoundEvent> DRILL = REGISTRY.register("drill",
			() -> new SoundEvent(new ResourceLocation("mindustry", "drill")));
	public static final RegistryObject<SoundEvent> SHOOT = REGISTRY.register("shoot",
			() -> new SoundEvent(new ResourceLocation("mindustry", "shoot")));
}
