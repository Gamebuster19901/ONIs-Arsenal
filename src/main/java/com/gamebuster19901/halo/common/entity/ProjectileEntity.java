package com.gamebuster19901.halo.common.entity;

import java.util.UUID;

import com.gamebuster19901.halo.common.item.capability.ShooterOwner;
import com.gamebuster19901.halo.common.util.EasyLocalization;

import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class ProjectileEntity extends HaloEntity implements ShooterOwner{
	
	public NBTTagCompound gun;
	public UUID shooter;
	
	public ProjectileEntity(EntityType type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound ret = new NBTTagCompound();
		ret.putString("id", EasyLocalization.getResourceLocation(getClass()).toString());
		ret.putInt("ticksExisted", this.ticksExisted);
		this.writeUnlessRemoved(ret);
		return ret;
	}
	
	@Override
	protected void readAdditional(NBTTagCompound compound) {
		this.ticksExisted = compound.getInt("ticksExisted");
		if(compound.contains("gun")) {
			this.gun = compound.getCompound("gun");
		}
		if(compound.contains("shooter")) {
			this.shooter = UUID.fromString(compound.getString("shooter"));
		}
	}

	@Override
	protected void writeAdditional(NBTTagCompound compound) {
		compound.putInt("ticksExisted", this.ticksExisted);
		if(gun != null) {
			compound.put("gun", gun);
		}
		if(shooter != null) {
			compound.putString("shooter", shooter.toString());
		}
	}

}
