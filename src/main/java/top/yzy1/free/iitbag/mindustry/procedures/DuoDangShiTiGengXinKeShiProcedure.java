package top.yzy1.free.iitbag.mindustry.procedures;

import net.minecraft.world.level.LevelAccessor;

public class DuoDangShiTiGengXinKeShiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double Etotal = 0;
		double MinIndex = 0;
		double i = 0;
		double dy = 0;
		double dx = 0;
		double dz = 0;
		/*
		List<Double> x1 = new ArrayList<>();
		List<Double> y1 = new ArrayList<>();
		List<Double> z1 = new ArrayList<>();
		List<Double> total = new ArrayList<>();
		List<Double> total3 = new ArrayList<>();
		List<Entity> EItotal = new ArrayList<>();
		{
		final Vec3 _center = new Vec3(x, (y+1), z);
		List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7 / 2d), e -> true)
		.stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
		for (Entity entityiterator : _entfound) {
		if (!(entityiterator instanceof DuoEntity)) {x1.add(entityiterator.getX());
		y1.add(entityiterator.getY());
		z1.add(entityiterator.getZ());
		EItotal.add(entityiterator);
		i++;
		}
		}
		}
		if (i==0) {return;
		}for (int index0 = 0; index0<(int)(x1.size()); index0++) {total.add(x1.get(index0)+y1.get(index0)+z1.get(index0));
		}Etotal = x+y+z;for (int index1 = 0; index1<(int)(x1.size()); index1++) {total.set(index1,Math.abs(total.get(index1)-Etotal));
		}total3.addAll(total);
		total.sort(Comparator.naturalOrder());
		for (int index2 = 0; index2<(int)(total.size()); index2++) {if (total.get(0)==total3.get(index2)) {MinIndex = index2;break;
		}}{
		final Vec3 _center = new Vec3(x, (y+1), z);
		List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7 / 2d), e -> true)
		.stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
		for (Entity entityiterator : _entfound) {
		if (entityiterator instanceof DuoEntity) {dy = (y+1)-EItotal.get(MinIndex).getY();dx = x-EItotal.get(MinIndex).getX();dz = z-EItotal.get(MinIndex).getZ();{
		Entity _ent = entityiterator;
		_ent.setYRot((float)(90+Math.toDegrees(Math.atan2(dz,dx))));
		_ent.setXRot(0);
		_ent.setYBodyRot(_ent.getYRot());
		_ent.setYHeadRot(_ent.getYRot());
		_ent.yRotO = _ent.getYRot();
		_ent.xRotO = _ent.getXRot();
		if (_ent instanceof LivingEntity _entity) {
		_entity.yBodyRotO = _entity.getYRot();
		_entity.yHeadRotO = _entity.getYRot();
		}
		}break;
		}
		}
		}
		*/
	}
}
