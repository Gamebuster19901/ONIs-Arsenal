package com.gamebuster19901.halo.common.entity.capability.shooterOwner;

import java.util.concurrent.Callable;

import com.gamebuster19901.halo.common.item.capability.shootable.ShootableFactory;

import net.minecraft.entity.Entity;

public class ShooterOwnerFactory implements Callable<ShooterOwner>{

	public static final ShooterOwnerFactory INSTANCE = new ShooterOwnerFactory();
	
	@Override
	public ShooterOwner call() throws Exception {
		return new ShooterOwnerDefaultImpl((Entity)null, ShootableFactory.INSTANCE.call());
	}

}
