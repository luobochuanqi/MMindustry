package mc.mdt.common.blocks;

import mc.mdt.common.blockentity.TurretDuoBlockEntity;
import mc.mdt.common.entitys.TurretDuoEntity;
import mc.mdt.common.init.MDTEntitys;
import mc.mdt.common.util.BlockWithLivingEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Made with real block
public class TurretDuoBlock extends BlockWithLivingEntity {

    public static final Logger LOGGER = LoggerFactory.getLogger("TurretDuoBlock");

    public TurretDuoBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TurretDuoBlockEntity(pos, state);
    }

    @Override
    public @Nullable LivingEntity createBlockLivingEntity(BlockPos pos, World world) {
        return new TurretDuoEntity(MDTEntitys.TURRET_DUO_ENTITY_ONE, pos, world);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        this.containedLivingEntity = createBlockLivingEntity(pos, world);
        world.spawnEntity(containedLivingEntity);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        super.onEntityLand(world, entity);
        Double csX = this.containedLivingEntity.getX();
        LOGGER.info("x:" + csX);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            Double csY = this.containedLivingEntity.getY();
            float health = this.containedLivingEntity.getHealth();
            LOGGER.info("y:" + csY);
            LOGGER.info("h:" + health);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
