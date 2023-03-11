package xyz.luobochuanqi.mindustry.common.Type.DrillBlock;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class DrillBlockSlot extends SlotItemHandler {
    public DrillBlockSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public int getMaxStackSize() {
        return 10;
    }
}
