package top.yzy1.free.iitbag.mindustry.procedures;

import top.yzy1.free.iitbag.mindustry.entity.DuoEntity;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class DuoGametickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double dy = 0;
		double dx = 0;
		double dz = 0;
		Entity AE = null;
		{
			Entity _ent = entity;
			_ent.teleportTo((entity.getPersistentData().getDouble("tx")), (entity.getPersistentData().getDouble("ty")),
					(entity.getPersistentData().getDouble("tz")));
			if (_ent instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport((entity.getPersistentData().getDouble("tx")), (entity.getPersistentData().getDouble("ty")),
						(entity.getPersistentData().getDouble("tz")), _ent.getYRot(), _ent.getXRot());
		}
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7 / 2d), e -> true).stream()
					.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (!(entityiterator instanceof DuoEntity || entityiterator instanceof ThrowableProjectile || entityiterator instanceof Arrow
						|| entityiterator instanceof ItemEntity || entityiterator instanceof Player)) {
					AE = entityiterator;
					break;
				}
			}
		}
		if (AE == null) {
			return;
		}
		dy = y - AE.getY();
		dx = x - AE.getX();
		dz = z - AE.getZ();
		{
			Entity _ent = entity;
			_ent.setYRot((float) (90 + Math.toDegrees(Math.atan2(dz, dx))));
			_ent.setXRot(0);
			_ent.setYBodyRot(_ent.getYRot());
			_ent.setYHeadRot(_ent.getYRot());
			_ent.yRotO = _ent.getYRot();
			_ent.xRotO = _ent.getXRot();
			if (_ent instanceof LivingEntity _entity) {
				_entity.yBodyRotO = _entity.getYRot();
				_entity.yHeadRotO = _entity.getYRot();
			}
		}
	}
}
