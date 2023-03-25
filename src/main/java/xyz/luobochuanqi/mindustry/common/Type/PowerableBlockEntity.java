package xyz.luobochuanqi.mindustry.common.Type;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
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

public abstract class PowerableBlockEntity extends TileEntity {
    public LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);

    public PowerableBlockEntity(TileEntityType<?> pType) {
        super(pType);
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
        return new CustomEnergyStorage(4000, 0);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }
}
