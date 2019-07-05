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

package com.gamebuster19901.oni.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class EnergyProjectile extends HaloProjectile{

	public EnergyProjectile(EntityType type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void hitBlock(RayTraceResult rayTrace) {
		this.world.playSound(null, posX, posY, posZ, getImpactSound(), SoundCategory.NEUTRAL, 1f, getNextSoundPitch());
		onHit();
	}

}
