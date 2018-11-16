package com.gamebuster19901.halo.common.util;

import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public interface Updateable {
	
	public default void onTick(WorldTickEvent e) {
		if(canUpdate(e)) {
			update();
		}
	}
	
	public default boolean canUpdate(WorldTickEvent e) {
		return e.world.provider.getDimension() == 0 || e.side == Side.CLIENT;
	}
	
	public void update();
}
