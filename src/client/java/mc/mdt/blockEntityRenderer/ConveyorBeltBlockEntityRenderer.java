package mc.mdt.blockEntityRenderer;

import mc.mdt.common.blockentity.ConveyorBeltBlockEntity;
import mc.mdt.common.blocks.ConveyorBeltBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import org.joml.Vector3f;

import java.util.Objects;

/**
 * @author BillBodkin
 */
@Environment(EnvType.CLIENT)
public class ConveyorBeltBlockEntityRenderer<T extends ConveyorBeltBlockEntity> implements BlockEntityRenderer<T> {
    public ConveyorBeltBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        super();
    }

    @Override
    public void render(ConveyorBeltBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        DefaultedList<ItemStack> beltInv = blockEntity.items;
        for (int i = 0; i < blockEntity.slotActuallyHasItem.length; i++) {
            if (blockEntity.slotActuallyHasItem[i] == 0) {
                beltInv.set(i, ItemStack.EMPTY);
            }
        }

        matrices.push();

        int forwardSlots = 3;
        int forwardSlotsSize = 3;

        for (int i = 0; i < forwardSlots; i++) {
            if (!beltInv.get(i).isEmpty()) {

                float cooldownOffset = getTransferCooldownOffset(blockEntity, i);
                float offset =
                        (
                                i +
                                        cooldownOffset
                        ) /
                                (float) forwardSlotsSize;

                float sidewaysOffset = (float) blockEntity.transferSidewaysOffset[i] / 100f;

                renderBeltItem(blockEntity, matrices, vertexConsumers, light, overlay, beltInv.get(i), offset, sidewaysOffset);
            }
        }
        matrices.pop();
    }

    public float getTransferCooldownOffset(ConveyorBeltBlockEntity blockEntity, int slot) {
        return 1f - ((float) blockEntity.transferCooldownCounter[slot] / (float) blockEntity.transferCooldown);
    }

    public void renderBeltItem(ConveyorBeltBlockEntity blockEntity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, ItemStack itemStack, float offset, float sidewaysOffset) {
        Direction facing = blockEntity.getCachedState().get(ConveyorBeltBlock.FACING);

        // Move the item
        Vector3f translated = new Vector3f();

        boolean hasDepth = Objects.requireNonNull(MinecraftClient.getInstance().getItemRenderer().getModels().getModel(itemStack.getItem())).hasDepth();

        float height = hasDepth ? 0.15f : 0.18f;
        float itemScale = 0.7f;

        Direction slopeDir = null;

        if (facing == Direction.NORTH) {
            translated = new Vector3f(0.5f + sidewaysOffset, height, 1 - offset);
        } else if (facing == Direction.EAST) {
            translated = new Vector3f(offset, height, 0.5f + sidewaysOffset);
        } else if (facing == Direction.SOUTH) {
            translated = new Vector3f(0.5f - sidewaysOffset, height, offset);
        } else if (facing == Direction.WEST) {
            translated = new Vector3f(1 - offset, height, 0.5f - sidewaysOffset);
        }

        matrices.translate(translated.x, translated.y, translated.z);

        if (slopeDir == Direction.NORTH) {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-45));
        } else if (slopeDir == Direction.SOUTH) {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45));
        } else if (slopeDir == Direction.EAST) {
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-45));
        } else if (slopeDir == Direction.WEST) {
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45));
        }

        if (!hasDepth) {
            matrices.translate(0f, 0f, -0.08f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
            matrices.scale(itemScale, itemScale, itemScale);
        }

        MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformationMode.GROUND, light, overlay, matrices, vertexConsumers, MinecraftClient.getInstance().world, 0);

        if (!hasDepth) {
            matrices.scale(1 / itemScale, 1 / itemScale, 1 / itemScale);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90));
            matrices.translate(0f, 0f, 0.08f);
        }

        if (slopeDir == Direction.NORTH) {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45));
        } else if (slopeDir == Direction.SOUTH) {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-45));
        } else if (slopeDir == Direction.EAST) {
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45));
        } else if (slopeDir == Direction.WEST) {
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-45));
        }

        matrices.translate(-translated.x, -translated.y, -translated.z);
    }
}