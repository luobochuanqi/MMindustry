package xyz.luobochuanqi.mindustry.common.world.Block.Machine;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import xyz.luobochuanqi.mindustry.common.init.ItemRegister;
import xyz.luobochuanqi.mindustry.common.util.CustomEnergyStorage;
import xyz.luobochuanqi.mindustry.common.world.Block.PowerableBlockEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public abstract class GeneratorBlockEntity extends PowerableBlockEntity implements ITickableTileEntity {
    public LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
    public int counter;

    public GeneratorBlockEntity(TileEntityType<?> pType) {
        super(pType);
    }

    @Override
    public void tick() {
        if (counter > 0) {
            counter--;
            if (counter <= 0) {
                energy.ifPresent(cap -> {
                    if (cap.canReceive()) {
                        ((CustomEnergyStorage) cap).receiveEnergy(getBasePowerGeneration(), false);
                    }
                });
            }
        } else {
            final int[] LS = new int[1];
            handler.ifPresent(cap -> {
                ItemStack itemStack = cap.getStackInSlot(0);
                for (Item item : getPowerGeneratedItem()) {
                    energy.ifPresent(eCap -> {
                        LS[0] = eCap.receiveEnergy(getBasePowerGeneration(), true);
                    });
                    if (itemStack.getItem() == item && LS[0] > 0) {
                        cap.extractItem(0, 1, false);
                        counter = getProductionTime();
                        break;
                    }
                }
            });
        }
    }

    @Override
    public IEnergyStorage createEnergy() {
        return new CustomEnergyStorage(120, 120);
    }

    @Override
    public void load(BlockState pBlockState, CompoundNBT pTag) {
        CompoundNBT invTag = pTag.getCompound("inv");
        handler.ifPresent(nbt -> ((INBTSerializable<CompoundNBT>) nbt).deserializeNBT(invTag));
        counter = pTag.getInt("counter");
        super.load(pBlockState, pTag);
    }

    @Override
    public CompoundNBT save(CompoundNBT pTag) {
        handler.ifPresent(nbt -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) nbt).serializeNBT();
            pTag.put("inv", compound);
        });
        pTag.putInt("counter", counter);
        return super.save(pTag);
    }

    private IItemHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack pStack) {
                for (Item item : getPowerGeneratedItem()) {
                    if (pStack.getItem() == item) {
                        return true;
                    }
                }
                return false;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack pStack, boolean simulate) {
                boolean flag = false;
                for (Item item : getPowerGeneratedItem()) {
                    if (pStack.getItem() == item) {
                        flag = true;
                    }
                }
                if (!flag) {
                    return pStack;
                }
                return super.insertItem(slot, pStack, simulate);
            }

            @Override
            public int getSlotLimit(int slot) {
                if (slot == 0) {
                    return getMaxStackSize();
                } else {
                    return super.getSlotLimit(slot);
                }
            }

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }

    /**
     * Return the Set of minerals that can generate power
     */
    public Set<Item> getPowerGeneratedItem() {
        Set<Item> ItemSet = new HashSet<>();
        ItemSet.add(ItemRegister.coal.get());
        return ItemSet;
    }

    public int getBasePowerGeneration() {
        return 60;
    }

    public int getMaxStackSize() {
        return 10;
    }

    public int getProductionTime() {
        return 40;
    }
}
