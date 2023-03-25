package xyz.luobochuanqi.mindustry.client.GUI;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.energy.CapabilityEnergy;
import xyz.luobochuanqi.mindustry.Utils;

public class OneEItemSlotScreen extends ContainerScreen<OneEItemSlotContainer> {
    private ResourceLocation texture;
    private OneEItemSlotContainer container;

    public OneEItemSlotScreen(OneEItemSlotContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        texture = new ResourceLocation(Utils.ModID, "textures/screens/drill_container.png");
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.container = pMenu;
        setGUITexture(texture);
    }

    public void setGUITexture(ResourceLocation GUITexture) {
        this.texture = GUITexture;
    }

    @Override
    public void render(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        this.renderBackground(pMatrixStack);
        super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        this.renderTooltip(pMatrixStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(MatrixStack pMatrixStack, float pPartialTicks, int pX, int pY) {
        this.minecraft.getTextureManager().bind(texture);
        this.blit(pMatrixStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(MatrixStack pMatrixStack, int pX, int pY) {
        container.getTileEntity().getCapability(CapabilityEnergy.ENERGY, null).ifPresent(cap -> {
            this.font.draw(pMatrixStack, String.valueOf(cap.getEnergyStored()), 15, 16, -52225);
        });
    }
}
