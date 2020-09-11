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

package com.gamebuster19901.oni.common.item.weapons.plasmapistol;

import com.gamebuster19901.guncore.capability.common.energy.EnergyDefaultImpl;
import com.gamebuster19901.guncore.capability.common.heat.OverheatDefaultImpl;
import com.gamebuster19901.guncore.capability.common.item.combined.WeaponShootableEnergyOverheatImpl;
import com.gamebuster19901.guncore.capability.common.item.shootable.ShootableDefaultImpl;
import com.gamebuster19901.guncore.capability.common.item.weapon.Weapon;
import com.gamebuster19901.guncore.capability.common.item.weapon.WeaponDefaultImpl;
import com.gamebuster19901.guncore.common.item.abstracts.Projectile;
import com.gamebuster19901.guncore.common.util.Resourced;
import com.gamebuster19901.oni.common.entity.PlasmaPistolBullet;

import net.minecraftforge.registries.ForgeRegistries;

public class PlasmaPistolImpl extends WeaponShootableEnergyOverheatImpl {
	
	private static final int firingRate = 10;
	private static final float maxBloom = 4f;
	private static final float bloomIncrease = 1.2f;
	private static final float bloomDecrease = 0.12f;
	private static final float muzzleVelocity = (float) Weapon.FPSToMPT(10);
	private static final boolean isAutomatic = false;
	private static final float minVerticalRecoil = 1f;
	private static final float maxVerticalRecoil = 1f;
	private static final float minHorizontalRecoil = 0f;
	private static final float maxHorizontalRecoil = 0f;
	private static final Projectile projectile = new Projectile(new PlasmaPistolBullet(ForgeRegistries.ENTITIES.getValue(Resourced.getResourceLocation("oni", PlasmaPistolBullet.class)),null));
	
	public PlasmaPistolImpl() {
		super(
			new WeaponDefaultImpl(3, firingRate, isAutomatic),
			new ShootableDefaultImpl(maxBloom, bloomIncrease, bloomDecrease, muzzleVelocity, minVerticalRecoil, maxVerticalRecoil, minHorizontalRecoil, maxHorizontalRecoil, projectile.getProjectileNBT()),
			new EnergyDefaultImpl(1000, 10, 50, 0),
			new OverheatDefaultImpl(0, 100, 1, 14)
		);
	}

}
