/*
 * Oni's Arsenal Copyright 2019 - 2020 Gamebuster19901
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

import com.gamebuster19901.guncore.capability.common.charge.Charge;
import com.gamebuster19901.guncore.capability.common.item.shootable.Shootable;

import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class PlasmaPistolBullet extends EnergyProjectile {
	
	public static SoundEvent normalDischargeSound;
	public static SoundEvent bigDischargeSound;
	public static SoundEvent normalImpactSound;
	public static SoundEvent bigImpactSound;
	public static SoundEvent normalIdleSound;
	public static SoundEvent bigIdleSound;
	
	private float size;
	
	public PlasmaPistolBullet(EntityType type, World worldIn) {
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
		this.gun = shootable.serializeNBT();
		setSize(shootable);
	}

	@Override
	public CompoundNBT getGun() {
		return gun;
	}
	
	private void setSize(Shootable shootable) {
		size = 0.05f;
		if(shootable instanceof Charge) {
			Charge chargableShootable = (Charge)shootable;
			size = chargableShootable.getChargeProgress();
		}
	}

	@Override
	public SoundEvent getDischargeSound() {
		if (size < 0.80) {
			return normalDischargeSound;
		}
		return bigDischargeSound;
	}

	@Override
	public SoundEvent getImpactSound() {
		if (size < 0.80) {
			return normalImpactSound;
		}
		return bigImpactSound;
	}
	
	@Override
	public SoundEvent getIdleSound() {
		if (size < 0.80) {
			return normalIdleSound;
		}
		return bigIdleSound;
	}

	@Override
	protected void registerData() {}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("big", true);
	}

}
