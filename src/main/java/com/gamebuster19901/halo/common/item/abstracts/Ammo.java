package com.gamebuster19901.halo.common.item.abstracts;

import com.gamebuster19901.halo.common.util.EasyLocalization;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

public abstract class Ammo extends Item implements EasyLocalization{
	private final Projectile projectile;
	
	public Ammo(Projectile projectile, int maxStackSize) {
		super();
		this.setRegistryName(getResourceLocation());
		this.setTranslationKey(getEZTranslationKey());
		this.projectile = projectile;
		this.setMaxStackSize(maxStackSize);
	}
	
	public final NBTTagCompound getProjectile() {
		return projectile.getProjectileNBT();
	}
}
