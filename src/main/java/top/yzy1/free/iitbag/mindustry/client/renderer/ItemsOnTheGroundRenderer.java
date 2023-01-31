
package top.yzy1.free.iitbag.mindustry.client.renderer;

import top.yzy1.free.iitbag.mindustry.entity.ItemsOnTheGroundEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HumanoidModel;

public class ItemsOnTheGroundRenderer extends HumanoidMobRenderer<ItemsOnTheGroundEntity, HumanoidModel<ItemsOnTheGroundEntity>> {
	public ItemsOnTheGroundRenderer(EntityRendererProvider.Context context) {
		super(context, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER)), 0f);
		this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
				new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))));
	}

	@Override
	public ResourceLocation getTextureLocation(ItemsOnTheGroundEntity entity) {
		return new ResourceLocation("mindustry:textures/entities/steve1.png");
	}
}
