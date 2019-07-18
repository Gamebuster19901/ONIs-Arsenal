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
import com.gamebuster19901.guncore.common.util.EasyLocalization;
import com.gamebuster19901.oni.common.entity.AssaultRifleBullet;

import net.minecraftforge.registries.ForgeRegistries;

public class AssaultRifleAmmo extends HaloAmmo{
	
	public static final AssaultRifleAmmo INSTANCE = new AssaultRifleAmmo();
	
	public AssaultRifleAmmo() {
		super(new Projectile(new AssaultRifleBullet(ForgeRegistries.ENTITIES.getValue(EasyLocalization.getResourceLocation("oni", AssaultRifleBullet.class)), null)), 36);
	}

}
