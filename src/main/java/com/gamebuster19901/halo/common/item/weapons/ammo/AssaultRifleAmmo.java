package com.gamebuster19901.halo.common.item.weapons.ammo;

import com.gamebuster19901.guncore.common.item.abstracts.Projectile;
import com.gamebuster19901.halo.common.entity.AssaultRifleBullet;

public class AssaultRifleAmmo extends HaloAmmo{
	
	public static final AssaultRifleAmmo INSTANCE = new AssaultRifleAmmo();
	
	public AssaultRifleAmmo() {
		super(new Projectile(new AssaultRifleBullet(null)), 36);
	}

}
