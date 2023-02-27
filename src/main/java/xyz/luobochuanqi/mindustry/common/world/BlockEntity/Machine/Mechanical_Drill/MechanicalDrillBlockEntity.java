package xyz.luobochuanqi.mindustry.common.world.BlockEntity.Machine.Mechanical_Drill;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.luobochuanqi.mindustry.common.init.ItemRegister;
import xyz.luobochuanqi.mindustry.common.init.TileEntityRegister;
import xyz.luobochuanqi.mindustry.common.world.Type.DrillBlockEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public class MechanicalDrillBlockEntity extends DrillBlockEntity implements ITickableTileEntity {
    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    private static final Logger LOGGER = LogManager.getLogger();

    public MechanicalDrillBlockEntity() {
        super(TileEntityRegister.mechanicalDrillBlockEntity.get());
    }

    @Override
    public void tick() {

    }

    @Override
    public Set<Item> getDrillables() {
        Set<Item> ItemSet = new HashSet<>();
        ItemSet.add(ItemRegister.sand.get());
        ItemSet.add(ItemRegister.copper.get());
        ItemSet.add(ItemRegister.lead.get());
        ItemSet.add(ItemRegister.scrap.get());
        ItemSet.add(ItemRegister.coal.get());
        return ItemSet;
    }

    @Override
    public void load(BlockState pBlockState, CompoundNBT pNBT) {
        CompoundNBT invTag = pNBT.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(invTag));
        super.load(pBlockState, pNBT);
    }

    @Override
    public CompoundNBT save(CompoundNBT pNBT) {
        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            pNBT.put("inv", compound);
        });
        return super.save(pNBT);
    }

    private IItemHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack pStack) {
                for (Item item : getDrillables()) {
                    if (pStack.getItem() == item) {
                        LOGGER.debug("qqq1");
                        return true;
                    }
                }
                return false;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack pStack, boolean simulate) {
                boolean flag = false;
                for (Item item : getDrillables()) {
                    if (pStack.getItem() == item) {
                        LOGGER.debug(item.toString());
                        flag = true;
                    }
                }
                if (!flag) {
                    return pStack;
                }
                return super.insertItem(slot, pStack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> pCap, @Nullable Direction side) {
        if (pCap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(pCap, side);
    }
}
