
/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.fluid.types.TarFluidType;
import top.yzy1.free.iitbag.mindustry.fluid.types.SlagFluidType;
import top.yzy1.free.iitbag.mindustry.fluid.types.CryofluidFluidType;
import top.yzy1.free.iitbag.mindustry.MindustryMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fluids.FluidType;

public class MindustryModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MindustryMod.MODID);
	public static final RegistryObject<FluidType> SLAG_TYPE = REGISTRY.register("slag", () -> new SlagFluidType());
	public static final RegistryObject<FluidType> CRYOFLUID_TYPE = REGISTRY.register("cryofluid", () -> new CryofluidFluidType());
	public static final RegistryObject<FluidType> TAR_TYPE = REGISTRY.register("tar", () -> new TarFluidType());
}
