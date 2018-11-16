package com.gamebuster19901.halo.common.util;

import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class Time {
	private Time() {throw new InstantiationError("Timer should not be instantiated");}
	
	public static long getCurrentTick(World world) {
		if(world == null) {
			world = DimensionManager.getWorld(0);
			if(world == null) {
				throw new IllegalStateException("Cannot get the current tick count because there is no world");
			}
		}
		return world.getTotalWorldTime();
	}
	
	
}
