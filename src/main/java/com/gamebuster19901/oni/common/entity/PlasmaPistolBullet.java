/*
 * Oni's Arsenal Copyright 2019 Gamebuster19901
 * 
 * Halo Copyright Microsoft Corporation
 *
 * Oni's Arsenal was created under Microsoft's
 * "Game Content Usage Rules", using assets
 * based on the Halo universe. Oni's Arsenal
 * is not endorsed by or affiliated with
 * Microsoft.
 * 
 * The Game Content Usage Rules can be found at:
 * 
 * https://www.xbox.com/en-us/developers/rules
 * 
 */

package com.gamebuster19901.oni.common.entity;

import java.util.UUID;

import com.gamebuster19901.guncore.capability.common.item.shootable.Shootable;
import com.gamebuster19901.guncore.common.util.EasyLocalization;

import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class PlasmaPistolBullet extends EnergyProjectile implements EasyLocalization{
	public static EntityType TYPE;
	
	public static SoundEvent shootingSound;
	
	
	public PlasmaPistolBullet(World worldIn) {
		super(TYPE, worldIn);
		this.setSize(2f / 16, 1f / 16);
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
		this.gun = shootable.serializeNBT();
	}

	@Override
	public NBTTagCompound getGun() {
		return gun;
	}

	@Override
	public SoundEvent getShootingSound() {
		return shootingSound;
	}

	@Override
	public SoundEvent getImpactSound() {
		return null;
	}

	@Override
	protected void registerData() {}

}
