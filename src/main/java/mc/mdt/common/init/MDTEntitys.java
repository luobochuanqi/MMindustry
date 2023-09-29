package mc.mdt.common.init;

import io.wispforest.owo.registration.reflect.EntityRegistryContainer;
import mc.mdt.common.entitys.BulletEntity;
import mc.mdt.common.entitys.DuoTurretEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

/**
 * @author luobochuanqi
 */
public class MDTEntitys implements EntityRegistryContainer {

    public static EntityType<DuoTurretEntity> TURRET_DUO_ENTITY = FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DuoTurretEntity::new)
            .dimensions(EntityDimensions.fixed(0.7f, 0.7f))
            .build();

    public static EntityType<BulletEntity> BULLET_ENTITY =
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, createBulletEntity())
                    .dimensions(EntityDimensions.fixed(0.1f, 0.1f))
                    .build();

    private static EntityType.EntityFactory<BulletEntity> createBulletEntity() {
        return (entityType, world) -> new BulletEntity(entityType, world);
    }

    @Override
    public void afterFieldProcessing() {
//        MMindustry.LOGGER.warn("莫西莫西");
        FabricDefaultAttributeRegistry.register(TURRET_DUO_ENTITY, DuoTurretEntity.createMobAttributes());
    }
}
