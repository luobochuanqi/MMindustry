package mc.mdt.common.entitys;

import mc.mdt.common.init.MDTEntitys;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

/**
 * @author luobochuanqi
 */
public class DuoTurretEntity extends TurretEntity {
    public DuoTurretEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(MDTEntitys.TURRET_DUO_ENTITY_TYPE, world);
    }
}
