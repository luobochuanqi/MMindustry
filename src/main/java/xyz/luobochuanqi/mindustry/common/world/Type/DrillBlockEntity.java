package xyz.luobochuanqi.mindustry.common.world.Type;

import net.minecraft.tileentity.TileEntity;
import xyz.luobochuanqi.mindustry.common.init.TileEntityRegister;

public class DrillBlockEntity extends TileEntity {
    public DrillBlockEntity() {
        super(TileEntityRegister.drillBlockEntity.get());
    }
}
