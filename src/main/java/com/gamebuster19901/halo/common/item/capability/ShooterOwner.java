package com.gamebuster19901.halo.common.item.capability;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public interface ShooterOwner {
	
	public default void setShooter(Entity entity) {
		if(entity != null) {
			setShooter(entity.getUniqueID());
		}
	}
	
	public UUID getShooter();
	
	public void setShooter(UUID uuid);
	
	public void setGun(Shootable shootable);
	
	public NBTTagCompound getGun();
	
}
