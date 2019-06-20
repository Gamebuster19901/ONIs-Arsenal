package com.gamebuster19901.halo.common.item.weapons.plasmapistol;

import com.gamebuster19901.halo.capability.common.energy.EnergyDefaultImpl;
import com.gamebuster19901.halo.capability.common.heat.OverheatDefaultImpl;
import com.gamebuster19901.halo.capability.common.item.combined.WeaponShootableEnergyOverheatImpl;
import com.gamebuster19901.halo.capability.common.item.shootable.ShootableDefaultImpl;
import com.gamebuster19901.halo.capability.common.item.weapon.Weapon;
import com.gamebuster19901.halo.capability.common.item.weapon.WeaponDefaultImpl;
import com.gamebuster19901.halo.common.item.NullAmmo;

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
	
	public PlasmaPistolImpl() {
		super(
			new WeaponDefaultImpl(firingRate, isAutomatic),
			new ShootableDefaultImpl(maxBloom, bloomIncrease, bloomDecrease, muzzleVelocity, minVerticalRecoil, maxVerticalRecoil, minHorizontalRecoil, maxHorizontalRecoil, NullAmmo.INSTANCE.getProjectile()),
			new EnergyDefaultImpl(1000, 10, 50, 0),
			new OverheatDefaultImpl(0, 100, 1, 14)
		);
	}

}
