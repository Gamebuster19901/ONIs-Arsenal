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

package com.gamebuster19901.oni.common.item.weapons.ammo;

import com.gamebuster19901.guncore.common.item.abstracts.Projectile;
import com.gamebuster19901.guncore.common.util.Resourced;
import com.gamebuster19901.oni.common.entity.AssaultRifleBullet;

import net.minecraftforge.registries.ForgeRegistries;

public class AssaultRifleAmmo extends HaloAmmo{
	
	public static final AssaultRifleBullet BULLET = new AssaultRifleBullet(ForgeRegistries.ENTITIES.getValue(Resourced.getResourceLocation("oni", AssaultRifleBullet.class)), null);
	public static final AssaultRifleAmmo INSTANCE = new AssaultRifleAmmo(3f + 1f/3f);
	
	public AssaultRifleAmmo(float additionalDamage) {
		super(new Projectile(BULLET), 36, additionalDamage);
	}

}
