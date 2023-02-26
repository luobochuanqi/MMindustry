package xyz.luobochuanqi.mindustry.common.world.Type;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import xyz.luobochuanqi.mindustry.common.init.TileEntityRegister;

public class DrillBlockEntity extends TileEntity implements ITickableTileEntity {
    public DrillBlockEntity() {
        super(TileEntityRegister.drillBlockEntity.get());
    }

    @Override
    public void tick() {

    }
}
