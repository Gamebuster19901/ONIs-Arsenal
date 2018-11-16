package com.gamebuster19901.halo.common.item.capability;

import java.util.concurrent.Callable;
import net.minecraft.entity.Entity;

public class ShooterOwnerFactory implements Callable<ShooterOwner>{

	public static final ShooterOwnerFactory INSTANCE = new ShooterOwnerFactory();
	
	@Override
	public ShooterOwner call() throws Exception {
		return new ShooterOwnerDefaultImpl((Entity)null);
	}

}
