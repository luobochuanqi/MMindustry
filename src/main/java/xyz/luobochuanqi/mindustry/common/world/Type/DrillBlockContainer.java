package xyz.luobochuanqi.mindustry.common.world.Type;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

import javax.annotation.Nullable;

public class DrillBlockContainer extends Container {
    protected DrillBlockContainer(@Nullable ContainerType<?> pMenuType, int pContainerId) {
        super(pMenuType, pContainerId);
    }

    @Override
    public boolean stillValid(PlayerEntity pPlayer) {
        return false;
    }
}
