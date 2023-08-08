package mc.mdt;

import mc.mdt.common.ModItemGroup;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MMindustry implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("MMindustry");
	public static final String MODID = "mmindustry";

	public static final Item CUSTOM_ITEM = new Item(new FabricItemSettings());

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Louding MMindustry...");

		Registry.register(Registries.ITEM, new Identifier(MODID, "custom_item"), CUSTOM_ITEM);
		Registry.register(Registries.ITEM_GROUP, ModItemGroup.MMDT_ITEM_GROUP, ModItemGroup.MMDT_GROUP);
		ItemGroupEvents.modifyEntriesEvent(ModItemGroup.MMDT_ITEM_GROUP).register(content -> {
			content.add(CUSTOM_ITEM);
		});
	}
}