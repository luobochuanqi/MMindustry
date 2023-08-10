package mc.mdt;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Loading MMindustry...");

		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "custom_item"), CUSTOM_ITEM);
		Registry.register(Registries.ITEM_GROUP, MMDT_ITEM_GROUP_IDENTIFIER, MMDT_ITEM_GROUP);

		ItemGroupEvents.modifyEntriesEvent(MMDT_ITEM_GROUP_REGISTRY_KEY).register(content -> {
			content.add(CUSTOM_ITEM);
		});
	}
}