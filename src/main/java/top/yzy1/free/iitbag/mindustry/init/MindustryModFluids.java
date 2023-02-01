
/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.fluid.TarFluid;
import top.yzy1.free.iitbag.mindustry.fluid.SlagFluid;
import top.yzy1.free.iitbag.mindustry.fluid.CryofluidFluid;
import top.yzy1.free.iitbag.mindustry.MindustryMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

public class MindustryModFluids {
	public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(ForgeRegistries.FLUIDS, MindustryMod.MODID);
	public static final RegistryObject<FlowingFluid> SLAG = REGISTRY.register("slag", () -> new SlagFluid.Source());
	public static final RegistryObject<FlowingFluid> FLOWING_SLAG = REGISTRY.register("flowing_slag", () -> new SlagFluid.Flowing());
	public static final RegistryObject<FlowingFluid> CRYOFLUID = REGISTRY.register("cryofluid", () -> new CryofluidFluid.Source());
	public static final RegistryObject<FlowingFluid> FLOWING_CRYOFLUID = REGISTRY.register("flowing_cryofluid", () -> new CryofluidFluid.Flowing());
	public static final RegistryObject<FlowingFluid> TAR = REGISTRY.register("tar", () -> new TarFluid.Source());
	public static final RegistryObject<FlowingFluid> FLOWING_TAR = REGISTRY.register("flowing_tar", () -> new TarFluid.Flowing());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			ItemBlockRenderTypes.setRenderLayer(SLAG.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_SLAG.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(CRYOFLUID.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_CRYOFLUID.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(TAR.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_TAR.get(), RenderType.translucent());
		}
	}
}
