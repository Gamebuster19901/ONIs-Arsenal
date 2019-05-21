package com.gamebuster19901.halo.common.util;

import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public interface Updateable {
	
	public default void onTick(WorldTickEvent e) {
		if(canUpdate(e)) {
			update();
		}
	}
	
	public default boolean canUpdate(WorldTickEvent e) {
		return true;
	}
	
	public void update();
}
