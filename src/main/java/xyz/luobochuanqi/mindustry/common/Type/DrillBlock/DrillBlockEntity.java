package xyz.luobochuanqi.mindustry.common.Type.DrillBlock;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.init.ItemRegister;
import xyz.luobochuanqi.mindustry.common.util.Mod2x2Part;

import java.util.HashSet;
import java.util.Set;

public class DrillBlockEntity extends TileEntity implements ITickableTileEntity {
    public static final EnumProperty<Mod2x2Part> ModPART = Utils.Mod2x2PART;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public DrillBlockEntity(TileEntityType<?> pType) {
        super(pType);
    }

    /**
     * Get the pos of each child-block
     * */
    public BlockPos[] getBlockPoses(BlockPos pPos, BlockState pState) {
        Direction direction = pState.getValue(FACING);
        BlockPos[] blockPoses = new BlockPos[5];
        BlockPos nextBlockpos = pPos;
        // Get pos of each child-block clockwise
        for (int i = 0; i < 3; i++) {
            nextBlockpos = nextBlockpos.relative(direction);
            blockPoses[i] = nextBlockpos;
            direction = direction.getClockWise();
        }
        return blockPoses;
    }

    public BlockPos getMainBlockPos() {
        BlockPos[] pBlockPoses = getBlockPoses(this.getBlockPos(), this.getBlockState());
        BlockPos MainBlockPos = worldPosition;
        for (int i = 0; i < 3; i++) {
            if (this.level.getBlockState(pBlockPoses[i]).getValue(ModPART) == Mod2x2Part.START) {
                MainBlockPos = pBlockPoses[i];
            }
        }
        return MainBlockPos;
    }

    @Override
    public void tick() {

    }

    /**
     * Return the Set of minerals that can be mined
     * */
    public Set<Item> getDrillables() {
        Set<Item> ItemSet = new HashSet<>();
        ItemSet.add(ItemRegister.sand.get());
        return ItemSet;
    }

    public double getBaseDrillSpeed() {
        return 0.4;
    }
}
