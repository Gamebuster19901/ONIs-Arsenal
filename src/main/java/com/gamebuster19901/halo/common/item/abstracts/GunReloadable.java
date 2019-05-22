package com.gamebuster19901.halo.common.item.abstracts;

import com.gamebuster19901.halo.common.item.capability.Reloadable;
import com.gamebuster19901.halo.common.item.capability.ReloadableDefaultImpl;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public abstract class GunReloadable extends Gun implements Reloadable.Tag{
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new ICapabilityProvider() {
			Reloadable reloadable = ReloadableDefaultImpl.CAPABILITY.getDefaultInstance();

			@Override
			public <T> LazyOptional<T> getCapability(Capability<T> capability, EnumFacing facing) {
				if(capability == ReloadableDefaultImpl.CAPABILITY) {
					return (LazyOptional<T>) LazyOptional.of(() -> new ReloadableDefaultImpl(1, 1));
				}
				return LazyOptional.empty();
			}
			
		};
	}
}
