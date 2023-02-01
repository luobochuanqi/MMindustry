
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.item.TitaniumItem;
import top.yzy1.free.iitbag.mindustry.item.TarItem;
import top.yzy1.free.iitbag.mindustry.item.SlagItem;
import top.yzy1.free.iitbag.mindustry.item.SiliconItem;
import top.yzy1.free.iitbag.mindustry.item.ScrapItem;
import top.yzy1.free.iitbag.mindustry.item.SandItem;
import top.yzy1.free.iitbag.mindustry.item.LeadItem;
import top.yzy1.free.iitbag.mindustry.item.GraphiteItem;
import top.yzy1.free.iitbag.mindustry.item.DebugingItemsItem;
import top.yzy1.free.iitbag.mindustry.item.CryofluidItem;
import top.yzy1.free.iitbag.mindustry.item.CopperItem;
import top.yzy1.free.iitbag.mindustry.item.CoalItem;
import top.yzy1.free.iitbag.mindustry.item.BulletItem;
import top.yzy1.free.iitbag.mindustry.MindustryMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

public class MindustryModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MindustryMod.MODID);
	public static final RegistryObject<Item> COPPER = REGISTRY.register("copper", () -> new CopperItem());
	public static final RegistryObject<Item> LEAD = REGISTRY.register("lead", () -> new LeadItem());
	public static final RegistryObject<Item> SAND = REGISTRY.register("sand", () -> new SandItem());
	public static final RegistryObject<Item> COAL = REGISTRY.register("coal", () -> new CoalItem());
	public static final RegistryObject<Item> GRAPHITE = REGISTRY.register("graphite", () -> new GraphiteItem());
	public static final RegistryObject<Item> SILICON = REGISTRY.register("silicon", () -> new SiliconItem());
	public static final RegistryObject<Item> SCRAP = REGISTRY.register("scrap", () -> new ScrapItem());
	public static final RegistryObject<Item> MECHANICAL_DRILL = block(MindustryModBlocks.MECHANICAL_DRILL, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> PNEUMATIC_DRILL = block(MindustryModBlocks.PNEUMATIC_DRILL, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> COPPER_ORE = block(MindustryModBlocks.COPPER_ORE, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> SCRAP_ORE = block(MindustryModBlocks.SCRAP_ORE, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> TITANIUM_ORE = block(MindustryModBlocks.TITANIUM_ORE, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> LEAD_ORE = block(MindustryModBlocks.LEAD_ORE, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> COAL_ORE = block(MindustryModBlocks.COAL_ORE, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> GRAPHITE_PRESS = block(MindustryModBlocks.GRAPHITE_PRESS, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> MELTER = block(MindustryModBlocks.MELTER, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> SILICON_SMELTER = block(MindustryModBlocks.SILICON_SMELTER, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> POWER_NODE = block(MindustryModBlocks.POWER_NODE, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> COMBUSTION_GENERATOR = block(MindustryModBlocks.COMBUSTION_GENERATOR, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> BATTERY = block(MindustryModBlocks.BATTERY, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> TITANIUM = REGISTRY.register("titanium", () -> new TitaniumItem());
	public static final RegistryObject<Item> TITANIUM_WALL = block(MindustryModBlocks.TITANIUM_WALL, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> COPPER_BLOCK = block(MindustryModBlocks.COPPER_BLOCK, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> PLASTANIUM_WALL = block(MindustryModBlocks.PLASTANIUM_WALL, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> THORIUM_WALL = block(MindustryModBlocks.THORIUM_WALL, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> SLAG_BUCKET = REGISTRY.register("slag_bucket", () -> new SlagItem());
	public static final RegistryObject<Item> CRYOFLUID_BUCKET = REGISTRY.register("cryofluid_bucket", () -> new CryofluidItem());
	public static final RegistryObject<Item> CONVEYOR_0 = block(MindustryModBlocks.CONVEYOR_0, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> ROUTER = block(MindustryModBlocks.ROUTER, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> FORCE_PROJECTOR = block(MindustryModBlocks.FORCE_PROJECTOR, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> DEBUGING_ITEMS = REGISTRY.register("debuging_items", () -> new DebugingItemsItem());
	public static final RegistryObject<Item> TAR_BUCKET = REGISTRY.register("tar_bucket", () -> new TarItem());
	public static final RegistryObject<Item> DUO_BLOCK = block(MindustryModBlocks.DUO_BLOCK, MindustryModTabs.TAB_MDT_TAB);
	public static final RegistryObject<Item> BULLET = REGISTRY.register("bullet", () -> new BulletItem());

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
