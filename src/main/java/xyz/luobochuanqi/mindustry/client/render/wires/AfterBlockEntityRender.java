package xyz.luobochuanqi.mindustry.client.render.wires;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;

public class AfterBlockEntityRender {

    public static void render(RenderLivingEvent.Post event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;

        if (player.getMainHandItem().getItem() == Items.GHAST_TEAR) {
            showMobs(event.getMatrixStack(), event.getBuffers(), event.getEntity());
        }
    }

    private static void greenLine(IVertexBuilder builder, Matrix4f positionMatrix, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
        builder.vertex(positionMatrix, dx1, dy1, dz1)
                .color(0.0f, 1.0f, 0.0f, 1.0f)
                .endVertex();
        builder.vertex(positionMatrix, dx2, dy2, dz2)
                .color(0.0f, 1.0f, 0.0f, 1.0f)
                .endVertex();
    }

    private static void Line(IVertexBuilder builder, Matrix4f positionMatrix, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
        builder.vertex(positionMatrix, dx1, dy1, dz1)
                .color(1.0f, 0.0f, 0.0f, 1.0f)
                .endVertex();
        builder.vertex(positionMatrix, dx2, dy2, dz2)
                .color(1.0f, 0.0f, 0.0f, 1.0f)
                .endVertex();
    }


    private static void showMobs(MatrixStack matrixStack, IRenderTypeBuffer buffer, LivingEntity pBlockPos) {
        IVertexBuilder builder = buffer.getBuffer(WiresRenderType.OVERLAY_LINES);

        Matrix4f positionMatrix = matrixStack.last().pose();

        Line(builder, positionMatrix, 0, .5f, 0, 0, 6, 0);
    }
}
