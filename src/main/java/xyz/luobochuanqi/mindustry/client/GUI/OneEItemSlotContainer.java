package xyz.luobochuanqi.mindustry.client.GUI;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import xyz.luobochuanqi.mindustry.common.init.ContainerRegister;

public class OneEItemSlotContainer extends Container {
    private final TileEntity tileEntity;

    public OneEItemSlotContainer(int pContainerId, World pLevel, BlockPos pPos, PlayerInventory pPlayerInventory) {
        super(ContainerRegister.one_eitem_slot_container.get(), pContainerId);
        this.tileEntity = pLevel.getBlockEntity(pPos);
        TileEntity tileEntity = pLevel.getBlockEntity(pPos);
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
            this.addSlot(new Max10Slot(capability, 0, 79, 35));
        });
        layoutPlayerInventorySlots(pPlayerInventory, 8, 84);
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) this.slots.get(pIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (pIndex < 1) {
                if (!this.moveItemStackTo(itemstack1, 1, this.slots.size(), true))
                    return ItemStack.EMPTY;
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                if (pIndex < 1 + 27) {
                    if (!this.moveItemStackTo(itemstack1, 1 + 27, this.slots.size(), true))
                        return ItemStack.EMPTY;
                } else {
                    if (!this.moveItemStackTo(itemstack1, 1, 1 + 27, false))
                        return ItemStack.EMPTY;
                }
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0)
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;
            slot.onTake(pPlayer, itemstack1);
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(PlayerEntity pPlayer) {
        return true;
    }

    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int i = 0; i < verAmount; i++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }
}