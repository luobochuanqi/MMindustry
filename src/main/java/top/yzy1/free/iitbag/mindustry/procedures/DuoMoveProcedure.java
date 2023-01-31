package top.yzy1.free.iitbag.mindustry.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

public class DuoMoveProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			Entity _ent = entity;
			_ent.teleportTo((entity.getPersistentData().getDouble("tx")), (entity.getPersistentData().getDouble("ty")),
					(entity.getPersistentData().getDouble("tz")));
			if (_ent instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport((entity.getPersistentData().getDouble("tx")), (entity.getPersistentData().getDouble("ty")),
						(entity.getPersistentData().getDouble("tz")), _ent.getYRot(), _ent.getXRot());
		}
	}
}
