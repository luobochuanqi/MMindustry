package mc.mdt.common.entitys;

import mc.mdt.common.init.MDTEntitys;
import mc.mdt.common.init.MDTItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BulletEntity extends ProjectileEntity implements FlyingItemEntity {
    public double powerX;
    public double powerY;
    public double powerZ;

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
        this.refreshPositionAndAngles(owner.getX(), owner.getY(), owner.getZ(), this.getYaw(), this.getPitch());
        this.refreshPosition();
        double d = Math.sqrt(velocityX * velocityX + velocityY * velocityY + velocityZ * velocityZ);
        if (d != 0.0) {
            this.powerX = velocityX / d * 0.1;
            this.powerY = velocityY / d * 0.1;
            this.powerZ = velocityZ / d * 0.1;
        }
        this.setOwner(owner);
        this.setRotation(owner.getYaw(), owner.getPitch());
//        this.setVelocity(velocityX, velocityY, velocityZ);
    }


    @Override
    public void tick() {
        HitResult hitResult;
        Entity entity = this.getOwner();
        if (!this.getWorld().isClient && (entity != null && entity.isRemoved() || !this.getWorld().isChunkLoaded(this.getBlockPos()))) {
            this.discard();
            return;
        }
        super.tick();
        if ((hitResult = ProjectileUtil.getCollision(this, this::canHit)).getType() != HitResult.Type.MISS) {
            this.onCollision(hitResult);
        }
        this.checkBlockCollision();
        Vec3d vec3d = this.getVelocity();
        double d = this.getX() + vec3d.x;
        double e = this.getY() + vec3d.y;
        double f = this.getZ() + vec3d.z;
        ProjectileUtil.setRotationFromVelocity(this, 0.2f);
        this.setVelocity(vec3d.add(this.powerX, this.powerY, this.powerZ));
        this.setPosition(d, e, f);
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
            this.discard();
        }
    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(MDTItems.BULLET);
    }
}
