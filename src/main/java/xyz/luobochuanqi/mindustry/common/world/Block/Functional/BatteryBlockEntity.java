package xyz.luobochuanqi.mindustry.common.world.Block.Functional;

import net.minecraftforge.energy.IEnergyStorage;
import xyz.luobochuanqi.mindustry.common.init.TileEntityRegister;
import xyz.luobochuanqi.mindustry.common.util.CustomEnergyStorage;
import xyz.luobochuanqi.mindustry.common.world.Block.PowerableBlockEntity;

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
