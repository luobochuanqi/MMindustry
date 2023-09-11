package mc.mdt;

import mc.mdt.common.blockItem.ConveyorBeltBockItem;
import mc.mdt.common.blockentity.DuoTurretBlockEntity;
import mc.mdt.common.blockentity.WoodConveyorBeltBlockEntity;
import mc.mdt.common.blocks.DuoTurretBlcok;
import mc.mdt.common.blocks.WoodConveyorBeltBlock;
import mc.mdt.common.entitys.MDTEntitys;
import mc.mdt.common.screenHandler.ConveyorBeltScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author luobochuanqi
 */
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

    // BELT ENTITY MOVE SPEEDS
    public static final double WOOD_ENTITY_MOVE_SPEED = 0.011f;

    // BELT MOVE ITEMS TO CENTER SPEED
    public static final int WOOD_MOVE_TO_CENTER_SPEED = 2;

    // wood
    public static WoodConveyorBeltBlock WOOD_CONVEYOR_BELT_BLOCK = new WoodConveyorBeltBlock(FabricBlockSettings.create().strength(0.8f));
    public static final Identifier WOOD_CONVEYOR_BELT_IDENTIFIER = new Identifier(MOD_ID, "wood_conveyor_belt");
    public static BlockEntityType<WoodConveyorBeltBlockEntity> WOOD_CONVEYOR_BELT_BLOCK_ENTITY;
    public static Item WOOD_CONVEYOR_BELT_BLOCK_ITEM;

    // screen
    // Creating the screen handler type
    public static final ScreenHandlerType<ConveyorBeltScreenHandler> CONVEYOR_BELT_SCREEN_HANDLER_TYPE
            = ScreenHandlerRegistry.registerSimple(new Identifier(MOD_ID, "conveyor_belt_screen"), ConveyorBeltScreenHandler::new);

    /**
     * DuoTurretBlock
     */
    public static DuoTurretBlcok DUO_TURRET_BLOCK = new DuoTurretBlcok(FabricBlockSettings.create().strength(0.8f));
    public static final Identifier DUO_TURRET_IDENTIFIER = new Identifier(MOD_ID, "turret_duo");
    public static BlockEntityType<DuoTurretBlockEntity> DUO_TURRET_BLOCK_ENTITY;
    public static Item DUO_TURRET_BLOCK_ITEM;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("Loading MMindustry...");

        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "custom_item"), CUSTOM_ITEM);
        Registry.register(Registries.ITEM_GROUP, MMDT_ITEM_GROUP_IDENTIFIER, MMDT_ITEM_GROUP);

        MDTEntitys.init();

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

        // turret
        DUO_TURRET_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                DUO_TURRET_IDENTIFIER,
                FabricBlockEntityTypeBuilder.create(
                                DuoTurretBlockEntity::new,
                                DUO_TURRET_BLOCK)
                        .build()
        );
        Registry.register(Registries.BLOCK, DUO_TURRET_IDENTIFIER, DUO_TURRET_BLOCK);
        DUO_TURRET_BLOCK_ITEM = Registry.register(Registries.ITEM, DUO_TURRET_IDENTIFIER, new BlockItem(DUO_TURRET_BLOCK, new FabricItemSettings()));

        ItemGroupEvents.modifyEntriesEvent(MMDT_ITEM_GROUP_REGISTRY_KEY).register(content -> {
            content.add(CUSTOM_ITEM);
            content.add(WOOD_CONVEYOR_BELT_BLOCK_ITEM);
        });
    }
}