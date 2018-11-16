package com.gamebuster19901.halo.common.item.capability;

import java.util.UUID;

import net.minecraft.entity.Entity;

public interface ShooterOwner {
	
	public UUID getShooter();
	
	public default void setShooter(Entity entity) {
		if(entity != null) {
			setShooter(entity.getUniqueID());
		}
	}
	
	public void setShooter(UUID uuid);
	
}
