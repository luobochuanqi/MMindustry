package xyz.luobochuanqi.mindustry.common.world.BlockEntity.Power.BatteryBlock;

import xyz.luobochuanqi.mindustry.common.Type.PowerableBlockEntity;
import xyz.luobochuanqi.mindustry.common.init.TileEntityRegister;

public class BatteryBlockEntity extends PowerableBlockEntity {
    public BatteryBlockEntity() {
        super(TileEntityRegister.batteryBlockEntity.get());
    }
}
