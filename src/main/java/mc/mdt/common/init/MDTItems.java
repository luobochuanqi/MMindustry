package mc.mdt.common.init;

import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class MDTItems implements ItemRegistryContainer {
    public static Item BULLET = new Item(new FabricItemSettings());
}
