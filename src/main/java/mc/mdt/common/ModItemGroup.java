package mc.mdt.common;

import mc.mdt.MMindustry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * @author luobochuanqi
 */
public class ModItemGroup {
    public static final RegistryKey<ItemGroup> MMDT_ITEM_GROUP
            = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MMindustry.MODID, "mmdt_group"));

    public static final ItemGroup MMDT_GROUP = FabricItemGroup.builder()
            .displayName(Text.translatable(MMindustry.MODID + ".mmdt_group"))
            .icon(() -> new ItemStack(Items.DIAMOND))
            .build();
}
