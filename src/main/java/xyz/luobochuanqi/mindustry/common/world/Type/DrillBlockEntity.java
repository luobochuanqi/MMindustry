package xyz.luobochuanqi.mindustry.common.world.Type;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.HashSet;
import java.util.Set;

public class DrillBlockEntity extends TileEntity implements ITickableTileEntity {
    public DrillBlockEntity(TileEntityType<?> pType) {
        super(pType);
    }

    @Override
    public void tick() {

    }

    /**
     * Return the Set of minerals that can be mined
     * */
    public Set<Item> getDrillables() {
        Set<Item> ItemSet = new HashSet<>();
        ItemSet.add(Items.SAND);
        return ItemSet;
    }

    public double getBaseDrillSpeed() {
        return 0.4;
    }
}
