package mc.mdt.common.entitys;

import mc.mdt.MMindustry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * @author luobochuanqi
 */
public class MDTEntitys {

    public static EntityType<DuoTurretEntity> TURRET_DUO_ENTITY_TYPE;

    public static void init() {
        TURRET_DUO_ENTITY_TYPE = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(MMindustry.MOD_ID, "turret_duo"),
                FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DuoTurretEntity::new)
                        .dimensions(EntityDimensions.fixed(0.1f, 0.1f))
                        .build()
        );
        FabricDefaultAttributeRegistry.register(TURRET_DUO_ENTITY_TYPE, DuoTurretEntity.createMobAttributes());
    }
}
