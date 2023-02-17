package xyz.luobochuanqi.mindustry.client.render.wires;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import xyz.luobochuanqi.mindustry.common.init.TileEntityRegister;

public class WiresRenderer {

    public static void render(RenderWorldLastEvent event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;

        if (player.getMainHandItem().getItem() == Items.NETHER_STAR) {
            locateTileEntities(player, event.getMatrixStack());
        }
    }

    private static void blueLine(IVertexBuilder builder, Matrix4f positionMatrix, BlockPos pos, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
        builder.vertex(positionMatrix, pos.getX()+dx1, pos.getY()+dy1, pos.getZ()+dz1)
                .color(0.0f, 0.0f, 1.0f, 1.0f)
                .endVertex();
        builder.vertex(positionMatrix, pos.getX()+dx2, pos.getY()+dy2, pos.getZ()+dz2)
                .color(0.0f, 0.0f, 1.0f, 1.0f)
                .endVertex();
    }

    private static void powerLine(IVertexBuilder builder, Matrix4f positionMatrix, BlockPos pos1, BlockPos pos2, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
        builder.vertex(positionMatrix, pos1.getX()+dx1, pos1.getY()+dy1, pos1.getZ()+dz1)
                .color(1.0f, 0.0f, 0.0f, 1.0f)
                .endVertex();
        builder.vertex(positionMatrix, pos2.getX()+dx2, pos2.getY()+dy2, pos2.getZ()+dz2)
                .color(1.0f, 0.0f, 0.0f, 1.0f)
                .endVertex();
    }

    private static void locateTileEntities(ClientPlayerEntity player, MatrixStack matrixStack) {
        IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        IVertexBuilder builder = buffer.getBuffer(WiresRenderType.OVERLAY_LINES);

        BlockPos playerPos = player.blockPosition();
        int px = playerPos.getX();
        int py = playerPos.getY();
        int pz = playerPos.getZ();
        World world = player.getCommandSenderWorld();

        matrixStack.pushPose();

        Vector3d projectedView = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        matrixStack.translate(-projectedView.x, -projectedView.y, -projectedView.z);

        Matrix4f matrix = matrixStack.last().pose();

        BlockPos.Mutable pos1 = new BlockPos.Mutable();
        BlockPos.Mutable pos2 = new BlockPos.Mutable();
        for (int dx = -10; dx <= 10; dx++) {
            for (int dy = -10; dy <= 10; dy++) {
                for (int dz = -10; dz <= 10; dz++) {
                    pos1.set(10, 4, 8);
                    pos2.set(px + dx, py + dy, pz + dz);
                    if (world.getBlockEntity(pos1) != null && world.getBlockEntity(pos2) != null
                            && world.getBlockEntity(pos1).getType() == TileEntityRegister.batteryBlockEntity.get()
                            && world.getBlockEntity(pos2).getType() == TileEntityRegister.batteryBlockEntity.get()) {
                        powerLine(builder, matrix, pos1, pos2, 0.5F, 1.1F, 0.5F, 0.5F, 1.1F, 0.5F);

//                        blueLine(builder, matrix, pos, 0, 0, 0, 1, 0, 0);
//                        blueLine(builder, matrix, pos, 0, 1, 0, 1, 1, 0);
//                        blueLine(builder, matrix, pos, 0, 0, 1, 1, 0, 1);
//                        blueLine(builder, matrix, pos, 0, 1, 1, 1, 1, 1);

//                        blueLine(builder, matrix, pos, 0, 0, 0, 0, 0, 1);
//                        blueLine(builder, matrix, pos, 1, 0, 0, 1, 0, 1);
//                        blueLine(builder, matrix, pos, 0, 1, 0, 0, 1, 1);
//                        blueLine(builder, matrix, pos, 1, 1, 0, 1, 1, 1);
//
//                        blueLine(builder, matrix, pos, 0, 0, 0, 0, 1, 0);
//                        blueLine(builder, matrix, pos, 1, 0, 0, 1, 1, 0);
//                        blueLine(builder, matrix, pos, 0, 0, 1, 0, 1, 1);
//                        blueLine(builder, matrix, pos, 1, 0, 1, 1, 1, 1);
                    }
                }
            }
        }

        matrixStack.popPose();

        RenderSystem.disableDepthTest();
        buffer.endBatch(WiresRenderType.OVERLAY_LINES);
    }
}
