package com.gamebuster19901.halo.common.entity;

import com.gamebuster19901.halo.common.util.EasyLocalization;

import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AssaultRifleBullet extends ProjectileEntity implements EasyLocalization{
	
	public static SoundEvent sound;
	
	public AssaultRifleBullet(World worldIn) {
		super(worldIn);
		this.setSize(1f / 16, 1f / 16);
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.world != null && !this.isDead) {
			if(this.ticksExisted == 1) {
				float pitch = this.rand.nextFloat() * (1.5f - 1) + 1;
				this.world.playSound(posX, posY, posZ, sound, SoundCategory.NEUTRAL, 1f, pitch, false);
				//this.world.spawnAlwaysVisibleParticle(p_190523_1_, p_190523_2_, p_190523_4_, p_190523_6_, p_190523_8_, p_190523_10_, p_190523_12_, p_190523_14_);
			}
			if(this.ticksExisted > 120 || this.ticksExisted < 1) {
				this.setDead();
				return;
			}
			if(!this.world.isRemote) {
				this.posX = this.posX + this.getLookVec().x;
				this.posY = this.posY + this.getLookVec().y;
				this.posZ = this.posZ + this.getLookVec().z;
			}
		}
	}

}
