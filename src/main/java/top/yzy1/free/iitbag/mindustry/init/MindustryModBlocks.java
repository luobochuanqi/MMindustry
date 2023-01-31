
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.block.TitaniumWallBlock;
import top.yzy1.free.iitbag.mindustry.block.TitaniumOreBlock;
import top.yzy1.free.iitbag.mindustry.block.ThoriumWallBlock;
import top.yzy1.free.iitbag.mindustry.block.TarBlock;
import top.yzy1.free.iitbag.mindustry.block.SlagBlock;
import top.yzy1.free.iitbag.mindustry.block.SiliconSmelterBlock;
import top.yzy1.free.iitbag.mindustry.block.ScrapOreBlock;
import top.yzy1.free.iitbag.mindustry.block.RouterBlock;
import top.yzy1.free.iitbag.mindustry.block.PowerNodeBlock;
import top.yzy1.free.iitbag.mindustry.block.PneumaticDrillBlock;
import top.yzy1.free.iitbag.mindustry.block.PlastaniumWallBlock;
import top.yzy1.free.iitbag.mindustry.block.MelterBlock;
import top.yzy1.free.iitbag.mindustry.block.MechanicalDrillBlock;
import top.yzy1.free.iitbag.mindustry.block.LeadOreBlock;
import top.yzy1.free.iitbag.mindustry.block.GraphitePressBlock;
import top.yzy1.free.iitbag.mindustry.block.ForceProjectorBlock;
import top.yzy1.free.iitbag.mindustry.block.DuoBlockBlock;
import top.yzy1.free.iitbag.mindustry.block.CryofluidBlock;
import top.yzy1.free.iitbag.mindustry.block.CopperOreBlock;
import top.yzy1.free.iitbag.mindustry.block.CopperBlockBlock;
import top.yzy1.free.iitbag.mindustry.block.Conveyor0Block;
import top.yzy1.free.iitbag.mindustry.block.CombustionGeneratorBlock;
import top.yzy1.free.iitbag.mindustry.block.CoalOreBlock;
import top.yzy1.free.iitbag.mindustry.block.BatteryBlock;
import top.yzy1.free.iitbag.mindustry.MindustryMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

public class MindustryModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MindustryMod.MODID);
	public static final RegistryObject<Block> MECHANICAL_DRILL = REGISTRY.register("mechanical_drill", () -> new MechanicalDrillBlock());
	public static final RegistryObject<Block> PNEUMATIC_DRILL = REGISTRY.register("pneumatic_drill", () -> new PneumaticDrillBlock());
	public static final RegistryObject<Block> COPPER_ORE = REGISTRY.register("copper_ore", () -> new CopperOreBlock());
	public static final RegistryObject<Block> SCRAP_ORE = REGISTRY.register("scrap_ore", () -> new ScrapOreBlock());
	public static final RegistryObject<Block> TITANIUM_ORE = REGISTRY.register("titanium_ore", () -> new TitaniumOreBlock());
	public static final RegistryObject<Block> LEAD_ORE = REGISTRY.register("lead_ore", () -> new LeadOreBlock());
	public static final RegistryObject<Block> COAL_ORE = REGISTRY.register("coal_ore", () -> new CoalOreBlock());
	public static final RegistryObject<Block> GRAPHITE_PRESS = REGISTRY.register("graphite_press", () -> new GraphitePressBlock());
	public static final RegistryObject<Block> MELTER = REGISTRY.register("melter", () -> new MelterBlock());
	public static final RegistryObject<Block> SILICON_SMELTER = REGISTRY.register("silicon_smelter", () -> new SiliconSmelterBlock());
	public static final RegistryObject<Block> POWER_NODE = REGISTRY.register("power_node", () -> new PowerNodeBlock());
	public static final RegistryObject<Block> COMBUSTION_GENERATOR = REGISTRY.register("combustion_generator", () -> new CombustionGeneratorBlock());
	public static final RegistryObject<Block> BATTERY = REGISTRY.register("battery", () -> new BatteryBlock());
	public static final RegistryObject<Block> TITANIUM_WALL = REGISTRY.register("titanium_wall", () -> new TitaniumWallBlock());
	public static final RegistryObject<Block> COPPER_BLOCK = REGISTRY.register("copper_block", () -> new CopperBlockBlock());
	public static final RegistryObject<Block> PLASTANIUM_WALL = REGISTRY.register("plastanium_wall", () -> new PlastaniumWallBlock());
	public static final RegistryObject<Block> THORIUM_WALL = REGISTRY.register("thorium_wall", () -> new ThoriumWallBlock());
	public static final RegistryObject<Block> SLAG = REGISTRY.register("slag", () -> new SlagBlock());
	public static final RegistryObject<Block> CRYOFLUID = REGISTRY.register("cryofluid", () -> new CryofluidBlock());
	public static final RegistryObject<Block> CONVEYOR_0 = REGISTRY.register("conveyor_0", () -> new Conveyor0Block());
	public static final RegistryObject<Block> ROUTER = REGISTRY.register("router", () -> new RouterBlock());
	public static final RegistryObject<Block> FORCE_PROJECTOR = REGISTRY.register("force_projector", () -> new ForceProjectorBlock());
	public static final RegistryObject<Block> TAR = REGISTRY.register("tar", () -> new TarBlock());
	public static final RegistryObject<Block> DUO_BLOCK = REGISTRY.register("duo_block", () -> new DuoBlockBlock());
}
