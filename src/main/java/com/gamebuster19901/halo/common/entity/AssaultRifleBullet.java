package com.gamebuster19901.halo.common.entity;

import com.gamebuster19901.halo.Main;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class AssaultRifleBullet extends Entity{

	public AssaultRifleBullet(World worldIn) {
		super(worldIn);
		this.setSize(1f / 16f, 1f / 16f);
		if(worldIn != null) {
			this.world.playSound(posX, posY, posZ, soundIn, SoundCategory.PLAYERS, 100f, 1f, true);
			this.world.spawnAlwaysVisibleParticle(p_190523_1_, p_190523_2_, p_190523_4_, p_190523_6_, p_190523_8_, p_190523_10_, p_190523_12_, p_190523_14_);
		}
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
	}
	
	@Override
	public void onUpdate() {
		this.motionX = this.ge
	}

}
