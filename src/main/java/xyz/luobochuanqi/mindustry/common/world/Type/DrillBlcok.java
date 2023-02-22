package xyz.luobochuanqi.mindustry.common.world.Type;

import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.util.Mod2x2Part;

import javax.annotation.Nullable;

public class DrillBlcok extends HorizontalBlock {
    public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;
    public static final EnumProperty<Mod2x2Part> ModPART = Utils.Mod2x2PART;

    public DrillBlcok(AbstractBlock.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ModPART, Mod2x2Part.START));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DrillBlcokEntity();
    }

//    public ActionResultType use(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockRayTraceResult pHit) {
//        if (pLevel.isClientSide) {
//            return ActionResultType.CONSUME;
//        } else {
//            if (pState.getValue(PART) != BedPart.HEAD) {
//                pPos = pPos.relative(pState.getValue(FACING));
//                pState = pLevel.getBlockState(pPos);
//                if (!pState.is(this)) {
//                    return ActionResultType.CONSUME;
//                }
//            }
//        }
//        return ActionResultType.SUCCESS;
//    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     * 在给定提供的面向邻居和邻居状态的情况下更新提供的状态，返回新状态。
     * 例如，如果可能的话，围栏与通过状态连接，湿混凝土粉末立即返回其凝固的对应物。
     * 请注意，理想情况下，此方法应仅考虑传入的特定人脸。
     */

//    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, IWorld pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
//        if (pFacing == getNeighbourDirection(pState.getValue(PART), pState.getValue(FACING))) {
//            if (pFacingState.is(this) && pFacingState.getValue(PART) != pState.getValue(PART)) {
////                return pState.setValue(OCCUPIED, pFacingState.getValue(OCCUPIED));
//                return pState.getValue()
//            }
//            return Blocks.AIR.defaultBlockState();
////            return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
//        } else {
//            return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
//        }
//    }

//    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, IWorld pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
//        if (pFacingState.getValue(PART) != pState.getValue(PART)) {
//            return Blocks.AIR.defaultBlockState();
//        } else {
//            return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
//        }
//    }

    /**
     * Given a bed part and the direction it's facing, find the direction to move to get the other bed part
     */
    private static Direction getNeighbourDirection(BedPart pPart, Direction pDirection) {
        if (pPart == BedPart.FOOT) {
            return pDirection;
        }
        return pDirection.getOpposite();
    }

    /**
     * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
     * this block
     */
    public void playerWillDestroy(World pLevel, BlockPos pPos, BlockState pState, PlayerEntity pPlayer) {
        if (!pLevel.isClientSide) {
            BedPart bedpart = pState.getValue(PART);
            if (bedpart == BedPart.FOOT) {
                BlockPos blockpos = pPos.relative(getNeighbourDirection(bedpart, pState.getValue(FACING)));
                BlockState blockstate = pLevel.getBlockState(blockpos);
                if (blockstate.getBlock() == this && blockstate.getValue(PART) == BedPart.HEAD) {
                    pLevel.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                    pLevel.levelEvent(pPlayer, 2001, blockpos, Block.getId(blockstate));
                }
            }
            else if (bedpart == BedPart.HEAD) {
                BlockPos blockpos = pPos.relative(getNeighbourDirection(bedpart, pState.getValue(FACING)));
                BlockState blockstate = pLevel.getBlockState(blockpos);
                if (blockstate.getBlock() == this && blockstate.getValue(PART) == BedPart.FOOT) {
                    pLevel.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                    pLevel.levelEvent(pPlayer, 2001, blockpos, Block.getId(blockstate));
                }
            }
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext pContext) {
        Direction direction = pContext.getHorizontalDirection();
        BlockPos blockpos = pContext.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(direction);
        return pContext.getLevel().getBlockState(blockpos1).canBeReplaced(pContext) ? this.defaultBlockState().setValue(FACING, direction) : null;
    }

//    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
//        Direction direction = getConnectedDirection(pState).getOpposite();
//        switch(direction) {
//            case NORTH:
//                return NORTH_SHAPE;
//            case SOUTH:
//                return SOUTH_SHAPE;
//            case WEST:
//                return WEST_SHAPE;
//            default:
//                return EAST_SHAPE;
//        }
//    }

    /**
     * @deprecated call via {@link IBlockState#getMobilityFlag()} whenever possible. Implementing/overriding is fine.
     */
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.IGNORE;
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     * @deprecated call via {@link IBlockState#getRenderType()} whenever possible. Implementing/overriding is fine.
     */
    public BlockRenderType getRenderShape(BlockState pState) {
        return BlockRenderType.MODEL;
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, ModPART, PART);
    }

//    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
//        return new DrillBlcokEntity();
//    }

//    public BlockPos relative(Direction pDirection) {
//        return new BlockPos(this.getX() + pDirection.getStepX(), this.getY() + pDirection.getStepY(), this.getZ() + pDirection.getStepZ());
//    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(World pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (!pLevel.isClientSide) {
            BlockPos blockpos = pPos.relative(pState.getValue(FACING));
            BlockState nextState;
            pLevel.setBlock(blockpos, pState.setValue(ModPART, Mod2x2Part.FRONT).rotate(Rotation.CLOCKWISE_90), 3);

            nextState = pLevel.getBlockState(blockpos);
            blockpos = blockpos.relative(nextState.getValue(FACING));
            pLevel.setBlock(blockpos, nextState.setValue(ModPART, Mod2x2Part.CORNER).rotate(Rotation.CLOCKWISE_90), 3);

            nextState = pLevel.getBlockState(blockpos);
            blockpos = blockpos.relative(nextState.getValue(FACING));
            pLevel.setBlock(blockpos, nextState.setValue(ModPART, Mod2x2Part.RIGHT).rotate(Rotation.CLOCKWISE_90), 3);

            pLevel.blockUpdated(pPos, Blocks.AIR);
            pState.updateNeighbourShapes(pLevel, pPos, 3);

//            for(int i=0;i<3;i++){ BlockPos blockpos=pPos.relative(pState.getValue(FACING)); BlockState nextState; pLevel.setBlock(blockpos,pState.setValue(ModPART,Mod2x2Part.FRONT).rotate(Rotation.CLOCKWISE_90),3); nextState=pLevel.getBlockState(blockpos); blockpos=blockpos.relative(nextState.getValue(FACING)); pLevel.setBlock(blockpos,nextState.setValue(ModPART,Mod2x2Part[i]).rotate(Rotation.CLOCKWISE_90),3); nextState=pLevel.getBlockState(blockpos); blockpos=blockpos.relative(nextState.getValue(FACING)); } pLevel.blockUpdated(pPos,Blocks.AIR); pState.updateNeighbourShapes(pLevel,pPos,3);
        }
    }

    /**
     * Return a random long to be passed to {@link IBakedModel#getQuads}, used for random model rotations
     */
    @OnlyIn(Dist.CLIENT)
    public long getSeed(BlockState pState, BlockPos pPos) {
        BlockPos blockpos = pPos.relative(pState.getValue(FACING), pState.getValue(PART) == BedPart.HEAD ? 0 : 1);
        return MathHelper.getSeed(blockpos.getX(), pPos.getY(), blockpos.getZ());
    }
}
