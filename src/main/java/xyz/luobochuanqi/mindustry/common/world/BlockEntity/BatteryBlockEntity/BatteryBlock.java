package xyz.luobochuanqi.mindustry.common.world.BlockEntity.BatteryBlockEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

public class BatteryBlock extends Block {

    private static final Logger LOGGER = LogManager.getLogger();

    public BatteryBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new BatteryBlockEntity();
    }

    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
            BatteryBlockEntity obsidianCounterTileEntity = (BatteryBlockEntity) worldIn.getBlockEntity(pos);
//            int counter = obsidianCounterTileEntity.increase();
            TranslationTextComponent translationTextComponent = new TranslationTextComponent("message.mindustry.cs");
            player.sendMessage(translationTextComponent, player.getUUID());

            //save data
            CompoundNBT nbt = new CompoundNBT();
            nbt.putFloat("px", pos.getX());
            nbt.putFloat("py", pos.getY());
            nbt.putFloat("pz", pos.getZ());
            worldIn.getBlockEntity(pos).save(nbt);
            LOGGER.info(worldIn.getBlockEntity(pos).getTileData().get("px"));
            LOGGER.debug(nbt.getFloat("px"));
        }
        return ActionResultType.SUCCESS;
    }
}
