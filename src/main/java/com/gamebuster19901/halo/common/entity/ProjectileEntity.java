package com.gamebuster19901.halo.common.entity;

import com.gamebuster19901.halo.common.util.EasyLocalization;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class ProjectileEntity extends Entity{

	public ProjectileEntity(World worldIn) {
		super(worldIn);
	}
	
	@Override
	public NBTTagCompound serializeNBT() {
        NBTTagCompound ret = new NBTTagCompound();
        ret.setString("id", EasyLocalization.getResourceLocation(getClass()).toString());
        return this.writeToNBT(ret);
	}

}
