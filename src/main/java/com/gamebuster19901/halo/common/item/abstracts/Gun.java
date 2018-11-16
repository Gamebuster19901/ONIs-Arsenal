package com.gamebuster19901.halo.common.item.abstracts;

import com.gamebuster19901.halo.common.item.capability.Shootable;
import com.gamebuster19901.halo.common.item.capability.ShootableDefaultImpl;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public abstract class Gun extends HeldWeapon implements Shootable.Tag{
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		ICapabilityProvider prevProvider = super.initCapabilities(stack, nbt);
		
		return new ICapabilityProvider() {
			Shootable shootable = ShootableDefaultImpl.CAPABILITY.getDefaultInstance();
			
			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				if(prevProvider.hasCapability(capability, facing)) {
					return true;
				}
				return capability == ShootableDefaultImpl.CAPABILITY;
			}

			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				T prev = prevProvider.getCapability(capability, facing);
				if(prev != null){
					return prev;
				}
				if(capability == ShootableDefaultImpl.CAPABILITY) {
					return (T) shootable;
				}
				return null;
			}
			
		};
	}
}
