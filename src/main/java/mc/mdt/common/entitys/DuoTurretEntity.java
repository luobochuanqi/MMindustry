package mc.mdt.common.entitys;

import mc.mdt.MMindustry;
import mc.mdt.common.init.MDTEntitys;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.EnumSet;

/**
 * @author luobochuanqi
 */
public class DuoTurretEntity extends TurretEntity {
    private static final TrackedData<Boolean> SHOOTING = DataTracker.registerData(GhastEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int fireballStrength = 1;

    public DuoTurretEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(MDTEntitys.TURRET_DUO_ENTITY_TYPE, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(7, new LookAtTargetGoal(this));
        this.goalSelector.add(7, new ShootFireballGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, 10, true, false, entity -> Math.abs(entity.getY() - this.getY()) <= 4.0));
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
                MMindustry.LOGGER.info("no Target");
            } else {
                LivingEntity livingEntity = this.duoTurret.getTarget();
                MMindustry.LOGGER.info("yes Target");
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
            this.duoTurret.setShooting(false);
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
                    world.syncWorldEvent(null, WorldEvents.GHAST_WARNS, this.duoTurret.getBlockPos(), 0);
                }
                if (this.cooldown == 20) {
                    double e = 4.0;
                    Vec3d vec3d = this.duoTurret.getRotationVec(1.0f);
                    double f = livingEntity.getX() - (this.duoTurret.getX() + vec3d.x * 4.0);
                    double g = livingEntity.getBodyY(0.5) - (0.5 + this.duoTurret.getBodyY(0.5));
                    double h = livingEntity.getZ() - (this.duoTurret.getZ() + vec3d.z * 4.0);
                    if (!this.duoTurret.isSilent()) {
                        world.syncWorldEvent(null, WorldEvents.GHAST_SHOOTS, this.duoTurret.getBlockPos(), 0);
                    }
                    FireballEntity fireballEntity = new FireballEntity(world, (LivingEntity) this.duoTurret, f, g, h, this.duoTurret.getFireballStrength());
                    fireballEntity.setPosition(this.duoTurret.getX() + vec3d.x * 4.0, this.duoTurret.getBodyY(0.5) + 0.5, fireballEntity.getZ() + vec3d.z * 4.0);
                    world.spawnEntity(fireballEntity);
                    this.cooldown = -40;
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }
            this.duoTurret.setShooting(this.cooldown > 10);
        }
    }
}
