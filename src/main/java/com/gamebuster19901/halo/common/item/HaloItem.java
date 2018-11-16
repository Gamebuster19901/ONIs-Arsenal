package com.gamebuster19901.halo.common.item;

import com.gamebuster19901.halo.common.util.EasyLocalization;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraftforge.common.capabilities.ICapabilityProvider;

public abstract class HaloItem extends Item implements EasyLocalization{
	public HaloItem() {
		this.setRegistryName(getResourceLocation());
		this.setUnlocalizedName(getEZUnlocalizedName());
	}
	
    /**
     * Called from ItemStack.setItem, will hold extra data for the life of this ItemStack.
     * Can be retrieved from stack.getCapabilities()
     * The NBT can be null if this is not called from readNBT or if the item the stack is
     * changing FROM is different then this item, or the previous item had no capabilities.
     *
     * This is called BEFORE the stacks item is set so you can use stack.getItem() to see the OLD item.
     * Remember that getItem CAN return null.
     *
     * @param stack The ItemStack
     * @param nbt NBT of this item serialized, or null.
     * @return A holder instance associated with this ItemStack where you can hold capabilities for the life of this item.
     */
	@Override
	public abstract ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt);
	
}
