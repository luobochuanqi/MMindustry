package mc.mdt.common.init;

import io.wispforest.owo.registration.reflect.EntityRegistryContainer;
import mc.mdt.MMindustry;
import mc.mdt.common.entitys.DuoTurretEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;

/**
 * @author luobochuanqi
 */
public class MDTEntitys implements EntityRegistryContainer {

    public static EntityType<DuoTurretEntity> TURRET_DUO_ENTITY_TYPE = FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DuoTurretEntity::new)
            .dimensions(EntityDimensions.fixed(0.7f, 0.7f))
            .build();

    @Override
    public void afterFieldProcessing() {
        MMindustry.LOGGER.info("1");
        FabricDefaultAttributeRegistry.register(TURRET_DUO_ENTITY_TYPE, DuoTurretEntity.createMobAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0));
    }
}
