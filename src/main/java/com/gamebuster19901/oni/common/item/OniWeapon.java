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

package com.gamebuster19901.oni.common.item;

import static com.gamebuster19901.oni.Main.MODID;

import com.gamebuster19901.guncore.common.item.abstracts.HeldWeapon;

public class OniWeapon extends HeldWeapon {
	
	@Override
	public String getModId() {
		return MODID;
	}

}
