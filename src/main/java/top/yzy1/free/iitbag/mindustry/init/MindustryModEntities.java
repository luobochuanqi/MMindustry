
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.entity.ItemsOnTheGroundEntity;
import top.yzy1.free.iitbag.mindustry.entity.DuoEntityProjectile;
import top.yzy1.free.iitbag.mindustry.entity.DuoEntity;
import top.yzy1.free.iitbag.mindustry.MindustryMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MindustryModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MindustryMod.MODID);
	public static final RegistryObject<EntityType<ItemsOnTheGroundEntity>> ITEMS_ON_THE_GROUND = register("items_on_the_ground",
			EntityType.Builder.<ItemsOnTheGroundEntity>of(ItemsOnTheGroundEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ItemsOnTheGroundEntity::new).fireImmune().sized(0f, 0.6f));
	public static final RegistryObject<EntityType<DuoEntity>> DUO = register("duo",
			EntityType.Builder.<DuoEntity>of(DuoEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(13)
					.setUpdateInterval(3).setCustomClientFactory(DuoEntity::new)

					.sized(0.6f, 0.6f));
	public static final RegistryObject<EntityType<DuoEntityProjectile>> DUO_PROJECTILE = register("projectile_duo",
			EntityType.Builder.<DuoEntityProjectile>of(DuoEntityProjectile::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(DuoEntityProjectile::new).sized(0.5f, 0.5f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			ItemsOnTheGroundEntity.init();
			DuoEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(ITEMS_ON_THE_GROUND.get(), ItemsOnTheGroundEntity.createAttributes().build());
		event.put(DUO.get(), DuoEntity.createAttributes().build());
	}
}
