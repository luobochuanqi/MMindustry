package top.yzy1.free.iitbag.mindustry.procedures;

import top.yzy1.free.iitbag.mindustry.init.MindustryModBlocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.core.BlockPos;

public class SlagDangPiLinFangKuaiGengXinShiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(new BlockPos(x - 1, y, z))).getBlock() == MindustryModBlocks.TAR.get()) {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, (x - 1), y, z, 1, Explosion.BlockInteraction.BREAK);
			world.setBlock(new BlockPos(x - 1, y, z), Blocks.FIRE.defaultBlockState(), 3);
		}
		if ((world.getBlockState(new BlockPos(x + 1, y, z))).getBlock() == MindustryModBlocks.TAR.get()) {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, (x + 1), y, z, 1, Explosion.BlockInteraction.BREAK);
			world.setBlock(new BlockPos(x + 1, y, z), Blocks.FIRE.defaultBlockState(), 3);
		}
		if ((world.getBlockState(new BlockPos(x, y, z - 1))).getBlock() == MindustryModBlocks.TAR.get()) {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, x, y, (z - 1), 1, Explosion.BlockInteraction.BREAK);
			world.setBlock(new BlockPos(x, y, z - 1), Blocks.FIRE.defaultBlockState(), 3);
		}
		if ((world.getBlockState(new BlockPos(x, y, z + 1))).getBlock() == MindustryModBlocks.TAR.get()) {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, x, y, (z + 1), 1, Explosion.BlockInteraction.BREAK);
			world.setBlock(new BlockPos(x, y, z + 1), Blocks.FIRE.defaultBlockState(), 3);
		}
		if ((world.getBlockState(new BlockPos(x, y - 1, z))).getBlock() == MindustryModBlocks.TAR.get()) {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, x, (y - 1), z, 1, Explosion.BlockInteraction.BREAK);
			world.setBlock(new BlockPos(x, y - 1, z), Blocks.FIRE.defaultBlockState(), 3);
		}
		if ((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock() == MindustryModBlocks.TAR.get()) {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, x, (y + 1), z, 1, Explosion.BlockInteraction.BREAK);
			world.setBlock(new BlockPos(x, y + 1, z), Blocks.FIRE.defaultBlockState(), 3);
		}
	}
}
