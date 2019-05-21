package com.gamebuster19901.halo.common.item.abstracts;

import com.gamebuster19901.halo.common.item.HaloItem;
import com.gamebuster19901.halo.common.item.capability.Weapon;
import com.gamebuster19901.halo.common.item.capability.WeaponDefaultImpl;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public abstract class HeldWeapon extends HaloItem implements Weapon.Tag{
	
	public HeldWeapon() {
		super(1);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new ICapabilityProvider() {			
			@Override
			public <T> LazyOptional<T> getCapability(Capability<T> capability, EnumFacing facing) {
				if(capability == WeaponDefaultImpl.CAPABILITY) {
					return (LazyOptional<T>) LazyOptional.of(() -> new WeaponDefaultImpl(1,false));
				}
				return LazyOptional.empty();
			}
		};
	}
	
	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
	 * update it's contents.
	 */
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		Capability<Weapon> weapon = WeaponDefaultImpl.CAPABILITY;
		if(isSelected) {
			if(stack.getCapability(weapon).isPresent()) {
				stack.getCapability(weapon).orElseThrow(AssertionError::new).update();
			}
		}
	}
	
}
