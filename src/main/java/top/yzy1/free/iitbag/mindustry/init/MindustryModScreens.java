
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.client.gui.SiliconSmelterUiScreen;
import top.yzy1.free.iitbag.mindustry.client.gui.PneumaticDrillUiScreen;
import top.yzy1.free.iitbag.mindustry.client.gui.MelterUiScreen;
import top.yzy1.free.iitbag.mindustry.client.gui.MechanicalDrillUiScreen;
import top.yzy1.free.iitbag.mindustry.client.gui.GraphitePressUiScreen;
import top.yzy1.free.iitbag.mindustry.client.gui.DuoUiScreen;
import top.yzy1.free.iitbag.mindustry.client.gui.ConveyorUiScreen;
import top.yzy1.free.iitbag.mindustry.client.gui.CombustionGeneratorUiScreen;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MindustryModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(MindustryModMenus.MECHANICAL_DRILL_UI.get(), MechanicalDrillUiScreen::new);
			MenuScreens.register(MindustryModMenus.GRAPHITE_PRESS_UI.get(), GraphitePressUiScreen::new);
			MenuScreens.register(MindustryModMenus.COMBUSTION_GENERATOR_UI.get(), CombustionGeneratorUiScreen::new);
			MenuScreens.register(MindustryModMenus.SILICON_SMELTER_UI.get(), SiliconSmelterUiScreen::new);
			MenuScreens.register(MindustryModMenus.MELTER_UI.get(), MelterUiScreen::new);
			MenuScreens.register(MindustryModMenus.PNEUMATIC_DRILL_UI.get(), PneumaticDrillUiScreen::new);
			MenuScreens.register(MindustryModMenus.CONVEYOR_UI.get(), ConveyorUiScreen::new);
			MenuScreens.register(MindustryModMenus.DUO_UI.get(), DuoUiScreen::new);
		});
	}
}
