package mc.mdt.common.entitys;

import mc.mdt.common.init.MDTEntitys;
import mc.mdt.common.init.MDTItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class BulletEntity extends ProjectileEntity implements FlyingItemEntity {
    public BulletEntity(EntityType<? extends BulletEntity> entityType, World world) {
        super(MDTEntitys.BULLET_ENTITY, world);
    }

//    public BulletEntity(LivingEntity livingEntity, double d, double e, double f, World world) {
//        super(MDTEntitys.BULLET_ENTITY, world);
//        this.setOwner(livingEntity);
//        this.setVelocity(d, e, f);
//    }

    public BulletEntity(World world, LivingEntity owner, double velocityX, double velocityY, double velocityZ) {
        super(MDTEntitys.BULLET_ENTITY, world);
        this.refreshPosition();
        this.setOwner(owner);
        this.setVelocity(velocityX, velocityY, velocityZ);
        this.setRotation(owner.getYaw(), owner.getPitch());
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.damage(this.getDamageSources().mobProjectile(this.getEffectCause(), null), 9);
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.kill(); // kills the projectile
        }
    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(MDTItems.BULLET);
    }
}
