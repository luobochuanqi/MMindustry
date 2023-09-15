package mc.mdt.common.blockentity;

import mc.mdt.MMindustry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DuoTurretBlockEntity extends BlockEntity {
    private EntityType<?> entityType;
    private int entityId;

    public DuoTurretBlockEntity(BlockPos pos, BlockState state) {
        super(MMindustry.DUO_TURRET_BLOCK_ENTITY, pos, state);
    }

    public void setEntityType(EntityType<?> entityType) {
        this.entityType = entityType;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        this.entityType = EntityType.get(tag.getString("EntityType")).get();
        this.entityId = tag.getInt("EntityId");
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putString("EntityType", EntityType.getId(entityType).toString());
        tag.putInt("EntityId", entityId);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, DuoTurretBlockEntity blockEntity) {
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, DuoTurretBlockEntity blockEntity) {
        // Check if the entity still exists and remove it if it doesn't
//        if (world.getEntityById(entityId) == null) {
//            world.removeBlock(getPos(), false);
//        }
    }
}
