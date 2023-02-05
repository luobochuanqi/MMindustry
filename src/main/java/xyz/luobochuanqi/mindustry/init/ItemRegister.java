package xyz.luobochuanqi.mindustry.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.client.modTab.ModTab;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Utils.ModID);

    public static final RegistryObject<Item> obsidianBlock =
            ITEMS.register("obsidian_block",
                    () -> new BlockItem(BlockRegister.batteryBlock.get(),
                            new Item.Properties().tab(ModTab.modItemGroup)));
}
