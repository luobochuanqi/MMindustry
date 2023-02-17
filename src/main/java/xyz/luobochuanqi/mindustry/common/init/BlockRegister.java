package xyz.luobochuanqi.mindustry.common.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.world.BlockEntity.BatteryBlockEntity.BatteryBlock;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Utils.ModID);

    public static final RegistryObject<Block> batteryBlock =
            BLOCKS.register("battery_block",
                    () -> new BatteryBlock(AbstractBlock.Properties.of(Material.STONE).strength(5)));
}
