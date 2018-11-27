package com.gamebuster19901.halo.common.entity;

import com.gamebuster19901.halo.common.util.EasyLocalization;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class AssaultRifleBullet extends ProjectileEntity implements EasyLocalization{

	public static final ResourceLocation SHOT_SOUND = EasyLocalization.getResourceLocation(AssaultRifleBullet.class);
	
	public AssaultRifleBullet(World worldIn) {
		super(worldIn);
		this.setSize(1f / 16, 1f / 16);
		if(this.world != null) {
			float pitch = this.rand.nextFloat() * (1.5f - 1) + 1;
			this.world.playSound(posX, posY, posZ, SoundEvent.REGISTRY.getObject(SHOT_SOUND), SoundCategory.NEUTRAL, 100f, pitch, false);
			//this.world.spawnAlwaysVisibleParticle(p_190523_1_, p_190523_2_, p_190523_4_, p_190523_6_, p_190523_8_, p_190523_10_, p_190523_12_, p_190523_14_);
		}
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
		
	}

}
