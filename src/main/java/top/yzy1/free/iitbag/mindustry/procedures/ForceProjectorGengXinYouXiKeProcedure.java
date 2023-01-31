package top.yzy1.free.iitbag.mindustry.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.particles.ParticleTypes;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class ForceProjectorGengXinYouXiKeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7 / 2d), e -> true).stream()
					.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				entityiterator.setDeltaMovement(new Vec3((entityiterator.getDeltaMovement().x() - entityiterator.getDeltaMovement().x() * 1.5),
						(entityiterator.getDeltaMovement().y() - entityiterator.getDeltaMovement().y() * 1.5),
						(entityiterator.getDeltaMovement().z() - entityiterator.getDeltaMovement().z() * 1.5)));
				world.addParticle(ParticleTypes.CRIT, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 0, 0, 0);
			}
		}
	}
}
