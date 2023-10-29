package mc.mdt.common.util;

import net.minecraft.block.BlockWithEntity;
import net.minecraft.entity.LivingEntity;

/**
 * @author luobochuanqi
 */
public abstract class BlockWithLivingEntity extends BlockWithEntity implements BlockLivingEntityProvider {
    public LivingEntity containedLivingEntity;

    protected BlockWithLivingEntity(Settings settings) {
        super(settings);
    }

    public LivingEntity getContainedLivingEntity() {
        return containedLivingEntity;
    }

    //    @Override
//    public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
//        super.onSyncedBlockEvent(state, world, pos, type, data);
//        LivingEntity livingEntity = createBlockLivingEntity(pos, world);
//        List<? extends LivingEntity> livingEntities = world.getEntitiesByClass(livingEntity.getClass(), new Box(pos), EntityPredicates.VALID_LIVING_ENTITY);
//        if (livingEntities.isEmpty()) {
//            return false;
//        }
//        return true;
//    }
}
