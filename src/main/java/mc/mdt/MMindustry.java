package mc.mdt;

import mc.mdt.common.blockItem.ConveyorBeltBockItem;
import mc.mdt.common.blockentity.WoodConveyorBeltBlockEntity;
import mc.mdt.common.blocks.WoodConveyorBeltBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MMindustry implements ModInitializer {
	/**
	 * This logger is used to write text to the console and the log file.
	 * It is considered best practice to use your mod id as the logger's name.
	 * That way, it's clear which mod wrote info, warnings, and errors.
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger("MMindustry");
	public static final String MOD_ID = "mmindustry";

	/**
	 * ItemGroup
	 */
	public static final ItemGroup MMDT_ITEM_GROUP = FabricItemGroup.builder()
			.displayName(Text.translatable(Util.TEXT_MISC + MMindustry.MOD_ID + ".mmdt_group"))
			.icon(() -> new ItemStack(Items.DIAMOND))
			.build();
	public static final Identifier MMDT_ITEM_GROUP_IDENTIFIER = new Identifier(MOD_ID, "mmdt_item_group");
	public static final RegistryKey<ItemGroup> MMDT_ITEM_GROUP_REGISTRY_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, MMDT_ITEM_GROUP_IDENTIFIER);

	/**
	 *
	 */
	public static final Item CUSTOM_ITEM = new Item(new FabricItemSettings());

	// BELT TRANSFER COOL DOWNS
	public static final int WOOD_TRANSFER_COOLDOWN = 30;
	public static final int IRON_TRANSFER_COOLDOWN = 4;
	public static final int GOLD_TRANSFER_COOLDOWN = 2;

	// BELT ENTITY MOVE SPEEDS
	public static final double WOOD_ENTITY_MOVE_SPEED = 0.011f;
	public static final double IRON_ENTITY_MOVE_SPEED = 0.108f;
	public static final double GOLD_ENTITY_MOVE_SPEED = 0.375f;

	// BELT MOVE ITEMS TO CENTER SPEED
	public static final int WOOD_MOVE_TO_CENTER_SPEED = 2;
	public static final int IRON_MOVE_TO_CENTER_SPEED = 5;
	public static final int GOLD_MOVE_TO_CENTER_SPEED = 12;

	// ROBOTIC ARM MAX PROGRESS (SPEEDS)
	public static final int WOOD_ROBOTIC_ARM_SPEED = 100;
	public static final int IRON_ROBOTIC_ARM_SPEED = 10;
	public static final int GOLD_ROBOTIC_ARM_SPEED = 6;
	public static final int NETHERITE_ROBOTIC_ARM_SPEED = 6;

	// wood
	public static WoodConveyorBeltBlock WOOD_CONVEYOR_BELT_BLOCK = new WoodConveyorBeltBlock(FabricBlockSettings.create().strength(0.8f));
	public static final Identifier WOOD_CONVEYOR_BELT_IDENTIFIER = new Identifier(MOD_ID, "wood_conveyor_belt");
	public static BlockEntityType<WoodConveyorBeltBlockEntity> WOOD_CONVEYOR_BELT_BLOCK_ENTITY;
	public static Item WOOD_CONVEYOR_BELT_BLOCK_ITEM;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Loading MMindustry...");

		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "custom_item"), CUSTOM_ITEM);
		Registry.register(Registries.ITEM_GROUP, MMDT_ITEM_GROUP_IDENTIFIER, MMDT_ITEM_GROUP);

		// wood
		WOOD_CONVEYOR_BELT_BLOCK_ENTITY = Registry.register(
				Registries.BLOCK_ENTITY_TYPE,
				WOOD_CONVEYOR_BELT_IDENTIFIER,
				FabricBlockEntityTypeBuilder.create(
						WoodConveyorBeltBlockEntity::new,
						WOOD_CONVEYOR_BELT_BLOCK)
						.build()
		);
		Registry.register(Registries.BLOCK, WOOD_CONVEYOR_BELT_IDENTIFIER, WOOD_CONVEYOR_BELT_BLOCK);
		WOOD_CONVEYOR_BELT_BLOCK_ITEM = Registry.register(Registries.ITEM, WOOD_CONVEYOR_BELT_IDENTIFIER, new ConveyorBeltBockItem(WOOD_CONVEYOR_BELT_BLOCK, new FabricItemSettings()));

		ItemGroupEvents.modifyEntriesEvent(MMDT_ITEM_GROUP_REGISTRY_KEY).register(content -> {
			content.add(CUSTOM_ITEM);
			content.add(WOOD_CONVEYOR_BELT_BLOCK_ITEM);
		});
	}
}