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

package com.gamebuster19901.oni.common.item.weapons.assaultrifle;

import com.gamebuster19901.guncore.capability.common.item.combined.WeaponShootableReloadableImpl;
import com.gamebuster19901.guncore.capability.common.item.reloadable.ReloadableDefaultImpl;
import com.gamebuster19901.guncore.capability.common.item.shootable.ShootableDefaultImpl;
import com.gamebuster19901.guncore.capability.common.item.weapon.Weapon;
import com.gamebuster19901.guncore.capability.common.item.weapon.WeaponDefaultImpl;
import com.gamebuster19901.guncore.common.item.NullAmmo;
import com.gamebuster19901.oni.proxy.Proxy;
import com.gamebuster19901.oni.proxy.ServerProxy;

public class AssaultRifleImpl extends WeaponShootableReloadableImpl{

	private static final int firingRate = 10;
	private static final float maxBloom = 4f;
	private static final float bloomIncrease = 1.2f;
	private static final float bloomDecrease = 0.12f;
	private static final float muzzleVelocity = (float) Weapon.FPSToMPT(2600);
	private static final boolean isAutomatic = true;
	private static final float minVerticalRecoil = 1f;
	private static final float maxVerticalRecoil = 1f;
	private static final float minHorizontalRecoil = 0f;
	private static final float maxHorizontalRecoil = 0f;
	private static final int magSize = 36;
	private static final int reloadTime = 20;
	
	public AssaultRifleImpl(Proxy side) {
		super(side instanceof ServerProxy ? getServerImpl() : getClientImpl());
	}
	
	private static Object[] getServerImpl() {
		return new Object[] {
			new WeaponDefaultImpl(3, firingRate, isAutomatic), 
			new ShootableDefaultImpl(3 + 1f / 3f, maxBloom, bloomIncrease, bloomDecrease, muzzleVelocity, minVerticalRecoil, maxVerticalRecoil, minHorizontalRecoil, maxHorizontalRecoil, NullAmmo.INSTANCE.getProjectile()),
			new ReloadableDefaultImpl(magSize, reloadTime)
		};
	}
	
	private static Object[] getClientImpl() {
		return new Object[] {
				new WeaponDefaultImpl(3, firingRate, isAutomatic), 
				new ShootableDefaultImpl(3 + 1f / 3f, maxBloom, bloomIncrease, bloomDecrease, muzzleVelocity, minVerticalRecoil, maxVerticalRecoil, minHorizontalRecoil, maxHorizontalRecoil, NullAmmo.INSTANCE.getProjectile()),
				new ReloadableDefaultImpl(magSize, reloadTime),
				new AssaultRifleReticleImpl(),
				new AssaultRifleOverlayImpl()
		};
	}
	
}
