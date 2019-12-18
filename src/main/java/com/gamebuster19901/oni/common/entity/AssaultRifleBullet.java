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

import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class AssaultRifleBullet extends OniProjectile{
	public static SoundEvent dischargeSound;
	
	public AssaultRifleBullet(EntityType type, World worldIn) {
		super(type, worldIn);
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
		this.gun = (CompoundNBT) shootable.serializeNBT();
	}

	@Override
	public CompoundNBT getGun() {
		return gun;
	}

	@Override
	protected void registerData() {}

	@Override
	public SoundEvent getDischargeSound() {
		return dischargeSound;
	}

	@Override
	public SoundEvent getImpactSound() {
		return null;
	}

	@Override
	public SoundEvent getIdleSound() {
		return null;
	}

}
