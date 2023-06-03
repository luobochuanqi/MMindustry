package xyz.luobochuanqi.mindustry.common.world.Item;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.world.Block.Machine.DrillBlockEntity;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class DrillBlockItem extends BlockItem {
    public DrillBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag)
    {
        if(!Screen.hasShiftDown())
            pTooltip.add(new TranslationTextComponent(Utils.DESC_INFO+"drill.applyTo")
                    .append(" ")
                    .append(new TranslationTextComponent(Utils.DESC_INFO+"holdShift").setStyle(Style.EMPTY.withColor(TextFormatting.GOLD).withBold(true))));
        else
        {
            pTooltip.add(new TranslationTextComponent(Utils.DESC_INFO+"drill.applyTo"));
            Set<Item> array = ((DrillBlockEntity)getBlock().createTileEntity(getBlock().defaultBlockState(), pLevel)).getDrillableItem();
            for(Item item : array) {
                pTooltip.add(new TranslationTextComponent(item.getDescriptionId()).setStyle(Style.EMPTY.withColor(TextFormatting.DARK_GRAY)));
            }
        }
    }
}
