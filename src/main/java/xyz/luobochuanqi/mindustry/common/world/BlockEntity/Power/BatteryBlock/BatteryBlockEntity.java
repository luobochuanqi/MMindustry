package xyz.luobochuanqi.mindustry.common.world.BlockEntity.Power.BatteryBlock;

import net.minecraftforge.energy.IEnergyStorage;
import xyz.luobochuanqi.mindustry.common.Type.PowerableBlockEntity;
import xyz.luobochuanqi.mindustry.common.init.TileEntityRegister;
import xyz.luobochuanqi.mindustry.common.util.CustomEnergyStorage;

public class BatteryBlockEntity extends PowerableBlockEntity {
    public BatteryBlockEntity() {
        super(TileEntityRegister.batteryBlockEntity.get());
    }

    @Override
    public IEnergyStorage createEnergy() {
        return new CustomEnergyStorage(4000, 4000);
    }

    @Override
    public void tick() {

    }
}
