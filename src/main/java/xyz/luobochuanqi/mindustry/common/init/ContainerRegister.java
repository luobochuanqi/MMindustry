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
import xyz.luobochuanqi.mindustry.common.Type.DrillBlock.DrillContainerItemNumber;
import xyz.luobochuanqi.mindustry.common.world.BlockEntity.Machine.Mechanical_Drill.MechanicalDrillBlockContainer;

public class ContainerRegister {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, Utils.ModID);

    public static final RegistryObject<ContainerType<MechanicalDrillBlockContainer>> mechanical_drill_block_container =
            CONTAINERS.register("mechanical_drill_block_container",
                    () -> IForgeContainerType.create((int windowId, PlayerInventory playerInv, PacketBuffer extraData)
                            -> new MechanicalDrillBlockContainer(windowId, Minecraft.getInstance().level, extraData.readBlockPos(), playerInv, new DrillContainerItemNumber())));
}