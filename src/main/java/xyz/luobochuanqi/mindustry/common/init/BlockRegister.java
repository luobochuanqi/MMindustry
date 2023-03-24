package xyz.luobochuanqi.mindustry.common.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.world.Block.Ore.OreBlocks;
import xyz.luobochuanqi.mindustry.common.world.BlockEntity.Machine.Mechanical_Drill.MechanicalDrillBlock;
import xyz.luobochuanqi.mindustry.common.world.BlockEntity.Power.BatteryBlock.BatteryBlock;
import xyz.luobochuanqi.mindustry.common.world.BlockEntity.Power.CombustionGenerator.CombustionGeneratorBlock;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Utils.ModID);

    public static final RegistryObject<Block> copper_ore =
            BLOCKS.register("copper_ore",
                    () -> new OreBlocks(OreBlocks.OreBlockType.copper));
    public static final RegistryObject<Block> lead_ore =
            BLOCKS.register("lead_ore",
                    () -> new OreBlocks(OreBlocks.OreBlockType.lead));
    public static final RegistryObject<Block> coal_ore =
            BLOCKS.register("coal_ore",
                    () -> new OreBlocks(OreBlocks.OreBlockType.coal));
    public static final RegistryObject<Block> titanium_ore =
            BLOCKS.register("titanium_ore",
                    () -> new OreBlocks(OreBlocks.OreBlockType.titanium));
    public static final RegistryObject<Block> thorium_ore =
            BLOCKS.register("thorium_ore",
                    () -> new OreBlocks(OreBlocks.OreBlockType.thorium));
    public static final RegistryObject<Block> scrap_ore =
            BLOCKS.register("scrap_ore",
                    () -> new OreBlocks(OreBlocks.OreBlockType.scrap));

    public static final RegistryObject<Block> battery_block =
            BLOCKS.register("battery_block",
                    () -> new BatteryBlock(AbstractBlock.Properties.of(Material.STONE).strength(5)));
    public static final RegistryObject<Block> combustion_generator_block =
            BLOCKS.register("combustion_generator_block",
                    () -> new CombustionGeneratorBlock(AbstractBlock.Properties.of(Material.STONE).strength(5)));

//    public static final RegistryObject<Block> drill_block =
//            BLOCKS.register("drill_block",
//                    () -> new DrillBlock(AbstractBlock.Properties.of(Material.STONE).strength(5)));
    public static final RegistryObject<Block> mechanical_drill_block =
            BLOCKS.register("mechanical_drill_block",
                    () -> new MechanicalDrillBlock(AbstractBlock.Properties.of(Material.STONE).strength(5)));
}
