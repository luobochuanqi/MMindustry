package mc.mdt.common.screenHandler;

import mc.mdt.MMindustry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ConveyorBeltScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    /**
     * This constructor gets called on the client when the server wants it to open the screenHandler,
     * The client will call the other constructor with an empty Inventory and the screenHandler will automatically
     * sync this empty inventory with the inventory on the server.
     */
    public ConveyorBeltScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3));
    }

    /**
     * This constructor gets called from the BlockEntity on the server without calling the other constructor first, the server knows the inventory of the container
     * and can therefore directly provide it as an argument. This inventory will then be synced to the client.
     */
    public ConveyorBeltScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(MMindustry.CONVEYOR_BELT_SCREEN_HANDLER_TYPE, syncId);
        checkSize(inventory, 3);
        this.inventory = inventory;
        // some inventories do custom logic when a player opens it.
        inventory.onOpen(playerInventory.player);

        int m;
        int l;
        //Our inventory
        for (m = 0; m < 3; m++) {
            this.addSlot(new Slot(inventory, m, 62 + m * 18, 17 + 18));
        }
        //The player inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        //The player Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }

    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
