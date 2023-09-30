package mc.mdt.common.blocks;

import mc.mdt.MMindustry;
import mc.mdt.common.blockentity.DuoTurretBlockEntity;
import mc.mdt.common.entitys.DuoTurretEntity;
import mc.mdt.common.init.MDTEntitys;
import mc.mdt.common.util.BlockLivingEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DuoTurretBlcok extends BlockWithEntity implements BlockLivingEntityProvider {
    public DuoTurretBlcok(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DuoTurretBlockEntity(pos, state);
    }

    @Override
    public @Nullable LivingEntity createBlockLivingEntity(BlockPos pos, World world) {
        return new DuoTurretEntity(MDTEntitys.TURRET_DUO_ENTITY, world);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
//        this.createBlockLivingEntity(pos, world).refreshPositionAndAngles(pos, 0, 0);
            BlockPos entityPos = pos.up();
            LivingEntity livingEntity = MDTEntitys.TURRET_DUO_ENTITY.create((ServerWorld) world, null, null, entityPos, SpawnReason.SPAWN_EGG, false, false);
            world.spawnEntity(livingEntity);
        }
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
//        return world.isClient ?
//                checkType(type, MMindustry.DUO_TURRET_BLOCK_ENTITY, DuoTurretBlockEntity::clientTick)
//                : checkType(type, MMindustry.DUO_TURRET_BLOCK_ENTITY, DuoTurretBlockEntity::serverTick);
        return world.isClient ?
                checkType(type, MMindustry.DUO_TURRET_BLOCK_ENTITY, DuoTurretBlockEntity::clientTick)
                : checkType(type, MMindustry.DUO_TURRET_BLOCK_ENTITY, DuoTurretBlockEntity::serverTick);
    }
}
