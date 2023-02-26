package xyz.luobochuanqi.mindustry.common.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.world.BlockEntity.BatteryBlockEntity.BatteryBlockEntity;
import xyz.luobochuanqi.mindustry.common.world.BlockEntity.Machine.Mechanical_Drill.MechanicalDrillBlockEntity;
import xyz.luobochuanqi.mindustry.common.world.Type.DrillBlockEntity;

public class TileEntityRegister {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Utils.ModID);

    public static final RegistryObject<TileEntityType<BatteryBlockEntity>> batteryBlockEntity =
            TILE_ENTITIES.register("battery_block_entity", () -> TileEntityType.Builder.of(BatteryBlockEntity::new,
                    BlockRegister.battery_block.get()).build(null));

    public static final RegistryObject<TileEntityType<DrillBlockEntity>> drillBlockEntity =
            TILE_ENTITIES.register("drill_block_entity", () -> TileEntityType.Builder.of(DrillBlockEntity::new,
                    BlockRegister.drill_block.get()).build(null));
    public static final RegistryObject<TileEntityType<MechanicalDrillBlockEntity>> mechanicalDrillBlockEntity =
            TILE_ENTITIES.register("mechanical_drill_block_entity", () -> TileEntityType.Builder.of(MechanicalDrillBlockEntity::new,
                    BlockRegister.mechanical_drill_block.get()).build(null));
}
