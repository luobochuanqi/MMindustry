package xyz.luobochuanqi.mindustry.common.Type.DrillBlock;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class DrillBlockScreen extends ContainerScreen<DrillBlockContainer> {
    public DrillBlockScreen(DrillBlockContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    private static final ResourceLocation texture =
            new ResourceLocation("mindustry:textures/screens/drill_container.png");

    @Override
    public void render(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        this.renderBackground(pMatrixStack);
        super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        this.renderTooltip(pMatrixStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(MatrixStack pMatrixStack, float pPartialTicks, int pX, int pY) {
        GlStateManager._color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.blit(pMatrixStack, this.leftPos, this.topPos, 0, 0, this.imageHeight, this.imageWidth);
        this.minecraft.getTextureManager().bind(texture);
    }
}
