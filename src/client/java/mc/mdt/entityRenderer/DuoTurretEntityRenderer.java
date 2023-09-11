package mc.mdt.entityRenderer;

import mc.mdt.MMindustry;
import mc.mdt.MMindustryClient;
import mc.mdt.common.entitys.DuoTurretEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

//@Environment(EnvType.CLIENT)
public class DuoTurretEntityRenderer extends MobEntityRenderer<DuoTurretEntity, DuoTurretEntityModel> {
    public DuoTurretEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DuoTurretEntityModel(context.getPart(MMindustryClient.MODEL_CUBE_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(DuoTurretEntity entity) {
        return new Identifier(MMindustry.MOD_ID, "textures/entity/duo/duo.png");
    }
}
