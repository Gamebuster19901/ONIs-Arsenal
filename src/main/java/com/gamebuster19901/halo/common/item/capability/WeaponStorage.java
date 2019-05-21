package com.gamebuster19901.halo.common.item.capability;

import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class WeaponStorage implements Capability.IStorage<Weapon>{

	public static final WeaponStorage INSTANCE = new WeaponStorage();
	
	public static final String FIRE_RATE = "fireRate";
	public static final String AUTOMATIC = "isAutomatic";
	public static final String NEXT_FIRE = "nextFire";
	
	@Override
	public INBTBase writeNBT(Capability<Weapon> capability, Weapon instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		
		nbt.putInt(FIRE_RATE, instance.getFireRate());
		nbt.putBoolean(AUTOMATIC, instance.isAutomatic());
		nbt.putByte(NEXT_FIRE, instance.getTimeUntilNextFire());
		
		return nbt;
	}

	@Override
	public void readNBT(Capability<Weapon> capability, Weapon instance, EnumFacing side, INBTBase tag) {
		NBTTagCompound nbt = (NBTTagCompound) tag;
		
		instance.setFireRate(nbt.getInt(FIRE_RATE));
		instance.setAutomatic(nbt.getBoolean(AUTOMATIC));
		instance.setTimeUntilNextFire(nbt.getByte(NEXT_FIRE));
	}

}
