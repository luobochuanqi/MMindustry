
package top.yzy1.free.iitbag.mindustry.client.renderer;

import top.yzy1.free.iitbag.mindustry.entity.DuoEntity;
import top.yzy1.free.iitbag.mindustry.client.model.Modelduo;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class DuoRenderer extends MobRenderer<DuoEntity, Modelduo<DuoEntity>> {
	public DuoRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelduo(context.bakeLayer(Modelduo.LAYER_LOCATION)), 0f);
	}

	@Override
	public ResourceLocation getTextureLocation(DuoEntity entity) {
		return new ResourceLocation("mindustry:textures/entities/copper_wall.png");
	}
}
