package xyz.luobochuanqi.mindustry.common.Type.DrillBlock;

import net.minecraft.util.IIntArray;

public class DrillContainerItemNumber implements IIntArray {
    int i = 0;

    @Override
    public int get(int pIndex) {
        return i;
    }

    @Override
    public void set(int pIndex, int pValue) {
        i = pValue;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
