package mc.mdt.common.util;

import net.minecraft.block.Block;

/**
 * @author luobochuanqi
 */
public abstract class BlockWithLivingEntity extends Block implements BlockLivingEntityProvider {
    protected BlockWithLivingEntity(Settings settings) {
        super(settings);
    }
}
