package xyz.luobochuanqi.mindustry.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.world.BlockEntity.BatteryBlockEntity.BatteryBlockEntity;

public class TileEntityRegister {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Utils.ModID);

    public static final RegistryObject<TileEntityType<BatteryBlockEntity>> batteryBlockEntity =
            TILE_ENTITIES.register("battery_block_entity", () -> TileEntityType.Builder.of(BatteryBlockEntity::new,
                    BlockRegister.batteryBlock.get()).build(null));
}
