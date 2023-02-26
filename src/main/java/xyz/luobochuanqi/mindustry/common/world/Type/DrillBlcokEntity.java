package xyz.luobochuanqi.mindustry.common.world.Type;

import net.minecraft.tileentity.TileEntity;
import xyz.luobochuanqi.mindustry.common.init.TileEntityRegister;

public class DrillBlcokEntity extends TileEntity {
    public DrillBlcokEntity() {
        super(TileEntityRegister.drillBlockEntity.get());
    }
}
