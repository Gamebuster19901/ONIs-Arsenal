package com.gamebuster19901.halo.common.item.capability;

import com.gamebuster19901.halo.common.item.NullAmmo;
import com.gamebuster19901.halo.common.item.abstracts.Ammo;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ReloadableStorage implements IStorage<Reloadable>{

	public static final ReloadableStorage INSTANCE = new ReloadableStorage();
	
	public static final String MAG_SIZE = "magSize";
	public static final String RELOAD_TIME = "reloadTime";
	
	public static final String AMOUNT_LOADED = "amountLoaded";
	public static final String RELOAD_PROGRESS = "reloadProgress";
	public static final String RELOADING = "isReloading";
	public static final String AMMO = "ammoType";
	
	@Override
	public NBTBase writeNBT(Capability<Reloadable> capability, Reloadable instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		
		nbt.setInteger(MAG_SIZE, instance.getMagazineSize());
		nbt.setInteger(RELOAD_TIME, instance.getReloadTime());
		
		nbt.setInteger(AMOUNT_LOADED, instance.getAmountInMagazine());
		nbt.setInteger(RELOAD_PROGRESS, instance.getReloadProgress());
		nbt.setBoolean(RELOADING, instance.isReloading());
		nbt.setString(AMMO, instance.getAmmoType().getRegistryName().toString());
		
		return nbt;
	}

	@Override
	public void readNBT(Capability<Reloadable> capability, Reloadable reloadable, EnumFacing side, NBTBase tag) {
		NBTTagCompound nbt = (NBTTagCompound) tag;

		reloadable.setMagazineSize(nbt.getInteger(MAG_SIZE));
		reloadable.setReloadTime(nbt.getInteger(RELOAD_TIME));
		
		reloadable.setAmountInMagazine(nbt.getInteger(AMOUNT_LOADED));
		reloadable.setReloadProgress(nbt.getInteger(RELOAD_PROGRESS));
		reloadable.setIsReloading(nbt.getBoolean(RELOADING));
		ResourceLocation ammo = new ResourceLocation(nbt.getString(AMMO));
		if(Item.REGISTRY.containsKey(ammo)) {
			reloadable.setAmmoType((Ammo) Item.REGISTRY.getObject(ammo));
		}
		else {
			reloadable.setAmmoType(NullAmmo.INSTANCE);
		}
		
	}

}
