package xyz.luobochuanqi.mindustry.common.Type.DrillBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.init.BlockRegister;
import xyz.luobochuanqi.mindustry.common.init.ItemRegister;
import xyz.luobochuanqi.mindustry.common.util.Mod2x2Part;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class DrillBlockEntity extends TileEntity implements ITickableTileEntity {
    public static final EnumProperty<Mod2x2Part> ModPART = Utils.Mod2x2PART;
    public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public Inventory inventory = new Inventory(1);
    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
    public int increment = 0;
    public int rate;
    public int counter = 0;

    public DrillBlockEntity(TileEntityType<?> pType) {
        super(pType);
    }

    public void updateData() {
        this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(Cap -> {
            this.level.getBlockEntity(getMainBlockPos()).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(pCap -> {
                CompoundNBT pNBT = ((INBTSerializable<CompoundNBT>) pCap).serializeNBT();
                ((INBTSerializable<CompoundNBT>) Cap).deserializeNBT(pNBT);
            });
        });
    }

    /**
     * @return the amount of minerals mined to simulate the mining rate
     */
    public int getTheNumOfOres() {
        int num = 0;
        final ItemStack[] itemStack = new ItemStack[1];
        Block block2 = null;
        Block block1 = this.level.getBlockState(this.worldPosition.below()).getBlock();
        BlockPos[] blockPoses = getBlockPoses(this.worldPosition, this.getBlockState());
        for (Block block : getDrillableBlock()) {
            num = 0;
            // Get minerals under the main block
            if (block1 == block) {
                num++;
            }
            // Gets minerals under child-blocks
            for (int i = 0; i < 3; i++) {
                if (this.level.getBlockState(blockPoses[i].below()).getBlock() == block) {
                    num++;
                }
            }
            this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(cap -> {
                itemStack[0] = cap.getStackInSlot(0);
            });
            if (getDrillableItemByBlock(block) != itemStack[0].getItem() && !itemStack[0].isEmpty()) {
                num = 0;
                continue;
            }
            if (num >= 1) {
                block2 = block;
                break;
            }
        }
        return num;
    }

    /**
     * @return the pos of each child-block
     */
    public BlockPos[] getBlockPoses(BlockPos pPos, BlockState pState) {
        Direction direction = pState.getValue(FACING);
        BlockPos[] blockPoses = new BlockPos[5];
        BlockPos nextBlockpos = pPos;
        // Get pos of each child-block clockwise
        for (int i = 0; i < 3; i++) {
            nextBlockpos = nextBlockpos.relative(direction);
            blockPoses[i] = nextBlockpos;
            direction = direction.getClockWise();
        }
        return blockPoses;
    }

    public BlockPos getMainBlockPos() {
        BlockPos[] pBlockPoses = getBlockPoses(this.getBlockPos(), this.getBlockState());
        BlockPos MainBlockPos = worldPosition;
        for (int i = 0; i < 3; i++) {
            if (this.level.getBlockState(pBlockPoses[i]).getValue(ModPART) == Mod2x2Part.START) {
                MainBlockPos = pBlockPoses[i];
            }
        }
        return MainBlockPos;
    }

    @Override
    public void tick() {
        if (level.getBlockState(this.worldPosition).getValue(ModPART) == Mod2x2Part.START && !this.level.isClientSide) {
            rate = (int) (getTheNumOfOres() * (getBaseDrillSpeed() / 4 * 10));
            if (counter > 0) {
                counter--;
            } else if (counter <= 0) {
                increment += rate;
                Utils.LOGGER.info("rate: " + rate + " num：" + getTheNumOfOres());
                if (increment >= 10) {
                    ItemStack newItemStack = new ItemStack(getDrillableItemByBlock(this.level.getBlockState(this.worldPosition.below()).getBlock()), 1);
                    this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(pCap -> {
                        if (pCap.getStackInSlot(0).getCount() < pCap.getSlotLimit(0)) {
                            pCap.insertItem(0, newItemStack, false);
                        }
                        addSmock(this.worldPosition);
                        for (int i = 0; i < 3; i++) {
                            BlockPos[] blockPoses = getBlockPoses(this.worldPosition, this.getBlockState());
                            addSmock(blockPoses[i]);
                        }
                    });
                    increment -= 10;
                }
                counter = 20;
            }
        }
    }

    public void addSmock(BlockPos pPos) {
        Random pRand = new Random();
        double d0 = (double) pPos.getX() + 0.5D + (0.5D - pRand.nextDouble());
        double d1 = (double) pPos.getY() + 1.0D;
        double d2 = (double) pPos.getZ() + 0.5D + (0.5D - pRand.nextDouble());
        double d3 = (double) pRand.nextFloat() * 0.4D;
        level.addAlwaysVisibleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, d0, d1, d2, 0.0D, d3, 0.0D);
        Utils.LOGGER.info("yy");
        level.addParticle(ParticleTypes.LARGE_SMOKE, d0, d1, d2, 0.0D, d3, 0.0D);
    }

    /**
     * Return the Set of minerals that can be mined
     */
    public Set<Item> getDrillableItem() {
        Set<Item> ItemSet = new HashSet<>();
        ItemSet.add(ItemRegister.sand.get());
        return ItemSet;
    }

    /**
     * Return the Set of minerals that can be mined
     */
    public Set<Block> getDrillableBlock() {
        Set<Block> BlockSet = new HashSet<>();
        BlockSet.add(Blocks.SAND);
        return BlockSet;
    }

    public Item getDrillableItemByBlock(Block pBlock) {
        if (Blocks.SAND.equals(pBlock)) {
            return ItemRegister.sand.get();
        } else if (BlockRegister.copper_ore.get().equals(pBlock)) {
            return ItemRegister.copper.get();
        } else if (BlockRegister.lead_ore.get().equals(pBlock)) {
            return ItemRegister.lead.get();
        } else if (BlockRegister.scrap_ore.get().equals(pBlock)) {
            return ItemRegister.scrap.get();
        } else if (BlockRegister.coal_ore.get().equals(pBlock)) {
            return ItemRegister.coal.get();
        } else if (BlockRegister.titanium_ore.get().equals(pBlock)) {
            return ItemRegister.titanium.get();
        } else if (BlockRegister.thorium_ore.get().equals(pBlock)) {
            return ItemRegister.thorium.get();
        } else return Items.AIR;
    }

    @Override
    public void load(BlockState pBlockState, CompoundNBT pNBT) {
        CompoundNBT invTag = pNBT.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
        super.load(pBlockState, pNBT);
    }

    @Override
    public CompoundNBT save(CompoundNBT pNBT) {
        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            pNBT.put("inv", compound);
        });
        return super.save(pNBT);
    }

    private IItemHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack pStack) {
                for (Item item : getDrillableItem()) {
                    if (pStack.getItem() == item) {
                        return true;
                    }
                }
                return false;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack pStack, boolean simulate) {
                boolean flag = false;
                for (Item item : getDrillableItem()) {
                    if (pStack.getItem() == item) {
                        flag = true;
                    }
                }
                if (!flag) {
                    return pStack;
                }
                return super.insertItem(slot, pStack, simulate);
            }

            @Override
            public int getSlotLimit(int slot) {
                if (slot == 0) {
                    return getMaxStackSize();
                } else {
                    return super.getSlotLimit(slot);
                }
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> pCap, @Nullable Direction side) {
        if (pCap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(pCap, side);
    }

    public double getBaseDrillSpeed() {
        return 0.4;
    }

    public int getMaxStackSize() {
        return 10;
    }
}
