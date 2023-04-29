package xyz.luobochuanqi.mindustry.common.util;

import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

public class BFSIterator implements Iterator<BlockPos>
{
    private final Set<BlockPos> searched = Sets.newLinkedHashSet();
    private final Queue<BlockPos> queue = Queues.newArrayDeque();

    public BFSIterator(BlockPos node)
    {
        node = node.immutable();
        this.searched.add(node);
        this.queue.offer(node);
    }

    @Override
    public boolean hasNext()
    {
        return this.queue.size() > 0;
    }

    @Override
    public BlockPos next()
    {
        BlockPos node = this.queue.remove();
//        SimplePowerNetwork.connections.get(node)
        for (Direction direction : SimplePowerNetwork.getConnections().get(node))
        {
            BlockPos another = node.relative(direction);
            if (this.searched.add(another))
            {
                this.queue.offer(another);
            }
        }
        return node;
    }

    public Set<BlockPos> getSearched()
    {
        return this.searched;
    }
}
