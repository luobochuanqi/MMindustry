package xyz.luobochuanqi.mindustry.world.BlockEntity.BatteryBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import xyz.luobochuanqi.mindustry.init.TileEntityRegister;

public class BatteryBlockEntity extends TileEntity {
    public int counter = 0;

    public BatteryBlockEntity() {
        super(TileEntityRegister.batteryBlockEntity.get());
    }

    @Override
    public void load(BlockState pState, CompoundNBT pNbt) {
        counter = pNbt.getInt("counter");
        super.load(pState, pNbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT pCompound) {
        pCompound.putInt("counter", counter);
        return super.save(pCompound);
    }
}
