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
