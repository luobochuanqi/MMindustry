package xyz.luobochuanqi.mindustry.common.world.BlockEntity.Machine.Mechanical_Drill;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.luobochuanqi.mindustry.Utils;

public class MechanicalDrillBlockScreen extends ContainerScreen<MechanicalDrillBlockContainer> {
    private static final Logger LOGGER = LogManager.getLogger();
    private ResourceLocation texture;

    public MechanicalDrillBlockScreen(MechanicalDrillBlockContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 166;
        setGUITexture(new ResourceLocation(Utils.ModID, "textures/screens/drill_container.png"));
    }

    public void setGUITexture(ResourceLocation GUITexture) {
        this.texture = GUITexture;
    }

    @Override
    public void render(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
//        LOGGER.debug("start render");
        this.renderBackground(pMatrixStack);
        super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        this.renderTooltip(pMatrixStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(MatrixStack pMatrixStack, float pPartialTicks, int pX, int pY) {
        this.minecraft.getTextureManager().bind(texture);
        this.blit(pMatrixStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
    }
}
