package mc.mdt.common.blockentity;

import mc.mdt.common.blocks.ConveyorBeltBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * @author BillBodkin
 * NamedScreenHandlerFactory,
 */
public class ConveyorBeltBlockEntity extends BlockEntity implements SidedInventory, Inventory {

    public DefaultedList<ItemStack> items = DefaultedList.ofSize(3, ItemStack.EMPTY);

    public int transferCooldown = 30;
    public int moveToCenterSpeed = 2;
    public int[] transferCooldownCounter = new int[size()];
    public int[] transferCooldownCounterLastTick = new int[10];
    public int[] transferSidewaysOffset = new int[size()];
    public int[] slotActuallyHasItem = new int[size()];

    public int[] sideTransferAttempts = new int[2];
    public long[] sideTransferLatestAttempt = new long[3];

    public ConveyorBeltBlockEntity(BlockPos pos, BlockState state, BlockEntityType blockEntityType) {
        super(blockEntityType, pos, state);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, ConveyorBeltBlockEntity blockEntity) {

        for (int i = 0; i < 3; i++) {
            blockEntity.updateCooldowns(i);
        }
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, ConveyorBeltBlockEntity blockEntity) {
//        if (!state.get(ConveyorBeltBlock.ENABLED)) {
//            blockEntity.updateSlotActuallyEmptyHack();
//            return;
//        }
//        MMindustry.LOGGER.debug(String.valueOf(blockEntity));

        for (int i = 0; i < 3; i++) {
            if (blockEntity.getStack(i).isEmpty()) {
                // if empty slot, reset cool-downs
                blockEntity.resetCooldowns(i);
                continue;
            }

            blockEntity.updateCooldowns(i);

            // if cool-down has run down, meaning item can goto next slot
            if (blockEntity.transferCooldownCounter[i] <= 0) {
                blockEntity.transferCooldownCounter[i] = 0;

                if (i == 2) {
                    // if last slot in belt, will goto next belt
                    Direction direction = state.get(ConveyorBeltBlock.FACING);
                    blockEntity.moveToNextBelt(direction, i);
                } else {
                    // else, moving from one slot to another within this belt

                    if (transferToEmpty(blockEntity, i, blockEntity, i + 1)) {
                        blockEntity.transferCooldownCounter[i + 1] = blockEntity.transferCooldown;
                        blockEntity.transferSidewaysOffset[i + 1] = blockEntity.transferSidewaysOffset[i];
                        blockEntity.markDirty();
                    }
                }
            }
        }

        blockEntity.updateSlotActuallyEmptyHack();
    }

    public static boolean transferToEmpty(Inventory fromInv, int fromSlot, Inventory toInv, int toSlot) {
        if (!fromInv.getStack(fromSlot).isEmpty() && toInv.getStack(toSlot).isEmpty()) {
            ItemStack toMove = fromInv.removeStack(fromSlot);
            toInv.setStack(toSlot, toMove);
            return true;
        }
        return false;
    }

    @Nullable
    public static ConveyorBeltBlockEntity getConveyorBlockEntityAt(World world, BlockPos pos) {
        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.5D;
        double z = pos.getZ() + 0.5D;

        ConveyorBeltBlockEntity inventory = null;
        BlockPos blockPos = new BlockPos.Mutable(x, y, z);
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.hasBlockEntity()) {
            Block block = blockState.getBlock();
            if (block instanceof ConveyorBeltBlock) {
                BlockEntity blockEntity = world.getBlockEntity(blockPos);
                inventory = (ConveyorBeltBlockEntity) blockEntity;
            }
        }

        return inventory;
    }

    @Override
    public void writeNbt(NbtCompound tag) {

        Inventories.writeNbt(tag, items);
        tag.putIntArray("TransferCooldownCounter", transferCooldownCounter);
        tag.putIntArray("TransferSidewaysOffset", transferSidewaysOffset);
        tag.putIntArray("SlotActuallyHasItem", slotActuallyHasItem);

        tag.putIntArray("SideTransferAttempts", sideTransferAttempts);
        tag.putLongArray("SideTransferLatestAttempt", sideTransferLatestAttempt);

        super.writeNbt(tag);
    }

