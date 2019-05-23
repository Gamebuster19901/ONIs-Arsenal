package com.gamebuster19901.halo.common.entity;

import com.gamebuster19901.halo.common.util.EasyLocalization;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public abstract class HaloEntity extends Entity{

	public HaloEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		if(worldIn == null) {
			this.dimension = DimensionType.OVERWORLD;
		}
	}
	
	@Override
	public boolean writeUnlessRemoved(NBTTagCompound compound) {
		String s = EasyLocalization.getResourceLocation(getClass()).toString();
		if (!this.removed && s != null) {
			compound.putString("id", s);
			this.writeWithoutTypeId(compound);
			return true;
		} else {
			return false;
		}
	}

}
