package xyz.luobochuanqi.mindustry.common.Type.DrillBlock;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import xyz.luobochuanqi.mindustry.common.init.ContainerRegister;
import xyz.luobochuanqi.mindustry.common.world.BlockEntity.Machine.Mechanical_Drill.MechanicalDrillBlockEntity;

public class DrillBlockContainer extends Container {
    private IItemHandler internal;
    private DrillContainerItemNumber itemNumber;

    public DrillBlockContainer(int pContainerId, World pLevel, BlockPos pPos, PlayerInventory pPlayerInventory, DrillContainerItemNumber pItemNumber) {
        super(ContainerRegister.drill_block_container.get(), pContainerId);
        this.itemNumber = pItemNumber;
        MechanicalDrillBlockEntity mechanicalDrillBlockEntity = (MechanicalDrillBlockEntity) pLevel.getBlockEntity(pPos);
        mechanicalDrillBlockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
            this.addSlot(new SlotItemHandler(capability, 0, 80, 32));
        });
//        this.addSlot(new Slot(mechanicalDrillBlockEntity.getInventory(), 0, 80, 32));
        layoutPlayerInventorySlots(pPlayerInventory, 8, 84);
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

    public IIntArray getIntArray() {
        return itemNumber;
    }
}
