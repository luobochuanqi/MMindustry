
package top.yzy1.free.iitbag.mindustry.entity;

import top.yzy1.free.iitbag.mindustry.init.MindustryModItems;
import top.yzy1.free.iitbag.mindustry.init.MindustryModEntities;

import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.network.protocol.Packet;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class DuoEntityProjectile extends AbstractArrow implements ItemSupplier {
	public DuoEntityProjectile(PlayMessages.SpawnEntity packet, Level world) {
		super(MindustryModEntities.DUO_PROJECTILE.get(), world);
	}

	public DuoEntityProjectile(EntityType<? extends DuoEntityProjectile> type, Level world) {
		super(type, world);
	}

	public DuoEntityProjectile(EntityType<? extends DuoEntityProjectile> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
	}

	public DuoEntityProjectile(EntityType<? extends DuoEntityProjectile> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void doPostHurtEffects(LivingEntity livingEntity) {
		super.doPostHurtEffects(livingEntity);
		livingEntity.setArrowCount(livingEntity.getArrowCount() - 1);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return new ItemStack(MindustryModItems.BULLET.get());
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(MindustryModItems.BULLET.get());
	}
}
