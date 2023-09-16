package mc.mdt.entityRenderer;

import com.google.common.collect.ImmutableList;
import mc.mdt.common.entitys.DuoTurretEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class DuoTurretEntityModel extends EntityModel<DuoTurretEntity> {

    private final ModelPart base;

    public DuoTurretEntityModel(ModelPart modelPart) {
        this.base = modelPart.getChild("body");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();


        ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData up = body.addChild("up", ModelPartBuilder.create().uv(0, 17).cuboid(-16.0F, -5.0F, 0.0F, 16.0F, 0.0F, 16.0F)
                .uv(27, 34).cuboid(-12.0F, -6.0F, 2.0F, 8.0F, 1.0F, 3.0F)
                .uv(0, 27).cuboid(-11.0F, -6.0F, 1.0F, 6.0F, 1.0F, 1.0F)
                .uv(14, 52).cuboid(-9.0F, -6.0F, 0.0F, 2.0F, 1.0F, 1.0F)
                .uv(6, 0).cuboid(-9.0F, -6.0F, 5.0F, 2.0F, 1.0F, 3.0F), ModelTransform.pivot(8.0F, 0.0F, -8.0F));

        ModelPartData left2 = up.addChild("left2", ModelPartBuilder.create().uv(47, 34).cuboid(-12.0F, -6.0F, 5.0F, 3.0F, 1.0F, 1.0F)
                .uv(7, 52).cuboid(-12.0F, -6.0F, 6.0F, 2.0F, 1.0F, 1.0F)
                .uv(16, 47).cuboid(-13.0F, -6.0F, 3.0F, 1.0F, 1.0F, 3.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData right2 = up.addChild("right2", ModelPartBuilder.create().uv(0, 30).cuboid(-7.0F, -6.0F, 5.0F, 3.0F, 1.0F, 1.0F)
                .uv(9, 30).cuboid(-6.0F, -6.0F, 6.0F, 2.0F, 1.0F, 1.0F)
                .uv(7, 47).cuboid(-4.0F, -6.0F, 3.0F, 1.0F, 1.0F, 3.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData down = body.addChild("down", ModelPartBuilder.create().uv(0, 0).cuboid(-16.0F, 0.0F, 0.0F, 16.0F, 0.0F, 16.0F), ModelTransform.pivot(8.0F, 0.0F, -8.0F));

        ModelPartData edge = body.addChild("edge", ModelPartBuilder.create(), ModelTransform.pivot(8.0F, 0.0F, -8.0F));

        ModelPartData left = edge.addChild("left", ModelPartBuilder.create().uv(49, 24).cuboid(-13.0F, -5.0F, 1.0F, 1.0F, 5.0F, 1.0F)
                .uv(0, 47).cuboid(-14.0F, -5.0F, 2.0F, 1.0F, 5.0F, 2.0F)
                .uv(17, 34).cuboid(-15.0F, -5.0F, 4.0F, 1.0F, 5.0F, 7.0F)
                .uv(49, 17).cuboid(-14.0F, -5.0F, 11.0F, 1.0F, 5.0F, 1.0F)
                .uv(49, 7).cuboid(-13.0F, -5.0F, 12.0F, 1.0F, 5.0F, 1.0F)
                .uv(49, 0).cuboid(-12.0F, -5.0F, 13.0F, 1.0F, 5.0F, 1.0F)
                .uv(10, 34).cuboid(-11.0F, -5.0F, 14.0F, 2.0F, 5.0F, 1.0F)
                .uv(0, 17).cuboid(-10.0F, -5.0F, 10.0F, 1.0F, 5.0F, 4.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData right = edge.addChild("right", ModelPartBuilder.create().uv(48, 46).cuboid(-4.0F, -5.0F, 1.0F, 1.0F, 5.0F, 1.0F)
                .uv(41, 46).cuboid(-3.0F, -5.0F, 2.0F, 1.0F, 5.0F, 2.0F)
                .uv(0, 34).cuboid(-2.0F, -5.0F, 4.0F, 1.0F, 5.0F, 7.0F)
                .uv(25, 47).cuboid(-3.0F, -5.0F, 11.0F, 1.0F, 5.0F, 1.0F)
                .uv(17, 34).cuboid(-4.0F, -5.0F, 12.0F, 1.0F, 5.0F, 1.0F)
                .uv(11, 4).cuboid(-5.0F, -5.0F, 13.0F, 1.0F, 5.0F, 1.0F)
                .uv(0, 34).cuboid(-7.0F, -5.0F, 14.0F, 2.0F, 5.0F, 1.0F)
                .uv(0, 0).cuboid(-7.0F, -5.0F, 10.0F, 1.0F, 5.0F, 4.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData front = edge.addChild("front", ModelPartBuilder.create().uv(32, 46).cuboid(-9.0F, -5.0F, 8.0F, 2.0F, 5.0F, 2.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData behind = edge.addChild("behind", ModelPartBuilder.create().uv(34, 39).cuboid(-12.0F, -5.0F, 0.0F, 8.0F, 5.0F, 1.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        return TexturedModelData.of(meshdefinition, 64, 64);
    }

    // You can use BlockBench, make your model and export it to get this method for your entity model.
//    public static TexturedModelData getTexturedModelData() {
//        ModelData modelData = new ModelData();
//        ModelPartData modelPartData = modelData.getRoot();
//        modelPartData.addChild(EntityModelPartNames.CUBE, ModelPartBuilder.create().uv(0, 0).cuboid(-6F, 12F, -6F, 12F, 12F, 12F), ModelTransform.pivot(0F, 0F, 0F));
//        return TexturedModelData.of(modelData, 64, 64);
//    }

    @Override
    public void setAngles(DuoTurretEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.base).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
    }
}
