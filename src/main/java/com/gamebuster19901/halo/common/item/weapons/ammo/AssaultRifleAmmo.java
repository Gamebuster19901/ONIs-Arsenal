package com.gamebuster19901.halo.common.item.weapons.ammo;

import com.gamebuster19901.halo.common.item.abstracts.Ammo;
import com.gamebuster19901.halo.common.item.abstracts.Projectile;

import net.minecraft.entity.projectile.EntityDragonFireball;

public class AssaultRifleAmmo extends Ammo{
	
	public static final AssaultRifleAmmo INSTANCE = new AssaultRifleAmmo();
	
	public AssaultRifleAmmo() {
		super(new Projectile(new EntityDragonFireball(null).serializeNBT()), 36);
	}

}
