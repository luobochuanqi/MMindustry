package top.yzy1.free.iitbag.mindustry.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.6.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelduo<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mindustry", "modelduo"), "main");
	public final ModelPart bone10;

	public Modelduo(ModelPart root) {
		this.bone10 = root.getChild("bone10");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition bone10 = partdefinition.addOrReplaceChild("bone10", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition aleph = bone10.addOrReplaceChild("aleph", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bone6 = aleph.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bone2 = bone6.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(-4.0F, -4.0F, 2.0F));
		PartDefinition a = bone2.addOrReplaceChild("a",
				CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, 0.5F, 5.0F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 0.0F, -2.0F));
		PartDefinition cube_r1 = a.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(11, 25).addBox(-2.9341F, 0.0F, 4.9776F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, -0.6545F, 0.0F));
		PartDefinition bone = bone2.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(0, 25).addBox(2.0F, 0.0F, -7.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 25)
						.addBox(-4.0F, 0.0F, -7.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(13, 25)
						.addBox(-1.5F, 0.0F, 1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 25).addBox(-3.0F, 0.0F, 5.0F, 6.0F, 2.0F,
								1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, 0.5F, -2.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition a2 = bone2.addOrReplaceChild("a2",
				CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, 0.5F, 0.0F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 25)
						.addBox(-3.0F, 0.5F, 6.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 25)
						.addBox(-3.0F, 0.5F, 4.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(6.0F, 0.0F, -8.0F));
		PartDefinition a3 = bone2.addOrReplaceChild("a3",
				CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, 0.5F, -1.0F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, 0.0F, -8.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r3 = a3.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(13, 25).addBox(-13.0326F, 0.0F, -8.9338F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(13, 25).addBox(-9.0326F, 0.0F, -3.9338F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 0.5F, 6.0F, 0.0F, -0.6545F, 0.0F));
		PartDefinition a4 = bone2.addOrReplaceChild("a4",
				CubeListBuilder.create().texOffs(0, 25).addBox(-3.0F, 0.5F, 0.0F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, 0.0F, 4.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r4 = a4.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(13, 25).addBox(-11.4459F, 0.0F, 9.1514F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(13, 25)
						.addBox(-7.4459F, 0.0F, 4.1514F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 0.5F, -6.0F, 0.0F, 0.6545F, 0.0F));
		PartDefinition a6 = bone2.addOrReplaceChild("a6",
				CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, 0.5F, 1.0F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 0.0F, -9.0F));
		PartDefinition cube_r5 = a6.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(11, 25).addBox(-2.9341F, 0.0F, -5.9776F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.5F, 7.0F, 0.0F, 0.6545F, 0.0F));
		PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(4.0F, 4.0F, -2.0F));
		PartDefinition cube_r6 = bone3.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(0, 25).addBox(2.5F, 1.5F, -6.0622F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 25)
						.addBox(-4.5F, 1.5F, -6.0622F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, -0.5236F, -1.5708F, 0.0F));
		PartDefinition a5 = bone3.addOrReplaceChild("a5", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.0F));
		PartDefinition cube_r7 = a5.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(11, 25).addBox(-2.9341F, 1.0042F, 4.2263F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.5236F, -0.6545F, 0.0F));
		PartDefinition cube_r8 = a5.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, 1.0F, 4.1962F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.5236F, 0.0F, 0.0F));
		PartDefinition a7 = bone3.addOrReplaceChild("a7", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, -7.0F));
		PartDefinition cube_r9 = a7.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(11, 25).addBox(-2.9341F, 1.0042F, -5.2263F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.5F, 7.0F, -0.5236F, 0.6545F, 0.0F));
		PartDefinition cube_r10 = a7.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, 1.0F, -5.1962F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.5F, 7.0F, -0.5236F, 0.0F, 0.0F));
		PartDefinition a8 = bone3.addOrReplaceChild("a8", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r11 = a8.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -2.0F, -1.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.5236F, 0.0F, 0.0F));
		PartDefinition cube_r12 = a8.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -2.0F, 0.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, -0.5236F, 0.0F, 0.0F));
		PartDefinition cube_r13 = a8.addOrReplaceChild("cube_r13",
				CubeListBuilder.create().texOffs(0, 25).addBox(-3.0F, 1.4415F, 3.9149F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, -1.5708F, 0.6109F));
		PartDefinition a9 = bone3.addOrReplaceChild("a9", CubeListBuilder.create(),
				PartPose.offsetAndRotation(1.0F, -4.0F, -6.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r14 = a9.addOrReplaceChild("cube_r14",
				CubeListBuilder.create().texOffs(0, 25).addBox(-12.0326F, -5.8092F, -8.016F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(0, 25).addBox(-8.0326F, -3.5592F, -3.516F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 0.5F, 6.0F, 0.5236F, -0.6545F, 0.0F));
		PartDefinition a10 = bone3.addOrReplaceChild("a10", CubeListBuilder.create(),
				PartPose.offsetAndRotation(2.0F, -4.0F, 6.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r15 = a10.addOrReplaceChild("cube_r15",
				CubeListBuilder.create().texOffs(0, 25).addBox(-6.4459F, -3.9864F, 3.6012F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(0, 25).addBox(-10.4459F, -6.4864F, 8.1012F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 0.5F, -6.0F, -0.5236F, 0.6545F, 0.0F));
		PartDefinition bone4 = bone2.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));
		PartDefinition a11 = bone4.addOrReplaceChild("a11", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, -2.0F));
		PartDefinition cube_r16 = a11.addOrReplaceChild("cube_r16",
				CubeListBuilder.create().texOffs(7, 22).addBox(-1.9341F, -1.75F, 1.9776F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -4.5F, 0.0F, 0.0F, -0.6545F, 0.0F));
		PartDefinition cube_r17 = a11.addOrReplaceChild("cube_r17",
				CubeListBuilder.create().texOffs(0, 0).addBox(-3.1345F, -2.25F, 2.4649F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -4.5F, 0.0F, 0.0F, -0.3927F, 0.0F));
		PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r18 = bone5.addOrReplaceChild("cube_r18",
				CubeListBuilder.create().texOffs(13, 25).addBox(2.0F, -2.0F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(13, 25)
						.addBox(-4.0F, -2.0F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 19)
						.addBox(-2.0F, 1.0F, -1.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(10, 19)
						.addBox(-2.0F, -2.0F, -1.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-2.0F, -2.25F, 3.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 19).addBox(-2.0F, -2.0F, 2.0F, 4.0F,
								1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -4.5F, -2.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r19 = bone5.addOrReplaceChild("cube_r19",
				CubeListBuilder.create().texOffs(13, 25).addBox(-1.5F, -0.5F, -2.134F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -4.5F, -2.0F, 0.0F, -1.5708F, -0.5236F));
		PartDefinition cube_r20 = bone5.addOrReplaceChild("cube_r20",
				CubeListBuilder.create().texOffs(10, 19).addBox(-2.0F, -1.4021F, -1.1846F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -4.5F, -2.0F, 0.0F, -1.5708F, 0.6545F));
		PartDefinition cube_r21 = bone5.addOrReplaceChild("cube_r21",
				CubeListBuilder.create().texOffs(0, 0).addBox(-0.4887F, -2.25F, -5.1928F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -4.5F, -2.0F, 0.0F, 1.2217F, 0.0F));
		PartDefinition cube_r22 = bone5.addOrReplaceChild("cube_r22",
				CubeListBuilder.create().texOffs(0, 0).addBox(-0.4887F, -2.25F, 3.1928F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -4.5F, -2.0F, 0.0F, -1.2217F, 0.0F));
		PartDefinition a12 = bone4.addOrReplaceChild("a12", CubeListBuilder.create(), PartPose.offset(6.0F, 0.0F, -8.0F));
		PartDefinition a13 = bone4.addOrReplaceChild("a13",
				CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, -6.5F, -4.0F, 7.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, 0.0F, -8.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r23 = a13.addOrReplaceChild("cube_r23",
				CubeListBuilder.create().texOffs(13, 25).addBox(-12.0326F, -2.0F, -9.9338F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(13, 25).addBox(-8.0326F, -2.0F, -4.9338F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -4.5F, 6.0F, 0.0F, -0.6545F, 0.0F));
		PartDefinition a14 = bone4.addOrReplaceChild("a14",
				CubeListBuilder.create().texOffs(0, 25).addBox(-3.0F, -6.5F, 1.0F, 7.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, 0.0F, 4.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r24 = a14.addOrReplaceChild("cube_r24",
				CubeListBuilder.create().texOffs(13, 25).addBox(-10.4459F, -2.0F, 10.1514F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(13, 25).addBox(-6.4459F, -2.0F, 5.1514F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -4.5F, -6.0F, 0.0F, 0.6545F, 0.0F));
		PartDefinition a15 = bone4.addOrReplaceChild("a15", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, -9.0F));
		PartDefinition cube_r25 = a15.addOrReplaceChild("cube_r25",
				CubeListBuilder.create().texOffs(7, 20).addBox(-1.9341F, -1.75F, -4.9776F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -4.5F, 7.0F, 0.0F, 0.6545F, 0.0F));
		PartDefinition cube_r26 = a15.addOrReplaceChild("cube_r26",
				CubeListBuilder.create().texOffs(0, 0).addBox(-3.1345F, -2.25F, -4.4649F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -4.5F, 7.0F, 0.0F, 0.3927F, 0.0F));
		PartDefinition under = bone10.addOrReplaceChild("under", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 0.0F));
		PartDefinition bone7 = under.addOrReplaceChild("bone7", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, 0.0F, 3.1416F, -3.1416F));
		PartDefinition cube_r27 = bone7.addOrReplaceChild("cube_r27",
				CubeListBuilder.create().texOffs(0, 25).addBox(2.5F, -3.2631F, -8.8122F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 25)
						.addBox(-4.5F, -3.2631F, -8.8122F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.5236F, -1.5708F, 0.0F));
		PartDefinition a16 = bone7.addOrReplaceChild("a16", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));
		PartDefinition cube_r28 = a16.addOrReplaceChild("cube_r28",
				CubeListBuilder.create().texOffs(11, 25).addBox(-2.9341F, -3.7589F, 6.9763F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.5236F, -0.6545F, 0.0F));
		PartDefinition cube_r29 = a16.addOrReplaceChild("cube_r29",
				CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, -3.7631F, 6.9462F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.5236F, 0.0F, 0.0F));
		PartDefinition a17 = bone7.addOrReplaceChild("a17", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -7.0F));
		PartDefinition cube_r30 = a17.addOrReplaceChild("cube_r30",
				CubeListBuilder.create().texOffs(11, 25).addBox(-2.9341F, -3.7589F, -7.9763F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.0F, 7.0F, -0.5236F, 0.6545F, 0.0F));
		PartDefinition cube_r31 = a17.addOrReplaceChild("cube_r31",
				CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, -3.7631F, -7.9462F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.0F, 7.0F, -0.5236F, 0.0F, 0.0F));
		PartDefinition a18 = bone7.addOrReplaceChild("a18", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));
		PartDefinition cube_r32 = a18.addOrReplaceChild("cube_r32",
				CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -6.7631F, 1.25F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.5236F, 0.0F, 0.0F));
		PartDefinition cube_r33 = a18.addOrReplaceChild("cube_r33",
				CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -6.7631F, -2.25F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, -0.5236F, 0.0F, 0.0F));
		PartDefinition cube_r34 = a18.addOrReplaceChild("cube_r34",
				CubeListBuilder.create().texOffs(0, 25).addBox(-3.0F, -3.0639F, 7.0696F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, -1.5708F, 0.6109F));
		PartDefinition a19 = bone7.addOrReplaceChild("a19", CubeListBuilder.create(),
				PartPose.offsetAndRotation(1.0F, 1.0F, -6.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r35 = a19.addOrReplaceChild("cube_r35",
				CubeListBuilder.create().texOffs(0, 25).addBox(-12.0326F, -10.5724F, -5.266F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(0, 25).addBox(-8.0326F, -8.3224F, -0.766F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 1.0F, 6.0F, 0.5236F, -0.6545F, 0.0F));
		PartDefinition a20 = bone7.addOrReplaceChild("a20", CubeListBuilder.create(),
				PartPose.offsetAndRotation(2.0F, 1.0F, 6.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r36 = a20.addOrReplaceChild("cube_r36",
				CubeListBuilder.create().texOffs(0, 25).addBox(-6.4459F, -8.7495F, 0.8512F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(0, 25).addBox(-10.4459F, -11.2495F, 5.3512F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 1.0F, -6.0F, -0.5236F, 0.6545F, 0.0F));
		PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offset(-4.0F, 6.0F, 2.0F));
		PartDefinition a21 = bone8.addOrReplaceChild("a21", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, -2.0F));
		PartDefinition cube_r37 = a21.addOrReplaceChild("cube_r37",
				CubeListBuilder.create().texOffs(13, 17).addBox(-1.9341F, -7.25F, 1.9776F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0F, -0.6545F, 0.0F));
		PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r38 = bone9.addOrReplaceChild("cube_r38",
				CubeListBuilder.create().texOffs(13, 25).addBox(2.0F, -7.5F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(13, 25)
						.addBox(-4.0F, -7.5F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 19)
						.addBox(-2.0F, -4.5F, -1.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(10, 19)
						.addBox(-2.0F, -5.5F, -1.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(13, 17)
						.addBox(-2.0F, -7.25F, 2.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(13, 17).addBox(-2.0F, -7.0F, 2.0F, 4.0F,
								1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -4.0F, -2.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r39 = bone9.addOrReplaceChild("cube_r39",
				CubeListBuilder.create().texOffs(13, 25).addBox(-1.5F, -7.2631F, -1.884F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -4.0F, -2.0F, 0.0F, -1.5708F, -0.5236F));
		PartDefinition cube_r40 = bone9.addOrReplaceChild("cube_r40",
				CubeListBuilder.create().texOffs(13, 17).addBox(-0.4887F, -7.25F, -5.1928F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -4.0F, -2.0F, 0.0F, 1.2217F, 0.0F));
		PartDefinition cube_r41 = bone9.addOrReplaceChild("cube_r41",
				CubeListBuilder.create().texOffs(13, 17).addBox(-0.4887F, -7.25F, 3.1928F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -4.0F, -2.0F, 0.0F, -1.2217F, 0.0F));
		PartDefinition a22 = bone8.addOrReplaceChild("a22", CubeListBuilder.create(), PartPose.offset(6.0F, 0.0F, -8.0F));
		PartDefinition a23 = bone8.addOrReplaceChild("a23",
				CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, -11.5F, -4.0F, 7.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, 0.0F, -8.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r42 = a23.addOrReplaceChild("cube_r42",
				CubeListBuilder.create().texOffs(13, 25).addBox(-12.0326F, -7.5F, -9.9338F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(13, 25).addBox(-8.0326F, -7.5F, -4.9338F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -4.0F, 6.0F, 0.0F, -0.6545F, 0.0F));
		PartDefinition a24 = bone8.addOrReplaceChild("a24",
				CubeListBuilder.create().texOffs(0, 25).addBox(-3.0F, -11.5F, 1.0F, 7.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, 0.0F, 4.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r43 = a24.addOrReplaceChild("cube_r43",
				CubeListBuilder.create().texOffs(13, 25).addBox(-10.4459F, -7.5F, 10.1514F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(13, 25).addBox(-6.4459F, -7.5F, 5.1514F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -4.0F, -6.0F, 0.0F, 0.6545F, 0.0F));
		PartDefinition a25 = bone8.addOrReplaceChild("a25", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, -9.0F));
		PartDefinition cube_r44 = a25.addOrReplaceChild("cube_r44",
				CubeListBuilder.create().texOffs(13, 17).addBox(-1.9341F, -7.25F, -4.9776F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -4.0F, 7.0F, 0.0F, 0.6545F, 0.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		bone10.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
