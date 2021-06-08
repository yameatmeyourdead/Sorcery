package com.yameatmeyourdead.sorcery.te;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TileInventory extends TileEntity implements IInventory {
    protected NonNullList<ItemStack> inventory;
    LazyOptional<IItemHandler> handlerDown;
    LazyOptional<IItemHandler> handlerUp;
    LazyOptional<IItemHandler> handlerNorth;
    LazyOptional<IItemHandler> handlerSouth;
    LazyOptional<IItemHandler> handlerEast;
    LazyOptional<IItemHandler> handlerWest;
    private int size;
    private String name;

    public TileInventory(TileEntityType<?> type, int size, String name) {
        super(type);
        this.inventory = NonNullList.withSize(size, ItemStack.EMPTY);
        this.size = size;
        this.name = name;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.inventory);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();
        ItemStackHelper.saveAllItems(nbt, this.inventory);
        return nbt;
    }

    @Override
    public int getContainerSize() {
        return this.size;
    }

    @Override
    public ItemStack getItem(int slotIndex) {
        return inventory.get(slotIndex);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        if(!getItem(index).isEmpty()){
            if(!getLevel().isClientSide()) {
                getLevel().sendBlockUpdated(getBlockPos(), getLevel().getBlockState(getBlockPos()), getLevel().getBlockState(getBlockPos()), 3);
            }

            if(getItem(index).getCount() <= count) {
                ItemStack itemStack = inventory.get(index);
                inventory.set(index, ItemStack.EMPTY);
                setChanged();
                return itemStack;
            }

            ItemStack itemStack = inventory.get(index).split(count);
            setChanged();
            return itemStack;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        if(!inventory.get(slot).isEmpty()) {
            ItemStack itemStack = inventory.get(slot);
            setItem(slot, ItemStack.EMPTY);
            return itemStack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        if(!stack.isEmpty() && stack.getCount() > getMaxStackSize())
            stack.setCount(getMaxStackSize());
        setChanged();
        if(!getLevel().isClientSide())
            getLevel().sendBlockUpdated(getBlockPos(), getLevel().getBlockState(getBlockPos()), getLevel().getBlockState(getBlockPos()), 3);
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public void startOpen(PlayerEntity player) {
        
    }

    @Override
    public void stopOpen(PlayerEntity player) {
        
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return true;
    }

    @Override
    public void clearContent() {
        this.inventory = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.inventory) if (!stack.isEmpty()) return false;
        return true;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return true;
    }

    protected void itemHandlerInit() {
        if (this instanceof ISidedInventory) {
            handlerUp = LazyOptional.of(() -> new SidedInvWrapper((ISidedInventory) this, Direction.UP));
            handlerDown = LazyOptional.of(() -> new SidedInvWrapper((ISidedInventory) this, Direction.DOWN));
            handlerNorth = LazyOptional.of(() -> new SidedInvWrapper((ISidedInventory) this, Direction.NORTH));
            handlerSouth = LazyOptional.of(() -> new SidedInvWrapper((ISidedInventory) this, Direction.SOUTH));
            handlerEast = LazyOptional.of(() -> new SidedInvWrapper((ISidedInventory) this, Direction.EAST));
            handlerWest = LazyOptional.of(() -> new SidedInvWrapper((ISidedInventory) this, Direction.WEST));
        }
        else {
            handlerUp = LazyOptional.of(() -> new InvWrapper(this));
            handlerDown = handlerUp;
            handlerNorth = handlerUp;
            handlerSouth = handlerUp;
            handlerEast = handlerUp;
            handlerWest = handlerUp;
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if(side != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            switch(side) {
                case UP:
                    return handlerUp.cast();
                case DOWN:
                    return handlerDown.cast();
                case NORTH:
                    return handlerNorth.cast();
                case EAST:
                    return handlerEast.cast();
                case SOUTH:
                    return handlerSouth.cast();
                case WEST:
                    return handlerWest.cast();
                default:
                    return handlerUp.cast();
            }
        } else if(side == null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handlerUp.cast();
        }

        return super.getCapability(cap, side);
    }
}