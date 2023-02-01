
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import top.yzy1.free.iitbag.mindustry.client.renderer.ItemsOnTheGroundRenderer;
import top.yzy1.free.iitbag.mindustry.client.renderer.DuoRenderer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MindustryModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MindustryModEntities.ITEMS_ON_THE_GROUND.get(), ItemsOnTheGroundRenderer::new);
		event.registerEntityRenderer(MindustryModEntities.DUO.get(), DuoRenderer::new);
		event.registerEntityRenderer(MindustryModEntities.DUO_PROJECTILE.get(), ThrownItemRenderer::new);
	}
}
