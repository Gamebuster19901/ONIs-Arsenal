package com.gamebuster19901.halo.common.item.abstracts;

import com.gamebuster19901.halo.common.item.capability.Shootable;
import com.gamebuster19901.halo.common.item.capability.ShootableDefaultImpl;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public abstract class Gun extends HeldWeapon implements Shootable.Tag{
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		ICapabilityProvider prevProvider = super.initCapabilities(stack, nbt);
		
		return new ICapabilityProvider() {

			@Override
			public <T> LazyOptional<T> getCapability(Capability<T> capability, EnumFacing facing) {
				LazyOptional<T> prev = prevProvider.getCapability(capability, facing);
				if(prev != null){
					return prev;
				}
				if(capability == ShootableDefaultImpl.CAPABILITY) {
					return (LazyOptional<T>) LazyOptional.of(() -> new ShootableDefaultImpl(0,0,0,0,0,0,0,0,new NBTTagCompound()));
				}
				return null;
			}
			
		};
	}
}
