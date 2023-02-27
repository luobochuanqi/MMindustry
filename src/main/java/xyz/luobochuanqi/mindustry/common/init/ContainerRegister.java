package xyz.luobochuanqi.mindustry.common.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.luobochuanqi.mindustry.Utils;

public class ContainerRegister {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, Utils.ModID);

//    public static final RegistryObject<ContainerType<ObsidianFirstContainer>> obsidianFirstContainer =
//            CONTAINERS.register("obsidian_first_container",
//                    () -> IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data)
//                            -> new ObsidianFirstContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world, new ObsidianFirstContainerItemNumber())));
}
