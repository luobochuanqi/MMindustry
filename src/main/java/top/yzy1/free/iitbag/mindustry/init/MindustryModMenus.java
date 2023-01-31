
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.world.inventory.SiliconSmelterUiMenu;
import top.yzy1.free.iitbag.mindustry.world.inventory.PneumaticDrillUiMenu;
import top.yzy1.free.iitbag.mindustry.world.inventory.MelterUiMenu;
import top.yzy1.free.iitbag.mindustry.world.inventory.MechanicalDrillUiMenu;
import top.yzy1.free.iitbag.mindustry.world.inventory.GraphitePressUiMenu;
import top.yzy1.free.iitbag.mindustry.world.inventory.DuoUiMenu;
import top.yzy1.free.iitbag.mindustry.world.inventory.ConveyorUiMenu;
import top.yzy1.free.iitbag.mindustry.world.inventory.CombustionGeneratorUiMenu;
import top.yzy1.free.iitbag.mindustry.MindustryMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

public class MindustryModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MindustryMod.MODID);
	public static final RegistryObject<MenuType<MechanicalDrillUiMenu>> MECHANICAL_DRILL_UI = REGISTRY.register("mechanical_drill_ui",
			() -> IForgeMenuType.create(MechanicalDrillUiMenu::new));
	public static final RegistryObject<MenuType<GraphitePressUiMenu>> GRAPHITE_PRESS_UI = REGISTRY.register("graphite_press_ui",
			() -> IForgeMenuType.create(GraphitePressUiMenu::new));
	public static final RegistryObject<MenuType<CombustionGeneratorUiMenu>> COMBUSTION_GENERATOR_UI = REGISTRY.register("combustion_generator_ui",
			() -> IForgeMenuType.create(CombustionGeneratorUiMenu::new));
	public static final RegistryObject<MenuType<SiliconSmelterUiMenu>> SILICON_SMELTER_UI = REGISTRY.register("silicon_smelter_ui",
			() -> IForgeMenuType.create(SiliconSmelterUiMenu::new));
	public static final RegistryObject<MenuType<MelterUiMenu>> MELTER_UI = REGISTRY.register("melter_ui",
			() -> IForgeMenuType.create(MelterUiMenu::new));
	public static final RegistryObject<MenuType<PneumaticDrillUiMenu>> PNEUMATIC_DRILL_UI = REGISTRY.register("pneumatic_drill_ui",
			() -> IForgeMenuType.create(PneumaticDrillUiMenu::new));
	public static final RegistryObject<MenuType<ConveyorUiMenu>> CONVEYOR_UI = REGISTRY.register("conveyor_ui",
			() -> IForgeMenuType.create(ConveyorUiMenu::new));
	public static final RegistryObject<MenuType<DuoUiMenu>> DUO_UI = REGISTRY.register("duo_ui", () -> IForgeMenuType.create(DuoUiMenu::new));
}
