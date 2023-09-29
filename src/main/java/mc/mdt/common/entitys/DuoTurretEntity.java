package mc.mdt.common.entitys;

import mc.mdt.common.init.MDTEntitys;
import mc.mdt.common.init.MDTSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;

/**
 * @author luobochuanqi
 */
public class DuoTurretEntity extends TurretEntity {
    private static final TrackedData<Boolean> SHOOTING = DataTracker.registerData(DuoTurretEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int fireballStrength = 1;

    public DuoTurretEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(MDTEntitys.TURRET_DUO_ENTITY, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(7, new LookAtTargetGoal(this));
        this.goalSelector.add(7, new ShootFireballGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, 100, false, false, entity -> Math.abs(entity.getY() - this.getY()) <= 13.75));
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return LivingEntity.createLivingAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 250.0).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 13.75);
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

//    @Override
//    protected SoundEvent getAmbientSound() {
//        return MDTSounds.SHOOT;
//    }

    @Override
    protected SoundEvent getDeathSound() {
        return MDTSounds.BOOM;
    }

//    @Override
//    protected SoundEvent getHurtSound(DamageSource source) {
//        return MDTSounds.SHOOT;
//    }

    @Override
    protected float getSoundVolume() {
        return 5.0f;
    }

    static class LookAtTargetGoal
            extends Goal {
        private final DuoTurretEntity duoTurret;

        public LookAtTargetGoal(DuoTurretEntity duoTurret) {
            this.duoTurret = duoTurret;
            this.setControls(EnumSet.of(Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            return true;
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            if (this.duoTurret.getTarget() == null) {
                Vec3d vec3d = this.duoTurret.getVelocity();
                this.duoTurret.setYaw(-((float) MathHelper.atan2(vec3d.x, vec3d.z)) * 57.295776f);
                this.duoTurret.bodyYaw = this.duoTurret.getYaw();
            } else {
                LivingEntity livingEntity = this.duoTurret.getTarget();
                double d = 64.0;
                if (livingEntity.squaredDistanceTo(this.duoTurret) < 4096.0) {
                    double e = livingEntity.getX() - this.duoTurret.getX();
                    double f = livingEntity.getZ() - this.duoTurret.getZ();
                    this.duoTurret.setYaw(-((float) MathHelper.atan2(e, f)) * 57.295776f);
                    this.duoTurret.bodyYaw = this.duoTurret.getYaw();
                }
            }
        }
    }

    public boolean isShooting() {
        return this.dataTracker.get(SHOOTING);
    }

    public void setShooting(boolean shooting) {
        this.dataTracker.set(SHOOTING, shooting);
    }

    public int getFireballStrength() {
        return this.fireballStrength;
    }

    static class ShootFireballGoal
            extends Goal {
        private final DuoTurretEntity duoTurret;
        public int cooldown;

        public ShootFireballGoal(DuoTurretEntity duoTurret) {
            this.duoTurret = duoTurret;
        }

        @Override
        public boolean canStart() {
            return this.duoTurret.getTarget() != null;
        }

        @Override
        public void start() {
            this.cooldown = 0;
        }

        @Override
        public void stop() {
//            this.duoTurret.setShooting(false);
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.duoTurret.getTarget();
            if (livingEntity == null) {
                return;
            }
            double d = 64.0;
            if (livingEntity.squaredDistanceTo(this.duoTurret) < 4096.0 && this.duoTurret.canSee(livingEntity)) {
                World world = this.duoTurret.getWorld();
                ++this.cooldown;
                if (this.cooldown == 10 && !this.duoTurret.isSilent()) {
//                    world.syncWorldEvent(null, WorldEvents.GHAST_WARNS, this.duoTurret.getBlockPos(), 0);
                }
                if (this.cooldown == 20) {
                    double e = 4.0;
                    Vec3d vec3d = this.duoTurret.getRotationVec(1.0f);
                    double f = livingEntity.getX() - (this.duoTurret.getX() + vec3d.x * 4.0);
                    double g = livingEntity.getBodyY(0.5) - (0.5 + this.duoTurret.getBodyY(0.5));
                    double h = livingEntity.getZ() - (this.duoTurret.getZ() + vec3d.z * 4.0);
                    if (!this.duoTurret.isSilent()) {
                        world.playSound(null, this.duoTurret.getBlockPos(), MDTSounds.SHOOT, SoundCategory.HOSTILE);
//                        world.syncWorldEvent(null, WorldEvents.GHAST_SHOOTS, this.duoTurret.getBlockPos(), 0);
                    }
//                    FireballEntity fireballEntity = new FireballEntity(world, (LivingEntity) this.duoTurret, f, g, h, this.duoTurret.getFireballStrength());
//                    fireballEntity.setPosition(this.duoTurret.getX() + vec3d.x * 4.0, this.duoTurret.getBodyY(0.5) + 0.5, fireballEntity.getZ() + vec3d.z * 4.0);
//                    world.spawnEntity(fireballEntity);
                    BulletEntity bulletEntity = new BulletEntity(world, (LivingEntity) this.duoTurret, f, g, h);
                    bulletEntity.setPosition(this.duoTurret.getX() + vec3d.x * 4.0, this.duoTurret.getBodyY(0.5) + 0.5, bulletEntity.getZ() + vec3d.z * 4.0);
                    world.spawnEntity(bulletEntity);
                    this.cooldown = -40;
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }
//            this.duoTurret.setShooting(this.cooldown > 10);
        }
    }

    static class ShootBulletGoal
            extends Goal {
        private final DuoTurretEntity duoTurret;
        public int cooldown;

        public ShootBulletGoal(DuoTurretEntity duoTurret) {
            this.duoTurret = duoTurret;
        }

        @Override
        public boolean canStart() {
            return this.duoTurret.getTarget() != null;
        }

        @Override
        public void start() {
            this.cooldown = 0;
        }

        @Override
        public void stop() {
//            this.duoTurret.setShooting(false);
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.duoTurret.getTarget();
            if (livingEntity == null) {
                return;
            }
            double d = 64.0;
            if (livingEntity.squaredDistanceTo(this.duoTurret) < 4096.0 && this.duoTurret.canSee(livingEntity)) {
                World world = this.duoTurret.getWorld();
                ++this.cooldown;
                if (this.cooldown == 10 && !this.duoTurret.isSilent()) {
//                    world.syncWorldEvent(null, WorldEvents.GHAST_WARNS, this.duoTurret.getBlockPos(), 0);
                }
                if (this.cooldown == 20) {
                    double e = 4.0;
                    Vec3d vec3d = this.duoTurret.getRotationVec(1.0f);
                    double f = livingEntity.getX() - (this.duoTurret.getX() + vec3d.x * 4.0);
                    double g = livingEntity.getBodyY(0.5) - (0.5 + this.duoTurret.getBodyY(0.5));
                    double h = livingEntity.getZ() - (this.duoTurret.getZ() + vec3d.z * 4.0);
                    if (!this.duoTurret.isSilent()) {
                        world.playSound(null, this.duoTurret.getBlockPos(), MDTSounds.SHOOT, SoundCategory.HOSTILE);
//                        world.syncWorldEvent(null, WorldEvents.GHAST_SHOOTS, this.duoTurret.getBlockPos(), 0);
                    }
                    FireballEntity fireballEntity = new FireballEntity(world, (LivingEntity) this.duoTurret, f, g, h, this.duoTurret.getFireballStrength());
                    fireballEntity.setPosition(this.duoTurret.getX() + vec3d.x * 4.0, this.duoTurret.getBodyY(0.5) + 0.5, fireballEntity.getZ() + vec3d.z * 4.0);
                    world.spawnEntity(fireballEntity);
                    this.cooldown = -40;
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }
//            this.duoTurret.setShooting(this.cooldown > 10);
        }
    }
}
