package com.gamebuster19901.oni.common.entity;

import static com.gamebuster19901.oni.Main.MODID;

import com.gamebuster19901.guncore.common.entity.ProjectileEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class HaloProjectile extends ProjectileEntity{

	public HaloProjectile(EntityType type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public String getModId() {
		return MODID;
	}

}
