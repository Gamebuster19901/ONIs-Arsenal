package com.gamebuster19901.halo.common.item.capability.combined;

import java.util.Random;

import com.gamebuster19901.halo.common.item.NullAmmo;
import com.gamebuster19901.halo.common.item.abstracts.Ammo;
import com.gamebuster19901.halo.common.item.capability.Reloadable;
import com.gamebuster19901.halo.common.item.capability.Shootable;
import com.gamebuster19901.halo.common.item.capability.Weapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;

public class WeaponShootableReloadableImpl implements Weapon, Shootable, Reloadable{
	
	private Weapon weapon = null;
	private Shootable shootable = null;
	private Reloadable reloadable = null;
	
	public WeaponShootableReloadableImpl(Object... capabilityImplementations) {
		for(Object o : capabilityImplementations) {
			if(o instanceof Weapon) {
				if(weapon == null) {
					weapon = (Weapon) o;
				}
				else {
					throw new IllegalArgumentException("Weapon instance specified twice!");
				}
			}
			if(o instanceof Shootable) {
				if(shootable == null) {
					shootable = (Shootable) o;
				}
				else {
					throw new IllegalArgumentException("Shootable instance specified twice!");
				}
			}
			if(o instanceof Reloadable) {
				if(reloadable == null) {
					reloadable = (Reloadable) o;
				}
				else {
					throw new IllegalArgumentException("Reloadable instance specified twice!");
				}
			}
		}
		
		if(weapon == null || shootable == null || reloadable == null) {
			String err = "The following types were not specified :";
	
			if(weapon == null) {
				err = err + "weapon, ";
			}
			if(shootable == null) {
				err = err + "shootable, ";
			}
			if(reloadable == null) {
				err = err + "reloadable, ";
			}
			
			err = err.substring(err.lastIndexOf(", \""));
			
			throw new IllegalArgumentException(err);
		}
		
	}

	@Override
	public int getMagazineSize() {
		return reloadable.getMagazineSize();
	}

	@Override
	public int getAmountInMagazine() {
		return reloadable.getAmountInMagazine();
	}

	@Override
	public void setAmountInMagazine(int amount) {
		reloadable.setAmountInMagazine(amount);
	}

	@Override
	public int getReloadTime() {
		return reloadable.getAmountInMagazine();
	}

	@Override
	public int getReloadProgress() {
		return reloadable.getReloadProgress();
	}

	@Override
	public Ammo getAmmoType() {
		return reloadable.getAmmoType();
	}
	
	@Override
	public void setAmmoType(Ammo ammo) {
		reloadable.setAmmoType(ammo);
		setProjectile(ammo.getProjectile());
	}

	@Override
	public boolean isValidAmmo(Ammo ammo) {
		return reloadable.isValidAmmo(ammo);
	}

	@Override
	public boolean isReloading() {
		return reloadable.isReloading();
	}

	@Override
	public Ammo reload(IInventory... containers) {
		Ammo ammo = reloadable.reload(containers);
		setProjectile(ammo.getProjectile());
		return ammo;
	}

	@Override
	public float getBloom() {
		return shootable.getBloom();
	}

	@Override
	public float getMaxBloom() {
		return shootable.getMaxBloom();
	}

	@Override
	public float getBloomIncreasePerShot() {
		return shootable.getBloomIncreasePerShot();
	}

	@Override
	public float getBloomDecreasePerTick() {
		return shootable.getBloomDecreasePerTick();
	}

	@Override
	public float getMuzzleVelocity() {
		return shootable.getMuzzleVelocity();
	}

	@Override
	public float getMinVerticalRecoil() {
		return shootable.getVerticalRecoil();
	}

	@Override
	public float getMaxVerticalRecoil() {
		return shootable.getMaxVerticalRecoil();
	}

	@Override
	public float getMinHorizontalRecoil() {
		return shootable.getMinHorizontalRecoil();
	}

	@Override
	public float getMaxHorizontalRecoil() {
		return shootable.getMaxHorizontalRecoil();
	}
	
	@Override
	public void setProjectile(NBTTagCompound projectile) {
		shootable.setProjectile(projectile);
	}
	
	@Override
	public NBTTagCompound getProjectile() {
		return getAmmoType().getProjectile();
	}

	@Override
	public Random getRandom() {
		return shootable.getRandom();
	}

	@Override
	public void setRandom(Random random) {
		shootable.setRandom(random);
	}

	@Override
	public boolean canFire(Entity shooter) {
		return this.getAmountInMagazine() > 0 && !this.isReloading() && this.getTimeUntilNextFire() <= 0 && this.getAmmoType() != NullAmmo.INSTANCE;
	}
	
	@Override
	public void fire(Entity shooter) {
		weapon.fire(shooter);
		shootable.shoot(shooter);
		if(shooter instanceof EntityPlayer && ((EntityPlayer) shooter).isCreative()) {
			return;
		}
		reloadable.decreaseAmountInMag(1);
	}

	@Override
	public int getFireRate() {
		return weapon.getFireRate();
	}

	@Override
	public byte getTimeUntilNextFire() {
		return weapon.getTimeUntilNextFire();
	}

	@Override
	public boolean isAutomatic() {
		return weapon.isAutomatic();
	}

	@Override
	public void update() {
		weapon.update();
		shootable.update();
		reloadable.update();
	}

	@Override
	public void setFireRate(int rate) {
		weapon.setFireRate(rate);
	}

	@Override
	public void setTimeUntilNextFire(byte time) {
		weapon.setTimeUntilNextFire(time);
	}

	@Override
	public void setAutomatic(boolean isAutomatic) {
		weapon.setAutomatic(isAutomatic);
	}

	@Override
	public void setMaxBloom(float maxBloom) {
		shootable.setMaxBloom(maxBloom);
	}

	@Override
	public void setBloomIncreasePerShot(float bloomIncrease) {
		shootable.setBloomIncreasePerShot(bloomIncrease);
	}

	@Override
	public void setBloomDecreasePerTick(float bloomDecrease) {
		shootable.setBloomDecreasePerTick(bloomDecrease);
	}

	@Override
	public void setMuzzleVelocity(float velocity) {
		shootable.setMuzzleVelocity(velocity);
	}

	@Override
	public void setMinVerticalRecoil(float minVertical) {
		shootable.setMinVerticalRecoil(minVertical);
	}

	@Override
	public void setMaxVerticalRecoil(float maxVertical) {
		shootable.setMaxVerticalRecoil(maxVertical);
	}

	@Override
	public void setMinHorizontalRecoil(float minHorizontal) {
		shootable.setMinHorizontalRecoil(minHorizontal);
	}

	@Override
	public void setMaxHorizontalRecoil(float maxHorizontal) {
		shootable.setMaxHorizontalRecoil(maxHorizontal);
	}

	@Override
	public void setMagazineSize(int size) {
		reloadable.setMagazineSize(size);
	}

	@Override
	public void setReloadTime(int ticks) {
		reloadable.setReloadTime(ticks);
	}

	@Override
	public void setReloadProgress(int ticks) {
		reloadable.setReloadProgress(ticks);
	}

	@Override
	public void setIsReloading(boolean reloading) {
		reloadable.setIsReloading(reloading);
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = (NBTTagCompound) weapon.serializeNBT();
		nbt.merge((NBTTagCompound)shootable.serializeNBT());
		nbt.merge((NBTTagCompound)reloadable.serializeNBT());
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		weapon.deserializeNBT(nbt);
		shootable.deserializeNBT(nbt);
		reloadable.deserializeNBT(nbt);
	}

}
