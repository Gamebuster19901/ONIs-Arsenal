package com.gamebuster19901.halo.common.item.weapons.ammo;

import com.gamebuster19901.halo.common.entity.AssaultRifleBullet;
import com.gamebuster19901.halo.common.item.abstracts.Ammo;
import com.gamebuster19901.halo.common.item.abstracts.Projectile;

public class AssaultRifleAmmo extends Ammo{
	
	public static final AssaultRifleAmmo INSTANCE = new AssaultRifleAmmo();
	
	public AssaultRifleAmmo() {
		super(new Projectile(new AssaultRifleBullet(null).serializeNBT()), 36);
	}

}
