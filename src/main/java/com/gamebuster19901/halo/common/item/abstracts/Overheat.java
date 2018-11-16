package com.gamebuster19901.halo.common.item.abstracts;

import net.minecraft.nbt.NBTTagCompound;

public interface Overheat {

	public float getTemp(NBTTagCompound nbt);
	
	public float getMaxTemp(NBTTagCompound nbt);
	
}
