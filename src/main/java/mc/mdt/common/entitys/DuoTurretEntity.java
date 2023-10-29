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
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;

/**
 * @author luobochuanqi
 */
public class DuoTurretEntity extends TurretEntity {
    private static final TrackedData<Boolean> SHOOTING = DataTracker.registerData(DuoTurretEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int fireballStrength = 1;

    public DuoTurretEntity(EntityType<?> entityType, World world) {
        super(MDTEntitys.TURRET_DUO_ENTITY, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(7, new LookAtTargetGoal(this));
        this.goalSelector.add(7, new ShootFireballGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<HostileEntity>(this, HostileEntity.class, 1, false, false, entity -> entity.squaredDistanceTo(new Vec3d(this.getX(), this.getY(), this.getZ())) <= 400.0));
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
//                Vec3d vec3d = this.duoTurret.getVelocity();
//                this.duoTurret.setYaw(-((float) MathHelper.atan2(vec3d.x, vec3d.z)) * 57.295776f);
//                this.duoTurret.bodyYaw = this.duoTurret.getYaw();
            } else {
                LivingEntity livingEntity = this.duoTurret.getTarget();
                double d = 64.0;
                if (livingEntity.squaredDistanceTo(this.duoTurret) < 4096.0) {
                    double e = livingEntity.getX() - this.duoTurret.getX();
                    double f = livingEntity.getZ() - this.duoTurret.getZ();
                    this.duoTurret.getLookControl().lookAt(livingEntity);
//                    this.duoTurret.setYaw(-((float) MathHelper.atan2(e, f)) * 57.295776f + 90.0f);
//                    this.duoTurret.bodyYaw = this.duoTurret.getYaw();
//                    this.duoTurret.headYaw = -((float) MathHelper.atan2(e, f)) * 57.295776f;
//                    this.duoTurret.headYaw = this.duoTurret.getYaw();
//                    this.duoTurret.prevYaw = this.duoTurret.getYaw();
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
//            MMindustry.LOGGER.info(String.valueOf(cooldown));
            LivingEntity livingEntity = this.duoTurret.getTarget();
            if (livingEntity == null) {
                return;
            }
            double d = 64.0;
            if (livingEntity.squaredDistanceTo(this.duoTurret) < 400.0 && this.duoTurret.canSee(livingEntity)) {
                World world = this.duoTurret.getWorld();
                ++this.cooldown;
                if (this.cooldown == 13) {
                    // Determine whether the current direction is towards the target.
//                    LivingEntity livingEntity1 = this.duoTurret.getTarget();
//                    double e1 = livingEntity1.getX() - this.duoTurret.getX();
//                    double f1 = livingEntity1.getZ() - this.duoTurret.getZ();
//                    float targetYaw = -((float) MathHelper.atan2(e1, f1)) * 57.295776f;
//                    if (this.duoTurret.getYaw() == targetYaw)
                    {
                        double e = 4.0;
                        Vec3d vec3d = this.duoTurret.getRotationVec(1.0f);
                        double f = livingEntity.getX() - (this.duoTurret.getX() + vec3d.x * 0.5);
                        double g = livingEntity.getBodyY(0.7) - (this.duoTurret.getBodyY(0.5));
                        double h = livingEntity.getZ() - (this.duoTurret.getZ() + vec3d.z * 0.5);
                        if (!this.duoTurret.isSilent()) {
                            world.playSound(null, this.duoTurret.getBlockPos(), MDTSounds.SHOOT, SoundCategory.HOSTILE);
                        }
                        BulletEntity bulletEntity1 = new BulletEntity(world, (LivingEntity) this.duoTurret, f, g, h);
                        bulletEntity1.setPosition(this.duoTurret.getX() + vec3d.x * 0.4, this.duoTurret.getBodyY(0.2), this.duoTurret.getZ() + vec3d.z * 0.4);
                        world.spawnEntity(bulletEntity1);
                    }
                    this.cooldown = 0;
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }
//            this.duoTurret.setShooting(this.cooldown > 10);
        }
    }
}
