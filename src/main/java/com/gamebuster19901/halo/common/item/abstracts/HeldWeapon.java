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

public abstract class HeldWeapon extends HaloItem implements Weapon.Tag{
	
	public static final EnumFacing F = EnumFacing.DOWN;
	
	public HeldWeapon() {
		this.setMaxStackSize(1);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new ICapabilityProvider() {
			Weapon weapon = WeaponDefaultImpl.CAPABILITY.getDefaultInstance();
			
			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				return capability == WeaponDefaultImpl.CAPABILITY;
			}
			
			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				if(capability == WeaponDefaultImpl.CAPABILITY) {
					return (T) weapon;
				}
				return null;
			}
			
		};
	}
	
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		Capability<Weapon> weapon = WeaponDefaultImpl.CAPABILITY;
		if(isSelected) {
			if(stack.hasCapability(weapon, EnumFacing.DOWN)) {
				stack.getCapability(weapon, EnumFacing.DOWN).update();
			}
		}
	}
	
}
