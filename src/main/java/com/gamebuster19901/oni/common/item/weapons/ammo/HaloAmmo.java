package com.gamebuster19901.oni.common.item.weapons.ammo;

import static com.gamebuster19901.oni.Main.MODID;

import com.gamebuster19901.guncore.common.item.abstracts.Ammo;
import com.gamebuster19901.guncore.common.item.abstracts.Projectile;

public class HaloAmmo extends Ammo{

	public HaloAmmo(Projectile projectile, int maxStackSize) {
		super(projectile, maxStackSize);
	}

	@Override
	public String getModId() {
		return MODID;
	}

}
