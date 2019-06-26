package com.gamebuster19901.halo.common.entity;

import java.util.UUID;

import com.gamebuster19901.guncore.capability.common.item.shootable.Shootable;

import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class AssaultRifleBullet extends HaloProjectile{
	public static EntityType TYPE;
	
	public static SoundEvent shootingSound;
	
	public AssaultRifleBullet(World worldIn) {
		super(TYPE, worldIn);
		this.setSize(1f / 16, 1f / 16);
	}

	@Override
	public UUID getShooter() {
		return shooter;
	}

	@Override
	public void setShooter(UUID uuid) {
		this.shooter = uuid;
	}

	@Override
	public void setGun(Shootable shootable) {
		this.gun = (NBTTagCompound) shootable.serializeNBT();
	}

	@Override
	public NBTTagCompound getGun() {
		return gun;
	}

	@Override
	protected void registerData() {}

	@Override
	public SoundEvent getShootingSound() {
		return shootingSound;
	}

	@Override
	public SoundEvent getImpactSound() {
		return null;
	}

}
