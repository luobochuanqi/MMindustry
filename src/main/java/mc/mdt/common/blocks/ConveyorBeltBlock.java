package mc.mdt.common.blocks;

import mc.mdt.MMindustry;
import mc.mdt.Util;
import mc.mdt.common.blockItem.ConveyorBeltBockItem;
import mc.mdt.common.blockentity.ConveyorBeltBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * @author BillBodkin
 */
public class ConveyorBeltBlock extends BlockWithEntity {

    // TODO No enabled state in mindustry.
    public static final BooleanProperty ENABLED;
    public static final DirectionProperty FACING;

    static {
        ENABLED = Properties.ENABLED;
        FACING = Properties.HORIZONTAL_FACING;
    }

    public ConveyorBeltBlock(Settings settings, boolean skipSetDefaultState) {
        super(settings);

        if(!skipSetDefaultState){
            return;
        }
        this.setDefaultState(
                this.stateManager.getDefaultState()
//                        .with(ENABLED, true)
                        .with(FACING, Direction.NORTH)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(
//                ENABLED,
                FACING
        );
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction side = ctx.getSide();
        Direction facing = ctx.getHorizontalPlayerFacing();
        PlayerEntity player = ctx.getPlayer();

        if(player == null){
            return this.getDefaultState();
        }

        return this.getDefaultState()
//                .with(ENABLED, !ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()))
                .with(FACING, facing)
        ;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 0.2f, 1.0f);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ConveyorBeltBlockEntity conveyorBeltBlockEntity) {
                // Replace with the below so does not drop filter items
                //ItemScatterer.spawn(world, pos, (ConveyorBeltBlockEntity)blockEntity);
                for(int i = 0; i < conveyorBeltBlockEntity.size(); ++i) {
                    ItemStack stack = conveyorBeltBlockEntity.getStack(i);

                    NbtCompound stackNbt = stack.getOrCreateNbt();
                    if(!stackNbt.getBoolean("IsFilterItem")){
                        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                    }
                }
                // update comparators
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof ConveyorBeltBockItem || player.getStackInHand(Hand.OFF_HAND).getItem() instanceof ConveyorBeltBockItem){
            // Holding a belt
            player.sendMessage(Text.translatable(Util.TEXT_MESSAGE + MMindustry.MOD_ID + "belt_place_on_belt.message").formatted(Formatting.GRAY), true);
            // Allow placing of belt on this without shifting
            return ActionResult.PASS;
        }

        // Open the GUI for player.
        if (!world.isClient) {
            /*
              This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity casted to
              a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
             */
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                // With this call the server will request the client to open the appropriate Screenhandler
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

//    @Override
//    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
//        if (!oldState.isOf(state.getBlock())) {
//            this.updateEnabled(world, pos, state);
//        }
//    }
//
//    @Override
//    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
//        this.updateEnabled(world, pos, state);
//    }
//
//    private void updateEnabled(World world, BlockPos pos, BlockState state) {
//        boolean bl = !world.isReceivingRedstonePower(pos);
//        if (bl != state.get(ENABLED)) {
//            world.setBlockState(pos, state.with(ENABLED, bl), 2);
//        }
//    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
//        if(!state.get(ConveyorBeltBlock.ENABLED)) {
//            return;
//        }

        if(entity instanceof LivingEntity le) {
            if(!le.isSneaking()) {
                Direction direction = state.get(ConveyorBeltBlock.FACING);
                Vec3i vectorI = direction.getVector();
                Vec3d vector = new Vec3d(vectorI.getX(), vectorI.getY(), vectorI.getZ());
                moveEntityOn(vector, le);
            }
        }

        super.onSteppedOn(world, pos, state, entity);
    }

    public void moveEntityOn(Vec3d vector, LivingEntity livingEntity) {
        vector = vector.multiply(1.5f);
        moveEntityOn2(vector, livingEntity);
    }

    public void moveEntityOn2(Vec3d vector, LivingEntity livingEntity) {
        livingEntity.addVelocity(vector.getX(), vector.getY(), vector.getZ());
    }

    // TODO Why this ConveyorBelt mod author not do as this?
    public void moveEntityOn3(Vec3d vector, LivingEntity livingEntity) {
        vector = vector.multiply(1.5f);
        livingEntity.addVelocity(vector.getX(), vector.getY(), vector.getZ());
    }

    public Identifier getNextCycleBeltType() {
        return null;
    }
}
