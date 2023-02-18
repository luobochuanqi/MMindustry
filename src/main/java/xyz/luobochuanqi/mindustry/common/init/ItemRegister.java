package xyz.luobochuanqi.mindustry.common.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.client.modTab.ModTab;
import xyz.luobochuanqi.mindustry.common.world.Item.Ores;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Utils.ModID);

    public static final RegistryObject<Item> obsidianBlock =
            ITEMS.register("obsidian_block",
                    () -> new BlockItem(BlockRegister.batteryBlock.get(),
                            new Item.Properties().tab(ModTab.modItemGroup)));
    //Ores
    public static final RegistryObject<Item> copper =
            ITEMS.register("copper", () -> new Ores(Ores.OreType.copper));
    public static final RegistryObject<Item> lead =
            ITEMS.register("lead", () -> new Ores(Ores.OreType.lead));
    public static final RegistryObject<Item> plastanium =
            ITEMS.register("plastanium", () -> new Ores(Ores.OreType.plastanium));
    public static final RegistryObject<Item> phase_fabric =
            ITEMS.register("phase_fabric", () -> new Ores(Ores.OreType.phase_fabric));
    public static final RegistryObject<Item> surge_alloy =
            ITEMS.register("surge_alloy", () -> new Ores(Ores.OreType.surge_alloy));
    public static final RegistryObject<Item> spore_pod =
            ITEMS.register("spore_pod", () -> new Ores(Ores.OreType.spore_pod));
    public static final RegistryObject<Item> blast_compound =
            ITEMS.register("blast_compound", () -> new Ores(Ores.OreType.blast_compound));
    public static final RegistryObject<Item> pyratite =
            ITEMS.register("pyratite", () -> new Ores(Ores.OreType.pyratite));
    public static final RegistryObject<Item> metaglass =
            ITEMS.register("metaglass", () -> new Ores(Ores.OreType.metaglass));
    public static final RegistryObject<Item> graphite =
            ITEMS.register("graphite", () -> new Ores(Ores.OreType.graphite));
    public static final RegistryObject<Item> sand =
            ITEMS.register("sand", () -> new Ores(Ores.OreType.sand));
    public static final RegistryObject<Item> coal =
            ITEMS.register("coal", () -> new Ores(Ores.OreType.coal));
    public static final RegistryObject<Item> titanium =
            ITEMS.register("titanium", () -> new Ores(Ores.OreType.titanium));
    public static final RegistryObject<Item> thorium =
            ITEMS.register("thorium", () -> new Ores(Ores.OreType.thorium));
    public static final RegistryObject<Item> scrap =
            ITEMS.register("scrap", () -> new Ores(Ores.OreType.scrap));
    public static final RegistryObject<Item> silicon =
            ITEMS.register("silicon", () -> new Ores(Ores.OreType.silicon));
}