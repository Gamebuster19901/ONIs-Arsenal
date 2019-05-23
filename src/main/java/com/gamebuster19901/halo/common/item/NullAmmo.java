package com.gamebuster19901.halo.common.item;

import com.gamebuster19901.halo.common.item.abstracts.Ammo;
import com.gamebuster19901.halo.common.item.abstracts.Projectile;

import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.world.dimension.DimensionType;

public class NullAmmo extends Ammo {

	private static final Projectile snowball;
	static {
		EntitySnowball entity = new EntitySnowball(null);
		entity.dimension = DimensionType.OVERWORLD;
		snowball = new Projectile(entity.serializeNBT());
	}
	 
	
	public static final NullAmmo INSTANCE = new NullAmmo();
	
	public NullAmmo() {
		super(snowball, 1);
	}

}
