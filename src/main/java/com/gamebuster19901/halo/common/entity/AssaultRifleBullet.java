package com.gamebuster19901.halo.common.entity;

import java.util.UUID;

import com.gamebuster19901.halo.common.item.capability.Shootable;
import com.gamebuster19901.halo.common.util.EasyLocalization;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class AssaultRifleBullet extends ProjectileEntity implements EasyLocalization{
	
	public static SoundEvent shootingSound;
	
	public NBTTagCompound gun;
	public UUID shooter;
	
	public AssaultRifleBullet(World worldIn) {
		super(worldIn);
		this.setSize(1f / 16, 1f / 16);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.world != null && !this.isDead) {
			if(this.ticksExisted == 1) {
				float pitch = this.rand.nextFloat() * (1.5f - 1) + 1;
				this.world.playSound(posX, posY, posZ, shootingSound, SoundCategory.NEUTRAL, 1f, pitch, false);
				//this.world.spawnAlwaysVisibleParticle(p_190523_1_, p_190523_2_, p_190523_4_, p_190523_6_, p_190523_8_, p_190523_10_, p_190523_12_, p_190523_14_);
			}
			if(this.ticksExisted > 120 || this.ticksExisted < 1) {
				this.setDead();
				return;
			}
			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			this.markVelocityChanged();
		}
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.posX = compound.getDouble("posX");
		this.posY = compound.getDouble("posY");
		this.posZ = compound.getDouble("posZ");
		this.motionX = compound.getDouble("motionX");
		this.motionY = compound.getDouble("motionY");
		this.motionZ = compound.getDouble("motionZ");
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
		if(gun != null) {
			compound.setTag("gun", gun);
		}
		if(shooter != null) {
			compound.setString("shooter", shooter.toString());
		}
	}

	@Override
	public UUID getShooter() {
		return shooter;
	}

	@Override
	public void setShooter(UUID uuid) {
		this.shooter = uuid;
	}

	@Override
	public void setGun(Shootable shootable) {
		this.gun = (NBTTagCompound) shootable.serializeNBT();
	}

	@Override
	public NBTTagCompound getGun() {
		return gun;
	}

}
