package com.gamebuster19901.halo.common.item.abstracts;

import net.minecraft.nbt.NBTTagCompound;

public interface EnergyWeapon{
	
	public long getEnergy(NBTTagCompound nbt);
	
	public long getDepletionPerShot(NBTTagCompound nbt);
	
	public long rechargeRate(NBTTagCompound nbt);
	
}
