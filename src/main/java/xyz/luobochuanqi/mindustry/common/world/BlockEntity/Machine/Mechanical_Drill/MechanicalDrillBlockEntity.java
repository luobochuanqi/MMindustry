package xyz.luobochuanqi.mindustry.common.world.BlockEntity.Machine.Mechanical_Drill;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import xyz.luobochuanqi.mindustry.common.Type.DrillBlock.DrillBlockEntity;
import xyz.luobochuanqi.mindustry.common.Type.DrillBlock.DrillContainerItemNumber;
import xyz.luobochuanqi.mindustry.common.init.BlockRegister;
import xyz.luobochuanqi.mindustry.common.init.ItemRegister;
import xyz.luobochuanqi.mindustry.common.init.TileEntityRegister;
import xyz.luobochuanqi.mindustry.common.util.Mod2x2Part;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public class MechanicalDrillBlockEntity extends DrillBlockEntity implements ITickableTileEntity, INamedContainerProvider {
    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
    private DrillContainerItemNumber itemNumber = new DrillContainerItemNumber();

    public MechanicalDrillBlockEntity() {
        super(TileEntityRegister.mechanicalDrillBlockEntity.get());
    }

    @Override
    public void tick() {
        if (!this.level.isClientSide) {
            if (level.getBlockState(worldPosition).getValue(ModPART) != Mod2x2Part.START) {
                updateData();
            }
        }
        super.tick();
    }

    @Override
    public Set<Item> getDrillableItem() {
        Set<Item> ItemSet = new HashSet<>();
        ItemSet.add(ItemRegister.sand.get());
        ItemSet.add(ItemRegister.copper.get());
        ItemSet.add(ItemRegister.lead.get());
        ItemSet.add(ItemRegister.scrap.get());
        ItemSet.add(ItemRegister.coal.get());
        return ItemSet;
    }

    @Override
    public Set<Block> getDrillableBlock() {
        Set<Block> BlockSet = new HashSet<>();
        BlockSet.add(Blocks.SAND);
        BlockSet.add(BlockRegister.copper_ore.get());
        BlockSet.add(BlockRegister.lead_ore.get());
        BlockSet.add(BlockRegister.scrap_ore.get());
        BlockSet.add(BlockRegister.coal_ore.get());
        return BlockSet;
    }

    @Override
    public double getBaseDrillSpeed() {
        return 0.4;
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
                for (Item item : getDrillableItem()) {
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
                for (Item item : getDrillableItem()) {
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

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new MechanicalDrillBlockContainer(i, this.level, getMainBlockPos(), playerInventory, new DrillContainerItemNumber());
    }
}
