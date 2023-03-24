package xyz.luobochuanqi.mindustry.client.GUI;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class Max10Slot extends SlotItemHandler {
    private final IItemHandler itemHandler;
    private final int index;

    public Max10Slot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.itemHandler = itemHandler;
        this.index = index;
    }

    @Override
    public int getMaxStackSize() {
        return 10;
    }

//    @Override
//    public boolean mayPlace(@Nonnull ItemStack stack) {
//        if (stack.isEmpty()) {
//            return false;
//        } else if (stack.getCount() + this.getItem().getCount() > this.getMaxStackSize()) {
//            return false;
//        }
//        return itemHandler.isItemValid(index, stack);
//    }
}
