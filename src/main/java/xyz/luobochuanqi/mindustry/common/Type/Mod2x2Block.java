package xyz.luobochuanqi.mindustry.common.Type;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.util.Mod2x2Part;

import javax.annotation.Nullable;

public abstract class Mod2x2Block extends HorizontalBlock {
    public static final EnumProperty<Mod2x2Part> ModPART = Utils.Mod2x2PART;
    public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;
    public BlockPos MainBlockPos;

    protected Mod2x2Block(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ModPART, Mod2x2Part.START).setValue(ENABLED, true));
    }

    /**
     * Get the pos of each child-block
     * */
    public static BlockPos[] getBlockPoses(BlockPos pPos, BlockState pState) {
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

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, ModPART, ENABLED);
    }

    /**
     * Make sure the placement is clear
     */
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext pContext) {
        Direction direction = pContext.getHorizontalDirection();
        BlockPos blockPos = pContext.getClickedPos();
        boolean flag = true;
        for (int i = 1; i <= 3; i++) {
            blockPos = blockPos.relative(direction);
            if (!pContext.getLevel().getBlockState(blockPos).canBeReplaced(pContext)) {
                flag = false;
            }
            direction = direction.getClockWise();
        }
        if (flag) {
            return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
        }
        return null;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(World pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (!pLevel.isClientSide) {
            BlockPos nextblockpos = pPos;
            BlockState nextState = pState;
            this.MainBlockPos = pPos;
            // Place the child-blocks clockwise
            for (int i = 1; i <= 3; i++) {
                nextblockpos = nextblockpos.relative(nextState.getValue(FACING));
                pLevel.setBlock(nextblockpos, nextState.setValue(ModPART, Mod2x2Part.valueOf(i)).rotate(Rotation.CLOCKWISE_90), 3);
                nextState = pLevel.getBlockState(nextblockpos);
            }
            pLevel.blockUpdated(pPos, Blocks.AIR);
            pState.updateNeighbourShapes(pLevel, pPos, 3);
        }
    }

    /**
     * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
     * this block
     */
    public void playerWillDestroy(World pLevel, BlockPos pPos, BlockState pState, PlayerEntity pPlayer) {
        if (!pLevel.isClientSide) {
            BlockPos[] blockPoses = getBlockPoses(pPos, pState);
            for (int i = 0; i < 3; i++) {
                BlockState blockstate = pLevel.getBlockState(blockPoses[i]);
                if (blockstate.getBlock() == this) {
                    pLevel.setBlock(blockPoses[i], Blocks.AIR.defaultBlockState(), 35);
                    pLevel.levelEvent(pPlayer, 2001, blockPoses[i], Block.getId(blockstate));
                }
            }
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }
}
