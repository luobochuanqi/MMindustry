package xyz.luobochuanqi.mindustry.client.GUI;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.energy.CapabilityEnergy;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.util.CustomEnergyStorage;
import xyz.luobochuanqi.mindustry.common.world.Block.PowerableBlockEntity;

public class OneEItemSlotScreen extends ContainerScreen<OneEItemSlotContainer> {
    private ResourceLocation texture;
    private OneEItemSlotContainer container;
    private PowerableBlockEntity blockEntity;
    private int energy;

    public OneEItemSlotScreen(OneEItemSlotContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        texture = new ResourceLocation(Utils.ModID, "textures/screens/drill_container.png");
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.container = pMenu;
        this.blockEntity = (PowerableBlockEntity) container.getTileEntity();
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
        super.renderLabels(pMatrixStack, pX, pY);

        blockEntity.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(cap -> {
            energy = ((CustomEnergyStorage) cap).getEnergyStored();
        });
        Utils.LOGGER.info(energy);
        blockEntity.setChanged();
//        container.getTileEntity().getTileData().getCompound("energy");
        Utils.LOGGER.info(container.getTileEntity().getBlockPos());
        Utils.LOGGER.info(blockEntity.getEnergy());
    }
}
