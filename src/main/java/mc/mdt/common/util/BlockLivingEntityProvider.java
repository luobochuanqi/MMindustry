package mc.mdt.common.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * @author luobochuanqi
 */
public interface BlockLivingEntityProvider {
    /**
     * 对应实体
     * @return 一个新的  {@link LivingEntity} 实例
     */
    @Nullable
    public LivingEntity createBlockLivingEntity(BlockPos pos, World world);
}
