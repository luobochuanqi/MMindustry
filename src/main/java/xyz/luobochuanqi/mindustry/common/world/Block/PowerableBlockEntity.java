package xyz.luobochuanqi.mindustry.common.world.Block;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import xyz.luobochuanqi.mindustry.common.util.CustomEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class PowerableBlockEntity extends TileEntity implements ITickableTileEntity {
    public LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);

    public PowerableBlockEntity(TileEntityType<?> pType) {
        super(pType);
    }

    @Override
    public void tick() {
        if (!level.isClientSide) {
//            sendOutPower();
        }
    }

    /**
     * (if there is any power present in )
     * loop over all six directions and check
     * if there is a power receiver there that is willing to get power from us.
     * */
    private void sendOutPower() {
        energy.ifPresent(energy -> {
            AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
            if (capacity.get() > 0) {
                for (Direction direction : Direction.values()) {
                    TileEntity te = level.getBlockEntity(worldPosition.relative(direction));
                    if (te != null) {
                        boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                                    if (handler.canReceive()) {
                                        int received = handler.receiveEnergy(Math.min(capacity.get(), 100), false);
                                        capacity.addAndGet(-received);
                                        ((CustomEnergyStorage) energy).receiveEnergy(received, false);
                                        setChanged();
                                        return capacity.get() > 0;
                                    } else {
                                        return true;
                                    }
                                }
                        ).orElse(true);
                        if (!doContinue) {
                            return;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void load(BlockState pBlockState, CompoundNBT pTag) {
        CompoundNBT energyTag = pTag.getCompound("energy");
        energy.ifPresent(nbt -> ((INBTSerializable<CompoundNBT>) nbt).deserializeNBT(energyTag));
        super.load(pBlockState, pTag);
    }

    @Override
    public CompoundNBT save(CompoundNBT pTag) {
        energy.ifPresent(nbt -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) nbt).serializeNBT();
            pTag.put("energy", compound);
        });
        return super.save(pTag);
    }

    public IEnergyStorage createEnergy() {
        return new CustomEnergyStorage(4000, 4000);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }

    public int getEnergy() {
        AtomicInteger e = new AtomicInteger();
        this.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(cap -> {
            e.set(((CustomEnergyStorage) cap).getEnergyStored());
        });
        return e.get();
    }
}
