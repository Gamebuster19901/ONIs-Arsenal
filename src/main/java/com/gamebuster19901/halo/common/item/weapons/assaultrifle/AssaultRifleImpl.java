package com.gamebuster19901.halo.common.item.weapons.assaultrifle;

import com.gamebuster19901.halo.common.item.NullAmmo;
import com.gamebuster19901.halo.common.item.capability.combined.WeaponShootableReloadableImpl;
import com.gamebuster19901.halo.common.item.capability.reloadable.ReloadableDefaultImpl;
import com.gamebuster19901.halo.common.item.capability.shootable.ShootableDefaultImpl;
import com.gamebuster19901.halo.common.item.capability.weapon.Weapon;
import com.gamebuster19901.halo.common.item.capability.weapon.WeaponDefaultImpl;

public class AssaultRifleImpl extends WeaponShootableReloadableImpl{

	private static final int firingRate = 10;
	private static final float maxBloom = 4f;
	private static final float bloomIncrease = 0.1f;
	private static final float bloomDecrease = 0.02f;
	private static final float muzzleVelocity = (float) Weapon.FPSToMPT(20);
	private static final boolean isAutomatic = true;
	private static final float minVerticalRecoil = 1f;
	private static final float maxVerticalRecoil = 1f;
	private static final float minHorizontalRecoil = 0f;
	private static final float maxHorizontalRecoil = 0f;
	private static final int magSize = 36;
	private static final int reloadTime = 20;
	
	public AssaultRifleImpl() {
		super(new WeaponDefaultImpl(firingRate, isAutomatic), 
			  new ShootableDefaultImpl(maxBloom, bloomIncrease, bloomDecrease, muzzleVelocity, minVerticalRecoil, maxVerticalRecoil, minHorizontalRecoil, maxHorizontalRecoil, NullAmmo.INSTANCE.getProjectile()),
			  new ReloadableDefaultImpl(magSize, reloadTime));
	}
	
}
