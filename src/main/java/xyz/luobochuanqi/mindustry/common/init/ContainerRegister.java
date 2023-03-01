package xyz.luobochuanqi.mindustry.common.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.luobochuanqi.mindustry.Utils;

public class ContainerRegister {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, Utils.ModID);

//    public static ContainerType<DrillBlockContainer> DRILL_BLOCK_CONTAINER;

//    public static final RegistryObject<ContainerType<DrillBlockContainer>> drill_container =
//            CONTAINERS.register("drill_container",
//                    () -> IForgeContainerType.create((windowId, inv, data) -> {
//                        BlockPos pos = data.readBlockPos();
//                        return new DrillBlockContainer(windowId, Minecraft.getInstance().level, pos, inv, Minecraft.getInstance().player);
//                    }));
}