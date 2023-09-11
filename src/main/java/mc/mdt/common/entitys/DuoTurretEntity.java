package mc.mdt.common.entitys;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

/**
 * @author luobochuanqi
 */
public class DuoTurretEntity extends MobEntity {
    protected DuoTurretEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean canHit() {
        return false;
    }
}
