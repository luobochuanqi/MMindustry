//package xyz.luobochuanqi.mindustry.common.world.Type;
//
//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.mojang.blaze3d.systems.RenderSystem;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.screen.Screen;
//import net.minecraft.client.gui.widget.TextFieldWidget;
//import net.minecraft.client.gui.widget.Widget;
//import net.minecraft.client.gui.widget.button.Button;
//import net.minecraft.client.settings.SliderPercentageOption;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.TranslationTextComponent;
//import xyz.luobochuanqi.mindustry.Utils;
//
//public class DrillBlockGUI extends Screen {
//    private ResourceLocation GUI = new ResourceLocation(Utils.ModID, "textures/gui/firstblock_gui.png");
//
//    protected DrillBlockGUI(ITextComponent pTitle) {
//        super(pTitle);
//    }
//
////    public DrillBlockGUI(DrillBlockContainer container, PlayerInventory inv, ITextComponent name) {
////        super(container, inv, name);
////    }
//
//    @Override
//    public void render(int mouseX, int mouseY, float partialTicks) {
//        this.renderBackground();
//        super.render(mouseX, mouseY, partialTicks);
//        this.renderHoveredToolTip(mouseX, mouseY);
//    }
//
//    @Override
//    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
//    }
//
//    @Override
//    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
//        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
//        this.minecraft.getTextureManager().bindTexture(GUI);
//        int relX = (this.width - this.xSize) / 2;
//        int relY = (this.height - this.ySize) / 2;
//        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
//    }
//}