//    @Override
//    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
//        // We provide *this* to the screenHandler as our class Implements Inventory
//        // Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
//        return new ConveyorBeltScreenHandler(syncId, inv, this);
//    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);

        Inventories.readNbt(tag, items);
        transferCooldownCounter = tag.getIntArray("TransferCooldownCounter");
        transferSidewaysOffset = tag.getIntArray("TransferSidewaysOffset");
        slotActuallyHasItem = tag.getIntArray("SlotActuallyHasItem");

        sideTransferAttempts = tag.getIntArray("SideTransferAttempts");
        sideTransferLatestAttempt = tag.getLongArray("SideTransferLatestAttempt");
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

//    @Override
//    public Text getDisplayName() {
//        return Text.translatable(getCachedState().getBlock().getTranslationKey());
//    }

    public boolean canMoveToSlot(int slot) {
        if (slot == 4 || slot == 5) {
            // For filter
            return false;
        }
        return getStack(slot).isEmpty() && (slot == 0 || getStack(slot - 1).isEmpty()) && (transferCooldownCounter[slot] >= (transferCooldown / 2) || transferCooldown < 4);
    }

    public boolean canMoveHereFromSide(World world, int sideIndex) {
        if (sideIndex == 2) {
            // sideTransferLatestAttempt[2] is top side
            sideTransferLatestAttempt[2] = world.getTime();
            return canMoveToSlot(1);
        }

        sideTransferAttempts[sideIndex] += 1;
        sideTransferLatestAttempt[sideIndex] = world.getTime();

        // If last attempt on other side was more than a second ago, assume no longer trying
        if (sideTransferLatestAttempt[1 - sideIndex] < (world.getTime() - 1)) {
            sideTransferAttempts[1 - sideIndex] = 0;
        }

        if (!(sideTransferLatestAttempt[2] < (world.getTime() - 1))) {
            // Hopper above trying to get in - takes priority
            return false;
        }

        if (!canMoveToSlot(1)) {
            // Slot behind target belt trying to get in - takes priority
            return false;
        }
        // Can go if this side has been trying for longer then the other
        return sideTransferAttempts[sideIndex] > sideTransferAttempts[1 - sideIndex];
    }

    public boolean moveToNextBelt(Direction direction, int fromSlot, ConveyorBeltBlockEntity conveyorBlockEntityInfront) {
        if (conveyorBlockEntityInfront == null) {
            return false;
        }

        Direction directionOfBeltInfront = conveyorBlockEntityInfront.world.getBlockState(conveyorBlockEntityInfront.pos).get(ConveyorBeltBlock.FACING);

        if (directionOfBeltInfront.getOpposite() == direction) {
            // Belts are facing towards each-over, item can move
            return false;
        }

        int newOffset = transferSidewaysOffset[fromSlot];
        int newSlot = 0;
        int sideIndex = -1;
        boolean canMove = true;

        if (direction.rotateClockwise(Direction.Axis.Y) == directionOfBeltInfront) {
            newOffset = 50;
            newSlot = 1;
            sideIndex = 0;
        }

        if (direction.rotateCounterclockwise(Direction.Axis.Y) == directionOfBeltInfront) {
            newOffset = -50;
            newSlot = 1;
            sideIndex = 1;
        }

        if (sideIndex != -1) {
            if (!conveyorBlockEntityInfront.canMoveHereFromSide(world, sideIndex)) {
                canMove = false;
            }
        }

        if (conveyorBlockEntityInfront.slotActuallyHasItem[newSlot] > 0) {
            // Adds a tiny gap between items which prevents some strange behaviour where the cool-downs arnt reset on downward sloping belts when items are in a constant stream.
            return false;
        }

        if (!canMove) {
            return false;
        }

        // move item there
        if (!transferToEmpty(this, fromSlot, conveyorBlockEntityInfront, newSlot)) {
            return false;
        }

        if (sideIndex == 0 || sideIndex == 1) {
            // going onto left or right side of next belt, cooldown is halved as will be joining halfway into the second slot
            conveyorBlockEntityInfront.transferCooldownCounter[newSlot] = (int) ((float) conveyorBlockEntityInfront.transferCooldown * 0.5);
        } else {
            // going onto beck of next belt
            conveyorBlockEntityInfront.transferCooldownCounter[newSlot] = conveyorBlockEntityInfront.transferCooldown;
        }

        conveyorBlockEntityInfront.transferSidewaysOffset[newSlot] = newOffset;

        markDirty();
        conveyorBlockEntityInfront.markDirty();

        return true;
    }

    public final boolean moveToNextBelt(Direction direction, int fromSlot) {
        ConveyorBeltBlockEntity conveyorBlockEntityInfront = getConveyorBlockEntityAt(world, pos.offset(direction));

        return moveToNextBelt(direction, fromSlot, conveyorBlockEntityInfront);
    }

    public void resetCooldowns(int slot) {
        transferCooldownCounter[slot] = transferCooldown;
        transferSidewaysOffset[slot] = 0;
    }

    public void updateCooldowns(int slot) {
        transferCooldownCounter[slot] -= 1;

        if (transferCooldownCounter[slot] <= 0) {
            transferCooldownCounter[slot] = 0;
        }

        if (Math.abs(transferSidewaysOffset[slot]) < moveToCenterSpeed) {
            transferSidewaysOffset[slot] = 0;
        } else if (transferSidewaysOffset[slot] > 0) {
            transferSidewaysOffset[slot] -= moveToCenterSpeed;
        } else if (transferSidewaysOffset[slot] < 0) {
            transferSidewaysOffset[slot] += moveToCenterSpeed;
        }
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        if (slot >= 4) {
            ItemStack stack = items.get(slot);

            if (stack.isEmpty()) {
                return stack;
            }

            // VERY hacky
            NbtCompound nbt = stack.getOrCreateNbt();
            if (nbt.getBoolean("IsFilterItem")) {
                StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                StackTraceElement calling = stackTraceElements[2];
                String callingClass = calling.getClassName();
                // String callingMethod = calling.getMethodName();
                if ("dan200.computercraft.shared.util.ItemStorage$InventoryWrapper".equals(callingClass)) {
                    // System.out.println(callingMethod);
                    return ItemStack.EMPTY;
                }
            }
        }

        return items.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (amount == 0) {
            return ItemStack.EMPTY;
        }

        if (amount == items.get(slot).getCount()) {
            return removeStack(slot);
        }

        ItemStack toRet = items.get(slot).copy();
        items.get(slot).decrement(amount);
        toRet.setCount(amount);

        // updateSlotActuallyEmptyHack();
        return toRet;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack toRet = items.get(slot).copy();
        items.set(slot, ItemStack.EMPTY);

        // updateSlotActuallyEmptyHack();
        return toRet;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        items.set(slot, stack);

        // updateSlotActuallyEmptyHack();
    }

    public void updateSlotActuallyEmptyHack() {
        int[] slotActuallyHasItemBefore = slotActuallyHasItem.clone();

        Boolean needToUpdate = false;
        for (int i = 0; i < Math.min(slotActuallyHasItem.length, 4); i++) {
            slotActuallyHasItem[i] = getStack(i).isEmpty() ? slotActuallyHasItem[i] - 1 : 2;
            if (slotActuallyHasItem[i] < 0) {
                slotActuallyHasItem[i] = 0;
            }
            if (slotActuallyHasItem[i] != slotActuallyHasItemBefore[i]) {
                needToUpdate = true;
            }
        }

        if (needToUpdate) {
            markDirty();
        }

        transferCooldownCounterLastTick = transferCooldownCounter.clone();
    }

    @Override
    public void markDirty() {
        super.markDirty();

        if (this.hasWorld() && !this.getWorld().isClient()) {
            // new
            ((ServerWorld) world).getChunkManager().markForUpdate(getPos());
        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        // Just return an array of all slots
        int[] result = new int[items.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        Direction facing = getCachedState().get(ConveyorBeltBlock.FACING);

        if (facing.getOpposite() == dir) {
            return slot == 0 && canMoveToSlot(0);
        }

        if (slot != 1) {
            return false;
        }

        if (dir == Direction.UP) {
            return slot == 1 && canMoveHereFromSide(Objects.requireNonNull(getWorld()), 2);
        }

        int sideIndex = -1;

        if (facing.rotateCounterclockwise(Direction.Axis.Y) == dir) {
            sideIndex = 0;
        }

        if (facing.rotateClockwise(Direction.Axis.Y) == dir) {
            sideIndex = 1;
        }

        if (sideIndex != -1) {
            return slot == 1 && canMoveHereFromSide(Objects.requireNonNull(getWorld()), sideIndex);
        }

        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    @Override
    public BlockPos getPos() {
        return this.pos;
    }

    @Override
    public void clear() {
        items.clear();
    }
}
