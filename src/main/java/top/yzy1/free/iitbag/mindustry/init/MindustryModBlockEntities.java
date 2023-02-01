
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.block.entity.SiliconSmelterBlockEntity;
import top.yzy1.free.iitbag.mindustry.block.entity.PneumaticDrillBlockEntity;
import top.yzy1.free.iitbag.mindustry.block.entity.MelterBlockEntity;
import top.yzy1.free.iitbag.mindustry.block.entity.MechanicalDrillBlockEntity;
import top.yzy1.free.iitbag.mindustry.block.entity.GraphitePressBlockEntity;
import top.yzy1.free.iitbag.mindustry.block.entity.DuoBlockBlockEntity;
import top.yzy1.free.iitbag.mindustry.block.entity.Conveyor0BlockEntity;
import top.yzy1.free.iitbag.mindustry.block.entity.CombustionGeneratorBlockEntity;
import top.yzy1.free.iitbag.mindustry.block.entity.BatteryBlockEntity;
import top.yzy1.free.iitbag.mindustry.MindustryMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

public class MindustryModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,
			MindustryMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> MECHANICAL_DRILL = register("mechanical_drill", MindustryModBlocks.MECHANICAL_DRILL,
			MechanicalDrillBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> PNEUMATIC_DRILL = register("pneumatic_drill", MindustryModBlocks.PNEUMATIC_DRILL,
			PneumaticDrillBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> GRAPHITE_PRESS = register("graphite_press", MindustryModBlocks.GRAPHITE_PRESS,
			GraphitePressBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> MELTER = register("melter", MindustryModBlocks.MELTER, MelterBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> SILICON_SMELTER = register("silicon_smelter", MindustryModBlocks.SILICON_SMELTER,
			SiliconSmelterBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> COMBUSTION_GENERATOR = register("combustion_generator",
			MindustryModBlocks.COMBUSTION_GENERATOR, CombustionGeneratorBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> BATTERY = register("battery", MindustryModBlocks.BATTERY, BatteryBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> CONVEYOR_0 = register("conveyor_0", MindustryModBlocks.CONVEYOR_0,
			Conveyor0BlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> DUO_BLOCK = register("duo_block", MindustryModBlocks.DUO_BLOCK, DuoBlockBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block,
			BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
