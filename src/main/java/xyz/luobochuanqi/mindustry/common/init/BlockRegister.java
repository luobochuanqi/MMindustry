package xyz.luobochuanqi.mindustry.common.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.world.Block.Ore.OresBlock;
import xyz.luobochuanqi.mindustry.common.world.BlockEntity.BatteryBlockEntity.BatteryBlock;
import xyz.luobochuanqi.mindustry.common.world.BlockEntity.Machine.Mechanical_Drill.MechanicalDrillBlock;
import xyz.luobochuanqi.mindustry.common.world.Type.DrillBlock;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Utils.ModID);

    public static final RegistryObject<Block> copper_ore =
            BLOCKS.register("copper_ore",
                    () -> new OresBlock(OresBlock.OreBlockType.copper));
    public static final RegistryObject<Block> lead_ore =
            BLOCKS.register("lead_ore",
                    () -> new OresBlock(OresBlock.OreBlockType.lead));
    public static final RegistryObject<Block> coal_ore =
            BLOCKS.register("coal_ore",
                    () -> new OresBlock(OresBlock.OreBlockType.coal));
    public static final RegistryObject<Block> titanium_ore =
            BLOCKS.register("titanium_ore",
                    () -> new OresBlock(OresBlock.OreBlockType.titanium));
    public static final RegistryObject<Block> thorium_ore =
            BLOCKS.register("thorium_ore",
                    () -> new OresBlock(OresBlock.OreBlockType.thorium));
    public static final RegistryObject<Block> scrap_ore =
            BLOCKS.register("scrap_ore",
                    () -> new OresBlock(OresBlock.OreBlockType.scrap));

    public static final RegistryObject<Block> battery_block =
            BLOCKS.register("battery_block",
                    () -> new BatteryBlock(AbstractBlock.Properties.of(Material.STONE).strength(5)));

    public static final RegistryObject<Block> drill_block =
            BLOCKS.register("drill_block",
                    () -> new DrillBlock(AbstractBlock.Properties.of(Material.STONE).strength(5)));
    public static final RegistryObject<Block> mechanical_drill_block =
            BLOCKS.register("mechanical_drill_block",
                    () -> new MechanicalDrillBlock(AbstractBlock.Properties.of(Material.STONE).strength(5)));
}
