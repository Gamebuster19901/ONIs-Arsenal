package com.gamebuster19901.halo.common.entity;

import java.util.UUID;

import com.gamebuster19901.halo.common.item.capability.ShooterOwner;
import com.gamebuster19901.halo.common.util.EasyLocalization;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class ProjectileEntity extends Entity implements ShooterOwner{
	
	public NBTTagCompound gun;
	public UUID shooter;
	
	public ProjectileEntity(World worldIn) {
		super(worldIn);
	}
	
	@Override
	public NBTTagCompound serializeNBT() {
        NBTTagCompound ret = new NBTTagCompound();
        ret.setString("id", EasyLocalization.getResourceLocation(getClass()).toString());
        ret.setInteger("ticksExisted", this.ticksExisted);
        return this.writeToNBT(ret);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.posX = compound.getDouble("posX");
		this.posY = compound.getDouble("posY");
		this.posZ = compound.getDouble("posZ");
		this.motionX = compound.getDouble("motionX");
		this.motionY = compound.getDouble("motionY");
		this.motionZ = compound.getDouble("motionZ");
		this.ticksExisted = compound.getInteger("ticksExisted");
		if(compound.hasKey("gun")) {
			this.gun = compound.getCompoundTag("gun");
		}
		if(compound.hasKey("shooter")) {
			this.shooter = UUID.fromString(compound.getString("shooter"));
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setDouble("posX", this.posX);
		compound.setDouble("posY", this.posY);
		compound.setDouble("posZ", this.posZ);
		compound.setDouble("motionX", this.motionX);
		compound.setDouble("motionY", this.motionY);
		compound.setDouble("motionZ", this.motionZ);
		compound.setInteger("ticksExisted", this.ticksExisted);
		if(gun != null) {
			compound.setTag("gun", gun);
		}
		if(shooter != null) {
			compound.setString("shooter", shooter.toString());
		}
	}

}
