
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.MindustryMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.entity.decoration.PaintingVariant;

public class MindustryModPaintings {
	public static final DeferredRegister<PaintingVariant> REGISTRY = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, MindustryMod.MODID);
	public static final RegistryObject<PaintingVariant> LOGO_1 = REGISTRY.register("logo_1", () -> new PaintingVariant(192, 128));
}
