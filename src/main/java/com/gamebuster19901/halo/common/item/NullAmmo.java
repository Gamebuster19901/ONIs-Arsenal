package com.gamebuster19901.halo.common.item;

import com.gamebuster19901.halo.common.item.abstracts.Ammo;
import com.gamebuster19901.halo.common.item.abstracts.Projectile;

import net.minecraft.entity.projectile.EntitySnowball;

public class NullAmmo extends Ammo {

	private static final Projectile snowball = new Projectile(new EntitySnowball(null).serializeNBT());
	
	public static final NullAmmo INSTANCE = new NullAmmo();
	
	public NullAmmo() {
		super(snowball, 0);
	}

}
