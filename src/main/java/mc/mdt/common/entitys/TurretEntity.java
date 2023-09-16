package mc.mdt.common.entitys;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class TurretEntity extends MobEntity {
    protected TurretEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
        // 设置无限血量
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(Double.MAX_VALUE);
        this.setHealth((float) Double.MAX_VALUE);
    }

    @Override
    public boolean canHit() {
        return super.canHit();
    }

    @Override
    protected void pushAway(Entity entity) {
    }

    @Override
    public boolean isInvulnerable() {
        return super.isInvulnerable();
    }
}
