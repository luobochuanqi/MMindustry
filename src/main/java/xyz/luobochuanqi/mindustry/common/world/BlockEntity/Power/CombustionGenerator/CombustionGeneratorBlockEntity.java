package xyz.luobochuanqi.mindustry.common.world.BlockEntity.Power.CombustionGenerator;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.luobochuanqi.mindustry.client.GUI.OneEItemSlotContainer;
import xyz.luobochuanqi.mindustry.common.Type.GeneratorBlcok.GeneratorBlockEntity;
import xyz.luobochuanqi.mindustry.common.init.TileEntityRegister;

import javax.annotation.Nullable;

public class CombustionGeneratorBlockEntity extends GeneratorBlockEntity implements INamedContainerProvider {
    public CombustionGeneratorBlockEntity() {
        super(TileEntityRegister.combustionGeneratorBlockEntity.get());
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new OneEItemSlotContainer(i, this.level, this.worldPosition, playerInventory);
    }

    @Override
    public int getMaxStackSize() {
        return 10;
    }

    @Override
    public int getBasePowerGeneration() {
        return 60;
    }

    @Override
    public int getProductionTime() {
        return 40;
    }
}
