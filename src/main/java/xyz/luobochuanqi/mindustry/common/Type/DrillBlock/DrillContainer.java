//package xyz.luobochuanqi.mindustry.common.Type.DrillBlock;
//
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.IInventory;
//import net.minecraft.inventory.Inventory;
//import net.minecraft.inventory.container.Container;
//import net.minecraft.inventory.container.Slot;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.SlotItemHandler;
//import xyz.luobochuanqi.mindustry.common.init.ContainerRegister;
//
//public class DrillContainer extends Container {
//    private final IInventory hopper;
//    private final TileEntity tileEntity;
//
//    public DrillContainer(int pContainerId, PlayerInventory pPlayerInventory, World pLevel, BlockPos pPos) {
//        this(pContainerId, pPlayerInventory, new Inventory(6), pLevel, pPos);
//    }
//
//    public DrillContainer(int pContainerId, PlayerInventory pPlayerInventory, IInventory pContainer, World pLevel, BlockPos pPos) {
//        super(ContainerRegister.DRILL_CONTAINER.get(), pContainerId);
//        this.hopper = pContainer;
//        this.tileEntity = pLevel.getBlockEntity(pPos);
//        checkContainerSize(pContainer, 1);
//        pContainer.startOpen(pPlayerInventory.player);
//        int i = 51;
//
////        for(int j = 1; j < 5; ++j) {
////            this.addSlot(new Slot(pContainer, j, 44 + j * 18, 20));
////        }
//
//        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
//            addSlot(new SlotItemHandler(h, 0, 134, 20));
//        });
//
//        for(int l = 0; l < 3; ++l) {
//            for(int k = 0; k < 9; ++k) {
//                this.addSlot(new Slot(pPlayerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
//            }
//        }
//
//        for(int i1 = 0; i1 < 9; ++i1) {
//            this.addSlot(new Slot(pPlayerInventory, i1, 8 + i1 * 18, 109));
//        }
//    }
//
//    /**
//     * Determines whether supplied player can use this container
//     */
//    public boolean stillValid(PlayerEntity pPlayer) {
//        return this.hopper.stillValid(pPlayer);
//    }
//
//    /**
//     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
//     * inventory and the other inventory(s).
//     */
//    public ItemStack quickMoveStack(PlayerEntity pPlayer, int pIndex) {
//        ItemStack itemstack = ItemStack.EMPTY;
//        Slot slot = this.slots.get(pIndex);
//        if (slot != null && slot.hasItem()) {
//            ItemStack itemstack1 = slot.getItem();
//            itemstack = itemstack1.copy();
//            if (pIndex < this.hopper.getContainerSize()) {
//                if (!this.moveItemStackTo(itemstack1, this.hopper.getContainerSize(), this.slots.size(), true)) {
//                    return ItemStack.EMPTY;
//                }
//            } else if (!this.moveItemStackTo(itemstack1, 0, this.hopper.getContainerSize(), false)) {
//                return ItemStack.EMPTY;
//            }
//
//            if (itemstack1.isEmpty()) {
//                slot.set(ItemStack.EMPTY);
//            } else {
//                slot.setChanged();
//            }
//        }
//
//        return itemstack;
//    }
//
//    /**
//     * Called when the container is closed.
//     */
//    public void removed(PlayerEntity pPlayer) {
//        super.removed(pPlayer);
//        this.hopper.stopOpen(pPlayer);
//    }
//}
