package com.gamebuster19901.halo.common.item.capability;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class WeaponDefaultImpl implements Weapon{
	@CapabilityInject(Weapon.class)
	public static Capability<Weapon> CAPABILITY = null;
	
	protected int fireRate;
	protected boolean isAutomatic;
	
	byte nextFire = 0;
	
	public WeaponDefaultImpl(int baseFireRate, boolean isAutomatic) {
		this.fireRate = baseFireRate;
		this.isAutomatic = isAutomatic;
	}
	
	@Override
	public void fire(Entity shooter) {
		nextFire = getTimeUntilNextFire();
	}

	@Override
	public int getFireRate() {
		return fireRate;
	}
	
	@Override
	public void setFireRate(int rate) {
		this.fireRate = rate;
	}

	@Override
	public byte getTimeUntilNextFire() {
		return nextFire;
	}
	
	@Override
	public void setTimeUntilNextFire(byte nextFire) {
		this.nextFire = nextFire;
	}

	@Override
	public boolean isAutomatic() {
		return isAutomatic;
	}
	
	@Override
	public void setAutomatic(boolean isAutomatic) {
		this.isAutomatic = isAutomatic;
	}

	@SubscribeEvent
	@Deprecated
	@Override
	public void onTick(WorldTickEvent e) {
		if(e.world.provider.getDimension() == 0 || e.side == Side.CLIENT) {
			update();
		}
	}

	@Override
	public void update() {
		if(nextFire > 0) {
			nextFire--;
		}
	}
}
