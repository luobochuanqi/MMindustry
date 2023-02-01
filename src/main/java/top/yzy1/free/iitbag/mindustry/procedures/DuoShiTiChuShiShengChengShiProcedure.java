package top.yzy1.free.iitbag.mindustry.procedures;

import net.minecraft.world.entity.Entity;

public class DuoShiTiChuShiShengChengShiProcedure {
	public static void execute(double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putDouble("tx", x);
		entity.getPersistentData().putDouble("ty", y);
		entity.getPersistentData().putDouble("tz", z);
		entity.getPersistentData().putDouble("shootTime", 0);
	}
}
