package com.gamebuster19901.halo.common.item.weapons.ammo;

import com.gamebuster19901.guncore.common.item.abstracts.Ammo;
import com.gamebuster19901.guncore.common.item.abstracts.Projectile;

import static com.gamebuster19901.halo.Main.MODID;

public class HaloAmmo extends Ammo{

	public HaloAmmo(Projectile projectile, int maxStackSize) {
		super(projectile, maxStackSize);
	}

	@Override
	public String getModId() {
		return MODID;
	}

}
