
package top.yzy1.free.iitbag.mindustry.entity;

import top.yzy1.free.iitbag.mindustry.procedures.DuoShiTiChuShiShengChengShiProcedure;
import top.yzy1.free.iitbag.mindustry.procedures.DuoMoveProcedure;
import top.yzy1.free.iitbag.mindustry.procedures.DuoDangShiTiBeiShaSiShiProcedure;
import top.yzy1.free.iitbag.mindustry.procedures.DuoAtkProcedure;
import top.yzy1.free.iitbag.mindustry.init.MindustryModEntities;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class DuoEntity extends PathfinderMob implements RangedAttackMob {
	public DuoEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(MindustryModEntities.DUO.get(), world);
	}

	public DuoEntity(EntityType<DuoEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		setPersistenceRequired();
		this.moveControl = new FlyingMoveControl(this, 10, true);
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new FlyingPathNavigation(this, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Zombie.class, true, true) {
			@Override
			public boolean canUse() {
				double x = DuoEntity.this.getX();
				double y = DuoEntity.this.getY();
				double z = DuoEntity.this.getZ();
				Entity entity = DuoEntity.this;
				Level world = DuoEntity.this.level;
				return super.canUse() && DuoAtkProcedure.execute(world, x, y, z, entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = DuoEntity.this.getX();
				double y = DuoEntity.this.getY();
				double z = DuoEntity.this.getZ();
				Entity entity = DuoEntity.this;
				Level world = DuoEntity.this.level;
				return super.canContinueToUse() && DuoAtkProcedure.execute(world, x, y, z, entity);
			}
		});
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Creeper.class, true, true) {
			@Override
			public boolean canUse() {
				double x = DuoEntity.this.getX();
				double y = DuoEntity.this.getY();
				double z = DuoEntity.this.getZ();
				Entity entity = DuoEntity.this;
				Level world = DuoEntity.this.level;
				return super.canUse() && DuoAtkProcedure.execute(world, x, y, z, entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = DuoEntity.this.getX();
				double y = DuoEntity.this.getY();
				double z = DuoEntity.this.getZ();
				Entity entity = DuoEntity.this;
				Level world = DuoEntity.this.level;
				return super.canContinueToUse() && DuoAtkProcedure.execute(world, x, y, z, entity);
			}
		});
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Skeleton.class, true, true) {
			@Override
			public boolean canUse() {
				double x = DuoEntity.this.getX();
				double y = DuoEntity.this.getY();
				double z = DuoEntity.this.getZ();
				Entity entity = DuoEntity.this;
				Level world = DuoEntity.this.level;
				return super.canUse() && DuoAtkProcedure.execute(world, x, y, z, entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = DuoEntity.this.getX();
				double y = DuoEntity.this.getY();
				double z = DuoEntity.this.getZ();
				Entity entity = DuoEntity.this;
				Level world = DuoEntity.this.level;
				return super.canContinueToUse() && DuoAtkProcedure.execute(world, x, y, z, entity);
			}
		});
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, Spider.class, true, true) {
			@Override
			public boolean canUse() {
				double x = DuoEntity.this.getX();
				double y = DuoEntity.this.getY();
				double z = DuoEntity.this.getZ();
				Entity entity = DuoEntity.this;
				Level world = DuoEntity.this.level;
				return super.canUse() && DuoAtkProcedure.execute(world, x, y, z, entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = DuoEntity.this.getX();
				double y = DuoEntity.this.getY();
				double z = DuoEntity.this.getZ();
				Entity entity = DuoEntity.this;
				Level world = DuoEntity.this.level;
				return super.canContinueToUse() && DuoAtkProcedure.execute(world, x, y, z, entity);
			}
		});
		this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 10) {
			@Override
			public boolean canContinueToUse() {
				return this.canUse();
			}
		});
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
	}

	@Override
	public boolean causeFallDamage(float l, float d, DamageSource source) {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.getDirectEntity() instanceof Player)
			return false;
		if (source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud)
			return false;
		if (source == DamageSource.FALL)
			return false;
		if (source == DamageSource.CACTUS)
			return false;
		if (source == DamageSource.DROWN)
			return false;
		if (source == DamageSource.WITHER)
			return false;
		if (source.getMsgId().equals("witherSkull"))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);
		DuoDangShiTiBeiShaSiShiProcedure.execute(this.level, this.getX(), this.getY(), this.getZ());
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason,
			@Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		DuoShiTiChuShiShengChengShiProcedure.execute(this.getX(), this.getY(), this.getZ(), this);
		return retval;
	}

	@Override
	public void baseTick() {
		super.baseTick();
		DuoMoveProcedure.execute(this);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float flval) {
		DuoEntityProjectile entityarrow = new DuoEntityProjectile(MindustryModEntities.DUO_PROJECTILE.get(), this, this.level);
		double d0 = target.getY() + target.getEyeHeight() - 1.1;
		double d1 = target.getX() - this.getX();
		double d3 = target.getZ() - this.getZ();
		entityarrow.shoot(d1, d0 - entityarrow.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.6F, 12.0F);
		level.addFreshEntity(entityarrow);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return world.isUnobstructed(this);
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected void doPush(Entity entityIn) {
	}

	@Override
	protected void pushEntities() {
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}

	@Override
	public void setNoGravity(boolean ignored) {
		super.setNoGravity(true);
	}

	public void aiStep() {
		super.aiStep();
		this.setNoGravity(true);
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0);
		builder = builder.add(Attributes.MAX_HEALTH, 250);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 0);
		builder = builder.add(Attributes.FOLLOW_RANGE, 13);
		builder = builder.add(Attributes.FLYING_SPEED, 0);
		builder = builder.add(ForgeMod.SWIM_SPEED.get(), 0);
		return builder;
	}
}
