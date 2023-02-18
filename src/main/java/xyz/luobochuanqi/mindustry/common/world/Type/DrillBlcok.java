//package xyz.luobochuanqi.mindustry.common.world.Type;
//
//import net.minecraft.block.*;
//import net.minecraft.block.material.PushReaction;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.merchant.villager.VillagerEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.BlockItemUseContext;
//import net.minecraft.item.DyeColor;
//import net.minecraft.item.ItemStack;
//import net.minecraft.pathfinding.PathType;
//import net.minecraft.state.BooleanProperty;
//import net.minecraft.state.EnumProperty;
//import net.minecraft.state.StateContainer;
//import net.minecraft.state.properties.BedPart;
//import net.minecraft.state.properties.BlockStateProperties;
//import net.minecraft.tileentity.BedTileEntity;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.tileentity.TileEntityMerger;
//import net.minecraft.util.*;
//import net.minecraft.util.math.AxisAlignedBB;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.BlockRayTraceResult;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.util.math.shapes.ISelectionContext;
//import net.minecraft.util.math.shapes.VoxelShape;
//import net.minecraft.util.math.shapes.VoxelShapes;
//import net.minecraft.util.math.vector.Vector3d;
//import net.minecraft.util.text.TranslationTextComponent;
//import net.minecraft.world.*;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import org.apache.commons.lang3.ArrayUtils;
//
//import javax.annotation.Nullable;
//import java.util.List;
//import java.util.Optional;
//
//public class DrillBlcok extends Block implements ITileEntityProvider {
//    public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;
//    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
//    protected static final VoxelShape BASE = Block.box(0.0D, 3.0D, 0.0D, 16.0D, 9.0D, 16.0D);
//    protected static final VoxelShape LEG_NORTH_WEST = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 3.0D, 3.0D);
//    protected static final VoxelShape LEG_SOUTH_WEST = Block.box(0.0D, 0.0D, 13.0D, 3.0D, 3.0D, 16.0D);
//    protected static final VoxelShape LEG_NORTH_EAST = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 3.0D, 3.0D);
//    protected static final VoxelShape LEG_SOUTH_EAST = Block.box(13.0D, 0.0D, 13.0D, 16.0D, 3.0D, 16.0D);
//    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(BASE, LEG_NORTH_WEST, LEG_NORTH_EAST);
//    protected static final VoxelShape SOUTH_SHAPE = VoxelShapes.or(BASE, LEG_SOUTH_WEST, LEG_SOUTH_EAST);
//    protected static final VoxelShape WEST_SHAPE = VoxelShapes.or(BASE, LEG_NORTH_WEST, LEG_SOUTH_WEST);
//    protected static final VoxelShape EAST_SHAPE = VoxelShapes.or(BASE, LEG_NORTH_EAST, LEG_SOUTH_EAST);
//    private final DyeColor color;
//
//    public DrillBlcok(DyeColor pColor, AbstractBlock.Properties pProperties) {
//        super(pProperties);
//        this.color = pColor;
//        this.registerDefaultState(this.stateDefinition.any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, Boolean.valueOf(false)));
//    }
//
//    @Nullable
//    @OnlyIn(Dist.CLIENT)
//    public static Direction getBedOrientation(IBlockReader pLevel, BlockPos pPos) {
//        BlockState blockstate = pLevel.getBlockState(pPos);
//        return blockstate.getBlock() instanceof BedBlock ? blockstate.getValue(FACING) : null;
//    }
//
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
//
//            if (!canSetSpawn(pLevel)) {
//                pLevel.removeBlock(pPos, false);
//                BlockPos blockpos = pPos.relative(pState.getValue(FACING).getOpposite());
//                if (pLevel.getBlockState(blockpos).is(this)) {
//                    pLevel.removeBlock(blockpos, false);
//                }
//
//                pLevel.explode((Entity)null, DamageSource.badRespawnPointExplosion(), (ExplosionContext)null, (double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, 5.0F, true, Explosion.Mode.DESTROY);
//                return ActionResultType.SUCCESS;
//            } else if (pState.getValue(OCCUPIED)) {
//                if (!this.kickVillagerOutOfBed(pLevel, pPos)) {
//                    pPlayer.displayClientMessage(new TranslationTextComponent("block.minecraft.bed.occupied"), true);
//                }
//
//                return ActionResultType.SUCCESS;
//            } else {
//                pPlayer.startSleepInBed(pPos).ifLeft((p_220173_1_) -> {
//                    if (p_220173_1_ != null) {
//                        pPlayer.displayClientMessage(p_220173_1_.getMessage(), true);
//                    }
//
//                });
//                return ActionResultType.SUCCESS;
//            }
//        }
//    }
//
//    public static boolean canSetSpawn(World pLevel) {
//        return pLevel.dimensionType().bedWorks();
//    }
//
//    private boolean kickVillagerOutOfBed(World pLevel, BlockPos pPos) {
//        List<VillagerEntity> list = pLevel.getEntitiesOfClass(VillagerEntity.class, new AxisAlignedBB(pPos), LivingEntity::isSleeping);
//        if (list.isEmpty()) {
//            return false;
//        } else {
//            list.get(0).stopSleeping();
//            return true;
//        }
//    }
//
//    public void fallOn(World p_180658_1_, BlockPos p_180658_2_, Entity p_180658_3_, float p_180658_4_) {
//        super.fallOn(p_180658_1_, p_180658_2_, p_180658_3_, p_180658_4_ * 0.5F);
//    }
//
//    /**
//     * Called when an Entity lands on this Block. This method *must* update motionY because the entity will not do that
//     * on its own
//     */
//    public void updateEntityAfterFallOn(IBlockReader pLevel, Entity pEntity) {
//        if (pEntity.isSuppressingBounce()) {
//            super.updateEntityAfterFallOn(pLevel, pEntity);
//        } else {
//            this.bounceUp(pEntity);
//        }
//
//    }
//
//    private void bounceUp(Entity pEntity) {
//        Vector3d vector3d = pEntity.getDeltaMovement();
//        if (vector3d.y < 0.0D) {
//            double d0 = pEntity instanceof LivingEntity ? 1.0D : 0.8D;
//            pEntity.setDeltaMovement(vector3d.x, -vector3d.y * (double)0.66F * d0, vector3d.z);
//        }
//
//    }
//
//    /**
//     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
//     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
//     * returns its solidified counterpart.
//     * Note that this method should ideally consider only the specific face passed in.
//     */
//    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, IWorld pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
//        if (pFacing == getNeighbourDirection(pState.getValue(PART), pState.getValue(FACING))) {
//            return pFacingState.is(this) && pFacingState.getValue(PART) != pState.getValue(PART) ? pState.setValue(OCCUPIED, pFacingState.getValue(OCCUPIED)) : Blocks.AIR.defaultBlockState();
//        } else {
//            return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
//        }
//    }
//
//    /**
//     * Given a bed part and the direction it's facing, find the direction to move to get the other bed part
//     */
//    private static Direction getNeighbourDirection(BedPart pPart, Direction pDirection) {
//        return pPart == BedPart.FOOT ? pDirection : pDirection.getOpposite();
//    }
//
//    /**
//     * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
//     * this block
//     */
//    public void playerWillDestroy(World pLevel, BlockPos pPos, BlockState pState, PlayerEntity pPlayer) {
//        if (!pLevel.isClientSide && pPlayer.isCreative()) {
//            BedPart bedpart = pState.getValue(PART);
//            if (bedpart == BedPart.FOOT) {
//                BlockPos blockpos = pPos.relative(getNeighbourDirection(bedpart, pState.getValue(FACING)));
//                BlockState blockstate = pLevel.getBlockState(blockpos);
//                if (blockstate.getBlock() == this && blockstate.getValue(PART) == BedPart.HEAD) {
//                    pLevel.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
//                    pLevel.levelEvent(pPlayer, 2001, blockpos, Block.getId(blockstate));
//                }
//            }
//        }
//
//        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
//    }
//
//    @Nullable
//    public BlockState getStateForPlacement(BlockItemUseContext pContext) {
//        Direction direction = pContext.getHorizontalDirection();
//        BlockPos blockpos = pContext.getClickedPos();
//        BlockPos blockpos1 = blockpos.relative(direction);
//        return pContext.getLevel().getBlockState(blockpos1).canBeReplaced(pContext) ? this.defaultBlockState().setValue(FACING, direction) : null;
//    }
//
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
//
//    public static Direction getConnectedDirection(BlockState pState) {
//        Direction direction = pState.getValue(FACING);
//        return pState.getValue(PART) == BedPart.HEAD ? direction.getOpposite() : direction;
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public static TileEntityMerger.Type getBlockType(BlockState pState) {
//        BedPart bedpart = pState.getValue(PART);
//        return bedpart == BedPart.HEAD ? TileEntityMerger.Type.FIRST : TileEntityMerger.Type.SECOND;
//    }
//
//    private static boolean isBunkBed(IBlockReader pLevel, BlockPos pPos) {
//        return pLevel.getBlockState(pPos.below()).getBlock() instanceof BedBlock;
//    }
//
//    public static Optional<Vector3d> findStandUpPosition(EntityType<?> pEntityType, ICollisionReader pLevel, BlockPos pPos, float p_242652_3_) {
//        Direction direction = pLevel.getBlockState(pPos).getValue(FACING);
//        Direction direction1 = direction.getClockWise();
//        Direction direction2 = direction1.isFacingAngle(p_242652_3_) ? direction1.getOpposite() : direction1;
//        if (isBunkBed(pLevel, pPos)) {
//            return findBunkBedStandUpPosition(pEntityType, pLevel, pPos, direction, direction2);
//        } else {
//            int[][] aint = bedStandUpOffsets(direction, direction2);
//            Optional<Vector3d> optional = findStandUpPositionAtOffset(pEntityType, pLevel, pPos, aint, true);
//            return optional.isPresent() ? optional : findStandUpPositionAtOffset(pEntityType, pLevel, pPos, aint, false);
//        }
//    }
//
//    private static Optional<Vector3d> findBunkBedStandUpPosition(EntityType<?> p_242653_0_, ICollisionReader p_242653_1_, BlockPos p_242653_2_, Direction p_242653_3_, Direction p_242653_4_) {
//        int[][] aint = bedSurroundStandUpOffsets(p_242653_3_, p_242653_4_);
//        Optional<Vector3d> optional = findStandUpPositionAtOffset(p_242653_0_, p_242653_1_, p_242653_2_, aint, true);
//        if (optional.isPresent()) {
//            return optional;
//        } else {
//            BlockPos blockpos = p_242653_2_.below();
//            Optional<Vector3d> optional1 = findStandUpPositionAtOffset(p_242653_0_, p_242653_1_, blockpos, aint, true);
//            if (optional1.isPresent()) {
//                return optional1;
//            } else {
//                int[][] aint1 = bedAboveStandUpOffsets(p_242653_3_);
//                Optional<Vector3d> optional2 = findStandUpPositionAtOffset(p_242653_0_, p_242653_1_, p_242653_2_, aint1, true);
//                if (optional2.isPresent()) {
//                    return optional2;
//                } else {
//                    Optional<Vector3d> optional3 = findStandUpPositionAtOffset(p_242653_0_, p_242653_1_, p_242653_2_, aint, false);
//                    if (optional3.isPresent()) {
//                        return optional3;
//                    } else {
//                        Optional<Vector3d> optional4 = findStandUpPositionAtOffset(p_242653_0_, p_242653_1_, blockpos, aint, false);
//                        return optional4.isPresent() ? optional4 : findStandUpPositionAtOffset(p_242653_0_, p_242653_1_, p_242653_2_, aint1, false);
//                    }
//                }
//            }
//        }
//    }
//
//    private static Optional<Vector3d> findStandUpPositionAtOffset(EntityType<?> p_242654_0_, ICollisionReader p_242654_1_, BlockPos p_242654_2_, int[][] p_242654_3_, boolean p_242654_4_) {
//        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
//
//        for(int[] aint : p_242654_3_) {
//            blockpos$mutable.set(p_242654_2_.getX() + aint[0], p_242654_2_.getY(), p_242654_2_.getZ() + aint[1]);
//            Vector3d vector3d = TransportationHelper.findSafeDismountLocation(p_242654_0_, p_242654_1_, blockpos$mutable, p_242654_4_);
//            if (vector3d != null) {
//                return Optional.of(vector3d);
//            }
//        }
//
//        return Optional.empty();
//    }
//
//    /**
//     * @deprecated call via {@link IBlockState#getMobilityFlag()} whenever possible. Implementing/overriding is fine.
//     */
//    public PushReaction getPistonPushReaction(BlockState pState) {
//        return PushReaction.DESTROY;
//    }
//
//    /**
//     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
//     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
//     * @deprecated call via {@link IBlockState#getRenderType()} whenever possible. Implementing/overriding is fine.
//     */
//    public BlockRenderType getRenderShape(BlockState pState) {
//        return BlockRenderType.ENTITYBLOCK_ANIMATED;
//    }
//
//    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
//        pBuilder.add(FACING, PART, OCCUPIED);
//    }
//
//    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
//        return new BedTileEntity(this.color);
//    }
//
//    /**
//     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
//     */
//    public void setPlacedBy(World pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
//        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
//        if (!pLevel.isClientSide) {
//            BlockPos blockpos = pPos.relative(pState.getValue(FACING));
//            pLevel.setBlock(blockpos, pState.setValue(PART, BedPart.HEAD), 3);
//            pLevel.blockUpdated(pPos, Blocks.AIR);
//            pState.updateNeighbourShapes(pLevel, pPos, 3);
//        }
//
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public DyeColor getColor() {
//        return this.color;
//    }
//
//    /**
//     * Return a random long to be passed to {@link IBakedModel#getQuads}, used for random model rotations
//     */
//    @OnlyIn(Dist.CLIENT)
//    public long getSeed(BlockState pState, BlockPos pPos) {
//        BlockPos blockpos = pPos.relative(pState.getValue(FACING), pState.getValue(PART) == BedPart.HEAD ? 0 : 1);
//        return MathHelper.getSeed(blockpos.getX(), pPos.getY(), blockpos.getZ());
//    }
//
//    public boolean isPathfindable(BlockState pState, IBlockReader pLevel, BlockPos pPos, PathType pType) {
//        return false;
//    }
//
//    private static int[][] bedStandUpOffsets(Direction p_242656_0_, Direction p_242656_1_) {
//        return ArrayUtils.addAll((int[][])bedSurroundStandUpOffsets(p_242656_0_, p_242656_1_), (int[][])bedAboveStandUpOffsets(p_242656_0_));
//    }
//
//    private static int[][] bedSurroundStandUpOffsets(Direction p_242658_0_, Direction p_242658_1_) {
//        return new int[][]{{p_242658_1_.getStepX(), p_242658_1_.getStepZ()}, {p_242658_1_.getStepX() - p_242658_0_.getStepX(), p_242658_1_.getStepZ() - p_242658_0_.getStepZ()}, {p_242658_1_.getStepX() - p_242658_0_.getStepX() * 2, p_242658_1_.getStepZ() - p_242658_0_.getStepZ() * 2}, {-p_242658_0_.getStepX() * 2, -p_242658_0_.getStepZ() * 2}, {-p_242658_1_.getStepX() - p_242658_0_.getStepX() * 2, -p_242658_1_.getStepZ() - p_242658_0_.getStepZ() * 2}, {-p_242658_1_.getStepX() - p_242658_0_.getStepX(), -p_242658_1_.getStepZ() - p_242658_0_.getStepZ()}, {-p_242658_1_.getStepX(), -p_242658_1_.getStepZ()}, {-p_242658_1_.getStepX() + p_242658_0_.getStepX(), -p_242658_1_.getStepZ() + p_242658_0_.getStepZ()}, {p_242658_0_.getStepX(), p_242658_0_.getStepZ()}, {p_242658_1_.getStepX() + p_242658_0_.getStepX(), p_242658_1_.getStepZ() + p_242658_0_.getStepZ()}};
//    }
//
//    private static int[][] bedAboveStandUpOffsets(Direction p_242655_0_) {
//        return new int[][]{{0, 0}, {-p_242655_0_.getStepX(), -p_242655_0_.getStepZ()}};
//    }
//}
