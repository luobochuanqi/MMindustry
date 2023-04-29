package xyz.luobochuanqi.mindustry.common.util;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class SimplePowerNetwork implements IPowerNetwork{
    protected final Map<BlockPos, Set<BlockPos>> components;
    protected static SetMultimap<BlockPos, Direction> connections;

    public SimplePowerNetwork()
    {
        this.components = Maps.newHashMap();
        this.connections = Multimaps.newSetMultimap(Maps.newHashMap(),
                () -> EnumSet.noneOf(Direction.class));
    }

    public static SetMultimap<BlockPos, Direction> getConnections() {
//        SetMultimap<BlockPos, Direction> connections1 = connections;
        return connections;
    }

    @Override
    public int size(BlockPos node)
    {
        return this.components.containsKey(node) ? this.components.get(node).size() : 1;
    }

    @Override
    public BlockPos root(BlockPos node)
    {
        return this.components.containsKey(node) ? this.components.get(node).iterator().next() : node.immutable();
    }

    @Override
    public void cut(BlockPos node, Direction direction, ConnectivityListener afterSplit)
    {
        if (this.connections.remove(node, direction))
        {
            BlockPos another = node.relative(direction);
            this.connections.remove(another, direction.getOpposite());
            BFSIterator nodeIterator = new BFSIterator(node), anotherIterator = new BFSIterator(another);
            while (nodeIterator.hasNext())
            {
                BlockPos next = nodeIterator.next();
                if (!anotherIterator.getSearched().contains(next))
                {
                    BFSIterator iterator = anotherIterator;
                    anotherIterator = nodeIterator;
                    nodeIterator = iterator;
                    continue;
                }
                return;
            }
            Set<BlockPos> primaryComponent = this.components.get(node), secondaryComponent;
            BlockPos primaryNode = primaryComponent.iterator().next();
            Set<BlockPos> searched = nodeIterator.getSearched();
            if (searched.contains(primaryNode))
            {
                secondaryComponent = Sets.newLinkedHashSet(Sets.difference(primaryComponent, searched));
                primaryComponent.retainAll(searched);
            }
            else
            {
                secondaryComponent = searched;
                primaryComponent.removeAll(searched);
            }
            if (secondaryComponent.size() <= 1)
            {
                secondaryComponent.forEach(this.components::remove);
            }
            else
            {
                secondaryComponent.forEach(pos -> this.components.put(pos, secondaryComponent));
            }
            if (primaryComponent.size() <= 1)
            {
                primaryComponent.forEach(this.components::remove);
            }
            afterSplit.onChange(primaryNode, secondaryComponent.iterator().next());
        }
    }

    @Override
    public void link(BlockPos node, Direction direction, ConnectivityListener beforeMerge)
    {
        BlockPos secondary = node.immutable();
        if (this.connections.put(secondary, direction))
        {
            BlockPos primary = node.relative(direction);
            this.connections.put(primary, direction.getOpposite());
            Set<BlockPos> primaryComponent = this.components.get(primary);
            Set<BlockPos> secondaryComponent = this.components.get(secondary);
            if (primaryComponent == null && secondaryComponent == null)
            {
                Set<BlockPos> union = Sets.newLinkedHashSet();
                beforeMerge.onChange(secondary, primary);
                this.components.put(secondary, union);
                this.components.put(primary, union);
                union.add(secondary);
                union.add(primary);
            }
            else if (primaryComponent == null)
            {
                beforeMerge.onChange(secondaryComponent.iterator().next(), primary);
                this.components.put(primary, secondaryComponent);
                secondaryComponent.add(primary);
            }
            else if (secondaryComponent == null)
            {
                beforeMerge.onChange(primaryComponent.iterator().next(), secondary);
                this.components.put(secondary, primaryComponent);
                primaryComponent.add(secondary);
            }
            else if (primaryComponent != secondaryComponent)
            {
                beforeMerge.onChange(primaryComponent.iterator().next(), secondaryComponent.iterator().next());
                Set<BlockPos> union = Sets.newLinkedHashSet(Sets.union(primaryComponent, secondaryComponent));
                union.forEach(pos -> this.components.put(pos, union));
            }
        }
    }
}
