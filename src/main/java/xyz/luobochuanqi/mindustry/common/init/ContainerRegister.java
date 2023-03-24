package xyz.luobochuanqi.mindustry.common.init;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.client.GUI.OneItemSlotContainer;

public class ContainerRegister {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, Utils.ModID);

//    public static final RegistryObject<ContainerType<DrillBlockContainer>> drill_block_container =
//            CONTAINERS.register("drill_block_container",
//                    () -> IForgeContainerType.create((int windowId, PlayerInventory playerInv, PacketBuffer extraData)
//                            -> new DrillBlockContainer(windowId, Minecraft.getInstance().level, extraData.readBlockPos(), playerInv)));

    public static final RegistryObject<ContainerType<OneItemSlotContainer>> one_item_slot_container =
            CONTAINERS.register("one_item_slot_container",
                    () -> IForgeContainerType.create((int windowId, PlayerInventory playerInv, PacketBuffer extraData)
                            -> new OneItemSlotContainer(windowId, Minecraft.getInstance().level, extraData.readBlockPos(), playerInv)));
}