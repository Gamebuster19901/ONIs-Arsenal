package com.gamebuster19901.halo.common.item.capability;

import java.util.UUID;

import net.minecraft.entity.Entity;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class ShooterOwnerDefaultImpl implements ShooterOwner{

	@CapabilityInject(ShooterOwner.class)
	public static Capability<ShooterOwner> CAPABILITY = null;
	
	private UUID shooter;
	
	public ShooterOwnerDefaultImpl(Entity shooter) {
		setShooter(shooter);
	}
	
	public ShooterOwnerDefaultImpl(UUID uuid) {
		setShooter(uuid);
	}

	@Override
	public UUID getShooter() {
		return shooter;
	}

	@Override
	public void setShooter(UUID uuid) {
		this.shooter = uuid;
	}

	
}
