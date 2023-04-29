package xyz.luobochuanqi.mindustry.common.util;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public interface IPowerNetwork {
    int size(BlockPos node);

    BlockPos root(BlockPos node);

    void cut(BlockPos node, Direction direction, ConnectivityListener afterSplit);

    void link(BlockPos node, Direction direction, ConnectivityListener beforeMerge);

    @FunctionalInterface
    interface ConnectivityListener
    {
        void onChange(BlockPos primaryNode, BlockPos secondaryNode);
    }
}
