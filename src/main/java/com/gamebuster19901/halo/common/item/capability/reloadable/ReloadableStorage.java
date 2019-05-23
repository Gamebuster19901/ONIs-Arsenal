package com.gamebuster19901.halo.common.item.capability.reloadable;

import com.gamebuster19901.halo.common.item.NullAmmo;
import com.gamebuster19901.halo.common.item.abstracts.Ammo;

import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.registries.ForgeRegistries;

public class ReloadableStorage implements IStorage<Reloadable>{

	public static final ReloadableStorage INSTANCE = new ReloadableStorage();
	
	public static final String MAG_SIZE = "magSize";
	public static final String RELOAD_TIME = "reloadTime";
	
	public static final String AMOUNT_LOADED = "amountLoaded";
	public static final String RELOAD_PROGRESS = "reloadProgress";
	public static final String RELOADING = "isReloading";
	public static final String AMMO = "ammoType";
	
	@Override
	public INBTBase writeNBT(Capability<Reloadable> capability, Reloadable instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		
		nbt.putInt(MAG_SIZE, instance.getMagazineSize());
		nbt.putInt(RELOAD_TIME, instance.getReloadTime());
		
		nbt.putInt(AMOUNT_LOADED, instance.getAmountInMagazine());
		nbt.putInt(RELOAD_PROGRESS, instance.getReloadProgress());
		nbt.putBoolean(RELOADING, instance.isReloading());
		nbt.putString(AMMO, instance.getAmmoType().getRegistryName().toString());
		
		return nbt;
	}

	@Override
	public void readNBT(Capability<Reloadable> capability, Reloadable reloadable, EnumFacing side, INBTBase tag) {
		NBTTagCompound nbt = (NBTTagCompound) tag;

		reloadable.setMagazineSize(nbt.getInt(MAG_SIZE));
		reloadable.setReloadTime(nbt.getInt(RELOAD_TIME));
		
		reloadable.setAmountInMagazine(nbt.getInt(AMOUNT_LOADED));
		reloadable.setReloadProgress(nbt.getInt(RELOAD_PROGRESS));
		reloadable.setIsReloading(nbt.getBoolean(RELOADING));
		ResourceLocation ammo = new ResourceLocation(nbt.getString(AMMO));
		if(ForgeRegistries.ITEMS.containsKey(ammo)) {
			reloadable.setAmmoType((Ammo) ForgeRegistries.ITEMS.getValue(ammo));
		}
		else {
			reloadable.setAmmoType(NullAmmo.INSTANCE);
		}
		
	}

}
