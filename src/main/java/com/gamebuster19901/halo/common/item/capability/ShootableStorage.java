package com.gamebuster19901.halo.common.item.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ShootableStorage implements IStorage<Shootable>{

	public static final ShootableStorage INSTANCE = new ShootableStorage();
	
	public static final String MAX_BLOOM = "maxBloom";
	public static final String BLOOM_I = "bloomI";
	public static final String BLOOM_D = "bloomD";
	public static final String VELOCITY = "muzzleVelocity";
	public static final String MIN_RECOIL_X = "minRecoilX";
	public static final String MAX_RECOIL_X = "maxRecoilX";
	public static final String MIN_RECOIL_Y = "minRecoilY";
	public static final String MAX_RECOIL_Y = "maxRecoilY";
	public static final String PROJECTILE = "projectile";
	
	@Override
	public NBTBase writeNBT(Capability<Shootable> capability, Shootable instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		
		nbt.setFloat(MAX_BLOOM, instance.getMaxBloom());
		nbt.setFloat(BLOOM_I, instance.getBloomIncreasePerShot());
		nbt.setFloat(BLOOM_D, instance.getBloomDecreasePerTick());
		nbt.setFloat(VELOCITY, instance.getMuzzleVelocity());
		nbt.setFloat(MIN_RECOIL_X, instance.getMinHorizontalRecoil());
		nbt.setFloat(MAX_RECOIL_X, instance.getMaxHorizontalRecoil());
		nbt.setFloat(MIN_RECOIL_Y, instance.getMinVerticalRecoil());
		nbt.setFloat(MAX_RECOIL_Y, instance.getMaxVerticalRecoil());
		nbt.setTag(PROJECTILE, instance.getProjectile());
		
		return nbt;
	}

	@Override
	public void readNBT(Capability<Shootable> capability, Shootable shootable, EnumFacing side, NBTBase tag) {
		NBTTagCompound nbt = (NBTTagCompound) tag;
		
		shootable.setMaxBloom(nbt.getFloat(MAX_BLOOM));
		shootable.setBloomIncreasePerShot(nbt.getFloat(BLOOM_I));
		shootable.setBloomDecreasePerTick(nbt.getFloat(BLOOM_D));
		shootable.setMuzzleVelocity(nbt.getFloat(VELOCITY));
		shootable.setMinHorizontalRecoil(nbt.getFloat(MIN_RECOIL_X));
		shootable.setMaxHorizontalRecoil(nbt.getFloat(MAX_RECOIL_X));
		shootable.setMinVerticalRecoil(nbt.getFloat(MIN_RECOIL_Y));
		shootable.setMaxVerticalRecoil(nbt.getFloat(MAX_RECOIL_Y));
		shootable.setProjectile(nbt.getCompoundTag(PROJECTILE));
	}

}
