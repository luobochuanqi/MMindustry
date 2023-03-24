package xyz.luobochuanqi.mindustry.common.world.BlockEntity.Machine.Mechanical_Drill;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.luobochuanqi.mindustry.client.GUI.OneItemSlotContainer;
import xyz.luobochuanqi.mindustry.common.Type.DrillBlock.DrillBlockEntity;
import xyz.luobochuanqi.mindustry.common.init.BlockRegister;
import xyz.luobochuanqi.mindustry.common.init.ItemRegister;
import xyz.luobochuanqi.mindustry.common.init.TileEntityRegister;
import xyz.luobochuanqi.mindustry.common.util.Mod2x2Part;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public class MechanicalDrillBlockEntity extends DrillBlockEntity implements ITickableTileEntity, INamedContainerProvider {
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
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new OneItemSlotContainer(i, this.level, getMainBlockPos(), playerInventory);
    }

    @Override
    public double getBaseDrillSpeed() {
        return 0.4;
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
}
